package com.siqi_dangjian.controller;

import com.siqi_dangjian.bean.Admin;
import com.siqi_dangjian.bean.User;
import com.siqi_dangjian.service.impl.AdminService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/login")
public class LoginController extends BaseController

{

    @Autowired
    private AdminService adminService;

    Logger logger  = Logger.getRootLogger();

    @RequestMapping(value = "/admin_login", method = RequestMethod.GET)
    @ResponseBody
    public ModelMap adminLogin(@RequestParam(value = "phone", required = false) Long phone, @RequestParam(value = "passWord", required = false) String passWord, HttpSession httpSession) {
        modelMap = new ModelMap();
        Admin admin = adminService.Login(phone);
        if (admin != null)
            setSuccess();
        else setFail();
        return modelMap;
    }


   /* @RequestMapping(value = "/admin_login", method = RequestMethod.GET)
    @ResponseBody
    public ModelMap adminLogin(@RequestParam(value = "phone", required = false) Long phone, @RequestParam(value = "passWord", required = false) String passWord, HttpSession httpSession) {
        User u = null;
        String md5Str = null;
        modelMap = new ModelMap();
        try {
            if (StringUtils.isNotEmpty(passWord)) {
                md5Str = MD5.EncoderByMd5(passWord);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            logger.error("login--->admin_login",e);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            logger.error("login--->admin_login",e);
        }
        try {
            u = userService.getUserByPhone(phone);
        }catch (Exception e){
            e.printStackTrace();
        }
        if (u == null) {
            setFail();
            setMsg("电话不存在");
        } else {
            if (u.getPassword().equals(md5Str)) {
                setSuccess();
                setMsg("登陆成功");
                httpSession.setAttribute("userName", u.getUsername());
                httpSession.setAttribute("phone", u.getPhone());
            } else {
                setFail();
                setMsg("用户密码不正确");
            }
        }
        return modelMap;
    }


    @RequestMapping("/reset_password")
    @ResponseBody
    public ModelMap reset_password(@RequestParam(value = "new_password", required = false) String new_password, @RequestParam(value = "old_password", required = false) String old_password, HttpSession session) throws IOException {
        Long phone = (Long) session.getAttribute("phone");
        User u = new User();
        u = userService.getUserByPhone(phone);
        String md5;
        try {
            md5 = MD5.EncoderByMd5(old_password);
            if (u != null) {
                if (u.getPassword().equals(md5)) {
                    u.setPassword(MD5.EncoderByMd5(new_password));
                    userService.saveOrUpDate(u);
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
            logger.error("login--->reset_password",e);
        }

        return modelMap;
    }

    @RequestMapping("/register_user")
    @ResponseBody
    public ModelMap register(@RequestParam(value = "password", required = false) String password, @RequestParam(value = "userName", required = false) String userName, @RequestParam(value = "phone", required = false) String phone) throws IOException {
        User u = new User();
        String md5;
        try {
            md5 = MD5.EncoderByMd5(password);
            u.setUser_type(1);
            u.setUsername(userName);
            u.setPassword(md5);
            u.setPhone(new Long(phone));
            u.setCanUse(1);
            userService.saveOrUpDate(u);
            setSuccess();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            setFail("后台异常");
            logger.error("login--->register_user",e);
            return modelMap;
        }
        return modelMap;
    }*/

    @RequestMapping("/logout")
    @ResponseBody
    public ModelAndView logout(HttpServletRequest httpServletRequest)  {
        httpServletRequest.getSession().invalidate();
        ModelAndView modelAndView =new ModelAndView();
        modelAndView.setViewName("/login");
        return modelAndView;
    }

}
