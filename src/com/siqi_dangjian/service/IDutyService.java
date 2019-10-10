package com.siqi_dangjian.service;

import com.siqi_dangjian.bean.Duty;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;


public interface IDutyService {

    Map getUserNameByType(Integer type) throws Exception;

    Map getUserNameById(Long id) throws Exception;

    BigInteger insertOrUpdate(Duty duty) throws Exception;

    void logicDelete(List idList) throws Exception;

    void delete(List idList) throws Exception;

    Duty selectById(Long id) throws Exception;

    Map selectAll(Map blurMap, Map intMap, Map dateMap, Integer limit, Integer page) throws Exception;

    List<Duty> selectList() throws Exception;

    Map selectAllCategory(Map blurMap, Map intMap, Map dateMap);

    Long selectByTypeName(Map blurMap) throws Exception;
}
