package com.siqi_dangjian.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.siqi_dangjian.bean.ConclusionType;
import com.siqi_dangjian.bean.MeetingType;
import com.siqi_dangjian.service.IConclusionTypeService;
import com.siqi_dangjian.service.IMeetingTypeService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 会议类型控制器
 */
@Controller
@RequestMapping("/meetingType")
public class MeetingTypeController extends BaseController {

    @Autowired
    private IMeetingTypeService meetingTypeService;


    Logger logger = Logger.getRootLogger();

    @RequestMapping("/gotoAdd")
    public ModelAndView gotoAdd() {
        ModelAndView view = new ModelAndView();
        view.setViewName("WEB-INF/page/meetingType_Add");
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
            meetingTypeService.logicDelete(idList);
        } catch (Exception e){
            setFail("删除失败");
            logger.error("meetingType--->deleteMeetingType",e);
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
    @RequestMapping("/addMeetingType")
    public ModelAndView addMeetingType(@RequestParam(value = "id", required = false) Long id,
                                      @RequestParam(value = "type_name", required = false) String typeName) {

        ModelAndView modelAndView = new ModelAndView();
        MeetingType meetingType;
        try {
            if (id == null) {
                 meetingType = new MeetingType();
            }else{
                 meetingType = meetingTypeService.selectById(id);
            }
            meetingType.setId(id);
            meetingType.setTypeName(typeName);
            meetingType.setCanUse(1);
            meetingTypeService.insertOrUpdate(meetingType);

            setSuccess();
            modelAndView.setViewName("frame/meetingTypeList");

        } catch (Exception e) {
            e.printStackTrace();
            modelAndView.setViewName("WEB-INF/page/meetingType_Add");
            return modelAndView;
        }
        return modelAndView;

    }


    /**
     * 编辑总结（计划）类型表
     * @param  id
     */
    @RequestMapping("/setMeetingType")
    public ModelAndView setMeetingType(@RequestParam(value = "Id", required = false) Long id){

        ModelAndView view = new ModelAndView();
        MeetingType  meetingType;

            try {
                meetingType = meetingTypeService.selectById(id);
                view.addObject("id", meetingType.getId());
                view.addObject("type_name", meetingType.getTypeName());
                view.addObject("party_branch_id", meetingType.getPartyBranchId());

                view.setViewName("WEB-INF/page/meetingType_Add");
            } catch (Exception e) {
                e.printStackTrace();
                setMsg("获取数据错误");
            }
        return view;
    }

    /**
     * 查询文档分类
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
            Map map = meetingTypeService.selectAllCategory(blurMap, intMap, dateMap);
            setData("list", map.get("list"));
        } catch (Exception e) {
            setFail("查询失败");
            e.printStackTrace();
            logger.error("MeetingType--->allCategory", e);
            return modelMap;
        }
        setSuccess();
        return modelMap;
    }


    /**
     * 查询总结（计划）类型表信息
     * @param limit
     * @param page
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public ModelMap getMeetingTypeList(@RequestParam(value = "limit", required = false) Integer limit,
                                      @RequestParam(value = "page", required = false) Integer page) {

        modelMap = new ModelMap();

        Map blurMap = new HashMap<>();
        Map dateMap = new HashMap<>();
        Map intMap = new HashMap<>();


        try {
            Map map = meetingTypeService.selectAll(blurMap, intMap, dateMap, limit, page);

            List list = (List<MeetingType>) map.get("list");
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
