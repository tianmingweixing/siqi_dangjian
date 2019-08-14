package com.siqi_dangjian.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * 会议
 */
@Entity
@Table(name = "meeting")
public class Meeting extends BaseBean {

    @Column(name="name",nullable=true,length=30)
    private String name;

    /**
     * 会议类型：1：支委会；2：党员大会；3：廉政...
     */
    @Column(name="meeting_type",nullable=true,length=1,columnDefinition="INT default 0")
    private Integer meetingType;

    /**
     * 会议内容
     */
    @Column(name="content",nullable=true,length=3000)
    private String content;

    /**
     * 会议指导
     */
    @Column(name="guide",nullable=true,length=2000)
    private String guide;

    /**
     * 会议图片1
     */
    @Column(name="images_a",nullable=true,length=100)
    private String imagesA;

    /**
     * 会议图片1
     */
    @Column(name="images_b",nullable=true,length=100)
    private String imagesB;

    /**
     * 成立时间
     */
    @Column(name="start_time",length = 1,nullable = true)
    private Timestamp startTime;

    /**
     * 换届时间
     */
    @Column(name="end_time",length = 1,nullable = true)
    private Timestamp endTime;

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

    public Integer getMeetingType() {
        return meetingType;
    }

    public void setMeetingType(Integer meetingType) {
        this.meetingType = meetingType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getGuide() {
        return guide;
    }

    public void setGuide(String guide) {
        this.guide = guide;
    }

    public String getImagesA() {
        return imagesA;
    }

    public void setImagesA(String imagesA) {
        this.imagesA = imagesA;
    }

    public String getImagesB() {
        return imagesB;
    }

    public void setImagesB(String imagesB) {
        this.imagesB = imagesB;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public Long getPartyBranchId() {
        return partyBranchId;
    }

    public void setPartyBranchId(Long partyBranchId) {
        this.partyBranchId = partyBranchId;
    }
}
