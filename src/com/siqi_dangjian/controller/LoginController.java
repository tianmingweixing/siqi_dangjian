package com.siqi_dangjian.controller;

import com.siqi_dangjian.bean.Admin;
import com.siqi_dangjian.bean.User;
import com.siqi_dangjian.service.IAdminService;
import com.siqi_dangjian.service.IUserService;
import com.siqi_dangjian.service.impl.AdminService;
import com.siqi_dangjian.service.impl.UserService;
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

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;


@Controller
@RequestMapping("/login")
public class LoginController extends BaseController{

    @Autowired
    IUserService userService;

    @Autowired
    private IAdminService adminService;

    Logger logger  = Logger.getRootLogger();



    @RequestMapping(value = "/admin_register", method = RequestMethod.GET)
    @ResponseBody
    public ModelMap adminRegister(@RequestParam(value = "account", required = false) String account,
                               @RequestParam(value = "passWord", required = false) String passWord){
        String md5Str;
        modelMap = new ModelMap();
        try {

            Admin admin = adminService.getAdminByAccount(account);

            if (admin != null) {
                setMsg("该账户已被使用");
                return modelMap;
            } else {
                if (StringUtils.isNotEmpty(passWord)) {
                    md5Str = MD5.EncoderByMd5(passWord);
                    admin = new Admin();
                    admin.setAccount(account);
                    admin.setPassword(md5Str);
                    admin.setCanUse(1);
                    adminService.insertOrUpdate(admin);
                    setMsg("注册成功");
                    return modelMap;
                }
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            logger.error("register--->admin_register",e);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            logger.error("register--->admin_register",e);
        }catch (Exception e) {
            e.printStackTrace();
            setMsg("注册失败");

        }
        return modelMap;
    }


    @RequestMapping(value = "/admin_login", method = RequestMethod.GET)
    @ResponseBody
    public ModelMap adminLogin(@RequestParam(value = "account", required = false) String account,
                               @RequestParam(value = "passWord", required = false) String passWord,
                               HttpSession httpSession) {
        Admin admin = null;
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
            admin = adminService.getAdminByAccount(account);
        }catch (Exception e){
            e.printStackTrace();
        }
        if (admin == null) {
            setFail();
            setMsg("账户不存在");
        } else {
            if (admin.getPassword().equals(md5Str)) {
                setSuccess();
                setMsg("登陆成功");
                httpSession.setAttribute("account", admin.getAccount());
            } else {
                setFail();
                setMsg("用户密码不正确");
            }
        }
        return modelMap;
    }


   /* @RequestMapping("/reset_password")
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

//    @RequestMapping("/logout")
//    @ResponseBody
//    public ModelAndView logout(HttpServletRequest httpServletRequest,HttpServletResponse response)  {
//        httpServletRequest.getSession().invalidate();
//        ModelAndView modelAndView =new ModelAndView();
//        modelAndView.setViewName("/login");
//        response.setCharacterEncoding("UTF-8");
//        return modelAndView;
//    }

}
