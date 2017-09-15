package com.xmniao.xmn.core.market.entity.pay;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

/**
 * 
* @projectName: xmnService 
* @ClassName: OrderPriceList    
* @Description:订单价格清单   
* @author: liuzhihao   
* @date: 2016年12月28日 下午8:22:38
 */
@SuppressWarnings("serial")
public class OrderPriceList implements Serializable{

	private BigDecimal totalPrice;//总价格
	
	private BigDecimal totalIntegral;//总积分
	
	private Integer totalNum;//总个数
	
	private BigDecimal totalWeight;//总重量
	
	private BigDecimal totalPostPrice;//运费总价 
	
	private BigDecimal denomination;//优惠卷面额
	
	private Integer cdid;//优惠卷ID
	
	private Integer isCoupon;//是否有优惠卷可用 0 没有 1 有 
	
	private Integer isUserCoupon;//是否使用优惠卷
	
	private Set<Integer> tids;

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public BigDecimal getTotalIntegral() {
		return totalIntegral;
	}

	public void setTotalIntegral(BigDecimal totalIntegral) {
		this.totalIntegral = totalIntegral;
	}

	public Integer getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

	public BigDecimal getTotalWeight() {
		return totalWeight;
	}

	public void setTotalWeight(BigDecimal totalWeight) {
		this.totalWeight = totalWeight;
	}

	public BigDecimal getTotalPostPrice() {
		return totalPostPrice;
	}

	public void setTotalPostPrice(BigDecimal totalPostPrice) {
		this.totalPostPrice = totalPostPrice;
	}

	public Set<Integer> getTids() {
		return tids;
	}

	public void setTids(Set<Integer> tids) {
		this.tids = tids;
	}

	public BigDecimal getDenomination() {
		return denomination;
	}

	public void setDenomination(BigDecimal denomination) {
		this.denomination = denomination;
	}

	public Integer getCdid() {
		return cdid;
	}

	public void setCdid(Integer cdid) {
		this.cdid = cdid;
	}

	public Integer getIsCoupon() {
		return isCoupon;
	}

	public void setIsCoupon(Integer isCoupon) {
		this.isCoupon = isCoupon;
	}
	
	public Integer getIsUserCoupon() {
		return isUserCoupon;
	}

	public void setIsUserCoupon(Integer isUserCoupon) {
		this.isUserCoupon = isUserCoupon;
	}

	@Override
	public String toString() {
		return "OrderPriceList [totalPrice=" + totalPrice + ", totalIntegral=" + totalIntegral + ", totalNum=" + totalNum
			+ ", totalWeight=" + totalWeight + ", totalPostPrice=" + totalPostPrice + ", denomination=" + denomination + ", cdid="
			+ cdid + ", isCoupon=" + isCoupon + ", isUserCoupon=" + isUserCoupon + ", tids=" + tids + "]";
	}

	
	
}
