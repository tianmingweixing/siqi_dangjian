package com.siqi_dangjian.dao.impl;

import com.siqi_dangjian.bean.ActivitiesBrand;
import com.siqi_dangjian.dao.IActivityBrandDao;
import com.siqi_dangjian.util.CommonUtil;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ActivityBrandDao extends BaseDao<ActivitiesBrand> implements IActivityBrandDao {


    @Override
    public void insertOrUpdate(ActivitiesBrand activitiesBrand) throws Exception {
        saveOrUpdateObject(activitiesBrand);
    }

    @Override
    public void logicDelete(List idList) throws Exception {
        session = sessionFactory.getCurrentSession();
        String sql = "update activities_brand set isUse = 0";
        sql = CommonUtil.appendInSql(sql,idList,"id");
        SQLQuery query = session.createSQLQuery(sql);
        query.executeUpdate();
    }

    @Override
    public void delete(List idList) throws Exception {
        session = sessionFactory.getCurrentSession();
        String sql = "delete from activities_brand";
        sql = CommonUtil.appendInSql(sql,idList,"id");
        SQLQuery query = session.createSQLQuery(sql);
        query.executeUpdate();
    }

    @Override
    public ActivitiesBrand selectById(Long id) throws Exception {
        return getObjectById(id);
    }

    @Override
    public Map selectAll(int limit, int page) throws Exception {
        session = sessionFactory.getCurrentSession();

        String sql = "SELECT\n" +
                "\ta.id,\n" +
                "\tDATE_FORMAT(a.create_time, '%Y-%m-%d') create_time,\n" +
                "\ta.party_branch_id,\n" +
                "\tIFNULL(a.brand_name,\"暂无信息\") brand_name,\n" +
                "\tDATE_FORMAT(a.update_time, '%Y-%m-%d') update_time\n" +
                "FROM\n" +
                "\tactivities_brand a\n" +
                "WHERE\n" +
                "\ta.can_use = 1";
        String sqlCount = "SELECT count(*) count FROM activities_brand a WHERE a.can_use = 1";
        Map resMap = CommonUtil.queryList(session,sql,sqlCount,limit,page);
        return resMap;
    }

    @Override
    public List<ActivitiesBrand> selectList() throws Exception {
        session = sessionFactory.getCurrentSession();
        String hql = "from ActivitiesBrand where canUse=1";
        Query query = session.createQuery(hql);
        List res = (List<ActivitiesBrand>)query.list();
        return res;
    }

}
