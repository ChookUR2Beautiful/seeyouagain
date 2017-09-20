package com.xmniao.xmn.core.marketingmanagement.entity;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.xmniao.xmn.core.base.BaseEntity;
import com.xmniao.xmn.core.util.StringUtils;
import com.xmniao.xmn.core.util.SystemConstants;
/**
 * 项目名称：XmnWeb
 * 
 * 类名称：TGiveMoneyInfo
 * 
 * 类说明：营销活动管理-折扣补贴-=给出补贴金额明细
 * 
 * 创建人：chen'heng
 * 
 * 创建时间：2015-05-21
 * 
 * Copyright © 广东寻蜜鸟网络科技有限公司
 */
public class TGiveMoneyInfo extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4897881530513531461L;
	
	private Long bid;//订单号
	private String genusname;//所属商家名称
	private String phoneid;//手机号
	private Date zdate;//支付时间
	private Date zdateStart;//开始支付时间
	private Date zdateEnd;//结束支付时间
	private Date fdate;//到账时间
	private Double baseagio;//下单折扣
	private Double money;//消费金额
	private String paytype;//支付方式
	private String paytypeText;
	private Integer isaccount;//是否到账
	private Integer aid;//活动编号
	private Double giveMoney;//赠送金额
	private String nname;
	private Double ratioMoney; //佣金补贴的赠送金额
	
	public Long getBid() {
		return bid;
	}
	public void setBid(Long bid) {
		this.bid = bid;
	}
	public String getGenusname() {
		return genusname;
	}
	public void setGenusname(String genusname) {
		this.genusname = genusname;
	}
	public String getPhoneid() {
		return phoneid;
	}
	public void setPhoneid(String phoneid) {
		this.phoneid = phoneid;
	}
	
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getZdate() {
		return zdate;
	}
	public void setZdate(Date zdate) {
		this.zdate = zdate;
	}
	public Date getZdateStart() {
		return zdateStart;
	}
	public void setZdateStart(Date zdateStart) {
		this.zdateStart = zdateStart;
	}
	public Date getZdateEnd() {
		return zdateEnd;
	}
	public void setZdateEnd(Date zdateEnd) {
		this.zdateEnd = zdateEnd;
	}
	public String getPaytype() {
		return paytype;
	}
	public void setPaytype(String paytype) {
		this.paytype = paytype;
	}
	public String getPaytypeText() {
		return SystemConstants.getPayTypeText(paytype);
	}
	public void setPaytypeText(String paytypeText) {
		this.paytypeText = paytypeText;
	}
	public Integer getIsaccount() {
		return isaccount;
	}
	public void setIsaccount(Integer isaccount) {
		this.isaccount = isaccount;
	}
	public Integer getAid() {
		return aid;
	}
	public void setAid(Integer aid) {
		this.aid = aid;
	}
	
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getFdate() {
		return fdate;
	}
	public void setFdate(Date fdate) {
		this.fdate = fdate;
	}
	public Double getBaseagio() {
		return baseagio;
	}
	public void setBaseagio(Double baseagio) {
		this.baseagio = baseagio;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public Double getGiveMoney() {
		return giveMoney;
	}
	public void setGiveMoney(Double giveMoney) {
		this.giveMoney = giveMoney;
	}
	public String getNname() {
		return nname;
	}
	public void setNname(String nname) {
		this.nname = nname;
	}
	
	public Double getRatioMoney() {
		return ratioMoney;
	}
	public void setRatioMoney(Double ratioMoney) {
		this.ratioMoney = ratioMoney;
	}
	@Override
	public String toString() {
		return "TGiveMoneyInfo [bid=" + bid + ", genusname=" + genusname
				+ ", phoneid=" + phoneid + ", zdate=" + zdate + ", zdateStart="
				+ zdateStart + ", zdateEnd=" + zdateEnd + ", fdate=" + fdate
				+ ", baseagio=" + baseagio + ", money=" + money + ", paytype="
				+ paytype + ", isaccount=" + isaccount + ", aid=" + aid
				+ ", giveMoney=" + giveMoney + "]";
	}

	

}
