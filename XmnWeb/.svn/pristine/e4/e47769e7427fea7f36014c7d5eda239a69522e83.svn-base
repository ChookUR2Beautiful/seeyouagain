/**
 * 文件名：TActivity.java
 * 
 * 创建日期:2015-01-14 10:03:36
 * 
 * Copyright © 寻蜜鸟网络科技有限公司
 */
package com.xmniao.xmn.core.marketingmanagement.entity;
import java.util.Date;

import com.xmniao.xmn.core.base.BaseEntity;
/**
 * 项目名称：XmnWeb
 * 
 * 类名称：TActivityRule
 * 
 * 类说明：营销活动管理
 * 
 * 创建人：caoyingde
 * 
 * 创建时间：2015-01-14 10：09：03
 * 
 * Copyright © 广东寻蜜鸟网络科技有限公司
 */
public class TActivityRule extends BaseEntity {
	private static final long serialVersionUID = 4097740642012046107L;
	private Integer rid;
	private Integer aid;
	private String minMoeny;//满返活动：赠送区间最小金额，0表示0 折扣补贴活动：单日单店最多补贴次数，0表示不限制 刮刮卡：中奖金额最小值
	private String maxMoeny;//满返活动：赠送区间最大金额，0表示无穷大 折扣补贴活动：单次最高补贴金额，0表示不限制 刮刮卡活动：中奖金额最大值，可以等于中奖金额最小值
	private String giveMoney;//满返活动：赠送金额，必须大于0 折扣补贴活动：补贴折扣，必须大于0 刮刮卡活动：中奖比例，大于0，小于1
	private String addUser;
	private Date addTime;
	private String updateUser;
	private Date updateTime;
	private String awardName;//奖项名称
	private int isRelationSellerNum;//参与活动商家总数
	public Integer getRid() {
		return rid;
	}


	public void setRid(Integer rid) {
		this.rid = rid;
	}


	public Integer getAid() {
		return aid;
	}


	public void setAid(Integer aid) {
		this.aid = aid;
	}


	


	public String getMinMoeny() {
		return minMoeny;
	}


	public void setMinMoeny(String minMoeny) {
		this.minMoeny = minMoeny;
	}


	public String getMaxMoeny() {
		return maxMoeny;
	}


	public void setMaxMoeny(String maxMoeny) {
		this.maxMoeny = maxMoeny;
	}
	

	public String getGiveMoney() {
		return giveMoney;
	}


	public void setGiveMoney(String giveMoney) {
		this.giveMoney = giveMoney;
	}


	public String getAddUser() {
		return addUser;
	}


	public void setAddUser(String addUser) {
		this.addUser = addUser;
	}


	public Date getAddTime() {
		return addTime;
	}


	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}


	public String getUpdateUser() {
		return updateUser;
	}


	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}


	public Date getUpdateTime() {
		return updateTime;
	}


	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}


	public String getAwardName() {
		return awardName;
	}


	public void setAwardName(String awardName) {
		this.awardName = awardName;
	}


	public int getIsRelationSellerNum() {
		return isRelationSellerNum;
	}


	public void setIsRelationSellerNum(int isRelationSellerNum) {
		this.isRelationSellerNum = isRelationSellerNum;
	}


	@Override
	public String toString() {
		return "TActivityRule [rid=" + rid +", aid=" + aid +", minMoeny=" + minMoeny +", maxMoeny=" + maxMoeny+", giveMoney=" + giveMoney +
				", addUser=" + addUser +", addTime=" + addTime+", updateUser=" + updateUser +", updateTime=" + updateTime +", awardName=" + awardName +", isRelationSellerNum=" + isRelationSellerNum +"]";
	}
}
