package com.siqi_dangjian.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.siqi_dangjian.bean.ActivitiesType;
import com.siqi_dangjian.service.IActivityTypeService;
import com.siqi_dangjian.util.CommonString;
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
import java.util.List;
import java.util.Map;

/**
 * 总结（计划）类型表
 */
@Controller
@RequestMapping("/activityType")
public class ActivtiesTypeController extends BaseController {

    @Autowired
    private IActivityTypeService activityTypeService;

    Logger logger = Logger.getRootLogger();

    /**
     * 查询管理员列表
     * @param limit
     * @param page
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public ModelMap getActivityTypeList(@RequestParam(value = "limit", required=false)Integer limit,
                                            @RequestParam(value = "page", required=false)Integer page){

        modelMap = new ModelMap();

        try {
            Map map = activityTypeService.selectAll(limit,page);

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
                ActivitiesType activitiesType = activityTypeService.selectById(id);
                view.addObject("id", activitiesType.getId());
                view.addObject("type_name", activitiesType.getTypeName());
                view.addObject("party_branch_id", activitiesType.getPartyBranchId());
                view.addObject("create_time", activitiesType.getCreateTime());
            }

            view.setViewName("/frame/activityType_Add");
            setSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            setCode(CommonString.SYSTEM_EXPECTION);
            setFail("后台异常");
            if(CommonString.IS_OPEN_LOG)
                logger.error("activityType--->goto异常：",e);
        }

        return view;
    }

    /**
     * 添加活动分类
     * @param id
     * @param type_name
     * @param party_branch_id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelMap saveActivityType(@RequestParam(value = "id", required = false) Long id,
                              @RequestParam(value = "type_name", required = false) String type_name,
                              @RequestParam(value = "party_branch_id", required = false) Long party_branch_id) {
        modelMap = new ModelMap();
        try {
            ActivitiesType activitiesType= new ActivitiesType();
            if (id != null){
                activitiesType = activityTypeService.selectById(id);
            }

            activitiesType.setTypeName(type_name);
            activitiesType.setPartyBranchId(1L);
            activitiesType.setCanUse(1);
            activityTypeService.insertOrUpdate(activitiesType);
            setMsg("添加成功");
            setSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            setCode(CommonString.SYSTEM_EXPECTION);
            setFail("添加失败");
            if(CommonString.IS_OPEN_LOG)
                logger.error("cctivityType--->活动分类-添加异常：",e);
        }
        return modelMap;
    }

    /**
     * 删除活动分类
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ModelMap deleteActivityType(@RequestParam(value="deleteArray", required=false)String deleteArray){
        modelMap = new ModelMap();
        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>() {}.getType();
        List<String> jsonList = gson.fromJson(deleteArray,type);
        try {
            activityTypeService.delete(jsonList);
            setSuccess();
        } catch (Exception e){
            setFail("删除失败");
            if(CommonString.IS_OPEN_LOG)
                logger.error("activityType--->活动分类-删除异常：",e);
        }

        return modelMap;
    }


    /**
     * 获取活动分类List
     * @return
     */
    @RequestMapping("/selectTypeList")
    @ResponseBody
    public ModelMap selectTypeList(){
        modelMap = new ModelMap();

        try {
            List<ActivitiesType> res = activityTypeService.selectList();
            setData("list",res);
            setSuccess();
        } catch (Exception e){
            setFail("获取活动分类失败");
            if(CommonString.IS_OPEN_LOG)
                logger.error("activityType--->获取活动分类List-异常：",e);
        }

        return modelMap;
    }


}
