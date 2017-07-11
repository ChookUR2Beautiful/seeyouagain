package com.xmn.saas.entity.sellercard;

import java.util.Date;

/**
 * 
* @projectName: xmnService 
* @ClassName: DebitcardSeller    
* @Description:充值卡实体   
* @author: liuzhihao   
* @date: 2017年2月23日 上午11:32:58
 */
public class DebitcardSeller {
    private Integer id;

    private Integer sellerid;

    private Integer sellertype;

    private String sellername;

    private Integer status;

    private String subSellerid;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSellerid() {
        return sellerid;
    }

    public void setSellerid(Integer sellerid) {
        this.sellerid = sellerid;
    }

    public Integer getSellertype() {
        return sellertype;
    }

    public void setSellertype(Integer sellertype) {
        this.sellertype = sellertype;
    }

    public String getSellername() {
        return sellername;
    }

    public void setSellername(String sellername) {
        this.sellername = sellername == null ? null : sellername.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSubSellerid() {
        return subSellerid;
    }

    public void setSubSellerid(String subSellerid) {
        this.subSellerid = subSellerid == null ? null : subSellerid.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}