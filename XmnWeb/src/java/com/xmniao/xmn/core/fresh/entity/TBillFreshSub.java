package com.xmniao.xmn.core.fresh.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.xmniao.xmn.core.base.BaseEntity;
import com.xmniao.xmn.core.util.DateUtil;
import com.xmniao.xmn.core.util.SystemConstants;

public class TBillFreshSub extends BaseEntity {

	private static final long serialVersionUID = -5886936068433182262L;

	private Long id;
	private String orderSn;// 父订单号，对应t_bill_fresh表bid
	private String subOrderSn;// 子订单号
	private Long supplierId;// 供应商id，对应t_supplier表supplierId
	private Long uid;// 用户id
	private BigDecimal totalAmount;// 订单总金
	private BigDecimal preferential;// 订单优惠金额
	private BigDecimal cashAmount;// 现金支付总额
	private BigDecimal balanceAmount;// 余额支付金额
	private BigDecimal integralAmount;// 积分支付总额
	private BigDecimal coinAmount; //鸟币支付金额
	private BigDecimal freight;// 运费
	private Integer status;// 0 待发货 1 已发货（待收货） 2 （订单锁定）取消订单，待发货状态可取消订单 3 退款中 4
							// 退款失败 5 已退款 6 订单完成（确认收货）
	private String strStatus;//订单状态，用于导出
	private String courierNumber;// 快递单号
	private String courierType;// 快递类型，跟快递100保持一致
	private String strCourierType;//快递类型：编号对应的中文的快递类型
	private Date deliveryTime;// 发货时间
	private Date createTime;// 创建(拆单)时间
	private Date modifyTime;// NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE
									// CURRENT_TIMESTAMP,
	
	private String deliveryTimeStr;// 发货时间Str
	private String createTimeStr;// 创建(拆单)时间Str
	private String modifyTimeStr;//Str  NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE // CURRENT_TIMESTAMP,
	
	private String memo;// 备注,如退款失败原因备注
	private String address;// 配送地址
	private String mobile;// 联系手机，配送时的联系方式s
	private String consignee;// 收货人
	private String operator;//操作人
	private Integer hstatus;//分账状态
	private String strHstatus;//分账状态，字符串
	/**
	 * 分账明细
	 */
	private String commission;
	private String xmniaoMoney;//分账后剩余金额
	private String memberBackMoney;//消费用户返现金额
	private String bSellerMoney;//用户所属商户分账金额
	private String sellerAreaMoney;//经销商分账金额
	
	private Integer version;// 版本号，用于乐观锁控制
	// 用于时间范围查询
	private Date sdate;
	private Date edate;
	private Date subDeliverySdate;
	private Date subDeliveryEdate;
	private Date subCreateSdate;
	private Date subCreateEdate;
	
	// 导出时间选择
	private String subexsdate;//导出起始时间
	private String subexedate;//导出结束时间

	private String supplierName;	//供应商名称
	
	private List<TProductBill> productList;  
    private String productDetail;//商品详情
    
    private String paytype;	//支付方式
	
    private String phoneid; //电话
    
    private String paytypeString;
    
    private Integer activityType;
    
    private Integer sendUid;
    
	public Integer getSendUid() {
		return sendUid;
	}

	public void setSendUid(Integer sendUid) {
		this.sendUid = sendUid;
	}

	public String getbSellerMoney() {
		return bSellerMoney;
	}

	public void setbSellerMoney(String bSellerMoney) {
		this.bSellerMoney = bSellerMoney;
	}

	public Integer getActivityType() {
		return activityType;
	}

	public void setActivityType(Integer activityType) {
		this.activityType = activityType;
	}

	public String getPaytypeString() {
    	return SystemConstants.getPayTypeText(paytype);
	}

	public void setPaytypeString(String paytypeString) {
		this.paytypeString = paytypeString;
	}

	public BigDecimal getCoinAmount() {
		return coinAmount;
	}

	public void setCoinAmount(BigDecimal coinAmount) {
		this.coinAmount = coinAmount;
	}

	public String getPaytype() {
		return paytype;
	}

	public void setPaytype(String paytype) {
		this.paytype = paytype;
	}

	public String getPhoneid() {
		return phoneid;
	}

	public void setPhoneid(String phoneid) {
		this.phoneid = phoneid;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderSn() {
		return orderSn;
	}

	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}

	public String getSubOrderSn() {
		return subOrderSn;
	}

