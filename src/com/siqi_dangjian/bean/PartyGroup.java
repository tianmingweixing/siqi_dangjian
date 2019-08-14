package com.siqi_dangjian.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * 支部班子
 */
@Entity
@Table(name = "party_group")
public class PartyGroup extends BaseBean {

    @Column(name="name",nullable=true,length=30)
    private String name;

    /**
     * 班子职责
     */
    @Column(name="duty",nullable=true,length=300)
    private String duty;

    /**
     * 党支部编号
     */
    @Column(name="party_no",nullable=true,length=30)
    private String partyNo;

    /**
     * 班子编号
     */
    @Column(name="party_group_no",nullable=true,length=5)
    private Double partyGroupNo;

    /**
     * 成立时间
     */
    @Column(name="founding_time",length = 1,nullable = true)
    private Timestamp foundingTime;

    /**
     * 换届时间
     */
    @Column(name="change_time",length = 1,nullable = true)
    private Timestamp changeTime;

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

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String getPartyNo() {
        return partyNo;
    }

    public void setPartyNo(String partyNo) {
        this.partyNo = partyNo;
    }

    public Double getPartyGroupNo() {
        return partyGroupNo;
    }

    public void setPartyGroupNo(Double partyGroupNo) {
        this.partyGroupNo = partyGroupNo;
    }

    public Timestamp getFoundingTime() {
        return foundingTime;
    }

    public void setFoundingTime(Timestamp foundingTime) {
        this.foundingTime = foundingTime;
    }

    public Timestamp getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(Timestamp changeTime) {
        this.changeTime = changeTime;
    }

    public Long getPartyBranchId() {
        return partyBranchId;
    }

    public void setPartyBranchId(Long partyBranchId) {
        this.partyBranchId = partyBranchId;
    }
}
