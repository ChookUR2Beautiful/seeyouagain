package com.xmniao.service.pay;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xmniao.common.AgentPayRequest;
import com.xmniao.common.MD5;
import com.xmniao.common.PayConstants;
import com.xmniao.common.UtilString;
import com.xmniao.common.WithdrawMoneyUtils;
import com.xmniao.dao.ExpensesMpper;
import com.xmniao.dao.MentionAccountMapper;
import com.xmniao.dao.UpdateWalletBalanceMapper;
import com.xmniao.dao.UpdateWithdrawalsRecordStateMapper;
import com.xmniao.dao.WalletMapper;
import com.xmniao.entity.WithdrawMoneyConfig;
import com.xmniao.service.CommonService;
import com.xmniao.service.KuaiqianPayService;
import com.xmniao.service.LlPayService;
import com.xmniao.service.ShengPayService;
import com.xmniao.service.TongPayService;
import com.xmniao.service.UPayService;
import com.xmniao.service.impl.ChinaPnRPayImpl;
import com.xmniao.service.impl.RongTWithdrawMoneyImpl;
import com.xmniao.service.impl.WithdrawMoneyServiceTools;
import com.xmniao.thrift.ledger.FailureException;
import com.xmniao.thrift.ledger.WithdrawMoneyService;

/**
 * 提現接口
 * 
 * @author YangJing
 * 
 *         2015年1月20日
 */
@Service("WithdrawMoneyServiceImpl")
public class WithdrawMoneyServiceImpl implements WithdrawMoneyService.Iface {// 汇付天下，融宝，u付

    /** 日志记录 */
    private final Logger log = Logger.getLogger(WithdrawMoneyServiceImpl.class);
    /** 提现工具类 */
    @Autowired
    private WithdrawMoneyServiceTools withdrawMoneyServiceTools;
    /** 汇付天下 */
    @Autowired
    private ChinaPnRPayImpl chinaPnRPayImpl;
    /** 融保通提现 */
    @Autowired
    private RongTWithdrawMoneyImpl rongTWithdrawMoneyImpl;
    /** 盛付通提现 */
    @Autowired
    private ShengPayService shengPayService;
    /** 快钱服务 */
    @Autowired
    private KuaiqianPayService kuaiqianPayService;
    /** 提现记录Mapper */
    @Autowired
    public UpdateWithdrawalsRecordStateMapper updateWithdrawalsRecordStateMapper;
    /** U付 */
    @Autowired
    private UPayService uPayService;
    /** 通联 */
    @Autowired
    private TongPayService tongPayService;
    /** 连连 */
    @Autowired
    private LlPayService llPayService;
    /** 融保通回调URL */
    @Resource(name = "RBRetUrl")
    String RBRetUrl;

    @Autowired
    public UpdateWalletBalanceMapper updateWalletBalanceMapper;
    /** redis */
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private ExpensesMpper expensesMpper;
    @Autowired
    private WalletMapper walletMapper;
    @Autowired
    private MentionAccountMapper mentionAccountMapper;
    /** 提现公共服务 */
    @Autowired
    private CommonService commonService;

    @Autowired
    private MentionAccountServiceImpl mentionAccountService;
    /**
     * 商家提现redis名称
     */
    @Resource(name = "storeRedisName")
    private String storeRedisName;

    /**
     * 合作商提现redis名称
     */
    @Resource(name = "jointRedisName")
    private String jointRedisName;

    /**
     * 最高提现金额
     */
    // private double tMoney = 50000;

    @Autowired
    private WithdrawMoneyConfig withdrawMoneyConfig;

    @Autowired
    private WithdrawMoneyUtils withdrawMoneyUtils;
    
    private static BigDecimal expensePersent = new BigDecimal(0.06);//用户提现手续费收取比例
    /**
     * 提现接口
     * 
     * @param orderNumber
     *            订单号
     * @param status
     *            订单状态 交易状态:1.提现成功 2.提现失败 3.银行处理中(启动代发,默认此参数值)
     * @param userType
     *            用户类型:1 商户 2 会员 3 向蜜客 4 合作商 5 连锁店
     * @param withdrawType
     *            代发(提现)通道：1.汇付天下 2.融宝 3.u付 4.盛付通 5.快钱 6.通联
     * @return status 1成功 2失败 3处理中 6程序出错
     * @throws Exception
     */

    @Transactional(rollbackFor = { FailureException.class, Exception.class })
    public Map<String, String> headWithrawMoney(String orderNumber, int status, int userType, String withdrawType)
	    throws FailureException {
	long sdate = System.currentTimeMillis();

	log.info("方法-->提现入口 headWithrawMoney-->orderNumber:" + orderNumber + "-->status:" + status + "-->userType:" + userType);


	Map<String, String> resultMap = new HashMap<String, String>();
	resultMap.put("orderNumber", orderNumber);
	resultMap.put("userType", String.valueOf(userType));
	try {
//		String batchno = orderNumber+userType;
//		Map m2= shengPayService.shengPayQuery(batchno);
//		log.info(m2);
		//Map m=withdrawMoney(orderNumber, status, userType, withdrawType);
		//log.info(m);
	    // 检测params
	    resultMap = checkParams(orderNumber, status, userType, resultMap);
	    if (resultMap.get("status") != null) {
		return resultMap;
	    } else {
		resultMap.put("status", PayConstants.WITHDRAW_STATUS_FAIL);
		resultMap.put("msg", PayConstants.WITHDRAW_MSG_FAIL);
	    }

	    if (status == Integer.valueOf(PayConstants.WITHDRAW_STATUS_PROCESS)) {
		// withdrawType 1汇付天下，2融宝，3 u付
		Map<String, String> map = withdrawMoney(orderNumber, status, userType, withdrawType);
		if (map == null || map.size() < 1) {
		    resultMap.put("status", PayConstants.WITHDRAW_STATUS_FAIL);
		    resultMap.put("msg", PayConstants.WITHDRAW_MSG_FAIL);
		    return resultMap;
		}
		status = Integer.parseInt(map.get("status"));
		resultMap.put("status", map.get("status"));
		resultMap.put("msg", map.get("RespDesc"));
	    }
	    // 修改提现记录状态
	    updateWithdrawState(orderNumber, status, userType, withdrawType, null, resultMap.get("msg"));
	} catch (Exception e) {
	    log.error("orderNumber:" + orderNumber + ",程序出错:", e);
	    /*
	     * resultMap.put("status", PayConstants.WITHDRAW_STATUS_ERROR); resultMap.put("msg",
	     * PayConstants.WITHDRAW_MSG_ERROR);
	     */
	    throw new FailureException();

	}
	long edate = System.currentTimeMillis();
	long ydate = edate - sdate;
	log.info("响应参数：" + resultMap);
	log.info("接口运行时间：" + ydate + "ms");
	return resultMap;

    }

