package com.xmniao.xmn.core.market.controller.activity.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;
import com.xmniao.xmn.core.market.entity.activity.FreshActivityAuction;
import com.xmniao.xmn.core.market.entity.activity.FreshActivityAuctionBidding;
public class AuctionBidRequest extends BaseRequest implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 5328278694894310532L;

    @NotNull( message = "活动id不能为空！" )
    private Integer  activityId;
    
    @NotNull( message = "加价次数不能为空！" )
    private Integer riseNum;
    
    @NotNull(message = "我的出价不能为空")
    private BigDecimal risePrice;
    
    

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Integer getRiseNum() {
        return riseNum;
    }

    public void setRiseNum(Integer riseNum) {
        this.riseNum = riseNum;
    }

    public BigDecimal getRisePrice() {
        return risePrice;
    }

    public void setRisePrice(BigDecimal risePrice) {
        this.risePrice = risePrice;
    }


    @Override
    public String toString() {
        return "AuctionBidRequest [activityId=" + activityId + ", riseNum=" + riseNum
                + ", risePrice=" + risePrice + "]";
    }

    public FreshActivityAuctionBidding converToBean(Integer uid){
        FreshActivityAuctionBidding freshActivityAuctionBidding =new FreshActivityAuctionBidding();
        freshActivityAuctionBidding.setRiseNum(this.getRiseNum());
        freshActivityAuctionBidding.setRisePrice(this.getRisePrice());
        freshActivityAuctionBidding.setActivityId(this.getActivityId());
        freshActivityAuctionBidding.setUid(uid);
        return freshActivityAuctionBidding;
    }
    
    
    
    
    
    

}
