package com.xmniao.xmn.core.http.entity;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.annotation.JSONField;
import com.xmniao.xmn.core.util.DateUtil;
import com.xmniao.xmn.core.util.StringUtils;
import com.xmniao.xmn.core.util.DateHelper;


/**
 * 
 * 项目名称：TravelingWeb
 * 
 * 类名称：PUserRequest
 * 
 * 类描述： PHP用户查询接口请求
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年10月22日 下午2:11:18
 * 
 * Copyright (c) 深圳市XXX有限公司-版权所有
 */
public class PUserRequestSelect extends InterfacRequest {

	private static final long serialVersionUID = -5683658226908348467L;



	private String uid;// 用户ID
	private String uname;// 用户登录名
	private String nname;// 用户昵称
	private String password;// 密码，md5加密后的
	private String email;// 邮件
	private String sign;// 用户签名
	private String regtime;// 注册时间
	private String regip;//注册ip
	private String lastloginip;// 最后一次登录IP
	private String lastlogintime;// 最后一次登录时间	
	private String phone;// 注册手机号码
	private String status;// 用户状态；1正常 2锁定 3注销 4黑名单
	private String zuid;// 主账号用户id
	// 注册来源：
	// 1 旅游众筹网站
	// 2 寻觅鸟网站
	// 3 400客服电话
	// 4 旅游众筹安卓客户端
	// 5 旅游众筹IOS客户端
	// 6 寻觅鸟安卓客户端
	// 7 寻觅鸟IOS客户端
	// 8 商家安卓客户端
	// 9 商家IOS客户端
	private String regtype;
	private String zphone;// 主账号手机
	private String mobilephone;// 电话号码
	private String model;// 手机型号
	private String resolution;// 手机分辨率
	private String lang;// 手机语言
	private String brand;// 手机品牌
	private String networktype;// 联网类型
	private String name;// 真实姓名
	private String avatar;// 头像
	private Integer idtype;// 证件类型
	private String idcard;// 证件号码
	private String qq;// QQ号码
	private Integer sex;// 性别, 0未知，1男，2女
	private String brithday;// 生日
	private Integer referrer_member_id;// 推荐人ID
	private String referrer_name;// 推荐人姓名
	private Integer province;// 省
	private String city;// 市
	private String region;// 区
	private String zc_time;//众筹第一次消费时间
	private String ios_token;//ios的软件token
	private Integer mike_type;//向蜜客类型 1 用户向蜜客(使用所属商家ID和所属商家名称) 2商家向蜜客
	private String mike_time;//成为香密客时间
	private String mike_start;//向蜜客有效的起始日期
	private String mike_end;//向蜜客的截止日期
	private Integer genussellerid;//所属商家ID
	private String genusname;//所属商家名称
	private Integer jointid;//所属商家所属合作商ID
	private String corporate;//所属商家所属合作商名称
	private String last_brand;//最后一次登录手机品牌
	private String last_model;//最后一次登录手机型号
	private String usertype;//最后一次登录手机型号
	
	/*
	 * 拓展查询字段 ： 注册日期区间段查询
	 * staregtime  开始时间
	 * endregtime  结束时间
	 */
	private Date staregtime;
	private Date endregtime;
	
