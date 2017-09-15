package com.xmniao.xmn.core.xmer.service;

import com.xmniao.xmn.core.base.BaseRequest;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.Constant;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.entity.BisException;
import com.xmniao.xmn.core.common.request.StoreInfoModifyRequest;
import com.xmniao.xmn.core.common.request.StoreInfoUpdateRequest;
import com.xmniao.xmn.core.live.dao.BusinessDao;
import com.xmniao.xmn.core.live.dao.LiveUserDao;
import com.xmniao.xmn.core.live.service.LiveHomeV2Service;
import com.xmniao.xmn.core.util.DateUtil;
import com.xmniao.xmn.core.util.PropertiesUtil;
import com.xmniao.xmn.core.util.SaasBidType;
import com.xmniao.xmn.core.util.VersionUtil;
import com.xmniao.xmn.core.xmer.dao.SaasOrderDao;
import com.xmniao.xmn.core.xmer.dao.SaasSoldOrderDao;
import com.xmniao.xmn.core.xmer.dao.SellerInfoDao;
import com.xmniao.xmn.core.xmer.dao.XmerDao;
import com.xmniao.xmn.core.xmer.entity.SaaSoldOrder;
import com.xmniao.xmn.core.xmer.entity.SaasSignOrder;
import org.apache.commons.collections.functors.ExceptionClosure;
import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 签约店铺信息修改
 */
@Service
public class StoreInfoService {

    private final Logger log = Logger.getLogger(SellerInfoService.class);

    @Autowired
    private SessionTokenService sessionTokenService;
    @Autowired
    private LiveUserDao liveUserDao;
    @Autowired
    private SaasOrderDao saasOrderDao;
    @Autowired
    private SaasSoldOrderDao saasSoldOrderDao;
    @Autowired
    private SellerInfoDao sellerInfoDao;
    @Autowired
    private XmerDao xmerDao;
    @Autowired
    private SaasOrderService saasOrderService;
    @Autowired
    private String localDomain;
    @Autowired
    private XmerService xmerService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private String fileUrl;
    @Autowired
    private LiveHomeV2Service liveHomeV2Service;

    @Autowired
    private PropertiesUtil propertiesUtil;
    @Autowired
    private XmerInfoService xmerInfoService;
    @Autowired
    private BusinessDao businessDao;

    private boolean isLastVersion(BaseRequest request) {
        int modifyStoreIOS = 363;
        int modifyStoreAndroid = 363;
        try {
            modifyStoreIOS = Integer.parseInt(propertiesUtil.getValue("modifyStoreIOS", "conf_xmer.properties"));
            modifyStoreAndroid = Integer.parseInt(propertiesUtil.getValue("modifyStoreAndroid", "conf_xmer.properties"));
        } catch (Exception e) {
            log.warn("解析配置失败，modifyStoreIOS， modifyStoreAndroid");
        }
        int versionCode = VersionUtil.getVersionCode(request);
        int checkVersion = modifyStoreAndroid;
        if (VersionUtil.isAndroid(request)) {
            checkVersion =modifyStoreIOS;
        }
        return versionCode >= checkVersion;
    }

    private BaseResponse checkParam(StoreInfoModifyRequest infoRequest) {
        if (!isLastVersion(infoRequest)) {
            return null;
        }
//        if (infoRequest.getEnvirpic() == null || infoRequest.getEnvirpic().trim().equals("")) {
//            return new BaseResponse(ResponseCode.FAILURE,"环境图不能为空");
//        }
//        if (infoRequest.getAgree() != 1) {
//            return new BaseResponse(ResponseCode.FAILURE,"请勾选同意协议");
//        }
//        if (infoRequest.getLicensefurl() == null || infoRequest.getLicensefurl().trim().equals("")) {
//            return new BaseResponse(ResponseCode.FAILURE,"卫生许可证不能为空");
//        }
        if (infoRequest.getSystemversion() != null && infoRequest.getSystemversion().contains("h5")) {
            return null;
        }
        if (infoRequest.getCompany() == null || infoRequest.getCompany().trim().equals("")) {
            return new BaseResponse(ResponseCode.FAILURE,"公司名称不能为空");
        }
        return null;
    }

