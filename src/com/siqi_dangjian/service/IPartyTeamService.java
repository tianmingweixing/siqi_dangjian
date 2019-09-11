package com.siqi_dangjian.service;

import com.siqi_dangjian.bean.PartyTeam;
import java.util.List;
import java.util.Map;

public interface IPartyTeamService {

    Map getUserNameByType(Integer type) throws Exception;

    Map getUserNameById(Long id) throws Exception;

    void insertOrUpdate(PartyTeam partyTeam) throws Exception;

    void logicDelete(List idList) throws Exception;

    void delete(List idList) throws Exception;

    PartyTeam selectById(Long id) throws Exception;

    Map selectAll(Map blurMap, Map intMap, Map dateMap, Integer limit, Integer page) throws Exception;

    List<PartyTeam> selectList() throws Exception;

    Map selectAllCategory() throws Exception;
}
