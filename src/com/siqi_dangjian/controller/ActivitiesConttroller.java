package com.siqi_dangjian.controller;

import com.siqi_dangjian.bean.PartyBranch;
import com.siqi_dangjian.service.impl.ActivityTypeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/activities")
public class ActivitiesConttroller extends BaseController{

    @Autowired
    private ActivityTypeService activityTypeService;

    Logger logger = Logger.getRootLogger();

    /**
     * 查询管理员列表
     * @param limit
     * @param page
     * @return
     */
    @RequestMapping("listOfType")
    @ResponseBody
    public ModelMap getTypeListOfActivities(@RequestParam(value = "limit", required=false)Integer limit,
                                      @RequestParam(value = "page", required=false)Integer page){

        modelMap = new ModelMap();

        try {
            Map map = activityTypeService.selectAll(limit,page);

            List list = (List<PartyBranch>) map.get("list");
            Integer count = (int) map.get("count");
            setData("data", list);
            setData("count", count);
            setSuccess();
        }catch (Exception e){
            e.printStackTrace();
            setFail();
        }

        return modelMap;
    }
}
