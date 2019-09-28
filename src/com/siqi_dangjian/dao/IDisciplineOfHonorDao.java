package com.siqi_dangjian.dao;

import com.siqi_dangjian.bean.DisciplineOfHonor;

import java.util.List;
import java.util.Map;

public interface IDisciplineOfHonorDao {
    void insertOrUpdate(DisciplineOfHonor disciplineOfHonor) throws Exception;

    void logicDelete(List idList) throws Exception;

    void delete(List idList) throws Exception;

    DisciplineOfHonor selectById(Long id) throws Exception;

    Map selectAll(Map blurParam,Map intParam, Map dateParam,
                   int limit, int page) throws Exception;

    Integer selectCountByUserIdAndType(Long UserId, Integer type) throws Exception;
}
