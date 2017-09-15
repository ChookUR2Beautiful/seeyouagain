package com.xmniao.xmn.core.market.service.product.impl;

import com.alibaba.fastjson.JSON;
import com.xmniao.xmn.core.base.RedisService;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.market.base.Page;
import com.xmniao.xmn.core.market.common.MarketConsts;
import com.xmniao.xmn.core.market.controller.product.vo.ProductAttrRequest;
import com.xmniao.xmn.core.market.controller.product.vo.ProductCollectRequest;
import com.xmniao.xmn.core.market.dao.*;
import com.xmniao.xmn.core.market.entity.cart.FreshActivityGroup;
import com.xmniao.xmn.core.market.entity.cart.SaleGroup;
import com.xmniao.xmn.core.market.entity.cart.SaleProperty;
import com.xmniao.xmn.core.market.entity.cart.SalePropertyValue;
import com.xmniao.xmn.core.market.entity.pay.FreshActivityCommon;
import com.xmniao.xmn.core.market.entity.pay.FreshActivityProduct;
import com.xmniao.xmn.core.market.entity.pay.ProductDetails;
import com.xmniao.xmn.core.market.entity.pay.ProductInfo;
import com.xmniao.xmn.core.market.exception.CustomException;
import com.xmniao.xmn.core.market.service.home.HomeService;
import com.xmniao.xmn.core.market.service.product.ProductService;
import com.xmniao.xmn.core.util.PropertiesUtil;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by yang.qiang on 2016/12/28.
 */
@Service
public class ProductServiceImpl implements ProductService {
    // 日志
    private final Logger log = Logger.getLogger(ProductServiceImpl.class);
    @Autowired
    private String fileUrl;

    @Autowired
    private ProductInfoDao productInfoDao;
    @Autowired
    private FreshActivityGroupDao freshActivityGroupDao;
    
    @Autowired
    private FreshActivityProductDao freshActivityProductDao;

    @Autowired
    private SessionTokenService sessionTokenService;

    @Autowired
    private SalePropertyDao salePropertyDao;

    @Autowired
    private SaleGroupDao saleGroupDao;

    @Autowired
    private SalePropertyValueDao salePropertyValueDao;

    @Autowired
    private ProductDetailsDao productDetailsDao;
    
    @Autowired
    private PropertiesUtil propertiesUtil;
    @Autowired
    private FreshActivityCommonDao freshActivityCommonDao;
    
    @Autowired
    private HomeService homeService;
    @Autowired
    private RedisService redisService;
    public static final String HOT_SALE_KEY = "xmnservice:market:hot.sale:";
    /**
     * 根据条件统计商品数量
     * @param typeId
     * @param brandId
     *@param maxMoney
     * @param minMoney   @return
     */
    @Override
    public Long countProductByCondition(Long typeId, Long brandId, Double maxMoney, Double minMoney) {
        Long count = productInfoDao.countProductByCondition(typeId,brandId,maxMoney,minMoney);
        return count;
    }


    /**
     *
     * @param typeId
     * @param brandId
     *@param maxMoney
     * @param minMoney
     * @param page    @return
     */
    @Override
    public List<ProductInfo> queryProductByCondition(Long typeId, Long brandId, Double maxMoney, Double minMoney, Page page) {
        List<ProductInfo> productList = productInfoDao.queryProductByCondition(typeId,brandId,maxMoney,minMoney,page);
        for (ProductInfo productInfo : productList) {
            productInfo.setBreviary(fileUrl + productInfo.getBreviary());
            loadLabel(productInfo);
        }
        return productList;
    }


    @Override
    public Map<Object, Object> attr(ProductAttrRequest productAttrRequest) {
        Map<Object, Object> resultMap = new HashMap<Object, Object>();

        List<SaleProperty> salePropertyList =
                salePropertyDao.selectByCodeId(productAttrRequest.getCodeId().longValue());
        for (SaleProperty vo : salePropertyList) {
            List<SalePropertyValue> salePropertyValueList =
                    salePropertyValueDao.selectByPropertyId(vo.getId());
            vo.setSubList(salePropertyValueList);
            resultMap.put("dataList", salePropertyList);
        }
        // 活动商品
        if (productAttrRequest.getActivityId() != null) {
            List<FreshActivityGroup> freshActivityGroupList =
                    freshActivityGroupDao.selectByActivityIdAndCodeId(productAttrRequest
                            .getActivityId(), productAttrRequest.getCodeId().longValue());
            resultMap.put("amountInfo", freshActivityGroupList);

        }
        // 普通商品
        else {
            List<SaleGroup> saleGroupList =
                    saleGroupDao.selectByCodeId(productAttrRequest.getCodeId());
            resultMap.put("amountInfo", saleGroupList);
        }
        return resultMap;
    }

