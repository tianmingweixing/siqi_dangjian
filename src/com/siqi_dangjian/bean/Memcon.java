package com.siqi_dangjian.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name="memcon")
public class Memcon extends BaseBean {

    /**
     * 谈话对象
     */
    @Column(name="username",nullable=true,length=15)
    private String userName;

    /**
     * 谈话人
     */
    @Column(name="speakers",nullable=true,length=15)
    private String speakers;

    /**
     * 地点
     */
    @Column(name="address",nullable=true,length=300)
    private String address;

    /**
     * 谈话内容
     */
    @Column(name="content",nullable=true,length=3000)
    private String content;

    /**
     * 谈话内容
     */
    @Column(name="memcon_time",nullable=true,length=1)
    private Timestamp memconTime;

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

    public String getSpeakers() {
        return speakers;
    }

    public void setSpeakers(String speakers) {
        this.speakers = speakers;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getMemconTime() {
        return memconTime;
    }

    public void setMemconTime(Timestamp memconTime) {
        this.memconTime = memconTime;
    }

    public Long getPartyBranchId() {
        return partyBranchId;
    }

    public void setPartyBranchId(Long partyBranchId) {
        this.partyBranchId = partyBranchId;
    }
}
