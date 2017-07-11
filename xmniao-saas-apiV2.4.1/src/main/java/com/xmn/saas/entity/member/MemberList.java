package com.xmn.saas.entity.member;

import java.util.List;

public class MemberList {
	
	/**
	 * 日期
	 */
	private String sdate;
	
	/**
	 * 数量
	 */
	private int count;
	
	/**
	 * 周几
	 */
	private String weekDay;
	
	/**
	 * 每日会员数量
	 */
	private List<MemberBill> memberBillList;
	
	
	public List<MemberBill> getMemberBillList() {
		return memberBillList;
	}

	public void setMemberBillList(List<MemberBill> memberBillList) {
		this.memberBillList = memberBillList;
	}

	public String getSdate() {
		return sdate;
	}

	public void setSdate(String sdate) {
		this.sdate = sdate;
	}



	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getWeekDay() {
		return weekDay;
	}

	public void setWeekDay(String weekDay) {
		this.weekDay = weekDay;
	}


	

}
