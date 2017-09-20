package com.xmniao.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.common.XmnConstants;
import com.xmniao.dao.WalletRecordMapper;
import com.xmniao.service.LedgerDeductionService;
import com.xmniao.service.ModifyService;
import com.xmniao.thrift.ledger.FailureException;
import com.xmniao.thrift.ledger.ResponseData;

/**
 * 
 * 
 * 项目名称：payService
 * 
 * 类名称：LedgerDeductionServiceImpl
 * 
 * 类描述： 已分账订单分账扣回
 * 
 * 创建人： Chen Bo
 * 
 * 创建时间：2016年7月28日 下午6:35:45 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@Service
public class LedgerDeductionServiceImpl implements LedgerDeductionService {

    /**
     * 初始日志类
     */
    private Logger log = LoggerFactory.getLogger(LedgerDeductionServiceImpl.class);
    
    @Autowired
    private WalletRecordMapper walletRecordMapper;
    
    @Autowired
    private ModifyService modifyService;
    
    /**
     * 消费订单
     * @throws FailureException 
     */
	@Override
	public ResponseData deductionsXmnOrderLedger(Map<String,String> param) throws FailureException {
		
		log.info("deductionsXmnOrderLedger:"+param);
		Map<String, Object> map = new HashMap<String, Object>();
		
		String orderType = param.get("orderType");//订单类型
		
		map.put("remarks", param.get("orderId")+"%");
		
		if(orderType.equals("1")){
			map.put("rtype", XmnConstants.RTYPE_21);
			//消费订单
		}else if(orderType.equals("2")){
			//线下积分订单
			map.put("rtype",XmnConstants.RTYPE_33);
		}else if(orderType.equals("3")){
			//线上积分订单
			return new ResponseData(2,"线上积分订单暂无分账退回功能",null);
		}else{
			return new ResponseData(2,"订单类型错误",null);
		}
		
		//1.查询该订单是否已经进行分账扣回
		boolean flag = walletRecordMapper.getRecordCount(map) > 0 ? true
				: false;
		if(flag){
			log.info("该订单已进行分账扣回"+param);
			return new ResponseData(0,"该订单已进行分账扣回:",null);
		}
		
		ResponseData responseData = modifyService.deductionsWallet2(param);
	
		return responseData;
	}

	/**
	 * 线下订单
	 */
	@Override
	public ResponseData deductionsOfflineOrderLedger(String orderId)  throws FailureException{
		
		return null;
	}

	/**
	 * 线上订单
	 */
	@Override
	public ResponseData deductionsOnlineOrderLedger(String orderId)  throws FailureException{
		
		return null;
	}

}
