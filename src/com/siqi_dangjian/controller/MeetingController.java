package com.siqi_dangjian.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.siqi_dangjian.bean.Meeting;
import com.siqi_dangjian.service.IMeetingService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import java.lang.reflect.Type;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/meeting")
public class MeetingController extends BaseController {

    @Autowired
    private IMeetingService meetingService;

    Logger logger = Logger.getRootLogger();

    @RequestMapping("/gotoAdd")
    public ModelAndView gotoAdd(@RequestParam(value = "id", required = false) Long id) {
        ModelAndView view = new ModelAndView();
        view.setViewName("WEB-INF/page/meeting_Add");
        return view;
    }

    /**
     * 逻辑删除
     * @return
     */
    @RequestMapping("/logicDelete")
    @ResponseBody
    public ModelMap logicDelete(@RequestParam(value="deleteArray", required=false)String deleteArray){
        modelMap = new ModelMap();
        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>() {}.getType();
        List<String> idList = gson.fromJson(deleteArray,type);
        try {
            meetingService.logicDelete(idList);
        } catch (Exception e){
            setFail("删除失败");
            logger.error("meeting--->deleteMeeting",e);
            return modelMap;
        }
        setSuccess();
        return modelMap;
    }



    /**
     * 添加或更新
     * @param id .
     * @param start_time .
     * @param guide  会议指导
     * @param name .
     * @param content .
     * @param meeting_type  1：支委会；2：党员大会；3：廉政...
     * @return
     */
    @RequestMapping("/addMeeting")
    public ModelAndView addMeeting(@RequestParam(value = "id", required = false) Long id,
                                    @RequestParam(value = "start_time", required = false) String start_time,
                                    @RequestParam(value = "end_time", required = false) String end_time,
                                    @RequestParam(value = "name", required = false) String name,
                                    @RequestParam(value = "guide", required = false) String guide,
                                    @RequestParam(value = "content", required = false) String content,
                                    @RequestParam(value = "meeting_type", required = false) Integer meeting_type) {

        ModelAndView modelAndView = new ModelAndView();

        try {
            Meeting meeting = new Meeting();
            meeting.setId(id);
            meeting.setName(name);
            meeting.setContent(content);
            meeting.setGuide(guide);
            meeting.setMeetingType(meeting_type);
            meeting.setEndTime(Date.valueOf(start_time));
            meeting.setStartTime(Date.valueOf(end_time));
            meeting.setCanUse(1);
            meetingService.insertOrUpdate(meeting);
            setSuccess();
            modelAndView.setViewName("frame/meetingList");

        } catch (Exception e) {
            e.printStackTrace();
            modelAndView.setViewName("WEB-INF/page/meeting_Add");
            return modelAndView;
        }
        return modelAndView;

    }


    /**
     * 编辑会议表
     * @param  id
     */
    @RequestMapping("/setMeeting")
    public ModelAndView setMeeting(@RequestParam(value = "Id", required = false) Long id){

        ModelAndView view = new ModelAndView();
        Meeting  meeting;

            try {
                meeting = meetingService.selectById(id);
                view.addObject("id", meeting.getId());
                view.addObject("meeting_type", meeting.getMeetingType());
                view.addObject("content", meeting.getContent());
                view.addObject("guide", meeting.getGuide());
                view.addObject("name", meeting.getName());
                view.addObject("start_time", meeting.getStartTime());
                view.addObject("end_time", meeting.getEndTime());


                view.setViewName("WEB-INF/page/meeting_Add");
            } catch (Exception e) {
                e.printStackTrace();
                setMsg("获取数据错误");
            }
        return view;
    }


    /**
     * 查询会议表信息
     * @param name
     * @param meeting_type
     * @param limit
     * @param page
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public ModelMap getMeetingList(@RequestParam(value = "name", required = false) String name,
                                    @RequestParam(value = "meeting_type", required = false) String meeting_type,
                                    @RequestParam(value = "start_time", required = false) String start_time,
                                    @RequestParam(value = "end_time", required = false) String end_time,
                                    @RequestParam(value = "limit", required = false) Integer limit,
                                    @RequestParam(value = "page", required = false) Integer page) {

        modelMap = new ModelMap();

        Map blurMap = new HashMap<>();
        Map dateMap = new HashMap<>();
        Map intMap = new HashMap<>();


        if (StringUtils.isNotEmpty(meeting_type)) {
            intMap.put("meeting_type", meeting_type);
        }

        if (StringUtils.isNotEmpty(name)) {
            blurMap.put("name", name);
        }

        if (StringUtils.isNotEmpty(start_time)) {
            dateMap.put("start_time", start_time);
        }

        if (StringUtils.isNotEmpty(end_time)) {
            dateMap.put("end_time", end_time);
        }

        try {
            Map map = meetingService.selectAll(blurMap, intMap, dateMap, limit, page);

            List list = (List<Meeting>) map.get("list");
            Integer count = (int) map.get("count");
            setData("data", list);
            setData("count", count);
            setSuccess();
        } catch (Exception e) {
            setFail();
            e.printStackTrace();
        }

        return modelMap;
    }
}
