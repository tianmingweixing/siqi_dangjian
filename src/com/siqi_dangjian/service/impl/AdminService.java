package com.siqi_dangjian.service.impl;

import com.siqi_dangjian.bean.Admin;
import com.siqi_dangjian.bean.Conclusion;
import com.siqi_dangjian.dao.IAdminDao;
import com.siqi_dangjian.dao.impl.ConclusionDao;
import com.siqi_dangjian.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class AdminService implements IAdminService {

    @Autowired
    private IAdminDao adminDao;

    @Override
    public Admin getAdminByAccount(String account) {
        Admin admin = adminDao.selectAdminByAccount(account);
        return admin;
    }

    @Override
    public Admin Login(Long name) {
        try {
            return adminDao.selectById(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Map getUserNameByType(Integer type) throws Exception {
        return null;
    }

    @Override
    public Admin selectById(Long id) throws Exception {
        return adminDao.selectById(id);
    }

    @Override
    public void insertOrUpdate(Admin admin) throws Exception {
        adminDao.insertOrUpdate(admin);

    }

    @Override
    public void logicDelete(List idList) throws Exception {
        adminDao.logicDelete(idList);
    }

    @Override
    public void delete(List idList) throws Exception {
        adminDao.delete(idList);
    }


    @Override
    public Map selectAll(Map blurMap, Map intMap, Map dateMap, Integer limit, Integer page) throws Exception {
        Map map =  adminDao.selectAll(blurMap,intMap,dateMap,limit, page);
        return map;
    }
}
