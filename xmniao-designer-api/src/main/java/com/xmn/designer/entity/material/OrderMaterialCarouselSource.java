package com.xmn.designer.entity.material;

import java.util.Date;

public class OrderMaterialCarouselSource {
    private Long id;

    private Long materialId;

    private Long materialCarouselId;

    private String picUrl;

    private String remark;

    private Integer orderVal;

    private String creater;

    private String updater;

    private Date createTime;

    private Date updateTime;

    private String type;

    private String fontCoordinate;

    private String imgCoordinate;

    private String imgSize;

    private String fontSize;

    private String font;

    private Integer fontNum;

    private String fontText;

    private String imgType;

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
}