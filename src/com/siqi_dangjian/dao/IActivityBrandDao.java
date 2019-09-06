package com.siqi_dangjian.dao;

import com.siqi_dangjian.bean.ActivitiesBrand;

import java.util.List;
import java.util.Map;

public interface IActivityBrandDao {

    void insertOrUpdate(ActivitiesBrand activitiesBrand) throws Exception;

    void logicDelete(List idList) throws Exception;

    void delete(List idList) throws Exception;

    ActivitiesBrand selectById(Long id) throws Exception;

    Map selectAll(int limit, int page) throws Exception;

    List<ActivitiesBrand> selectList() throws Exception;
    }
