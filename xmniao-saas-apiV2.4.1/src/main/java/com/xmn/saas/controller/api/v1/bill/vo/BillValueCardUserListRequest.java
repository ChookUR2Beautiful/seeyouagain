package com.xmn.saas.controller.api.v1.bill.vo;

import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.NotNull;

import com.xmn.saas.base.Request;
import com.xmn.saas.entity.common.SellerAccount;

public class BillValueCardUserListRequest extends Request {


    /**
     * 
     */
    private static final long serialVersionUID = -1052322405213867154L;

    /**
     * 商户储值卡用户列表类
     * 
     * @author zhouxiaojian
     */


    @NotNull( message = "记录条数不能为空！" )
    private Integer pageSize;

    @NotNull( message = "页数不能为空！" )
    private Integer page;



    public Integer getPageSize() {
        return pageSize;
    }



    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }



    public Integer getPage() {
        return page;
    }



    public void setPage(Integer page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return "BillValueCardUserListRequest [pageSize=" + pageSize + ", page=" + page + "]";
    }

    public Map<String, String> converToBean(SellerAccount sellerAccount,
            BillValueCardUserListRequest request) {
        // 请求参数
        Map<String, String> params = new HashMap<>();
        params.put("sellerid", sellerAccount.getSellerid() + "");
        // 分页
        params.put("pageSize", pageSize + "");
        params.put("page", page + "");
        return params;
    }



}