    /**
     * 签约店铺信息（多重身份，才会进入改方法（v客，脉客））
     * @param infoRequest
     * @return
     */
    public Object modifyStoreInfo(StoreInfoModifyRequest infoRequest) {
        try {

            String sUid = xmerService.getUidBySUid(infoRequest.getSessiontoken(), infoRequest.getsUid());
            if(sUid == null || sUid.equals("")||sUid.equals("null")){
                log.info("无效token，请重新登录!");
                return new BaseResponse(ResponseCode.TOKENERR,"无效token，请重新登录");
            }

            // 版本检测
            BaseResponse tmp = checkParam(infoRequest);
            if (tmp != null) {
                return tmp;
            }

            Integer uid = Integer.parseInt(sUid);
            Map<Integer, Boolean> identityMap = xmerService.identityMap(uid);
//			1 寻觅客 2中脉 3 V客 4 主播v客
            boolean isXmer = identityMap.get(1);
            boolean isV = identityMap.get(3);
            boolean isM = identityMap.get(2);
            boolean isAnchorV = identityMap.get(4);
            Integer saasType = infoRequest.getSaasType();  // saas类型，1 寻觅客 2中脉 3 V客 4 主播接受V客赠送
            boolean isMultiUser = isV || isM;
            // 	V客和中脉用户 saas类型不能为空
            if (isMultiUser && saasType == null) {
                return new BaseResponse(ResponseCode.FAILURE,"saas类型不能为空");
            }
            log.warn("选择处理逻辑: saasType: " + String.valueOf(saasType) + "  是否是寻觅客： " + isXmer);
            if (saasType != null && saasType != 1) {  //
                BaseResponse response =  decrementCombo(uid, infoRequest);
                return response;
            } else {  // 普通寻觅客
                BaseResponse response = createSaasOrder(uid, infoRequest);
                return response;
            }
        } catch (BisException e) {  //业务异常
            String message = e.getMessage();
            log.warn(message);
            if (e.get("sellerid") != null) {  //回滚
                try {
                    rollBack(Integer.parseInt(e.get("sellerid").toString()));
                } catch (Exception err) {
                    err.printStackTrace();
                    log.warn("回滚失败");
                }
            }
            return new BaseResponse(ResponseCode.FAILURE, message);
        } catch (Exception e) {
            log.warn("更新签约店铺数据失败");
            e.printStackTrace();
            return new BaseResponse(ResponseCode.FAILURE, "更新签约店铺数据失败");
        }
    }

    // 获取或增加虚拟店铺
    Map<Object, Object> getAndCreateVirtualSeller(int fatherid, Map<Object, Object> param) {
        try {
            Map<Object, Object> virtualSeller = sellerInfoDao.selectLsSellerByName(param);
            String sellername = param.get("sellername") == null ? "" : param.get("sellername").toString().trim();
            // 需要新增连锁虚拟记录
            if (fatherid == -1 && virtualSeller == null && !sellername.isEmpty()) {
                // 增加
                sellerInfoDao.insertSellerByMap(param);
                return param;
            }
            return virtualSeller;
        } catch (Exception e) {
            log.warn("获取虚拟连锁记录失败", e);
            return null;
        }
    }

    Map<Object, Object> cloneMap(Map<Object, Object> map) {
        Map<Object, Object> tmp = new HashMap<Object, Object>();
        for (Object key : map.keySet()) {
            tmp.put(key, map.get(key));
        }
        return tmp;
    }

