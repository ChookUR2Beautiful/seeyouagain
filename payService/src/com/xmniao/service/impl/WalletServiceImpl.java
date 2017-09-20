package com.xmniao.service.impl;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.xmniao.common.MapUtil;
import com.xmniao.common.StateCodeUtil;
import com.xmniao.common.UtilCommon;
import com.xmniao.common.UtilException;
import com.xmniao.common.UtilString;
import com.xmniao.common.XmnConstants;
import com.xmniao.dao.GetMentionLedgerMapper;
import com.xmniao.dao.WalletExpansionMapper;
import com.xmniao.dao.WalletExpansionRecordMapper;
import com.xmniao.dao.WalletLockMapper;
import com.xmniao.dao.WalletMapper;
import com.xmniao.dao.WalletRecordMapper;
import com.xmniao.entity.Wallet;
import com.xmniao.entity.WalletExpansion;
import com.xmniao.entity.WalletExpansionRecord;
import com.xmniao.entity.WalletLock;
import com.xmniao.entity.WalletRecord;
import com.xmniao.exception.ParamBlankException;
import com.xmniao.exception.WalletZbalanceLockException;
import com.xmniao.service.CommonService;
import com.xmniao.service.WalletService;
import com.xmniao.thrift.ledger.FailureException;

/**
 * 钱包相关操作
 * 
 * @author ChenBo
 * 
 */
@Transactional
@Service("WalletSercieImpl")
public class WalletServiceImpl implements WalletService {
	// 初始日志类
	private final Logger log = Logger.getLogger(WalletServiceImpl.class);

	@Autowired
	private WalletMapper walletMapper;

	@Autowired
	private WalletRecordMapper walletRecordMapper;

	@Autowired
	private GetMentionLedgerMapper getMentionLedgerMapper;

	@Autowired
	private CommonService commonService;
	
	// 0
	private final BigDecimal ZERO = new BigDecimal("0.00");
	
	@Autowired
	private WalletExpansionMapper walletExpansionMapper;
	
	@Autowired
	private WalletExpansionRecordMapper walletExpansionRecordMapper;
	
	@Autowired
	private WalletLockMapper  walletLockMapper; 

	/**
	 * 更新钱包金额(按目前需求，当扣钱时，扣到金额<0时，允许继续扣到负数) walletMap可传两组查询条件： 1.通过accountid查询
	 * 2.通过uid/sellerid/jointid查询 两者都传的话，以accountid为准
	 * Map<String,Object> walletMap可传参数：
	 * accountid	Integer		钱包账号
	 * uId 			String 		会员ID/商户ID/合作商ID
	 * userType 	String		用户类型
	 * amount		BigDecimal	钱包金额
	 * balance		BigDecimal	分账金额
	 * commision	BigDecimal 	佣金金额
	 * zbalance		BigDecimal	赠送金额
	 * integral		BigDecimal 	积分金额
	 * seller_amount BigDecimal 营收金额
	 * rtype		Integer		操作类型
	 * remarks		String 		调用方订单号/ID
	 * description	String		描述
	 * overdraft	Integer		是否允许透支钱包	--不传默认为允许
	 * 
	 * @param walletMap
	 *            其中key:accountid是必填项，其他金额为选填项
	 * @throws Exception
	 */
	@Transactional(rollbackFor = { RuntimeException.class } ,isolation= Isolation.SERIALIZABLE, timeout=10)
	@Override
	public Map<String, String> updateWalletAmount(Map<String, Object> walletMap)
			throws RuntimeException {

		if (walletMap == null || walletMap.size() == 0) {
			return returnMap(StateCodeUtil.PR_SYSTEM_ERROR,
					"调用退款钱包金额接口时，输入Map参数不能为null或空值");
		}

		Wallet wallet = null;
		Integer userId = (Integer) walletMap.get("accountid");
		if (userId != null) {
			wallet = walletMapper.getWalletById(userId); // 通过钱包ID查询钱包
		}else if(walletMap.get("uId") != null && !walletMap.get("uId").toString().trim().equals("")){
			wallet = walletMapper.getWalletByuId(walletMap); // 通过用户号/商户号/合作商查询钱包
		}else if(walletMap.get("account") != null && !walletMap.get("account").toString().trim().equals("")){
			log.info("根据account账号查询");
			wallet = walletMapper.getWalletByAccount(walletMap.get("account").toString(),walletMap.get("userType").toString());
		}

		if (wallet == null) {
			// 找不到钱包
			log.error("找不到对应钱包！" + walletMap);
			return returnMap(StateCodeUtil.PR_WALLET_ERROR, "找不到对应钱包！");
		}
		if (wallet.getStatus() != 1) {
			// 钱包状态被锁定或注销！
			log.error("钱包状态被锁定或注销，无法使用！" + walletMap);
			return returnMap(StateCodeUtil.PR_WALLET_ERROR, "钱包状态被锁定或注销，无法使用！");
		}
		log.info("当前钱包信息："
				+ JSON.toJSONStringWithDateFormat(wallet, "yyyy-MM-dd HH:mm:ss"));

		return updateWallet(wallet, walletMap);
	}

	public Map<String, String> updateWallet(Wallet wallet,
			Map<String, Object> walletMap) throws RuntimeException {
		log.info("[updateRefundWallet]要更新的金额:" + walletMap.toString());
		
		// 验证钱包签名
		if(!isVerified(wallet)){
			log.error("当前签名不匹配");
			return returnMap(StateCodeUtil.PR_WALLET_ERROR, "当前签名不匹配");
		}
		String oldSign = wallet.getSign();
		
		//是否可以提前透支钱包余额  True 允许扣成负数  false 不允许扣成负数
		boolean overdraft = walletMap.get("overdraft")==null?true:(Boolean)walletMap.get("overdraft");
		
		// 调试 打印当前钱包金额
		log.info("[updateRefundWallet]当前的钱:" + logInfoWallet(wallet));
		
		// 更新 钱包金额
		if (walletMap.get("amount") != null
				&& ZERO.compareTo((BigDecimal) walletMap.get("amount")) != 0) {
			BigDecimal amount = wallet.getAmount()
					.add((BigDecimal) walletMap.get("amount")).setScale(2);
			wallet.setAmount(amount);
			if(!overdraft && ZERO.compareTo(amount)>0 && ZERO.compareTo((BigDecimal) walletMap.get("amount"))>0){
				log.error("修改钱包失败：余额金额不足");
				return returnMap(StateCodeUtil.PR_WALLET_ERROR, "余额金额不足");
			}
		}


		//更新 赠送金额,赠送支付金额部分，不退！2015年1月26日
		if(walletMap.get("zbalance") != null  && ZERO.compareTo((BigDecimal)walletMap.get("zbalance")) != 0){
			//if((int)walletMap.get("rtype") != 1){
				BigDecimal zbalance = wallet.getZbalance().add((BigDecimal)walletMap.get("zbalance")).setScale(2);
				wallet.setZbalance(zbalance);		
			//}
				if(!overdraft && ZERO.compareTo(zbalance)>0 && ZERO.compareTo((BigDecimal) walletMap.get("zbalance"))>0){
					log.error("修改钱包失败：赠送金额不足");
					return returnMap(StateCodeUtil.PR_WALLET_ERROR, "赠送金额不足");
				}
		}

		// 更新 返利金额
		if (walletMap.get("balance") != null
				&& ZERO.compareTo((BigDecimal) walletMap.get("balance")) != 0) {
			BigDecimal balance = wallet.getBalance()
					.add((BigDecimal) walletMap.get("balance")).setScale(2);
			wallet.setBalance(balance);
			if(!overdraft && ZERO.compareTo(balance)>0 && ZERO.compareTo((BigDecimal) walletMap.get("balance"))>0){
				log.error("修改钱包失败：分账金额不足");
				return returnMap(StateCodeUtil.PR_WALLET_ERROR, "分账金额不足");
			}
		}

		// 更新 佣金金额
		if (walletMap.get("commision") != null
				&& ZERO.compareTo((BigDecimal) walletMap.get("commision")) != 0) {
			BigDecimal commision = wallet.getCommision()
					.add((BigDecimal) walletMap.get("commision")).setScale(2);
			wallet.setCommision(commision);
			if(!overdraft && ZERO.compareTo(commision)>0 && ZERO.compareTo((BigDecimal) walletMap.get("commision"))>0){
				log.error("修改钱包失败：佣金金额不足");
				return returnMap(StateCodeUtil.PR_WALLET_ERROR, "佣金金额不足");
			}
		}

		// 更新 营收金额
		if (walletMap.get("seller_amount") != null
				&& ZERO.compareTo((BigDecimal) walletMap.get("seller_amount")) != 0) {
			BigDecimal sellerAmount = wallet.getSellerAmount()
					.add((BigDecimal) walletMap.get("seller_amount"))
					.setScale(2);
			wallet.setSellerAmount(sellerAmount);
			if(!overdraft && ZERO.compareTo(sellerAmount)>0  && ZERO.compareTo((BigDecimal) walletMap.get("seller_amount"))>0){
				log.error("修改钱包失败：营收金额不足");
				return returnMap(StateCodeUtil.PR_WALLET_ERROR, "营收金额不足");
			}
		}

		// 更新 积分
		if (walletMap.get("integral") != null
				&& ZERO.compareTo((BigDecimal) walletMap.get("integral")) != 0) {
			BigDecimal integral = wallet.getIntegral()
					.add((BigDecimal) walletMap.get("integral")).setScale(2);
			wallet.setIntegral(integral);
			if(!overdraft && ZERO.compareTo(integral)>0  && ZERO.compareTo((BigDecimal) walletMap.get("integral"))>0){
				log.error("修改钱包失败：积分不足");
				return returnMap(StateCodeUtil.PR_WALLET_ERROR, "积分不足");
			}
		}

		// 生成新签名
		Map<String, String> signMap = new HashMap<String, String>();
		signMap.put("uid", String.valueOf(wallet.getUid()));
		signMap.put("sellerid", String.valueOf(wallet.getSellerid()));
		signMap.put("jointid", String.valueOf(wallet.getJointid()));
		signMap.put("pwd", String.valueOf(wallet.getpPwd()));
		signMap.put("amount", String.valueOf(wallet.getAmount()));
		signMap.put("balance", String.valueOf(wallet.getBalance()));
		signMap.put("commision", String.valueOf(wallet.getCommision()));
		signMap.put("zbalance", String.valueOf(wallet.getZbalance()));
		signMap.put("integral", String.valueOf(wallet.getIntegral()));
		signMap.put("sellerAmount", String.valueOf(wallet.getSellerAmount()));
		// signMap.put("sign",SignWallSourse.sign(signMap));
		String newSign = commonService.walletSign(signMap);
		wallet.setSign(newSign);
		wallet.setLastDate(UtilString.dateFormatNow());
		log.info("newsign:"+newSign);
		// 更新钱包数据库
		int result = 0;
		try{
			result = walletMapper.updateWalletMoney(wallet, oldSign);
			
			if (result != 1) {
				log.error("更新钱包金额出错,result：" + result);
				return returnMap(StateCodeUtil.PR_REFUND_FAIL, "更新钱包金额出错");
			}
		}catch(WalletZbalanceLockException e){
			throw new WalletZbalanceLockException(e);
		}
		catch(Exception e){
			log.error("更新钱包金额异常," + UtilException.getExceptionInformation1(e));
			return returnMap(StateCodeUtil.PR_REFUND_FAIL, e.getMessage());
		}

		log.info("[updateRefundWallet]更新成功后的钱:" + logInfoWallet(wallet));
		// log.info("用户"+userId+"钱包更新操作已完成,请查阅个人钱包账户");

		// 更新钱包后，添加更新钱包记录到记录表中
		Long id = addWalletRecord(wallet, walletMap);
		Map<String,String> rMap = returnMap(StateCodeUtil.PR_SUCCESS, "");
		rMap.put("id", id==null?"":id+"");
		return rMap;
	}

