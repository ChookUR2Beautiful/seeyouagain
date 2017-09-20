package com.xmniao.xmn.core.business_statistics.entity;

import com.xmniao.xmn.core.base.BaseEntity;

/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TMicrographTemplateTag
 * 
 * 类描述： 模板标签关系实体类
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-12-14 下午4:08:51 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TMicrographTemplateTag extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = -4138640561104613981L;

	private Long id;//主键

    private Integer templateId;//模板id

    private Integer tagId;//标签id
    
    private String filterVal;//过滤值

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
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
		return "TMicrographTemplateTag [id=" + id + ", templateId="
				+ templateId + ", tagId=" + tagId + ", filterVal=" + filterVal
				+ "]";
	}

	
    
    
}