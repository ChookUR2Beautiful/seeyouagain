package com.xmniao.xmn.core.xmermanagerment.entity;

import java.util.Date;

/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：BLevel
 * 
 * 类描述： 寻蜜客等级
 * 
 * 创建人： Chen Bo
 * 
 * 创建时间：2016年7月13日 下午6:14:11 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class BLevel {
    private Integer id;

    private Integer levelNo;

    private Integer signNum;

    private String levelName;

    private Date sdate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLevelNo() {
        return levelNo;
    }

    public void setLevelNo(Integer levelNo) {
        this.levelNo = levelNo;
    }

    public Integer getSignNum() {
        return signNum;
    }

    public void setSignNum(Integer signNum) {
        this.signNum = signNum;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName == null ? null : levelName.trim();
    }

    public Date getSdate() {
        return sdate;
    }

    public void setSdate(Date sdate) {
        this.sdate = sdate;
    }
}