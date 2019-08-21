package com.siqi_dangjian.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 党员
 */
@Entity
@Table(name="user")
public class User extends BaseBean {


    /**
     * 用户微信名，或者后台登陆昵称
     */
    @Column(name="username",nullable=true,length=15)
    private String userName;

    /**
     * 性别 1代表男，2代表女
     */
    @Column(name="sex",nullable=true,length=1,columnDefinition="INT default 1")
    private Integer sex;

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
     * 职务id：1：积极分子，2：发展党员，3：预备党员，4：正式党员,5:党委
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

    @Column(name="openId",nullable=true,length=40)
    private String openId;

    /**
     * 转正时间
     */
    @Column(name="official_time",length = 1,nullable = true)
    private Timestamp officialTime;

    /**
     * 入党时间
     */
    @Column(name="join_time",length = 1,nullable = true)
    private Timestamp joinTime;

    /**
     * 成为积极分子时间
     */
    @Column(name="activist_time",length = 1,nullable = true)
    private Timestamp activistTime;

    /**
     * 成为预备党员时间
     */
    @Column(name="ready_time",length = 1,nullable = true)
    private Timestamp readyTime;

    /**
     * 成为发展对象时间
     */
    @Column(name="develop_time",length = 1,nullable = true)
    private Timestamp developTime;

    /**
     * 党支部id
     */
    @Column(name="party_branch_id",length = 8,nullable = true)
    private Long partyBranchId;


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

    public Timestamp getOfficialTime() {
        return officialTime;
    }

    public void setOfficialTime(Timestamp officialTime) {
        this.officialTime = officialTime;
    }

    public Timestamp getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(Timestamp joinTime) {
        this.joinTime = joinTime;
    }

    public Timestamp getActivistTime() {
        return activistTime;
    }

    public void setActivistTime(Timestamp activistTime) {
        this.activistTime = activistTime;
    }

    public Timestamp getReadyTime() {
        return readyTime;
    }

    public void setReadyTime(Timestamp readyTime) {
        this.readyTime = readyTime;
    }

    public Timestamp getDevelopTime() {
        return developTime;
    }

    public void setDevelopTime(Timestamp developTime) {
        this.developTime = developTime;
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
