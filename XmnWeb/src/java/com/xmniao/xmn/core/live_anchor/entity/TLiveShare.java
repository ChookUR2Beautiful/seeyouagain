package com.xmniao.xmn.core.live_anchor.entity;

import java.util.Date;

import com.xmniao.xmn.core.base.BaseEntity;


/**
 * 
 * 
 * 项目名称：XmnWeb_LIVE_170105
 * 
 * 类名称：TLiveShare
 * 
 * 类描述： 直播邀请分享实体类
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-12-21 下午6:36:18 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TLiveShare extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = -4299090379709814573L;

	private Long id;//业务主键

    private String title;//分享标题

    private String content;//分享内容

    private String position;//分享链接位置：001 商家详情，002 直播间，003 首页， 004商家列表页

    private String pageType;//分享页面类型：001 下载页， 002 已有页面，003 外部链接

    private String pageContent;//分享页面：001 商家详情页，002 直播分享页 ，003 回放分享页 ，004 寻蜜客邀请页， 005 积分商品详情页 

    private String shareLink;//分享链接

    private String appType;//应用类型：001 用户端，002 微信端
    
    private String picUrl;//图片URL
    
    private String status;

    private Date createTime;//创建时间

    private Date updateTime;//更新时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    public String getPageType() {
        return pageType;
    }

    public void setPageType(String pageType) {
        this.pageType = pageType == null ? null : pageType.trim();
    }

    public String getPageContent() {
        return pageContent;
    }

    public void setPageContent(String pageContent) {
        this.pageContent = pageContent == null ? null : pageContent.trim();
    }

    public String getShareLink() {
        return shareLink;
    }

    public void setShareLink(String shareLink) {
        this.shareLink = shareLink == null ? null : shareLink.trim();
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType == null ? null : appType.trim();
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
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TLiveShare [id=" + id + ", title=" + title + ", content="
				+ content + ", position=" + position + ", pageType=" + pageType
				+ ", pageContent=" + pageContent + ", shareLink=" + shareLink
				+ ", appType=" + appType + ", picUrl=" + picUrl + ", status="
				+ status + ", createTime=" + createTime + ", updateTime="
				+ updateTime + "]";
	}
    
    
}