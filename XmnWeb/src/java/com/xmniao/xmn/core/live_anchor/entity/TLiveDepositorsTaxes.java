package com.xmniao.xmn.core.live_anchor.entity;

import java.math.BigDecimal;
import com.xmniao.xmn.core.base.BaseEntity;

public class TLiveDepositorsTaxes extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4818598839214423640L;

	private String type;  //收益类型 0：综合收益，1:V客推荐， 2:V客红包，3:壕赚VIP红包，4:壕赚商户充值红包 5:V客创业管理奖金 6:寻蜜客签约收益 7：寻蜜客分账收益
	
	private String typeName;
	
	private String rateType;   //费率类型 1:比例  2:固定金额
	
	private BigDecimal rate;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getRateType() {
		return rateType;
	}

	public void setRateType(String rateType) {
		this.rateType = rateType;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	@Override
	public String toString() {
		return "TLiveDepositorsTaxes [type=" + type + ", typeName=" + typeName
				+ ", rateType=" + rateType + ", rate=" + rate + "]";
	}

    
}