package com.xmn.saas.entity.activity;

import java.util.Date;

/**
 * 活动领取父类
 * 
 * @author jianming
 *
 */
public class ActivityRecord {
	private Integer uid;
	
	private String usrName; // 用户名

	private String awardName; // 奖品名称

	/*
	 * 奖品类型 优惠券类型(3.抵用券 4.赠品券 5.红包)
	 */
	private Integer awardType; // 礼品类型

	private String awardTypeToString;

	private String getWayToString; // 获得方式中文
	
	private String vipName;		//会员类型
		
	private String head;  //会员头像
	
	private String attachTime;	//绑定商户时间
	
	public String getAttachTime() {
		return attachTime;
	}


	public void setAttachTime(String attachTime) {
		this.attachTime = attachTime;
	}


	public String getAwardTypeToString() {
		if(awardType!=null){
			if(awardType==3)return "现金抵用券";
			if(awardType==4)return "赠品券";
			if(awardType==5)return "普通红包";
		}
		return "";
	}
	
	
	public String getHead() {
		return head;
	}



	public void setHead(String head) {
		this.head = head;
	}



	public void setAwardTypeToString(String awardTypeToString) {
		this.awardTypeToString = awardTypeToString;
	}
	
	public String getVipName() {
		return vipName;
	}

	public void setVipName(String vipName) {
		this.vipName = vipName;
	}

	public void setGetWayToString(String getWayToString) {
		this.getWayToString = getWayToString;
	}

	public String getAwardName() {
		return awardName;
	}

	public void setAwardName(String awardName) {
		this.awardName = awardName;
	}

	public Integer getAwardType() {
		return awardType;
	}

	public void setAwardType(Integer awardType) {
		this.awardType = awardType;
	}

	public String getUsrName() {
		return usrName;
	}

	public void setUsrName(String usrName) {
		this.usrName = usrName;
	}


	public Integer getUid() {
		return uid;
	}


	public void setUid(Integer uid) {
		this.uid = uid;
	}
	
	

}
