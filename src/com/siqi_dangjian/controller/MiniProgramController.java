package com.siqi_dangjian.controller;

import com.siqi_dangjian.bean.*;
import com.siqi_dangjian.service.*;
import com.siqi_dangjian.service.impl.ConfigurationService;
import com.siqi_dangjian.service.impl.PartyBranchService;
import com.siqi_dangjian.service.impl.TipsService;
import com.siqi_dangjian.util.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.*;

/**
 * 微信端调用控制器
 */
@Controller
@RequestMapping("/mini")
public class MiniProgramController extends BaseController {

    @Autowired
    private IApplicationFormService applicationFormService;

    @Autowired
    private IMeetingTypeService meetingTypeService;


    @Autowired
    private IMeetingService meetingService;

    @Autowired
    private IMeetingOfUserService meetingOfUserService;


    @Autowired
    private IActivityOfUserService activityOfUserService;


    @Autowired
    private TipsService tipsService;


    @Autowired
    private IDutyService dutyService;

    @Autowired
    private IActivityService activityService;

    @Autowired
    private IActivityTypeService activityTypeService;

    @Autowired
    private ISympathyService sympathyService;

    @Autowired
    private RedisCacheManager redisCacheManager;

    @Autowired
    private INoticeService noticeService;

    @Autowired
    private IDisciplineOfHonorService disciplineOfHonorService;

    @Autowired
    private IUserService userService;

    @Autowired
    private ConfigurationService configurationService;

    @Autowired
    private PartyBranchService partyBranchService;

    Logger logger = Logger.getRootLogger();


    /**
     * @apiGroup login
     * @api {GET} /wx_login 微信登陆接口
     * @apiDescription 微信登陆接口
     * @apiParam {String} data 用户信息
     */
    @RequestMapping(value = "/wx_login", method = RequestMethod.GET)
    @ResponseBody
    public ModelMap wxLogin(HttpServletRequest request, HttpSession session, String data) {

        final long TOKEN_EXP = 1000 * 60 * 60 * 24 * 7;//过期时间,7天

        try {
            modelMap = new ModelMap();
            Long party_branch_id = configurationService.selectPartyBranchId();

            String user_code = request.getParameter("code");
            if (StringUtils.isEmpty(user_code)) {
                setFail("不合法code");
                setCode(CommonString.INVILD_CODE);
            } else {
                String str = "?appid=" + CommonString.APP_ID + "&secret=" + CommonString.APP_SECRET + "&js_code=" + user_code + "&grant_type=authorization_code";
                JSONObject object = ConnectUtil.connectByGet("https://api.weixin.qq.com/sns/jscode2session", str);
                String openId = object.getString("openid");
                String session_key = object.getString("session_key");

                //获取微信用户信息
                JSONObject wechatUserInfo = JSONObject.fromObject(data);
                String avatarUrl = wechatUserInfo.getString("avatarUrl");
                String nickName = wechatUserInfo.getString("nickName");
                String gender = wechatUserInfo.getString("gender");

                if (StringUtils.isNotEmpty(openId)) {
                    User user = userService.wxLogin(openId);
                    if (user == null) {
                        user = new User();
                        user.setOpenId(openId);
                        user.setCanUse(1);
                        user.setDutyId(1L);
                        user.setHeadImg(avatarUrl);
                    }

                    //4 . 更新sessionKey和 登陆时间
                    user.setSessionKey(session_key);
                    user.setLastTime(new Date());

                    user.setNickName(nickName);
                    user.setPartyBranchId(party_branch_id);
                    user.setSex(Integer.valueOf(gender));
                    userService.addUser(user);


                    //jwt 过期时间 7天
                    Date expiration = new Date(System.currentTimeMillis() + TOKEN_EXP);

                    //5 . JWT 返回自定义登陆态 Token
                    String token = JwtUtil.getToken(user, expiration);
                    modelMap.addAttribute("token", token);
                    User saveUser = userService.wxLogin(openId);
                    Map userInfo = userService.getUserInfoById(saveUser.getId());
                    modelMap.addAttribute("user", userInfo);
                    setSuccess();

                } else {
                    setFail("缺少参数:openId");
                    setCode(CommonString.SYSTEM_EXPECTION);
                }
            }
        } catch (Exception e) {
            logger.error("login--->wx_login", e);
            setFail("登陆错误");
            setCode(CommonString.SYSTEM_EXPECTION);
            e.printStackTrace();
        }
        return modelMap;
    }


    /**
     * @apiGroup Word
     * @api {GET} /getMeetingCategoryList 查询文档分类
     * @apiDescription 查询文档分类
     *
     * 查询文档分类
     * @return
     */
    @RequestMapping("/getMeetingCategoryList")
    @ResponseBody
    public ModelMap getMeetingCategoryList() {
        modelMap = new ModelMap();
        Map blurMap = new HashMap<>();
        Map dateMap = new HashMap<>();
        Map intMap = new HashMap<>();

        try {
            Map map = meetingTypeService.selectAllCategory(blurMap, intMap, dateMap);
            setData("list", map.get("list"));
        } catch (Exception e) {
            setFail("查询会议分类异常 X﹏X");
            setCode(CommonString.BACK_EXPECTION);
            e.printStackTrace();
            logger.error("MeetingType--->allCategory", e);
        }
        setSuccess();
        return modelMap;
    }


    /**
     * 添加入党申请
     * @param userName
     * @param phone
     * @param userId
     * @param appForm
     * @return
     */
    @RequestMapping("/addApplicationForm")
    @ResponseBody
    public ModelMap addApplicationForm( @RequestParam(value = "userName", required = false) String userName,
                                       @RequestParam(value = "token", required = false) String token,
                                       @RequestParam(value = "phone", required = false) String phone,
                                       @RequestParam(value = "userId", required = false) Long userId,
                                       @RequestParam(value = "appForm", required = false) String appForm) {
        modelMap = new ModelMap();


        ApplicationForm applicationForm;
        try {
            if (jwtUtilCheckToken(token)) return modelMap;

            if (userId == null) {
                setFail("请输入用户ID");
                setCode(CommonString.FRONT_EXPECTION);
                return modelMap;
            }

            if (phone == null) {
                setFail("请输入用户phone");
                setCode(CommonString.FRONT_EXPECTION);
                return modelMap;
            }

            if (appForm == null) {
                setFail("请输入appForm");
                setCode(CommonString.FRONT_EXPECTION);
                return modelMap;
            }

            if (userName == null) {
                setFail("请输入用户名");
                setCode(CommonString.FRONT_EXPECTION);
                return modelMap;
            }

            applicationForm = new ApplicationForm();
            applicationForm.setReviewTime(new Timestamp(new Date().getTime()));
            applicationForm.setUserName(userName);
            applicationForm.setPhone(phone);
            applicationForm.setAppFrom(appForm);
            applicationForm.setStatus(0);
            Long party_branch_id = configurationService.selectPartyBranchId();
            applicationForm.setPartyBranchId(party_branch_id);
            applicationForm.setUserId(Long.valueOf(userId));
            applicationForm.setCanUse(1);
            applicationFormService.insertOrUpdateApplicationForm(applicationForm);
            setSuccess();
            setMsg("添加入党申请成功！");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("--->addApplicationForm", e);
            setCode(CommonString.BACK_EXPECTION);
            setFail("添加入党申请错误");
        }
        return modelMap;
    }

