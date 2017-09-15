package com.xmniao.xmn.core.market.service.cart.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.market.common.MarketConsts;
import com.xmniao.xmn.core.market.controller.cart.vo.CartAddRequest;
import com.xmniao.xmn.core.market.controller.cart.vo.CartDeleteRequest;
import com.xmniao.xmn.core.market.controller.cart.vo.CartEditAttrRequest;
import com.xmniao.xmn.core.market.controller.cart.vo.CartListRequest;
import com.xmniao.xmn.core.market.dao.FreshActivityCommonDao;
import com.xmniao.xmn.core.market.dao.FreshActivityGroupDao;
import com.xmniao.xmn.core.market.dao.FreshActivityProductDao;
import com.xmniao.xmn.core.market.dao.ProductInfoDao;
import com.xmniao.xmn.core.market.dao.SaleGroupDao;
import com.xmniao.xmn.core.market.entity.cart.FreshActivityGroup;
import com.xmniao.xmn.core.market.entity.cart.SaleGroup;
import com.xmniao.xmn.core.market.entity.pay.FreshActivityCommon;
import com.xmniao.xmn.core.market.entity.pay.FreshActivityProduct;
import com.xmniao.xmn.core.market.entity.pay.ProductInfo;
import com.xmniao.xmn.core.market.exception.CustomException;
import com.xmniao.xmn.core.market.service.cart.CartService;
import com.xmniao.xmn.core.util.FrameUtil;
import com.xmniao.xmn.core.util.PropertiesUtil;

@Service
public class CartServiceImpl implements CartService {
    // 日志
    private final Logger log = Logger.getLogger(CartServiceImpl.class);

    @Autowired
    private SessionTokenService sessionTokenService;

    @Autowired
    private ProductInfoDao productInfoDao;

    @Autowired
    private SaleGroupDao saleGroupDao;

    @Autowired
    private FreshActivityCommonDao freshActivityCommonDao;

    @Autowired
    private FreshActivityProductDao freshActivityProductDao;

    @Autowired
    private FreshActivityGroupDao freshActivityGroupDao;
    
    @Autowired
    private String fileUrl;

    @Autowired
    private PropertiesUtil propertiesUtil;

