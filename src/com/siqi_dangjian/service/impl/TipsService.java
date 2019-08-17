package com.siqi_dangjian.service.impl;

import com.siqi_dangjian.bean.Tips;
import com.siqi_dangjian.dao.IActivityOfUserDao;
import com.siqi_dangjian.dao.ITipsDao;
import com.siqi_dangjian.service.ITipsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class TipsService implements ITipsService {

    @Autowired
    private ITipsDao tipsDao;

    @Override
    public Map getUserNameByType(Integer type) throws Exception {
        return null;
    }

    @Override
    public Map getUserNameById(Long id) throws Exception {
        return null;
    }

    @Override
    public void insertOrUpdate(Tips tips) throws Exception {
        tipsDao.insertOrUpdate(tips);
    }

    @Override
    public void logicDelete(List idList) throws Exception {
        tipsDao.logicDelete(idList);
    }

    @Override
    public void delete(List idList) throws Exception {
        tipsDao.delete(idList);
    }

    @Override
    public Tips selectById(Long id) throws Exception {
        Tips tips = tipsDao.selectById(id);
        return tips;
    }

    @Override
    public Map selectAll(Map blurMap, Map intMap, Map dateMap, Integer limit, Integer page) throws Exception {
        Map map =  tipsDao.selectAll(blurMap,intMap,dateMap,limit, page);
        return map;
    }
}
