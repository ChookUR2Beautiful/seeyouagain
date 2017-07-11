/**
 * 
 */
package com.xmn.saas.entity.coupon.type;

/**   
 * 项目名称：xmniao-saas-api    
 * 类描述：   账单支付类型枚举
 * 创建人：huangk   
 * 创建时间：2016年10月14日 下午2:33:37      
 */

public enum Paytype {
	sypay("1000000","收益支付"),
	alipay("1000001","支付宝"),
	umpay("1000002","U付"),
	wxpay("1000003","微信"),
	wypay("1000004","网银"),
	hfpay("1000005","汇付天下"),
	rbpay("1000006","融宝"),
	sftpay("1000007","盛付通"),
	kqpay("1000008","快钱"),
	tlpay("1000009","通联"),
	llpay("10000010","连连"),
	yhqpay("10000011","优惠券"),
	wxscpay("10000013","微信公众号支付");
	
	private String value;

	private String desc;

	private Paytype(String value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}

	public static String getDesc(String type) {
		for (Paytype enumType : Paytype.values()) {
			if (enumType.value.equals(type)) {
				return enumType.getDesc();
			}
		}
		return "" + type;
	}
}
