package com.siqi_dangjian.dao;

import com.siqi_dangjian.bean.PartyBranch;
import com.siqi_dangjian.bean.User;
import java.util.List;
import java.util.Map;

public interface IPartyBranchDao {
    void insertOrUpdate(PartyBranch partyBranch) throws Exception;

    void logicDelete(List idList) throws Exception;

    void delete(List idList) throws Exception;

    PartyBranch selectById(Long id) throws Exception;

    Map selectAll(Map blurParam,Map intParam, Map dateParam,int limit, int page) throws Exception;
}
