/**   
 * 文件名：TJoint.java   
 *    
 * 日期：2014年11月19日11时21分24秒  
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司     
 */
package com.xmniao.xmn.core.business_cooperation.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.xmniao.xmn.core.base.BaseEntity;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

/**
 * 项目名称：XmnWeb
 * 
 * 类名称：TJoint
 * 
 * 类描述：合作商
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年11月19日11时21分24秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TJoint extends BaseEntity {

	private static final long serialVersionUID = -5209227250147084884L;
	private Integer jointid;// 合作商ID
	private String corporate;// 公司名称
	private String legalperson;// 公司法人
	private String idnumber;// 法人身份证号
	private String idnumberurl;// 身份证电子版URL
	private String license;// 执照编号
	private String licenseurl;// 执照电子版URL
	private String city;// 市编号
	private String area;// 合作区域编号格式|10001;123;1545
	private String address;// 公司地址
	private Integer status;// 默认|0：未启动 1：已启动
	private Integer industry;// 所属行业
	private Date sdate;// 加入日期
	private Date edate;// 启动日期
	private Integer startnum;// 已启动商圈数
	private Double percentage;// 比例格式:百分比
	private String logo;// 合作商LOGO
	private String remarks;// 备注
	
	private String phoneid;// 合作商联系手机
	private String applicant;// 经办人
	private String number;// 档案编号
	
	private Date sdateStart;// 启动日期（搜索条件）
	private Date sdateEnd;// 启动日期（搜索条件）
	private String cityTitle;// 城市名称
	private String areaTitle;// 区域名称
	private Integer areaNum;// 总商圈数
	private Integer sellerNum;// 总商户圈数
	private Integer agreeNum;// 已签约商户圈数
	private Integer noSignNum;// 未签约商户
	private Integer willonlineNum;// 待上线商户数
	private Integer noPassNum;// 审核未通过商户数
	private Integer onlineNum;// 正式上线商户数
	private Integer activeSellerNum;// 活跃商户数
	private Integer sleepSellerNum;// 沉睡商户数
	
	
	private String  loginAccount;// 登录账号
	private String province;// 省编号
	private Integer staffid;// 管理员id
	
	//管理员信息
	private String account;//管理员帐号
	@RequestLogging(record=false)
	private String password;//管理员密码
	private String fullname;//姓名
	private String nickname;//昵称
	private String headurl;//头像
	private String imei;// 绑定手机IMEI
	private Integer sex;// 1=男|2=女
	
	
	private Integer saasnum;	//签约SAAS总数据
	private Integer stocknum;	//签约库存
	private BigDecimal saasamount;//签约 总额
	private Double saasagio;	//签约折扣
	
	/**
	 * 经纬度信息，add by lifeng
	 */
	private Integer lid;// 坐标ID
	private Double longitude;// 经度
	private Double latitude;// 纬度
	
	
	//获取状态文字说明
	public String getStatusText(){
		if(status == null) return null;
		if(status == 0) return "未启动";
		if(status == 1) return "已启动";
		return null;
	}
	
	
	public Integer getActiveSellerNum() {
		return activeSellerNum;
	}


	public void setActiveSellerNum(Integer activeSellerNum) {
		this.activeSellerNum = activeSellerNum;
	}


	public Integer getSleepSellerNum() {
		return sleepSellerNum;
	}


	public void setSleepSellerNum(Integer sleepSellerNum) {
		this.sleepSellerNum = sleepSellerNum;
	}


	public Integer getNoSignNum() {
		return noSignNum;
	}


	public void setNoSignNum(Integer noSignNum) {
		this.noSignNum = noSignNum;
	}


	public Integer getWillonlineNum() {
		return willonlineNum;
	}


	public void setWillonlineNum(Integer willonlineNum) {
		this.willonlineNum = willonlineNum;
	}


	public Integer getNoPassNum() {
		return noPassNum;
	}


	public void setNoPassNum(Integer noPassNum) {
		this.noPassNum = noPassNum;
	}


	public Integer getOnlineNum() {
		return onlineNum;
	}


	public void setOnlineNum(Integer onlineNum) {
		this.onlineNum = onlineNum;
	}


	/**
	 * 创建一个新的实例 TJoint.
	 * 
	 */
	public TJoint() {
		super();
	}
	
	/**
	 * @return the account
	 */
	public String getAccount() {
		return account;
	}

	/**
	 * @param account the account to set
	 */
	public void setAccount(String account) {
		this.account = account;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the fullname
	 */
	public String getFullname() {
		return fullname;
	}

	/**
	 * @param fullname the fullname to set
	 */
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	/**
	 * @return the nickname
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * @param nickname the nickname to set
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * @return the imei
	 */
	public String getImei() {
		return imei;
	}

	/**
	 * @param imei the imei to set
	 */
	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getAreaTitle() {
		return areaTitle;
	}

	public void setAreaTitle(String areaTitle) {
		this.areaTitle = areaTitle;
	}

	public TJoint(Integer jointid) {
		this.jointid = jointid;
	}

	public String getPercentageText() {
		if(percentage != null){
			return ((int)(percentage*100)) + "%";
		}
		return null;
	}

	public void setPercentageText(String percentageText) {
		int index = percentageText.indexOf("%");
		if(index > 0){
			Double d = Double.parseDouble(percentageText.substring(0, index))/100;
			setPercentage(d);
		}
	}

	/**
	 * @return the staffid
	 */
	public Integer getStaffid() {
		return staffid;
	}

	/**
	 * @param staffid the staffid to set
	 */
	public void setStaffid(Integer staffid) {
		this.staffid = staffid;
	}

	/**
	 * jointid
	 * 
	 * @return the jointid
	 */
	public Integer getJointid() {
		return jointid;
	}

	/**
	 * @param jointid
	 *            the jointid to set
	 */
	public void setJointid(Integer jointid) {
		this.jointid = jointid;
	}

	/**
	 * corporate
	 * 
	 * @return the corporate
	 */
	public String getCorporate() {
		return corporate;
	}

	/**
	 * @param corporate
	 *            the corporate to set
	 */
	public void setCorporate(String corporate) {
		this.corporate = corporate;
	}

	/**
	 * legalperson
	 * 
	 * @return the legalperson
	 */
	public String getLegalperson() {
		return legalperson;
	}

	/**
	 * @param legalperson
	 *            the legalperson to set
	 */
	public void setLegalperson(String legalperson) {
		this.legalperson = legalperson;
	}

	/**
	 * idnumber
	 * 
	 * @return the idnumber
	 */
	public String getIdnumber() {
		return idnumber;
	}

	/**
	 * @param idnumber
	 *            the idnumber to set
	 */
	public void setIdnumber(String idnumber) {
		this.idnumber = idnumber;
	}

	/**
	 * idnumberurl
	 * 
	 * @return the idnumberurl
	 */
	public String getIdnumberurl() {
		return idnumberurl;
	}

	/**
	 * @param idnumberurl
	 *            the idnumberurl to set
	 */
	public void setIdnumberurl(String idnumberurl) {
		this.idnumberurl = idnumberurl;
	}

	/**
	 * license
	 * 
	 * @return the license
	 */
	public String getLicense() {
		return license;
	}

	/**
	 * @param license
	 *            the license to set
	 */
	public void setLicense(String license) {
		this.license = license;
	}

	/**
	 * licenseurl
	 * 
	 * @return the licenseurl
	 */
	public String getLicenseurl() {
		return licenseurl;
	}

	/**
	 * @param licenseurl
	 *            the licenseurl to set
	 */
	public void setLicenseurl(String licenseurl) {
		this.licenseurl = licenseurl;
	}

	/**
	 * city
	 * 
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * area
	 * 
	 * @return the area
	 */
	public String getArea() {
		return area;
	}

	/**
	 * @param area
	 *            the area to set
	 */
	public void setArea(String area) {
		this.area = area;
	}

	/**
	 * address
	 * 
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * status
	 * 
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * industry
	 * 
	 * @return the industry
	 */
	public Integer getIndustry() {
		return industry;
	}

	/**
	 * @param industry
	 *            the industry to set
	 */
	public void setIndustry(Integer industry) {
		this.industry = industry;
	}

	/**
	 * sdate
	 * 
	 * @return the sdate
	 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getSdate() {
		return sdate;
	}

	/**
	 * @param sdate
	 *            the sdate to set
	 */
	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}

	/**
	 * edate
	 * 
	 * @return the edate
	 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getEdate() {
		return edate;
	}

	/**
	 * @param edate
	 *            the edate to set
	 */
	public void setEdate(Date edate) {
		this.edate = edate;
	}

	/**
	 * startnum
	 * 
	 * @return the startnum
	 */
	public Integer getStartnum() {
		return startnum;
	}

	/**
	 * @param startnum
	 *            the startnum to set
	 */
	public void setStartnum(Integer startnum) {
		this.startnum = startnum;
	}

	public Double getPercentage() {
		return percentage;
	}

	public void setPercentage(Double percentage) {
		this.percentage = percentage;
	}

	/**
	 * logo
	 * 
	 * @return the logo
	 */
	public String getLogo() {
		return logo;
	}

	/**
	 * @param logo
	 *            the logo to set
	 */
	public void setLogo(String logo) {
		this.logo = logo;
	}

	/**
	 * remarks
	 * 
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * @param remarks
	 *            the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	

	public Date getSdateStart() {
		return sdateStart;
	}

	public void setSdateStart(Date sdateStart) {
		this.sdateStart = sdateStart;
	}

	public Date getSdateEnd() {
		return sdateEnd;
	}

	public void setSdateEnd(Date sdateEnd) {
		this.sdateEnd = sdateEnd;
	}

	public String getCityTitle() {
		return cityTitle;
	}

	public void setCityTitle(String cityTitle) {
		this.cityTitle = cityTitle;
	}
	
	public Integer getAreaNum() {
		return areaNum;
	}

	public void setAreaNum(Integer areaNum) {
		this.areaNum = areaNum;
	}

	public Integer getSellerNum() {
		return sellerNum;
	}

	public void setSellerNum(Integer sellerNum) {
		this.sellerNum = sellerNum;
	}

	public Integer getAgreeNum() {
		return agreeNum;
	}

	public void setAgreeNum(Integer agreeNum) {
		this.agreeNum = agreeNum;
	}

	public String getPhoneid() {
		return phoneid;
	}

	public void setPhoneid(String phoneid) {
		this.phoneid = phoneid;
	}

	public String getApplicant() {
		return applicant;
	}

	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getLoginAccount() {
		return loginAccount;
	}

	public void setLoginAccount(String loginAccount) {
		this.loginAccount = loginAccount;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getSaasnum() {
		return saasnum;
	}


	public void setSaasnum(Integer saasnum) {
		this.saasnum = saasnum;
	}


	public Integer getStocknum() {
		return stocknum;
	}


	public void setStocknum(Integer stocknum) {
		this.stocknum = stocknum;
	}


	public BigDecimal getSaasamount() {
		return saasamount;
	}


	public void setSaasamount(BigDecimal saasamount) {
		this.saasamount = saasamount;
	}


	public Double getSaasagio() {
		return saasagio;
	}


	public void setSaasagio(Double saasagio) {
		this.saasagio = saasagio;
	}

	public Integer getLid() {
		return lid;
	}


	public void setLid(Integer lid) {
		this.lid = lid;
	}


	public Double getLongitude() {
		return longitude;
	}


	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}


	public Double getLatitude() {
		return latitude;
	}


	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	

	/**
	 * @return the headurl
	 */
	public String getHeadurl() {
		return headurl;
	}


	/**
	 * @param headurl the headurl to set
	 */
	public void setHeadurl(String headurl) {
		this.headurl = headurl;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TJoint [jointid=" + jointid + ", corporate=" + corporate
				+ ", legalperson=" + legalperson + ", idnumber=" + idnumber
				+ ", idnumberurl=" + idnumberurl + ", license=" + license
				+ ", licenseurl=" + licenseurl + ", city=" + city + ", area="
				+ area + ", address=" + address + ", status=" + status
				+ ", industry=" + industry + ", sdate=" + sdate + ", edate="
				+ edate + ", startnum=" + startnum + ", percentage="
				+ percentage + ", logo=" + logo + ", remarks=" + remarks
				+ ", phoneid=" + phoneid + ", applicant=" + applicant
				+ ", number=" + number + ", sdateStart=" + sdateStart
				+ ", sdateEnd=" + sdateEnd + ", cityTitle=" + cityTitle
				+ ", areaTitle=" + areaTitle + ", areaNum=" + areaNum
				+ ", sellerNum=" + sellerNum + ", agreeNum=" + agreeNum
				+ ", noSignNum=" + noSignNum + ", willonlineNum="
				+ willonlineNum + ", noPassNum=" + noPassNum + ", onlineNum="
				+ onlineNum + ", activeSellerNum=" + activeSellerNum
				+ ", sleepSellerNum=" + sleepSellerNum + ", loginAccount="
				+ loginAccount + ", province=" + province + ", staffid="
				+ staffid + ", account=" + account + ", password=" + password
				+ ", fullname=" + fullname + ", nickname=" + nickname
				+ ", headurl=" + headurl + ", imei=" + imei + ", sex=" + sex
				+ ", saasnum=" + saasnum + ", stocknum=" + stocknum
				+ ", saasamount=" + saasamount + ", saasagio=" + saasagio
				+ ", lid=" + lid + ", longitude=" + longitude + ", latitude="
				+ latitude + "]";
	}
	
}
