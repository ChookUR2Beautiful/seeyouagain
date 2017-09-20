package com.xmniao.xmn.core.http.entity;


/**
 * 
 * 项目名称：TravelingWeb
 * 
 * 类名称：Member
 * 
 * 类描述： PHP用户数据处理实体
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年10月22日 下午2:11:18
 * 
 * Copyright (c) 深圳市XXX有限公司-版权所有
 */
public class Member extends InterfacRequest{
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 6958384831497693824L;
	private String uid;// 用户ID
	private String uname;// 用户登录名称
	private String nname;// 昵称
	private String password;// 密码，md5加密后的
	private String oldpassword;
	private String email;//邮箱
	private String lastloginip;// 最后一次登录IP
	private String lastlogintime;// 最后一次登录时间
	private String status;// 用户的状态 1正常 2锁定 3注销	4黑名单
	private String phone;// 手机号码	
	private String zphone;// 主帐号手机号码
	private Integer zuid;//主账号用户id
	private Integer usertype;//1普通用户，2香蜜客会员。其他值默认1
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
	private Integer city;// 市
	private String region;// 区
	private String address;// 详细地址
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
	private String attach_time;// 归属于商户或者向蜜客的时间
	private String last_brand;//最后一次登录手机品牌
	private String last_model;//最后一次登录手机型号
	private String ip;// 注册IP	
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
	private String regtype="2";			
	private String sign;// 用户签名
	private String regtime;// 注册时间
	private Integer referrer_zc_id;
	private String regcity;		//添加时封装实体的  注册城市 属性
	private String regarea;		//添加时封装实体的  注册区域  属性
	private String regzoneid;	//添加时封装实体的 注册商圈 属性
	
	private Integer baseusertype; //修改前的用户类型
	public Integer getBaseusertype() {
		return baseusertype;
	}

	public void setBaseusertype(Integer baseusertype) {
		this.baseusertype = baseusertype;
	}

	public Member(){
		this(null,null,null);
	}
	
	public Member(String uid){
		this(uid,null,null);
	}
	
	public Member(String uid,String uname){
		this(uid,uname,null);
	}
	
	public Member(String uid,String uname,String phone){
		this.uid=uid;
		this.uname = uname;
		this.phone = phone;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public Integer getUsertype() {
		return usertype;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public void setUsertype(Integer usertype) {
		this.usertype = usertype;
	}
	public String getRegtype() {
		return regtype;
	}
	public void setRegtype(String regtype) {
		this.regtype = regtype;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getZphone() {
		return zphone;
	}
	public void setZphone(String zphone) {
		this.zphone = zphone;
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

	public String getBrithday() {
		return brithday;
	}
	public void setBrithday(String brithday) {
		this.brithday = brithday;
	}
	
	public String getReferrer_name() {
		return referrer_name;
	}
	public void setReferrer_name(String referrer_name) {
		this.referrer_name = referrer_name;
	}
	
	
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public Integer getIdtype() {
		return idtype;
	}
	public void setIdtype(Integer idtype) {
		this.idtype = idtype;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public Integer getReferrer_member_id() {
		return referrer_member_id;
	}
	public void setReferrer_member_id(Integer referrer_member_id) {
		this.referrer_member_id = referrer_member_id;
	}
	public Integer getReferrer_zc_id() {
		return referrer_zc_id;
	}
	public void setReferrer_zc_id(Integer referrer_zc_id) {
		this.referrer_zc_id = referrer_zc_id;
	}
	public Integer getProvince() {
		return province;
	}
	public void setProvince(Integer province) {
		this.province = province;
	}
	public Integer getCity() {
		return city;
	}
	public void setCity(Integer city) {
		this.city = city;
	}
	public String getRegcity() {
		return regcity;
	}

	public void setRegcity(String regcity) {
		this.regcity = regcity;
	}

	public String getRegarea() {
		return regarea;
	}

	public void setRegarea(String regarea) {
		this.regarea = regarea;
	}

	public String getRegzoneid() {
		return regzoneid;
	}

	public void setRegzoneid(String regzoneid) {
		this.regzoneid = regzoneid;
	}

	@Override
	public String toString() {
		return "Member [uid=" + uid + ", uname=" + uname + ", nname=" + nname
				+ ", password=" + password + ", oldpassword=" + oldpassword
				+ ", email=" + email + ", lastloginip=" + lastloginip
				+ ", lastlogintime=" + lastlogintime + ", status=" + status
				+ ", phone=" + phone + ", zphone=" + zphone + ", zuid=" + zuid
				+ ", usertype=" + usertype + ", mobilephone=" + mobilephone
				+ ", model=" + model + ", resolution=" + resolution + ", lang="
				+ lang + ", brand=" + brand + ", networktype=" + networktype
				+ ", name=" + name + ", avatar=" + avatar + ", idtype="
				+ idtype + ", idcard=" + idcard + ", qq=" + qq + ", sex=" + sex
				+ ", brithday=" + brithday + ", referrer_member_id="
				+ referrer_member_id + ", referrer_name=" + referrer_name
				+ ", province=" + province + ", city=" + city + ", region="
				+ region + ", address=" + address + ", zc_time=" + zc_time
				+ ", ios_token=" + ios_token + ", mike_type=" + mike_type
				+ ", mike_time=" + mike_time + ", mike_start=" + mike_start
				+ ", mike_end=" + mike_end + ", genussellerid=" + genussellerid
				+ ", genusname=" + genusname + ", jointid=" + jointid
				+ ", corporate=" + corporate + ", attach_time=" + attach_time
				+ ", last_brand=" + last_brand + ", last_model=" + last_model
				+ ", ip=" + ip + ", regtype=" + regtype + ", sign=" + sign
				+ ", regtime=" + regtime + ", referrer_zc_id=" + referrer_zc_id
				+ ", regcity=" + regcity + ", regarea=" + regarea
				+ ", regzoneid=" + regzoneid + "]";
	}

	public String getOldpassword() {
		return oldpassword;
	}

	public void setOldpassword(String oldpassword) {
		this.oldpassword = oldpassword;
	}

	public Integer getZuid() {
		return zuid;
	}

	public void setZuid(Integer zuid) {
		this.zuid = zuid;
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

	public String getAttach_time() {
		return attach_time;
	}

	public void setAttach_time(String attach_time) {
		this.attach_time = attach_time;
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
