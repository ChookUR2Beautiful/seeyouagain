package com.xmniao.xmn.core.market.service.product;

import com.xmniao.xmn.core.market.base.Page;
import com.xmniao.xmn.core.market.controller.product.vo.ProductAttrRequest;
import com.xmniao.xmn.core.market.controller.product.vo.ProductCollectRequest;
import com.xmniao.xmn.core.market.entity.pay.FreshActivityCommon;
import com.xmniao.xmn.core.market.entity.pay.ProductInfo;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Joney.Yang on 2016/12/28.
 */
public interface ProductService {

    public Map<Object, Object> attr(ProductAttrRequest productAttrRequest);

    public Map<String,Integer> collect(ProductCollectRequest request);


    public ProductInfo detail(Integer activityId,Long codeId,String sessiontoken);
    
    public Map<Object, Object> collectList(String sessiontoken);
    
    public List<ProductInfo> historyList(String sessiontoken);

    public String text() throws IOException;

    Long countProductByCondition(Long typeId, Long brandId, Double maxMoney, Double minMoney);

    List<ProductInfo> queryProductByCondition(Long typeId, Long brandId, Double maxMoney, Double minMoney, Page page);
    void loadLabel(ProductInfo productInfo);
    void loadLabel(ProductInfo productInfo,FreshActivityCommon activity);
    void loadLabel(ProductInfo productInfo, HashMap<Integer,FreshActivityCommon> activity);
}
