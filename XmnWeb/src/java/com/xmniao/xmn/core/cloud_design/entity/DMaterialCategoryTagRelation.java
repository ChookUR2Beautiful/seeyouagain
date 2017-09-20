package com.xmniao.xmn.core.cloud_design.entity;

import com.xmniao.xmn.core.base.BaseEntity;


/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：DMaterialCategoryTagRelation
 * 
 * 类描述： 物料分类、标签关联关系实体类
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-11-18 下午3:03:47 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class DMaterialCategoryTagRelation  extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1373025999575733926L;

	private Long id;//业务主键

    private Long categoryId;//分类ID

    private Long materialTagId;//标签ID

    private String materialTagVal;//标签名称
    
    private String filterVal;//过滤值

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

	/**
	 * @return the filterVal
	 */
	public String getFilterVal() {
		return filterVal;
	}

	/**
	 * @param filterVal the filterVal to set
	 */
	public void setFilterVal(String filterVal) {
		this.filterVal = filterVal;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DMaterialCategoryTagRelation [id=" + id + ", categoryId="
				+ categoryId + ", materialTagId=" + materialTagId
				+ ", materialTagVal=" + materialTagVal + ", filterVal="
				+ filterVal + "]";
	}
    
    
}