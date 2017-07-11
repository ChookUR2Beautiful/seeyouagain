package com.xmn.saas.controller.api.v1.bill.vo;

import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.NotNull;

import com.xmn.saas.base.Request;
import com.xmn.saas.entity.common.SellerAccount;

public class BillValueCardListRequest extends Request {

    /**
     * 
     */
    private static final long serialVersionUID = 2641528989113167076L;

    /**
     * 商户储值卡充值消费列表类
     * 
     * @author zhouxiaojian
     */

    @NotNull( message = "开始日期不能为空！" )
    private String sdate;

    @NotNull( message = "结束日期不能为空！" )
    private String edate;

    @NotNull( message = "搜索类型不能为空！" )
    private Integer rtype;// 1 充值 2 消费

    @NotNull( message = "记录条数不能为空！" )
    private Integer pageSize;

    @NotNull( message = "页数不能为空！" )
    private Integer page;



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



    public Integer getRtype() {
        return rtype;
    }



    public void setRtype(Integer rtype) {
        this.rtype = rtype;
    }



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
        return "BillValueCardListRequest [sdate=" + sdate + ", edate=" + edate + ", rtype=" + rtype
                + ", pageSize=" + pageSize + ", page=" + page + "]";
    }



    public Map<String, String> converToBean(SellerAccount sellerAccount,
            BillValueCardListRequest request) {
        // 请求参数
        Map<String, String> params = new HashMap<>();
        params.put("sellerid", sellerAccount.getSellerid() + "");
        params.put("rtype", rtype + "");
        params.put("sdate", request.getSdate());
        params.put("edate", request.getEdate());
        // 分页
        params.put("pageSize", pageSize + "");
        params.put("page", page + "");
        return params;
    }



}
