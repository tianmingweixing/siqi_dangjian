package com.siqi_dangjian.bean;

import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

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
    @Column(name="party_group_no",nullable=true,length=30)
    private String partyGroupNo;

    /**
     * 成立时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name="founding_time",nullable = true)
    private Date foundingTime;

    /**
     * 换届时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name="change_time",nullable = true)
    private Date changeTime;

    /**
     * 党支部id
     */
    @Column(name="party_branch_id",length = 8,nullable = true)
    private Long partyBranchId;

    public Date getFoundingTime() {
        return foundingTime;
    }

    public void setFoundingTime(Date foundingTime) {
        this.foundingTime = foundingTime;
    }

    public Date getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(Date changeTime) {
        this.changeTime = changeTime;
    }

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

    public String getPartyGroupNo() {
        return partyGroupNo;
    }

    public void setPartyGroupNo(String partyGroupNo) {
        this.partyGroupNo = partyGroupNo;
    }


    public Long getPartyBranchId() {
        return partyBranchId;
    }

    public void setPartyBranchId(Long partyBranchId) {
        this.partyBranchId = partyBranchId;
    }
}
