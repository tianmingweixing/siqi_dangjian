package com.siqi_dangjian.dao;


import com.siqi_dangjian.bean.Duty;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

public interface IDutyDao {
    BigInteger insertOrUpdate(Duty Duty) throws Exception;

    void logicDelete(List idList) throws Exception;

    void delete(List idList) throws Exception;

    Duty selectById(Long id) throws Exception;

    Map selectAll(Map blurParam,Map intParam, Map dateParam, int limit, int page) throws Exception;

    Map selectAll(Map blurMap, Map intMap, Map dateMap);
}
