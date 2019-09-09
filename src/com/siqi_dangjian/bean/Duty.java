package com.siqi_dangjian.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 职务表
 */
@Entity
@Table(name = "duty")
public class Duty extends BaseBean {
    /**
     * 政治面貌 ：1 发展对象 2 积极分子；3 预备党员；；4 正式党员；5。。。
     */
    @Column(name="type_name",nullable=true,length=30)
    private String typeName;

    /**
     * 描述
     */
    @Column(name="description",nullable=true,length=300)
    private String description;

    /**
     * 党支部id
     */
    @Column(name="party_branch_id",length = 8,nullable = true)
    private Long partyBranchId;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getPartyBranchId() {
        return partyBranchId;
    }

    public void setPartyBranchId(Long partyBranchId) {
        this.partyBranchId = partyBranchId;
    }
}
