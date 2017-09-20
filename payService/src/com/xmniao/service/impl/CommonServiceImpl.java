package com.xmniao.service.impl;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
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
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.remoting.exception.RemotingException;
import com.xmniao.common.MD5;
import com.xmniao.common.PayConstants;
import com.xmniao.dao.ExpensesMpper;
import com.xmniao.dao.MentionAccountMapper;
import com.xmniao.dao.UpdateWalletBalanceMapper;
import com.xmniao.dao.UpdateWithdrawalsRecordStateMapper;
import com.xmniao.dao.WalletMapper;
import com.xmniao.service.CommonService;
import com.xmniao.service.msg.WithdrawDetailProducer;
import com.xmniao.service.msg.WithdrawProducer;
import com.xmniao.service.pay.WithdrawMoneyServiceImpl;
import com.xmniao.thrift.ledger.FailureException;

@Service
public class CommonServiceImpl implements CommonService {

	/** 日志记录 */
	private final Logger log = Logger.getLogger(CommonServiceImpl.class);
	/** 提现接口服务 */
	@Autowired
	private WithdrawMoneyServiceImpl withdrawMoneyServiceImpl;
	
	 @Autowired
	public UpdateWalletBalanceMapper updateWalletBalanceMapper;
	/** */
	@Autowired
	private UpdateWithdrawalsRecordStateMapper recordStateMapper;
	/** */
	@Autowired
	private UpdateWithdrawalsRecordStateMapper updateWithdrawalsRecordStateMapper;
	/** 提现返回消息生产者*/
	@Autowired
	private WithdrawProducer withdrawProducer;
	
	@Autowired
	private StringRedisTemplate redisTemplate;
	@Autowired
	private ExpensesMpper expensesMpper;
	@Autowired
	private WalletMapper walletMapper;
	@Autowired
	private MentionAccountMapper mentionAccountMapper;
	@Autowired
	private  WithdrawDetailProducer withdrawDetailProducer;
	
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
	 * 更新提现打款状态
	 * @param orderNumber 订单号
	 * @param userType  用户类型
	 * @param status 状态
	 * @param withdrawType 提现方式
	 * @param uwsMap 分账系统参数
	 */

	@Override
	public void updateWithdrawState(String orderNumber, String userType,
			int status, String withdrawType, Map<String, String> uwsMap)
			throws Exception {

		//更新支付系统提现状态
		int updateState = withdrawMoneyServiceImpl.updateWithdrawState(
				orderNumber, status, Integer.parseInt(userType), withdrawType,
				uwsMap.get("platform_code"),uwsMap.get("Message"));
		
		if (updateState != 1) {
			throw new Exception("提现记录表更新失败！");
		}
		Map<String, String> sendMap = new HashMap<String, String>();
		String statusStr = String.valueOf(uwsMap.get("status"));
		String sts = "";
		//数据库状态 跟 返回状态 不一致
        if(statusStr.equals(PayConstants.WITHDRAW_STATUS_SUCCESS)){
        	sts = PayConstants.WITHDRAW_RETURN_STATUS_SUCCESS;
		}else if(statusStr.equals(PayConstants.WITHDRAW_STATUS_FAIL)){
			sts = PayConstants.WITHDRAW_RETURN_STATUS_FAIL;
		}else if(statusStr.equals(PayConstants.WITHDRAW_STATUS_PROCESS)){
			sts = PayConstants.WITHDRAW_RETURN_STATUS_PROCESS;
		}
		
		sendMap.put("orderNumber", uwsMap.get("orderNumber"));
		sendMap.put("status", sts);
		sendMap.put("message", uwsMap.get("Message"));
		sendMap.put("platform_code",uwsMap.get("platform_code"));
		sendMap.put("userType", userType);
		log.info("发送消息："+sendMap);
		// 发送提现返回消息
		withdrawProducer.withdrawSendMsg(sendMap);

	}

	

	/**
	 * 获取用户商家提现记录
	 * 
	 * @param statusMap
	 * @return
	 */
	@Override
	public Map<String, Object> selectByflowid(Map<String, Object> statusMap) {
		return recordStateMapper.selectByflowid(statusMap);
	}

	/**
	 * 获取合作商提现记录
	 * 
	 * @param paramMap
	 * @return
	 */
	@Override
	public Map<String, Object> selectByjointid(Map<String, Object> paramMap) {
		return recordStateMapper.selectByjointid(paramMap);
	}

