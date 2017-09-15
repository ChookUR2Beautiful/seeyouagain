package com.xmniao.xmn.core.market.controller.home;

import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.market.base.Page;
import com.xmniao.xmn.core.market.common.Response;
import com.xmniao.xmn.core.market.entity.home.*;
import com.xmniao.xmn.core.market.entity.pay.ProductInfo;
import com.xmniao.xmn.core.market.exception.CustomException;
import com.xmniao.xmn.core.market.service.home.HomeConstant;
import com.xmniao.xmn.core.market.service.home.HomeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by yang qiang on 2016/12/21.
 *
 * 主页接口 Controller
 * 获取主页相关信息的接口
 */
@Controller("api-v1-market-home")
@RequestMapping("/api/v1/market/home")
public class HomeController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private HomeService homeService;

    /**
     * @name 查询分类分类及及子分类列表
     * @description 查询主分类以及子分类列表,按顺序返回
     * @url /api/v1/market/home/type_list
     * @method POST
     * @param typeId 分类id 主页为0 默认0
     */
    @RequestMapping(value = "type_list", method = RequestMethod.POST)
    public void typeList(@RequestParam(value = "typeId", defaultValue = "0") Integer typeId, HttpServletResponse httpResponse) throws IOException {

        logger.info("调用[首页分类接口 api/v1/market/home/type_list POST]接口参数, 参数: typeId=" + typeId);
        try {
            List<FreshType> typeList = homeService.getProductTypeList(typeId);
            HashMap<String, Object> resultMap = new HashMap<>();
            resultMap.put("typeList",typeList);
            new Response(ResponseCode.SUCCESS, "请求成功", resultMap).write(new HashMap<Class<?>, String[]>() {{
                put(FreshType.class, new String[]{
                    "id",   // type:int     分类id
                    "name"  // type:String  分类名
                });
            }}, httpResponse);
        } catch (CustomException e) {
            new Response(e.getCode(), e.getMessage()).write(httpResponse);
        } catch (Exception e) {
            logger.error("调用[首页分类接口 api/v1/market/home/type_list POST]]接口出现异常", e);
            new Response(ResponseCode.FAILURE, "请求失败").write(httpResponse);
        }
    }

    /**
     * @anme 获取首页及分类首页Banner图
     * @description 返回首页以及分类首页的Banner图列表
     * @url /api/v1/market/home/banner_list
     * @method POST
     * @param typeId 分类id 主页为0 默认0
     */
    @RequestMapping(value = "banner_list",method = RequestMethod.POST)
    public void bannerList(@RequestParam(value = "typeId",defaultValue = "0") Integer typeId,HttpServletResponse httpResponse) throws IOException {
        logger.info("调用[获取首页及分类首页Banner图 api/v1/market/home/banner_list POST]接口, 参数: typeId=" + typeId);
        try {
            List<FreshImage> bannerList = homeService.getFreshImages(typeId,HomeConstant.FRESH_IMAGE_TYPE_BANNER);
            HashMap<String, Object> resultMap = new HashMap<>();
            resultMap.put("bannerList",bannerList);
            new Response(ResponseCode.SUCCESS, "请求成功", resultMap).write(
                new HashMap<Class<?>, String[]>() {{put(FreshImage.class, new String[]{
                    "imageUrl",         // type:String  图标地址
                    "jumpProductId",    // type:int     跳转商品详情时所需的 商品id
                    "jumpType",         // type:int     跳转方式  01:商品详情   02:H5页面  03:不跳转(纯展示)  04 一元夺宝  05 竞拍页面  06 秒杀活动
                    "jumpUrl",          // type:String  跳转H5页面所需的 url地址
                    "title",            // type:String  标题
                    "jumpActivityId",   // 秒杀活动id
                });}},
                httpResponse);
        } catch (CustomException e) {
            new Response(e.getCode(), e.getMessage()).write(httpResponse);
        } catch (Exception e) {
            logger.error("调用[获取首页及分类首页Banner图 api/v1/market/home/banner_list POST]]接口出现异常", e);
            new Response(ResponseCode.FAILURE, "请求失败").write(httpResponse);
        }


    }

    /**
     * @name 首页图标列表获取接口
     * @description 获取首页图标列表
     * @url /api/v1/market/home/icon_list
     * @param sessiontoken 会话令牌
     * @method POST
     */
    @RequestMapping(value = "icon_list",method = RequestMethod.POST)
    public void iconList(HttpServletResponse httpResponse) throws IOException {

        logger.info("调用[首页图标列表获取接口 /api/v1/market/home/icon_list POST]接口");
        try {
            List<FreshImage> iconList = homeService.getFreshImages(HomeConstant.FRESH_IMAGE_TYPE_ID_HOME, HomeConstant.FRESH_IMAGE_TYPE_ICON);
            HashMap<String, Object> resultMap = new HashMap<>();
            resultMap.put("iconList",iconList);
            new Response(ResponseCode.SUCCESS, "请求成功", resultMap).write(
                        new HashMap<Class<?>, String[]>() {{put(FreshImage.class, new String[]{
                            "imageUrl",         //type:String  图标地址
                            "jumpProductId",    //type:int     跳转商品详情时所需的 商品id
                            "jumpType",         //type:int     跳转方式  01:商品详情   02:H5页面  03:不跳转(纯展示)  04 一元夺宝  05 竞拍页面  06 秒杀活动
                            "jumpUrl",          //type:String  跳转H5页面所需的 url地址
                            "title",            //type:String  标题
                            "jumpActivityId",   // 秒杀活动id
                        });}},
                httpResponse);

        } catch (CustomException e) {
            new Response(e.getCode(), e.getMessage()).write(httpResponse);
        } catch (Exception e) {
            logger.error("调用[首页图标列表获取接口 /api/v1/market/home/icon_list POST]]接口出现异常", e);
            new Response(ResponseCode.FAILURE, "请求失败").write(httpResponse);
        }


    }

    /**
     * @name 热门品牌获取列表接口
     * @description 获取热门品牌列表
     * @url /api/v1/market/home/hot_brand_list
     * @method POST
     * @param sessiontoken 会话令牌
     * @param typeId 分类id 默认0
     */
    @RequestMapping(value = "hot_brand_list",method = RequestMethod.POST)
    public void hotBrandList(@RequestParam(value = "typeId",defaultValue = "0") Integer typeId, HttpServletResponse httpResponse) throws IOException {

        logger.info("调用[热门品牌列表获取 /api/v1/market/home/hot_brand_list POST]接口, 参数: typeId=" + typeId);
        try {
            List<FreshHotBrand> brandList = homeService.queryHostBrandList(typeId);
            HashMap<String, Object> resultMap = new HashMap<>();
            resultMap.put("brandList",brandList);
            new Response(ResponseCode.SUCCESS, "请求成功", resultMap).write(
                        new HashMap<Class<?>, String[]>() {{put(FreshHotBrand.class, new String[]{
                            "brandId",      // type:int     品牌id
                            "imageUrl",     // type:String  品牌logo url
                            "title"         // type:String  标题
                        });}},
                httpResponse);
        } catch (CustomException e) {
            new Response(e.getCode(), e.getMessage()).write(httpResponse);
        } catch (Exception e) {
            logger.error("调用[热门品牌列表获取]接口出现异常", e);
            new Response(ResponseCode.FAILURE, "请求失败").write(httpResponse);
        }



    }

    /**
     * @name 热卖专场列表获取接口
     * @description 获取热卖专场列表
     * @url /api/v1/market/home/activity_list
     * @param sessiontoken 会话令牌
     * @method POST
     */
    @RequestMapping(value = "activity_list",method = RequestMethod.POST)
    public void activityList(HttpServletResponse httpResponse) throws IOException {

        logger.info("调用[热卖专场列表获取接口 /api/v1/market/home/activity_list POST]接口");
        try {
            // 查询专场列表
            List<FreshModule> activityList = homeService.queryHotSaleActivityList();

            // 如果专场商品为手动添加的商品, 则不返回activityId(活动id)
            for (FreshModule freshModule : activityList) {
                if (freshModule.getActivityProductType() == HomeConstant.FRESH_MODULE_ACTIVITY_PRODUCT_TYPE_MANUAL) {
                    freshModule.setActivityId(null);
                }
            }

            HashMap<String, Object> resultMap = new HashMap<>();
            resultMap.put("activityList",activityList);
            new Response(ResponseCode.SUCCESS, "请求成功", resultMap).write(
                        new HashMap<Class<?>, String[]>() {{
                            put(FreshModule.class, new String[]{
                                "id",           // type:int     专场id
                                "moduleName",   // type:String  专场名
                                "imageUrl",     // type:String  专场图片地址
                                "jumpUrl",      // type:String  跳转地址
                                "activityId"    // type:int     活动id
                            });
                        }},
                httpResponse);

        } catch (CustomException e) {
            new Response(e.getCode(), e.getMessage()).write(httpResponse);
        } catch (Exception e) {
            logger.error("调用[热卖专场列表获取接口]接口出现异常", e);
            new Response(ResponseCode.FAILURE, "请求失败").write(httpResponse);
        }
    }

    /**
     * @name 热卖专场商品列表获取接口
     * @url /api/v1/market/home/activity_product_list
     * @method POST
     * @param sessiontoken 会话令牌
     * @param moduleId    专场id
     * @param pageSize  分页页面大小
     * @param pageNum   分页页数
     */
    @RequestMapping(value = "activity_product_list",method = RequestMethod.POST)
    public void activityProductList(@RequestParam(value = "moduleId") Long moduleId, Page page, HttpServletResponse httpResponse) throws IOException {


        logger.info("调用[热卖专场商品列表获取接口 /api/v1/market/home/activity_product_list POST]接口, 参数: moduleId=" + moduleId);
        try {
            // 查询热卖专场的商品列表
            List<ProductInfo> productList = homeService.queryActivityProductList(moduleId,page);
            HashMap<String, Object> resultMap = new HashMap<>();
            resultMap.put("productList",productList);
            new Response(ResponseCode.SUCCESS, "请求成功", resultMap).write(
                        new HashMap<Class<?>, String[]>() {{put(ProductInfo.class, new String[]{
                            "pid",      // type:int  产品的id
                            "pname",    // type:String  产品的具体名称
                            "price",    // type:double  产品原单品价格
                            "breviary", // type:String  产品列表缩略图
                            "choice",   // type:int     是否精选商品  0不是  1是
                            "integral", // type:int     积分支付金额
                            "cash",     // type:double  现金支付金额
                            "codeid",   // type:int     产品唯一标识编号
                            "store",    // type:int     库存
                            "labels",       //商品标签
                        });}},
                httpResponse);

        } catch (CustomException e) {
            new Response(e.getCode(), e.getMessage()).write(httpResponse);
        } catch (Exception e) {
            logger.error("调用[热卖专场商品列表获取接口]接口出现异常", e);
            new Response(ResponseCode.FAILURE, "请求失败").write(httpResponse);
        }


    }


    /**
     * @name 商品模块接口
     * @description 获取商品模块相关信息(模块标题,模块商品列表) 包括主页(精选商品)  子分类首页(本区特惠, 本区热销)
     * @url /api/v1/market/home/module
     * @method POST
     * @param moduleType 01:商品模块(如本区特惠) 02:精选模块(如精选商品,本区热销)
     * @param typeId    分类id 主页传0,默认0, 子分类必须传
     * @param page      分页页数
     * @param pageSize  分页页面大小
     */
    @RequestMapping(value = "/module",method = RequestMethod.POST)
    public void module(@RequestParam("moduleType") Integer moduleType,
                       @RequestParam(value = "typeId", defaultValue = "0") Long typeId,
                       Page page,
                       HttpServletResponse httpResponse) throws IOException {

        logger.info("调用[商品模块接口 /api/v1/market/home/module POST]接口, 参数: moduleType=" + moduleType + "  typeId=" + typeId);
        try {
            HashMap<String, Object> resultMap = new HashMap<>();
            // 查询商品模块
            FreshModule freshModule = homeService.queryModule(moduleType, typeId);
            // 查询该商品模块的商品列表
            List<ProductInfo> productList = homeService.queryProductList4Module(freshModule, page);

            // 模块设置的不是活动商品, 不返回 活动id
            if (freshModule.getProductType() != HomeConstant.FRESH_MODULE_PRODUCT_TYPE_COMMON &&
                freshModule.getProductType() != HomeConstant.FRESH_MODULE_PRODUCT_TYPE_KILL) {

                logger.info("模块["+freshModule.getId()+"] 商品类型不是活动商品,不返回活动ID");
                freshModule.setActivityId(null);
            }

            resultMap.put("freshModule", freshModule);
            resultMap.put("productList", productList);
            new Response(ResponseCode.SUCCESS, "请求成功", resultMap).write(
                new HashMap<Class<?>, String[]>() {{
                    put(ProductInfo.class, new String[]{
                        "pid",      // type:int     产品的id
                        "pname",    // type:String  产品的具体名称
                        "price",    // type:double  产品原单品价格
                        "breviary", // type:String  产品列表缩略图
                        "choice",   // type:int     是否精选商品  0不是  1是
                        "integral", // type:int     积分支付金额
                        "cash",     // type:double  现金支付金额
                        "codeid",   // type:int     产品唯一标识编号
                        "store",    // type:int     库存
                        "labels",   // 商品标签id

                    });
                    put(FreshModule.class, new String[]{
                        "moduleName",   // type:String  模块名
                        "activityId",   // type:int 活动id
                        "endTime",      // 活动结束时间
                        "distEntTime",  // 距离结束时间
                    });
                }},
                "yyyy-MM-dd HH:mm:ss",
                httpResponse);

        } catch (CustomException e) {
            new Response(e.getCode(), e.getMessage()).write(httpResponse);
        } catch (Exception e) {
            logger.error("调用[商品模块接口]接口出现异常", e);
            new Response(ResponseCode.FAILURE, "请求失败").write(httpResponse);
        }


    }

    /**
     * @name 品牌详情接口
     * @description 获取品牌详情
     * @url /api/v1/market/home/brand_info
     * @method POST
     * @param brandId 品牌id
     */
    @RequestMapping(value = "/brand_info",method = RequestMethod.POST)
    public void brand_info(
        @RequestParam("brandId") Long brandId,
        HttpServletResponse httpResponse) throws IOException {


        logger.info("调用[品牌详情接口 /api/v1/market/home/brand_info POST]接口, 参数: brandId=" + brandId);
        try {
            FreshBrand freshBrand = homeService.queryBrandInfo(brandId);

            HashMap<String, Object> resultMap = new HashMap<>();
            resultMap.put("freshBrand", freshBrand);
            new Response(ResponseCode.SUCCESS, "请求成功", resultMap).write(
                new HashMap<Class<?>, String[]>() {{
                    put(FreshBrand.class, new String[]{
                        "id",       // type:int     品牌id
                        "logo",     // type:String  品牌Logo
                        "name",     // type:String  品牌名称
                        "remark",   // type:String  品牌说明介绍
                    });
                }},
                httpResponse);
        } catch (CustomException e) {
            new Response(e.getCode(), e.getMessage()).write(httpResponse);
        } catch (Exception e) {
            logger.error("调用[品牌详情接口]接口出现异常", e);
            new Response(ResponseCode.FAILURE, "请求失败").write(httpResponse);
        }
    }

    /**
     * @name 首页活动位Banner列表接口
     * @description 获取首页活动位Banner图片
     * @url /api/v1/market/home/activity_banner
     * @method POST
     */
    @RequestMapping(value = "/activity_banner",method = RequestMethod.POST)
    public void activityBanner(HttpServletResponse httpResponse) throws IOException {

        logger.info("调用[首页活动位Banner列表接口 /api/v1/market/home/activity_banner POST]接口 ");
        try {

            List<FreshImage> activityBannerList = homeService.getFreshImages(HomeConstant.FRESH_IMAGE_TYPE_ID_HOME, HomeConstant.FRESH_IMAGE_TYPE_ACTIVITY_BANNER);

            HashMap<String, Object> resultMap = new HashMap<>();
            resultMap.put("activityBannerList", activityBannerList);
            new Response(ResponseCode.SUCCESS, "请求成功", resultMap).write(
                new HashMap<Class<?>, String[]>() {{
                    put(FreshImage.class, new String[]{
                        "imageUrl",         // type:String  图标地址
                        "jumpProductId",    // type:int     跳转商品详情时所需的 商品id
                        "jumpType",         // type:int     APP跳转方式  01:商品详情   02:H5页面 03:不跳转(纯展示) 04 一元夺宝 05 竞拍页面
                        "jumpUrl",          // type:String  跳转H5页面所需的 url地址
                        "title"             // type:String  标题
                    });
                }},
                httpResponse);
        } catch (CustomException e) {
            new Response(e.getCode(), e.getMessage()).write(httpResponse);
        } catch (Exception e) {
            logger.error("调用[首页活动位Banner列表接口]接口出现异常", e);
            new Response(ResponseCode.FAILURE, "请求失败").write(httpResponse);
        }


    }

}
