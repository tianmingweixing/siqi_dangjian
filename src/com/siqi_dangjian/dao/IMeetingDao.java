package com.siqi_dangjian.dao;

import com.siqi_dangjian.bean.BaseBean;
import com.siqi_dangjian.bean.Meeting;
import com.siqi_dangjian.bean.MeetingOfUser;

import java.util.List;
import java.util.Map;

public interface IMeetingDao {
    void insertOrUpdate(Meeting meeting) throws Exception;

    void logicDelete(List idList) throws Exception;

    void delete(List idList) throws Exception;

    BaseBean selectById(Long id) throws Exception;

    Map selectAll(Map blurParam, Map intParam, Map dateParam,
                   int limit, int page) throws Exception;

    Integer selectMeetingCountByType(String coum, Long parem) throws Exception;

    void insertOrUpdate(MeetingOfUser meetingOfUser);

    String selectSignInById(Long id) throws Exception;
}
