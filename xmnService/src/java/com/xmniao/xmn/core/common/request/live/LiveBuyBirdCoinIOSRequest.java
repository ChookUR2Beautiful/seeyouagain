package com.xmniao.xmn.core.common.request.live;

import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;

/**
* 项目名称：xmnService_3.1.2_zb   
* 类名称：LiveBuyBirdCoinIOSRequest   
* 类描述：   获取购买鸟币套餐信息 IOS内购请求
* 创建人：yhl   
* 创建时间：2016年8月24日 上午9:35:36   
* @version    
 */
public class LiveBuyBirdCoinIOSRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7791924145986622771L;

	@NotNull(message="IOS内购参数不能为空")
	private String receiptStr;
	
	@NotNull(message="验证内购票据订单号")
	private String orderNo;

	public String getReceiptStr() {
		return receiptStr;
	}

	public void setReceiptStr(String receiptStr) {
		this.receiptStr = receiptStr;
	}
	
	public String getOrderNo() {
		return orderNo;
	}
	
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	@Override
	public String toString() {
		return "LiveBuyBirdCoinIOSRequest [receiptStr=" + receiptStr
				+ ", orderNo=" + orderNo + "]";
	}


}
