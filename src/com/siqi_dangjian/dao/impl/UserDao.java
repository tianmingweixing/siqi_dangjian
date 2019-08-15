package com.siqi_dangjian.dao.impl;

import com.siqi_dangjian.bean.User;
import com.siqi_dangjian.dao.IUserDao;
import com.siqi_dangjian.util.CommonUtil;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

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

    public void delete(List idList) throws Exception{
        session = sessionFactory.getCurrentSession();
        String sql = "delete user";
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
        session = sessionFactory.getCurrentSession();
//        "\tDATE_FORMAT(u.create_time, '%Y-%m-%d') create_time,\n" +
//                "\tIFNULL(G.brief,\"暂无信息\") brief,\n" +
        String sql = "\tSELECT * FROM \n" +
                "\tuser u LEFT JOIN duty d ON u.dutyid = d.id\n" +
                "\tWHERE\n" +
                "\tu.can_use = 1 and d.can_use=1";

        String sqlCount = "SELECT\n" +
                "  count(*) count\n" +
                "FROM user u LEFT JOIN duty d ON u.dutyid = d.id  where u.can_use = 1 and d.can_use=1";
        sql = CommonUtil.appendBlurStr(sql,blurParam);
        sql = CommonUtil.appendDateStr(sql,dateParam,"u");
        sql = CommonUtil.appendIntStr(sql,intParam,"u");
        sqlCount = CommonUtil.appendBlurStr(sqlCount,blurParam);
        sqlCount = CommonUtil.appendDateStr(sqlCount,dateParam,"u");
        sqlCount = CommonUtil.appendIntStr(sqlCount,intParam,"u");
        Map resMap = CommonUtil.queryList(session,sql,sqlCount,limit,page);
        return resMap;

    }

}