    // 完善商铺资料
    public Object updateStoreInfo(StoreInfoUpdateRequest info) {
        try {
            Integer uid = 0;
            // h5 特殊处理不需要验证sessiontoken
            if (!info.getSystemversion().toLowerCase().contains("h5")) {
                uid = liveHomeV2Service.getUid(info.getSessiontoken());
                if (uid == null) {
                    return new BaseResponse(ResponseCode.FAILURE, "请重新登陆");
                }
            }
            // 查询店铺信息 审核失败，则改为待审核
            Map<Object, Object>  sellerInfo = sellerInfoDao.querySellerInfoBySellerid(info.getSellerid());
            Integer status = Integer.parseInt(sellerInfo.get("status").toString());
//            0 未验证 1 审核中 2 未通过 3 已签约 4 未签约
            if (status == 2) {
                status = 1;
            }
            String s1 = sellerInfo.get("sellername") == null ? null : sellerInfo.get("sellername").toString().trim();
            String p1 = sellerInfo.get("phoneid") == null ? null : sellerInfo.get("phoneid").toString().trim();
            String a1 = sellerInfo.get("address") == null ? null : sellerInfo.get("address").toString().trim();
            boolean isS1 = false;
            boolean isP2 = false;
            boolean isA3 = false;
            if (info.getSellername() != null && info.getSellername().trim().equals(s1)) {
                isS1 = true;
            }
            if (info.getPhoneid() != null && info.getPhoneid().trim().equals(p1)) {
                isP2 = true;
            }
            if (info.getAddress() != null && info.getAddress().trim().equals(a1)) {
                isA3 = true;
            }
            // 修改了其中一项
            if (!isS1 || !isP2 || !isA3) {
                String s2 = info.getSellername() == null ? s1 : info.getSellername().trim();
                String p2 = info.getPhoneid() == null ? p1 : info.getPhoneid().trim();
                String a2 = info.getAddress() == null ? a1 : info.getAddress().trim();
                log.info("修改店铺资料，效验名称是否重复：" + String.valueOf(s2) + " " + String.valueOf(p2) + " " + String.valueOf(a2));
                Integer counts = checkSellerInfoIsRepeat(s2, p2, a2);
                if (counts != null && counts > 1) {
                    return new BaseResponse(ResponseCode.SELLER_NAME_REPEAT,"商铺名称已存在，请重新填写");
                }
            }



            String signdate = DateUtil.format(new Date());
            Map<Object, Object> map = new HashMap<Object, Object>();
            map.put("sellername", info.getSellername());
            map.put("province", info.getProvince());
            map.put("city", info.getCity());
            map.put("area", info.getArea());
            map.put("address", info.getAddress());
            map.put("category", info.getCategory());
            map.put("genre", info.getGenre());
            map.put("fullname", info.getFullname());
            map.put("phoneid", info.getPhoneid());
            map.put("tel", info.getTel());
            map.put("signdate", signdate);
            Double agio = null;
            if (info.getAgio() != null) {
                agio = 0.1 * info.getAgio();
            }
            map.put("agio", agio);
            map.put("sdate", info.getSdate());
            map.put("sellerid", info.getSellerid());
            map.put("zoneid", info.getZoneid());
            map.put("typename", info.getTypename());
            map.put("tradename", info.getTradename());
            map.put("status", status);

            map.put("company", info.getCompany());
            map.put("business_license_name", info.getBusiness_license_name()); //营业执照名称
            map.put("lssellername", info.getLssellername());  //连锁店

            map.put("udate", DateUtil.format(new Date()));  //修改时间

            map.put("ismultiple", 1); // 1 为连锁
            map.put("isonline", 1);
            map.put("sellername", info.getLssellername());

            Map<Object, Object> virtualMap = cloneMap(map);
            // 虚拟连锁记录
            Map<Object, Object> virtualSeller = getAndCreateVirtualSeller(info.getFatherid(), virtualMap);
            Integer fatherid = virtualSeller == null || virtualSeller.get("sellerid") == null ? null : Integer.parseInt(virtualSeller.get("sellerid").toString());
            map.put("fatherid", fatherid);
            map.put("ismultiple", 0); // 0 为非连锁
            map.put("sellerid", info.getSellerid());
            map.put("sellername", info.getSellername());
            map.put("isonline", sellerInfo.get("isonline"));  //上线
            map.put("status", status);

//            if (info.getSystemversion() != null && info.getSystemversion().contains("h5")) {
//                Map<Object,Object> locationMap = new HashMap<Object,Object>();
//                locationMap.put("lon", info.getLongitude());//经度
//                locationMap.put("lat", info.getLatitude());//纬度
//                List<Map<Object,Object>> list = businessDao.selectByLonAndLat(locationMap);
//                Map<Object, Object> tmpMap = null;
//                if(list != null && !list.isEmpty()){
//                    tmpMap = list.get(0);
//                }
//                if (tmpMap != null) {
////                    map.put("province", tmpMap.get("provinceId"));
////                    map.put("city", tmpMap.get("cityId"));
////                    map.put("area", tmpMap.get("areaId"));
//                    map.put("zoneid", tmpMap.get("zoneId"));
//                }
//
//            }

            sellerInfoDao.updateSellerBySellerid(map);

            if (info.getLongitude() != null && info.getLatitude() != null) {
                map.put("longitude", info.getLongitude());
                map.put("latitude", info.getLatitude());
                map.put("sdate", DateUtil.format(new Date()));
                sellerInfoDao.updateSellerLandmark(map);
            }

            if (info.getConsume() != null) {
                map.put("consume", info.getConsume());
                sellerInfoDao.updateSellerInfoToDetail(map);
            }

            // 获取订单信息，用来生成支付二维码
            map.clear();
            map.put("sellerid", info.getSellerid());
            SaaSoldOrder saasInfo = saasSoldOrderDao.getSellerOrder(map);
            String ordersn = saasInfo.getOrdersn();  //订单Id
            Map<Object, Object> result = new HashMap<>();

            // v客或脉客
            Integer saasType = info.getSaasType();
            if (saasType != null && (saasType == 2 || saasType == 3 || saasType == 4) ) {
                saasInfo.setStatus(1);  //v客或脉客,默认支付成功
            }

            int whichType = 0;  // 1 店铺已支付  2 店铺待支付 3 saas套餐售完
            // 查询订单支付状态
            if (saasInfo.getStatus() != null && 1 == saasInfo.getStatus()) {  //支付成功
                whichType = 1;
                log.warn("编辑店铺，whichType" + whichType);
            } else {  //未支付
                String saas_ordersn = saasInfo.getSaas_ordersn();
                saasType = saasInfo.getSaas_channel();  //签约的SAAS获取渠道 1常规 2脉客 3 V客 4主播接受V客赠送
                // 查询订单对应saas套餐是否用完
                Map<Object,Object> orderMap = saasOrderDao.querySaasOrderInfoByOrdersn(saas_ordersn);  //查询对应的saas套餐是否用完
                Integer stock = 0;
                Integer returnnums = 0;
                try {
                    stock = Integer.parseInt(orderMap.get("stock").toString());
                    returnnums = Integer.parseInt(orderMap.get("returnnums").toString());
                } catch (Exception e) {
                    e.printStackTrace();
                    log.warn("查看saas套餐库存失败");
                    return new BaseResponse(ResponseCode.FAILURE, "完善商铺资料失败, 查看saas套餐库存失败");
                }
                // 可用的saas套餐
                Map<Object, Object> nextSaasInfoMap = null;
                boolean isSoldOut = false;
                log.warn("编辑店铺，stock == 0 && returnnums == 0, " + stock + "  " + returnnums);
                // 对应的套餐用完
                if (stock == 0 && returnnums == 0) {
                    // 查询对应类型的saas套餐是否用完
                    nextSaasInfoMap = getSaasOrderInfo(uid, saasType);
                    isSoldOut = true;
                }
                log.warn("编辑店铺，isSoldOut: " + isSoldOut + " nextSaasInfoMap is null = " + (nextSaasInfoMap == null));
                if (!isSoldOut) {  //对应套餐没用完
                    whichType = 2;
                }
                if (isSoldOut && nextSaasInfoMap == null) {  //对应的套餐用完，且没有可用的套餐
                    whichType = 3;
                }
                if (isSoldOut && nextSaasInfoMap != null) {  //对应的套餐用完，但有可用的套餐
                    whichType = 2;
                    try {
                        // 更新订单对应的saas套餐
                        String orderId = "02"+SaasBidType.getBid();  //创建新的店铺订单
                        Map<String, Object> param = new HashMap<String, Object>();
                        String next_saas_ordersn = nextSaasInfoMap.get("ordersn").toString();
                        Integer next_saas_channel = Integer.parseInt(nextSaasInfoMap.get("saas_channel").toString());
                        param.put("new_ordersn", orderId);
                        param.put("saas_ordersn",next_saas_ordersn );
                        param.put("old_ordersn", ordersn);
                        param.put("saas_channel", next_saas_channel);
                        saasOrderDao.updateNewSaasSoldOrder(param);
                        ordersn = orderId;
                    } catch (Exception e) {
                        e.printStackTrace();
                        log.warn("更新店铺资料，更新Saas套餐失败");
                        return new BaseResponse(ResponseCode.FAILURE, "更新店铺资料，更新Saas套餐失败");
                    }
                }
                result.put("amount", saasInfo.getAmount());
                result.put("type", 2);
                result.put("url", toPayUrl(info, info.getSellerid(), ordersn));
                result.put("uid", saasInfo.getUid());
            }
            MapResponse response = new MapResponse(ResponseCode.SUCCESS, "更新店铺资料成功");
            result.put("sellerid", info.getSellerid());
            result.put("orderid", ordersn);
            result.put("whichType", whichType);
            response.setResponse(result);
//            log.warn("编辑店铺，response：" + response.getResponse().toString());
            return response;
        } catch (Exception e) {
            log.warn("完善商铺资料失败");
            e.printStackTrace();
            return new BaseResponse(ResponseCode.FAILURE, "完善商铺资料失败");
        }
    }