    /**
     * @apiGroup Activity
     * @api {GET} /getActivityTipsList 获取活动心得列表
     * @apiDescription 获取活动心得列表
     * @apiParam {String} title 标题
     * @apiParam {String} userName 用户名
     * @apiParam {Integer} limit 页大小
     * @apiParam {Integer} page 页号
     */
    @RequestMapping("/getActivityTipsList")
    @ResponseBody
    public ModelMap getActivityTipsList(@RequestParam(value = "title", required = false) String title,
                                        @RequestParam(value = "userName", required = false) String userName,
                                        @RequestParam(value = "limit", required = false) Integer limit,
                                        @RequestParam(value = "page", required = false) Integer page) {
        modelMap = new ModelMap();
        try {
            Map blurMap = new HashMap<>();
            Map dateMap = new HashMap<>();
            Map intMap = new HashMap<>();

            if (StringUtils.isNotEmpty(title)) {
                blurMap.put("title", title);
            }
            if (StringUtils.isNotEmpty(userName)) {
                blurMap.put("user_name", userName);
            }

            if (limit == null || page == null) {
                setFail("缺少分页参数limit,page (°_°) (°_°)");
                setCode(CommonString.FRONT_EXPECTION);
                return modelMap;
            } else {
                Map map = tipsService.selectAll(blurMap, intMap, dateMap, limit, page);

                List list = (List<Map>) map.get("list");
                Integer count = (int) map.get("count");
                setData("data", list);
                setData("count", count);
                setMsg("查询活动心得信息成功");
                setSuccess();
            }

        } catch (Exception e) {
            e.printStackTrace();
            setCode(CommonString.FRONT_EXPECTION);
            setFail("查询活动心得信息异常 X﹏X");
        }
        return modelMap;
    }


    /**
     * @apiGroup Activity
     * @api {GET} /saveActivityTips 添加活动心得
     * @apiDescription 添加活动心得
     * @apiParam {Long} id 心得id
     * @apiParam {String} content 心得内容
     * @apiParam {Long} userId 用户id
     * @apiParam {Long} activityId 活动id
     * @apiParam {Long} party_branch_id 支部id
     */
    @ResponseBody
    @RequestMapping(value = "/saveActivityTips", method = RequestMethod.GET)
    public ModelMap saveActivityTips(@RequestParam(value = "id", required = false) Long id,
                                     @RequestParam(value = "content", required = false) String content,
                                     @RequestParam(value = "token", required = false) String token,
                                     @RequestParam(value = "userId", required = false) Long userId,
                                     @RequestParam(value = "activityId", required = false) Long activityId,
                                     @RequestParam(value = "party_branch_id", required = false) Long party_branch_id) {
        modelMap = new ModelMap();
        User user;
        Activities activities;
        Tips tips;
        try {
            //判断token：是否为空,是否过期
            if (jwtUtilCheckToken(token)) return modelMap;

            //判断用户是否存在
            if (userId != null) {
                user = userService.getUserById(userId);
                if (user == null) {
                    setFail("该用户不存在");
                    setCode(CommonString.FRONT_EXPECTION);
                    return modelMap;
                }
            } else {
                setFail("请输入用户ID");
                setCode(CommonString.FRONT_EXPECTION);
                return modelMap;
            }

            //判断活动是否存在
            if (activityId != null) {
                activities = activityService.selectById(activityId);
                if (activities == null) {
                    setFail("该活动不存在");
                    setCode(CommonString.FRONT_EXPECTION);
                    return modelMap;
                }
            } else {
                setFail("请输入活动ID");
                setCode(CommonString.FRONT_EXPECTION);
                return modelMap;
            }

            if (content == null) {
                setFail("您还没有写心得呢！");
                setCode(CommonString.FRONT_EXPECTION);
                return modelMap;
            }

            if (id != null) {
                tips = tipsService.selectById(id);
            } else {
                tips = new Tips();
            }

            tips.setActivityId(activityId);
            tips.setContent(content);
            tips.setCanUse(1);
            tips.setUserId(userId);
            tips.setPartyBranchId(1L);
            tips.setType(1);
            tips.setUserName(user.getUserName());
            tipsService.insertOrUpdate(tips);
            setFail("添加活动心得成功");
            setSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            setCode(CommonString.BACK_EXPECTION);
            setFail("添加活动心得失败");
            if (CommonString.IS_OPEN_LOG)
                logger.error("mini--->活动心得添加异常 X﹏X：", e);
        }
        return modelMap;
    }


    /**
     * 判断 token
     * @param token
     * @return
     */
    public boolean jwtUtilCheckToken(@RequestParam(value = "token", required = false) String token) {
        if (JwtUtil.checkToken(token) == 0) {
            setFail("没有token,请添加token");
            setCode(CommonString.TOKEN_CHECK_FAIL);
            return true;
        }else if (JwtUtil.checkToken(token) == 2) {
            setFail("用户登陆信息过期,请重新登陆");
            setCode(CommonString.TOKEN_CHECK_FAIL);
            return true;
        }
        return false;
    }


