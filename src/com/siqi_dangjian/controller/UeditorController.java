package com.siqi_dangjian.controller;

import com.siqi_dangjian.bean.PublicMsg;
import com.siqi_dangjian.bean.Ueditor;
import com.siqi_dangjian.util.CommonString;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

@Controller
@RequestMapping("/ueditor")
public class UeditorController {
    @RequestMapping("/")
    private String showPage(){
        return "index";
    }

    @RequestMapping(value="/setUeditorConfig")
    @ResponseBody
    public String ueditor(HttpServletRequest request) {

        return PublicMsg.UEDITOR_CONFIG;
    }

    @RequestMapping(value="/imgUpload")
    @ResponseBody
    public Ueditor imgUpload(MultipartFile upfile, HttpServletRequest request) {
        //年月日
        Calendar now = Calendar.getInstance();
        String year = String.valueOf(now.get(Calendar.YEAR));
        String month = String.valueOf(now.get(Calendar.MONTH) + 1);
        String day = String.valueOf(now.get(Calendar.DAY_OF_MONTH));
        Ueditor ueditor = new Ueditor();
        try {

            // 获取文件名
            String fileName = upfile.getOriginalFilename();
            // 获取文件后缀
            String subkey = fileName.substring(fileName.lastIndexOf("."));
            // 重新生成文件名称
            String saveFilename = String.valueOf(new Date().getTime()) + subkey;
            //
            //本地的路径
            String file_path = CommonString.FILE_PARENT_PATH + CommonString.FILE_IMAGE_PATH + year + month + day + "/" + saveFilename;

            //服务器中的位置(目录绝对路径)
//            String saveServerPath = request.getSession().getServletContext().getRealPath(CommonString.FILE_PARENT_PATH + CommonString.FILE_IMAGE_PATH + year + month + day);
            String saveServerPath = "https://sqdjapi.ahyuntin.com"+ CommonString.FILE_PARENT_PATH + CommonString.FILE_IMAGE_PATH + year + month + day;
            File filePath = new File(new File(saveServerPath).getAbsolutePath() + "/" + saveFilename);//文件的完整路径
            if (!filePath.getParentFile().exists()) {
                filePath.getParentFile().mkdirs();
            }
            upfile.transferTo(filePath);

            ueditor.setState("SUCCESS");
            ueditor.setTitle(fileName);
            ueditor.setOriginal(fileName);
            ueditor.setUrl(file_path);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return ueditor;
    }
}
