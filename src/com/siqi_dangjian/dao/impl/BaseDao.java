package com.siqi_dangjian.dao.impl;

import com.siqi_dangjian.bean.BaseBean;
import com.siqi_dangjian.util.CommonUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.math.BigInteger;
import java.util.ArrayList;

import static com.siqi_dangjian.util.CommonUtil.getNowTimeStamp;


public class BaseDao<T extends BaseBean> {


    @Autowired
    public SessionFactory sessionFactory;

    public Session session;

    /**
     * 通过注解获取对象
     * @param id
     * @return
     */
    public T getObjectById(long id){
        session = sessionFactory.getCurrentSession();
        T object= (T) session.get((Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0],id);
        return object;
    }

    /**
     * 保存或更新对象
     * @param t
     */
    public void saveOrUpdateObject(T t){
        session = sessionFactory.getCurrentSession();
        if(t.getId()==null || t.getId() == 0) {
            t.setCreateTime(getNowTimeStamp());
        } else {
            t.setUpdateTime(getNowTimeStamp());
        }
        session.saveOrUpdate(t);

    }

    /**
     * 逻辑删除
     * @param t
     * @throws Exception
     */
    public void logic_delete(T t) throws Exception {
        session = sessionFactory.getCurrentSession();
        if(t.canUse==0){
            throw new Exception("该数据以删除，不能重复删除");
        } else {
            t.setCanUse(0);
            t.setDeleteTime(getNowTimeStamp());
        }
        session.saveOrUpdate(t);
    }

    public ArrayList<T> getAll(){
        session = sessionFactory.getCurrentSession();
        Class <T> entityClass = (Class <T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        String className = CommonUtil.subStrClassName(entityClass.getName());
        String hql = " from "+className+" where canUse = 1";
        Query query = session.createQuery(hql);
        return (ArrayList<T>) query.list();
    }

    public Integer getCount(String table_name){
        session = sessionFactory.getCurrentSession();
        String sql = "select COUNT(*) from "+table_name+" where can_use = 1";
        Query query = session.createSQLQuery(sql);
        BigInteger res = (BigInteger) query.uniqueResult();
        return res.intValue();
    }

    public void delete(T t){
        session = sessionFactory.getCurrentSession();
        session.delete(t);
    }
}
