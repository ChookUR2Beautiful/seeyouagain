package com.xmn.saas.controller.api.v1.wallet.vo;

import javax.validation.constraints.NotNull;

import org.springframework.beans.BeanUtils;

import com.xmn.saas.base.Request;
import com.xmn.saas.entity.wallet.BankApply;
import com.xmn.saas.entity.wallet.Orderinvoice;

public class InvoiceApplyRequest extends Request {

    /**
     * 申请发票类
     * 
     * @author zhouxiaojian
     */
    private static final long serialVersionUID = -1962911603144737737L;

    @NotNull( message = "开票金额不能为空！" )
    private double amount;

    @NotNull( message = "税务登记号不能为空！" )
    private String taxId;

    @NotNull( message = "发票抬头不能为空" )
    private String invoiceTitle;

    private String note;

    @NotNull( message = "发票地址不能为空" )
    private String address;
    
    private Integer sellerId;
    
    

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public String getInvoiceTitle() {
        return invoiceTitle;
    }

    public void setInvoiceTitle(String invoiceTitle) {
        this.invoiceTitle = invoiceTitle;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "InvoiceApplyRequest [amount=" + amount + ", taxId=" + taxId + ", invoiceTitle="
                + invoiceTitle + ", note=" + note + ", address=" + address + "]";
    }
    
    
    public Orderinvoice converToBean (InvoiceApplyRequest request){
        Orderinvoice orderinvoice =new Orderinvoice();
        orderinvoice.setInvoice(request.getInvoiceTitle());//发票抬头
        orderinvoice.setNote(request.getNote());//发票内容
        orderinvoice.setApplyAmount(request.getAmount());//申请金额
        orderinvoice.setTaxid(request.getTaxId());//税务登记号
        orderinvoice.setSellerAddress(request.getAddress());
        return orderinvoice;
        
    }


}
