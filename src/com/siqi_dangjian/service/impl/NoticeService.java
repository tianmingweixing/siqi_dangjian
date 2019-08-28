package com.siqi_dangjian.service.impl;

import com.siqi_dangjian.bean.Notice;
import com.siqi_dangjian.dao.INoticeDao;
import com.siqi_dangjian.service.INoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class NoticeService implements INoticeService {

    @Autowired
    private INoticeDao noticeDao;

    @Override
    public Map getUserByType(Integer type) throws Exception {
        return null;
    }

    @Override
    public Map getUserInfoById(Long id) throws Exception {
        return null;
    }

    @Override
    public void insertOrUpdate(Notice notice) throws Exception {
        noticeDao.insertOrUpdate(notice);
    }

    @Override
    public void logicDelete(List idList) throws Exception {
        noticeDao.logicDelete(idList);
    }

    @Override
    public void delete(List idList) throws Exception {
        noticeDao.delete(idList);
    }

    @Override
    public Notice selectById(Long id) throws Exception {
        Notice notice = noticeDao.selectById(id);
        return notice;
    }

    @Override
    public Map selectAll(Map blurMap, Map intMap, Map dateMap, Integer limit, Integer page) throws Exception {
        Map map = noticeDao.selectAll(blurMap,intMap,dateMap,limit, page);
        return map;
    }
}
