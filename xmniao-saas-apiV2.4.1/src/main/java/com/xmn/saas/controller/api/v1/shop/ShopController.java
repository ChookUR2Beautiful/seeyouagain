package com.xmn.saas.controller.api.v1.shop;

import com.xmn.saas.base.AbstractController;
import com.xmn.saas.base.GlobalConfig;
import com.xmn.saas.base.Response;
import com.xmn.saas.base.ResponseCode;
import com.xmn.saas.controller.api.v1.shop.vo.ShopPutRequest;
import com.xmn.saas.entity.common.SellerAccount;
import com.xmn.saas.entity.shop.*;
import com.xmn.saas.exception.SaasException;
import com.xmn.saas.service.base.RedisService;
import com.xmn.saas.service.common.AreaService;
import com.xmn.saas.service.shop.SellerInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;


/**
 * 类名称：ShopDetailController
 * 类描述：   查询店铺基本信息
 * 创建人：xiaoxiong
 * 创建时间：2016年9月24日 下午4:58:14
 * 修改人：xiaoxiong
 * 修改时间：2016年9月24日 下午4:58:14
 * 修改备注：
 */
@Controller(value = "api-v1-shop-controller")
@RequestMapping("/api/v1/shop")
public class ShopController extends AbstractController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 注入店铺service
     */
    @Autowired
    private SellerInfoService sellerInfoService;


    /**
     * 注入操作rids封装类
     */
    @Autowired
    private RedisService redisService;

    @Autowired
    private GlobalConfig config;

    @Autowired
    private AreaService areaService;


    @RequestMapping("/detail")
    @ResponseBody
    public void detail() throws IOException {

        SellerAccount sellerAccount = redisService.getSessionCacheObject(getToken(), SellerAccount.class);

        Integer sellerid = sellerAccount.getSellerid();

        if (sellerid == null || sellerid == 0) {
            new Response(ResponseCode.DATAERR, "sessionToken错误，没有找到sellerid").write();
            return;
        }
        /**
         * 查询是否有带审核申请
         */
        int applyCount = sellerInfoService.querySellerApplyCount(1, sellerid);

        /**
         * 如果有待审核的数据则显示待审核的数据（并且把状态返回，客户端显示审核中）
         */
        if (applyCount > 0) {
            /**
             * 查审核表数据
             */
            SellerApply sellerApply = sellerInfoService.querySellerApplyBySellerid(1, sellerid);
            /**
             * 转换数据
             */
            converSellerInfo(sellerApply);

            return;
        }

        /**
         * 查商铺基本信息
         */
        SellerInfo sellerInfo = sellerInfoService.querySellerBySellerid(sellerid);
        //根据省市区编号返回省市区名称
        getArea(sellerInfo);

        if (sellerInfo == null) {
            new Response(ResponseCode.DATA_NULL, "没有找到店铺基本信息").write();
            return;
        }
        /**
         * 查询店铺详细信息
         */
        SellerDetailed sellerDetailed = sellerInfoService.querySellerDetailedBySellerId(sellerid);
        /**
         * 查询店铺坐标信息
         */
        SellerLandMark sellerLandMark = sellerInfoService.querySellerLandMarkBySellerid(sellerid);
        /**
         * 查询商家商圈信息
         */
        Integer zoneId = sellerInfo.getZoneId();
        if (zoneId != null) {
            Business business = sellerInfoService.queryBusinessBySellerid(zoneId);
            if (business != null && business.getTitle() != null) {
                sellerInfo.setZoneName(business.getTitle());
            }
        }

        sellerInfo.setDetailed(sellerDetailed);
        sellerInfo.setLandMark(sellerLandMark);

        /**
         * 没有待审核的提交申请表示可以提交
         */
        sellerInfo.setStatus(0);
        new Response(ResponseCode.SUCCESS, "成功", sellerInfo).write(new HashMap<Class<?>, String[]>() {{

            put(SellerInfo.class, new String[]{
                "sellerid", "sellerName", "zoneId", "address", "phone", "openDate", "typeName", "zoneName", "status", "applyCount",
                "detailed", "landMark", "tradename", "category", "genre", "province", "city", "area","tags"
            });
            put(SellerDetailed.class, new String[]{"consume", "isWifi", "isParking", "landMark"});
            put(SellerLandMark.class, new String[]{"longitude", "latitude"});
            put(LiveClassifyTag.class, new String[]{"id","tagName"});
        }});
    }

    private void getArea(SellerInfo sellerInfo) {
        String parea = areaService.selectAreaById(sellerInfo.getProvinceNo());
        String carea = areaService.selectAreaById(sellerInfo.getCityNo());
        String aarea = areaService.selectAreaById(sellerInfo.getAreaNo());
        if (parea != null) {
            sellerInfo.setProvince(parea.replace("省", "") + "省");
        }
        if (carea != null) {
            sellerInfo.setCity(carea.replace("市", "") + "市");
        }
        if (aarea != null) {
            sellerInfo.setArea(aarea);
        }
    }

    private void converSellerInfo(SellerApply sellerApply) throws IOException {


        SellerInfo sellerInfo = new SellerInfo();
        BeanUtils.copyProperties(sellerApply, sellerInfo);
        SellerInfo tempInfo = sellerInfoService.querySellerBySellerid(sellerApply.getSellerid());
        sellerInfo.setTypeName(tempInfo.getTypeName());
        sellerInfo.setCategory(tempInfo.getCategory());
        sellerInfo.setGenre(tempInfo.getGenre());
        sellerInfo.setTradename(tempInfo.getTradename());
        sellerInfo.setOpenDate(tempInfo.getOpenDate());
        sellerInfo.setProvinceNo(sellerApply.getProvinceNo());
        sellerInfo.setCityNo(sellerApply.getCityNo());
        sellerInfo.setAreaNo(sellerApply.getAreaNo());

        getArea(sellerInfo);
        //查询商圈信息
        Business business = sellerInfoService.queryBusinessBySellerid(tempInfo.getZoneId());
        if (business != null && business.getTitle() != null) {
            sellerInfo.setZoneName(business.getTitle());
            sellerInfo.setZoneId(tempInfo.getZoneId());
        }


        //查询店铺详细信息
        SellerDetailed detailed = sellerInfoService.querySellerDetailedBySellerId(sellerApply.getSellerid());
        if (detailed == null) {
            detailed = new SellerDetailed();
        }
        detailed.setLandMark(sellerApply.getLandMark());//显示审核表中的地标

        SellerLandMark landMark = new SellerLandMark();
        BeanUtils.copyProperties(sellerApply, landMark);

        sellerInfo.setDetailed(detailed);
        sellerInfo.setLandMark(landMark);
        sellerInfo.setStatus(1);

        new Response(ResponseCode.SUCCESS, "成功", sellerInfo).write(new HashMap<Class<?>, String[]>() {{
            put(SellerInfo.class, new String[]{"sellerid", "sellerName", "zoneId", "address", "phone", "openDate", "typeName", "status", "zoneName", "applyCount",
                "detailed", "landMark", "tradename", "category", "genre", "province", "city", "area"});
            put(SellerDetailed.class, new String[]{"consume", "isWifi", "isParking", "landMark"});
            put(SellerLandMark.class, new String[]{"longitude", "latitude"});

        }});

    }

    /**
     * @throws Exception
     * @Description: 修改店铺资料
     * @author xiaoxiong
     * @date 2016年9月26日
     * @version
     */
    @RequestMapping(value = "/put", method = RequestMethod.POST)
    @ResponseBody
    public void put(@Valid ShopPutRequest request, BindingResult result) throws Exception {
        /**
         * 验证参数
         */
        if (!request.doValidate(result)) {
            return;
        }

        SellerAccount sellerAccount = redisService.getSessionCacheObject(getToken(), SellerAccount.class);

        Integer sellerid = sellerAccount.getSellerid();

        try {
            /**
             * 查询是否有提交审核记录
             */
            int applyCount = sellerInfoService.querySellerApplyCount(1, sellerid);
            if (applyCount > 0) {
                new Response(ResponseCode.HAS_BEEN_SUBMIT, "您有正在审核的申请，请耐心的等待。").write();
                return;
            }

            /**
             * 商家修改资料提交申请
             */
            sellerInfoService.insertSellerApply(request.convertSellerApply(sellerid));

            new Response(ResponseCode.SUCCESS, "成功").write();

        } catch (Exception e) {
            e.printStackTrace();
            new Response(ResponseCode.FAILURE, "错误").write();
        }
    }

    /**
     * @throws IOException
     * @throws Exception
     * @Description: 获取微信公众号付款码
     * @author xiaoxiong
     * @date 2016年9月26日
     * @version
     */
    @RequestMapping(value = "/getWxPayUrl")
    @ResponseBody
    public void getWxPayUrl() throws IOException {

        try {

            SellerAccount sellerAccount = redisService.getSellerAccount(getToken());

            int sellerid = sellerAccount.getSellerid();

            String url = config.getWxPayUrl() + "?sellerid=" + sellerid;

            new Response(ResponseCode.SUCCESS, "成功", url).write();
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }

        new Response(ResponseCode.FAILURE, "错误").write();
    }

    /**
     * @name     查询商户标签列表
     * @url     /api/v1/shop/tag_list
     * @method  POST
     */
    @ResponseBody
    @RequestMapping(value = "tag_list",method = RequestMethod.POST)
    public void tagList() throws IOException {
        logger.info("调用[查询商户标签列表 /api/v1/shop/tag_list POST]");

        try {
            List<LiveClassify> tagClasses = sellerInfoService.querySellerTag();
            success(tagClasses,
                new HashMap<Class<?>, String[]>() {
                    {
                        put(LiveClassify.class, new String[]{
                            "classifyName",
                            "tags",
                        });
                        put(LiveClassifyTag.class, new String[]{
                            "id",
                            "tagName",
                        });
                    }
                });
        } catch (SaasException e){
            failure(e);
        } catch (Exception e) {
            logger.error("调用[查询商户标签列表]出现异常",e);
            failure();
        }

    }

}
