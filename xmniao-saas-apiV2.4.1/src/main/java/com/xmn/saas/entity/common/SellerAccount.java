package com.xmn.saas.entity.common;

import java.util.Date;

public class SellerAccount {
    private Integer aid;

    private Integer sellerid;

    private String account;

    private String nname;

    private String fullname;

    private String password;

    private String levelpass;

    private Integer type;

    private Date sdate;

    private String remarks;

    private String phone;

    private String iostoken;

    private Date edate;

    private String workno;

    private Integer uid;

    private Integer paystatus;

    private Integer newstatus;

    private String nonewstime;

    private Integer userstatus;

    private String resourceId;

    private String parAccount;

    // 是否接收收款短信通知
    private Integer receiveMessage ;



    public SellerAccount() {
        super();
    }

    @Override
    public String toString() {
        return "SellerAccount{" +
                "aid=" + aid +
                ", sellerid=" + sellerid +
                ", account='" + account + '\'' +
                ", nname='" + nname + '\'' +
                ", fullname='" + fullname + '\'' +
                ", password='" + password + '\'' +
                ", levelpass='" + levelpass + '\'' +
                ", type=" + type +
                ", sdate=" + sdate +
                ", remarks='" + remarks + '\'' +
                ", phone='" + phone + '\'' +
                ", iostoken='" + iostoken + '\'' +
                ", edate=" + edate +
                ", workno='" + workno + '\'' +
                ", uid=" + uid +
                ", paystatus=" + paystatus +
                ", newstatus=" + newstatus +
                ", nonewstime='" + nonewstime + '\'' +
                ", userstatus=" + userstatus +
                ", resourceId='" + resourceId + '\'' +
                ", parAccount='" + parAccount + '\'' +
                '}';
    }

    public SellerAccount(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public Integer getAid() {
        return aid;
    }

    public Integer getReceiveMessage() {
        return receiveMessage;
    }

    public void setReceiveMessage(Integer receiveMessage) {
        this.receiveMessage = receiveMessage;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public Integer getSellerid() {
        return sellerid;
    }

    public void setSellerid(Integer sellerid) {
        this.sellerid = sellerid;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getNname() {
        return nname;
    }

    public void setNname(String nname) {
        this.nname = nname == null ? null : nname.trim();
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname == null ? null : fullname.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getLevelpass() {
        return levelpass;
    }

    public void setLevelpass(String levelpass) {
        this.levelpass = levelpass == null ? null : levelpass.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getSdate() {
        return sdate;
    }

    public void setSdate(Date sdate) {
        this.sdate = sdate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getIostoken() {
        return iostoken;
    }

    public void setIostoken(String iostoken) {
        this.iostoken = iostoken == null ? null : iostoken.trim();
    }

    public Date getEdate() {
        return edate;
    }

    public void setEdate(Date edate) {
        this.edate = edate;
    }

    public String getWorkno() {
        return workno;
    }

    public void setWorkno(String workno) {
        this.workno = workno == null ? null : workno.trim();
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getPaystatus() {
        return paystatus;
    }

    public void setPaystatus(Integer paystatus) {
        this.paystatus = paystatus;
    }

    public Integer getNewstatus() {
        return newstatus;
    }

    public void setNewstatus(Integer newstatus) {
        this.newstatus = newstatus;
    }

    public String getNonewstime() {
        return nonewstime;
    }

    public void setNonewstime(String nonewstime) {
        this.nonewstime = nonewstime == null ? null : nonewstime.trim();
    }

    public Integer getUserstatus() {
        return userstatus;
    }

    public void setUserstatus(Integer userstatus) {
        this.userstatus = userstatus;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId == null ? null : resourceId.trim();
    }

    public String getParAccount() {
        return parAccount;
    }

    public void setParAccount(String parAccount) {
        this.parAccount = parAccount == null ? null : parAccount.trim();
    }


}