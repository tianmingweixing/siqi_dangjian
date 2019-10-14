package com.siqi_dangjian.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 配置表
 */
@Entity
@Table(name = "configuration")
public class Configuration extends BaseBean {

    /**
     * 支部ID
     */
    @Column(name = "party_branch_id",length = 8)
    private Long partyBranchId;

    /**
     * 微信AppId
     */
    @Column(name="wx_appId",length = 100)
    private String wxAppId;

    /**
     * 微信秘钥
     */
    @Column(name="wx_app_secret",length = 100)
    private String wxAppSecret;

    /**
     * 微信商户号
     */
    @Column(name="wx_much_id",length =100)
    private String wxMuchId;

    /**
     * 公司名称
     */
    @Column(name="com_name",length = 100)
    private String comName;

    /**
     * 公司简介
     */
    @Column(name="com_info",length = 1000)
    private String comInfo;

    /**
     * 公司电话
     */
    @Column(name="com_phone",length = 20)
    private String comPhone;

    /**
     * 公司图片
     */
    @Column(name="com_img",length = 200)
    private String comImg;

    /**
     * 轮播图片1
     */
    @Column(name="image_path_a",length = 100)
    private String imagePathA;

    /**
     * 图片2
     */
    @Column(name="image_path_b",length = 100)
    private String imagePathB;

    /**
     * 图片3
     */
    @Column(name="image_path_c",length =100)
    private String imagePathC;

    /**
     * 图片4
     */
    @Column(name="image_path_d",length = 100)
    private String imagePathD;

    /**
     * 图片5
     */
    @Column(name="image_path_e",length = 100)
    private String imagePathE;

    /**
     * 图片6
     */
    @Column(name="image_path_f",length = 100)
    private String imagePathF;

    public String getComInfo() {
        return comInfo;
    }

    public void setComInfo(String comInfo) {
        this.comInfo = comInfo;
    }

    public String getComPhone() {
        return comPhone;
    }

    public void setComPhone(String comPhone) {
        this.comPhone = comPhone;
    }

    public String getComImg() {
        return comImg;
    }

    public void setComImg(String comImg) {
        this.comImg = comImg;
    }

    public Long getPartyBranchId() {
        return partyBranchId;
    }

    public void setPartyBranchId(Long partyBranchId) {
        this.partyBranchId = partyBranchId;
    }

    public String getWxAppId() {
        return wxAppId;
    }

    public void setWxAppId(String wxAppId) {
        this.wxAppId = wxAppId;
    }

    public String getWxAppSecret() {
        return wxAppSecret;
    }

    public void setWxAppSecret(String wxAppSecret) {
        this.wxAppSecret = wxAppSecret;
    }

    public String getWxMuchId() {
        return wxMuchId;
    }

    public void setWxMuchId(String wxMuchId) {
        this.wxMuchId = wxMuchId;
    }

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
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

    public String getImagePathC() {
        return imagePathC;
    }

    public void setImagePathC(String imagePathC) {
        this.imagePathC = imagePathC;
    }

    public String getImagePathD() {
        return imagePathD;
    }

    public void setImagePathD(String imagePathD) {
        this.imagePathD = imagePathD;
    }

    public String getImagePathE() {
        return imagePathE;
    }

    public void setImagePathE(String imagePathE) {
        this.imagePathE = imagePathE;
    }

    public String getImagePathF() {
        return imagePathF;
    }

    public void setImagePathF(String imagePathF) {
        this.imagePathF = imagePathF;
    }

}
