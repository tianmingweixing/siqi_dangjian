package com.siqi_dangjian.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.siqi_dangjian.bean.ActivitiesBrand;
import com.siqi_dangjian.service.IActivityBrandService;
import com.siqi_dangjian.service.IConfigurationService;
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
 * 活动品牌表
 */
@Controller
@RequestMapping("/activtiesBrand")
public class ActivtiesBrandController extends BaseController {

    @Autowired
    private IActivityBrandService activityBrandService;

    @Autowired
    private IConfigurationService configurationService;

    Logger logger = Logger.getRootLogger();

    /**
     * 查询活动品牌列表
     * @param limit
     * @param page
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public ModelMap getActivityBrandList(@RequestParam(value = "limit", required=false)Integer limit,
                                            @RequestParam(value = "page", required=false)Integer page){

        modelMap = new ModelMap();

        try {
            Map map = activityBrandService.selectAll(limit,page);

            List list = (List<ActivitiesBrand>) map.get("list");
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
                ActivitiesBrand activitiesBrand = activityBrandService.selectById(id);
                view.addObject("id", activitiesBrand.getId());
                view.addObject("brand_name", activitiesBrand.getBrandName());
                view.addObject("party_branch_id", activitiesBrand.getPartyBranchId());
                view.addObject("create_time", activitiesBrand.getCreateTime());
            }

            view.setViewName("/frame/activityBrand_Add");
            setSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            setCode(CommonString.SYSTEM_EXPECTION);
            setFail("后台异常");
            if(CommonString.IS_OPEN_LOG)
                logger.error("activityBrand--->goto异常：",e);
        }

        return view;
    }

    /**
     * 添加活动品牌
     * @param id
     * @param brand_name
     * @param party_branch_id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelMap saveActivityBrand(@RequestParam(value = "id", required = false) Long id,
                              @RequestParam(value = "brand_name", required = false) String brand_name,
                              @RequestParam(value = "party_branch_id", required = false) Long party_branch_id) {
        modelMap = new ModelMap();
        try {
            party_branch_id = configurationService.selectPartyBranchId();

            ActivitiesBrand activitiesBrand= new ActivitiesBrand();
            if (id != null){
                activitiesBrand = activityBrandService.selectById(id);
            }

            activitiesBrand.setBrandName(brand_name);
            activitiesBrand.setPartyBranchId(party_branch_id);
            activitiesBrand.setCanUse(1);
            activityBrandService.insertOrUpdate(activitiesBrand);
            setMsg("添加成功");
            setSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            setCode(CommonString.SYSTEM_EXPECTION);
            setFail("添加失败");
            if(CommonString.IS_OPEN_LOG)
                logger.error("ActivitiesBrand--->活动品牌-添加异常：",e);
        }
        return modelMap;
    }

    /**
     * 删除活动品牌
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ModelMap deleteActivityBrand(@RequestParam(value="deleteArray", required=false)String deleteArray){
        modelMap = new ModelMap();
        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>() {}.getType();
        List<String> jsonList = gson.fromJson(deleteArray,type);
        try {
            activityBrandService.delete(jsonList);
            setSuccess();
        } catch (Exception e){
            setFail("删除失败");
            if(CommonString.IS_OPEN_LOG)
                logger.error("ActivitiesBrand--->活动品牌-删除异常：",e);
        }

        return modelMap;
    }


    /**
     * 获取活动分类List
     * @return
     */
    @RequestMapping("/selectBrandList")
    @ResponseBody
    public ModelMap selectBrandList(){
        modelMap = new ModelMap();

        try {
            List<ActivitiesBrand> res = activityBrandService.selectList();
            setData("list",res);
            setSuccess();
        } catch (Exception e){
            setFail("获取活动分类失败");
            if(CommonString.IS_OPEN_LOG)
                logger.error("ActivitiesBrand--->获取活动品牌List-异常：",e);
        }

        return modelMap;
    }


}
