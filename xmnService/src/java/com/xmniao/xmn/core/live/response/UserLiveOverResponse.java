package com.xmniao.xmn.core.live.response;

import com.xmniao.xmn.core.base.BaseResponse;

/**
 * 用户结束直播返回参数
* 类名称：UserLiveOverResponse   
* 类描述：   
* 创建人：xiaoxiong   
* 创建时间：2016年12月19日 下午3:59:56
 */
public class UserLiveOverResponse{
	

	/**
	 * 主播头像
	 */
	private String avatar;
	
	/**
	 * 主播昵称
	 */
	private String nname;
	
	/**
	 * 观看人数
	 */
	private int viewNum = 0;
	
	/**
	 * 主播是否有其他粉丝卷
	 */
	private int isOtherCoupon = 0;
	
	/**
	 * 返现总额
	 */
	private String rebateMoney ;
	
	/**
	 * 赠送鸟豆总额
	 */
	private String liveBeanMoney;

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getNname() {
		return nname;
	}

	public void setNname(String nname) {
		this.nname = nname;
	}

	public int getViewNum() {
		return viewNum;
	}

	public void setViewNum(int viewNum) {
		this.viewNum = viewNum;
	}

	public int getIsOtherCoupon() {
		return isOtherCoupon;
	}

	public void setIsOtherCoupon(int isOtherCoupon) {
		this.isOtherCoupon = isOtherCoupon;
	}

	public String getRebateMoney() {
		return rebateMoney;
	}

	public void setRebateMoney(String rebateMoney) {
		this.rebateMoney = rebateMoney;
	}

	public String getLiveBeanMoney() {
		return liveBeanMoney;
	}

	public void setLiveBeanMoney(String liveBeanMoney) {
		this.liveBeanMoney = liveBeanMoney;
	}
	
}
