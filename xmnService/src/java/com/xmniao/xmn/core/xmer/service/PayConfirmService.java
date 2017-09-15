package com.xmniao.xmn.core.xmer.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.PayConfirmRequest;
import com.xmniao.xmn.core.util.ArithUtil;
import com.xmniao.xmn.core.xmer.dao.PayConfirmDao;

@Service
public class PayConfirmService {

	
	@Autowired
	private String localDomain;
	
	//注入持久层Dao
	@Autowired
	private PayConfirmDao payConfirmDao;
	
	public Object payConfirm(PayConfirmRequest payConfirmRequest){
		Map<Object,Object> amountMap=null;//订单金额
		Map<Object,Object> map = null;//商家信息
		try{
			//获取订单金额 
			amountMap = payConfirmDao.queryOrderAmount(payConfirmRequest.getOrdersn());
		}catch(Exception e){
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE,"获取订单金额异常!检查订单ID是否正确^_^"+e.getMessage());
		}
		if(amountMap == null){
			return new BaseResponse(ResponseCode.IS_PAY_ORDER_SUCCESS,"支付失败，你的订单不存在，查询订单号是否正确");
		}
		Integer sellerid = Integer.valueOf(amountMap.get("sellerid").toString());
		try{
			//获取商铺信息
			map = payConfirmDao.queryOrderSellerInfo(sellerid);
			if (map != null && map.size() > 0) {
				map.put("agio", ArithUtil.mul(Double.parseDouble(map.get("agio").toString()), 10));
			}
		}catch(Exception e){
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE,"获取商铺信息异常!检查商户ID是否正确...^_^"+e.getMessage());
		}
		if(map == null){
			return new BaseResponse(ResponseCode.IS_PAY_ORDER_SUCCESS,"查询商铺信息失败");
		}
		//获取商铺地址
		Map<Object,Object> areaMap = new HashMap<Object,Object>();
		//省名称
		areaMap.put("areaid", map.get("province"));
		areaMap.put("pid", 0);
		String provinceName = payConfirmDao.querySellerAreaName(areaMap);
		if(StringUtils.isEmpty(provinceName)){
			provinceName = "";
		}
		//市名称
		areaMap.put("areaid", map.get("city"));
		areaMap.put("pid", map.get("province"));
		String cityName = payConfirmDao.querySellerAreaName(areaMap);
		if(StringUtils.isEmpty(cityName)){
			cityName = "";
		}
		//区名称
		areaMap.put("areaid", map.get("area"));
		areaMap.put("pid", map.get("city"));
		String areaName = payConfirmDao.querySellerAreaName(areaMap);
		if(StringUtils.isEmpty(areaName)){
			areaName = "";
		}
		map.put("agio", map.get("agio"));
		map.put("province", provinceName);//省名称
		map.put("city", cityName);//市名称
		map.put("area", areaName);//区名称
		map.put("amount", amountMap.get("amount"));//订单金额
		map.put("orderid", payConfirmRequest.getOrdersn());//订单id
		map.put("payBasePath", localDomain);//conf_redis.properties配置的根路径
		MapResponse response = new MapResponse(ResponseCode.SUCCESS,"成功");
		response.setResponse(map);
		return response;
	}
}
