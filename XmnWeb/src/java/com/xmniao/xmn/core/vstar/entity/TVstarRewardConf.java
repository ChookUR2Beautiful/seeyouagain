package com.xmniao.xmn.core.vstar.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.xmniao.xmn.core.base.BaseEntity;

/**
 * 
 * 
 * 项目名称：XmnWeb_vstar
 * 
 * 类名称：TVstarRewardConf
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2017-7-17 下午4:01:21 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TVstarRewardConf extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = 6601350689924426607L;

	private Long id;//业务主键

    private Integer pilotTime;//试播有效时长(分钟)

    private BigDecimal rewardCoin;//奖励鸟币

    private Integer status;//有效状态，1有效，2无效。默认1

    private Date createTime;//创建时间

    private Date updateTime;//更新时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPilotTime() {
        return pilotTime;
    }

    public void setPilotTime(Integer pilotTime) {
        this.pilotTime = pilotTime;
    }

    public BigDecimal getRewardCoin() {
        return rewardCoin;
    }

    public void setRewardCoin(BigDecimal rewardCoin) {
        this.rewardCoin = rewardCoin;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
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
		return "TVstarRewardConf [id=" + id + ", pilotTime=" + pilotTime
				+ ", rewardCoin=" + rewardCoin + ", status=" + status
				+ ", createTime=" + createTime + ", updateTime=" + updateTime
				+ "]";
	}
    
    
}