    Map<Object, Object> modifyRequestToMap(StoreInfoModifyRequest infoRequest, Integer uid, int status, String uid_relation_chain) {
        Map<Object, Object> paramMap = new HashMap<Object, Object>();
        String date = DateUtil.format(new Date());
        String eDate = DateUtil.format(DateUtils.addYears(new Date(), 1));  //默认签约1年
        paramMap.put("uid", uid);
        paramMap.put("sellername", infoRequest.getSellername());
        paramMap.put("province", infoRequest.getProvince());
        paramMap.put("city", infoRequest.getCity());
        paramMap.put("area", infoRequest.getArea());
        paramMap.put("saas_type", infoRequest.getSaasType());  // 寻觅客，V客,中脉用户 v客赠送
        paramMap.put("zoneid", infoRequest.getZoneid());
        paramMap.put("address", infoRequest.getAddress());
        paramMap.put("fullname", infoRequest.getFullname());
        paramMap.put("phoneid", infoRequest.getPhoneid());
        String licenseurl = null;
        if (infoRequest.getLicenseurl() != null) {
            licenseurl = infoRequest.getLicenseurl().replace(fileUrl, "");
        }
        paramMap.put("licenseurl", licenseurl);
        paramMap.put("agio", infoRequest.getAgio()  * 0.1);
        paramMap.put("status", status);  // 默认 0未验证  1审核中 2未通过 3已签约 4未签约 5暂停合作  6已注销
        paramMap.put("signdate", date);  //签约时间
        paramMap.put("udate", date);  //修改时间
        paramMap.put("svalidity", date);
        paramMap.put("evalidity", eDate);  //
        paramMap.put("uid_relation_chain", uid_relation_chain);  //关系链
        // 3.6.3版本
        paramMap.put("company_name", infoRequest.getCompany());  //公司名称
        int fatherid = infoRequest.getFatherid() < 0 ? 0 : infoRequest.getFatherid();
        paramMap.put("fatherid", fatherid);
        paramMap.put("lssellername", infoRequest.getLssellername());
        return paramMap;
    }

