package com.xmniao.xmn.core.cloud_design.entity;

import com.xmniao.xmn.core.base.BaseEntity;

/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：DMaterialCategory
 * 
 * 类描述： 物料分类实体类
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-11-16 下午6:13:45 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class DMaterialCategory extends BaseEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = -1339615720844025068L;

	private Long id;//业务主键

    private String name;//名称

    private Long parentId;//父主键,自关联(已作废)

    private Integer level;//层级(已作废)

    private String layers;//层级字符串，父layers + 自身id转化字符串(已作废)

    private Integer orderVal;//排序号

    private String picUrl;//图片地址

    private String isRecommend;//是否推荐（001：推荐，002：未推荐）
    
    private String attrIds;//关联规格ID
    
    private String attrs;//关联规格 1_颜色,2_尺寸
    
    private String tagIds;//关联规格ID
    
    private String tags;//关联标签 1_红酒,2_白酒

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getLayers() {
        return layers;
    }

    public void setLayers(String layers) {
        this.layers = layers == null ? null : layers.trim();
    }

    public Integer getOrderVal() {
        return orderVal;
    }

    public void setOrderVal(Integer orderVal) {
        this.orderVal = orderVal;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl == null ? null : picUrl.trim();
    }

    public String getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(String isRecommend) {
        this.isRecommend = isRecommend == null ? null : isRecommend.trim();
    }
    
    

	/**
	 * @return the attrs
	 */
	public String getAttrs() {
		return attrs;
	}

	/**
	 * @param attrs the attrs to set
	 */
	public void setAttrs(String attrs) {
		this.attrs = attrs;
	}

	/**
	 * @return the tags
	 */
	public String getTags() {
		return tags;
	}

	/**
	 * @param tags the tags to set
	 */
	public void setTags(String tags) {
		this.tags = tags;
	}
	
	

	/**
	 * @return the attrIds
	 */
	public String getAttrIds() {
		return attrIds;
	}

	/**
	 * @param attrIds the attrIds to set
	 */
	public void setAttrIds(String attrIds) {
		this.attrIds = attrIds;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DMaterialCategory [id=" + id + ", name=" + name + ", parentId="
				+ parentId + ", level=" + level + ", layers=" + layers
				+ ", orderVal=" + orderVal + ", picUrl=" + picUrl
				+ ", isRecommend=" + isRecommend + ", attrIds=" + attrIds
				+ ", attrs=" + attrs + ", tagIds=" + tagIds + ", tags=" + tags
				+ "]";
	}
    
    
}