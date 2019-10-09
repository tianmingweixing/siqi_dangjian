package com.siqi_dangjian.service;

import com.siqi_dangjian.bean.ApplicationForm;
import java.util.List;
import java.util.Map;

public interface IApplicationFormService {

    /**
     * 插入或修改入党申请
     * @param applicationForm
     */
    void insertOrUpdateApplicationForm(ApplicationForm applicationForm);

    /**
     * 删除入党申请
     * @param idList
     */
    void deleteApplicationForm(List idList);

    /**
     * 修改审核状态
     * @param status
     */
    void updateStatus(int status);

    /**
     * 根据id获取入党申请
     * @param id
     * @return
     */
    ApplicationForm getApplicationFormById(long id);

    /**
     *查询入党申请列表
     * @param limit
     * @param page
     * @param blurMap
     * @param intMap
     * @param dateMap
     * @return
     */
    Map getApplicationFormList(int limit , int page , Map blurMap, Map intMap, Map dateMap);
}
