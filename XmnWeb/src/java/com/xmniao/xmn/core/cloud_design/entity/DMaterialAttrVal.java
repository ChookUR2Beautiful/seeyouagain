package com.xmniao.xmn.core.cloud_design.entity;

public class DMaterialAttrVal {
    private Long id;

    private Long materialAttrId;

    private Long categoryAttrValId;

    private String val;

    private Integer sortVal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMaterialAttrId() {
        return materialAttrId;
    }

    public void setMaterialAttrId(Long materialAttrId) {
        this.materialAttrId = materialAttrId;
    }

    public Long getCategoryAttrValId() {
        return categoryAttrValId;
    }

    public void setCategoryAttrValId(Long categoryAttrValId) {
        this.categoryAttrValId = categoryAttrValId;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val == null ? null : val.trim();
    }

    public Integer getSortVal() {
        return sortVal;
    }

    public void setSortVal(Integer sortVal) {
        this.sortVal = sortVal;
    }
}