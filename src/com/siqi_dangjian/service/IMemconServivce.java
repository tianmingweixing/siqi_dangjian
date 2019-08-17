package com.siqi_dangjian.service;

import com.siqi_dangjian.bean.Memcon;
import java.util.List;
import java.util.Map;

public interface IMemconServivce {

    Map getMemconByType(Integer type) throws Exception;

    Map getMemconById(Long id) throws Exception;

    void insertOrUpdate(Memcon Memcon) throws Exception;

    void logicDelete(List idList) throws Exception;

    void delete(List idList) throws Exception;

    Memcon selectById(Long id) throws Exception;

    Map selectAll(Map blurMap, Map intMap, Map dateMap, Integer limit, Integer page) throws Exception;
}
