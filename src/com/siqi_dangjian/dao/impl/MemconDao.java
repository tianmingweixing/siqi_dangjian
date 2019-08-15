package com.siqi_dangjian.dao.impl;


import com.siqi_dangjian.bean.Memcon;
import com.siqi_dangjian.bean.User;
import com.siqi_dangjian.dao.IMemconDao;
import com.siqi_dangjian.util.CommonUtil;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public class MemconDao extends BaseDao<Memcon> implements IMemconDao {
    @Override
    public void insertOrUpdate(Memcon memcon) throws Exception {
        saveOrUpdateObject(memcon);
    }

    @Override
    public void logicDelete(List idList) throws Exception {
        session = sessionFactory.getCurrentSession();
        String sql = "update memcon set isUse = 0";
        sql = CommonUtil.appendInSql(sql,idList,"id");
        SQLQuery query = session.createSQLQuery(sql);
        query.executeUpdate();
    }

    @Override
    public User selectById(Long id) throws Exception {
        return null;
    }

    @Override
    public Map selectAll(Map blurParam, Map dateParam, Map intParam, int limit, int page) throws Exception {
        return null;
    }
}
