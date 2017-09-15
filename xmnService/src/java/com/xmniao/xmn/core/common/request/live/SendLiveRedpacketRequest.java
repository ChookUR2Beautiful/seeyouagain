package com.xmniao.xmn.core.common.request.live;

import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：SendLiveRedpacketRequest   
* 类描述：   发送直播间红包请求参数
* 创建人：yezhiyong   
* 创建时间：2016年12月21日 下午4:10:35   
* @version    
*
 */
public class SendLiveRedpacketRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3960264915257809658L;

	/**
	 * '红包类型 0：随机 1：固定'
	 */
	@NotNull(message="红包类型不能为空")
	public Integer amountType;
	
	/**
	 * 红包总鸟蛋数
	 */
	@NotNull(message="红包总鸟蛋数不能为空")
	public Integer totalAmount;
	
	/**
	 * 红包个数
	 */
	@NotNull(message="红包个数不能为空")
	public Integer number;
	
	/**
	 * 在那个直播通告上发的红包
	 */
	@NotNull(message="直播通告不能为空")
	public Integer liveRecordId;
	
	/**
	 * 是初次发红包 还是失败后的再次请求 1初次  0 再次
	 */
	@NotNull(message="类型不能为空")
	public Integer type;
	
	/**
	 * 指定红包uid
	 */
	public Integer privateRedUid;
	
	public Integer getLiveRecordId() {
		return liveRecordId;
	}

	public void setLiveRecordId(Integer liveRecordId) {
		this.liveRecordId = liveRecordId;
	}

	public Integer getAmountType() {
		return amountType;
	}

	public void setAmountType(Integer amountType) {
		this.amountType = amountType;
	}

	public Integer getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Integer totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getPrivateRedUid() {
		return privateRedUid;
	}

	public void setPrivateRedUid(Integer privateRedUid) {
		this.privateRedUid = privateRedUid;
	}


	@Override
	public String toString() {
		return "SendLiveRedpacketRequest [amountType=" + amountType + ", totalAmount=" + totalAmount + ", number="
				+ number + ", liveRecordId=" + liveRecordId + ", type=" + type + ", privateRedUid=" + privateRedUid
				+ ", isPrivateRed="+ "]";
	}		

	
}
