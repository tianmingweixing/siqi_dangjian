package com.siqi_dangjian.dao;

import com.siqi_dangjian.bean.User;
import java.util.List;
import java.util.Map;

public interface IUserDao {

    Map getUserByType(Integer type) throws Exception;

    Map getUserInfoById(Long id) throws Exception;

    void insertOrUpdate(User user) throws Exception;

    void logicDelete(List idList) throws Exception;

    void delete(List idList) throws Exception;

    User selectById(Long id) throws Exception;

    Map selectAll(Map blurParam, Map dateParam,
                      Map intParam, int limit, int page) throws Exception;
}
