package com.xmniao.xmn.core.sellerPackage.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.sellerPackage.dao.SellerPackageDao;
import com.xmniao.xmn.core.sellerPackage.entity.ComboTrade;
import com.xmniao.xmn.core.sellerPackage.request.RecomboRequest;
import com.xmniao.xmn.core.sellerPackage.response.ComboListResponse;
import com.xmniao.xmn.core.sellerPackage.service.ComboService;
import com.xmniao.xmn.core.util.ArithUtil;
import com.xmniao.xmn.core.util.DateUtil;
import com.xmniao.xmn.core.util.PropertiesUtil;
import com.xmniao.xmn.core.verification.dao.BillDao;

/**
 * 
* @projectName: xmnService 
* @ClassName: ComboServiceImpl    
* @Description:套餐实现   
* @author: liuzhihao   
* @date: 2017年2月20日 上午10:00:30
 */
@Service
public class ComboServiceImpl implements ComboService {

	//套餐实现类的报错日志
	private final Logger log = Logger.getLogger(ComboServiceImpl.class);
	
	//注入套餐信息dao
	@Autowired
	private SellerPackageDao sellerPackageDao;
	
	//注入配置文件service
	@Autowired
	private PropertiesUtil propertiesUtil;
	
	//注入订单dao
	@Autowired
	private BillDao billDao;
	
	private MapResponse response;
	
	//注入服务器地址
	@Autowired
	private String fileUrl;
	
	/**
	 * 查询套餐列表
	 */
	@Override
	public List<ComboListResponse> findAllByRanges(Double lat,Double lon,Integer cityId,Integer tradeId) throws Exception{
		
		List<ComboListResponse> response = new ArrayList<ComboListResponse>();
		
		//套餐跳转地址
		String url = "";
		try{
			url = propertiesUtil.getValue("comboUrl", "conf_common.properties");
		}catch(Exception e){
			e.printStackTrace();
			log.info("获取套餐跳转地址异常");
		}
		
		//查询套餐列表信息
		List<Map<Object,Object>> combos = sellerPackageDao.findAllByRanges(lat, lon,cityId,tradeId);
		
		if(combos != null && combos.size() > 0){
			
			for(Map<Object,Object> combo : combos){
				
				ComboListResponse comboResponse = new ComboListResponse();
				
				//查询店铺消费人次
				Integer consums = 100;
				try{
					consums = billDao.sumAllOrdersBySellerId(Integer.parseInt(combo.get("sellerid").toString()));
				}catch(Exception e){
					e.printStackTrace();
					log.info("查询店铺消费人数异常");
				}
				
				comboResponse.setComboId(Integer.parseInt(combo.get("id").toString()));//套餐ID
				comboResponse.setComboTitle(ObjectUtils.toString(combo.get("comboname")));//套餐标题
				Double comboprice = 
					StringUtils.isEmpty(ObjectUtils.toString(combo.get("comboprice")))?0.00:Double.parseDouble(combo.get("comboprice").toString());
				
				comboResponse.setComboPrice(BigDecimal.valueOf(comboprice).setScale(2, BigDecimal.ROUND_HALF_UP).toString());//套餐价格
				Double coinprice = 
					StringUtils.isEmpty(ObjectUtils.toString(combo.get("coinprice")))?0.00:Double.parseDouble(combo.get("coinprice").toString());
				comboResponse.setComboCoin(BigDecimal.valueOf(coinprice).setScale(2, BigDecimal.ROUND_HALF_UP).toString());//套餐鸟币价格
				
				comboResponse.setComboImage(
					StringUtils.isEmpty(ObjectUtils.toString(combo.get("comboimage")))?"":fileUrl+combo.get("comboimage"));//套餐封面图
				comboResponse.setConsums(consums);//店铺消费人次
				
				comboResponse.setComboUrl(url);//套餐跳转H5地址
				//用户坐标距离店铺距离
				
				Double ranges = StringUtils.isEmpty(ObjectUtils.toString(combo.get("ranges")))?0.0:Double.parseDouble(combo.get("ranges").toString());
				if(ranges <= 1000){
					comboResponse.setRanges(ranges+"m");
				}else{
					comboResponse.setRanges(ArithUtil.div(ranges, 1000)+"km");
				}
				
				comboResponse.setSellerId(Integer.parseInt(combo.get("sellerid").toString()));//店铺ID
				
				comboResponse.setSellerName(ObjectUtils.toString(combo.get("sellername")));//店铺名称
				comboResponse.setTradeName(ObjectUtils.toString(combo.get("tradename")));//二级分类名称
				comboResponse.setZoneName(ObjectUtils.toString(combo.get("zonename")));//商圈名称
				
				response.add(comboResponse);
			}
		}
		return response;
	}

