package com.xmniao.xmn.core.common.request;

import net.sf.oval.constraint.Max;
import net.sf.oval.constraint.Min;
import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;


/**
 * 
*    
* 项目名称：xmnService   
* 类名称：PayRequest   
* 类描述：   支付封装参数
* 创建人：xiaoxiong   
* 创建时间：2016年7月13日 下午3:04:48   
* @version    
*
 */
public class PayRequest extends BaseRequest{
	
	@NotNull(message="订单金额不能为空")
	private Double amount;//订单金额
	@NotNull(message="支付方式不能为空")
	private String paymenttype; //支付方式，1000001:支付宝SDK支付;1000003:微信SDK支付;1000013:公众号支付，1000000：钱包支付'
	
	
	private String name;
	
	
	private String phone;
	
	
	private String address;
	
	@Min(value=2)
	@Max(value=2)
	@NotNull(message="订单类型不能为空")
	private Integer ordertype;//订单类型 目前固定2
	@NotNull(message="订单来源不能为空")
	private String source;//订单来源001:业务系统-SAAS套餐订单;002:业务系统-SAAS软件订单;003:业务系统-积分商品订单
	private String subject;//标题
	private Double integral;//积分支付金额
	@NotNull(message="订单编号不能为空")
	private String ordersn ;//订单编号
	
	
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPaymenttype() {
		return paymenttype;
	}

	public void setPaymenttype(String paymenttype) {
		this.paymenttype = paymenttype;
	}

	public Integer getOrdertype() {
		return ordertype;
	}

	public void setOrdertype(Integer ordertype) {
		this.ordertype = ordertype;
	}



	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}


	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Double getIntegral() {
		return integral;
	}

	public void setIntegral(Double integral) {
		this.integral = integral;
	}

	public String getOrdersn() {
		return ordersn;
	}

	public void setOrdersn(String ordersn) {
		this.ordersn = ordersn;
	}
	
	
}
