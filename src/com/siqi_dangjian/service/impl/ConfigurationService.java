package com.siqi_dangjian.service.impl;

import com.siqi_dangjian.bean.Configuration;
import com.siqi_dangjian.dao.IConfigurationDao;
import com.siqi_dangjian.service.IConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ConfigurationService implements IConfigurationService {

    @Autowired
    private IConfigurationDao configurationDao;


    @Override
    public void insertOrUpdate(Configuration configuration) throws Exception {
        configurationDao.insertOrUpdate(configuration);
    }

    @Override
    public void logicDelete(List idList) throws Exception {
        configurationDao.logicDelete(idList);
    }

    @Override
    public void delete(List idList) throws Exception {
        configurationDao.delete(idList);
    }

    @Override
    public Configuration selectById(Long id) throws Exception {
        return configurationDao.selectById(id);
    }

    @Override
    public Map selectAll(Map blurParam, Map dateParam, Map intParam, int limit, int page) throws Exception {
        return configurationDao.selectAll(blurParam, dateParam, intParam, limit, page);
    }

    @Override
    public Long selectPartyBranchId() throws Exception {
        return configurationDao.selectPartyBranchId();
    }
}
