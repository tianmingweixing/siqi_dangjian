package com.siqi_dangjian.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.siqi_dangjian.bean.Admin;
import com.siqi_dangjian.bean.PartyBranch;
import com.siqi_dangjian.service.IAdminService;
import com.siqi_dangjian.util.CommonString;
import com.siqi_dangjian.util.MD5;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.Type;
import java.security.NoSuchAlgorithmException;
import java.util.*;


@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController{

    @Autowired
    private IAdminService adminService;

    Logger logger = Logger.getRootLogger();

    @RequestMapping(value = "/goto")
    public ModelAndView addOrEidt(@RequestParam(value = "id", required = false) Long id) {
        ModelAndView view = new ModelAndView();
        try {
            if (id != null) {
                Admin admin = adminService.selectById(id);
                view.addObject("id", admin.getId());
                view.addObject("username", admin.getUserName());
                view.addObject("admin_type", admin.getAdminType());
                view.addObject("authority", admin.getAuthority());
                view.addObject("admin_account", admin.getAccount());
                view.addObject("admin_head", admin.getHeadImg());
                view.addObject("party_branch_id", admin.getPartyBranchId());

                view.setViewName("/frame/admin_Add");

            } else{
                view.setViewName("/frame/admin_Add");
            }

        } catch (Exception e) {
            e.printStackTrace();
            setFail();
            if(CommonString.IS_OPEN_LOG)
                logger.error("admin--->addOrEidt异常：",e);
        }

        return view;
    }

    /**
     * @param id
     * @param username
     * @param password
     * @param admin_type
     * @param authority
     * @param headImg
     * @param admin_account
     * @param party_branch_id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/saveAdmin", method = RequestMethod.POST)
    public ModelMap saveAdmin(@RequestParam(value = "id", required = false) Long id,
                              @RequestParam(value = "username", required = false) String username,
                              @RequestParam(value = "password", required = false) String password,
                              @RequestParam(value = "admin_type", required = false) Integer admin_type,
                              @RequestParam(value = "authority", required = false) String authority,
                              @RequestParam(value = "headImg", required = false) String headImg,
                              @RequestParam(value = "admin_account", required = false) String admin_account,
                              @RequestParam(value = "party_branch_id", required = false) Long party_branch_id) {
        modelMap = new ModelMap();
        String md5Str = "";
        try {
            Admin admin= new Admin();
            if (id != null){
                admin.setId(id);
            }

            admin.setAccount(admin_account);
            if (StringUtils.isNotEmpty(password)) {
                md5Str = MD5.EncoderByMd5(password);
            }
            admin.setPassword(md5Str);
            admin.setAdminType(admin_type);
            admin.setUserName(username);
            admin.setAuthority(authority);
            admin.setPartyBranchId(party_branch_id);
            admin.setHeadImg(headImg);
            admin.setCanUse(1);
            adminService.insertOrUpdate(admin);
            setMsg("添加成功");
            setSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            setCode(400);
            setFail("添加失败");
            if(CommonString.IS_OPEN_LOG)
                logger.error("admin--->添加管理员-添加异常：",e);
        }
        return modelMap;
    }


    /**
     * 删除管理员
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ModelMap deleteAdmin(@RequestParam(value="deleteArray", required=false)String deleteArray){
        modelMap = new ModelMap();
        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>() {}.getType();
        List<String> jsonList = gson.fromJson(deleteArray,type);
        try {
            adminService.delete(jsonList);
            setSuccess();
        } catch (Exception e){
            setFail("删除失败");
            if(CommonString.IS_OPEN_LOG)
                logger.error("admin--->删除管理员-删除异常：",e);
        }

        return modelMap;
    }

    /**
     * 查询管理员列表
     * @param account
     * @param userName
     * @param adminType
     * @param limit
     * @param page
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public ModelMap getPartBranchList(@RequestParam(value = "account",required = false)String account,
                                      @RequestParam(value = "userName",required = false)String userName,
                                      @RequestParam(value = "adminType",required = false)Integer adminType,
                                      @RequestParam(value="limit", required=false)Integer limit,
                                      @RequestParam(value="page", required=false)Integer page){

        modelMap = new ModelMap();

        Map blurMap = new HashMap<>();
        Map dateMap = new HashMap<>();
        Map intMap  = new HashMap<>();


        if(StringUtils.isNotEmpty(account)) {
            intMap.put("account", account);
        }
        if(StringUtils.isNotEmpty(userName)) {
            blurMap.put("username", userName);
        }
        if(adminType != null) {
            intMap.put("admin_type", adminType.toString());//要转成字符串
        }

        try {
            Map map = adminService.selectAll(blurMap,intMap,dateMap,limit,page);

            List list = (List<PartyBranch>) map.get("list");
            Integer count = (int) map.get("count");
            setData("data", list);
            setData("count", count);
            setSuccess();
        }catch (Exception e){
            e.printStackTrace();
            setFail();
        }

        return modelMap;
    }

    @RequestMapping("/reset_password")
    @ResponseBody
    public ModelMap reset_password(@RequestParam(value = "new_password", required = false) String new_password,
                                   @RequestParam(value = "old_password", required = false) String old_password, HttpSession session) throws IOException {
        modelMap = new ModelMap();
        String account = (String) session.getAttribute("account");
        Admin admin = adminService.getAdminByAccount(account);
        String md5;
        try {
            md5 = MD5.EncoderByMd5(old_password);
            if (admin != null) {
                if (admin.getPassword().equals(md5)) {
                    admin.setPassword(MD5.EncoderByMd5(new_password));
                    adminService.insertOrUpdate(admin);
                    setSuccess();
                } else {
                    setFail("旧密码错误");
                }
            } else {
                setFail("用户信息拉取失败");
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            setFail("后台异常");
        } catch (Exception e) {
            e.printStackTrace();
            setFail("后台异常");
        }

        return modelMap;
    }
}
