package com.xmniao.xmn.core.order.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 多个预售订单
 * Created by Administrator on 2017/5/24.
 */
public class MultipleOrder implements Serializable {
    private static final long serialVersionUID = 402925092421723555L;

    private String orderNo; //订单订单编号
    private String title;  //名称
    private Integer nums;  //购买数量
    private String picUrl;  //封面图
    private String totalAmount;  //订单支付总额
    private String reallyPayAmount;  //实付
    private String cname;   //固定值 套餐， 直播粉丝卷, 3天有效，5次机会
    private Integer status;  //订单状态
    private String orderStautsDesc;  //订单状态描述
    private String createTime;  // 创建时间
    private String useTimeDesc; //使用期限描述
    private Integer whichType;  // 1 套餐 2 粉丝卷 3 体验官
    private boolean isLiveSeller = false; //是否是直播间套餐

    private Date tmpDate;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getNums() {
        return nums;
    }

    public void setNums(Integer nums) {
        this.nums = nums;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getReallyPayAmount() {
        return reallyPayAmount;
    }

    public void setReallyPayAmount(String reallyPayAmount) {
        this.reallyPayAmount = reallyPayAmount;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getOrderStautsDesc() {
        return orderStautsDesc;
    }

    public void setOrderStautsDesc(String orderStautsDesc) {
        this.orderStautsDesc = orderStautsDesc;
    }

    public Integer getWhichType() {
        return whichType;
    }

    public void setWhichType(Integer whichType) {
        this.whichType = whichType;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUseTimeDesc() {
        return useTimeDesc;
    }

    public void setUseTimeDesc(String useTimeDesc) {
        this.useTimeDesc = useTimeDesc;
    }

    public boolean isLiveSeller() {
        return isLiveSeller;
    }

    public void setLiveSeller(boolean liveSeller) {
        isLiveSeller = liveSeller;
    }


    public Date getTmpDate() {
        return tmpDate;
    }

    public void setTmpDate(Date tmpDate) {
        this.tmpDate = tmpDate;
    }

    @Override
    public String toString() {
        return "MultipleOrder{" +
                "orderNo='" + orderNo + '\'' +
                ", title='" + title + '\'' +
                ", nums=" + nums +
                ", picUrl='" + picUrl + '\'' +
                ", totalAmount='" + totalAmount + '\'' +
                ", reallyPayAmount='" + reallyPayAmount + '\'' +
                ", cname='" + cname + '\'' +
                ", status=" + status +
                ", orderStautsDesc='" + orderStautsDesc + '\'' +
                ", createTime='" + createTime + '\'' +
                ", useTimeDesc='" + useTimeDesc + '\'' +
                ", whichType=" + whichType +
                ", isLiveSeller=" + isLiveSeller +
                ", tmpDate=" + tmpDate +
                '}';
    }
}
