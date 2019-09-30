package com.siqi_dangjian.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.siqi_dangjian.bean.Activities;
import com.siqi_dangjian.bean.Tips;
import com.siqi_dangjian.bean.User;
import com.siqi_dangjian.service.IConfigurationService;
import com.siqi_dangjian.service.impl.ActivityService;
import com.siqi_dangjian.service.impl.TipsService;
import com.siqi_dangjian.service.impl.UserService;
import com.siqi_dangjian.util.CommonString;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/tips")
public class ActivitiesTipsController extends BaseController{

    @Autowired
    private TipsService tipsService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private UserService userService;

    @Autowired
    private IConfigurationService configurationService;

    Logger logger = Logger.getRootLogger();

     /**
     * 查询列表
     * @param title
     * @param userName
     * @param limit
     * @param page
     * @return
     */
    @RequestMapping("list")
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
            Map map = tipsService.selectAll(blurMap,intMap,dateMap,limit,page);

            List list = (List<Map>) map.get("list");
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
                Tips tips = tipsService.selectById(id);
                view.addObject("id", tips.getId());
                view.addObject("activityId", tips.getActivityId());
                view.addObject("content", tips.getContent());
                view.addObject("party_branch_id", tips.getPartyBranchId());
                view.addObject("userId", tips.getUserId());
                view.addObject("userName", tips.getUserName());
                view.addObject("create_time", tips.getCreateTime());

                Activities activity = activityService.selectById(tips.getActivityId());
                if (activity != null){
                    view.addObject("title", activity.getTitle());
                }
            }

            view.setViewName("/frame/activityTips_Add");
            setSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            setCode(CommonString.SYSTEM_EXPECTION);
            setFail("后台异常");
            if(CommonString.IS_OPEN_LOG)
                logger.error("tips--->goto异常：",e);
        }

        return view;
    }

    /**
     * 添加心得
     * @param id
     * @param content
     * @param userId
     * @param activityId
     * @param party_branch_id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelMap saveActivityTips(@RequestParam(value = "id", required = false) Long id,
                                 @RequestParam(value = "content", required = false) String content,
                                 @RequestParam(value = "userId", required = false) Long userId,
                                 @RequestParam(value = "activityId", required = false) Long activityId,
                                 @RequestParam(value = "party_branch_id", required = false) Long party_branch_id) {
        modelMap = new ModelMap();
        User user = new User();
        Activities activities;
        Tips tips = new Tips();
        try {
            party_branch_id = configurationService.selectPartyBranchId();
            //判断用户是否存在
            if (userId != null){
                user = userService.getUserById(userId);
                if (user == null){
                    setFail("该用户不存在");
                    setCode(1);
                    return modelMap;
                }
            }

            //判断活动是否存在
            if (activityId != null){
                activities = activityService.selectById(activityId);
                if (activities == null){
                    setFail("该活动不存在");
                    setCode(2);
                    return modelMap;
                }
            }

            if (id != null){
                tips = tipsService.selectById(id);
            }

            tips.setActivityId(activityId);
            tips.setContent(content);
            tips.setCanUse(1);
            tips.setUserId(userId);
            tips.setPartyBranchId(party_branch_id);
            tips.setType(1);
            tips.setUserName(user.getUserName());
            tipsService.insertOrUpdate(tips);
            setMsg("添加成功");
            setSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            setCode(CommonString.SYSTEM_EXPECTION);
            setFail("添加失败");
            if(CommonString.IS_OPEN_LOG)
                logger.error("tips--->活动心得-添加异常：",e);
        }
        return modelMap;
    }

    /**
     * 删除活动心得
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ModelMap deleteActivityTips(@RequestParam(value="deleteArray", required=false)String deleteArray){
        modelMap = new ModelMap();
        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>() {}.getType();
        List<String> jsonList = gson.fromJson(deleteArray,type);
        try {
            tipsService.delete(jsonList);
            setSuccess();
        } catch (Exception e){
            setFail("删除失败");
            if(CommonString.IS_OPEN_LOG)
                logger.error("tips--->活动心得-删除异常：",e);
        }
        return modelMap;
    }

}
