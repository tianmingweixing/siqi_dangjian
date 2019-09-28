package com.siqi_dangjian.service.impl;

import com.siqi_dangjian.bean.Activities;
import com.siqi_dangjian.bean.PartyBranch;
import com.siqi_dangjian.dao.IActivityDao;
import com.siqi_dangjian.service.IActivityOfUserService;
import com.siqi_dangjian.service.IActivityService;
import com.siqi_dangjian.service.IConfigurationService;
import com.siqi_dangjian.service.IPartyBranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ActivityService implements IActivityService {

    @Autowired
    private IActivityDao activityDao;

    @Autowired
    private IConfigurationService configurationService;

    @Autowired
    private IPartyBranchService partyBranchService;

    @Autowired
    private IActivityOfUserService activityOfUserService;


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
        Activities activities = activityDao.selectById(id);
        return activities;
    }

    /**
     * 根据id查询活动
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public Map selectActivitiesInfoById(Long id) throws Exception {
        Map data = new HashMap();
        Map intParme = new HashMap();
        Activities activities = activityDao.selectById(id);
        //Configuration config = configurationService.selectById(1L);
        PartyBranch partyBranch = partyBranchService.selectById(activities.getBrandId());
        intParme.put("activity_id",activities.getId().toString());
        Map userMap = activityOfUserService.selectAll(new HashMap(),intParme,new HashMap(),5,1);
        data.put("activities",activities);
        if (partyBranch != null) {
            data.put("partuName", partyBranch.getName());
            data.put("partuImg", partyBranch.getPartyImg());
        }
        data.put("userMap",userMap);

        return data;
    }

    @Override
    public Integer selectActivityCountByType(Long type_id) throws Exception {
        return activityDao.selectActivityCountByType(type_id);
    }

    @Override
    public Map selectActivityGroupCount() {
        return activityDao.selectActivityGroupCount();
    }

    @Override
    public Map selectActivitysByState(int state, int limit, int page) throws Exception {
        return activityDao.selectActivitysByState(state, limit, page);
    }

    @Override
    public String selectSignInById(Long id) throws Exception {
        return activityDao.selectSignInById(id);
    }

    @Override
    public Map selectActivityStatusGroupCount() {
         Map map = activityDao.selectActivityStatusGroupCount();
        List countList = (List) map.get("countList");

        //状态转换为
        for (int i = 0; i < countList.size(); i++) {
            Map statusMap = (Map) countList.get(i);
            if(statusMap.get("activityStatus").equals(0)){
                statusMap.put("activityStatus","筹备中");
            }else if(statusMap.get("activityStatus").equals(1)){
                statusMap.put("activityStatus","进行中");
            }else if(statusMap.get("activityStatus").equals(2)){
                statusMap.put("activityStatus","已结束");
            }
        }
        return  map;
    }

}
