package com.siqi_dangjian.dao;

import com.siqi_dangjian.bean.Tips;

import java.util.List;
import java.util.Map;

public interface ITipsDao {
    void insertOrUpdate(Tips tips) throws Exception;

    void logicDelete(List idList) throws Exception;

    void delete(List idList) throws Exception;

    Tips selectById(Long id) throws Exception;

    Map selectAll(Map blurParam, Map dateParam,
                      Map intParam, int limit, int page) throws Exception;

    Map selectActivityTips(Long id, Integer type, Integer limit, Integer page) throws Exception;


    List selectByUserId(Long id);

    String selectActivityIdByUserId(Long id);
}
