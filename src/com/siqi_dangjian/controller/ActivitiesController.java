package com.siqi_dangjian.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.siqi_dangjian.bean.Activities;
import com.siqi_dangjian.bean.ActivitiesType;
import com.siqi_dangjian.bean.ActivityOfUser;
import com.siqi_dangjian.bean.MeetingOfUser;
import com.siqi_dangjian.service.IActivityOfUserService;
import com.siqi_dangjian.service.impl.ActivityService;
import com.siqi_dangjian.service.impl.ActivityTypeService;
import com.siqi_dangjian.util.CommonString;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Type;
import java.util.*;

@Controller
@RequestMapping("/activities")
public class ActivitiesController extends BaseController{

    @Autowired
    private ActivityTypeService activityTypeService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private IActivityOfUserService activityOfUserService;

    Logger logger = Logger.getRootLogger();


    /**
     * 会议签到
     * @return
     */
    @RequestMapping("/signIn")
    @ResponseBody
    public ModelMap addMeetingSignIn(@RequestParam(value = "user_id", required = false) Long user_id,
                                     @RequestParam(value = "activity_id", required = false) String activity_id) {

        modelMap = new ModelMap();
        ActivityOfUser activityOfUser;
        try {
            if (StringUtils.isNotEmpty(String.valueOf(user_id))) {
                List  list =  activityOfUserService.selectListById(user_id);

                for(int i=0;i<list.size();i++){
                    Map map = (Map) list.get(i);
                    String activityId = String.valueOf(map.get("activity_id"));
                    if (activityId.equals(activity_id)) {
                        setMsg("用户已报名");
                        return modelMap;
                    }
                }
                activityOfUser = new ActivityOfUser();
                activityOfUser.setActivityId(Long.valueOf(activity_id));
                activityOfUser.setUserId(user_id);
                activityOfUser.setCanUse(1);
                activityOfUserService.insertOrUpdate(activityOfUser);
                setSuccess();
            }
        } catch (Exception e) {
            e.printStackTrace();
            setFail("添加活动报名异常");
            return modelMap;
        }
        return modelMap;
    }


    /**
     * 取消活动报名
     * @return
     */
    @RequestMapping("/cancelSignIn")
    @ResponseBody
    public ModelMap cancelSignIn(@RequestParam(value = "user_id", required = false) Long user_id,
                                 @RequestParam(value = "activity_id", required = false) String activity_id) {

        modelMap = new ModelMap();
        try {
            if (StringUtils.isNotEmpty(String.valueOf(user_id))) {
                List  list =  activityOfUserService.selectListById(user_id);

                for(int i=0;i<list.size();i++){
                    Map map = (Map) list.get(i);
                    String activityId = String.valueOf(map.get("activity_id"));
                    if (activityId.equals(activity_id)) {
                        activityOfUserService.cancelSignIn(user_id,activity_id);
                        setSuccess();
                        setMsg("取消报名成功!");
                        return modelMap;
                    }
                }
            }
            setFail("该用户没有签到");
            setCode(CommonString.FRONT_EXPECTION);
        } catch (Exception e) {
            e.printStackTrace();
            setFail("取消报名失败");
            setCode(CommonString.BACK_EXPECTION);
            return modelMap;
        }
        return modelMap;

    }

