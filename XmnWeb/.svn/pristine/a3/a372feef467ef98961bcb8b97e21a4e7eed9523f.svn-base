package com.xmniao.xmn.core.reward_dividends.controller.vo;


import com.alibaba.fastjson.JSON;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


/**
 * V客充值奖励订单 获取订单列表 请求vo
 * Created by yang.qiang on 2017/5/31.
 */
public class RechargeOrderListRequest {

    // 订单编号
    private String orderNo;
    // 用户id
    private Integer uid;
    // 手机号码
    private String phone;
    // 订单状态
    private Byte status;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTimeBegin;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTimeAfter;


    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
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
        this.phone = phone;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getCreateTimeBegin() {
        return createTimeBegin;
    }

    public void setCreateTimeBegin(Date createTimeBegin) {
        this.createTimeBegin = createTimeBegin;
    }

    public Date getCreateTimeAfter() {
        return createTimeAfter;
    }

    public void setCreateTimeAfter(Date createTimeAfter) {
        this.createTimeAfter = createTimeAfter;
    }
}
