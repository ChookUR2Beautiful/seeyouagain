package com.xmn.designer.entity.material;

import java.util.List;

public class MaterialAttr {
    private Long id;

    private Long materialId;

    private Long categoryId;

    private Long categoryAttrId;

    private String name;

    private Integer sortVal;

    private String picUrl;
    
    private List<MaterialAttrVal> subList;
    

    public List<MaterialAttrVal> getSubList() {
        return subList;
    }

    public void setSubList(List<MaterialAttrVal> subList) {
        this.subList = subList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getCategoryAttrId() {
        return categoryAttrId;
    }

    public void setCategoryAttrId(Long categoryAttrId) {
        this.categoryAttrId = categoryAttrId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getSortVal() {
        return sortVal;
    }

    public void setSortVal(Integer sortVal) {
        this.sortVal = sortVal;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl == null ? null : picUrl.trim();
    }
}