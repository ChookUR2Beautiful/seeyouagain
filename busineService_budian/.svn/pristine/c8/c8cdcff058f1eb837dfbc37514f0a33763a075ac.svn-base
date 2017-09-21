package com.xmniao.common;

/**
 * 
 * @Title: PaymentInfo.java
 * 
 * @Package com.xmniao.base.enums
 * 
 * @Description: 分账系统支付方式及支付费率
 * 
 * @author Administrator
 * 
 * @date 2014年12月29日 下午1:49:43
 * 
 * @version V1.0
 */
public enum PaymentType {
	S_Y_Z_F("1000000", "收益支付", "0.006"),
	Z_F_B("1000001", "支付宝", "0.0125"), 
	U_F_Z_F("1000002", "U付支付", "0.008"),
	W_X_Z_F("1000003", "微信支付", "0.006"),
	W_Y_Z_F("1000004", "网银支付", "0.0055"), 
	H_F_Z_F("1000005", "汇付支付", "0.0055"),
	R_B_Z_F("1000006", "融宝支付", "0.0055"),
	S_F_T_Z_F("1000007","盛付通支付","0.008"),
	K_Q_Z_F("1000008", "快钱支付", "0.008"),
	T_L_Z_F("1000009", "通联支付", "0.0065"),
	L_L_Z_F("1000010", "连连支付", "0.006"),
	Y_H_Q_Z_F("1000011", "优惠券支付", "0.006");

	/**
	 * 支付id
	 */
	private String id;
    
	/**
	 * 支付渠道
	 */
	private String name;

	/**
	 * 支付费率
	 */
	private String rate;

	private PaymentType(String id, String name, String rate) {
		this.id = id;
		this.name = name;
		this.rate = rate;
	}

	/**
	 * 按编号获取支付渠道
	 * @param id
	 * @return
	 */
	public static String getNameById(String id) {
		for (PaymentType type : PaymentType.values()) {
			if (type.getId().equals(id)) {
				return type.getName();
			}
		}
		return null;
	}

	/**
	 * 按编号获取支付费率
	 * @param id
	 * @return
	 */
	public static String getRateById(String id) {
		for (PaymentType type : PaymentType.values()) {
			if (type.getId().equals(id)) {
				return type.getRate();
			}
		}
		return null;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

}
