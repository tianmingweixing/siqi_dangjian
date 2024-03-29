package com.siqi_dangjian.bean;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * 工作总结（计划）表
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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="year_limit",length = 30)
    private Date yearLimit;


    /**
     * 计划内容
     */
    @Column(name="plan_content",length = 3000)
    private String planContent;

    /**
     * 标题
     */
    @Column(name="title",length = 100)
    private String title;

    /**
     * 总结类型
     */
    @Column(name="conclusion_type_id",length = 8,columnDefinition = "INT default 1")
    private Long conclusionTypeId;


    public Date getYearLimit() {
        return yearLimit;
    }

    public void setYearLimit(Date yearLimit) {
        this.yearLimit = yearLimit;
    }

    public Long getConclusionTypeId() {
        return conclusionTypeId;
    }

    public void setConclusionTypeId(Long conclusionTypeId) {
        this.conclusionTypeId = conclusionTypeId;
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


    public Long getPartyBranchId() {
        return partyBranchId;
    }

    public void setPartyBranchId(Long partyBranchId) {
        this.partyBranchId = partyBranchId;
    }

}
