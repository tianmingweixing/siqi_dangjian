package com.siqi_dangjian.bean;

import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "activities")
public class Activities extends BaseBean{


    /**
     * 支部ID
     */
    @Column(name = "party_branch_id",length = 8)
    private Long partyBranchId;

    /**
     * 活动标题
     */
    @Column(name="title",length = 100,nullable = true)
    private String title;

    /**
     * 活动内容
     */
    @Column(name="content",length = 3000,nullable = true)
    private String content;

    /**
     * 活动类型  1 党日活动 2 党员活动 3 主题实践活动
     */
    @Column(name="type_id",length = 8,nullable = true)
    private Long typeId;

    /**
     * 活动品牌
     */
    @Column(name="brand_id",length = 8,nullable = true)
    private Long brandId;

    /**
     * 开始时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name="start_time",length = 30,nullable = true)
    public Date startTime;


    /**
     * 结束时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name="end_time",length = 30,nullable = true)
    public Date endTime;

    /**
     * 截至报名时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name="end_join_time",length = 30,nullable = true)
    public Date endJoinTime;

    /**
     * 活动状态 0：筹备 1： 进行 2： 结束
     */
    @Column(name="status",length = 8,nullable = true)
    public Integer status;


    /**
     * 图片1
     */
    @Column(name="image_path_a",length = 150)
    private String imagePathA;

    /**
     * 图片2
     */
    @Column(name="image_path_b",length = 150)
    private String imagePathB;

    /**
     * 点评
     */
    @Column(name="review",length = 500)
    private String review;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getEndJoinTime() {
        return endJoinTime;
    }

    public void setEndJoinTime(Date endJoinTime) {
        this.endJoinTime = endJoinTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getImagePathA() {
        return imagePathA;
    }

    public void setImagePathA(String imagePathA) {
        this.imagePathA = imagePathA;
    }

    public String getImagePathB() {
        return imagePathB;
    }

    public void setImagePathB(String imagePathB) {
        this.imagePathB = imagePathB;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Long getPartyBranchId() {
        return partyBranchId;
    }

    public void setPartyBranchId(Long partyBranchId) {
        this.partyBranchId = partyBranchId;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }
}
