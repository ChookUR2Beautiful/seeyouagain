package com.xmniao.xmn.core.common.request.market.pay;

import java.util.ArrayList;
import java.util.List;

import com.xmniao.xmn.core.base.BaseRequest;

import net.sf.oval.constraint.NotNull;

/**
 * 
* @projectName: xmnService 
* @ClassName: CouponListRequest    
* @Description:积分超市优惠卷列表请求类   
* @author: liuzhihao   
* @date: 2017年1月12日 下午12:28:31
 */
@SuppressWarnings("serial")
public class CouponListRequest extends BaseRequest{

	@NotNull(message="购物车选择购买商品的ID不能为空")
	private String cartids;//购物车选择购买商品的ID
	
	@NotNull(message="购买类型不能为空")
	private Integer buyType;//购买类型 0 购物车购买 1 直接购买
	
	private List<String> carts = new ArrayList<String>();

	public String getCartids() {
		return cartids;
	}

	public void setCartids(String cartids) {
		this.cartids = cartids;
	}

	public Integer getBuyType() {
		return buyType;
	}

	public void setBuyType(Integer buyType) {
		this.buyType = buyType;
	}

	public List<String> getCarts() {
		if(cartids != null){
			List<String> l = new ArrayList<String>();
			for(String s : cartids.split(",")){
				l.add(s);
			}
			return l;
		}
		return carts;
	}

	public void setCarts(List<String> carts) {
		this.carts = carts;
	}

	@Override
	public String toString() {
		return "CouponListRequest [cartids=" + cartids + ", buyType=" + buyType + ", carts=" + carts + "]";
	}

}
