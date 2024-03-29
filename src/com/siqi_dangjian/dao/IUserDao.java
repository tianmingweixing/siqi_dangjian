package com.siqi_dangjian.dao;

import com.siqi_dangjian.bean.User;
import java.util.List;
import java.util.Map;

public interface IUserDao {

    User getUserByOpenId(String openId);

    Map getUserByType(Integer type) throws Exception;

    Map getUserInfoById(Long id) throws Exception;

    void insertOrUpdate(User user) throws Exception;

    void logicDelete(List idList) throws Exception;

    void delete(List idList) throws Exception;

    User selectById(Long id) throws Exception;

    Map selectGroupCount() throws Exception;

    Integer selectUserCountOfGroup() throws Exception;

    Map selectAll(Map blurParam, Map dateParam,Map intParam, int limit, int page) throws Exception;

    Map selectAllByDifficultyType(Map blurParam,  Map intParam, List dutyIdArr, int limit, int page) throws Exception;

    Integer selectDifficultToPartyMembersCount(Long dutyid) throws Exception;

    Integer selectNeedyWorkerCount(Long dutyid) throws Exception;

    Integer selectUserCountByTypeOrTeam(String coum, Long parem) throws Exception;

}
