package com.xmn.saas.controller.api.v1.bill.vo;

import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.NotNull;

import com.xmn.saas.base.Request;
import com.xmn.saas.entity.common.SellerAccount;

public class BillDetailRequest extends Request {

    /**
     * 
     */
    private static final long serialVersionUID = -3827526943758741148L;

    /**
     * 账单详情类
     * 
     * @author zhouxiaojian
     */

    @NotNull( message = "账单类型不能为空！" )
    private Integer searchType;

    @NotNull( message = "订单号不能为空！" )
    private String bid;


    public Integer getSearchType() {
        return searchType;
    }



    public void setSearchType(Integer searchType) {
        this.searchType = searchType;
    }



    public String getBid() {
        return bid;
    }



    public void setBid(String bid) {
        this.bid = bid;
    }




    @Override
    public String toString() {
        return "BillDetailRequest [searchType=" + searchType + ", bid=" + bid + "]";
    }



    public Map<String, String> converToBean(SellerAccount sellerAccount, BillDetailRequest request) {
        // 请求参数
        Map<String, String> params = new HashMap<>();
        params.put("bid", request.getBid() + "");
        params.put("searchType", request.getSearchType()+ "");
        params.put("sellerid", sellerAccount.getSellerid() + "");
        
        return params;
    }



}
