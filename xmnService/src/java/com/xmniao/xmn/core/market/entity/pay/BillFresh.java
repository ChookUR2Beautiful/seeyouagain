package com.xmniao.xmn.core.market.entity.pay;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
* @projectName: xmnService 
* @ClassName: BillFresh    
* @Description:积分订单实体   
* @author: liuzhihao   
* @date: 2016年12月22日 上午10:17:26
 */
public class BillFresh {
    private Long bid;

    private Integer uid;

    private String nname;

    private String phoneid;

    private Integer warenum;

    private BigDecimal money;

    private BigDecimal profit;

    private BigDecimal commision;

    private BigDecimal giveMoney;

    private Double integral;

    private BigDecimal cardpay;

    private BigDecimal payment;

    private BigDecimal rebate;

    private String express;

    private String address;

    private String tel;

    private String mobile;

    private Integer status;

    private Integer wstatus;

    private String logistics;

    private Date sdate;

    private Date zdate;

    private Date ddate;

    private Date ydate;

    private Date edate;

    private String paytype;

    private Integer isaccount;

    private Integer hstatus;

    private String payid;

    private String number;
    
    private Integer cdid;

    private BigDecimal cuser;

    private BigDecimal cdenom;

    private String thirdUid;

    private Integer source;

    private Byte phoneType;

    private String vers;

    private Integer versionId;

    private String imei;

    private Integer appSerial;

    private Date udate;

    private Integer dstatus;

    private String commission;

    private Integer noid;

    private String username;

    private BigDecimal freight;

    private BigDecimal maxIntegral;

    private Integer version;

    private Integer genussellerid;

    private String genusname;

    private Integer consumeJointid;

    private String consumeJointname;

    private Integer cityid;
    
    private Integer remindExpress;//提醒发货
    
    private Integer rid;//收货地址ID
    
    private Integer sendUid;//送礼人ID
    
    private Integer appSourceStatus;//app来源

    public Long getBid() {
        return bid;
    }