    @Override
    public ProductInfo detail(Integer activityId,Long codeId,String sessiontoken) throws CustomException{
        ProductInfo productInfo = productInfoDao.selectByCodeId(codeId.intValue());
        if(productInfo==null){
            return null;
        }
        //设置库存
        homeService.loadStore(productInfo);
        productInfo.setDiscount(productInfo.getPrice());
        ProductDetails productDetail = productDetailsDao.selectByCodeId(codeId);
        if(activityId!=null){
            productInfo.setActivityId(activityId);
            //设置活动库存
            homeService.loadStore(productInfo,activityId.longValue());
            FreshActivityProduct freshActivityProduct = freshActivityProductDao.findByActivityIdAndCodeId(activityId, codeId);
            if(freshActivityProduct!=null){
                productInfo.setCash(freshActivityProduct.getSalePrice());//活动价格
                productInfo.setIntegral(freshActivityProduct.getSellIntegral());//活动积分
            }
        }
       
        productInfo.setBreviary(fileUrl+productInfo.getBreviary());
        //图片集合
        List<String> bannerList = new ArrayList<>();
        if(StringUtils.isNotBlank(productDetail.getImg1()))
        bannerList.add(fileUrl+productDetail.getImg1());
        
        if(StringUtils.isNotBlank(productDetail.getImg2()))
        bannerList.add(fileUrl+productDetail.getImg2());
        
        if(StringUtils.isNotBlank(productDetail.getImg3()))
        bannerList.add(fileUrl+productDetail.getImg3());
        
        if(StringUtils.isNotBlank(productDetail.getImg4()))
        bannerList.add(fileUrl+productDetail.getImg4());
        
        if(StringUtils.isNotBlank(productDetail.getImg5()))
        bannerList.add(fileUrl+productDetail.getImg5());
        
        if(StringUtils.isNotBlank(productDetail.getImg6()))
        bannerList.add(fileUrl+productDetail.getImg6());
        
        if(StringUtils.isNotBlank(productDetail.getImg7()))
        bannerList.add(fileUrl+productDetail.getImg7());
        
        if(StringUtils.isNotBlank(productDetail.getImg8()))
        bannerList.add(fileUrl+productDetail.getImg8());
        
        if(StringUtils.isNotBlank(productDetail.getImg9()))
        bannerList.add(fileUrl+productDetail.getImg9());
        
        if(StringUtils.isNotBlank(productDetail.getImg10()))
        bannerList.add(fileUrl+productDetail.getImg10());
        
        if(StringUtils.isNotBlank(productDetail.getImg11()))
        bannerList.add(fileUrl+productDetail.getImg11());
        
        if(StringUtils.isNotBlank(productDetail.getImg12()))
        bannerList.add(fileUrl+productDetail.getImg12());
        
        if(bannerList.size()==0){
            if(StringUtils.isNotBlank(productDetail.getPic1()))
                bannerList.add(fileUrl+productDetail.getPic1());
            
            if(StringUtils.isNotBlank(productDetail.getPic2()))
                bannerList.add(fileUrl+productDetail.getPic2());
            
            if(StringUtils.isNotBlank(productDetail.getPic3()))
                bannerList.add(fileUrl+productDetail.getPic3());
            
            if(StringUtils.isNotBlank(productDetail.getPic4()))
                bannerList.add(fileUrl+productDetail.getPic4());
            
            if(StringUtils.isNotBlank(productDetail.getPic5()))
                bannerList.add(fileUrl+productDetail.getPic5());
        }
        
        productInfo.setBanner(bannerList);
        if(StringUtils.isNotBlank(productDetail.getHtml())){
            productInfo.setHtml(productDetail.getHtml());
        }else{
            if(StringUtils.isBlank(productDetail.getHtml()) && bannerList.size()!=0){
                StringBuilder sb= new StringBuilder();
                sb.append("<style type=\"text/css\">*{margin:0;padding:0;}img{width: 100% !important;height: auto !important;display:block;margin:auto;vertical-align: top;}</style>");
                for(String url : bannerList){
                    sb.append("<p><img  src=\""+url+"\"  /></p>");
                }
                productInfo.setHtml(sb.toString());
                
            }
        }
        productInfo.setHtmlType(0);//0 富文本   1  html地址
        
        if(StringUtils.isNotBlank(sessiontoken)){
            // 获取用户ID
            String uid =
                    ObjectUtils.toString(sessionTokenService.getStringForValue(sessiontoken));
            // 获取redis中的收藏信息
            String collectStr =
                    (String) sessionTokenService.getStringForValue(MarketConsts.COLLECT_HEADER + uid);
            
            List<ProductInfo> productInfoList = JSON.parseArray(collectStr, ProductInfo.class);
            if(productInfoList!=null && productInfoList.size()>0){
                boolean flag= true;
                for(ProductInfo vo:productInfoList){
                    if(vo.getCodeid().equals(productInfo.getCodeid()) && vo.getActivityId()!=null && vo.getActivityId().equals(productInfo.getActivityId())){
                        productInfo.setCollection(vo.getCollection());
                        flag =false;
                        break;
                    }
                    if(vo.getCodeid().equals(productInfo.getCodeid()) && productInfo.getActivityId()==null){
                        productInfo.setCollection(vo.getCollection());
                        flag =false;
                        break;
                    }
                }
                if(flag){
                    productInfo.setCollection(0);
                }
            }else{
                productInfo.setCollection(0);
            }
            // 获取redis中的浏览历史信息
            String historyStr =
                    (String) sessionTokenService.getStringForValue(MarketConsts.HISTORY_HEADER + uid);
            List<ProductInfo> historyList = JSON.parseArray(historyStr, ProductInfo.class);
            if(historyList==null || historyList.size()<=0 ){
                historyList=new ArrayList<>();
                historyList.add(productInfo);
            }else if(historyList!=null){
                //判断活动的商品是否存在足迹中
                List<String> codeIdActivityList = new ArrayList<>();
                for(ProductInfo vo:historyList){
                    codeIdActivityList.add(vo.getCodeid().toString()+","+vo.getActivityId());
                }
                String str =  productInfo.getCodeid().toString()+","+(productInfo.getActivityId()==null?"null":productInfo.getActivityId().toString());
                if(!codeIdActivityList.contains(str)){
                    historyList.add(productInfo);
                }
                //删除大于99的记录
                if(historyList.size()>=100){
                    historyList.remove(99);
                }
            }
            // 保存数据到redis
            sessionTokenService.setStringForValue(MarketConsts.HISTORY_HEADER + uid,
                    JSON.toJSONString(historyList), 0, TimeUnit.SECONDS);
        }
        return productInfo;
    }

