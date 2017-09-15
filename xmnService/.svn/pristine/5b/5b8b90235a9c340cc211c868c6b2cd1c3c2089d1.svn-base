package com.xmniao.xmn.core.market.controller.cart.vo;

import java.io.Serializable;

import com.xmniao.xmn.core.base.BaseRequest;

import net.sf.oval.constraint.NotNull;

public class CartEditAttrRequest extends BaseRequest implements Serializable {



    /**
     * 
     */
    private static final long serialVersionUID = -8530419396272022204L;
    
    @NotNull( message = "规格id不能为空！" )
    private String attrIds;
    
    @NotNull( message = "规格值不能为空！" )
    private String attrVals;

    @NotNull(message="数量不能为空！")
    private Integer num;
    // 活动id
    private Integer activityId;
    // 购物记录id

    @NotNull( message = "购物记录id不能为空！" )
    private String cartId;



    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getAttrIds() {
        return attrIds;
    }

    public void setAttrIds(String attrIds) {
        this.attrIds = attrIds;
    }

    public String getAttrVals() {
        return attrVals;
    }

    public void setAttrVals(String attrVals) {
        this.attrVals = attrVals;
    }
    

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "CartEditAttrRequest [attrIds=" + attrIds + ", attrVals=" + attrVals + ", num="
                + num + ", activityId=" + activityId + ", cartId=" + cartId + "]";
    }



}
