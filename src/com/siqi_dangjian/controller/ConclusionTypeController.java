package com.siqi_dangjian.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.siqi_dangjian.bean.ConclusionType;
import com.siqi_dangjian.service.IConclusionTypeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 总结（计划）类型表
 */
@Controller
@RequestMapping("/conclusionType")
public class ConclusionTypeController extends BaseController {

    @Autowired
    private IConclusionTypeService conclusionTypeService;

    Logger logger = Logger.getRootLogger();

    @RequestMapping("/gotoAdd")
    public ModelAndView gotoAdd(@RequestParam(value = "id", required = false) Long id) {
        ModelAndView view = new ModelAndView();
        view.setViewName("WEB-INF/page/conclusionType_Add");
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
            conclusionTypeService.logicDelete(idList);
        } catch (Exception e){
            setFail("删除失败");
            logger.error("ConclusionType--->deleteConclusionType",e);
            return modelMap;
        }
        setSuccess();
        return modelMap;
    }



    /**
     * 添加或更新
     * @param id .
     * @return modelAndView
     */
    @RequestMapping("/addConclusionType")
    public ModelAndView addConclusion(@RequestParam(value = "id", required = false) Long id,
                                      @RequestParam(value = "type", required = false) Integer type,
                                      @RequestParam(value = "type_name", required = false) String typeName) {

        ModelAndView modelAndView = new ModelAndView();

        try {
            ConclusionType conclusionType = new ConclusionType();
            conclusionType.setId(id);
            conclusionType.setTypeName(typeName);
            conclusionType.setType(type);
            conclusionType.setCanUse(1);
            conclusionTypeService.insertOrUpdate(conclusionType);

            setSuccess();
            modelAndView.setViewName("frame/conclusionTypeList");

        } catch (Exception e) {
            e.printStackTrace();
            modelAndView.setViewName("WEB-INF/page/conclusionType_Add");
            return modelAndView;
        }
        return modelAndView;

    }


    /**
     * 编辑总结（计划）类型表
     * @param  id
     */
    @RequestMapping("/setConclusionType")
    public ModelAndView setConclusion(@RequestParam(value = "Id", required = false) Long id){

        ModelAndView view = new ModelAndView();
        ConclusionType  conclusionType;

            try {
                conclusionType = conclusionTypeService.selectById(id);
                view.addObject("id", conclusionType.getId());
                view.addObject("type_name", conclusionType.getTypeName());
                view.addObject("type", conclusionType.getType());
                view.addObject("party_branch_id", conclusionType.getPartyBranchId());

                view.setViewName("WEB-INF/page/conclusionType_Add");
            } catch (Exception e) {
                e.printStackTrace();
                setMsg("获取数据错误");
            }
        return view;
    }


    /**
     * 查询总结（计划）类型表信息
     * @param limit
     * @param page
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public ModelMap getConclusionList(@RequestParam(value = "limit", required = false) Integer limit,
                                      @RequestParam(value = "page", required = false) Integer page) {

        modelMap = new ModelMap();

        Map blurMap = new HashMap<>();
        Map dateMap = new HashMap<>();
        Map intMap = new HashMap<>();


        try {
            Map map = conclusionTypeService.selectAll(blurMap, intMap, dateMap, limit, page);

            List list = (List<ConclusionType>) map.get("list");
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
