package com.siqi_dangjian.dao.impl;

import com.siqi_dangjian.bean.ApplicationForm;
import com.siqi_dangjian.dao.IApplicationFormDao;
import com.siqi_dangjian.util.CommonUtil;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public class ApplicationFormDao extends BaseDao<ApplicationForm> implements IApplicationFormDao {

    @Override
    public void insertOrUpdateApplicationForm(ApplicationForm applicationForm) {
        saveOrUpdateObject(applicationForm);
    }

    @Override
    public void deleteApplicationForm(List idList) {
        session = sessionFactory.getCurrentSession();
        String sql = "update application_form set can_use = 0";
        sql = CommonUtil.appendInSql(sql, idList, "id");
        SQLQuery query = session.createSQLQuery(sql);
        query.executeUpdate();
    }

    @Override
    public void updateStatus(int status) {
        session = sessionFactory.getCurrentSession();
        String hql = "update application_form set status = ? where canUse=1 and id = ?";
        Query query = session.createQuery(hql).setInteger(0,status);
        query.executeUpdate();
    }

    @Override
    public ApplicationForm getApplicationFormById(long id) {
        session = sessionFactory.getCurrentSession();
        String hql = "from ApplicationForm where can_use=1 and id = ?";
        Query query = session.createQuery(hql);
        query.setLong(0,id);
        return (ApplicationForm)query.uniqueResult();
    }

    @Override
    public Map getApplicationFormList(int limit, int page, Map blurMap, Map intMap, Map dateMap) {
        session = sessionFactory.getCurrentSession();
        String sql = "SELECT a.id,a.app_form,a.phone,a.user_name,a.user_id,a.refuse_reason,\n" +
                "             DATE_FORMAT(a.review_time,'%Y-%m-%d') review_time,\n" +
                "             DATE_FORMAT(a.create_time,'%Y-%m-%d') create_time,\n" +
                "  a.status,\t(case a.status\n" +
                "              when 0 then '待审核'\n" +
                "              when 1 then '通过'\n" +
                "              when 2 then '拒绝' else '空的' end)ReviewStatus\n" +
                "FROM application_form a INNER JOIN user u ON a.user_id=u.id\n" +
                "WHERE a.can_use = 1 and u.can_use = 1 ";


        String sqlCount = "SELECT COUNT(*) count\n" +
                "FROM application_form a INNER JOIN user u ON a.user_id=u.id \n" +
                "WHERE a.can_use = 1 and u.can_use = 1 ";

        sql = CommonUtil.appendBlurStr(sql,blurMap);
        sql = CommonUtil.appendDateStr(sql,dateMap,"a");
        sql = CommonUtil.appendIntStr(sql,intMap,"a");
        sql = sql +" ORDER BY a.create_time DESC";

        sqlCount = CommonUtil.appendBlurStr(sqlCount,blurMap);
        sqlCount = CommonUtil.appendDateStr(sqlCount,dateMap,"a");
        sqlCount = CommonUtil.appendIntStr(sqlCount,intMap,"a");
        Map resMap = CommonUtil.queryList(session,sql,sqlCount,limit,page);

        return resMap;
    }
}
