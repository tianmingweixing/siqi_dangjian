package com.siqi_dangjian.dao;

import com.siqi_dangjian.bean.Notice;
import java.util.List;
import java.util.Map;

public interface INoticeDao {
    void insertOrUpdate(Notice notice) throws Exception;

    void logicDelete(List idList) throws Exception;

    void delete(List idList) throws Exception;

    Notice selectById(Long id) throws Exception;

    Map selectAll(Map blurParam, Map intParam, Map dateParam, int limit, int page) throws Exception;

    Map selectAll(Integer limit, Integer page);
}
