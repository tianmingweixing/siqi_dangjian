package com.siqi_dangjian.dao.impl;


import com.siqi_dangjian.bean.DisciplineOfHonor;
import com.siqi_dangjian.dao.IDisciplineOfHonorDao;
import com.siqi_dangjian.util.CommonUtil;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@Repository
public class DisciplineOfHonorDao extends BaseDao<DisciplineOfHonor>implements IDisciplineOfHonorDao {

    @Override
    public void insertOrUpdate(DisciplineOfHonor disciplineOfHonor) throws Exception {
        saveOrUpdateObject(disciplineOfHonor);
    }

    @Override
    public void logicDelete(List idList) throws Exception {
        session = sessionFactory.getCurrentSession();
        String sql = "update discipline_of_honor set can_use = 0";
        sql = CommonUtil.appendInSql(sql,idList,"id");
        SQLQuery query = session.createSQLQuery(sql);
        query.executeUpdate();
    }

    @Override
    public void delete(List idList) throws Exception{
        session = sessionFactory.getCurrentSession();
        String sql = "delete from discipline_of_honor";
        sql = CommonUtil.appendInSql(sql,idList,"id");
        SQLQuery query = session.createSQLQuery(sql);
        query.executeUpdate();
    }

    @Override
    public DisciplineOfHonor selectById(Long id) throws Exception {
        return getObjectById(id);
    }

    /**
     * 根据UserId和type查询用户荣誉、违纪的次数
     * @param UserId
     * @param type 0：荣誉   1：违纪  2:全部
     * @return
     */
    @Override
    public Integer selectCountByUserIdAndType(Long UserId, Integer type) throws Exception {
        session = sessionFactory.getCurrentSession();
        String sql = "SELECT count(id) count\n" +
                "\tFROM discipline_of_honor\n" +
                "WHERE\n" +
                "\tcan_use = 1 and user_id = ?";
        if (type == 0 || type == 1){
            sql = "SELECT count(id) count\n" +
                    "\tFROM discipline_of_honor\n" +
                    "WHERE\n" +
                    "\tcan_use = 1 and user_id = ? and type = ?";
        }

        SQLQuery query = session.createSQLQuery(sql);
        query.setParameter(0,UserId);
        if (type == 0 || type == 1) {
            query.setParameter(1, type);
        }
        BigInteger temp = (BigInteger) query.uniqueResult();
        Integer count = temp.intValue();
        return count;
    }


    @Override
    public Map selectAll(Map blurParam,  Map intParam,Map dateParam, int limit, int page) throws Exception {
        session = sessionFactory.getCurrentSession();
        String sql = "SELECT\n" +
                "\td.amount,\n" +
                "\td.certificate,\n" +
                "\td.content,\n" +
                "\td.create_time,\n" +
                "\td.id,\n" +
                "\td.`name`,\n" +
                "\td.note,\n" +
                "\td.passive_unit,\n" +
                "\tDATE_FORMAT(d.time, '%Y-%m-%d')time,\n" +
                "\t(\n" +
                "\t\tCASE d.type\n" +
                "\t\tWHEN 0 THEN\n" +
                "\t\t\t'荣誉'\n" +
                "\t\tWHEN 1 THEN\n" +
                "\t\t\t'违纪'\n" +
                "\t\tELSE\n" +
                "\t\t\t'暂无数据'\n" +
                "\t\tEND\n" +
                "\t) type,\n" +
                "\td.unit,\n" +
                "\td.user_id \n" +
                "FROM\n" +
                "\tdiscipline_of_honor d\n" +
                "JOIN  user u\n" +
                "ON  d.user_id = u.id \n" +
                "WHERE\n" +
                "\td.can_use = 1 and u.can_use = 1";

        String sqlCount = "SELECT\n" +
                "  count(*) count\n" +
                "FROM discipline_of_honor d where d.can_use = 1";


        sql = CommonUtil.appendBlurStr(sql,blurParam);
        sql = CommonUtil.appendCustomDateStr(sql,dateParam,"d","time");//自定义时间查询
        sql = CommonUtil.appendIntStr(sql,intParam,"d");
        sqlCount = CommonUtil.appendBlurStr(sqlCount,blurParam);
        sqlCount = CommonUtil.appendDateStr(sqlCount,dateParam,"d");
        sqlCount = CommonUtil.appendIntStr(sqlCount,intParam,"d");
        Map resMap = CommonUtil.queryList(session,sql,sqlCount,limit,page);
        return resMap;

    }



}
