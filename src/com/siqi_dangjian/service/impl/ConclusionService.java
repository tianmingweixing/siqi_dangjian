package com.siqi_dangjian.service.impl;

import com.siqi_dangjian.bean.Conclusion;
import com.siqi_dangjian.dao.IConclusionDao;
import com.siqi_dangjian.service.IConclusionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ConclusionService implements IConclusionService {

    @Autowired
    private IConclusionDao ConclusionDao;

    @Override
    public Map getUserByType(Integer type) throws Exception {
        return null;
    }

    @Override
    public Map getUserInfoById(Long id) throws Exception {
        return null;
    }

    @Override
    public void insertOrUpdate(Conclusion conclusion) throws Exception {
        ConclusionDao.insertOrUpdate(conclusion);
    }

    @Override
    public void logicDelete(List idList) throws Exception {
        ConclusionDao.logicDelete(idList);
    }

    @Override
    public void delete(List idList) throws Exception {
        ConclusionDao.delete(idList);
    }

    @Override
    public Conclusion selectById(Long id) throws Exception {
        Conclusion conclusion = ConclusionDao.selectById(id);
        return conclusion;
    }

    @Override
    public Map selectAll(Map blurMap, Map intMap, Map dateMap, Integer limit, Integer page) throws Exception {
        Map map = ConclusionDao.selectAll(blurMap,intMap,dateMap,limit, page);
        return map;
    }
}
