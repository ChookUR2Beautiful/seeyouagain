package com.xmn.saas.controller.api.v1.micro.vo;

import java.math.BigDecimal;

import com.xmn.saas.base.Request;
import com.xmn.saas.entity.common.SellerAccount;
import com.xmn.saas.entity.micro.MicroBill;

import javax.validation.constraints.NotNull;

/**
 * 扫码支付请求类
 * 
 * @author zhouxiaojian
 *
 */
public class MicroPayRequest extends Request {

    /**
     * 
     */
    private static final long serialVersionUID = 4395243674811735108L;

    @NotNull( message = "订单名称不能为空" )
    private String orderName;

    @NotNull( message = "支付类型不能为空" )
    private String paymentType;
    
    @NotNull( message = "支付总金额不能为空" )
    private Double totalAmount;

    @NotNull( message = "付款码不能为空" )
    private String authCode;
    
    @NotNull( message = "订单来源不能为空" )
    private String source;
    
    @NotNull( message = "客户类型不能为空" )
    private String clientType;
    
    @NotNull( message = "版本不能为空" )
    private String appVersion;

    

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }
    

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }



    @Override
    public String toString() {
        return "MicroPayRequest [orderName=" + orderName + ", paymentType=" + paymentType
                + ", totalAmount=" + totalAmount + ", authCode=" + authCode + ", source=" + source
                + ", clientType=" + clientType + ", appVersion=" + appVersion + "]";
    }

    public MicroBill converToBean(SellerAccount sellerAccount){
        MicroBill microBill =new MicroBill();
        microBill.setAuthCode(this.getAuthCode());
        microBill.setTotalAmount(BigDecimal.valueOf(this.getTotalAmount()));
        microBill.setPayType(Integer.valueOf(this.getPaymentType()));
        microBill.setSellerid(sellerAccount.getSellerid());
        microBill.setSource(this.getSource());
        microBill.setClientType(this.getClientType());
        microBill.setAppVersion(this.getAppVersion());
        microBill.setOrderName(this.getOrderName());
        return microBill;
    }

    
    

}
