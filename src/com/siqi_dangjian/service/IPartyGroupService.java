package com.siqi_dangjian.service;


import com.siqi_dangjian.bean.PartyGroup;
import java.util.List;
import java.util.Map;

public interface IPartyGroupService {

    Map getUserNameByType(Integer type) throws Exception;

    Map getUserNameById(Long id) throws Exception;

    void insertOrUpdate(PartyGroup partyGroup) throws Exception;

    void logicDelete(List idList) throws Exception;

    void delete(List idList) throws Exception;

    PartyGroup selectById(Long id) throws Exception;

    Map selectAll(Map blurMap, Map intMap, Map dateMap, Integer limit, Integer page) throws Exception;
}
