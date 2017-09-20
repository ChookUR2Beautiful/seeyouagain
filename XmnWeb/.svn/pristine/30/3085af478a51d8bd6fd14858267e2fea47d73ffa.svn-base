package com.xmniao.xmn.core.business_statistics.entity;

import com.xmniao.xmn.core.base.BaseEntity;

/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TSaasTag
 * 
 * 类描述： SaaS标签选项实体类
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-12-9 下午6:13:03 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TSaasTag  extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = -2355186751420789250L;

	private Long id;//主键

    private Long tagCategoryId;//标签分类表

    private String name;//标签选项名
    
    private String filterVal;//过滤值
    
    private Long type;//标签类型 1:模板标签  2:主播标签 3:名嘴标签

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTagCategoryId() {
        return tagCategoryId;
    }

    public void setTagCategoryId(Long tagCategoryId) {
        this.tagCategoryId = tagCategoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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
	
	

	/**
	 * @return the type
	 */
	public Long getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(Long type) {
		this.type = type;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TSaasTag [id=" + id + ", tagCategoryId=" + tagCategoryId
				+ ", name=" + name + ", filterVal=" + filterVal + ", type="
				+ type + "]";
	}
    
    
}