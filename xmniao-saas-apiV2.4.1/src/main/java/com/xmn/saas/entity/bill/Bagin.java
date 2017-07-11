package com.xmn.saas.entity.bill;

public class Bagin {

	/**
	 * 订单号
	 */
	private Long bid;
	
	/**
	 * 验证吗
	 */
	private String codeid;
	
	/**
	 * 支付金额
	 */
	private double money;
	
	/**
	 * 支付时间
	 */
	private String zdate;
	
	/**
	 * 菜品ID
	 */
	private int bpid;

	/**
	 * 返回类型
	 */
	private int type=2;
	
	

	public int getType() {
		return type;
	}


	public void setType(int type) {
		this.type = type;
	}


	public int getBpid() {
		return bpid;
	}


	public void setBpid(int bpid) {
		this.bpid = bpid;
	}


	public Long getBid() {
		return bid;
	}


	public void setBid(Long bid) {
		this.bid = bid;
	}


	public String getCodeid() {
		return codeid;
	}


	public void setCodeid(String codeid) {
		this.codeid = codeid;
	}


	public double getMoney() {
		return money;
	}


	public void setMoney(double money) {
		this.money = money;
	}


	public String getZdate() {
		return zdate;
	}


	public void setZdate(String zdate) {
		this.zdate = zdate;
	}
	
	
	
}
