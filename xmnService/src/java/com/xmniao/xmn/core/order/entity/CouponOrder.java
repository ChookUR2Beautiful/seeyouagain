package com.xmniao.xmn.core.order.entity;

import java.math.BigDecimal;

/**
 * 
* 类名称：CouponOrder   
* 类描述：   预售卷订单
* 创建人：xiaoxiong   
* 创建时间：2016年11月21日 下午6:08:08
 */
public class CouponOrder {
	private static final long serialVersionUID = 1L;
	private long id;
	private String orderSn;
	private int anchorCid;
	private int cidNum;
	private int uid;
	private String nname;
	private String phoneid;
	private int recordId;
	private int sellerid;
	private int status;
	private String payid;
	private String paymentType;
	private String thirdUid;
	private String thirdSerial;
	private BigDecimal totalAmount;
	private BigDecimal cash;
	private BigDecimal balance;
	private BigDecimal commision;
	private BigDecimal zbalance;
	private BigDecimal beans;
	private BigDecimal integral;
	private BigDecimal cuser;
	private int cdid;
	private BigDecimal returnIntegral;
	private BigDecimal retrunCouponAmount;
	private String createTime;
	private String modifyTime;
	private int version;
	private int orderSource;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getOrderSn() {
		return orderSn;
	}
	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}
	public int getAnchorCid() {
		return anchorCid;
	}
	public void setAnchorCid(int anchorCid) {
		this.anchorCid = anchorCid;
	}
	public int getCidNum() {
		return cidNum;
	}
	public void setCidNum(int cidNum) {
		this.cidNum = cidNum;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getNname() {
		return nname;
	}
	public void setNname(String nname) {
		this.nname = nname;
	}
	public String getPhoneid() {
		return phoneid;
	}
	public void setPhoneid(String phoneid) {
		this.phoneid = phoneid;
	}
	public int getRecordId() {
		return recordId;
	}
	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}
	public int getSellerid() {
		return sellerid;
	}
	public void setSellerid(int sellerid) {
		this.sellerid = sellerid;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getPayid() {
		return payid;
	}
	public void setPayid(String payid) {
		this.payid = payid;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public String getThirdUid() {
		return thirdUid;
	}
	public void setThirdUid(String thirdUid) {
		this.thirdUid = thirdUid;
	}
	public String getThirdSerial() {
		return thirdSerial;
	}
	public void setThirdSerial(String thirdSerial) {
		this.thirdSerial = thirdSerial;
	}
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	public BigDecimal getCash() {
		return cash;
	}
	public void setCash(BigDecimal cash) {
		this.cash = cash;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public BigDecimal getCommision() {
		return commision;
	}
	public void setCommision(BigDecimal commision) {
		this.commision = commision;
	}
	public BigDecimal getZbalance() {
		return zbalance;
	}
	public void setZbalance(BigDecimal zbalance) {
		this.zbalance = zbalance;
	}
	public BigDecimal getBeans() {
		return beans;
	}
	public void setBeans(BigDecimal beans) {
		this.beans = beans;
	}
	public BigDecimal getIntegral() {
		return integral;
	}
	public void setIntegral(BigDecimal integral) {
		this.integral = integral;
	}
	public BigDecimal getCuser() {
		return cuser;
	}
	public void setCuser(BigDecimal cuser) {
		this.cuser = cuser;
	}
	public int getCdid() {
		return cdid;
	}
	public void setCdid(int cdid) {
		this.cdid = cdid;
	}
	public BigDecimal getReturnIntegral() {
		return returnIntegral;
	}
	public void setReturnIntegral(BigDecimal returnIntegral) {
		this.returnIntegral = returnIntegral;
	}
	public BigDecimal getRetrunCouponAmount() {
		return retrunCouponAmount;
	}
	public void setRetrunCouponAmount(BigDecimal retrunCouponAmount) {
		this.retrunCouponAmount = retrunCouponAmount;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public int getOrderSource() {
		return orderSource;
	}
	public void setOrderSource(int orderSource) {
		this.orderSource = orderSource;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
	 
	
}
