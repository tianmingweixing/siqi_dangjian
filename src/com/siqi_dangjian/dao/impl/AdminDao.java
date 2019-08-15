package com.siqi_dangjian.dao.impl;

import com.siqi_dangjian.bean.Admin;
import com.siqi_dangjian.dao.IAdminDao;
import com.siqi_dangjian.util.CommonUtil;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public class AdminDao extends BaseDao<Admin> implements IAdminDao {
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
    public Admin selectById(Long id) throws Exception {
        return getObjectById(id);
    }

    @Override
    public Map selectAll(Map blurParam, Map dateParam, Map intParam, int limit, int page) throws Exception {
        return null;
    }
}
