package com.siqi_dangjian.dao.impl;

import com.siqi_dangjian.bean.PartyTeam;
import com.siqi_dangjian.dao.IPartyTeamDao;
import com.siqi_dangjian.util.CommonUtil;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
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
        String sql = "update party_team set can_use = 0";
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
    public Map selectAll(Map blurParam, Map intParam ,Map dateParam, int limit, int page) throws Exception {
        session = sessionFactory.getCurrentSession();
        String sql = "SELECT\n" +
                "\tp.duty,\n" +
                "\tDATE_FORMAT(p.founding_time, '%Y-%m-%d')founding_time,\n" +
                "\tDATE_FORMAT(p.change_time, '%Y-%m-%d')change_time,\n" +
                "\tp.id,\n" +
                "\tp.`name`,\n" +
                "\tp.party_branch_id,\n" +
                "\tp.party_group_no,\n" +
                "\tp.party_no,\n" +
                "\tDATE_FORMAT(p.create_time, '%Y-%m-%d')create_time\n" +
                "FROM\n" +
                "\tparty_team p\n" +
                "WHERE\n" +
                "\tp.can_use = 1";

        String sqlCount = "SELECT \n" +
                "count(*) count \n" +
                "FROM party_team p WHERE p.can_use = 1";
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
    public List<PartyTeam> selectList() throws Exception {
        session = sessionFactory.getCurrentSession();
        String sql = "select id,name FROM party_team where can_use=1";

//        Query query = session.createSQLQuery(sql).addEntity(Duty.class);//必须查出Duty全部

        Query query = session.createSQLQuery(sql)
                .addScalar("id", LongType.INSTANCE)
                .addScalar("name", StringType.INSTANCE);

        query.setResultTransformer(Transformers.aliasToBean(PartyTeam.class));

        return query.list();

    }
}
