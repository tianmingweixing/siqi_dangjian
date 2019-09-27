package com.siqi_dangjian.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.siqi_dangjian.bean.*;
import com.siqi_dangjian.service.*;
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
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IDutyService dutyService;

    @Autowired
    private ISympathyService sympathyService;

    @Autowired
    private IPartyTeamService partyTeamService;

    @Autowired
    private IPartyGroupService partyGroupService;

    Logger logger = Logger.getRootLogger();

    @RequestMapping("/gotoAdd")
    public ModelAndView gotoAdd() {
        ModelAndView view = new ModelAndView();
        view.setViewName("WEB-INF/page/user_Add");
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
            userService.logicDeleteUser(idList);
        } catch (Exception e) {
            setFail("删除失败");
            logger.error("user--->deleteUser", e);
            return modelMap;
        }
        setSuccess();
        return modelMap;
    }


    /**
     * 添加或更新
     * @param id
     * @param username
     * @param sex
     * @param education
     * @param address
     * @param company
     * @param age
     * @param dutyid
     * @param joinTime
     * @return
     */
    @RequestMapping("/addUser")
    @ResponseBody
    public ModelMap addUser(@RequestParam(value = "id", required = false) Long id,
                            @RequestParam(value = "dutyid", required = false) Long dutyid,
                            @RequestParam(value = "partyGroupsId", required = false) Long partyGroupsId,
                            @RequestParam(value = "partyTeamId", required = false) Long partyTeamId,
                            @RequestParam(value = "username", required = false) String username,
                            @RequestParam(value = "sex", required = false) Integer sex,
                            @RequestParam(value = "education", required = false) String education,
                            @RequestParam(value = "address", required = false) String address,
                            @RequestParam(value = "company", required = false) String company,
                            @RequestParam(value = "age", required = false) Integer age,
                            @RequestParam(value = "ID_cord", required = false) String ID_cord,
                            @RequestParam(value = "phone", required = false) String phone,
                            @RequestParam(value = "profiles", required = false) String profiles,
                            @RequestParam(value = "head_img", required = false) String head_img,
                            @RequestParam(value = "join_time", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date joinTime) {

        modelMap = new ModelMap();
        User user;

        try {
            if (id != null) {
                user = userService.getUserById(id);
            } else {
                user = new User();
            }

            user.setDutyId(dutyid);
            user.setPartyBranchId(1L);
            user.setPartyGroupsId(partyGroupsId);
            user.setPartyTeamId(partyTeamId);
            user.setId(id);
            user.setIDCord(ID_cord);
            user.setAddress(address);
            user.setAge(age);
            user.setCompany(company);
            user.setPhone(phone);
            user.setEducation(education);
            user.setSex(sex);
            user.setHeadImg(head_img);
            user.setProfiles(profiles);
            user.setUserName(username);
            user.setJoinTime(joinTime);
            user.setCanUse(1);
            userService.addUser(user);
            setSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("user-->userAdd", e);
            setFail("用户添加失败");
            return modelMap;
        }
        return modelMap;

    }


    /**
     * 编辑党小组
     * @param id addSympathy
     * @return
     */
    @RequestMapping(value = "/setUser")
    public ModelAndView setUser(@RequestParam(value = "id", required = false) Long id,
                                @RequestParam(value = "sympathyId", required = false) Long sympathyId,
                                @RequestParam(value = "partyGroupsId", required = false) Long partyGroupsId,
                                @RequestParam(value = "partyTeamId", required = false) Long partyTeamId,
                                @RequestParam(value = "addSympathy", required = false) Integer addSympathy) {
        ModelAndView view = new ModelAndView();
        User user;
        Duty duty;
        Sympathy sympathy;
        PartyTeam partyTeam;
        PartyGroup partyGroup;

        try {
            if (id != null) {
                user = userService.getUserById(id);
            } else {
                user = new User();
            }

            if (user.getDutyId() != null) {
                duty = dutyService.selectById(user.getDutyId());
                view.addObject("description", duty.getDescription());
                view.addObject("type_name", duty.getTypeName());
                view.addObject("dutyid", duty.getId());
            }
            if (partyGroupsId != null) {
                partyGroup = partyGroupService.selectById(partyGroupsId);
                view.addObject("partyGroupsId", partyGroup.getId());

            }

            if (partyTeamId != null) {
                partyTeam = partyTeamService.selectById(partyTeamId);
                view.addObject("partyTeamId", partyTeam.getId());

            }

            if (sympathyId != null) {
                sympathy = sympathyService.selectById(sympathyId);
                view.addObject("sympathyId", sympathy.getId());
                view.addObject("difficult", sympathy.getDifficult());
                view.addObject("sympathy_time", sympathy.getSympathyTime());
                view.addObject("unit_and_position", sympathy.getUnitAndPosition());
                view.addObject("sympathy_product", sympathy.getSympathyProduct());
                view.addObject("note", sympathy.getNote());
            }

                view.addObject("id", user.getId());
                view.addObject("dutyid", user.getDutyId());
                view.addObject("address", user.getAddress());
                view.addObject("company", user.getCompany());
                view.addObject("ID_cord", user.getIDCord());
                view.addObject("join_time", user.getJoinTime());
                view.addObject("sex", user.getSex());
                view.addObject("username", user.getUserName());
                view.addObject("phone", user.getPhone());
                view.addObject("userno", user.getUserNo());
                view.addObject("age", user.getAge());
                view.addObject("education", user.getEducation());

            if (addSympathy != null) {
                view.addObject("userId", user.getId());
                view.setViewName("WEB-INF/page/sympathy_Add");
            } else {
                view.setViewName("WEB-INF/page/user_Add");
            }
            setSuccess();
            setMsg("获取用户数据成功");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("user-->setUser", e);
            setMsg("获取用户数据错误");
        }
        return view;
    }

    /**
     * 根据id查询用户信息
     * @param id
     * @return user
     */
    @RequestMapping("/getUserById")
    @ResponseBody
    public ModelMap getUserById(@RequestParam(value = "userId", required = false) Long id) {
        modelMap = new ModelMap();
        User user;
        try {
            user = userService.getUserById(id);
            setSuccess();
            setData("user",user);

        } catch (Exception e) {
            e.printStackTrace();
            setFail("查询用户信息错误");
        }
        return modelMap;
    }

    /**
     * 模糊查询用户信息
     * @param username
     * @param founding_time
     * @param change_time
     * @param limit
     * @param page
     * @return List
     */
    @RequestMapping("list")
    @ResponseBody
    public ModelMap getUserList(@RequestParam(value = "username", required = false) String username,
                                @RequestParam(value = "company", required = false) String company,
                                @RequestParam(value = "dutyid", required = false) String dutyid,
                                @RequestParam(value = "userId", required = false) String userId,
                                @RequestParam(value = "founding_time", required = false) String founding_time,
                                @RequestParam(value = "change_time", required = false) String change_time,
                                @RequestParam(value = "limit", required = false) Integer limit,
                                @RequestParam(value = "page", required = false) Integer page) {

        modelMap = new ModelMap();
        Map blurMap = new HashMap<>();
        Map dateMap = new HashMap<>();
        Map intMap = new HashMap<>();


        if (StringUtils.isNotEmpty(company)) {
            blurMap.put("company", company);
        }

        if (StringUtils.isNotEmpty(userId)) {
            intMap.put("id", userId);
        }

        if (StringUtils.isNotEmpty(dutyid)) {
            intMap.put("dutyid", dutyid);
        }

        if (StringUtils.isNotEmpty(username)) {
            blurMap.put("username", username);
        }

        if (StringUtils.isNotEmpty(founding_time)) {
            dateMap.put("founding_time", founding_time);
        }
        if (StringUtils.isNotEmpty(change_time)) {
            dateMap.put("change_time", change_time);
        }
        try {
            Map map = userService.getUserList(blurMap, intMap, dateMap, limit, page);
            List list = (List<User>) map.get("list");
            Integer count = (int) map.get("count");
            setData("data", list);
            setData("count", count);
            setSuccess();
        } catch (Exception e) {
            setFail();
            e.printStackTrace();
            logger.error("user-->list", e);

        }
        return modelMap;
    }
}
