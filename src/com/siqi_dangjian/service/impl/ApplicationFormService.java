package com.siqi_dangjian.service.impl;

import com.siqi_dangjian.bean.ApplicationForm;
import com.siqi_dangjian.dao.IApplicationFormDao;
import com.siqi_dangjian.service.IApplicationFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ApplicationFormService implements IApplicationFormService {

    @Autowired
    private IApplicationFormDao applicationFormDao;

    @Override
    public void insertOrUpdateApplicationForm(ApplicationForm applicationForm) {
        applicationFormDao.insertOrUpdateApplicationForm(applicationForm);
    }

    @Override
    public void deleteApplicationForm(List idList) {
        applicationFormDao.deleteApplicationForm(idList);
    }

    @Override
    public void updateStatus(int status) {
    applicationFormDao.updateStatus(status);
    }

    @Override
    public ApplicationForm getApplicationFormById(long id) {
        return applicationFormDao.getApplicationFormById(id);
    }

    @Override
    public Map getApplicationFormList(int limit, int page, Map blurMap, Map intMap, Map dateMap) {
        return applicationFormDao.getApplicationFormList( limit,  page,  blurMap,  intMap,  dateMap);
    }
}
