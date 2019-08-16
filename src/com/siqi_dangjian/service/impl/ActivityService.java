package com.siqi_dangjian.service.impl;

import com.siqi_dangjian.bean.Activities;
import com.siqi_dangjian.dao.IActivityDao;
import com.siqi_dangjian.service.IActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ActivityService implements IActivityService {

    @Autowired
    private IActivityDao activityDao;


    /**
     * 添加或更新活动
     * @param activities
     * @throws Exception
     */
    @Override
    public void addActivitiesContent(Activities activities) throws Exception {
        activityDao.insertOrUpdate(activities);
    }

    @Override
    public Map getActivityContentById(Long id) {
        Map map = activityDao.selectActivityContentById(id);
        return map;
    }

    @Override
    public Map getActivityContentByType(Integer type) {
        Map map = activityDao.selectActivityContentByType(type);
        return map;
    }

    /**
     * 查询活动列表
     * @param blurMap
     * @param intMap
     * @param dateMap
     * @param limit
     * @param page
     * @return
     * @throws Exception
     */
    @Override
    public Map getActivityContentList(Map blurMap, Map intMap, Map dateMap,Integer limit,Integer page) throws Exception {
        Map map = activityDao.selectAll(blurMap,intMap,dateMap,limit, page);
        return map;
    }

    /**
     * 删除活动
     * @param idList
     * @throws Exception
     */
    @Override
    public  void deleteActivity(List idList) throws Exception {
        activityDao.delete(idList);
    }

    /**
     * 逻辑删除活动
     * @param idList
     * @throws Exception
     */
    public void logicDeleteActivity(List idList) throws Exception {
        activityDao.logicDelete(idList);
    }

    /**
     * 根据id查询活动
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public Activities getActivityById(Long id) throws Exception {
        return activityDao.selectById(id);
    }

}
