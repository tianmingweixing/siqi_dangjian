package com.siqi_dangjian.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.siqi_dangjian.bean.Activities;
import com.siqi_dangjian.bean.ActivitiesType;
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

    Logger logger = Logger.getRootLogger();

    /**
     * 查询列表
     * @param title
     * @param type
     * @param start_time
     * @param end_time
     * @param limit
     * @param page
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public ModelMap getActivityList(@RequestParam(value = "title",required = false)String title,
                                    @RequestParam(value = "type",required = false)Integer type,
                                    @RequestParam(value = "start_time", required = false) String start_time,
                                    @RequestParam(value = "end_time", required = false) String end_time,
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
                view.addObject("start_time", activities.getStartTime());
                view.addObject("end_time", activities.getEndTime());
                view.addObject("is_end", activities.getIsEnd());
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
     * @param is_end
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
                                 @RequestParam(value = "start_time", required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date start_time,
                                 @RequestParam(value = "end_time", required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date end_time,
                                 @RequestParam(value = "is_end", required = false) Integer is_end,
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
            activities.setContent(content);
            activities.setIsEnd(is_end);
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
            list.add(activities.getImagePathA());
            list.add(activities.getImagePathB());
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
