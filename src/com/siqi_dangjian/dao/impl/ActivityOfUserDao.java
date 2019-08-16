package com.siqi_dangjian.dao.impl;

import com.siqi_dangjian.bean.ActivityOfUser;
import com.siqi_dangjian.dao.IActivityOfUserDao;
import com.siqi_dangjian.util.CommonUtil;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ActivityOfUserDao extends BaseDao<ActivityOfUser> implements IActivityOfUserDao {

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
    public Map selectAll(Map blurParam, Map dateParam, Map intParam, int limit, int page) throws Exception {
        session = sessionFactory.getCurrentSession();
//        "\tDATE_FORMAT(u.create_time, '%Y-%m-%d') create_time,\n" +
//                "\tIFNULL(G.brief,\"暂无信息\") brief,\n" +
        String sql = "\tSELECT * FROM \n" +
                "\tactivity_of_user a\n" +
                "\tWHERE\n" +
                "\ta.can_use=1";

        String sqlCount = "SELECT\n" +
                "  count(*) count\n" +
                "FROM activity_of_user a where a.can_use = 1";
        sql = CommonUtil.appendBlurStr(sql,blurParam);
        sql = CommonUtil.appendDateStr(sql,dateParam,"a");
        sql = CommonUtil.appendIntStr(sql,intParam,"a");
        sqlCount = CommonUtil.appendBlurStr(sqlCount,blurParam);
        sqlCount = CommonUtil.appendDateStr(sqlCount,dateParam,"a");
        sqlCount = CommonUtil.appendIntStr(sqlCount,intParam,"a");
        Map resMap = CommonUtil.queryList(session,sql,sqlCount,limit,page);
        return resMap;

    }
}
