package com.xmn.saas.controller.api.v1.wallet.vo;

import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.NotNull;

import com.xmn.saas.base.Request;
import com.xmn.saas.entity.common.SellerAccount;

/**
 * 提现申请列表类
 * 
 * @author zhouxiaojian
 *
 */
public class WithdrawalListRequest extends Request {


    /**
     * 
     */
    private static final long serialVersionUID = 6722465461405154588L;

    @NotNull( message = "单页返回的记录条数 不能为空" )
    private Integer count;

    @NotNull( message = "返回结果的页码不能为空" )
    private Integer page;

    private Integer type;
    
    private Integer withdrawKind;
    
    


    public Integer getWithdrawKind() {
        return withdrawKind;
    }

    public void setWithdrawKind(Integer withdrawKind) {
        this.withdrawKind = withdrawKind;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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



    @Override
    public String toString() {
        return "WithdrawalListRequest [count=" + count + ", page=" + page + ", type=" + type + "]";
    }

    public Map<String, String> converToBean(SellerAccount sellerAccount) {
        Map<String, String> map = new HashMap<>();
        map.put("uId", sellerAccount.getSellerid() + "");
        map.put("userType", "2");
        if (this.getType() == null) {
            map.put("status", "0,1,3");
        } else if (this.getType() == 1) {
            map.put("status", "1");
        } else if (this.getType() == 3) {
            map.put("status", "0,3");
        }
        if(this.getWithdrawKind()!=null){
            map.put("withdrawKind", this.getWithdrawKind()+"");
        }
        map.put("cPage", this.getPage() + "");
        map.put("pageSize", this.getCount() + "");
        return map;
    }



}
