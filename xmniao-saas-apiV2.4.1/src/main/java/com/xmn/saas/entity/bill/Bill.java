package com.xmn.saas.entity.bill;

import java.math.BigDecimal;
import java.util.Date;

public class Bill {
    private Long bid;

    private Integer codeid;

    private Integer sellerid;

    private String sellername;

    private Integer consumeJointid;

    private String nname;

    private BigDecimal money;

    private BigDecimal profit;

    private BigDecimal commision;

    private BigDecimal payment;

    private BigDecimal rebate;

    private Integer aid;

    private String fullname;

    private String phoneid;

    private Boolean bfirst;

    private Integer status;

    private Date sdate;

    private Date zdate;

    private Date ydate;

    private String number;

    private String paytype;

    private Integer isaccount;

    private Double baseagio;

    private Integer type;

    private Integer hstatus;

    private String consumeCorporate;

    private Integer genussellerid;

    private String genusname;

    private Integer jointid;

    private String corporate;

    private Byte mikeType;

    private Integer uid;

    private String commission;

    private String area;

    private Integer zoneid;

    private String payid;

    private Integer isverify;

    private Date ldate;

    private Date fdate;

    private Date edate;

    private Byte commStatus;

    private Byte estimate;

    private Integer isVirtual;

    private Double flatAgio;

    private Byte entryStatus;

    private BigDecimal giveMoney;

    private BigDecimal flatMoney;

    private BigDecimal maxHistory;

   
    private BigDecimal cuser;

    private BigDecimal cdenom;

    private String thirdUid;

    private Byte normal;

    private Byte isActivity;

    private BigDecimal prizeNum;

    private Integer source;

    private Byte phoneType;

    private String version;

    private Integer versionId;

    private Integer appSerial;

    private String imei;

    private Double ratio;

    private BigDecimal ratioMoney;

    private Integer orderSource;

    private Long noid;

    private BigDecimal amount;

    private Double integral;

    private Integer xmerUid;

    private Integer mikeid;

    private String mikename;

    private BigDecimal reduction;

    private Integer couponType;
    
    private Integer ledgerMode;
    
    private BigDecimal fullReduction;
    
    private Integer billType;//判断流水的类型
    
    
    private Integer liveLedger;	//是否分账
    
    private Integer liveLedgerStyle;	//'直播分账形式 0 自动分账 1 手动分账',

    private Integer liveLedgerMode;  //直播分账模式 1 全额分账 2 仅粉丝券分账',
    
    private double liveLedgerRatio;//直播分账比例 如:0.6000
    private String uidRelationChain;//用户会员等级关系链
    
    
    public Integer getLiveLedgerStyle() {
        return liveLedgerStyle;
    }

    public void setLiveLedgerStyle(Integer liveLedgerStyle) {
        this.liveLedgerStyle = liveLedgerStyle;
    }

    public Integer getLiveLedgerMode() {
        return liveLedgerMode;
    }

    public void setLiveLedgerMode(Integer liveLedgerMode) {
        this.liveLedgerMode = liveLedgerMode;
    }

    public double getLiveLedgerRatio() {
        return liveLedgerRatio;
    }

    public void setLiveLedgerRatio(double liveLedgerRatio) {
        this.liveLedgerRatio = liveLedgerRatio;
    }

    public String getUidRelationChain() {
        return uidRelationChain;
    }

    public void setUidRelationChain(String uidRelationChain) {
        this.uidRelationChain = uidRelationChain;
    }

    public Integer getLiveLedger() {
		return liveLedger;
	}

	public void setLiveLedger(Integer liveLedger) {
		this.liveLedger = liveLedger;
	}

	public Integer getBillType() {
        return billType;
    }

    public void setBillType(Integer billType) {
        this.billType = billType;
    }

    public BigDecimal getFullReduction() {
		return fullReduction;
	}

	public void setFullReduction(BigDecimal fullReduction) {
		this.fullReduction = fullReduction;
	}

	public Integer getLedgerMode() {
		return ledgerMode;
	}

	public void setLedgerMode(Integer ledgerMode) {
		this.ledgerMode = ledgerMode;
	}

	public Long getBid() {
        return bid;
    }

    public void setBid(Long bid) {
        this.bid = bid;
    }

    public Integer getCodeid() {
        return codeid;
    }

    public void setCodeid(Integer codeid) {
        this.codeid = codeid;
    }

    public Integer getSellerid() {
        return sellerid;
    }

    public void setSellerid(Integer sellerid) {
        this.sellerid = sellerid;
    }

    public String getSellername() {
        return sellername;
    }

    public void setSellername(String sellername) {
        this.sellername = sellername == null ? null : sellername.trim();
    }

    public Integer getConsumeJointid() {
        return consumeJointid;
    }

    public void setConsumeJointid(Integer consumeJointid) {
        this.consumeJointid = consumeJointid;
    }

    public String getNname() {
        return nname;
    }

    public void setNname(String nname) {
        this.nname = nname == null ? null : nname.trim();
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

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname == null ? null : fullname.trim();
    }

    public String getPhoneid() {
        return phoneid;
    }

    public void setPhoneid(String phoneid) {
        this.phoneid = phoneid == null ? null : phoneid.trim();
    }

    public Boolean getBfirst() {
        return bfirst;
    }

