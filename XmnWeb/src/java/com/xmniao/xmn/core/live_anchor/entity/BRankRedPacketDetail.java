package com.xmniao.xmn.core.live_anchor.entity;

import java.math.BigDecimal;
import java.util.List;

import com.xmniao.xmn.core.base.BaseEntity;

/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：BRankRedPacketDetail
 * 
 * 类描述： 级别返还模式比例配置实体类
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2017-2-24 下午3:35:01 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class BRankRedPacketDetail extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = -7443126678466761470L;

	private Integer id;//业务主键

    private Integer rankDetailId;//等级详情id

    private Integer dividendsRole;//分红角色 1内购 2外购

    private BigDecimal consumeLimitLowest;//区间消费最低

    private BigDecimal consumeLimitHighest;//区间消费最高

    private BigDecimal cashLowest;//区间现金红包最低百分比

    private BigDecimal cashHighest;//区间现金红包最高百分比

    private BigDecimal coinLowest;//区间鸟币红包最低百分比

    private BigDecimal coinHighest;//区间鸟币红包最高百分比
    
    private List<Integer> rankDetailIds;//等级详情id数组
    
    private String consumeZone;//打赏鸟豆区间
    
    private String cashZone;//鸟币红包返还比例
    
    private String coinZone;//余额红包返还比例
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRankDetailId() {
        return rankDetailId;
    }

    public void setRankDetailId(Integer rankDetailId) {
        this.rankDetailId = rankDetailId;
    }

    public Integer getDividendsRole() {
        return dividendsRole;
    }

    public void setDividendsRole(Integer dividendsRole) {
        this.dividendsRole = dividendsRole;
    }

    public BigDecimal getConsumeLimitLowest() {
        return consumeLimitLowest;
    }

    public void setConsumeLimitLowest(BigDecimal consumeLimitLowest) {
        this.consumeLimitLowest = consumeLimitLowest;
    }

    public BigDecimal getConsumeLimitHighest() {
        return consumeLimitHighest;
    }

    public void setConsumeLimitHighest(BigDecimal consumeLimitHighest) {
        this.consumeLimitHighest = consumeLimitHighest;
    }

    public BigDecimal getCashLowest() {
        return cashLowest;
    }

    public void setCashLowest(BigDecimal cashLowest) {
        this.cashLowest = cashLowest;
    }

    public BigDecimal getCashHighest() {
        return cashHighest;
    }

    public void setCashHighest(BigDecimal cashHighest) {
        this.cashHighest = cashHighest;
    }

    public BigDecimal getCoinLowest() {
        return coinLowest;
    }

    public void setCoinLowest(BigDecimal coinLowest) {
        this.coinLowest = coinLowest;
    }

    public BigDecimal getCoinHighest() {
        return coinHighest;
    }

    public void setCoinHighest(BigDecimal coinHighest) {
        this.coinHighest = coinHighest;
    }
    
    

	/**
	 * @return the rankDetailIds
	 */
	public List<Integer> getRankDetailIds() {
		return rankDetailIds;
	}

	/**
	 * @param rankDetailIds the rankDetailIds to set
	 */
	public void setRankDetailIds(List<Integer> rankDetailIds) {
		this.rankDetailIds = rankDetailIds;
	}

	
	
	/**
	 * @return the consumeZone
	 */
	public String getConsumeZone() {
		return consumeZone;
	}

	/**
	 * @param consumeZone the consumeZone to set
	 */
	public void setConsumeZone(String consumeZone) {
		this.consumeZone = consumeZone;
	}

	/**
	 * @return the cashZone
	 */
	public String getCashZone() {
		return cashZone;
	}

	/**
	 * @param cashZone the cashZone to set
	 */
	public void setCashZone(String cashZone) {
		this.cashZone = cashZone;
	}

	/**
	 * @return the coinZone
	 */
	public String getCoinZone() {
		return coinZone;
	}

	/**
	 * @param coinZone the coinZone to set
	 */
	public void setCoinZone(String coinZone) {
		this.coinZone = coinZone;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BRankRedPacketDetail [id=" + id + ", rankDetailId="
				+ rankDetailId + ", dividendsRole=" + dividendsRole
				+ ", consumeLimitLowest=" + consumeLimitLowest
				+ ", consumeLimitHighest=" + consumeLimitHighest
				+ ", cashLowest=" + cashLowest + ", cashHighest=" + cashHighest
				+ ", coinLowest=" + coinLowest + ", coinHighest=" + coinHighest
				+ "]";
	}
    
    
}