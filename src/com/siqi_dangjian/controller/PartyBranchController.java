package com.siqi_dangjian.controller;


import com.siqi_dangjian.bean.PartyBranch;
import com.siqi_dangjian.service.IPartyBranchService;
import com.siqi_dangjian.util.CommonString;
import com.siqi_dangjian.util.CommonUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
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
     * @param activityArea
     * @param foundingTime
     * @param changeTime
     */
    @RequestMapping("/addPartBranch")
    @ResponseBody
    public ModelMap addPartBranch(@RequestParam(value = "file", required = false) CommonsMultipartFile file, HttpServletRequest request,
                                  @RequestParam(value = "img_path", required = false) String img_path,
                                  @RequestParam(value = "name", required = false) String name,
                                  @RequestParam(value = "id", required = false) Long id,
                                  @RequestParam(value = "partyMemberCount", required = false) Integer partyMemberCount,
                                  @RequestParam(value = "duty", required = false) String duty,
                                  @RequestParam(value = "partyNo", required = false) String partyNo,
                                  @RequestParam(value = "partyInfo", required = false) String partyInfo,
                                  @RequestParam(value = "activityArea", required = false) Double activityArea,
                                  @RequestParam(value = "foundingTime", required = false)@DateTimeFormat(pattern = "yyyy-MM-dd") Date foundingTime,
                                  @RequestParam(value = "changeTime", required = false)@DateTimeFormat(pattern = "yyyy-MM-dd") Date changeTime){

        ModelMap modelMap = new ModelMap();
        PartyBranch partyBranch;
        try {
             partyBranch = new PartyBranch();
            if (file != null) {
                String path = CommonUtil.uploadImg(file,request);//调用公共的上传单张图片方法
                partyBranch.setPartyImg(path);
            }else{
                String path = CommonUtil.subImgPathString(img_path);//调用公共的截取字符串方法,获取图片相对路径
                partyBranch.setPartyImg(path);
            }

            partyBranch.setId(id);
            partyBranch.setActivityArea(activityArea);
            partyBranch.setPartyNo(partyNo);
            partyBranch.setDuty(duty);
            partyBranch.setFoundingTime(foundingTime);
            partyBranch.setChangeTime(changeTime);
            partyBranch.setName(name);
            partyBranch.setPartyInfo(partyInfo);
            partyBranch.setPartyMemberCount(partyMemberCount);
            partyBranch.setCanUse(1);
            partyBranchService.insertOrUpdate(partyBranch);
            setSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            setFail();

            return modelMap;
        }
        return modelMap;

    }


    /**
     * 编辑党支部
     * @param id
     * @return
     */
    @RequestMapping("/setPartBranch")
    public ModelAndView setPartBranch(@RequestParam(value = "id", required = false) Long id,HttpServletRequest request) {
        ModelAndView view = new ModelAndView();
        PartyBranch partyBranch;

        try {
            partyBranch = partyBranchService.selectById(id);

            view.addObject("id", partyBranch.getId());
            view.addObject("name", partyBranch.getName());
            view.addObject("partyMemberCount", partyBranch.getPartyMemberCount());
            view.addObject("partyInfo", partyBranch.getPartyInfo());
            view.addObject("partyNo", partyBranch.getPartyNo());
            view.addObject("party_img", partyBranch.getPartyImg());
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
        Map intMap = new HashMap<>();

        if (StringUtils.isNotEmpty(party_no)) {
            intMap.put("party_no", party_no);
        }
        if (StringUtils.isNotEmpty(name)) {
            blurMap.put("name", name);
        }
        if (StringUtils.isNotEmpty(founding_time)) {
            dateMap.put("founding_time", founding_time);
        }
        if (StringUtils.isNotEmpty(change_time)) {
            dateMap.put("change_time", change_time);
        }
        try {
            Map map = partyBranchService.selectAll(blurMap, intMap, dateMap, limit, page);

            List list = (List<PartyBranch>) map.get("list");
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
