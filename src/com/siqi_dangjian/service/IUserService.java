package com.siqi_dangjian.service;

import com.siqi_dangjian.bean.User;
import java.util.List;
import java.util.Map;

public interface IUserService {


    Map getUserInfoById(Long id) throws Exception;

    Map getUserTipsById(Long id, Integer limit, Integer type, Integer page) throws Exception;

    Map getUserByType(Integer type);

    User getUserById(Long id) throws Exception;

    Map getUserList(Map blurMap, Map intMap, Map dateMap,Integer limit,Integer page) throws Exception;

    Map getUserListByDifficultyType(Map blurMap, Map intMap, List dutyIdArr,Integer limit,Integer page) throws Exception;

    void addUser(User user) throws Exception;

    void deleteUser(List idList) throws Exception;

    void logicDeleteUser(List idList) throws Exception;

    List selectGroupCount() throws Exception;

    void saveOrUpDate(User user1) throws Exception;

    Integer selectUserCountByTypeOrTeam(String coum, Long parem) throws Exception;

    User wxLogin(String openId);

}