	/**
	 * 详情推荐套餐
	 */
	@Override
	public List<ComboListResponse> recommendCombo(Double lat,Double lon,Integer cityId) throws Exception {
		//套餐跳转地址
		String url = "";
		try{
			url = propertiesUtil.getValue("comboUrl", "conf_common.properties");
		}catch(Exception e){
			e.printStackTrace();
			log.info("获取套餐跳转地址异常");
		}
		
		//套餐返回结果
		List<ComboListResponse> combos = new ArrayList<ComboListResponse>();
		//格式化时间
		SimpleDateFormat sdf = new SimpleDateFormat(" yyyy-MM-dd");
		String startDate = sdf.format(DateUtil.now())+" 00:00:00";//其实时间
		String endDate = sdf.format(DateUtil.tomorrow())+" 00:00:00";//结束时间
		//查询推荐套餐
		List<Map<Object,Object>> recommendCombos = sellerPackageDao.findRecommendCombo(lat, lon, cityId, startDate, endDate);
		
		if(recommendCombos != null && recommendCombos.size() > 0){
			
			for(Map<Object,Object> map : recommendCombos){
				ComboListResponse combo = new ComboListResponse();
				
				combo.setComboId(Integer.parseInt(map.get("id").toString()));//套餐ID
				combo.setSellerId(Integer.parseInt(map.get("sellerid").toString()));//店铺ID
				combo.setSellerName(ObjectUtils.toString(map.get("sellername")));//店铺名称
				combo.setTradeName(ObjectUtils.toString(map.get("tradename")));//分类名称
				combo.setZoneName(ObjectUtils.toString(map.get("zonename")));
				combo.setComboTitle(ObjectUtils.toString(map.get("comboname")));//套餐标题
				
				Double comboprice = 
					StringUtils.isEmpty(ObjectUtils.toString(map.get("comboprice")))?0.00:Double.parseDouble(map.get("comboprice").toString());
				
				combo.setComboPrice(BigDecimal.valueOf(comboprice).setScale(2, BigDecimal.ROUND_HALF_UP).toString());//套餐价格
				Double coinprice = 
					StringUtils.isEmpty(ObjectUtils.toString(map.get("coinprice")))?0.00:Double.parseDouble(map.get("coinprice").toString());
				combo.setComboCoin(BigDecimal.valueOf(coinprice).setScale(2, BigDecimal.ROUND_HALF_UP).toString());//套餐鸟币价格
				
				combo.setComboImage(
					StringUtils.isEmpty(
						ObjectUtils.toString(map.get("comboimage"))
						)?"":fileUrl+map.get("comboimage").toString());//套餐轮播图
				
				Double ranges = 
					StringUtils.isEmpty(ObjectUtils.toString(map.get("ranges")))?0.0:Double.parseDouble(map.get("ranges").toString());
				if(ranges <= 1000){
					combo.setRanges(ranges+"m");
				}else{
					combo.setRanges(ArithUtil.div(ranges, 1000)+"km");
				}
				
				combo.setComboUrl(url);
				combos.add(combo);
			}
			
		}
		
		return combos;
		
	}
	
	
	/**
	 * 首页推荐套餐
	 */
	@Override
	public List<ComboListResponse> homeRecommendCombo(Double lat, Double lon, Integer cityId) throws Exception {
		//套餐跳转地址
				String url = "";
				try{
					url = propertiesUtil.getValue("comboUrl", "conf_common.properties");
				}catch(Exception e){
					e.printStackTrace();
					log.info("获取套餐跳转地址异常");
				}
				
				//套餐返回结果
				List<ComboListResponse> combos = new ArrayList<ComboListResponse>();
				//格式化时间
				SimpleDateFormat sdf = new SimpleDateFormat(" yyyy-MM-dd");
				String startDate = sdf.format(DateUtil.now())+" 00:00:00";//其实时间
				String endDate = sdf.format(DateUtil.tomorrow())+" 00:00:00";//结束时间
				//查询推荐套餐
				List<Map<Object,Object>> recommendCombos = sellerPackageDao.findHomeRecommendCombo(lat, lon, cityId, startDate, endDate);
				
				if(recommendCombos != null && recommendCombos.size() > 0){
					
					for(Map<Object,Object> map : recommendCombos){
						ComboListResponse combo = new ComboListResponse();
						
						combo.setComboId(Integer.parseInt(map.get("id").toString()));//套餐ID
						combo.setSellerId(Integer.parseInt(map.get("sellerid").toString()));//店铺ID
						combo.setSellerName(ObjectUtils.toString(map.get("sellername")));//店铺名称
						combo.setTradeName(ObjectUtils.toString(map.get("tradename")));//分类名称
						combo.setZoneName(ObjectUtils.toString(map.get("zonename")));
						combo.setComboTitle(ObjectUtils.toString(map.get("comboname")));//套餐标题
						
						Double comboprice = 
							StringUtils.isEmpty(ObjectUtils.toString(map.get("comboprice")))?0.00:Double.parseDouble(map.get("comboprice").toString());
						
						combo.setComboPrice(BigDecimal.valueOf(comboprice).setScale(2, BigDecimal.ROUND_HALF_UP).toString());//套餐价格
						Double coinprice = 
							StringUtils.isEmpty(ObjectUtils.toString(map.get("coinprice")))?0.00:Double.parseDouble(map.get("coinprice").toString());
						combo.setComboCoin(BigDecimal.valueOf(coinprice).setScale(2, BigDecimal.ROUND_HALF_UP).toString());//套餐鸟币价格
						combo.setComboImage(
							StringUtils.isEmpty(
								ObjectUtils.toString(map.get("comboimage"))
								)?"":fileUrl+map.get("comboimage").toString());//套餐轮播图
						
						Double ranges = 
							StringUtils.isEmpty(ObjectUtils.toString(map.get("ranges")))?0.0:Double.parseDouble(map.get("ranges").toString());
						if(ranges <= 1000){
							combo.setRanges(ranges+"m");
						}else{
							combo.setRanges(ArithUtil.div(ranges, 1000)+"km");
						}
						
						combo.setComboUrl(url);
						combos.add(combo);
					}
					
				}
				
				return combos;
	}
	
