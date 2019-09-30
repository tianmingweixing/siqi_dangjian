package com.siqi_dangjian.dao.impl;

import com.siqi_dangjian.bean.Configuration;
import com.siqi_dangjian.dao.IConfigurationDao;
import com.siqi_dangjian.util.CommonUtil;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@Repository
public class ConfigurationDao extends BaseDao<Configuration> implements IConfigurationDao {

    @Override
    public void insertOrUpdate(Configuration configuration) throws Exception {
        saveOrUpdateObject(configuration);
    }

    @Override
    public void logicDelete(List idList) throws Exception {
        session = sessionFactory.getCurrentSession();
        String sql = "update configuration set isUse = 0";
        sql = CommonUtil.appendInSql(sql,idList,"id");
        SQLQuery query = session.createSQLQuery(sql);
        query.executeUpdate();
    }
    @Override
    public void delete(List idList) throws Exception{
        session = sessionFactory.getCurrentSession();
        String sql = "delete from configuration";
        sql = CommonUtil.appendInSql(sql,idList,"id");
        SQLQuery query = session.createSQLQuery(sql);
        query.executeUpdate();
    }

    @Override
    public Configuration selectById(Long id) throws Exception {
        return getObjectById(id);
    }

    @Override
    public Map selectAll(Map blurParam, Map dateParam, Map intParam, int limit, int page) throws Exception {
        session = sessionFactory.getCurrentSession();

        String sql = "\tSELECT * FROM \n" +
                "\tconfiguration c \n" +
                "\tWHERE\n" +
                "\tc.can_use = 1 ";

        String sqlCount = "SELECT\n" +
                " count(*) count\n" +
                " FROM configuration c where c.can_use = 1 ";
        sql = CommonUtil.appendBlurStr(sql,blurParam);
        sql = CommonUtil.appendDateStr(sql,dateParam,"c");
        sql = CommonUtil.appendIntStr(sql,intParam,"c");
        sqlCount = CommonUtil.appendBlurStr(sqlCount,blurParam);
        sqlCount = CommonUtil.appendDateStr(sqlCount,dateParam,"c");
        sqlCount = CommonUtil.appendIntStr(sqlCount,intParam,"c");
        Map resMap = CommonUtil.queryList(session,sql,sqlCount,limit,page);
        return resMap;

    }

    @Override
    public Long selectPartyBranchId() throws Exception {
        session = sessionFactory.getCurrentSession();

        String sql = "\tSELECT c.party_branch_id FROM \n" +
                "\tconfiguration c \n" +
                "\tWHERE\n" +
                "\tc.can_use = 1";

        SQLQuery query = session.createSQLQuery(sql);
        BigInteger branchId = (BigInteger)query.uniqueResult();
        return branchId.longValue();

    }
}
