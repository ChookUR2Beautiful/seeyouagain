package com.xmniao.xmn.core.fresh.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.xmniao.xmn.core.base.BaseEntity;
import com.xmniao.xmn.core.util.DateUtil;


/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TFreshActivityAuctionBidding
 * 
 * 类描述： 小鸟超市竞拍出价实体类
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2017-3-1 下午5:38:01 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TFreshActivityAuctionBidding extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = -4087238077272348745L;

	private Integer id;//主键ID

    private Integer activityId;//活动id

    private Integer uid;//用户ID
    
    private String userName;//用户名称

    private String phone;//手机号码

    private BigDecimal risePrice;//当前竞价金额

    private Integer riseNum;//加价数量

    private Date createTime;//出价时间
    
    private String createTimeStr;

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

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }
    
    

    /**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public BigDecimal getRisePrice() {
        return risePrice;
    }

    public void setRisePrice(BigDecimal risePrice) {
        this.risePrice = risePrice;
    }

    public Integer getRiseNum() {
        return riseNum;
    }

    public void setRiseNum(Integer riseNum) {
        this.riseNum = riseNum;
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
		createTimeStr=DateUtil.formatDate(DateUtil.Y_M_D_HMS, createTime);
		return createTimeStr;
	}

	/**
	 * @param createTimeStr the createTimeStr to set
	 */
	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TFreshActivityAuctionBidding [id=" + id + ", activityId="
				+ activityId + ", uid=" + uid + ", phone=" + phone
				+ ", risePrice=" + risePrice + ", riseNum=" + riseNum
				+ ", createTime=" + createTime + "]";
	}
    
    
}