package com.xmniao.xmn.core.market.service.home;

import com.xmniao.xmn.core.market.base.Page;
import com.xmniao.xmn.core.market.entity.home.*;
import com.xmniao.xmn.core.market.entity.pay.ProductInfo;

import java.util.List;

/**
 * Created by yang qiang on 2016/12/23.
 */
public interface HomeService {

    List<FreshType> getProductTypeList(Integer typeId);


    List<FreshHotBrand> queryHostBrandList(Integer typeId);

    List<FreshModule> queryHotSaleActivityList();

    List<ProductInfo> queryActivityProductList(Long moduleId, Page page);

//    Long countActivityProduct(Long moduleId);

    List<ProductInfo> querySelectedProduct(Long typeId, Page page);

//    Long countSelectedProduct(Long typeId);

    List<ProductInfo> queryHotSaleProduct(Long typeId, Page page);

    FreshModule queryModule(Integer moduleType, Long typeId);

    List<FreshModule> queryModuleList(Integer moduleType, Long typeId);

    List<ProductInfo> queryProductList4Module(FreshModule freshModule, Page page);

    FreshBrand queryBrandInfo(Long brandId);

//    Long countProduct4Module(FreshModule freshModule);

    @Deprecated
    void loadStore(ProductInfo productInfo, Long activityId);

    @Deprecated
    void loadStore(ProductInfo productInfo);

    boolean activityAlive(Integer activityId);


    List<FreshImage> getFreshImages(Integer freshProductType, Integer ImageType);
}
