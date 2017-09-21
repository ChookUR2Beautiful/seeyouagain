package com.xmniao.domain.order;

import java.io.Serializable;
import java.math.BigDecimal;
public class MallOrderBean implements Serializable {
	private static final long serialVersionUID = 1L;
    
	private Long bid;
	private Integer uid;
	
	private String phoneid;
	private BigDecimal money;
	private BigDecimal profit;
	private BigDecimal commision;
	private BigDecimal giveMoney;
	private BigDecimal liveCoin;
	private BigDecimal cuser;
	private Integer cdid;
	private Integer rid;
	private BigDecimal integral;
	private BigDecimal payment;
	
	private BigDecimal maxIntegral;
	
	private BigDecimal freight;
	
	private Integer status;
	
	private String address;
	private String username;
	private String mobile;
	
	private Integer rankType;
	
	private Long rankId;
	
	private Integer version;

	public Long getBid() {
		return bid;
	}

	public void setBid(Long bid) {
		this.bid = bid;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public BigDecimal getProfit() {
		return profit;
	}

	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}

	public BigDecimal getCommision() {
		return commision;
	}

	public void setCommision(BigDecimal commision) {
		this.commision = commision;
	}

	public BigDecimal getGiveMoney() {
		return giveMoney;
	}

	public void setGiveMoney(BigDecimal giveMoney) {
		this.giveMoney = giveMoney;
	}

	public BigDecimal getIntegral() {
		if(this.integral == null) this.integral = new BigDecimal("0");
		
		return integral;
	}

	public void setIntegral(BigDecimal integral) {
		this.integral = integral;
	}

	public BigDecimal getPayment() {
		if(this.payment == null) this.payment = new BigDecimal("0");
		
		return payment;
	}

	public void setPayment(BigDecimal payment) {
		this.payment = payment;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
	
	public BigDecimal getMaxIntegral() {
		if(this.maxIntegral == null) this.maxIntegral = new BigDecimal("0");
		
		return maxIntegral;
	}

	public void setMaxIntegral(BigDecimal maxIntegral) {
		this.maxIntegral = maxIntegral;
	}
	
	public String getAddress() {
		return address;
	}
	
	public BigDecimal getFreight() {
		if(freight == null) this.freight = new BigDecimal("0");
		return freight;
	}

	public void setFreight(BigDecimal freight) {
		this.freight = freight;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPhoneid() {
		return phoneid;
	}

	public void setPhoneid(String phoneid) {
		this.phoneid = phoneid;
	}

	public BigDecimal getLiveCoin() {
		return liveCoin;
	}

	public void setLiveCoin(BigDecimal liveCoin) {
		this.liveCoin = liveCoin;
	}

	public BigDecimal getCuser() {
		return cuser;
	}

	public void setCuser(BigDecimal cuser) {
		this.cuser = cuser;
	}

	public Integer getCdid() {
		return cdid;
	}

	public void setCdid(Integer cdid) {
		this.cdid = cdid;
	}
	
	public Integer getRid() {
		return rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}

	public Integer getRankType() {
		return rankType;
	}

	public void setRankType(Integer rankType) {
		this.rankType = rankType;
	}

	public Long getRankId() {
		return rankId;
	}

	public void setRankId(Long rankId) {
		this.rankId = rankId;
	}

	public BigDecimal getCash(){
		BigDecimal cash = new BigDecimal("0");
		
		cash = cash.add(this.getPayment());
		
		//扣除运费现金支付部分
		if(this.getFreight().compareTo(new BigDecimal("0")) > 0){
			if(cash.compareTo(this.getFreight()) > 0){
				cash =  cash.subtract(this.getFreight());
			}else{
				cash =  new BigDecimal("0");
			}
		}
		
		return cash;
	}
	
	public BigDecimal getBalance(){
		BigDecimal balance = new BigDecimal("0");
		
		if(this.profit == null) this.profit = new BigDecimal("0");
		
		if(this.commision == null) this.commision = new BigDecimal("0");
		
		if(this.giveMoney == null) this.giveMoney = new BigDecimal("0");
		
		balance = balance.add(this.profit)
					    .add(this.commision)
					    .add(this.giveMoney);
		
		//扣除运费余额支付部分
		if(this.getFreight().compareTo(new BigDecimal("0")) > 0){
			if(this.getPayment().compareTo(this.getFreight()) <= 0){
				BigDecimal b = this.getFreight().subtract(this.getPayment());
				
				if(balance.compareTo(b) > 0){
					balance = balance.subtract(b);
				}else{
					balance = new BigDecimal("0");
				}
				
			}
		}
		
		return balance;
	}
	
	
}
