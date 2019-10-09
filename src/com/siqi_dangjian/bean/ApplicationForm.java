package com.siqi_dangjian.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * 入党申请
 */
@Entity
@Table(name="application_form")
public class ApplicationForm extends BaseBean {

    @Column(name="phone",nullable=true,length=11)
    private String phone;

    /**
     * 申请人ID
     */
    @Column(name="user_id",length = 8,nullable = true)
    private Long userId;

    /**
     * 用户名
     */
    @Column(name="user_name",nullable=true,length=15)
    private String userName;



    /**
     * 申请表
     */
    @Column(name="app_form",nullable=true,length=200)
    private String appFrom;


    /**
     * 审核状态：0：待审核；1：通过；2：拒绝
     */
    @Column(name="status",length = 30, columnDefinition = "INT default 0")
    private Integer status;

    /**
     * 拒绝理由
     */
    @Column(name="refuse_reason",length = 300,nullable = true)
    private String refuseReason;


    /**
     * 审核时间
     */
    @Column(name="review_time",length = 100,nullable = true)
    private Timestamp reviewTime;

    /**
     * 党支部id
     */
    @Column(name="party_branch_id",length = 8,nullable = true)
    private Long partyBranchId;

    public void setReviewTime(Timestamp reviewTime) {
        this.reviewTime = reviewTime;
    }

    public void setRefuseReason(String refuseReason) {
        this.refuseReason = refuseReason;
    }


    public String getRefuseReason() {
        return refuseReason;
    }

    public Timestamp getReviewTime() {
        return reviewTime;
    }

    public String getAppFrom() {
        return appFrom;
    }

    public void setAppFrom(String appFrom) {
        this.appFrom = appFrom;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getPartyBranchId() {
        return partyBranchId;
    }

    public void setPartyBranchId(Long partyBranchId) {
        this.partyBranchId = partyBranchId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

}
