package com.xmn.saas.controller.api.v1.micro.vo;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import com.xmn.saas.base.Request;
import com.xmn.saas.entity.common.SellerAccount;
import com.xmn.saas.entity.micro.MicroBill;

/**
 * 扫码支付请求类
 * 
 * @author zhouxiaojian
 *
 */
public class MicroCancelRequest extends Request {



    /**
     * 
     */
    private static final long serialVersionUID = -3422379025649133958L;

    @NotNull( message = "支付类型不能为空" )
    private String paymentType;

    @NotNull( message = "订单号不能为空" )
    private String orderNumber;


    @NotNull( message = "支付总金额不能为空" )
    private Double totalAmount;



    public String getPaymentType() {
        return paymentType;
    }



    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }



    public String getOrderNumber() {
        return orderNumber;
    }



    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }



    public Double getTotalAmount() {
        return totalAmount;
    }



    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }



    public MicroBill converToBean(SellerAccount sellerAccount) {
        MicroBill microBill = new MicroBill();
        microBill.setOrderNumber(this.getOrderNumber() + "");
        microBill.setPayType(Integer.valueOf(this.getPaymentType()));
        microBill.setTotalAmount(BigDecimal.valueOf(totalAmount));
        microBill.setSellerid(sellerAccount.getSellerid());
        return microBill;
    }



}
