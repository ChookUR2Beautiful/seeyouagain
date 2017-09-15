package com.xmniao.xmn.core.common.request.seller;

import com.xmniao.xmn.core.base.BaseRequest;

import net.sf.oval.constraint.Min;
import net.sf.oval.constraint.NotNull;
/**
 * 
* @projectName: xmnService 
* @ClassName: 领取成功/充值成功请求    
* @Description:与用户有关的商铺请求类  
* @author: wdh   
* 
 */
public class DebitCardSucessRquest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6361706286577859183L;
	@NotNull(message="充值套餐的id")
	private Integer recharegeId;
	@NotNull(message="type不能为空")
	private Integer type; //0充值成功，1领卡成功
	@NotNull(message="商家id不能为空")
	private Integer entry_sellerid;//商户的id
	@NotNull(message="商家类型不能为空")
	private Integer sellerType;

	
	public Integer getEntry_sellerid() {
		return entry_sellerid;
	}
	public void setEntry_sellerid(Integer entry_sellerid) {
		this.entry_sellerid = entry_sellerid;
	}
	public Integer getRecharegeId() {
		return recharegeId;
	}
	public void setRecharegeId(Integer recharegeId) {
		this.recharegeId = recharegeId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getSellerType() {
		return sellerType;
	}
	public void setSellerType(Integer sellerType) {
		this.sellerType = sellerType;
	}
	
	
}
