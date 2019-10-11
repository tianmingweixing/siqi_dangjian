package com.siqi_dangjian.controller;

import com.siqi_dangjian.bean.*;
import com.siqi_dangjian.service.impl.*;
import com.siqi_dangjian.util.CommonString;
import com.siqi_dangjian.util.CommonUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
@RequestMapping("/index")
public class IndexController extends BaseController{

    @Autowired
    private ActivityTypeService activityTypeService;

    @Autowired
    private ConfigurationService configurationService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private UserService userService;

    @Autowired
    private DutyService dutyService;

    @Autowired
    private PartyTeamService partyTeamService;

    @Autowired
    private MeetingTypeService meetingTypeService;

    @Autowired
    private MeetingService meetingService;

    @Autowired
    private PartyBranchService partyBranchService;

    Logger logger = Logger.getRootLogger();

    /**
     * 查询党员统计
     */
    @RequestMapping("total")
    @ResponseBody
    public ModelMap getUserCountInfo(){

        modelMap = new ModelMap();
        List coum = new ArrayList();
        List data = new ArrayList();

       try {
            List<Duty> list = dutyService.selectList();
           for (Duty item : list) {
               Integer count = userService.selectUserCountByTypeOrTeam("dutyid",item.getId());
               data.add(count);
               coum.add(item.getTypeName());
           }

            setData("coum", coum);
            setData("data", data);
            setSuccess();
        }catch (Exception e){
            e.printStackTrace();
            setCode(CommonString.SYSTEM_EXPECTION);
            setFail("后台异常");
        }
        return modelMap;
    }

    /**
     * 查询党组统计
     */
    @RequestMapping("totalOfTeam")
    @ResponseBody
    public ModelMap getUserCountInfoOfTeam(){

        modelMap = new ModelMap();
        List coum = new ArrayList();
        List data = new ArrayList();

        try {
            List<PartyTeam> list = partyTeamService.selectList();
            for (PartyTeam item : list) {
                Integer count = userService.selectUserCountByTypeOrTeam("party_team_id",item.getId());
                data.add(count);
                coum.add(item.getName());
            }

            setData("coum", coum);
            setData("data", data);
            setSuccess();
        }catch (Exception e){
            e.printStackTrace();
            setCode(CommonString.SYSTEM_EXPECTION);
            setFail("后台异常");
        }
        return modelMap;
    }

    /**
     * 查询今年的活动统计
     */
    @RequestMapping("getActivityCountInfo")
    @ResponseBody
    public ModelMap getActivityCountInfo(){

        modelMap = new ModelMap();
        List coum = new ArrayList();
        List data = new ArrayList();

        try {
            List<ActivitiesType> list = activityTypeService.selectList();
            for (ActivitiesType item : list) {
                Map res = new HashMap();
                Integer count = activityService.selectActivityCountByType(item.getId());
                res.put("value",count);
                res.put("name",item.getTypeName());
                data.add(res);
                coum.add(item.getTypeName());
            }

            setData("coum", coum);
            setData("data", data);
            setSuccess();
        }catch (Exception e){
            e.printStackTrace();
            setCode(CommonString.SYSTEM_EXPECTION);
            setFail("后台异常");
        }
        return modelMap;
    }

    /**
     * 查询今年的会议统计
     */
    @RequestMapping("getMeetingCountInfo")
    @ResponseBody
    public ModelMap getMeetingCountInfo(){

        modelMap = new ModelMap();
        List coum = new ArrayList();
        List data = new ArrayList();

        try {
            List<MeetingType> list = meetingTypeService.selectList();
            for (MeetingType item : list) {
                Map res = new HashMap();
                Integer count = meetingService.selectMeetingCountByType("meeting_type_id",item.getId());
                res.put("value",count);
                res.put("name",item.getTypeName());
                data.add(res);
                coum.add(item.getTypeName());
            }

            setData("coum", coum);
            setData("data", data);
            setSuccess();
        }catch (Exception e){
            e.printStackTrace();
            setCode(CommonString.SYSTEM_EXPECTION);
            setFail("后台异常");
        }
        return modelMap;
    }

    /**
     * 查询支部基本信息
     */
    @RequestMapping("getPartyInfo")
    @ResponseBody
    public ModelMap getPartyInfo(){

        modelMap = new ModelMap();
        try {
            Long partyBranchId = configurationService.selectPartyBranchId();
            PartyBranch partyBranch = partyBranchService.selectById(partyBranchId);
            if (partyBranch != null){
                /*
                Date foundingTime = partyBranch.getFoundingTime();
                String foundingTimeStr = CommonUtil.timeStrToDateStr(foundingTime.toString());
                Date changeTime = partyBranch.getFoundingTime();
                String changeTimeStr = CommonUtil.timeStrToDateStr(changeTime.toString());

                setData("name", partyBranch.getName());
                setData("foundingTime", foundingTimeStr);
                setData("changeTime", changeTimeStr);*/
                setData("info", partyBranch);
                setSuccess();
            }else {
                setCode(1);
                setFail("获取党支部信息失败");
            }

        }catch (Exception e){
            e.printStackTrace();
            setCode(CommonString.SYSTEM_EXPECTION);
            setFail("后台异常");
        }
        return modelMap;
    }
}