    @Override
    public Map<Object, Object> add(CartAddRequest cartAddRequest) throws CustomException {
        // 获取用户ID
        Map<Object, Object> resultMap = new HashMap<Object, Object>();
        List<String> resultList = new ArrayList<>();
        String uid =
                ObjectUtils.toString(sessionTokenService.getStringForValue(cartAddRequest
                        .getSessiontoken()));
        // 获取redis中的购物车信息
        String cartListStr =
                (String) sessionTokenService.getStringForValue(MarketConsts.CART_INFO_HEADER + uid);
        List<ProductInfo> cartList = JSON.parseArray(cartListStr, ProductInfo.class);
        log.info("购物车add接口访问redis中key： " + MarketConsts.CART_INFO_HEADER + uid + "的购物车信息");
        if (StringUtils.isNotBlank(cartListStr) && cartList.size() > 0) {
            if (cartList != null && cartList.size() > 0) {
                for(ProductInfo vo : cartList){
                    resultList.add(vo.getAttrIds()+","+vo.getCodeid()+","+vo.getActivityId()+";");
                }
                
                // 更新列表
                for (ProductInfo vo : cartList) {
                    String str = cartAddRequest.getAttrIds()+","+cartAddRequest.getCodeId()+","+cartAddRequest.getActivityId()+";";
                    String orginStr = vo.getAttrIds()+","+vo.getCodeid()+","+vo.getActivityId()+";";
                    if (StringUtils.isNotBlank(vo.getCartId())
                            && vo.getCartId().equals(cartAddRequest.getCartId())) {
                        cartAddRequest.setActivityId(vo.getActivityId());
                        cartAddRequest.setAttrIds(vo.getAttrIds());
                        cartAddRequest.setCodeId(vo.getCodeid().intValue());
                        if(!compareStock(cartAddRequest, vo.getNum())){
                            resultMap.put("msg", "库存不足或活动已结束！");
                            resultMap.put("code", 1);
                            return resultMap;
                        }
                        
                        vo.setNum(vo.getNum() + cartAddRequest.getNum()>99?99:vo.getNum() + cartAddRequest.getNum());//限制数量最大99
                        resultMap.put("msg", "商品购买数量最多为99！");
                        resultMap.put("code", 1);
                        break;
                    }else if(StringUtils.isBlank(cartAddRequest.getCartId()) && 
                            StringUtils.isNotBlank(cartAddRequest.getAttrIds()) && 
                            cartAddRequest.getAttrIds().equals(vo.getAttrIds())&& this.contains(resultList,str) && str.equals(orginStr)){
                        if(!compareStock(cartAddRequest, vo.getNum())){
                            resultMap.put("msg", "库存不足或活动已结束！");
                            resultMap.put("code", 1);
                            return resultMap;
                        }
                        
                        vo.setNum(vo.getNum() + cartAddRequest.getNum()>99?99:vo.getNum() + cartAddRequest.getNum());//限制数量最大99
                        resultMap.put("msg", "商品购买数量最多为99！");
                        resultMap.put("code", 1);
                        break;
                    } else if(StringUtils.isBlank(cartAddRequest.getCartId()) && 
                            StringUtils.isNotBlank(cartAddRequest.getAttrIds()) && !this.contains(resultList,str)){
                        if(!compareStock(cartAddRequest, 0)){
                            resultMap.put("msg", "库存不足或活动已结束！");
                            resultMap.put("code", 1);
                            return resultMap;
                        }
                        ProductInfo productInfo = getProductInfo(cartAddRequest);
                        cartList.add(productInfo);
                        break;
                    }
                }
                // 保存数据到redis
                sessionTokenService.setStringForValue(MarketConsts.CART_INFO_HEADER + uid,
                        JSON.toJSONString(cartList), 0, TimeUnit.SECONDS);
                resultMap.put("msg", "已加入购物车");
                resultMap.put("code", 0);
                resultMap.put("cartList", cartList);

            } else {
                resultMap.put("msg", "增加失败，购物记录id错误或规格id错误");
                resultMap.put("code", 1);
            }
        } else if (cartListStr == null || cartList.size() <= 0) {
            ProductInfo productInfo = getProductInfo(cartAddRequest);
            List<ProductInfo> list = new ArrayList<>();
            list.add(productInfo);
            // 保存数据到redis
            sessionTokenService.setStringForValue(MarketConsts.CART_INFO_HEADER + uid,
                    JSON.toJSONString(list), 0, TimeUnit.SECONDS);
            resultMap.put("msg", "已加入购物车");
            resultMap.put("code", 0);
            resultMap.put("cartList", list);
        }
        return resultMap;
    }

