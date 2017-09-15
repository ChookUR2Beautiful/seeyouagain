package com.xmniao.xmn.core.sellerPackage.entity;

/**
 * 
* @projectName: xmnService 
* @ClassName: ComboTrade    
* @Description:套餐分类   
* @author: liuzhihao   
* @date: 2017年2月20日 下午3:33:01
 */
public class ComboTrade {

	private Integer tradeId;//分类ID
	
	private String tradeName;//分类名称

	public Integer getTradeId() {
		return tradeId;
	}

	public void setTradeId(Integer tradeId) {
		this.tradeId = tradeId;
	}

	public String getTradeName() {
		return tradeName;
	}

	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}

	@Override
	public String toString() {
		return "ComboTrade [tradeId=" + tradeId + ", tradeName=" + tradeName + "]";
	}
	
}
