package com.siqi_dangjian.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.siqi_dangjian.bean.Conclusion;
import com.siqi_dangjian.bean.Notice;
import com.siqi_dangjian.service.IConclusionService;
import com.siqi_dangjian.service.INoticeService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.lang.reflect.Type;
import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 公示公告表
 */
@Controller
@RequestMapping("/notice")
public class NoticeController extends BaseController {

    @Autowired
    private INoticeService noticeService;

    Logger logger = Logger.getRootLogger();

    @RequestMapping("/gotoAdd")
    public ModelAndView gotoAdd() {
        ModelAndView view = new ModelAndView();
        view.setViewName("WEB-INF/page/notice_Add");//跳转添加页面

        return view;
    }

    /**
     * 逻辑删除
     * @return modelMap
     */
    @RequestMapping("/logicDelete")
    @ResponseBody
    public ModelMap logicDelete(@RequestParam(value="deleteArray", required=false)String deleteArray){
        modelMap = new ModelMap();
        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>() {}.getType();
        List<String> idList = gson.fromJson(deleteArray,type);
        try {
            noticeService.logicDelete(idList);
        } catch (Exception e){
            setFail("删除失败");
            logger.error("notice--->deleteNotice",e);
            return modelMap;
        }
        setSuccess();
        return modelMap;
    }



    /**
     * 添加或更新
     * @param id .
     * @param title
     * @param content .
     * @return ModelMap
     */
    @RequestMapping("/addNotice")
    @ResponseBody
    public ModelMap addNotice(@RequestParam(value = "file", required = false) CommonsMultipartFile files,
                              @RequestParam(value = "id", required = false) Long id,
                              @RequestParam(value = "title", required = false) String title,
                              @RequestParam(value = "content", required = false) String content) {

        ModelMap modelMap = new ModelMap();
        Notice notice;
        try {
            notice = new Notice();

            if (files != null) {

                String name = files.getOriginalFilename();
                String path = "/home/up_load/";
                Calendar now = Calendar.getInstance();
                String year = String.valueOf(now.get(Calendar.YEAR));
                String month = String.valueOf(now.get(Calendar.MONTH) + 1);
                String day = String.valueOf(now.get(Calendar.DAY_OF_MONTH));
                path = path + year + month + day;
                File uploadFile = new File(path);
                if (!uploadFile.exists()) {
                    uploadFile.mkdirs();
                }
                String filePath = path + "/" + name;

                files.transferTo(uploadFile);
                notice.setImagePath(filePath);
            }

            notice.setId(id);
            notice.setContent(content);
            notice.setTitle(title);
            notice.setCanUse(1);

            noticeService.insertOrUpdate(notice);
            setSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            setFail();
            logger.error("addNotice",e);
            return modelMap;
        }
        return modelMap;

    }


    /**
     * 编辑会议表
     * @param  id
     */
    @RequestMapping("/setNotice")
    public ModelAndView setNotice(@RequestParam(value = "Id", required = false) Long id){

        ModelAndView view = new ModelAndView();
        Notice  notice;

            try {
                notice = noticeService.selectById(id);
                view.addObject("id", notice.getId());
                view.addObject("content", notice.getContent());
                view.addObject("title", notice.getTitle());
                view.addObject("image_path", notice.getImagePath());
                view.addObject("partyBranchId", notice.getPartyBranchId());

               view.setViewName("WEB-INF/page/notice_Add");//跳转添加页面

            } catch (Exception e) {
                e.printStackTrace();
                logger.error("setConclusion",e);
                setMsg("获取数据错误");
            }
        return view;
    }


    /**
     * 查询会议表信息
     * @param title 标题
     * @param limit
     * @param page
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public ModelMap getNoticeList(@RequestParam(value = "title", required = false) String title,
                                  @RequestParam(value = "start_time_search", required = false) String start_time,
                                  @RequestParam(value = "end_time_search", required = false) String end_time,
                                  @RequestParam(value = "limit", required = false) Integer limit,
                                  @RequestParam(value = "page", required = false) Integer page) {

        modelMap = new ModelMap();

        Map blurMap = new HashMap<>();
        Map dateMap = new HashMap<>();
        Map intMap = new HashMap<>();


        if (StringUtils.isNotEmpty(title)) {
            blurMap.put("title", title);
        }

        if (StringUtils.isNotEmpty(start_time)) {
            dateMap.put("start_time", start_time);
        }

        if (StringUtils.isNotEmpty(end_time)) {
            dateMap.put("end_time", end_time);
        }


        try {
            Map map = noticeService.selectAll(blurMap, intMap, dateMap, limit, page);

            List list = (List<Notice>) map.get("list");
            Integer count = (int) map.get("count");
            setData("data", list);
            setData("count", count);
            setSuccess();
        } catch (Exception e) {
            setFail();
            e.printStackTrace();
            logger.error("getNoticeList",e);
        }

        return modelMap;
    }
}
