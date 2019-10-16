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
        String  hql ="from User where openId = ? and can_use = 1";
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
        String sql = "SELECT\n" +
                "\tu.id,\n" +
                "\tu.dutyid dutyId,\n" +
                "\tu.create_time,\n" +
                "\tu.activist_time,\n" +
                "\tu.address,\n" +
                "\tu.age,\n" +
                "\tu.birth,\n" +
                "  u.profiles,\n" +
                "\tu.company,\n" +
                "\tu.develop_time,\n" +
                "  (case u.difficulty_type\n" +
                "   when 0 then '非困难'\n" +
                "   when 1 then '困难'\n" +
                "   when 2 then '非常困难'   else '暂无信息' end)difficulty_type,\n" +
                "(case u.education\n" +
                "   when 1 then '初中'\n" +
                "   when 2 then '高中'\n" +
                "   when 3 then '中专'\n" +
                "   when 4 then '大专'\n" +
                "   when 5 then '本科'\n" +
                "   when 6 then '硕士'\n" +
                "   when 7 then '博士'\n" +
                "   else '暂无信息'\n" +
                "   end\n" +
                "  )education,\n" +
                "\tu.head_img,\n" +
                "\tu.ID_cord,\n" +
                "\tu.join_time,\n" +
                "\tu.last_time,\n" +
                "\tu.nation,\n" +
                "\tu.party_posts,\n" +
                "\tu.nick_name,\n" +
                "\tu.official_time,\n" +
                "\tu.origo,\n" +
                "\tu.phone,\n" +
                "\tu.ready_time,\n" +
                "\tif(u.sex='1','男','女')sex,\n" +
                "\tu.train_people,\n" +
                "\tu.username,\n" +
                "\tu.party_team_id,\n" +
                "\tu.party_groups_id,\n" +
                "\t(\n" +
                "\t\tSELECT\n" +
                "\t\t\td.type_name\n" +
                "\t\tFROM\n" +
                "\t\t\tduty d\n" +
                "\t\tWHERE\n" +
                "\t\t\td.id = u.dutyid\n" +
                "\t) dutyName,\n" +
                "\t(\n" +
                "\t\tSELECT\n" +
                "\t\t\tg.`name`\n" +
                "\t\tFROM\n" +
                "\t\t\tparty_group g\n" +
                "\t\tWHERE\n" +
                "\t\t\tg.id = u.party_groups_id\n" +
                "\t) groupName,\n" +
                "\t(\n" +
                "\t\tSELECT\n" +
                "\t\t\tt.`name`\n" +
                "\t\tFROM\n" +
                "\t\t\tparty_team t\n" +
                "\t\tWHERE\n" +
                "\t\t\tt.id = u.party_team_id\n" +
                "\t) teamName\n" +
                "FROM\n" +
                "\tUSER u\n" +
                "WHERE\n" +
                "\tu.can_use = 1\n" +
                "AND u.id = ?";
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
                "\tcount(*) count,\n" +
                "\td.type_name typeName,\n" +
                "\td.id typeId \n" +
                "FROM\n" +
                "\tUSER u\n" +
                "JOIN duty d ON u.dutyid = d.id\n" +
                "WHERE\n" +
                "\td.can_use = 1 and u.can_use = 1\n" +
                "GROUP BY\n" +
                "\td.id";

        SQLQuery query1 = session.createSQLQuery(sqlCount);
        List countList = query1.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
        resMap.put("userTypeCount", countList);

        return resMap;

    }



    /**
     * 困难党员数量
     * @return
     * @throws Exception
     */
    @Override
    public Integer selectDifficultToPartyMembersCount(Long dutyid) throws Exception {
        session = sessionFactory.getCurrentSession();

        String sqlCount = "SELECT\n" +
                "  count(id) count\n" +
                "FROM\n" +
                "  USER u\n" +
                "WHERE\n" +
                "  u.difficulty_type in(1,2) and u.dutyid = ? and u.can_use = 1";

        SQLQuery query1 = session.createSQLQuery(sqlCount);
        query1.setParameter(0,dutyid);
        BigInteger temp = (BigInteger) query1.uniqueResult();
        Integer count = temp.intValue();
        return count;

    }
    /**
     * 困难职工数量
     * @return
     * @throws Exception
     */
    @Override
    public Integer selectNeedyWorkerCount(Long dutyid) throws Exception {
        session = sessionFactory.getCurrentSession();

        String sqlCount = "SELECT\n" +
                "  count(id) count\n" +
                "FROM\n" +
                "  USER u\n" +
                "WHERE\n" +
                "  u.difficulty_type in(1,2) and u.dutyid != ? and u.can_use = 1";

        SQLQuery query1 = session.createSQLQuery(sqlCount);
        query1.setParameter(0,dutyid);
        BigInteger temp = (BigInteger) query1.uniqueResult();
        Integer count = temp.intValue();
        return count;


    }


    /**
     * 查班子成员数量
     * @return
     * @throws Exception
     */
    @Override
    public Integer selectUserCountOfGroup() throws Exception {

        session = sessionFactory.getCurrentSession();
        String sqlCount = "SELECT COUNT(u.id) count from user u where u.party_groups_id <> '' and u.can_use = 1";

        SQLQuery query = session.createSQLQuery(sqlCount);
        BigInteger temp = (BigInteger) query.uniqueResult();
        Integer count = temp.intValue();

        return count;

    }

    @Override
    public Map selectAll(Map blurParam,  Map intParam, Map dateParam, int limit, int page) throws Exception {
        session = sessionFactory.getCurrentSession();
        String sql = "SELECT\n" +
                "  u.id,\n" +
                "  IFNULL(u.username, \"暂无信息\") username,\n" +
                "  IFNULL(u.nick_name, \"暂无信息\") nick_name,\n" +
                "  u.head_img,\n" +
                "  u.profiles,\n" +
                "  u.company_office,\n" +
                "  u.party_posts,\n" +
                "  if(u.sex='1','男','女')sex,\n" +
                "  u.age,\n" +
                "  (case u.education\n" +
                "   when 1 then '初中'\n" +
                "   when 2 then '高中'\n" +
                "   when 3 then '中专'\n" +
                "   when 4 then '大专'\n" +
                "   when 5 then '本科'\n" +
                "   when 6 then '硕士'\n" +
                "   when 7 then '博士'\n" +
                "   else '暂无信息'\n" +
                "   end\n" +
                "  )education,\n" +
                "  u.company,\n" +
                "  u.phone,\n" +
                "  u.ID_cord,\n" +
                "  d.type_name ,\n" +
                "  (case u.difficulty_type\n" +
                "   when 0 then '非困难'\n" +
                "   when 1 then '困难'\n" +
                "   when 2 then '非常困难'   else '暂无信息' end)difficulty_type,\n" +
                "  DATE_FORMAT(u.join_time, '%Y-%m-%d') join_time,\n" +
                "  DATE_FORMAT(u.create_time, '%Y-%m-%d') create_time,\n" +
                "  u.address,\n" +
                "  u.dutyid,\n" +
                "  u.party_branch_id,\n" +
                "  p.name party_branch_name,\n" +
                "  u.party_groups_id,\n" +
                "  u.party_team_id,\n" +
                "  (select g.name from party_group g where u.party_groups_id = g.id) as groupName,\n" +
                "  (select t.name from party_team t where u.party_groups_id = t.id) as teamName\n" +
                "FROM\n" +
                "  USER u\n" +
                "  LEFT JOIN duty d ON u.dutyid = d.id\n" +
                "  join party_branch p on u.party_branch_id = p.id\n" +
                "WHERE\n" +
                "  u.can_use = 1 and p.can_use = 1 and d.can_use =1";

        String sqlCount = "SELECT\n" +
                " count(u.id) count\n" +
                " FROM\n" +
                " USER u\n" +
                " LEFT JOIN duty d ON u.dutyid = d.id\n" +
                " join party_branch p on u.party_branch_id = p.id\n" +
                " WHERE\n" +
                " u.can_use = 1 and p.can_use = 1 and d.can_use =1";
        sql = CommonUtil.appendBlurStr(sql,blurParam);
        sql = CommonUtil.appendDateStr(sql,dateParam,"u");
        sql = CommonUtil.appendIntStr(sql,intParam,"u");
        sqlCount = CommonUtil.appendBlurStr(sqlCount,blurParam);
        sqlCount = CommonUtil.appendDateStr(sqlCount,dateParam,"u");
        sqlCount = CommonUtil.appendIntStr(sqlCount,intParam,"u");

        sql = sql +" ORDER BY u.create_time DESC";

        Map resMap = CommonUtil.queryList(session,sql,sqlCount,limit,page);
        return resMap;

    }


    @Override
    public Map selectAllByDifficultyType(Map blurParam,  Map intParam, List dutyIdArr, int limit, int page) throws Exception {
        session = sessionFactory.getCurrentSession();
        String sql = "SELECT\n" +
                "  u.id,\n" +
                "  IFNULL(u.username, \"暂无信息\") username,\n" +
                "  IFNULL(u.nick_name, \"暂无信息\") nick_name,\n" +
                "  u.head_img,\n" +
                "  u.profiles,\n" +
                "  u.company_office,\n" +
                "  u.party_posts,\n" +
                "  if(u.sex='1','男','女')sex,\n" +
                "  u.age,\n" +
                "  (case u.education\n" +
                "   when 1 then '初中'\n" +
                "   when 2 then '高中'\n" +
                "   when 3 then '中专'\n" +
                "   when 4 then '大专'\n" +
                "   when 5 then '本科'\n" +
                "   when 6 then '硕士'\n" +
                "   when 7 then '博士'\n" +
                "   else '暂无信息'\n" +
                "   end\n" +
                "  )education,\n" +
                "  u.company,\n" +
                "  u.phone,\n" +
                "  u.ID_cord,\n" +
                "  d.type_name ,\n" +
                "  (case u.difficulty_type\n" +
                "   when 0 then '非困难'\n" +
                "   when 1 then '困难'\n" +
                "   when 2 then '非常困难'   else '暂无信息' end)difficulty_type,\n" +
                "  DATE_FORMAT(u.join_time, '%Y-%m-%d') join_time,\n" +
                "  DATE_FORMAT(u.create_time, '%Y-%m-%d') create_time,\n" +
                "  u.address,\n" +
                "  u.dutyid,\n" +
                "  u.party_branch_id,\n" +
                "  p.name party_branch_name,\n" +
                "  u.party_groups_id,\n" +
                "  u.party_team_id,\n" +
                "  (select g.name from party_group g where u.party_groups_id = g.id) as groupName,\n" +
                "  (select t.name from party_team t where u.party_groups_id = t.id) as teamName\n" +
                "FROM\n" +
                "  USER u\n" +
                "  LEFT JOIN duty d ON u.dutyid = d.id\n" +
                "  join party_branch p on u.party_branch_id = p.id\n" +
                "WHERE\n" +
                "  u.can_use = 1 and p.can_use = 1 and d.can_use =1";

        String sqlCount = "SELECT\n" +
                " count(u.id) count\n" +
                " FROM\n" +
                " USER u\n" +
                " LEFT JOIN duty d ON u.dutyid = d.id\n" +
                " join party_branch p on u.party_branch_id = p.id\n" +
                " WHERE\n" +
                " u.can_use = 1 and p.can_use = 1 and d.can_use =1";
        sql = CommonUtil.appendBlurStr(sql,blurParam);
        sql = CommonUtil.appendInSql2(sql,dutyIdArr,"u.dutyid");
        sql = CommonUtil.appendIntStr(sql,intParam,"u");
        sqlCount = CommonUtil.appendBlurStr(sqlCount,blurParam);
        sqlCount =  CommonUtil.appendInSql2(sqlCount,dutyIdArr,"u.dutyid");
        sqlCount = CommonUtil.appendIntStr(sqlCount,intParam,"u");

        sql = sql +" ORDER BY u.create_time DESC";

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
