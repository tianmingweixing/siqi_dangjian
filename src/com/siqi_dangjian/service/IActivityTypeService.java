package com.siqi_dangjian.service;

import com.siqi_dangjian.bean.ActivitiesType;
import java.util.List;
import java.util.Map;

public interface IActivityTypeService {
    void insertOrUpdate(ActivitiesType activitiesType) throws Exception;

    void logicDelete(List idList) throws Exception;

    void delete(List idList) throws Exception;

    ActivitiesType selectById(Long id) throws Exception;

    Map selectAll(int limit, int page) throws Exception;

    List<ActivitiesType> selectList() throws Exception;
}
