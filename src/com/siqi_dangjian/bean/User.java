package com.siqi_dangjian.bean;

import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 党员
 */
@Entity
@Table(name="user")
public class User extends BaseBean {


    @Column(name="openId",nullable=true,length=40)
    private String openId;


    @Column(name="session_key",nullable=true,length=100)
    private String sessionKey;

    @Column(name="last_time",nullable=true,length=100)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastTime;

    /**
     * 用户名
     */
    @Column(name="username",nullable=true,length=15)
    private String userName;


    /**
     * 用户简介
     */
    @Column(name="profiles",nullable=true,length=2000)
    private String profiles;


    /**
     * 用户微信名，或者后台登陆昵称
     */
    @Column(name="nick_name",nullable=true,length=15)
    private String nickName;

    /**
     * 性别 1代表男，2代表女
     */
    @Column(name="sex",nullable=true,length=8,columnDefinition="INT default 1")
    private Integer sex;

    /**
     * 用户头像
     */
    @Column(name="head_img",nullable=true,length=300)
    private String headImg;

    /**
     * 年龄
     */
    @Column(name="age",nullable=true,length=3)
    private Integer age;

    @Column(name="birth",length = 1,nullable = true)
    private Timestamp birth;

    /**
     * 学历
     */
    @Column(name="education",nullable=true,length=20)
    private String education;

    /**
     * 政治面貌：1：积极分子，2：发展党员，3：预备党员，4：正式党员,5:党委
     */
    @Column(name="dutyid",nullable=true,length=5)
    private Long dutyId;

    /**
     * 班子id
     */
    @Column(name="party_groups_id",nullable=true,length=8)
    private Long partyGroupsId;

    /**
     * 党小组id
     */
    @Column(name="party_team_id",nullable=true,length=8)
    private Long partyTeamId;

    /**
     * 单位
     */
    @Column(name="company",nullable=true,length=100)
    private String company;

    /**
     * 培养人
     */
    @Column(name="train_people",nullable=true,length=50)
    private String trainPeople;

    @Column(name="phone",nullable=true,length=11)
    private String phone;

    /**
     * 民族
     */
    @Column(name="nation",nullable=true,length=30)
    private String nation;

    /**
     * 籍贯
     */
    @Column(name="origo",nullable=true,length=30)
    private String origo;

    @Column(name="ID_cord",nullable=true,length=50)
    private String IDCord;

    @Column(name="address",nullable=true,length=100)
    private String address;


    /**
     * 困难状态；0：非困难；1：困难；2：特困难
     */
    @Column(name="difficulty_type",length=1,columnDefinition="INT default 0")
    private Integer difficultyType;

    /**
     * 用户编号
     */
    @Column(name="userno",nullable=true,length=40)
    private String userNo;


    /**
     * 转正时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="official_time",length = 1,nullable = true)
    private Date officialTime;

    /**
     * 入党时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="join_time",length = 1,nullable = true)
    private Date joinTime;


    /**
     * 成为积极分子时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="activist_time",length = 1,nullable = true)
    private Date activistTime;

    /**
     * 成为预备党员时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="ready_time",length = 1,nullable = true)
    private Date readyTime;

    /**
     * 成为发展对象时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="develop_time",length = 1,nullable = true)
    private Date developTime;

    /**
     * 党支部id
     */
    @Column(name="party_branch_id",length = 8,nullable = true)
    private Long partyBranchId;


    public String getProfiles() {
        return profiles;
    }

    public void setProfiles(String profiles) {
        this.profiles = profiles;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    public Date getOfficialTime() {
        return officialTime;
    }

    public void setOfficialTime(Date officialTime) {
        this.officialTime = officialTime;
    }

    public Date getActivistTime() {
        return activistTime;
    }

    public void setActivistTime(Date activistTime) {
        this.activistTime = activistTime;
    }

    public Date getReadyTime() {
        return readyTime;
    }

    public void setReadyTime(Date readyTime) {
        this.readyTime = readyTime;
    }

    public Date getDevelopTime() {
        return developTime;
    }

    public void setDevelopTime(Date developTime) {
        this.developTime = developTime;
    }

    public Date getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(Date joinTime) {
        this.joinTime = joinTime;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }


    public Integer getDifficultyType() {
        return difficultyType;
    }

    public void setDifficultyType(Integer difficultyType) {
        this.difficultyType = difficultyType;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Timestamp getBirth() {
        return birth;
    }

    public void setBirth(Timestamp birth) {
        this.birth = birth;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public Long getDutyId() {
        return dutyId;
    }

    public void setDutyId(Long dutyId) {
        this.dutyId = dutyId;
    }

    public Long getPartyGroupsId() {
        return partyGroupsId;
    }

    public void setPartyGroupsId(Long partyGroupsId) {
        this.partyGroupsId = partyGroupsId;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getTrainPeople() {
        return trainPeople;
    }

    public void setTrainPeople(String trainPeople) {
        this.trainPeople = trainPeople;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getOrigo() {
        return origo;
    }

    public void setOrigo(String origo) {
        this.origo = origo;
    }

    public String getIDCord() {
        return IDCord;
    }

    public void setIDCord(String IDCord) {
        this.IDCord = IDCord;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Long getPartyBranchId() {
        return partyBranchId;
    }

    public void setPartyBranchId(Long partyBranchId) {
        this.partyBranchId = partyBranchId;
    }

    public Long getPartyTeamId() {
        return partyTeamId;
    }

    public void setPartyTeamId(Long partyTeamId) {
        this.partyTeamId = partyTeamId;
    }
}