    // 获取组装数据后的productInfo
    private  ProductInfo getProductInfo(CartAddRequest cartAddRequest) {
        // 新购买的商品
        ProductInfo productInfo = productInfoDao.selectByCodeId(cartAddRequest.getCodeId());
        //图片
        productInfo.setBreviary(fileUrl+productInfo.getBreviary());
        productInfo.setNum(cartAddRequest.getNum());
        // 规格
        productInfo.setAttrIds(cartAddRequest.getAttrIds());
        productInfo.setAttrVals(cartAddRequest.getAttrVals());

        if (cartAddRequest.getActivityId() != null) {
            productInfo.setActivityId(cartAddRequest.getActivityId());
            FreshActivityCommon freshActivityCommon =
                    freshActivityCommonDao.selectByPrimaryKey(cartAddRequest.getActivityId());
            if (freshActivityCommon.getStatus() == 0
                    && freshActivityCommon.getEndDate().compareTo(new Date()) >= 0) {
                FreshActivityProduct freshActivityProduct =
                        freshActivityProductDao.findByActivityIdAndCodeId(cartAddRequest
                                .getActivityId(), cartAddRequest.getCodeId().longValue());
                if (freshActivityProduct != null) {
                    productInfo.setCash(freshActivityProduct.getSalePrice());// 活动基础价格
                    productInfo.setIntegral(freshActivityProduct.getSellIntegral());// 活动销售积分
                }
                FreshActivityGroup freshActivityGroup =
                        freshActivityGroupDao.selectByActivityIdAndCodeIdAndPvIds(cartAddRequest
                                .getActivityId(), cartAddRequest.getCodeId().longValue(),
                                cartAddRequest.getAttrIds());
                if (freshActivityGroup != null
                        && freshActivityGroup.getStock() >= cartAddRequest.getNum()) {
                    productInfo.setStock(freshActivityGroup.getStock() - cartAddRequest.getNum());
                    productInfo.setAmount(freshActivityGroup.getAmount());
                    productInfo.setType(0);// 设置为有效产品
                } else {
                    throw new CustomException("库存不足！");
                };
            } else {
                throw new CustomException("活动商品不存在！");
            };
        } else {
            SaleGroup saleGroup =
                    saleGroupDao.selectByAttr(cartAddRequest.getCodeId(),
                            cartAddRequest.getAttrIds());
            if(saleGroup==null){
                throw new CustomException("商品不存在！");  
            }
            if ( saleGroup.getStock() < cartAddRequest.getNum()) {
                throw new CustomException("库存不足！");
            }
            if (saleGroup != null) {
                productInfo.setStock(saleGroup.getStock() - cartAddRequest.getNum());
                productInfo.setAmount(saleGroup.getAmount());
                productInfo.setType(0);// 设置为有效产品
            }
        }
        productInfo.setCartId(FrameUtil.getUUID());// 设置商品的购物id
        
        //从配置文件中读出包邮价
        String freePostage = "199.00";
		try {
			freePostage = propertiesUtil.getValue("freePostage", "conf_integral_pay.properties");
		} catch (IOException e) {
			log.info("读取积分商城包邮价格失败! 默认为199.00");
			e.printStackTrace();
		}
        productInfo.setFreePostage(freePostage);
        
        return productInfo;
    }

    @Override
    public Map<Object, Object> delete(CartDeleteRequest cartDeleteRequest) throws CustomException{
        // 获取用户ID
        Map<Object, Object> resultMap = new HashMap<Object, Object>();
        String uid =
                ObjectUtils.toString(sessionTokenService.getStringForValue(cartDeleteRequest
                        .getSessiontoken()));
        // 获取redis中的购物车信息
        String cartListStr =
                (String) sessionTokenService.getStringForValue(MarketConsts.CART_INFO_HEADER + uid);
        log.info("购物车delete接口访问redis中key： " + MarketConsts.CART_INFO_HEADER + uid + "的购物车信息");
        if (StringUtils.isNotBlank(cartListStr)) {
            List<ProductInfo> cartList = JSON.parseArray(cartListStr, ProductInfo.class);
            List<ProductInfo> removeList = new ArrayList<>();
            if (cartList != null && cartList.size() > 0) {

                // 更新列表
                 for (ProductInfo vo : cartList) {
                    // redis中相同产品
                    // 有效的商品
                    if (cartDeleteRequest.getType() == 0) {
                        if (StringUtils.isNotBlank(vo.getCartId())
                                && vo.getCartId().equals(cartDeleteRequest.getCartId())) {
                            vo.setNum(vo.getNum() - cartDeleteRequest.getNum() <= 0 ? 1 : vo
                                    .getNum() - cartDeleteRequest.getNum());
                            if(vo.getNum() - cartDeleteRequest.getNum()>99){
                                vo.setNum(99);
                            }
                            break;
                        }

                    }
                    // 删除无效商品
                    else if (cartDeleteRequest.getType() == 1) {
                        //删除无效的商品
                        if(vo.getType()==null || vo.getType()==1){
                            removeList.add(vo);
                        }
                    }
                }
                 cartList.removeAll(removeList);

            }
            // 保存数据到redis
            sessionTokenService.setStringForValue(MarketConsts.CART_INFO_HEADER + uid,
                    JSON.toJSONString(cartList), 0, TimeUnit.SECONDS);
            resultMap.put("cartList", cartList);
            resultMap.put("msg", "删除成功");
            resultMap.put("code", 0);
        }
        return resultMap;
    }

