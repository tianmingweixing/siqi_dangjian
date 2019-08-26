package com.siqi_dangjian.service;

import com.siqi_dangjian.bean.Admin;

import java.util.List;
import java.util.Map;

public interface IAdminService {

    Admin getAdminByAccount(String account);

    Admin Login(Long name);

    Map getUserNameByType(Integer type) throws Exception;

    Admin selectById(Long id) throws Exception;

    void insertOrUpdate(Admin admin) throws Exception;

    void logicDelete(List idList) throws Exception;

    void delete(List idList) throws Exception;

    Map selectAll(Map blurMap, Map intMap, Map dateMap, Integer limit, Integer page) throws Exception;
}
