package com.xmniao.xmn.core.market.controller.cart.vo;

import java.io.Serializable;

import com.xmniao.xmn.core.base.BaseRequest;

import net.sf.oval.constraint.NotNull;
public class CartAddRequest extends BaseRequest implements Serializable {

	/**
     * 
     */
    private static final long serialVersionUID = 2247858432846233563L;

    
    private String attrIds;  
    
    private String attrVals;
    
    
    @NotNull(message="数量不能为空！")
    private Integer num;
    
    private Integer codeId ;
    
    //活动id
    private Integer activityId;
    //购物记录id
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

    public Integer getCodeId() {
        return codeId;
    }

    public void setCodeId(Integer codeId) {
        this.codeId = codeId;
    }

    @Override
    public String toString() {
        return "CartAddRequest [attrIds=" + attrIds + ", attrVals=" + attrVals + ", num=" + num
                + ", codeId=" + codeId + ", activityId=" + activityId + ", cartId=" + cartId + "]";
    }

  
    
    
    
    
    
    

}
