package com.xmniao.xmn.core.market.controller.activity.vo;

import java.io.Serializable;

import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;
import com.xmniao.xmn.core.market.dao.FreshActivityAuctionDao;
import com.xmniao.xmn.core.market.entity.activity.FreshActivityAuction;
public class AuctionTextRequest extends BaseRequest implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -5364212197389934261L;



    @NotNull( message = "规则类型不能为空！" )
    private Integer type;



    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
    


    
    
    
    
    

}
