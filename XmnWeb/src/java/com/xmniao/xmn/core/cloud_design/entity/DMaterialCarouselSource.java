package com.xmniao.xmn.core.cloud_design.entity;

import java.util.Date;

import com.xmniao.xmn.core.base.BaseEntity;

/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：DMaterialCarouselSource
 * 
 * 类描述： 在此处添加类描述 TODO 
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-11-24 下午9:32:09 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class DMaterialCarouselSource extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = 3801461229785579680L;

	private Long id;//业务主键

    private Long materialId;//物料id

    private Long materialCarouselId;//模板id

    private String picUrl;//图片地址

    private String remark;//描述

    private Integer orderVal;//排序号

    private String creater;//创建人

    private String updater;//更新人

    private Date createTime;//创建时间

    private Date updateTime;//更新时间

    private String type;//框架类型（图片：001，文字：002）

    private String fontCoordinate;//字体坐标（x，y）

    private String imgCoordinate;//图片坐标（x,y）

    private String imgSize;//图片大小(像素:40X40)

    private String fontSize;//字体大小（例如：12）

    private String font;//字体（微软雅黑）

    private Integer fontNum;//字体数量

    private String fontText;//文字内容

    private String imgType;//图片类型（banner图：001，模板图：002

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

    public Long getMaterialCarouselId() {
        return materialCarouselId;
    }

    public void setMaterialCarouselId(Long materialCarouselId) {
        this.materialCarouselId = materialCarouselId;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl == null ? null : picUrl.trim();
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getFontCoordinate() {
        return fontCoordinate;
    }

    public void setFontCoordinate(String fontCoordinate) {
        this.fontCoordinate = fontCoordinate == null ? null : fontCoordinate.trim();
    }

    public String getImgCoordinate() {
        return imgCoordinate;
    }

    public void setImgCoordinate(String imgCoordinate) {
        this.imgCoordinate = imgCoordinate == null ? null : imgCoordinate.trim();
    }

    public String getImgSize() {
        return imgSize;
    }

    public void setImgSize(String imgSize) {
        this.imgSize = imgSize == null ? null : imgSize.trim();
    }

    public String getFontSize() {
        return fontSize;
    }

    public void setFontSize(String fontSize) {
        this.fontSize = fontSize == null ? null : fontSize.trim();
    }

    public String getFont() {
        return font;
    }

    public void setFont(String font) {
        this.font = font == null ? null : font.trim();
    }

    public Integer getFontNum() {
        return fontNum;
    }

    public void setFontNum(Integer fontNum) {
        this.fontNum = fontNum;
    }

    public String getFontText() {
        return fontText;
    }

    public void setFontText(String fontText) {
        this.fontText = fontText == null ? null : fontText.trim();
    }

    public String getImgType() {
        return imgType;
    }

    public void setImgType(String imgType) {
        this.imgType = imgType == null ? null : imgType.trim();
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DMaterialCarouselSource [id=" + id + ", materialId="
				+ materialId + ", materialCarouselId=" + materialCarouselId
				+ ", picUrl=" + picUrl + ", remark=" + remark + ", orderVal="
				+ orderVal + ", creater=" + creater + ", updater=" + updater
				+ ", createTime=" + createTime + ", updateTime=" + updateTime
				+ ", type=" + type + ", fontCoordinate=" + fontCoordinate
				+ ", imgCoordinate=" + imgCoordinate + ", imgSize=" + imgSize
				+ ", fontSize=" + fontSize + ", font=" + font + ", fontNum="
				+ fontNum + ", fontText=" + fontText + ", imgType=" + imgType
				+ "]";
	}
    
    
}