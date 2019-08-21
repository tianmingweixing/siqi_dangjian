package com.siqi_dangjian.controller;



import com.siqi_dangjian.util.CommonString;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;


@RequestMapping("/upload")
@Controller
public class UploadController extends BaseController{


    Logger logger = Logger.getRootLogger();

    @RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
    @ResponseBody
    public ModelMap upload(@RequestParam("file")MultipartFile files, HttpServletRequest request, HttpServletResponse response) throws IOException {
        modelMap = new ModelMap();

        try{
            String name = files.getOriginalFilename();
            Calendar now = Calendar.getInstance();
            String year = String.valueOf(now.get(Calendar.YEAR));
            String month = String.valueOf(now.get(Calendar.MONTH)+1);
            String day = String.valueOf(now.get(Calendar.DAY_OF_MONTH));
            String path = CommonString.FILE_PARENT_PATH+CommonString.FILE_IMAGE_PATH+year+month+day;
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }
//            path = path + File.separator + name;
            path = path + "/" + name;
            File uploadFile = new File(path);
            files.transferTo(uploadFile);

            setSuccess();
            setData("src",path);
        }catch(Exception e){
            setFail("后台异常");
            e.printStackTrace();
            logger.error("upload--->uploadImage",e);
        }

        return modelMap;
    }



    /*@RequestMapping(value = "/uploadVedio", method={RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public ModelMap uploadVedio(@RequestParam(value = "vedio", required = false) MultipartFile multipartFile,
                                HttpServletRequest request) {
        modelMap = new ModelMap();
        String message = "";
        FileEntity entity = new FileEntity();
        VedioUploadTool fileUploadTool = new VedioUploadTool();
        try {
            entity = fileUploadTool.createFile(multipartFile, request);
            if (entity != null) {
                setSuccess();
            } else {
                setFail();
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("upload--->uploadVedio",e);
            setFail();
        }
        return modelMap;
    }*/
}
