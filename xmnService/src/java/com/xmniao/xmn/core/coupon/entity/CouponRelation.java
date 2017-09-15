package com.xmniao.xmn.core.coupon.entity;

import java.math.BigDecimal;

public class CouponRelation {
    private Integer id;

    private String bid;

    private String cserial;

    private BigDecimal cdenom;

    private BigDecimal cuser;

    private String orderType;

    private Byte ctype;

    private Integer cdid;

    private String serial;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid == null ? null : bid.trim();
    }

    public String getCserial() {
        return cserial;
    }

    public void setCserial(String cserial) {
        this.cserial = cserial == null ? null : cserial.trim();
    }

    public BigDecimal getCdenom() {
        return cdenom;
    }

    public void setCdenom(BigDecimal cdenom) {
        this.cdenom = cdenom;
    }

    public BigDecimal getCuser() {
        return cuser;
    }

    public void setCuser(BigDecimal cuser) {
        this.cuser = cuser;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType == null ? null : orderType.trim();
    }

    public Byte getCtype() {
        return ctype;
    }

    public void setCtype(Byte ctype) {
        this.ctype = ctype;
    }

    public Integer getCdid() {
        return cdid;
    }

    public void setCdid(Integer cdid) {
        this.cdid = cdid;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial == null ? null : serial.trim();
    }
}