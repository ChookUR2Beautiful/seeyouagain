package com.xmniao.xmn.core.http.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 * 项目名称：TravelingWeb
 * 
 * 类名称：PUser
 * 
 * 类描述： PHP用户查询接口返回实体
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年10月22日 下午2:04:45
 * 
 * Copyright (c) 深圳市XXX有限公司-版权所有
 */
public class PUserResponseSelect implements Serializable {

	private static final long serialVersionUID = -861964737779492291L;

	private String uid;// 用户ID
	private Integer usertype;//用户类型
	private String uname;// 用户登录名称
	private String nname;// 昵称
	private String password;// 密码，md5加密后的
	private String email;// 邮箱
	private String sign;// 用户签名
	private String regtime;// 注册时间
	private String regip;// 注册IP
	private String lastloginip;// 最后一次登录IP
	private String lastlogintime;// 最后一次登录时间
	private String status;// 用户的状态 1正常 2锁定 3注销
	private String phone;// 手机号码
	private String zphone;// 主帐号手机号码

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
	private String regtype;
	private String mobilephone;// 电话号码
	private String model;// 手机型号
	private String resolution;// 手机分辨率
	private String lang;// 手机语言
	private String brand;// 手机品牌
	private String networktype;// 联网类型
	private String name;// 真实姓名
	private String avatar;// 头像
	private String idtype;// 证件类型
	private String idcard;// 证件号码
	private String qq;// QQ号码
	private String sex;// 性别, 0未知，1男，2女
	private String brithday;// 生日
	private String referrer_member_id;// 推荐人ID
	private String referrer_name;// 推荐人姓名
	private String province;// 省
	private String city;// 市
	private String region;// 区
	private String address;// 详细地址
	
	private String zc_time; //众筹第一次消费时间
	private String ios_token; //ios的软件token
	private String mike_type;//向蜜客类型 1 用户向蜜客(使用所属商家ID和所属商家名称) 2商家向蜜客
	private String mike_time;//成为香密客时间
	private String mike_start;//向蜜客有效的起始日期
	private String mike_end;//向蜜客的截止日期
	private Integer genussellerid;//所属商家ID
	private String genusname;//所属商家
	private Integer jointid; 	//所属商家所属合作商ID
	private String corporate; //所属商家所属合作商名称
	private String last_brand;//最后一次登录手机品牌
	private String last_model;//最后一次登录手机型号
	private String regcity;//注册时所在城市
	private String regarea;//注册时所在区域
	private String regzoneid;//注册时所在商圈
	
	//用户主播信息
	private Integer rankNo;//用户等级数
	private Integer concernNums;//用户关注数量
	private Integer giveGiftsNums; //送出礼物总数
	
	//组合注册城市和区域,结果例如==>深圳-南山区
	public String getReg_city_area() {
		return regcity + "-" + regarea;
	}

	/*
	 * 增加3个辅助字段来显示regcity  regarea  regzoneid
	 * 新增的需求需要这3个属性的id值,而之前将id值已经做了处理,使得在页面显示时为区域的名称
	 * 现在增加的字段是取接口传过来的数据
	 */
	private String regcityId;		//保存从PHP端接口传过来的regcity的值
	private String regareaId;		//保存从PHP端接口传过来的regarea的值
	private String regzoneTempId;	//保存从PHP端接口传过来的regzoneid的值	
    /*
     *查询钱包字段 
     */
	private Integer walletStatus ;//钱包状态(接收sql操作结果)
	private String wallStatus ;//钱包状态(显示)
	
    private BigDecimal amount;

    private BigDecimal balance;

    private BigDecimal commision;

    private BigDecimal zbalance;

    private Double integral;
    
    
    
