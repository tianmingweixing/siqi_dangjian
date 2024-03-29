package com.siqi_dangjian.dao.impl;

import com.siqi_dangjian.bean.Duty;
import com.siqi_dangjian.bean.MeetingType;
import com.siqi_dangjian.dao.IMeetingTypeDao;
import com.siqi_dangjian.util.CommonUtil;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public class MeetingTypeDao extends BaseDao<MeetingType> implements IMeetingTypeDao {

    @Override
    public void insertOrUpdate(MeetingType meetingType) throws Exception {
        saveOrUpdateObject(meetingType);
    }

    @Override
    public void logicDelete(List idList) throws Exception {
        session = sessionFactory.getCurrentSession();
        String sql = "update meeting_type set can_use = 0";
        sql = CommonUtil.appendInSql(sql,idList,"id");
        SQLQuery query = session.createSQLQuery(sql);
        query.executeUpdate();
    }

    @Override
    public void delete(List idList) throws Exception{
        session = sessionFactory.getCurrentSession();
        String sql = "delete from meeting_type";
        sql = CommonUtil.appendInSql(sql,idList,"id");
        SQLQuery query = session.createSQLQuery(sql);
        query.executeUpdate();
    }

    @Override
    public MeetingType selectById(Long id) throws Exception {
        return getObjectById(id);
    }


    @Override
    public Map selectAll(Map blurParam,Map intParam, Map dateParam,  Integer limit, Integer page) throws Exception {
        session = sessionFactory.getCurrentSession();
        String sql = "SELECT\n" +
                "\tm.id,\n" +
                "\tm.type_name,\n" +
                "\tDATE_FORMAT(m.update_time, '%Y-%m-%d') update_time,\n" +
                "\tDATE_FORMAT(m.create_time, '%Y-%m-%d') create_time\n" +
                "FROM\n" +
                "\tmeeting_type m\n" +
                "WHERE\n" +
                "\tm.can_use = 1";

        String sqlCount = "SELECT\n" +
                "  count(*) count\n" +
                "FROM meeting_type m where m.can_use = 1 ";

        sqlCount = CommonUtil.appendBlurStr(sqlCount,blurParam);
        sqlCount = CommonUtil.appendDateStr(sqlCount,dateParam,"m");
        sqlCount = CommonUtil.appendIntStr(sqlCount,intParam,"m");
        Map resMap = CommonUtil.queryList(session,sql,sqlCount,limit,page);
        return resMap;

    }

    @Override
    public Map selectAllCategory(Map blurMap, Map intMap, Map dateMap) {
        session = sessionFactory.getCurrentSession();
        String sql = "SELECT\n" +
                "\tm.id,\n" +
                "\tm.type_name\n" +
                "FROM\n" +
                "\tmeeting_type m\n" +
                "WHERE\n" +
                "\tm.can_use = 1";

        String sqlCount = "SELECT\n" +
                "  count(*) count\n" +
                "FROM meeting_type m where m.can_use = 1 ";

        sqlCount = CommonUtil.appendBlurStr(sqlCount,blurMap);
        sqlCount = CommonUtil.appendDateStr(sqlCount,intMap,"m");
        sqlCount = CommonUtil.appendIntStr(sqlCount,dateMap,"m");
        Map resMap = CommonUtil.queryAllCategoryList(session,sql,sqlCount);
        return resMap;
    }

    @Override
    public List<MeetingType> selectList() throws Exception {
        session = sessionFactory.getCurrentSession();
        String sql = "select id,type_name typeName FROM meeting_Type where can_use=1";

//        Query query = session.createSQLQuery(sql).addEntity(Duty.class);

        Query query = session.createSQLQuery(sql)
                .addScalar("id", LongType.INSTANCE)
                .addScalar("typeName", StringType.INSTANCE);

        query.setResultTransformer(Transformers.aliasToBean(MeetingType.class));

        List<MeetingType> list = query.list();
        return list;

    }
}
