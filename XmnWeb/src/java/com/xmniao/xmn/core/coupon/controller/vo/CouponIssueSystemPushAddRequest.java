package com.xmniao.xmn.core.coupon.controller.vo;

import com.alibaba.fastjson.JSON;
import com.xmniao.xmn.core.coupon.entity.TCoupon;

import java.util.List;

/**
 * 系统推送添加优惠劵 VO
 * Created by yang.qiang on 2017/5/26.
 */
public class CouponIssueSystemPushAddRequest {
    private Integer ctype;// 优惠券类型(用于接收sql操作结果)，0本地生活类优惠卷(不可使用积分组合支付) 1生鲜类现金卷(可以和积分组合支付) 5 平台券 6 鸟币
    private String coupons;
    private Integer userType;//0按会员类型,1按具体用户
    private Integer levelSelect;
    private String selectUsers;
    private double birdCoin;//鸟币金额
    
    private Integer getWay;//获取方式，1摇一摇，2满返，3短信获取 4直接发放,5：订单后刮优惠劵；6:分享后刮优惠劵;7.系统推送; 8.庄园激活奖励;9新时尚大赛奖励


    /** 转换优惠劵列表*/
    public List<TCoupon> convertCouponList(){
        return JSON.parseArray(coupons, TCoupon.class);
    }

    @Override
    public String toString() {
        return "CouponIssueSystemPushAddRequest{" +
                "ctype=" + ctype +
                ", coupons='" + coupons + '\'' +
                ", userType=" + userType +
                ", levelSelect=" + levelSelect +
                ", selectUsers='" + selectUsers + '\'' +
                '}';
    }
    
    
    


	/**
	 * @return the birdCoin
	 */
	public double getBirdCoin() {
		return birdCoin;
	}

	/**
	 * @param birdCoin the birdCoin to set
	 */
	public void setBirdCoin(double birdCoin) {
		this.birdCoin = birdCoin;
	}

	/**
	 * @return the getWay
	 */
	public Integer getGetWay() {
		return getWay;
	}

	/**
	 * @param getWay the getWay to set
	 */
	public void setGetWay(Integer getWay) {
		this.getWay = getWay;
	}

	public Integer getCtype() {
        return ctype;
    }

    public void setCtype(Integer ctype) {
        this.ctype = ctype;
    }

    public String getCoupons() {
        return coupons;
    }

    public void setCoupons(String coupons) {
        this.coupons = coupons;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getLevelSelect() {
        return levelSelect;
    }

    public void setLevelSelect(Integer levelSelect) {
        this.levelSelect = levelSelect;
    }

    public String getSelectUsers() {
        return selectUsers;
    }

    public void setSelectUsers(String selectUsers) {
        this.selectUsers = selectUsers;
    }
}
