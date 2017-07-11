package com.xmn.saas.entity.member;

import java.math.BigDecimal;

public class MemberDetail {
	
	/**
	 * 昵称
	 */
	private String name;
	
	/**
	 * 头像
	 */
	private String avatar;
	
	/**
	 * 手机号码
	 */
	private String phone;
	
	/**
	 * 消费次数
	 */
	private int count;
	
	/**
	 * 最近消费日期
	 */
	private String nearDate;
	
	/**
	 * 首次消费时间
	 */
	private String firstDate;
	
	/**
	 * 最大消费
	 */
	private double maxMoney;
	
	/**
	 * 最小消费
	 */
	private double minMoney;
	
	/**
	 * 累计消费
	 */
	private double sumMoney;
	
	/**
	 * 平均消费
	 */
	private double avgMoney;
	
	/**
	 * 所属商家
	 */
	private int isBind;

	
	
	public int getIsBind() {
		return isBind;
	}

	public void setIsBind(int isBind) {
		this.isBind = isBind;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getNearDate() {
		return nearDate;
	}

	public void setNearDate(String nearDate) {
		this.nearDate = nearDate;
	}

	public String getFirstDate() {
		return firstDate;
	}

	public void setFirstDate(String firstDate) {
		this.firstDate = firstDate;
	}

	public double getMaxMoney() {
		 BigDecimal bg = new BigDecimal(this.maxMoney);  
         double maxMoney = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();  
         return maxMoney;
	}

	public void setMaxMoney(double maxMoney) {
		this.maxMoney = maxMoney;
	}

	public double getMinMoney() {

		 BigDecimal bg = new BigDecimal(this.minMoney);  
		 double minMoney = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		 return minMoney;
	}

	public void setMinMoney(double minMoney) {
		this.minMoney = minMoney;
	}

	public double getSumMoney() {
		
		 BigDecimal bg = new BigDecimal(this.sumMoney);  
         double sumMoney = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return sumMoney;
	}

	public void setSumMoney(double sumMoney) {
		this.sumMoney = sumMoney;
	}

	public double getAvgMoney() {
		 BigDecimal bg = new BigDecimal(this.avgMoney);  
         double avgMoney = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return avgMoney;
	}

	public void setAvgMoney(double avgMoney) {
		this.avgMoney = avgMoney;
	}
	
	
	

}
