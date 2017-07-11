package com.xmn.saas.entity.activity;

import java.math.BigDecimal;
import java.util.Date;

public class Activity {
	private Integer id;
	
	private Date beginDate; // 开始日期;
	
	private Date endDate; // 结束日期
	
	private BigDecimal consumeAmount;	//刺激消费金额
	
	Integer giveAwardCount;		//获奖人数,秒杀为秒杀次数
	
	private Integer activityType = 0; // 活动类型 1.免尝 2.大转盘 3.秒杀
	
	private Integer residue; //奖品剩余数量
	
	public Integer getResidue() {
		return residue;
	}

	public void setResidue(Integer residue) {
		this.residue = residue;
	}

	public float getGiveAwardCount() {
		return giveAwardCount==null?0:giveAwardCount;
	}

	public void setGiveAwardCount(Integer giveAwardCount) {
		this.giveAwardCount = giveAwardCount;
	}

	public BigDecimal getConsumeAmount() {
		return consumeAmount;
	}

	public void setConsumeAmount(BigDecimal consumeAmount) {
		this.consumeAmount = consumeAmount;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	

	public Integer  getActivityType() {
		return activityType;
	}
	
	private Double awardProportion; // 获奖几率

	public Double getAwardProportion() {
		return awardProportion;
	}

	public void setAwardProportion(Double awardProportion) {
		this.awardProportion = awardProportion;
	}
	
	
}
