package com.siqi_dangjian.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 会议-user 关联表 用于会议签到
 */
@Entity
@Table(name = "meeting_of_user")
public class MeetingOfUser extends BaseBean{

    @Column(name="user_name",nullable=true,length=15)
    private String userName;

    @Column(name="user_id",nullable=true,length=8)
    private Long userId;

    @Column(name="meeting_id",nullable=true,length=8)
    private Long meetingId;

    public MeetingOfUser(String userName, Long userId, Long meetingId) {
        this.userName = userName;
        this.userId = userId;
        this.meetingId = meetingId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(Long meetingId) {
        this.meetingId = meetingId;
    }
}
