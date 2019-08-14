package com.siqi_dangjian.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 党务表
 */
@Entity
@Table(name = "partyWork")
public class PartyWork extends BaseBean{



    /**
     * 支部ID
     */
    @Column(name = "party_branch_id",length = 8)
    private Long partyBranchId;

    /**
     * 标题
     */
    @Column(name="title",length = 300)
    private String title;

    /**
     * 内容
     */
    @Column(name="content",length = 3000)
    private String content;

    /**
     * 反馈
     */
    @Column(name="feedback",length = 500)
    private String feedback;

    /**
     * 图片1
     */
    @Column(name="image_path_A",length = 150)
    private String imagePathA;

    /**
     * 图片2
     */
    @Column(name="image_path_B",length = 150)
    private String imagePathB;

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

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public Long getPartyBranchId() {
        return partyBranchId;
    }

    public void setPartyBranchId(Long partyBranchId) {
        this.partyBranchId = partyBranchId;
    }
}
