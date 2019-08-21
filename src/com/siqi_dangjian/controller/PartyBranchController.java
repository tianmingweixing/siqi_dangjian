package com.siqi_dangjian.controller;


import com.siqi_dangjian.bean.PartyBranch;
import com.siqi_dangjian.service.IPartyBranchService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import java.sql.Timestamp;
import java.util.*;


@Controller
@RequestMapping("/partyBranch")
public class PartyBranchController extends BaseController{

    @Autowired
    private IPartyBranchService partyBranchService;


    /**
     * 添加或更新党支部信息
     * @param name
     * @param partyMemberCount
     * @param duty
     * @param partyNo
     * @param partyInfo
     * @param partyImg
     * @param activityArea
     * @param foundingTime
     * @param changeTime
     */
    @RequestMapping("/addPartBranch")
    @ResponseBody
    public ModelAndView addPartBranch(@RequestParam(value = "name", required = false) String name,
                              @RequestParam(value = "id", required = false) Long id,
                              @RequestParam(value = "partyMemberCount", required = false) Integer partyMemberCount,
                              @RequestParam(value = "duty", required = false) String duty,
                              @RequestParam(value = "partyNo", required = false) String partyNo,
                              @RequestParam(value = "partyInfo", required = false) String partyInfo,
                              @RequestParam(value = "partyImg", required = false) String partyImg,
                              @RequestParam(value = "activityArea", required = false) Double activityArea,
                              @RequestParam(value = "foundingTime", required = false) Timestamp foundingTime,
                              @RequestParam(value = "changeTime", required = false) Timestamp changeTime){

        ModelAndView modelAndView = new ModelAndView();


        try {
            PartyBranch partyBranch = new PartyBranch();
            partyBranch.setId(id);
            partyBranch.setActivityArea(activityArea);
            partyBranch.setPartyNo(partyNo);
            partyBranch.setDuty(duty);
            partyBranch.setFoundingTime(foundingTime);
            partyBranch.setChangeTime(changeTime);
            partyBranch.setName(name);
            partyBranch.setPartyImg(partyImg);
            partyBranch.setPartyInfo(partyInfo);
            partyBranch.setPartyMemberCount(partyMemberCount);
            partyBranch.setCanUse(1);
            partyBranchService.insertOrUpdate(partyBranch);
            setSuccess();
            modelAndView.setViewName("frame/partyBranchList");
        } catch (Exception e) {
            e.printStackTrace();
            setFail();
            modelAndView.setViewName("partyBranch_Add");
            return modelAndView;
        }
        return modelAndView;

    }


    /**
     * 编辑党支部
     * @param id
     * @return
     */
    @RequestMapping("/setPartBranch")
    public ModelAndView setPartBranch(@RequestParam(value = "id", required = false) Long id) {
        ModelAndView view = new ModelAndView();
        PartyBranch  partyBranch;

            try {
                partyBranch = partyBranchService.selectById(id);
                view.addObject("id", partyBranch.getId());
                view.addObject("name", partyBranch.getName());
                view.addObject("partyMemberCount", partyBranch.getPartyMemberCount());
                view.addObject("partyInfo", partyBranch.getPartyInfo());
                view.addObject("partyNo", partyBranch.getPartyNo());
                view.addObject("partyImg", partyBranch.getPartyImg());
                view.addObject("duty", partyBranch.getDuty());
                view.addObject("activityArea", partyBranch.getActivityArea());
                view.addObject("foundingTime", partyBranch.getFoundingTime());
                view.addObject("changeTime", partyBranch.getChangeTime());

                view.setViewName("WEB-INF/page/partyBranch_Add");
            } catch (Exception e) {
                e.printStackTrace();
                setMsg("获取数据错误");
            }
        return view;
    }


    /**
     * 查询党支部信息
     * @param name
     * @param founding_time
     * @param change_time
     * @param limit
     * @param page
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public ModelMap getPartBranchList(@RequestParam(value = "name",required = false)String name,
                                      @RequestParam(value = "partyNo",required = false)String party_no,
                                      @RequestParam(value = "founding_time",required = false)String founding_time,
                                      @RequestParam(value = "change_time",required = false)String change_time,
                                      @RequestParam(value="limit", required=false)Integer limit,
                                      @RequestParam(value="page", required=false)Integer page){

        modelMap = new ModelMap();

        Map blurMap = new HashMap<>();
        Map dateMap = new HashMap<>();
        Map intMap  = new HashMap<>();

//        if(StringUtils.isNotEmpty(type)) {
//            intMap.put("type", type);
//        }
        if(StringUtils.isNotEmpty(party_no)) {
            intMap.put("party_no", party_no);
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
            Map map = partyBranchService.selectAll(blurMap,intMap,dateMap,limit,page);

            List list = (List<PartyBranch>) map.get("list");
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
