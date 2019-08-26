package com.siqi_dangjian.controller;

import com.siqi_dangjian.bean.Configuration;
import com.siqi_dangjian.service.impl.ConfigurationService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Array;
import java.util.List;

@Controller
@RequestMapping(value = "/config")
public class ConfigurationController extends BaseController{

    @Autowired
    private ConfigurationService configurationService;

    Logger logger = Logger.getRootLogger();

    @ResponseBody
    @RequestMapping(value = "/getConfig", method = RequestMethod.GET)
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
            setMsg("获取信息成功");
        }catch (Exception e){
            e.printStackTrace();
            setFail("获取信息异常");
            logger.error("config --> getConfig");
        }
        return modelMap;
    }

    @ResponseBody
    @RequestMapping(value = "/saveLunbo", method = RequestMethod.POST)
    public ModelMap saveLunbo(@RequestParam("pathArr[]") List<String> pathArr) {
        modelMap = new ModelMap();
        try {
            Configuration comInfo = configurationService.selectById(1L);
            if (pathArr.size() > 0){
                comInfo.setImagePathA(pathArr.get(0));
            }
            if (pathArr.size() > 1){
                comInfo.setImagePathB(pathArr.get(1));
            }
            if (pathArr.size() > 2){
                comInfo.setImagePathC(pathArr.get(2));
            }
            if (pathArr.size() > 3){
                comInfo.setImagePathD(pathArr.get(3));
            }
            if (pathArr.size() > 4){
                comInfo.setImagePathE(pathArr.get(4));
            }
            configurationService.insertOrUpdate(comInfo);
            setSuccess();
        }catch (Exception e){
            e.printStackTrace();
            setFail("设置轮播图失败");
            logger.error("config --> getConfig");
        }
        return modelMap;
    }

    @ResponseBody
    @RequestMapping(value = "/setConfig", method = RequestMethod.POST)
    public ModelMap setComInfo(@RequestParam(value = "com_name", required = false) String com_name
                        ,@RequestParam(value = "com_phone", required = false) String com_phone
                        ,@RequestParam(value = "com_info", required = false) String com_info
                        ,@RequestParam(value = "com_img", required = false) String com_img) {
        modelMap = new ModelMap();
        try {
            Configuration comInfo = configurationService.selectById(1L);
            comInfo.setComName(com_name);
            comInfo.setComInfo(com_info);
            comInfo.setComImg(com_img);
            comInfo.setComPhone(com_phone);

            configurationService.insertOrUpdate(comInfo);
            setSuccess();
            setMsg("设置成功");
        }catch (Exception e){
            e.printStackTrace();
            setFail("设置失败");
            logger.error("config --> getConfig");
        }
        return modelMap;
    }

    @RequestMapping("/deleteImage")
    @ResponseBody
    public ModelMap deleteImage(@RequestParam(value = "index",required = false)String index){
        modelMap = new ModelMap();
        try {
            Configuration comInfo = configurationService.selectById(1L);
            if (index.equals("0")){
                comInfo.setImagePathA("");
            }
            if (index.equals("1")){
                comInfo.setImagePathB("");
            }
            if (index.equals("2")){
                comInfo.setImagePathC("");
            }
            if (index.equals("3")){
                comInfo.setImagePathD("");
            }
            if (index.equals("4")){
                comInfo.setImagePathE("");
            }
            configurationService.insertOrUpdate(comInfo);

            setSuccess();
        } catch (Exception e){
            setFail("后台异常");
            logger.error("upload--->deleteImage",e);
            return modelMap;
        }
        return modelMap;
    }
}
