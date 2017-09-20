package com.xmniao.xmn.core.business_statistics.entity;

import com.xmniao.xmn.core.base.BaseEntity;

/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TSaasTagCategory
 * 
 * 类描述： SaaS商户端标签分类实体类
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-12-9 下午1:52:34 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TSaasTagCategory extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = 9135756543779312004L;

	private Long id;//主键

    private Integer type;//使用类型 1:模板标签  2:主播标签 3:名嘴标签

    private String name;//标签分类名

    private Integer order;//排序号
    
    private String tagNames;//标签选项名称
    
    private String tagIds;//标签选项Ids
    
    private String tagVals;//标签选项键值对(1_财经圈,2_时尚圈)

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

	/**
	 * @return the tagNames
	 */
	public String getTagNames() {
		return tagNames;
	}

	/**
	 * @param tagNames the tagNames to set
	 */
	public void setTagNames(String tagNames) {
		this.tagNames = tagNames;
	}
	
	

	/**
	 * @return the tagIds
	 */
	public String getTagIds() {
		return tagIds;
	}

	/**
	 * @param tagIds the tagIds to set
	 */
	public void setTagIds(String tagIds) {
		this.tagIds = tagIds;
	}
	
	

	/**
	 * @return the tagVals
	 */
	public String getTagVals() {
		return tagVals;
	}

	/**
	 * @param tagVals the tagVals to set
	 */
	public void setTagVals(String tagVals) {
		this.tagVals = tagVals;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TSaasTagCategory [id=" + id + ", type=" + type + ", name="
				+ name + ", order=" + order + ", tagNames=" + tagNames
				+ ", tagIds=" + tagIds + ", tagVals=" + tagVals + "]";
	}
    
    
}