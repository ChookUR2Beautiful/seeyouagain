package com.xmniao.xmn.core.common.request.market.pay;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.xmniao.xmn.core.base.BaseRequest;

import net.sf.oval.constraint.NotNull;

@SuppressWarnings("serial")
public class GenerateOrderRequest extends BaseRequest{

	@NotNull(message="用户收货地址ID不能为空")
	private Integer rid;//用户收货地址ID
	
	private Integer cid;//优惠卷ID
	
	private String cdid;//用户优惠卷ID
	
	private List<Integer> cdids=null;
	
	@NotNull(message="购物车选择购买商品的ID不能为空")
	private String cartids;//购物车选择购买商品的ID
	
	private List<String> carts = new ArrayList<String>();
	
	@NotNull(message="购买类型不能为空")
	private Integer buyType;//购买类型 0 购物车购买 1 直接购买
	
	@NotNull(message="是否使用优惠卷类型不能为空")
	private Integer isUserCoupon=1;//是否使用优惠卷 0 不使用 1使用

	public Integer getRid() {
		return rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}

	public String getCdid() {
		return cdid;
	}

	public void setCdid(String cdid) {
		this.cdid = cdid;
	}

	public List<Integer> getCdids() {
		if(StringUtils.isNotEmpty(cdid)){
			List<Integer> l = new ArrayList<Integer>();
			for(String s : cdid.split(",")){
				l.add(Integer.parseInt(s));
			}
			return l;
		}
		return cdids;
	}

	public void setCdids(List<Integer> cdids) {
		this.cdids = cdids;
	}

	public String getCartids() {
		return cartids;
	}

	public void setCartids(String cartids) {
		this.cartids = cartids;
	}

	public List<String> getCarts() {
		if(StringUtils.isNotEmpty(cartids)){
			List<String> l = new ArrayList<String>();
			for(String s : cartids.split(",")){
				l.add(s.trim());
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
	
	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	@Override
	public String toString() {
		return "GenerateOrderRequest [rid=" + rid + ", cid=" + cid + ", cdid=" + cdid + ", cdids=" + cdids
				+ ", cartids=" + cartids + ", carts=" + carts + ", buyType=" + buyType + ", isUserCoupon="
				+ isUserCoupon + "]";
	}
	
}
