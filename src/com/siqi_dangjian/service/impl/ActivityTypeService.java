package com.siqi_dangjian.service.impl;

import com.siqi_dangjian.bean.ActivitiesType;
import com.siqi_dangjian.dao.impl.ActivityTypeDao;
import com.siqi_dangjian.service.IActivityTypeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class ActivityTypeService implements IActivityTypeService {

    @Autowired
    private ActivityTypeDao activityTypeDao;

    @Override
    public void insertOrUpdate(ActivitiesType activitiesType) throws Exception {
        activityTypeDao.insertOrUpdate(activitiesType);
    }

    @Override
    public void logicDelete(List idList) throws Exception {
        activityTypeDao.logicDelete(idList);
    }

    @Override
    public void delete(List idList) throws Exception {
        activityTypeDao.delete(idList);
    }

    @Override
    public ActivitiesType selectById(Long id) throws Exception {
        return activityTypeDao.selectById(id);
    }

    @Override
    public Map selectAll(int limit, int page) throws Exception {
        return activityTypeDao.selectAll(limit,page);
    }
}
