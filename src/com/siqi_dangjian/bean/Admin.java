package com.siqi_dangjian.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 管理员
 */
@Entity
@Table(name="admin")
public class Admin extends BaseBean {

    @Column(name="username",nullable=true,length=15)
    private String userName;

    @Column(name="head_img",nullable=true,length=300)
    private String headImg;

    @Column(name="password",nullable=true,length=12)
    private String password;

    /**
     * 管理员类型：1：超级管理员；2：普通管理员
     */
    @Column(name="admin_type",nullable=true,length=1,columnDefinition="INT default 2")
    private Integer adminType;

    /**
     * 权限
     */
    @Column(name="authority",nullable=true,length=300)
    private String authority;


    /**
     * 账号
     */
    @Column(name="account",nullable=true,length=300)
    private String account;

    /**
     * 党支部id
     */
    @Column(name="party_branch_id",length = 8,nullable = true)
    private Long partyBranchId;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAdminType() {
        return adminType;
    }

    public void setAdminType(Integer adminType) {
        this.adminType = adminType;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public Long getPartyBranchId() {
        return partyBranchId;
    }

    public void setPartyBranchId(Long partyBranchId) {
        this.partyBranchId = partyBranchId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
