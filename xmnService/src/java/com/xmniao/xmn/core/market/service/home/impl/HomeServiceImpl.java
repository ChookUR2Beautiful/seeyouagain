package com.xmniao.xmn.core.market.service.home.impl;

import com.xmniao.xmn.core.base.RedisService;
import com.xmniao.xmn.core.market.base.Page;
import com.xmniao.xmn.core.market.dao.*;
import com.xmniao.xmn.core.market.entity.home.*;
import com.xmniao.xmn.core.market.entity.pay.FreshActivityCommon;
import com.xmniao.xmn.core.market.entity.pay.ProductInfo;
import com.xmniao.xmn.core.market.exception.CustomException;
import com.xmniao.xmn.core.market.service.home.HomeConstant;
import com.xmniao.xmn.core.market.service.home.HomeService;
import com.xmniao.xmn.core.market.service.product.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by yang qiang on 2016/12/23.
 */
@Service
public class HomeServiceImpl implements HomeService{

    @Autowired
    private FreshTypeDao freshTypeDao;
    @Autowired
    private FreshImageDao freshImageDao;
    @Autowired
    private FreshBrandDao freshBrandDao;
    @Autowired
    private FreshHotBrandDao freshHotBrandDao;
    @Autowired
    private FreshActivityCommonDao freshActivityCommonDao;
    @Autowired
    private ProductInfoDao productInfoDao;
    @Autowired
    private FreshModuleDao freshModuleDao;
    @Autowired
    private FreshActivityProductDao freshActivityProductDao;
    @Autowired
    private FreshActivityGroupDao freshActivityGroupDao;
    @Autowired
    private SaleGroupDao saleGroupDao;
    @Autowired
    private String fileUrl;
    @Autowired
    private RedisService redisService;
    @Autowired
    private ProductService productService;
    @Autowired
    private HomeAccess homeAccess;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());




    /**
     * 查询商品分类列表
     * @param typeId
     * @return
     */
    @Override
    public List<FreshType> getProductTypeList(Integer typeId) {
        List<FreshType> typeList = freshTypeDao.selectByType(typeId);
        return typeList;
    }



    /**
     * 获取热门品牌列表
     * @param typeId
     * @return
     */
    @Override
    public List<FreshHotBrand> queryHostBrandList(Integer typeId) {
        List<FreshHotBrand> brandList = freshHotBrandDao.selectByTypeId(typeId);
        for (FreshHotBrand freshHotBrand : brandList) {
            freshHotBrand.setImageUrl(fileUrl+freshHotBrand.getImageUrl());
        }
        return brandList;
    }

    /**
     * 获取热卖专场活动列表
     * @return
     */
    @Override
    public List<FreshModule> queryHotSaleActivityList() {
        List<FreshModule> moduleList = freshModuleDao.selectActivityModule();

        Iterator<FreshModule> iterator = moduleList.iterator();
        while (iterator.hasNext()){
            FreshModule freshModule = iterator.next();

            if (
                freshModule.getActivityId() != null
                && !activityAlive(freshModule.getActivityId().intValue())) {
                logger.info("模块("+freshModule.getModuleName()+"["+freshModule.getId()+"]"+")活动["+freshModule.getActivityId()+"] 已失效!");
                iterator.remove();
                continue;
            }

            freshModule.setImageUrl(fileUrl+freshModule.getImageUrl());

        }
        return moduleList;
    }

    /**
     * 获取热卖专场商品列表
     * @param page
     * @param moduleId
     * @param pageStart
     *@param pageSize @return
     */
    public List<ProductInfo> queryActivityProductList(Long moduleId, Page page) {
        FreshModule freshModule = freshModuleDao.selectByPrimaryKey(moduleId);
        List<ProductInfo> productList = new ArrayList<>();

        if (freshModule == null) {
            return productList;
        }

        switch (freshModule.getActivityProductType()){
            case HomeConstant.FRESH_MODULE_ACTIVITY_PRODUCT_TYPE_ACTIVITY:
                // 01显示活动商品
                productList = productInfoDao.selectActivityProductByActivityId(freshModule.getActivityId(),page);

                break;
            case HomeConstant.FRESH_MODULE_ACTIVITY_PRODUCT_TYPE_MANUAL:
                // 02显示手动添加的专场商品
                productList = productInfoDao.selectManualActivityProduct(freshModule,page);
                break;
            default:
                logger.error("FreshModule(id="+freshModule.getId()+")的activity_product_type不能为:"+freshModule.getActivityProductType());
                break;
        }


        Long activityId = freshModule.getActivityId();
        FreshActivityCommon activity = null ;
        if (activityId != null){
            activity = freshActivityCommonDao.selectByPrimaryKey(activityId.intValue());
        }
        // 遍历商品列表, 图片条件请求域名
        for (ProductInfo productInfo : productList) {
            productInfo.setBreviary(fileUrl+productInfo.getBreviary());
            productService.loadLabel(productInfo,activity);
        }
        return productList;
    }


    /**
     * 查询精选商品
     *
     * @param typeId
     * @param page
     * @param pageSize
     * @return
     */
    @Override
    public List<ProductInfo> querySelectedProduct(Long typeId, Page page) {
        List<ProductInfo> productList = productInfoDao.selectSelectedProduct(typeId, page);
        // 拼接图片地址头
        for (ProductInfo productInfo : productList) {
            productInfo.setBreviary(fileUrl+productInfo.getBreviary());
            productService.loadLabel(productInfo);

        }

        return productList;
    }


    /**
     * 查询热销商品
     * @param typeId
     * @param page
     * @return
     */
    @Override
    public List<ProductInfo> queryHotSaleProduct(Long typeId, Page page) {
        List<ProductInfo> productList = productInfoDao.selectHotSaleProduct(typeId,page);

        // 拼接图片地址头
        for (ProductInfo productInfo : productList) {
            productInfo.setBreviary(fileUrl + productInfo.getBreviary());
            productService.loadLabel(productInfo);
        }
        return productList;
    }

    /**
     * 查询 模块
     * @param moduleType
     * @param typeId
     * @return
     */
    @Override
    public FreshModule queryModule(Integer moduleType, Long typeId) {
        FreshModule freshModule = homeAccess.queryModule(moduleType,typeId);
        logger.info("查询模块"+freshModule.toString());
        // 如果商品为活动商品, 判断活动是否有效 activityAlive
        if (!homeAccess.isAliveModule(freshModule)) {
            logger.info("模块["+freshModule.getId()+"]无效!");
            throw new CustomException("模块"+freshModule.toString()+"无效, moduleType="+moduleType+" typeId="+typeId);
        }
        return freshModule;
    }

    /**
     * 查询模块列表
     * @param moduleType
     * @param typeId
     * @return
     */
    @Override
    public List<FreshModule> queryModuleList(Integer moduleType, Long typeId) {
        List<FreshModule> moduleList = freshModuleDao.selectModuleListByModelTypeAndTypeId(moduleType,typeId);
        return moduleList;
    }


    /**
     * 根据模块 查询商品列表
     * @param freshModule
     * @param page
     * @return
     */
    @Override
    public List<ProductInfo> queryProductList4Module(FreshModule freshModule, Page page) {
        ArrayList<ProductInfo> productList = new ArrayList<>();
        if (freshModule == null) {
            return productList;
        }

        page.setMaxSize(freshModule.getShowNum());

        switch (freshModule.getProductType()){

            case HomeConstant.FRESH_MODULE_PRODUCT_TYPE_HOT:

                // 查询热销商品列表
                productList.addAll(this.queryHotSaleProduct(freshModule.getTypeId(),page));
                break;

            case HomeConstant.FRESH_MODULE_PRODUCT_TYPE_COMMON:
                // 查询通用活动商品列表
                Long activityId = freshModule.getActivityId();
                productList.addAll(this.queryActivityProductList(freshModule.getId(),page));
                break;

            case HomeConstant.FRESH_MODULE_PRODUCT_TYPE_SELECTED:
                // 查询精选商品列表
                productList.addAll(this.querySelectedProduct(freshModule.getTypeId(),page));
                break;

            case HomeConstant.FRESH_MODULE_PRODUCT_TYPE_KILL:
                // 查询秒杀商品
                FreshActivityCommon currentSpikeActivity = this.queryCurrentSpikeActivity(freshModule.getActivityId());
                if (currentSpikeActivity != null) {

                    freshModule.setActivityId(currentSpikeActivity.getId().longValue());
                    freshModule.setEndTime(currentSpikeActivity.getEndDate());
                    freshModule.setDistEntTime(currentSpikeActivity.getEndDate().getTime()-System.currentTimeMillis());

                    long currentSessoinId = currentSpikeActivity.getId().longValue();
                    productList.addAll(productInfoDao.selectActivityProductByActivityId(currentSessoinId,page));
                    for (ProductInfo productInfo : productList) {
                        productInfo.setBreviary(fileUrl + productInfo.getBreviary());
                        productService.loadLabel(productInfo,currentSpikeActivity);
                    }
                }else {
                    logger.info("模块["+freshModule.getId()+"]没有对应的当前秒杀场次");
                    throw new CustomException("模块["+freshModule.getId()+"]没有对应的当前秒杀场次");
                }
                break;
            default:
                logger.info("模块 商品类型错误, 不能为 : "+freshModule.getProductType());
                throw new CustomException("查询失败");
        }
        return productList;
    }

    /**
     * 查询当前正在进行的秒杀场次
     * @param activityId
     * @return
     */
    private FreshActivityCommon queryCurrentSpikeActivity(Long spikeId) {
        return freshActivityCommonDao.selectCurrentSpikeActivityBySpikeId(spikeId);
    }

    /**
     * 根据品牌id 查询品牌详情
     * @param brandId
     * @return
     */
    @Override
    public FreshBrand queryBrandInfo(Long brandId) {
        FreshBrand freshBrand = freshBrandDao.selectByPrimaryKey(brandId.intValue());
        if(freshBrand != null){
            freshBrand.setLogo(fileUrl + freshBrand.getLogo());
        }
        return freshBrand;
    }

    /**
     * 加载库存
     * @param productInfo
     * @param activityId
     */
    @Override
    public void loadStore(ProductInfo productInfo, Long activityId){
        productInfo.setStore(freshActivityGroupDao.sumProductSotreByFreshModule(productInfo.getCodeid(),activityId));
    }

    @Override
    public void loadStore(ProductInfo productInfo){
        productInfo.setStore(saleGroupDao.sumProductStoreByCodeId(productInfo.getCodeid()));
    }


    /** 判断活动是否有效
     *  begin_date  end_date status
     * */
    @Override
    public boolean activityAlive(Integer activityId){
        if (activityId == null) return false;

        FreshActivityCommon activity = freshActivityCommonDao.selectByPrimaryKey(activityId);
        if (activity.getStatus() != 0) return false;
        if (activity.getEndDate().compareTo(new Date()) < 0) return false;
        if (activity.getBeginDate().compareTo(new Date()) > 0) return false;

        return true;
    }

    /**
     * 查询积分超市图片(FreshImage)
     * @param freshProductType      积分超市商品分类 0:首页
     * @param ImageType             积分超市图片类型
     * @return
     */
    @Override
    public List<FreshImage> getFreshImages(Integer freshProductType, Integer ImageType) {
        List<FreshImage> freshImages = freshImageDao.selectByType(freshProductType,ImageType);
        for (FreshImage freshImage : freshImages) {
            freshImage.setImageUrl(fileUrl + freshImage.getImageUrl());
        }
        return freshImages;
    }


}
