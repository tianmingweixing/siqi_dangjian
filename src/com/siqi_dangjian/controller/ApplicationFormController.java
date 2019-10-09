package com.siqi_dangjian.controller;

import com.siqi_dangjian.bean.ApplicationForm;
import com.siqi_dangjian.bean.User;
import com.siqi_dangjian.dao.IDutyDao;
import com.siqi_dangjian.dao.impl.DutyDao;
import com.siqi_dangjian.service.IApplicationFormService;
import com.siqi_dangjian.service.IUserService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.*;


@Controller
@RequestMapping(value = "/applicationForm")
public class ApplicationFormController extends BaseController {

    Logger logger = Logger.getRootLogger();

    @Autowired
    private IApplicationFormService applicationFormService;

    @Autowired
    private IUserService userService;

    @Autowired
    private DutyDao dutyDao;

    @RequestMapping("/changeStatus")
    @ResponseBody
    public ModelMap changeStatus(@RequestParam(value = "id", required = false) Long id,
                                 @RequestParam(value = "status", required = false) Integer status,
                                 @RequestParam(value = "refuse_reason", required = false) String refuseReason) {
        modelMap = new ModelMap();


        ApplicationForm applicationForm;
        try {

            applicationForm = applicationFormService.getApplicationFormById(id);

            if (applicationForm == null) {
                applicationForm = new ApplicationForm();
            }
            applicationForm.setStatus(status);
            applicationForm.setReviewTime(new Timestamp(new Date().getTime()));
            applicationForm.setId(id);
            applicationForm.setCanUse(1);
            if(status == 1){
               Long userId = applicationForm.getUserId();
               if(userId != null){
                 User user = userService.getUserById(userId);
                 Long dutyId = dutyDao.selectByTypeName("正式党员");
                 user.setDutyId(dutyId);
               }

            }else if(status == 2){
                applicationForm.setRefuseReason(refuseReason);
            }
            applicationFormService.insertOrUpdateApplicationForm(applicationForm);
            setSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("good--->changeHot", e);
            setFail();
        }
        return modelMap;
    }

    /**
     * 添加入党申请
     *
     * @param id
     * @param userName
     * @param phone
     * @param userId
     * @param appForm
     * @return
     */
    @RequestMapping("/addApplicationForm")
    @ResponseBody
    public ModelMap addApplicationForm(@RequestParam(value = "id", required = false) Long id,
                                       @RequestParam(value = "userName", required = false) String userName,
                                       @RequestParam(value = "phone", required = false) String phone,
                                       @RequestParam(value = "userId", required = false) String userId,
                                       @RequestParam(value = "appForm", required = false) String appForm) {
        modelMap = new ModelMap();


        ApplicationForm applicationForm;
        try {

            applicationForm = applicationFormService.getApplicationFormById(id);

            if (applicationForm == null) {
                applicationForm = new ApplicationForm();
            }
            applicationForm.setReviewTime(new Timestamp(new Date().getTime()));
            applicationForm.setId(id);
            applicationForm.setUserName(userName);
            applicationForm.setPhone(phone);
            applicationForm.setAppFrom(appForm);
            applicationForm.setUserId(Long.valueOf(userId));
            applicationForm.setCanUse(1);
            applicationFormService.insertOrUpdateApplicationForm(applicationForm);
            setSuccess();
            setMsg("添加入党申请成功");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("good--->changeHot", e);
            setFail();
        }
        return modelMap;
    }

    /**
     * @param phone
     * @param user_name
     * @param status
     * @param start_time
     * @param end_time
     * @param limit
     * @param page
     * @return
     */
    @RequestMapping(value = "getApplicationFormList")
    @ResponseBody
    public ModelMap getApplicationFormList(@RequestParam(value = "phone", required = false) String phone,
                                           @RequestParam(value = "user_name", required = false) String user_name,
                                           @RequestParam(value = "status", required = false) Integer status,
                                           @RequestParam(value = "start_time", required = false) String start_time,
                                           @RequestParam(value = "end_time", required = false) String end_time,
                                           @RequestParam(value = "limit", required = false) Integer limit,
                                           @RequestParam(value = "page", required = false) Integer page) {
        modelMap = new ModelMap();

        Map blurMap = new HashMap<>();
        Map dateMap = new HashMap<>();
        Map intMap = new HashMap<>();

        if (StringUtils.isNotEmpty(phone)) {
            intMap.put("phone", phone);
        }
        if (StringUtils.isNotEmpty(user_name)) {
            blurMap.put("user_name", user_name);
        }
        if (status != null) {
            intMap.put("status", status.toString());
        }
        if (StringUtils.isNotEmpty(start_time)) {
            dateMap.put("start_time", start_time);
        }
        if (StringUtils.isNotEmpty(end_time)) {
            dateMap.put("end_time", end_time);
        }

        try {

            Map map = applicationFormService.getApplicationFormList(limit, page, blurMap, intMap, dateMap);

            List list = (List) map.get("list");
            Integer count = (int) map.get("count");
            setData("data", list);
            setData("count", count);
            setSuccess();
            setMsg("查询列表成功");
        } catch (Exception e) {
            e.printStackTrace();
            setFail();
            setMsg("查询列表异常");
        }
        return modelMap;
    }

    /* *//**
     * 逻辑删除商品
     * @return
     *//*
    @RequestMapping("/deleteNewProductReview")
    @ResponseBody
    public ModelMap deleteNewProductReview(@RequestParam(value="deleteArray", required=false)String deleteArray){
        modelMap = new ModelMap();
        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>() {}.getType();
        List<String> jsonList = gson.fromJson(deleteArray,type);
        try {
            newProductReviewService.deleteNewProductReview(jsonList);
        } catch (Exception e){
            setFail("删除失败");
            logger.error("good--->deleteGood",e);
            return modelMap;
        }
        setSuccess();
        return modelMap;
    }
*/


}
