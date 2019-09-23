package com.siqi_dangjian.controller;

import com.siqi_dangjian.bean.*;
import com.siqi_dangjian.service.*;
import com.siqi_dangjian.service.impl.ConfigurationService;
import com.siqi_dangjian.service.impl.PartyBranchService;
import com.siqi_dangjian.service.impl.TipsService;
import com.siqi_dangjian.util.*;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * 微信端调用控制器
 */
@Controller
@RequestMapping("/mini")
public class MiniProgramController extends BaseController {

    @Autowired
    private IMeetingService meetingService;

    @Autowired
    private IMeetingOfUserService meetingOfUserService;


    @Autowired
    private TipsService tipsService;


    @Autowired
    private IDutyService dutyService;

    @Autowired
    private IActivityService activityService;

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

    @RequestMapping(value = "/wx_login", method = RequestMethod.GET)
    @ResponseBody
    public ModelMap wxLogin(HttpServletRequest request, HttpSession session, String data) {

        final long TOKEN_EXP = 1000 * 60 * 60 * 24 * 7;//过期时间,7天

        try {
            modelMap = new ModelMap();

            String user_code = request.getParameter("code");
            if (StringUtils.isEmpty(user_code)) {
                setFail("不合法code");
                setCode(CommonString.INVILD_CODE);
            } else {
                String str = "?appid=" + CommonString.APP_ID + "&secret=" + CommonString.APP_SECRET + "&js_code=" + user_code + "&grant_type=authorization_code";
                JSONObject object = ConnectUtil.connectByGet("https://api.weixin.qq.com/sns/jscode2session", str);
                String openId = object.getString("openid");
                String session_key = object.getString("session_key");

                if (StringUtils.isNotEmpty(openId)) {
                    User user = userService.wxLogin(openId);
                    if (user == null) {
                        user = new User();
                        user.setOpenId(openId);
                        user.setCanUse(1);
                    }

                    //4 . 更新sessionKey和 登陆时间U
                    user.setSessionKey(session_key);
                    user.setLastTime(new Date());

                    //获取微信用户信息
                    JSONObject wechatUserInfo = JSONObject.fromObject(data);
                    String avatarUrl = wechatUserInfo.getString("avatarUrl");
                    String nickName = wechatUserInfo.getString("nickName");
                    String gender = wechatUserInfo.getString("gender");
                    user.setHeadImg(avatarUrl);
                    user.setNickName(nickName);
                    user.setSex(Integer.valueOf(gender));
                    userService.addUser(user);

                    //jwt 过期时间 7天
                    Date expiration = new Date(System.currentTimeMillis() + TOKEN_EXP);

                    //5 . JWT 返回自定义登陆态 Token
                    String token = JwtUtil.getToken(user, expiration);
                    modelMap.addAttribute("token", token);
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
     * 查询心得表信息
     * @param title
     * @param userName
     * @param limit
     * @param page
     * @return
     */
    @RequestMapping("/getActivityTipsList")
    @ResponseBody
    public ModelMap getActivityTipsList(@RequestParam(value = "title",required = false)String title,
                                        @RequestParam(value = "userName",required = false)String userName,
                                        @RequestParam(value = "limit", required=false)Integer limit,
                                        @RequestParam(value = "page", required=false)Integer page){
        modelMap = new ModelMap();
        try {
            Map blurMap = new HashMap<>();
            Map dateMap = new HashMap<>();
            Map intMap  = new HashMap<>();

            if(StringUtils.isNotEmpty(title)) {
                blurMap.put("title", title);
            }
            if(StringUtils.isNotEmpty(userName)) {
                blurMap.put("user_name", userName);
            }

            if(limit == null || page == null){
                setFail("缺少分页参数limit,page");
                setCode(CommonString.FRONT_EXPECTION);
            }else{
                Map map = tipsService.selectAll(blurMap,intMap,dateMap,limit,page);

                List list = (List<Map>) map.get("list");
                Integer count = (int) map.get("count");
                setData("data", list);
                setData("count", count);
                setMsg("查询活动心得信息成功");
                setSuccess();
            }

        }catch (Exception e){
            e.printStackTrace();
            setCode(CommonString.FRONT_EXPECTION);
            setFail("查询活动心得信息异常");
        }
        return modelMap;
    }


    /**
     * 添加活动心得
     * @param id
     * @param content
     * @param userId
     * @param activityId
     * @param party_branch_id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/saveActivityTips", method = RequestMethod.POST)
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

            if (!JwtUtil.checkToken(token)) {
                setFail("用户登陆信息过期,请重新登陆");
                setCode(CommonString.TOKEN_CHECK_FAIL);
                return modelMap;
            }

            //判断用户是否存在
            if (userId != null){
                user = userService.getUserById(userId);
                if (user == null){
                    setFail("该用户不存在");
                    setCode(CommonString.FRONT_EXPECTION);
                    return modelMap;
                }
            }else{
                setFail("请输入用户ID");
                setCode(CommonString.FRONT_EXPECTION);
                return modelMap;
            }

            //判断活动是否存在
            if (activityId != null){
                activities = activityService.selectById(activityId);
                if (activities == null){
                    setFail("该活动不存在");
                    setCode(CommonString.FRONT_EXPECTION);
                    return modelMap;
                }
            }else{
                setFail("请输入活动ID");
                setCode(CommonString.FRONT_EXPECTION);
                return modelMap;
            }

            if (content == null) {
                setFail("您还没有写心得呢！");
                setCode(CommonString.FRONT_EXPECTION);
                return modelMap;
            }

            if (id != null){
                tips = tipsService.selectById(id);
            }else{
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
            if(CommonString.IS_OPEN_LOG)
            logger.error("mini--->活动心得添加异常：",e);
        }
        return modelMap;
    }


    /**
     * 添加会议指导
     * @param id .
     * @param start_time .
     * @param guide  会议指导
     * @param name .
     * @param content .
     * @param meetingTypeId  1：支委会；2：党员大会；3：廉政...
     * @return
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

            if (!JwtUtil.checkToken(token)) {
                setFail("用户登陆信息过期,请重新登陆");
                setCode(CommonString.TOKEN_CHECK_FAIL);
                return modelMap;
            }

            if (id == null) {
                setFail("请输入会议ID");
                setCode(CommonString.FRONT_EXPECTION);
                return modelMap;
            }else{
                meeting = meetingService.selectById(id);
            }

            if (userId == null) {
                setFail("您还没有输入指导人的ID");
                setCode(CommonString.FRONT_EXPECTION);
                return modelMap;
            }

            if (guide == null) {
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
            logger.error("mini--->会议指导添加失败：",e);

        }
        return modelMap;

    }



    /**
     * 会议签到
     * @return
     */
    @RequestMapping("/signIn")
    @ResponseBody
    public ModelMap addMeetingSignIn(@RequestParam(value = "user_id", required = false) Long user_id,
                                     @RequestParam(value = "meeting_id", required = false) String meeting_id,
                                     @RequestParam(value = "token", required = false) String token) {

        modelMap = new ModelMap();
        MeetingOfUser meetingOfUser;
        try {

            if (!JwtUtil.checkToken(token)) {
                setFail("用户登陆信息过期,请重新登陆");
                setCode(CommonString.TOKEN_CHECK_FAIL);
                return modelMap;
            }

            if (StringUtils.isNotEmpty(String.valueOf(user_id))) {
                List  list =  meetingOfUserService.selectListById(user_id);

                for(int i=0;i<list.size();i++){
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
                setMsg("添加会议签到成功");
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
     * 取消会议签到
     * @return
     */
    @RequestMapping("/cancelSignIn")
    @ResponseBody
    public ModelMap cancelSignIn(@RequestParam(value = "userId", required = false) Long user_id,
                                     @RequestParam(value = "meetingId", required = false) String meeting_id,
                                     @RequestParam(value = "token", required = false) String token
    ) {

        modelMap = new ModelMap();
        MeetingOfUser meetingOfUser;
        try {

            if (!JwtUtil.checkToken(token)) {
                setFail("用户登陆信息过期,请重新登陆");
                setCode(CommonString.TOKEN_CHECK_FAIL);
                return modelMap;
            }

            if (StringUtils.isNotEmpty(String.valueOf(user_id))) {
                List  list =  meetingOfUserService.selectListById(user_id);

                for(int i=0;i<list.size();i++){
                    Map map = (Map) list.get(i);
                    String meetingId = String.valueOf(map.get("meeting_id"));
                    if (meetingId.equals(meeting_id)) {
                        meetingOfUserService.cancelSignIn(user_id,meeting_id);
                        setSuccess();
                        setMsg("取消签到成功");
                    }else{
                        setFail("此用户未签到!");
                        return modelMap;
                    }
                }
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
     * 查询活动列表
     * @param title
     * @param type
     * @param start_time
     * @param end_time
     * @param token
     * @param limit
     * @param page
     * @return
     */
    @RequestMapping("/getActivityList")
    @ResponseBody
    public ModelMap getActivityList(@RequestParam(value = "title",required = false)String title,
                                    @RequestParam(value = "type",required = false)Integer type,
                                    @RequestParam(value = "start_time", required = false) String start_time,
                                    @RequestParam(value = "end_time", required = false) String end_time,
                                    @RequestParam(value = "token", required = false) String token,
                                    @RequestParam(value = "limit", required=false)Integer limit,
                                    @RequestParam(value = "page", required=false)Integer page){

        modelMap = new ModelMap();

        try {


            Map blurMap = new HashMap<>();
            Map dateMap = new HashMap<>();
            Map intMap  = new HashMap<>();


            if(type != null) {
                intMap.put("type", type.toString());
            }
            if(StringUtils.isNotEmpty(title)) {
                blurMap.put("title", title);
            }
            if(StringUtils.isNotEmpty(start_time)) {
                dateMap.put("start_time", start_time);
            }
            if(StringUtils.isNotEmpty(end_time)) {
                dateMap.put("end_time", end_time);
            }

            if(limit != null && page != null){
                setFail("缺少分页参数limit,page");
                setCode(CommonString.FRONT_EXPECTION);
                return modelMap;
            }

            Map map = activityService.selectAll(blurMap,intMap,dateMap,limit,page);

            List list = (List<ActivitiesType>) map.get("list");
            Integer count = (int) map.get("count");
            setData("data", list);
            setData("count", count);
            setSuccess();
            setMsg("查询活动信息成功");
        }catch (Exception e){
            e.printStackTrace();
            setCode(CommonString.BACK_EXPECTION);
            setFail("查询用户信息失败");
        }

        return modelMap;
    }





    /**
     * 编辑用户
     * @param  id
     * @return
     */
    @RequestMapping(value = "/setUser")
    @ResponseBody
    public ModelMap setUser(@RequestParam(value = "id", required = false) Long id,
                            @RequestParam(value = "token", required = false) String token) {

        User user;
        Duty duty;

        try {

            if (!JwtUtil.checkToken(token)) {
                setFail("用户登陆信息过期,请重新登陆");
                setCode(CommonString.TOKEN_CHECK_FAIL);
                return modelMap;
            }

            modelMap = new ModelMap();
            user = userService.getUserById(id);

            if (user.getDutyId() != null) {
                duty = dutyService.selectById(user.getDutyId());
                setData("duty", duty);
            }
            setData("user", user);
            setMsg("编辑用户成功");
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
     * 模糊查询用户信息
     * @param username
     * @param founding_time
     * @param change_time
     * @param limit
     * @param page
     * @return List
     */
    @RequestMapping("getUserList")
    @ResponseBody
    public ModelMap getUserList(@RequestParam(value = "username", required = false) String username,
                                @RequestParam(value = "company", required = false) String company,
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

        if(limit != null && page != null){
            setFail("缺少分页参数limit,page");
            setCode(CommonString.FRONT_EXPECTION);
            return modelMap;
        }

        try {
            Map map = userService.getUserList(blurMap, intMap, dateMap, limit, page);
            List list = (List<PartyTeam>) map.get("list");
            Integer count = (int) map.get("count");
            setData("data", list);
            setData("count", count);
            setSuccess();
            setMsg("查询用户信息成功");
        } catch (Exception e) {
            setFail("查询用户信息失败");
            setCode(CommonString.BACK_EXPECTION);
            e.printStackTrace();
            logger.error("user-->list", e);
        }
        return modelMap;
    }

    /**
     * 公告轮播信息
     * @param limit
     * @param page
     * @return
     */
    @RequestMapping("getNoticeTitle")
    @ResponseBody
    public ModelMap getNoticeTitle(@RequestParam(value = "limit", required = false) Integer limit,
                                  @RequestParam(value = "page", required = false) Integer page) {

        modelMap = new ModelMap();

        if(limit != null && page != null){
            setFail("缺少分页参数limit,page");
            setCode(CommonString.FRONT_EXPECTION);
            return modelMap;
        }

        try {
            Map map = noticeService.selectAll(limit, page);

            List list = (List<Notice>) map.get("list");
            setData("data", list);
            setSuccess();
            setMsg("查询公告轮播信息成功");
        } catch (Exception e) {
            setFail("查询公告轮播信息错误");
           setCode(CommonString.BACK_EXPECTION);
            e.printStackTrace();
            logger.error("mini-->getNoticeTitle",e);
        }
        return modelMap;
    }

    /**
     * 查询公示公告表信息
     * @param title 标题
     * @param limit
     * @param page
     * @return
     */
    @RequestMapping("list")
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

        if(limit != null && page != null){
            setFail("缺少分页参数limit,page");
            setCode(CommonString.FRONT_EXPECTION);
            return modelMap;
        }

        try {
            Map map = noticeService.selectAll(blurMap, intMap, dateMap, limit, page);

            List list = (List<Notice>) map.get("list");
            Integer count = (int) map.get("count");
            setData("data", list);
            setData("count", count);
            setMsg("查询公示公告表信息成功");
            setSuccess();
        } catch (Exception e) {
            setFail("查询公示公告表信息错误");
            setCode(CommonString.BACK_EXPECTION);
            e.printStackTrace();
            logger.error("mini-->getNoticeList",e);
        }
        return modelMap;
    }


    /**
     * 查询荣誉和违纪表
     * 类型  0：荣誉   1：违纪
     * @param limit
     * @param page
     * @return
     */
    @RequestMapping("getDisciplineOfHonorList")
    @ResponseBody
    public ModelMap getDisciplineOfHonorList(@RequestParam(value = "name", required = false) String name,
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

        if (StringUtils.isNotEmpty(name)) {
            blurMap.put("name", name);
        }

        try {
            Map map = disciplineOfHonorService.selectAll(blurMap, intMap, dateMap, limit, page);

            List list = (List<DisciplineOfHonor>) map.get("list");
            Integer count = (int) map.get("count");
            setData("data", list);
            setData("count", count);
            setSuccess();
            setMsg("查询荣誉和违纪信息成功");
        } catch (Exception e) {
            setFail("查询荣誉和违纪信息错误");
            e.printStackTrace();
            if(limit != null & page != null){
                setMsg("查询信息失败");
            }
            setMsg("缺少分页参数limit,page");
            logger.error("mini --> getDisciplineOfHonorList");
        }

        return modelMap;
    }

    /**
     * 查询党支部信息
     * @param limit
     * @param page
     * @return
     */
    @RequestMapping("getPartBranchList")
    @ResponseBody
    public ModelMap getPartBranchList(@RequestParam(value="limit", required=false)Integer limit,
                                      @RequestParam(value="page", required=false)Integer page){

        modelMap = new ModelMap();

        Map blurMap = new HashMap<>();
        Map dateMap = new HashMap<>();
        Map intMap = new HashMap<>();

        if(limit != null && page != null){
            setFail("缺少分页参数limit,page");
            setCode(CommonString.FRONT_EXPECTION);
            return modelMap;
        }

        try {
            Map map = partyBranchService.selectAll(blurMap, intMap, dateMap, limit, page);

            List list = (List<PartyBranch>) map.get("list");
            Integer count = (int) map.get("count");
            setData("list", list);
            setData("count", count);
            setMsg("查询党支部信息成功");
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
     * 获取系统信息
     * @return
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
            setMsg("获取轮播信息成功");
        }catch (Exception e){
            e.printStackTrace();
            setFail("获取轮播信息异常");
            setCode(CommonString.BACK_EXPECTION);
            logger.error("mini --> getConfig");
        }
        return modelMap;
    }

    /**
     * 用户分类统计(发展对象 2 积极分子；3 预备党员；4 正式党员)的数量
     * @return modelMap
     */
    @RequestMapping(value = "/selectGroupCount")
    @ResponseBody
    public ModelMap selectUserGroupCount() {
        modelMap = new ModelMap();
        try {
        Map map =  userService.selectGroupCount();
        setData("countList",map.get("countList"));
        setSuccess();
        setCode(CommonString.REQUEST_SUCCESS);
        } catch (Exception e) {
            setCode(CommonString.BACK_EXPECTION);
            setFail("分组查询数量错误");
            logger.error("mini--->selectGroupCount", e);
            return modelMap;
        }
        return modelMap;
    }

    /**
     * 活动分类统计的数量 (党委会、党员大会、集中学习、党日活动、廉政教育、专题讨论、特色活动、党课记录、其他)
     * @return modelMap
     */
    @RequestMapping(value = "/selectActivityGroupCount")
    @ResponseBody
    public ModelMap selectActivityGroupCount() {
        modelMap = new ModelMap();
        try {
        Map map =  activityService.selectActivityGroupCount();
        setData("countList",map.get("countList"));
        setSuccess();
//        setCode(CommonString.REQUEST_SUCCESS);
        } catch (Exception e) {
            setCode(CommonString.BACK_EXPECTION);
            setFail("活动统计查询数量错误");
            logger.error("mini--->selectActivityGroupCount", e);
            return modelMap;
        }
        return modelMap;
    }




}