	public Integer getWalletStatus() {
		return walletStatus;
	}
	public void setWalletStatus(Integer walletStatus) {
		this.walletStatus = walletStatus;
	}
	
	
	public String getWallStatus() {
        if(null == walletStatus) return "";
        if(1 == walletStatus) return "正常";
        if(2 == walletStatus) return "锁定";
        if(3 == walletStatus) return "注销";
		return wallStatus;
	}
	public void setWallStatus(String wallStatus) {
		this.wallStatus = wallStatus;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public BigDecimal getCommision() {
		return commision;
	}
	public void setCommision(BigDecimal commision) {
		this.commision = commision;
	}
	public BigDecimal getZbalance() {
		return zbalance;
	}
	public void setZbalance(BigDecimal zbalance) {
		this.zbalance = zbalance;
	}
	public void setIntegral(Double integral) {
		this.integral = integral;
	}
	
	public Double getIntegral() {
		return integral;
	}
	/**
	 * 导出数据时，处理下拉框问题
	 * @return
	 */
	//处理注册类型，使显示为文字描述
	public String getRegtypeText(){
		if(regtype == null)
		{
			return null;
		}
		if(regtype.equals("1")){
			return "旅游众筹网站";
		}
		if(regtype.equals("2")){
			return "寻觅鸟网站";
		} 
		if(regtype.equals("3")){
			return "400客服电话";
		} 
		if(regtype.equals("4")){
			return "旅游众筹安卓客户端";
		}
		if(regtype.equals("5")){
			return "旅游众筹IOS客户端";
		} 
		if(regtype.equals("6")){
			return " 寻觅鸟安卓客户端";
		} 
		if(regtype.equals("7")){
			return "寻觅鸟IOS客户端";
		} 
		if(regtype.equals("8")){
			return "商家安卓客户端";
		} 
		if(regtype.equals("9")){
			return "商家IOS客户端";
		}
		return null;
	}
	//性别 sex
	public String getSexText(){
		if(sex == null)
		{
			return null;
		}
		if(sex.equals("0")){
			return "未知";
		}
		if(sex.equals("1")){
			return "男";
		}
		if(sex.equals("2")){
			return "女";
		}
		return null;
	}
	//状态status 
	public String getStatusText(){
		if(status == null)
		{
			return null;
		}
		if(status.equals("1")){
			return "正常";
		}
		if(status.equals("2")){
			return "锁定";
		}
		if(status.equals("3")){
			return "注销";
		}
		return null;
	}
	//用户类型 usertype 1.普通用户  2.寻蜜客
	public String getUsertypeText(){
		if(usertype == null)
		{
			return null;
		}
		if(usertype == 1){
			return "普通用户";
		}
		if(usertype == 2){
			return "寻蜜客";
		}
		return null;
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

	public Integer getUsertype() {
		return usertype;
	}

	public void setUsertype(Integer usertype) {
		this.usertype = usertype;
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

	public PUserResponseSelect() {
		this(null,null);
	}

	public PUserResponseSelect(String uid, String password) {
		this.uid = uid;
		this.password = password;
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

	public String getRegtype() {
		return regtype;
	}

	public void setRegtype(String regtype) {
		this.regtype = regtype;
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

	public String getIdtype() {
		return idtype;
	}

	public void setIdtype(String idtype) {
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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBrithday() {
		return brithday;
	}

	public void setBrithday(String brithday) {
		this.brithday = brithday;
	}

	public String getReferrer_member_id() {
		return referrer_member_id;
	}

	public void setReferrer_member_id(String referrer_member_id) {
		this.referrer_member_id = referrer_member_id;
	}

	public String getReferrer_name() {
		return referrer_name;
	}

	public void setReferrer_name(String referrer_name) {
		this.referrer_name = referrer_name;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGenusname() {
		return genusname;
	}

	public void setGenusname(String genusname) {
		this.genusname = genusname;
	}

	public Integer getUserType() {
		return usertype;
	}

	public void setUserType(Integer usertype) {
		this.usertype = usertype;
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

	public String getMike_type() {
		return mike_type;
	}

	public void setMike_type(String mike_type) {
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

	public String getRegcityId() {
		return regcityId;
	}

	public void setRegcityId(String regcityId) {
		this.regcityId = regcityId;
	}

	public String getRegareaId() {
		return regareaId;
	}

	public void setRegareaId(String regareaId) {
		this.regareaId = regareaId;
	}

	public String getRegzoneTempId() {
		return regzoneTempId;
	}

	public void setRegzoneTempId(String regzoneTempId) {
		this.regzoneTempId = regzoneTempId;
	}

	
	/**
	 * @return the rankNo
	 */
	public Integer getRankNo() {
		return rankNo;
	}
	/**
	 * @param rankNo the rankNo to set
	 */
	public void setRankNo(Integer rankNo) {
		this.rankNo = rankNo;
	}
	/**
	 * @return the concernNums
	 */
	public Integer getConcernNums() {
		return concernNums;
	}
	/**
	 * @param concernNums the concernNums to set
	 */
	public void setConcernNums(Integer concernNums) {
		this.concernNums = concernNums;
	}
	/**
	 * @return the giveGiftsNums
	 */
	public Integer getGiveGiftsNums() {
		return giveGiftsNums;
	}
	/**
	 * @param giveGiftsNums the giveGiftsNums to set
	 */
	public void setGiveGiftsNums(Integer giveGiftsNums) {
		this.giveGiftsNums = giveGiftsNums;
	}
	@Override
	public String toString() {
		return "PUserResponseSelect [uid=" + uid + ", usertype=" + usertype
				+ ", uname=" + uname + ", nname=" + nname + ", password="
				+ password + ", email=" + email + ", sign=" + sign
				+ ", regtime=" + regtime + ", regip=" + regip
				+ ", lastloginip=" + lastloginip + ", lastlogintime="
				+ lastlogintime + ", status=" + status + ", phone=" + phone
				+ ", zphone=" + zphone + ", regtype=" + regtype
				+ ", mobilephone=" + mobilephone + ", model=" + model
				+ ", resolution=" + resolution + ", lang=" + lang + ", brand="
				+ brand + ", networktype=" + networktype + ", name=" + name
				+ ", avatar=" + avatar + ", idtype=" + idtype + ", idcard="
				+ idcard + ", qq=" + qq + ", sex=" + sex + ", brithday="
				+ brithday + ", referrer_member_id=" + referrer_member_id
				+ ", referrer_name=" + referrer_name + ", province=" + province
				+ ", city=" + city + ", region=" + region + ", address="
				+ address + ", zc_time=" + zc_time + ", ios_token=" + ios_token
				+ ", mike_type=" + mike_type + ", mike_time=" + mike_time
				+ ", mike_start=" + mike_start + ", mike_end=" + mike_end
				+ ", genussellerid=" + genussellerid + ", genusname="
				+ genusname + ", jointid=" + jointid + ", corporate="
				+ corporate + "]";
	}

	

}