	/**
	 * 根据提现流水号 得到提现状态
	 * @param userType
	 * @param orderNumber
	 * @return
	 */
	@Override
	// 0未打款 1 提现成功 2 提现失败 3 银行处理中 4 财务审核不通过     -1参数异常
	public String getWithdrawStatus(String userType, String orderNumber) {

		Map<String, Object> withdRawals = null;
		String status = PayConstants.WITHDRAW_STATUS_AUDIT;

		// 获取提现记录参数
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("orderNumber", orderNumber);
		int table = getTableByUsertype(Integer.valueOf(userType));
		if (userType == null) {
			log.error("userTyp为空");
			status = "-1";
			return status;
		} else if (table == WITHRAW_RECORD) {

			withdRawals = this.selectByflowid(paramMap);
		} else {

			withdRawals = this.selectByjointid(paramMap);
		}

		if (withdRawals == null) {
			log.error("代发流水号[{}]无对应的流水订单:" + orderNumber);
			status = "-1";
		} else {
			status = String.valueOf(withdRawals.get("status"));
		}

		return status;

	}

	/**
	 * 根据用户类型 类型订单号得到提现记录   **注意：这个用户类型只是用来判断哪张表    
	 * @param orderNumber
	 * @param userType
	 * @return
	 */
	@Override
	public Map<String, Object> getWithdrawInfoByNumUsertype(String orderNumber,
			int userType) {

		Map<String, Object> statusMap = new HashMap<String, Object>();
		statusMap.put("orderNumber", orderNumber);
		statusMap.put("userType", String.valueOf(userType));
		Map<String, Object> paramsMap = null;
		int table = getTableByUsertype(userType);
		if (table == WITHRAW_RECORD) {
			paramsMap = updateWithdrawalsRecordStateMapper
					.selectByflowid(statusMap);
			paramsMap.put("id", paramsMap.get("flowid"));
		} else if (table == WITHRAW_JOINT) {
			paramsMap = updateWithdrawalsRecordStateMapper
					.selectByjointid(statusMap);
		}
		return paramsMap;

	}

	/**
	 * 根据 支付方式 支付状态 得到提现记录数      **注意：这个用户类型只是用来判断哪张表    
	 * @param userType
	 * @param status
	 * @param platform
	 * @return
	 */
	@Override
	public Integer getWithdrawCountByStatusPlatform(int userType,
			String status, String platform) {
		Integer count = null;
		// 查询记录表条件
		Map<String, Object> reqParams = new HashMap<String, Object>();
		reqParams.put("status", status);
		reqParams.put("platform", platform); // ????程序字段：1 汇付 2 融宝 3 U付
												// 5快钱//数据库字段：0 汇付 1 U付 // 2 融宝
												// 3 // 支付宝 4 财付通 5 其他

		int table = getTableByUsertype(userType);
		if (table == WITHRAW_RECORD) {// 查询会员、商家 需要更新的记录数
			count = updateWithdrawalsRecordStateMapper
					.selectByflowidCounts(reqParams);

		} else if (table == WITHRAW_JOINT) {// 查询合作商 需要更新的记录数
			count = updateWithdrawalsRecordStateMapper
					.selectByjointidCounts(reqParams);
		}

		return count;

	}
	
	/**
	 * 根据 支付方式 支付状态 得到提现列表      **注意：这个用户类型只是用来判断哪张表    
	 * @param userType 用户类型
	 * @param status 状态
	 * @param platform 支付方式
	 * @param start 开始记录
	 * @param end   结束记录
	 * @return
	 */

	@Override
	public List<Map<String, Object>> getWithdrawListByStatusPlatform(
			int userType, String status, String platform, int start, int end) {

		List<Map<String, Object>> list = null;
		Map<String, Object> reqParams = new HashMap<String, Object>();
		reqParams.put("status", status);
		reqParams.put("platform", platform); // ????程序字段：1 汇付 2 融宝 3 U付
												// 5快钱//数据库字段
		reqParams.put("start", start);
		reqParams.put("end", end);
		int table = getTableByUsertype(userType);
		if (table == WITHRAW_RECORD) {// 查询需要更新的会员、商家
			list = updateWithdrawalsRecordStateMapper
					.selectByflowids(reqParams);
		} else if (table == WITHRAW_JOINT) {// 查询需要更新的合作商提现记录详情 一次查询500条
			list = updateWithdrawalsRecordStateMapper
					.selectByjointids(reqParams);
		}

		return list;
	}