    @Override
    public Map<String,Integer> collect(ProductCollectRequest request) throws CustomException{
        Map<String,Integer> resultMap = new HashMap<>();
        // 获取用户ID
        String uid =
                ObjectUtils.toString(sessionTokenService.getStringForValue(request.getSessiontoken()));
        // 获取redis中的收藏信息
        String redisListStr =
                (String) sessionTokenService.getStringForValue(MarketConsts.COLLECT_HEADER + uid);
        List<ProductInfo> redisList = JSON.parseArray(redisListStr, ProductInfo.class);
        if(redisList!=null &&  redisList.size()>=0 ){
           //判断活动的商品是否存在收藏中
            List<String> codeIdActivityList = new ArrayList<>();
            for(ProductInfo vo:redisList){
                codeIdActivityList.add(vo.getCodeid().toString()+","+vo.getActivityId());
            }
            String str =  request.getCodeId().toString()+","+(request.getActivityId()==null?"null":request.getActivityId().toString());
            //缓存没有相同商品
            if(!codeIdActivityList.contains(str)){
                ProductInfo productInfo =new ProductInfo();
                productInfo.setActivityId(request.getActivityId());
                productInfo.setCodeid(request.getCodeId().longValue());
                productInfo.setCollection(request.getCollect());
                redisList.add(productInfo);
            }
            //缓存存在相同商品
            else if(codeIdActivityList.contains(str)){
                //活动商品
                if(request.getActivityId()!=null){
                    for(ProductInfo vo : redisList){
                        //缓存有相同商品
                        if(vo.getCodeid().equals(request.getCodeId().longValue()) && vo.getActivityId()!=null && vo.getActivityId().equals(request.getActivityId())){
                            vo.setCollection(request.getCollect());
                            break;
                        }
                    }
                }
                //非活动商品
                else if(request.getActivityId()==null){
                    for(ProductInfo vo : redisList){
                        //缓存有相同商品
                        if(vo.getCodeid().equals(request.getCodeId().longValue()) ){
                            vo.setCollection(request.getCollect());
                            break;
                        }
                    }
                }
            }
            
            // 保存数据到redis
            sessionTokenService.setStringForValue(MarketConsts.COLLECT_HEADER + uid,JSON.toJSONString(redisList),0,TimeUnit.SECONDS);
        }else if(redisList==null){
            redisList =new ArrayList<>();
            ProductInfo productInfo =new ProductInfo();
            if(request.getActivityId()!=null){
                productInfo.setActivityId(request.getActivityId());  
            }
            productInfo.setCodeid(request.getCodeId().longValue());
            productInfo.setCollection(request.getCollect());
            redisList.add(productInfo);
            // 保存数据到redis
            sessionTokenService.setStringForValue(MarketConsts.COLLECT_HEADER + uid,JSON.toJSONString(redisList),0,TimeUnit.SECONDS);
        }
        resultMap.put("collection", request.getCollect());
        return resultMap;
    }
    
