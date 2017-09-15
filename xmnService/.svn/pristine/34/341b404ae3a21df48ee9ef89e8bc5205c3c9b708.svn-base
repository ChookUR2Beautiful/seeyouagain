package com.xmniao.xmn.core.market.controller.activity.vo;

import java.io.Serializable;

import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;
import com.xmniao.xmn.core.market.entity.activity.BillFreshActivity;
public class AuctionPayRequest extends BaseRequest implements Serializable {


    /**
     * 
     */
    private static final long serialVersionUID = -2833839227146323564L;
    @NotNull( message = "订单号不能为空！" )
    private String  orderNo;
    
   

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    @Override
    public String toString() {
        return "AuctionPayRequest [orderNo=" + orderNo + "]";
    }

    public BillFreshActivity converToBean(Integer uid){
        BillFreshActivity vo =new BillFreshActivity();
        vo.setId(this.getOrderNo());//订单号
        vo.setUserId(uid.toString());
        return vo;
    }
    
    
    
    
    
    

}
