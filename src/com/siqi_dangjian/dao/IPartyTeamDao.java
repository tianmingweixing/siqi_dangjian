package com.siqi_dangjian.dao;

import com.siqi_dangjian.bean.PartyTeam;
import com.siqi_dangjian.bean.User;
import java.util.List;
import java.util.Map;

public interface IPartyTeamDao {
    void insertOrUpdate(PartyTeam partyTeam) throws Exception;

    void logicDelete(List idList) throws Exception;

    PartyTeam selectById(Long id) throws Exception;

    void delete(List idList) throws Exception;

    Map selectAll(Map blurParam,Map intParam, Map dateParam,
                   int limit, int page) throws Exception;

    List<PartyTeam> selectList() throws Exception;
}
