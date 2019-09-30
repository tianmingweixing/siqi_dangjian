package com.siqi_dangjian.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.siqi_dangjian.bean.PartyTeam;
import com.siqi_dangjian.service.IConfigurationService;
import com.siqi_dangjian.service.IPartyTeamService;
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


@Controller
@RequestMapping("/partyTeam")
public class PartyTeamController extends BaseController{

    @Autowired
    private IPartyTeamService partyTeamService;

    @Autowired
    private IConfigurationService configurationService;

    Logger logger = Logger.getRootLogger();

    @RequestMapping("/gotoAdd")
    public ModelAndView gotoAdd(@RequestParam(value = "id", required = false) Long id) {
        ModelAndView view = new ModelAndView();
        view.setViewName("WEB-INF/page/partyTeam_Add");
        return view;
    }

    /**
     * 查询党小组分类
     * @return
     */
    @RequestMapping("/allCategory")
    @ResponseBody
    public ModelMap getAllCategoryList() {
        modelMap = new ModelMap();

        try {
            Map map = partyTeamService.selectAllCategory();
            setData("list", map.get("list"));
        } catch (Exception e) {
            setFail("查询失败");
            e.printStackTrace();
            logger.error("partyTeam--->allCategory", e);
            return modelMap;
        }
        setSuccess();
        return modelMap;
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
            partyTeamService.logicDelete(idList);
        } catch (Exception e){
            setFail("删除失败");
            logger.error("partyTeam--->deletePartyTeam",e);
            return modelMap;
        }
        setSuccess();
        return modelMap;
    }


    /**
     * 添加或更新党小组信息
     * @param name
     * @param partyGroupNo
     * @param duty
     * @param partyNo
     * @param foundingTime
     * @param changeTime
     */
    @RequestMapping("/addPartyTeam")
    @ResponseBody
    public ModelMap addPartyTeam(@RequestParam(value = "id", required = false) Long id,
                                      @RequestParam(value = "name", required = false) String name,
                                      @RequestParam(value = "partyGroupNo", required = false) String partyGroupNo,
                                      @RequestParam(value = "duty", required = false) String duty,
                                      @RequestParam(value = "partyNo", required = false) String partyNo,
                                      @RequestParam(value = "foundingTime", required = false)@DateTimeFormat(pattern = "yyyy-MM-dd") Date foundingTime,
                                      @RequestParam(value = "changeTime", required = false)@DateTimeFormat(pattern = "yyyy-MM-dd") Date changeTime){

        ModelMap modelMap = new ModelMap();

        try {
            Long party_branch_id = configurationService.selectPartyBranchId();

            PartyTeam partyTeam = new PartyTeam();
            partyTeam.setId(id);
            partyTeam.setPartyBranchId(party_branch_id);
            partyTeam.setPartyGroupNo(partyGroupNo);
            partyTeam.setPartyNo(partyNo);
            partyTeam.setDuty(duty);
            partyTeam.setFoundingTime(foundingTime);
            partyTeam.setChangeTime(changeTime);
            partyTeam.setName(name);
            partyTeam.setCanUse(1);
            partyTeamService.insertOrUpdate(partyTeam);
            setSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            setFail();
            return modelMap;
        }
        return modelMap;

    }


    /**
     * 编辑党小组
     * @param id
     * @return
     */
    @RequestMapping("/setPartyTeam")
    public ModelAndView setPartyTeam(@RequestParam(value = "id", required = false) Long id) {
        ModelAndView view = new ModelAndView();
        PartyTeam  partyTeam;

            try {
                partyTeam = partyTeamService.selectById(id);
                view.addObject("id", partyTeam.getId());
                view.addObject("name", partyTeam.getName());
                view.addObject("partyGroupNo", partyTeam.getPartyGroupNo());
                view.addObject("partyNo", partyTeam.getPartyNo());
                view.addObject("duty", partyTeam.getDuty());
                view.addObject("foundingTime", partyTeam.getFoundingTime());
                view.addObject("changeTime", partyTeam.getChangeTime());

                view.setViewName("WEB-INF/page/partyTeam_Add");
//                view.setViewName("frame/PartyTeam_Add");
            } catch (Exception e) {
                e.printStackTrace();
                setMsg("获取数据错误");
            }
        return view;
    }


    /**
     * 查询党小组信息
     * @param name
     * @param founding_time
     * @param change_time
     * @param limit
     * @param page
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public ModelMap getPartyTeamList(@RequestParam(value = "name",required = false)String name,
                                      @RequestParam(value = "partyGroupNo",required = false)String partyGroupNo,
                                      @RequestParam(value = "founding_time",required = false)String founding_time,
                                      @RequestParam(value = "change_time",required = false)String change_time,
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

        if(StringUtils.isNotEmpty(founding_time)) {
            dateMap.put("founding_time", founding_time);
        }
        if(StringUtils.isNotEmpty(change_time)) {
            dateMap.put("change_time", change_time);
        }
        try {
            Map map = partyTeamService.selectAll(blurMap,intMap,dateMap,limit,page);

            List list = (List<PartyTeam>) map.get("list");
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
