package com.xmniao.xmn.core.market.controller.cart.vo;

import java.io.Serializable;

import com.xmniao.xmn.core.base.BaseRequest;

import net.sf.oval.constraint.NotNull;
public class CartDeleteRequest extends BaseRequest implements Serializable {

	/**
     * 
     */
    private static final long serialVersionUID = 2247858432846233563L;

    
    
    @NotNull(message="数量不能为空！")
    private Integer num;
    
    @NotNull(message="商品类型不能为空！")
    private Integer type;
    
    
    @NotNull(message="购物记录id不能为空！")
    private String cartId ;
    
    


    public Integer getNum() {
        return num;
    }


    public void setNum(Integer num) {
        this.num = num;
    }


    public String getCartId() {
        return cartId;
    }


    public void setCartId(String cartId) {
        this.cartId = cartId;
    }
    
    


    public Integer getType() {
        return type;
    }


    public void setType(Integer type) {
        this.type = type;
    }


    @Override
    public String toString() {
        return "CartDeleteRequest [num=" + num + ", type=" + type + ", cartId=" + cartId + "]";
    }
    
    

    
    
    
    
    
    

}
