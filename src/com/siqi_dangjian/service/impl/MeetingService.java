package com.siqi_dangjian.service.impl;

import com.siqi_dangjian.bean.Meeting;
import com.siqi_dangjian.bean.MeetingOfUser;
import com.siqi_dangjian.dao.IMeetingDao;
import com.siqi_dangjian.service.IMeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class MeetingService implements IMeetingService {

    @Autowired
    private IMeetingDao meetingDao;

    @Override
    public Map getUserNameByType(Integer type) throws Exception {
        return null;
    }

    @Override
    public Map getUserNameById(Long id) throws Exception {
        return null;
    }

    @Override
    public void insertOrUpdate(Meeting meeting) throws Exception {
        meetingDao.insertOrUpdate(meeting);
    }

    @Override
    public void logicDelete(List idList) throws Exception {
        meetingDao.logicDelete(idList);
    }

    @Override
    public void delete(List idList) throws Exception {
        meetingDao.delete(idList);
    }

    @Override
    public Meeting selectById(Long id) throws Exception {
        Meeting meeting = (Meeting) meetingDao.selectById(id);
        return meeting;

    }

    @Override
    public Map selectAll(Map blurMap, Map intMap, Map dateMap, Integer limit, Integer page) throws Exception {
        Map map =  meetingDao.selectAll(blurMap,intMap,dateMap,limit, page);
        return map;
    }

    @Override
    public Integer selectMeetingCountByType(String coum, Long parem) throws Exception {
        return meetingDao.selectMeetingCountByType(coum, parem);
    }

    @Override
    public void insertOrUpdate(MeetingOfUser meetingOfUser) {
        meetingDao.insertOrUpdate(meetingOfUser);
    }

    @Override
    public String selectSignInById(Long id) throws Exception {
        return meetingDao.selectSignInById(id);
    }

    @Override
    public Meeting selectById(String id) throws Exception {
        return meetingDao.selectById(Long.valueOf(id));
    }
}
