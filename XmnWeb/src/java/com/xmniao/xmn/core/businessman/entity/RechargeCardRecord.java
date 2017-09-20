/**
 * 
 */
package com.xmniao.xmn.core.businessman.entity;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.xmniao.xmn.core.base.BaseEntity;
import com.xmniao.xmn.core.util.SystemConstants;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：RechargeCardRecord
 * 
 * 类描述： 充值记录
 * 
 * 创建人： chenJie
 * 
 * 创建时间：2017年2月22日 上午11:46:10 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */

public class RechargeCardRecord extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3788962132974165120L;
	
	
	private String orderNo;//订单编号
	
	private String userName;//会员名称
	
	private Integer uid;
	
	private Integer sellerid;
	
	private Integer sellertype;
	
	private String relationChain;
	
	private String phone;
	
	private String userLevel;//会员级别
	
	private BigDecimal quota;//充值金额
	
	private String payType;//支付方式
	
	private String payTypeText;
	
	private String sellername;//充值商户
	
	private BigDecimal qquota;//充值前额度
	
	private BigDecimal hquota;//充值后额度
	
	private String upLevel;//上级
	
	private BigDecimal upAmount;//上级分拥
	
	private String topLevel;//上上级
	
	private BigDecimal topAmount;//上上级分佣
	
	private Date payTime;//充值时间
	
	private Date zDate;
	
	private Date eDate;
	
	private BigDecimal realCoin;//实际获得鸟豆数
	
	private BigDecimal payment;//充值金额

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getSellerid() {
		return sellerid;
	}

	public void setSellerid(Integer sellerid) {
		this.sellerid = sellerid;
	}

	public Integer getSellertype() {
		return sellertype;
	}

	public void setSellertype(Integer sellertype) {
		this.sellertype = sellertype;
	}

	public String getRelationChain() {
		return relationChain;
	}

	public void setRelationChain(String relationChain) {
		this.relationChain = relationChain;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(String userLevel) {
		this.userLevel = userLevel;
	}

	public BigDecimal getQuota() {
		return quota;
	}

	public void setQuota(BigDecimal quota) {
		this.quota = quota;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getSellername() {
		return sellername;
	}

	public void setSellername(String sellername) {
		this.sellername = sellername;
	}

	public BigDecimal getQquota() {
		return qquota;
	}

	public void setQquota(BigDecimal qquota) {
		this.qquota = qquota;
	}

	public BigDecimal getHquota() {
		return hquota;
	}

	public void setHquota(BigDecimal hquota) {
		this.hquota = hquota;
	}

	public String getUpLevel() {
		return upLevel;
	}

	public void setUpLevel(String upLevel) {
		this.upLevel = upLevel;
	}

	public BigDecimal getUpAmount() {
		return upAmount;
	}

	public void setUpAmount(BigDecimal upAmount) {
		this.upAmount = upAmount;
	}

	public String getTopLevel() {
		return topLevel;
	}

	public void setTopLevel(String topLevel) {
		this.topLevel = topLevel;
	}

	public BigDecimal getTopAmount() {
		return topAmount;
	}

	public void setTopAmount(BigDecimal topAmount) {
		this.topAmount = topAmount;
	}
	
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public Date getzDate() {
		return zDate;
	}

	public void setzDate(Date zDate) {
		this.zDate = zDate;
	}

	public Date geteDate() {
		return eDate;
	}

	public void seteDate(Date eDate) {
		this.eDate = eDate;
	}
	
	
	public String getPayTypeText(){
		return SystemConstants.getPayTypeText(payType);
	}

	public void setPayTypeText(String payTypeText) {
		this.payTypeText = payTypeText;
	}
	
	public String getPayTimeText(){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(payTime);
	}
	
	public BigDecimal getRealCoin() {
		return realCoin;
	}

	public void setRealCoin(BigDecimal realCoin) {
		this.realCoin = realCoin;
	}

	public BigDecimal getPayment() {
		return payment;
	}

	public void setPayment(BigDecimal payment) {
		this.payment = payment;
	}

	@Override
	public String toString() {
		return "RechargeCardRecord [orderNo=" + orderNo + ", userName="
				+ userName + ", uid=" + uid + ", sellerid=" + sellerid
				+ ", sellertype=" + sellertype + ", relationChain="
				+ relationChain + ", phone=" + phone + ", userLevel="
				+ userLevel + ", quota=" + quota + ", payType=" + payType
				+ ", payTypeText=" + payTypeText + ", sellername=" + sellername
				+ ", qquota=" + qquota + ", hquota=" + hquota + ", upLevel="
				+ upLevel + ", upAmount=" + upAmount + ", topLevel=" + topLevel
				+ ", topAmount=" + topAmount + ", payTime=" + payTime
				+ ", zDate=" + zDate + ", eDate=" + eDate + ", realCoin="
				+ realCoin + ", payment=" + payment + "]";
	}



}
