package com.siqi_dangjian.controller;

import com.siqi_dangjian.bean.Configuration;
import com.siqi_dangjian.bean.DisciplineOfHonor;
import com.siqi_dangjian.bean.PartyBranch;
import com.siqi_dangjian.service.IDisciplineOfHonorService;
import com.siqi_dangjian.service.IUserService;
import com.siqi_dangjian.service.impl.ActivityService;
import com.siqi_dangjian.service.impl.ConfigurationService;
import com.siqi_dangjian.service.impl.PartyBranchService;
import com.siqi_dangjian.util.CommonString;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import java.util.*;

/**
 * 微信端调用控制器
 */
@Controller
@RequestMapping("/mini")
public class MiniProgramController extends BaseController {

    @Autowired
    private ActivityService activityService;

    @Autowired
    private IDisciplineOfHonorService disciplineOfHonorService;

    @Autowired
    private IUserService userService;

    @Autowired
    private ConfigurationService configurationService;

    @Autowired
    private PartyBranchService partyBranchService;

    Logger logger = Logger.getRootLogger();


    /**
     * 查询荣誉和违纪表
     * 类型  0：荣誉   1：违纪
     * @param limit
     * @param page
     * @return
     */
    @RequestMapping("getDisciplineOfHonorList")
    @ResponseBody
    public ModelMap getDisciplineOfHonorList(@RequestParam(value = "name", required = false) String name,
                                             @RequestParam(value = "type", required = false) String type,
                                             @RequestParam(value = "limit", required = false) Integer limit,
                                             @RequestParam(value = "page", required = false) Integer page) {

        modelMap = new ModelMap();
        Map blurMap = new HashMap<>();
        Map dateMap = new HashMap<>();
        Map intMap = new HashMap<>();

        if (StringUtils.isNotEmpty(type)) {
            intMap.put("type", type);
        }

        if (StringUtils.isNotEmpty(name)) {
            blurMap.put("name", name);
        }

        try {
            Map map = disciplineOfHonorService.selectAll(blurMap, intMap, dateMap, limit, page);

            List list = (List<DisciplineOfHonor>) map.get("list");
            Integer count = (int) map.get("count");
            setData("data", list);
            setData("count", count);
            setSuccess();
            setMsg("查询荣誉和违纪信息成功");
        } catch (Exception e) {
            setFail();
            e.printStackTrace();
            logger.error("mini --> getDisciplineOfHonorList");
        }

        return modelMap;
    }

    /**
     * 查询党支部信息
     * @param limit
     * @param page
     * @return
     */
    @RequestMapping("getPartBranchList")
    @ResponseBody
    public ModelMap getPartBranchList(@RequestParam(value="limit", required=false)Integer limit,
                                      @RequestParam(value="page", required=false)Integer page){

        modelMap = new ModelMap();

        Map blurMap = new HashMap<>();
        Map dateMap = new HashMap<>();
        Map intMap = new HashMap<>();

        try {
            Map map = partyBranchService.selectAll(blurMap, intMap, dateMap, limit, page);

            List list = (List<PartyBranch>) map.get("list");
            Integer count = (int) map.get("count");
            setData("list", list);
            setData("count", count);
            setMsg("查询党支部信息成功");
            setSuccess();
        } catch (Exception e) {
            setFail();
            e.printStackTrace();
            logger.error("mini --> getConfig");
        }
        return modelMap;
    }

    /**
     * 获取轮播图片
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getConfig")
    public ModelMap getSystemInfo() {
        modelMap = new ModelMap();
        try {
            Configuration comInfo = configurationService.selectById(1L);
            String[] lunbo = new String[5];
            lunbo[0] = comInfo.getImagePathA();
            lunbo[1] = comInfo.getImagePathB();
            lunbo[2] = comInfo.getImagePathC();
            lunbo[3] = comInfo.getImagePathD();
            lunbo[4] = comInfo.getImagePathE();

            setData("lunbo", lunbo);
            setData("comInfo", comInfo);
            setSuccess();
            setMsg("获取轮播信息成功");
        }catch (Exception e){
            e.printStackTrace();
            setFail("获取轮播信息异常");
            logger.error("mini --> getConfig");
        }
        return modelMap;
    }

    /**
     * 分组查询(发展对象 2 积极分子；3 预备党员；4 正式党员)的数量
     * @return modelMap
     */
    @RequestMapping(value = "/selectGroupCount")
    @ResponseBody
    public ModelMap selectGroupCount() {
        modelMap = new ModelMap();
        try {
        Map map =  userService.selectGroupCount();
        setData("countList",map.get("countList"));
        setSuccess();
        setCode(CommonString.REQUEST_SUCCESS);
        } catch (Exception e) {
            setCode(CommonString.SYSTEM_EXPECTION);
            setFail("后台异常");
            logger.error("mini--->selectGroupCount", e);
            return modelMap;
        }
        return modelMap;
    }




}
