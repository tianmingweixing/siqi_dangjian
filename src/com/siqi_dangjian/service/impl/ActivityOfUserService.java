package com.siqi_dangjian.service.impl;

import com.siqi_dangjian.bean.ActivityOfUser;
import com.siqi_dangjian.dao.IActivityOfUserDao;
import com.siqi_dangjian.service.IActivityOfUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ActivityOfUserService implements IActivityOfUserService {

    @Autowired
    private IActivityOfUserDao activityOfUserDao;

    @Override
    public Map getUserNameByType(Integer type) throws Exception {
        return null;
    }

    @Override
    public Map getUserNameById(Long id) throws Exception {
        Map map = activityOfUserDao.getUserNameById(id);
        return map;
    }

    @Override
    public void insertOrUpdate(ActivityOfUser activityOfUser) throws Exception {
        activityOfUserDao.insertOrUpdate(activityOfUser);
    }

    @Override
    public void logicDelete(List idList) throws Exception {
        activityOfUserDao.logicDelete(idList);
    }

    @Override
    public void delete(List idList) throws Exception {
        activityOfUserDao.delete(idList);
    }

    @Override
    public ActivityOfUser selectById(Long id) throws Exception {
        ActivityOfUser activityOfUser = activityOfUserDao.selectById(id);
        return activityOfUser;
    }

    @Override
    public Map selectAll(Map blurMap, Map intMap, Map dateMap, Integer limit, Integer page) throws Exception {
        Map map =  activityOfUserDao.selectAll(blurMap,intMap,dateMap,limit, page);
        return map;
    }

    @Override
    public List selectListById(Long user_id) throws Exception {
        return activityOfUserDao.selectListById(user_id);
    }

    @Override
    public void cancelSignIn(Long user_id, String activity_id) throws Exception {
        activityOfUserDao.cancelSignIn(user_id,activity_id);
    }

    @Override
    public Integer selectCountByUserId(Long UserId) throws Exception {
        return activityOfUserDao.selectCountByUserId(UserId);
    }
}
