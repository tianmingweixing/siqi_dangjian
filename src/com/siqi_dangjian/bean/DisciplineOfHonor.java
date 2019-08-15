package com.siqi_dangjian.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;


/**
 * 荣誉和违纪表
 */
@Entity
@Table(name = "discipline_of_honor")
public class DisciplineOfHonor extends BaseBean {
    /**
     * 支部ID
     */
    @Column(name = "party_branch_id",length = 8)
    private Long partyBranchId;

    /**
     * 荣誉和违纪名称
     */
    @Column(name="name",length = 100)
    private String name;

    /**
     * 荣誉和违纪时间
     */
    @Column(name="time",length =100)
    private Timestamp time;


    /**
     * 类型  0：荣誉   1：违纪
     */
    @Column(name="type",length = 10,columnDefinition = "INT default 0")
    private Integer type;

    /**
     * 授奖单位或个人
     */
    @Column(name="unit",length = 200)
    private String unit;


    /**
     * 被奖惩个人或单位
     */
    @Column(name="passive_unit",length = 100)
    private String passiveUnit;


    /**
     * 荣誉凭证
     */
    @Column(name="certificate",length = 100)
    private String certificate;

    /**
     * 金额
     */
    @Column(name="amount",length = 100)
    private Double amount;

    /**
     * 奖惩内容
     */
    @Column(name="content",length = 300)
    private String content;

    /**
     * 备注
     */
    @Column(name="note",length = 300)
    private String note;

    public Long getPartyBranchId() {
        return partyBranchId;
    }

    public void setPartyBranchId(Long partyBranchId) {
        this.partyBranchId = partyBranchId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getPassiveUnit() {
        return passiveUnit;
    }

    public void setPassiveUnit(String passiveUnit) {
        this.passiveUnit = passiveUnit;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
