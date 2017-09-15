package com.xmniao.xmn.core.match.request;

import com.xmniao.xmn.core.base.BaseRequest;

import net.sf.oval.constraint.NotNull;

public class StartMatchSignRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4209172269004669643L;
	
	@NotNull(message="姓名不能为空")
	private String nname ; //昵称
	@NotNull(message="手机号码不能为空")
	private String phone;//手机号码
	@NotNull(message="省份不能为空")
	private String provinceId; //比赛省份
	@NotNull(message="城市不能为空")
	private String cityId;
	@NotNull(message="区域不能为空")
	private String areaId;
	@NotNull(message="图片不能为空")
	private String fileUrls; //图片链接
	private String msgCode;//短信验证码
	@NotNull(message="是否需要短信验证码不能为空")
	private String isNeedMsgCode; //0不需要，1需要
	
	public String getNname() {
		return nname;
	}
	public void setNname(String nname) {
		this.nname = nname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public String getFileUrls() {
		return fileUrls;
	}
	public void setFileUrls(String fileUrls) {
		this.fileUrls = fileUrls;
	}
	public String getMsgCode() {
		return msgCode;
	}
	public void setMsgCode(String msgCode) {
		this.msgCode = msgCode;
	}
	public String getIsNeedMsgCode() {
		return isNeedMsgCode;
	}
	public void setIsNeedMsgCode(String isNeedMsgCode) {
		this.isNeedMsgCode = isNeedMsgCode;
	}	
	
}
