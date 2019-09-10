package com.siqi_dangjian.service.impl;

import com.siqi_dangjian.bean.MeetingType;
import com.siqi_dangjian.dao.IMeetingTypeDao;
import com.siqi_dangjian.service.IMeetingTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class MeetingTypeService implements IMeetingTypeService {

    @Autowired
    private IMeetingTypeDao meetingTypeDao;



    @Override
    public void insertOrUpdate(MeetingType conclusionType) throws Exception {
        meetingTypeDao.insertOrUpdate(conclusionType);

    }


    @Override
    public void logicDelete(List idList) throws Exception {
        meetingTypeDao.logicDelete(idList);
    }

    @Override
    public void delete(List idList) throws Exception {
        meetingTypeDao.delete(idList);
    }

    @Override
    public MeetingType selectById(Long id) throws Exception {
        MeetingType meetingType = meetingTypeDao.selectById(id);
        return meetingType;
    }

    @Override
    public Map selectAll(Map blurMap, Map intMap, Map dateMap, Integer limit, Integer page) throws Exception {
        Map map = meetingTypeDao.selectAll(blurMap,intMap,dateMap,limit, page);
        return map;
    }

    @Override
    public Map selectAllCategory(Map blurMap, Map intMap, Map dateMap) {
        Map map = meetingTypeDao.selectAllCategory(blurMap,intMap,dateMap);

        return map;
    }

    @Override
    public List<MeetingType> selectList() throws Exception {
        return meetingTypeDao.selectList();
    }
}