    /**
     * @apiGroup Meeting
     * @api {GET} /addMeetingGuide 添加会议指导
     * @apiDescription 添加会议指导
     * @apiParam {Long} id 会议id
     * @apiParam {Long} userId 指导人
     * @apiParam {Date} start_time 开始时间
     * @apiParam {Date} end_time 结束时间
     * @apiParam {String} compere 公司
     * @apiParam {String} recorder 记录人
     * @apiParam {String} people_counting 心得内容
     * @apiParam {String} attendance 心得内容
     * @apiParam {String} address 地址
     * @apiParam {String} name 名称
     * @apiParam {String} imgPath 会议图
     * @apiParam {String} guide 心得内容
     * @apiParam {String} content 内容
     * @apiParam {Long} party_branch_id 支部id
     */
    @RequestMapping("/addMeetingGuide")
    @ResponseBody
    public ModelMap addMeetingGuide(@RequestParam(value = "id", required = false) Long id,
                                    @RequestParam(value = "userId", required = false) Long userId,
                                    @RequestParam(value = "token", required = false) String token,
                                    @RequestParam(value = "start_time", required = false) Date start_time,
                                    @RequestParam(value = "end_time", required = false) Date end_time,
                                    @RequestParam(value = "compere", required = false) String compere,
                                    @RequestParam(value = "recorder", required = false) String recorder,
                                    @RequestParam(value = "people_counting", required = false) String people_counting,
                                    @RequestParam(value = "attendance", required = false) String attendance,
                                    @RequestParam(value = "address", required = false) String address,
                                    @RequestParam(value = "name", required = false) String name,
                                    @RequestParam(value = "imgPath", required = false) String images_a,
                                    @RequestParam(value = "guide", required = false) String guide,
                                    @RequestParam(value = "content", required = false) String content,
                                    @RequestParam(value = "meeting_type_id", required = false) Long meetingTypeId) {

        modelMap = new ModelMap();
        Meeting meeting;
        try {

            if (jwtUtilCheckToken(token)) return modelMap;

            if (id == null) {
                setFail("请输入会议ID");
                setCode(CommonString.FRONT_EXPECTION);
                return modelMap;
            } else {
                meeting = meetingService.selectById(id);
            }

            if (userId == null) {
                setFail("您还没有输入指导人的ID");
                setCode(CommonString.FRONT_EXPECTION);
                return modelMap;
            }

            if (guide == null || guide == "") {
                setFail("您还没有输入会议指导");
                setCode(CommonString.FRONT_EXPECTION);
                return modelMap;
            }

            meeting.setId(id);
            meeting.setUserId(userId);
            meeting.setName(name);
            meeting.setAddress(address);
            meeting.setRecorder(recorder);
            meeting.setCompere(compere);
            meeting.setAttendance(attendance);
            meeting.setPeopleCounting(people_counting);
            meeting.setContent(content);
            meeting.setGuide(guide);
            meeting.setMeetingTypeId(meetingTypeId);
            meeting.setImagesA(images_a);
            meeting.setEndTime(start_time);
            meeting.setStartTime(end_time);
            meeting.setCanUse(1);
            meetingService.insertOrUpdate(meeting);
            setMsg("会议指导添加成功");
            setSuccess();

        } catch (Exception e) {
            e.printStackTrace();
            setFail("会议指导添加失败");
            setCode(CommonString.BACK_EXPECTION);
            logger.error("mini--->会议指导添加失败：", e);

        }
        return modelMap;

    }

    /**
     * @apiGroup Meeting
     * @api {GET} /getMeetingListByUserId 获取用户会议列表
     * @apiDescription 获取用户会议列表
     * @apiParam {String} userId 用户id
     * @apiParam {Integer} limit 页大小
     * @apiParam {Integer} page 页号
     */
    @RequestMapping("/getMeetingListByUserId")
    @ResponseBody
    public ModelMap getMeetingListByUserId(@RequestParam(value = "userId", required = false) String userId,
                                        @RequestParam(value = "limit", required = false) Integer limit,
                                        @RequestParam(value = "page", required = false) Integer page) {
        modelMap = new ModelMap();
        try {
            Map blurMap = new HashMap();
            Map dateMap = new HashMap();
            Map intMap = new HashMap();

            if (StringUtils.isNotEmpty(userId)){
                intMap.put("user_id",userId);
            }

            Map map = meetingOfUserService.selectAll(blurMap, dateMap, intMap, limit, page);

            List list = (List<Map>) map.get("list");
            Integer count = (int) map.get("count");
            setData("data", list);
            setData("count", count);
            setMsg("查询会议列表信息成功");
            setSuccess();

        } catch (Exception e) {
            e.printStackTrace();
            setCode(CommonString.FRONT_EXPECTION);
            setFail("查询会议列表信息异常 X﹏X");
        }
        return modelMap;
    }




    /**
     * @apiGroup Activity
     * @api {GET} /addActivitySignIn 活动报名
     * @apiDescription 活动报名
     * @apiParam {Long} user_id 用户id
     * @apiParam {String} activity_id 活动id
     * @apiParam {String} token 令牌
     *
     * 活动报名
     */
    @RequestMapping("/addActivitySignIn")
    @ResponseBody
    public ModelMap addActivitySignIn(@RequestParam(value = "user_id", required = false) Long user_id,
                                      @RequestParam(value = "activity_id", required = false) String activity_id,
                                      @RequestParam(value = "token", required = false) String token) {

        modelMap = new ModelMap();
        ActivityOfUser activityOfUser;
        try {

            if (jwtUtilCheckToken(token)) return modelMap;

            if (user_id != null ) {
                List  list =  activityOfUserService.selectListById(user_id);
                if (activity_id == null || activity_id == "") {
                    setFail("没有活动ID(activity_id),我怎么报名ㄟ( ▔, ▔ )ㄏ");
                    return modelMap;
                }
                for(int i=0;i<list.size();i++){
                    Map map = (Map) list.get(i);
                    String activityId = String.valueOf(map.get("activity_id"));
                    if (activityId.equals(activity_id)) {
                        setFail("用户已报名!");
                        setCode(CommonString.FRONT_EXPECTION);
                        return modelMap;
                    }
                }
                activityOfUser = new ActivityOfUser();
                activityOfUser.setActivityId(Long.valueOf(activity_id));
                activityOfUser.setUserId(user_id);
                activityOfUser.setCanUse(1);
                activityOfUserService.insertOrUpdate(activityOfUser);
                setSuccess();
                setMsg("活动报名成功(>‿◠)✌");
            }else{
                setFail("没有用户ID(user_id),我怎么报名ㄟ( ▔, ▔ )ㄏ");
                return modelMap;
            }
        } catch (Exception e) {
            e.printStackTrace();
            setFail("添加活动报名异常 X﹏X");
        }
        return modelMap;
    }


    /**
     * @apiGroup Activity
     * @api {GET} /cancelActivitySignIn 取消活动报名
     * @apiDescription 取消活动报名
     * @apiParam {Long} user_id 用户id
     * @apiParam {String} activity_id 活动id
     * @apiParam {String} token 令牌
     */
    @RequestMapping("/cancelActivitySignIn")
    @ResponseBody
    public ModelMap cancelActivitySignIn(@RequestParam(value = "user_id", required = false) Long user_id,
                                         @RequestParam(value = "activity_id", required = false) String activity_id,
                                         @RequestParam(value = "token", required = false) String token) {

        modelMap = new ModelMap();
        try {

            if (jwtUtilCheckToken(token)) return modelMap;

            if (user_id != null ) {
                List  list =  activityOfUserService.selectListById(user_id);
                if (activity_id == null || activity_id == "") {
                    setMsg("没有活动ID(activity_id),我怎么取消报名ㄟ( ▔, ▔ )ㄏ");
                    return modelMap;
                }

                for(int i=0;i<list.size();i++){
                    Map map = (Map) list.get(i);
                    String activityId = String.valueOf(map.get("activity_id"));
                    if (activityId.equals(activity_id)) {
                        activityOfUserService.cancelSignIn(user_id,activity_id);
                        setSuccess();
                        setMsg("取消报名成功(>‿◠)✌");
                        return modelMap;
                    }
                }
            }else{
                setMsg("没有用户ID(user_id),我怎么取消报名ㄟ( ▔, ▔ )ㄏ");
                return modelMap;
            }
            setFail("该用户没有报名");
            setCode(CommonString.FRONT_EXPECTION);
        } catch (Exception e) {
            e.printStackTrace();
            setFail("取消报名失败");
            setCode(CommonString.BACK_EXPECTION);
        }
        return modelMap;

    }


