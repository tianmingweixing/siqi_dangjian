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
        String sql = "update sympathy set can_use = 0";
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
    public Map selectAll(Map blurParam,  Map intParam,Map dateParam, int limit, int page) throws Exception {
        session = sessionFactory.getCurrentSession();

        String sql = "SELECT\n" +
                "\ts.id sympathyId,\n" +
                "\tu.id userId,\n" +
                "\tu.username,\n" +
                "\ts.difficult,\n" +
                "\ts.note,\n" +
                "\ts.sympathy_product,\n" +
                "\tDATE_FORMAT(s.sympathy_time, '%Y-%m-%d') sympathy_time,\n" +
                "\ts.unit_and_position,\n" +
                "\tu.age,\n" +
                "\tu.sex,\n" +
                "\tu.phone,\n" +
                "\tu.company,\n" +
                "\tu.join_time,\n" +
                "\tu.ID_cord\n" +
                "FROM\n" +
                "\tsympathy s\n" +
                "JOIN USER u ON s.user_id = u.id\n" +
                "WHERE\n" +
                "\ts.can_use = 1\n" +
                "AND u.can_use = 1";

        String sqlCount = "SELECT\n" +
                "\tcount(*)\n" +
                "FROM\n" +
                "\tsympathy s\n" +
                "JOIN user u ON s.user_id = u.id\n" +
                "WHERE\n" +
                "\ts.can_use = 1\n" +
                "AND u.can_use = 1";
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