    // 减少saas套餐
    public BaseResponse decrementCombo(Integer uid, StoreInfoModifyRequest infoRequest) throws BisException {
        Integer counts = checkSellerInfoIsRepeat(infoRequest);
        if(counts != null && counts > 1) {
            return new BaseResponse(ResponseCode.SELLER_NAME_REPEAT,"商铺名称已存在，请重新填写");
        }
        //查询寻蜜客最早购买的saas套餐的未使用完的订单信息
        Map<Object,Object> orderMap = getSaasOrderInfo(uid, infoRequest.getSaasType());
        if(orderMap == null){
            return new BaseResponse(ResponseCode.SAAS_STOCK_EMPTY, "SAAS套餐已使用完毕，请重新购买");
        }
//        Map<Object, Object> relation = liveUserDao.queryRelationByUid(uid);
//        Object uid_relation_chain = relation.get("uid_relation_chain");

        String sdate = DateUtil.format(DateUtil.now(),"yyyy-MM-dd HH:mm:ss");
        Map<Object, Object> param = modifyRequestToMap(infoRequest, uid, 1, null);
        // h5 v客 连锁店处理
        if (infoRequest.getSystemversion() != null && infoRequest.getSystemversion().contains("h5")) {
            // 连锁店处理
            param.put("ismultiple", 1); // 1 为连锁
            param.put("isonline", 1);
            param.put("sellername", infoRequest.getLssellername());
            Map<Object, Object> virtualMap = cloneMap(param);
            Map<Object, Object> virtualSeller = getAndCreateVirtualSeller(infoRequest.getFatherid(), virtualMap);
            Integer fatherid = virtualSeller == null || virtualSeller.get("sellerid") == null ? null : Integer.parseInt(virtualSeller.get("sellerid").toString());
            param.put("ismultiple", 0); // 0 为非连锁
            param.put("fatherid", fatherid);
            param.put("sellername", infoRequest.getSellername());
            param.remove("sellerid");
        } else {
            param.put("ismultiple", 0);
        }
        param.put("isonline", 0);
        Map<Object, Object> paramMap = insertSellerByMap(uid,1, param, null);  //插入信息表

        Integer sellerid = Integer.parseInt(paramMap.get("sellerid").toString());
        insertSellerInfoToDetail(sellerid, 0);  //保存店铺的人均消费
        insertSellerLandmark(sellerid, infoRequest.getLongitude(), infoRequest.getLatitude(), sdate);   // 添加经度和纬度
        insertSellerAgioRecord(sellerid, infoRequest.getAgio() * 0.1, sdate); //插入折扣记录表
        addPicByLastVersion(sellerid, infoRequest); //插入卫生许可证，环境图

        Integer soldnums = null;
        int stock = 0;
        int returnnums = 0;
        String orderId = null;
        try {
            soldnums = orderMap.get("soldnums") == null ? 0 : Integer.parseInt(orderMap.get("soldnums").toString());
            soldnums = soldnums + 1;   ;//卖出订单数量(原有的基础上+1)
            //优先扣除退款套数
            returnnums = Integer.parseInt(orderMap.get("returnnums").toString());
            stock = Integer.valueOf(orderMap.get("stock").toString());
            int source = returnnums == 0 ? 0 : 1;  //SAAS来源 0 正常库存 1 销售退回库存'
            int saas_channel = Integer.parseInt(orderMap.get("saas_channel").toString());
            orderId = "02"+SaasBidType.getBid();
            // 添加签约店铺订单
            SaasSignOrder saasSignOrder =  addSaasSignOrder(orderId, uid, sellerid, infoRequest.getSellername(),
                    orderMap.get("ordersn").toString(), source, saas_channel, 1);
            if (returnnums == 0) {  //SAAS来源 0 正常库存 1 销售退回库存'
                stock = stock - 1;//库存数量(原有的基础上-1)
            }else {
                returnnums = returnnums - 1;
            }
        } catch (Exception e) {
            BisException bisException = new BisException("插入签约店铺, 扣除库存失败");
            bisException.put("sellerid", sellerid);
            throw bisException;
        }

        try {
            //根据订单id更新卖出数量和库存量
            int updateOrderFlag = updateOrderNums(orderMap.get("ordersn").toString(), soldnums, stock, returnnums,
                    Integer.valueOf(orderMap.get("version").toString()));
            if(updateOrderFlag == 0){ //如果更新失败
                throw new IllegalStateException();
            }
        } catch (Exception e) {
            BisException bisException = new BisException("插入签约店铺更新库存失败");
            bisException.put("sellerid", sellerid);
            throw bisException;
        }
//        xmerDao.addSignNum(uid);	//新增签约数量
        updateXmer(uid);  // 更新等级
        MapResponse response = new MapResponse(ResponseCode.SUCCESS, "提交成功");
        Map<Object, Object> result = new HashMap<>();
        result.put("sellerid", sellerid);
        result.put("orderid", orderId);
        response.setResponse(result);
        return response;
    }

