package com.xmniao.xmn.core.cloud_design.entity;

import java.util.List;

import com.xmniao.xmn.core.base.BaseEntity;

/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：DMaterialAttr
 * 
 * 类描述： 物料商品规格实体类
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-11-24 下午3:53:52 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class DMaterialAttr extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = -2899250610910431397L;

	private Long id;//业务主键

    private Long materialId;//物料商品Id

    private Long categoryId;//物料分类Id

    private Long categoryAttrId;//物料规格Id
    
    private List<String> categoryAttrIdList;//d_material_attr_group物料规格分组信息规格id查询条件
    
    private String attrValIds;//规格细项ids

    private String name;//物料规格名称

    private Integer sortVal;//排序值

    private String picUrl;//图片地址

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
    
    

	/**
	 * @return the categoryAttrIdList
	 */
	public List<String> getCategoryAttrIdList() {
		return categoryAttrIdList;
	}

	/**
	 * @param categoryAttrIdList the categoryAttrIdList to set
	 */
	public void setCategoryAttrIdList(List<String> categoryAttrIdList) {
		this.categoryAttrIdList = categoryAttrIdList;
	}

	/**
	 * @return the attrValIds
	 */
	public String getAttrValIds() {
		return attrValIds;
	}

	/**
	 * @param attrValIds the attrValIds to set
	 */
	public void setAttrValIds(String attrValIds) {
		this.attrValIds = attrValIds;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DMaterialAttr [id=" + id + ", materialId=" + materialId
				+ ", categoryId=" + categoryId + ", categoryAttrId="
				+ categoryAttrId + ", name=" + name + ", sortVal=" + sortVal
				+ ", picUrl=" + picUrl + "]";
	}
    
    
}