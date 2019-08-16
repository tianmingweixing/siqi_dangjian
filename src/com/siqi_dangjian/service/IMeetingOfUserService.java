package com.siqi_dangjian.service;

import com.siqi_dangjian.bean.MeetingOfUser;
import java.util.List;
import java.util.Map;

public interface IMeetingOfUserService {

    Map getUserNameByType(Integer type) throws Exception;

    Map getUserNameById(Long id) throws Exception;

    void insertOrUpdate(MeetingOfUser meetingOfUser) throws Exception;

    void logicDelete(List idList) throws Exception;

    void delete(List idList) throws Exception;

    MeetingOfUser selectById(Long id) throws Exception;

    Map selectAll(Map blurMap, Map intMap, Map dateMap, Integer limit, Integer page) throws Exception;
}
