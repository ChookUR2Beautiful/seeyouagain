package com.xmniao.xmn.core.market.entity.pay;

import java.math.BigDecimal;

/**
 * 
* @projectName: xmnService 
* @ClassName: PostageRule    
* @Description: 快递模板详情  
* @author: liuzhihao   
* @date: 2016年12月24日 下午12:25:20
 */
public class PostageRule {
    private Integer id;

    private Integer tid;

    private Integer baseWeight;

    private BigDecimal baseFee;

    private Integer extraWeight;

    private BigDecimal extraFee;

    private Integer isdefault;

    private Integer status;

    private String area;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public Integer getBaseWeight() {
        return baseWeight;
    }

    public void setBaseWeight(Integer baseWeight) {
        this.baseWeight = baseWeight;
    }

    public BigDecimal getBaseFee() {
        return baseFee;
    }

    public void setBaseFee(BigDecimal baseFee) {
        this.baseFee = baseFee;
    }

    public Integer getExtraWeight() {
        return extraWeight;
    }

    public void setExtraWeight(Integer extraWeight) {
        this.extraWeight = extraWeight;
    }

    public BigDecimal getExtraFee() {
        return extraFee;
    }

    public void setExtraFee(BigDecimal extraFee) {
        this.extraFee = extraFee;
    }

    public Integer getIsdefault() {
        return isdefault;
    }

    public void setIsdefault(Integer isdefault) {
        this.isdefault = isdefault;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }
}