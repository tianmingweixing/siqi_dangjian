package com.siqi_dangjian.dao.impl;

import com.siqi_dangjian.bean.Tips;
import com.siqi_dangjian.dao.ITipsDao;
import com.siqi_dangjian.util.CommonUtil;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class TipsDao extends BaseDao<Tips> implements ITipsDao {

    @Override
    public void insertOrUpdate(Tips tips) throws Exception {
        saveOrUpdateObject(tips);
    }

    @Override
    public void logicDelete(List idList) throws Exception {
        session = sessionFactory.getCurrentSession();
        String sql = "update tips set isUse = 0";
        sql = CommonUtil.appendInSql(sql,idList,"id");
        SQLQuery query = session.createSQLQuery(sql);
        query.executeUpdate();
    }

    @Override
    public void delete(List idList) throws Exception{
        session = sessionFactory.getCurrentSession();
        String sql = "delete from tips";
        sql = CommonUtil.appendInSql(sql,idList,"id");
        SQLQuery query = session.createSQLQuery(sql);
        query.executeUpdate();
    }

    @Override
    public Tips selectById(Long id) throws Exception {
        return getObjectById(id);
    }

    @Override
    public Map selectAll(Map blurParam, Map intParam, Map dateParam, int limit, int page) throws Exception {
        session = sessionFactory.getCurrentSession();

        String sql = "SELECT t.activity_id,t.content,t.id,t.user_id,t.user_name,a.title,DATE_FORMAT(t.create_time,'%y-%m-%d') create_time FROM tips t INNER JOIN activities a ON t.activity_id=a.id WHERE t.can_use = 1 and a.can_use=1";
        String sqlCount = "SELECT count(*) FROM tips t INNER JOIN activities a ON t.activity_id=a.id WHERE t.can_use = 1 and a.can_use=1";
        sql = CommonUtil.appendBlurStr(sql,blurParam);
        sql = CommonUtil.appendDateStr(sql,dateParam,"t");
        sql = CommonUtil.appendIntStr(sql,intParam,"t");
        sqlCount = CommonUtil.appendBlurStr(sqlCount,blurParam);
        sqlCount = CommonUtil.appendDateStr(sqlCount,dateParam,"t");
        sqlCount = CommonUtil.appendIntStr(sqlCount,intParam,"t");
        Map resMap = CommonUtil.queryList(session,sql,sqlCount,limit,page);
        return resMap;
    }
}
