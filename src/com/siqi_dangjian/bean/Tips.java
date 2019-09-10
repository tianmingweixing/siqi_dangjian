package com.siqi_dangjian.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 心得表
 */
@Entity
@Table(name = "tips")
public class Tips extends BaseBean{
    /**
     * 支部ID
     */
    @Column(name = "party_branch_id",length = 8)
    private Long partyBranchId;

    /**
     * 用户ID
     */
    @Column(name="user_id",length = 8)
    private Long userId;

    /**
     * 用户名
     */
    @Column(name="user_name",length = 100)
    private String userName;

    /**
     * 活动ID
     */
    @Column(name="activity_id",length =8)
    private Long activityId;

    /**
     * 心得
     */
    @Column(name="content",length = 1000)
    private String content;

    /**
     * 会议ID
     */
    @Column(name="meeting_id",length = 8)
    private Long meetingId;

    /**
     * 类型  0：会议心得   1：活动心得
     */
    @Column(name="type",length = 2,columnDefinition = "INT default 0")
    private Integer type;

    public Long getPartyBranchId() {
        return partyBranchId;
    }

    public void setPartyBranchId(Long partyBranchId) {
        this.partyBranchId = partyBranchId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public Long getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(Long meetingId) {
        this.meetingId = meetingId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
