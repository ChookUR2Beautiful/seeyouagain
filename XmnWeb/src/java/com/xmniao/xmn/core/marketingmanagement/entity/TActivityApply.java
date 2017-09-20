package com.xmniao.xmn.core.marketingmanagement.entity;

import java.util.Date;

import com.xmniao.xmn.core.base.BaseEntity;

public class TActivityApply extends BaseEntity{
	private static final long serialVersionUID = 1L;

	private Integer id;

    private Integer sellerid;

    private Integer type;

    private Integer activityId;

    private String eescription;

    private String phone;

    private Integer status;

    private String reason;

    private Date updateDate;

    private Date sdate;

    private Date edate;
    
    /*
     * 批量操作，申请ID
     */
    private String ids;

    private String sellername;
    
    //活动名称
    private String activityName;
    
    //申请时间
    private Date sdateStart;
    
    private Date sdateEnd;
    
    //审核时间
    private Date edateStart;
    
    private Date edateEnd;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSellerid() {
        return sellerid;
    }

    public void setSellerid(Integer sellerid) {
        this.sellerid = sellerid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getEescription() {
        return eescription;
    }

    public void setEescription(String eescription) {
        this.eescription = eescription == null ? null : eescription.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Date getSdate() {
        return sdate;
    }

    public void setSdate(Date sdate) {
        this.sdate = sdate;
    }

    public Date getEdate() {
        return edate;
    }

    public void setEdate(Date edate) {
        this.edate = edate;
    }
    
	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	
	public String getSellername() {
		return sellername;
	}

	public void setSellername(String sellername) {
		this.sellername = sellername;
	}

	
	public Date getSdateStart() {
		return sdateStart;
	}

	public void setSdateStart(Date sdateStart) {
		this.sdateStart = sdateStart;
	}

	public Date getSdateEnd() {
		return sdateEnd;
	}

	public void setSdateEnd(Date sdateEnd) {
		this.sdateEnd = sdateEnd;
	}

	public Date getEdateStart() {
		return edateStart;
	}

	public void setEdateStart(Date edateStart) {
		this.edateStart = edateStart;
	}

	public Date getEdateEnd() {
		return edateEnd;
	}

	public void setEdateEnd(Date edateEnd) {
		this.edateEnd = edateEnd;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	/**
	 * 列表展示，该行是否禁止复选框可选
	 */
	public boolean isDisableCheck() {
		return status==0?false:true;
	}

	@Override
	public String toString() {
		return "TActivityApply [id=" + id + ", sellerid=" + sellerid
				+ ", type=" + type + ", activityId=" + activityId
				+ ", eescription=" + eescription + ", phone=" + phone
				+ ", status=" + status + ", reason=" + reason + ", updateDate="
				+ updateDate + ", sdate=" + sdate + ", edate=" + edate
				+ ", ids=" + ids + ", sellername=" + sellername
				+ ", activityName=" + activityName + ", sdateStart="
				+ sdateStart + ", sdateEnd=" + sdateEnd + ", edateStart="
				+ edateStart + ", edateEnd=" + edateEnd + "]";
	}

}