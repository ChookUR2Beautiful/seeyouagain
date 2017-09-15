package com.xmniao.xmn.core.common.request.seller;

import com.xmniao.xmn.core.base.BaseRequest;

import net.sf.oval.constraint.Min;
import net.sf.oval.constraint.NotNull;
/**
 * 
* @projectName: xmnService 
* @ClassName: GetCardSellerRquest    
* @Description:领取专享卡操作类  
* @author: wdh   
* 
 */
public class GetCardSellerRquest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6361706286577859183L;
	@NotNull(message="充值商家不能为空")
	private Integer entry_sellerid; //领取专项卡对应的商家Id
	@NotNull(message="兑换专享卡的套餐id不能为空")
	private Integer  rechargeId; //兑换的套餐id
	@NotNull(message="兑换专id不能为空")
	private Integer debitId; //储值卡id
	public Integer getEntry_sellerid() {
		return entry_sellerid;
	}
	public void setEntry_sellerid(Integer entry_sellerid) {
		this.entry_sellerid = entry_sellerid;
	}
	public Integer getRechargeId() {
		return rechargeId;
	}
	public void setRechargeId(Integer rechargeId) {
		this.rechargeId = rechargeId;
	}
	public Integer getDebitId() {
		return debitId;
	}
	public void setDebitId(Integer debitId) {
		this.debitId = debitId;
	}
	
	
}