    /**
     * @apiGroup Meeting
     * @api {GET} /addMeetingSignIn 会议签到
     * @apiDescription 会议签到
     * @apiParam {Long} user_id 用户id
     * @apiParam {String} meeting_id 会议id
     * @apiParam {String} token 令牌
     */
    @RequestMapping("/addMeetingSignIn")
    @ResponseBody
    public ModelMap addMeetingSignIn(@RequestParam(value = "user_id", required = false) Long user_id,
                                     @RequestParam(value = "meeting_id", required = false) String meeting_id,
                                     @RequestParam(value = "token", required = false) String token) {

        modelMap = new ModelMap();
        MeetingOfUser meetingOfUser;
        try {

            if (jwtUtilCheckToken(token)) return modelMap;

            if (user_id != null ) {
                List list = meetingOfUserService.selectListById(user_id);
                if (meeting_id == null || meeting_id == "") {
                    setFail("没有会议ID(meeting_id),我怎么签到ㄟ( ▔, ▔ )ㄏ");
                    return modelMap;
                }
                for (int i = 0; i < list.size(); i++) {
                    Map map = (Map) list.get(i);
                    String meetingId = String.valueOf(map.get("meeting_id"));
                    if (meetingId.equals(meeting_id)) {
                        setFail("用户已签到!");
                        setCode(CommonString.FRONT_EXPECTION);
                        return modelMap;
                    }
                }
                meetingOfUser = new MeetingOfUser();
                meetingOfUser.setMeetingId(Long.valueOf(meeting_id));
                meetingOfUser.setUserId(user_id);
                meetingOfUser.setCanUse(1);
                meetingOfUserService.insertOrUpdate(meetingOfUser);
                setSuccess();
                setMsg("添加会议签到成功 (>‿◠)✌");
            }else{
                setFail("没有用户ID(user_id),我怎么签到ㄟ( ▔, ▔ )ㄏ");
                return modelMap;
            }
        } catch (Exception e) {
            e.printStackTrace();
            setFail("添加会议签到失败");
            setCode(CommonString.BACK_EXPECTION);
            return modelMap;
        }
        return modelMap;

    }

    /**
     * @apiGroup Meeting
     * @api {GET} /cancelSignIn
     * @apiDescription 取消会议签到
     * @apiParam {Long} userId 用户id
     * @apiParam {String} meetingId 会议id
     * @apiParam {String} token 令牌
     */
    @RequestMapping("/cancelMeetingSignIn")
    @ResponseBody
    public ModelMap cancelMeetingSignIn(@RequestParam(value = "user_id", required = false) Long user_id,
                                 @RequestParam(value = "meeting_id", required = false) String meeting_id,
                                 @RequestParam(value = "token", required = false) String token
    ) {

        modelMap = new ModelMap();
        try {

            if (jwtUtilCheckToken(token)) return modelMap;

            if (user_id != null ) {
                List list = meetingOfUserService.selectListById(user_id);
                if (meeting_id == null || meeting_id == "") {
                    setFail("没有会议ID(meeting_id),我怎么取消签到ㄟ( ▔, ▔ )ㄏ");
                    return modelMap;
                }
                for (int i = 0; i < list.size(); i++) {
                    Map map = (Map) list.get(i);
                    String meetingId = String.valueOf(map.get("meeting_id"));
                    if (meetingId.equals(meeting_id)) {
                        meetingOfUserService.cancelSignIn(user_id, meeting_id);
                        setSuccess();
                        setMsg("取消签到成功 (>‿◠)✌");
                        return modelMap;
                    }
                }
            }else{
                setFail("没有用户ID(user_id),我怎么取消签到ㄟ( ▔, ▔ )ㄏ");
                return modelMap;
            }

            setFail("此用户未签到");
            setCode(CommonString.FRONT_EXPECTION);
        } catch (Exception e) {
            e.printStackTrace();
            setFail("取消会议签到失败");
            setCode(CommonString.BACK_EXPECTION);
        }
        return modelMap;
    }



    /**
     * @apiGroup Meeting
     * @api {GET} /getMeetingById 查询会议详情
     * @apiDescription 查询会议详情
     * @apiParam {String} id 会议id
     *
     */
    @RequestMapping("/getMeetingById")
    @ResponseBody
    public ModelMap getMeetingById(String id){
        modelMap = new ModelMap();

        //判断id是否为空
        if(StringUtils.isEmpty(id)){
            setFail("缺少参数会议id ㄟ( ▔, ▔ )ㄏ");
            setCode(CommonString.FRONT_EXPECTION);
            return modelMap;
        }

        try {
                Meeting meeting =  meetingService.selectById(id);
                //图片转为数组
                String imgs = (String) meeting.getImagesA();
                String[] path = imgs.split(",");
                String[] ImgArr= CommonUtil.removeArrayEmptyTextBackNewArray(path);
                meeting.setImagesA(String.valueOf(JSONArray.fromObject(ImgArr)));
                modelMap.addAttribute("meeting",meeting);
                modelMap.addAttribute("ImgArr",ImgArr);
               setSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            setFail("根据会员id查询异常 X﹏X");
            setCode(CommonString.BACK_EXPECTION);
        }
        return modelMap;

    }


    /**
     * @apiGroup Meeting
     * @api {GET} /getMeetingList 查询会议表信息
     * @apiDescription 查询会议表信息
     * @apiParam {String} name 会议名称
     * @apiParam {Integer} limit 页大小
     * @apiParam {Integer} page 页号
     *
     */
    @RequestMapping("/getMeetingList")
    @ResponseBody
    public ModelMap getMeetingList(@RequestParam(value = "name", required = false) String name,
                                   @RequestParam(value = "meeting_type_id", required = false) String meeting_type_id,
                                   @RequestParam(value = "meeting_id_search", required = false) String meeting_id_search,
                                   @RequestParam(value = "start_time", required = false) String start_time,
                                   @RequestParam(value = "end_time", required = false) String end_time,
                                   @RequestParam(value = "limit", required = false) Integer limit,
                                   @RequestParam(value = "page", required = false) Integer page) {

        modelMap = new ModelMap();

        Map blurMap = new HashMap<>();
        Map dateMap = new HashMap<>();
        Map intMap = new HashMap<>();


        if (StringUtils.isNotEmpty(meeting_type_id)) {
            intMap.put("meeting_type_id", meeting_type_id);
        }
        if (StringUtils.isNotEmpty(meeting_id_search)) {
            intMap.put("id", meeting_id_search);
        }

        if (StringUtils.isNotEmpty(name)) {
            blurMap.put("name", name);
        }

        if (StringUtils.isNotEmpty(start_time)) {
            dateMap.put("start_time", start_time);
        }

        if (StringUtils.isNotEmpty(end_time)) {
            dateMap.put("end_time", end_time);
        }

        if (limit == null || page == null) {
            setFail("缺少分页参数limit,page (°_°)");
            setCode(CommonString.FRONT_EXPECTION);
            return modelMap;
        }

        try {
            Map map = meetingService.selectAll(blurMap, intMap, dateMap, limit, page);

            List list = (List) map.get("list");
            //图片转为数组
            for (int i = 0; i < list.size(); i++) {
                Map meetingMap = (Map) list.get(i);
                String imgs = (String) meetingMap.get("images_a");
                String[] path = imgs.split(",");
                String[] ImgArr= CommonUtil.removeArrayEmptyTextBackNewArray(path);
                meetingMap.put("images_a",ImgArr);
            }

            Integer count = (int) map.get("count");

            setData("data", list);
            setData("count", count);
            setMsg("查询会议列表成功 (>‿◠)✌");
            setSuccess();
        } catch (Exception e) {
            setFail("查询会议信息失败");
            setCode(CommonString.BACK_EXPECTION);
            e.printStackTrace();
        }
        return modelMap;
    }

