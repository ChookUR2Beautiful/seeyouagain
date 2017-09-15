/**
 * 2016年10月24日 下午8:07:28
 */
package com.xmniao.xmn.core.common.request.live;

import java.math.BigDecimal;

import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;

/**
 * @项目名称：xmnService
 * @类名称：PresellOrderRequest
 * @类描述：
 * @创建人： yeyu
 * @创建时间 2016年10月24日 下午8:07:28
 * @version
 */
public class PresellOrderRequest extends BaseRequest {

	/**
	 *long
	 */
	private static final long serialVersionUID = 1L;
//	@NotNull(message="支付类型不能为空")
	private Long pay_type = 0L;
	@NotNull(message="直播记录ID不能为空")
	private Integer recordId;
	@NotNull(message="粉丝券数量不能为空")
	private Integer cidNum;
	//@NotNull(message="抵用券ID不能为空")
	private Integer cdid;
	@NotNull(message="粉丝券ID不能为空")
	private Integer cid;
	@NotNull(message="主播ID不能为空")
	private Integer anchorId;
	//微信公众号openid
	private String openId;
	
	private String returnUrl;
	
	private String uid;
	
	@NotNull(message="鸟币支付类型不能为空")
	private Integer isFansCard;
	@NotNull(message="余额支付类型不能为空")
	private Integer isBalance;
	
	//购买来源：1、通过主播购买，2、通过商户购买，3、通过预告购买',4、其他
	@NotNull(message = "购买来源不能为空")
	private Integer source = 4;
	
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public Long getPay_type() {
		return pay_type;
	}
	public void setPay_type(Long pay_type) {
		this.pay_type = pay_type;
	}
	public Integer getRecordId() {
		return recordId;
	}
	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}
	public Integer getCidNum() {
		return cidNum;
	}
	public void setCidNum(Integer cidNum) {
		this.cidNum = cidNum;
	}
	public Integer getCdid() {
		return cdid;
	}
	public void setCdid(Integer cdid) {
		this.cdid = cdid;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public Integer getAnchorId() {
		return anchorId;
	}
	public void setAnchorId(Integer anchorId) {
		this.anchorId = anchorId;
	}
	public Integer getIsFansCard() {
		return isFansCard;
	}
	public void setIsFansCard(Integer isFansCard) {
		this.isFansCard = isFansCard;
	}
	public Integer getIsBalance() {
		return isBalance;
	}
	public void setIsBalance(Integer isBalance) {
		this.isBalance = isBalance;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getReturnUrl() {
		return returnUrl;
	}
	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}
	
	public Integer getSource() {
		return source;
	}
	public void setSource(Integer source) {
		this.source = source;
	}
	@Override
	public String toString() {
		return "PresellOrderRequest [pay_type=" + pay_type + ", recordId="
				+ recordId + ", cidNum=" + cidNum + ", cdid=" + cdid + ", cid="
				+ cid + ", anchorId=" + anchorId + ", openId=" + openId
				+ ", returnUrl=" + returnUrl + ", uid=" + uid + ", isFansCard="
				+ isFansCard + ", isBalance=" + isBalance + ", source="
				+ source + "]";
	}
	
}
