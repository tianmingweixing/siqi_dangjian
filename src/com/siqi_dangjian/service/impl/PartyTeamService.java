package com.siqi_dangjian.service.impl;

import com.siqi_dangjian.bean.PartyTeam;
import com.siqi_dangjian.dao.IPartyTeamDao;
import com.siqi_dangjian.service.IPartyTeamService;
import com.siqi_dangjian.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class PartyTeamService implements IPartyTeamService {

    @Autowired
    private IPartyTeamDao partyTeamDao;

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
    public void insertOrUpdate(PartyTeam partyTeam) throws Exception {
        partyTeamDao.insertOrUpdate(partyTeam);
    }

    @Override
    public void logicDelete(List idList) throws Exception {
        partyTeamDao.logicDelete(idList);
    }

    @Override
    public void delete(List idList) throws Exception {
        partyTeamDao.delete(idList);
    }

    @Override
    public PartyTeam selectById(Long id) throws Exception {
        PartyTeam partyTeam = partyTeamDao.selectById(id);
        return partyTeam;
    }

    @Override
    public Map selectAll(Map blurMap, Map intMap, Map dateMap, Integer limit, Integer page) throws Exception {
        Map map =  partyTeamDao.selectAll(blurMap,intMap,dateMap,limit, page);
        //查询班子成员信息
        if (map != null) {
            List<Map> teams = (List) map.get("list");
            for (Map item : teams) {
                BigInteger teamId = (BigInteger) item.get("id");
                Integer userCount = userService.selectUserCountByTypeOrTeam("party_team_id", teamId.longValue());
                item.put("userCount", userCount);
            }
        }
        return map;
    }

    @Override
    public List<PartyTeam> selectList() throws Exception {
        return partyTeamDao.selectList();
    }

    @Override
    public Map selectAllCategory() throws Exception {
        Map map =  partyTeamDao.selectAllCategory();
        return map;
    }
}
