package com.siqi_dangjian.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.siqi_dangjian.bean.Duty;
import com.siqi_dangjian.service.IDutyService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import java.lang.reflect.Type;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/duty")
public class DutyController extends BaseController{

    @Autowired
    private IDutyService dutyService;

   Logger logger = Logger.getRootLogger();

    @RequestMapping("/gotoAdd")
    public ModelAndView gotoAdd(@RequestParam(value = "id", required = false) Long id) {
        ModelAndView view = new ModelAndView();
        view.setViewName("WEB-INF/page/duty_Add");
        return view;
    }

    /**
     * 逻辑删除
     * @return
     */
    @RequestMapping("/logicDelete")
    @ResponseBody
    public ModelMap logicDelete(@RequestParam(value="deleteArray", required=false)String deleteArray){
        modelMap = new ModelMap();
        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>() {}.getType();
        List<String> idList = gson.fromJson(deleteArray,type);
        try {
            dutyService.logicDelete(idList);
        } catch (Exception e){
            setFail("删除失败");
            logger.error("duty--->deleteDuty",e);
            return modelMap;
        }
        setSuccess();
        return modelMap;
    }

    /**
     * 查询人员分类
     * @return
     */
    @RequestMapping("/allCategory")
    @ResponseBody
    public ModelMap getAllCategoryList() {
        modelMap = new ModelMap();
        Map blurMap = new HashMap<>();
        Map dateMap = new HashMap<>();
        Map intMap = new HashMap<>();

        try {
            Map map = dutyService.selectAllCategory(blurMap, intMap, dateMap);
            setData("list", map.get("list"));
        } catch (Exception e) {
            setFail("查询失败");
            e.printStackTrace();
            logger.error("duty--->allCategory", e);
            return modelMap;
        }
        setSuccess();
        return modelMap;
    }

    /**
     * 添加或更新
     * @param id
     * @param description
     * @return
     */
    @RequestMapping("/addDuty")
    @ResponseBody
    public ModelMap addDuty(@RequestParam(value = "id", required = false) Long id,
                            @RequestParam(value = "description", required = false) String description,
                            @RequestParam(value = "type_name", required = false) String typeName
    ) {

        modelMap = new ModelMap();

        Duty duty;
        try {
            if (id != null) {
                duty = dutyService.selectById(id);
            } else {
                duty = new Duty();
            }
            duty.setDescription(description);
            duty.setTypeName(typeName);
            duty.setId(id);
            duty.setCanUse(1);
            BigInteger bigInteger = dutyService.insertOrUpdate(duty);
            long lastId = bigInteger.longValue();
            setData("lastId", lastId);
            setSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            setFail("添加职务信息失败");
            logger.error("duty--->addDuty", e);
            return modelMap;
        }
        return modelMap;

    }


    /**
     * 编辑党小组
     * @param id
     * @return
     */
    @RequestMapping("/setDuty")
    public ModelAndView setUser(@RequestParam(value = "id", required = false) Long id) {
        ModelAndView view = new ModelAndView();
        Duty duty;

        try {
            duty = dutyService.selectById(id);
            view.addObject("id", duty.getId());
            view.addObject("type_name", duty.getTypeName());
            view.addObject("description", duty.getDescription());

            view.setViewName("WEB-INF/page/duty_Add");
        } catch (Exception e) {
            e.printStackTrace();
            setMsg("获取数据错误");
        }
        return view;
    }


    /**
     * 查询党小组信息
     * @param party_duty
     * @param limit
     * @param page
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public ModelMap getDutyList(@RequestParam(value = "party_duty", required = false) String party_duty,
                                 @RequestParam(value = "id", required = false) String id,
                                 @RequestParam(value = "limit", required = false) Integer limit,
                                 @RequestParam(value = "page", required = false) Integer page) {

        modelMap = new ModelMap();

        Map blurMap = new HashMap<>();
        Map dateMap = new HashMap<>();
        Map intMap = new HashMap<>();


        if (StringUtils.isNotEmpty(party_duty)) {
            blurMap.put("party_duty", party_duty);
        }

        if (StringUtils.isNotEmpty(id)) {
            intMap.put("id", id);
        }

        try {
            Map map = dutyService.selectAll(blurMap, intMap, dateMap, limit, page);

            List list = (List<Duty>) map.get("list");
            Integer count = (int) map.get("count");
            setData("data", list);
            setData("count", count);
            setSuccess();
        } catch (Exception e) {
            setFail();
            e.printStackTrace();
        }

        return modelMap;
    }
}
