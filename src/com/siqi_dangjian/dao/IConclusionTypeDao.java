package com.siqi_dangjian.dao;

import com.siqi_dangjian.bean.ConclusionType;

import java.util.List;
import java.util.Map;

public interface IConclusionTypeDao {
    void insertOrUpdate(ConclusionType conclusionType) throws Exception;

    void logicDelete(List idList) throws Exception;

    void delete(List idList) throws Exception;

    ConclusionType selectById(Long id) throws Exception;

    Map selectAll(Map blurParam, Map intParam, Map dateParam, int limit, int page) throws Exception;

}
