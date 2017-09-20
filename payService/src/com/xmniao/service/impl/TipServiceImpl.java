package com.xmniao.service.impl;

import java.math.BigDecimal;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.xmniao.common.StateCodeUtil;
import com.xmniao.common.UpdateLedgerSystem;
import com.xmniao.dao.WalletMapper;
import com.xmniao.dao.WalletRecordMapper;
import com.xmniao.entity.Wallet;
import com.xmniao.service.TipService;
import com.xmniao.service.pay.SynthesizeServiceImpl;
@Service
public class TipServiceImpl implements TipService {
	
	
	private final Logger log = Logger.getLogger(TipServiceImpl.class);
	
	@Autowired
	private WalletServiceImpl walletServiceImpl ;
	@Autowired
	private WalletMapper walletMapper;
	@Autowired
	private WalletRecordMapper walletRecordMapper;
	@Autowired
	private UpdateLedgerSystem updateLedgerSystem;
	@Autowired
	private SynthesizeServiceImpl synthesizeServiceImpl;
	
	/**
	 * 打赏
	 * @param paramMap
	 * @throws Exception
	 */
	@Override
	public void  tip(Map<String,String> paramMap) throws Exception{
		
		log.info("打赏开始，参数为："+paramMap);
		Map<String, String> result = new HashMap<String, String>();
		result.put("status", "6");
		result.put("msg", "打赏异常");
		try {
			//更新钱包
			 this.updateWallet(paramMap,result);
		} catch (Exception e) {
			log.error("打赏异常 ",e);
		}
		
			//更新分账打赏状态 
			int ledger = updateLedgerSystem.updateLedgerTipState(result);
			if (ledger == 0) {
				throw new Exception("分账更新打赏状态失败!");
			}
		log.info("打赏结束");
		
	}
	