	@Override
	public int getTableByUsertype(int userType) {
		int table = 0;
		if (userType ==PayConstants.USERTYPE_SELLER || userType == PayConstants.USERTYPE_USER || userType == PayConstants.USERTYPE_CHAIN) {// 查询会员、商家
																// 需要更新的记录数
			table = WITHRAW_RECORD;

		} else if (userType == PayConstants.USERTYPE_JOINT) {// 查询合作商 需要更新的记录数
			table = WITHRAW_JOINT;
		}
		return table;
	}
	
	
	@Override
	@Transactional(rollbackFor = { FailureException.class, Exception.class }, isolation= Isolation.SERIALIZABLE, timeout=10)
	public void jointWithdrawOperate(Map<String, Object> wrRecordMap,
			Map<String, Object> redisMap, Map<String, String> signMap,
			Map<String, Object> amountMap) throws FailureException {
		log.info("增加提现记录参数：" + wrRecordMap);
		
		int state = updateWithdrawalsRecordStateMapper
				.insertJWRRecord(wrRecordMap);

		if (state == 0) {
			log.error("增加提现记录失败！");
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

	
		amountMap.put("orderId", redisMap.get("order_number"));// 分账订单Id
		signMap.put("lastDate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		state = walletMapper.updateWalletBySign(signMap);
		if (state == 1) {
			state = updateWalletBalanceMapper.insertWalletRecord(amountMap);
			if (state == 1) {
				log.info("提现记录插入成功！");
				// 传入redis
				String redisJson = JSON.toJSONString(redisMap);
				log.info("redisJson:" + redisJson);
				redisTemplate.opsForList().leftPush(jointRedisName, redisJson);
			} else {
				log.error("钱包修改记录插入失败！");
				throw new FailureException(1, "添加钱包修改记录失败");
			}
			log.info("修改成功" + amountMap.toString());
		} else {
			log.error("修改异常" + amountMap.toString());
			throw new FailureException(1, "提现失败");
		}
		log.info("合作商提现修改余额状态:" + state);
	}
	
	@Override
	@Transactional(rollbackFor = { FailureException.class, Exception.class }, isolation= Isolation.SERIALIZABLE, timeout=10)
	public int withdrawOperate(Map<String, Object> wrRecordMap,
			Map<String, Object> redisMap, Map<String, String> signMap,
			Map<String, Object> amountMap) throws FailureException, UnsupportedEncodingException, MQClientException, RemotingException, MQBrokerException, InterruptedException {
		int state = updateWithdrawalsRecordStateMapper
				.insertWRRecord(wrRecordMap);
		
		if (state == 0) {
			log.error("增加提现记录失败！");
			throw new FailureException(1, "添加提现记录失败");
		} else {
			Double expenses =	(Double) wrRecordMap.get("expenses");	
			if(expenses != 0){
				int addState = expensesMpper.addExpenses(wrRecordMap);
				if (addState == 0) {
					log.error("增加提现手续费记录失败！");
					throw new FailureException(1, "添加提现手续费记录失败");
				} 
			}
			
			redisMap.put("order_number",
					String.valueOf(wrRecordMap.get("id")));
		}

		amountMap.put("orderId", redisMap.get("order_number"));// 分账订单Id
		signMap.put("lastDate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		state = walletMapper.updateWalletBySign(signMap);
		
		if (state == 1) {
			state = updateWalletBalanceMapper.insertWalletRecord(amountMap);
			if (state == 1) {
				log.info("提现记录插入成功！");
				// 传入redis
				String redisJson = JSON.toJSONString(redisMap);
				log.info("redisJson:" + redisJson);
				//提现类型为用户，则发送提现信息
				if ("1".equals(amountMap.get("ledgerType"))) {
					Map<String,String> MQmap = new HashMap<>();
					MQmap.put("type", amountMap.get("balanceType")+"");
					MQmap.put("amount", amountMap.get("money")+"");
					MQmap.put("uid", redisMap.get("uid")+"");
					MQmap.put("description", wrRecordMap.get("tdesc")+"");
					MQmap.put("sdate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
					withdrawDetailProducer.sendMsg(MQmap);//发送提现信息
				}
				redisTemplate.opsForList().leftPush(storeRedisName, redisJson);
			} else {
				log.error("提现记录插入失败！");
				throw new FailureException(1, "添加钱包记录失败");
			}
			log.info("修改成功" + amountMap.toString());
		} else {
			log.error("修改异常" + amountMap.toString());
			throw new FailureException(1, "提现失败");
		}
		return state;
	}	
	
	@Override
	public double getTodayWithdrawals(String id, String type) {
		double allMoney = 0.0;

		if ("1".equals(type.trim()) || "2".equals(type.trim())|| "4".equals(type.trim())) {// 商户与用户   4区域代理
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



	@Override
	public String walletSign(Map<String, String> signMap) {
		log.info("要签名的信息："+signMap);
		StringBuffer sb = new StringBuffer();
		sb.append(String.valueOf(signMap.get("uid")));
		sb.append(String.valueOf(signMap.get("sellerid")));
		sb.append(String.valueOf(signMap.get("jointid")));
		sb.append(signMap.get("pwd") == null
				|| String.valueOf(signMap.get("pwd")).equals("null") ? ""
				: String.valueOf(signMap.get("pwd")));
		sb.append(String.format("%.2f",
				Double.valueOf(String.valueOf(signMap.get("amount")))));
		sb.append(String.format("%.2f",
				Double.valueOf(String.valueOf(signMap.get("balance")))));
		sb.append(String.format("%.2f",
				Double.valueOf(String.valueOf(signMap.get("commision")))));
		sb.append(String.format("%.2f",
				Double.valueOf(String.valueOf(signMap.get("zbalance")))));
		

		sb.append(String.valueOf((new BigDecimal(signMap.get("integral")).longValue())));
		sb.append(String.format("%.2f",
				Double.valueOf(String.valueOf(signMap.get("sellerAmount")))));

		log.info("钱包加密：" + sb.toString());

		return MD5.Encode(sb.toString());
		
	}

	
	/**
	 * 尋蜜客錢包簽名
	 */
	@Override
	public String XmerWalletSign(Map<String,String> signMap){
		log.info("要签名的信息："+signMap);
		StringBuffer sb = new StringBuffer();
		sb.append(String.valueOf(signMap.get("uid")));
		sb.append(String.format("%.2f",
				Double.valueOf(String.valueOf(signMap.get("profit")))));
		sb.append(String.format("%.2f",
				Double.valueOf(String.valueOf(signMap.get("trunout")))));
		log.info("钱包加密：" + sb.toString());

		return MD5.Encode(sb.toString());
	}
	
	/**
	 * 直播钱包签名
	 */
	@Override
	public String LiveWalletSign(Map<String,String> signMap){
		log.info("要签名的信息："+signMap);
		StringBuffer sb = new StringBuffer();
		sb.append(String.valueOf(signMap.get("uid")));
		sb.append(String.format("%.2f",
				Double.valueOf(String.valueOf(signMap.get("balance")))));
		sb.append(String.format("%.2f",
				Double.valueOf(String.valueOf(signMap.get("commision")))));
		sb.append(String.format("%.2f",
				Double.valueOf(String.valueOf(signMap.get("zbalance")))));
		sb.append(String.format("%.2f",
				Double.valueOf(String.valueOf(signMap.get("sellerCoin")))));
		log.info("钱包加密：" + sb.toString());

		return MD5.Encode(sb.toString());
	}



	@Override
	public String WalletExpansionSign(Map<String, String> signMap) {
		log.info("要签名的信息："+signMap);
		StringBuffer sb = new StringBuffer();
		sb.append(String.valueOf(signMap.get("id")));
		sb.append(String.valueOf(signMap.get("accountid")));
		sb.append(String.valueOf(signMap.get("type")));
		sb.append(String.valueOf(signMap.get("isFreeze")));
		sb.append(String.format("%.2f",
				Double.valueOf(String.valueOf(signMap.get("amount")))));
		sb.append(String.format("%.2f",
				Double.valueOf(String.valueOf(signMap.get("transfer")))));
		log.info("钱包加密：" + sb.toString());

		return MD5.Encode(sb.toString());
	}

}
