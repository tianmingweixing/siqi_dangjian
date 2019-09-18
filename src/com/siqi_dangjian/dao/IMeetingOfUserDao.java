package com.siqi_dangjian.dao;

import com.siqi_dangjian.bean.MeetingOfUser;
import com.siqi_dangjian.bean.User;

import java.util.List;
import java.util.Map;

public interface IMeetingOfUserDao {
    void insertOrUpdate(MeetingOfUser meetingOfUser) throws Exception;

    void logicDelete(List idList) throws Exception;

    void delete(List idList) throws Exception;

    MeetingOfUser selectById(Long id) throws Exception;

    Map selectAll(Map blurParam, Map dateParam,
                  Map intParam, int limit, int page) throws Exception;

    List selectListById(Long user_id);
}
