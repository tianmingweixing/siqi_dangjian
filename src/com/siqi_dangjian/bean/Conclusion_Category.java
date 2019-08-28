package com.siqi_dangjian.bean;

import javax.persistence.*;

@Entity
@Table(name = "conclusion_Category")
public class Conclusion_Category extends BaseBean{

    /**
     * 类别名称
     */
    @Column(name="name",length = 30,nullable = false)
    public String name;

    /**
     * 类别编码
     */
    @Column(name="category_code",length = 30,nullable = false)
    public String category_code;


    /**
     * 上级类别编码
     */
    @Column(name="parent_code",length = 30,nullable = true)
    public String parent_code;

    /**
     * 分类等级,
     * 1代表一级大类（例如衣服，食品，电子产品）
     */
    @Column(name="level",length = 1,nullable = false)
    public int level;


    public String getParent_code() {
        return parent_code;
    }

    public void setParent_code(String parent_code) {
        this.parent_code = parent_code;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory_code() {
        return category_code;
    }

    public void setCategory_code(String category_code) {
        this.category_code = category_code;
    }
}