	/**
	 * 获取套餐分类列表
	 * @return
	 * @throws IOException 
	 */
	private List<ComboTrade> getComboTrade() throws IOException{
		
		List<ComboTrade> trades = new ArrayList<ComboTrade>();
		
		String comboTrades = propertiesUtil.getValue("comboTrade", "conf_common.properties");
		
		if(StringUtils.isNotEmpty(comboTrades)){
			//json转换为数组
			JSONArray arr = JSONArray.parseArray(comboTrades);
			if(arr != null && arr.size() > 0){
				
				for(int i=0; i<arr.size(); i++){
					ComboTrade trade = new ComboTrade();
					
					trade.setTradeId(JSON.parseObject(arr.getString(i)).getInteger("id"));
					trade.setTradeName(JSON.parseObject(arr.getString(i)).getString("name"));
					
					trades.add(trade);
				}
			}
		}
		return trades;
	}

	/**
	 * 获取推荐套餐
	 */
	@Override
	public Object getRecommendCombo(RecomboRequest recomboRequest) {
		
		//返回结果集
		Map<Object,Object> map = new HashMap<Object,Object>();
		//查询今日套餐推荐
		List<ComboListResponse> combos = new ArrayList<ComboListResponse>();
		try {
			//非首页推荐
			combos= recommendCombo(recomboRequest.getLat(), recomboRequest.getLon(), recomboRequest.getCityId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info("获取今日套餐推荐异常");
			return new BaseResponse(ResponseCode.FAILURE,"获取今日推荐套餐异常");
		}
		
		map.put("recombos", combos);
		
		//获取套餐分类
		List<ComboTrade> trades = new ArrayList<ComboTrade>();
		try {
			trades = getComboTrade();
		} catch (IOException e) {
			e.printStackTrace();
			log.info("获取套餐分类异常");
		}
			
		map.put("trades", trades);	
			
		response = new MapResponse(ResponseCode.SUCCESS,"查询套餐推荐成功");
		response.setResponse(map);
		return response;
	}

	/**
	 * 获取套餐列表
	 */
	@Override
	public Object getComboList(RecomboRequest recomboRequest) {
		
		//返回结果集
		Map<Object,Object> map = new HashMap<Object,Object>();
		
		//查询套餐列表
		try {
			List<ComboListResponse> combos 
				= findAllByRanges(recomboRequest.getLat(), recomboRequest.getLon(),recomboRequest.getCityId(),recomboRequest.getTradeId());
			map.put("combos", combos);
			response = new MapResponse(ResponseCode.SUCCESS,"查询套餐列表成功");
			response.setResponse(map);
			return response;
		
		} catch (Exception e) {
			e.printStackTrace();
			log.info("查询套餐列表异常");
			
			return new BaseResponse(ResponseCode.FAILURE,"获取套餐列表异常");
		}
		
	}
	
}
