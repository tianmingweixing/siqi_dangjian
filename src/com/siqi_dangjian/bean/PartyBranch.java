package com.siqi_dangjian.bean;

import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

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
    @Column(name="party_member_count",nullable=true,length=10,columnDefinition="INT default 0")
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
    @Column(name="party_img",nullable=true,length=300)
    private String partyImg;

    /**
     * 组织结构图
     */
    @Column(name="structure_img",nullable=true,length=300)
    private String structureImg;

    /**
     * 活动面积
     */
    @Column(name="activity_area",nullable=true,length=5)
    private Double activityArea;

    /**
     * 成立时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="founding_time",nullable = true)
    private Date foundingTime;

    /**
     * 换届时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="change_time",nullable = true)
    private Date changeTime;

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

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String getStructureImg() {
        return structureImg;
    }

    public void setStructureImg(String structureImg) {
        this.structureImg = structureImg;
    }
}
