package com.xmniao.quartz.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xmniao.common.MD5;
import com.xmniao.common.UpdateLedgerSystem;
import com.xmniao.dao.LedgerMapper;
import com.xmniao.dao.UpdateWalletBalanceMapper;
import com.xmniao.quartz.AutoLedgerService;
import com.xmniao.service.CommonService;
import com.xmniao.thrift.ledger.FailureException;

/**
 * 自动分账监听
 * 
 * @author YangJing
 *
 */
@Transactional
@Service("autoLedgerService")
public class AutoLedgerServiceImpl implements AutoLedgerService {

	 /**
     * 日志记录
     */
    private final Logger log = Logger.getLogger(AutoLedgerServiceImpl.class);
    
    @Autowired
    private CommonService commonService;
	
	@Autowired
	private LedgerMapper ledgerMapper;
	
	@Autowired
	private UpdateWalletBalanceMapper updateWalletBalanceMapper;

	 @Autowired
	 private UpdateLedgerSystem updateLedgerSystem;
	
	@Override
	@Transactional(rollbackFor = { FailureException.class, Exception.class })
	public void autoLedger() throws FailureException {
		
		log.info("自动分账开始--->");
		
		long sdate = System.currentTimeMillis();
		
		log.info("删除重复订单");
		int delRow = ledgerMapper.deleteRepeatLedger();
		log.info("删除完毕，返回值："+delRow);
		
		List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
		
		List<Map<String, Object>> ledgerList = ledgerMapper.getLedgerOrder();
		log.info("分账订单ledgerList:"+ledgerList);
		
		for(Map<String, Object> ledger : ledgerList){
			
//			String id = String.valueOf(ledger.get("id"));
			String orderId = String.valueOf(ledger.get("orderId"));
			String uId = String.valueOf(ledger.get("uId"));
			String uMoney = String.valueOf(ledger.get("uMoney"));
			String sId = String.valueOf(ledger.get("sId"));
			String sMoney = String.valueOf(ledger.get("sMoney"));
			String mId = String.valueOf(ledger.get("mId"));
			String mMoney = String.valueOf(ledger.get("mMoney"));
			String mType = String.valueOf(ledger.get("mType"));
			String brId = String.valueOf(ledger.get("brId"));
			String brMoney = String.valueOf(ledger.get("brMoney"));
			String crId = String.valueOf(ledger.get("crId"));
			String crMoney = String.valueOf(ledger.get("crMoney"));
			String remark = String.valueOf(ledger.get("remark"));
			String mExpenses = String.valueOf(ledger.get("mExpenses"));
			String brExpenses = String.valueOf(ledger.get("brExpenses"));
			String crExpenses = String.valueOf(ledger.get("crExpenses"));
			
			List<Map<String, String>> paramMap = new ArrayList<Map<String, String>>();
			
			if(!uId.equals("0") && !uMoney.equals("0.00")){
				//用户map
				Map<String, String> userMap = new HashMap<String, String>();
				userMap.put("uId", uId);
				userMap.put("userType", "1");
				userMap.put("balanceType", "2");
				userMap.put("balance", uMoney);
				userMap.put("orderId", orderId);
				userMap.put("ledgerType", "1");
				userMap.put("remark", remark);
				paramMap.add(userMap);
			}
			
			if(!sId.equals("0") && !sMoney.equals("0.00")){
				
				//商家map
				Map<String, String> sellerMap = new HashMap<String, String>();
				sellerMap.put("uId", sId);
				sellerMap.put("userType", "2");
				sellerMap.put("balanceType", "3");
				sellerMap.put("balance", sMoney);
				sellerMap.put("orderId", orderId);
				sellerMap.put("ledgerType", "2");
				sellerMap.put("remark", remark);
				paramMap.add(sellerMap);
			}
			
			if(!mId.equals("0") && !mMoney.equals("0.00")){
				//向蜜客map
				Map<String, String> mikeMap = new HashMap<String, String>();
				mikeMap.put("uId", mId);
				mikeMap.put("userType", mType);
				mikeMap.put("balanceType", "1");
				mikeMap.put("balance", mMoney);
				mikeMap.put("orderId", orderId);
				mikeMap.put("ledgerType", "3");
				mikeMap.put("remark", remark);
				mikeMap.put("expenses", mExpenses);
				paramMap.add(mikeMap);
			}
			
			if(!brId.equals("0") && !brMoney.equals("0.00")){
				//所属合作商map
				Map<String, String> brMap = new HashMap<String, String>();
				brMap.put("uId", brId);
				brMap.put("userType", "3");
				brMap.put("balanceType", "3");
				brMap.put("balance", brMoney);
				brMap.put("orderId", orderId);
				brMap.put("ledgerType", "4");
				brMap.put("remark", remark);
				brMap.put("expenses", brExpenses);
				paramMap.add(brMap);
			}
			
			if(!crId.equals("0") && !crMoney.equals("0.00")){
				//消费合作商map
				Map<String, String> crMap = new HashMap<String, String>();
				crMap.put("uId", crId);
				crMap.put("userType", "3");
				crMap.put("balanceType", "3");
				crMap.put("balance", crMoney);
				crMap.put("orderId", orderId);
				crMap.put("ledgerType", "5");
				crMap.put("remark", remark);
				crMap.put("expenses", crExpenses);
				paramMap.add(crMap);
			}
			
			Map<String, String> map = ledger(paramMap);
			int sta = 0;
			if(map != null){
				
				sta = ledgerMapper.deleteLedger(map.get("bid"));
				
				log.info("删除分账订单状态："+sta);
				if(sta > 0){
					
					mapList.add(map);
				}
			}
		}
		
		if(mapList.size() > 0){
			
			int resSta = updateLedgerSystem.updateLedgerState(mapList);
			if(resSta != 0){
				throw new FailureException();
			}
		}
		
		long edate = System.currentTimeMillis();
		
		long result = edate-sdate;
		
		log.info("自动分账运行时间："+result);
		
		log.info("分账结束<---");
	}
	
