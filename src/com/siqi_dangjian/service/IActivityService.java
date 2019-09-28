package com.siqi_dangjian.service;

import com.siqi_dangjian.bean.Activities;
import java.util.List;
import java.util.Map;

public interface IActivityService {

    void insertOrUpdate(Activities activities) throws Exception;

    void delete(List idList) throws Exception;

    void logicDelete(List idList) throws Exception;

    Map selectAll(Map blurParam, Map intParam, Map dateParam, int limit, int page) throws Exception;

    Map getActivityByType(Integer type) throws Exception;

    Activities selectById(Long id) throws Exception;

    Map selectActivitiesInfoById(Long id) throws Exception;

    Integer selectActivityCountByType(Long type_id) throws Exception;

    Map selectActivityGroupCount();

    Map selectActivitysByState(int state, int limit, int page) throws Exception;

    String selectSignInById(Long id) throws Exception;

    Map selectActivityStatusGroupCount();
}