    /**
     * 扣减库存
     * @param saas_ordersn
     * @return
     */
    public Integer decrementBySaas_ordersn(String saas_ordersn) throws BisException {
        Map<Object, Object> orderMap = saasOrderDao.querySaasOrderInfoByOrdersn(saas_ordersn);
        int soldnums = Integer.valueOf(orderMap.get("soldnums").toString())+1;//卖出订单数量(原有的基础上+1)
        //优先扣除退款套数
        int returnnums = Integer.parseInt(orderMap.get("returnnums").toString());
        int stock = Integer.valueOf(orderMap.get("stock").toString());
        int source = returnnums == 0 ? 0 : 1;  //SAAS来源 0 正常库存 1 销售退回库存'

        if (returnnums == 0) {  //SAAS来源 0 正常库存 1 销售退回库存'
            stock = stock - 1;//库存数量(原有的基础上-1)
        }else {
            returnnums = returnnums - 1;
        }
        String ordersn = orderMap.get("ordersn").toString();
        String version = orderMap.get("version").toString();
        log.info("减少Saas套餐库存：" + orderMap.toString());
        //根据订单id更新卖出数量和库存量
        int updateOrderFlag = updateOrderNums(ordersn, soldnums, stock, returnnums,
                Integer.valueOf(version));
        return updateOrderFlag;
    }



    public BaseResponse createSaasOrder(Integer uid, StoreInfoModifyRequest infoRequest) throws BisException {
        Integer counts = checkSellerInfoIsRepeat(infoRequest);
        if(counts != null && counts > 0) {
            return new BaseResponse(ResponseCode.SELLER_NAME_REPEAT,"商铺名称已存在，请重新填写");
        }
        //查询寻蜜客最早购买的saas套餐的未使用完的订单信息
        Map<Object,Object> orderMap = getSaasOrderInfo(uid, 1);  //没有saastype，则是寻觅客
        if(orderMap == null){
            return new BaseResponse(ResponseCode.SAAS_STOCK_EMPTY, "SAAS套餐已使用完毕，请重新购买");
        }
        String sdate = DateUtil.format(DateUtil.now(),"yyyy-MM-dd HH:mm:ss");
        Map<Object, Object> relation = liveUserDao.queryRelationByUid(uid);
        Object uid_relation_chain = relation.get("uid_relation_chain");
        String _relation = uid_relation_chain == null ? null : uid_relation_chain.toString();
        Map<Object, Object> param = modifyRequestToMap(infoRequest, uid, 1, _relation);
        param.put("isonline", 0);
        param.put("ismultiple", 0);
        Map<Object, Object> paramMap = insertSellerByMap(uid,0, param, uid_relation_chain);  //插入信息表

        Integer sellerid  = Integer.parseInt(paramMap.get("sellerid").toString());
        insertSellerInfoToDetail(sellerid, 0);  //保存店铺的人均消费
        insertSellerLandmark(sellerid, infoRequest.getLongitude(), infoRequest.getLatitude(), sdate);   // 添加经度和纬度
        insertSellerAgioRecord(sellerid, infoRequest.getAgio() * 0.1, sdate); //插入折扣记录表
        addPicByLastVersion(sellerid, infoRequest); //插入卫生许可证，环境图

        //优先扣除退款套数
        int returnnums = Integer.parseInt(orderMap.get("returnnums").toString());
        int source = returnnums == 0 ? 0 : 1;  //SAAS来源 0 正常库存 1 销售退回库存'

        int saas_channel = Integer.parseInt(orderMap.get("saas_channel").toString());
        String orderId = "02"+SaasBidType.getBid();
        // 添加签约店铺订单
        SaasSignOrder saasSignOrder =  addSaasSignOrder(orderId, uid, sellerid, infoRequest.getSellername(),
                orderMap.get("ordersn").toString(), source, saas_channel, 0);
        MapResponse response = new MapResponse(ResponseCode.SUCCESS, "提交成功");
        Map<Object, Object> result = new HashMap<>();
        result.put("amount", saasSignOrder.getAmount());
        result.put("sellerid", sellerid);
        result.put("orderid", orderId);
        result.put("uid", uid);
        result.put("type", 2);
        result.put("url", toPayUrl(infoRequest, sellerid, orderId));
        response.setResponse(result);
        return response;
    }

