package com.siqi_dangjian.service.impl;

import com.siqi_dangjian.bean.ActivitiesBrand;
import com.siqi_dangjian.dao.impl.ActivityBrandDao;
import com.siqi_dangjian.service.IActivityBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ActivityBrandService implements IActivityBrandService {

    @Autowired
    private ActivityBrandDao activityBrandDao;

    @Override
    public void insertOrUpdate(ActivitiesBrand activitiesBrand) throws Exception {
        activityBrandDao.insertOrUpdate(activitiesBrand);
    }

    @Override
    public void logicDelete(List idList) throws Exception {
        activityBrandDao.logicDelete(idList);
    }

    @Override
    public void delete(List idList) throws Exception {
        activityBrandDao.delete(idList);
    }

    @Override
    public ActivitiesBrand selectById(Long id) throws Exception {
        return activityBrandDao.selectById(id);
    }

    @Override
    public Map selectAll(int limit, int page) throws Exception {
        return activityBrandDao.selectAll(limit,page);
    }

    @Override
    public List<ActivitiesBrand> selectList() throws Exception {
        return activityBrandDao.selectList();
    }


}
