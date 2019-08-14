package com.siqi_dangjian.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 配置表
 */
@Entity
@Table(name = "configuration")
public class Configuration {

    /**
     * 支部ID
     */
    @Column(name = "party_branch_id",length = 8)
    private Long partyBranchId;

    /**
     * 微型AppId
     */
    @Column(name="wx_appId",length = 100)
    private String wxAppId;

    /**
     * 微信秘钥
     */
    @Column(name="wx_app_secret",length = 100)
    private String wxAppSecret;

    /**
     * 微型商户号
     */
    @Column(name="wx_much_id",length =100)
    private String wxMuchId;

    /**
     * 公司名称
     */
    @Column(name="com_name",length = 100)
    private String comName;

    /**
     * 图片1
     */
    @Column(name="image_path_A",length = 100)
    private String imagePathA;

    /**
     * 图片2
     */
    @Column(name="image_path_B",length = 100)
    private String imagePathB;

    /**
     * 图片3
     */
    @Column(name="image_path_C",length =100)
    private String imagePathC;

    /**
     * 图片4
     */
    @Column(name="image_path_D",length = 100)
    private String imagePathD;

    /**
     * 图片5
     */
    @Column(name="image_path_E",length = 100)
    private String imagePathE;

    /**
     * 图片6
     */
    @Column(name="image_path_F",length = 100)
    private String imagePathF;

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
