package com.siqi_dangjian.service;

import com.siqi_dangjian.bean.Notice;
import java.util.List;
import java.util.Map;

public interface INoticeService {

    Map getUserByType(Integer type) throws Exception;

    Map getUserInfoById(Long id) throws Exception;

    void insertOrUpdate(Notice notice) throws Exception;

    void logicDelete(List idList) throws Exception;

    void delete(List idList) throws Exception;

    Notice selectById(Long id) throws Exception;

    Map selectAll(Map blurMap, Map intMap, Map dateMap, Integer limit, Integer page) throws Exception;
}
