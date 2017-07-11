package com.xmn.saas.entity.shop;

import java.util.Date;
import java.util.List;

public class LiveClassify {
    private Integer id;

    private Integer classifyType;

    private String classifyName;

    private Date updateTime;

    private List<LiveClassifyTag> tags;


    public List<LiveClassifyTag> getTags() {
        return tags;
    }

    public void setTags(List<LiveClassifyTag> tags) {
        this.tags = tags;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClassifyType() {
        return classifyType;
    }

    public void setClassifyType(Integer classifyType) {
        this.classifyType = classifyType;
    }

    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName == null ? null : classifyName.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}