    /**
     * @apiGroup Activity
     * @api {GET} /getActivitysByState 根据活动状态查询活动列表
     * @apiDescription 根据活动状态查询活动列表
     * @apiParam {Integer} state 活动状态：0-全部；1-进行中；2-筹办中；3-已完成
     * @apiParam {Integer} limit 页大小
     * @apiParam {Integer} page 页号
     */
    @RequestMapping("/getActivitysByState")
    @ResponseBody
    public ModelMap getActivitysByState(@RequestParam(value = "state", required = false) Integer state,
                                    @RequestParam(value = "limit", required = false) Integer limit,
                                    @RequestParam(value = "page", required = false) Integer page) {

        modelMap = new ModelMap();


        if (state == null ) {
            setFail("缺少参数state (°_°)");
            setCode(CommonString.FRONT_EXPECTION);
            return modelMap;
        }else if (limit == null || page == null) {
            setFail("缺少分页参数limit,page (°_°)");
            setCode(CommonString.FRONT_EXPECTION);
            return modelMap;
        }

        try {

            Map map = activityService.selectActivitysByState(state, limit, page);

            List list =(List<Map>) map.get("list");
            Integer count = (int) map.get("count");
            setData("data", list);
            setData("count", count);
            setSuccess();
            setMsg("查询活动信息成功 (>‿◠)✌");
        } catch (Exception e) {
            e.printStackTrace();
            setCode(CommonString.BACK_EXPECTION);
            setFail("查询活动信息失败");
        }
        return modelMap;
    }


    /**
     * @apiGroup User
     * @api {GET} /setUser 编辑用户
     * @apiDescription 编辑用户
     * @apiParam {Long} id 用户id
     * @apiParam {String} token 令牌
     */
    @RequestMapping(value = "/setUser")
    @ResponseBody
    public ModelMap setUser(@RequestParam(value = "id", required = false) Long id,
                            @RequestParam(value = "token", required = false) String token) {

        User user;
        Duty duty;
        modelMap = new ModelMap();
        try {

            if (jwtUtilCheckToken(token)) return modelMap;


            user = userService.getUserById(id);

            if (user.getDutyId() != null) {
                duty = dutyService.selectById(user.getDutyId());
                setData("duty", duty);
            }
            setData("user", user);
            setMsg("编辑用户成功 (>‿◠)✌");
            setSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("user-->setUser", e);
            if (id == null) {
                setMsg("缺少参数：id");
            }
            setFail();
        }
        return modelMap;

    }