    /**
     * 查询列表
     * @param title
     * @param type_id
     * @param start_time
     * @param end_time
     * @param limit
     * @param page
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public ModelMap getActivityList(@RequestParam(value = "title",required = false)String title,
                                    @RequestParam(value = "type",required = false)String type_id,
                                    @RequestParam(value = "brand_id",required = false)String brand_id,
                                    @RequestParam(value = "activity_id",required = false)String activity_id,
                                    @RequestParam(value = "start_time", required = false) String start_time,
                                    @RequestParam(value = "end_time", required = false) String end_time,
                                    @RequestParam(value = "limit", required=false)Integer limit,
                                    @RequestParam(value = "page", required=false)Integer page){

        modelMap = new ModelMap();

        try {
            Map blurMap = new HashMap<>();
            Map dateMap = new HashMap<>();
            Map intMap  = new HashMap<>();


            if(type_id != null && type_id != "") {
                intMap.put("type_id", type_id);
            }
            if(activity_id != null && activity_id != "") {
                intMap.put("id", activity_id);
            }
            if(brand_id != null && brand_id != "") {
                intMap.put("brand_id", brand_id);
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

            Map map = activityService.selectAll(blurMap,intMap,dateMap,limit,page);

            List list = (List<ActivitiesType>) map.get("list");
            Integer count = (int) map.get("count");
            setData("data", list);
            setData("count", count);
            setSuccess();
        }catch (Exception e){
            e.printStackTrace();
            setCode(CommonString.SYSTEM_EXPECTION);
            setFail("后台异常");
        }

        return modelMap;
    }

    @RequestMapping(value = "/goto")
    public ModelAndView addOrEidt(@RequestParam(value = "id", required = false) Long id) {
        ModelAndView view = new ModelAndView();
        try {
            if (id != null) {
                Activities activities = activityService.selectById(id);

                view.addObject("id", activities.getId());
                view.addObject("title", activities.getTitle());
                view.addObject("party_branch_id", activities.getPartyBranchId());
                view.addObject("create_time", activities.getCreateTime());
                view.addObject("content", activities.getContent());
                view.addObject("typeId", activities.getTypeId());
                view.addObject("brandId", activities.getBrandId());
                view.addObject("start_time", activities.getStartTime());
                view.addObject("end_time", activities.getEndTime());
                view.addObject("status", activities.getStatus());
                String userNameStr =  activityService.selectSignInById(id);
                view.addObject("userNameStr", userNameStr);
                List<String> imgarr = new ArrayList<>();
                imgarr.add(activities.getImagePathA());
                imgarr.add(activities.getImagePathB());
                view.addObject("imgarr", imgarr);
                view.addObject("review", activities.getReview());
            }

            view.setViewName("/frame/activity_Add");
            setSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            setCode(CommonString.SYSTEM_EXPECTION);
            setFail("后台异常");
            if(CommonString.IS_OPEN_LOG)
                logger.error("activity--->goto异常：",e);
        }

        return view;
    }

    /**
     * 添加活动
     * @param id
     * @param title
     * @param content
     * @param type_id
     * @param start_time
     * @param end_time
     * @param status
     * @param image_path_a
     * @param image_path_b
     * @param party_branch_id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelMap saveActivity(@RequestParam(value = "id", required = false) Long id,
                                 @RequestParam(value = "title", required = false) String title,
                                 @RequestParam(value = "content", required = false) String content,
                                 @RequestParam(value = "type_id", required = false) Long type_id,
                                 @RequestParam(value = "brand_id", required = false) Long brand_id,
                                 @RequestParam(value = "start_time", required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date start_time,
                                 @RequestParam(value = "end_time", required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date end_time,
                                 @RequestParam(value = "status", required = false) Integer status,
                                 @RequestParam(value = "image_path_a", required = false) String image_path_a,
                                 @RequestParam(value = "image_path_b", required = false) String image_path_b,
                                 @RequestParam(value = "party_branch_id", required = false) Long party_branch_id) {
        modelMap = new ModelMap();
        try {
            Activities activities = new Activities();
            if (id != null){
                activities = activityService.selectById(id);
            }

            activities.setTitle(title);
            activities.setPartyBranchId(1L);
            activities.setCanUse(1);
            activities.setTypeId(type_id);
            activities.setBrandId(brand_id);
            activities.setContent(content);
            activities.setStatus(status);
            activities.setImagePathA(image_path_a);
            activities.setImagePathB(image_path_b);
            activities.setStartTime(start_time);
            activities.setEndTime(end_time);
            activityService.insertOrUpdate(activities);
            setMsg("添加成功");
            setSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            setCode(CommonString.SYSTEM_EXPECTION);
            setFail("添加失败");
            if(CommonString.IS_OPEN_LOG)
                logger.error("cctivity--->活动分类-添加异常：",e);
        }
        return modelMap;
    }

    /**
     * 删除活动
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ModelMap deleteActivity(@RequestParam(value="deleteArray", required=false)String deleteArray){
        modelMap = new ModelMap();
        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>() {}.getType();
        List<String> jsonList = gson.fromJson(deleteArray,type);
        try {
            activityService.delete(jsonList);
            setSuccess();
        } catch (Exception e){
            setFail("删除失败");
            if(CommonString.IS_OPEN_LOG)
                logger.error("admin--->活动-删除异常：",e);
        }

        return modelMap;
    }

    /**
     * 删除活动
     * @return
     */
    @RequestMapping("/getActivities")
    @ResponseBody
    public ModelMap getActivities(@RequestParam(value="id", required=false)Long id){
        modelMap = new ModelMap();
        try {
            Activities activities = activityService.selectById(id);
            List list = new ArrayList();

            if(activities.getImagePathA() != null){
                if (!activities.getImagePathA().equals(""))
                    list.add(activities.getImagePathA());
            }
            if(activities.getImagePathB() != null){
                if (!activities.getImagePathB().equals(""))
                    list.add(activities.getImagePathB());
            }
            setSuccess();
            setData("list",list);
        } catch (Exception e){
            setFail("删除失败");
            if(CommonString.IS_OPEN_LOG)
                logger.error("admin--->活动-删除异常：",e);
        }

        return modelMap;
    }
}
