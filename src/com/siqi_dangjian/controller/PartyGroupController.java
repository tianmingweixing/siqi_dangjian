package com.siqi_dangjian.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.siqi_dangjian.bean.PartyGroup;
import com.siqi_dangjian.service.IPartyGroupService;
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
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 支部班子控制器
 */
@Controller
@RequestMapping("/partyGroup")
public class PartyGroupController extends BaseController{

    @Autowired
    private IPartyGroupService partyGroupService;

    Logger logger = Logger.getRootLogger();

    @RequestMapping("/gotoAdd")
    public ModelAndView gotoAdd() {
        ModelAndView view = new ModelAndView();
        view.setViewName("WEB-INF/page/partyGroup_Add");
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
            partyGroupService.logicDelete(idList);
        } catch (Exception e){
            setFail("删除失败");
            logger.error("partyGroup--->deletePartyGroup",e);
            return modelMap;
        }
        setSuccess();
        return modelMap;
    }


    /**
     * 添加或更新支部班子信息
     * @param name
     * @param duty
     * @param foundingTime
     * @param changeTime
     */
    @RequestMapping("/addPartyGroup")
    @ResponseBody
    public ModelMap addPartyGroup(@RequestParam(value = "id", required = false) Long id,
                                      @RequestParam(value = "name", required = false) String name,
                                      @RequestParam(value = "duty", required = false) String duty,
                                      @RequestParam(value = "foundingTime", required = false)@DateTimeFormat(pattern = "yyyy-MM-dd")Date foundingTime,
                                      @RequestParam(value = "changeTime", required = false)@DateTimeFormat(pattern = "yyyy-MM-dd") Date changeTime){

        ModelMap modelMap = new ModelMap();

        try {
            PartyGroup partyGroup = new PartyGroup();
            partyGroup.setId(id);
            partyGroup.setDuty(duty);
            partyGroup.setFoundingTime(foundingTime);
            partyGroup.setChangeTime(changeTime);
            partyGroup.setName(name);
            partyGroup.setCanUse(1);
            partyGroupService.insertOrUpdate(partyGroup);
            setSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            setFail("addPartyGroup错误");
            return modelMap;
        }
        return modelMap;

    }


    /**
     * 编辑支部班子
     * @param id
     * @return
     */
    @RequestMapping("/setPartyGroup")
    public ModelAndView setPartyGroup(@RequestParam(value = "id", required = false) Long id) {
        ModelAndView view = new ModelAndView();
        PartyGroup  partyGroup;

            try {
                partyGroup = partyGroupService.selectById(id);
                view.addObject("id", partyGroup.getId());
                view.addObject("name", partyGroup.getName());
                view.addObject("partyGroupNo", partyGroup.getPartyGroupNo());
                view.addObject("partyNo", partyGroup.getPartyNo());
                view.addObject("duty", partyGroup.getDuty());
                view.addObject("foundingTime", partyGroup.getFoundingTime());
                view.addObject("changeTime", partyGroup.getChangeTime());

                view.setViewName("WEB-INF/page/partyGroup_Add");
            } catch (Exception e) {
                e.printStackTrace();
                setMsg("获取数据错误");
            }
        return view;
    }


    /**
     * 查询支部班子信息
     * @param name
     * @param founding_time
     * @param change_time
     * @param limit
     * @param page
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public ModelMap getPartyGroupList(@RequestParam(value = "name",required = false)String name,
                                      @RequestParam(value = "partyGroupNo",required = false)String partyGroupNo,
                                      @RequestParam(value = "founding_time",required = false)@DateTimeFormat(pattern = "yyyy-MM-dd")Date founding_time,
                                      @RequestParam(value = "change_time",required = false)@DateTimeFormat(pattern = "yyyy-MM-dd")Date change_time,
                                      @RequestParam(value="limit", required=false)Integer limit,
                                      @RequestParam(value="page", required=false)Integer page){

        modelMap = new ModelMap();

        Map blurMap = new HashMap<>();
        Map dateMap = new HashMap<>();
        Map intMap  = new HashMap<>();


        if(StringUtils.isNotEmpty(partyGroupNo)) {
            intMap.put("party_group_no", partyGroupNo);
        }

        if(StringUtils.isNotEmpty(name)) {
            blurMap.put("name", name);
        }

        if(StringUtils.isNotEmpty(String.valueOf(founding_time))) {
            dateMap.put("founding_time", founding_time);
        }
        if(StringUtils.isNotEmpty(String.valueOf(change_time))) {
            dateMap.put("change_time", change_time);
        }
        try {
            Map map = partyGroupService.selectAll(blurMap,intMap,dateMap,limit,page);

            List list = (List<PartyGroup>) map.get("list");
            Integer count = (int) map.get("count");
            setData("data", list);
            setData("count", count);
            setSuccess();
        }catch (Exception e){
            setFail();
            e.printStackTrace();
        }

        return modelMap;
    }
}
