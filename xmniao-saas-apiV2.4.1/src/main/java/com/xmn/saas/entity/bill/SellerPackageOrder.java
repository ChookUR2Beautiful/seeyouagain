package com.xmn.saas.entity.bill;

import java.math.BigDecimal;
import java.util.Date;

public class SellerPackageOrder {
    private Integer id;

    private String orderNo;

    private Integer pid;

    private String title;

    private Integer sellerid;

    private Integer uid;

    private String phone;

    private String uname;

    private Date createTime;

    private Date payTime;

    private Date lastTime;

    private Integer nums;

    private BigDecimal originalPrice;

    private BigDecimal sellingPrice;

    private BigDecimal sellingCoinPrice;

    private BigDecimal totalAmount;

    private BigDecimal totalCoinAmount;

    private BigDecimal sellerAgio;

    private BigDecimal baseAgio;

    private BigDecimal cash;

    private BigDecimal balance;

    private BigDecimal commision;

    private BigDecimal zbalance;

    private BigDecimal sellerCoin;

    private BigDecimal beans;

    private BigDecimal cuser;

    private Integer cdid;

    private BigDecimal retrunCouponAmount;

    private String paymentType;

    private String payid;

    private Integer status;

    private Integer ledgerType;

    private BigDecimal ledgerRatio;

    private Date updateTime;

    private String uidRelationChain;

    private Integer version;

    private Integer orderSource;

    private Integer buySource;

    private String notice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getSellerid() {
        return sellerid;
    }

    public void setSellerid(Integer sellerid) {
        this.sellerid = sellerid;
    }

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    public Integer getNums() {
        return nums;
    }

    public void setNums(Integer nums) {
        this.nums = nums;
    }

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    public BigDecimal getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(BigDecimal sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public BigDecimal getSellingCoinPrice() {
        return sellingCoinPrice;
    }

    public void setSellingCoinPrice(BigDecimal sellingCoinPrice) {
        this.sellingCoinPrice = sellingCoinPrice;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getTotalCoinAmount() {
        return totalCoinAmount;
    }

    public void setTotalCoinAmount(BigDecimal totalCoinAmount) {
        this.totalCoinAmount = totalCoinAmount;
    }

    public BigDecimal getSellerAgio() {
        return sellerAgio;
    }

    public void setSellerAgio(BigDecimal sellerAgio) {
        this.sellerAgio = sellerAgio;
    }

    public BigDecimal getBaseAgio() {
        return baseAgio;
    }

    public void setBaseAgio(BigDecimal baseAgio) {
        this.baseAgio = baseAgio;
    }

    public BigDecimal getCash() {
        return cash;
    }

    public void setCash(BigDecimal cash) {
        this.cash = cash;
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

    public BigDecimal getSellerCoin() {
        return sellerCoin;
    }

    public void setSellerCoin(BigDecimal sellerCoin) {
        this.sellerCoin = sellerCoin;
    }

    public BigDecimal getBeans() {
        return beans;
    }

    public void setBeans(BigDecimal beans) {
        this.beans = beans;
    }

    public BigDecimal getCuser() {
        return cuser;
    }

    public void setCuser(BigDecimal cuser) {
        this.cuser = cuser;
    }

    public Integer getCdid() {
        return cdid;
    }

    public void setCdid(Integer cdid) {
        this.cdid = cdid;
    }

    public BigDecimal getRetrunCouponAmount() {
        return retrunCouponAmount;
    }

    public void setRetrunCouponAmount(BigDecimal retrunCouponAmount) {
        this.retrunCouponAmount = retrunCouponAmount;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType == null ? null : paymentType.trim();
    }

    public String getPayid() {
        return payid;
    }

    public void setPayid(String payid) {
        this.payid = payid == null ? null : payid.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getLedgerType() {
        return ledgerType;
    }

    public void setLedgerType(Integer ledgerType) {
        this.ledgerType = ledgerType;
    }

    public BigDecimal getLedgerRatio() {
        return ledgerRatio;
    }

    public void setLedgerRatio(BigDecimal ledgerRatio) {
        this.ledgerRatio = ledgerRatio;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUidRelationChain() {
        return uidRelationChain;
    }

    public void setUidRelationChain(String uidRelationChain) {
        this.uidRelationChain = uidRelationChain == null ? null : uidRelationChain.trim();
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(Integer orderSource) {
        this.orderSource = orderSource;
    }

    public Integer getBuySource() {
        return buySource;
    }

    public void setBuySource(Integer buySource) {
        this.buySource = buySource;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice == null ? null : notice.trim();
    }
}