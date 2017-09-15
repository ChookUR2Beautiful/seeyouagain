package com.xmniao.xmn.core.common.request.live;

import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;

/**
 * 
*    
* 项目名称：xmnService_3.1.2_zb   
* 类名称：LiveBuyBirdCoinRequest   
* 类描述：   获取购买鸟币套餐信息请求参数
* 创建人：yhl   
* 创建时间：2016年8月24日 上午9:35:36   
* @version    
*
 */
public class LiveBuyBirdCoinRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7791924145986622771L;

	@NotNull(message="客户端操作系统类型不能为空")
	private Integer systemType;
	
	private Integer uid;
	
	//用于IOS 区分鸟人科技 和 寻蜜鸟  鸟人科技为1 其他为0
	private Integer source=0;  
	
	//是否是储值卡套餐 0 否 1是
	private Integer isCard=0;
	
	//是否是储值卡套餐 0 否 1是
	private Integer cardId=0;
	
	public Integer getSystemType() {
		return systemType;
	}

	public void setSystemType(Integer systemType) {
		this.systemType = systemType;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public Integer getIsCard() {
		return isCard;
	}

	public void setIsCard(Integer isCard) {
		this.isCard = isCard;
	}

	public Integer getCardId() {
		return cardId;
	}

	public void setCardId(Integer cardId) {
		this.cardId = cardId;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}
	
	@Override
	public String toString() {
		return "LiveBuyBirdCoinRequest [systemType=" + systemType + ", uid="
				+ uid + ", source=" + source + ", isCard=" + isCard
				+ ", cardId=" + cardId + "]";
	}

	
}
