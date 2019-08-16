package com.siqi_dangjian.dao;

import com.siqi_dangjian.bean.Configuration;

import java.util.List;
import java.util.Map;

public interface IConfigurationDao {
    void insertOrUpdate(Configuration configuration) throws Exception;

    void logicDelete(List idList) throws Exception;

    void delete(List idList) throws Exception;

    Configuration selectById(Long id) throws Exception;

    Map selectAll(Map blurParam, Map dateParam,
                  Map intParam, int limit, int page) throws Exception;
}
