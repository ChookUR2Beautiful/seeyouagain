package com.xmniao.xmn.core.market.entity.pay;

import java.util.Date;

/**
 * 
* @projectName: xmnService 
* @ClassName: FreshActivityCommon    
* @Description:通用活动实体   
* @author: liuzhihao   
* @date: 2016年12月22日 下午5:33:42
 */
public class FreshActivityCommon {
    private Integer id;

    private String title;

    private String img;

    private Integer type;

    private String url;

    private Integer status;
    
    private Integer orderval;
  
    private String remark;

    private Date beginDate;

    private Date endDate;

    private Date endTime;

    private Date createTime;

    private Date updateTime;
    
    private Long spikeId;//秒杀活动ID
    
    private Integer orderLimit;//秒杀活动限制购买数量 0 或 null 表示不限制购买
    
    private Integer labelId;//标签ID

    private Long distStartTime;
    private Long distEndTime;

    public Long getDistStartTime() {
        return distStartTime;
    }

    public void setDistStartTime(Long distStartTime) {
        this.distStartTime = distStartTime;
    }

    public Long getDistEndTime() {
        return distEndTime;
    }

    public void setDistEndTime(Long distEndTime) {
        this.distEndTime = distEndTime;
    }

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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    
    public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

    public Integer getOrderval() {
		return orderval;
	}

	public void setOrderval(Integer orderval) {
		this.orderval = orderval;
	}

	public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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

	public Long getSpikeId() {
		return spikeId;
	}

	public void setSpikeId(Long spikeId) {
		this.spikeId = spikeId;
	}

	public Integer getOrderLimit() {
		return orderLimit;
	}

	public void setOrderLimit(Integer orderLimit) {
		this.orderLimit = orderLimit;
	}

	public Integer getLabelId() {
		return labelId;
	}

	public void setLabelId(Integer labelId) {
		this.labelId = labelId;
	}

	@Override
	public String toString() {
		return "FreshActivityCommon [id=" + id + ", title=" + title + ", img=" + img + ", type=" + type + ", url=" + url
			+ ", status=" + status + ", orderval=" + orderval + ", remark=" + remark + ", beginDate=" + beginDate + ", endDate="
			+ endDate + ", endTime=" + endTime + ", createTime=" + createTime + ", updateTime=" + updateTime + ", spikeId="
			+ spikeId + ", orderLimit=" + orderLimit + ", labelId=" + labelId + "]";
	}
    
}