package com.siqi_dangjian.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 政治面貌；1：积极分子；2：预备党员；3：发展对象；4：正式党员；5：党委。。。
 */
@Entity
@Table(name = "duty")
public class Duty extends BaseBean {

    @Column(name="name",nullable=true,length=30)
    private String name;

    /**
     * 职责
     */
    @Column(name="party_duty",nullable=true,length=300)
    private String partyDuty;

    /**
     * 党支部id
     */
    @Column(name="party_branch_id",length = 8,nullable = true)
    private Long partyBranchId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
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
