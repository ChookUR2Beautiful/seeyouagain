package com.xmn.saas.entity.wallet;

import java.util.Date;

/**
 * 银行卡类
 * @author zhouxiaojian
 *
 */

public class BankApply {
    private Integer appid;
    
    private Integer sellerId;

    private Integer aid;

    private Integer accounttype;

    private String fullname;

    private String bank;

    private String location;

    private Integer idtype;

    private String idcard;

    private String bankid;

    private Integer type;

    private Date bdate;

    private String bankphone;

    private String remark;

    private Integer handletype;

    private String handleremark;

    private Integer modifitype;

    private String cityname;

    private String abbrev;

    private String bankname;

    private Integer ispublic;

    private String accountid;

    private String license;

    private String upidcard;

    private String dwidcard;

    private Integer applytype;
    
    

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public Integer getAppid() {
        return appid;
    }

    public void setAppid(Integer appid) {
        this.appid = appid;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public Integer getAccounttype() {
        return accounttype;
    }

    public void setAccounttype(Integer accounttype) {
        this.accounttype = accounttype;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname == null ? null : fullname.trim();
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank == null ? null : bank.trim();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
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

    public String getBankid() {
        return bankid;
    }

    public void setBankid(String bankid) {
        this.bankid = bankid == null ? null : bankid.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getBdate() {
        return bdate;
    }

    public void setBdate(Date bdate) {
        this.bdate = bdate;
    }

    public String getBankphone() {
        return bankphone;
    }

    public void setBankphone(String bankphone) {
        this.bankphone = bankphone == null ? null : bankphone.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getHandletype() {
        return handletype;
    }

    public void setHandletype(Integer handletype) {
        this.handletype = handletype;
    }

    public String getHandleremark() {
        return handleremark;
    }

    public void setHandleremark(String handleremark) {
        this.handleremark = handleremark == null ? null : handleremark.trim();
    }

    public Integer getModifitype() {
        return modifitype;
    }

    public void setModifitype(Integer modifitype) {
        this.modifitype = modifitype;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname == null ? null : cityname.trim();
    }

    public String getAbbrev() {
        return abbrev;
    }

    public void setAbbrev(String abbrev) {
        this.abbrev = abbrev == null ? null : abbrev.trim();
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname == null ? null : bankname.trim();
    }

    public Integer getIspublic() {
        return ispublic;
    }

    public void setIspublic(Integer ispublic) {
        this.ispublic = ispublic;
    }

    public String getAccountid() {
        return accountid;
    }

    public void setAccountid(String accountid) {
        this.accountid = accountid == null ? null : accountid.trim();
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license == null ? null : license.trim();
    }

    public String getUpidcard() {
        return upidcard;
    }

    public void setUpidcard(String upidcard) {
        this.upidcard = upidcard == null ? null : upidcard.trim();
    }

    public String getDwidcard() {
        return dwidcard;
    }

    public void setDwidcard(String dwidcard) {
        this.dwidcard = dwidcard == null ? null : dwidcard.trim();
    }

    public Integer getApplytype() {
        return applytype;
    }

    public void setApplytype(Integer applytype) {
        this.applytype = applytype;
    }
}