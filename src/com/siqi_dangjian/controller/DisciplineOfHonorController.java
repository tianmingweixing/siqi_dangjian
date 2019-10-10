package com.siqi_dangjian.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.siqi_dangjian.bean.DisciplineOfHonor;
import com.siqi_dangjian.bean.User;
import com.siqi_dangjian.service.IConfigurationService;
import com.siqi_dangjian.service.IDisciplineOfHonorService;
import com.siqi_dangjian.service.IUserService;
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
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 荣誉和违纪控制器
 */
@Controller
@RequestMapping("/disciplineOfHonor")
public class DisciplineOfHonorController extends BaseController {

    @Autowired
    private IDisciplineOfHonorService disciplineOfHonorService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IConfigurationService configurationService;


    Logger logger = Logger.getRootLogger();

    /**
     * 类型  0：荣誉   1：违纪
     *
     * @param type
     * @return
     */
    @RequestMapping("/gotoAdd")
    public ModelAndView gotoAdd(@RequestParam(value = "type", required = false) Integer type) {
        ModelAndView view = new ModelAndView();
        if (type == 0) {
            view.setViewName("WEB-INF/page/honor_Add");//跳转添加荣誉信息页面
        } else if (type == 1) {
            view.setViewName("WEB-INF/page/discipline_Add");//跳转添加违纪信息页面
        }
        return view;
    }

    /**
     * 逻辑删除
     *
     * @return
     */
    @RequestMapping("/logicDelete")
    @ResponseBody
    public ModelMap logicDelete(@RequestParam(value = "deleteArray", required = false) String deleteArray) {
        modelMap = new ModelMap();
        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>() {
        }.getType();
        List<String> idList = gson.fromJson(deleteArray, type);
        try {
            disciplineOfHonorService.logicDelete(idList);
        } catch (Exception e) {
            setFail("删除失败");
            logger.error("DisciplineOfHonor--->deleteDisciplineOfHonor", e);
            return modelMap;
        }
        setSuccess();
        return modelMap;
    }



    /**
     * 添加或更新
     * @param id
     * @param party_branch_id 支部ID
     * @param name 荣誉和违纪名称
     * @param time 荣誉和违纪时间
     * @param type 类型  0：荣誉   1：违纪
     * @param unit 授奖单位或个人
     * @param passive_unit 被奖惩个人或单位
     * @param certificate 荣誉凭证
     * @param amount 金额
     * @param content 奖惩内容
     * @param note 备注
     * @return
     */
    @RequestMapping("/addDisciplineOfHonor")
    @ResponseBody
    public ModelMap addDisciplineOfHonor(@RequestParam(value = "id", required = false) Long id,
                                         @RequestParam(value = "userId", required = false) Long userId,
                                         @RequestParam(value = "party_branch_id", required = false) Long party_branch_id,
                                         @RequestParam(value = "name", required = false) String name,
                                         @RequestParam(value = "time", required = false)@DateTimeFormat(pattern = "yyyy-MM-dd") Date time,
                                         @RequestParam(value = "type", required = false) Integer type,
                                         @RequestParam(value = "unit", required = false) String unit,
                                         @RequestParam(value = "passive_unit", required = false) String passive_unit,
                                         @RequestParam(value = "certificate", required = false) String certificate,
                                         @RequestParam(value = "amount", required = false) String amount,
                                         @RequestParam(value = "content", required = false) String content,
                                         @RequestParam(value = "note", required = false) String note) {

         modelMap = new ModelMap();

        try {
            DisciplineOfHonor disciplineOfHonor = new DisciplineOfHonor();

            party_branch_id = configurationService.selectPartyBranchId();
            disciplineOfHonor.setId(id);
            disciplineOfHonor.setUserId(userId);
            disciplineOfHonor.setAmount(amount);
            disciplineOfHonor.setCertificate(certificate);
            disciplineOfHonor.setContent(content);
            disciplineOfHonor.setName(name);
            disciplineOfHonor.setNote(note);
            disciplineOfHonor.setPartyBranchId(party_branch_id);
            disciplineOfHonor.setPassiveUnit(passive_unit);
            disciplineOfHonor.setTime(time);
            disciplineOfHonor.setUnit(unit);
            disciplineOfHonor.setType(type);

            disciplineOfHonor.setCanUse(1);
            disciplineOfHonorService.insertOrUpdate(disciplineOfHonor);
            setSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            setFail();
            return modelMap;
        }
        return modelMap;

    }


