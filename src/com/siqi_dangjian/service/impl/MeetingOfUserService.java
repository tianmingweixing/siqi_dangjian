package com.siqi_dangjian.service.impl;

import com.siqi_dangjian.bean.MeetingOfUser;
import com.siqi_dangjian.dao.IMeetingOfUserDao;
import com.siqi_dangjian.service.IMeetingOfUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class MeetingOfUserService implements IMeetingOfUserService {


    @Autowired
    private IMeetingOfUserDao MeetingOfUserDao;

    @Override
    public Map getUserNameByType(Integer type) throws Exception {
        return null;
    }

    @Override
    public Map getUserNameById(Long id) throws Exception {
        return null;
    }


    @Override
    public void insertOrUpdate(MeetingOfUser meetingOfUser) throws Exception {
        MeetingOfUserDao.insertOrUpdate(meetingOfUser);
    }

    @Override
    public void logicDelete(List idList) throws Exception {
        MeetingOfUserDao.logicDelete(idList);
    }

    @Override
    public void delete(List idList) throws Exception {
        MeetingOfUserDao.delete(idList);
    }

    @Override
    public MeetingOfUser selectById(Long id) throws Exception {
        MeetingOfUser meetingOfUser = MeetingOfUserDao.selectById(id);
        return meetingOfUser;
    }


    @Override
    public Map selectAll(Map blurMap, Map intMap, Map dateMap, Integer limit, Integer page) throws Exception {
        Map map =  MeetingOfUserDao.selectAll(blurMap,intMap,dateMap,limit, page);
        return map;
    }

    @Override
    public List selectListById(Long user_id) {
        return MeetingOfUserDao.selectListById(user_id);
    }
}
