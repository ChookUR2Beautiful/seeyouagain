package com.xmniao.xmn.core.marketingmanagement.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.xmniao.xmn.core.base.BaseEntity;
import com.xmniao.xmn.core.fresh.entity.TBillFresh;
import com.xmniao.xmn.core.util.DateUtil;


/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TMaterialOrder
 * 
 * 类描述：物料订单信息
 * 
 * 创建人： yhl
 * 
 * 创建时间：2016年7月15日13:47:56
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TMaterialOrder extends BaseEntity{

	private static final long serialVersionUID = 3708753077931756949L;
	//
	private Integer id ;
	//'订单号'
	private String order_sn;
	//'订单号' 别名
	private String orderSn;
	//'用户id'
	private Long uid;
	//'用户名'
	private String userInfoName;
	//'商户id'
	private String mid; 
	//商户名称
	private String sellername;
	//'0 待支付 1 已支付（待发货） 2 取消支付/支付失败 3 已发货（待收货）4已收货（订单完成）'
	private Integer status;
	//订单状态描述
	private String bstatus;
	//'支付ID，支付接口产生 用于查询支付记录使用'
	private String payid;
	//'支付方式，1000001:支付宝SDK支付;1000003:微信SDK支付;1000013:公众号支付，1000000：钱包支付'
	private String payment_type;
	//'第三方支付帐号'
	private String third_uid;
	//'第三方支付流水号'
	private String third_serial;
	//'配送地址'
	private String address;
	//'联系手机，配送时的联系方式'
	private String mobile;
	//'收货人'
	private String consignee;
	//'订单总金额'
	private BigDecimal amount; 
	//'现金支付总额'
	private BigDecimal cash; 
	//'余额支付金额'
	private BigDecimal balance;
	//'积分支付总额'
	private BigDecimal integral;
	//'运费'
	private BigDecimal freight;
	// '创建(拆单)时间'
	private Date create_time;
	//'创建(拆单)时间'
	private String create_time_str;
	// 更新时间
	private Date modify_time;
	//更新时间
	private String modify_time_str;
	//'快递单号'
	private String courier_number;
	//'快递类型，跟快递100保持一致'
	private String courier_type;
	
	//'快递类型，跟快递100保持一致'
	private String courier_type_str;
	//'发货时间'
	private Date delivery_time;
	//'发货时间'
	private String delivery_time_str;
	//'版本号，用于乐观锁控制'
	private int version;
	//标示选项卡订单状态参数
	private Integer types;
	
	private Integer types_n;
	private String order_sn_n;
	private String mobile_n;

	private String end_time;
	
	private Date subexsdate;
	
	private Date subexedate;
	
	/**
	 * 商品列表  及 商品详情
	 * */
	private List<TMaterialOrderItems> orderItemList ;
	private String orderItemDetail;
	
	public String getModify_time_str() {
		if(null==modify_time) return "-";
		return DateUtil.smartFormat(modify_time);
	}
	public void setModify_time_str(String modify_time_str) {
		this.modify_time_str = modify_time_str;
	}
	public Integer getTypes_n() {
		return types_n;
	}
	public void setTypes_n(Integer types_n) {
		this.types_n = types_n;
	}
	public String getOrder_sn_n() {
		return order_sn_n;
	}
	public void setOrder_sn_n(String order_sn_n) {
		this.order_sn_n = order_sn_n;
	}
	public String getMobile_n() {
		return mobile_n;
	}
	public void setMobile_n(String mobile_n) {
		this.mobile_n = mobile_n;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOrder_sn() {
		if (null !=order_sn_n && "" != order_sn_n) {
			return order_sn_n;
		}else {
			return order_sn;
		}
	}
	public void setOrder_sn(String order_sn) {
		this.order_sn = order_sn;
		
	}
	public Long getUid() {
		return uid;
	}
	public void setUid(Long uid) {
		this.uid = uid;
	}

	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getBstatus() {
		//0 待支付 1 已支付（待发货） 2 取消支付/支付失败 3 已发货（待收货）4已收货（订单完成）
		if (status==0)return "待支付";
		if (status==1)return "待发货";
		if (status==2)return "取消支付/支付失败";
		if (status==3)return "已发货(待收货)";
		if (status==4)return "已收货(订单完成)";
		return "-";
	}
	public void setBstatus(String bstatus) {
		this.bstatus = bstatus;
	}
	public String getPayid() {
		return payid;
	}
	public void setPayid(String payid) {
		this.payid = payid;
	}
	public String getPayment_type() {
		if (null!=payment_type) {
			if ("1000001".equals(payment_type)) {
				return "支付宝SDK支付";
			}
			if ("1000003".equals(payment_type)) {
				return "微信SDK支付";
			}
			if ("1000013".equals(payment_type)) {
				return "公众号支付";
			}
			if ("1000000".equals(payment_type)) {
				return "钱包支付";
			}
			if("1000022".equals(payment_type)){
				return "支付宝APP支付（鸟人科技）";
			}
			if("1000023".equals(payment_type)){
				return "支付宝WAP支付（鸟人科技）";
			}
			if("1000024".equals(payment_type)){
				return "微信APP支付（鸟人科技）";
			}
			if("1000025".equals(payment_type)){
				return "微信公众号支付（鸟人科技）";
			}
		}
		return "--";
	}
	public void setPayment_type(String payment_type) {
		this.payment_type = payment_type;
	}
	public String getThird_uid() {
		return third_uid;
	}
	public void setThird_uid(String third_uid) {
		this.third_uid = third_uid;
	}
	public String getThird_serial() {
		return third_serial;
	}
	public void setThird_serial(String third_serial) {
		this.third_serial = third_serial;
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
		if (null !=mobile_n && "" != mobile_n) {
			this.mobile = mobile_n;
		}else {
			this.mobile = mobile;
		}
		this.mobile = mobile;
	}
	public String getConsignee() {
		return consignee;
	}
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
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
	public BigDecimal getIntegral() {
		return integral;
	}
	public void setIntegral(BigDecimal integral) {
		this.integral = integral;
	}
	public BigDecimal getFreight() {
		return freight;
	}
	public void setFreight(BigDecimal freight) {
		this.freight = freight;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public String getCreate_time_str() {
		if(null==create_time) return "-";
		return DateUtil.smartFormat(create_time);
	}
	public void setCreate_time_str(String create_time_str) {
		this.create_time_str = create_time_str;
	}
	public Date getModify_time() {
		return modify_time;
	}
	public void setModify_time(Date modify_time) {
		this.modify_time = modify_time;
	}
	public String getCourier_number() {
		return courier_number;
	}
	public void setCourier_number(String courier_number) {
		this.courier_number = courier_number;
	}
	public String getCourier_type() {
		return courier_type;
	}
	public void setCourier_type(String courier_type) {
		this.courier_type = courier_type;
	}
	public String getCourier_type_str() {
		return TBillFresh.expresstype(courier_type);
	}
	public void setCourier_type_str(String courier_type_str) {
		this.courier_type_str = courier_type_str;
	}
	public Date getDelivery_time() {
		return delivery_time;
	}
	public void setDelivery_time(Date delivery_time) {
		this.delivery_time = delivery_time;
	}
	public String getDelivery_time_str() {
		if(null==delivery_time) return "-";
		return DateUtil.smartFormat(delivery_time);
	}
	public void setDelivery_time_str(String delivery_time_str) {
		this.delivery_time_str = delivery_time_str;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public Integer getTypes() {
		if (null !=types_n) {
			return types_n;
		}else {
			return types;
		}
	}
	public void setTypes(Integer types) {
		this.types = types;
	}
	
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	
	public List<TMaterialOrderItems> getOrderItemList() {
		return orderItemList;
	}
	public void setOrderItemList(List<TMaterialOrderItems> orderItemList) {
		this.orderItemList = orderItemList;
	}
	public String getOrderItemDetail() {
		return orderItemDetail;
	}
	public void setOrderItemDetail(String orderItemDetail) {
		this.orderItemDetail = orderItemDetail;
	}
	public Date getSubexsdate() {
		return subexsdate;
	}
	public void setSubexsdate(Date subexsdate) {
		this.subexsdate = subexsdate;
	}
	public Date getSubexedate() {
		return subexedate;
	}
	public void setSubexedate(Date subexedate) {
		this.subexedate = subexedate;
	}
	public String getOrderSn() {
		return orderSn;
	}
	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}
	
	public String getSellername() {
		return sellername;
	}
	public void setSellername(String sellername) {
		this.sellername = sellername;
	}
	
	public String getUserInfoName() {
		return userInfoName;
	}
	public void setUserInfoName(String userInfoName) {
		this.userInfoName = userInfoName;
	}
	@Override
	public String toString() {
		return "TMaterialOrder [id=" + id + ", order_sn=" + order_sn
				+ ", orderSn=" + orderSn + ", uid=" + uid + ", mid=" + mid
				+ ", sellername=" + sellername + ", status=" + status + ", bstatus="
				+ bstatus + ", payid=" + payid + ", payment_type="
				+ payment_type + ", third_uid=" + third_uid + ", third_serial="
				+ third_serial + ", address=" + address + ", mobile=" + mobile
				+ ", consignee=" + consignee + ", amount=" + amount + ", cash="
				+ cash + ", balance=" + balance + ", integral=" + integral
				+ ", freight=" + freight + ", create_time=" + create_time
				+ ", create_time_str=" + create_time_str + ", modify_time="
				+ modify_time + ", modify_time_str=" + modify_time_str
				+ ", courier_number=" + courier_number + ", courier_type="
				+ courier_type + ", courier_type_str=" + courier_type_str
				+ ", delivery_time=" + delivery_time + ", delivery_time_str="
				+ delivery_time_str + ", version=" + version + ", types="
				+ types + ", types_n=" + types_n + ", order_sn_n=" + order_sn_n
				+ ", mobile_n=" + mobile_n + ", end_time=" + end_time
				+ ", subexsdate=" + subexsdate + ", subexedate=" + subexedate
				+ ", orderItemList=" + orderItemList + ", orderItemDetail="
				+ orderItemDetail + "]";
	}
	
}
