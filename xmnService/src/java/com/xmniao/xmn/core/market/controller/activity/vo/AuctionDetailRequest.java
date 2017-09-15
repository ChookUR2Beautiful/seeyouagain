package com.xmniao.xmn.core.market.controller.activity.vo;

import java.io.Serializable;

import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;
import com.xmniao.xmn.core.market.dao.FreshActivityAuctionDao;
import com.xmniao.xmn.core.market.entity.activity.FreshActivityAuction;
public class AuctionDetailRequest extends BaseRequest implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -8823252366966584557L;
    @NotNull( message = "id不能为空！" )
    private Integer id;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    
    
    
    
    
    

}