	public void setSubOrderSn(String subOrderSn) {
		this.subOrderSn = subOrderSn;
	}

	public Long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public BigDecimal getPreferential() {
		return preferential;
	}

	public void setPreferential(BigDecimal preferential) {
		this.preferential = preferential;
	}

	public BigDecimal getCashAmount() {
		return cashAmount;
	}

	public void setCashAmount(BigDecimal cashAmount) {
		this.cashAmount = cashAmount;
	}

	public BigDecimal getBalanceAmount() {
		return balanceAmount;
	}

	public void setBalanceAmount(BigDecimal balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

	public BigDecimal getIntegralAmount() {
		return integralAmount;
	}

	public void setIntegralAmount(BigDecimal integralAmount) {
		this.integralAmount = integralAmount;
	}

	public BigDecimal getFreight() {
		return freight;
	}

	public void setFreight(BigDecimal freight) {
		this.freight = freight;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getStrStatus() {
		// 0 待发货 1 已发货（待收货） 2 （订单锁定）取消订单，待发货状态可取消订单 3 退款中 4
		// 退款失败 5 已退款 6 订单完成（确认收货）
		switch (status) {
		case 0:
			return "待发货";
		case 1:	
			return "已发货";
		case 2:	
			return "取消订单";
		case 3:	
			return "退款中";
		case 4:	
			return "退款失败";
		case 5:	
			return "已退款";
		case 6:	
			return "订单完成";
		default:
			return strStatus + "-";
		}
	}

	public void setStrStatus(String strStatus) {
		this.strStatus = strStatus;
	}

	public String getCourierNumber() {
		return courierNumber;
	}

	public void setCourierNumber(String courierNumber) {
		this.courierNumber = courierNumber;
	}

	public String getCourierType() {
		return courierType;
	}

	public void setCourierType(String courierType) {
		this.courierType = courierType;
	}

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	public Integer getHstatus() {
		return hstatus;
	}

	public String getStrHstatus() {
		if(hstatus != null){
			switch (hstatus) {
			case 0:
				return "未分账";
			case 1:	
				return "结算通过";
			case 2:	
				return "结算未通过";
			case 3:	
				return "结算争议";
			case 4:	
				return "核算通过";
			case 5:	
				return "核算不通过";
			case 6:	
				return "核算争议";
			case 7:	
				return "财务通过";
			case 8:	
				return "财务不通过";
			case 9:	
				return "已分账";
			case 10:	
				return "自动分账处理中";
			case 11:	
				return "商家分账处理中";
			default:
				return strHstatus + "-";
			}
		}else{
			return strHstatus + "-";
		}
	}

	public void setStrHstatus(String strHstatus) {
		this.strHstatus = strHstatus;
	}

	public void setHstatus(Integer hstatus) {
		this.hstatus = hstatus;
	}

	public String getCommission() {
		return commission;
	}

	public void setCommission(String commission) {
		this.commission = commission;
	}

	public String getXmniaoMoney() {
		String str = xmniaoMoney;
		if(null !=this.commission && !"".equals(this.commission)){
			JSONObject json = JSON.parseObject(this.commission);  
			str=json.getString("xmniaoMoney");
		}
		return str;
	}

	public void setXmniaoMoney(String xmniaoMoney) {
		this.xmniaoMoney = xmniaoMoney;
	}

	public String getMemberBackMoney() {
		String str = memberBackMoney;
		if(null !=this.commission && !"".equals(this.commission)){
			JSONObject json = JSON.parseObject(this.commission);  
			str=json.getString("memberBackMoney");
		}
		return str;
	}

	public void setMemberBackMoney(String memberBackMoney) {
		this.memberBackMoney = memberBackMoney;
	}

	public String getBSellerMoney() {
		String str = bSellerMoney;
		if(null !=this.commission && !"".equals(this.commission)){
			JSONObject json = JSON.parseObject(this.commission);
			str=json.getString("bSellerMoney");
		}
		return str;
	}

	public void setBSellerMoney(String bSellerMoney) {
		this.bSellerMoney = bSellerMoney;
	}

	public String getSellerAreaMoney() {
		String str = sellerAreaMoney;
		if(null !=this.commission && !"".equals(this.commission)){
			JSONObject json = JSON.parseObject(this.commission);
			str=json.getString("sellerAreaMoney");
		}
		return str;
	}

	public void setSellerAreaMoney(String sellerAreaMoney) {
		this.sellerAreaMoney = sellerAreaMoney;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Date getSdate() {
		return sdate;
	}

	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}

	public Date getEdate() {
		return edate;
	}

	public void setEdate(Date edate) {
		this.edate = edate;
	}

	public String getSubexsdate() {
		return subexsdate;
	}

	public void setSubexsdate(String subexsdate) {
		this.subexsdate = subexsdate;
	}

	public String getSubexedate() {
		return subexedate;
	}

	public void setSubexedate(String subexedate) {
		this.subexedate = subexedate;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	
	public List<TProductBill> getProductList() {
		return productList;
	}

	public void setProductList(List<TProductBill> productList) {
		this.productList = productList;
	}

	public String getProductDetail() {
		return productDetail;
	}

	public void setProductDetail(String productDetail) {
		this.productDetail = productDetail;
	}
	
	public String getDeliveryTimeStr() {
		if(null==deliveryTime) return "-";
		return DateUtil.smartFormat(deliveryTime);
	}

	public void setDeliveryTimeStr(String deliveryTimeStr) {
		this.deliveryTimeStr = deliveryTimeStr;
	}

	public String getCreateTimeStr() {
		if(null==createTime) return "-";
		return DateUtil.smartFormat(createTime);
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}

	public String getModifyTimeStr() {
		if(null==modifyTime) return "-";
		return DateUtil.smartFormat(modifyTime);
	}

	public void setModifyTimeStr(String modifyTimeStr) {
		this.modifyTimeStr = modifyTimeStr;
	}

	public Date getSubDeliverySdate() {
		return subDeliverySdate;
	}

	public void setSubDeliverySdate(Date subDeliverySdate) {
		this.subDeliverySdate = subDeliverySdate;
	}

	public Date getSubDeliveryEdate() {
		return subDeliveryEdate;
	}

	public void setSubDeliveryEdate(Date subDeliveryEdate) {
		this.subDeliveryEdate = subDeliveryEdate;
	}

	public Date getSubCreateSdate() {
		return subCreateSdate;
	}

	public void setSubCreateSdate(Date subCreateSdate) {
		this.subCreateSdate = subCreateSdate;
	}

	public Date getSubCreateEdate() {
		return subCreateEdate;
	}

	public void setSubCreateEdate(Date subCreateEdate) {
		this.subCreateEdate = subCreateEdate;
	}

	public String getStrCourierType() {
		return strCourierType;
	}

	public void setStrCourierType(String strCourierType) {
		this.strCourierType = strCourierType;
	}

	@Override
	public String toString() {
		return "TBillFreshSub [id=" + id + ", orderSn=" + orderSn
				+ ", subOrderSn=" + subOrderSn + ", supplierId=" + supplierId
				+ ", uid=" + uid + ", totalAmount=" + totalAmount
				+ ", preferential=" + preferential + ", cashAmount="
				+ cashAmount + ", balanceAmount=" + balanceAmount
				+ ", integralAmount=" + integralAmount + ", freight=" + freight
				+ ", status=" + status + ", strStatus=" + strStatus
				+ ", courierNumber=" + courierNumber + ", courierType="
				+ courierType + ", strCourierType=" + strCourierType
				+ ", deliveryTime=" + deliveryTime + ", createTime="
				+ createTime + ", modifyTime=" + modifyTime
				+ ", deliveryTimeStr=" + deliveryTimeStr + ", createTimeStr="
				+ createTimeStr + ", modifyTimeStr=" + modifyTimeStr
				+ ", memo=" + memo + ", address=" + address + ", mobile="
				+ mobile + ", consignee=" + consignee + ", operator="
				+ operator + ", hstatus=" + hstatus + ", strHstatus="
				+ strHstatus + ", commission=" + commission + ", xmniaoMoney="
				+ xmniaoMoney + ", memberBackMoney=" + memberBackMoney
				+ ", bSellerMoney=" + bSellerMoney + ", sellerAreaMoney="
				+ sellerAreaMoney + ", version=" + version + ", sdate=" + sdate
				+ ", edate=" + edate + ", subDeliverySdate=" + subDeliverySdate
				+ ", subDeliveryEdate=" + subDeliveryEdate
				+ ", subCreateSdate=" + subCreateSdate + ", subCreateEdate="
				+ subCreateEdate + ", subexsdate=" + subexsdate
				+ ", subexedate=" + subexedate + ", supplierName="
				+ supplierName + ", productList=" + productList
				+ ", productDetail=" + productDetail + "]";
	}
	
}
