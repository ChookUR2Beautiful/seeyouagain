package com.xmniao.xmn.core.businessman.entity;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.xmniao.xmn.core.base.BaseEntity;

/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：FreetryRecord
 * 
 * 类描述： 免费尝新活动记录
 * 
 * 创建人： chenJie
 * 
 * 创建时间：2016年12月10日 下午2:06:23 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class FreetryRecord extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = -1180953181172733371L;

	private Integer id;

    private Integer activityId;

    private Integer cid;
    
    private String cname;

    private String serialNo;

    private Date getTime;
    
    private Date sTime;
    
    private Date eTime;

    private Integer uid;

    private String phone;

    private Integer useStatus;

    private Date useTime;
    
    private Date sTimeStr;
    
    private Date eTimeStr;
    
    private Integer isBinding;

    private Integer isShare;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo == null ? null : serialNo.trim();
    }
    
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    public Date getGetTime() {
        return getTime;
    }

    public void setGetTime(Date getTime) {
        this.getTime = getTime;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Integer getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(Integer useStatus) {
        this.useStatus = useStatus;
    }
    
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    public Date getUseTime() {
        return useTime;
    }

    public void setUseTime(Date useTime) {
        this.useTime = useTime;
    }

    public Integer getIsBinding() {
        return isBinding;
    }

    public void setIsBinding(Integer isBinding) {
        this.isBinding = isBinding;
    }

    public Integer getIsShare() {
        return isShare;
    }

    public void setIsShare(Integer isShare) {
        this.isShare = isShare;
    }

	public Date getsTime() {
		return sTime;
	}

	public void setsTime(Date sTime) {
		this.sTime = sTime;
	}

	public Date geteTime() {
		return eTime;
	}

	public void seteTime(Date eTime) {
		this.eTime = eTime;
	}
	
	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public Date getsTimeStr() {
		return sTimeStr;
	}

	public void setsTimeStr(Date sTimeStr) {
		this.sTimeStr = sTimeStr;
	}

	public Date geteTimeStr() {
		return eTimeStr;
	}

	public void seteTimeStr(Date eTimeStr) {
		this.eTimeStr = eTimeStr;
	}

	@Override
	public String toString() {
		return "FreetryRecord [id=" + id + ", activityId=" + activityId
				+ ", cid=" + cid + ", serialNo=" + serialNo + ", getTime="
				+ getTime + ", sTime=" + sTime + ", eTime=" + eTime + ", uid="
				+ uid + ", phone=" + phone + ", useStatus=" + useStatus
				+ ", useTime=" + useTime + ", isBinding=" + isBinding
				+ ", isShare=" + isShare + "]";
	}
    
}