    /**
     * 更新提现状态
     * 
     * @param orderNumber
     *            订单号
     * @param status
     *            订单状态
     * @param userType
     *            用户类型
     * @param withdrawType
     *            提现方式
     */
    public int updateWithdrawState(String orderNumber, int status, int userType, String withdrawType, String number, String msg) {
	int result = 0;
	Map<String, Object> map = new HashMap<String, Object>();
	String platForm = "";
	map.put("orderNumber", orderNumber);
	map.put("status", status);
	map.put("number", number);
	// 程序数据与数据数据 转换
	switch (withdrawType) {// 数据库中 0 汇付 1 U付 2 融宝
	case PayConstants.WITHDRAW_TYPE_HF: {
	    platForm = PayConstants.WITHDRAW_PLATFORM_HF;// 0 汇付
	}
	    break;
	case PayConstants.WITHDRAW_TYPE_UF: {
	    platForm = PayConstants.WITHDRAW_PLATFORM_UF;// 1 U付
	}
	    break;
	default: {
	    platForm = withdrawType;
	}
	    break;
	}

	map.put("platform", platForm);
	map.put("userType", userType);
	map.put("msg", msg);
	map.put("udate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
	// 商户、会员
	int table = commonService.getTableByUsertype(userType);

	if (table == CommonService.WITHRAW_RECORD) {
	    result = updateWithdrawalsRecordStateMapper.updateWithdrawalsRecordState(map);
	} else if (table == CommonService.WITHRAW_JOINT) { // 合作商
	    result = updateWithdrawalsRecordStateMapper.updateWithdrawalsRecordStateByjointid(map);
	}
	if (result == 1) {
	    log.info("修改提现记录成功");
	} else {
	    log.error("updateWithdrawState 修改提现记录失败,orderNumber=" + orderNumber);
	}
	return result;
    }

    /**
     * 检测Params
     * 
     * @param orderNumber
     *            订单号
     * @param status
     *            订单状态
     * @param userType
     *            用户类型
     * @return
     */

    private Map<String, String> checkParams(String orderNumber, int status, int userType, Map<String, String> resultMap) {

	if ((orderNumber) == null || (orderNumber).equals("") || (status != 1 && status != 2 && status != 3 && status != 4)
		|| (userType != 1 && userType != 2 && userType != 3 && userType != 4 && userType != 5)) {
	    log.error("参数异常: orderNumber=" + orderNumber + ",status=" + status);
	    resultMap.put("status", PayConstants.WITHDRAW_STATUS_FAIL);
	    resultMap.put("msg", PayConstants.WITHDRAW_MSG_FAIL + ",参数异常");
	    return resultMap;
	}
	// 对比数据库相同状态的数据
	Map<String, Object> param = new HashMap<String, Object>();
	param.put("orderNumber", orderNumber);
	int table = commonService.getTableByUsertype(userType);

	if (table == CommonService.WITHRAW_RECORD) {// 用户和商家的提现数据
	    updateWithdrawalsRecordStateMapper.updateWithdrawalsRecord2Lock(Integer.valueOf(orderNumber));
	    param = updateWithdrawalsRecordStateMapper.selectByflowid(param);

	} else if (table == CommonService.WITHRAW_JOINT) {// 合作商的提现数据
	    updateWithdrawalsRecordStateMapper.updateWithdrawalsRecordJointid2Lock(Integer.valueOf(orderNumber));
	    param = updateWithdrawalsRecordStateMapper.selectByjointid(param);
	}

	if (param == null || param.size() == 0) {
	    resultMap.put("status", PayConstants.WITHDRAW_STATUS_FAIL);
	    resultMap.put("msg", PayConstants.WITHDRAW_MSG_FAIL + ",不存在提现记录");
	    log.error("参数异常:不存在单号为：" + orderNumber + "的提现记录");

	} else if (((param.get("status") == null) || (param.get("status").toString())
		.equals(PayConstants.WITHDRAW_STATUS_PROCESS))) {// 管控状态为3
	    log.error("提现申请重复提交");
	    resultMap.put("status", PayConstants.WITHDRAW_STATUS_PROCESS);
	    resultMap.put("msg", PayConstants.WITHDRAW_MSG_PROCESS);

	} else if ((param.get("status").toString()).equals(PayConstants.WITHDRAW_STATUS_SUCCESS)) {// 管控状态为1
	    log.error("该提现已完成，请核对！");
	    resultMap.put("status", PayConstants.WITHDRAW_STATUS_SUCCESS);
	    resultMap.put("msg", PayConstants.WITHDRAW_MSG_SUCCESS);
	} else if (!(param.get("status").toString()).equals(PayConstants.WITHDRAW_STATUS_AUDIT)) {// 管控状态不为0
	    log.error("该提现已处理过，请重提！");
	    resultMap.put("status", PayConstants.WITHDRAW_STATUS_INVALID);
	    resultMap.put("msg", PayConstants.WITHDRAW_MSG_ERROR);
	} 
	return resultMap;
    }

    /**
     * 提现
     * 
     * @param orderNumber
     * @param status
     * @param userType
     * @param withdrawType
     * @return
     * @throws Exception
     */
    public Map<String, String> withdrawMoney(String orderNumber, int status, int userType, String withdrawType) throws Exception {
	// 根据订单号查询数据
	Map<String, Object> mapEntity = withdrawMoneyServiceTools.getOrderData(orderNumber, userType);
	Map<String, String> resultMap = new HashMap<String, String>();
	Map<String, Object> map;
	switch (withdrawType) { // 汇付天下，融宝，u付，盛付通 1 2 3 4
	case PayConstants.WITHDRAW_TYPE_HF: {// 汇付天下

	    map = WithdrawMoneyServiceTools.putTheDataH(mapEntity, userType);// 参数组装
	    resultMap = chinaPnRPayImpl.PnRPay(map);// 汇付天下代发执行
	    if (resultMap == null || resultMap.size() < 1) {
		return null;
	    }
	    log.info("汇付天下------status：" + resultMap.get("status"));
	    log.info("汇付天下------RespDesc：" + resultMap.get("RespDesc"));
	}
	    break;
	case PayConstants.WITHDRAW_TYPE_RB: {// 融宝代发
		
		String temp = mapEntity.get("ispublic")+"";
		//对公时不传证件类型和证件号
		if("1".equals(temp)){
			mapEntity.put("idtype","");
			mapEntity.put("identity","");
		}
	    AgentPayRequest agentPayRequest = WithdrawMoneyServiceTools
		    .putTheDataR(mapEntity, String.valueOf(userType));// 参数组装

	    resultMap = rongTWithdrawMoneyImpl.WithdrawMoney(agentPayRequest);// 融宝代发执行
	    if (resultMap.get("status").equals("succ")) {
		resultMap.put("status", PayConstants.WITHDRAW_STATUS_PROCESS);
		resultMap.put("RespDesc", PayConstants.WITHDRAW_MSG_PROCESS);

	    } else {
		resultMap.put("status", PayConstants.WITHDRAW_STATUS_FAIL);
		resultMap.put("RespDesc", PayConstants.WITHDRAW_MSG_FAIL + "," + resultMap.get("reason"));
	    }
	    log.info("融宝------status：" + resultMap.get("status"));
	    log.info("融宝------RespDesc：" + resultMap.get("RespDesc"));

	}
	    break;
	case PayConstants.WITHDRAW_TYPE_UF: {// U付代发

	    map = WithdrawMoneyServiceTools.putTheDataU(mapEntity, userType);// 参数组装
	    try {
		resultMap = uPayService.uPay(map);

		log.info("U付代发------status：" + resultMap.get("status"));
		log.info("U付代发------RespDesc：" + resultMap.get("RespDesc"));

	    } catch (FailureException e) {
		log.error("U付代发异常,订单号为：" + map.get("orderId"), e);
	    }
	}
	    break;
	case PayConstants.WITHDRAW_TYPE_SFT: { // 盛付通代发
	    mapEntity.put("userType", userType);
	    resultMap = shengPayService.shengPay(mapEntity);

	    log.info("盛付通代发------status：" + resultMap.get("status"));
	    log.info("盛付通代发------RespDesc：" + resultMap.get("RespDesc"));

	}
	    break;
	case PayConstants.WITHDRAW_TYPE_KQ: { // 快钱代发
	    mapEntity.put("userType", userType);
	    resultMap = kuaiqianPayService.kuaiqianPay(mapEntity);
	    log.info("快钱代发------status：" + resultMap.get("status"));
	    log.info("快钱代发------RespDesc：" + resultMap.get("RespDesc"));

	}
	    break;
	case PayConstants.WITHDRAW_TYPE_TL: { // 通联代发
	    mapEntity.put("userType", userType);
	    resultMap = tongPayService.tongPay(mapEntity);
	    log.info("通联代发------status：" + resultMap.get("status"));
	    log.info("通联代发------RespDesc：" + resultMap.get("RespDesc"));

	}
	    break;
	case PayConstants.WITHDRAW_TYPE_LL: { // 连连代发
	    mapEntity.put("no_order", String.valueOf(mapEntity.get("id"))+"_"+userType);
	    resultMap = llPayService.withdrawMoney(mapEntity);
	    log.info("连连代发------status：" + resultMap.get("status"));
	    log.info("连连代发------RespDesc：" + resultMap.get("RespDesc"));

	}
	    break;
	}

	return resultMap; // 返回参数：2:失败，3处理中
    }

    /**
     * 1.4.2.申请提现（用户，商家）
     * 
     */
    @Override
    public Map<String, String> updateWithdrawalsRecord(List<Map<String, String>> amountMapList, Map<String, String> orderMap) {

	Map<String, String> resultMap = new HashMap<String, String>();

	log.info("方法-->商家手动提现 updateWithdrawalsRecord-->orderMap:" + orderMap + "-->amountMapList:" + amountMapList);

	// 参数验证
	if (amountMapList == null || amountMapList.size() == 0 || orderMap == null || orderMap.isEmpty()) {
	    log.info("商家手动提现 ，返回参数：" + 2);
	    resultMap.put("state", "2");
	    resultMap.put("msg", "参数异常");
	    resultMap.put("wFlowid", "");
	    return resultMap;
	}
	
	try {
	    Map<String, Object> wrRecordMap = new HashMap<String, Object>();
	    
		
	    // 用户ID
	    String uId = orderMap.get("uId");
	    String userType = orderMap.get("userType");
	    String account = orderMap.get("account");
	    if (!UtilString.stringIsNotBlank(uId) && UtilString.stringIsNotBlank(account)) {	//若没传UID但传了ACCOUNT
			Map<String, String> wMap = walletMapper.getWalletInfoByAccount(orderMap);
			if (wMap == null || wMap.size() == 0) {
			    resultMap.put("state", "4");
			    resultMap.put("msg", "账号不存在");
			    resultMap.put("wFlowid", "");
			    return resultMap;
			} else {
			    if (!wMap.get("status").equals("1")) {
				resultMap.put("state", "4");
				resultMap.put("msg", "账号被锁定");
				resultMap.put("wFlowid", "");
				return resultMap;
			    }
	
			    if (!String.valueOf(wMap.get("uid")).equals("0")) {
				uId = String.valueOf(wMap.get("uid"));
			    } else if (!String.valueOf(wMap.get("sellerid")).equals("0")) {
				uId = String.valueOf(wMap.get("sellerid"));
			    } else if (!String.valueOf(wMap.get("jointid")).equals("0")) {
				uId = String.valueOf(wMap.get("joinitd"));
			    }else if (!String.valueOf(wMap.get("allianceid")).equals("0")) {
				uId = String.valueOf(wMap.get("allianceid"));
			    }
			    orderMap.put("uId", uId);
			}
	    }

	    // redis参数
	    Map<String, Object> redisMap = new HashMap<String, Object>();
	    redisMap.put("type", 2);
	    redisMap.put("ledger_type", 1);
	    redisMap.put("cash_type", orderMap.get("cash"));
	    redisMap.put("uid", uId);
	    redisMap.put("name", orderMap.get("name"));

	    wrRecordMap.put("purpose", orderMap.get("purpose"));
	    wrRecordMap.put("tdesc", orderMap.get("tdesc"));
	    wrRecordMap.put("recchannel", orderMap.get("recchannel"));
	    wrRecordMap.put("uid", Integer.parseInt(uId));
	    wrRecordMap.put("name", orderMap.get("name"));
	    wrRecordMap.put("userType", userType);
	    wrRecordMap.put("cash_type", orderMap.get("cash"));

	    // 今天已经提现的金额
	    
	     double todayWithdrawals = getTodayWithdrawals(uId, userType);
	      
	     if (todayWithdrawals >= withdrawMoneyConfig.getMaxMoney()) { 
	    	 log.info("商家手动提现 ，返回参数：" + 3);
	    	 resultMap.put("state", "3"); 
	    	 resultMap.put("msg", "超过今天的最大提现金额"); 
	    	 resultMap.put("wFlowid", "");
	    	 return resultMap; 
	    }
	     

	    /* 不传mentionAccountId则需主动查询 */
	    String mentionAccountId = orderMap.get("mentionAccountId");
	    String balanceType =null;
	    if (!UtilString.stringIsNotBlank(mentionAccountId)) {	//如果没传提现账号ID
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("uId", uId);
			map.put("userType", userType);
			balanceType = amountMapList.get(0).get("balanceType");

	
			Map<String,Object> maMap = mentionAccountService.getSingleMA(map);
	
			if (maMap==null ) {
			    log.info("用户Id为：" + uId + "无提现账号");
			    log.info("用户/商户手动提现，返回参数：" + 4);
			    resultMap.put("state", "4");
			    resultMap.put("msg", "用户Id为：" + uId + "无提现账号");
			    resultMap.put("wFlowid", "");
			    return resultMap;
			}

			mentionAccountId = maMap.get("id").toString();
			log.info("查询的mentionAccountId为：" + mentionAccountId);
	    }

	    Map<String, Object> mentionMap = mentionAccountMapper.selectMentionById(mentionAccountId);

	    if (!withdrawMoneyUtils.checkMenTion(mentionMap)) {
			log.info("用户Id为：" + uId + "的提现账号不完整");
			log.info("商家手动提现 ，返回参数：" + 4);
			resultMap.put("state", "4");
			resultMap.put("msg", "用户Id为：" + uId + "的提现账号不完整");
			resultMap.put("wFlowid", "");
			return resultMap;
	    }

	    redisMap.put("account", mentionMap.get("account"));
	    redisMap.put("account_type", "1");// 提现类型
	    redisMap.put("fullname", mentionMap.get("username"));
	    redisMap.put("bankname", mentionMap.get("bank"));
	    redisMap.put("branchname", mentionMap.get("bankname"));
	    redisMap.put("idtype", mentionMap.get("idtype"));
	    redisMap.put("idcard", mentionMap.get("identity"));

	    wrRecordMap.put("account_type", "1");// 提现类型
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
	    // 手续费
	    double expenses = 2;

	    double commission = 0;// 佣金
	    double rebate = 0;// 返利
	    double income = 0;// 营收
	    double wallet = 0;// 钱包

	    // 手续费
	    double cExpenses = 0;// 佣金
	    double rExpenses = 0;// 返利
	    double iExpenses = 0;// 营收
	    double wExpenses = 0;// 钱包

		for (Map<String, String> map : amountMapList) {
			String amountType = map.get("balanceType");// 金额类型
			
			if(StringUtils.isNotBlank(map.get("expense"))){
				expenses = Double.valueOf(map.get("expense"));
			}else {
				if(userType.equals("2")){
					expenses=5;
				}else if(amountType.equals("1") && userType.equals("1")){//用户提现，手续费为提现总额 %6  cj
					expenses = 0;
				} else{
					expenses=5;
				}
			}
			
			if (amountType != null && amountType.equals("1")) {
			    commission = Double.parseDouble(map.get("amount"));
			    cExpenses = rExpenses != 0 || iExpenses != 0 || wExpenses != 0 ? 0 : expenses;
			} else if (amountType != null && amountType.equals("2")) {
			    rebate = Double.parseDouble(map.get("amount"));
			    rExpenses = iExpenses != 0 || cExpenses != 0 || wExpenses != 0 ? 0 : expenses;
			} else if (amountType != null && amountType.equals("3")) {
			    income = Double.parseDouble(map.get("amount"));
			    iExpenses = cExpenses != 0 || rExpenses != 0 || wExpenses != 0 ? 0 : expenses;
			} else if (amountType != null && amountType.equals("4")) {
			    wallet = Double.parseDouble(map.get("amount"));
			    wExpenses = cExpenses != 0 || rExpenses != 0 || iExpenses != 0 ? 0 : expenses;
			}else {
			    log.info("商家手动提现 ，返回参数：" + 2);
			    resultMap.put("state", "2");
			    resultMap.put("msg", "参数异常");
			    resultMap.put("wFlowid", "");
			    return resultMap;
			}
		}

	    // 判断提现金额是否大于2
	    if ((commission + wallet + rebate + income  - cExpenses - rExpenses - iExpenses - wExpenses) < 0) {
			log.info("商家手动提现  提现金额小于手续费");
			log.info("商家手动提现 ，返回参数：" + 3);
			resultMap.put("state", "3");
			resultMap.put("msg", "提现金额小于手续费");
			resultMap.put("wFlowid", "");
			return resultMap;
	    }
	    // 判断提现金额是否差多额度
	    
	     if ((todayWithdrawals + commission + wallet + rebate + income- cExpenses - rExpenses - iExpenses - wExpenses)
	    		 > withdrawMoneyConfig.getMaxMoney()) { 
	    	 log.info("商家手动提现 ，返回参数：" + 3);
	    	 resultMap.put("state", "3"); 
	    	 resultMap.put("msg", "提现金额大于今天的最大提现金额"); 
	    	 resultMap.put("wFlowid", ""); 
	    	 return resultMap; 
	      }
	     

	    redisMap.put("commission", String.format("%.2f ", commission - cExpenses));
	    redisMap.put("wallet", String.format("%.2f ", wallet - wExpenses));
	    redisMap.put("rebate", String.format("%.2f ", rebate - rExpenses));
	    redisMap.put("income", String.format("%.2f ", income - iExpenses));
	    redisMap.put("expenses", String.format("%.2f ",expenses));
	    
	    if(userType.equals("1")){
	    	redisMap.put("expenses_info","-");
	    }else{
	    	redisMap.put("expenses_info",expenses+"元/笔");
	    }
	    
	    wrRecordMap.put("commission", commission - cExpenses);
	    wrRecordMap.put("wallet", wallet - wExpenses);
	    wrRecordMap.put("rebate", rebate - rExpenses);
	    wrRecordMap.put("income", income - iExpenses);
	    wrRecordMap.put("expenses", expenses);

	    if (userType.trim().equals("1")) {
			if (withdrawMoneyConfig.isLimitUser()) {
			    if (withdrawMoneyUtils.checkMaxMoney(uId, userType, wrRecordMap, withdrawMoneyConfig.getMaxMoney())) {
					log.info("商家手动提现 ，返回参数：" + 3);
					resultMap.put("state", "3");
					resultMap.put("msg", "提现金额大于今天的最大提现金额");
					resultMap.put("wFlowid", "");
					return resultMap;
			    }
			}
	    } else if (userType.trim().equals("2")||userType.trim().equals("4")) {
			if (withdrawMoneyConfig.isLimitSeller()) {
			    if (withdrawMoneyUtils.checkMaxMoney(uId, userType, wrRecordMap, withdrawMoneyConfig.getMaxMoney())) {
					log.info("商家手动提现 ，返回参数：" + 3);
					resultMap.put("state", "3");
					resultMap.put("msg", "提现金额大于今天的最大提现金额");
					resultMap.put("wFlowid", "");
					return resultMap;
			    }
			}
	    }
	    Map<String, String> tempMap = new HashMap<String, String>();
	    tempMap.put("uId", uId);
	    tempMap.put("typeId", userType);

	    Map<String, String> walletMap = walletMapper.getWalletByUserId(tempMap);// 钱包信息

	    log.info("用户钱包数据" + walletMap);
	    double oldCommission = Double.parseDouble(walletMap.get("commision") == null ? "0" : String.valueOf(walletMap
		    .get("commision")));// 佣金
	    double oldRebate = Double.parseDouble(walletMap.get("balance") == null ? "0" : String.valueOf(walletMap
		    .get("balance")));// 返利
	    double oldIncome = Double.parseDouble(walletMap.get("sellerAmount") == null ? "0" : String.valueOf(walletMap
		    .get("sellerAmount")));// 营收
	    double oldWallet = Double
		    .parseDouble(walletMap.get("amount") == null ? "0" : String.valueOf(walletMap.get("amount")));// 钱包

	    wrRecordMap.put("oldCommission", oldCommission);
	    wrRecordMap.put("oldRebate", oldRebate);
	    wrRecordMap.put("oldIncome", oldIncome);
	    wrRecordMap.put("oldWallet", oldWallet);
	    wrRecordMap.put("sdate", UtilString.dateFormatNow());
	    wrRecordMap.put("udate", UtilString.dateFormatNow());
	    wrRecordMap.put("autowithdrawals", 0);

	    if (((oldCommission < 0 && commission == 0) || oldCommission >= commission)
				&& oldRebate >= rebate
				&& ((oldIncome < 0 && income == 0) || oldIncome >= income)
				&& oldWallet >= wallet) {

		redisMap.put("login_account", walletMap.get("account") == null ? "" : walletMap.get("account"));
		redisMap.put("wallet_balance", String.format("%.2f ", oldWallet - wallet));
		redisMap.put("rebate_balance", String.format("%.2f ", oldRebate - rebate));
		redisMap.put("commission_balance", String.format("%.2f ", oldCommission - commission));
		redisMap.put("income_balance", String.format("%.2f ", oldIncome - income));

	    } else {
			log.error("手动提现金额异常");
			log.info("商家手动提现 ，返回参数：" + 2);
			resultMap.put("state", "2");
			resultMap.put("msg", "余额不足");
			resultMap.put("wFlowid", "");
			return resultMap;
	    }

	    redisMap.put("sdate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

	    Map<String, String> signMap = new HashMap<String, String>();
	    signMap.put("oldSign", walletMap.get("sign"));
	    signMap.put("pwd", walletMap.get("pwd"));
	    signMap.put("amount", String.valueOf(redisMap.get("wallet_balance")));
	    signMap.put("balance", String.valueOf(redisMap.get("rebate_balance")));
	    signMap.put("commision", String.valueOf(redisMap.get("commission_balance")));
	    signMap.put("sellerAmount", String.valueOf(redisMap.get("income_balance")));
	    signMap.put("zbalance", String.valueOf(walletMap.get("zbalance")));
	    signMap.put("integral", String.valueOf(walletMap.get("Integral")));
	    signMap.put("uid", userType.equals("1") ? uId : "0");
	    signMap.put("sellerid", userType.equals("2") ? uId : "0");
	    signMap.put("jointid", userType.equals("3") ? uId : "0");

	    String sign = commonService.walletSign(signMap);
	    signMap.put("sign", sign);
	    signMap.put("signType", "MD5");
	    signMap.put("typeId", userType);
	    signMap.put("uId", uId);
	    Map<String, Object> amountMap = new HashMap<String, Object>();
	    amountMap.put("accountid", walletMap.get("accountid")); // 钱包id
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
	    amountMap.put("Integral", "0"); // 积分数
	    amountMap.put("qIntegral", walletMap.get("Integral"));// 写入积分前剩余积分
	    amountMap.put("hIntegral", walletMap.get("Integral"));// 写入积分后剩余积分
	    amountMap.put("sdate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));// 记录时间
	    amountMap.put("rtype",/*balanceType.equals("5")?"27":*/"6"); // 记录类型
	    amountMap.put("balanceType",balanceType); //余额类型
	    amountMap.put("money",commission + wallet + rebate + income); //余额类型
	    amountMap.put("ledgerType", userType);// 分账用户类型
	    amountMap.put("expenses", expenses);
	    try {
	    	commonService.withdrawOperate(wrRecordMap, redisMap, signMap, amountMap);
	    } catch (Exception e) {
			log.error("手动提现修改记录异常", e);
			resultMap.put("state", "1");
			resultMap.put("msg", "提现申请失败");
			resultMap.put("wFlowid", String.valueOf(redisMap.get("order_number")));
			return resultMap;
	    }
	    log.info("商家手动提现 ，返回参数：" + 0);

	    resultMap.put("state", "0");
	    resultMap.put("msg", "提现申请成功");
	    resultMap.put("wFlowid", String.valueOf(redisMap.get("order_number")));
	} catch (Exception e) {
	    log.error("商户/用户提现异常", e);
	    resultMap.put("state", "1");
	    resultMap.put("msg", "提现申请失败");
	    resultMap.put("wFlowid", "");
	}
		return resultMap;
    }

    /**
     * 1.4.3.申请提现（合作商）
     * 
     */
    @Override
    public Map<String, String> updateJointWithdrawalsRecord(List<Map<String, String>> paramMap, Map<String, String> orderMap) {

	log.info("方法-->合作商手动提现 updateJointWithdrawalsRecord-->orderMap:" + orderMap + "-->amountMapList:" + paramMap);

	Map<String, String> resultMap = new HashMap<String, String>();

	// 参数验证
	if (paramMap == null || paramMap.size() == 0 || orderMap == null || orderMap.isEmpty()) {
	    log.info("合作商手动提现，返回参数：" + 2);
	    resultMap.put("state", "2");
	    resultMap.put("msg", "参数异常");
	    resultMap.put("wFlowid", "");
	    return resultMap;
	}
	try {
	    Map<String, Object> wrRecordMap = new HashMap<String, Object>();

	    // 用户ID
	    String uId = orderMap.get("uId");
	    String userType = orderMap.get("userType");
	    String account = orderMap.get("account");
	    if (!UtilString.stringIsNotBlank(uId) && UtilString.stringIsNotBlank(account)) {
		Map<String, String> wMap = walletMapper.getWalletInfoByAccount(orderMap);
		if (wMap == null || wMap.size() == 0) {
		    resultMap.put("state", "4");
		    resultMap.put("msg", "账号不存在");
		    resultMap.put("wFlowid", "");
		    return resultMap;
		} else {
		    if (!wMap.get("status").equals("1")) {
			resultMap.put("state", "4");
			resultMap.put("msg", "账号被锁定");
			resultMap.put("wFlowid", "");
			return resultMap;
		    }
		    if (!String.valueOf(wMap.get("uid")).equals("0")) {
			uId = String.valueOf(wMap.get("uid"));
		    } else if (!String.valueOf(wMap.get("sellerid")).equals("0")) {
			uId = String.valueOf(wMap.get("sellerid"));
		    } else if (!String.valueOf(wMap.get("jointid")).equals("0")) {
			uId = String.valueOf(wMap.get("jointid"));
		    }
		    orderMap.put("uId", uId);
		}
	    }

	    // redis参数
	    Map<String, Object> redisMap = new HashMap<String, Object>();
	    redisMap.put("type", 2);
	    redisMap.put("ledger_type", 1);
	    redisMap.put("cash_type", orderMap.get("cash"));
	    redisMap.put("uid", uId);
	    redisMap.put("name", orderMap.get("name"));
	    redisMap.put("invoice", orderMap.get("invoice"));
	    redisMap.put("express", orderMap.get("express"));
	    redisMap.put("expressid", orderMap.get("expressid"));
	    redisMap.put("invoice", orderMap.get("invoice"));
	    redisMap.put("express", orderMap.get("express"));

	    wrRecordMap.put("purpose", orderMap.get("purpose"));
	    wrRecordMap.put("tdesc", orderMap.get("tdesc"));
	    wrRecordMap.put("recchannel", orderMap.get("recchannel"));
	    wrRecordMap.put("cash_type", orderMap.get("cash"));
	    wrRecordMap.put("uid", Integer.parseInt(uId));
	    wrRecordMap.put("name", orderMap.get("name"));
	    wrRecordMap.put("invoice", orderMap.get("invoice"));
	    wrRecordMap.put("express", orderMap.get("express"));
	    wrRecordMap.put("expressid", orderMap.get("expressid"));

	    // 今天已经提现的金额
	    /*
	     * double todayWithdrawals = getTodayWithdrawals(uId, userType);
	     * 
	     * if (todayWithdrawals >= withdrawMoneyConfig.getMaxMoney()) { log.info("合作商手动提现，返回参数：" + 3);
	     * resultMap.put("state", "3"); resultMap.put("msg", "超出今天的最大提现金额"); resultMap.put("wFlowid", "");
	     * return resultMap; }
	     */

	    /* 不传mentionAccountId则需主动查询 */
	    String mentionAccountId = orderMap.get("mentionAccountId");
	    if (!UtilString.stringIsNotBlank(mentionAccountId)) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uId", uId);
		map.put("userType", userType);
//		map.put("isuse", 0);
		List<Map<String, Object>> list = mentionAccountMapper.selectByuid(map);
		if (list == null || list.size() == 0) {
		    log.info("合作商Id为：" + uId + "无提现账号");
		    log.info("合作商手动提现，返回参数：" + 4);
		    resultMap.put("state", "4");
		    resultMap.put("msg", "合作商Id为：" + uId + "无提现账号");
		    resultMap.put("wFlowid", "");
		    return resultMap;
		}
		mentionAccountId = list.get(0).get("id").toString();
		log.info("查询的mentionAccountId为：" + mentionAccountId);
	    }

	    Map<String, Object> mentionMap = mentionAccountMapper.selectMentionById(mentionAccountId);

	    if (!withdrawMoneyUtils.checkMenTion(mentionMap)) {
		log.info("用户Id为：" + uId + "的提现账号不完整");
		log.info("合作商手动提现，返回参数：" + 4);
		resultMap.put("state", "4");
		resultMap.put("msg", "用户Id为：" + uId + "的提现账号不完整");
		resultMap.put("wFlowid", "");
		return resultMap;
	    }

	    redisMap.put("account", mentionMap.get("account"));
	    redisMap.put("account_type", "1");// 提现类型
	    redisMap.put("fullname", mentionMap.get("username"));
	    redisMap.put("bankname", mentionMap.get("bank"));
	    redisMap.put("branchname", mentionMap.get("bankname"));
	    redisMap.put("idtype", mentionMap.get("idtype"));
	    redisMap.put("idcard", mentionMap.get("identity"));

	    wrRecordMap.put("account_type", "1");// 提现类型
	    wrRecordMap.put("account", mentionMap.get("account"));
	    wrRecordMap.put("fullname", mentionMap.get("username"));
	    wrRecordMap.put("mobileId", mentionMap.get("mobileid"));
	    wrRecordMap.put("bankname", mentionMap.get("bankname"));
	    wrRecordMap.put("abbrev", mentionMap.get("abbrev"));
	    wrRecordMap.put("bank", mentionMap.get("bank"));
	    wrRecordMap.put("ispublic", mentionMap.get("ispublic"));
	    wrRecordMap.put("idtype", mentionMap.get("idtype"));
	    wrRecordMap.put("identity", mentionMap.get("identity"));
	    wrRecordMap.put("province", mentionMap.get("province"));
	    wrRecordMap.put("cityname", mentionMap.get("cityname"));

	    // 手续费
	    double expenses = 0;

	    if (Integer.parseInt(userType) == 2) {
		expenses = 2;
	    } else {
		expenses = 2;
	    }

	    double commission = 0;// 佣金
	    double rebate = 0;// 返利
	    double income = 0;// 营收
	    double wallet = 0;// 钱包

	    // 扣完手续费
	    double cExpenses = 0;// 佣金
	    double rExpenses = 0;// 返利
	    double iExpenses = 0;// 营收
	    double wExpenses = 0;// 钱包

	    for (Map<String, String> map : paramMap) {

		String amountType = map.get("balanceType");// 金额类型

		if (amountType != null && amountType.equals("1")) {
		    commission = Double.parseDouble(map.get("amount"));
		    cExpenses = rExpenses != 0 || iExpenses != 0 || wExpenses != 0 ? 0 : expenses;
		} else if (amountType != null && amountType.equals("2")) {
		    rebate = Double.parseDouble(map.get("amount"));
		    rExpenses = iExpenses != 0 || cExpenses != 0 || wExpenses != 0 ? 0 : expenses;
		} else if (amountType != null && amountType.equals("3")) {
		    income = Double.parseDouble(map.get("amount"));
		    iExpenses = cExpenses != 0 || rExpenses != 0 || wExpenses != 0 ? 0 : expenses;
		} else if (amountType != null && amountType.equals("4")) {
		    wallet = Double.parseDouble(map.get("amount"));
		    wExpenses = cExpenses != 0 || rExpenses != 0 || iExpenses != 0 ? 0 : expenses;
		} else {
		    log.info("合作商手动提现，返回参数：" + 2);
		    resultMap.put("state", "2");
		    resultMap.put("msg", "参数异常");
		    resultMap.put("wFlowid", "");
		    return resultMap;
		}
	    }

	    // 判断提现金额是否大于2
	    if ((commission + wallet + rebate + income - cExpenses - rExpenses - iExpenses - wExpenses) < 0) {
		log.info("合作商手动提现  提现金额小于手续费");
		log.info("合作商手动提现 ，返回参数：" + 3);
		resultMap.put("state", "3");
		resultMap.put("msg", "提现金额小于手续费");
		resultMap.put("wFlowid", "");
		return resultMap;
	    }

	    // 判断提现金额是否差多额度
	    /*
	     * if ((todayWithdrawals + commission + wallet + rebate + income - cExpenses - rExpenses -
	     * iExpenses - wExpenses) > withdrawMoneyConfig.getMaxMoney()) { log.info("合作商手动提现，返回参数：" + 3);
	     * resultMap.put("state", "3"); resultMap.put("msg", "超出今天的最大提现金额"); resultMap.put("wFlowid", "");
	     * return resultMap; }
	     */

	    redisMap.put("commission", commission - cExpenses);
	    redisMap.put("wallet", wallet - wExpenses);
	    redisMap.put("rebate", rebate - rExpenses);
	    redisMap.put("income", income - iExpenses);
	    wrRecordMap.put("commission", commission - cExpenses);
	    wrRecordMap.put("wallet", wallet - wExpenses);
	    wrRecordMap.put("rebate", rebate - rExpenses);
	    wrRecordMap.put("income", income - iExpenses);
	    wrRecordMap.put("expenses", expenses);
	    wrRecordMap.put("sdate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

	    if (withdrawMoneyConfig.isLimitJoint()) {
		if (withdrawMoneyUtils.checkMaxMoney(uId, userType, wrRecordMap, withdrawMoneyConfig.getMaxMoney())) {
		    log.info("合作商手动提现，返回参数：" + 3);
		    resultMap.put("state", "3");
		    resultMap.put("msg", "超出今天的最大提现金额");
		    resultMap.put("wFlowid", "");
		    return resultMap;
		}
	    }

	    Map<String, String> tempMap = new HashMap<String, String>();
	    tempMap.put("uId", uId);
	    tempMap.put("typeId", userType);

	    Map<String, String> walletMap = walletMapper.getWalletByUserId(tempMap);// 钱包信息

	    log.info("用户钱包数据" + walletMap);
	    double oldCommission = Double.parseDouble(walletMap.get("commision") == null ? "0" : String.valueOf(walletMap
		    .get("commision")));// 佣金
	    double oldRebate = Double.parseDouble(walletMap.get("balance") == null ? "0" : String.valueOf(walletMap
		    .get("balance")));// 返利
	    double oldIncome = Double.parseDouble(walletMap.get("sellerAmount") == null ? "0" : String.valueOf(walletMap
		    .get("sellerAmount")));// 营收
	    double oldWallet = Double
		    .parseDouble(walletMap.get("amount") == null ? "0" : String.valueOf(walletMap.get("amount")));// 钱包

	    wrRecordMap.put("oldCommission", oldCommission);
	    wrRecordMap.put("oldRebate", oldRebate);
	    wrRecordMap.put("oldIncome", oldIncome);
	    wrRecordMap.put("oldWallet", oldWallet);

	    if (oldCommission >= commission && oldRebate >= rebate && ((oldIncome < 0 && income == 0) || oldIncome >= income)
		    && oldWallet >= wallet) {
		redisMap.put("login_account", walletMap.get("account") == null ? "" : walletMap.get("account"));
		redisMap.put("wallet_balance", oldWallet - wallet);

		redisMap.put("rebate_balance", oldRebate - rebate);
		redisMap.put("commission_balance", oldCommission - commission);
		redisMap.put("income_balance", oldIncome - income);

	    } else {
		log.error("手动提现金额异常");
		log.info("合作商手动提现，返回参数：" + 2);
		resultMap.put("state", "2");
		resultMap.put("msg", "余额不足");
		resultMap.put("wFlowid", "");
		return resultMap;
	    }

	    redisMap.put("sdate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

	    Map<String, String> signMap = new HashMap<String, String>();

	    signMap.put("oldSign", walletMap.get("sign"));
	    signMap.put("pwd", walletMap.get("pwd"));
	    signMap.put("amount", String.valueOf(redisMap.get("wallet_balance")));
	    signMap.put("balance", String.valueOf(redisMap.get("rebate_balance")));
	    signMap.put("commision", String.valueOf(redisMap.get("commission_balance")));
	    signMap.put("sellerAmount", String.valueOf(redisMap.get("income_balance")));
	    signMap.put("zbalance", String.valueOf(walletMap.get("zbalance")));
	    signMap.put("integral", String.valueOf(walletMap.get("Integral")));
	    signMap.put("uid", userType.equals("1") ? uId : "0");
	    signMap.put("sellerid", userType.equals("2") ? uId : "0");
	    signMap.put("jointid", userType.equals("3") ? uId : "0");
	    signMap.put("allianceid", userType.equals("4") ? uId : "0");

	    String sign = commonService.walletSign(signMap);
	    signMap.put("sign", sign);
	    signMap.put("signType", "MD5");
	    signMap.put("typeId", userType);
	    signMap.put("uId", uId);

	    Map<String, Object> amountMap = new HashMap<String, Object>();
	    amountMap.put("accountid", walletMap.get("accountid")); // 钱包id
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
	    amountMap.put("qzbalance", walletMap.get("zbalance")); // 分账前赠送余额
	    amountMap.put("hzbalance", walletMap.get("zbalance")); // 分账后赠送余额
	    amountMap.put("amount", "0.00");// 钱包金额
	    amountMap.put("qamount", wrRecordMap.get("wallet"));// 充值前钱包余额
	    amountMap.put("hamount", wrRecordMap.get("wallet"));// 充值后钱包余额
	    amountMap.put("Integral", "0.00"); // 积分数
	    amountMap.put("qIntegral", walletMap.get("Integral"));// 写入积分前剩余积分
	    amountMap.put("hIntegral", walletMap.get("Integral"));// 写入积分后剩余积分
	    amountMap.put("sdate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));// 记录时间
	    amountMap.put("rtype", "6"); // 记录类型
	    amountMap.put("ledgerType", userType);// 分账用户类型
	    amountMap.put("expenses", expenses);

	    try {
		commonService.jointWithdrawOperate(wrRecordMap, redisMap, signMap, amountMap);
	    } catch (FailureException e) {
		log.error("合作商手动提现修改记录异常", e);
		resultMap.put("state", "1");
		resultMap.put("msg", "提现申请失败");
		resultMap.put("wFlowid", String.valueOf(redisMap.get("order_number")));
		return resultMap;
	    }

	    log.info("返回参数：" + 0);
	    resultMap.put("state", "0");
	    resultMap.put("msg", "提现申请成功");
	    resultMap.put("wFlowid", String.valueOf(redisMap.get("order_number")));
	} catch (Exception e) {
	    log.error("合作商提现异常", e);
	    resultMap.put("state", "1");
	    resultMap.put("msg", "提现申请失败");
	    resultMap.put("wFlowid", "");
	}
	return resultMap;
    }

    /**
     * 申请提现（服务员） 提返利时返利不够从佣金扣除部分金额，返利优先
     */
    @Override
    public Map<String, String> updateWithdrawalsRecord2Waiter(List<Map<String, String>> amountMapList,
	    Map<String, String> orderMap) {

	Map<String, String> resultMap = new HashMap<String, String>();

	log.info("方法-->服务员申请提现 updateWithdrawalsRecord2Waiter-->orderMap:" + orderMap + "-->amountMapList:" + amountMapList);

	int expensesType = 0;
	// 参数验证
	if (amountMapList == null || amountMapList.size() == 0 || orderMap == null || orderMap.isEmpty()) {
	    log.info("服务员申请提现 ，返回参数：" + 2);
	    resultMap.put("state", "2");
	    resultMap.put("msg", "参数异常");
	    resultMap.put("wFlowid", "");
	    return resultMap;
	}

	Map<String, Object> wrRecordMap = new HashMap<String, Object>();

	// 用户ID
	String uId = orderMap.get("uId");
	String userType = orderMap.get("userType");

	// redis参数
	Map<String, Object> redisMap = new HashMap<String, Object>();
	redisMap.put("type", 2);
	redisMap.put("ledger_type", 1);
	redisMap.put("cash_type", orderMap.get("cash"));
	redisMap.put("uid", uId);
	redisMap.put("name", orderMap.get("name"));

	wrRecordMap.put("purpose", orderMap.get("purpose"));
	wrRecordMap.put("tdesc", orderMap.get("tdesc"));
	wrRecordMap.put("recchannel", orderMap.get("recchannel"));
	wrRecordMap.put("uid", Integer.parseInt(uId));
	wrRecordMap.put("name", orderMap.get("name"));
	wrRecordMap.put("userType", userType);
	wrRecordMap.put("cash_type", orderMap.get("cash"));

	// 今天已经提现的金额
	/*
	 * double todayWithdrawals = getTodayWithdrawals(uId, userType);
	 * 
	 * if (todayWithdrawals >= withdrawMoneyConfig.getMaxMoney()) { log.info("服务员申请提现 ，返回参数：" + 3);
	 * resultMap.put("state", "3"); resultMap.put("msg", "超过今天的最大提现金额"); resultMap.put("wFlowid", "");
	 * return resultMap; }
	 */

	String mentionAccountId = orderMap.get("mentionAccountId");

	Map<String, Object> mentionMap = mentionAccountMapper.selectMentionById(mentionAccountId);

	if (!withdrawMoneyUtils.checkMenTion(mentionMap)) {
	    log.info("用户Id为：" + uId + "的提现账号不完整");
	    log.info("商家手动提现 ，返回参数：" + 4);
	    resultMap.put("state", "4");
	    resultMap.put("msg", "用户Id为：" + uId + "的提现账号不完整");
	    resultMap.put("wFlowid", "");
	    return resultMap;
	}

	redisMap.put("account", mentionMap.get("account"));
	redisMap.put("account_type", "1");// 提现类型
	redisMap.put("fullname", mentionMap.get("username"));
	redisMap.put("bankname", mentionMap.get("bank"));
	redisMap.put("branchname", mentionMap.get("bankname"));
	redisMap.put("idtype", mentionMap.get("idtype"));
	redisMap.put("idcard", mentionMap.get("identity"));

	wrRecordMap.put("account_type", "1");// 提现类型
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
	// 手续费
	double expenses = 0;

	if (Integer.parseInt(userType) == 2) {
	    expenses = 2;
	} else {
	    expenses = 2;
	}

	double commission = 0;// 佣金
	double rebate = 0;// 返利
	double income = 0;// 营收
	double wallet = 0;// 钱包

	// 手续费
	double cExpenses = 0;// 佣金
	double rExpenses = 0;// 返利
	double iExpenses = 0;// 营收
	double wExpenses = 0;// 钱包

	for (Map<String, String> map : amountMapList) {

	    String amountType = map.get("balanceType");// 金额类型

	    if (amountType != null && amountType.equals("1")) {
		commission = Double.parseDouble(map.get("amount"));
		cExpenses = rExpenses != 0 || iExpenses != 0 || wExpenses != 0 ? 0 : expenses;
	    } else if (amountType != null && amountType.equals("2")) {
		rebate = Double.parseDouble(map.get("amount"));
		rExpenses = iExpenses != 0 || cExpenses != 0 || wExpenses != 0 ? 0 : expenses;
	    } else if (amountType != null && amountType.equals("3")) {
		income = Double.parseDouble(map.get("amount"));
		iExpenses = cExpenses != 0 || rExpenses != 0 || wExpenses != 0 ? 0 : expenses;
	    } else if (amountType != null && amountType.equals("4")) {
		wallet = Double.parseDouble(map.get("amount"));
		wExpenses = cExpenses != 0 || rExpenses != 0 || iExpenses != 0 ? 0 : expenses;
	    } else {
		log.info("服务员申请提现 ，返回参数：" + 2);
		resultMap.put("state", "2");
		resultMap.put("msg", "参数异常");
		resultMap.put("wFlowid", "");
		return resultMap;
	    }
	}

	// 判断提现金额是否大于2
	if ((commission + wallet + rebate + income - cExpenses - rExpenses - iExpenses - wExpenses) < 0) {
	    log.info("服务员申请提现  提现金额小于手续费");
	    log.info("服务员申请提现，返回参数：" + 3);
	    resultMap.put("state", "3");
	    resultMap.put("msg", "提现金额小于手续费");
	    resultMap.put("wFlowid", "");
	    return resultMap;
	}

	// 判断提现金额是否差多额度
	/*
	 * if ((todayWithdrawals + commission + wallet + rebate + income - cExpenses - rExpenses - iExpenses -
	 * wExpenses) > withdrawMoneyConfig.getMaxMoney()) { log.info("服务员申请提现 ，返回参数：" + 3);
	 * resultMap.put("state", "3"); resultMap.put("msg", "提现金额大于今天的最大提现金额"); resultMap.put("wFlowid", "");
	 * return resultMap; }
	 */

	Map<String, String> tempMap = new HashMap<String, String>();
	tempMap.put("uId", uId);
	tempMap.put("typeId", userType);

	Map<String, String> walletMap = walletMapper.getWalletByUserId(tempMap);// 钱包信息

	log.info("用户钱包数据" + walletMap);
	double oldCommission = Double.parseDouble(walletMap.get("commision") == null ? "0" : String.valueOf(walletMap
		.get("commision")));// 佣金
	double oldRebate = Double.parseDouble(walletMap.get("balance") == null ? "0" : String.valueOf(walletMap.get("balance")));// 返利
	double oldIncome = Double.parseDouble(walletMap.get("sellerAmount") == null ? "0" : String.valueOf(walletMap
		.get("sellerAmount")));// 营收
	double oldWallet = Double.parseDouble(walletMap.get("amount") == null ? "0" : String.valueOf(walletMap.get("amount")));// 钱包

	wrRecordMap.put("oldCommission", oldCommission);
	wrRecordMap.put("oldRebate", oldRebate);
	wrRecordMap.put("oldIncome", oldIncome);
	wrRecordMap.put("oldWallet", oldWallet);

	if (((oldCommission >= commission && oldRebate >= rebate) || ((oldCommission + oldRebate) >= (rebate + commission)))
		&& ((oldIncome < 0 && income == 0) || oldIncome >= income) && oldWallet >= wallet) {

	    if (oldRebate < rebate) {
		if (oldRebate <= expenses && oldCommission <= expenses) {
		    log.error("服务员申请提现金额异常，返利金额或佣金金额小于手续费");
		    log.info("服务员申请提现 ，返回参数：" + 2);
		    resultMap.put("state", "2");
		    resultMap.put("msg", "参数异常");
		    resultMap.put("wFlowid", "");
		    return resultMap;
		}

		if (oldRebate < expenses) {
		    cExpenses = expenses;
		    rExpenses = 0;
		}
		commission = rebate - oldRebate;
		rebate = oldRebate;

	    }

	    if (rExpenses != 0) {
		expensesType = PayConstants.EXPENSES_REBATE;
	    }
	    if (cExpenses != 0) {
		expensesType = PayConstants.EXPENSES_COMMISSION;
	    }
	    if (wExpenses != 0) {
		expensesType = PayConstants.EXPENSES_WALLET;
	    }
	    if (iExpenses != 0) {
		expensesType = PayConstants.EXPENSES_INCOME;
	    }

	    redisMap.put("login_account", walletMap.get("account") == null ? "" : walletMap.get("account"));
	    redisMap.put("rebate_balance", String.format("%.2f ", oldRebate - rebate));
	    redisMap.put("commission_balance", String.format("%.2f ", oldCommission - commission));
	    redisMap.put("commission", String.format("%.2f ", commission - cExpenses));
	    redisMap.put("rebate", String.format("%.2f ", rebate - rExpenses));
	    wrRecordMap.put("commission", commission - cExpenses);
	    wrRecordMap.put("rebate", rebate - rExpenses);
	    redisMap.put("income_balance", String.format("%.2f ", oldIncome - income));
	    redisMap.put("wallet_balance", String.format("%.2f ", oldWallet - wallet));
	    redisMap.put("income", String.format("%.2f ", income - iExpenses));
	    redisMap.put("wallet", String.format("%.2f ", wallet - wExpenses));
	    wrRecordMap.put("wallet", wallet - wExpenses);
	    wrRecordMap.put("income", income - iExpenses);
	    wrRecordMap.put("expenses", expenses);
	    wrRecordMap.put("expensesType", expensesType);
	    wrRecordMap.put("sdate", UtilString.dateFormatNow());
	    wrRecordMap.put("udate", UtilString.dateFormatNow());
	    wrRecordMap.put("autowithdrawals", 0);
	} else {
	    log.error("手动提现金额异常");
	    log.info("商家手动提现 ，返回参数：" + 2);
	    resultMap.put("state", "2");
	    resultMap.put("msg", "参数异常");
	    resultMap.put("wFlowid", "");
	    return resultMap;
	}

	if (userType.trim().equals("1")) {
	    if (withdrawMoneyConfig.isLimitUser()) {
		if (withdrawMoneyUtils.checkMaxMoney(uId, userType, wrRecordMap, withdrawMoneyConfig.getMaxMoney())) {
		    log.info("服务员申请提现 ，返回参数：" + 3);
		    resultMap.put("state", "3");
		    resultMap.put("msg", "提现金额大于今天的最大提现金额");
		    resultMap.put("wFlowid", "");
		    return resultMap;
		}
	    }
	} else if (userType.trim().equals("2")) {
	    if (withdrawMoneyConfig.isLimitSeller()) {
		if (withdrawMoneyUtils.checkMaxMoney(uId, userType, wrRecordMap, withdrawMoneyConfig.getMaxMoney())) {
		    log.info("服务员申请提现 ，返回参数：" + 3);
		    resultMap.put("state", "3");
		    resultMap.put("msg", "提现金额大于今天的最大提现金额");
		    resultMap.put("wFlowid", "");
		    return resultMap;
		}
	    }
	}

	redisMap.put("sdate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

	Map<String, String> signMap = new HashMap<String, String>();

	signMap.put("oldSign", walletMap.get("sign"));
	signMap.put("pwd", walletMap.get("pwd"));
	signMap.put("amount", String.valueOf(redisMap.get("wallet_balance")));
	signMap.put("balance", String.valueOf(redisMap.get("rebate_balance")));
	signMap.put("commision", String.valueOf(redisMap.get("commission_balance")));
	signMap.put("sellerAmount", String.valueOf(redisMap.get("income_balance")));
	signMap.put("zbalance", String.valueOf(walletMap.get("zbalance")));
	signMap.put("integral", String.valueOf(walletMap.get("Integral")));
	signMap.put("uid", userType.equals("1") ? uId : "0");
	signMap.put("sellerid", userType.equals("2") ? uId : "0");
	signMap.put("jointid", userType.equals("3") ? uId : "0");

	String sign = commonService.walletSign(signMap);
	signMap.put("sign", sign);
	signMap.put("signType", "MD5");
	signMap.put("typeId", userType);
	signMap.put("uId", uId);

	Map<String, Object> amountMap = new HashMap<String, Object>();
	amountMap.put("accountid", walletMap.get("accountid")); // 钱包id
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
	amountMap.put("Integral", "0"); // 积分数
	amountMap.put("qIntegral", walletMap.get("Integral"));// 写入积分前剩余积分
	amountMap.put("hIntegral", walletMap.get("Integral"));// 写入积分后剩余积分
	amountMap.put("sdate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));// 记录时间
	amountMap.put("rtype", "6"); // 记录类型
	amountMap.put("ledgerType", userType);// 分账用户类型
	amountMap.put("expenses", expenses);
	try {
	    commonService.withdrawOperate(wrRecordMap, redisMap, signMap, amountMap);
	} catch (Exception e) {
	    log.error("手动提现修改记录异常", e);
	    resultMap.put("state", "1");
	    resultMap.put("msg", "提现申请失败");
	    resultMap.put("wFlowid", String.valueOf(redisMap.get("order_number")));
	    return resultMap;
	}

	log.info("商家手动提现 ，返回参数：" + 0);

	resultMap.put("state", "0");
	resultMap.put("msg", "提现申请成功");
	resultMap.put("wFlowid", String.valueOf(redisMap.get("order_number")));
	return resultMap;
    }

    /**
     * 获取该商户/合作商当天的提现总金额
     * 
     * @return
     */
    private double getTodayWithdrawals(String id, String type) {
	double allMoney = 0.0;

	if ("1".equals(type.trim()) || "2".equals(type.trim()) || "4".equals(type.trim())) {// 商户与用户  4 区域代理
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
     * 当天的提现总金额
     */
	@Override
	public Map<String, String> countTodayWithdrawAmount(
			Map<String, String> paraMap) throws FailureException, TException {
		log.info("统计今日提现总额"+paraMap);
		Map<String, String> resultMap;
		try {
			resultMap = new HashMap<>();
			//验证参数 uId，userType不可为空
			if(StringUtils.isBlank(paraMap.get("uId"))||StringUtils.isBlank(paraMap.get("userType"))){
				log.error("uId和userType不可为空");
				throw new FailureException(1,"uId和userType不可为空");
			}
			
			Map<String, Object> countWithdraw = mentionAccountMapper.countWithdraw(paraMap);
			
			resultMap.put("totalAmount",countWithdraw.get("totalAmount").toString());
		} catch (Exception e) {
			log.error("查询当日提现总额失败",e);
			throw new FailureException(1,"查询异常");
		}
		return resultMap;
	}
	
    /**
     * 钱包加密
     * 
     * @param signMap
     * @return
     */
//    public String sign(Map<String, String> signMap) {
//	StringBuffer sb = new StringBuffer();
//	sb.append(String.valueOf(signMap.get("uid")));
//	sb.append(String.valueOf(signMap.get("sellerid")));
//	sb.append(String.valueOf(signMap.get("jointid")));
//	sb.append(signMap.get("pwd") == null || String.valueOf(signMap.get("pwd")).equals("null") ? "" : String.valueOf(signMap
//		.get("pwd")));
//	sb.append(String.format("%.2f", Double.valueOf(String.valueOf(signMap.get("amount")))));
//	sb.append(String.format("%.2f", Double.valueOf(String.valueOf(signMap.get("balance")))));
//	sb.append(String.format("%.2f", Double.valueOf(String.valueOf(signMap.get("commision")))));
//	sb.append(String.format("%.2f", Double.valueOf(String.valueOf(signMap.get("zbalance")))));
//	sb.append(String.valueOf((new BigDecimal(signMap.get("Integral")).longValue())));
//	sb.append(String.format("%.2f", Double.valueOf(String.valueOf(signMap.get("sellerAmount")))));
//
//	log.info("钱包加密：" + sb.toString());
//
//	return MD5.Encode(sb.toString());
//    }

}
