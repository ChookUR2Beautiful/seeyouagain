package com.xmniao.xmn.core.common.request.order;

import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;

/**
 * 体验卡购买请求参数
 * */
public class ExperienceCardRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4672240411649496212L;

//	@NotNull(message = "美食体验卡ID不能为空") 
	private Integer exprId;
	
	private Integer uid;
	
	//1:标示进入下单页面获取信息  ,  2:标示创建订单发起支付操作
	@NotNull(message = "操作类型不能为空") 
	private Integer operation = 1;
	
	//根据支付方式填写   下单时必填
	private Integer payType;
	
	//鸟币支付类型不能为空 
	private Integer isFansCard = 0;
	
	//余额支付类型不能为空
	private Integer isBalance = 0;
	
	private String orderNo;
	
	//查询订单的来源 0 默认订单列表  1 支付完成
	private Integer orderSource = 0;
	
	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getExprId() {
		return exprId;
	}

	public void setExprId(Integer exprId) {
		this.exprId = exprId;
	}

	public Integer getOperation() {
		return operation;
	}

	public void setOperation(Integer operation) {
		this.operation = operation;
	}

	public Integer getIsBalance() {
		return isBalance;
	}

	public void setIsBalance(Integer isBalance) {
		this.isBalance = isBalance;
	}

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public Integer getIsFansCard() {
		return isFansCard;
	}

	public void setIsFansCard(Integer isFansCard) {
		this.isFansCard = isFansCard;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getOrderSource() {
		return orderSource;
	}

	public void setOrderSource(Integer orderSource) {
		this.orderSource = orderSource;
	}

	@Override
	public String toString() {
		return "ExperienceCardRequest [exprId=" + exprId + ", uid=" + uid
				+ ", operation=" + operation + ", payType=" + payType
				+ ", isFansCard=" + isFansCard + ", isBalance=" + isBalance
				+ ", orderNo=" + orderNo + ", orderSource=" + orderSource + "]";
	}

}
