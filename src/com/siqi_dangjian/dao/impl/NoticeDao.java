package com.siqi_dangjian.dao.impl;

import com.siqi_dangjian.bean.Notice;
import com.siqi_dangjian.dao.INoticeDao;
import com.siqi_dangjian.util.CommonUtil;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public class NoticeDao extends BaseDao<Notice> implements INoticeDao {
    @Override
    public void insertOrUpdate(Notice notice) throws Exception {
        saveOrUpdateObject(notice);
    }

    @Override
    public void logicDelete(List idList) throws Exception {
        session = sessionFactory.getCurrentSession();
        String sql = "update notice set can_use = 0";
        sql = CommonUtil.appendInSql(sql,idList,"id");
        SQLQuery query = session.createSQLQuery(sql);
        query.executeUpdate();
    }

    @Override
    public void delete(List idList) throws Exception{
        session = sessionFactory.getCurrentSession();
        String sql = "delete from notice";
        sql = CommonUtil.appendInSql(sql,idList,"id");
        SQLQuery query = session.createSQLQuery(sql);
        query.executeUpdate();
    }

    @Override
    public Notice selectById(Long id) throws Exception {
        return getObjectById(id);
    }

    @Override
    public Map selectAll(Map blurParam,Map intParam, Map dateParam,  int limit, int page) throws Exception {
        session = sessionFactory.getCurrentSession();
        String sql = "SELECT\n" +
                "\tn.content,\n" +
                "\tn.id,\n" +
                "\tDATE_FORMAT(n.create_time, '%Y-%m-%d')create_time,\n" +
                "\tn.image_path,\n" +
                "\tn.party_branch_id,\n" +
                "\tn.title\n" +
                "FROM\n" +
                "\tnotice n\n" +
                "WHERE\n" +
                "\tn.can_use = 1";

        String sqlCount = "SELECT\n" +
                "  count(*) count\n" +
                "FROM notice n " +
                "WHERE\n" +
                "\tn.can_use = 1";
        sql = CommonUtil.appendBlurStr(sql,blurParam);
        sql = CommonUtil.appendDateStr(sql,dateParam,"n");
        sql = CommonUtil.appendIntStr(sql,intParam,"n");
        sqlCount = CommonUtil.appendBlurStr(sqlCount,blurParam);
        sqlCount = CommonUtil.appendDateStr(sqlCount,dateParam,"n");
        sqlCount = CommonUtil.appendIntStr(sqlCount,intParam,"n");
        Map resMap = CommonUtil.queryList(session,sql,sqlCount,limit,page);
        return resMap;

    }
}
