package com.siqi_dangjian.service;

import com.siqi_dangjian.bean.MeetingType;
import java.util.List;
import java.util.Map;

public interface IMeetingTypeService {

    void insertOrUpdate(MeetingType meetingType) throws Exception;

    void logicDelete(List idList) throws Exception;

    void delete(List idList) throws Exception;

    MeetingType selectById(Long id) throws Exception;

    Map selectAll(Map blurMap, Map intMap, Map dateMap, Integer limit, Integer page) throws Exception;

    Map selectAllCategory(Map blurMap, Map intMap, Map dateMap);
}
