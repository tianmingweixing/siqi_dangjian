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
    public Map selectAll(Map blurParam, Map dateParam, Map intParam, int limit, int page) throws Exception {
        session = sessionFactory.getCurrentSession();
//        "\tDATE_FORMAT(u.create_time, '%Y-%m-%d') create_time,\n" +
//                "\tIFNULL(c.conclusion_type_id,\"暂无信息\") conclusion_type_id,\n" +
        String sql = "SELECT\n" +
                "\tIFNULL(\n" +
                "\t\tc.conclusion_type_id,\n" +
                "\t\t\"暂无信息\"\n" +
                "\t) conclusion_type_id,\n" +
                "\tc.id,\n" +
                "\tc.plan_content,\n" +
                "\tc.title,\n" +
                "\t(\n" +
                "\t\tCASE c.type\n" +
                "\t\tWHEN 1 THEN\n" +
                "\t\t\t'总结'\n" +
                "\t\tWHEN 2 THEN\n" +
                "\t\t\t'计划'\n" +
                "\t\tELSE\n" +
                "\t\t\t'空的'\n" +
                "\t\tEND\n" +
                "\t) type,\n" +
                "\tc.year_limit\n" +
                "FROM\n" +
                "\tconclusion c\n" +
                "WHERE\n" +
                "\tc.can_use = 1";

        String sqlCount = "SELECT\n" +
                "  count(*) count\n" +
                "FROM conclusion c where c.can_use = 1 ";
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
