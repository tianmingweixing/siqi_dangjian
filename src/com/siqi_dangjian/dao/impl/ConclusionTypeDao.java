package com.siqi_dangjian.dao.impl;

import com.siqi_dangjian.bean.ConclusionType;
import com.siqi_dangjian.dao.IConclusionTypeDao;
import com.siqi_dangjian.util.CommonUtil;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ConclusionTypeDao extends BaseDao<ConclusionType> implements IConclusionTypeDao {

    @Override
    public void insertOrUpdate(ConclusionType conclusionType) throws Exception {
        saveOrUpdateObject(conclusionType);

    }

    @Override
    public void logicDelete(List idList) throws Exception {
        session = sessionFactory.getCurrentSession();
        String sql = "update conclusion_type set can_use = 0";
        sql = CommonUtil.appendInSql(sql,idList,"id");
        SQLQuery query = session.createSQLQuery(sql);
        query.executeUpdate();
    }

    @Override
    public void delete(List idList) throws Exception{
        session = sessionFactory.getCurrentSession();
        String sql = "delete from conclusion_type";
        sql = CommonUtil.appendInSql(sql,idList,"id");
        SQLQuery query = session.createSQLQuery(sql);
        query.executeUpdate();
    }

    @Override
    public ConclusionType selectById(Long id) throws Exception {
        return getObjectById(id);
    }

    @Override
    public Map selectAll(Map blurParam,Map intParam, Map dateParam,  int limit, int page) throws Exception {
        session = sessionFactory.getCurrentSession();
        String sql = "SELECT\n" +
                "\tc.id,\n" +
                "\tc.type_name,\n" +
                "\tDATE_FORMAT(c.update_time, '%Y-%m-%d') update_time,\n" +
                "\tDATE_FORMAT(c.create_time, '%Y-%m-%d') create_time,\n" +
                "\t(\n" +
                "\t\tCASE c.type\n" +
                "\t\tWHEN 0 THEN\n" +
                "\t\t\t'总结'\n" +
                "\t\tWHEN 1 THEN\n" +
                "\t\t\t'计划'\n" +
                "\t\tELSE\n" +
                "\t\t\t'空的'\n" +
                "\t\tEND\n" +
                "\t) type\n" +
                "FROM\n" +
                "\tconclusion_type c\n" +
                "WHERE\n" +
                "\tc.can_use = 1";

        String sqlCount = "SELECT\n" +
                "  count(*) count\n" +
                "FROM conclusion_type c where c.can_use = 1 ";
        sql = CommonUtil.appendBlurStr(sql,blurParam);
        sql = CommonUtil.appendDateStr(sql,dateParam,"c");
        sql = CommonUtil.appendIntStr(sql,intParam,"c");
        sqlCount = CommonUtil.appendBlurStr(sqlCount,blurParam);
        sqlCount = CommonUtil.appendDateStr(sqlCount,dateParam,"c");
        sqlCount = CommonUtil.appendIntStr(sqlCount,intParam,"c");
        Map resMap = CommonUtil.queryList(session,sql,sqlCount,limit,page);
        return resMap;

    }
}
