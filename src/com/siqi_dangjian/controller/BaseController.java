package com.siqi_dangjian.controller;

import com.siqi_dangjian.util.CommonString;
import org.springframework.ui.ModelMap;

public class BaseController {

    protected ModelMap modelMap;

    protected void setSuccess(){
        modelMap.addAttribute("code",CommonString.REQUEST_SUCCESS);
        modelMap.addAttribute("result", CommonString.RESULT_SUCCESS);
    }

    protected void setFail(){
        modelMap.addAttribute("result", CommonString.RESULT_FAIL);
    }

    protected void setFail(String msg){
        modelMap.addAttribute("result", CommonString.RESULT_FAIL);
        setMsg(msg);
    }

    protected void setCode(int code){
        modelMap.addAttribute("code", code);
    }
    protected void setData(String name,Object ob){
        modelMap.addAttribute(name,ob);
    }

    protected void removeData(String name){
        modelMap.remove(name);
    }

    protected Object getAttribute(String name){
       return modelMap.get(name);
    }
    protected void setMsg(String msg){
        modelMap.addAttribute("msg",msg);
    }
}
