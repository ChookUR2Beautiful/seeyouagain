/**    
 * 文件名：OrderLedgerInfoService.java    
 *    
 * 版本信息：    
 * 日期：2017年3月23日    
 * Copyright (c) 广东寻蜜鸟网络技术有限公司  2017     
 * 版权所有    
 *    
 */
package com.xmniao.service.seller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.xmniao.dao.order.OrderServiceDao;
import com.xmniao.domain.order.BillBean;
import com.xmniao.service.sellerOrder.SellerOrderServiceImpl;
import com.xmniao.thrift.busine.common.FailureException;
import com.xmniao.thrift.busine.common.ResponseData;



/**
 * 
 * 项目名称：busineService
 * 
 * 类名称：OrderLedgerInfoService
 * 
 * 类描述： 获取商家营收订单的支付及分账明细
 * 
 * 创建人： Chen Bo
 * 
 * 创建时间：2017年3月23日 下午6:27:21 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service("orderLedgerInfoService")
public class OrderLedgerInfoService {
	
    /**
     * 日志记录
     */
    private final Logger log = Logger.getLogger(OrderLedgerInfoService.class);
	
    /**
     * 注入订单DAO层
     */
    @Autowired
    private OrderServiceDao orderDao;
    
    
    @Autowired
    private SellerOrderServiceImpl sellerOrderService;
    
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    /**
     * 获取商家订单信息
     */
 	public ResponseData getOrderLedgerInfo(Map<String,String> paraMap) throws FailureException,
 			TException {
 		log.info("getOrderLedgerInfo:"+paraMap);
 		ResponseData responseData = new ResponseData();
 		if(StringUtils.isBlank(paraMap.get("bid"))
 		|| StringUtils.isBlank(paraMap.get("btype"))){
 			responseData.setMsg("传入参数不齐");
 			responseData.setState(1);
 			return responseData;
 		}
 		if(paraMap.get("btype").equals("1")){
 			return this.getXmnOrderLedgerInfo(paraMap.get("bid"));
 		}else if(paraMap.get("btype").equals("2")){
 			return sellerOrderService.getKillOrderLedgerInfo(paraMap.get("bid"));
 		}else if(paraMap.get("btype").equals("3")){
 			return sellerOrderService.getMicroOrderLedgerInfo(paraMap.get("bid"));
 		}else {
 			responseData.setMsg("订单类型不明确");
 			responseData.setState(1);
 			return responseData;
 		}
 	}
 	
   
	public Map<String, ResponseData> getOrderLedgerInfoList(
			List<Map<String, String>> paraList) throws FailureException,
			TException {
			Map<String,ResponseData> resultMap = new HashMap<String,ResponseData>();
			log.info("getOrderLedgerInfoList:"+paraList);
			List<String> xmnBidList = new ArrayList<String>();
			List<Map<String,String>> ortherBillList = new ArrayList<Map<String,String>>();
			for(Map<String,String> paraMap:paraList){
				if(paraMap==null || StringUtils.isBlank(paraMap.get("btype")) || StringUtils.isBlank(paraMap.get("bid"))){
					if(paraMap!=null && !StringUtils.isBlank(paraMap.get("bid"))){
						ResponseData responseData = new ResponseData(1,"查询信息错误",null);
						resultMap.put(paraMap.get("bid"), responseData);
					}else{
						log.info("无法处理的错误请求信息："+paraMap);
					}
				}else if(paraMap.get("btype").equals("1")){
					xmnBidList.add(paraMap.get("bid"));
				}else{
					ortherBillList.add(paraMap);
				}
			}
			if(xmnBidList.size()>0){
				List<BillBean> list = orderDao.getBillBeanList(xmnBidList);
				for(BillBean billBean:list){
					ResponseData rData = null;
					rData = this.getXmnOrderLedgerInfo(billBean);
					resultMap.put(billBean.getBid()+"", rData);
				}
			}
			
			for(Map<String,String> paraMap:ortherBillList){
				ResponseData responseData = new ResponseData();
				String bid = paraMap.get("bid");
				if(StringUtils.isBlank(bid)){
					continue;
				}
				if(StringUtils.isBlank(paraMap.get("btype"))){
					responseData.setMsg("传入参数不齐");
					responseData.setState(1);
				}else if(paraMap.get("btype").equals("1")){
					responseData= this.getXmnOrderLedgerInfo(bid);
				}else if(paraMap.get("btype").equals("2")){
					responseData= sellerOrderService.getKillOrderLedgerInfo(bid);
				}else if(paraMap.get("btype").equals("3")){
					responseData = sellerOrderService.getMicroOrderLedgerInfo(bid);
				}else {
					responseData.setMsg("订单类型不明确");
					responseData.setState(1);
				}
				resultMap.put(bid, responseData);
			}
			
//			
//			List<String> xmnBidList = new ArrayList<String>();
//			List<String> killBidList = new ArrayList<String>();
//			List<String> microBidList = new ArrayList<String>();
//			for(Map<String,String> paraMap:paraList){
//				if(paraMap==null || StringUtils.isBlank(paraMap.get("btye")) || StringUtils.isBlank(paraMap.get("bid"))){
//					if(paraMap!=null && !StringUtils.isBlank(paraMap.get("bid"))){
//						ResponseData responseData = new ResponseData(1,"查询信息错误",null);
//						resultMap.put(paraMap.get("bid"), responseData);
//					}else{
//						log.info("无法处理的错误请求信息："+paraMap);
//					}
//				}else if(paraMap.get("btype")=="1"){
//					xmnBidList.add(paraMap.get("bid"));
//				}else if(paraMap.get("btype")=="2"){
//					killBidList.add(paraMap.get("bid"));
//				}else if(paraMap.get("btype")=="3"){
//					microBidList.add(paraMap.get("bid"));
//				}else{
//					ResponseData responseData = new ResponseData(1,"查询信息错误",null);
//					resultMap.put(paraMap.get("bid"), responseData);
//				}
//			}
//			if(xmnBidList.size()>0){
//				List<BillBean> list = orderDao.getBillBeanList(xmnBidList);
//				for(BillBean billBean:list){
//					ResponseData rData = null;
//					rData = this.getXmnOrderLedgerInfo(billBean);
//					resultMap.put(billBean.getBid()+"", rData);
//				}
//			}
//			
//			for(String bid:killBidList){
//				ResponseData rData = null;
//				try{
//					sellerOrderService.getKillOrderLedgerInfo(bid);
//				}catch(Exception e){
//					log.error("查询秒杀订单【"+bid+"】异常",e);
//					rData = new ResponseData(2, "获取订单信息异常", null);
//				}
//				resultMap.put(bid, rData);
//			}
//			for(String bid:microBidList){
//				ResponseData rData = null;
//				try{
//					sellerOrderService.getMicroOrderLedgerInfo(bid);
//				}catch(Exception e){
//					log.error("查询面对面扫码订单【"+bid+"】异常",e);
//					rData = new ResponseData(2, "获取订单信息异常", null);
//				}
//				resultMap.put(bid, rData);
//			}
			
		return resultMap;
	}

	public ResponseData getXmnOrderLedgerInfo(BillBean billBean){
		ResponseData responseData = new ResponseData();
		try{
		if(billBean==null || billBean.getStatus()==0){
			if(billBean==null){
				responseData.setMsg("订单不存在");
			}else{
				responseData.setMsg("订单尚未支付");
			}
			responseData.setState(1);
			return responseData;
		}
		String json = billBean.getCommission();
		JSONObject jsonObject = JSONObject.parseObject(json);
		if(jsonObject==null){
			jsonObject= new JSONObject();
		}
		Map<String,String> resultMap= new HashMap<String,String>();
		resultMap.put("bid", String.valueOf(billBean.getBid()));
		resultMap.put("paytype", String.valueOf(billBean.getPaytype()));
		resultMap.put("money", String.valueOf(billBean.getMoney()));
		resultMap.put("payamount", String.valueOf(billBean.getCalculateRealPayAmount()));
		resultMap.put("wamount", String.valueOf(billBean.getCalculateRealPayAmount().subtract(billBean.getPayment())));
		resultMap.put("samount", String.valueOf(billBean.getCalculateThirdAmount()));
		resultMap.put("preferential", String.valueOf(billBean.getCalculateRealPreferentialAmount()));
		resultMap.put("zinteger", String.valueOf(billBean.getIntegral()));
		if(billBean.getPaytype().equals("1000015")||billBean.getPaytype().equals("1000020")){
			double agio = billBean.getBaseagio();
			if(billBean.getLiveCoinRatio()<=0.3){
				agio = agio+billBean.getLiveCoinRatio();
				resultMap.put("liveCoinRatio", "1.00:"+agio);
			}else{
				resultMap.put("liveCoinRatio", "1.00:1.42");
			}
		}else{
			resultMap.put("liveCoinRatio", String.valueOf(billBean.getLiveCoinRatio()));
		}
		resultMap.put("liveCoin", String.valueOf(billBean.getLiveCoin().add(billBean.getSellerCoin()==null?BigDecimal.ZERO:billBean.getSellerCoin())));
		resultMap.put("liveCoinRatio", String.valueOf(billBean.getLiveCoinRatio()));
		resultMap.put("codeid", String.valueOf(billBean.getCodeid()));
		
		String proportion = jsonObject.getString("proportion")==null?"":jsonObject.getString("proportion");
		String[] retios = proportion.split(","); 
		resultMap.put("ledgerratio", String.valueOf(retios.length>=2?retios[1]:billBean.getBaseagio()));
		resultMap.put("ledgeramount", String.valueOf(billBean.getCalculateRealLedgerAmount()));
		resultMap.put("selleramount", String.valueOf(jsonObject.getString("seller_amount")==null?"0.00":jsonObject.getString("seller_amount")));
		resultMap.put("expectedselleramount", String.valueOf(jsonObject.getString("expected_seller_amount")==null?resultMap.get("selleramount"):jsonObject.getString("expected_seller_amount")));
		resultMap.put("sellercommision", String.valueOf(jsonObject.getString("seller_money")==null?"0.00":jsonObject.getString("seller_money")));
		resultMap.put("jointamount", String.valueOf(jsonObject.getString("cpartner_amount")==null?"0.00":jsonObject.getString("cpartner_amount")));
		resultMap.put("xmeramount", String.valueOf(jsonObject.getString("mike_amount")==null?"0.00":jsonObject.getString("mike_amount")));
		resultMap.put("txmeramount", String.valueOf(jsonObject.getString("top_mike_amount")==null?"0.00":jsonObject.getString("top_mike_amount")));
		resultMap.put("pxmeramount", String.valueOf(jsonObject.getString("parent_mike_amount")==null?"0.00":jsonObject.getString("parent_mike_amount")));
		resultMap.put("platformamount", String.valueOf(jsonObject.getString("platform_amount")==null?"0.00":jsonObject.getString("platform_amount")));
		
		responseData.setState(0);
		responseData.setMsg("查询成功");
		responseData.setResultMap(resultMap);
		}catch(Exception e){
			log.error("查询异常",e);
			responseData.setState(2);
			responseData.setMsg("查询异常");
		}
		return responseData;
	
	}
	public ResponseData getXmnOrderLedgerInfo(String bid){
//		ResponseData responseData = new ResponseData();
//		try{
		BillBean billBean = orderDao.getBillBean(bid);
		return this.getXmnOrderLedgerInfo(billBean);
//		if(billBean==null || billBean.getStatus()==0){
//			if(billBean==null){
//				responseData.setMsg("订单不存在");
//			}else{
//				responseData.setMsg("订单尚未支付");
//			}
//			responseData.setState(1);
//			return responseData;
//		}
//		String json = billBean.getCommission();
//		JSONObject jsonObject = JSONObject.parseObject(json);
//		if(jsonObject==null){
//			jsonObject= new JSONObject();
//		}
//		Map<String,String> resultMap= new HashMap<String,String>();
//		resultMap.put("bid", String.valueOf(billBean.getBid()));
//		resultMap.put("paytype", String.valueOf(billBean.getPaytype()));
//		resultMap.put("money", String.valueOf(billBean.getMoney()));
//		resultMap.put("payamount", String.valueOf(billBean.getCalculateRealPayAmount()));
//		resultMap.put("wamount", String.valueOf(billBean.getCalculateRealPayAmount().subtract(billBean.getPayment())));
//		resultMap.put("samount", String.valueOf(billBean.getCalculateThirdAmount()));
//		resultMap.put("preferential", String.valueOf(billBean.getCalculateRealPreferentialAmount()));
//		resultMap.put("zinteger", String.valueOf(billBean.getIntegral()));
//		resultMap.put("liveCoin", String.valueOf(billBean.getLiveCoin()));
//		resultMap.put("liveCoinRatio", String.valueOf(billBean.getLiveCoinRatio()));
//		resultMap.put("codeid", String.valueOf(billBean.getCodeid()));
//		
//		String proportion = jsonObject.getString("proportion")==null?"":jsonObject.getString("proportion");
//		String[] retios = proportion.split(","); 
//		resultMap.put("ledgerratio", String.valueOf(retios.length>=2?retios[1]:billBean.getBaseagio()));
//		resultMap.put("ledgeramount", String.valueOf(billBean.getCalculateRealLedgerAmount()));
//		resultMap.put("selleramount", String.valueOf(jsonObject.getString("seller_amount")==null?"0.00":jsonObject.getString("seller_amount")));
//		resultMap.put("expectedselleramount", String.valueOf(jsonObject.getString("expected_seller_amount")==null?resultMap.get("selleramount"):jsonObject.getString("expected_seller_amount")));
//		resultMap.put("sellercommision", String.valueOf(jsonObject.getString("seller_money")==null?"0.00":jsonObject.getString("seller_money")));
//		resultMap.put("jointamount", String.valueOf(jsonObject.getString("cpartner_amount")==null?"0.00":jsonObject.getString("cpartner_amount")));
//		resultMap.put("xmeramount", String.valueOf(jsonObject.getString("mike_amount")==null?"0.00":jsonObject.getString("mike_amount")));
//		resultMap.put("txmeramount", String.valueOf(jsonObject.getString("top_mike_amount")==null?"0.00":jsonObject.getString("top_mike_amount")));
//		resultMap.put("pxmeramount", String.valueOf(jsonObject.getString("parent_mike_amount")==null?"0.00":jsonObject.getString("parent_mike_amount")));
//		resultMap.put("platformamount", String.valueOf(jsonObject.getString("platform_amount")==null?"0.00":jsonObject.getString("platform_amount")));
//		
//		responseData.setState(0);
//		responseData.setMsg("查询成功");
//		responseData.setResultMap(resultMap);
//		}catch(Exception e){
//			log.error("查询异常",e);
//			responseData.setState(2);
//			responseData.setMsg("查询异常");
//		}
//		return responseData;
	
	}


}
