package com.xmniao.xmn.core.xmer.entity;

import java.util.Date;

/**   
 * 项目名称：xmnService   
 * 类名称：SaaSoldOrder   
 * 类描述：   
 * 创建人：zhengyaowen
 * 创建时间：2016年5月23日 下午4:43:13   
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 * @version     
 */
public class SaaSoldOrder {

	private Integer id;//订单号
	private Integer sellerid;//商家ID
	private String sellername;//商家名称
	private Integer uid;//用户ID
	private Double amount;//订单金额
	private Integer status;//订单状态: 默认0  0 未签约(存入草稿) 1 审核中 2 已上线(审核通过) 3 不通过 4 退款审核中 5 退款成功 6 退款审核不通过
	private String reason;//不通过理由
	private int payType;//1 微信支付 2 支付宝支付  3 余额支付
	private Date zdate;//支付时间
	private Date createDate;//创建时间
	private String ordersn;//订单号 02开头
	private String saas_ordersn; //saas订单 01开头
	private Integer saas_source;  //saas类型
	private Integer saas_channel; //saas来源
	 
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the sellerid
	 */
	public Integer getSellerid() {
		return sellerid;
	}
	/**
	 * @param sellerid the sellerid to set
	 */
	public void setSellerid(Integer sellerid) {
		this.sellerid = sellerid;
	}
	/**
	 * @return the sellername
	 */
	public String getSellername() {
		return sellername;
	}
	/**
	 * @param sellername the sellername to set
	 */
	public void setSellername(String sellername) {
		this.sellername = sellername;
	}
	/**
	 * @return the uid
	 */
	public Integer getUid() {
		return uid;
	}
	/**
	 * @param uid the uid to set
	 */
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	/**
	 * @return the amount
	 */
	public Double getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}
	/**
	 * @param reason the reason to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}
	/**
	 * @return the payType
	 */
	public int getPayType() {
		return payType;
	}
	/**
	 * @param payType the payType to set
	 */
	public void setPayType(int payType) {
		this.payType = payType;
	}
	/**
	 * @return the zdate
	 */
	public Date getZdate() {
		return zdate;
	}
	/**
	 * @param zdate the zdate to set
	 */
	public void setZdate(Date zdate) {
		this.zdate = zdate;
	}
	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}
	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * @return the ordersn
	 */
	public String getOrdersn() {
		return ordersn;
	}
	/**
	 * @param ordersn the ordersn to set
	 */
	public void setOrdersn(String ordersn) {
		this.ordersn = ordersn;
	}

	public Integer getSaas_source() {
		return saas_source;
	}

	public void setSaas_source(Integer saas_source) {
		this.saas_source = saas_source;
	}

	public Integer getSaas_channel() {
		return saas_channel;
	}

	public void setSaas_channel(Integer saas_channel) {
		this.saas_channel = saas_channel;
	}

	public String getSaas_ordersn() {
		return saas_ordersn;
	}

	public void setSaas_ordersn(String saas_ordersn) {
		this.saas_ordersn = saas_ordersn;
	}

	@Override
	public String toString() {
		return "SaaSoldOrder{" +
				"id=" + id +
				", sellerid=" + sellerid +
				", sellername='" + sellername + '\'' +
				", uid=" + uid +
				", amount=" + amount +
				", status=" + status +
				", reason='" + reason + '\'' +
				", payType=" + payType +
				", zdate=" + zdate +
				", createDate=" + createDate +
				", ordersn='" + ordersn + '\'' +
				", saas_ordersn='" + saas_ordersn + '\'' +
				", saas_source=" + saas_source +
				", saas_channel=" + saas_channel +
				'}';
	}
}
