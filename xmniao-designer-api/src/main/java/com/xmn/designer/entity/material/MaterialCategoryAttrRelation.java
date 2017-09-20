package com.xmn.designer.entity.material;

import java.util.Date;

public class MaterialCategoryAttrRelation {
    private Long id;

    private Long categoryId;

    private Long materialAttrId;

    private String materialAttrVal;

    private String categoryVal;

    private Date createTime;

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

    public Long getMaterialAttrId() {
        return materialAttrId;
    }

    public void setMaterialAttrId(Long materialAttrId) {
        this.materialAttrId = materialAttrId;
    }

    public String getMaterialAttrVal() {
        return materialAttrVal;
    }

    public void setMaterialAttrVal(String materialAttrVal) {
        this.materialAttrVal = materialAttrVal == null ? null : materialAttrVal.trim();
    }

    public String getCategoryVal() {
        return categoryVal;
    }

    public void setCategoryVal(String categoryVal) {
        this.categoryVal = categoryVal == null ? null : categoryVal.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}