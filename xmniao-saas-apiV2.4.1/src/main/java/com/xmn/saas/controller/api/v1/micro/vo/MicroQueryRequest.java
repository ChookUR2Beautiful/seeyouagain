package com.xmn.saas.controller.api.v1.micro.vo;

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
public class MicroQueryRequest extends Request {



    /**
     * 
     */
    private static final long serialVersionUID = 7349677447229559323L;

    @NotNull( message = "支付类型不能为空" )
    private String paymentType;

    @NotNull( message = "订单号不能为空" )
    private String orderNumber;



    @Override
    public String toString() {
        return "MicroQueryRequest [paymentType=" + paymentType + ", orderNumber=" + orderNumber
                + "]";
    }



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



    public MicroBill converToBean(SellerAccount sellerAccount) {
        MicroBill microBill = new MicroBill();
        microBill.setOrderNumber(this.getOrderNumber()+"");
        microBill.setPayType(Integer.valueOf(this.getPaymentType()));
        microBill.setSellerid(sellerAccount.getSellerid());
        return microBill;
    }



}
