package com.xmniao.xmn.core.xmnburs.entity;

import java.util.Date;

import com.xmniao.xmn.core.base.BaseEntity;

/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：Burs
 * 
 * 类描述： 寻蜜鸟用户实体类
 * 
 * 创建人： Chen Bo
 * 
 * 创建时间：2016年3月23日 下午5:07:02 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class Burs extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = -6539235770298863299L;

	private Integer uid;

    private String uname;//登录名

    private String nname;//昵称

    private String password;

    private String email;

    private String sign;

    private Date regtime;

    private String regip;

    private Integer regcity;

    private Integer regarea;

    private Integer regzoneid;

    private String lastloginip;

    private Date lastlogintime;

    private String ukeys;

    private String pkeys;

    private String pttype;

    private String uin;

    private Integer status;

    private String zphone;

    private String zuid;

    private Integer regtype;

    private String channelId;

    private String phone;

    private String phoneArea;

    private Integer usertype;

    private Byte mikeType;

    private Date mikeTime;

    private Date mikeStart;

    private Date mikeEnd;

    private Date zcTime;

    private String iosToken;

    private Integer genussellerid;

    private String genusname;

    private Integer jointid;

    private String corporate;

    private Date attachTime;

    private Integer isVirtual;

    private Boolean phoneType;

    private String appVersion;

    private Integer appSeries;

    private String imei;

    private Byte marks;

    private String unionid;
    
    private String levelName;
    
    private String avatar;
    
    private Integer anchorId;
    
    public Integer getAnchorId() {
		return anchorId;
	}

	public void setAnchorId(Integer anchorId) {
		this.anchorId = anchorId;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

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
        this.uname = uname == null ? null : uname.trim();
    }

    public String getNname() {
        return nname;
    }

    public void setNname(String nname) {
        this.nname = nname == null ? null : nname.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign == null ? null : sign.trim();
    }

    public Date getRegtime() {
        return regtime;
    }

    public void setRegtime(Date regtime) {
        this.regtime = regtime;
    }

    public String getRegip() {
        return regip;
    }

    public void setRegip(String regip) {
        this.regip = regip == null ? null : regip.trim();
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
        this.lastloginip = lastloginip == null ? null : lastloginip.trim();
    }

    public Date getLastlogintime() {
        return lastlogintime;
    }

    public void setLastlogintime(Date lastlogintime) {
        this.lastlogintime = lastlogintime;
    }

    public String getUkeys() {
        return ukeys;
    }

    public void setUkeys(String ukeys) {
        this.ukeys = ukeys == null ? null : ukeys.trim();
    }

    public String getPkeys() {
        return pkeys;
    }

    public void setPkeys(String pkeys) {
        this.pkeys = pkeys == null ? null : pkeys.trim();
    }

    public String getPttype() {
        return pttype;
    }

    public void setPttype(String pttype) {
        this.pttype = pttype == null ? null : pttype.trim();
    }

    public String getUin() {
        return uin;
    }

    public void setUin(String uin) {
        this.uin = uin == null ? null : uin.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getZphone() {
        return zphone;
    }

    public void setZphone(String zphone) {
        this.zphone = zphone == null ? null : zphone.trim();
    }

    public String getZuid() {
        return zuid;
    }

    public void setZuid(String zuid) {
        this.zuid = zuid == null ? null : zuid.trim();
    }

    public Integer getRegtype() {
        return regtype;
    }

    public void setRegtype(Integer regtype) {
        this.regtype = regtype;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId == null ? null : channelId.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getPhoneArea() {
        return phoneArea;
    }

    public void setPhoneArea(String phoneArea) {
        this.phoneArea = phoneArea == null ? null : phoneArea.trim();
    }

    public Integer getUsertype() {
        return usertype;
    }

    public void setUsertype(Integer usertype) {
        this.usertype = usertype;
    }

    public Byte getMikeType() {
        return mikeType;
    }

    public void setMikeType(Byte mikeType) {
        this.mikeType = mikeType;
    }

    public Date getMikeTime() {
        return mikeTime;
    }

    public void setMikeTime(Date mikeTime) {
        this.mikeTime = mikeTime;
    }

    public Date getMikeStart() {
        return mikeStart;
    }

    public void setMikeStart(Date mikeStart) {
        this.mikeStart = mikeStart;
    }

    public Date getMikeEnd() {
        return mikeEnd;
    }

    public void setMikeEnd(Date mikeEnd) {
        this.mikeEnd = mikeEnd;
    }

    public Date getZcTime() {
        return zcTime;
    }

    public void setZcTime(Date zcTime) {
        this.zcTime = zcTime;
    }

    public String getIosToken() {
        return iosToken;
    }

    public void setIosToken(String iosToken) {
        this.iosToken = iosToken == null ? null : iosToken.trim();
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
        this.genusname = genusname == null ? null : genusname.trim();
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
        this.corporate = corporate == null ? null : corporate.trim();
    }

    public Date getAttachTime() {
        return attachTime;
    }

    public void setAttachTime(Date attachTime) {
        this.attachTime = attachTime;
    }

    public Integer getIsVirtual() {
        return isVirtual;
    }

    public void setIsVirtual(Integer isVirtual) {
        this.isVirtual = isVirtual;
    }

    public Boolean getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(Boolean phoneType) {
        this.phoneType = phoneType;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion == null ? null : appVersion.trim();
    }

    public Integer getAppSeries() {
        return appSeries;
    }

    public void setAppSeries(Integer appSeries) {
        this.appSeries = appSeries;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei == null ? null : imei.trim();
    }

    public Byte getMarks() {
        return marks;
    }

    public void setMarks(Byte marks) {
        this.marks = marks;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid == null ? null : unionid.trim();
    }

	@Override
	public String toString() {
		return "Burs [uid=" + uid + ", uname=" + uname + ", nname=" + nname
				+ ", password=" + password + ", email=" + email + ", sign="
				+ sign + ", regtime=" + regtime + ", regip=" + regip
				+ ", regcity=" + regcity + ", regarea=" + regarea
				+ ", regzoneid=" + regzoneid + ", lastloginip=" + lastloginip
				+ ", lastlogintime=" + lastlogintime + ", ukeys=" + ukeys
				+ ", pkeys=" + pkeys + ", pttype=" + pttype + ", uin=" + uin
				+ ", status=" + status + ", zphone=" + zphone + ", zuid="
				+ zuid + ", regtype=" + regtype + ", channelId=" + channelId
				+ ", phone=" + phone + ", phoneArea=" + phoneArea
				+ ", usertype=" + usertype + ", mikeType=" + mikeType
				+ ", mikeTime=" + mikeTime + ", mikeStart=" + mikeStart
				+ ", mikeEnd=" + mikeEnd + ", zcTime=" + zcTime + ", iosToken="
				+ iosToken + ", genussellerid=" + genussellerid
				+ ", genusname=" + genusname + ", jointid=" + jointid
				+ ", corporate=" + corporate + ", attachTime=" + attachTime
				+ ", isVirtual=" + isVirtual + ", phoneType=" + phoneType
				+ ", appVersion=" + appVersion + ", appSeries=" + appSeries
				+ ", imei=" + imei + ", marks=" + marks + ", unionid="
				+ unionid + "]";
	}
    
}