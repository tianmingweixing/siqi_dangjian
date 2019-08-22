package com.siqi_dangjian.dao.impl;


import com.siqi_dangjian.bean.Sympathy;
import com.siqi_dangjian.dao.ISympathyDao;
import com.siqi_dangjian.util.CommonUtil;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public class SympathyDao extends BaseDao<Sympathy> implements ISympathyDao {

    @Override
    public void insertOrUpdate(Sympathy sympathy) throws Exception {
        saveOrUpdateObject(sympathy);
    }

    @Override
    public void logicDelete(List idList) throws Exception {
        session = sessionFactory.getCurrentSession();
        String sql = "update sympathy set isUse = 0";
        sql = CommonUtil.appendInSql(sql,idList,"id");
        SQLQuery query = session.createSQLQuery(sql);
        query.executeUpdate();
    }

    @Override
    public void delete(List idList) throws Exception{
        session = sessionFactory.getCurrentSession();
        String sql = "delete from sympathy";
        sql = CommonUtil.appendInSql(sql,idList,"id");
        SQLQuery query = session.createSQLQuery(sql);
        query.executeUpdate();
    }

    @Override
    public Sympathy selectById(Long id) throws Exception {
        return getObjectById(id);
    }

    @Override
    public Map selectAll(Map blurParam, Map dateParam, Map intParam, int limit, int page) throws Exception {
        session = sessionFactory.getCurrentSession();

        String sql = "SELECT s.id sympathyId,u.id userId,u.username,s.difficult,s.note,s.sympathy_product,s.sympathy_time,s.unit_and_position,u.age,u.sex,u.phone,u.company,u.join_time,u.ID_cord FROM sympathy s join user u on s.party_branch_id = u.party_branch_id WHERE s.can_use = 1 and u.can_use";
        String sqlCount = "SELECT count(*)  " +
                "FROM sympathy s " +
                "JOIN user u ON s.party_branch_id = u.party_branch_id " +
                "WHERE s.can_use = 1 and u.can_use=1";
        sql = CommonUtil.appendBlurStr(sql,blurParam);
        sql = CommonUtil.appendDateStr(sql,dateParam,"s");
        sql = CommonUtil.appendIntStr(sql,intParam,"s");
        sqlCount = CommonUtil.appendBlurStr(sqlCount,blurParam);
        sqlCount = CommonUtil.appendDateStr(sqlCount,dateParam,"s");
        sqlCount = CommonUtil.appendIntStr(sqlCount,intParam,"s");
        Map resMap = CommonUtil.queryList(session,sql,sqlCount,limit,page);
        return resMap;
    }
}
