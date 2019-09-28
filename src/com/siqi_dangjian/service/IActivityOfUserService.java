package com.siqi_dangjian.service;

import com.siqi_dangjian.bean.ActivityOfUser;
import java.util.List;
import java.util.Map;

public interface IActivityOfUserService {

    Map getUserNameByType(Integer type) throws Exception;

    Map getUserNameById(Long id) throws Exception;

    void insertOrUpdate(ActivityOfUser activityOfUser) throws Exception;

    void logicDelete(List idList) throws Exception;

    void delete(List idList) throws Exception;

    ActivityOfUser selectById(Long id) throws Exception;

    Map selectAll(Map blurMap, Map intMap, Map dateMap, Integer limit, Integer page) throws Exception;

    List selectListById(Long user_id) throws Exception;

    void cancelSignIn(Long user_id, String activity_id) throws Exception;

    Integer selectCountByUserId(Long UserId) throws Exception;
}
