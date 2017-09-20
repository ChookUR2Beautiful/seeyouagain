package com.xmniao.xmn.core.businessman.entity;

import java.io.Serializable;
import java.util.Date;

public class PWaiterRegisterResponseAdd implements Serializable {

	private static final long serialVersionUID = -7372669552798182623L;
	
	private Integer uid;// 用户ID
	private String uname;// 用户登录名称
	private String nname;// 昵称
	private String password;// 密码，md5加密后的
	private String email;// 邮箱
	private String sign;// 用户签名
	private String regtime;// 注册时间
	private String regip;// 注册IP
	private Integer regcity;//注册时所在城市	
	private Integer regarea;//注册时所在区域
	private Integer regzoneid;//注册时所在商圈
	private String lastloginip;// 最后一次登录IP
	private String lastlogintime;// 最后一次登录时间
	private Integer status;// 用户的状态 1正常 2锁定 3注销
	private String phone;// 手机号码
	private String zphone;// 主帐号手机号码
	private Integer zuid;// 主帐号用户ID
	// 注册类型
	// 1 旅游众筹网站
	// 2 寻觅鸟网站
	// 3 400客服电话
	// 4 旅游众筹安卓客户端
	// 5 旅游众筹IOS客户端
	// 6 寻觅鸟安卓客户端
	// 7 寻觅鸟IOS客户端
	// 8 商家安卓客户端
	// 9 商家IOS客户端
	private Integer regtype;
	private Integer usertype;//用户类型
	private Integer phone_type;// 1android,2ios	
	private String app_version;// app版本	
	private Integer app_series;// app序列号	
	private String imei;// 手机imei号		
	private Date attach_time;// 归属于商户或者向蜜客的时间	
	private Date zc_time; //众筹第一次消费时间
	private String ios_token; //ios的软件token
	private Integer mike_type;//向蜜客类型 1 用户向蜜客(使用所属商家ID和所属商家名称) 2商家向蜜客
	private Date mike_time;//成为香密客时间
	private Date mike_start;//向蜜客有效的起始日期
	private Date mike_end;//向蜜客的截止日期
	private Integer genussellerid;//所属商家ID
	private String genusname;//所属商家
	private Integer jointid; 	//所属商家所属合作商ID
	private String corporate; //所属商家所属合作商名称	
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getNname() {
		return nname;
	}
	public void setNname(String nname) {
		this.nname = nname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getRegtime() {
		return regtime;
	}
	public void setRegtime(String regtime) {
		this.regtime = regtime;
	}
	public String getRegip() {
		return regip;
	}
	public void setRegip(String regip) {
		this.regip = regip;
	}
	public Integer getRegcity() {
		return regcity;
	}
	public void setRegcity(Integer regcity) {
		this.regcity = regcity;
	}
	public Integer getRegarea() {
		return regarea;
	}
	public void setRegarea(Integer regarea) {
		this.regarea = regarea;
	}
	public Integer getRegzoneid() {
		return regzoneid;
	}
	public void setRegzoneid(Integer regzoneid) {
		this.regzoneid = regzoneid;
	}
	public String getLastloginip() {
		return lastloginip;
	}
	public void setLastloginip(String lastloginip) {
		this.lastloginip = lastloginip;
	}
	public String getLastlogintime() {
		return lastlogintime;
	}
	public void setLastlogintime(String lastlogintime) {
		this.lastlogintime = lastlogintime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getZphone() {
		return zphone;
	}
	public void setZphone(String zphone) {
		this.zphone = zphone;
	}
	public Integer getZuid() {
		return zuid;
	}
	public void setZuid(Integer zuid) {
		this.zuid = zuid;
	}
	public Integer getRegtype() {
		return regtype;
	}
	public void setRegtype(Integer regtype) {
		this.regtype = regtype;
	}
	public Integer getUsertype() {
		return usertype;
	}
	public void setUsertype(Integer usertype) {
		this.usertype = usertype;
	}
	public Integer getPhone_type() {
		return phone_type;
	}
	public void setPhone_type(Integer phone_type) {
		this.phone_type = phone_type;
	}
	public String getApp_version() {
		return app_version;
	}
	public void setApp_version(String app_version) {
		this.app_version = app_version;
	}
	public Integer getApp_series() {
		return app_series;
	}
	public void setApp_series(Integer app_series) {
		this.app_series = app_series;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public Date getAttach_time() {
		return attach_time;
	}
	public void setAttach_time(Date attach_time) {
		this.attach_time = attach_time;
	}
	public Date getZc_time() {
		return zc_time;
	}
	public void setZc_time(Date zc_time) {
		this.zc_time = zc_time;
	}
	public String getIos_token() {
		return ios_token;
	}
	public void setIos_token(String ios_token) {
		this.ios_token = ios_token;
	}
	public Integer getMike_type() {
		return mike_type;
	}
	public void setMike_type(Integer mike_type) {
		this.mike_type = mike_type;
	}
	public Date getMike_time() {
		return mike_time;
	}
	public void setMike_time(Date mike_time) {
		this.mike_time = mike_time;
	}
	public Date getMike_start() {
		return mike_start;
	}
	public void setMike_start(Date mike_start) {
		this.mike_start = mike_start;
	}
	public Date getMike_end() {
		return mike_end;
	}
	public void setMike_end(Date mike_end) {
		this.mike_end = mike_end;
	}
	public Integer getGenussellerid() {
		return genussellerid;
	}
	public void setGenussellerid(Integer genussellerid) {
		this.genussellerid = genussellerid;
	}
	public String getGenusname() {
		return genusname;
	}
	public void setGenusname(String genusname) {
		this.genusname = genusname;
	}
	public Integer getJointid() {
		return jointid;
	}
	public void setJointid(Integer jointid) {
		this.jointid = jointid;
	}
	public String getCorporate() {
		return corporate;
	}
	public void setCorporate(String corporate) {
		this.corporate = corporate;
	}
	
}
