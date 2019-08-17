package com.siqi_dangjian.service;

import com.siqi_dangjian.bean.Activities;
import java.util.List;
import java.util.Map;

public interface IActivityService {

    void addActivitiesContent(Activities activities) throws Exception;

    Map getActivityContentById(Long id);

    Map getActivityContentByType(Integer type);

    Map getActivityContentList(Map blurMap, Map intMap, Map dateMap,Integer limit,Integer page) throws Exception;

    void deleteActivity(List idList) throws Exception;

    void logicDeleteActivity(List idList) throws Exception;

    Activities getActivityById(Long id) throws Exception;
}
