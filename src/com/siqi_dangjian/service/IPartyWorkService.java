package com.siqi_dangjian.service;

import com.siqi_dangjian.bean.PartyWork;
import java.util.List;
import java.util.Map;

public interface IPartyWorkService {

    Map getUserNameByType(Integer type) throws Exception;

    Map getUserNameById(Long id) throws Exception;

    void insertOrUpdate(PartyWork partyWork) throws Exception;

    void logicDelete(List idList) throws Exception;

    void delete(List idList) throws Exception;

    PartyWork selectById(Long id) throws Exception;

    Map selectAll(Map blurMap, Map intMap, Map dateMap, Integer limit, Integer page) throws Exception;
}
