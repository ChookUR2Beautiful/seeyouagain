package com.xmn.saas.controller.api.v1.bill.vo;

import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.NotNull;

import com.xmn.saas.base.Request;
import com.xmn.saas.entity.common.SellerAccount;

public class BillListRequest extends Request {

    /**
     * 账单流水列表类
     * 
     * @author zhouxiaojian
     */
    private static final long serialVersionUID = -1962911603144737737L;

    @NotNull( message = "开始日期不能为空！" )
    private String sdate;

    @NotNull( message = "结束日期不能为空！" )
    private String edate;

    @NotNull( message = "搜索类型不能为空！" )
    private Integer searchType;

    @NotNull( message = "记录条数不能为空！" )
    private Integer count;

    @NotNull( message = "页数不能为空！" )
    private Integer page;



    public Integer getSearchType() {
        return searchType;
    }



    public void setSearchType(Integer searchType) {
        this.searchType = searchType;
    }



    public Integer getCount() {
        return count;
    }



    public void setCount(Integer count) {
        this.count = count;
    }



    public Integer getPage() {
        return page;
    }



    public void setPage(Integer page) {
        this.page = page;
    }



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



    @Override
    public String toString() {
        return "BillListRequest [sdate=" + sdate + ", edate=" + edate + ", searchType="
                + searchType + ", count=" + count + ", page=" + page + "]";
    }



    public Map<String, String> converToBean(SellerAccount sellerAccount, BillListRequest request) {
        // 请求参数
        Map<String, String> params = new HashMap<>();
        params.put("uId", sellerAccount.getSellerid() + "");
        params.put("type", sellerAccount.getType() + "");
        params.put("userType",  "2");
        params.put("searchType", request.getSearchType() + "");
        params.put("sdate", request.getSdate());
        params.put("edate", request.getEdate());
        //分页        
        params.put("pageOffset", (request.getPage()-1)*request.getCount() + "");
        params.put("pageSize", request.getCount() + "");
        params.put("cPage", request.getPage()+"");
        return params;
    }



}
