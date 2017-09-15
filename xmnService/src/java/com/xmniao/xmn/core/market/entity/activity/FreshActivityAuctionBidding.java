package com.xmniao.xmn.core.market.entity.activity;

import java.math.BigDecimal;
import java.util.Date;

public class FreshActivityAuctionBidding {
    private Integer id;

    private Integer activityId;

    private Integer uid;

    private String phone;

    private BigDecimal risePrice;

    private Integer riseNum;

    private Date createTime;
    
    private String name;//商家名字
    
    private String userName;
    
    private Integer utype;//'直播用户类型： 1 主播 2 普通用户',
    
    

    public Integer getUtype() {
        return utype;
    }

    public void setUtype(Integer utype) {
        this.utype = utype;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
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

    public BigDecimal getRisePrice() {
        return risePrice;
    }

    public void setRisePrice(BigDecimal risePrice) {
        this.risePrice = risePrice;
    }

    public Integer getRiseNum() {
        return riseNum;
    }

    public void setRiseNum(Integer riseNum) {
        this.riseNum = riseNum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}