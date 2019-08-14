package com.siqi_dangjian.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 总结表
 */
@Entity
@Table(name = "conclusion")
public class Conclusion extends BaseBean{


    /**
     * 支部ID
     */
    @Column(name = "party_branch_id",length = 8)
    private Long partyBranchId;

    /**
     * 年限
     */
    @Column(name="year_limit",length = 30)
    private Integer yearLimit;

    /**
     * 计划内容
     */
    @Column(name="plan_content",length = 3000)
    private String planContent;

    /**
     * 标题
     */
    @Column(name="title",length = 30)
    private String title;

    /**
     * 总结类型
     * 0 总结  1 计划
     */
    @Column(name="type",length = 8,columnDefinition = "INT default 0")
    private Integer type;



    public Integer getYearLimit() {
        return yearLimit;
    }

    public void setYearLimit(Integer yearLimit) {
        this.yearLimit = yearLimit;
    }

    public String getPlanContent() {
        return planContent;
    }

    public void setPlanContent(String planContent) {
        this.planContent = planContent;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getPartyBranchId() {
        return partyBranchId;
    }

    public void setPartyBranchId(Long partyBranchId) {
        this.partyBranchId = partyBranchId;
    }

}
