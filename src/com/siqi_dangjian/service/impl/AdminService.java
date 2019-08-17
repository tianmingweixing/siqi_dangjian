package com.siqi_dangjian.service.impl;

import com.siqi_dangjian.bean.Admin;
import com.siqi_dangjian.dao.impl.AdminDao;
import com.siqi_dangjian.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdminService implements IAdminService {

    @Autowired
    private AdminDao adminDao;

    @Override
    public Admin Login(Long name) {
        try {
            return adminDao.selectById(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