    @Override
    public String text() throws IOException {
        String text=propertiesUtil.getValue("integral.text", "conf_integral_pay.properties");
        return text;
    }

    /**
     * 收藏列表
     */
    @Override
    public Map<Object, Object> collectList(String sessiontoken) {
        Map<Object, Object> resultMap = new HashMap<Object, Object>();
        // 获取用户ID
        String uid =
                ObjectUtils.toString(sessionTokenService.getStringForValue(sessiontoken));
        String collectStr =
                (String) sessionTokenService.getStringForValue(MarketConsts.COLLECT_HEADER + uid);
        List<ProductInfo> productInfoList = JSON.parseArray(collectStr, ProductInfo.class);
        List<ProductInfo> invalidList = new ArrayList<>();
        List<ProductInfo> removeList = new ArrayList<>();
        if (productInfoList != null && productInfoList.size() > 0) {
            // 更新列表
            for (ProductInfo vo : productInfoList) {
                //删除未收藏
                if(vo.getCollection()!=null && vo.getCollection()==0){
                    removeList.add(vo);
                }
                
                vo.setType(0);//设置为有效商品
                
                ProductInfo productInfo = productInfoDao.selectByCodeId(vo.getCodeid().intValue());
                if(productInfo!=null){
                    vo.setCash(productInfo.getCash());
                    vo.setIntegral(productInfo.getIntegral());
                    vo.setPname(productInfo.getPname());
                    vo.setChoice(productInfo.getChoice());
                    vo.setBreviary(productInfo.getBreviary());
                }
              //商品已下线
                if(productInfo==null ||(productInfo!=null && productInfo.getPstatus()!=1)){
                    // 设置无效商品
                    vo.setType(1);
                    invalidList.add(vo);
                    continue;
                }
                //设置库存
                homeService.loadStore(vo);
                // 活动商品
                if (vo.getActivityId() != null) {
                    homeService.loadStore(vo,vo.getActivityId().longValue());
                    FreshActivityCommon freshActivityCommon =
                            freshActivityCommonDao.selectByPrimaryKey(vo.getActivityId());
                    if (freshActivityCommon==null ||freshActivityCommon.getStatus() != 0
                            || freshActivityCommon.getEndDate().compareTo(new Date()) < 0) {
                        // 设置无效商品
                        vo.setType(1);
                        invalidList.add(vo);
                        continue;
                    }
                }
                if((vo.getStore()!=null && vo.getStore()==0)|| vo.getStore()==null){
                    // 设置无效商品
                    vo.setType(1);
                    invalidList.add(vo);
                }
            }
            // 保存数据到redis
            //清空未收藏数据
            productInfoList.removeAll(removeList);
            sessionTokenService.setStringForValue(MarketConsts.COLLECT_HEADER + uid,
                    JSON.toJSONString(productInfoList), 0, TimeUnit.SECONDS);
            productInfoList.removeAll(invalidList);
            for(ProductInfo vo: invalidList){
                if(vo.getBreviary()!=null){
                    vo.setBreviary(fileUrl+vo.getBreviary());
                }
            }
            for(ProductInfo vo: productInfoList){
                if(vo.getBreviary()!=null){
                    vo.setBreviary(fileUrl+vo.getBreviary());
                }
            }
            invalidList.removeAll(removeList);
            resultMap.put("invalidList", invalidList);
            List<ProductInfo> validList = new ArrayList<>();
            for(int i=productInfoList.size()-1; i >=0; i--){
                validList.add(productInfoList.get(i));
            }
            resultMap.put("validList", validList);
            resultMap.put("msg", "获取成功");
            resultMap.put("code", 0);
           
        }
        return resultMap;
    }

