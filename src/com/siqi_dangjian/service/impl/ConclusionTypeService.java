package com.siqi_dangjian.service.impl;

import com.siqi_dangjian.bean.ConclusionType;
import com.siqi_dangjian.dao.IConclusionTypeDao;
import com.siqi_dangjian.service.IConclusionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ConclusionTypeService implements IConclusionTypeService {

    @Autowired
    private IConclusionTypeDao ConclusionTypeDao;

    @Override
    public Map getUserByType(Integer type) throws Exception {
        return null;
    }

    @Override
    public Map getUserInfoById(Long id) throws Exception {
        return null;
    }

    @Override
    public void insertOrUpdate(ConclusionType conclusionType) throws Exception {
        ConclusionTypeDao.insertOrUpdate(conclusionType);

    }


    @Override
    public void logicDelete(List idList) throws Exception {
        ConclusionTypeDao.logicDelete(idList);
    }

    @Override
    public void delete(List idList) throws Exception {
        ConclusionTypeDao.delete(idList);
    }

    @Override
    public ConclusionType selectById(Long id) throws Exception {
        ConclusionType conclusionType = ConclusionTypeDao.selectById(id);
        return conclusionType;
    }

    @Override
    public Map selectAll(Map blurMap, Map intMap, Map dateMap, Integer limit, Integer page) throws Exception {
        Map map = ConclusionTypeDao.selectAll(blurMap,intMap,dateMap,limit, page);
        return map;
    }
}
