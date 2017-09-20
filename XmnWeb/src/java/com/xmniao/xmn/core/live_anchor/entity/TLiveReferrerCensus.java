package com.xmniao.xmn.core.live_anchor.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.xmniao.xmn.core.base.BaseEntity;

/**
 * 
 * 
 * 项目名称：XmnWeb_LIVE_170105
 * 
 * 类名称：TLiveReferrerCensus
 * 
 * 类描述：直播企业级推荐人下线数据统计(按天)实体类
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-12-26 下午8:22:21 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TLiveReferrerCensus extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = -8529597631032280505L;

	private Long id;//业务主键

    private Long enterpriseUid;//企业级UID

    private BigDecimal juniorRecharge;//下线累计充值

    private BigDecimal juniorReward;//下线累计打赏

    private BigDecimal juniorConsume;//下线累计消费
    
    private Date censusDate;//统计日期

    private Date createTime;//定时任务执行时间
    
    private String startTime;//统计开始时间
    
    private String endTime;//统计结束时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEnterpriseUid() {
        return enterpriseUid;
    }

    public void setEnterpriseUid(Long enterpriseUid) {
        this.enterpriseUid = enterpriseUid;
    }

    public BigDecimal getJuniorRecharge() {
        return juniorRecharge;
    }

    public void setJuniorRecharge(BigDecimal juniorRecharge) {
        this.juniorRecharge = juniorRecharge;
    }

    public BigDecimal getJuniorReward() {
        return juniorReward;
    }

    public void setJuniorReward(BigDecimal juniorReward) {
        this.juniorReward = juniorReward;
    }

    public BigDecimal getJuniorConsume() {
        return juniorConsume;
    }

    public void setJuniorConsume(BigDecimal juniorConsume) {
        this.juniorConsume = juniorConsume;
    }
    
    

    /**
	 * @return the censusDate
	 */
	public Date getCensusDate() {
		return censusDate;
	}

	/**
	 * @param censusDate the censusDate to set
	 */
	public void setCensusDate(Date censusDate) {
		this.censusDate = censusDate;
	}

	public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TLiveReferrerCensus [id=" + id + ", enterpriseUid="
				+ enterpriseUid + ", juniorRecharge=" + juniorRecharge
				+ ", juniorReward=" + juniorReward + ", juniorConsume="
				+ juniorConsume + ", censusDate=" + censusDate
				+ ", createTime=" + createTime + ", startTime=" + startTime
				+ ", endTime=" + endTime + "]";
	}
    
    
}