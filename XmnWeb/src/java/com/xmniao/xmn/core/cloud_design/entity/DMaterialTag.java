package com.xmniao.xmn.core.cloud_design.entity;

import com.xmniao.xmn.core.base.BaseEntity;


/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：DMaterialTag
 * 
 * 类描述： 物料标签实体类
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-9-27 下午4:51:28 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class DMaterialTag extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = -5841188667778568921L;

	private Long id;//主键

    private String name;//名称

    private Long parentId;//父主键,自关联

    private Integer level;//层级

    private String layers;//层级字符串，父layers + 自身id转化字符串

    private Integer orderVal;//排序号

    private String picUrl;//图片地址

    private String isRecommend;//是否推荐（001：推荐，002：未推荐）
    
    private String filterVal;//过滤值

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
		return "DMaterialTag [id=" + id + ", name=" + name + ", parentId="
				+ parentId + ", level=" + level + ", layers=" + layers
				+ ", orderVal=" + orderVal + ", picUrl=" + picUrl
				+ ", isRecommend=" + isRecommend + ", filterVal=" + filterVal
				+ "]";
	}
    
    
}