package com.xmniao.xmn.core.seller.entity;

import com.xmniao.xmn.core.base.BaseRequest;

import net.sf.oval.constraint.NotNull;

/**
 * 
 * @ClassName:SellerCommentRequest
 * @Description: 店铺评论请求类
 * @Author:xw
 * @Date:2017年5月19日下午2:45:57
 */
public class SellerCommentRequest extends BaseRequest{

	private static final long serialVersionUID = 601619211549074776L;

	@NotNull(message="店铺id不能为空")
	private Integer sellerid;
	
	@NotNull(message="店铺类型不能为空")
	private Integer sellerType;
	
	public Integer getSellerid() {
		return sellerid;
	}

	public void setSellerid(Integer sellerid) {
		this.sellerid = sellerid;
	}

	public Integer getSellerType() {
		return sellerType;
	}

	public void setSellerType(Integer sellerType) {
		this.sellerType = sellerType;
	}

	@Override
	public String toString() {
		return "SellerCommentRequest [sellerid=" + sellerid + ", sellerType=" + sellerType + ",Base:" + super.toString() + "]";
	}


	
	

}
