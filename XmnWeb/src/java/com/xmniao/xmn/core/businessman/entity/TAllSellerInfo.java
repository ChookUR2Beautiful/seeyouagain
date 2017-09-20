/**   
 * 文件名：TSeller.java   
 *    
 * 日期：2014年11月11日15时22分21秒  
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司     
 */
package com.xmniao.xmn.core.businessman.entity;

import com.xmniao.xmn.core.base.BaseEntity;

/**
 * 项目名称：XmnWeb
 * 
 * 类名称：TSeller
 * 
 * 类描述：商家(商户)
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年11月11日15时22分21秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TAllSellerInfo extends BaseEntity {

	private static final long serialVersionUID = -3422913519345379987L;

	private TSeller seller;// 商户基本信息vo
	private TSellerAccount sellerAccount;// 帐号基本信息Vo
	private TSellerDetailed sellerDetailed;// 商家详细信息Vo

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TAllSellerInfo [seller=" + seller + ", sellerAccount="
				+ sellerAccount + ", sellerDetailed=" + sellerDetailed + ", ]";
	}

	/**
	 * 创建一个新的实例 TSeller.
	 * 
	 */
	public TAllSellerInfo() {
		super();
	}

	/**
	 * @return the seller
	 */
	public TSeller getSeller() {
		return seller;
	}

	/**
	 * @param seller
	 *            the seller to set
	 */
	public void setSeller(TSeller seller) {
		this.seller = seller;
	}

	/**
	 * @return the sellerAccount
	 */
	public TSellerAccount getSellerAccount() {
		return sellerAccount;
	}

	/**
	 * @param sellerAccount
	 *            the sellerAccount to set
	 */
	public void setSellerAccount(TSellerAccount sellerAccount) {
		this.sellerAccount = sellerAccount;
	}

	/**
	 * @return the sellerDetailed
	 */
	public TSellerDetailed getSellerDetailed() {
		return sellerDetailed;
	}

	/**
	 * @param sellerDetailed
	 *            the sellerDetailed to set
	 */
	public void setSellerDetailed(TSellerDetailed sellerDetailed) {
		this.sellerDetailed = sellerDetailed;
	}

}
