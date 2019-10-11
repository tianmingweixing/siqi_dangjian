package com.siqi_dangjian.dao.impl;

import com.siqi_dangjian.bean.PartyBranch;
import com.siqi_dangjian.dao.IPartyBranchDao;
import com.siqi_dangjian.util.CommonUtil;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;
@Repository
public class PartyBranchDao extends BaseDao<PartyBranch> implements IPartyBranchDao {
    @Override
    public void insertOrUpdate(PartyBranch partyBranch) throws Exception {
        saveOrUpdateObject(partyBranch);
    }

    @Override
    public void logicDelete(List idList) throws Exception {
        session = sessionFactory.getCurrentSession();
        String sql = "update party_branch set isUse = 0";
        sql = CommonUtil.appendInSql(sql,idList,"id");
        SQLQuery query = session.createSQLQuery(sql);
        query.executeUpdate();
    }

    @Override
    public void delete(List idList) throws Exception{
        session = sessionFactory.getCurrentSession();
        String sql = "delete from party_branch";
        sql = CommonUtil.appendInSql(sql,idList,"id");
        SQLQuery query = session.createSQLQuery(sql);
        query.executeUpdate();
    }

    @Override
    public PartyBranch selectById(Long id) throws Exception {
        return getObjectById(id);
    }

    @Override
    public Map selectAll(Map blurParam,  Map intParam, Map dateParam, int limit, int page) throws Exception {
        session = sessionFactory.getCurrentSession();
        String sql = "SELECT\n" +
                "\tp.activity_area,\n" +
                "\tDATE_FORMAT(p.change_time, '%Y-%m-%d') change_time,\n" +
                "\tDATE_FORMAT(p.create_time, '%Y-%m-%d') create_time,\n" +
                "\tp.id,\n" +
                "\tp.`name`,\n" +
                "\tp.duty,\n" +
                "\tDATE_FORMAT(p.founding_time, '%Y-%m-%d') founding_time,\n" +
                "\tp.party_img,\n" +
                "\tp.structure_img,\n" +
                "\tp.party_info,\n" +
                "\tp.party_no,\n" +
                "\tu.count UserCount\n" +
                "FROM\n" +
                "\tparty_branch p\n" +
                "JOIN \n" +
                "(SELECT count(*) count,party_branch_id FROM user WHERE can_use = 1 )AS u\n" +
                "ON p.id = u.party_branch_id\n" +
                "WHERE\n" +
                "\tp.can_use = 1 ";

        String sqlCount = "SELECT \n" +
                "count(*) count \n" +
                "FROM party_branch p WHERE p.can_use = 1";
        sql = CommonUtil.appendBlurStr(sql,blurParam);
        sql = CommonUtil.appendDateStr(sql,dateParam,"p");
        sql = CommonUtil.appendIntStr(sql,intParam,"p");
        sqlCount = CommonUtil.appendBlurStr(sqlCount,blurParam);
        sqlCount = CommonUtil.appendDateStr(sqlCount,dateParam,"p");
        sqlCount = CommonUtil.appendIntStr(sqlCount,intParam,"p");
        sql = sql +" ORDER BY p.create_time DESC";
        Map resMap = CommonUtil.queryList(session,sql,sqlCount,limit,page);
        return resMap;

    }

    @Override
    public Integer updatePartyMemberCount(int type, int count) throws Exception {
        session = sessionFactory.getCurrentSession();
        SQLQuery query;
        String sql = "update party_branch set party_member_count = party_member_count + 1 where id = 1";
        query = session.createSQLQuery(sql);
        if(type == 2){
            sql = "update party_branch set party_member_count = party_member_count - ? where id = 1";
            query = session.createSQLQuery(sql);
            query.setParameter(0, count);
        }
        Integer row = query.executeUpdate();
        return row;
    }
}