	public Map<String, String> returnMap(String code, String msg) {
		return this.returnMap(code, msg, "");
	}

	// 返回的Map数据
	public Map<String, String> returnMap(String code, String msg,
			String response) {
		Map<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("code", code);
		resultMap.put("Msg", msg);
		resultMap.put("response", response);
		return resultMap;
	}

	public Map<String, String> updateWalletIntegral(
			Map<String, Object> walletMap) {
		return returnMap(StateCodeUtil.PR_SUCCESS, "");
	}

	/**
	 * 增加钱包修改记录 1.accountid --钱包ID 2.rtype --类型 3.remarks --订单号
	 * 4.balance--返利金额(可选) 5.commision--佣金金额(可选) 6.income --营收金额(可选) 7.amount
	 * --钱包金额(可选) 8.zbalance --赠送余额(可选) 9.Integral --积分(可选) 10.description--描述
	 */
	// @Override
	public Long addWalletRecord(Wallet wallet, Map<String, Object> walletMap)
			throws RuntimeException {

		log.info("添加钱包修改记录，walletMap的内容为：" + walletMap);
		BigDecimal cur = ZERO;	//修改金额
		BigDecimal before = ZERO;	//修改后金额
		Map<String, Object> walletInfoMap = new HashMap<String, Object>();
		walletInfoMap.put("expenses", walletMap.get("expenses"));
		walletInfoMap.put("accountid", wallet.getAccountid());
		walletInfoMap.put("rtype", walletMap.get("rtype")); // 退款类型
		walletInfoMap.put("remarks", walletMap.get("remarks") == null ? ""
				: walletMap.get("remarks") + "");
		walletInfoMap.put(
				"description",
				walletMap.get("description") == null ? "" : walletMap
						.get("description") + "");
		walletInfoMap.put("sdate", UtilString.dateFormatNow());

		// 收益金额
		cur = walletMap.get("balance") == null ? ZERO : (BigDecimal) walletMap
				.get("balance");
		if (!cur.equals(ZERO)) {
			before = wallet.getBalance();
			walletInfoMap.put("balance", cur.abs());
			walletInfoMap.put("hrebate", before == null ? ZERO : before);
			walletInfoMap.put("qrebate",
					before == null ? cur : before.subtract(cur));
		}

		cur = walletMap.get("commision") == null ? ZERO
				: (BigDecimal) walletMap.get("commision");
		if (!cur.equals(ZERO)) {
			before = wallet.getCommision();
			walletInfoMap.put("commision", cur.abs());
			walletInfoMap.put("hcommision", before == null ? ZERO : before);
			walletInfoMap.put("qcommision",
					before == null ? cur : before.subtract(cur));
		}

		cur = walletMap.get("seller_amount") == null ? ZERO
				: (BigDecimal) walletMap.get("seller_amount");
		if (!cur.equals(ZERO)) {
			before = wallet.getSellerAmount();
			walletInfoMap.put("income", cur.abs());
			walletInfoMap.put("hincome", before == null ? ZERO : before);
			walletInfoMap.put("qincome",
					before == null ? cur : before.subtract(cur));
		}
		/**
		 * 退款流程中，赠送金额会保存到字段中，但不会退款。
		 */
		cur = walletMap.get("zbalance") == null ? ZERO : (BigDecimal) walletMap
				.get("zbalance");
		if (!cur.equals(ZERO)) {
			before = wallet.getZbalance();
			walletInfoMap.put("zbalance", cur.abs());
			walletInfoMap.put("hzbalance", before==null?ZERO:before);
			walletInfoMap.put("qzbalance", before==null?cur:before.subtract(cur));
//			Integer rtype  = Integer.parseInt(String.valueOf(walletMap.get("rtype")));
//			walletInfoMap.put("qzbalance", before==null && rtype != null?ZERO:rtype == 1?before:before.subtract(cur));
		}

		cur = walletMap.get("amount") == null ? ZERO : (BigDecimal) walletMap
				.get("amount");
		if (!cur.equals(ZERO)) {
			before = wallet.getAmount();
			walletInfoMap.put("amount", cur.abs());
			walletInfoMap.put("hamount", before == null ? ZERO : before);
			walletInfoMap.put("qamount",
					before == null ? cur : before.subtract(cur));
		}

		cur = walletMap.get("integral") == null ? ZERO : (BigDecimal) walletMap
				.get("integral");
		if (!cur.equals(ZERO)) {
			before = wallet.getIntegral();
			walletInfoMap.put("Integral", cur.abs());
			walletInfoMap.put("hIntegral", before == null ? ZERO : before);
			walletInfoMap.put("qIntegral", before == null ? cur : before.subtract(cur));
		}
		
		System.out.println(JSON.toJSONString(walletInfoMap));
		if (walletRecordMapper.getRecordCount(walletInfoMap) > 0) {
			log.error("该记录已插入，请勿重复插入");
			throw new RuntimeException("该钱包修改记录已插入，请勿重复插入");
		}

		log.info("此次新增的钱包记录信息为：\r\n" + walletInfoMap);
		walletRecordMapper.addWalletRecord(walletInfoMap);

		return (Long)walletInfoMap.get("id");
	}

	public static void main(String[] args) {
		/*
		 * //2%退款费率 BigDecimal FEE_RATE = new BigDecimal("-0.011");//0.011
		 * BigDecimal fee1 =FEE_RATE.setScale(2,BigDecimal.ROUND_UP); BigDecimal
		 * fee2 = FEE_RATE.setScale(2,BigDecimal.ROUND_DOWN); BigDecimal fee3 =
		 * FEE_RATE.setScale(2,BigDecimal.ROUND_CEILING); BigDecimal fee4 =
		 * FEE_RATE.setScale(2,BigDecimal.ROUND_FLOOR); BigDecimal fee5 =
		 * FEE_RATE.setScale(2,BigDecimal.ROUND_HALF_DOWN); BigDecimal fee6 =
		 * FEE_RATE.setScale(2,BigDecimal.ROUND_HALF_EVEN);
		 * 
		 * 
		 * 
		 * BigDecimal totalAmount = new BigDecimal("0.09"); // BigDecimal fee =
		 * totalAmount.multiply(FEE_RATE);
		 * 
		 * BigDecimal a1 = totalAmount.subtract(fee1); BigDecimal a2 =
		 * totalAmount.subtract(fee2); BigDecimal a3 =
		 * totalAmount.subtract(fee3); BigDecimal a4 =
		 * totalAmount.subtract(fee4); BigDecimal a5 =
		 * totalAmount.subtract(fee5); BigDecimal a6 =
		 * totalAmount.subtract(fee6);
		 * 
		 * 
		 * System.out.println(a1+","+a2+","+a3+","+a4+","+a5+","+a6+","+"");
		 */

		/*
		 * Map<String, Object> map = new HashMap<String, Object>();
		 * map.put("seller_amount", new BigDecimal(4.00)); map.put("uId",
		 * 10086); map.put("userType", 2); new
		 * WalletServiceImpl().subtractWalletAmount(map);
		 */
		BigDecimal cur = new BigDecimal("2.88");
		System.out.println(cur.negate()+","+cur);
		
		String m ="13.99";
		BigDecimal amount = BigDecimal.valueOf(Double.valueOf(m == null ? "0" : m));//钱包余额
		System.out.println(amount+","+Double.valueOf(m == null ? "0" : m));
		
	}