	private String month; 
	
	
	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}
	

	public Map<String, String> getParam(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("page", page);
		map.put("pageSize", pageSize);
		if(StringUtils.hasLength(uid)){
			map.put("uid", uid);
		}
		if(StringUtils.hasLength(genusname)){
			map.put("genusname", genusname);
		}
		if(StringUtils.hasLength(uname)){
			map.put("uname", uname);
		}
		if(StringUtils.hasLength(nname)){
			map.put("nname", nname);
		}
		if(StringUtils.hasLength(email)){
			map.put("email", email);
		}
		if(StringUtils.hasLength(phone)){
			map.put("phone", phone);
		}
		if(StringUtils.hasLength(status)){
			map.put("status", status);
		}
		if(StringUtils.hasLength(regtype)){
			map.put("regtype", regtype);
		}
		if(StringUtils.hasLength(zphone)){
			map.put("zphone", zphone);
		}
		if(StringUtils.hasLength(zuid)){
			map.put("zuid", zuid);
		}
		if (StringUtils.hasLength(city)) {
			map.put("regcity", city);
		}
		if (StringUtils.hasLength(region)) {
			map.put("regarea", region);
		}
		if (StringUtils.hasLength(usertype)) {
			map.put("usertype", usertype);
		}
		if(staregtime!=null){
			String date =null;
			try{
				date = DateUtil.formatDate(staregtime,DateUtil.Y_M_D);
			}catch(Exception e){}
			if(StringUtils.hasLength(date)){
				map.put("staregtime", date);
			}
		}
		if(endregtime!=null){
			String date =null;
			try{
				date = DateUtil.formatDate(endregtime,DateUtil.Y_M_D);
			}catch(Exception e){}
			if(StringUtils.hasLength(date)){
				map.put("endregtime", date);
			}	
		}
		if(StringUtils.hasLength(month) && staregtime==null && endregtime==null){
			if(DateHelper.getMonth().equals(month)){
				String[] dates=DateHelper.getMonth(  Calendar.DATE);
				map.put("staregtime", dates[0]);
				map.put("endregtime", dates[1]);
			}else{
				String s = String.valueOf(DateHelper.getMonthMinDays(month));
				map.put("staregtime", (month+"-"+s));
				String e = String.valueOf(DateHelper.getMonthDays(month));
				map.put("endregtime",( month+"-"+e));
			}
		}
		return map;
	}
	
	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRegtype() {
		return regtype;
	}

	public void setRegtype(String regtype) {
		this.regtype = regtype;
	}

	public String getZphone() {
		return zphone;
	}

	public void setZphone(String zphone) {
		this.zphone = zphone;
	}

	public String getZuid() {
		return zuid;
	}

	public void setZuid(String zuid) {
		this.zuid = zuid;
	}
	
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getStaregtime() {
		return staregtime;
	}

	public void setStaregtime(Date staregtime) {
		this.staregtime = staregtime;
	}
	
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getEndregtime() {
		return endregtime;
	}

	public void setEndregtime(Date endregtime) {
		this.endregtime = endregtime;
	}

	
	
	@Override
	public String toString() {
		return "PUserRequestSelect [uid=" + uid + ", uname=" + uname
				+ ", nname=" + nname + ", email=" + email + ", phone=" + phone
				+ ", status=" + status + ", regtype=" + regtype + ", zphone="
				+ zphone + ", zuid=" + zuid + "]";
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getMobilephone() {
		return mobilephone;
	}

	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getNetworktype() {
		return networktype;
	}

	public void setNetworktype(String networktype) {
		this.networktype = networktype;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Integer getIdtype() {
		return idtype;
	}

	public void setIdtype(Integer idtype) {
		this.idtype = idtype;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getBrithday() {
		return brithday;
	}

	public void setBrithday(String brithday) {
		this.brithday = brithday;
	}

	public Integer getReferrer_member_id() {
		return referrer_member_id;
	}

	public void setReferrer_member_id(Integer referrer_member_id) {
		this.referrer_member_id = referrer_member_id;
	}

	public String getReferrer_name() {
		return referrer_name;
	}

	public void setReferrer_name(String referrer_name) {
		this.referrer_name = referrer_name;
	}

	public Integer getProvince() {
		return province;
	}

	public void setProvince(Integer province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getZc_time() {
		return zc_time;
	}

	public void setZc_time(String zc_time) {
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

	public String getMike_time() {
		return mike_time;
	}

	public void setMike_time(String mike_time) {
		this.mike_time = mike_time;
	}

	public String getMike_start() {
		return mike_start;
	}

	public void setMike_start(String mike_start) {
		this.mike_start = mike_start;
	}

	public String getMike_end() {
		return mike_end;
	}

	public void setMike_end(String mike_end) {
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

	public String getLast_brand() {
		return last_brand;
	}

	public void setLast_brand(String last_brand) {
		this.last_brand = last_brand;
	}

	public String getLast_model() {
		return last_model;
	}

	public void setLast_model(String last_model) {
		this.last_model = last_model;
	}

	

}
