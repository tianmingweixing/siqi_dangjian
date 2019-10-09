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
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.UUID;


@RequestMapping("/upload")
@Controller
public class UploadController extends BaseController{


    Logger logger = Logger.getRootLogger();

    @RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
    @ResponseBody
    public ModelMap upload(@RequestParam("file")MultipartFile[] files, HttpServletRequest request){
        modelMap = new ModelMap();

        try {
            if(files != null && files.length > 0){
                String[] strings = new String[files.length];
                int i = 0;
                for (MultipartFile file : files) {
                    String saveFilename = file.getOriginalFilename();
                    Calendar now = Calendar.getInstance();
                    String year = String.valueOf(now.get(Calendar.YEAR));
                    String month = String.valueOf(now.get(Calendar.MONTH) + 1);
                    String day = String.valueOf(now.get(Calendar.DAY_OF_MONTH));
                    //上传文件在本地的路径
                    String file_path = CommonString.FILE_PARENT_PATH + CommonString.FILE_IMAGE_PATH + year + month + day + "/" + saveFilename;

                    // 上传文件在服务器中的位置(目录绝对路径)
//                    String saveServerPath = request.getSession().getServletContext().getRealPath(CommonString.FILE_PARENT_PATH + CommonString.FILE_IMAGE_PATH + year + month + day);
                    String saveServerPath = CommonString.FILE_PARENT_PATH + CommonString.FILE_IMAGE_PATH + year + month + day;
                    File filePath = new File(new File(saveServerPath).getAbsolutePath() + "/" + saveFilename);//文件的完整路径
                    if (!filePath.getParentFile().exists()) {
                        filePath.getParentFile().mkdirs();
                    }
                    file.transferTo(filePath);
                    strings[i] = file_path;
                    i++;
                }
                    setData("src",strings);
                    setSuccess();
            }else{
                setData("src",null);
                setFail("上传图片异常");
            }

        } catch (IOException e) {
            setFail("上传图片异常");
            e.printStackTrace();
            logger.error("upload--->uploadImage", e);
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