	@Override
	@Transactional(rollbackFor = { FailureException.class,
			RuntimeException.class, Exception.class })
	public Map<String, String> updateBalance(
			List<Map<String, String>> paramMapList) throws FailureException,
			TException {

		int status = 0;
		String msg = "";

		Map<String, String> returnMap = new HashMap<String, String>();

		// 遍历参数
		for (Map<String, String> paramMap : paramMapList) {

			String uId = paramMap.get("uId");

			Map<String, Object> walletMap = new HashMap<String, Object>();
			walletMap.put("uId", uId);
			walletMap.put("userType", paramMap.get("userType"));
			walletMap.put("amount", BigDecimal.valueOf(Double.valueOf(paramMap
					.get("amount") == null ? "0" : paramMap.get("amount"))));
			walletMap.put("balance", BigDecimal.valueOf(Double.valueOf(paramMap
					.get("balance") == null ? "0" : paramMap.get("balance"))));
			walletMap.put("commision", BigDecimal.valueOf(Double
					.valueOf(paramMap.get("commision") == null ? "0" : paramMap
							.get("commision"))));
			walletMap.put("zbalance", BigDecimal.valueOf(Double
					.valueOf(paramMap.get("zbalance") == null ? "0" : paramMap
							.get("zbalance"))));
			walletMap.put("integral", BigDecimal.valueOf(Double
					.valueOf(paramMap.get("integral") == null ? "0" : paramMap
							.get("integral"))));
			walletMap.put("seller_amount", BigDecimal.valueOf(Double
					.valueOf(paramMap.get("sellerAmount") == null ? "0"
							: paramMap.get("sellerAmount"))));
			walletMap.put("rtype", Integer.valueOf(paramMap.get("rType")));
			walletMap.put("remarks", paramMap.get("orderId"));
			if (paramMap.get("remark") != null) {
				if (UtilString.stringIsBlank(paramMap.get("description"))) {
					walletMap.put("description", paramMap.get("remark"));
				}else{
					walletMap.put("description", paramMap.get("description"));
				}
			}

			Map<String, String> resultMap = updateWalletAmount(walletMap);

			if (resultMap.get("code").equals(StateCodeUtil.PR_SUCCESS)) {
				status = 0;
				msg = "修改成功";
			} else if (resultMap.get("code").equals(
					StateCodeUtil.PR_WALLET_ERROR)) {
				status = 1;
				msg = "用户Id为：" + uId + "钱包状态异常";
				log.error(msg);
				throw new FailureException(status, msg);
			} else if (resultMap.get("code").equals(
					StateCodeUtil.PR_SYSTEM_ERROR)) {
				status = 1;
				msg = "用户Id为：" + uId + "引起系统错误";
				log.error(msg);
				throw new FailureException(status, msg);
			} else if (resultMap.get("code").equals(
					StateCodeUtil.PR_REFUND_FAIL)) {
				status = 1;
				msg = "用户Id为：" + uId + "被锁定";
				log.error(msg);
				throw new FailureException(status, msg);
			} else {
				status = 1;
				msg = "用户Id为：" + uId + "引起未知异常";
				log.error(msg);
				throw new FailureException(status, msg);
			}
		}
		returnMap.put("state", status + "");
		returnMap.put("msg", msg);
		return returnMap;
	}

