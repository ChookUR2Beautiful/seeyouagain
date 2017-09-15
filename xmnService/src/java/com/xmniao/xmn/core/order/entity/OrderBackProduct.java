package com.xmniao.xmn.core.order.entity;

import java.io.Serializable;

/**
 * 订单取消   回退商品库存实体对象
 * */
public class OrderBackProduct implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8190397971492124567L;

	//商品codeId
	private int codeId;
	
	//订单下 购买商品数量
	private int wareNum;

	public int getCodeId() {
		return codeId;
	}

	public void setCodeId(int codeId) {
		this.codeId = codeId;
	}

	public int getWareNum() {
		return wareNum;
	}

	public void setWareNum(int wareNum) {
		this.wareNum = wareNum;
	}
	
	
	
	
	
}
