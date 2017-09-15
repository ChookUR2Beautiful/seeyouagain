package com.xmniao.xmn.core.market.service.pay.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.market.pay.CouponListRequest;
import com.xmniao.xmn.core.market.dao.CouponDetailDao;
import com.xmniao.xmn.core.market.entity.pay.ProductInfo;
import com.xmniao.xmn.core.market.service.pay.MarketCouponService;
import com.xmniao.xmn.core.market.service.pay.MarketOrderService;
import com.xmniao.xmn.core.util.ArithUtil;
import com.xmniao.xmn.core.util.DateUtil;
import com.xmniao.xmn.core.util.PropertiesUtil;

/**
 * 
* @projectName: xmnService 
* @ClassName: MarketCouponServiceImpl    
* @Description:积分商城优惠卷实现类   
* @author: liuzhihao   
* @date: 2016年12月22日 下午4:02:23
 */
@Service
public class MarketCouponServiceImpl implements MarketCouponService{
	
	private static final Logger log	= Logger.getLogger(MarketCouponServiceImpl.class);
	
	//优惠卷发放dao
	@Autowired
	private CouponDetailDao couponDetailDao;
	
	@Autowired
	private MarketOrderService marketOrderService;
	
	@Autowired
	private SessionTokenService sessionTokenService;
	
	@Autowired
	private PropertiesUtil propertiesUtil;

