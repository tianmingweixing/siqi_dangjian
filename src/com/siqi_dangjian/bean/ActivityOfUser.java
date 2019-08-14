package com.siqi_dangjian.bean;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 活动-user 关联表 用于活动签到
 */
@Entity
@Table(name = "activity_of_user")
public class ActivityOfUser extends BaseBean {
    @Column(name="username",nullable=true,length=15)
    private String userName;

    @Column(name="userid",nullable=true,length=8)
    private Long userId;

    @Column(name="activityid",nullable=true,length=8)
    private Long activityId;

    public ActivityOfUser(String userName, Long userId, Long activityId) {
        this.userName = userName;
        this.userId = userId;
        this.activityId = activityId;
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

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }
}
