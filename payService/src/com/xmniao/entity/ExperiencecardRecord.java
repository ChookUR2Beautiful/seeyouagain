package com.xmniao.entity;

import java.util.Date;

public class ExperiencecardRecord {
    private Integer id;

    private Integer cid;

    private Integer rtype;

    private Integer stock;

    private Integer qstock;

    private Integer hstock;

    private String source;

    private String remark;

    private Date createTime;

    @Override
    public String toString() {
        return "ExperiencecardRecord{" +
                "id=" + id +
                ", cid=" + cid +
                ", rtype=" + rtype +
                ", stock=" + stock +
                ", qstock=" + qstock +
                ", hstock=" + hstock +
                ", source='" + source + '\'' +
                ", remark='" + remark + '\'' +
                ", createTime=" + createTime +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getRtype() {
        return rtype;
    }

    public void setRtype(Integer rtype) {
        this.rtype = rtype;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getQstock() {
        return qstock;
    }

    public void setQstock(Integer qstock) {
        this.qstock = qstock;
    }

    public Integer getHstock() {
        return hstock;
    }

    public void setHstock(Integer hstock) {
        this.hstock = hstock;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}