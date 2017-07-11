package com.xmn.saas.controller.api.v1.bill.vo;

import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.NotNull;

import com.xmn.saas.base.Request;
import com.xmn.saas.entity.common.SellerAccount;

public class BillValueCardUserDetailRequest extends Request {

    /**
     * 
     */
    private static final long serialVersionUID = 6140170234333160118L;

    /**
     * 商户储值卡用户详情类
     * 
     * @author zhouxiaojian
     */
    @NotNull( message = "用户id不能为空！" )
    private String uid;
    
    

    @Override
    public String toString() {
        return "BillValueCardUserDetailRequest [uid=" + uid + "]";
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
    
    public Map<String,String> converToBean(SellerAccount sellerAccount){
        Map<String,String> params = new HashMap<>();
        params.put("sellerid", sellerAccount.getSellerid()+"");
        params.put("uid", this.getUid());
        
        return params;
        
    }



}
