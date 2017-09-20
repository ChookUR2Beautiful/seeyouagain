/**
 * 
 */
package com.xmniao.xmn.core.cloud_design.entity;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.xmniao.xmn.core.base.BaseEntity;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：MaterialOrder
 * 
 * 类描述： 物料订单
 * 
 * 创建人： chenJie
 * 
 * 创建时间：2016年9月28日 上午10:39:55 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */

public class MaterialOrder extends BaseEntity{
	
	private static final long serialVersionUID = -59323369228942994L;

	private long id;
	private Integer uid;//用户id
	private BigDecimal orderAmount;//订单总金额
	private BigDecimal balance;//余额支付金额
	private BigDecimal freight;//运费
	private String name;//供应商
	private String supplierId;//供应商ID
	private String orderNo;//订单编号
	private String tradeNo;//第三方订单号
	private String outTradeNo;//第三方交易流水号
	private String thirdUid;//第三方支付账号
	private String payType;//支付方式
	private Date payTime;//支付时间
	private String payTimeStr;
	private String deliveryAddress;//配送地址
	private String mobile;//收货联系手机号
	private String consignee;//联系人
	private Integer payStatus;//支付状态
	private String courierNumber;//快递单号
	private Integer status;//订单状态
	private Long materialId;//物料主键
	private String userName;//买家
	private String statusStr;
	private Date createTime;//下单时间
	private String createTimeStr;
	private Date createTimeEnd;
	private String createTimeEndStr;
	private Integer deviceType;//客户端类型
	private String leaveNote;//留言
	private String type;// 物料类型
	private String typeStr;
	private BigDecimal salePrice;//销售价格
	private Integer nums;//数量
	private String title;//标题
	private String remark;//描述
	private String url;//logo图
	private String postCompany;//快递公司
	private String description;//备注
	private Long designer;//设计师id
	private String startUrl;//初稿
	private String endUrl;//终稿
	private Long version;//版本号
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public BigDecimal getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(BigDecimal orderAmount) {
		this.orderAmount = orderAmount;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public BigDecimal getFreight() {
		return freight;
	}
	public void setFreight(BigDecimal freight) {
		this.freight = freight;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getTradeNo() {
		return tradeNo;
	}
	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}
	public String getOutTradeNo() {
		return outTradeNo;
	}
	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}
	public String getThirdUid() {
		return thirdUid;
	}
	public void setThirdUid(String thirdUid) {
		this.thirdUid = thirdUid;
	}
	public String getPayType() {
		if("1000000".equals(payType)){
			return "余额支付";
		}
		if("1000001".equals(payType)){
			return "支付宝支付";
		}
		if("1000003".equals(payType)){
			return "微信支付";
		}
		if("1000022".equals(payType)){
			return "支付宝APP支付（鸟人科技）";
		}
		if("1000023".equals(payType)){
			return "支付宝WAP支付（鸟人科技）";
		}
		if("1000024".equals(payType)){
			return "微信APP支付（鸟人科技）";
		}
		if("1000025".equals(payType)){
			return "微信公众号支付（鸟人科技）";
		}
		return "-";
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	
	public Long getMaterialId() {
		return materialId;
	}
	public void setMaterialId(Long materialId) {
		this.materialId = materialId;
	}
	public Date getPayTime() {
		return payTime;
	}
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	public String getDeliveryAddress() {
		return deliveryAddress;
	}
	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getConsignee() {
		return consignee;
	}
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getCreateTimeEnd() {
		return createTimeEnd;
	}
	public void setCreateTimeEnd(Date createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}
	public Integer getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(Integer deviceType) {
		this.deviceType = deviceType;
	}
	public String getLeaveNote() {
		return leaveNote;
	}
	public void setLeaveNote(String leaveNote) {
		this.leaveNote = leaveNote;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public BigDecimal getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}
	public Integer getNums() {
		return nums;
	}
	public void setNums(Integer nums) {
		this.nums = nums;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getStatusStr() {
		if(1==status) return "待付款/待协商";
		if(2==status) return "待确定";
		if(3==status) return "待发货";
		if(4==status) return "已发货";
		if(5==status) return "已完成";
		if(6==status) return "已关闭";
		if(7==status) return "售后";
		return "-";
	}
	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}
	public String getCreateTimeStr() {
		if(createTime != null){
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(createTime);
		}else {
			return null;
		}
	}
	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}
	public String getTypeStr() {
		if("001".equals(type)) return "平面，装饰物料";
		if("002".equals(type)) return "定制物料";
		return "-";
	}
	public void setTypeStr(String typeStr) {
		this.typeStr = typeStr;
	}
	
	public String getCreateTimeEndStr() {
		if(createTimeEnd != null){
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(createTimeEnd);
		}else {
			return null;
		}
	}
	public void setCreateTimeEndStr(String createTimeEndStr) {
		this.createTimeEndStr = createTimeEndStr;
	}
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}
	
	public String getPostCompany() {
		return postCompany;
	}
	public void setPostCompany(String postCompany) {
		this.postCompany = postCompany;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getCourierNumber() {
		return courierNumber;
	}
	public void setCourierNumber(String courierNumber) {
		this.courierNumber = courierNumber;
	}
	
	
	public String getPayTimeStr() {
		if(payTime != null){
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(payTime);
		}else {
			return null;
		}
	}
	public void setPayTimeStr(String payTimeStr) {
		this.payTimeStr = payTimeStr;
	}
	
	public Long getDesigner() {
		return designer;
	}
	public void setDesigner(Long designer) {
		this.designer = designer;
	}
	public String getStartUrl() {
		return startUrl;
	}
	public void setStartUrl(String startUrl) {
		this.startUrl = startUrl;
	}
	public String getEndUrl() {
		return endUrl;
	}
	public void setEndUrl(String endUrl) {
		this.endUrl = endUrl;
	}
	@Override
	public String toString() {
		return "MaterialOrder [id=" + id + ", uid=" + uid + ", orderAmount="
				+ orderAmount + ", balance=" + balance + ", freight=" + freight
				+ ", name=" + name + ", supplierId=" + supplierId
				+ ", orderNo=" + orderNo + ", tradeNo=" + tradeNo
				+ ", outTradeNo=" + outTradeNo + ", thirdUid=" + thirdUid
				+ ", payType=" + payType + ", payTime=" + payTime
				+ ", payTimeStr=" + payTimeStr + ", deliveryAddress="
				+ deliveryAddress + ", mobile=" + mobile + ", consignee="
				+ consignee + ", payStatus=" + payStatus + ", courierNumber="
				+ courierNumber + ", status=" + status + ", materialId="
				+ materialId + ", userName=" + userName + ", statusStr="
				+ statusStr + ", createTime=" + createTime + ", createTimeStr="
				+ createTimeStr + ", createTimeEnd=" + createTimeEnd
				+ ", createTimeEndStr=" + createTimeEndStr + ", deviceType="
				+ deviceType + ", leaveNote=" + leaveNote + ", type=" + type
				+ ", typeStr=" + typeStr + ", salePrice=" + salePrice
				+ ", nums=" + nums + ", title=" + title + ", remark=" + remark
				+ ", url=" + url + ", postCompany=" + postCompany
				+ ", description=" + description + ", designer=" + designer
				+ ", startUrl=" + startUrl + ", endUrl=" + endUrl
				+ ", version=" + version + "]";
	}
	
}
