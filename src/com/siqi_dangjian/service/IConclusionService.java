package com.siqi_dangjian.service;

import com.siqi_dangjian.bean.Conclusion;
import java.util.List;
import java.util.Map;


public interface IConclusionService {
    Map getUserByType(Integer type) throws Exception;

    Map getUserInfoById(Long id) throws Exception;

    void insertOrUpdate(Conclusion conclusion) throws Exception;

    void logicDelete(List idList) throws Exception;

    void delete(List idList) throws Exception;

    Conclusion selectById(Long id) throws Exception;

    Map selectAll(Map blurMap, Map intMap, Map dateMap, Integer limit, Integer page) throws Exception;
}
