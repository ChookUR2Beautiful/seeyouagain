package com.xmniao.xmn.core.seller.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商家满减活动表
 * */
public class ActivityFullreductionInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4975864086901618358L;
	
	private Integer id;
	//'商家id'
	private Integer sellerid ;
	//活动名称
	private String name;
	// '开始日期'
	private Date beginDate;
	// '结束日期'
	private Date endDate;
	// ' 消费并支付满'
	private BigDecimal consumeAndPay;
	// '抵用金额'
	private BigDecimal offsetAmount;
	//'每人限获（0：不限制，1:限一次)'
	private Integer limitNumber ;
	//'是否允许三级减免 0:不允许 1：允许'
	private Integer isDerate ;
	//'一级减免金额'
	private BigDecimal derateLevel1Amount ;
	// '一级消费并支付满'
	private BigDecimal consumeAndPay1;
	// '二级减免金额'
	private BigDecimal derateLevel2Amount;
	// '二级减免金额'
	private BigDecimal consumeAndPay2 ;
	// '三级减免金额'
	private BigDecimal derateLevel3Amount;
	// '三级消费并支付满
	private BigDecimal consumeAndPay3;
	//'状态 0：启用 1：禁用'
	private Integer status ;
	// '累计减免总金额'
	private BigDecimal reductionAmount;
	// '参加人数'
	private Integer joinNumber;
	// '曝光次数'
	private Integer views;
	// '赠品数量'
	private Integer giveNumber;
	// '使用数量'
	private Integer useNumber;
	//'新会员数'
	private Integer newuserNum ;
	//'刺激消费金额'
	private BigDecimal consumeAmount ;
	// '创建时间'
	private BigDecimal createTime;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public BigDecimal getConsumeAndPay() {
		return consumeAndPay;
	}
	public void setConsumeAndPay(BigDecimal consumeAndPay) {
		this.consumeAndPay = consumeAndPay;
	}
	public BigDecimal getOffsetAmount() {
		return offsetAmount;
	}
	public void setOffsetAmount(BigDecimal offsetAmount) {
		this.offsetAmount = offsetAmount;
	}
	public Integer getLimitNumber() {
		return limitNumber;
	}
	public void setLimitNumber(Integer limitNumber) {
		this.limitNumber = limitNumber;
	}
	public Integer getIsDerate() {
		return isDerate;
	}
	public void setIsDerate(Integer isDerate) {
		this.isDerate = isDerate;
	}
	public BigDecimal getDerateLevel1Amount() {
		return derateLevel1Amount;
	}
	public void setDerateLevel1Amount(BigDecimal derateLevel1Amount) {
		this.derateLevel1Amount = derateLevel1Amount;
	}
	public BigDecimal getConsumeAndPay1() {
		return consumeAndPay1;
	}
	public void setConsumeAndPay1(BigDecimal consumeAndPay1) {
		this.consumeAndPay1 = consumeAndPay1;
	}
	public BigDecimal getDerateLevel2Amount() {
		return derateLevel2Amount;
	}
	public void setDerateLevel2Amount(BigDecimal derateLevel2Amount) {
		this.derateLevel2Amount = derateLevel2Amount;
	}
	public BigDecimal getConsumeAndPay2() {
		return consumeAndPay2;
	}
	public void setConsumeAndPay2(BigDecimal consumeAndPay2) {
		this.consumeAndPay2 = consumeAndPay2;
	}
	public BigDecimal getDerateLevel3Amount() {
		return derateLevel3Amount;
	}
	public void setDerateLevel3Amount(BigDecimal derateLevel3Amount) {
		this.derateLevel3Amount = derateLevel3Amount;
	}
	public BigDecimal getConsumeAndPay3() {
		return consumeAndPay3;
	}
	public void setConsumeAndPay3(BigDecimal consumeAndPay3) {
		this.consumeAndPay3 = consumeAndPay3;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public BigDecimal getReductionAmount() {
		return reductionAmount;
	}
	public void setReductionAmount(BigDecimal reductionAmount) {
		this.reductionAmount = reductionAmount;
	}
	public Integer getJoinNumber() {
		return joinNumber;
	}
	public void setJoinNumber(Integer joinNumber) {
		this.joinNumber = joinNumber;
	}
	public Integer getViews() {
		return views;
	}
	public void setViews(Integer views) {
		this.views = views;
	}
	public Integer getGiveNumber() {
		return giveNumber;
	}
	public void setGiveNumber(Integer giveNumber) {
		this.giveNumber = giveNumber;
	}
	public Integer getUseNumber() {
		return useNumber;
	}
	public void setUseNumber(Integer useNumber) {
		this.useNumber = useNumber;
	}
	public Integer getNewuserNum() {
		return newuserNum;
	}
	public void setNewuserNum(Integer newuserNum) {
		this.newuserNum = newuserNum;
	}
	public BigDecimal getConsumeAmount() {
		return consumeAmount;
	}
	public void setConsumeAmount(BigDecimal consumeAmount) {
		this.consumeAmount = consumeAmount;
	}
	public BigDecimal getCreateTime() {
		return createTime;
	}
	public void setCreateTime(BigDecimal createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "ActivityFullreductionInfo [id=" + id + ", sellerid=" + sellerid
				+ ", name=" + name + ", beginDate=" + beginDate + ", endDate="
				+ endDate + ", consumeAndPay=" + consumeAndPay
				+ ", offsetAmount=" + offsetAmount + ", limitNumber="
				+ limitNumber + ", isDerate=" + isDerate
				+ ", derateLevel1Amount=" + derateLevel1Amount
				+ ", consumeAndPay1=" + consumeAndPay1
				+ ", derateLevel2Amount=" + derateLevel2Amount
				+ ", consumeAndPay2=" + consumeAndPay2
				+ ", derateLevel3Amount=" + derateLevel3Amount
				+ ", consumeAndPay3=" + consumeAndPay3 + ", status=" + status
				+ ", reductionAmount=" + reductionAmount + ", joinNumber="
				+ joinNumber + ", views=" + views + ", giveNumber="
				+ giveNumber + ", useNumber=" + useNumber + ", newuserNum="
				+ newuserNum + ", consumeAmount=" + consumeAmount
				+ ", createTime=" + createTime + "]";
	}
	
}
