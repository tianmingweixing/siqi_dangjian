package com.siqi_dangjian.dao.impl;

import com.siqi_dangjian.bean.ActivityOfUser;
import com.siqi_dangjian.dao.IActivityOfUserDao;
import com.siqi_dangjian.util.CommonUtil;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@Repository
public class ActivityOfUserDao extends BaseDao<ActivityOfUser> implements IActivityOfUserDao {

    @Override
    public Map getUserNameById(Long id) throws Exception {
        return null;
    }

    @Override
    public void insertOrUpdate(ActivityOfUser activityOfUser) throws Exception {
        saveOrUpdateObject(activityOfUser);
    }

    @Override
    public void logicDelete(List idList) throws Exception {
        session = sessionFactory.getCurrentSession();
        String sql = "update activity_of_user set isUse = 0";
        sql = CommonUtil.appendInSql(sql,idList,"id");
        SQLQuery query = session.createSQLQuery(sql);
        query.executeUpdate();
    }

    @Override
    public void delete(List idList) throws Exception{
        session = sessionFactory.getCurrentSession();
        String sql = "delete from activity_of_user";
        sql = CommonUtil.appendInSql(sql,idList,"id");
        SQLQuery query = session.createSQLQuery(sql);
        query.executeUpdate();
    }

    @Override
    public ActivityOfUser selectById(Long id) throws Exception {
        return getObjectById(id);
    }

    @Override
    public Map selectAll(Map blurMap, Map dateMap, Map intMap, int limit, int page) throws Exception {
        session = sessionFactory.getCurrentSession();
        String sql = "SELECT\n" +
                "\tau.user_id,au.activity_id,a.title,u.username,u.head_img,u.nick_name\n" +
                "FROM\n" +
                "\tactivity_of_user au\n" +
                "INNER JOIN `user` u ON au.user_id = u.id\n" +
                "INNER JOIN activities a ON au.activity_id = a.id\n" +
                "WHERE\n" +
                "\tau.can_use = 1 and a.can_use = 1 and u.can_use = 1";

        String sqlCount = "SELECT\n" +
                "\tcount(au.id) count\n" +
                "FROM\n" +
                "\tactivity_of_user au\n" +
                "INNER JOIN `user` u ON au.user_id = u.id\n" +
                "INNER JOIN activities a ON au.activity_id = a.id\n" +
                "WHERE\n" +
                "\tau.can_use = 1 and a.can_use = 1 and u.can_use = 1";

        sql = CommonUtil.appendBlurStr(sql,blurMap);
        sql = CommonUtil.appendDateStr(sql,dateMap,"au");
        sql = CommonUtil.appendIntStr(sql,intMap,"au");
        sqlCount = CommonUtil.appendBlurStr(sqlCount,blurMap);
        sqlCount = CommonUtil.appendDateStr(sqlCount,dateMap,"au");
        sqlCount = CommonUtil.appendIntStr(sqlCount,intMap,"au");
        Map resMap = CommonUtil.queryList(session,sql,sqlCount,limit,page);
        return resMap;

    }

    @Override
    public List selectListById(Long user_id) throws Exception{
        session = sessionFactory.getCurrentSession();
        String sql = "select activity_id from activity_of_user where can_use = 1 and user_id = ?";
        SQLQuery query = session.createSQLQuery(sql);
        query.setParameter(0,user_id);
        List list = query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
        return list;
    }

    @Override
    public void cancelSignIn(Long user_id, String activity_id) throws Exception{
         session = sessionFactory.getCurrentSession();
        String  sql = "delete from activity_of_user where user_id = ? and activity_id = ?";
        SQLQuery query = session.createSQLQuery(sql);
        query.setParameter(0,user_id);
        query.setParameter(1,activity_id);
        query.executeUpdate();
    }

    /**
     * 根据UserId查询用户参加活动的次数
     * @param UserId
     * @return
     */
    @Override
    public Integer selectCountByUserId(Long UserId) throws Exception {
        session = sessionFactory.getCurrentSession();
        String sql = "SELECT count(id) count\n" +
                "\tFROM activity_of_user\n" +
                "WHERE\n" +
                "\tcan_use = 1 and userid = ?";
        SQLQuery query = session.createSQLQuery(sql);
        query.setParameter(0,UserId);
        BigInteger temp = (BigInteger) query.uniqueResult();
        Integer count = temp.intValue();
        return count;
    }
}
