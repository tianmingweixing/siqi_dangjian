package com.siqi_dangjian.dao.impl;


import com.siqi_dangjian.bean.DisciplineOfHonor;
import com.siqi_dangjian.dao.IDisciplineOfHonorDao;
import com.siqi_dangjian.util.CommonUtil;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class DisciplineOfHonorDao extends BaseDao<DisciplineOfHonor>implements IDisciplineOfHonorDao {

    @Override
    public void insertOrUpdate(DisciplineOfHonor disciplineOfHonor) throws Exception {
        saveOrUpdateObject(disciplineOfHonor);
    }

    @Override
    public void logicDelete(List idList) throws Exception {
        session = sessionFactory.getCurrentSession();
        String sql = "update discipline_of_honor set can_use = 0";
        sql = CommonUtil.appendInSql(sql,idList,"id");
        SQLQuery query = session.createSQLQuery(sql);
        query.executeUpdate();
    }

    @Override
    public void delete(List idList) throws Exception{
        session = sessionFactory.getCurrentSession();
        String sql = "delete from discipline_of_honor";
        sql = CommonUtil.appendInSql(sql,idList,"id");
        SQLQuery query = session.createSQLQuery(sql);
        query.executeUpdate();
    }

    @Override
    public DisciplineOfHonor selectById(Long id) throws Exception {
        return getObjectById(id);
    }

    @Override
    public Map selectAll(Map blurParam,  Map intParam,Map dateParam, int limit, int page) throws Exception {
        session = sessionFactory.getCurrentSession();
//        "\tDATE_FORMAT(u.create_time, '%Y-%m-%d') create_time,\n" +
//                "\tIFNULL(G.brief,\"暂无信息\") brief,\n" +
        String sql = "SELECT\n" +
                "\td.amount,\n" +
                "\td.certificate,\n" +
                "\td.content,\n" +
                "\td.create_time,\n" +
                "\td.id,\n" +
                "\td.`name`,\n" +
                "\td.note,\n" +
                "\td.passive_unit,\n" +
                "\tDATE_FORMAT(d.time, '%Y-%m-%d')time,\n" +
                "\t(\n" +
                "\t\tCASE d.type\n" +
                "\t\tWHEN 0 THEN\n" +
                "\t\t\t'荣誉'\n" +
                "\t\tWHEN 1 THEN\n" +
                "\t\t\t'违纪'\n" +
                "\t\tELSE\n" +
                "\t\t\t'暂无数据'\n" +
                "\t\tEND\n" +
                "\t) type,\n" +
                "\td.unit\n" +
                "FROM\n" +
                "\tdiscipline_of_honor d\n" +
                "WHERE\n" +
                "\td.can_use = 1";

        String sqlCount = "SELECT\n" +
                "  count(*) count\n" +
                "FROM discipline_of_honor d where d.can_use = 1";
        sql = CommonUtil.appendBlurStr(sql,blurParam);
        sql = CommonUtil.appendDateStr(sql,dateParam,"d");
        sql = CommonUtil.appendIntStr(sql,intParam,"d");
        sqlCount = CommonUtil.appendBlurStr(sqlCount,blurParam);
        sqlCount = CommonUtil.appendDateStr(sqlCount,dateParam,"d");
        sqlCount = CommonUtil.appendIntStr(sqlCount,intParam,"d");
        Map resMap = CommonUtil.queryList(session,sql,sqlCount,limit,page);
        return resMap;

    }
}