	/**
	 * 钱包余额合并
	 * 
	 * @param map
	 * @return
	 * @throws FailureException
	 */
	@Override
	public Map<String, String> mergeMoney(Map<String, String> map)
			throws FailureException {

		// 返回值Map
		Map<String, String> resultMap = new HashMap<String, String>();
		if (map.isEmpty() || map.get("ids") == null
				|| map.get("moneyType") == null || map.get("fId") == null
				|| map.get("orderId") == null) {
			log.info("参数为空");
			resultMap.put("state", "2");
			resultMap.put("money", "0");
			resultMap.put("msg", "参数为空");
			return resultMap;
		}
		String ids = map.get("ids");
		int moneyType = Integer.parseInt(map.get("moneyType"));
		String fId = map.get("fId");
		String orderId = map.get("orderId");

		Map<String, Object> pMap = new HashMap<String, Object>();

		pMap.put("rtype", 10);
		pMap.put("remarks", orderId);

		int num = walletRecordMapper.getRecordCount(pMap);

		if (num > 0) {
			log.info("重复提交");
			resultMap.put("state", "1");
			resultMap.put("money", "0");
			resultMap.put("msg", "重复提交");
			return resultMap;
		}

		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("uId", fId);
		paramMap.put("typeId", "2");
		paramMap.put("moneyType", String.valueOf(moneyType));
		// 查询父商家钱包
		Map<String, String> fWMap = walletMapper.getWalletByUserId(paramMap);

		if (fWMap == null || fWMap.isEmpty()) {
			// 添加父商家
			int resultState = checkandaddwallet(fId, "2", "", "父商家");
			if (resultState != 0) {
				log.info("添加父商家失败");
				resultMap.put("state", "1");
				resultMap.put("money", "0");
				resultMap.put("msg", "添加父商家失败");
				return resultMap;
			}
			fWMap = walletMapper.getWalletByUserId(paramMap);
		}

		String moneyKey = "";

		switch (moneyType) {
		case 1:
			moneyKey = "commision";
			break;
		case 2:
			moneyKey = "balance";
			break;
		case 3:
			moneyKey = "sellerAmount";
			break;
		case 4:
			moneyKey = "amount";
			break;
		}

		// 父商家钱包余额
		BigDecimal fMoney = new BigDecimal(String.valueOf(fWMap.get(moneyKey)));

		String[] idArray = ids.split(",");

		for (String uId : idArray) {

			Map<String, String> walletMap = new HashMap<String, String>();
			walletMap.put("uId", uId);
			walletMap.put("typeId", "2");
			walletMap.put("moneyType", String.valueOf(moneyType));

			// 查询子商家钱包
			Map<String, String> wMap = walletMapper
					.getWalletByUserId(walletMap);

			if (wMap == null || wMap.isEmpty()) {

				resultMap.put("state", "2");
				resultMap.put("money", "0");
				resultMap.put("msg", "子商家：" + uId + "没有钱包");
				return resultMap;
			}

			// 子商家钱包余额
			BigDecimal cMoney = new BigDecimal(String.valueOf(wMap
					.get(moneyKey)));

			if (cMoney.compareTo(BigDecimal.valueOf(0.00)) == 0) {
				log.info("子商家没有钱");
				continue;
			}

			fMoney = fMoney.add(cMoney);

			walletMap.put("oldSign", wMap.get("sign"));

			// 生成新密钥
			Map<String, String> signMap = new HashMap<String, String>();

			signMap.put("pwd", wMap.get("pwd"));
			signMap.put("amount", String.valueOf(wMap.get("amount")));
			signMap.put(
					"balance",
					moneyKey.equals("balance") ? "0" : String.valueOf(wMap
							.get("balance")));
			signMap.put("commision", moneyKey.equals("commision") ? "0"
					: String.valueOf(wMap.get("commision")));
			signMap.put("sellerAmount", moneyKey.equals("sellerAmount") ? "0"
					: String.valueOf(wMap.get("sellerAmount")));
			signMap.put("zbalance", String.valueOf(wMap.get("zbalance")));
			signMap.put("integral", String.valueOf(wMap.get("Integral")));
			signMap.put("uid", "0");
			signMap.put("sellerid", uId);
			signMap.put("jointid", "0");

			String sign = commonService.walletSign(signMap);

			walletMap.put("sign", sign);
			walletMap.put("money", "0");

			int rowNum = walletMapper.updateMoneyByType(walletMap);

			if (rowNum == 1) {

				Map<String, Object> wrMap = new HashMap<String, Object>();
				wrMap.put("accountid", wMap.get("accountid"));
				wrMap.put("rtype", 10);
				wrMap.put("balance", 0);
				wrMap.put("qrebate", 0);
				wrMap.put("hrebate", 0);
				wrMap.put(
						"commision",
						moneyKey.equals("commision") ? String.valueOf(wMap
								.get("commision")) : "0");
				wrMap.put(
						"qcommision",
						moneyKey.equals("commision") ? String.valueOf(wMap
								.get("commision")) : "0");
				wrMap.put("hcommision", 0);
				wrMap.put(
						"income",
						moneyKey.equals("sellerAmount") ? String.valueOf(wMap
								.get("sellerAmount")) : "0");
				wrMap.put(
						"qincome",
						moneyKey.equals("sellerAmount") ? String.valueOf(wMap
								.get("sellerAmount")) : "0");
				wrMap.put("hincome", 0);
				wrMap.put("zbalance", 0);
				wrMap.put("qzbalance", 0);
				wrMap.put("hzbalance", 0);
				wrMap.put("amount", 0);
				wrMap.put("qamount", 0);
				wrMap.put("hamount", 0);
				wrMap.put("Integral", 0);
				wrMap.put("qIntegral", 0);
				wrMap.put("hIntegral", 0);
				wrMap.put("sdate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.format(new Date()));
				wrMap.put("remarks", orderId);
				wrMap.put("description", "资金归集");

				walletRecordMapper.addWalletRecord(wrMap);
			} else
				throw new FailureException(1,"修改钱包失败");

		}

		if (fMoney
				.compareTo(new BigDecimal(String.valueOf(fWMap.get(moneyKey)))) == 0) {
			log.info("子商家没有钱");
			resultMap.put("state", "0");
			resultMap.put("money", "0");
			resultMap.put("msg", "子商家沒有钱");
			return resultMap;
		} else {

			Map<String, Object> param = new HashMap<String, Object>();

			param.put("moneyType", moneyType);
			param.put("userType", "2");
			param.put("ids", idArray);

			paramMap.put("oldSign", fWMap.get("sign"));

			// 生成新密钥
			Map<String, String> signMap = new HashMap<String, String>();

			signMap.put("pwd", fWMap.get("pwd"));
			signMap.put("amount", String.valueOf(fWMap.get("amount")));
			signMap.put("balance",
					moneyKey.equals("balance") ? String.valueOf(fMoney)
							: String.valueOf(fWMap.get("balance")));
			signMap.put("commision",
					moneyKey.equals("commision") ? String.valueOf(fMoney)
							: String.valueOf(fWMap.get("commision")));
			signMap.put("sellerAmount",
					moneyKey.equals("sellerAmount") ? String.valueOf(fMoney)
							: String.valueOf(fWMap.get("sellerAmount")));
			signMap.put("zbalance", String.valueOf(fWMap.get("zbalance")));
			signMap.put("integral", String.valueOf(fWMap.get("Integral")));
			signMap.put("uid", "0");
			signMap.put("sellerid", fId);
			signMap.put("jointid", "0");

			String sign = commonService.walletSign(signMap);

			paramMap.put("sign", sign);
			paramMap.put("money", String.valueOf(fMoney));

			int rowNum = walletMapper.updateMoneyByType(paramMap);

			if (rowNum == 1) {

				Map<String, Object> wrMap = new HashMap<String, Object>();
				wrMap.put("accountid", fWMap.get("accountid"));
				wrMap.put("rtype", 10);
				wrMap.put("balance", 0);
				wrMap.put("qrebate", 0);
				wrMap.put("hrebate", 0);
				wrMap.put(
						"commision",
						moneyKey.equals("commision") ? fMoney.subtract(
								new BigDecimal(String.valueOf(fWMap
										.get("commision")))).setScale(2,
								BigDecimal.ROUND_HALF_UP) : "0");
				wrMap.put(
						"qcommision",
						moneyKey.equals("commision") ? String.valueOf(fWMap
								.get("commision")) : "0");
				wrMap.put(
						"hcommision",
						moneyKey.equals("commision") ? fMoney.setScale(2,
								BigDecimal.ROUND_HALF_UP) : "0");
				wrMap.put(
						"income",
						moneyKey.equals("sellerAmount") ? fMoney.subtract(
								new BigDecimal(String.valueOf(fWMap
										.get("sellerAmount")))).setScale(2,
								BigDecimal.ROUND_HALF_UP) : "0");
				wrMap.put(
						"qincome",
						moneyKey.equals("sellerAmount") ? String.valueOf(fWMap
								.get("sellerAmount")) : "0");
				wrMap.put(
						"hincome",
						moneyKey.equals("sellerAmount") ? fMoney.setScale(2,
								BigDecimal.ROUND_HALF_UP) : "0");
				wrMap.put("zbalance", 0);
				wrMap.put("qzbalance", 0);
				wrMap.put("hzbalance", 0);
				wrMap.put("amount", 0);
				wrMap.put("qamount", 0);
				wrMap.put("hamount", 0);
				wrMap.put("Integral", 0);
				wrMap.put("qIntegral", 0);
				wrMap.put("hIntegral", 0);
				wrMap.put("sdate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.format(new Date()));
				wrMap.put("remarks", orderId);
				wrMap.put("description", "资金归集");

				walletRecordMapper.addWalletRecord(wrMap);
			} else
				throw new FailureException(1,"修改钱包失败");

			log.info("归集成功");
			resultMap.put("state", "0");
			resultMap.put("money", String.valueOf(fMoney.subtract(
					new BigDecimal(String.valueOf(fWMap.get(moneyKey))))
					.setScale(2, BigDecimal.ROUND_HALF_UP)));
			resultMap.put("msg", "归集成功");
			return resultMap;
		}
	}

	/**
	 * 新增钱包方法
	 * 
	 * @param uId
	 * @param userType
	 * @param password
	 * @param sellerName
	 * @param phoneNumber
	 * @return
	 */
	public Map<String, String> checkandaddwallet(String uId, String userType,
			String password, String sellerName, String phoneNumber) {
		Map<String, String> returnMap = new HashMap<String, String>();

		if (StringUtils.isBlank(uId) || StringUtils.isBlank(userType)) {
			log.error("输入参数不能为空");
			returnMap.put("state", "2");
			returnMap.put("msg", "输入参数不能为空");
			return returnMap;
		}
		if (!userType.matches("^[1-3]$")) {
			log.error("输入类型错误");
			returnMap.put("state", "2");
			returnMap.put("msg", "输入类型错误");
			return returnMap;
		}
		if (password != null && password.length() != 32
				&& !password.trim().equals("")) {
			log.error("密码不为空时密码长度有误！");
			returnMap.put("state", "2");
			returnMap.put("msg", "密码不为空时密码长度有误！");
			return returnMap;
		}

		try {
			// 1.查询有无现有钱包账号
			Map<String, String> map = new HashMap<String, String>();
			map.put("uId", uId);
			map.put("typeId", userType);
			Map<String, String> resultMap = walletMapper.getWalletByUserId(map);
			Map<String, String> signMap = new HashMap<String, String>();
			signMap.put("uid", userType.equals("1") ? uId : "0");
			signMap.put("sellerid", userType.equals("2") ? uId : "0");
			signMap.put("jointid", userType.equals("3") ? uId : "0");
			signMap.put("pwd", password == null ? "" : password);
			signMap.put("amount", "0.00");
			signMap.put("balance", "0.00");
			signMap.put("commision", "0.00");
			signMap.put("zbalance", "0.00");
			signMap.put("integral", "0.00");
			signMap.put("sellerAmount", "0.00");

			String signStr = commonService.walletSign(signMap);
			if (resultMap == null || resultMap.size() == 0) {
				// 2.新增钱包账号
				map.clear();
				if ("1".equals(userType)) {
					map.put("uid", uId);
					map.put("sellerid", "0");
					map.put("jointid", "0");
					map.put("sign", signStr);
					map.put("sign_type", "1");
					map.put("status", "1");
				} else if ("2".equals(userType)) {
					map.put("uid", "0");
					map.put("sellerid", uId);
					map.put("jointid", "0");
					map.put("sign", signStr);
					map.put("sign_type", "1");
					map.put("status", "1");
				} else if ("3".equals(userType)) {
					map.put("uid", "0");
					map.put("jointid", uId);
					map.put("sellerid", "0");
					map.put("sign", signStr);
					map.put("sign_type", "1");
					map.put("status", "1");
				}
				map.put("p_pwd", password);
				map.put("sellername", sellerName);
				map.put("account", phoneNumber);
				map.put("apply_date", new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss").format(new Date()));
				map.put("last_date",
						new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
								.format(new Date()));
				map.put("effect_date", new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss").format(new Date()));

				int result = walletMapper.addWalletByuid(map);
				if (result == 1 && userType.equals("2")) {
					Map<String, Object> paramMap = new HashMap<String, Object>();
					Map<String, Object> map1 = new HashMap<String, Object>();
					paramMap = getMentionLedgerMapper.selectByAccountid(uId); // 通过uId获取用户钱包信息
					map1.put("uId", uId);
					map1.put("accountid", paramMap.get("accountid"));
					map1.put("mention", 0);
					map1.put("ledger", 1);
					map1.put("income", 0.00);
					map1.put("money", 0.00);
					map1.put("amount", 0.00);
					int result1 = getMentionLedgerMapper
							.insertByAccountid(map1);
					if (result1 == 1) {
						log.info("成功插入商户提现配置表");
					} else {
						log.error("插入商户提现配置表失败");
					}
					log.info("Success!");
					returnMap.put("state", "0");
					returnMap.put("msg", "添加钱包成功！");
					return returnMap;
				} else if (result == 1
						&& (userType.equals("1") || userType.equals("3"))) {
					returnMap.put("state", "0");
					returnMap.put("msg", "添加钱包成功！");
					return returnMap;
				} else {
					log.error("添加钱包失败");
					returnMap.put("state", "1");
					returnMap.put("msg", "添加钱包失败！");
					return returnMap;
				}
			} else {
				log.info("该用户已有钱包，请勿重复添加");
				returnMap.put("state", "3");
				returnMap.put("msg", "该用户已有钱包，请勿重复添加！");
				return returnMap;
			}

		} catch (Exception e) {
			log.error("添加钱包失败", e);
			returnMap.put("state", "3");
			returnMap.put("msg", "添加钱包异常！");
			return returnMap;
		}
	}

	/**
	 * 新增钱包方法
	 * 
	 * @param uId
	 * @param userType
	 * @param password
	 *            可为空
	 * @param sellerName
	 *            可为空
	 * @return 0:新增成功正常 1：操作异常 2：传入参数错误 3：已有记录，请勿重复添加
	 */
	public int checkandaddwallet(String uId, String userType, String password,
			String sellerName) {
		Map<String, String> resultMap = checkandaddwallet(uId, userType,
				password, sellerName, null);
		return Integer.parseInt(resultMap.get("state"));
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

	/**
	 * 更新钱包金额(按目前需求，当扣钱时，扣到金额<0时，则修改失败并返回) walletMap可传两组查询条件： 1.通过accountid查询
	 * 2.通过uId/userTyped查询
	 * 3.通过account查询
	 * 优先级顺序为accountid->uId/userType->account
	 * @param walletMap
	 *            其中key:accountid是必填项，其他金额为选填项
	 * @throws Exception
	 */
	@Transactional(rollbackFor = { RuntimeException.class } ,isolation= Isolation.SERIALIZABLE, timeout=10)
	public Map<String, String> updateWalletAmount2(Map<String, Object> walletMap)
			throws RuntimeException {

		if (walletMap == null || walletMap.size() == 0) {
			return returnMap(StateCodeUtil.PR_SYSTEM_ERROR,
					"调用退款钱包金额接口时，输入Map参数不能为null或空值");
		}
		
		Wallet wallet = getWallet(walletMap);
		
		if (wallet == null) {
			// 找不到钱包
			log.error("找不到对应钱包！" + walletMap);
			return returnMap(StateCodeUtil.PR_WALLET_ERROR, "找不到对应钱包！");
		}
		
		if (wallet.getStatus() != 1) {
			// 钱包状态被锁定或注销！
			log.error("钱包状态被锁定或注销，无法使用！" + walletMap);
			return returnMap(StateCodeUtil.PR_WALLET_ERROR, "钱包状态被锁定或注销，无法使用！");
		}
		log.info("当前钱包信息："
				+ JSON.toJSONStringWithDateFormat(wallet, "yyyy-MM-dd HH:mm:ss"));

		return updateWallet2(wallet, walletMap);
	}

	public Map<String, String> updateWallet2(Wallet wallet,
			Map<String, Object> walletMap) throws RuntimeException {
		log.info("[updateRefundWallet]要更新的金额:" + walletMap.toString());
		
		// 验证钱包签名		
		if(!isVerified(wallet)){
			log.error("当前签名不匹配");
			return returnMap(StateCodeUtil.PR_WALLET_ERROR, "当前签名不匹配");
		}
		String oldSign = wallet.getSign();
		
		// 调试 打印当前钱包金额
		log.info("[updateRefundWallet]当前的钱:" + logInfoWallet(wallet));

		// 更新 钱包金额
		if (walletMap.get("amount") != null
				&& ZERO.compareTo((BigDecimal) walletMap.get("amount")) != 0) {
			BigDecimal amount = wallet.getAmount()
					.add((BigDecimal) walletMap.get("amount")).setScale(2);
			wallet.setAmount(amount);
			if(ZERO.compareTo(amount)>0 && ZERO.compareTo((BigDecimal) walletMap.get("amount"))>0){
				log.error("修改钱包失败：余额金额不足");
				return returnMap(StateCodeUtil.PR_WALLET_ERROR, "余额金额不足");
			}
		}


		if(walletMap.get("zbalance") != null  
				&& ZERO.compareTo((BigDecimal)walletMap.get("zbalance")) != 0){
				BigDecimal zbalance = wallet.getZbalance().add((BigDecimal)walletMap.get("zbalance")).setScale(2);
				wallet.setZbalance(zbalance);
				if(ZERO.compareTo(zbalance)>0 && ZERO.compareTo((BigDecimal) walletMap.get("zbalance"))>0){
					log.error("修改钱包失败：赠送金额不足");
					return returnMap(StateCodeUtil.PR_WALLET_ERROR, "赠送金额不足");
				}
		}

		// 更新 返利金额
		if (walletMap.get("balance") != null
				&& ZERO.compareTo((BigDecimal) walletMap.get("balance")) != 0) {
			BigDecimal balance = wallet.getBalance()
					.add((BigDecimal) walletMap.get("balance")).setScale(2);
			wallet.setBalance(balance);
			if(ZERO.compareTo(balance)>0 && ZERO.compareTo((BigDecimal) walletMap.get("balance"))>0){
				log.error("修改钱包失败：分账金额不足");
				return returnMap(StateCodeUtil.PR_WALLET_ERROR, "分账金额不足");
			}
		}

		// 更新 佣金金额
		if (walletMap.get("commision") != null
				&& ZERO.compareTo((BigDecimal) walletMap.get("commision")) != 0) {
			BigDecimal commision = wallet.getCommision()
					.add((BigDecimal) walletMap.get("commision")).setScale(2);
			wallet.setCommision(commision);
			if(ZERO.compareTo(commision)>0 && ZERO.compareTo((BigDecimal) walletMap.get("commision"))>0){
				log.error("修改钱包失败：佣金金额不足");
				return returnMap(StateCodeUtil.PR_WALLET_ERROR, "佣金金额不足");
			}
		}

		// 更新 营收金额
		if (walletMap.get("seller_amount") != null
				&& ZERO.compareTo((BigDecimal) walletMap.get("seller_amount")) != 0) {
			BigDecimal sellerAmount = wallet.getSellerAmount()
					.add((BigDecimal) walletMap.get("seller_amount"))
					.setScale(2);
			wallet.setSellerAmount(sellerAmount);
			if(ZERO.compareTo(sellerAmount)>0  && ZERO.compareTo((BigDecimal) walletMap.get("seller_amount"))>0){
				log.error("修改钱包失败：营收金额不足");
				return returnMap(StateCodeUtil.PR_WALLET_ERROR, "营收金额不足");
			}
		}

		// 更新 积分
		if (walletMap.get("integral") != null
				&& ZERO.compareTo((BigDecimal)walletMap.get("integral")) != 0) {
			BigDecimal integral = wallet.getIntegral()
					.add((BigDecimal) walletMap.get("integral"))
					.setScale(2);
			wallet.setIntegral(integral);
			if(ZERO.compareTo(integral)>0  && ZERO.compareTo((BigDecimal) walletMap.get("integral"))>0){
				log.error("修改钱包失败：积分不足");
				return returnMap(StateCodeUtil.PR_WALLET_ERROR, "积分不足");
			}
		}

		// 生成新签名
		Map<String, String> signMap = new HashMap<String, String>();
		signMap.put("uid", String.valueOf(wallet.getUid()));
		signMap.put("sellerid", String.valueOf(wallet.getSellerid()));
		signMap.put("jointid", String.valueOf(wallet.getJointid()));
		signMap.put("pwd", String.valueOf(wallet.getpPwd()));
		signMap.put("amount", String.valueOf(wallet.getAmount()));
		signMap.put("balance", String.valueOf(wallet.getBalance()));
		signMap.put("commision", String.valueOf(wallet.getCommision()));
		signMap.put("zbalance", String.valueOf(wallet.getZbalance()));
		signMap.put("integral", String.valueOf(wallet.getIntegral()));
		signMap.put("sellerAmount", String.valueOf(wallet.getSellerAmount()));
		// signMap.put("sign",SignWallSourse.sign(signMap));
		String newSign = commonService.walletSign(signMap);
		wallet.setSign(newSign);
		wallet.setLastDate(UtilString.dateFormatNow());
		log.info("newsign:"+newSign);
		// 更新钱包数据库
		int result = 0;
		String recordId = "";//钱包记录id
		try{
			result = walletMapper.updateWalletMoney(wallet, oldSign);
			
			if (result != 1) {
				log.error("更新钱包金额出错,result：" + result);
				return returnMap(StateCodeUtil.PR_REFUND_FAIL, "更新钱包金额出错");
			}
		}catch(Exception e){
			log.error("更新钱包金额异常," + UtilException.getExceptionInformation1(e));
			return returnMap(StateCodeUtil.PR_REFUND_FAIL, e.getMessage());
		}
		
		log.info("[updateRefundWallet]更新成功后的钱:" + logInfoWallet(wallet));
		// log.info("用户"+userId+"钱包更新操作已完成,请查阅个人钱包账户");

		if(walletMap.get("rtype").equals("-1")){
			log.info("不需要将此次修改记录写进记录表中");
		}else{
			// 更新钱包后，添加更新钱包记录到记录表中
			Boolean recoverHandsel = (Boolean) walletMap.get("recoverHandsel");
			if(recoverHandsel != null && recoverHandsel){
				recordId = addURWalletRecord(wallet, walletMap);
			}else{
				recordId = addTDWalletRecord(wallet, walletMap);
			}
		}
		
		Map<String,String> resultMap = new HashMap<>();
		resultMap.put("code", StateCodeUtil.PR_SUCCESS);
		resultMap.put("Msg","");
		resultMap.put("response","");
		resultMap.put("id",recordId);
		return resultMap;
	}
	
	private Wallet getWallet(Map<String,Object> walletMap){
		Wallet wallet = null;
		Integer userId = (Integer) walletMap.get("accountid");
		if (userId != null) {
			wallet = walletMapper.getWalletById(userId); // 通过钱包ID查询钱包
		}else if(walletMap.get("uId") != null && !walletMap.get("uId").toString().trim().equals("") && !walletMap.get("uId").toString().equals("0")){
			wallet = walletMapper.getWalletByuId(walletMap); // 通过用户号/商户号/合作商查询钱包
		}else if(walletMap.get("account") != null && !walletMap.get("account").toString().trim().equals("")){
			log.info("根据account账号查询");
			wallet = walletMapper.getWalletByAccount(walletMap.get("account").toString(),walletMap.get("userType").toString());
		}
		return wallet;
	}
	
	/**
	*返回插入记录id
	 */
	public String addTDWalletRecord(Wallet wallet, Map<String, Object> walletMap)
			throws RuntimeException {

		log.info("添加钱包修改记录，walletMap的内容为：" + walletMap);
		BigDecimal cur = ZERO;	//修改金额
		BigDecimal before = ZERO;	//修改后金额
		Map<String, Object> walletInfoMap = new HashMap<String, Object>();
		walletInfoMap.put("accountid", wallet.getAccountid());
		walletInfoMap.put("rtype", walletMap.get("rtype")); 
		walletInfoMap.put("remarks", walletMap.get("remarks") == null ? ""
				: walletMap.get("remarks") + "");
		walletInfoMap.put(
				"description",
				walletMap.get("description") == null ? "" : walletMap
						.get("description") + "");
		walletInfoMap.put("sdate", UtilString.dateFormatNow());

		//调单接口，保存钱包修改记录时，修改金额字段的+-符号按调单接口传入对应字段同步
		boolean isSubtract=String.valueOf(walletMap.get("rtype")).equals("19") || String.valueOf(walletMap.get("rtype")).equals("21");
		
		// 收益金额
		cur = walletMap.get("balance") == null ? ZERO : (BigDecimal) walletMap
				.get("balance");
		if (!cur.equals(ZERO)) {
			before = wallet.getBalance();
			walletInfoMap.put("balance", isSubtract?cur.negate():cur);
			walletInfoMap.put("hrebate", before == null ? ZERO : before);
			walletInfoMap.put("qrebate",
					before == null ? cur : before.subtract(cur));
		}

		cur = walletMap.get("commision") == null ? ZERO
				: (BigDecimal) walletMap.get("commision");
		if (!cur.equals(ZERO)) {
			before = wallet.getCommision();
			walletInfoMap.put("commision", isSubtract?cur.negate():cur);
			walletInfoMap.put("hcommision", before == null ? ZERO : before);
			walletInfoMap.put("qcommision",
					before == null ? cur : before.subtract(cur));
		}

		cur = walletMap.get("seller_amount") == null ? ZERO
				: (BigDecimal) walletMap.get("seller_amount");
		if (!cur.equals(ZERO)) {
			before = wallet.getSellerAmount();
			walletInfoMap.put("income", isSubtract?cur.negate():cur);
			walletInfoMap.put("hincome", before == null ? ZERO : before);
			walletInfoMap.put("qincome",
					before == null ? cur : before.subtract(cur));
		}
		/**
		 * 退款流程中，赠送金额会保存到字段中，但不会退款。
		 */
		cur = walletMap.get("zbalance") == null ? ZERO : (BigDecimal) walletMap
				.get("zbalance");
		if (!cur.equals(ZERO)) {
			before = wallet.getZbalance();
			walletInfoMap.put("zbalance", isSubtract?cur.negate():cur);
			walletInfoMap.put("hzbalance", before==null?ZERO:before);
			walletInfoMap.put("qzbalance", before==null?cur:before.subtract(cur));
//			Integer rtype  = Integer.parseInt(String.valueOf(walletMap.get("rtype")));
//			walletInfoMap.put("qzbalance", before==null && rtype != null?ZERO:rtype == 1?before:before.subtract(cur));
		}

		cur = walletMap.get("amount") == null ? ZERO : (BigDecimal) walletMap
				.get("amount");
		if (!cur.equals(ZERO)) {
			before = wallet.getAmount();
			walletInfoMap.put("amount", isSubtract?cur.negate():cur);
			walletInfoMap.put("hamount", before == null ? ZERO : before);
			walletInfoMap.put("qamount",
					before == null ? cur : before.subtract(cur));
		}

		cur = walletMap.get("integral") == null ? ZERO : (BigDecimal) walletMap
				.get("integral");
		if (!cur.equals(ZERO)) {
			before = wallet.getIntegral();
			walletInfoMap.put("Integral", isSubtract?cur.negate():cur);
			walletInfoMap.put("hIntegral", before == null ? ZERO : before);
			walletInfoMap.put("qIntegral", before == null ? cur : before.subtract(cur));
		}
		System.out.println(JSON.toJSONString(walletInfoMap));
//		if (walletRecordMapper.getRecordCountMap(walletInfoMap) > 0) {
//			log.error("该记录已插入，请勿重复插入");
//			throw new RuntimeException("该钱包修改记录已插入，请勿重复插入");
//		}

		log.info("此次新增的钱包记录信息为：\r\n" + walletInfoMap);
		walletRecordMapper.addWalletRecord(walletInfoMap);

		return walletInfoMap.get("id")+"";
	}
	
	/*
	 * 添加钱包修改记录(与addTDWalletRecord的差别在于更新字段存储的正负方式)
	 */
	public String addURWalletRecord(Wallet wallet, Map<String, Object> walletMap)
			throws RuntimeException {

		log.info("添加钱包修改记录，walletMap的内容为：" + walletMap);
		BigDecimal cur = ZERO;	//修改金额
		BigDecimal after = ZERO;	//修改后金额
		Map<String, Object> walletInfoMap = new HashMap<String, Object>();
		walletInfoMap.put("accountid", wallet.getAccountid());
		walletInfoMap.put("rtype", walletMap.get("rtype")); 
		walletInfoMap.put("remarks", walletMap.get("remarks") == null ? ""
				: walletMap.get("remarks") + "");
		walletInfoMap.put(
				"description",
				walletMap.get("description") == null ? "" : walletMap
						.get("description") + "");
		walletInfoMap.put("sdate", UtilString.dateFormatNow());

		//调单接口，保存钱包修改记录时，修改金额字段的+-符号按调单接口传入对应字段同步
		boolean isSubtract=String.valueOf(walletMap.get("rtype")).equals("19") || String.valueOf(walletMap.get("rtype")).equals("21");
		
		// 收益金额
		cur = walletMap.get("balance") == null ? ZERO : ((BigDecimal) walletMap
				.get("balance")).abs();
		if (!cur.equals(ZERO)) {
			after = wallet.getBalance();
			walletInfoMap.put("balance", isSubtract?cur.negate():cur);
			walletInfoMap.put("hrebate", after == null ? ZERO : after);
			walletInfoMap.put("qrebate",
					after == null ? cur : after.add(cur));
		}

		cur = walletMap.get("commision") == null ? ZERO
				: ((BigDecimal) walletMap.get("commision")).abs();
		if (!cur.equals(ZERO)) {
			after = wallet.getCommision();
			walletInfoMap.put("commision", isSubtract?cur.negate():cur);
			walletInfoMap.put("hcommision", after == null ? ZERO : after);
			walletInfoMap.put("qcommision",
					after == null ? cur : after.add(cur));
		}

		cur = walletMap.get("seller_amount") == null ? ZERO
				: ((BigDecimal) walletMap.get("seller_amount")).abs();
		if (!cur.equals(ZERO)) {
			after = wallet.getSellerAmount();
			walletInfoMap.put("income", isSubtract?cur.negate():cur);
			walletInfoMap.put("hincome", after == null ? ZERO : after);
			walletInfoMap.put("qincome",
					after == null ? cur : after.add(cur));
		}
		/**
		 * 退款流程中，赠送金额会保存到字段中，但不会退款。
		 */
		cur = walletMap.get("zbalance") == null ? ZERO : ((BigDecimal) walletMap
				.get("zbalance")).abs();
		if (!cur.equals(ZERO)) {
			after = wallet.getZbalance();
			walletInfoMap.put("zbalance", isSubtract?cur.negate():cur);
			walletInfoMap.put("hzbalance", after==null?ZERO:after);
			walletInfoMap.put("qzbalance", after==null?cur:after.add(cur));
//			Integer rtype  = Integer.parseInt(String.valueOf(walletMap.get("rtype")));
//			walletInfoMap.put("qzbalance", before==null && rtype != null?ZERO:rtype == 1?before:before.subtract(cur));
		}

		cur = walletMap.get("amount") == null ? ZERO : ((BigDecimal) walletMap
				.get("amount")).abs();
		if (!cur.equals(ZERO)) {
			after = wallet.getAmount();
			walletInfoMap.put("amount", isSubtract?cur.negate():cur);
			walletInfoMap.put("hamount", after == null ? ZERO : after);
			walletInfoMap.put("qamount",
					after == null ? cur : after.add(cur));
		}

		cur = walletMap.get("integral") == null ? ZERO : ((BigDecimal) walletMap
				.get("integral")).abs();
		if (!cur.equals(ZERO)) {
			after = wallet.getIntegral();
			walletInfoMap.put("Integral", isSubtract?cur.negate():cur);
			walletInfoMap.put("hIntegral", after == null ? ZERO : after);
			walletInfoMap.put("qIntegral", after == null ? cur : after.add(cur));
		}
		System.out.println(JSON.toJSONString(walletInfoMap));
//		if (walletRecordMapper.getRecordCountMap(walletInfoMap) > 0) {
//			log.error("该记录已插入，请勿重复插入");
//			throw new RuntimeException("该钱包修改记录已插入，请勿重复插入");
//		}

		log.info("此次新增的钱包记录信息为：\r\n" + walletInfoMap);
		walletRecordMapper.addWalletRecord(walletInfoMap);

		return walletInfoMap.get("id")+"";
	}
	
	/**
	 * 平台扣款
	 * @param updateMap
	 * @param sequenceArray
	 * @return
	 */
	@Override
	@Transactional(rollbackFor = { RuntimeException.class } ,isolation= Isolation.SERIALIZABLE, timeout=10)
	public Map<String,String> walletDeductions(Map<String,Object> walletMap,String[] sequenceArray)throws FailureException{

		if (walletMap == null || walletMap.size() == 0) {
			return returnMap(StateCodeUtil.PR_SYSTEM_ERROR,
					"调用平台接口时，输入Map参数不能为null或空值");
		}
		
		Wallet wallet = getWallet(walletMap);
		
		if (wallet == null) {
			log.error("找不到对应钱包！" + walletMap);
			return returnMap(StateCodeUtil.PR_WALLET_ERROR, "找不到对应钱包！");
		}
		
		if (wallet.getStatus() != 1) {
			log.error("钱包状态被锁定或注销，无法使用！" + walletMap);
			return returnMap(StateCodeUtil.PR_WALLET_ERROR, "钱包状态被锁定或注销，无法使用！");
		}
		log.info("当前钱包信息："
				+ JSON.toJSONStringWithDateFormat(wallet, "yyyy-MM-dd HH:mm:ss"));

		//根据钱包当前金额，分解总扣款至各字段
		BigDecimal allAmount = new BigDecimal(walletMap.get("amount").toString());
		Map<String,Object> updateMap = dissociationAmount(wallet,sequenceArray,allAmount);
		if(updateMap==null || updateMap.size()==0){
			return returnMap(StateCodeUtil.PR_WALLET_ERROR, "钱包金额不足");
		}
		
		String descriptionDefault="平台扣款";
		String description =(String)walletMap.get("description");
		if(description!= null && UtilString.stringIsNotBlank(description)){
			descriptionDefault = description;
		}
		updateMap.put("description", descriptionDefault);	//描述
		updateMap.put("remarks", walletMap.get("remarks"));
		updateMap.put("rtype", 16);
		return updateWallet(wallet, updateMap);
	}
	

	private boolean isVerified(Wallet wallet){
		String oldSign = wallet.getSign();
		
		Map<String, String> sMap = new HashMap<String, String>();
		sMap.put("uid", String.valueOf(wallet.getUid()));
		sMap.put("sellerid", String.valueOf(wallet.getSellerid()));
		sMap.put("jointid", String.valueOf(wallet.getJointid()));
		sMap.put("pwd", String.valueOf(wallet.getpPwd()));
		sMap.put("amount", String.valueOf(wallet.getAmount()));
		sMap.put("balance", String.valueOf(wallet.getBalance()));
		sMap.put("commision", String.valueOf(wallet.getCommision()));
		sMap.put("zbalance", String.valueOf(wallet.getZbalance()));
		sMap.put("integral", String.valueOf(wallet.getIntegral()));
		sMap.put("sellerAmount", String.valueOf(wallet.getSellerAmount()));
		// signMap.put("sign",SignWallSourse.sign(signMap));
		String nSign = commonService.walletSign(sMap);
		
		if(!oldSign.equals(nSign)){
			log.error("当前签名不匹配");
			log.info("oldSign:"+oldSign);
			log.info("sign:"+nSign);
			return false;
		}else{
			return true;
		}
	}
	
	private String logInfoWallet(Wallet wallet){
		Map<String, Object> curMap = new HashMap<String, Object>();
		curMap.put("accountid", wallet.getAccountid());
		curMap.put("amount", wallet.getAmount());
		curMap.put("balance", wallet.getBalance());
		curMap.put("commision", wallet.getCommision());
		curMap.put("sellerAmount", wallet.getSellerAmount());
		curMap.put("zbalance", wallet.getZbalance());
		curMap.put("integral", wallet.getIntegral());
		
		return curMap.toString();
	}
	
	/**
	 * 将总扣款金额按顺序分摊到钱包的各字段
	 * @param wallet
	 * @param sequenceArray
	 * @param allAmount
	 * @return
	 */
	@SuppressWarnings("all")
	private Map<String,Object> dissociationAmount(Wallet wallet,String[] sequenceArray ,BigDecimal allAmount){
		Map<String,Object> updateMap=new HashMap<String,Object>();
		BigDecimal uAmount=allAmount;
		try{
			
			Class walletClazz = wallet.getClass();
			for(String field:sequenceArray){
				Method method = walletClazz.getDeclaredMethod("get"+UtilString.captureName(field));
				BigDecimal amount = (BigDecimal)method.invoke(wallet);
				if(uAmount.compareTo(amount)>0){
					updateMap.put(UtilString.stringTurnUnderline(field), amount.negate());
					uAmount=uAmount.subtract(amount);
				}else{
					updateMap.put(UtilString.stringTurnUnderline(field), uAmount.negate());
					uAmount=uAmount.subtract(amount);
					break;
				}
			}
		}catch(Exception e){
			log.error("程序异常"+e);
			return null;
		}
		if(uAmount.compareTo(UtilCommon.ZERO)>0){
			log.error("钱不够扣了");
			return null;
		}
		log.info("要更新字段的金额："+updateMap);
		return updateMap;
	}

	@Override
	public Map<String, String> setLockWallet(Map<String, Object> walletMap) {
		
		Wallet wallet = getWallet(walletMap);
		
		if (wallet == null) {
			// 找不到钱包
			log.error("找不到对应钱包！" + walletMap);
			return returnMap("1", "找不到对应钱包！");
		}
		// 验证钱包签名		
		if(!isVerified(wallet)){
			log.error("当前签名不匹配");
			return returnMap("1", "当前签名不匹配");
		}
		//钱包签名不包含钱包状态，故不需要重新签名。
		int status = (int) walletMap.get("status");
		if(status ==1 || status ==2){
			
		}else{
			return returnMap("1", "设置钱包状态错误");
		}
		wallet.setStatus(status);
		int result = walletMapper.updateWallet(wallet);
		if(result == 1){
			return returnMap("0", status==1?"钱包解锁成功!":"钱包锁定成功!");
		}else{
			log.error("修改失败，result:"+result);
			return returnMap("1", "修改钱包状态失败");
		}
	}
	
	@Override
	@Transactional(rollbackFor = { FailureException.class,
			RuntimeException.class, Exception.class })
	public Map<String, String> updateBalance2(Map<String, String> paramMap) throws FailureException,
			TException {

		int status = 0;
		String msg = "";

		Map<String, String> returnMap = new HashMap<String, String>();
			String option = paramMap.get("option");
			if(StringUtils.isBlank(option)){
				log.error("参数option不能为空  :paramMap:"+paramMap);
				returnMap.put("state","2");
				returnMap.put("msg","参数option不能为空");
				return returnMap;
			}
		
			String uId = paramMap.get("uId");

			Map<String, Object> walletMap = new HashMap<String, Object>();
			walletMap.put("uId", uId);
			walletMap.put("userType", paramMap.get("userType"));
			
			BigDecimal amount = BigDecimal.valueOf(Double.valueOf(paramMap
					.get("amount") == null ? "0" : paramMap.get("amount")));//钱包余额
			BigDecimal balance = BigDecimal.valueOf(Double.valueOf(paramMap
					.get("balance") == null ? "0" : paramMap.get("balance")));//分账余额
			BigDecimal commision = BigDecimal.valueOf(Double.valueOf(paramMap
					.get("commision") == null ? "0" : paramMap.get("commision")));//佣金余额
			BigDecimal zbalance = BigDecimal.valueOf(Double.valueOf(paramMap
					.get("zbalance") == null ? "0" : paramMap.get("zbalance")));//赠送余额
			BigDecimal integral = BigDecimal.valueOf(Double.valueOf(paramMap
					.get("integral") == null ? "0" : paramMap.get("integral")));//积分余额
			BigDecimal sellerAmount = BigDecimal.valueOf(Double.valueOf(paramMap
					.get("sellerAmount") == null ? "0" : paramMap.get("sellerAmount")));//商户或合作商营业收入余额
			
			//option 0：加  1： 减
			if("0".equals(option)){//加
				walletMap.put("amount", amount);
				walletMap.put("balance", balance);
				walletMap.put("commision", commision);
				walletMap.put("zbalance", zbalance);
				walletMap.put("integral", integral);
				walletMap.put("seller_amount", sellerAmount);
				walletMap.put("expenses", paramMap.get("expenses"));
			}else if("1".equals(option)){//减
				walletMap.put("amount", amount.negate());
				walletMap.put("balance", balance.negate());
				walletMap.put("commision", commision.negate());
				walletMap.put("zbalance", zbalance.negate());
				walletMap.put("integral", integral.negate());
				walletMap.put("seller_amount", sellerAmount.negate());
			}else {
				log.error("参数option必须为0或1  option："+option);
				returnMap.put("state","2");
				returnMap.put("msg","参数option必须为0或1");
				return returnMap;
			}
			
			walletMap.put("rtype", Integer.valueOf(paramMap.get("rType")));
			walletMap.put("remarks", paramMap.get("orderId"));
			if (paramMap.get("remark") != null) {
				if (UtilString.stringIsBlank(paramMap.get("description"))) {
					walletMap.put("description", paramMap.get("remark"));
				}else{
					walletMap.put("description", paramMap.get("description"));
				}
			}

			walletMap.put("overdraft", false);
			Map<String, String> resultMap = updateWalletAmount(walletMap);

			if (resultMap.get("code").equals(StateCodeUtil.PR_SUCCESS)) {
				status = 0;
				msg = "修改成功";
			} else if (resultMap.get("code").equals(
					StateCodeUtil.PR_WALLET_ERROR)) {
				status = 1;
				msg = resultMap.get("Msg");
				if(msg==null){
					msg = "用户Id为：" + uId + "钱包状态异常";
				}
				log.error(msg);
				return resultMap;
			} else if (resultMap.get("code").equals(
					StateCodeUtil.PR_SYSTEM_ERROR)) {
				status = 1;
				msg = resultMap.get("Msg");
				if(msg==null){
					msg = "用户Id为：" + uId + "引起系统错误";
				}
				log.error(msg);
				throw new FailureException(status, msg);
			} else if (resultMap.get("code").equals(
					StateCodeUtil.PR_REFUND_FAIL)) {
				status = 1;
				msg = resultMap.get("Msg");
				if(msg==null){
					msg = "用户Id为：" + uId + "被锁定";
				}
				log.error(msg);
				throw new FailureException(status, msg);
			} else {
				status = 1;
				msg = resultMap.get("Msg");
				if(msg==null){
					msg = "用户Id为：" + uId + "引起未知异常";
				}
				log.error(msg);
				throw new FailureException(status, msg);
			}
		returnMap.put("state", status + "");
		returnMap.put("msg", msg);
		returnMap.put("id",resultMap.get("id")==null?"":resultMap.get("id"));
		return returnMap;
	}
	
	@Override
	public int delWalletRecord(Map<String, Object> delMap) {
		return walletRecordMapper.delWalletRecord(delMap);
	}

	@Override
	public WalletRecord getWalletRecord(WalletRecord wr) {
		
		return walletRecordMapper.getWalletRecordData(wr);
	}
	
	@Override
	public WalletExpansion getWalletExpansion(Map<String, String> param) throws ParamBlankException {
		checkWalletSelectParam(param);
		return walletExpansionMapper.getWalletExpansion(param);
	}

	@Override
	public Boolean checkWalletSelectParam(Map<String, String> param) throws ParamBlankException {
		String uid = param.get("uid");
		String accountid = param.get("accountid");
		String typeId=param.get("typeId");
		if(StringUtils.isBlank(uid)&&StringUtils.isBlank(accountid)){
			throw new ParamBlankException("参数为空");
		}
		if(StringUtils.isNotBlank(uid)&&StringUtils.isBlank(typeId)){
			throw new ParamBlankException("用户类型为空");
		}else{
			if(StringUtils.isBlank(accountid)){
				Integer i = Integer.valueOf(typeId);
				if(i<0||i>3){
					throw new ParamBlankException("传入typeId无效");
				}
			}
		}
		return true;
	}

	@Override
	public WalletExpansion addWalletExpansion(Map<String, String> param) throws ParamBlankException {
		String uid = param.get("uid");
		String accountid = param.get("accountid");
		String type = param.get("type");
		Integer typeInt = Integer.valueOf(type);
		if((StringUtils.isBlank(uid)&&StringUtils.isBlank(accountid))||typeInt<XmnConstants.getMinType("EX_RTYPE_")||typeInt>XmnConstants.getMaxType("EX_RTYPE_")){
			throw new ParamBlankException("参数为空");
		}
		WalletExpansion wallet = getWalletExpansion(param);
		if(wallet!=null){
			throw new ParamBlankException("钱包已存在");
		}
		WalletExpansion walletExpansion = new WalletExpansion();
		if(StringUtils.isNotBlank(uid)){
			HashMap<String,Object> hashMap = new HashMap<>();
			hashMap.put("uId", uid);
			hashMap.put("typeId", param.get("typeId"));
			Map<String, Object> userWallet = walletMapper.selectByuid(hashMap);
			if(userWallet.get("accountid")==null){
				throw new ParamBlankException("用户钱包不存在");
			}
			walletExpansion.setAccountid(Long.valueOf(userWallet.get("accountid").toString()));
		}else{
			Wallet walletById = walletMapper.getWalletById(Integer.valueOf(accountid));
			if(walletById==null){
				throw new ParamBlankException("用户钱包不存在");
			}
			walletExpansion.setAccountid(walletById.getAccountid().longValue());
		}
		walletExpansion.setAmount(BigDecimal.valueOf(0));
		walletExpansion.setCreateTime(new Date());
		walletExpansion.setIsFreeze((Byte.valueOf("0")));
		walletExpansion.setModifyTime(new Date());
		walletExpansion.setTransfer(BigDecimal.valueOf(0));
		walletExpansion.setType(Byte.valueOf(type));
		walletExpansion.setSign("");
		walletExpansion.setForbidTransfer(UtilCommon.getWalletExpansionForbidTransfer(typeInt).byteValue());
		walletExpansionMapper.insert(walletExpansion);
		Map<String,String> signMap=walletExpansion.toSignMap();
		String sign = commonService.WalletExpansionSign(signMap);
		walletExpansion.setSign(sign);
		walletExpansionMapper.updateByPrimaryKey(walletExpansion);
		return walletExpansion;
	}

	@Override
	public List<WalletExpansion> getWalletExpansionList(Map<String, String> param) throws ParamBlankException {
		String uid = param.get("uid");
		if(StringUtils.isNotBlank(uid)){
			if(StringUtils.isBlank(param.get("typeId"))){
				throw new ParamBlankException("用户类型为空");
			}else{
				Integer i = Integer.valueOf(param.get("typeId"));
				if(i<0||i>3){
					throw new ParamBlankException("传入typeId无效");
				}
			}
		}
		Map<String, Object> string2Object = MapUtil.String2Object(param);
		String type = param.get("type");
		if(StringUtils.isNotBlank(type)){
			string2Object.put("type", type.split(","));
		}
		return walletExpansionMapper.getWalletExpansionList(string2Object);
	}

	@Override
	public List<WalletExpansionRecord> getWalletExpansionRecordList(Map<String, String> param) throws ParamBlankException {
		checkWalletSelectParam(param);
		Map<String, Object> string2Object = MapUtil.String2Object(param);
		String type = param.get("expansionType");
		if(StringUtils.isNotBlank(type)){
			string2Object.put("expansionType", type.split(","));
		}
		return walletExpansionRecordMapper.getWalletExpansionRecordList(string2Object);
	}

	@Override
	public Boolean hasZbalanceLock(Integer accountid) {
		return walletLockMapper.getZbalanceLock(accountid)>0;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public int lockWallet(Integer updateOption, Integer lockOption, Integer accountid,Integer type) {
		switch (updateOption) {
		case 0:
			if(hasZbalanceLock(accountid)){
				return 1;
			}
			Wallet wallet = walletMapper.getWalletById(accountid);
			return createLock(lockOption, accountid, type, wallet);
		case 1:
			return deleteLock(accountid, type);
		default:
			return 0;
		}
		
	}

	private int deleteLock(Integer accountid, Integer type) {
		switch (type) {
		case 1:
		case 2:	
			walletLockMapper.deleteByAccountid(accountid,1);
			walletLockMapper.deleteByAccountid(accountid,2);
			return 1;
		default:
			walletLockMapper.deleteByAccountid(accountid,type);
			return 1;
		}	
		
	}

	private int createLock(Integer lockOption, Integer accountid, Integer type, Wallet wallet) {
		WalletLock walletLock = new WalletLock();
		walletLock.setAccountid(accountid);
		walletLock.setLockAdd(lockOption==0);
		walletLock.setLockSubtract(lockOption==1);
		walletLock.setType(type.byteValue());
		walletLock.setCreateTime(new Date());
		walletLock.setQamount(wallet.getAmount());
		walletLock.setQbalance(wallet.getBalance());
		walletLock.setQcommision(wallet.getCommision());
		walletLock.setQincome(wallet.getQuota());
		walletLock.setQzbalance(wallet.getZbalance());
		switch (type) {
		case 1:
		case 2:	
			walletLock.setType(new Byte("1"));
			walletLockMapper.insert(walletLock);
			walletLock.setType(new Byte("2"));
			walletLockMapper.insert(walletLock);
			return 1;
		default:
			return 0;
		}
	}

	@Override
	public Wallet getWalletLockByUidType(Object uid, Object userType) {
		return walletMapper.getWalletLockByUidType(new Integer(uid.toString()),new Integer(userType.toString()));
	}

}
