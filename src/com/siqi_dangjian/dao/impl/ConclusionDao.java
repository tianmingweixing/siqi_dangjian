package com.siqi_dangjian.dao.impl;

import com.siqi_dangjian.bean.Conclusion;
import com.siqi_dangjian.dao.IConclusionDao;
import com.siqi_dangjian.util.CommonUtil;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public class ConclusionDao extends BaseDao<Conclusion> implements IConclusionDao {
    @Override
    public void insertOrUpdate(Conclusion conclusion) throws Exception {
        saveOrUpdateObject(conclusion);
    }

    @Override
    public void logicDelete(List idList) throws Exception {
        session = sessionFactory.getCurrentSession();
        String sql = "update conclusion set can_use = 0";
        sql = CommonUtil.appendInSql(sql,idList,"id");
        SQLQuery query = session.createSQLQuery(sql);
        query.executeUpdate();
    }

    @Override
    public void delete(List idList) throws Exception{
        session = sessionFactory.getCurrentSession();
        String sql = "delete from conclusion";
        sql = CommonUtil.appendInSql(sql,idList,"id");
        SQLQuery query = session.createSQLQuery(sql);
        query.executeUpdate();
    }

    @Override
    public Conclusion selectById(Long id) throws Exception {
        return getObjectById(id);
    }

    @Override
    public Map selectAll(Map blurParam,Map intParam, Map dateParam,  int limit, int page) throws Exception {
        session = sessionFactory.getCurrentSession();
        String sql = "SELECT\n" +
                "\tc.conclusion_type_id,\n" +
                "\tc.id,\n" +
                "\tt.type_name,\n" +
                "\tc.plan_content,\n" +
                "\tc.title,\n" +
                "\t(\n" +
                "\t\tCASE t.type\n" +
                "\t\tWHEN 0 THEN\n" +
                "\t\t\t'总结'\n" +
                "\t\tWHEN 1 THEN\n" +
                "\t\t\t'计划'\n" +
                "\t\tELSE\n" +
                "\t\t\t'空的'\n" +
                "\t\tEND\n" +
                "\t) type,\n" +
                "\tDATE_FORMAT(c.year_limit, '%Y-%m-%d') year_limit\n" +
                "FROM\n" +
                "\tconclusion c\n" +
                "JOIN conclusion_type t ON c.conclusion_type_id = t.id\n" +
                "WHERE\n" +
                "\tc.can_use = 1\n" +
                "AND t.can_use = 1";

        String sqlCount = "SELECT\n" +
                "  count(*) count\n" +
                "FROM conclusion c " +
                "JOIN conclusion_type t ON c.conclusion_type_id = t.id\n" +
                "WHERE\n" +
                "\tc.can_use = 1\n" +
                "AND t.can_use = 1 ";
        sql = CommonUtil.appendBlurStr(sql,blurParam);
        sql = CommonUtil.appendCustomDateStr(sql,dateParam,"c","year_limit");
        sql = CommonUtil.appendIntStr(sql,intParam,"t");

        sqlCount = CommonUtil.appendBlurStr(sqlCount,blurParam);
        sqlCount = CommonUtil.appendDateStr(sqlCount,dateParam,"c");
        sqlCount = CommonUtil.appendIntStr(sqlCount,intParam,"t");

        sql = sql +" ORDER BY c.year_limit DESC";

        Map resMap = CommonUtil.queryList(session,sql,sqlCount,limit,page);
        return resMap;

    }
}
