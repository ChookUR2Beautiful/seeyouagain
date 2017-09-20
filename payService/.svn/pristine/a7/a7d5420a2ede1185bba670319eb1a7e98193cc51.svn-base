package com.xmniao.entity;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xmniao.common.MD5;

public class SubsidyMsg {
	
	/** 用户ID或商家ID或合作商ID */
	private String uId ;
	/** 用户类型 1用户 2商家3合作商  */
	private String userType  ;
	/** 用户账号或商家账号或合作商账号  */
	private String phoneNumber = "";
	/** 佣金余额 */
	private String commision ;
	/** 赠送余额  */
	private String zbalance ;
	/** 积分余额  */
	private String integral;
	/** 商户或合作商营业收入余额 */
	private String sellerAmount;
	/** 订单ID */
	private String orderId;
	/** 描述 */
	private String remark;
	/** 发放类型  */
	private String rType;
	/** 签名  */
	private String sign;
	
	public boolean checkSign(){
		
		
		StringBuffer sb = new StringBuffer();
		sb.append(this.uId==null?"":uId);
		sb.append(this.userType==null?"":userType);
		sb.append(this.orderId==null?"":orderId);
		sb.append(this.rType==null?"":rType);
		sb.append(this.remark==null?"":remark);
		sb.append(this.commision==null?"":commision);
		sb.append(this.integral==null?"":integral);
		sb.append(this.sellerAmount==null?"":sellerAmount);
		sb.append(this.zbalance==null?"":zbalance);
        
		return MD5.Encode(sb.toString(), "UTF-8").equals(this.sign);
		
	}
	
	public String validate(){
		
		String msg = "";
		if(uId==null||uId.equals("")){
			msg = "校验失败，uId为空！";
		    return msg;
		}
		if(userType==null||userType.equals("")){
			msg = "校验失败，userType为空！";
			return msg;
		}
		if(orderId==null||orderId.equals("")){
			msg = "校验失败，orderId为空！";
			return msg;
		}
		if(rType==null||rType.equals("")){
			msg = "校验失败，rType为空！";
			return msg;
		}
		if(sign==null||sign.equals("")){
			msg = "校验失败，sign为空！";
			return msg;
		}
		
		if((commision==null||commision.equals(""))
		    &&(integral==null||integral.equals(""))
		    &&(sellerAmount==null||sellerAmount.equals(""))
			&&(zbalance==null||zbalance.equals(""))){
			msg = "校验失败，金额为空！";
			return msg;
		}
		
		return msg; 
	}
	
	

	
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getUId() {
		return uId;
	}
	public void setUId(String uId) {
		this.uId = uId;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getCommision() {
		return commision;
	}
	public void setCommision(String commision) {
		this.commision = commision;
	}
	public String getZbalance() {
		return zbalance;
	}
	public void setZbalance(String zbalance) {
		this.zbalance = zbalance;
	}
	public String getIntegral() {
		return integral;
	}
	public void setIntegral(String integral) {
		this.integral = integral;
	}
	public String getSellerAmount() {
		return sellerAmount;
	}
	public void setSellerAmount(String sellerAmount) {
		this.sellerAmount = sellerAmount;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getRType() {
		return rType;
	}
	public void setRType(String rType) {
		this.rType = rType;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return "SubsidyMsg [uId=" + uId + ", userType=" + userType
				+ ", phoneNumber=" + phoneNumber + ", commision=" + commision
				+ ", zbalance=" + zbalance + ", integral=" + integral
				+ ", sellerAmount=" + sellerAmount + ", orderId=" + orderId
				+ ", remark=" + remark + ", rType=" + rType + ", sign=" + sign
				+ "]";
	}

	


}
