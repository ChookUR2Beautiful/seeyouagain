package com.xmniao.xmn.core.business_statistics.entity;

import com.xmniao.xmn.core.base.BaseEntity;


/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TCelebrityAriticleCarousel
 * 
 * 类描述： SaaS网红文章相册实体类
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-12-12 上午11:13:10 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TCelebrityAriticleCarousel extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = 4077034126303957310L;

	private Long id;//业务主键

    private Long celebrityAriticleId;//网红文章主键

    private String image;//文章主图

    private Integer order;//排序号

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCelebrityAriticleId() {
        return celebrityAriticleId;
    }

    public void setCelebrityAriticleId(Long celebrityAriticleId) {
        this.celebrityAriticleId = celebrityAriticleId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TCelebrityAriticleCarousel [id=" + id
				+ ", celebrityAriticleId=" + celebrityAriticleId + ", image="
				+ image + ", order=" + order + "]";
	}
    
    
}