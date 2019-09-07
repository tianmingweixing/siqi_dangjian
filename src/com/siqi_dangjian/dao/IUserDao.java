package com.siqi_dangjian.dao;

import com.siqi_dangjian.bean.User;
import java.util.List;
import java.util.Map;

public interface IUserDao {

    public void saveOrUpDate(User user1) throws Exception;

    User getUserByOpenId(String openId);

    Map getUserByType(Integer type) throws Exception;

    Map getUserInfoById(Long id) throws Exception;

    void insertOrUpdate(User user) throws Exception;

    void logicDelete(List idList) throws Exception;

    void delete(List idList) throws Exception;

    User selectById(Long id) throws Exception;

    Map selectGroupCount() throws Exception;

    Map selectAll(Map blurParam,Map intParam, Map dateParam, int limit, int page) throws Exception;
}
