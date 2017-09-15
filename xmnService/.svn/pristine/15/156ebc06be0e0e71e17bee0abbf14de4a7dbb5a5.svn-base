package com.xmniao.xmn.core.market.controller.activity.vo;

import java.io.Serializable;

import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;
import com.xmniao.xmn.core.market.dao.FreshActivityAuctionDao;
import com.xmniao.xmn.core.market.entity.activity.FreshActivityAuction;
public class AuctionListRequest extends BaseRequest implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -5364212197389934261L;



    @NotNull( message = "页数不能为空！" )
    private Integer page;
    
    @NotNull(message = "每页大小不能为空")
    private Integer pageSize;



    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }
    
    
    
    
    
    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "AuctionListRequest [ page=" + page + ", pageSize=" + pageSize
                + "]";
    }

    public FreshActivityAuction converToBean(){
        FreshActivityAuction freshActivityAuction =new FreshActivityAuction();
        freshActivityAuction.setPageOffset((this.page - 1) * this.pageSize);
        freshActivityAuction.setPageSize(this.pageSize);
        return freshActivityAuction;
    }
    
    
    
    
    
    

}
