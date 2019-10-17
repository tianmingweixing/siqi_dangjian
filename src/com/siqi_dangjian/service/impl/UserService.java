package com.siqi_dangjian.service.impl;

import com.siqi_dangjian.bean.User;
import com.siqi_dangjian.dao.IDutyDao;
import com.siqi_dangjian.dao.IUserDao;
import com.siqi_dangjian.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    public Map getUserListByDifficultyType(Map blurMap, Map intMap, List dutyIdArr, Integer limit, Integer page) throws Exception {
        return userDao.selectAllByDifficultyType(blurMap,intMap,dutyIdArr,limit,page);
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
    public List selectGroupCount() throws Exception {
        List resList = new ArrayList();

        Map userCountMap = userDao.selectGroupCount();
        List<Map> list = (List)userCountMap.get("userTypeCount");
        for (Map item : list){
            resList.add(item);
        }

        Map itemMap = new HashMap<>();
        Map itemMap2 = new HashMap<>();
        Map itemMap3 = new HashMap<>();
        Map blurParam = new HashMap<>();
        blurParam.put("type_name", "正式党员");//此type_name是DataBase字段值不可修改
        Long dutyId = dutyDao.selectByTypeName(blurParam);
        Integer difficultToPartyMembersCount = userDao.selectDifficultToPartyMembersCount(dutyId);//困难党员
        Integer needyWorkerCount = userDao.selectNeedyWorkerCount(dutyId);
        Integer partyGroupCount = userDao.selectUserCountOfGroup();

        itemMap.put("typeName","困难党员");
        itemMap.put("count",difficultToPartyMembersCount);
        itemMap.put("typeId",0);
        resList.add(itemMap);

        itemMap2.put("typeName","困难职工");
        itemMap2.put("count",needyWorkerCount);
        itemMap2.put("typeId",0);
        resList.add(itemMap2);

        itemMap3.put("typeName","班子成员");
        itemMap3.put("count",partyGroupCount);
        itemMap3.put("typeId",0);
        resList.add(itemMap3);

        return resList;
    }



    @Override
    public Integer selectUserCountByTypeOrTeam(String coum, Long parem) throws Exception {
        return userDao.selectUserCountByTypeOrTeam(coum,parem);
    }

}
