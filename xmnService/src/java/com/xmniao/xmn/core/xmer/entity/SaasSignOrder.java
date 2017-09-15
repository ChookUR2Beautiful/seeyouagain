/**
 * 2016年5月23日 下午8:32:26
 */
package com.xmniao.xmn.core.xmer.entity;

/**
 * @项目名称：xmnService
 * @类名称：SaasSignOrder
 * @类描述：寻蜜客签约商户订单
 * @创建人： zhangchangyuan
 * @创建时间 2016年5月23日 下午8:32:26
 * @version
 */
public class SaasSignOrder {
	  
	 private String id;	//订单号
	 private String sellerid;//商家ID
	 private String sellername;//商家名称
	 private String uid;//用户ID'
	 private Double amount;//订单金额
	 private Integer status;//订单状态: 默认0  0 未签约(存入草稿) 1 审核中 2 已上线(审核通过) 3 不通过 4 退款审核中 5 退款成功 6 退款审核不通过
	 private String reason;//不通过理由
	 private Integer payType;//1 微信支付 2 支付宝支付  3 余额支付
	 private String zdate;//支付时间
	 private String createDate;//创建时间
	 private String saasOrdersn;//saas套餐订单编号
	 private Integer saasSource;//SAAS来源 0 正常库存 1 销售退回库存
	 private Integer saasChannel = 1; //saas购买渠道 1常规购买 2 脉客购买 3 V客兑换
	 
	public Integer getSaasSource() {
		return saasSource;
	}
	public void setSaasSource(Integer saasSource) {
		this.saasSource = saasSource;
	}
	public String getSaasOrdersn() {
		return saasOrdersn;
	}
	public void setSaasOrdersn(String saasOrdersn) {
		this.saasOrdersn = saasOrdersn;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSellerid() {
		return sellerid;
	}
	public void setSellerid(String sellerid) {
		this.sellerid = sellerid;
	}
	public String getSellername() {
		return sellername;
	}
	public void setSellername(String sellername) {
		this.sellername = sellername;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Integer getPayType() {
		return payType;
	}
	public void setPayType(Integer payType) {
		this.payType = payType;
	}
	public String getZdate() {
		return zdate;
	}
	public void setZdate(String zdate) {
		this.zdate = zdate;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public Integer getSaasChannel() {
		return saasChannel;
	}
	public void setSaasChannel(Integer saasChannel) {
		this.saasChannel = saasChannel;
	}

	@Override
	public String toString() {
		return "SaasSignOrder{" +
				"id='" + id + '\'' +
				", sellerid='" + sellerid + '\'' +
				", sellername='" + sellername + '\'' +
				", uid='" + uid + '\'' +
				", amount=" + amount +
				", status=" + status +
				", reason='" + reason + '\'' +
				", payType=" + payType +
				", zdate='" + zdate + '\'' +
				", createDate='" + createDate + '\'' +
				", saasOrdersn='" + saasOrdersn + '\'' +
				", saasSource=" + saasSource +
				", saasChannel=" + saasChannel +
				'}';
	}
}
