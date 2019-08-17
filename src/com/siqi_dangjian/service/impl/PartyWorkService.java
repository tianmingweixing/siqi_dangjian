package com.siqi_dangjian.service.impl;

import com.siqi_dangjian.bean.PartyWork;
import com.siqi_dangjian.dao.IPartyWorkDao;
import com.siqi_dangjian.service.IPartyWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class PartyWorkService implements IPartyWorkService {

    @Autowired
    private IPartyWorkDao partyWorkDao;

    @Override
    public Map getUserNameByType(Integer type) throws Exception {
        return null;
    }

    @Override
    public Map getUserNameById(Long id) throws Exception {
        return null;
    }

    @Override
    public void insertOrUpdate(PartyWork partyWork) throws Exception {
        partyWorkDao.insertOrUpdate(partyWork);
    }

    @Override
    public void logicDelete(List idList) throws Exception {
        partyWorkDao.logicDelete(idList);
    }

    @Override
    public void delete(List idList) throws Exception {
        partyWorkDao.delete(idList);
    }

    @Override
    public PartyWork selectById(Long id) throws Exception {
        PartyWork partyWork = partyWorkDao.selectById(id);
        return partyWork;
    }

    @Override
    public Map selectAll(Map blurMap, Map intMap, Map dateMap, Integer limit, Integer page) throws Exception {
        Map map =  partyWorkDao.selectAll(blurMap,intMap,dateMap,limit, page);
        return map;
    }
}
