package com.siqi_dangjian.dao.impl;

import com.siqi_dangjian.bean.Activities;
import com.siqi_dangjian.dao.IActivityDao;
import com.siqi_dangjian.util.CommonUtil;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ActivityDao extends BaseDao<Activities> implements IActivityDao {

    @Override
    public void insertOrUpdate(Activities activities) throws Exception {
        saveOrUpdateObject(activities);
    }

    @Override
    public void logicDelete(List idList) throws Exception {
        session = sessionFactory.getCurrentSession();
        String sql = "update activities set isUse = 0";
        sql = CommonUtil.appendInSql(sql,idList,"id");
        SQLQuery query = session.createSQLQuery(sql);
        query.executeUpdate();
    }

    @Override
    public void delete(List idList) throws Exception{
        session = sessionFactory.getCurrentSession();
        String sql = "delete from activities";
        sql = CommonUtil.appendInSql(sql,idList,"id");
        SQLQuery query = session.createSQLQuery(sql);
        query.executeUpdate();
    }

    /**
     * 根据id查询活动
     * @param id
     * @return Activities
     */
    @Override
    public Activities selectById(Long id) throws Exception {
        return getObjectById(id);
    }

    /**
     * 查询活动列表
     * @param blurParam
     * @param dateParam
     * @param intParam
     * @param limit
     * @param page
     * @return  Map
     * @throws Exception
     */
    @Override
    public Map selectAll(Map blurParam, Map intParam, Map dateParam, int limit, int page) throws Exception {
        session = sessionFactory.getCurrentSession();

        String sql = "SELECT\n" +
                "\ta.content,\n" +
                "\tDATE_FORMAT(a.create_time,'%Y-%m-%d %T') create_time,\n" +
                "\tDATE_FORMAT(a.start_time,'%Y-%m-%d %T') start_time,\n" +
                "\tDATE_FORMAT(a.end_time,'%Y-%m-%d %T') end_time,\n" +
                "\tDATE_FORMAT(a.end_join_time,'%Y-%m-%d %T') end_join_time,\n" +
                "\ta.id,\n" +
                "\ta.image_path_a,\n" +
                "\ta.image_path_b,\n" +
                "\ta.status,\n" +
                "\t(\n" +
                "\t\tCASE\n" +
                "\t\tWHEN a.end_time >= now()\n" +
                "\t\tAND a.start_time <= now() THEN\n" +
                "\t\t\t'进行中'\n" +
                "\t\tWHEN a.start_time > now() THEN\n" +
                "\t\t\t'筹备中'\n" +
                "\t\tWHEN a.end_time < now() THEN\n" +
                "\t\t\t'已结束'\n" +
                "\t\tEND\n" +
                "\t) AS activityStatus," +
                "\ta.party_branch_id,\n" +
                "\ta.review,\n" +
                "\ta.title,\n" +
                "\t(select b.brand_name from activities_brand b where b.id=a.brand_id and b.can_use=1) brand_name,\n" +
                "\t(select t.type_name from activities_type t where t.id=a.type_id and t.can_use=1) type_name\n" +
                "FROM\n" +
                "\tactivities a\n" +
                "WHERE\n" +
                "\ta.can_use = 1";

        String sqlCount = "SELECT count(id) count FROM activities a where a.can_use=1";

        sql = CommonUtil.appendBlurStr(sql,blurParam);
        sql = CommonUtil.appendCustomDateStr(sql,dateParam,"a","start_time");
        sql = CommonUtil.appendIntStr(sql,intParam,"a");
        sqlCount = CommonUtil.appendBlurStr(sqlCount,blurParam);
        sqlCount = CommonUtil.appendCustomDateStr(sqlCount,dateParam,"a","start_time");
        sqlCount = CommonUtil.appendIntStr(sqlCount,intParam,"a");

        sql = sql + " ORDER BY a.create_time desc";

        Map resMap = CommonUtil.queryList(session,sql,sqlCount,limit,page);
        return resMap;

    }

    /**
     * 根据类型查询活动
     * @param type
     * @return
     */
    @Override
    public Map selectActivityByType(Integer type) throws Exception {
        Map map = new HashMap();
        session = sessionFactory.getCurrentSession();
        String sql = "SELECT\n" +
                "\ta.brand_id,\n" +
                "\ta.content,\n" +
                "\ta.id,\n" +
                "\ta.end_time,\n" +
                "\ta.end_join_time,\n" +
                "\ta.start_time,\n" +
                "\ta.`status`,\n" +
                "\ta.image_path_a,\n" +
                "\ta.image_path_b,\n" +
                "\ta.title,\n" +
                "\ta.type_id,\n" +
                "\ta.review,\n" +
                "\tact.type_name\n" +
                "FROM\n" +
                "\tactivities a\n" +
                "LEFT JOIN activities_type act ON a.type_id = act.id\n" +
                "WHERE\n" +
                "\ta.can_use = 1\n" +
                "AND act.can_use = 1\n" +
                "AND a.type_id = ?";
        SQLQuery query = session.createSQLQuery(sql);
        query.setInteger(0,type);
        map.put("list",query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list());
        return map;
    }

    /**
     * 根据类型查询活动
     * @param type_id
     * @return
     */
    @Override
    public Integer selectActivityCountByType(Long type_id) throws Exception {
        session = sessionFactory.getCurrentSession();
        String sql = "SELECT count(id) count\n" +
                "\tFROM activities\n" +
                "WHERE\n" +
                "\tcan_use = 1 and type_id = ?";
        SQLQuery query = session.createSQLQuery(sql);
        query.setParameter(0,type_id);
        BigInteger temp = (BigInteger) query.uniqueResult();
        Integer count = temp.intValue();
        return count;
    }

    @Override
    public Map selectActivityGroupCount() {
        Map resMap = new HashMap();
        session = sessionFactory.getCurrentSession();

        String sqlCount = "SELECT\n" +
                "\tcount(*) count,\n" +
                "\tAT.type_name typeName,AT.id\n" +
                "FROM\n" +
                "\tactivities a\n" +
                "JOIN activities_type AT ON AT.id = a.type_id\n" +
                "WHERE\n" +
                "\ta.can_use = 1\n" +
                "GROUP BY\n" +
                "\ttypeName";

        SQLQuery query1 = session.createSQLQuery(sqlCount);
        List countList = query1.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
        resMap.put("countList", countList);

        return resMap;
    }

    @Override
    public String selectSignInById(Long id) throws Exception {
        session = sessionFactory.getCurrentSession();
        String sql = "SELECT\n" +
                "\tGROUP_CONCAT(u.username) AS userName\n" +
                "FROM\n" +
                "\tactivities a\n" +
                "JOIN activity_of_user o ON a.id = o.activity_id\n" +
                "JOIN USER u ON u.id = o.user_id\n" +
                "WHERE\n" +
                "\tu.can_use = 1\n" +
                "AND a.can_use = 1\n" +
                "AND a.id = ?";
        SQLQuery query = session.createSQLQuery(sql);
        query.setParameter(0,id);
        String userName = (String) query.uniqueResult();

        return userName;
    }

    /**
     * 根据活动状态查询活动列表
     * @param state
     * @param limit
     * @param page
     * @return  Map
     * @throws Exception
     */
    @Override
    public Map selectActivitysByState(int state, int limit, int page) throws Exception {
        session = sessionFactory.getCurrentSession();

        String sql = "SELECT\n" +
                "\ta.content,\n" +
                "\tDATE_FORMAT(a.create_time,'%Y-%m-%d %T') create_time,\n" +
                "\tDATE_FORMAT(a.start_time,'%Y-%m-%d %T') start_time,\n" +
                "\tDATE_FORMAT(a.end_time,'%Y-%m-%d %T') end_time,\n" +
                "\tDATE_FORMAT(a.end_join_time,'%Y-%m-%d %T') end_join_time,\n" +
                "\ta.id,\n" +
                "\ta.image_path_a,\n" +
                "\ta.image_path_b,\n" +
                "\ta.status,\n" +
                "\t(\n" +
                "\t\tCASE\n" +
                "\t\tWHEN a.end_time >= now()\n" +
                "\t\tAND a.start_time <= now() THEN\n" +
                "\t\t\t'进行中'\n" +
                "\t\tWHEN a.start_time > now() THEN\n" +
                "\t\t\t'筹备中'\n" +
                "\t\tWHEN a.end_time < now() THEN\n" +
                "\t\t\t'已结束'\n" +
                "\t\tEND\n" +
                "\t) AS activityStatus," +
                "\ta.party_branch_id,\n" +
                "\ta.review,\n" +
                "\ta.title,\n" +
                "\t(select b.brand_name from activities_brand b where b.id=a.brand_id and b.can_use=1) brand_name,\n" +
                "\t(select t.type_name from activities_type t where t.id=a.type_id and t.can_use=1) type_name\n" +
                "FROM\n" +
                "\tactivities a\n" +
                "WHERE\n" +
                "\ta.can_use = 1";

        String sqlCount = "SELECT count(id) count FROM activities a where a.can_use=1";

        if(state == 1){
            //查进行中活动
            sql = sql + " and NOW() BETWEEN a.start_time and a.end_time";
            sqlCount = sqlCount + " and NOW() BETWEEN a.start_time and a.end_time";
        }else if (state == 2){
            //查筹办中活动
            sql = sql + " and a.start_time > NOW()";
            sqlCount = sqlCount + " and a.start_time > NOW()";
        }else if (state == 3){
            //查已完成活动
            sql = sql + " and a.end_time < NOW()";
            sqlCount = sqlCount + " and a.start_time > NOW()";
        }else {
            //查全部
        }

        Map resMap = CommonUtil.queryList(session,sql,sqlCount,limit,page);
        return resMap;

    }

    @Override
    public Map selectActivityStatusGroupCount() {
        Map resMap = new HashMap();
        session = sessionFactory.getCurrentSession();

        String sql = "    SELECT                                                    \n" +
                     "      count(*) statusCount,                                   \n" +
                     "      (                                                       \n" +
                     "        CASE                                                  \n" +
                     "        WHEN end_time > now() AND start_time > now() THEN 0   \n" +
                     "        WHEN start_time < now() AND end_time > now() THEN 1   \n" +
                     "        WHEN end_time < now() THEN 2                          \n" +
                     "        END                                                   \n" +
                     "      ) AS activityStatus                                     \n" +
                     "    FROM                                                      \n" +
                     "      activities                                              \n" +
                     "    WHERE                                                     \n" +
                     "       can_use =1                                             \n" +
                     "     GROUP BY                                                 \n" +
                     "       activityStatus                                           ";

        SQLQuery query = session.createSQLQuery(sql);
        List countList = query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
        resMap.put("countList", countList);

        return resMap;
    }

    @Override
    public Activities selectActivitiesInfoByUserId(Long id) {
        session = sessionFactory.getCurrentSession();
        String sql = "select activities a  where  a.user_id = ?";
        SQLQuery query = session.createSQLQuery(sql);

        Activities activities = (Activities) query.uniqueResult();

        return activities;
    }

    @Override
    public Map selectActivityByUserId(Long userId, Integer limit , Integer page) {
        HashMap map = new HashMap();
        session = sessionFactory.getCurrentSession();
        String sql = "SELECT\n" +
                "\ta.title,\n" +
                "\ta.content,\n" +
                "\ta.review,\n" +
                "\tDATE_FORMAT(a.start_time,'%Y-%m-%d %T') start_time,\n" +
                "  DATE_FORMAT(a.end_time,'%Y-%m-%d %T') end_time,\n" +
                "(\n" +
                "\t\tCASE\n" +
                "\t\tWHEN a.end_time >= now()\n" +
                "\t\tAND a.start_time <= now() THEN\n" +
                "\t\t\t'进行中'\n" +
                "\t\tWHEN a.start_time > now() THEN\n" +
                "\t\t\t'筹备中'\n" +
                "\t\tWHEN a.end_time < now() THEN\n" +
                "\t\t\t'已结束'\n" +
                "\t\tEND\n" +
                "\t) AS activityStatus,\n" +
                "\ta.id AS activityId,\n" +
                "\tu.id AS user_id,\n" +
                "\tu.username\n" +
                "FROM\n" +
                "\tactivities a\n" +
                "INNER JOIN activity_of_user au ON a.id = au.activity_id\n" +
                "INNER JOIN USER u ON au.user_id = u.id\n" +
                "WHERE\n" +
                "\ta.can_use = 1\n" +
                "AND u.can_use = 1\n" +
                "AND au.user_id = ? ";

        String sqlCount = "SELECT count(*)\n" +
                "FROM\n" +
                "\tactivities a\n" +
                "INNER JOIN activity_of_user au ON a.id = au.activity_id\n" +
                "INNER JOIN USER u ON au.user_id = u.id\n" +
                "WHERE\n" +
                "\ta.can_use = 1\n" +
                "AND u.can_use = 1\n" +
                "AND au.user_id = ?";

        SQLQuery query = session.createSQLQuery(sql);
        SQLQuery query1 = session.createSQLQuery(sqlCount);
        query1.setLong(0,userId);
        query.setLong(0,userId);
        query.setFirstResult(limit * (page - 1));
        query.setMaxResults(limit);
        List list = query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
        BigInteger temp = (BigInteger) query1.uniqueResult();
        int count = temp.intValue();
        Map res = new HashMap();
        res.put("list", list);
        res.put("count", count);

        return res;
    }

}
