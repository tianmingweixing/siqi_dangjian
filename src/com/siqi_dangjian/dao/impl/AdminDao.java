package com.siqi_dangjian.dao.impl;

import com.siqi_dangjian.bean.Admin;
import com.siqi_dangjian.dao.IAdminDao;
import com.siqi_dangjian.util.CommonUtil;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public class AdminDao extends BaseDao<Admin> implements IAdminDao {


    @Override
    public Admin selectAdminByAccount(String account) {
        Admin admin;
        session = sessionFactory.getCurrentSession();
        String hql = "from Admin where can_use =1 and account = ?";
        Query query = session.createQuery(hql);
        query.setParameter(0,account);
        admin = (Admin)query.uniqueResult();
        return admin;
    }

    @Override
    public void insertOrUpdate(Admin admin) throws Exception {
        saveOrUpdateObject(admin);
    }

    @Override
    public void logicDelete(List idList) throws Exception {
        session = sessionFactory.getCurrentSession();
        String sql = "update admin set isUse = 0";
        sql = CommonUtil.appendInSql(sql,idList,"id");
        SQLQuery query = session.createSQLQuery(sql);
        query.executeUpdate();
    }

    @Override
    public void updateAuthority(Long id , String authority) throws Exception {
        session = sessionFactory.getCurrentSession();
        String sql = "update admin set authority = ? where id = ?";
        SQLQuery query = session.createSQLQuery(sql);
        query.setParameter(0,authority);
        query.setParameter(1,id);
        query.executeUpdate();
    }

    @Override
    public void delete(List idList) throws Exception{
        session = sessionFactory.getCurrentSession();
        String sql = "delete from admin";
        sql = CommonUtil.appendInSql(sql,idList,"id");
        SQLQuery query = session.createSQLQuery(sql);
        query.executeUpdate();
    }

    @Override
    public Admin selectById(Long id) throws Exception {
        return getObjectById(id);
    }

    @Override
    public Map selectAll(Map blurParam, Map intParam, Map dateParam, int limit, int page) throws Exception {
        session = sessionFactory.getCurrentSession();
//        "\tDATE_FORMAT(u.create_time, '%Y-%m-%d') create_time,\n" +
//                "\tIFNULL(G.brief,\"暂无信息\") brief,\n" +
        String sql = "\tSELECT a.id," +
                "a.username,a.account," +
                "IF(a.admin_type=1,'超级管理员','普通管理员') admin_type," +
                "a.authority,a.party_branch_id,p.name" +
                " FROM \n" +
                "\tadmin a inner join party_branch p on a.party_branch_id = p.id\n" +
                "\tWHERE\n" +
                "\ta.can_use = 1 and p.can_use=1";

        String sqlCount = "SELECT\n" +
                "  count(*) count\n" +
                "FROM admin a inner join party_branch p on a.party_branch_id = p.id where a.can_use = 1 and p.can_use=1";
        sql = CommonUtil.appendBlurStr(sql,blurParam);
        sql = CommonUtil.appendDateStr(sql,dateParam,"a");
        sql = CommonUtil.appendIntStr(sql,intParam,"a");
        sqlCount = CommonUtil.appendBlurStr(sqlCount,blurParam);
        sqlCount = CommonUtil.appendDateStr(sqlCount,dateParam,"a");
        sqlCount = CommonUtil.appendIntStr(sqlCount,intParam,"a");

        sql = sql +" ORDER BY a.create_time DESC";

        Map resMap = CommonUtil.queryList(session,sql,sqlCount,limit,page);
        return resMap;

    }
}
