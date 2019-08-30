package com.siqi_dangjian.dao.impl;


import com.siqi_dangjian.bean.Activities;
import com.siqi_dangjian.bean.ActivitiesType;
import com.siqi_dangjian.dao.IActivityTypeDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ActivityTypeDao extends BaseDao<ActivitiesType> implements IActivityTypeDao {


    @Override
    public void insertOrUpdate(ActivitiesType activitiesType) throws Exception {
        saveOrUpdateObject(activitiesType);
    }

    @Override
    public void logicDelete(List idList) throws Exception {
        logicDelete(idList);
    }

    @Override
    public void delete(List idList) throws Exception {
        delete(idList);
    }

    @Override
    public ActivitiesType selectById(Long id) throws Exception {
        return getObjectById(id);
    }

    @Override
    public Map selectAll(int limit, int page) throws Exception {
        return selectAll(limit,page);
    }
}
