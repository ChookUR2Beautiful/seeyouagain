package com.xmniao.xmn.core.common.request.market.pay;

import com.xmniao.xmn.core.base.BaseRequest;

import net.sf.oval.constraint.NotNull;

@SuppressWarnings("serial")
public class DirectOrderRequest extends BaseRequest{
	    
	@NotNull(message="数量不能为空！")
	private Integer num;
	
	@NotNull(message="商品唯一标识不能为空")
	private Integer codeId ;
	
	@NotNull(message="商品规格ID组合 不能为空")
	private String attrIds;//商品规格ID组合  
    
	@NotNull(message="商品规格描述不能为空")
    private String attrVals;//商品规格描述
	
	@NotNull(message="是否使用优惠卷类型不能为空")
	private Integer isUserCoupon=1;//是否使用优惠卷 0 不使用 1使用
	
	//活动id
	private Integer activityId;
	
	private Integer rid;//用户收货地址ID
	
	private Integer cdid;//用户优惠卷ID

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
	
	public Integer getIsUserCoupon() {
		return isUserCoupon;
	}

	public void setIsUserCoupon(Integer isUserCoupon) {
		this.isUserCoupon = isUserCoupon;
	}

	@Override
	public String toString() {
		return "DirectOrderRequest [num=" + num + ", codeId=" + codeId + ", attrIds=" + attrIds + ", attrVals=" + attrVals
			+ ", isUserCoupon=" + isUserCoupon + ", activityId=" + activityId + ", rid=" + rid + ", cdid=" + cdid + "]";
	}
	
}
