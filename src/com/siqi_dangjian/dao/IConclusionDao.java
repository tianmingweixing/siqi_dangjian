package com.siqi_dangjian.dao;

import com.siqi_dangjian.bean.Conclusion;

import java.util.List;
import java.util.Map;

public interface IConclusionDao {
    void insertOrUpdate(Conclusion conclusion) throws Exception;

    void logicDelete(List idList) throws Exception;

    void delete(List idList) throws Exception;

    Conclusion selectById(Long id) throws Exception;

    Map selectAll(Map blurParam,Map intParam, Map dateParam, int limit, int page) throws Exception;
}
