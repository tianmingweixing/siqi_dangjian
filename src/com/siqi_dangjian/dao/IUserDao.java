package com.siqi_dangjian.dao;

import com.siqi_dangjian.bean.User;

import java.util.List;
import java.util.Map;

public interface IUserDao {

    void insertOrUpdateUser(User user) throws Exception;

    void logicDeleteUser(List idList) throws Exception;

    User selectUserById(Long id) throws Exception;

    Map selectAllUser(Map blurParam, Map dateParam,
                      Map intParam, int limit, int page) throws Exception;
}
