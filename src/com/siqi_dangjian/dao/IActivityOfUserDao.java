package com.siqi_dangjian.dao;


import com.siqi_dangjian.bean.ActivityOfUser;

import java.util.List;
import java.util.Map;

public interface IActivityOfUserDao {

    Map getUserNameById(Long id) throws Exception;

    void insertOrUpdate(ActivityOfUser activityOfUser) throws Exception;

    void logicDelete(List idList) throws Exception;

    void delete(List idList) throws Exception;

    ActivityOfUser selectById(Long id) throws Exception;

    Map selectAll(Map blurParam, Map dateParam,
                  Map intParam, int limit, int page) throws Exception;
}
