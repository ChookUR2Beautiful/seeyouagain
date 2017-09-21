/**    
 * 文件名：DebitcardServiceImpl.java    
 *    
 * 版本信息：    
 * 日期：2017年3月28日    
 * Copyright (c) 广东寻蜜鸟网络技术有限公司  2017     
 * 版权所有    
 *    
 */
package com.xmniao.service.live;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.dao.live.DebitcardOrderDao;
import com.xmniao.domain.live.DebitcardOrder;
import com.xmniao.service.common.CommonServiceImpl;
import com.xmniao.thrift.busine.common.FailureException;
import com.xmniao.thrift.busine.common.ResponseData;
import com.xmniao.thrift.busine.live.DebitcardService;
/**
 * 
 * 项目名称：busineService
 * 
 * 类名称：DebitcardServiceImpl
 * 
 * 类描述： 商家储值卡相关业务
 * 
 * 创建人： Chen Bo
 * 
 * 创建时间：2017年3月28日 下午4:39:02 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service
public class DebitcardServiceImpl implements DebitcardService.Iface{

	// 初始化日志类
	private final Logger log = Logger.getLogger(DebitcardServiceImpl.class);
	@Autowired
	private SellerRechargeLiveOrder sellerRechargeService;
	
	@Autowired
	private CommonServiceImpl commonService;
	@Autowired
	private DebitcardOrderDao debitcardOrderDao;
	/**
	 * 兑换商家储值卡
	 */
	@Override
	public ResponseData exchangeDebitcard(Map<String, String> paramMap)
			throws FailureException, TException {
		log.info("兑换商家储值卡："+paramMap);
		
		String orderNo = paramMap.get("orderNo");
		String payState = paramMap.get("payState");
		DebitcardOrder debitcardOrder = debitcardOrderDao.getByOrderNo(orderNo);
		if(debitcardOrder.getPayState()!= null && debitcardOrder.getPayState()==1){
			log.info("订单已支付成功。。。");
			return new ResponseData(0,"订单已更新成功", null);
		}
		Map<String,String> walletMap = new HashMap<>();
		walletMap.put("uid", debitcardOrder.getUid()+"");
		walletMap.put("zbalance", debitcardOrder.getPayCoin().negate()+"");
		walletMap.put("sellerCoin", debitcardOrder.getDenomination()+"");
		walletMap.put("rtype", "26");
		walletMap.put("remarks", orderNo);
		walletMap.put("description", "兑换商家专享卡");
		com.xmniao.thrift.pay.ResponseData responseData =commonService.updateLiveWalletInternalChange(walletMap);
		if(responseData==null || responseData.getState()!=0){
			log.info("钱包兑换储值卡失败："+responseData);
			return new ResponseData(3, "钱包兑换失败", null);
		}
		debitcardOrder.setPayTime(new Date());
		debitcardOrder.setPayState(Integer.parseInt(payState));
		debitcardOrderDao.update(debitcardOrder);
		if(payState.equals("1")){
			sellerRechargeService.initExchangeDebitcardLedger(debitcardOrder);
			sellerRechargeService.recommendExchangeDebitcardLedger(debitcardOrder);
		}

		return new ResponseData(0,"成功", null);
	}

}
