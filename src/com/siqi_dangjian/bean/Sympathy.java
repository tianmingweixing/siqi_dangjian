package com.siqi_dangjian.bean;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * 慰问表
 */
@Entity
@Table(name = "sympathy")
public class Sympathy  extends BaseBean{



    /**
     * 用户ID
     */
    @Column(name = "user_id",length = 8)
    private Long userId;

    /**
     * 支部ID
     */
    @Column(name = "party_branch_id",length = 8)
    private Long partyBranchId;


    /**
     * 困难情况
     * 0 非困难  1困难   2非常困难
     */
    @Column(name = "difficult",length = 8,columnDefinition = "INT default 0")
    private Integer difficult;

    /**
     * 慰问时间
     */
    @DateTimeFormat(pattern="yyyy-MM-ddHH:mm:ss")
    @Column(name = "sympathy_time",length = 30)
    private Timestamp sympathyTime;

    /**
     * 慰问人单位及职务
     */
    @Column(name = "unit_and_position",length = 200)
    private String  unitAndPosition;

    /**
     * 慰问品及信息
     */
    @Column(name = "sympathy_product",length = 200)
    private String sympathyProduct;

    /**
     * 备注
     */
    @Column(name = "note",length = 500)
    private String note;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getDifficult() {
        return difficult;
    }

    public void setDifficult(Integer difficult) {
        this.difficult = difficult;
    }

    public Timestamp getSympathyTime() {
        return sympathyTime;
    }

    public void setSympathyTime(Timestamp sympathyTime) {
        this.sympathyTime = sympathyTime;
    }

    public String getUnitAndPosition() {
        return unitAndPosition;
    }

    public void setUnitAndPosition(String unitAndPosition) {
        this.unitAndPosition = unitAndPosition;
    }

    public String getSympathyProduct() {
        return sympathyProduct;
    }

    public void setSympathyProduct(String sympathyProduct) {
        this.sympathyProduct = sympathyProduct;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    public Long getPartyBranchId() {
        return partyBranchId;
    }

    public void setPartyBranchId(Long partyBranchId) {
        this.partyBranchId = partyBranchId;
    }
}
