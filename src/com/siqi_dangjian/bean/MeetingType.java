package com.siqi_dangjian.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 会议类型表
 */
@Entity
@Table(name = "meeting_Type")
public class MeetingType extends BaseBean{


    /**
     * 会议类型名称 : 1. 支委会、 2. 党员大会、3 党小组会、4 党课、、5 廉政教育、、6 组织生活会 7 政治理论学习 8 互授党课 、9 民主评议党员、10 专题讨论
     */
    @Column(name="type_name",length = 30)
    private String typeName;


    /**
     * 支部ID
     */
    @Column(name = "party_branch_id",length = 8)
    private Long partyBranchId;


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
