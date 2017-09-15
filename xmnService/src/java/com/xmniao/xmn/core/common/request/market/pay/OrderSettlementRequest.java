package com.xmniao.xmn.core.common.request.market.pay;

import java.util.ArrayList;
import java.util.List;

import com.xmniao.xmn.core.base.BaseRequest;

import net.sf.oval.constraint.NotNull;

/**
 * 
* @ClassName: OrderSettlementRequest 
* @Description: 订单结算请求类 
* @author: liuzhihao 
* @date 2017年4月18日 下午5:57:27 
*
 */
@SuppressWarnings("serial")
public class OrderSettlementRequest extends BaseRequest{
	
	private Integer rid;//用户选择的收货地址

	private String cdid;//用户选择的优惠卷ID
	
	private String cartids;//购物车选择购买商品的ID
	
	private Integer num;//商品数量
	
	private Integer codeId ;//商品唯一标识
	
	private Integer activityId;//活动id
	
	private String attrIds;//商品规格ID组合  
    
    private String attrVals;//商品规格描述
	
	private Integer isUserCoupon=1;//是否使用优惠卷 0 不使用 1使用
	
	@NotNull(message="购买类型不能为空")
	private Integer buyType;//购买类型 0 购物车购买 1直接购买
	
	private List<String> carts=null;

	private List<Integer> cdids=null; 
	
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
		
		if(cdid != null){
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

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Integer getCodeId() {
		return codeId;
	}

	public void setCodeId(Integer codeId) {
		this.codeId = codeId;
	}

	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	public String getAttrIds() {
		return attrIds;
	}

	public void setAttrIds(String attrIds) {
		this.attrIds = attrIds;
	}

	public String getAttrVals() {
		return attrVals;
	}

	public void setAttrVals(String attrVals) {
		this.attrVals = attrVals;
	}

	public Integer getIsUserCoupon() {
		return isUserCoupon;
	}

	public void setIsUserCoupon(Integer isUserCoupon) {
		this.isUserCoupon = isUserCoupon;
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
		return "OrderSettlementRequest [rid=" + rid + ", cdids=" + cdids + ", cartids=" + cartids + ", num=" + num
				+ ", codeId=" + codeId + ", activityId=" + activityId + ", attrIds=" + attrIds + ", attrVals="
				+ attrVals + ", isUserCoupon=" + isUserCoupon + ", buyType=" + buyType + ", carts=" + carts + "]";
	}

	
}
