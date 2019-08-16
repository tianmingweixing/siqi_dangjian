package com.siqi_dangjian.service;

import com.siqi_dangjian.bean.Activities;

import java.util.List;
import java.util.Map;

public interface IActivitiesService {
    void addTextContent(Activities activities);

    Map getActivityContentById(Long id);

    Map getActivityContentByType(Integer type);

    Map getActivityContentList(Map blurMap, Map intMap, Map dateMap,Integer limit,Integer page);

    void deleteActivity(List activityList);

    Integer getAllHotCount();

    Integer findByActivity(Long id);

    Activities getActivityById(Long id);
}
