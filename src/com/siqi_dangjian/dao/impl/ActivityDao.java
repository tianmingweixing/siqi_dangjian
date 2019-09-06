package com.siqi_dangjian.dao.impl;

import com.siqi_dangjian.bean.Activities;
import com.siqi_dangjian.dao.IActivityDao;
import com.siqi_dangjian.util.CommonUtil;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

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
    public Map selectAll(Map blurParam, Map dateParam, Map intParam, int limit, int page) throws Exception {
        session = sessionFactory.getCurrentSession();
//        "\tDATE_FORMAT(u.create_time, '%Y-%m-%d') create_time,\n" +
//                "\tIFNULL(G.brief,\"暂无信息\") brief,\n" +
        String sql = "SELECT\n" +
                "\ta.content,\n" +
                "\tDATE_FORMAT(a.create_time,'%y-%m-%d %h:%m:%s') create_time,\n" +
                "\tDATE_FORMAT(a.start_time,'%y-%m-%d %h:%m:%s') start_time,\n" +
                "\tDATE_FORMAT(a.end_time,'%y-%m-%d %h:%m:%s') end_time,\n" +
                "\ta.id,\n" +
                "\ta.image_path_a,\n" +
                "\ta.image_path_b,\n" +
                "\ta.is_end,\n" +
                "\ta.party_branch_id,\n" +
                "\ta.review,\n" +
                "\ta.title,\n" +
                "\ta.type_id\n" +
                "FROM\n" +
                "\tactivities a\n" +
                "WHERE\n" +
                "\ta.can_use = 1";

        String sqlCount = "SELECT count(id) count FROM activities a where a.can_use=1";

        sql = CommonUtil.appendBlurStr(sql,blurParam);
        sql = CommonUtil.appendDateStr(sql,dateParam,"a");
        sql = CommonUtil.appendIntStr(sql,intParam,"a");
        sqlCount = CommonUtil.appendBlurStr(sqlCount,blurParam);
        sqlCount = CommonUtil.appendDateStr(sqlCount,dateParam,"a");
        sqlCount = CommonUtil.appendIntStr(sqlCount,intParam,"a");

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
        String sql = "SELECT *\n" +
                "\tFROM activities\n" +
                "WHERE\n" +
                "\tcan_use = 1 and type = ?";
        SQLQuery query = session.createSQLQuery(sql);
        query.setInteger(0,type);
        map.put("list",query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list());
        return map;
    }
}
