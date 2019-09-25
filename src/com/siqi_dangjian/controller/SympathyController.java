package com.siqi_dangjian.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.siqi_dangjian.bean.Sympathy;
import com.siqi_dangjian.bean.User;
import com.siqi_dangjian.service.ISympathyService;
import com.siqi_dangjian.service.IUserService;
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
@RequestMapping("/sympathy")
public class SympathyController extends BaseController{

    @Autowired
    private ISympathyService sympathyService;

    @Autowired
    private IUserService userService;

    Logger logger = Logger.getRootLogger();

    @RequestMapping("/gotoAdd")
    public ModelAndView gotoAdd(@RequestParam(value = "id", required = false) Long id) {
        ModelAndView view = new ModelAndView();
        view.setViewName("WEB-INF/page/sympathy_Add");
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
            sympathyService.logicDelete(idList);
        } catch (Exception e){
            setFail("删除失败");
            logger.error("sympathy--->deleteSympathy",e);
            return modelMap;
        }
        setSuccess();
        return modelMap;
    }



    /**
     * 添加或更新
     * @param sympathyId
     * @param sympathy_time 慰问时间
     * @param unit_and_position 慰问人单位及职务
     * @param sympathyProduct 慰问品及信息
     * @param note 备注
     * @param difficult  1 非困难  2困难  3非常困难
     * @return
     */
    @RequestMapping("/addSympathy")
    @ResponseBody
    public ModelAndView addSympathy(@RequestParam(value = "sympathyId", required = false) Long sympathyId,
                                    @RequestParam(value = "userId", required = false) Long userId,
                                    @RequestParam(value = "sympathy_time", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date sympathy_time,
                                    @RequestParam(value = "unit_and_position", required = false) String unit_and_position,
                                    @RequestParam(value = "sympathy_product", required = false) String sympathyProduct,
                                    @RequestParam(value = "note", required = false) String note,
//                                    @RequestParam(value = "username", required = false) String userName,
//                                    @RequestParam(value = "age", required = false) Integer age,
//                                    @RequestParam(value = "sex", required = false) Integer sex,
                                    @RequestParam(value = "difficult", required = false) Integer difficult) {

        ModelAndView modelAndView = new ModelAndView();
        Sympathy sympathy;
        User user;
        try {

            if(sympathyId != null ){
                sympathy = sympathyService.selectById(sympathyId);
            }else{
                sympathy = new Sympathy();
            }
//            if (userId == null) {
//                user = new User();
//            }else{
//                user =  userService.getUserById(userId);
//            }

                sympathy.setId(sympathyId);
                sympathy.setSympathyTime(sympathy_time);
                sympathy.setUnitAndPosition(unit_and_position);
                sympathy.setSympathyProduct(sympathyProduct);
                sympathy.setNote(note);
                sympathy.setDifficult(difficult);
                sympathy.setCanUse(1);
                sympathy.setUserId(userId);
                sympathyService.insertOrUpdate(sympathy);

//                user.setUserName(userName);
//                user.setId(userId);
//                user.setAge(age);
//                user.setSex(sex);
//                user.setCanUse(1);
//                userService.addUser(user);
                setSuccess();
                modelAndView.setViewName("frame/sympathyList");

        } catch (Exception e) {
            e.printStackTrace();
            modelAndView.setViewName("WEB-INF/page/sympathy_Add");
            return modelAndView;
        }
        return modelAndView;

    }


    /**
     * 编辑慰问表
     * @param  sympathyId
     * @return userId
     */
    @RequestMapping("/setSympathy")
    public ModelAndView setUser(@RequestParam(value = "sympathyId", required = false) Long sympathyId,
                                @RequestParam(value = "userId", required = false) Long userId) {

        ModelAndView view = new ModelAndView();
        Sympathy sympathy;
        User user;

        try {
            sympathy = sympathyService.selectById(sympathyId);
            view.addObject("sympathyId", sympathy.getId());
            view.addObject("difficult", sympathy.getDifficult());
            view.addObject("sympathy_time", sympathy.getSympathyTime());
            view.addObject("unit_and_position", sympathy.getUnitAndPosition());
            view.addObject("sympathy_product", sympathy.getSympathyProduct());
            view.addObject("note", sympathy.getNote());

            user = userService.getUserById(userId);
            view.addObject("username", user.getUserName());
            view.addObject("sex", user.getSex());
            view.addObject("userId", user.getId());
            view.addObject("age", user.getAge());
            view.addObject("phone", user.getPhone());


            view.setViewName("WEB-INF/page/sympathy_Add");
        } catch (Exception e) {
            e.printStackTrace();
            setMsg("获取数据错误");
        }
        return view;
    }


    /**
     * 查询慰问表信息
     * @param username
     * @param limit
     * @param page
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public ModelMap getSympathyList(@RequestParam(value = "username", required = false) String username,
                                    @RequestParam(value = "difficult", required = false) String difficult,
                                    @RequestParam(value = "start_time_search", required = false) String start_time,
                                    @RequestParam(value = "end_time_search", required = false) String end_time,
                                    @RequestParam(value = "limit", required = false) Integer limit,
                                    @RequestParam(value = "page", required = false) Integer page) {

        modelMap = new ModelMap();
        Map blurMap = new HashMap<>();
        Map dateMap = new HashMap<>();
        Map intMap  = new HashMap<>();


        if(StringUtils.isNotEmpty(difficult)) {
            intMap.put("difficult", difficult);
        }

        if(StringUtils.isNotEmpty(username)) {
            blurMap.put("username", username);
        }

        if (StringUtils.isNotEmpty(start_time)) {
            dateMap.put("start_time", start_time);
        }

        if (StringUtils.isNotEmpty(end_time)) {
            dateMap.put("end_time", end_time);
        }

        try {
            Map map = sympathyService.selectAll(blurMap,intMap,dateMap,limit,page);
            List list = (List<Sympathy>) map.get("list");
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
