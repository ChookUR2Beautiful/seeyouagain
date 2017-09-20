package com.xmniao.xmn.core.live_anchor.entity;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.xmniao.xmn.core.base.BaseEntity;

public class TLiveFloatAdvertise extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = 5214810008882322443L;

	private Integer id;

    private String title;

    private Date startTime;

    private Date endTime;

    private String redirectUrl;

    private String picUrl;

    private Integer status;

    private Integer sort;

    private Date createTime;

    private Date updateTime;
    
    private List<TLiveFloatAdvertisePosition> liveFloatAdvertisePositionList;
    
    private String showPosition;
    
    private String showPositionDesc;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }
    
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl == null ? null : redirectUrl.trim();
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl == null ? null : picUrl.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
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

	public List<TLiveFloatAdvertisePosition> getLiveFloatAdvertisePositionList() {
		return liveFloatAdvertisePositionList;
	}

	public void setLiveFloatAdvertisePositionList(List<TLiveFloatAdvertisePosition> liveFloatAdvertisePositionList) {
		this.liveFloatAdvertisePositionList = liveFloatAdvertisePositionList;
	}
	

	public String getShowPosition() {
		return showPosition;
	}

	public void setShowPosition(String showPosition) {
		this.showPosition = showPosition;
	}

	public String getShowPositionDesc() {
		return showPositionDesc;
	}

	public void setShowPositionDesc(String showPositionDesc) {
		this.showPositionDesc = showPositionDesc;
	}

	@Override
	public String toString() {
		return "TLiveFloatAdvertise [id=" + id + ", title=" + title
				+ ", startTime=" + startTime + ", endTime=" + endTime
				+ ", redirectUrl=" + redirectUrl + ", picUrl=" + picUrl
				+ ", status=" + status + ", sort=" + sort + ", createTime="
				+ createTime + ", updateTime=" + updateTime
				+ ", liveFloatAdvertisePositionList="
				+ liveFloatAdvertisePositionList + ", showPosition="
				+ showPosition + ", showPositionDesc=" + showPositionDesc + "]";
	}
    
    
    
}