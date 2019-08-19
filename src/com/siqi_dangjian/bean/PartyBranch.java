package com.siqi_dangjian.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * 党支部
 */
@Entity
@Table(name = "party_branch")
public class PartyBranch extends BaseBean {

    @Column(name="name",nullable=true,length=30)
    private String name;

    /**
     * 成员总数
     */
    @Column(name="party_member_count",nullable=true,length=1,columnDefinition="INT default 0")
    private Integer partyMemberCount;

    /**
     * 党支部职责
     */
    @Column(name="duty",nullable=true,length=300)
    private String duty;

    /**
     * 党支部编号
     */
    @Column(name="party_no",nullable=true,length=30)
    private String partyNo;

    /**
     * 党支部简介
     */
    @Column(name="party_info",nullable=true,length=30)
    private String partyInfo;
    /**
     * 党支部图片
     */
    @Column(name="party_img",nullable=true,length=30)
    private String partyImg;

    /**
     * 活动面积
     */
    @Column(name="activity_area",nullable=true,length=5)
    private Double activityArea;

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

    public String getPartyInfo() {
        return partyInfo;
    }

    public void setPartyInfo(String partyInfo) {
        this.partyInfo = partyInfo;
    }

    public String getPartyImg() {
        return partyImg;
    }

    public void setPartyImg(String partyImg) {
        this.partyImg = partyImg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPartyMemberCount() {
        return partyMemberCount;
    }

    public void setPartyMemberCount(Integer partyMemberCount) {
        this.partyMemberCount = partyMemberCount;
    }

    public String getPartyNo() {
        return partyNo;
    }

    public void setPartyNo(String partyNo) {
        this.partyNo = partyNo;
    }

    public Double getActivityArea() {
        return activityArea;
    }

    public void setActivityArea(Double activityArea) {
        this.activityArea = activityArea;
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

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }
}
