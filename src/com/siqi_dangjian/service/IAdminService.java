package com.siqi_dangjian.service;

import com.siqi_dangjian.bean.Admin;

import java.util.List;
import java.util.Map;

public interface IAdminService {
    Admin Login(String adminName ,String password) throws Exception;

    void insertOrUpdate(Admin admin) throws Exception;

    void logicDelete(List idList) throws Exception;

    Map selectAll(Map blurParam, Map dateParam,
                  Map intParam, int limit, int page) throws Exception;
}
