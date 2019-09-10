package com.siqi_dangjian.dao.impl;

import com.siqi_dangjian.bean.Meeting;
import com.siqi_dangjian.dao.IMeetingDao;
import com.siqi_dangjian.util.CommonUtil;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@Repository
public class MeetingDao extends BaseDao<Meeting> implements IMeetingDao {
    @Override
    public void insertOrUpdate(Meeting meeting) throws Exception {
        saveOrUpdateObject(meeting);
    }

    @Override
    public void logicDelete(List idList) throws Exception {
        session = sessionFactory.getCurrentSession();
        String sql = "update meeting set can_use = 0";
        sql = CommonUtil.appendInSql(sql,idList,"id");
        SQLQuery query = session.createSQLQuery(sql);
        query.executeUpdate();
    }

    @Override
    public void delete(List idList) throws Exception{
        session = sessionFactory.getCurrentSession();
        String sql = "delete from meeting";
        sql = CommonUtil.appendInSql(sql,idList,"id");
        SQLQuery query = session.createSQLQuery(sql);
        query.executeUpdate();
    }

    @Override
    public Meeting selectById(Long id) throws Exception {
        return getObjectById(id);
    }

    @Override
    public Map selectAll(Map blurParam, Map intParam, Map dateParam,  int limit, int page) throws Exception {
        session = sessionFactory.getCurrentSession();

        String sql = "SELECT\n" +
                "\tm.content,\n" +
                "\tDATE_FORMAT(m.end_time, '%Y-%m-%d') end_time,\n" +
                "\tDATE_FORMAT(m.start_time, '%Y-%m-%d') start_time,\n" +
                "\tm.guide,\n" +
                "\tm.id,\n" +
                "\tm.compere,\n" +
                "\tm.recorder,\n" +
                "\tm.people_counting,\n" +
                "\tm.attendance,\n" +
                "\tm.address,\n" +
                "\tm.images_a,\n" +
                "\t(\n" +
                "\t\tCASE m.meeting_type_id\n" +
                "\t\tWHEN 1 THEN\n" +
                "\t\t\t'支委会'\n" +
                "\t\tWHEN 2 THEN\n" +
                "\t\t\t'党员大会'\n" +
                "\t\tWHEN 3 THEN\n" +
                "\t\t\t'党小组会'\n" +
                "\t\tWHEN 4 THEN\n" +
                "\t\t\t'党课'\n" +
                "\t\tWHEN 5 THEN\n" +
                "\t\t\t'廉政教育'\n" +
                "\t\tWHEN 6 THEN\n" +
                "\t\t\t'组织生活会'\n" +
                "\t\tWHEN 7 THEN\n" +
                "\t\t\t'政治理论学习'\n" +
                "\t\tWHEN 8 THEN\n" +
                "\t\t\t'互授党课'\n" +
                "\t\tWHEN 9 THEN\n" +
                "\t\t\t'民主评议党员'\n" +
                "\t\tWHEN 10 THEN\n" +
                "\t\t\t'专题讨论'\n" +
                "\t\tELSE\n" +
                "\t\t\t'暂无信息'\n" +
                "\t\tEND\n" +
                "\t) meeting_type_id,\n" +
                "\tm.name\n" +
                "FROM\n" +
                "\tmeeting m\n" +
                "WHERE\n" +
                "\tm.can_use = 1";

        String sqlCount = "SELECT\n" +
                "  count(*) count\n" +
                "FROM meeting m where m.can_use = 1";
        sql = CommonUtil.appendBlurStr(sql,blurParam);
        sql = CommonUtil.appendDateStr(sql,dateParam,"m");
        sql = CommonUtil.appendIntStr(sql,intParam,"m");
        sqlCount = CommonUtil.appendBlurStr(sqlCount,blurParam);
        sqlCount = CommonUtil.appendDateStr(sqlCount,dateParam,"m");
        sqlCount = CommonUtil.appendIntStr(sqlCount,intParam,"m");
        Map resMap = CommonUtil.queryList(session,sql,sqlCount,limit,page);
        return resMap;

    }

    @Override
    public Integer selectMeetingCountByType(String coum, Long parem) throws Exception {
        session = sessionFactory.getCurrentSession();
        String sql = "SELECT\n" +
                "\tCOUNT(u.id) count\n" +
                "FROM\n" +
                "\tmeeting u\n" +
                "WHERE\n" +
                "\tu.can_use = 1\n" +
                "AND u."+coum+" = ?";
        SQLQuery query = session.createSQLQuery(sql);
        query.setParameter(0,parem);
        BigInteger temp = (BigInteger) query.uniqueResult();
        Integer count = temp.intValue();
        return count;
    }
}
