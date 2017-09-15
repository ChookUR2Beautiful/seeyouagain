package com.xmniao.xmn.core.common.request;

import net.sf.oval.constraint.NotNull;

public class SignPayConfirmRequest {

	@NotNull(message="押金套餐ID不能为空")
	private Integer id;//Saas押金套餐ID
	
	//邀请人的id
	@NotNull(message="推荐人不能为空")
	private Integer parentid = 0;
	
	@NotNull(message="sessionToken不能为空")
	private String sessiontoken;//缓存
	
	
	public Integer getParentid() {
		return parentid;
	}
	
	private String otherTel;//代付手机号码
	
	private Integer otherPay;
	
	private String openid;//微信openid
	


	public String getOtherTel() {
		return otherTel;
	}

	public void setOtherTel(String otherTel) {
		this.otherTel = otherTel;
	}

	public Integer getOtherPay() {
		return otherPay;
	}

	public void setOtherPay(Integer otherPay) {
		this.otherPay = otherPay;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSessiontoken() {
		return sessiontoken;
	}

	public void setSessiontoken(String sessiontoken) {
		this.sessiontoken = sessiontoken;
	}

	@Override
	public String toString() {
		return "SignPayConfirmRequest [id=" + id 
				+ ", parentid=" + parentid + ", sessiontoken=" + sessiontoken
				+ "]";
	}
	

}
