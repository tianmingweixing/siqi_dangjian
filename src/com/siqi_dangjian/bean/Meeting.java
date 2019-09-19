package com.siqi_dangjian.bean;

import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * 会议
 */
@Entity
@Table(name = "meeting")
public class Meeting extends BaseBean {

    @Column(name="name",nullable=true,length=30)
    private String name;

    /**
     * 会议类型：1：支委会；2：党员大会；
     */
    @Column(name="meeting_type_id",nullable=true,length=8)
    private Long meetingTypeId;

    /**
     * 用户ID
     */
    @Column(name="user_id",length = 8)
    private Long userId;

    /**
     * 会议内容
     */
    @Column(name="content",nullable=true,length=3000)
    private String content;

    /**
     * 主持人
     */
    @Column(name="compere",nullable=true,length=3000)
    private String compere;

    /**
     * 记录人
     */
    @Column(name="recorder",nullable=true,length=3000)
    private String recorder;

    /**
     * 应到人数
     */
    @Column(name="people_counting",nullable=true,length=3000)
    private String peopleCounting;

    /**
     * 实到人数
     */
    @Column(name="attendance",nullable=true,length=3000)
    private String attendance;


    /**
     * 地点
     */
    @Column(name="address",nullable=true,length=3000)
    private String address;


    /**
     * 会议指导
     */
    @Column(name="guide",nullable=true,length=2000)
    private String guide;

    /**
     * 会议图片1
     */
    @Column(name="images_a",nullable=true,length=200)
    private String imagesA;

    /**
     * 会议图片2
     */
    @Column(name="images_b",nullable=true,length=200)
    private String imagesB;

    /**
     * 开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="start_time",length = 1,nullable = true)
    private Date startTime;

    /**
     * 结束时间
     */
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="end_time",length = 1,nullable = true)
    private Date endTime;

    /**
     * 党支部id
     */
    @Column(name="party_branch_id",length = 8,nullable = true)
    private Long partyBranchId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCompere() {
        return compere;
    }

    public void setCompere(String compere) {
        this.compere = compere;
    }

    public String getRecorder() {
        return recorder;
    }

    public void setRecorder(String recorder) {
        this.recorder = recorder;
    }

    public String getPeopleCounting() {
        return peopleCounting;
    }

    public void setPeopleCounting(String peopleCounting) {
        this.peopleCounting = peopleCounting;
    }

    public String getAttendance() {
        return attendance;
    }

    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getMeetingTypeId() {
        return meetingTypeId;
    }

    public void setMeetingTypeId(Long meetingTypeId) {
        this.meetingTypeId = meetingTypeId;
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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Long getPartyBranchId() {
        return partyBranchId;
    }

    public void setPartyBranchId(Long partyBranchId) {
        this.partyBranchId = partyBranchId;
    }
}
