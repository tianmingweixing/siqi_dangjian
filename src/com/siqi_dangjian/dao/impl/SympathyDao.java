package com.siqi_dangjian.dao.impl;


import com.siqi_dangjian.bean.Sympathy;
import com.siqi_dangjian.dao.ISympathyDao;
import com.siqi_dangjian.util.CommonUtil;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public class SympathyDao extends BaseDao<Sympathy> implements ISympathyDao {

    @Override
    public void insertOrUpdate(Sympathy sympathy) throws Exception {
        saveOrUpdateObject(sympathy);
    }

    @Override
    public void logicDelete(List idList) throws Exception {
        session = sessionFactory.getCurrentSession();
        String sql = "update sympathy set isUse = 0";
        sql = CommonUtil.appendInSql(sql,idList,"id");
        SQLQuery query = session.createSQLQuery(sql);
        query.executeUpdate();
    }

    @Override
    public void delete(List idList) throws Exception{
        session = sessionFactory.getCurrentSession();
        String sql = "delete from sympathy";
        sql = CommonUtil.appendInSql(sql,idList,"id");
        SQLQuery query = session.createSQLQuery(sql);
        query.executeUpdate();
    }

    @Override
    public Sympathy selectById(Long id) throws Exception {
        return getObjectById(id);
    }

    @Override
    public Map selectAll(Map blurParam, Map dateParam, Map intParam, int limit, int page) throws Exception {
        session = sessionFactory.getCurrentSession();

        String sql = "\tSELECT * FROM sympathy t WHERE t.can_use = 1";
        String sqlCount = "SELECT count(*) FROM sympathy t WHERE t.can_use = 1";
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
