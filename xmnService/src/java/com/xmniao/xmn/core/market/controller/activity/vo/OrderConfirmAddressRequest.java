package com.xmniao.xmn.core.market.controller.activity.vo;

import com.xmniao.xmn.core.market.entity.activity.BillFreshActivity;
import net.sf.oval.constraint.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by yang.qiang on 2017/2/27.
 */
public class OrderConfirmAddressRequest {

    private String sessiontoken;        //会话令牌

    @NotNull(message = "收货人姓名不能为空")
    private String receivingName;       //收货人姓名

    @NotNull(message = "收货人手机号不能为空")
    private String receivingPhone;      //收货人手机

    @NotNull(message = "收货城市不能为空")
    private String receivingCity;       //收货城市

    @NotNull(message = "收货地址不能为空")
    private String receivingAddress;    //收货地址

    @NotNull(message = "订单编号不能为空")
    private String orderId;               //订单id


    public BillFreshActivity convertToBillFreshActivity(){
        BillFreshActivity activity = new BillFreshActivity();
        BeanUtils.copyProperties(this,activity);
        activity.setId(orderId);
        return activity;
    }


    @Override
    public String toString() {
        return "OrderConfirmAddressRequest{" +
            "sessiontoken='" + sessiontoken + '\'' +
            ", receivingName='" + receivingName + '\'' +
            ", receivingPhone='" + receivingPhone + '\'' +
            ", receivingCity='" + receivingCity + '\'' +
            ", receivingAddress='" + receivingAddress + '\'' +
            ", orderId=" + orderId +
            '}';
    }

    public String getSessiontoken() {
        return sessiontoken;
    }

    public void setSessiontoken(String sessiontoken) {
        this.sessiontoken = sessiontoken;
    }

    public String getReceivingName() {
        return receivingName;
    }

    public void setReceivingName(String receivingName) {
        this.receivingName = receivingName;
    }

    public String getReceivingPhone() {
        return receivingPhone;
    }

    public void setReceivingPhone(String receivingPhone) {
        this.receivingPhone = receivingPhone;
    }

    public String getReceivingCity() {
        return receivingCity;
    }

    public void setReceivingCity(String receivingCity) {
        this.receivingCity = receivingCity;
    }

    public String getReceivingAddress() {
        return receivingAddress;
    }

    public void setReceivingAddress(String receivingAddress) {
        this.receivingAddress = receivingAddress;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
