/**
 * 
 */
package com.xmniao.xmn.core.coupon.entity;

import com.xmniao.xmn.core.base.BaseEntity;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：SystemPushCoupon
 * 
 * 类描述： 系统推送发放优惠券
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2017-4-19 下午7:16:04 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */

public class SystemPushCoupon extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1051092254720298947L;
	
	private Integer ctype;//优惠券类型
	
	private Integer cid;//优惠券id
	
	private Integer sendNum;//发行数量
	
	private String userIds;//系统推送优惠券,用户及手机号码字符串。用户之间以逗号分隔，uid和phone之间以:;分隔(606872:;:;11800000005:;11800000005, 606873:;:;11800000006:;11800000006)

	
	/**
	 * @return the ctype
	 */
	public Integer getCtype() {
		return ctype;
	}

	/**
	 * @param ctype the ctype to set
	 */
	public void setCtype(Integer ctype) {
		this.ctype = ctype;
	}

	/**
	 * @return the cid
	 */
	public Integer getCid() {
		return cid;
	}

	/**
	 * @param cid the cid to set
	 */
	public void setCid(Integer cid) {
		this.cid = cid;
	}

	/**
	 * @return the sendNum
	 */
	public Integer getSendNum() {
		return sendNum;
	}

	/**
	 * @param sendNum the sendNum to set
	 */
	public void setSendNum(Integer sendNum) {
		this.sendNum = sendNum;
	}

	/**
	 * @return the userIds
	 */
	public String getUserIds() {
		return userIds;
	}

	/**
	 * @param userIds the userIds to set
	 */
	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}

	@Override
	public String toString() {
		return "SystemPushCoupon [ctype=" + ctype + ", cid=" + cid
				+ ", sendNum=" + sendNum + ", userIds=" + userIds + "]";
	}
	
	

}
