package com.xmn.saas.entity.micrograph;

import java.math.BigDecimal;
import java.util.Date;

public class MicrographModule {
    private Integer id;

    private Integer pageId;

    private Integer type;

    private BigDecimal width;

    private BigDecimal height;

    private String remark;

    private String image;

    private Integer fontSize;

    private Integer fontNum;

    private Date createTime;

    private Date updateTime;

    private Integer status;
    
    private BigDecimal leftStyle;
    
    private BigDecimal top;
    

	public BigDecimal getLeftStyle() {
		return leftStyle;
	}

	public void setLeftStyle(BigDecimal leftStyle) {
		this.leftStyle = leftStyle;
	}

	public BigDecimal getTop() {
		return top;
	}

	public void setTop(BigDecimal top) {
		this.top = top;
	}

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
}