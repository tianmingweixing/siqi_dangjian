package com.siqi_dangjian.dao.impl;

import com.siqi_dangjian.bean.Meeting;
import com.siqi_dangjian.dao.IMeetingDao;
import com.siqi_dangjian.util.CommonUtil;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;
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
//        "\tDATE_FORMAT(u.create_time, '%Y-%m-%d') create_time,\n" +
//                "\tIFNULL(G.brief,\"暂无信息\") brief,\n" +
        String sql = "\tSELECT * FROM \n" +
                "\tmeeting m\n" +
                "\tWHERE\n" +
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
}
