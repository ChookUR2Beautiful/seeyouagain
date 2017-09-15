package com.xmniao.xmn.core.common.request.market.pay;

import java.util.ArrayList;
import java.util.List;

import com.xmniao.xmn.core.base.BaseRequest;

import net.sf.oval.constraint.NotNull;

/**
 * 
* @projectName: xmnService 
* @ClassName: SettlementRequest    
* @Description:购物车结算请求类   
* @author: liuzhihao   
* @date: 2016年12月26日 下午2:03:55
 */
@SuppressWarnings("serial")
public class SettlementRequest extends BaseRequest{

	private Integer rid;//用户选择的收货地址
	
	private Integer cdid;//用户选择的优惠卷ID
	
	@NotNull(message="购物车选择购买商品的ID不能为空")
	private String cartids;//购物车选择购买商品的ID
	
	private List<String> carts = null;
	
	@NotNull(message="购买类型不能为空")
	private Integer buyType;//购买类型 0 购物车购买 1直接购买
	
	@NotNull(message="是否使用优惠卷类型不能为空")
	private Integer isUserCoupon=1;//是否使用优惠卷 0 不使用 1使用

	public Integer getRid() {
		return rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}

	public Integer getCdid() {
		return cdid;
	}

	public void setCdid(Integer cdid) {
		this.cdid = cdid;
	}
	
	public String getCartids() {
		return cartids;
	}

	public void setCartids(String cartids) {
		this.cartids = cartids;
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

	public Integer getBuyType() {
		return buyType;
	}

	public void setBuyType(Integer buyType) {
		this.buyType = buyType;
	}

	public Integer getIsUserCoupon() {
		return isUserCoupon;
	}

	public void setIsUserCoupon(Integer isUserCoupon) {
		this.isUserCoupon = isUserCoupon;
	}

	@Override
	public String toString() {
		return "SettlementRequest [rid=" + rid + ", cdid=" + cdid + ", cartids=" + cartids + ", carts=" + carts + ", buyType="
			+ buyType + ", isUserCoupon=" + isUserCoupon + "]";
	}
	
}
