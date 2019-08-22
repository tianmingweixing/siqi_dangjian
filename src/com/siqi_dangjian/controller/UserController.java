package com.siqi_dangjian.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.siqi_dangjian.bean.PartyTeam;
import com.siqi_dangjian.bean.User;
import com.siqi_dangjian.service.IPartyTeamService;
import com.siqi_dangjian.service.IUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/user")
public class UserController extends BaseController{

    @Autowired
    private IUserService userService;

//    Logger logger = Logger.getRootLogger();

    @RequestMapping("/gotoAdd")
    public ModelAndView gotoAdd(@RequestParam(value = "id", required = false) Long id) {
        ModelAndView view = new ModelAndView();
        view.setViewName("WEB-INF/page/user_Add");
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
            userService.logicDeleteUser(idList);
        } catch (Exception e){
            setFail("删除失败");
//            logger.error("partyTeam--->deletePartyTeam",e);
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
     * @param dutyId
     * @param joinTime
     * @return
     */
    @RequestMapping("/addUser")
    @ResponseBody
    public ModelAndView addUser(@RequestParam(value = "id", required = false) Long id,
                                      @RequestParam(value = "username", required = false) String username,
                                      @RequestParam(value = "sex", required = false) Integer sex,
                                      @RequestParam(value = "education", required = false) String education,
                                      @RequestParam(value = "address", required = false) String address,
                                      @RequestParam(value = "company", required = false) String company,
                                      @RequestParam(value = "age", required = false) Integer age,
                                      @RequestParam(value = "ID_cord", required = false) String ID_cord,
                                      @RequestParam(value = "dutyId", required = false) Long dutyId,
                                      @RequestParam(value = "phone", required = false) String phone,
                                      @RequestParam(value = "joinTime", required = false) Timestamp joinTime){

        ModelAndView modelAndView = new ModelAndView();


        try {
            User user = new User();
            user.setId(id);
            user.setIDCord(ID_cord);
            user.setAddress(address);
            user.setAge(age);
            user.setCompany(company);
            user.setDutyId(dutyId);
            user.setPhone(phone);
            user.setEducation(education);
            user.setSex(sex);
            user.setUserName(username);
            user.setJoinTime(joinTime);
            user.setCanUse(1);
            userService.addUser(user);
            setSuccess();
            modelAndView.setViewName("frame/userList");
        } catch (Exception e) {
            e.printStackTrace();
            modelAndView.setViewName("WEB-INF/page/user_Add");
            return modelAndView;
        }
        return modelAndView;

    }


    /**
     * 编辑党小组
     * @param id
     * @return
     */
    @RequestMapping("/setUser")
    public ModelAndView setUser(@RequestParam(value = "id", required = false) Long id) {
        ModelAndView view = new ModelAndView();
        User  user;

            try {
                user = userService.getUserById(id);
                view.addObject("id", user.getId());
                view.addObject("address", user.getAddress());
                view.addObject("company", user.getCompany());
                view.addObject("ID_cord", user.getIDCord());
                view.addObject("joinTime", user.getJoinTime());
                view.addObject("sex", user.getSex());
                view.addObject("username", user.getUserName());
                view.addObject("phone", user.getPhone());
                view.addObject("userno", user.getUserNo());
                view.addObject("age", user.getAge());
                view.addObject("education", user.getEducation());
                view.addObject("dutyid", user.getDutyId());


                view.setViewName("WEB-INF/page/user_Add");
//                view.setViewName("frame/PartyTeam_Add");
            } catch (Exception e) {
                e.printStackTrace();
                setMsg("获取数据错误");
            }
        return view;
    }


    /**
     * 查询党小组信息
     * @param username
     * @param founding_time
     * @param change_time
     * @param limit
     * @param page
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public ModelMap getUserList(@RequestParam(value = "username",required = false)String username,
                                      @RequestParam(value = "company",required = false)String company,
                                      @RequestParam(value = "founding_time",required = false)String founding_time,
                                      @RequestParam(value = "change_time",required = false)String change_time,
                                      @RequestParam(value="limit", required=false)Integer limit,
                                      @RequestParam(value="page", required=false)Integer page){

        modelMap = new ModelMap();

        Map blurMap = new HashMap<>();
        Map dateMap = new HashMap<>();
        Map intMap  = new HashMap<>();


        if(StringUtils.isNotEmpty(company)) {
            blurMap.put("company", company);
        }

        if(StringUtils.isNotEmpty(username)) {
            blurMap.put("username", username);
        }

        if(StringUtils.isNotEmpty(founding_time)) {
            dateMap.put("founding_time", founding_time);
        }
        if(StringUtils.isNotEmpty(change_time)) {
            dateMap.put("change_time", change_time);
        }
        try {
            Map map = userService.getUserList(blurMap,intMap,dateMap,limit,page);

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
