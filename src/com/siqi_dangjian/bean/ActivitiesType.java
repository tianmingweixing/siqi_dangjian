package com.siqi_dangjian.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 活动类型表
 */
@Entity
@Table(name = "activities_type")
public class ActivitiesType extends BaseBean{
    /**
     * 类型名称 : 1.义务劳动  2.革命传统教育  3.知识竞赛 4.重点工程突击 5.特色活动
     */
    @Column(name="type_name",length = 30)
    private String typeName;

    /**
     * 支部ID
     */
    @Column(name = "party_branch_id",length = 8)
    private Long partyBranchId;

    public ActivitiesType() {
    }

    public ActivitiesType(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Long getPartyBranchId() {
        return partyBranchId;
    }

    public void setPartyBranchId(Long partyBranchId) {
        this.partyBranchId = partyBranchId;
    }
}
