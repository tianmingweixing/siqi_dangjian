package com.siqi_dangjian.dao;

import com.siqi_dangjian.bean.Admin;
import com.siqi_dangjian.bean.User;
import java.util.List;
import java.util.Map;


public interface IAdminDao {
    void insertOrUpdate(Admin admin) throws Exception;

    void logicDelete(List idList) throws Exception;

    void delete(List idList) throws Exception;

    Admin selectById(Long id) throws Exception;

    Map selectAll(Map blurParam, Map dateParam,
                  Map intParam, int limit, int page) throws Exception;
}
