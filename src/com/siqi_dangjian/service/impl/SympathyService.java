package com.siqi_dangjian.service.impl;

import com.siqi_dangjian.bean.Sympathy;
import com.siqi_dangjian.dao.ISympathyDao;
import com.siqi_dangjian.service.ISympathyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class SympathyService implements ISympathyService {

    @Autowired
    private ISympathyDao SympathyDao;

    @Override
    public Map getUserNameByType(Integer type) throws Exception {
        return null;
    }

    @Override
    public Map getUserNameById(Long id) throws Exception {
        return null;
    }

    @Override
    public void insertOrUpdate(Sympathy sympathy) throws Exception {
        SympathyDao.insertOrUpdate(sympathy);
    }

    @Override
    public void logicDelete(List idList) throws Exception {
        SympathyDao.logicDelete(idList);
    }

    @Override
    public void delete(List idList) throws Exception {
        SympathyDao.delete(idList);
    }

    @Override
    public Sympathy selectById(Long id) throws Exception {
        Sympathy sympathy =  SympathyDao.selectById(id);
        return sympathy;
    }

    @Override
    public Map selectAll(Map blurMap, Map intMap, Map dateMap, Integer limit, Integer page) throws Exception {
        Map map =  SympathyDao.selectAll(blurMap,intMap,dateMap,limit, page);
        return map;
    }
}
