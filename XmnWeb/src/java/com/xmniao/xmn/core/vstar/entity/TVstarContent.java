package com.xmniao.xmn.core.vstar.entity;

import java.util.Date;
import java.util.List;

import org.springframework.web.util.HtmlUtils;

import com.xmniao.xmn.core.base.BaseEntity;


/**
 * 
 * 
 * 项目名称：XmnWeb_vstar
 * 
 * 类名称：TVstarContent
 * 
 * 类描述： V客学堂
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2017-6-23 下午5:46:34 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TVstarContent extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = 5521406659942023882L;

	private Long id;//V客学堂资源内容ID

    private String title;//教学标题

    private String viceTitle;//副标题

    private Long vstarDictId;//教学分类ID(t_vstar_dict)

    private String coverImg;//封面图片

    private String shareImg;//分享图标

    private String shareTitle;//分享标题

    private String shareDescription;//分享描述

    private String activityUrl;//活动链接

    private Integer sortVal;//0-999，数量越大排列越前

    private Integer status;//有效状态，1有效，2无效

    private Integer contentType;//资源内容类型,1内容资料,2 H5活动,3图片素材

    private Date createTime;//创建时间
    
    private String fileIds;
    
    private String videoIds;
    
    private List<TVstarContentAttachment> contentAttachments;
    
	public List<TVstarContentAttachment> getContentAttachments() {
		return contentAttachments;
	}

	public void setContentAttachments(List<TVstarContentAttachment> contentAttachments) {
		this.contentAttachments = contentAttachments;
	}

	public String getFileIds() {
		return fileIds;
	}

	public void setFileIds(String fileIds) {
		this.fileIds = fileIds;
	}

	public String getVideoIds() {
		return videoIds;
	}

	public void setVideoIds(String videoIds) {
		this.videoIds = videoIds;
	}

    private Date updateTime;//更新时间

    private String contentText;//教学内容
    
    private Integer viewNum;//观看人数
    
    private Integer payNum;//付费人数

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

    public String getViceTitle() {
        return viceTitle;
    }

    public void setViceTitle(String viceTitle) {
        this.viceTitle = viceTitle == null ? null : viceTitle.trim();
    }

    public Long getVstarDictId() {
        return vstarDictId;
    }

    public void setVstarDictId(Long vstarDictId) {
        this.vstarDictId = vstarDictId;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg == null ? null : coverImg.trim();
    }

    public String getShareImg() {
        return shareImg;
    }

    public void setShareImg(String shareImg) {
        this.shareImg = shareImg == null ? null : shareImg.trim();
    }

    public String getShareTitle() {
        return shareTitle;
    }

    public void setShareTitle(String shareTitle) {
        this.shareTitle = shareTitle == null ? null : shareTitle.trim();
    }

    public String getShareDescription() {
        return shareDescription;
    }

    public void setShareDescription(String shareDescription) {
        this.shareDescription = shareDescription == null ? null : shareDescription.trim();
    }

    public String getActivityUrl() {
        return activityUrl;
    }

    public void setActivityUrl(String activityUrl) {
        this.activityUrl = activityUrl == null ? null : activityUrl.trim();
    }

    public Integer getSortVal() {
        return sortVal;
    }

    public void setSortVal(Integer sortVal) {
        this.sortVal = sortVal;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getContentType() {
        return contentType;
    }

    public void setContentType(Integer contentType) {
        this.contentType = contentType;
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

    public String getContentText() {
    	return HtmlUtils.htmlUnescape(contentText);
    }

    public void setContentText(String contentText) {
        this.contentText = contentText == null ? null : contentText.trim();
    }

	/**
	 * @return the viewNum
	 */
	public Integer getViewNum() {
		return viewNum;
	}

	/**
	 * @param viewNum the viewNum to set
	 */
	public void setViewNum(Integer viewNum) {
		this.viewNum = viewNum;
	}

	/**
	 * @return the payNum
	 */
	public Integer getPayNum() {
		return payNum;
	}

	/**
	 * @param payNum the payNum to set
	 */
	public void setPayNum(Integer payNum) {
		this.payNum = payNum;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TVstarContent [id=" + id + ", title=" + title + ", viceTitle="
				+ viceTitle + ", vstarDictId=" + vstarDictId + ", coverImg="
				+ coverImg + ", shareImg=" + shareImg + ", shareTitle="
				+ shareTitle + ", shareDescription=" + shareDescription
				+ ", activityUrl=" + activityUrl + ", sortVal=" + sortVal
				+ ", status=" + status + ", contentType=" + contentType
				+ ", createTime=" + createTime + ", updateTime=" + updateTime
				+ ", contentText=" + contentText + ", viewNum=" + viewNum
				+ ", payNum=" + payNum + "]";
	}
    
    
}