    /**
     * 浏览历史记录列表
     */
    @Override
    public List<ProductInfo> historyList(String sessiontoken) {
        // 获取用户ID
        String uid = ObjectUtils.toString(sessionTokenService.getStringForValue(sessiontoken));
        // 获取redis中的浏览历史信息
        String historyStr =
                (String) sessionTokenService.getStringForValue(MarketConsts.HISTORY_HEADER + uid);
        log.info("浏览历史historyList接口访问redis中key： " + MarketConsts.HISTORY_HEADER + uid + "的信息");
        List<ProductInfo> historyList = JSON.parseArray(historyStr, ProductInfo.class);
        List<ProductInfo> invalidList = new ArrayList<>();
        List<ProductInfo> validList = new ArrayList<>();
        if (historyList != null && historyList.size() > 0) {
            // 更新列表
            for (ProductInfo vo : historyList) {
                vo.setType(0);//设置为有效商品
                ProductInfo productInfo = productInfoDao.selectByCodeId(vo.getCodeid().intValue());
                if(productInfo!=null){
                    vo.setCash(productInfo.getCash());
                    vo.setIntegral(productInfo.getIntegral());
                    vo.setPname(productInfo.getPname());
                    vo.setChoice(productInfo.getChoice());
                    //设置库存
                    homeService.loadStore(vo);
                }
                //商品已下线
                if(productInfo==null ||productInfo.getPstatus()!=1){
                    // 设置无效商品
                    vo.setType(1);
                    invalidList.add(vo);
                    continue;
                }
                
                // 活动商品
                if (vo.getActivityId() != null) {
                  //设置库存
                    homeService.loadStore(vo,vo.getActivityId().longValue());
                    FreshActivityCommon freshActivityCommon =
                            freshActivityCommonDao.selectByPrimaryKey(vo.getActivityId());
                    if (freshActivityCommon == null ||freshActivityCommon.getStatus() != 0
                            || freshActivityCommon.getEndDate().compareTo(new Date()) < 0) {
                        // 设置无效商品
                        vo.setType(1);
                        invalidList.add(vo);
                        continue;
                    }
                }
            }
            // 保存数据到redis
            historyList.removeAll(invalidList);
            sessionTokenService.setStringForValue(MarketConsts.HISTORY_HEADER + uid,
                    JSON.toJSONString(historyList), 0, TimeUnit.SECONDS);
            for(int i=historyList.size()-1; i >=0; i--){
                validList.add(historyList.get(i));
            }
        }
        return validList;
    }



    /**
     * 加载商品常规Label
     * @param productInfo
     */
    public void loadLabel(ProductInfo productInfo){
        if (productInfo.getLabels() == null) {
            productInfo.setLabels(new HashSet<Integer>());
        }
        Set<Integer> labels = productInfo.getLabels();
        if (hotSaleList(productInfo.getClassa()).contains(productInfo.getCodeid().toString())){
            labels.add(2);  // 热销商品
            return;
        }
        if (productInfo.getChoice() == 1) {
            labels.add(3);  // 精选商品
            return;
        }
        if ((productInfo.getRdate().getTime()+604800000) > new Date().getTime()){
            labels.add(1);  // 新品商品
            return;
        }

    }

    /**
     * 加载商品活动Label
     * @param productInfo
     * @param activity
     */
    public void loadLabel(ProductInfo productInfo,FreshActivityCommon activity){
        if (productInfo.getLabels() == null) {
            productInfo.setLabels(new HashSet<Integer>());
        }

        if (activity != null && activity.getLabelId() != null) {
            productInfo.getLabels().add(activity.getLabelId());
        }else {
            loadLabel(productInfo);
        }

    }

    @Override
    public void loadLabel(ProductInfo productInfo, HashMap<Integer, FreshActivityCommon> activitys) {
        Integer activityId = productInfo.getActivityId();
        if (activityId != null && activitys.get(activityId) != null) {
            loadLabel(productInfo,activitys.get(activityId));
        }else {
            loadLabel(productInfo);
        }
    }

    /**
     * 根据类型id 获取热销商品列表
     * 从Redis获取热销商品数据, 不存在则生成数据
     */
    private List<String> hotSaleList(Integer typeId){
        String key = HOT_SALE_KEY + typeId;
        List<String> list = redisService.lrange(key);
        if (list != null) {
            return list;
        }else {
            list = productInfoDao.selectHotSaleCodeid(typeId,10);
            redisService.lset(key,list);
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MINUTE, 60);
            redisService.expireat(key,calendar.getTime());
            return list;
        }
    }
}
