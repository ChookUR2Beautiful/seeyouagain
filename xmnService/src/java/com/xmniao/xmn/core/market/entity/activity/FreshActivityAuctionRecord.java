package com.xmniao.xmn.core.market.entity.activity;

import java.math.BigDecimal;
import java.util.Date;

public class FreshActivityAuctionRecord {
    private Integer id;

    private Integer activityId;

    private Integer uid;

    private String phone;

    private BigDecimal risedPrice;

    private Integer risedNum;

    private Integer state;
    
    private String stateMsg;//状态字符串

    private Date createTime;
    
    private Integer pageOffset;

    private Integer pageSize;
    
    private BigDecimal nowPrice; //当前价格
    
    private String name;//活动标题
    
    private String picUrl;//缩略图
    
    private Date endTime;
    
    private String finishOrder;//竞拍成功的订单号
    
    private String depositOrder;//保证金订单号
    
    private Integer depositState;//保证金支付状态
    
    private BigDecimal depositPrice;//保证金金额
    
    private String userName;
    
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public BigDecimal getDepositPrice() {
        return depositPrice;
    }

    public void setDepositPrice(BigDecimal depositPrice) {
        this.depositPrice = depositPrice;
    }

    public Integer getDepositState() {
        return depositState;
    }

    public void setDepositState(Integer depositState) {
        this.depositState = depositState;
    }

    public String getFinishOrder() {
        return finishOrder;
    }

    public void setFinishOrder(String finishOrder) {
        this.finishOrder = finishOrder;
    }

    public String getDepositOrder() {
        return depositOrder;
    }

    public void setDepositOrder(String depositOrder) {
        this.depositOrder = depositOrder;
    }

    public String getStateMsg() {
        return stateMsg;
    }

    public void setStateMsg(String stateMsg) {
        this.stateMsg = stateMsg;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public BigDecimal getNowPrice() {
        return nowPrice;
    }

    public void setNowPrice(BigDecimal nowPrice) {
        this.nowPrice = nowPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public Integer getPageOffset() {
        return pageOffset;
    }

    public void setPageOffset(Integer pageOffset) {
        this.pageOffset = pageOffset;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
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

    public BigDecimal getRisedPrice() {
        return risedPrice;
    }

    public void setRisedPrice(BigDecimal risedPrice) {
        this.risedPrice = risedPrice;
    }

    public Integer getRisedNum() {
        return risedNum;
    }

    public void setRisedNum(Integer risedNum) {
        this.risedNum = risedNum;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}