    @Override
    public Map<Object, Object> list(CartListRequest cartDeleteRequest) throws CustomException{
        // 获取用户ID
        Map<Object, Object> resultMap = new HashMap<Object, Object>();
        String uid =
                ObjectUtils.toString(sessionTokenService.getStringForValue(cartDeleteRequest
                        .getSessiontoken()));
        // 获取redis中的购物车信息
        String cartListStr =
                (String) sessionTokenService.getStringForValue(MarketConsts.CART_INFO_HEADER + uid);
        log.info("购物车list接口访问redis中key： " + MarketConsts.CART_INFO_HEADER + uid + "的购物车信息");
        if (StringUtils.isNotBlank(cartListStr)) {
            List<ProductInfo> cartList = JSON.parseArray(cartListStr, ProductInfo.class);
            List<ProductInfo> invalidList = new ArrayList<>();// 无效的商品
            if (cartList != null && cartList.size() > 0) {
                // 更新列表
                for (ProductInfo vo : cartList) {
                    vo.setType(0);//设置为有效商品
                    //商品已下线
                    ProductInfo productInfo = productInfoDao.selectByCodeId(vo.getCodeid().intValue());
                    if(productInfo==null || productInfo.getPstatus()!=1){
                        // 设置无效商品
                        vo.setType(1);
                        invalidList.add(vo);
                        continue;
                    }
                    
                    // 活动商品
                    if (vo.getActivityId() != null) {
                        
                        FreshActivityCommon freshActivityCommon =
                                freshActivityCommonDao.selectByPrimaryKey(vo.getActivityId());
                        if (freshActivityCommon ==null ||freshActivityCommon.getStatus() != 0
                                || freshActivityCommon.getEndDate().compareTo(new Date()) < 0) {
                            // 设置无效商品
                            vo.setType(1);
                            invalidList.add(vo);
                            continue;
                        }
                        FreshActivityGroup freshActivityGroup =
                                freshActivityGroupDao.selectByActivityIdAndCodeIdAndPvIds(
                                        vo.getActivityId(), vo.getCodeid(), vo.getAttrIds());
                        if(freshActivityGroup==null){
                            // 设置无效商品
                            vo.setType(1);
                            invalidList.add(vo);
                            continue;
                        }
                        if(vo.getNum()<=0){
                            // 设置无效商品
                           vo.setType(1);
                           invalidList.add(vo); 
                           continue;
                        }
                        if (freshActivityGroup != null
                                && freshActivityGroup.getStock() < vo.getNum()) {
                            // 设置库存为实际库存
                            vo.setNum(freshActivityGroup.getStock());
                            if(vo.getNum()==0){
                             // 设置无效商品
                                vo.setType(1);
                                invalidList.add(vo); 
                            }
                        }
                    }
                    // 非活动商品
                    else if (vo.getActivityId() == null) {
                        SaleGroup saleGroup =
                                saleGroupDao.selectByAttr(vo.getCodeid().intValue(),
                                        vo.getAttrIds());
                        if(saleGroup ==null){
                            // 设置无效商品
                            vo.setType(1);
                            invalidList.add(vo);
                            continue;
                        }
                        if(vo.getNum()<=0){
                            // 设置无效商品
                           vo.setType(1);
                           invalidList.add(vo); 
                           continue;
                        }
                        if ( saleGroup.getStock() < vo.getNum()) {
                         // 设置库存为实际库存
                            vo.setNum(saleGroup.getStock());
                            if(vo.getNum()==0){
                                // 设置无效商品
                                vo.setType(1);
                                invalidList.add(vo); 
                            }
                        }

                    };
                }
                // 保存数据到redis
                sessionTokenService.setStringForValue(MarketConsts.CART_INFO_HEADER + uid,
                        JSON.toJSONString(cartList), 0, TimeUnit.SECONDS);
                cartList.removeAll(invalidList);
                resultMap.put("invalidList", invalidList);
                resultMap.put("validList", cartList);
                resultMap.put("msg", "获取成功");
                resultMap.put("code", 0);
            }

        }
        return resultMap;
    }

