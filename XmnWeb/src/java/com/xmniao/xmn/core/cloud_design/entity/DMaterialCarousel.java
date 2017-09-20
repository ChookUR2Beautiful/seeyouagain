package com.xmniao.xmn.core.cloud_design.entity;

import java.util.Date;
import java.util.List;

import com.xmniao.xmn.core.base.BaseEntity;


/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：DMaterialCarousel
 * 
 * 类描述： 物料模板实体类
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-11-24 下午9:19:05 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class DMaterialCarousel extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = -1578518645714946116L;

	private Long id;//业务主键

    private Long materialId;//物料id

    private String remark;//描述

    private Integer orderVal;//排序号

    private String creater;//创建人

    private String updater;//更新人

    private Date createTime;//创建时间

    private Date updateTime;//更新时间
    
    private String width;//背景宽度
    
    private String height;//背景高度
    
    private String picUrl;//背景图片地址
    
    private String imgSize;//背景图大小
    
    private List<DMaterialCarouselSource> source;//模板元数据List

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getOrderVal() {
        return orderVal;
    }

    public void setOrderVal(Integer orderVal) {
        this.orderVal = orderVal;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater == null ? null : creater.trim();
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater == null ? null : updater.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    
    
    

	/**
	 * @return the width
	 */
	public String getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(String width) {
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public String getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(String height) {
		this.height = height;
	}

	/**
	 * @return the picUrl
	 */
	public String getPicUrl() {
		return picUrl;
	}

	/**
	 * @param picUrl the picUrl to set
	 */
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	
	

	/**
	 * @return the imgSize
	 */
	public String getImgSize() {
		return imgSize;
	}

	/**
	 * @param imgSize the imgSize to set
	 */
	public void setImgSize(String imgSize) {
		this.imgSize = imgSize;
	}

	/**
	 * @return the source
	 */
	public List<DMaterialCarouselSource> getSource() {
		return source;
	}

	/**
	 * @param source the source to set
	 */
	public void setSource(List<DMaterialCarouselSource> source) {
		this.source = source;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DMaterialCarousel [id=" + id + ", materialId=" + materialId
				+ ", remark=" + remark + ", orderVal=" + orderVal
				+ ", creater=" + creater + ", updater=" + updater
				+ ", createTime=" + createTime + ", updateTime=" + updateTime
				+ ", width=" + width + ", height=" + height + ", picUrl="
				+ picUrl + ", imgSize=" + imgSize + ", source=" + source + "]";
	}
    
    
}