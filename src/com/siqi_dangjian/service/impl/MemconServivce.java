package com.siqi_dangjian.service.impl;

import com.siqi_dangjian.bean.Memcon;
import com.siqi_dangjian.dao.IMemconDao;
import com.siqi_dangjian.service.IMemconServivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class MemconServivce implements IMemconServivce {

    @Autowired
    private IMemconDao memconDao;

    @Override
    public Map getMemconByType(Integer type) throws Exception {
        return null;
    }

    @Override
    public Map getMemconById(Long id) throws Exception {
        return null;
    }

    @Override
    public void insertOrUpdate(Memcon Memcon) throws Exception {
        memconDao.insertOrUpdate(Memcon);
    }

    @Override
    public void logicDelete(List idList) throws Exception {
        memconDao.logicDelete(idList);
    }

    @Override
    public void delete(List idList) throws Exception {
        memconDao.delete(idList);
    }

    @Override
    public Memcon selectById(Long id) throws Exception {
        Memcon memcon = memconDao.selectById(id);
        return memcon;
    }

    @Override
    public Map selectAll(Map blurMap, Map intMap, Map dateMap, Integer limit, Integer page) throws Exception {
        Map map =  memconDao.selectAll(blurMap,intMap,dateMap,limit, page);
        return map;
    }
}
