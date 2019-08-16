package com.siqi_dangjian.dao;


import com.siqi_dangjian.bean.Duty;

import java.util.List;
import java.util.Map;

public interface IDutyDao {
    void insertOrUpdate(Duty Duty) throws Exception;

    void logicDelete(List idList) throws Exception;

    void delete(List idList) throws Exception;

    Duty selectById(Long id) throws Exception;

    Map selectAll(Map blurParam, Map dateParam,
                  Map intParam, int limit, int page) throws Exception;
}
