package com.siqi_dangjian.dao;

import com.siqi_dangjian.bean.PartyGroup;
import java.util.List;
import java.util.Map;

public interface IPartyGroupDao {
    void insertOrUpdate(PartyGroup partyGroup) throws Exception;

    void logicDelete(List idList) throws Exception;

    void delete(List idList) throws Exception;

    PartyGroup selectById(Long id) throws Exception;

    Map selectAll(Map blurParam,Map intParam, Map dateParam,
                   int limit, int page) throws Exception;

    Map selectAllCategory();
}
