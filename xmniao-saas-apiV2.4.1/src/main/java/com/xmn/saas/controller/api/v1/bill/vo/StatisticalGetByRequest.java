package com.xmn.saas.controller.api.v1.bill.vo;

import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.NotNull;

import com.xmn.saas.base.Request;
import com.xmn.saas.entity.common.SellerAccount;

public class StatisticalGetByRequest extends Request {

    /**
     * 账单类
     * 
     * @author zhouxiaojian
     */
    private static final long serialVersionUID = -1962911603144737737L;

    @NotNull( message = "开始日期不能为空！" )
    private String sdate;

    @NotNull( message = "结束日期不能为空！" )
    private String edate;



    public String getSdate() {
        return sdate;
    }



    public void setSdate(String sdate) {
        this.sdate = sdate;
    }



    public String getEdate() {
        return edate;
    }



    public void setEdate(String edate) {
        this.edate = edate;
    }

    public Map<String, String> converToBean(SellerAccount sellerAccount,StatisticalGetByRequest request ){
        // 请求参数
        Map<String, String> params = new HashMap<>();
        params.put("uId", sellerAccount.getSellerid() + "");
        //params.put("userType", sellerAccount.getType() + "");
        params.put("userType",  "2");//商户类型
        params.put("sdate", request.getSdate());
        params.put("edate", request.getEdate()); 
        return params;
    }
    


}
