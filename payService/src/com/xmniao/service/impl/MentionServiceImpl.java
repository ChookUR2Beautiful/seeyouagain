package com.xmniao.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.xmniao.common.UtilCommon;
import com.xmniao.common.UtilString;
import com.xmniao.dao.ExpensesMpper;
import com.xmniao.dao.MentionAccountMapper;
import com.xmniao.dao.UpdateWalletBalanceMapper;
import com.xmniao.dao.UpdateWithdrawalsRecordStateMapper;
import com.xmniao.dao.WalletMapper;
import com.xmniao.quartz.impl.AutoMentionServiceImpl;
import com.xmniao.service.CommonService;
import com.xmniao.thrift.ledger.FailureException;
@Service("mentionServiceImpl")
public class MentionServiceImpl {
	
	
	/**
	 * 日志记录
	 */
	private final Logger log = Logger.getLogger(AutoMentionServiceImpl.class);

	/**
	 * 钱包Mapper
	 */
	@Autowired
	private WalletMapper walletMapper;

	/**
	 * 提现方式Mapper
	 */
	@Autowired
	private MentionAccountMapper mentionAccountMapper;

	@Autowired
	private UpdateWithdrawalsRecordStateMapper updateWithdrawalsRecordStateMapper;

	@Autowired
	public UpdateWalletBalanceMapper updateWalletBalanceMapper;

	/**
	 * 自动提现手续费Mapper
	 */
	@Autowired
	private ExpensesMpper expensesMpper;

	/**
	 * redisTemplate
	 */
	@Autowired
	private StringRedisTemplate redisTemplate;

	/**
	 * 自动提现redis名称
	 */
	@Resource(name = "autoRedisName")
	private String autoRedisName;

	/**
	 * 默认提现金额
	 */
	private double tMoney = 50000;

	@Autowired
	private CommonService commonService;
	
