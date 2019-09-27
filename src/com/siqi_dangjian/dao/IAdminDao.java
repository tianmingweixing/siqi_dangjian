package com.siqi_dangjian.dao;

import com.siqi_dangjian.bean.Admin;
import com.siqi_dangjian.bean.User;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.Map;


public interface IAdminDao {

    Admin selectAdminByAccount(String account);

    void insertOrUpdate(Admin admin) throws Exception;

    void logicDelete(List idList) throws Exception;

    void delete(List idList) throws Exception;

    void updateAuthority(Long id , String authority) throws Exception;

    Admin selectById(Long id) throws Exception;

    Map selectAll(Map blurParam, Map intParam,
                  Map dateParam, int limit, int page) throws Exception;
}
