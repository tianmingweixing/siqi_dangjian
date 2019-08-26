package com.siqi_dangjian.bean;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Timestamp;

@MappedSuperclass
public class BaseBean {

    @Id
    @GeneratedValue
    public Long id;

    /**
     * 创建时间 yyyy-mm-dd hh:mm:ss
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name="create_time",length = 1,nullable = true)
    public Timestamp createTime;

    /**
     * 更新时间
     */
    @Column(name="update_time",length = 1,nullable = true)
    public Timestamp updateTime;


    /**
     * 逻辑删除
     */
    @Column(name="can_use",length = 1,columnDefinition = "INT default 1")
    public int canUse;

    /**
     * 删除时间
     */
    @Column(name="delete_time",length = 1,nullable = true)
    public Timestamp deleteTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public int getCanUse() {
        return canUse;
    }

    public void setCanUse(int canUse) {
        this.canUse = canUse;
    }

    public Timestamp getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Timestamp deleteTime) {
        this.deleteTime = deleteTime;
    }
}
