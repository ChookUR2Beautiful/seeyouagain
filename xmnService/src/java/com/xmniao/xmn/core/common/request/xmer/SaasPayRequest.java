package com.xmniao.xmn.core.common.request.xmer;

import com.xmniao.xmn.core.base.BaseRequest;

import net.sf.oval.constraint.Max;
import net.sf.oval.constraint.Min;
import net.sf.oval.constraint.NotNull;

public class SaasPayRequest extends BaseRequest{

	@NotNull(message="支付方式不能为空")
	private String paymenttype; //支付方式，1000001:支付宝SDK支付;1000003:微信SDK支付;1000013:公众号支付，1000000：钱包支付'
	
	@Min(value=2)
	@Max(value=2)
//	@NotNull(message="订单类型不能为空")
	private Integer ordertype = 2;//订单类型（1:充值订单 2:消费订单）
	
//	@NotNull(message="订单来源不能为空")
	private String source = "001";//订单来源001:业务系统-SAAS套餐订单;002:业务系统-SAAS软件订单;003:业务系统-积分商品订单
	
//	@NotNull(message="订单来源不能为空")
	private String subject;//标题
	
	@NotNull(message="订单编号不能为空")
	private String ordersn ;//订单编号

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

	public String getOrdersn() {
		return ordersn;
	}

	public void setOrdersn(String ordersn) {
		this.ordersn = ordersn;
	}

	@Override
	public String toString() {
		return "SaasPayRequest [paymenttype=" + paymenttype + ", ordertype=" + ordertype + ", source=" + source
				+ ", subject=" + subject + ", ordersn=" + ordersn +","+ super.toString()+ "]";
	}
	
	
	
}