    /**
     * 编辑荣誉和违纪表
     *
     * @param id 类型  0：荣誉   1：违纪
     */
    @RequestMapping("/setDisciplineOfHonor")
    public ModelAndView setDisciplineOfHonor(@RequestParam(value = "Id", required = false) Long id,
                                             @RequestParam(value = "type", required = false) Long type,
                                             @RequestParam(value = "userId", required = false) Long userId) {

        ModelAndView view = new ModelAndView();
        DisciplineOfHonor disciplineOfHonor;
        User user;

        try {
            disciplineOfHonor = disciplineOfHonorService.selectById(id);
            view.addObject("id", disciplineOfHonor.getId());
            view.addObject("party_branch_id", disciplineOfHonor.getPartyBranchId());
            view.addObject("name", disciplineOfHonor.getName());
            view.addObject("time", CommonUtil.timeFormat(disciplineOfHonor.getTime()));
            view.addObject("type", disciplineOfHonor.getType());
            view.addObject("unit", disciplineOfHonor.getUnit());
            view.addObject("passive_unit", disciplineOfHonor.getPassiveUnit());
            view.addObject("certificate", disciplineOfHonor.getCertificate());
            view.addObject("amount", disciplineOfHonor.getAmount());
            view.addObject("content", disciplineOfHonor.getContent());
            view.addObject("note", disciplineOfHonor.getNote());

            user = userService.getUserById(userId);
            view.addObject("username", user.getUserName());
            view.addObject("sex", user.getSex());
            view.addObject("userId", user.getId());
            view.addObject("age", user.getAge());
            view.addObject("phone", user.getPhone());

            if (type == 0) {
                view.setViewName("WEB-INF/page/honor_Add");//跳转添加荣誉信息页面
            } else if (type == 1) {
                view.setViewName("WEB-INF/page/discipline_Add");//跳转添加违纪信息页面
            }
        } catch (Exception e) {
            e.printStackTrace();
            setMsg("获取数据错误");
        }
        return view;
    }


    /**
     * 查询荣誉和违纪表
     *
     * @param limit
     * @param page
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public ModelMap getDisciplineOfHonorList(@RequestParam(value = "name", required = false) String name,
                                             @RequestParam(value = "type", required = false) String type,
                                             @RequestParam(value = "user_id", required = false) String user_id,
                                             @RequestParam(value = "start_time_search", required = false) String start_time,
                                             @RequestParam(value = "end_time_search", required = false) String end_time,
                                             @RequestParam(value = "limit", required = false) Integer limit,
                                             @RequestParam(value = "page", required = false) Integer page) {

        modelMap = new ModelMap();

        Map blurMap = new HashMap<>();
        Map dateMap = new HashMap<>();
        Map intMap = new HashMap<>();

        if (StringUtils.isNotEmpty(type)) {
            intMap.put("type", type);
        }

        if (StringUtils.isNotEmpty(user_id)) {
            intMap.put("user_id", user_id);
        }

        if (StringUtils.isNotEmpty(name)) {
            blurMap.put("name", name);
        }

        if (StringUtils.isNotEmpty(start_time)) {
            dateMap.put("start_time", start_time);
        }

        if (StringUtils.isNotEmpty(end_time)) {
            dateMap.put("end_time", end_time);
        }


        try {
            Map map = disciplineOfHonorService.selectAll(blurMap, intMap, dateMap, limit, page);

            List list = (List<DisciplineOfHonor>) map.get("list");
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
