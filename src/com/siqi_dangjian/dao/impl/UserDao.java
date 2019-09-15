package com.siqi_dangjian.dao.impl;

import com.siqi_dangjian.bean.User;
import com.siqi_dangjian.dao.IUserDao;
import com.siqi_dangjian.util.CommonUtil;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserDao extends BaseDao<User> implements IUserDao {

    @Override
    public User getUserByOpenId(String openId) {
        session = sessionFactory.getCurrentSession();
        String  hql ="from user where openId = ? and can_use = 1";
        Query query = session.createQuery(hql);
        query.setString(0,openId);
        User user =(User) query.uniqueResult();
        return user;
    }

    @Override
    public Map getUserByType(Integer type) throws Exception{
        Map map;
        session = sessionFactory.getCurrentSession();
        String sql = "SELECT *\n" +
                "\tFROM user\n" +
                "WHERE\n" +
                "\tcan_use = 1 and type = ?";
        SQLQuery query = session.createSQLQuery(sql);
        query.setInteger(0,type);
        map = (Map) query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
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
    public Map selectGroupCount() throws Exception {
        Map resMap = new HashMap();
        session = sessionFactory.getCurrentSession();

        String sqlCount = "SELECT\n" +
                "count(*) count,\n" +
                "(case d.name\n" +
                "          when 1 then '发展对象' \n" +
                "          when 2 then '积极分子' \n" +
                "          when 3 then '预备党员' \n" +
                "          when 4 then '正式党员' else '空的' end)dName\n" +
                "FROM\n" +
                "\tduty d \n" +
                "WHERE\n" +
                " d.can_use = 1\n" +
                "GROUP BY\n" +
                "\tdName\n" +
                "ORDER BY d.name";

        SQLQuery query1 = session.createSQLQuery(sqlCount);
        List countList = query1.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
        resMap.put("countList", countList);

        return resMap;

    }

    @Override
    public Map selectAll(Map blurParam,  Map intParam, Map dateParam, int limit, int page) throws Exception {
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
                "\tu.party_groups_id,\n" +
                "\tu.party_team_id,\n" +
                "\tu.ID_cord,\n" +
                "\ts.id sympathyId,\n" +
                "\t d.type_name ,\n" +
                "\t(case u.difficulty_type \n" +
                "                when 0 then '非困难'  \n" +
                "                when 1 then '困难'  \n" +
                "                when 2 then '特困难' else '暂无信息' end)difficulty_type,\n" +
                "\tDATE_FORMAT(u.join_time, '%Y-%m-%d') join_time,\n" +
                "\tDATE_FORMAT(u.create_time, '%Y-%m-%d') create_time,\n" +
                "\tu.address,\n" +
                "\tu.party_groups_id,\n" +
                "\tu.party_team_id\n" +
                "FROM\n" +
                "\tUSER u\n" +
                " LEFT JOIN duty d ON u.dutyid = d.id\n" +
                " LEFT JOIN sympathy s ON s.user_id = u.id\n" +
                " WHERE\n" +
                "\tu.can_use = 1\n" +
                " AND d.can_use = 1";

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

    @Override
    public Integer selectUserCountByTypeOrTeam(String coum, Long parem) throws Exception {
        session = sessionFactory.getCurrentSession();
        String sql = "SELECT\n" +
                "\tCOUNT(id) count\n" +
                "FROM\n" +
                "\tuser u\n" +
                "WHERE\n" +
                "\tu.can_use = 1\n" +
                "AND u."+coum+" = ?";
        SQLQuery query = session.createSQLQuery(sql);
        query.setParameter(0,parem);
        BigInteger temp = (BigInteger) query.uniqueResult();
        Integer count = temp.intValue();
        return count;

    }

}