    @Override
    public Map<Object, Object> editAttr(CartEditAttrRequest cartAddRequest) throws CustomException{
        // 获取用户ID
        Map<Object, Object> resultMap = new HashMap<Object, Object>();
        String uid =
                ObjectUtils.toString(sessionTokenService.getStringForValue(cartAddRequest
                        .getSessiontoken()));
        // 获取redis中的购物车信息
        String cartListStr =
                (String) sessionTokenService.getStringForValue(MarketConsts.CART_INFO_HEADER + uid);
        log.info("购物车editAttr接口访问redis中key： " + MarketConsts.CART_INFO_HEADER + uid + "的购物车信息");
        if (StringUtils.isNotBlank(cartListStr)) {
            List<ProductInfo> cartList = JSON.parseArray(cartListStr, ProductInfo.class);
            if (cartList != null && cartList.size() > 0) {
                // 更新列表
                for (ProductInfo vo : cartList) {

                    if (StringUtils.isNotBlank(cartAddRequest.getCartId())
                            && vo.getCartId().equals(cartAddRequest.getCartId())) {
                        vo.setAttrIds(cartAddRequest.getAttrIds());
                        vo.setAttrVals(cartAddRequest.getAttrVals());
                        vo.setNum(cartAddRequest.getNum()<0?0:cartAddRequest.getNum());
                        //设置最大数量99
                        if(cartAddRequest.getNum()>99){
                            vo.setNum(99);  
                        }
                    }
                }
                // 保存数据到redis
                sessionTokenService.setStringForValue(MarketConsts.CART_INFO_HEADER + uid,
                        JSON.toJSONString(cartList), 0, TimeUnit.SECONDS);
                resultMap.put("msg", "修改成功");
                resultMap.put("code", 0);
            } else {
                resultMap.put("msg", "修改失败");
                resultMap.put("code", 1);
            }
        }
        return resultMap;
    }
    
    
    //比较实际库存和购物车商品库存
    public boolean compareStock(CartAddRequest cartAddRequest,Integer orginNum){
        // 活动商品
        if (cartAddRequest.getActivityId() != null) {
            
            FreshActivityCommon freshActivityCommon =
                    freshActivityCommonDao.selectByPrimaryKey(cartAddRequest.getActivityId());
            if (freshActivityCommon.getStatus() != 0
                    || freshActivityCommon.getEndDate().compareTo(new Date()) < 0) {
                
                return false;
            }
            FreshActivityGroup freshActivityGroup =
                    freshActivityGroupDao.selectByActivityIdAndCodeIdAndPvIds(
                            cartAddRequest.getActivityId(), cartAddRequest.getCodeId().longValue(), cartAddRequest.getAttrIds());
            if (freshActivityGroup != null
                    && freshActivityGroup.getStock() <(cartAddRequest.getNum()+orginNum)) {
                return false;
            }
        }
        // 非活动商品
        else if (cartAddRequest.getActivityId() == null) {
            SaleGroup saleGroup =
                    saleGroupDao.selectByAttr(cartAddRequest.getCodeId(),
                            cartAddRequest.getAttrIds());
            if (saleGroup ==null || saleGroup.getStock() < (cartAddRequest.getNum()+orginNum)) {
                return false;
            }

        };
        return true;
    }
    /**
     * 判读是否包含同种商品
     * @param listStr
     * @param str
     * @return
     */
    public boolean contains(List<String> resultList,String str){
        for(String s:resultList){
            if(s.equals(str)){
                return true;
            }
        }
        return false;
    }

}
