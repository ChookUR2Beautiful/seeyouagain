/**
 * 
 */
package com.xmniao.xmn.core.xmer.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.Constant;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.BalanceDetailInfoRequest;
import com.xmniao.xmn.core.verification.dao.WalletDao;
import com.xmniao.xmn.core.xmer.dao.WalletRecordDao;

/**
 * 项目名称：xmnService
 * 
 * 类名称：BalanceDetailInfoService
 * 
 * 创建人： huang'tao
 * 
 * 创建时间：2016-5-19下午6:27:18
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@Service
public class BalanceDetailInfoService {
	
	private final Logger log = Logger.getLogger(BalanceDetailInfoService.class);
	
	/**
	 * 注入Dao层
	 */
	@Autowired
	private WalletRecordDao walletRecordDao;
	
	/**
	 * 注入Dao层
	 */
	@Autowired
	private WalletDao walletDao;
	
	/**
	 * 注入缓存
	 */
	@Autowired
	private SessionTokenService sessionTokenService;
	
	private MapResponse response = null;
	
	/**
	 * 获取余额明细信息
	 * @param balanceInfoRequest
	 * @return
	 */
	public Object getBalancesInfo(BalanceDetailInfoRequest balanceInfoRequest){
		String uid = sessionTokenService.getStringForValue(balanceInfoRequest.getSessiontoken()).toString();//获取寻蜜客id
		if(StringUtils.isEmpty(uid)){
			return new BaseResponse(ResponseCode.TOKENERR, "token已过期,请重新登录");
		}
		Map<Object,Object> map = new HashMap<Object,Object>();//查询map
		Map<Object, Object> wallet= null;
		try{
			wallet = walletDao.getXmerWallet(Integer.valueOf(uid));//查询寻蜜客钱包
		}catch(Exception e){
			e.printStackTrace();
			log.info("查询钱包id异常");
		}
		List<Map<Object,Object>> balances = new ArrayList<Map<Object,Object>>();
		if(wallet != null){
			Integer xid= Integer.parseInt(String.valueOf(wallet.get("id")));//钱包id
			map.put("xid", xid);//寻蜜客钱包ID
//			map.put("rtype", 0);//类型2saas分账 0 消费分账	两个都要查询	 1转出  不需要
			map.put("page", balanceInfoRequest.getPage());//页数
			map.put("type", balanceInfoRequest.getType());//时间类型 0:前三个月 1：近三个月
			map.put("limit", Constant.PAGE_LIMIT);//每页条数
			try{
				List<Map<Object,Object>> xmkWalletRecodeList= walletRecordDao.queryXmkWalletByRtype(map);
				if(xmkWalletRecodeList == null||xmkWalletRecodeList.size()==0){
					map.clear();//清空map
					xmkWalletRecodeList = new ArrayList<Map<Object,Object>>(); 
					map.put("balances", xmkWalletRecodeList);
					response = new MapResponse(ResponseCode.SUCCESS,"查询成功");
					response.setResponse(map);
					return response;
				}else{
					for(Map<Object,Object> balance : xmkWalletRecodeList){
						Map<Object,Object> balanceMap = new HashMap<Object,Object>();
						balanceMap.put("name", balance.get("description"));
						balanceMap.put("zdate", balance.get("sdate"));
						
						balanceMap.put("amount", "+"+balance.get("profit"));
						if(balance.get("rtype").toString().equals("0")||balance.get("rtype").toString().equals("2")){
							balanceMap.put("amount", "+"+balance.get("profit"));
						}else{
							balanceMap.put("amount", "-"+balance.get("profit"));
						}
						
						if(balance.get("rtype").toString().equals("0")){
							balanceMap.put("status", "寻蜜鸟分账");
						}else if(balance.get("rtype").toString().equals("1")){
							balanceMap.put("status", "转出");
						}else if(balance.get("rtype").toString().equals("2")){
							balanceMap.put("status", "SAAS分账");
						}else if(balance.get("rtype").toString().equals("3")){
							balanceMap.put("status", "寻蜜鸟分账退回");
						}else if(balance.get("rtype").toString().equals("4")){
							balanceMap.put("status", "SAAS分账退回");
						}
						
						balances.add(balanceMap);
					}
					map.clear();//清空map
					map.put("balances", balances);
					response = new MapResponse(ResponseCode.SUCCESS,"查询成功");
					response.setResponse(map);
					return response;
				}
			}catch(Exception e){
				e.printStackTrace();
				log.info("查询用户钱包记录表异常");
				return new BaseResponse(ResponseCode.FAILURE,"查询异常");
			}
		}else{
			map.put("balances", balances);
			response = new MapResponse(ResponseCode.SUCCESS,"查询成功");
			response.setResponse(map);
			return response;
		}
	} 
	
}
