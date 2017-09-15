package com.xmniao.xmn.core.market.controller.activity.vo;

import java.io.Serializable;

import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;
import com.xmniao.xmn.core.market.dao.FreshActivityAuctionDao;
import com.xmniao.xmn.core.market.entity.activity.FreshActivityAuction;
import com.xmniao.xmn.core.market.entity.activity.FreshActivityAuctionRecord;
public class AuctionRecordRequest extends BaseRequest implements Serializable {

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

    public FreshActivityAuctionRecord converToBean(Integer uid){
        FreshActivityAuctionRecord freshActivityAuctionRecord =new FreshActivityAuctionRecord();
        freshActivityAuctionRecord.setPageOffset((this.page - 1) * this.pageSize);
        freshActivityAuctionRecord.setPageSize(this.pageSize);
        freshActivityAuctionRecord.setUid(uid);
        return freshActivityAuctionRecord;
    }
    
    
    
    
    
    

}
