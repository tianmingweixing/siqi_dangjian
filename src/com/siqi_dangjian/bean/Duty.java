package com.siqi_dangjian.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 职务表
 */
@Entity
@Table(name = "duty")
public class Duty extends BaseBean {
    /**
     * 政治面貌 ：1 发展对象 2 积极分子；3 预备党员；；4 正式党员；5。。。
     */
    @Column(name="name",nullable=true,length=30)
    private Integer name;

    /**
     * 党内职务: 宣传委员 组织委员 纪律委员 支部书记 党小组成员
     */
    @Column(name="party_duty",nullable=true,length=300)
    private String partyDuty;

    /**
     * 党支部id
     */
    @Column(name="party_branch_id",length = 8,nullable = true)
    private Long partyBranchId;

    public Integer getName() {
        return name;
    }

    public void setName(Integer name) {
        this.name = name;
    }

    public String getPartyDuty() {
        return partyDuty;
    }

    public void setPartyDuty(String partyDuty) {
        this.partyDuty = partyDuty;
    }

    public Long getPartyBranchId() {
        return partyBranchId;
    }

    public void setPartyBranchId(Long partyBranchId) {
        this.partyBranchId = partyBranchId;
    }
}
