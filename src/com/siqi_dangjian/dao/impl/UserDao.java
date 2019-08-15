package com.siqi_dangjian.dao.impl;

import com.siqi_dangjian.bean.User;
import com.siqi_dangjian.dao.IUserDao;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserDao extends BaseDao<User> implements IUserDao {


    @Override
    public void inserOrUpdateUser(User user) throws Exception {
        saveOrUpdateObject(user);
    }

    @Override
    public void logicDeleteUser(User user) throws Exception {
        logic_delete(user);
    }

    @Override
    public User selectUserById(Long id) throws Exception {
        return getObjectById(id);
    }

    @Override
    public Map selectAllUser(Map blurParam, Map dateParam, Map intParam, int limit, int page) throws Exception {
        return new HashMap();
    }
}