	/**
	 * 修改钱包余额（分账接口）事务处理
	 * 
	 * @param walletMap
	 * @throws Exception
	 */
	@Transactional(rollbackFor = { FailureException.class, Exception.class })
	public Map<String, String> ledger(List<Map<String, String>> paramMap){
		
		Map<String, String> resultMap = new HashMap<String, String>();
		
		List<Map<String, Object>> amountList = new ArrayList<Map<String, Object>>();
		String orderId = "";
		Map<String, Integer> reMap = new HashMap<String, Integer>();

		for (int a = 0, len = paramMap.size(); a < len; a++) {
			Map<String, Object> amountMap = new HashMap<String, Object>();
			Map<String, String> map = paramMap.get(a);
			if ( (map.get("userType") == null || ("0").equals(map.get("userType")) || map.get("userType").length() == 0)
					|| (map.get("uId") == null || ("0").equals(map.get("uId")) || map.get("uId").length() == 0) ){ 
				log.error("该用户不存在！map:" + map);
				resultMap.put("bid", orderId);
				resultMap.put("code", "2");
				resultMap.put("remark", "订单"+orderId+"中有用户不存在");
				return resultMap;
			}
			
			if (reMap != null && reMap.containsKey(map.get("uId") + "_" + map.get("userType"))) {
				amountMap = amountList.get(reMap.get(map.get("uId") + "_" + map.get("userType")));
				orderId = String.valueOf(map.get("orderId"));// 订单ID
				String balanceType = (String) map.get("balanceType");
				String accountId = String.valueOf(amountMap.get("accountid"));// 账户ID
				
				String money = map.get("balance"); // 分配的钱
				double balance_d = Double.valueOf(String.valueOf(amountMap.get("balance_d"))); // 返利余额
				double commision_d = Double.valueOf(String.valueOf(amountMap.get("commision_d"))); // 佣金余额
				double seller_amount_d = Double.valueOf(String.valueOf(amountMap
						.get("seller_amount_d"))); // 营业余额
				double zbalance_d = Double.valueOf(String.valueOf(amountMap.get("zbalance_d"))); // 赠送金额
				double money_d = Double.valueOf(money); // 分配的钱
				int balanceType_d = Integer.valueOf(balanceType); // 金（余）额类型

				if (balanceType_d == 1) {
					commision_d = Double.valueOf(String.valueOf(amountMap
							.get("commision_d"))) + money_d;
				} else if (balanceType_d == 2) {
					balance_d = Double.valueOf(String.valueOf(amountMap
							.get("balance_d"))) + money_d;
				} else if (balanceType_d == 3) {
					seller_amount_d = Double.valueOf(String.valueOf(amountMap
							.get("seller_amount_d"))) + money_d;
				} else if (balanceType_d == 4) {
					zbalance_d = Double.valueOf(String.valueOf(amountMap
							.get("zbalance_d"))) + money_d;
				}
				
				//2015年4月22日14:47:00添加
				//组装手续费
				double expenses = Double.parseDouble(String.valueOf(amountMap.get("expenses")));
				expenses = Double.parseDouble(map.get("expenses") == null?"0":map.get("expenses"))+expenses;
				
				//签名
				double userType = Double.valueOf(String.valueOf(map.get("userType"))); //用户类型
				Map<String, String> signMap = new HashMap<String, String>();
				signMap.put("uid", userType == 1 ? map.get("uId") : "0");
				signMap.put("sellerid", userType == 2 ? map.get("uId") : "0");
				signMap.put("jointid", userType == 3 ? map.get("uId") : "0");
				signMap.put("amount", String.valueOf(amountMap.get("qamount")));
				signMap.put("balance", String.valueOf(balance_d));
				signMap.put("commision", String.valueOf(commision_d));
				signMap.put("sellerAmount", String.valueOf(seller_amount_d));
				signMap.put("zbalance", String.valueOf(zbalance_d));
				signMap.put("integral", String.valueOf(amountMap.get("Integral")));
				signMap.put("pwd", String.valueOf(amountMap.get("p_pwd")));
				String signStr = commonService.walletSign(signMap);//获取新密钥
				
				amountMap.put("uId", Integer.parseInt(map.get("uId")));
				amountMap.put("userType", map.get("userType")); // 用户类型
				amountMap.put("balanceType", balanceType);
				amountMap.put("sign", signStr);
				amountMap.put("oldSign", amountMap.get("oldSign"));
				amountMap.put("commision_d", commision_d);
				amountMap.put("balance_d", balance_d);
				amountMap.put("seller_amount_d", seller_amount_d);
				amountMap.put("zbalance_d", zbalance_d);
				amountMap.put("accountid", accountId); // 钱包id
				amountMap.put("balance", balanceType.equals("2") ? money_d+Double.valueOf(String.valueOf(amountMap.get("balance")))
						: amountMap.get("balance"));// 返利金额
				amountMap.put("commision", balanceType.equals("1") ? money_d+Double.valueOf(String.valueOf(amountMap.get("commision")))
						: amountMap.get("commision"));// 佣金金额
				amountMap.put("income", balanceType.equals("3") ? money_d+Double.valueOf(String.valueOf(amountMap.get("income")))
						: amountMap.get("income"));// 营业收入
				amountMap.put("qrebate", String.valueOf(amountMap.get("qrebate"))); // 分账前返利余额
				amountMap.put("hrebate", String.valueOf(balance_d)); // 分账后返利余额
				amountMap.put("qcommision", String.valueOf(amountMap.get("qcommision"))); // 分账前佣金余额
				amountMap.put("hcommision", String.valueOf(commision_d));// 分账后佣余额
				amountMap.put("qincome", String.valueOf(amountMap.get("qincome"))); // 分账前营业余额
				amountMap.put("hincome", String.valueOf(seller_amount_d)); // 分账后营业余额
				amountMap.put("zbalance",  balanceType.equals("4") ? money
						: amountMap.get("zbalance")); // 赠送余额
				amountMap.put("qzbalance", String.valueOf(amountMap.get("qzbalance"))); // 分账前赠送余额
				amountMap.put("hzbalance", String.valueOf(zbalance_d)); // 分账后赠送余额
				amountMap.put("amount", "0.00");// 钱包金额
				amountMap.put("qamount", amountMap.get("qamount"));// 充值前钱包余额
				amountMap.put("hamount", amountMap.get("hamount"));// 充值后钱包余额
				amountMap.put("Integral", "0.00"); // 积分数
				amountMap.put("qIntegral", amountMap.get("qIntegral"));// 写入积分前剩余积分
				amountMap.put("hIntegral", amountMap.get("hIntegral"));// 写入积分后剩余积分
				amountMap.put("sdate", new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss").format(new Date()));// 记录时间
				amountMap.put("rtype", amountMap.get("rtype")); // 记录类型
				amountMap.put("orderId", amountMap.get("orderId"));// 分账订单Id
				amountMap.put("ledgerType", amountMap.get("ledgerType"));// 分账用户类型
				amountMap.put("remark", map.get("remark"));// 描述
				
				//2015年4月22日14:47:00添加
				
				amountMap.put("expenses", map.get("expenses") == null?"0":expenses);

				log.info("amountMap的数据" + amountMap);

				amountList.set(reMap.get(map.get("uId") + "_" + map.get("userType")), amountMap);

			} else {

				Map<String, String> result = new HashMap<String, String>(); 
				result = updateWalletBalanceMapper
						.getWalletByUserId(map);
				//签名Map
				Map<String, String> signMap = new HashMap<String, String>();

				reMap.put(map.get("uId") + "_" + map.get("userType"), amountList.size());
				String accountId = "";// 账户ID
				orderId = String.valueOf(map.get("orderId"));// 订单ID
				String ledgerType = String.valueOf(map.get("ledgerType"));// 分账用户类型
				String balanceType = (String) map.get("balanceType");
				String rtype = "4".equals(balanceType.trim()) ? "3" : "0";
				if (result == null) {
					log.error("该用户不存在！map:" + map);
					resultMap.put("bid", orderId);
					resultMap.put("code", "2");
					resultMap.put("remark", "订单"+orderId+"中有用户不存在");
					return resultMap;
				}else {
					signMap.putAll(result);
					Map<String, Object> wrMap = new HashMap<String, Object>();
					accountId = String.valueOf(result.get("accountid"));// 账户ID
					wrMap.put("accountId", accountId);
					wrMap.put("orderId", orderId);
					wrMap.put("ledgerType", ledgerType);
					wrMap.put("rtype", rtype);
					int wrCount = updateWalletBalanceMapper.getWRCount(wrMap);
					log.info("分账订单重复数：" + wrCount);
					if (wrCount > 0) {
						log.info("重复分账订单");
						log.info("返回的参数：" + 0);
						resultMap.put("bid", orderId);
						resultMap.put("code", "0");
						resultMap.put("remark", "订单"+orderId+"重复分账");
						return resultMap;
					}
				}
				signMap.put("balance", String.valueOf(result.get("balance")));
				signMap.put("commision", String.valueOf(result.get("commision")));
				signMap.put("sellerAmount",
						String.valueOf(result.get("seller_amount")));
				signMap.put("zbalance", String.valueOf(result.get("zbalance")));
				signMap.put("integral", String.valueOf(result.get("integral")));
				signMap.put("pwd", String.valueOf(result.get("p_pwd")));
				String getSign = commonService.walletSign(signMap);
				if (getSign.equals(signMap.get("sign")) == false) {
					log.error("密钥不一致");
					resultMap.put("bid", orderId);
					resultMap.put("code", "2");
					resultMap.put("remark", "订单"+orderId+"中有用户钱包密钥不一致");
					return resultMap;
				}
				String balance = String.valueOf(result.get("balance")); // 返利余额
				String commision = String.valueOf(result.get("commision")); // 佣金余额
				String seller_amount = String.valueOf(result
						.get("seller_amount")); // 营业余额
				String zbalance = String.valueOf(result.get("zbalance")); // 赠送余额
				String money = map.get("balance"); // 分配的钱

				double balance_d = Double.valueOf(balance); // 返利余额
				double commision_d = Double.valueOf(commision); // 佣金余额
				double seller_amount_d = Double.valueOf(seller_amount); // 营业余额
				double zbalance_d = Double.valueOf(zbalance); // 赠送金额
				double money_d = Double.valueOf(money); // 分配的钱
				int balanceType_d = Integer.valueOf(balanceType); // 金（余）额类型

				if (balanceType_d == 1) {
					commision_d = commision_d + money_d;
				} else if (balanceType_d == 2) {
					balance_d = balance_d + money_d;
				} else if (balanceType_d == 3) {
					seller_amount_d = seller_amount_d + money_d;
				} else if (balanceType_d == 4) {
					zbalance_d = zbalance_d + money_d;
				}

				signMap.put("balance", String.valueOf(balance_d));
				signMap.put("commision", String.valueOf(commision_d));
				signMap.put("sellerAmount", String.valueOf(seller_amount_d));
				signMap.put("zbalance", String.valueOf(zbalance_d));
				signMap.put("integral", String.valueOf(result.get("integral")));
				signMap.put("pwd", String.valueOf(result.get("p_pwd")));
				String signStr = commonService.walletSign(signMap);
				amountMap.put("uId", Integer.parseInt(map.get("uId")));
				amountMap.put("p_pwd", result.get("p_pwd"));
				amountMap.put("userType", map.get("userType")); // 用户类型
				amountMap.put("sign", signStr);
				amountMap.put("oldSign", result.get("sign"));
				amountMap.put("commision_d", commision_d);
				amountMap.put("balance_d", balance_d);
				amountMap.put("seller_amount_d", seller_amount_d);
				amountMap.put("zbalance_d", zbalance_d);
				amountMap.put("balanceType", balanceType);
				amountMap.put("accountid", accountId); // 钱包id
				amountMap.put("balance", balanceType.equals("2") ? money
						: "0.00");// 返利金额
				amountMap.put("commision", balanceType.equals("1") ? money
						: "0.00");// 佣金金额
				amountMap.put("income", balanceType.equals("3") ? money
						: "0.00");// 营业收入
				amountMap.put("qrebate", balance); // 分账前返利余额
				amountMap.put("hrebate", String.valueOf(balance_d)); // 分账后返利余额
				amountMap.put("qcommision", commision); // 分账前佣金余额
				amountMap.put("hcommision", String.valueOf(commision_d));// 分账后佣余额
				amountMap.put("qincome", seller_amount); // 分账前营业余额
				amountMap.put("hincome", String.valueOf(seller_amount_d)); // 分账后营业余额
				amountMap.put("zbalance", balanceType.equals("4") ? money
						: "0.00"); // 赠送余额
				amountMap.put("qzbalance", zbalance); // 分账前赠送余额
				amountMap.put("hzbalance", String.valueOf(zbalance_d)); // 分账后赠送余额
				amountMap.put("amount", "0.00");// 钱包金额
				amountMap.put("qamount", result.get("amount"));// 充值前钱包余额
				amountMap.put("hamount", result.get("amount"));// 充值后钱包余额
				amountMap.put("Integral", "0.00"); // 积分数
				amountMap.put("qIntegral", result.get("integral"));// 写入积分前剩余积分
				amountMap.put("hIntegral", result.get("integral"));// 写入积分后剩余积分
				amountMap.put("sdate", new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss").format(new Date()));// 记录时间
				amountMap.put("rtype", rtype); // 记录类型
				amountMap.put("orderId", orderId);// 分账订单Id
				amountMap.put("ledgerType", ledgerType);// 分账用户类型
				amountMap.put("remark", map.get("remark"));// 描述
				
				//2015年4月22日14:47:00添加
				amountMap.put("expenses", map.get("expenses") == null?"0":map.get("expenses"));

				log.info("amountMap的数据" + amountMap);
				amountList.add(amountMap);
			}
		}
		
		int result1 = 0;
		for(Map<String, Object> ammountMap : amountList){
		
			result1 = updateWalletBalanceMapper.updateBalanceType(ammountMap);
			if(result1 != 1){
				log.error("该条记录不能分账");
				log.error("修改异常" + amountList.toString());
				resultMap.put("bid", orderId);
				resultMap.put("code", "3");
				resultMap.put("remark", "订单"+orderId+"中有修改用户出错");
				return resultMap;
			}
		}
		if (result1 == 1) {
			result1 = updateWalletBalanceMapper.insertWR(amountList);
			if (result1 == amountList.size()) {
				resultMap.put("bid", orderId);
				resultMap.put("code", "0");
				resultMap.put("remark", "订单"+orderId+"分账成功");
				log.info("分账记录插入成功");
			} else {
				log.error("分账记录插入失败");
				resultMap.put("bid", orderId);
				resultMap.put("code", "3");
				resultMap.put("remark", "新增记录失败");
				return resultMap;
			}
			log.info("修改成功" + amountList.toString());
		} else {
			log.error("修改异常" + amountList.toString());
			resultMap.put("bid", orderId);
			resultMap.put("code", "3");
			resultMap.put("remark", "新增记录失败");
			return resultMap;
		}
		return resultMap;
	}
	
	/**
	 * 钱包加密
	 * 
	 * @param signMap
	 * @return
	 */
//	public String sign(Map<String, String> signMap) {
//		StringBuffer sb = new StringBuffer();
//		sb.append(String.valueOf(signMap.get("uid")));
//		sb.append(String.valueOf(signMap.get("sellerid")));
//		sb.append(String.valueOf(signMap.get("jointid")));
//		sb.append(signMap.get("pwd") == null
//				|| String.valueOf(signMap.get("pwd")).equals("null") ? ""
//				: String.valueOf(signMap.get("pwd")));
//		sb.append(String.format("%.2f",
//				Double.valueOf(String.valueOf(signMap.get("amount")))));
//		sb.append(String.format("%.2f",
//				Double.valueOf(String.valueOf(signMap.get("balance")))));
//		sb.append(String.format("%.2f",
//				Double.valueOf(String.valueOf(signMap.get("commision")))));
//		sb.append(String.format("%.2f",
//				Double.valueOf(String.valueOf(signMap.get("zbalance")))));
//		sb.append(String.valueOf((new BigDecimal(signMap.get("Integral")).longValue())));
//		sb.append(String.format("%.2f",
//				Double.valueOf(String.valueOf(signMap.get("sellerAmount")))));
//
//		log.info("钱包加密：" + sb.toString());
//
//		return MD5.Encode(sb.toString());
//	}
}
