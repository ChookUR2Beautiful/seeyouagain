package com.xmniao.xmn.core.cloud_design.entity;

import com.xmniao.xmn.core.base.BaseEntity;

/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：DMaterialCategoryAttrVal
 * 
 * 类描述： 物料规格细项
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-9-30 上午10:57:36 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class DMaterialCategoryAttrVal extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = -2794668292448380635L;

	private Long id;//主键

    private Long categoryAttrId;//对应商品分类属性(规格)id

    private String val;//属性值

    private Integer sortVal;//排序号
    
    private String filterVal;//过滤值
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCategoryAttrId() {
        return categoryAttrId;
    }

    public void setCategoryAttrId(Long categoryAttrId) {
        this.categoryAttrId = categoryAttrId;
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
		return "DMaterialCategoryAttrVal [id=" + id + ", categoryAttrId="
				+ categoryAttrId + ", val=" + val + ", sortVal=" + sortVal
				+ ", filterVal=" + filterVal + "]";
	}
    
    
}