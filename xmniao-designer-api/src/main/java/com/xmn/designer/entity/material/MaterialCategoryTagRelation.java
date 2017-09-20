package com.xmn.designer.entity.material;

import java.util.Date;

public class MaterialCategoryTagRelation {
    private Long id;

    private Long categoryId;

    private Long materialTagId;

    private String materialTagVal;

    private Date createTime;
    
    private String categoryVal;
    
    private String picUrl;
    
    

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getCategoryVal() {
        return categoryVal;
    }

    public void setCategoryVal(String categoryVal) {
        this.categoryVal = categoryVal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getMaterialTagId() {
        return materialTagId;
    }

    public void setMaterialTagId(Long materialTagId) {
        this.materialTagId = materialTagId;
    }

    public String getMaterialTagVal() {
        return materialTagVal;
    }

    public void setMaterialTagVal(String materialTagVal) {
        this.materialTagVal = materialTagVal == null ? null : materialTagVal.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}