/**
 *2016年5月19日下午2:59:40
 */
package com.xmniao.xmn.core.xmer.entity;

import java.util.Date;

/**
 *@ClassName:SaasOrder
 *@Description:SAAS软件订单表实体
 *@author hls
 *@date:2016年5月19日下午2:59:40
 */
public class SaasOrder {
	private Long id;	//订单号160501 100655 1254。格式6位年月日+小时 分 秒+4位随机数
	private String ordersn;	//订单编号
	private Integer dpid;//押金套餐ID
	private Integer xmerid;//寻蜜客ID
	private Integer num;//签约数量
	private Double agio;//套餐折扣
	private Double amount;//订单金额
	private Integer status;//订单状态默认0 0 未支付 1 支付成功 2 支付失败 3 申请退款中 4 退款成功 5 退款申请不通过
	private String reason;//不通过理由
	private Date zdate;//支付时间
	private Date sdate;//创建时间
	private Integer payType;//支付方式 1 微信支付 2 支付宝支付  3 余额支付
	private Integer soldnums;//卖出数量
	private Integer stock;//剩余套餐库存数量
	private Integer parentid;//推荐人id
	private Double price;	//套餐单价
	private String openid;		//微信opendId
	private Integer otherPay;	//是否是代付
	private String otherTel;	//代付手机号码
	//V3.6.0 版本添加
	private String uidRelationChain;	//寻蜜客关系链
	private Integer saas_channel;	//saas购买渠道 1常规购买 2 脉客购买 3 V客兑换
	private Integer appSourceStatus;//app来源
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOrdersn() {
		return ordersn;
	}
	public void setOrdersn(String ordersn) {
		this.ordersn = ordersn;
	}
	public Integer getDpid() {
		return dpid;
	}
	public void setDpid(Integer dpid) {
		this.dpid = dpid;
	}
	public Integer getXmerid() {
		return xmerid;
	}
	public void setXmerid(Integer xmerid) {
		this.xmerid = xmerid;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public Double getAgio() {
		return agio;
	}
	public void setAgio(Double agio) {
		this.agio = agio;
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
	public Date getZdate() {
		return zdate;
	}
	public void setZdate(Date zdate) {
		this.zdate = zdate;
	}
	public Date getSdate() {
		return sdate;
	}
	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}
	public Integer getPayType() {
		return payType;
	}
	public void setPayType(Integer payType) {
		this.payType = payType;
	}
	public Integer getSoldnums() {
		return soldnums;
	}
	public void setSoldnums(Integer soldnums) {
		this.soldnums = soldnums;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public Integer getParentid() {
		return parentid;
	}
	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public Integer getOtherPay() {
		return otherPay;
	}
	public void setOtherPay(Integer otherPay) {
		this.otherPay = otherPay;
	}
	public String getOtherTel() {
		return otherTel;
	}
	public void setOtherTel(String otherTel) {
		this.otherTel = otherTel;
	}
	public String getUidRelationChain() {
		return uidRelationChain;
	}
	public void setUidRelationChain(String uidRelationChain) {
		this.uidRelationChain = uidRelationChain;
	}
	public Integer getSaas_channel() {
		return saas_channel;
	}
	public void setSaas_channel(Integer saas_channel) {
		this.saas_channel = saas_channel;
	}
	public Integer getAppSourceStatus() {
		return appSourceStatus;
	}
	public void setAppSourceStatus(Integer appSourceStatus) {
		this.appSourceStatus = appSourceStatus;
	}
	@Override
	public String toString() {
		return "SaasOrder [id=" + id + ", ordersn=" + ordersn + ", dpid=" + dpid + ", xmerid=" + xmerid + ", num=" + num
				+ ", agio=" + agio + ", amount=" + amount + ", status=" + status + ", reason=" + reason + ", zdate="
				+ zdate + ", sdate=" + sdate + ", payType=" + payType + ", soldnums=" + soldnums + ", stock=" + stock
				+ ", parentid=" + parentid + ", price=" + price + ", openid=" + openid + ", otherPay=" + otherPay
				+ ", otherTel=" + otherTel + ", uidRelationChain=" + uidRelationChain + ", saas_channel=" + saas_channel
				+ ", appSourceStatus=" + appSourceStatus + "]";
	}
	

	
}