    /**
     * @apiGroup User
     * @api {GET} /addUser 添加或更新用户
     * @apiDescription 添加或更新用户
     * @apiParam {Long} id 用户id
     * @apiParam {String} username 用户名
     * @apiParam {Integer} sex 用户性别
     * @apiParam {String} education 学历
     * @apiParam {String} ID_cord 用户身份
     * @apiParam {String} token 令牌
     * @apiParam {String} address 地址
     * @apiParam {String} phone 用户号码
     * @apiParam {String} company 公司
     * @apiParam {Integer} age 用户年龄
     * @apiParam {Integer} dutyid 政治面貌
     * @apiParam {Date} joinTime 用户加入时间
     * @apiParam {Long} partyGroupsId 班子
     * @apiParam {Long} partyTeamId 党组
     *
     */
    @RequestMapping("/addUser")
    @ResponseBody
    public ModelMap addUser(@RequestParam(value = "id", required = false) Long id,
                            @RequestParam(value = "dutyid", required = false) Long dutyid,
                            @RequestParam(value = "partyGroupsId", required = false) Long partyGroupsId,
                            @RequestParam(value = "partyTeamId", required = false) Long partyTeamId,
                            @RequestParam(value = "username", required = false) String username,
                            @RequestParam(value = "sex", required = false) Integer sex,
                            @RequestParam(value = "education", required = false) String education,
                            @RequestParam(value = "address", required = false) String address,
                            @RequestParam(value = "company", required = false) String company,
                            @RequestParam(value = "token", required = false) String token,
                            @RequestParam(value = "age", required = false) Integer age,
                            @RequestParam(value = "ID_cord", required = false) String ID_cord,
                            @RequestParam(value = "phone", required = false) String phone,
                            @RequestParam(value = "join_time", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date joinTime) {

        modelMap = new ModelMap();
        User user;

        try {

            if (jwtUtilCheckToken(token)) return modelMap;


            if (id != null) {
                user = userService.getUserById(id);
            } else {
                user = new User();
            }

            user.setDutyId(dutyid);
            user.setPartyGroupsId(partyGroupsId);
            user.setPartyTeamId(partyTeamId);
            user.setPartyBranchId(1L);
            user.setId(id);
            user.setIDCord(ID_cord);
            user.setAddress(address);
            user.setAge(age);
            user.setCompany(company);
            user.setPhone(phone);
            user.setEducation(education);
            user.setSex(sex);
            user.setUserName(username);
            user.setJoinTime(joinTime);
            user.setCanUse(1);
            userService.addUser(user);
            setSuccess();
            setMsg("用户添加成功 (>‿◠)✌");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("user-->userAdd", e);
            setFail("用户添加失败");
            setCode(CommonString.BACK_EXPECTION);
        }
        return modelMap;
    }

    /**
     * @apiGroup User
     * @api {GET} /getUserById 根据id查询用户信息
     * @apiDescription 根据id查询用户信息和参加活动的次数以及荣誉次数
     * @apiParam {Long} id 用户id
     * @return user
     */
    @RequestMapping("/getUserById")
    @ResponseBody
    public ModelMap getUserById(@RequestParam(value = "id", required = false) Long id) {
        modelMap = new ModelMap();
        if(id == null){
            setFail("没有用户id,无法查询 ^_^||| ");
            setCode(CommonString.FRONT_EXPECTION);
            return modelMap;
        }

        try {
            Map userInfo = userService.getUserInfoById(id);
            setSuccess();
            setData("user",userInfo);
            setMsg("查询用户信息成功 (@^▽^@)✌ ");

        } catch (Exception e) {
            e.printStackTrace();
            setFail("查询用户信息异常 ^_^|||");
            setCode(CommonString.BACK_EXPECTION);
        }
        return modelMap;
    }


    /**
     * @apiGroup User
     * @api {GET} /getUserTipsById 根据id查询用户心得
     * @apiDescription 根据id查询用户参加活动的心得
     * @apiParam {Long} id 用户id
     * @apiParam {Integer} type 心得类型 1：活动心得 0： 会议心得
     * @apiParam {Integer} limit
     * @apiParam {Integer} page
     */
    @RequestMapping("/getUserTipsById")
    @ResponseBody
    public ModelMap getUserTipsById(Long id,Integer limit,Integer type,Integer page) {

        modelMap = new ModelMap();

        if(id == null ){
            setFail("没有用户id,无法查询 ^_^||| ");
            setCode(CommonString.FRONT_EXPECTION);
            return modelMap;
        }
        if(type == null){
            setFail("没有心得类型参数:type,无法查询 ^_^||| ");
            setCode(CommonString.FRONT_EXPECTION);
            return modelMap;
        }

        if (limit == null || page == null) {
            setFail("缺少分页参数limit,page (°_°)");
            setCode(CommonString.FRONT_EXPECTION);
            return modelMap;
        }

        //查用户参加的活动的心得
        try {
            Map tipsInfo = userService.getUserTipsById(id,type,limit,page);
            setSuccess();
            setData("tipsInfo",tipsInfo);
            setMsg("查询用户心得成功 (@^▽^@)✌ ");
        } catch (Exception e) {
            e.printStackTrace();
            setFail("查询用户心得异常 ^_^|||");
            setCode(CommonString.BACK_EXPECTION);
        }
        return modelMap;
    }

    /**
     * @apiGroup User
     * @api {GET} /getUserList 获取展示的党员信息列表
     * @apiDescription 获取展示的党员信息列表
     * @apiParam {String} username 用户名
     * @apiParam {String} company 公司
     * @apiParam {String} dutyid 政治面貌
     * @apiParam {String} difficulty_type 困难状态：0-非困难；1-困难；2：非常困难
     * @apiParam {String} type 查询条件：1：普通党员查询；2：查选困难党员；3：查询困难职工
     * @apiParam {String} founding_time 入党时间
     * @apiParam {String} change_time 修改时间
     * @apiParam {Integer} limit 页大小
     * @apiParam {Integer} page 页号
     */
    @RequestMapping("getUserList")
    @ResponseBody
    public ModelMap getUserList(@RequestParam(value = "username", required = false) String username,
                                @RequestParam(value = "company", required = false) String company,
                                @RequestParam(value = "dutyid", required = false) String dutyid,
                                @RequestParam(value = "difficulty_type", required = false) String difficulty_type,
                                @RequestParam(value = "type", required = false) String type,
                                @RequestParam(value = "founding_time", required = false) String founding_time,
                                @RequestParam(value = "change_time", required = false) String change_time,
                                @RequestParam(value = "limit", required = false) Integer limit,
                                @RequestParam(value = "page", required = false) Integer page) {

        modelMap = new ModelMap();
        Map blurMap = new HashMap<>();
        Map dateMap = new HashMap<>();
        Map intMap = new HashMap<>();


        if (StringUtils.isNotEmpty(company)) {
            blurMap.put("company", company);
        }

        if (StringUtils.isNotEmpty(username)) {
            blurMap.put("username", username);
        }


        if (StringUtils.isNotEmpty(founding_time)) {
            dateMap.put("founding_time", founding_time);
        }
        if (StringUtils.isNotEmpty(change_time)) {
            dateMap.put("change_time", change_time);
        }

        if (limit == null || page == null) {
            setFail("缺少分页参数limit,page (°_°)");
            setCode(CommonString.FRONT_EXPECTION);
            return modelMap;
        }

        try {

            //判断type查询所需党员信息
            //1：根据dutyId查询党员
            //2：根据困难状态查询党员
            //3：根据困难状态查询职工
            if(type.equals("1")){
                if (StringUtils.isNotEmpty(dutyid)) {
                    intMap.put("dutyid", dutyid);
                }
                Map map = userService.getUserList(blurMap, intMap, dateMap, limit, page);
                List list = (List) map.get("list");
                Integer count = (int) map.get("count");
                setData("data", list);
                setData("count", count);
                setSuccess();
                setMsg("查询用户信息成功 (>‿◠)✌");

            }else if(type.equals("2")){
                if (StringUtils.isNotEmpty(dutyid)) {
                    intMap.put("difficulty_type", difficulty_type);
                }
                List dutyIdArr = new ArrayList();
                dutyIdArr.add(5);
                dutyIdArr.add(6);
                Map map = userService.getUserListByDifficultyType(blurMap, intMap, dutyIdArr, limit, page);
                List list = (List) map.get("list");
                Integer count = (int) map.get("count");
                setData("data", list);
                setData("count", count);
                setSuccess();
                setMsg("查询用户信息成功 (>‿◠)✌");

            }else if(type.equals("3")){
                if (StringUtils.isNotEmpty(dutyid)) {
                    intMap.put("difficulty_type", difficulty_type);
                }
                List dutyIdArr = new ArrayList();
                dutyIdArr.add(2);
                dutyIdArr.add(3);
                dutyIdArr.add(4);
                Map map = userService.getUserListByDifficultyType(blurMap, intMap, dutyIdArr, limit, page);
                List list = (List) map.get("list");
                Integer count = (int) map.get("count");
                setData("data", list);
                setData("count", count);
                setSuccess();
                setMsg("查询用户信息成功 (>‿◠)✌");

            }else{
                setFail("缺少参数type");
                setCode(-1);
            }

        } catch (Exception e) {
            setFail("查询用户信息失败");
            setCode(CommonString.BACK_EXPECTION);
            e.printStackTrace();
            logger.error("user-->list", e);
        }
        return modelMap;
    }

    /**
     * @apiGroup Notice
     * @api {GET} /getNoticeTitle 公告轮播信息
     * @apiDescription 公告轮播信息
     * @apiParam {Integer} limit 页大小
     * @apiParam {Integer} page 页数
     */
    @RequestMapping("getNoticeTitle")
    @ResponseBody
    public ModelMap getNoticeTitle(@RequestParam(value = "limit", required = false) Integer limit,
                                   @RequestParam(value = "page", required = false) Integer page) {

        modelMap = new ModelMap();

        if (limit == null || page == null) {
            setFail("缺少分页参数limit,page (°_°)");
            setCode(CommonString.FRONT_EXPECTION);
            return modelMap;
        }

        try {
            Map map = noticeService.selectAll(limit, page);

            List list = (List<Notice>) map.get("list");
            setData("data", list);
            setSuccess();
            setMsg("查询公告轮播信息成功 (>‿◠)✌");
        } catch (Exception e) {
            setFail("查询公告轮播信息错误");
            setCode(CommonString.BACK_EXPECTION);
            e.printStackTrace();
            logger.error("mini-->getNoticeTitle", e);
        }
        return modelMap;
    }

    /**
     * @apiGroup Notice
     * @api {GET} /getNoticeList 获取公示公告表列表
     * @apiDescription 获取公示公告表列表
     * @apiParam {String} title 标题
     * @apiParam {String} start_time_search 开始时间
     * @apiParam {String} end_time_search 结束时间
     * @apiParam {Integer} limit 页大小
     * @apiParam {Integer} page 页数
     */
    @RequestMapping("getNoticeList")
    @ResponseBody
    public ModelMap getNoticeList(@RequestParam(value = "title", required = false) String title,
                                  @RequestParam(value = "start_time_search", required = false) String start_time,
                                  @RequestParam(value = "end_time_search", required = false) String end_time,
                                  @RequestParam(value = "limit", required = false) Integer limit,
                                  @RequestParam(value = "page", required = false) Integer page) {
        modelMap = new ModelMap();

        Map blurMap = new HashMap<>();
        Map dateMap = new HashMap<>();
        Map intMap = new HashMap<>();


        if (StringUtils.isNotEmpty(title)) {
            blurMap.put("title", title);
        }

        if (StringUtils.isNotEmpty(start_time)) {
            dateMap.put("start_time", start_time);
        }

        if (StringUtils.isNotEmpty(end_time)) {
            dateMap.put("end_time", end_time);
        }

        if (limit == null || page == null) {
            setFail("缺少分页参数limit,page (°_°)");
            setCode(CommonString.FRONT_EXPECTION);
            return modelMap;
        }

        try {
            Map map = noticeService.selectAll(blurMap, intMap, dateMap, limit, page);

            List list = (List<Notice>) map.get("list");
            Integer count = (int) map.get("count");
            setData("data", list);
            setData("count", count);
            setMsg("查询公示公告表信息成功 (>‿◠)✌");
            setSuccess();
        } catch (Exception e) {
            setFail("查询公示公告表信息错误");
            setCode(CommonString.BACK_EXPECTION);
            e.printStackTrace();
            logger.error("mini-->getNoticeList", e);
        }
        return modelMap;
    }

    /**
     * @apiGroup DisciplineOfHonor
     * @api {GET} /getDisciplineOfHonorList 获取荣誉和违纪列表
     * @apiDescription 获取荣誉和违纪列表
     * @apiParam {String} name 名称
     * @apiParam {String} type 类型---0：荣誉 1：违纪
     * @apiParam {Integer} limit 页大小
     * @apiParam {Integer} page 页数
     */
    @RequestMapping("getDisciplineOfHonorList")
    @ResponseBody
    public ModelMap getDisciplineOfHonorList(@RequestParam(value = "user_id", required = false) String user_id,
                                             @RequestParam(value = "type", required = false) String type,
                                             @RequestParam(value = "limit", required = false) Integer limit,
                                             @RequestParam(value = "page", required = false) Integer page) {

        modelMap = new ModelMap();
        Map blurMap = new HashMap<>();
        Map dateMap = new HashMap<>();
        Map intMap = new HashMap<>();

        if (StringUtils.isNotEmpty(type)) {
            intMap.put("type", type);
        }

        if (StringUtils.isNotEmpty(user_id)) {
            blurMap.put("user_id", user_id);
        }

        if (limit == null || page == null) {
            setFail("缺少分页参数limit,page (°_°) (°_°)");
            setCode(CommonString.FRONT_EXPECTION);
            return modelMap;
        }

        try {
            Map map = disciplineOfHonorService.selectAll(blurMap, intMap, dateMap, limit, page);

            List list = (List<DisciplineOfHonor>) map.get("list");
            Integer count = (int) map.get("count");
            setData("data", list);
            setData("count", count);
            setSuccess();
            setMsg("查询荣誉和违纪信息成功 (>‿◠)✌");
        } catch (Exception e) {
            setFail("查询荣誉和违纪信息错误");
            e.printStackTrace();
            logger.error("mini --> getDisciplineOfHonorList");
        }

        return modelMap;
    }

    /**
     * @apiGroup DisciplineOfHonor
     * @api {GET} /getDisciplineOfHonor 获取荣誉和违纪详情
     * @apiDescription 获取荣誉和违纪列表
     * @apiParam {String} id 名称
     */
    @RequestMapping("getDisciplineOfHonor")
    @ResponseBody
    public ModelMap getDisciplineOfHonor(@RequestParam(value = "id", required = false) Long id) {

        modelMap = new ModelMap();


        if (!StringUtils.isNotEmpty(id.toString())) {
            setFail("缺少id");
            return modelMap;
        }

        try {
            Map map = disciplineOfHonorService.selectDisciplineOfHonorInfoById(id);

            setData("data", map);
            setSuccess();
            setMsg("查询荣誉和违纪信息成功 (>‿◠)✌");
        } catch (Exception e) {
            setFail("查询荣誉和违纪信息错误");
            e.printStackTrace();
            logger.error("mini --> getDisciplineOfHonorList");
        }

        return modelMap;
    }

    /**
     * @apiGroup PartBranch
     * @api {GET} /getPartBranchList 查询党支部信息
     * @apiDescription 查询党支部信息
     * @apiParam {Integer} limit 页大小
     * @apiParam {Integer} page 页数
     */
    @RequestMapping("getPartBranchList")
    @ResponseBody
    public ModelMap getPartBranchList(@RequestParam(value = "limit", required = false) Integer limit,
                                      @RequestParam(value = "page", required = false) Integer page) {

        modelMap = new ModelMap();

        Map blurMap = new HashMap<>();
        Map dateMap = new HashMap<>();
        Map intMap = new HashMap<>();

        if (limit == null || page == null) {
            setFail("缺少分页参数limit,page (°_°) (°_°)");
            setCode(CommonString.FRONT_EXPECTION);
            return modelMap;
        }

        try {
            Map map = partyBranchService.selectAll(blurMap, intMap, dateMap, limit, page);

            List list = (List<PartyBranch>) map.get("list");
            Integer count = (int) map.get("count");
            setData("list", list);
            setData("count", count);
            setMsg("查询党支部信息成功 (>‿◠)✌");
            setSuccess();
        } catch (Exception e) {
            setFail("查询党支部信息错误");
            setCode(CommonString.BACK_EXPECTION);
            e.printStackTrace();
            logger.error("mini --> getConfig");
        }
        return modelMap;
    }

    /**
     * @apiGroup Config
     * @api {GET} /getConfig 查询系统信息
     * @apiDescription 查询系统信息或轮播
     */
    @ResponseBody
    @RequestMapping(value = "/getConfig")
    public ModelMap getSystemInfo() {
        modelMap = new ModelMap();
        try {
            Configuration comInfo = configurationService.selectById(1L);
            String[] lunbo = new String[5];
            lunbo[0] = comInfo.getImagePathA();
            lunbo[1] = comInfo.getImagePathB();
            lunbo[2] = comInfo.getImagePathC();
            lunbo[3] = comInfo.getImagePathD();
            lunbo[4] = comInfo.getImagePathE();

            setData("lunbo", lunbo);
            setData("comInfo", comInfo);
            setSuccess();
            setMsg("获取轮播信息成功 (>‿◠)✌");
        } catch (Exception e) {
            e.printStackTrace();
            setFail("获取轮播信息异常 X﹏X");
            setCode(CommonString.BACK_EXPECTION);
            logger.error("mini --> getConfig");
        }
        return modelMap;
    }

    /**
     * @apiGroup User
     * @api {GET} /selectGroupCount 用户分类统计
     * @apiDescription 用户分类统计(发展对象 2 积极分子 ； 3 预备党员 ； 4 正式党员)的数量
     */
    @RequestMapping(value = "/selectGroupCount")
    @ResponseBody
    public ModelMap selectUserGroupCount() {
        modelMap = new ModelMap();
        try {
            List list = userService.selectGroupCount();
            setData("countList", list);
            setSuccess();
            setCode(CommonString.REQUEST_SUCCESS);
        } catch (Exception e) {
            setCode(CommonString.BACK_EXPECTION);
            setFail("分组查询数量错误");
            logger.error("mini--->selectGroupCount", e);
        }
        return modelMap;
    }

    /**
     * @apiGroup Activity
     * @api {GET} /selectActivityGroupCount 活动分类统计的数量和名称
     * @apiDescription 活动分类统计的数量 (党委会、党员大会、集中学习、党日活动、廉政教育、专题讨论、特色活动、党课记录、其他)
     */
    @RequestMapping(value = "/selectActivityGroupCount")
    @ResponseBody
    public ModelMap selectActivityGroupCount() {
        modelMap = new ModelMap();
        try {

            Map map = activityService.selectActivityGroupCount();
            setData("countList", map.get("countList"));
            setSuccess();
        } catch (Exception e) {
            setCode(CommonString.BACK_EXPECTION);
            setFail("活动统计查询数量错误");
            logger.error("mini--->selectActivityGroupCount", e);
        }
        return modelMap;
    }

    /** @apiGroup Activity
     * @api {GET} /selectActivityType 查询活动分类
     * @apiDescription 查询活动分类 (党委会、党员大会、集中学习、党日活动、廉政教育、专题讨论、特色活动、党课记录、其他)
     * @return modelMap
     */
    @RequestMapping(value = "/selectActivityType")
    @ResponseBody
    public ModelMap selectActivityType() {
        modelMap = new ModelMap();
        try {
            List types =  activityTypeService.selectList();
            setData("types",types);
            setSuccess();

        } catch (Exception e) {
            setCode(CommonString.BACK_EXPECTION);
            setFail("查询活动分类错误");
            logger.error("mini--->selectActivityType", e);
        }
        return modelMap;
    }


    /** @apiGroup Activity
     * @api {GET} /selectActivityById 查询活动详情
     * @apiDescription 查询活动详情
     * @apiParam {Long} activityId 活动id
     * @return modelMap
     */
    @RequestMapping(value = "/selectActivityById")
    @ResponseBody
    public ModelMap selectActivityById(Long activityId) {
        modelMap = new ModelMap();

        if (activityId == null ) {
            setFail("缺少参数 activityId (°_°) (°_°)");
            setCode(CommonString.FRONT_EXPECTION);
            return modelMap;
        }

        try {
            Map activityInfo =  activityService.selectActivitiesInfoById(activityId);
            setData("activityInfo",activityInfo);
            setSuccess();

        } catch (Exception e) {
            e.printStackTrace();
            setCode(CommonString.BACK_EXPECTION);
            setFail("查询活动详情错误");
            logger.error("mini--->selectActivityById", e);
        }
        return modelMap;
    }


    /** @apiGroup Activity
     * @api {GET} /selectActivityByUserId 根据用户id查询个人参加的活动
     * @apiDescription 根据用户id查询个人参加的活动
     * @apiParam {Long} userId 用户id
     * @return modelMap
     */
    @RequestMapping(value = "/selectActivityByUserId")
    @ResponseBody
    public ModelMap selectActivityByUserId(Long userId, Integer limit, Integer page) {
        modelMap = new ModelMap();

        if (userId == null ) {
            setFail("缺少参数 userId (°_°)");
            setCode(CommonString.FRONT_EXPECTION);
            return modelMap;
        }

        if (limit == null || page == null) {
            setFail("缺少分页参数limit,page (°_°) (°_°)");
            setCode(CommonString.FRONT_EXPECTION);
            return modelMap;
        }

        try {
            Map userActivityInfo =  activityService.selectActivityByUserId(userId,limit,page);
            setData("userActivityInfo",userActivityInfo);
            setSuccess();

        } catch (Exception e) {
            e.printStackTrace();
            setCode(CommonString.BACK_EXPECTION);
            setFail("查询个人的活动详情错误");
            logger.error("mini--->selectActivityByUserId", e);
        }
        return modelMap;
    }


    /** @apiGroup Activity
     * @api {GET} /selectActivityByType 根据类型查询活动
     * @apiDescription 查询活动详情
     * @apiParam {Integer} activityType 活动类型
     * @return modelMap
     */
    @RequestMapping(value = "/selectActivityByType")
    @ResponseBody
    public ModelMap selectActivityByType(Integer activityType) {
        modelMap = new ModelMap();
        if (activityType == null ) {
            setFail("缺少参数 activityType (°_°)");
            setCode(CommonString.FRONT_EXPECTION);
            return modelMap;
        }
        try {
            Map res =  activityService.getActivityByType(activityType);
            setData("data",res);
            setSuccess();

        } catch (Exception e) {
            setCode(CommonString.BACK_EXPECTION);
            setFail("查询活动详情错误");
            logger.error("mini--->selectActivityByType", e);
        }
        return modelMap;
    }



    /**
     * @apiGroup Activity
     * @api {GET} /selectActivityStatusGroupCount 活动状态统计
     * @apiDescription 活动状态统计 (0 筹备，1 进行中 2 已结束)
     */
    @RequestMapping(value = "/selectActivityStatusGroupCount")
    @ResponseBody
    public ModelMap selectActivityStatusGroupCount() {
        modelMap = new ModelMap();
        try {
            Map map = activityService.selectActivityStatusGroupCount();
            setData("countList", map.get("countList"));
            setSuccess();
        } catch (Exception e) {
            setCode(CommonString.BACK_EXPECTION);
            setFail("活动状态统计异常 ");
            logger.error("mini--->selectActivityGroupCount", e);
        }
        return modelMap;
    }



}
