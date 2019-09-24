package com.siqi_dangjian.dao.impl;

import com.siqi_dangjian.bean.PartyGroup;
import com.siqi_dangjian.dao.IPartyGroupDao;
import com.siqi_dangjian.util.CommonUtil;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public class PartyGroupDao extends BaseDao<PartyGroup> implements IPartyGroupDao {
    @Override
    public void insertOrUpdate(PartyGroup partyGroup) throws Exception {
        saveOrUpdateObject(partyGroup);
    }

    @Override
    public void logicDelete(List idList) throws Exception {
        session = sessionFactory.getCurrentSession();
        String sql = "update party_group set can_use = 0";
        sql = CommonUtil.appendInSql(sql,idList,"id");
        SQLQuery query = session.createSQLQuery(sql);
        query.executeUpdate();
    }

    @Override
    public void delete(List idList) throws Exception{
        session = sessionFactory.getCurrentSession();
        String sql = "delete from party_group";
        sql = CommonUtil.appendInSql(sql,idList,"id");
        SQLQuery query = session.createSQLQuery(sql);
        query.executeUpdate();
    }

    @Override
    public PartyGroup selectById(Long id) throws Exception {
        return getObjectById(id);
    }

    @Override
    public Map selectAll(Map blurParam,  Map intParam,Map dateParam, int limit, int page) throws Exception {
        session = sessionFactory.getCurrentSession();//GROUP_CONCAT(u.username) AS userName
        String sql =
                "SELECT\n" +
                "  p.duty,\n" +
                "  DATE_FORMAT(p.founding_time, '%Y-%m-%d')founding_time,\n" +
                "  DATE_FORMAT(p.change_time, '%Y-%m-%d')change_time,\n" +
                "  DATE_FORMAT(p.create_time, '%Y-%m-%d')create_time,\n" +
                "  p.id,\n" +
                "  p.`name`,\n" +
                "  count(u.id) count,\n" +
                "  GROUP_CONCAT(u.username) AS userName,\n" +
                "  p.party_branch_id,\n" +
                "  p.party_group_no,\n" +
                "  p.party_no\n" +
                "FROM\n" +
                "  party_group p\n" +
                "  join user u on p.id = u.party_groups_id\n" +
                "WHERE\n" +
                "  p.can_use = 1 and u.can_use = 1 " +
                "  group by p.id ";

        String sqlCount = "SELECT \n" +
                "count(*) count \n" +
                "FROM party_group p WHERE p.can_use = 1";
        sql = CommonUtil.appendBlurStr(sql,blurParam);
        sql = CommonUtil.appendDateStr(sql,dateParam,"p");
        sql = CommonUtil.appendIntStr(sql,intParam,"p");
        sqlCount = CommonUtil.appendBlurStr(sqlCount,blurParam);
        sqlCount = CommonUtil.appendDateStr(sqlCount,dateParam,"p");
        sqlCount = CommonUtil.appendIntStr(sqlCount,intParam,"p");
        Map resMap = CommonUtil.queryList(session,sql,sqlCount,limit,page);
        return resMap;

    }

    @Override
    public Map selectAllCategory() {
        session = sessionFactory.getCurrentSession();
        String sql = "SELECT\n" +
                "\tp.duty,\n" +
                "\tp.id,\n" +
                "\tp.`name`,\n" +
                "\tp.party_branch_id,\n" +
                "\tp.party_group_no,\n" +
                "\tp.party_no\n" +
                "FROM\n" +
                "\tparty_group p\n" +
                "WHERE\n" +
                "\tp.can_use = 1";
        Map resMap = CommonUtil.queryAllCategory(session,sql);
        return resMap;
    }
}
