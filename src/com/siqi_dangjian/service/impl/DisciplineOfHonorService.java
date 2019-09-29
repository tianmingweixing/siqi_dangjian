package com.siqi_dangjian.service.impl;

import com.siqi_dangjian.bean.DisciplineOfHonor;
import com.siqi_dangjian.bean.User;
import com.siqi_dangjian.dao.IDisciplineOfHonorDao;
import com.siqi_dangjian.service.IDisciplineOfHonorService;
import com.siqi_dangjian.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class DisciplineOfHonorService implements IDisciplineOfHonorService {

    @Autowired
    private IDisciplineOfHonorDao disciplineOfHonorDao;

    @Autowired
    private IUserService userService;

    @Override
    public void insertOrUpdate(DisciplineOfHonor disciplineOfHonor) throws Exception {
        disciplineOfHonorDao.insertOrUpdate(disciplineOfHonor);
    }

    @Override
    public void logicDelete(List idList) throws Exception {
        disciplineOfHonorDao.logicDelete(idList);
    }

    @Override
    public void delete(List idList) throws Exception {
        disciplineOfHonorDao.delete(idList);
    }

    @Override
    public DisciplineOfHonor selectById(Long id) throws Exception {
        DisciplineOfHonor disciplineOfHonor = disciplineOfHonorDao.selectById(id);
        return disciplineOfHonor;
    }

    @Override
    public Map selectAll(Map blurMap, Map intMap, Map dateMap, Integer limit, Integer page) throws Exception {
        Map map =  disciplineOfHonorDao.selectAll(blurMap,intMap,dateMap,limit, page);
        return map;
    }

    @Override
    public Map selectDisciplineOfHonorInfoById(Long id) throws Exception {
        DisciplineOfHonor disciplineOfHonor =  disciplineOfHonorDao.selectById(id);
        Long userId = disciplineOfHonor.getUserId();
        User user = userService.getUserById(userId);
        Map data = new HashMap();
        data.put("disciplineOfHonor",disciplineOfHonor);
        data.put("user",user);
        return data;
    }

    @Override
    public Integer selectCountByUserIdAndType(Long UserId, Integer type) throws Exception {
        return disciplineOfHonorDao.selectCountByUserIdAndType(UserId, type);
    }
}