    // 插入卫生许可证，环境图
    private void addPicByLastVersion(int sellerid, StoreInfoModifyRequest infoRequest) {
        boolean lastVersion = isLastVersion(infoRequest);
        if (lastVersion) {
            insertSellerPic(sellerid, infoRequest.getLicensefurl(), infoRequest.getEnvirpic());
        }
    }

    // 没有事物，只能通过该方法回滚,
    private void rollBack(Integer sellerid) {
        sellerInfoDao.deleteSeller(sellerid);
        sellerInfoDao.deleteSellerInfoToDetail(sellerid);
        sellerInfoDao.deleteAgioRecord(sellerid);
        sellerInfoDao.deleteLandmark(sellerid);
        saasOrderDao.deleteSaasOrder(sellerid);
    }

    public String toPayUrl(BaseRequest request, Integer sellerid, String orderid) {
        Map<Object, Object> param = new HashMap<>();
        param.put("sellerid", sellerid);
        param.put("ordersn", orderid);
        param.put("type", 2);
        param.put("randomNumber", new Random().nextInt());
        param.put("appversion", request.getAppversion());
        param.put("systemversion", request.getSystemversion());
        param.put("apiversion", request.getApiversion());
        param.put("sessiontoken", request.getSessiontoken());
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Object, Object> entry : param.entrySet()) {
            sb.append(entry.getKey());
            sb.append("=");
            sb.append(entry.getValue());
            sb.append("&");
        }
        String url = localDomain + "/payConfirm?" + sb.substring(0, sb.length() - 1);
        return url;
    }

    public Integer checkSellerInfoIsRepeat(StoreInfoModifyRequest storeInfoModifyRequest){
        String sellername = storeInfoModifyRequest.getSellername() == null ? null : storeInfoModifyRequest.getSellername().trim();
        String phoneid = storeInfoModifyRequest.getPhoneid() == null ? null : storeInfoModifyRequest.getPhoneid().trim();
        String address = storeInfoModifyRequest.getAddress() == null ? null : storeInfoModifyRequest.getAddress().trim();
        return checkSellerInfoIsRepeat(sellername, phoneid, address);
    }

    public Integer checkSellerInfoIsRepeat(String sellername, String phoneid, String address) {
        Map<Object,Object> checkSellerInfoMap = new HashMap<Object,Object>();
        checkSellerInfoMap.put("sellername", sellername);
        checkSellerInfoMap.put("phoneid", phoneid);
        checkSellerInfoMap.put("address", address);
        return saasOrderDao.checkSellerInfoIsRepeat(checkSellerInfoMap);
    }

    //插入信息表
    private Map<Object, Object> insertSellerByMap(int uid, int status, Map<Object, Object> paramMap, Object uid_relation_chain) throws BisException {
        try {
            sellerInfoDao.insertSellerByMap(paramMap);
            return paramMap;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BisException("插入店铺信息失败");
        }
    }

    //保存店铺的人均消费
    private void insertSellerInfoToDetail(Integer sellerid, double consume) throws BisException {
        try {
            Map<Object, Object> paramMap = new HashMap<Object, Object>();
            paramMap.put("sellerid", sellerid);
            paramMap.put("consume", consume);
            sellerInfoDao.insertSellerInfoToDetail(paramMap);//添加个人消费
        } catch (Exception e) {
            e.printStackTrace();
            BisException bisException = new BisException("插入店铺的人均消费失败");
            bisException.put("sellerid", sellerid);
            throw bisException;
        }

    }

    // 添加经度和纬度
    private void insertSellerLandmark(Integer sellerid, Double longitude, Double latitude, String sdate) throws BisException {
        try {
            Map<Object, Object> paramMap = new HashMap<Object, Object>();
            paramMap.put("sellerid", sellerid);
            paramMap.put("sdate", sdate);
            paramMap.put("longitude", longitude);//经度
            paramMap.put("latitude", latitude);//纬度
            sellerInfoDao.insertSellerLandmark(paramMap);//添加经度和纬度
        } catch (Exception e) {
            e.printStackTrace();
            BisException bisException = new BisException("插入店铺经度和纬度失败");
            bisException.put("sellerid", sellerid);
            throw bisException;
        }

    }

    //插入折扣记录表
    private void insertSellerAgioRecord(Integer sellerid, Double agio, String sdate) throws BisException {
        try {
            Map<Object, Object> paramMap = new HashMap<Object, Object>();
            paramMap.put("sellerid", sellerid);
            paramMap.put("agio", agio);//折扣
            paramMap.put("sdate", sdate);
            Integer id = sellerInfoDao.insertSellerAgio(paramMap);//插入折扣表
            paramMap.put("id", id);
            sellerInfoDao.insertSellerAgioRecord(paramMap);//插入折扣记录表
        } catch (Exception e) {
            e.printStackTrace();
            BisException bisException = new BisException("插入店铺折扣记录失败");
            bisException.put("sellerid", sellerid);
            throw bisException;
        }
    }

    // 添加环境图，卫生许可证
    private void insertSellerPic(int sellerid, String licensefurl, String envirpic) {
        xmerInfoService.insertEnvirpicAndLicensefurl(sellerid, licensefurl,envirpic );
    }

    // 添加签约店铺订单
    private SaasSignOrder addSaasSignOrder(String orderId, Integer uid, Integer sellerid, String sellername,
                                           String ordersn, int source, int sourceChannel, int status) throws BisException {
        try {
            //创建订单
            SaasSignOrder saasSignOrder = new SaasSignOrder();
            saasSignOrder.setId(orderId);
            saasSignOrder.setUid(String.valueOf(uid));
            saasSignOrder.setAmount(Constant.SIGN_AMOUNT);
            saasSignOrder.setSellerid(String.valueOf(sellerid));
            saasSignOrder.setSellername(sellername.trim());
            saasSignOrder.setStatus(status);//存入草稿
            //签约店铺使用的套餐订单号
            saasSignOrder.setSaasOrdersn(ordersn);
            saasSignOrder.setZdate(DateUtil.format(DateUtil.now()));
            saasSignOrder.setCreateDate(DateUtil.format(DateUtil.now()));
            saasSignOrder.setSaasSource(source);
            saasSignOrder.setSaasChannel(sourceChannel);
            //添加签约店铺订单
            saasOrderDao.addSaasSignOrder(saasSignOrder);
            return saasSignOrder;
        } catch (Exception e) {
            e.printStackTrace();
            BisException bisException = new BisException("插入签约店铺订单记录失败");
            bisException.put("sellerid", sellerid);
            throw bisException;
        }

    }

    //根据订单id更新卖出数量和库存量
    private Integer updateOrderNums(String ordersn, int soldnums, int stock, int returnnums, int version) {
        Map<Object, Object> paramMap = new HashMap<Object, Object>();
        paramMap.put("ordersn", ordersn);  //订单号
        paramMap.put("soldnums", soldnums);  //
        paramMap.put("stock", stock);
        paramMap.put("returnnums", returnnums);
        paramMap.put("version", version);//版本控制，防止并发
        return saasOrderDao.updateOrderNums(paramMap);  //根据订单id更新卖出数量和库存量
    }

    private void modifySeller(Integer sellerid, Integer status) {
        Map<Object, Object> paramMap = new HashMap<Object, Object>();
        paramMap.put("sellerid",sellerid);
        paramMap.put("status", status);
        sellerInfoDao.modifySeller(paramMap);
    }

    // 更新用户签约数量, 寻蜜客头衔等信息
    public void updateXmer(Integer uid) {
        xmerDao.addSignNum(uid);	//新增签约数量
        //更新寻蜜客头衔map
        Map<Object,Object> levelMap = new HashMap<Object,Object>();
        levelMap.put("uid", uid);
        levelMap.put("achievement", saasOrderService.getXmerLevelName(uid));
        saasOrderDao.modifyXmer(levelMap);//更新寻蜜客头衔
    }


    // 查询saas套餐,对应身份获取对应saas套餐
    private Map<Object, Object> getSaasOrderInfo(Integer uid, Integer saasType) {
        List<Map<Object,Object>> saasInfoList = saasOrderDao.queryOrderNumsV2(uid);
        if (saasInfoList == null || saasInfoList.size() == 0) {
            return null;
        }
        saasType = saasType == null ? 1 : saasType;  //寻觅客saastype可以为null
        for (int i = 0; i < saasInfoList.size(); i++) {
            Map<Object, Object> saasInfo = saasInfoList.get(i);
//            saas购买渠道 1常规购买 2 脉客购买 3 V客兑换  4 主播接受V客赠送
            Integer saas_channel = Integer.parseInt(saasInfo.get("saas_channel").toString());
            if (saas_channel == saasType) {
                return saasInfo;
            }
//        saasType    1 寻觅客 2中脉 3 V客
            // 主播接受V客赠送（v客的saas套餐）
//            if (saas_channel == 4 && saasType == 4) {
//                return saasInfo;
//            }
        }
        return null;
    }
}