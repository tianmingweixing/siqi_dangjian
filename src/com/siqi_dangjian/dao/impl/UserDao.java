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
//        "\tDATE_FORMAT(u.create_time, '%Y-%m-%d') create_time,\n" +
//                "\tIFNULL(G.brief,\"暂无信息\") brief,\n" +
        String sql = "\tSELECT u.id,u.username,u.head_img,u.sex,u.age,u.education,u.company,u.phone,u.ID_cord,u.join_time,u.address FROM \n" +
                "\tuser u LEFT JOIN duty d ON u.dutyid = d.id\n" +
                "\tWHERE\n" +
                "\tu.can_use = 1 and d.can_use=1";

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