	@Transactional(rollbackFor = { FailureException.class, Exception.class })
	public void mentionIncome(Map<String,Object> incomeUser) throws FailureException{
				
		Map<String, Object> wrRecordMap = new HashMap<String, Object>();
		// 用户ID
		String uId = String.valueOf(incomeUser.get("sellerId"));
		// 用户类型
		String userType = "2";
		// 用户昵称
		String sellerName = String.valueOf(incomeUser.get("sellername"));
        log.info("自动营业额提现开始，商家Id："+ uId +",商家名称："+sellerName);
		// redis参数
		Map<String, Object> redisMap = new HashMap<String, Object>();
		redisMap.put("type", 2);
		redisMap.put("ledger_type", 2);
		redisMap.put("cash_type", 1);
		redisMap.put("uid", uId);
		redisMap.put("name",
				sellerName == null || ("").equals(sellerName.trim())
						|| ("null").equals(sellerName.trim()) ? uId
						: sellerName);

		wrRecordMap.put("purpose", "自动提现");	
		wrRecordMap.put("tdesc", "自动提现");
		wrRecordMap.put("recchannel", "2");
		wrRecordMap.put("cash_type", 1);
		wrRecordMap.put("uid", Integer.parseInt(uId));
		wrRecordMap.put("name",
				sellerName == null || ("").equals(sellerName.trim())
						|| ("null").equals(sellerName.trim()) ? uId
						: sellerName);
		wrRecordMap.put("userType", userType);

		// 今天已经提现的金额
		double todayWithdrawals = getTodayWithdrawals(uId, userType);

		if (todayWithdrawals >= (tMoney - 2)) {
			log.info("已超过今天的最大提现金额！");
			return;
		}

		Map<String, Object> userMap = new HashMap<String, Object>();

		userMap.put("uId", uId);
		userMap.put("userType", userType);

		List<Map<String, Object>> mentionMapList = mentionAccountMapper
				.selectByuid(userMap);

		if(mentionMapList.size()>0){
			Map<String, Object> mentionMap = mentionMapList.get(0);

			if (mentionMap == null
					|| mentionMap.get("account") == null
					|| mentionMap.get("bankname") == null
					|| mentionMap.get("mobileid") == null
					|| mentionMap.get("abbrev") == null
					|| mentionMap.get("bank") == null
					|| mentionMap.get("ispublic") == null
					//|| mentionMap.get("idtype") == null
					//|| mentionMap.get("identity") == null
					|| mentionMap.get("province") == null
					|| mentionMap.get("cityname") == null
					|| "".equals(String.valueOf(mentionMap
							.get("ispublic")))
					//|| "".equals(String.valueOf(mentionMap
					//		.get("idtype")))
					//|| "".equals(String.valueOf(mentionMap
					//		.get("identity")))
					|| "".equals(String.valueOf(mentionMap
							.get("province")))
					|| "".equals(String.valueOf(mentionMap
							.get("cityname")))
					|| "".equals(String.valueOf(mentionMap
							.get("account")))
					|| "".equals(String.valueOf(mentionMap
							.get("bankname")))
					|| "".equals(String.valueOf(mentionMap
							.get("mobileid")))
					|| "".equals(String.valueOf(mentionMap
							.get("abbrev")))
					|| "".equals(String.valueOf(mentionMap.get("bank")))) {
				log.info("用户Id为：" + uId + "的提现账号不完整");
				return ;
			}
			if("0".equals(String.valueOf(mentionMap
					.get("ispublic")))){
				if(mentionMap.get("idtype") == null
					|| mentionMap.get("identity") == null
					|| "".equals(String.valueOf(mentionMap
							.get("idtype")))
					|| "".equals(String.valueOf(mentionMap
							.get("identity")))){
					log.info("用户Id为：" + uId + "的提现账号不完整");
					return ;
				}
			}

			redisMap.put("account", mentionMap.get("account"));
			redisMap.put("account_type", "1");
			redisMap.put("fullname", mentionMap.get("username"));
			redisMap.put("bankname", mentionMap.get("bank"));
			redisMap.put("branchname", mentionMap.get("bankname"));
			redisMap.put("idtype", mentionMap.get("idtype"));
			redisMap.put("idcard", mentionMap.get("identity"));

			wrRecordMap.put("account_type", "1");
			wrRecordMap.put("account", mentionMap.get("account"));
			wrRecordMap.put("bankname", mentionMap.get("bankname"));
			wrRecordMap.put("mobileid", mentionMap.get("mobileid"));
			wrRecordMap.put("fullname", mentionMap.get("username"));
			wrRecordMap.put("abbrev", mentionMap.get("abbrev"));
			wrRecordMap.put("bank", mentionMap.get("bank"));
			wrRecordMap.put("ispublic", mentionMap.get("ispublic"));
			wrRecordMap.put("idtype", mentionMap.get("idtype"));
			wrRecordMap.put("identity", mentionMap.get("identity"));
			wrRecordMap.put("province", mentionMap.get("province"));
			wrRecordMap.put("cityname", mentionMap.get("cityname"));

			double commission = 0;// 佣金
			double rebate = 0;// 返利
			double income = 0;// 营收
			double wallet = 0;// 钱包

			// 获取提现金额
			income = Double.parseDouble(String.valueOf(incomeUser
					.get("tIncome")));
			
			// 手续费
			double expenses = 5;

			/*if (Integer.parseInt(userType) == 2) {
				if(income<UtilCommon.FREE_EXPENSES_QUOTA){
					expenses = 2;
				}
			} else {
				expenses = 2;
			}*/



			log.info("提现帐号信息：帐号：" + mentionMap.get("account")+",用户名："+mentionMap.get("username"));
			double oldCommission = Double.parseDouble(incomeUser
					.get("commision") == null ? "0" : String
					.valueOf(incomeUser.get("commision")));// 佣金
			double oldRebate = Double.parseDouble(incomeUser
					.get("balance") == null ? "0" : String
					.valueOf(incomeUser.get("balance")));// 返利
			double oldIncome = Double.parseDouble(incomeUser
					.get("income") == null ? "0" : String
					.valueOf(incomeUser.get("income")));// 营收
			double oldWallet = Double.parseDouble(incomeUser
					.get("amount") == null ? "0" : String
					.valueOf(incomeUser.get("amount")));// 钱包

			wrRecordMap.put("oldCommission", oldCommission);
			wrRecordMap.put("oldRebate", oldRebate);
			wrRecordMap.put("oldIncome", oldIncome);
			wrRecordMap.put("oldWallet", oldWallet);

			if (((oldIncome < 0 && income == 0) || oldIncome >= income)) {
				if (oldIncome > tMoney - todayWithdrawals) {

					income = tMoney - todayWithdrawals;
				} else {

					income = oldIncome;
				}
				
				redisMap.put("expenses",expenses);
				redisMap.put("expenses_info",expenses+"元/笔");
				
				redisMap.put("wallet_balance",
						String.format("%.2f ", oldWallet));
				redisMap.put("rebate_balance",
						String.format("%.2f ", oldRebate));
				redisMap.put("commission_balance",
						String.format("%.2f ", oldCommission));
				redisMap.put("income_balance",
						String.format("%.2f ", oldIncome - income));

				redisMap.put("commission",
						String.format("%.2f ", commission));
				redisMap.put("wallet", String.format("%.2f ", wallet));
				redisMap.put("rebate", String.format("%.2f ", rebate));
				redisMap.put("income",
						String.format("%.2f ", income - expenses));
				redisMap.put("expenses", String.format("%.2f ", expenses));

				wrRecordMap.put("expenses", expenses);
				wrRecordMap.put("commission", commission);
				wrRecordMap.put("wallet", wallet);
				wrRecordMap.put("rebate", rebate);
				wrRecordMap.put("income", income - expenses);
				wrRecordMap.put("sdate",  UtilString.dateFormatNow());
				wrRecordMap.put("udate", UtilString.dateFormatNow());
				wrRecordMap.put("autowithdrawals", "1");
				log.info("自动提现营业额，oldIncome:"+oldIncome+"，提现金额 income"+income);

			} else {
				log.error("自动提现金额异常，oldIncome:"+oldIncome+"，提现金额 income"+income);
				return;
			}

			redisMap.put("sdate", new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss").format(new Date()));

			log.info("增加提现记录参数：" + wrRecordMap);
			int state = updateWithdrawalsRecordStateMapper
					.insertWRRecord(wrRecordMap);

			if (state == 0) {
				log.error("增加提现记录失败!");
				throw new FailureException(1, "添加提现记录失败");
			} else {

				int addState = expensesMpper.addExpenses(wrRecordMap);

				if (addState == 0) {
					log.error("增加提现手续费记录失败!");
					throw new FailureException(1, "添加提现手续费记录失败");
				} else
					redisMap.put("order_number",
							String.valueOf(wrRecordMap.get("id")));
			}

			state = updateWalletAmount(incomeUser, uId, userType,
					redisMap, wrRecordMap);

			String redisJson = JSON.toJSONString(redisMap);
			log.info("redisJson:" + redisJson);
			redisTemplate.opsForList().leftPush(autoRedisName,
					redisJson);
		
			
		}

	}
	
	@Transactional(rollbackFor = { FailureException.class, Exception.class })
	public void mentionCommision(Map<String,Object> commisionUser) throws FailureException{
		

		Map<String, Object> wrRecordMap = new HashMap<String, Object>();

		// 用户ID
		String uId = String.valueOf(commisionUser.get("sellerId"));
		// 用户类型
		String userType = "2";
		// 用户昵称
		String sellerName = String.valueOf(commisionUser.get("sellername"));
		
		log.info("自动佣金提现开始，商家Id："+ uId +",商家名称："+sellerName);
		// redis参数
		Map<String, Object> redisMap = new HashMap<String, Object>();
		redisMap.put("type", 2);
		redisMap.put("ledger_type", 2);
		redisMap.put("cash_type", 1);
		redisMap.put("uid", uId);
		redisMap.put("name",
				sellerName == null || ("").equals(sellerName.trim())
						|| ("null").equals(sellerName.trim()) ? uId
						: sellerName);

		wrRecordMap.put("purpose", "自动提现");
		wrRecordMap.put("tdesc", "自动提现");
		wrRecordMap.put("recchannel", "2");
		wrRecordMap.put("cash_type", 1);
		wrRecordMap.put("uid", Integer.parseInt(uId));
		wrRecordMap.put("name",
				sellerName == null || ("").equals(sellerName.trim())
						|| ("null").equals(sellerName.trim()) ? uId
						: sellerName);
		wrRecordMap.put("userType", userType);

		// 今天已经提现的金额
		double todayWithdrawals = getTodayWithdrawals(uId, userType);

		if (todayWithdrawals >= (tMoney - 2)) {
			return;
		}

		Map<String, Object> userMap = new HashMap<String, Object>();

		userMap.put("uId", uId);
		userMap.put("userType", userType);

		List<Map<String, Object>> mentionMapList = mentionAccountMapper
				.selectByuid(userMap);

		if (mentionMapList.size()>0) {
			Map<String, Object> mentionMap = mentionMapList.get(0);

				if (mentionMap == null
						|| mentionMap.get("account") == null
						|| mentionMap.get("bankname") == null
						|| mentionMap.get("mobileid") == null
						|| mentionMap.get("abbrev") == null
						|| mentionMap.get("bank") == null
						|| mentionMap.get("ispublic") == null
						//|| mentionMap.get("idtype") == null
						//|| mentionMap.get("identity") == null
						|| mentionMap.get("province") == null
						|| mentionMap.get("cityname") == null
						|| "".equals(String.valueOf(mentionMap
								.get("ispublic")))
						//|| "".equals(String.valueOf(mentionMap
						//		.get("idtype")))
						//|| "".equals(String.valueOf(mentionMap
						//		.get("identity")))
						|| "".equals(String.valueOf(mentionMap
								.get("province")))
						|| "".equals(String.valueOf(mentionMap
								.get("cityname")))
						|| "".equals(String.valueOf(mentionMap
								.get("account")))
						|| "".equals(String.valueOf(mentionMap
								.get("bankname")))
						|| "".equals(String.valueOf(mentionMap
								.get("mobileid")))
						|| "".equals(String.valueOf(mentionMap
								.get("abbrev")))
						|| "".equals(String.valueOf(mentionMap.get("bank")))) {
					log.info("用户Id为：" + uId + "的提现账号不完整");
					return;
				}
				if("0".equals(String.valueOf(mentionMap
						.get("ispublic")))){
					if(
						mentionMap.get("idtype") == null
						|| mentionMap.get("identity") == null
						|| "".equals(String.valueOf(mentionMap
								.get("idtype")))
						|| "".equals(String.valueOf(mentionMap
								.get("identity")))
						){
					log.info("用户Id为：" + uId + "的提现账号不完整");
					return;
					}
				}

				redisMap.put("account", mentionMap.get("account"));
				redisMap.put("account_type", "1");
				redisMap.put("fullname", mentionMap.get("username"));
				redisMap.put("bankname", mentionMap.get("bank"));
				redisMap.put("branchname", mentionMap.get("bankname"));
				redisMap.put("idtype", mentionMap.get("idtype"));
				redisMap.put("idcard", mentionMap.get("identity"));

				wrRecordMap.put("account_type", "1");
				wrRecordMap.put("account", mentionMap.get("account"));
				wrRecordMap.put("bankname", mentionMap.get("bankname"));
				wrRecordMap.put("mobileid", mentionMap.get("mobileid"));
				wrRecordMap.put("fullname", mentionMap.get("username"));
				wrRecordMap.put("abbrev", mentionMap.get("abbrev"));
				wrRecordMap.put("bank", mentionMap.get("bank"));
				wrRecordMap.put("ispublic", mentionMap.get("ispublic"));
				wrRecordMap.put("idtype", mentionMap.get("idtype"));
				wrRecordMap.put("identity", mentionMap.get("identity"));
				wrRecordMap.put("province", mentionMap.get("province"));
				wrRecordMap.put("cityname", mentionMap.get("cityname"));
				wrRecordMap.put("sdate", UtilString.dateFormatNow());
				wrRecordMap.put("udate", UtilString.dateFormatNow());
				wrRecordMap.put("autowithdrawals", "1");
				// 手续费
				double expenses = 5;

				/*if (Integer.parseInt(userType) == 2) {
					expenses = 2;
				} else {
					expenses = 2;
				}*/

				double commission = 0;// 佣金
				double rebate = 0;// 返利
				double income = 0;// 营收
				double wallet = 0;// 钱包

				// 获取提现金额
				commission = Double.parseDouble(String
						.valueOf(commisionUser.get("tMoney")));

				double oldCommission = Double.parseDouble(commisionUser
						.get("commision") == null ? "0" : String
						.valueOf(commisionUser.get("commision")));// 佣金
				double oldRebate = Double.parseDouble(commisionUser
						.get("balance") == null ? "0" : String
						.valueOf(commisionUser.get("balance")));// 返利
				double oldIncome = Double.parseDouble(commisionUser
						.get("income") == null ? "0" : String
						.valueOf(commisionUser.get("income")));// 营收
				double oldWallet = Double.parseDouble(commisionUser
						.get("amount") == null ? "0" : String
						.valueOf(commisionUser.get("amount")));// 钱包

				wrRecordMap.put("oldCommission", oldCommission);
				wrRecordMap.put("oldRebate", oldRebate);
				wrRecordMap.put("oldIncome", oldIncome);
				wrRecordMap.put("oldWallet", oldWallet);

				if (oldCommission > (tMoney - todayWithdrawals)) {

					commission = tMoney - todayWithdrawals;
				} else {

					commission = oldCommission;
				}
				
				redisMap.put("expenses",expenses);
				redisMap.put("expenses_info",expenses+"元/笔");
				
				redisMap.put("wallet_balance",
						String.format("%.2f ", oldWallet));
				redisMap.put("rebate_balance",
						String.format("%.2f ", oldRebate));
				redisMap.put("commission_balance",
						String.format("%.2f ", oldCommission - commission));
				redisMap.put("income_balance",
						String.format("%.2f ", oldIncome));

				redisMap.put("sdate", new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss").format(new Date()));

				redisMap.put("commission",
						String.format("%.2f ", commission - expenses));
				redisMap.put("wallet", String.format("%.2f ", wallet));
				redisMap.put("rebate", String.format("%.2f ", rebate));
				redisMap.put("income", String.format("%.2f ", income));
				redisMap.put("expenses", String.format("%.2f ", expenses));
				
				wrRecordMap.put("expenses", expenses);
				wrRecordMap.put("commission", commission - expenses);
				wrRecordMap.put("wallet", wallet);
				wrRecordMap.put("rebate", rebate);
				wrRecordMap.put("income", income);

				log.info("增加提现记录参数：" + wrRecordMap);
				int state = updateWithdrawalsRecordStateMapper
						.insertWRRecord(wrRecordMap);

				if (state == 0) {
					log.error("增加提现记录失败");
					throw new FailureException(1, "添加提现记录失败");
				} else {

					int addState = expensesMpper.addExpenses(wrRecordMap);

					if (addState == 0) {
						log.error("增加提现手续费记录失败！");
						throw new FailureException(1, "添加提现手续费记录失败");
					} else
						redisMap.put("order_number",
								String.valueOf(wrRecordMap.get("id")));
				}

				// 修改钱包金额
				state = updateWalletAmount(commisionUser, uId, userType,
						redisMap, wrRecordMap);

				String redisJson = JSON.toJSONString(redisMap);
				log.info("redisJson:" + redisJson);
				// 存入redis队列
				redisTemplate.opsForList().leftPush(autoRedisName,
						redisJson);

		}
		
	}
	
	

	/**
	 * 修改钱包金额
	 * 
	 * @param mentionMap
	 * @param uId
	 * @param userType
	 * @param redisMap
	 * @param signMap
	 * @return
	 * @throws FailureException
	 */
	private int updateWalletAmount(Map<String, Object> mentionMap, String uId,
			String userType, Map<String, Object> redisMap,
			Map<String, Object> wrRecordMap) throws FailureException {
		int state;
		// 定义参数
		Map<String, String> signMap = new HashMap<String, String>();
		signMap.put("oldSign", String.valueOf(mentionMap.get("sign")));
		signMap.put("pwd", String.valueOf(mentionMap.get("pwd")));
		signMap.put("amount", String.valueOf(redisMap.get("wallet_balance")));
		signMap.put("balance", String.valueOf(redisMap.get("rebate_balance")));
		signMap.put("commision",
				String.valueOf(redisMap.get("commission_balance")));
		signMap.put("sellerAmount",
				String.valueOf(redisMap.get("income_balance")));
		signMap.put("zbalance", String.valueOf(mentionMap.get("zbalance")));
		signMap.put("integral", String.valueOf(mentionMap.get("integral")));
		signMap.put("uid", userType.equals("1") ? uId : "0");
		signMap.put("sellerid", userType.equals("2") ? uId : "0");
		signMap.put("jointid", userType.equals("3") ? uId : "0");

		// 新加密串
		String newSign = commonService.walletSign(signMap);
		signMap.put("sign", newSign);
		signMap.put("signType", "MD5");
		signMap.put("typeId", userType);
		signMap.put("uId", uId);
		signMap.put("lastDate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		state = walletMapper.updateWalletBySign(signMap);
		log.info("商家自动提现修改余额状态:" + state);

		Map<String, Object> amountMap = new HashMap<String, Object>();
		amountMap.put("uId", Integer.parseInt(uId));
		amountMap.put("userType", userType); // 用户类型
		amountMap.put("sign", newSign);
		amountMap.put("oldSign", String.valueOf(mentionMap.get("sign")));
		amountMap.put("accountid", mentionMap.get("accountid")); // 钱包id
		amountMap.put("balance", wrRecordMap.get("rebate"));// 返利金额
		amountMap.put("commision", wrRecordMap.get("commission"));// 佣金金额
		amountMap.put("income", wrRecordMap.get("income"));// 营业收入
		amountMap.put("qrebate", wrRecordMap.get("oldRebate")); // 分账前返利余额
		amountMap.put("hrebate", redisMap.get("rebate_balance")); // 分账后返利余额
		amountMap.put("qcommision", wrRecordMap.get("oldCommission")); // 分账前佣金余额
		amountMap.put("hcommision", redisMap.get("commission_balance"));// 分账后佣余额
		amountMap.put("qincome", wrRecordMap.get("oldIncome")); // 分账前营业余额
		amountMap.put("hincome", redisMap.get("income_balance")); // 分账后营业余额
		amountMap.put("zbalance", "0.00"); // 赠送余额
		amountMap.put("qzbalance", "0.00"); // 分账前赠送余额
		amountMap.put("hzbalance", "0.00"); // 分账后赠送余额
		amountMap.put("amount", "0.00");// 钱包金额
		amountMap.put("qamount", "0.00");// 充值前钱包余额
		amountMap.put("hamount", "0.00");// 充值后钱包余额
		amountMap.put("Integral", "0.00"); // 积分数
		amountMap.put("qIntegral", "0");// 写入积分前剩余积分
		amountMap.put("hIntegral", "0");// 写入积分后剩余积分
		amountMap.put("sdate",
				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));// 记录时间
		amountMap.put("rtype", "6"); // 记录类型
		amountMap.put("orderId", redisMap.get("order_number"));// 分账订单Id
		amountMap.put("ledgerType", userType);// 分账用户类型
		amountMap.put("expenses", wrRecordMap.get("expenses"));

		if (state == 1) {
			state = updateWalletBalanceMapper.insertWalletRecord(amountMap);
			if (state == 1) {
				log.info("提现记录插入成功！");
			} else {
				log.error("提现记录插入失败！amountMap:" + amountMap);
				throw new FailureException(1, "添加提现记录失败");
			}
			log.info("修改成功" + amountMap.toString());
		} else {
			log.error("修改异常" + amountMap.toString());
			throw new FailureException(1, "提现失败");
		}
		log.info("合作商提现修改余额状态:" + state);

		return state;
	}

	/**
	 * 获取该商户/合作商当天的提现总金额
	 * 
	 * @return
	 */
	private double getTodayWithdrawals(String id, String type) {
		double allMoney = 0.0;

		if ("1".equals(type.trim()) || "2".equals(type.trim())) {// 商户与用户
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("uId", id);
			map.put("userType", type);
			allMoney = mentionAccountMapper.countTodayMoney(map);
		} else if ("3".equals(type.trim())) {// 合作商
			allMoney = updateWithdrawalsRecordStateMapper.countTodayMoney(id);
		}
		log.info("该用户当天已提现金额:" + allMoney);

		return allMoney;
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
