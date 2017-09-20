package com.xmniao.xmn.core.live_anchor.entity;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.xmniao.xmn.core.base.BaseEntity;

public class TFloatAdvert extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = -1615081158156490889L;

	private Integer id;

    private Integer cid;

    private Date startTime;

    private Date endTime;

    private String picUrl;

    private String anchorIds;
    
    private Integer status;

    private Date updateTime;
    
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}


	/* ******************自定义内容区域begin*************************/
    private String cname;
    
    private String nickname;//主播用户昵称
    
    private String statusDesc;
    
	private Object[] array;// 批量跟新时id集合
    
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}
	
	public String getStatusDesc() {
		return statusDesc;
	}

	public Object[] getArray() {
		return array;
	}

	public void setArray(Object[] array) {
		this.array = array;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	

	
	 /* ******************自定义内容区域end*************************/

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
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

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

    public String getAnchorIds() {
        return anchorIds;
    }

    public void setAnchorIds(String anchorIds) {
        this.anchorIds = anchorIds == null ? null : anchorIds.trim();
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
		return "TFloatAdvert [id=" + id + ", cid=" + cid + ", startTime="
				+ startTime + ", endTime=" + endTime + ", picUrl=" + picUrl
				+ ", anchorIds=" + anchorIds + ", status=" + status
				+ ", updateTime=" + updateTime + ", cname=" + cname
				+ ", nickname=" + nickname + ", statusDesc=" + statusDesc + "]";
	}

    
    
}