	/**
	 * 查询用户积分商城优惠卷列表
	 */
	@Override
	public Object queryCouponList(CouponListRequest couponListRequest) {
		Map<Object,Object> result = new HashMap<Object,Object>();
		//获取用户ID
		String uid = ObjectUtils.toString(sessionTokenService.getStringForValue(couponListRequest.getSessiontoken()));
		
		//通过缓存获取用户购买的商品
		List<String> cartIds = couponListRequest.getCarts();
		
		Integer buyType = couponListRequest.getBuyType();//购买类型
		
		//用户购买的商品总价格
		Double totalPrice = 0.0;
		//优惠卷名称
		String waterName="";
		//是否有可用优惠卷
		boolean isCoupon = false;
		
		if(cartIds == null || cartIds.isEmpty()){
			return new BaseResponse(ResponseCode.FAILURE,"你还没有选择购买的商品");
		}
		//获取用户购买的商铺信息
		List<ProductInfo> products = marketOrderService.getProductInfoList(uid, cartIds, false, buyType);
		
		if(products == null || products.isEmpty()){
			return new BaseResponse(ResponseCode.FAILURE,"购物车没有商品,去商场逛逛吧");
		}
		
		//判断用户购买的商品是否可以使用优惠卷
		for(ProductInfo product : products){
			
			Integer gxId = product.getSupplierid();//供应商ID
			
			if(gxId != 27){
				isCoupon = true;
			}
			
			Double price = 
				ArithUtil.mul(ArithUtil.add(product.getCash().doubleValue(), product.getAmount().doubleValue()), product.getNum());
			
			totalPrice += price;
		}
		
		Map<Object,Object> map = new HashMap<Object,Object>();
		map.put("isDate", DateUtil.format(DateUtil.now(), "yyyy-MM-dd")+" 00:00:00");
		map.put("uid", uid);
		map.put("totalPrice", totalPrice);
		
		//获取巴马水优惠卷的ID集合
		List<Integer> couponIds = new ArrayList<Integer>();
		try{
			String waterCoupons = propertiesUtil.getValue("waterCoupons", "conf_integral_pay.properties");
			waterName = propertiesUtil.getValue("waterName", "conf_integral_pay.properties");
			if(StringUtils.isNotEmpty(waterCoupons)){
				
				JSONArray json = JSONObject.parseArray(waterCoupons);
				
				for(int i=0; i<json.size();i++){
				
					JSONObject object = json.getJSONObject(i);
					
					Integer waterCouponCid = Integer.parseInt(object.getString("cid"));//巴马水优惠卷ID
					
					couponIds.add(waterCouponCid);
				}
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		map.put("cids", couponIds);
		map.put("cname", waterName);
		//组装用户可用优惠卷集合
		List<Map<Object,Object>> coupons = new ArrayList<Map<Object,Object>>();
		
		if(isCoupon){
			//不可用巴马百岁源矿泉水优惠卷
			map.put("type", 1);
			try{
				coupons = couponDetailDao.findAllIsUserCoupons(map);
				//优惠卷使用类型 0 不可多选 1可多选
				result.put("ctype", 1);
			}catch(Exception e){
				e.printStackTrace();
				return new BaseResponse(ResponseCode.FAILURE,"查询优惠卷异常");
			}
			
		}else{
			try{
				map.put("type", 0);
				coupons = couponDetailDao.findAllIsUserCoupons(map);
				//优惠卷使用类型 0 不可多选 1可多选
				result.put("ctype", 0);
			}catch(Exception e){
				e.printStackTrace();
				return new BaseResponse(ResponseCode.FAILURE,"查询优惠卷异常");
			}
		}
		
		if(coupons != null && !coupons.isEmpty()){
			for(Map<Object,Object> coupon : coupons){
				
				if(StringUtils.isEmpty(ObjectUtils.toString(coupon.get("rule")))){
					coupon.put("rule", "");
				}
			}
		}
		
		MapResponse response = new MapResponse(ResponseCode.SUCCESS,"查询成功");
		result.put("coupons", coupons);
		response.setResponse(result);
		
		return response;
	}

	
	/**
	 * 获取用户可使用的优惠卷列表
	 */
	@Override
	public MapResponse getUsableCouponsByUid(String uid,List<String> cartIds,int type) {
		
		MapResponse response = null;

		Map<Object,Object> result = new HashMap<Object,Object>();
		
		if(cartIds == null || cartIds.isEmpty()){
			response = new MapResponse(ResponseCode.FAILURE,"你还没有选择购买的商品");
			return response;
		}
		//获取用户购买的商铺信息
		List<ProductInfo> products = marketOrderService.getProductInfoList(uid, cartIds, false, type);
		
		if(products == null || products.isEmpty()){
			response = new MapResponse(ResponseCode.FAILURE,"你购买的商品已失效，请重新添加商品");
			return response;
		}
		
		//消费总金额
		Double payment = 0.00;
		
		//判断是否有购买特殊物品:这里指 巴马水
		boolean haveSpecileGoos = false;
		
		//获取特殊商品编号
		int serial = getSpecialGoodsNum();
		
		for(ProductInfo product : products){
			
			if(product.getSupplierid() != serial){
				haveSpecileGoos = true;
			}
			
			Double price = 
					ArithUtil.mul(ArithUtil.add(product.getCash().doubleValue(), product.getAmount().doubleValue()), product.getNum());
				
			payment += price;
		}
		//查询用户可用优惠卷列表:优惠卷类型 包含(通用卷,超市卷)
		Map<Object,Object> paramap = new HashMap<Object,Object>();
		paramap.put("uid", uid);
		paramap.put("price", payment);
		if(haveSpecileGoos){
			//不可以使用特殊卷
			paramap.put("type", 1);
			result.put("multiselect", 1);//是否可以多选  0否   1是
		}else{
			result.put("type", 0);
			paramap.put("multiselect", 0);//是否可以多选  0否   1是
		}
		
		getSpecialCouponsNum(paramap);
		
		List<Map<Object,Object>> coupons = couponDetailDao.getMarketUsableCouponsByUid(paramap);
		
		if(coupons != null && coupons.size() > 0){
			result.put("coupons", coupons);
		}
		response = new MapResponse(ResponseCode.SUCCESS, "查询成功");
		response.setResponse(result);
		return response;
	}
	
	/**
	 * 
	* @Title: getSpecialGoodsNum 
	* @Description:获取特殊商品编号
	* @param:@return
	* @return:int返回类型 
	* @throws
	 */
	private int getSpecialGoodsNum(){
		
		int serial = 0; 
		
		try {
			
			String num = propertiesUtil.getValue("specilGoodsNum", "conf_integral_pay.properties");
			
			if(StringUtils.isNotEmpty(num)){
				serial = Integer.parseInt(num);
			}
			
		} catch (IOException e) {
			log.info("获取特殊商品编号异常");
			e.printStackTrace();
		}
		
		return serial;
	}

	/**
	 * 获取特殊优惠卷编号
	 */
	private void getSpecialCouponsNum(Map<Object,Object> map){
		
		try{
			String waterCoupons = propertiesUtil.getValue("waterCoupons", "conf_integral_pay.properties");
			String waterName = propertiesUtil.getValue("waterName", "conf_integral_pay.properties");
			List<Integer> cids = new ArrayList<Integer>();
			if(StringUtils.isNotEmpty(waterCoupons)){
				JSONArray json = JSONObject.parseArray(waterCoupons);
				for(int i=0; i<json.size();i++){
					JSONObject object = json.getJSONObject(i);
					Integer waterCouponCid = Integer.parseInt(object.getString("cid"));//巴马水优惠卷ID
					cids.add(waterCouponCid);
				}
				
				map.put("cids", cids);
			}
			
			if(StringUtils.isNotEmpty(waterName)){
				map.put("cname", waterName);
			}
		}catch(Exception e){
			log.info("获取积分超市特殊优惠卷信息异常");
			e.printStackTrace();
		}
	}
}
