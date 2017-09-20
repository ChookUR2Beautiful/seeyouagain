/**
 * 文件名：TActivity.java
 * 
 * 创建日期:2015-01-14 10:03:36
 * 
 * Copyright © 寻蜜鸟网络科技有限公司
 */
package com.xmniao.xmn.core.marketingmanagement.entity;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.xmniao.xmn.core.base.BaseEntity;
import com.xmniao.xmn.core.util.StringUtils;
/**
 * 项目名称：XmnWeb
 * 
 * 类名称：TActivity
 * 
 * 类说明：营销活动管理
 * 
 * 创建人：caoyingde
 * 
 * 创建时间：2015-01-14 10：09：03
 * 
 * Copyright © 广东寻蜜鸟网络科技有限公司
 */
public class TActivity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4097740642012046107L;
		
	private Integer aid;
	private String aname;//活动名称
	private String sname;//活动简称，用户前段显示
	private String rule; //活动规则, 50>10 表示满50送10块
	private String eescription;//活动描述
	private Integer type;//'1 抽奖返利活动2 消费赠送活动(如消费满50元送10元)3 其他活动',  (1刮刮卡 2买赠 5折扣 4佣金补贴6.优惠券赠送活动)
	private String startDate;//开始时间
	private String endDate;//结束时间
	private String startTime;//开始时间
	private String endTime;//结束时间
	private String rate;//赠送频率，1首单，2,首满，3每次
	private Integer ratenmber;
	private String repel;//是否与其他活动互斥，0不互斥，1互斥
	private String giveMoneyCount;//补贴金额
	private String deleteList;
	private List<TActivityRule> tActivityRule = new ArrayList<TActivityRule>();
	private List<TActivityRule> tactivityrule ;
	private Integer minMoeny;//满返活动：赠送区间最小金额，0表示0 折扣补贴活动：单日单店最多补贴次数，0表示不限制 刮刮卡：中奖金额最小值
	private Double maxMoeny;//满返活动：赠送区间最大金额，0表示无穷大 折扣补贴活动：单次最高补贴金额，0表示不限制 刮刮卡活动：中奖金额最大值，可以等于中奖金额最小值
	private String participateNum;//参与用户
	private String billNum;//订单总数
	
	private String sDate;//商家参与营销表  营销开始时间
	private String eDate;//商家参与营销表  营销结束时间
	//积分活动规则
	private List<TIntegralRule> tIntegralRule = new ArrayList<TIntegralRule>();
	private List<TIntegralRule> tantegralRule ;
	private String grade;//商家类别
	

	/*
	 * 业务关联字段：t_seller_marketing.id
	 * 业务要求：当t_seller_marketing.aid = aid时，该条记录不能删除
	 * 增加字段：sellerMarketingId = t_seller_marketing.id
	 */
	private Integer isRelationSeller;//商家总数
	
	private Double giveMoney;//折扣补贴
	
	private Double ngiveMoney;
	/*
	 * 拓展查询属性，增加开始时间区段 条件查询
	 * sDateBegin  开始日期起点
	 * eDateEnd    开始日期终点
	 */
	private String sDateBegin;
	private String eDateEnd;
	
	private Double hitRatio;//中奖比例
	private Integer setWay;//设奖方式 0：按数量设置，1：按比例设置
	private String doType;//操作类型
	
	
	/*   2016-04-20新增   */
	private String bendDate;
	private String bstartDate;
	private String conditions;
	private Integer showTimes;
	private String entrance;
	private String imgUrl;
	private String explains;
	private Integer rateNum;
	private String createDate;
	/**
	 * 截取开始时间的时分
	 * @return
	 */
	public String getStartTimeText(){
		if(this.startTime!=null){
			return this.startTime.substring(0, 5);
		}
		return null;
	}
	
	/**
	 * 截取结束时间的时分
	 * @return
	 */
	public String getEndTimeText(){
		if(this.startTime!=null){
			return this.endTime.substring(0, 5);
		}
		return null;
	}
	public String getDoType() {
		return doType;
	}

	public void setDoType(String doType) {
		this.doType = doType;
	}
		
	public Integer getAid() {
		return aid;
	}
	public void setAid(Integer aid) {
		this.aid = aid;
	}
	public String getAname() {
		return aname;
	}
	public void setAname(String aname) {
		this.aname = aname;
	}
	
	public String getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(String deleteList) {
		this.deleteList = deleteList;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	
	
	
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getsDateBegin() {
		return sDateBegin;
	}
	public void setsDateBegin(String sDateBegin) {
		this.sDateBegin = sDateBegin;
	}
	public String geteDateEnd() {
		return eDateEnd;
	}
	public void seteDateEnd(String eDateEnd) {
		this.eDateEnd = eDateEnd;
	}
	public Integer getMinMoeny() {
		return minMoeny;
	}
	public void setMinMoeny(Integer minMoeny) {
		this.minMoeny = minMoeny;
	}
	public Double getMaxMoeny() {
		return maxMoeny;
	}
	public void setMaxMoeny(Double maxMoeny) {
		this.maxMoeny = maxMoeny;
	}

	public String getParticipateNum() {
		return participateNum;
	}

	public void setParticipateNum(String participateNum) {
		this.participateNum = participateNum;
	}

	public String getBillNum() {
		return billNum;
	}

	public void setBillNum(String billNum) {
		this.billNum = billNum;
	}

	public String getRule() {
		return rule;
	}
	public void setRule(String rule) {
		this.rule = rule;
	}
	public String getEescription() {
		return eescription;
	}
	public void setEescription(String eescription) {
		this.eescription = eescription;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getIsRelationSeller() {
		return isRelationSeller;
	}
	public void setIsRelationSeller(Integer isRelationSeller) {
		this.isRelationSeller = isRelationSeller;
	}
	
	public String getRate() {
		return rate;
	}
	
	public String getRateName() {
		if(rate != null  && rate.equals("1")){
			return "首单";
		}else if(rate != null && rate.equals("2")){
			return "首满";
		}else if(rate != null && rate.equals("3")){
			return "每次";
		}
		return null;
	}
	public String getRepelName() {
		if(repel != null  && repel.equals("0")){
			return "不互斥";
		}else if(repel != null && repel.equals("1")){
			return "互斥";
		}
		return "";
	}
	
	
	
	public void setRate(String rate) {
		this.rate = rate;
	}
	
	public Integer getRatenmber() {
		if(StringUtils.hasLength(rate)){
			ratenmber = Integer.parseInt(rate);
		}
		return ratenmber;
	}
	public void setRatenmber(Integer ratenmber) {
		this.ratenmber = ratenmber;
	}
	public String getRepel() {
		return repel;
	}
	public void setRepel(String repel) {
		this.repel = repel;
	}
	
	
	public List<TActivityRule> gettActivityRule() {
		return tActivityRule;
	}
	public void settActivityRule(List<TActivityRule> tActivityRule) {
		this.tActivityRule = tActivityRule;
	}
	
	public List<TActivityRule> getTactivityrule() {
		return tactivityrule;
	}
	public void setTactivityrule(List<TActivityRule> tactivityrule) {
		this.tactivityrule = tactivityrule;
	}
	public String getGiveMoneyCount() {
		return giveMoneyCount;
	}
	public void setGiveMoneyCount(String giveMoneyCount) {
		this.giveMoneyCount = giveMoneyCount;
	}

	public Double getGiveMoney() {
		return giveMoney;
	}
	public Double getNgiveMoney() {
		if(giveMoney!=null){
			ngiveMoney=giveMoney;
		}
		return ngiveMoney;
	}
	
	public String getNgiveMoneyStr() {
		if(giveMoney != null&&giveMoney!=0) {
			DecimalFormat  dft  = new DecimalFormat("######0.00"); 
			return dft.format(new BigDecimal(giveMoney).multiply(new BigDecimal(100)));
		}
		return "0";
	}
	
	public void setNgiveMoney(Double ngiveMoney) {
		this.ngiveMoney = ngiveMoney;
	}
	public void setGiveMoney(Double giveMoney) {
		this.giveMoney = giveMoney;
	}
	public Double getHitRatio() {
		return hitRatio;
	}

	public void setHitRatio(Double hitRatio) {
		this.hitRatio = hitRatio;
	}

	public Integer getSetWay() {
		return setWay;
	}

	public void setSetWay(Integer setWay) {
		this.setWay = setWay;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	
	public String getsDate() {
		sDate = null;
		if(startDate != null && startTime != null){
			sDate = startDate+" "+startTime;
		}
		return sDate;
	}

	public void setsDate(String sDate) {
		this.sDate = sDate;
	}

	public String geteDate() {
		sDate = null;
		if(endDate != null && endTime != null){
			eDate = endDate+" "+endTime;
		}
		return eDate;
	}

	public void seteDate(String eDate) {
		this.eDate = eDate;
	}
	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public List<TIntegralRule> gettIntegralRule() {
		return tIntegralRule;
	}

	public void settIntegralRule(List<TIntegralRule> tIntegralRule) {
		this.tIntegralRule = tIntegralRule;
	}

	public List<TIntegralRule> getTantegralRule() {
		return tantegralRule;
	}

	public void setTantegralRule(List<TIntegralRule> tantegralRule) {
		this.tantegralRule = tantegralRule;
	}
	
	public String getBendDate() {
		return bendDate;
	}

	public void setBendDate(String bendDate) {
		this.bendDate = bendDate;
	}

	public String getBstartDate() {
		return bstartDate;
	}

	public void setBstartDate(String bstartDate) {
		this.bstartDate = bstartDate;
	}

	public String getConditions() {
		return conditions;
	}

	public void setConditions(String conditions) {
		this.conditions = conditions;
	}

	public Integer getShowTimes() {
		return showTimes;
	}

	public void setShowTimes(Integer showTimes) {
		this.showTimes = showTimes;
	}

	public String getEntrance() {
		return entrance;
	}

	public void setEntrance(String entrance) {
		this.entrance = entrance;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getExplains() {
		return explains;
	}

	public void setExplains(String explains) {
		this.explains = explains;
	}

	public Integer getRateNum() {
		return rateNum;
	}

	public void setRateNum(Integer rateNum) {
		this.rateNum = rateNum;
	}
 
	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "TActivity [aid=" + aid + ", aname=" + aname + ", sname="
				+ sname + ", rule=" + rule + ", eescription=" + eescription
				+ ", type=" + type + ", startDate=" + startDate + ", endDate="
				+ endDate + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", rate=" + rate + ", ratenmber=" + ratenmber + ", repel="
				+ repel + ", giveMoneyCount=" + giveMoneyCount
				+ ", deleteList=" + deleteList + ", tActivityRule="
				+ tActivityRule + ", tactivityrule=" + tactivityrule
				+ ", minMoeny=" + minMoeny + ", maxMoeny=" + maxMoeny
				+ ", participateNum=" + participateNum + ", billNum=" + billNum
				+ ", sDate=" + sDate + ", eDate=" + eDate + ", tIntegralRule="
				+ tIntegralRule + ", tantegralRule=" + tantegralRule
				+ ", grade=" + grade + ", isRelationSeller=" + isRelationSeller
				+ ", giveMoney=" + giveMoney + ", ngiveMoney=" + ngiveMoney
				+ ", sDateBegin=" + sDateBegin + ", eDateEnd=" + eDateEnd
				+ ", hitRatio=" + hitRatio + ", setWay=" + setWay + ", doType="
				+ doType + ", bendDate=" + bendDate + ", bstartDate="
				+ bstartDate + ", conditions=" + conditions + ", showTimes="
				+ showTimes + ", entrance=" + entrance + ", imgUrl=" + imgUrl
				+ ", explains=" + explains + ", rateNum=" + rateNum
				+ ", createDate=" + createDate + "]";
	}

}