    public void setBfirst(Boolean bfirst) {
        this.bfirst = bfirst;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public Date getYdate() {
        return ydate;
    }

    public void setYdate(Date ydate) {
        this.ydate = ydate;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
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

    public Double getBaseagio() {
        return baseagio;
    }

    public void setBaseagio(Double baseagio) {
        this.baseagio = baseagio;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getHstatus() {
        return hstatus;
    }

    public void setHstatus(Integer hstatus) {
        this.hstatus = hstatus;
    }

    public String getConsumeCorporate() {
        return consumeCorporate;
    }

    public void setConsumeCorporate(String consumeCorporate) {
        this.consumeCorporate = consumeCorporate == null ? null : consumeCorporate.trim();
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

    public Byte getMikeType() {
        return mikeType;
    }

    public void setMikeType(Byte mikeType) {
        this.mikeType = mikeType;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getCommission() {
        return commission;
    }

    public void setCommission(String commission) {
        this.commission = commission == null ? null : commission.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public Integer getZoneid() {
        return zoneid;
    }

    public void setZoneid(Integer zoneid) {
        this.zoneid = zoneid;
    }

    public String getPayid() {
        return payid;
    }

    public void setPayid(String payid) {
        this.payid = payid == null ? null : payid.trim();
    }

    public Integer getIsverify() {
        return isverify;
    }

    public void setIsverify(Integer isverify) {
        this.isverify = isverify;
    }

    public Date getLdate() {
        return ldate;
    }

    public void setLdate(Date ldate) {
        this.ldate = ldate;
    }

    public Date getFdate() {
        return fdate;
    }

    public void setFdate(Date fdate) {
        this.fdate = fdate;
    }

    public Date getEdate() {
        return edate;
    }

    public void setEdate(Date edate) {
        this.edate = edate;
    }

    public Byte getCommStatus() {
        return commStatus;
    }

    public void setCommStatus(Byte commStatus) {
        this.commStatus = commStatus;
    }

    public Byte getEstimate() {
        return estimate;
    }

    public void setEstimate(Byte estimate) {
        this.estimate = estimate;
    }

    public Integer getIsVirtual() {
        return isVirtual;
    }

    public void setIsVirtual(Integer isVirtual) {
        this.isVirtual = isVirtual;
    }

    public Double getFlatAgio() {
        return flatAgio;
    }

    public void setFlatAgio(Double flatAgio) {
        this.flatAgio = flatAgio;
    }

    public Byte getEntryStatus() {
        return entryStatus;
    }

    public void setEntryStatus(Byte entryStatus) {
        this.entryStatus = entryStatus;
    }

    public BigDecimal getGiveMoney() {
        return giveMoney;
    }

    public void setGiveMoney(BigDecimal giveMoney) {
        this.giveMoney = giveMoney;
    }

    public BigDecimal getFlatMoney() {
        return flatMoney;
    }

    public void setFlatMoney(BigDecimal flatMoney) {
        this.flatMoney = flatMoney;
    }

    public BigDecimal getMaxHistory() {
        return maxHistory;
    }

    public void setMaxHistory(BigDecimal maxHistory) {
        this.maxHistory = maxHistory;
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

    public Byte getNormal() {
        return normal;
    }

    public void setNormal(Byte normal) {
        this.normal = normal;
    }

    public Byte getIsActivity() {
        return isActivity;
    }

    public void setIsActivity(Byte isActivity) {
        this.isActivity = isActivity;
    }

    public BigDecimal getPrizeNum() {
        return prizeNum;
    }

    public void setPrizeNum(BigDecimal prizeNum) {
        this.prizeNum = prizeNum;
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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }

    public Integer getVersionId() {
        return versionId;
    }

    public void setVersionId(Integer versionId) {
        this.versionId = versionId;
    }

    public Integer getAppSerial() {
        return appSerial;
    }

    public void setAppSerial(Integer appSerial) {
        this.appSerial = appSerial;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei == null ? null : imei.trim();
    }

    public Double getRatio() {
        return ratio;
    }

    public void setRatio(Double ratio) {
        this.ratio = ratio;
    }

    public BigDecimal getRatioMoney() {
        return ratioMoney;
    }

    public void setRatioMoney(BigDecimal ratioMoney) {
        this.ratioMoney = ratioMoney;
    }

    public Integer getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(Integer orderSource) {
        this.orderSource = orderSource;
    }

    public Long getNoid() {
        return noid;
    }

    public void setNoid(Long noid) {
        this.noid = noid;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Double getIntegral() {
        return integral;
    }

    public void setIntegral(Double integral) {
        this.integral = integral;
    }

    public Integer getXmerUid() {
        return xmerUid;
    }

    public void setXmerUid(Integer xmerUid) {
        this.xmerUid = xmerUid;
    }

    public Integer getMikeid() {
        return mikeid;
    }

    public void setMikeid(Integer mikeid) {
        this.mikeid = mikeid;
    }

    public String getMikename() {
        return mikename;
    }

    public void setMikename(String mikename) {
        this.mikename = mikename == null ? null : mikename.trim();
    }

    public BigDecimal getReduction() {
        return reduction;
    }

    public void setReduction(BigDecimal reduction) {
        this.reduction = reduction;
    }

    public Integer getCouponType() {
        return couponType;
    }

    public void setCouponType(Integer couponType) {
        this.couponType = couponType;
    }

    
}