package com.siqi_dangjian.dao.impl;


import com.siqi_dangjian.bean.MeetingOfUser;
import com.siqi_dangjian.dao.IMeetingOfUserDao;
import com.siqi_dangjian.util.CommonUtil;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class MeetingOfUserDao extends BaseDao<MeetingOfUser> implements IMeetingOfUserDao {
    @Override
    public void insertOrUpdate(MeetingOfUser meetingOfUser) throws Exception {
        saveOrUpdateObject(meetingOfUser);
    }

    @Override
    public void logicDelete(List idList) throws Exception {
        session = sessionFactory.getCurrentSession();
        String sql = "update meeting_of_user set isUse = 0";
        sql = CommonUtil.appendInSql(sql,idList,"id");
        SQLQuery query = session.createSQLQuery(sql);
        query.executeUpdate();
    }

    @Override
    public void delete(List idList) throws Exception{
        session = sessionFactory.getCurrentSession();
        String sql = "delete from meeting_of_user";
        sql = CommonUtil.appendInSql(sql,idList,"id");
        SQLQuery query = session.createSQLQuery(sql);
        query.executeUpdate();
    }

    @Override
    public MeetingOfUser selectById(Long id) throws Exception {
        return getObjectById(id);
    }

    @Override
    public Map selectAll(Map blurParam, Map dateParam, Map intParam, int limit, int page) throws Exception {
        session = sessionFactory.getCurrentSession();
//        "\tDATE_FORMAT(u.create_time, '%Y-%m-%d') create_time,\n" +
//                "\tIFNULL(G.brief,\"暂无信息\") brief,\n" +
        String sql = "\tSELECT * FROM meeting_of_user u WHERE u.can_use = 1\n";

        String sqlCount = "SELECT \n" +
                "count(*) count \n" +
                "FROM meeting_of_user u WHERE u.can_use = 1";
        sql = CommonUtil.appendBlurStr(sql,blurParam);
        sql = CommonUtil.appendDateStr(sql,dateParam,"u");
        sql = CommonUtil.appendIntStr(sql,intParam,"u");
        sqlCount = CommonUtil.appendBlurStr(sqlCount,blurParam);
        sqlCount = CommonUtil.appendDateStr(sqlCount,dateParam,"u");
        sqlCount = CommonUtil.appendIntStr(sqlCount,intParam,"u");
        Map resMap = CommonUtil.queryList(session,sql,sqlCount,limit,page);
        return resMap;

    }
}
