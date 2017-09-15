package com.xmniao.xmn.core.market.controller.activity.vo;

import java.io.Serializable;

import net.sf.oval.constraint.NotNull;

import com.xmniao.xmn.core.base.BaseRequest;
public class AuctionBidListRequest extends BaseRequest implements Serializable {


    /**
     * 
     */
    private static final long serialVersionUID = -8278492999903283939L;
    @NotNull( message = "活动id不能为空！" )
    private Integer  activityId;

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }


    @Override
    public String toString() {
        return "AuctionBidListRequest [activityId=" + activityId + "]";
    }
    
    

}
