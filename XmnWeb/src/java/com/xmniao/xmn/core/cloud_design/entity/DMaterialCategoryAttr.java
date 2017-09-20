package com.xmniao.xmn.core.cloud_design.entity;

import com.xmniao.xmn.core.base.BaseEntity;

/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：DMaterialCategoryAttr
 * 
 * 类描述： 物料分类属性(物料规格)实体类
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-9-29 下午5:51:49 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class DMaterialCategoryAttr extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1459730007445863803L;

	private Long id;//主键

    private String categoryIds;//关联分类Ids
    
    private String categoryVals;//关联分类键值对

    private String name;//属性名

    private Integer sortVal;//排序值

    private String picUrl;//图片地址

    private String isCustomize;//是否定制（001定制；002非定制）

    private String isMultiple;//是否多选（001 多选 ；002 单选）,只有定制规格才有是否多选
    
    private String isCustomizable;//是否可自定义规格 (001可自定义 | 002不可自定义)
    
    private String attrVals; //规格细项，以"、"分割，如：玫瑰红、玫瑰金
    
    private String attrValIds; //规格细项Ids，以"，"分割

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    


    /**
	 * @return the categoryIds
	 */
	public String getCategoryIds() {
		return categoryIds;
	}

	/**
	 * @param categoryIds the categoryIds to set
	 */
	public void setCategoryIds(String categoryIds) {
		this.categoryIds = categoryIds;
	}

	
	
	/**
	 * @return the categoryVals
	 */
	public String getCategoryVals() {
		return categoryVals;
	}

	/**
	 * @param categoryVals the categoryVals to set
	 */
	public void setCategoryVals(String categoryVals) {
		this.categoryVals = categoryVals;
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

    

    /**
	 * @return the isCustomize
	 */
	public String getIsCustomize() {
		return isCustomize;
	}

	/**
	 * @param isCustomize the isCustomize to set
	 */
	public void setIsCustomize(String isCustomize) {
		this.isCustomize = isCustomize;
	}

	public String getIsMultiple() {
        return isMultiple;
    }

    public void setIsMultiple(String isMultiple) {
        this.isMultiple = isMultiple == null ? null : isMultiple.trim();
    }
    
    

	/**
	 * @return the isCustomizable
	 */
	public String getIsCustomizable() {
		return isCustomizable;
	}

	/**
	 * @param isCustomizable the isCustomizable to set
	 */
	public void setIsCustomizable(String isCustomizable) {
		this.isCustomizable = isCustomizable;
	}

	/**
	 * @return the attrVals
	 */
	public String getAttrVals() {
		return attrVals;
	}

	/**
	 * @param attrVals the attrVals to set
	 */
	public void setAttrVals(String attrVals) {
		this.attrVals = attrVals;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DMaterialCategoryAttr [id=" + id + ", categoryIds="
				+ categoryIds + ", categoryVals=" + categoryVals + ", name="
				+ name + ", sortVal=" + sortVal + ", picUrl=" + picUrl
				+ ", isCustomize=" + isCustomize + ", isMultiple=" + isMultiple
				+ ", isCustomizable=" + isCustomizable + ", attrVals="
				+ attrVals + ", attrValIds=" + attrValIds + "]";
	}
    
    
}