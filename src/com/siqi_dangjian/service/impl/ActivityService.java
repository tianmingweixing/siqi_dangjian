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
    public void insertOrUpdate(Activities activities) throws Exception {
        activityDao.insertOrUpdate(activities);
    }
    /**
     * 逻辑删除活动
     * @param idList
     * @throws Exception
     */
    @Override
    public void logicDelete(List idList) throws Exception {
        activityDao.logicDelete(idList);
    }
    /**
     * 删除活动
     * @param idList
     * @throws Exception
     */
    @Override
    public void delete(List idList) throws Exception {
        activityDao.delete(idList);
    }

    @Override
    public Map selectAll(Map blurParam, Map intParam, Map dateParam, int limit, int page) throws Exception {
        return activityDao.selectAll(blurParam,intParam,dateParam,limit,page);
    }

    @Override
    public Map getActivityByType(Integer type) throws Exception {
        Map map = activityDao.selectActivityByType(type);
        return map;
    }

    /**
     * 根据id查询活动
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public Activities selectById(Long id) throws Exception {
        return activityDao.selectById(id);
    }

    @Override
    public Integer selectActivityCountByType(Long type_id) throws Exception {
        return activityDao.selectActivityCountByType(type_id);
    }

    @Override
    public Map selectActivityGroupCount() {
        return activityDao.selectActivityGroupCount();
    }

}
