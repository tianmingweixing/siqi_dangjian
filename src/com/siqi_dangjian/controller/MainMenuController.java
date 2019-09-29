package com.siqi_dangjian.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("mainMenu")
public class MainMenuController {

    @RequestMapping(value = "/goto1", method = RequestMethod.GET)
    public ModelAndView go(HttpServletResponse response){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("WEB-INF/page/mainMenu");
        response.setCharacterEncoding("UTF-8");
        return modelAndView;
    }

//    @RequestMapping(value = "/goto2",produces="text/html; charset=UTF-8")
//    public ModelAndView go2(HttpServletResponse response){
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("WEB-INF/page/test2");
//        response.setCharacterEncoding("UTF-8");
//        return modelAndView;
//    }

    @RequestMapping(value = "/gotoGoodList",produces="text/html; charset=UTF-8")
    public ModelAndView gotoGoodList(HttpServletResponse response){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("frame/goodList");
        response.setCharacterEncoding("UTF-8");
        return modelAndView;
    }
}
