package com.xmn.saas.entity.bill;

import java.math.BigDecimal;

public class CouponRelation {
    private Integer id;

    private Long bid;

    private String cserial;

    private BigDecimal cdenom;

    private BigDecimal cuser;

    private Byte ctype;

    private Integer cdid;

    private String serial;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getBid() {
        return bid;
    }

    public void setBid(Long bid) {
        this.bid = bid;
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