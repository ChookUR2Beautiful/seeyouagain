package com.xmniao.xmn.core.seller.entity;

import com.xmniao.xmn.core.base.BaseRequest;

import net.sf.oval.constraint.NotNull;
/**
 * (美食体验官新增点评、主播修改点评时查询点评店铺信息)请求类
 * @ClassName:CommentSellerRequest
 * @Description:
 * @Author:xw
 * @Date:2017年5月24日下午5:57:39
 */
public class CommentSellerRequest extends BaseRequest{

	private static final long serialVersionUID = -5474905848356502969L;
	
	@NotNull(message="入口类型不能为空")
	private Integer type;	//1主播，2美食体验馆

	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	private Integer sellerId;
	private Integer sellerType; //商家标识 1 签约商家, 2 非签约商家
	private Integer recordId;
	public Integer getSellerId() {
		return sellerId;
	}
	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}
	public Integer getSellerType() {
		return sellerType;
	}
	public void setSellerType(Integer sellerType) {
		this.sellerType = sellerType;
	}
	public Integer getRecordId() {
		return recordId;
	}
	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}
	@Override
	public String toString() {
		return "CommentSellerRequest [type=" + type + ",sellerId=" + sellerId + ", sellerType=" + sellerType + ", recordId=" + recordId + ",Base:" + super.toString()
				+ "]";
	}
	
}
