package com.siqi_dangjian.service.impl;

import com.siqi_dangjian.bean.Duty;
import com.siqi_dangjian.dao.IDutyDao;
import com.siqi_dangjian.service.IDutyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class DutyService implements IDutyService {


    @Autowired
    private IDutyDao dutyDao;

    @Override
    public Map getUserNameByType(Integer type) throws Exception {
        return null;
    }

    @Override
    public Map getUserNameById(Long id) throws Exception {
        return null;
    }

    @Override
    public BigInteger insertOrUpdate(Duty duty) throws Exception {
        BigInteger lastId = dutyDao.insertOrUpdate(duty);
        return lastId;
    }

    @Override
    public void logicDelete(List idList) throws Exception {
        dutyDao.logicDelete(idList);
    }

    @Override
    public void delete(List idList) throws Exception {
        dutyDao.delete(idList);
    }

    @Override
    public Duty selectById(Long id) throws Exception {
        Duty duty = dutyDao.selectById(id);
        return duty;
    }

    @Override
    public Map selectAll(Map blurMap, Map intMap, Map dateMap, Integer limit, Integer page) throws Exception {
        Map map =  dutyDao.selectAll(blurMap,intMap,dateMap,limit, page);
        return map;
    }
}
