package com.siqi_dangjian.dao;

import com.siqi_dangjian.bean.PartyWork;
import com.siqi_dangjian.bean.User;
import java.util.List;
import java.util.Map;

public interface IPartyWorkDao {
    void insertOrUpdate(PartyWork partyWork) throws Exception;

    void logicDelete(List idList) throws Exception;

    void delete(List idList) throws Exception;

    PartyWork selectById(Long id) throws Exception;

    Map selectAll(Map blurParam, Map dateParam,
                  Map intParam, int limit, int page) throws Exception;
}
