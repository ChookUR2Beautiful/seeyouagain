package com.xmniao.xmn.core.business_statistics.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.xmniao.xmn.core.base.BaseEntity;

/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TMicrographModule
 * 
 * 类描述： 微图助力页面模块实体类
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-12-14 下午1:51:21 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TMicrographModule extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = -113984141597929008L;

	private Integer id;//主键

    private Integer pageId;//模板页面主键

    private Integer type;//模块类型 0:文字 1:图片

    private Long leftStyle;//模块左边距(百分比)

    private Long top;//模块顶边距(百分比)

    private BigDecimal width;//宽度(百分比)

    private BigDecimal height;//高度(百分比)

    private String remark;//描述

    private String image;//图片
    
    private String picUrl;//图片地址

    private Integer fontSize;//字体大小

    private Integer fontNum;//字数

    private Date createTime;//创建时间

    private Date updateTime;//更新时间

    private Integer status;//状态 0:正常 1:删除
    
    private String fontCoordinate;//字体坐标（x，y）

    private String imgCoordinate;//图片坐标（x,y）

    private String imgSize;//图片大小(像素:40X40)
    
    private Integer sortVal;//排序值

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPageId() {
        return pageId;
    }

    public void setPageId(Integer pageId) {
        this.pageId = pageId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getLeftStyle() {
        return leftStyle;
    }

    public void setLeftStyle(Long leftStyle) {
        this.leftStyle = leftStyle;
    }

    public Long getTop() {
        return top;
    }

    public void setTop(Long top) {
        this.top = top;
    }

    public BigDecimal getWidth() {
        return width;
    }

    public void setWidth(BigDecimal width) {
        this.width = width;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
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

	public Integer getFontSize() {
        return fontSize;
    }

    public void setFontSize(Integer fontSize) {
        this.fontSize = fontSize;
    }

    public Integer getFontNum() {
        return fontNum;
    }

    public void setFontNum(Integer fontNum) {
        this.fontNum = fontNum;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

	/**
	 * @return the fontCoordinate
	 */
	public String getFontCoordinate() {
		return fontCoordinate;
	}

	/**
	 * @param fontCoordinate the fontCoordinate to set
	 */
	public void setFontCoordinate(String fontCoordinate) {
		this.fontCoordinate = fontCoordinate;
	}

	/**
	 * @return the imgCoordinate
	 */
	public String getImgCoordinate() {
		return imgCoordinate;
	}

	/**
	 * @param imgCoordinate the imgCoordinate to set
	 */
	public void setImgCoordinate(String imgCoordinate) {
		this.imgCoordinate = imgCoordinate;
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
	 * @return the sortVal
	 */
	public Integer getSortVal() {
		return sortVal;
	}

	/**
	 * @param sortVal the sortVal to set
	 */
	public void setSortVal(Integer sortVal) {
		this.sortVal = sortVal;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TMicrographModule [id=" + id + ", pageId=" + pageId + ", type="
				+ type + ", leftStyle=" + leftStyle + ", top=" + top
				+ ", width=" + width + ", height=" + height + ", remark="
				+ remark + ", image=" + image + ", fontSize=" + fontSize
				+ ", fontNum=" + fontNum + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + ", status=" + status
				+ ", fontCoordinate=" + fontCoordinate + ", imgCoordinate="
				+ imgCoordinate + ", imgSize=" + imgSize + ", sortVal="
				+ sortVal + "]";
	}
    
    
    
    
}