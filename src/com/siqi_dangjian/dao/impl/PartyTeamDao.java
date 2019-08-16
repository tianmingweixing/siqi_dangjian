package com.siqi_dangjian.dao.impl;

import com.siqi_dangjian.bean.PartyTeam;
import com.siqi_dangjian.dao.IPartyTeamDao;
import com.siqi_dangjian.util.CommonUtil;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public class PartyTeamDao extends BaseDao<PartyTeam> implements IPartyTeamDao {
    @Override
    public void insertOrUpdate(PartyTeam partyTeam) throws Exception {
        saveOrUpdateObject(partyTeam);
    }

    @Override
    public void logicDelete(List idList) throws Exception {
        session = sessionFactory.getCurrentSession();
        String sql = "update party_team set isUse = 0";
        sql = CommonUtil.appendInSql(sql,idList,"id");
        SQLQuery query = session.createSQLQuery(sql);
        query.executeUpdate();
    }

    @Override
    public void delete(List idList) throws Exception{
        session = sessionFactory.getCurrentSession();
        String sql = "delete from party_team";
        sql = CommonUtil.appendInSql(sql,idList,"id");
        SQLQuery query = session.createSQLQuery(sql);
        query.executeUpdate();
    }

    @Override
    public PartyTeam selectById(Long id) throws Exception {
        return getObjectById(id);
    }

    @Override
    public Map selectAll(Map blurParam, Map dateParam, Map intParam, int limit, int page) throws Exception {
        session = sessionFactory.getCurrentSession();
//        "\tDATE_FORMAT(u.create_time, '%Y-%m-%d') create_time,\n" +
//                "\tIFNULL(G.brief,\"暂无信息\") brief,\n" +
        String sql = "\tSELECT * FROM party_team u WHERE u.can_use = 1\n";

        String sqlCount = "SELECT \n" +
                "count(*) count \n" +
                "FROM party_team u WHERE u.can_use = 1";
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
