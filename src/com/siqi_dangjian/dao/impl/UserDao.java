package com.siqi_dangjian.dao.impl;

import com.siqi_dangjian.bean.User;
import com.siqi_dangjian.dao.IUserDao;
import com.siqi_dangjian.util.CommonUtil;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserDao extends BaseDao<User> implements IUserDao {

    @Override
    public Map getUserByType(Integer type) throws Exception{
        Map map = new HashMap();
        session = sessionFactory.getCurrentSession();
        String sql = "SELECT *\n" +
                "\tFROM user\n" +
                "WHERE\n" +
                "\tcan_use = 1 and type = ?";
        SQLQuery query = session.createSQLQuery(sql);
        query.setInteger(0,type);
        map.put("list",query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list());
        return map;
    }

    @Override
    public Map getUserInfoById(Long id) throws Exception{
        session = sessionFactory.getCurrentSession();
        String sql = "SELECT *\n" +
                "\tFROM user\n" +
                "WHERE\n" +
                "\tcan_use = 1 and id = ?";
        SQLQuery query = session.createSQLQuery(sql);
        query.setLong(0,id);
        return (Map) query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).uniqueResult();
    }

    @Override
    public void insertOrUpdate(User user) throws Exception {
        saveOrUpdateObject(user);
    }

    @Override
    public void logicDelete(List idList) throws Exception {
        session = sessionFactory.getCurrentSession();
        String sql = "update user set can_use = 0";
        sql = CommonUtil.appendInSql(sql,idList,"id");
        SQLQuery query = session.createSQLQuery(sql);
        query.executeUpdate();
    }

    @Override
    public void delete(List idList) throws Exception{
        session = sessionFactory.getCurrentSession();
        String sql = "delete from user";
        sql = CommonUtil.appendInSql(sql,idList,"id");
        SQLQuery query = session.createSQLQuery(sql);
        query.executeUpdate();
    }

    @Override
    public User selectById(Long id) throws Exception {
        return getObjectById(id);
    }

    @Override
    public Map selectAll(Map blurParam, Map dateParam, Map intParam, int limit, int page) throws Exception {
        session = sessionFactory.getCurrentSession();

        String sql = " SELECT\n" +
                "\tu.id,\n" +
                "\tu.username,\n" +
                "\tu.head_img,\n" +
                "\tif(u.sex='1','男','女')sex,\n" +
                "\tu.age,\n" +
                "\tu.education,\n" +
                "\tu.company,\n" +
                "\tu.phone,\n" +
                "\tu.dutyid,\n" +
                "\tu.ID_cord,\n" +
                "\t(case d.name\n" +
                "          when 1 then '发展对象' \n" +
                "          when 2 then '积极分子' \n" +
                "          when 3 then '预备党员' \n" +
                "          when 4 then '正式党员' else '空的' end)name,\n" +
                "\td.party_duty,\n" +
                "\tDATE_FORMAT(u.join_time, '%Y-%m-%d') join_time,\n" +
                "\tDATE_FORMAT(u.create_time, '%Y-%m-%d') create_time,\n" +
                "\tu.address\n" +
                "FROM\n" +
                "\tUSER u\n" +
                "LEFT JOIN duty d ON u.dutyid = d.id\n" +
                "WHERE\n" +
                "\tu.can_use = 1\n" +
                "AND d.can_use = 1";

        String sqlCount = "SELECT\n" +
                "  count(*) count\n" +
                "FROM user u where u.can_use = 1";
        sql = CommonUtil.appendBlurStr(sql,blurParam);
        sql = CommonUtil.appendDateStr(sql,dateParam,"u");
        sql = CommonUtil.appendIntStr(sql,intParam,"u");
        sqlCount = CommonUtil.appendBlurStr(sqlCount,blurParam);
        sqlCount = CommonUtil.appendDateStr(sqlCount,dateParam,"u");
        sqlCount = CommonUtil.appendIntStr(sqlCount,intParam,"u");
        Map resMap = CommonUtil.queryList(session,sql,sqlCount,limit,page);
        return resMap;

    }

}
