package com.siqi_dangjian.dao.impl;

import com.siqi_dangjian.bean.Duty;
import com.siqi_dangjian.dao.IDutyDao;
import com.siqi_dangjian.util.CommonUtil;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@Repository
public class DutyDao extends BaseDao<Duty> implements IDutyDao {


    @Override
    public BigInteger insertOrUpdate(Duty duty) throws Exception {
        BigInteger lastId= saveOrUpdateObjectReturnId(duty);//插入数据时返回自增id
        return lastId;
    }

    @Override
    public void logicDelete(List idList) throws Exception {
        session = sessionFactory.getCurrentSession();
        String sql = "update duty set can_use = 0";
        sql = CommonUtil.appendInSql(sql,idList,"id");
        SQLQuery query = session.createSQLQuery(sql);
        query.executeUpdate();
    }

    @Override
    public void delete(List idList) throws Exception{
        session = sessionFactory.getCurrentSession();
        String sql = "delete from duty";
        sql = CommonUtil.appendInSql(sql,idList,"id");
        SQLQuery query = session.createSQLQuery(sql);
        query.executeUpdate();
    }

    @Override
    public Duty selectById(Long id) throws Exception {
        return getObjectById(id);
    }

    @Override
    public Map selectAll(Map blurParam,  Map intParam,Map dateParam, int limit, int page) throws Exception {
        session = sessionFactory.getCurrentSession();
        String sql = "SELECT\n" +
                "\td.id,\n" +
                "\td.create_time,\n" +
                "\td.description,\n" +
                "\td.party_branch_id,\n" +
                "\td.type_name\n" +
                "FROM\n" +
                "\tduty d\n" +
                "WHERE\n" +
                "\td.can_use = 1";

        String sqlCount = "SELECT\n" +
                "  count(*) count\n" +
                "FROM duty d where d.can_use=1";
        sql = CommonUtil.appendBlurStr(sql,blurParam);
        sql = CommonUtil.appendDateStr(sql,dateParam,"d");
        sql = CommonUtil.appendIntStr(sql,intParam,"d");
        sqlCount = CommonUtil.appendBlurStr(sqlCount,blurParam);
        sqlCount = CommonUtil.appendDateStr(sqlCount,dateParam,"d");
        sqlCount = CommonUtil.appendIntStr(sqlCount,intParam,"d");
        Map resMap = CommonUtil.queryList(session,sql,sqlCount,limit,page);
        return resMap;

    }

    @Override
    public List<Duty> selectList() throws Exception {
        session = sessionFactory.getCurrentSession();
        String sql = "select id,type_name typeName FROM duty where can_use=1";

//        Query query = session.createSQLQuery(sql).addEntity(Duty.class);

        Query query = session.createSQLQuery(sql)
                .addScalar("id", LongType.INSTANCE)
                .addScalar("typeName", StringType.INSTANCE);

        query.setResultTransformer(Transformers.aliasToBean(Duty.class));

        List<Duty> list = query.list();
        return list;

    }

    @Override
    public Map selectAllCategory(Map blurMap, Map intMap, Map dateMap) {
        session = sessionFactory.getCurrentSession();
        String sql = "SELECT\n" +
                "\td.id,\n" +
                "\td.create_time,\n" +
                "\td.description,\n" +
                "\td.party_branch_id,\n" +
                "\td.type_name\n" +
                "FROM\n" +
                "\tduty d\n" +
                "WHERE\n" +
                "\td.can_use = 1";

        String sqlCount = "SELECT\n" +
                "  count(*) count\n" +
                "FROM duty d where d.can_use=1";
        sql = CommonUtil.appendBlurStr(sql,blurMap);
        sql = CommonUtil.appendDateStr(sql,dateMap,"d");
        sql = CommonUtil.appendIntStr(sql,intMap,"d");
        sqlCount = CommonUtil.appendBlurStr(sqlCount,blurMap);
        sqlCount = CommonUtil.appendDateStr(sqlCount,dateMap,"d");
        sqlCount = CommonUtil.appendIntStr(sqlCount,intMap,"d");
        Map resMap = CommonUtil.queryAllCategoryList(session,sql,sqlCount);
        return resMap;
    }
}
