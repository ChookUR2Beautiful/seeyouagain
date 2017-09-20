package com.xmniao.xmn.core.cloud_design.entity;

import java.util.Date;

import com.xmniao.xmn.core.base.BaseEntity;

/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：DMaterialCategoryAttrRelation
 * 
 * 类描述： 物料分类、规格关联关系实体类
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-11-17 下午5:38:22 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class DMaterialCategoryAttrRelation extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = -5911583110927932674L;

	private Long id;//业务主键

    private Long categoryId;//物料分类ID

    private Long materialAttrId;//物料规格ID

    private String materialAttrVal;//物料规格名称
    
    private String categoryVal;//物料分类名称
    
    private Date createTime;//创建时间

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
    
    

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DMaterialCategoryAttrRelation [id=" + id + ", categoryId="
				+ categoryId + ", materialAttrId=" + materialAttrId
				+ ", materialAttrVal=" + materialAttrVal + ", categoryVal="
				+ categoryVal + ", createTime=" + createTime + "]";
	}
    
    
}