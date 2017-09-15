package com.xmniao.xmn.core.market.entity.activity.indiana;

import com.xmniao.xmn.core.market.entity.pay.ProductInfo;

import java.math.BigDecimal;
import java.util.Date;

public class FreshActivityIndiana {
    private Integer id;

    private String title;

    private Integer status;

    private Date beginTime;

    private Date endTime;

    private Date termTime;

    private Long codeid;

    private String pvIds;

    private String pvValue;

    private BigDecimal basePrice;

    private Integer point;

    private BigDecimal pointPrice;

    private Integer boutNum;

    private Integer boutResidue;

    private Integer isReal;

    private Date createTime;

    private Date updateTime;

    // 夺宝活动关联的商品
    private ProductInfo product;
    // 夺宝活动当前期次
    private FreshActivityIndianaBout currentBout;
    private int residuePoint;

    public FreshActivityIndianaBout getCurrentBout() {
        return currentBout;
    }

    public void setCurrentBout(FreshActivityIndianaBout currentBout) {
        this.currentBout = currentBout;
    }

    public ProductInfo getProduct() {
        return product;
    }
    public void setProduct(ProductInfo product) {
        this.product = product;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getTermTime() {
        return termTime;
    }

    public void setTermTime(Date termTime) {
        this.termTime = termTime;
    }

    public Long getCodeid() {
        return codeid;
    }

    public void setCodeid(Long codeid) {
        this.codeid = codeid;
    }

    public String getPvIds() {
        return pvIds;
    }

    public void setPvIds(String pvIds) {
        this.pvIds = pvIds == null ? null : pvIds.trim();
    }

    public String getPvValue() {
        return pvValue;
    }

    public void setPvValue(String pvValue) {
        this.pvValue = pvValue == null ? null : pvValue.trim();
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public BigDecimal getPointPrice() {
        return pointPrice;
    }

    public void setPointPrice(BigDecimal pointPrice) {
        this.pointPrice = pointPrice;
    }

    public Integer getBoutNum() {
        return boutNum;
    }

    public void setBoutNum(Integer boutNum) {
        this.boutNum = boutNum;
    }

    public Integer getBoutResidue() {
        return boutResidue;
    }

    public void setBoutResidue(Integer boutResidue) {
        this.boutResidue = boutResidue;
    }

    public Integer getIsReal() {
        return isReal;
    }

    public void setIsReal(Integer isReal) {
        this.isReal = isReal;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public void setResiduePoint(int residuePoint) {
        this.residuePoint = residuePoint;
    }

    public int getResiduePoint() {
        return residuePoint;
    }
}