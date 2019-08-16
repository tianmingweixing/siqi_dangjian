package com.siqi_dangjian.dao;


import com.siqi_dangjian.bean.Activities;

import java.util.List;
import java.util.Map;

public interface IActivityDao {

    Map selectActivityContentById(Long id);

    Map selectActivityContentByType(Integer type);

    void insertOrUpdate(Activities activities) throws Exception;

    void logicDelete(List idList) throws Exception;

    void delete(List idList) throws Exception;

    Activities selectById(Long id) throws Exception;

    Map selectAll(Map blurParam, Map dateParam,
                          Map intParam, int limit, int page) throws Exception;
    }
