package com.xmniao.xmn.core.business_statistics.entity;

import java.util.Date;

import com.xmniao.xmn.core.base.BaseEntity;
import com.xmniao.xmn.core.util.DateUtil;


/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TCelebrityAriticle
 * 
 * 类描述： SaaS网红文章实体类
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-12-12 上午10:43:34 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TCelebrityAriticle extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = 8170160284663097901L;

	private Long id;//业务主键

    private String name;//文章名称(标题)

    private Integer type;//文章分类 1:名嘴食评  2:网红晒照

    private Integer status;//文章类型 1:单页图文  2:多图相册

    private Long celebrityId;//网红id
    
    private String celebrityName;//网红名称

    private String image;//图片url

    private String sellerName;//商户名

    private Long sellerId;//商户id

    private Integer views;//查看次数

    private String describe;//描述信息

    private Date createTime;//创建时间
    
    private String createTimeStr;//创建时间
    
    private String startTime;//查询开始时间
    
    private String endTime;//查询结束时间

    private String zoneName;//商圈名

    private String htmlContent;//文章内容
    
    private String orderNo;//关联订单编号
    
    private String relativePath;//多图相册地址,多个以";"分隔

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getCelebrityId() {
        return celebrityId;
    }

    public void setCelebrityId(Long celebrityId) {
        this.celebrityId = celebrityId;
    }
    
    

    /**
	 * @return the celebrityName
	 */
	public String getCelebrityName() {
		return celebrityName;
	}

	/**
	 * @param celebrityName the celebrityName to set
	 */
	public void setCelebrityName(String celebrityName) {
		this.celebrityName = celebrityName;
	}

	public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName == null ? null : sellerName.trim();
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe == null ? null : describe.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    
    

    /**
	 * @return the createTimeStr
	 */
	public String getCreateTimeStr() {
		if(createTime==null){
			return null;
		}
		return createTimeStr=DateUtil.formatDate(createTime, DateUtil.Y_M_D_HMS);
	}

	/**
	 * @param createTimeStr the createTimeStr to set
	 */
	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}

	/**
	 * @return the startTime
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName == null ? null : zoneName.trim();
    }

    public String getHtmlContent() {
        return htmlContent;
    }

    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent == null ? null : htmlContent.trim();
    }
    
    

	/**
	 * @return the orderNo
	 */
	public String getOrderNo() {
		return orderNo;
	}

	/**
	 * @param orderNo the orderNo to set
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	

	/**
	 * @return the relativePath
	 */
	public String getRelativePath() {
		return relativePath;
	}

	/**
	 * @param relativePath the relativePath to set
	 */
	public void setRelativePath(String relativePath) {
		this.relativePath = relativePath;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TCelebrityAriticle [id=" + id + ", name=" + name + ", type="
				+ type + ", status=" + status + ", celebrityId=" + celebrityId
				+ ", celebrityName=" + celebrityName + ", image=" + image
				+ ", sellerName=" + sellerName + ", sellerId=" + sellerId
				+ ", views=" + views + ", describe=" + describe
				+ ", createTime=" + createTime + ", createTimeStr="
				+ createTimeStr + ", startTime=" + startTime + ", endTime="
				+ endTime + ", zoneName=" + zoneName + ", htmlContent="
				+ htmlContent + ", orderNo=" + orderNo + ", relativePath="
				+ relativePath + "]";
	}
    
    
}