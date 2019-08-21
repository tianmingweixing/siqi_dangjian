package com.siqi_dangjian.service.impl;

import com.siqi_dangjian.bean.Activities;
import com.siqi_dangjian.bean.User;
import com.siqi_dangjian.dao.IUserDao;
import com.siqi_dangjian.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UserService implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Override
    public Map getUserInfoById(Long id) {
        return null;
    }

    @Override
    public Map getUserByType(Integer type) {
        return null;
    }

    @Override
    public void addUser(User user) throws Exception {
        userDao.insertOrUpdate(user);
    }

    @Override
    public User getUserById(Long id) throws Exception {
       User user = userDao.selectById(id);
        return user;
    }

    @Override
    public Map getUserList(Map blurMap, Map intMap, Map dateMap, Integer limit, Integer page) throws Exception {
        Map map =  userDao.selectAll(blurMap,intMap,dateMap,limit, page);
        return map;
    }

    @Override
    public void deleteUser(List idList) throws Exception {
        userDao.delete(idList);
    }

    @Override
    public void logicDeleteUser(List idList) throws Exception {
        userDao.logicDelete(idList);
    }
}