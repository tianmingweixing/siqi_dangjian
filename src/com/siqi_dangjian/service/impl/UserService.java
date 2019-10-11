package com.siqi_dangjian.service.impl;

import com.siqi_dangjian.bean.Activities;
import com.siqi_dangjian.bean.User;
import com.siqi_dangjian.dao.IDutyDao;
import com.siqi_dangjian.dao.IUserDao;
import com.siqi_dangjian.service.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UserService implements IUserService {

    @Autowired
    IActivityService activityService;

    @Autowired
    private ITipsService tipsService;

    @Autowired
    private IUserDao userDao;

    @Autowired
    private IDutyDao dutyDao;

    @Autowired
    private IActivityOfUserService activityOfUserService;

    @Autowired
    private IDisciplineOfHonorService disciplineOfHonorService;


    @Override
    public User wxLogin(String openId) {
        return userDao.getUserByOpenId(openId);
    }

    @Override
    public Map getUserInfoById(Long id) throws Exception {
        Map userInfo = userDao.getUserInfoById(id);
        //查参加的活动次数和受奖励次数
        Integer joinNum = activityOfUserService.selectCountByUserId(id);
        Integer honorNum = disciplineOfHonorService.selectCountByUserIdAndType(id,0);
        userInfo.put("joinNum",joinNum);
        userInfo.put("honorNum",honorNum);
        return userInfo;
    }

    @Override
    public Map getUserTipsById(Long id, Integer type, Integer limit, Integer page) throws Exception {
        //查用户参加的活动的心得
        Map mapList = tipsService.selectActivityTips( id, type, limit, page);
        return mapList;
    }

    @Override
    public Map getUserByType(Integer type) {
        return null;
    }

    @Override
    public void addUser(User user) throws Exception {
        userDao.insertOrUpdate(user);
    }

    @Override
    public void saveOrUpDate(User user1) throws Exception {
        userDao.insertOrUpdate(user1);
    }

    @Override
    public User getUserById(Long id) throws Exception {
       User user = userDao.selectById(id);
        return user;
    }

    @Override
    public Map getUserList(Map blurMap, Map intMap, Map dateMap, Integer limit, Integer page) throws Exception {
        Map map =  userDao.selectAll(blurMap,intMap,dateMap,limit, page);
        return map;
    }

    @Override
    public void deleteUser(List idList) throws Exception {
        userDao.delete(idList);
    }

    @Override
    public void logicDeleteUser(List idList) throws Exception {
        userDao.logicDelete(idList);
    }

    @Override
    public Map selectGroupCount() throws Exception {
        Map userCountMap = userDao.selectGroupCount();
        Map blurParam = new HashMap<>();
        blurParam.put("type_name", "正式党员");
        Long dutyId = dutyDao.selectByTypeName(blurParam);
        Integer difficultToPartyMembersCount = userDao.selectDifficultToPartyMembersCount(dutyId);
        Integer needyWorkerCount = userDao.selectNeedyWorkerCount(dutyId);
        userCountMap.put("difficultToPartyMembersCount",difficultToPartyMembersCount);
        userCountMap.put("needyWorkerCount",needyWorkerCount);
        return userCountMap;
    }



    @Override
    public Integer selectUserCountByTypeOrTeam(String coum, Long parem) throws Exception {
        return userDao.selectUserCountByTypeOrTeam(coum,parem);
    }

}
