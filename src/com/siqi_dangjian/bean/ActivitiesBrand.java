package com.siqi_dangjian.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 活动品牌表
 */
@Entity
@Table(name = "activities_brand")
public class ActivitiesBrand extends BaseBean{
    /**
     * 品牌名称 : 1.挖矿先锋  2.公益先锋
     */
    @Column(name="brand_name",length = 30)
    private String brandName;

    /**
     * 支部ID
     */
    @Column(name = "party_branch_id",length = 8)
    private Long partyBranchId;

    public ActivitiesBrand() {
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Long getPartyBranchId() {
        return partyBranchId;
    }

    public void setPartyBranchId(Long partyBranchId) {
        this.partyBranchId = partyBranchId;
    }
}
