package com.siqi_dangjian.service.impl;

import com.siqi_dangjian.bean.PartyBranch;
import com.siqi_dangjian.dao.IPartyBranchDao;
import com.siqi_dangjian.service.IPartyBranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class PartyBranchService implements IPartyBranchService {

    @Autowired
    private IPartyBranchDao partyBranchDao;

    @Override
    public Map getUserNameByType(Integer type) throws Exception {
        return null;
    }

    @Override
    public Map getUserNameById(Long id) throws Exception {
        return null;
    }

    @Override
    public void insertOrUpdate(PartyBranch partyBranch) throws Exception {
        partyBranchDao.insertOrUpdate(partyBranch);
    }

    @Override
    public void logicDelete(List idList) throws Exception {
        partyBranchDao.logicDelete(idList);
    }

    @Override
    public void delete(List idList) throws Exception {
        partyBranchDao.delete(idList);
    }

    @Override
    public PartyBranch selectById(Long id) throws Exception {
        PartyBranch partyBranch = partyBranchDao.selectById(id);
        return partyBranch;
    }

    @Override
    public Map selectAll(Map blurMap, Map intMap, Map dateMap, Integer limit, Integer page) throws Exception {
        Map map =  partyBranchDao.selectAll(blurMap,intMap,dateMap,limit, page);
        return map;
    }
}
