package com.siqi_dangjian.service;


import com.siqi_dangjian.bean.DisciplineOfHonor;
import java.util.List;
import java.util.Map;

public interface IDisciplineOfHonorService {

    void insertOrUpdate(DisciplineOfHonor disciplineOfHonor) throws Exception;

    void logicDelete(List idList) throws Exception;

    void delete(List idList) throws Exception;

    DisciplineOfHonor selectById(Long id) throws Exception;

    Map selectAll(Map blurMap, Map intMap, Map dateMap, Integer limit, Integer page) throws Exception;

    Map selectDisciplineOfHonorInfoById(Long id) throws Exception;

    Integer selectCountByUserIdAndType(Long UserId, Integer type) throws Exception;
}
