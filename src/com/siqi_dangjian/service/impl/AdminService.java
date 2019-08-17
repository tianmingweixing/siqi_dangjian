package com.siqi_dangjian.service.impl;

import com.siqi_dangjian.bean.Admin;
import com.siqi_dangjian.bean.User;
import com.siqi_dangjian.dao.impl.AdminDao;
import com.siqi_dangjian.service.IAdminService;
import com.siqi_dangjian.util.MD5;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class AdminService implements IAdminService {

    @Autowired
    private AdminDao adminDao;

    @Override
    public Admin Login(String adminName ,String password) throws Exception{
        return null;
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
    public Map selectAll(Map blurParam, Map dateParam, Map intParam, int limit, int page) throws Exception {
        return adminDao.selectAll(blurParam,dateParam,intParam,limit,page);
    }
}
