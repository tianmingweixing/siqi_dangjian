package com.siqi_dangjian.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 总结（计划）类型表
 */
@Entity
@Table(name = "conclusion_type")
public class ConclusionType extends BaseBean{



    /**
     * 季度类型名称 : 1.年度  2.半年度  3.月度 4.日度 或其他
     */
    @Column(name="type_name",length = 30)
    private String typeName;

    /**
     * 工作会议类型
     * 1 总结  2 计划
     */
    @Column(name="type",length = 8,columnDefinition = "INT default 1")
    private Integer type;

    /**
     * 支部ID
     */
    @Column(name = "party_branch_id",length = 8)
    private Long partyBranchId;


    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
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
