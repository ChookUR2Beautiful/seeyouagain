package com.xmniao.xmn.core.coupon.response;

import java.io.Serializable;

/**
 * 
* @projectName: xmnService 
* @ClassName: UseCardRecordResponse    
* @Description:用户卡记录返回类   
* @author: liuzhihao   
* @date: 2017年2月28日 上午11:46:40
 */
@SuppressWarnings("serial")
public class UseCardRecordResponse implements Serializable{

	private String sellerName;//店铺名称
	
	private String consumDate;//消费时间
	
	private String consumCoin;//消费金额

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public String getConsumDate() {
		return consumDate;
	}

	public void setConsumDate(String consumDate) {
		this.consumDate = consumDate;
	}

	public String getConsumCoin() {
		return consumCoin;
	}

	public void setConsumCoin(String consumCoin) {
		this.consumCoin = consumCoin;
	}

	@Override
	public String toString() {
		return "UseCardRecordResponse [sellerName=" + sellerName + ", consumDate=" + consumDate + ", consumCoin=" + consumCoin
			+ "]";
	}
	
	
}
