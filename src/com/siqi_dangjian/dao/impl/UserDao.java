package com.siqi_dangjian.dao.impl;

import com.siqi_dangjian.bean.User;
import com.siqi_dangjian.dao.IUserDao;
import com.siqi_dangjian.util.CommonUtil;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserDao extends BaseDao<User> implements IUserDao {


    @Override
    public void insertOrUpdateUser(User user) throws Exception {
        saveOrUpdateObject(user);
    }

    @Override
    public void logicDeleteUser(List idList) throws Exception {
        session = sessionFactory.getCurrentSession();
        String sql = "update user set isUse = 0";
        sql = CommonUtil.appendInSql(sql,idList,"id");
        SQLQuery query = session.createSQLQuery(sql);
        query.executeUpdate();
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
