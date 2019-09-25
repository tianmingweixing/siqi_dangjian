package com.siqi_dangjian.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.siqi_dangjian.bean.Meeting;
import com.siqi_dangjian.bean.MeetingOfUser;
import com.siqi_dangjian.service.IMeetingOfUserService;
import com.siqi_dangjian.service.IMeetingService;
import com.siqi_dangjian.util.CommonString;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/meeting")
public class MeetingController extends BaseController {

    @Autowired
    private IMeetingService meetingService;

    @Autowired
    private IMeetingOfUserService meetingOfUserService;

    Logger logger = Logger.getRootLogger();


    /**
     * 会议签到
     * @return
     */
    @RequestMapping("/signIn")
    @ResponseBody
    public ModelMap addMeetingSignIn(@RequestParam(value = "user_id", required = false) Long user_id,
                               @RequestParam(value = "meeting_id", required = false) String meeting_id) {

        modelMap = new ModelMap();
        MeetingOfUser meetingOfUser;
        try {
            if (StringUtils.isNotEmpty(String.valueOf(user_id))) {
                List  list =  meetingOfUserService.selectListById(user_id);

                for(int i=0;i<list.size();i++){
                    Map map = (Map) list.get(i);
                    String meetingId = String.valueOf(map.get("meeting_id"));
                    if (meetingId.equals(meeting_id)) {
                        setMsg("用户已签到");
                        return modelMap;
                    }
                }
                meetingOfUser = new MeetingOfUser();
                meetingOfUser.setMeetingId(Long.valueOf(meeting_id));
                meetingOfUser.setUserId(user_id);
                meetingOfUser.setCanUse(1);
                meetingOfUserService.insertOrUpdate(meetingOfUser);
                setSuccess();
            }
        } catch (Exception e) {
            e.printStackTrace();
            setFail();
            return modelMap;
        }
        return modelMap;

    }

    /**
     * 取消会议签到
     * @return
     */
    @RequestMapping("/cancelSignIn")
    @ResponseBody
    public ModelMap cancelSignIn(@RequestParam(value = "user_id", required = false) Long user_id,
                                 @RequestParam(value = "meeting_id", required = false) String meeting_id) {

        modelMap = new ModelMap();
        try {
            if (StringUtils.isNotEmpty(String.valueOf(user_id))) {
                List  list =  meetingOfUserService.selectListById(user_id);

                for(int i=0;i<list.size();i++){
                    Map map = (Map) list.get(i);
                    String meetingId = String.valueOf(map.get("meeting_id"));
                    if (meetingId.equals(meeting_id)) {
                        meetingOfUserService.cancelSignIn(user_id,meeting_id);
                        setSuccess();
                        setMsg("取消签到成功!");
                        return modelMap;
                    }
                }
            }
            setFail("该用户没有签到");
            setCode(CommonString.FRONT_EXPECTION);
        } catch (Exception e) {
            e.printStackTrace();
            setFail("取消签到失败");
            setCode(CommonString.BACK_EXPECTION);
            return modelMap;
        }
        return modelMap;

    }


    @RequestMapping("/gotoAdd")
    public String gotoAdd(@RequestParam(value = "id", required = false) Long id) {
        return "WEB-INF/page/meeting_Add";
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
     * @param meetingTypeId  1：支委会；2：党员大会；3：廉政...
     * @return
     */
    @RequestMapping("/addMeeting")
    public ModelAndView addMeeting(@RequestParam(value = "id", required = false) Long id,
                                   @RequestParam(value = "start_time", required = false) String start_time,
                                   @RequestParam(value = "end_time", required = false) String end_time,
                                   @RequestParam(value = "compere", required = false) String compere,
                                   @RequestParam(value = "recorder", required = false) String recorder,
                                   @RequestParam(value = "people_counting", required = false) String people_counting,
                                   @RequestParam(value = "attendance", required = false) String attendance,
                                   @RequestParam(value = "address", required = false) String address,
                                   @RequestParam(value = "name", required = false) String name,
                                   @RequestParam(value = "imgPath", required = false) String images_a,
                                   @RequestParam(value = "guide", required = false) String guide,
                                   @RequestParam(value = "content", required = false) String content,
                                   @RequestParam(value = "meeting_type_id", required = false) Long meetingTypeId) {

        ModelAndView modelAndView = new ModelAndView();

        try {
            Meeting meeting = new Meeting();
            meeting.setId(id);
            meeting.setName(name);
            meeting.setAddress(address);
            meeting.setRecorder(recorder);
            meeting.setCompere(compere);
            meeting.setAttendance(attendance);
            meeting.setPeopleCounting(people_counting);
            meeting.setContent(content);
            meeting.setGuide(guide);
            meeting.setMeetingTypeId(meetingTypeId);
            meeting.setImagesA(images_a);
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
                String userName = meetingService.selectSignInById(id);
                meeting = meetingService.selectById(id);
                view.addObject("userName",userName);
                view.addObject("id", meeting.getId());
                view.addObject("meeting_type_id", meeting.getMeetingTypeId());
                view.addObject("content", meeting.getContent());
                view.addObject("guide", meeting.getGuide());
                view.addObject("name", meeting.getName());
                view.addObject("compere", meeting.getCompere());
                view.addObject("recorder", meeting.getRecorder());
                view.addObject("people_counting", meeting.getPeopleCounting());
                view.addObject("attendance", meeting.getAttendance());
                view.addObject("address", meeting.getAddress());
                view.addObject("start_time", meeting.getStartTime());
                view.addObject("end_time", meeting.getEndTime());

                String images_a = meeting.getImagesA();
                String[] path = images_a.split(",");
                ArrayList<Map> list = new ArrayList<>();
                for (int i = 0; i < path.length; i++) {
                    Map map = new HashMap<>();
                    map.put("url",path[i]);
                    map.put("index",i);
                    list.add(i,map);
                }

                JSONArray imgObject = JSONArray.fromObject(list);
                view.addObject("images_a",imgObject.toString());
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
     * @param meeting_type_id
     * @param limit
     * @param page
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public ModelMap getMeetingList(@RequestParam(value = "name", required = false) String name,
                                    @RequestParam(value = "meeting_type_id", required = false) String meeting_type_id,
                                    @RequestParam(value = "meeting_id_search", required = false) String meeting_id_search,
                                    @RequestParam(value = "start_time", required = false) String start_time,
                                    @RequestParam(value = "end_time", required = false) String end_time,
                                    @RequestParam(value = "limit", required = false) Integer limit,
                                    @RequestParam(value = "page", required = false) Integer page) {

        modelMap = new ModelMap();

        Map blurMap = new HashMap<>();
        Map dateMap = new HashMap<>();
        Map intMap = new HashMap<>();


        if (StringUtils.isNotEmpty(meeting_type_id)) {
            intMap.put("meeting_type_id", meeting_type_id);
        }
        if (StringUtils.isNotEmpty(meeting_id_search)) {
            intMap.put("id", meeting_id_search);
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