	/**
	 * 打赏更新钱包
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional(rollbackFor = {Exception.class})
	public void updateWallet(Map<String,String> paramMap,Map<String,String> result) throws Exception{

		// 返回分账状态
		String status = "";
		// 结果信息
		String msg = "";
		// 钱包记录表 remarks = orderId_userType
		String remarks = "";
		// 钱包记录表 订单数 校验是否存在订单 是否已打赏
		int orderCount = 0;
		// 钱包对象
		Wallet wallet = null;
		// 打赏人Id
		String uId = "";
		// 被打赏人Id
		String waiterId = "";
		// 更新钱包返回
		Map<String, String> walletResult = null;
		// 返回结果map
		
		result.put("orderid", paramMap.get("order_code"));
		// 钱包记录 Map
		Map<String, Object> walletMap = new HashMap<String, Object>();
		walletMap.put("userType", "1");

		remarks = paramMap.get("order_code") + "_" + walletMap.get("userType");
		uId = paramMap.get("uid");
		waiterId = paramMap.get("waiter_id");

		// 检查订单数 校验是否存在订单 是否已打赏
		orderCount = walletMapper.checkReward(remarks);
		if (orderCount == 0) { // 不存在订单
			status = "6";
			msg = "不存在remarks为：" + remarks + "的订单";
			result.put("status", status);
			result.put("msg", msg);
			log.error(msg);
			return;
		} else if (orderCount != 1) {// 已打赏
			status = "4";
			msg = "打赏成功";
			result.put("status", status);
			result.put("msg", msg);
			log.error("remarks为：" + remarks + "的订单  已存在打赏记录");
			return;
		}

		// 更新打赏人钱包

		walletMap.put("uId", uId);
		walletMap.put("userType", "1");
		walletMap.put("amount", BigDecimal.valueOf(0));
		walletMap.put("balance",
				BigDecimal.valueOf(Double.valueOf("-" + paramMap.get("tip"))));
		walletMap.put("commision", BigDecimal.valueOf(0));
		walletMap.put("zbalance", BigDecimal.valueOf(0));
		walletMap.put("integral", BigDecimal.valueOf(0));
		walletMap.put("seller_amount", BigDecimal.valueOf(0));
		walletMap.put("rtype", 11);
		walletMap.put("remarks", paramMap.get("order_code") + "_1");
		walletMap.put("description","uid:"+uId + "打赏给uid:" + waiterId + " ￥"+paramMap.get("tip") + "元");
		walletMap.put("sdate", paramMap.get("time"));
		wallet = walletMapper.getWalletByuId(walletMap); // 通过用户号/商户号/合作商查询钱包
		if (wallet == null || wallet.getStatus() != 1) {
			// 找不到钱包或 钱包状态被锁定或注销！
			status = "6";
			msg = "钱包状态被锁定或注销，无法使用！，uId:" + uId + ",remarks:"+ remarks;
			result.put("status", status);
			result.put("msg", msg);
			throw new Exception(msg);
		}

		if (wallet.getBalance().compareTo(
				BigDecimal.valueOf(Double.valueOf(paramMap.get("tip")))) == -1) {

			status = "6";
			msg = "钱包返利金额不足    返利金额："+ wallet.getBalance().setScale(2) + "打赏金额为："+ paramMap.get("tip") + "，uId:" + uId + ",remarks:"+ remarks;
			result.put("status", status);
			result.put("msg", msg);
			throw new Exception(msg);
		}

		log.info("当前钱包信息："+ JSON.toJSONStringWithDateFormat(wallet, "yyyy-MM-dd HH:mm:ss"));
		// 更新打赏人钱包
		walletResult = walletServiceImpl.updateWallet(wallet, walletMap);
		if (!StateCodeUtil.PR_SUCCESS.equals(walletResult.get("code"))) {
			status = "5";
			msg = "更新打赏人钱包错误";
			result.put("status", status);
			result.put("msg", msg);
			throw new Exception("更新钱包错误 ，uId:" + uId + ",remarks:" + remarks);
		}

		// 更新waiter_id 钱包

		walletMap.put("uId", waiterId);
		walletMap.put("balance",
				BigDecimal.valueOf(Double.valueOf(paramMap.get("tip"))));
		walletMap.put("remarks", paramMap.get("order_code") + "_2");
		walletMap.put("description","uid:"+waiterId + "得到uid:" + uId + "的  ￥" + paramMap.get("tip") + "元打赏");
		wallet = walletMapper.getWalletByuId(walletMap); // 通过用户号/商户号/合作商查询钱包
		if (wallet == null) {
			int st = synthesizeServiceImpl.checkandaddwallet(waiterId,"1", "", paramMap.get("s_nname"));
			if(st!=0){
				status = "5";
				msg = "新增 uid: "+waiterId+"钱包,返回状态错误";
				result.put("status", status);
				result.put("msg", msg);
				throw new Exception("新增 uid: "+waiterId+"钱包,返回状态为："+st+"  | 0:新增成功正常, 1：操作异常, 2：传入参数错误, 3：已有记录，请勿重复添加");
			}
			wallet = walletMapper.getWalletByuId(walletMap);
		}
		log.info("当前钱包信息："+ JSON.toJSONStringWithDateFormat(wallet, "yyyy-MM-dd HH:mm:ss"));
		// 更新被打赏人钱包
		walletResult = walletServiceImpl.updateWallet(wallet, walletMap);

		if (!StateCodeUtil.PR_SUCCESS.equals(walletResult.get("code"))) {
			
			status = "5";
			msg = "更新被打赏人钱包错误";
			result.put("status", status);
			result.put("msg", msg);
			throw new Exception("更新钱包错误 ，waiterId:" + waiterId + ",remarks:"+ remarks);
		}

		status = "4";
		msg = "打赏成功";
		result.put("status", status);
		result.put("msg", msg);
		log.info(msg);
	}
	
	
	public static void main(String[] args) {

		 FileSystemXmlApplicationContext context = new FileSystemXmlApplicationContext(new String[] {"WebRoot/WEB-INF/pay-context.xml","WebRoot/WEB-INF/pay-service.xml"}, true);
	     context.start();
	     
	     TipServiceImpl ts = context.getBean(TipServiceImpl.class);
	     String message = "{\"id\":381,\"order_code\":250124000888,\"s_nname\":\"\",\"s_phone\":\"13152128704\",\"time\":\"2015-04-01 18:06:04\",\"tip\":2.00,\"u_nname\":\"鲁东瘦牛\",\"u_phone\":\"18565600130\",\"uid\":232,\"waiter_id\":100}";
	     Map<String, String> paramMap = JSON.parseObject(message,new TypeReference<Map<String, String>>(){});
	     try {
			ts.tip(paramMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
}
