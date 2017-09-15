package com.xmniao.xmn.core.integral.entity;

import java.math.BigDecimal;
import java.util.Date;

public class ProductBill {
    private Integer id;

    private Integer codeid;

    private String pname;

    private Long bid;

    private String subBid;

    private Integer warenum;

    private Date rdate;

    private Date udate;

    private BigDecimal price;

    private String goodsname;

    private String suttle;

    private BigDecimal freight;

    private Long supplierId;

    private BigDecimal integral;

    private BigDecimal purchaseprice;

    private String attrVal;

    private Integer attrId;

    private BigDecimal attrAmount;

    private String attrStr;

    private Double expweight;

    private Integer exptid;
    
    private String img1;
    

    public String getImg1() {
		return img1;
	}

	public void setImg1(String img1) {
		this.img1 = img1;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCodeid() {
        return codeid;
    }

    public void setCodeid(Integer codeid) {
        this.codeid = codeid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname == null ? null : pname.trim();
    }

    public Long getBid() {
        return bid;
    }

    public void setBid(Long bid) {
        this.bid = bid;
    }

    public String getSubBid() {
        return subBid;
    }

    public void setSubBid(String subBid) {
        this.subBid = subBid == null ? null : subBid.trim();
    }

    public Integer getWarenum() {
        return warenum;
    }

    public void setWarenum(Integer warenum) {
        this.warenum = warenum;
    }

    public Date getRdate() {
        return rdate;
    }

    public void setRdate(Date rdate) {
        this.rdate = rdate;
    }

    public Date getUdate() {
        return udate;
    }

    public void setUdate(Date udate) {
        this.udate = udate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getGoodsname() {
        return goodsname;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname == null ? null : goodsname.trim();
    }

    public String getSuttle() {
        return suttle;
    }

    public void setSuttle(String suttle) {
        this.suttle = suttle == null ? null : suttle.trim();
    }

    public BigDecimal getFreight() {
        return freight;
    }

    public void setFreight(BigDecimal freight) {
        this.freight = freight;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public BigDecimal getIntegral() {
        return integral;
    }

    public void setIntegral(BigDecimal integral) {
        this.integral = integral;
    }

    public BigDecimal getPurchaseprice() {
        return purchaseprice;
    }

    public void setPurchaseprice(BigDecimal purchaseprice) {
        this.purchaseprice = purchaseprice;
    }

    public String getAttrVal() {
        return attrVal;
    }

    public void setAttrVal(String attrVal) {
        this.attrVal = attrVal == null ? null : attrVal.trim();
    }

    public Integer getAttrId() {
        return attrId;
    }

    public void setAttrId(Integer attrId) {
        this.attrId = attrId;
    }

    public BigDecimal getAttrAmount() {
        return attrAmount;
    }

    public void setAttrAmount(BigDecimal attrAmount) {
        this.attrAmount = attrAmount;
    }

    public String getAttrStr() {
        return attrStr;
    }

    public void setAttrStr(String attrStr) {
        this.attrStr = attrStr == null ? null : attrStr.trim();
    }

    public Double getExpweight() {
        return expweight;
    }

    public void setExpweight(Double expweight) {
        this.expweight = expweight;
    }

    public Integer getExptid() {
        return exptid;
    }

    public void setExptid(Integer exptid) {
        this.exptid = exptid;
    }
}