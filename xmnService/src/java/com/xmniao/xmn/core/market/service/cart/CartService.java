package com.xmniao.xmn.core.market.service.cart;

import java.util.Map;

import com.xmniao.xmn.core.market.controller.cart.vo.CartAddRequest;
import com.xmniao.xmn.core.market.controller.cart.vo.CartDeleteRequest;
import com.xmniao.xmn.core.market.controller.cart.vo.CartEditAttrRequest;
import com.xmniao.xmn.core.market.controller.cart.vo.CartListRequest;


/**
 * 购物车服务类
 * @author zhouxiaojian
 * @Date 2016/12/22
 */
public interface CartService {
    /**
     * 添加商品到购物车
     * @param cartAddRequest
     */
    public Map<Object, Object> add(CartAddRequest cartAddRequest);
    
    /**
     * 修改购物车的商品规格
     * @param cartAddRequest
     * @return
     */
    public Map<Object, Object> editAttr(CartEditAttrRequest cartAddRequest);
    
    /**
     * 购物车删除商品
     * @param cartAddRequest
     */
    public Map<Object, Object> delete(CartDeleteRequest cartDeleteRequest);
    
    /**
     * 购物车商品列表
     * @param cartAddRequest
     */
    public Map<Object, Object> list(CartListRequest cartDeleteRequest);
    
    

}
