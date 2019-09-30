package com.siqi_dangjian.service.impl;

import com.siqi_dangjian.bean.PartyGroup;
import com.siqi_dangjian.dao.IPartyGroupDao;
import com.siqi_dangjian.service.IPartyGroupService;
import com.siqi_dangjian.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class PartyGroupService implements IPartyGroupService {

    @Autowired
    private IPartyGroupDao partyGroupDao;

    @Autowired
    private IUserService userService;

    @Override
    public Map getUserNameByType(Integer type) throws Exception {
        return null;
    }

    @Override
    public Map getUserNameById(Long id) throws Exception {
        return null;
    }

    @Override
    public void insertOrUpdate(PartyGroup partyGroup) throws Exception {
        partyGroupDao.insertOrUpdate(partyGroup);
    }

    @Override
    public void logicDelete(List idList) throws Exception {
        partyGroupDao.logicDelete(idList);
    }

    @Override
    public void delete(List idList) throws Exception {
        partyGroupDao.delete(idList);
    }

    @Override
    public PartyGroup selectById(Long id) throws Exception {
        PartyGroup partyGroup = partyGroupDao.selectById(id);
        return partyGroup;
    }

    @Override
    public Map selectAll(Map blurMap, Map intMap, Map dateMap, Integer limit, Integer page) throws Exception {
        Map map =  partyGroupDao.selectAll(blurMap,intMap,dateMap,limit, page);
        //查询班子成员信息
        if (map != null) {
            List<Map> group = (List) map.get("list");
            for (Map item : group) {
                BigInteger groupId = (BigInteger) item.get("id");
                Integer userCount = userService.selectUserCountByTypeOrTeam("party_groups_id", groupId.longValue());
                item.put("userCount", userCount);
            }
        }

        return map;
    }

    @Override
    public Map selectAllCategory() {
        Map map =  partyGroupDao.selectAllCategory();
        return map;
    }
}
