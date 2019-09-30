package com.siqi_dangjian.dao.impl;

import com.siqi_dangjian.bean.Meeting;
import com.siqi_dangjian.bean.MeetingOfUser;
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
    public void insertOrUpdate(MeetingOfUser meetingOfUser) {
        session = sessionFactory.getCurrentSession();
        String sql = "INSERT INTO meeting_of_user (can_use, user_id, meeting_id)\n" +
                "VALUES\n" +
                "\t(?, ?, ?) ";
        SQLQuery query = session.createSQLQuery(sql);
        query.setParameter(0,1);
        query.setParameter(1,meetingOfUser.getUserId());
        query.setParameter(2,meetingOfUser.getMeetingId());
        query.executeUpdate();
    }

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
                "  m.content,\n" +
                "  DATE_FORMAT(m.end_time, '%Y-%m-%d %T') end_time,\n" +
                "  DATE_FORMAT(m.start_time, '%Y-%m-%d %T') start_time,\n" +
                "  m.guide,\n" +
                "  m.id,\n" +
                "  m.compere,\n" +
                "  m.recorder,\n" +
                "  m.people_counting,\n" +
                "  m.attendance,\n" +
                "  m.address,\n" +
                "  m.images_a,\n" +
                "  m.meeting_type_id,\n" +
                "  t.type_name,\n" +
                "  m.name\n" +
                "FROM\n" +
                "  meeting m\n" +
                "join meeting_type t\n" +
                "on m.meeting_type_id = t.id\n" +
                "WHERE\n" +
                "  m.can_use = 1 and t.can_use = 1";

        String sqlCount = "SELECT\n" +
                "  count(*) count\n" +
                "FROM meeting m where m.can_use = 1";
        sql = CommonUtil.appendBlurStr(sql,blurParam);
        sql = CommonUtil.appendDateStr(sql,dateParam,"m");
        sql = CommonUtil.appendIntStr(sql,intParam,"m");
        sqlCount = CommonUtil.appendBlurStr(sqlCount,blurParam);
        sqlCount = CommonUtil.appendDateStr(sqlCount,dateParam,"m");
        sqlCount = CommonUtil.appendIntStr(sqlCount,intParam,"m");

        sql = sql +" ORDER BY m.year_limit DESC";
        sqlCount = sqlCount + "ORDER BY m.year_limit DESC";

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

    @Override
    public String selectSignInById(Long id) throws Exception {
        session = sessionFactory.getCurrentSession();
        String sql = "SELECT\n" +
                "\tGROUP_CONCAT(u.username) AS userName\n" +
                "FROM\n" +
                "\tmeeting m\n" +
                "JOIN meeting_of_user f ON m.id = f.meeting_id\n" +
                "JOIN USER u ON u.id = f.user_id\n" +
                "WHERE\n" +
                "\tu.can_use = 1\n" +
                "AND m.can_use = 1\n" +
                "AND m.id = ?";
        SQLQuery query = session.createSQLQuery(sql);
        query.setParameter(0,id);
        String userName = (String) query.uniqueResult();

        return userName;
    }


}