    public void setBid(Long bid) {
        this.bid = bid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getNname() {
        return nname;
    }

    public void setNname(String nname) {
        this.nname = nname == null ? null : nname.trim();
    }

    public String getPhoneid() {
        return phoneid;
    }

    public void setPhoneid(String phoneid) {
        this.phoneid = phoneid == null ? null : phoneid.trim();
    }

    public Integer getWarenum() {
        return warenum;
    }

    public void setWarenum(Integer warenum) {
        this.warenum = warenum;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    public BigDecimal getCommision() {
        return commision;
    }

    public void setCommision(BigDecimal commision) {
        this.commision = commision;
    }

    public BigDecimal getGiveMoney() {
        return giveMoney;
    }

    public void setGiveMoney(BigDecimal giveMoney) {
        this.giveMoney = giveMoney;
    }

    public Double getIntegral() {
        return integral;
    }

    public void setIntegral(Double integral) {
        this.integral = integral;
    }

    public BigDecimal getCardpay() {
        return cardpay;
    }

    public void setCardpay(BigDecimal cardpay) {
        this.cardpay = cardpay;
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    public BigDecimal getRebate() {
        return rebate;
    }

    public void setRebate(BigDecimal rebate) {
        this.rebate = rebate;
    }

    public String getExpress() {
        return express;
    }

    public void setExpress(String express) {
        this.express = express == null ? null : express.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getWstatus() {
        return wstatus;
    }

    public void setWstatus(Integer wstatus) {
        this.wstatus = wstatus;
    }

    public String getLogistics() {
        return logistics;
    }

    public void setLogistics(String logistics) {
        this.logistics = logistics == null ? null : logistics.trim();
    }

    public Date getSdate() {
        return sdate;
    }

    public void setSdate(Date sdate) {
        this.sdate = sdate;
    }

    public Date getZdate() {
        return zdate;
    }

    public void setZdate(Date zdate) {
        this.zdate = zdate;
    }

    public Date getDdate() {
        return ddate;
    }

    public void setDdate(Date ddate) {
        this.ddate = ddate;
    }

    public Date getYdate() {
        return ydate;
    }

    public void setYdate(Date ydate) {
        this.ydate = ydate;
    }

    public Date getEdate() {
        return edate;
    }

    public void setEdate(Date edate) {
        this.edate = edate;
    }

    public String getPaytype() {
        return paytype;
    }

    public void setPaytype(String paytype) {
        this.paytype = paytype == null ? null : paytype.trim();
    }

    public Integer getIsaccount() {
        return isaccount;
    }

    public void setIsaccount(Integer isaccount) {
        this.isaccount = isaccount;
    }

    public Integer getHstatus() {
        return hstatus;
    }

    public void setHstatus(Integer hstatus) {
        this.hstatus = hstatus;
    }

    public String getPayid() {
        return payid;
    }

    public void setPayid(String payid) {
        this.payid = payid == null ? null : payid.trim();
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    public Integer getCdid() {
		return cdid;
	}

	public void setCdid(Integer cdid) {
		this.cdid = cdid;
	}

	public BigDecimal getCuser() {
        return cuser;
    }

    public void setCuser(BigDecimal cuser) {
        this.cuser = cuser;
    }

    public BigDecimal getCdenom() {
        return cdenom;
    }

    public void setCdenom(BigDecimal cdenom) {
        this.cdenom = cdenom;
    }

    public String getThirdUid() {
        return thirdUid;
    }

    public void setThirdUid(String thirdUid) {
        this.thirdUid = thirdUid == null ? null : thirdUid.trim();
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public Byte getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(Byte phoneType) {
        this.phoneType = phoneType;
    }

    public String getVers() {
        return vers;
    }

    public void setVers(String vers) {
        this.vers = vers == null ? null : vers.trim();
    }

    public Integer getVersionId() {
        return versionId;
    }

    public void setVersionId(Integer versionId) {
        this.versionId = versionId;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei == null ? null : imei.trim();
    }

    public Integer getAppSerial() {
        return appSerial;
    }

    public void setAppSerial(Integer appSerial) {
        this.appSerial = appSerial;
    }

    public Date getUdate() {
        return udate;
    }

    public void setUdate(Date udate) {
        this.udate = udate;
    }

    public Integer getDstatus() {
        return dstatus;
    }

    public void setDstatus(Integer dstatus) {
        this.dstatus = dstatus;
    }

    public String getCommission() {
        return commission;
    }

    public void setCommission(String commission) {
        this.commission = commission == null ? null : commission.trim();
    }

    public Integer getNoid() {
        return noid;
    }

    public void setNoid(Integer noid) {
        this.noid = noid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public BigDecimal getFreight() {
        return freight;
    }

    public void setFreight(BigDecimal freight) {
        this.freight = freight;
    }

    public BigDecimal getMaxIntegral() {
        return maxIntegral;
    }

    public void setMaxIntegral(BigDecimal maxIntegral) {
        this.maxIntegral = maxIntegral;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
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

    public Integer getConsumeJointid() {
        return consumeJointid;
    }

    public void setConsumeJointid(Integer consumeJointid) {
        this.consumeJointid = consumeJointid;
    }

    public String getConsumeJointname() {
        return consumeJointname;
    }

    public void setConsumeJointname(String consumeJointname) {
        this.consumeJointname = consumeJointname == null ? null : consumeJointname.trim();
    }

    public Integer getCityid() {
        return cityid;
    }

    public void setCityid(Integer cityid) {
        this.cityid = cityid;
    }
    
	public Integer getRemindExpress() {
		return remindExpress;
	}

	public void setRemindExpress(Integer remindExpress) {
		this.remindExpress = remindExpress;
	}

	public Integer getRid() {
		return rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}

	public Integer getSendUid() {
		return sendUid;
	}

	public void setSendUid(Integer sendUid) {
		this.sendUid = sendUid;
	}

	public Integer getAppSourceStatus() {
		return appSourceStatus;
	}

	public void setAppSourceStatus(Integer appSourceStatus) {
		this.appSourceStatus = appSourceStatus;
	}

	@Override
	public String toString() {
		return "BillFresh [bid=" + bid + ", uid=" + uid + ", nname=" + nname + ", phoneid=" + phoneid + ", warenum=" + warenum
			+ ", money=" + money + ", profit=" + profit + ", commision=" + commision + ", giveMoney=" + giveMoney + ", integral="
			+ integral + ", cardpay=" + cardpay + ", payment=" + payment + ", rebate=" + rebate + ", express=" + express
			+ ", address=" + address + ", tel=" + tel + ", mobile=" + mobile + ", status=" + status + ", wstatus=" + wstatus
			+ ", logistics=" + logistics + ", sdate=" + sdate + ", zdate=" + zdate + ", ddate=" + ddate + ", ydate=" + ydate
			+ ", edate=" + edate + ", paytype=" + paytype + ", isaccount=" + isaccount + ", hstatus=" + hstatus + ", payid=" + payid
			+ ", number=" + number + ", cdid=" + cdid + ", cuser=" + cuser + ", cdenom=" + cdenom + ", thirdUid=" + thirdUid
			+ ", source=" + source + ", phoneType=" + phoneType + ", vers=" + vers + ", versionId=" + versionId + ", imei=" + imei
			+ ", appSerial=" + appSerial + ", udate=" + udate + ", dstatus=" + dstatus + ", commission=" + commission + ", noid="
			+ noid + ", username=" + username + ", freight=" + freight + ", maxIntegral=" + maxIntegral + ", version=" + version
			+ ", genussellerid=" + genussellerid + ", genusname=" + genusname + ", consumeJointid=" + consumeJointid
			+ ", consumeJointname=" + consumeJointname + ", cityid=" + cityid + ", remindExpress=" + remindExpress + ", rid=" + rid
			+ ", sendUid=" + sendUid + "]";
	}
	
}