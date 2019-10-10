package com.siqi_dangjian.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.siqi_dangjian.bean.Conclusion;
import com.siqi_dangjian.service.IConclusionService;
import com.siqi_dangjian.service.IConclusionTypeService;
import com.siqi_dangjian.service.IConfigurationService;
import com.siqi_dangjian.util.CommonUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/conclusion")
public class ConclusionController extends BaseController {

    @Autowired
    private IConclusionService conclusionService;

    @Autowired
    private IConclusionTypeService conclusionTypeService;

    @Autowired
    private IConfigurationService configurationService;


    Logger logger = Logger.getRootLogger();

    @RequestMapping("/gotoAdd")
    public ModelAndView gotoAdd(@RequestParam(value = "default_type", required = false) String default_type) {
        ModelAndView view = new ModelAndView();
        if (default_type.equals("计划")){
            view.setViewName("WEB-INF/page/conclusion_Add");//跳转添加工作计划页面
        }else if(default_type.equals("总结")){
            view.setViewName("WEB-INF/page/conclusion_zj_Add");//跳转添加工作总结页面
        }
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
            conclusionService.logicDelete(idList);
        } catch (Exception e){
            setFail("删除失败");
            logger.error("conclusion--->deleteConclusion",e);
            return modelMap;
        }
        setSuccess();
        return modelMap;
    }

    /**
     * 查询文档分类
     * @param limit
     * @param page
     * @return
     */
    @RequestMapping("/allCategory")
    @ResponseBody
    public ModelMap getAllCategoryList(@RequestParam(value = "default_type", required = false) String type_name,
                                       @RequestParam(value = "limit", required = false) Integer limit,
                                       @RequestParam(value = "page", required = false) Integer page) {
        modelMap = new ModelMap();
        Map blurMap = new HashMap<>();
        Map dateMap = new HashMap<>();
        Map intMap = new HashMap<>();

        if (StringUtils.isNotEmpty(type_name)) {
            blurMap.put("type_name", type_name);
        }

        try {
            Map map = conclusionTypeService.selectAll(blurMap, intMap, dateMap, 10, 1);
            setData("list", map.get("list"));
        } catch (Exception e) {
            setFail("查询失败");
            e.printStackTrace();
            logger.error("conclusionType--->allCategory", e);
            return modelMap;
        }
        setSuccess();
        return modelMap;
    }

    /**
     * 添加或更新
     * @param id .
     * @param year_limit .
     * @param title
     * @param plan_content .
     * @return
     */
    @RequestMapping("/addConclusion")
    @ResponseBody
    public ModelMap addConclusion(@RequestParam(value = "id", required = false) Long id,
                                      @RequestParam(value = "conclusion_type_id", required = false) Long conclusionTypeId,
                                      @RequestParam(value = "year_limit", required = false)@DateTimeFormat(pattern = "yyyy-MM-dd") Date year_limit,
                                      @RequestParam(value = "title", required = false) String title,
                                      @RequestParam(value = "plan_content", required = false) String plan_content) {

        ModelMap modelMap = new ModelMap();
        Conclusion conclusion;
        try {
            Long party_branch_id = configurationService.selectPartyBranchId();

            if (id == null) {
                 conclusion = new Conclusion();
            }else{
                 conclusion = conclusionService.selectById(id);
            }
            conclusion.setId(id);
            conclusion.setConclusionTypeId(conclusionTypeId);
            conclusion.setPlanContent(plan_content);
            conclusion.setTitle(title);
            conclusion.setYearLimit(year_limit);
            conclusion.setPartyBranchId(party_branch_id);
            conclusion.setCanUse(1);
            conclusionService.insertOrUpdate(conclusion);
            setSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            setFail();
            return modelMap;
        }
        return modelMap;

    }


    /**
     * 编辑会议表
     * @param  id
     */
    @RequestMapping("/setConclusion")
    public ModelAndView setConclusion(@RequestParam(value = "Id", required = false) Long id,
                                      @RequestParam(value = "default_type", required = false) String default_type){

        ModelAndView view = new ModelAndView();
        Conclusion  conclusion;

            try {
                conclusion = conclusionService.selectById(id);
                view.addObject("id", conclusion.getId());
                view.addObject("year_limit", CommonUtil.timeFormat(conclusion.getYearLimit()));
                view.addObject("plan_content", conclusion.getPlanContent());
                view.addObject("title", conclusion.getTitle());
                view.addObject("conclusion_type_id", conclusion.getConclusionTypeId());
                if (default_type.equals("计划")){
                    view.setViewName("WEB-INF/page/conclusion_Add");//跳转添加工作计划页面
                }else if(default_type.equals("总结")){
                    view.setViewName("WEB-INF/page/conclusion_zj_Add");//跳转添加工作总结页面
                }
            } catch (Exception e) {
                e.printStackTrace();
                setMsg("获取数据错误");
            }
        return view;
    }


    /**
     * 查询会议表信息
     * @param title 标题
     * @param limit
     * @param page
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public ModelMap getConclusionList(@RequestParam(value = "title", required = false) String title,
                                      @RequestParam(value = "conclusion_type_id", required = false) String conclusion_type_id,
                                      @RequestParam(value = "default_type", required = false) String default_type,
                                      @RequestParam(value = "start_time_search", required = false) String start_time,
                                      @RequestParam(value = "end_time_search", required = false) String end_time,
                                      @RequestParam(value = "limit", required = false) Integer limit,
                                      @RequestParam(value = "page", required = false) Integer page) {

        modelMap = new ModelMap();

        Map blurMap = new HashMap<>();
        Map dateMap = new HashMap<>();
        Map intMap = new HashMap<>();


        if (StringUtils.isNotEmpty(conclusion_type_id)) {
            intMap.put("id", conclusion_type_id);
        }

        if (StringUtils.isNotEmpty(default_type)) {
            blurMap.put("type_name", default_type);
        }

        if (StringUtils.isNotEmpty(title)) {
            blurMap.put("title", title);
        }

        if (StringUtils.isNotEmpty(start_time)) {
            dateMap.put("start_time", start_time);
        }

        if (StringUtils.isNotEmpty(end_time)) {
            dateMap.put("end_time", end_time);
        }


        try {
            Map map = conclusionService.selectAll(blurMap, intMap, dateMap, limit, page);

            List list = (List<Conclusion>) map.get("list");
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
