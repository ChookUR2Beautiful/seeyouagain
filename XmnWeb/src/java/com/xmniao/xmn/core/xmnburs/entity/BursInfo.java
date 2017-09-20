package com.xmniao.xmn.core.xmnburs.entity;

import java.util.Date;
import java.util.List;

import com.xmniao.xmn.core.base.BaseEntity;
import com.xmniao.xmn.core.live_anchor.entity.BLiveAnchorImage;

/**
 * 
 * 项目名称：XmnWeb_LVB
 * 
 * 类名称：BursInfo
 *
 * 类描述：在此处添加类描述
 * 
 * 创建人： huang'tao
 * 
 * 创建时间：2016-8-15上午11:11:39
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class BursInfo extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = -4851213418421451492L;

	private Integer uid;

    private String phone;

    private String uname;

    private String mobilephone;

    private String model;

    private String resolution;

    private String lang;

    private String brand;

    private String lastBrand;

    private String lastModel;

    private String networktype;

    private String name;

    private String avatar;

    private String qq;

    private Integer sex;

    private String birthday;

    private Integer idtype;

    private String idcard;

    private String referrerMemberId;

    private String referrerName;

    private Integer referrerZcId;

    private String province;

    private String city;

    private String region;

    private String address;

    private Date zcTime;

    private String interest;
    
	public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname == null ? null : uname.trim();
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone == null ? null : mobilephone.trim();
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model == null ? null : model.trim();
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution == null ? null : resolution.trim();
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang == null ? null : lang.trim();
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand == null ? null : brand.trim();
    }

    public String getLastBrand() {
        return lastBrand;
    }

    public void setLastBrand(String lastBrand) {
        this.lastBrand = lastBrand == null ? null : lastBrand.trim();
    }

    public String getLastModel() {
        return lastModel;
    }

    public void setLastModel(String lastModel) {
        this.lastModel = lastModel == null ? null : lastModel.trim();
    }

    public String getNetworktype() {
        return networktype;
    }

    public void setNetworktype(String networktype) {
        this.networktype = networktype == null ? null : networktype.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday == null ? null : birthday.trim();
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
        this.idcard = idcard == null ? null : idcard.trim();
    }

    public String getReferrerMemberId() {
        return referrerMemberId;
    }

    public void setReferrerMemberId(String referrerMemberId) {
        this.referrerMemberId = referrerMemberId == null ? null : referrerMemberId.trim();
    }

    public String getReferrerName() {
        return referrerName;
    }

    public void setReferrerName(String referrerName) {
        this.referrerName = referrerName == null ? null : referrerName.trim();
    }

    public Integer getReferrerZcId() {
        return referrerZcId;
    }

    public void setReferrerZcId(Integer referrerZcId) {
        this.referrerZcId = referrerZcId;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region == null ? null : region.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Date getZcTime() {
        return zcTime;
    }

    public void setZcTime(Date zcTime) {
        this.zcTime = zcTime;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest == null ? null : interest.trim();
    }
}