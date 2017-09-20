package com.xmniao.service.pay;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xmniao.common.MapUtil;
import com.xmniao.common.ParamUtil;
import com.xmniao.common.StateCodeUtil;
import com.xmniao.common.UtilCommon;
import com.xmniao.common.UtilException;
import com.xmniao.common.UtilString;
import com.xmniao.common.WithdrawMoneyUtils;
import com.xmniao.dao.CheckWalletMapper;
import com.xmniao.dao.DeletedAccountMapper;
import com.xmniao.dao.ExpensesMpper;
import com.xmniao.dao.GetMentionLedgerMapper;
import com.xmniao.dao.JointInfoMapper;
import com.xmniao.dao.LedgerMapper;
import com.xmniao.dao.MentionAccountMapper;
import com.xmniao.dao.PwdRecordMapper;
import com.xmniao.dao.UpdateMentionLedgerMapper;
import com.xmniao.dao.UpdateWalletBalanceMapper;
import com.xmniao.dao.UpdateWithdrawalsRecordStateMapper;
import com.xmniao.dao.WBRecordMapper;
import com.xmniao.dao.WalletMapper;
import com.xmniao.dao.WalletRecordMapper;
import com.xmniao.entity.WithdrawMoneyConfig;
import com.xmniao.enums.WalletStatusCodeEnum;
import com.xmniao.exception.WalletZbalanceLockException;
import com.xmniao.service.CommonService;
import com.xmniao.service.FailOrderService;
import com.xmniao.service.LedgerDeductionService;
import com.xmniao.service.LedgerService;
import com.xmniao.service.ModifyService;
import com.xmniao.service.SellerStatisticsService;
import com.xmniao.service.UPayService;
import com.xmniao.service.WalletService;
import com.xmniao.service.WithdrawService;
import com.xmniao.service.impl.ChinaPnRPayImpl;
import com.xmniao.service.msg.WithdrawDetailProducer;
import com.xmniao.thrift.ledger.FailureException;
import com.xmniao.thrift.ledger.JointWithdraw;
import com.xmniao.thrift.ledger.MentionAccount;
import com.xmniao.thrift.ledger.ResponseData;
import com.xmniao.thrift.ledger.SellerStatistics;
import com.xmniao.thrift.ledger.SellerWallet;
import com.xmniao.thrift.ledger.SynthesizeService;
import com.xmniao.thrift.ledger.Wallet;
import com.xmniao.thrift.ledger.WalletRecord;

/**
 * 综合接口服务
 * 
 * @author YangJing
 * 
 */
@Service("SynthesizeServiceImpl")
public class SynthesizeServiceImpl implements SynthesizeService.Iface {

	/**
	 * 日志记录
	 */
	private final Logger log = Logger.getLogger(SynthesizeServiceImpl.class);

	@Autowired
	private UpdateMentionLedgerMapper updateMentionLedgerMapper;

	@Autowired
	private GetMentionLedgerMapper getMentionLedgerMapper;

	@Autowired
	private WalletMapper walletMapper;

	@Autowired
	private MentionAccountMapper mentionAccountMapper;

	@Autowired
	public CheckWalletMapper checkWalletMapper;

	@Autowired
	public UpdateWalletBalanceMapper updateWalletBalanceMapper;

	@Autowired
	public UpdateWithdrawalsRecordStateMapper updateWithdrawalsRecordStateMapper;

	@Autowired
	private StringRedisTemplate redisTemplate;

	@Autowired
	private ExpensesMpper expensesMpper;

	@Autowired
	private PwdRecordMapper pwdRecordMapper;

	@Autowired
	private WalletRecordMapper walletRecordMapper;
	@Autowired
	private JointInfoMapper jointInfoMapper;

	@Autowired
	private DeletedAccountMapper deletedAccountMapper;

	@Autowired
	private LedgerMapper ledgerMapper;

	@Autowired
	private WBRecordMapper wbRecordMapper;

	@Autowired
	private ChinaPnRPayImpl chinaPnRPay;

	@Autowired
	private UPayService uPayService;

	@Autowired
	private WalletService walletService;

	@Autowired
	private LedgerService ledgerService;

	@Autowired
	private WithdrawService withdrawService;

	@Autowired
	private FailOrderService failOrderService;

	@Autowired
	private SellerStatisticsService sellerStatisticsService;

	@Autowired
	private WithdrawDetailProducer withdrawDetailProducer;

	@Autowired
	private LedgerDeductionService ledgerDeductionService;
	
	@Resource(name = "IP_NUMBER")
	private String IP_NUMBER;

	@Resource(name = "PORT")
	private int PORT;

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

	/** 提现公共服务 */
	@Autowired
	private CommonService commonService;

	@Autowired
	private WithdrawMoneyConfig withdrawMoneyConfig;

	@Autowired
	private WithdrawMoneyUtils withdrawMoneyUtils;

	@Autowired
	private ModifyService modifyService;

	@Autowired
	private MentionAccountServiceImpl mentionAccountService;

	@Autowired
	private WithdrawMoneyServiceImpl withdrawMoneyService;
	
	/**
	 * 最高提现金额
	 */
	// private double tMoney = 50000;

	// 查询可提现金额时，只有>=该金额时，才返回可提现金额
	// private final double minMoney = 50.00;

	/**
	 * 1.2.1. 钱包余额查询
	 */
	@Override
	public Map<String, String> getWalletBalance(String uId, int typeId)
			throws FailureException, TException {
		log.info("getWalletBalance]uId:" + uId + ",typeId:" + typeId);

		if (typeId < 1 || typeId > 4) {
			log.error("请求的用户类型错误");
			log.info("返回参数：" + new HashMap<String, String>());
			return new HashMap<String, String>();
		}

		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("uId", uId);
			map.put("typeId", typeId);

			Map<String, Object> resultMap = walletMapper.selectByuid(map);

			Map<String, String> returnMap = new HashMap<String, String>();

			if (resultMap != null) {
				Integer accountid=(Integer) resultMap.get("accountid");
				if(accountid!=null){
					returnMap.put("zbalanceLock", walletService.hasZbalanceLock(accountid).toString());
				}
				for (Map.Entry<String, Object> index : resultMap.entrySet()) {
					returnMap.put(index.getKey(), index.getValue().toString());
				}
			}
			log.info("返回参数:" + returnMap);
			return returnMap;
		} catch (Exception e) {
			log.error("查询钱包余额异常");
		}
		log.info("返回参数：" + new HashMap<String, String>());
		return new HashMap<String, String>();
	}

	/*
	 * 修改分账方式和提现方式
	 * 
	 * @see
	 * com.xmniao.thrift.ledger.SynthesizeService.Iface#updateMentionLedger(int,
	 * int, java.lang.String)
	 */
	@Transactional
	@Override
	public int updateMentionLedger(int typeId, int typeValue, String uId,
			String amount, String money) throws FailureException, TException {

		log.info("修改分账方式和提现方式：typeId:" + typeId + "-----typeValue=" + typeValue
				+ "-----uId=" + uId + "-----amount=" + amount + "-----money="
				+ money);

		try {
			if ((uId) == null || (uId).equals("")
					|| (typeId != 1 && typeId != 2)
					|| (typeValue != 0 && typeValue != 1)) {
				log.error("参数不允许为空");
				log.info("返回参数：" + 3);
				return 3;
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("typeId", typeId);
			map.put("typeValue", typeValue);
			map.put("uId", uId);
			if(1==typeId){
				map.put("amount", amount);
				map.put("money", money);	
			}
			
			int result = updateMentionLedgerMapper.updateMentionLedger(map);
			if (result == 1) {
				log.info("修改成功");
				log.info("返回参数" + 0);
				return 0;
			} else {
				log.error("修改失败,用户不存在");
				log.info("返回参数：" + 1);
				return 1;
			}
		} catch (Exception e) {
			log.error("修改异常", e);
			log.info("返回参数：" + 2);
			return 2;
		}

	}

	/*
	 * 查询分账和提现方式接口
	 * 
	 * @see
	 * com.xmniao.thrift.ledger.SynthesizeService.Iface#getMentionLedger(int,
	 * java.lang.String)
	 */
	@Override
	public Map<String, String> getMentionLedger(int typeId, String uId)
			throws FailureException, TException {

		log.info("[getMentionLedger]uId的值:   " + uId + "  typeId的值:   "
				+ typeId);
		Map<String, String> resultMap = new HashMap<String, String>();
		try {

			if (((typeId) != 1 && (typeId) != 2)
					|| ((uId) == null || (uId).equals(""))) {
				resultMap.put("state", "3");

				log.error("用户参数不能为空");
				log.info("返回参数：" + resultMap);
				return resultMap;// 用户参数异常
			}
			if (typeId == 1) {
				Map<String, Object> map = getMentionLedgerMapper
						.getMention(uId); // 商户提现配置表的商户id及金额
				if (map == null) { // 商户id不存在，则通过查询电子钱包表accountid插入到提现配置表

					Map<String, Object> result = getMentionLedgerMapper
							.selectByAccountid(uId); // 通过uId获取用户钱包信息
					if (result == null) {
						int res = checkandaddwallet(uId, "2", "", "");
						if (res == 0) {
							resultMap.put("state", "0");
							resultMap.put("amount", "0");
							resultMap.put("income", "0");
							log.info("返回参数：" + resultMap);
							return resultMap;
						} else {
							resultMap.put("state", "2");
							log.error("插入商户提现配置表失败");
							log.info("返回参数：" + resultMap);
							return resultMap;
						}
					}
					Map<String, Object> map1 = new HashMap<String, Object>();
					map1.put("uId", uId);
					map1.put("accountid", result.get("accountid"));
					map1.put("mention", 0);
					map1.put("ledger", 1);
					map1.put("income", 0.00);
					map1.put("money", 0.00);
					map1.put("amount", 0.00);
					int result1 = getMentionLedgerMapper
							.insertByAccountid(map1);
					if (result1 == 1) {
						log.info("成功插入商户提现配置表");
						resultMap.put("state", "0");
						resultMap.put("amount", "0");
						resultMap.put("income", "0");
						log.info("返回参数：" + resultMap);
						return resultMap;
					} else {
						resultMap.put("state", "2");
						log.error("插入商户提现配置表失败");
						log.info("返回参数：" + resultMap);
						return resultMap;
					}

				} else if (map != null) {
					for (Map.Entry<String, Object> index : map.entrySet()) {
						resultMap.put(index.getKey(), index.getValue()
								.toString());
					}
				}
				log.info("成功查询");
				log.info("返回参数：" + resultMap);
				return resultMap;

			} else if (typeId == 2) {
				Map<String, Object> map = getMentionLedgerMapper.getLedger(uId);
				if (map == null) {
					Map<String, Object> result = getMentionLedgerMapper
							.selectByAccountid(uId);
					if (result == null) {
						int res = checkandaddwallet(uId, "2", "", "");
						if (res == 0) {
							resultMap.put("state", "1");
							resultMap.put("amount", "0.00");
							resultMap.put("income", "0.00");
							log.info("返回参数：" + resultMap);
							return resultMap;
						} else {
							resultMap.put("state", "2");
							log.error("插入商户提现配置表失败");
							log.info("返回参数：" + resultMap);
							return resultMap;
						}
					}
					Map<String, Object> map1 = new HashMap<String, Object>();
					map1.put("uId", uId);
					map1.put("accountid", result.get("accountid"));
					map1.put("mention", 0);
					map1.put("ledger", 1);
					map1.put("income", 0.00);
					map1.put("money", 0.00);
					map1.put("amount", 0.00);
					int result1 = getMentionLedgerMapper
							.insertByAccountid(map1);
					if (result1 == 1) {
						log.info("成功插入商户提现配置表");
						resultMap.put("state", "1");
						resultMap.put("amount", "0.00");
						resultMap.put("income", "0.00");
						log.info("返回参数：" + resultMap);
						return resultMap;
					} else {
						resultMap.put("state", "2");
						log.error("插入商户提现配置表失败");
						log.info("返回参数：" + resultMap);
						return resultMap;
					}
				} else if (map != null) {
					for (Map.Entry<String, Object> index : map.entrySet()) {
						resultMap.put(index.getKey(), index.getValue()
								.toString());
					}
				}
				log.info("成功查询");
				log.info("返回参数：" + resultMap);
				return resultMap;
			}

		} catch (Exception e) {
			log.error("查询异常", e);
			resultMap.put("state", "2");
			log.info("返回参数：" + resultMap);
			return resultMap;
		}
		log.info("返回参数：" + resultMap);
		return resultMap;
	}

	/**
	 * 验证钱包密码
	 * 
	 * by YangJing
	 */
	@Override
	public int checkWalletPwd(String uId, String pwd, int typeId)
			throws FailureException, TException {
		log.info("[checkWalletPwd]用户Id=" + uId + "-----------密码=" + pwd
				+ "----------用户类型Id=" + typeId);

		if (uId == null || uId.equals("") || pwd == null || pwd.equals("")
				|| (typeId != 1 && typeId != 2 && typeId != 3 && typeId != 4)) {
			log.info("返回参数：" + 2);
			return 2;
		}
		if (pwd.length() != 32) {
			log.error("密码长度有误！");
			log.info("返回参数：" + 2);
			return 2;
		}

		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("uId", uId);
		paramMap.put("typeId", String.valueOf(typeId));

		try {
			Map<String, String> resultMap = walletMapper
					.getWalletByUserId(paramMap);
			if (resultMap != null) {
				if (pwd.equals(resultMap.get("pwd"))) {
					log.info("返回参数：" + 0);
					return 0;
				} else {
					log.info("返回参数：" + 1);
					return 1;
				}
			} else {
				return 3;
			}
		} catch (Exception e) {
			log.error("验证钱包密码异常：", e);
		}
		log.info("返回参数：" + 1);
		return 1;
	}

	/**
	 * 修改钱包密码
	 * 
	 * By YangJing
	 */
	@Override
	@Transactional
	public int updateWalletPwd(String uId, String pwd, int typeId)
			throws FailureException, TException {

		log.info("修改钱包密码或设置钱包密码 uId:" + uId + "-----pwd:" + pwd
				+ "-----typeId:" + typeId);

		if (uId == null || uId.equals("") || pwd == null || pwd.equals("")
				|| (typeId != 1 && typeId != 2 && typeId != 3 && typeId != 4)) {
			log.error("参数不允许为空");
			log.info("返回参数：" + 2);
			return 2;
		}
		if (pwd.length() != 32) {
			log.error("密码长度有误！");
			log.info("返回参数：" + 2);
			return 2;
		}

		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("uId", uId);
		paramMap.put("typeId", String.valueOf(typeId));
		try {
			Map<String, String> resultMap = walletMapper
					.getWalletByUserId(paramMap);

			if (resultMap != null) {

				resultMap.remove("pwd");
				resultMap.put("pwd", pwd);
				
				resultMap.put("integral",resultMap.get("Integral"));
				
				String signStr = commonService.walletSign(resultMap);

				paramMap.put("sign", signStr);
				paramMap.put("oldSign", resultMap.get("sign"));
				paramMap.put("pwd", pwd);

				log.info("修改钱包密码参数：paramMap=" + paramMap);

				int state = walletMapper.updatePwdBySign(paramMap);
				Map<String, String> map = new HashMap<String, String>();
				map.put("uId", uId);
				map.put("sdate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.format(new Date()));
				if (state == 1) {
					if (typeId == 1) {
						int state1 = pwdRecordMapper.insertPwdRecordUid(map);
						if (state1 == 1) {
							log.info("成功插入修改密码记录");
							log.info("返回参数：" + 0);
							return 0;
						}
					} else if (typeId == 2) {
						int state1 = pwdRecordMapper
								.insertPwdRecordSellerid(map);
						if (state1 == 1) {
							log.info("成功插入修改密码记录");
							log.info("返回参数：" + 0);
							return 0;
						}
					} else if (typeId == 3) {
						int state1 = pwdRecordMapper
								.insertPwdRecordJointid(map);
						if (state1 == 1) {
							log.info("成功插入修改密码记录");
							log.info("返回参数：" + 0);
							return 0;
						}
					}else if (typeId == 4) {
						int state1 = pwdRecordMapper
								.insertPwdRecordAllianceid(map);
						if (state1 == 1) {
							log.info("成功插入修改密码记录");
							log.info("返回参数：" + 0);
							return 0;
						}
					}
					log.info("返回参数：" + 0);
					return 0;
				} else {
					log.info("返回参数：" + 3);
					return 3;
				}
			} else {

				int result = checkandaddwallet(uId, String.valueOf(typeId),
						pwd, "");
				if (result >= 3) {
					result = 1;
				}
				log.info("返回参数：" + result);
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("验证钱包密码异常：", e);
		}
		return 1;
	}

	/**
	 * 新增钱包
	 */
	@Override
	@Transactional
	public int addWallet(String uId, String userType, String password,
			String sellerName) throws FailureException, TException {
		log.info("[addWallet]uId:" + uId + ",userType:" + userType
				+ ",password:" + password + ",sellerName:" + sellerName);

		int result = checkandaddwallet(uId, userType, password, sellerName);
		if (result >= 3) {
			result = 1;
		}
		log.info("返回参数：" + result);
		return result;
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
		if (!userType.matches("^[1-4]$")) {
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
			map.put("userType", userType);
			map.put("account", phoneNumber);
			// Map<String, String> resultMap =
			// walletMapper.getWalletByUserId(map);
			int count = walletMapper.getWalletNum(map);
			Map<String, String> signMap = new HashMap<String, String>();
			signMap.put("uid", userType.equals("1") ? uId : "0");
			signMap.put("sellerid", userType.equals("2") ? uId : "0");
			signMap.put("jointid", userType.equals("3") ? uId : "0");
			signMap.put("allianceid", userType.equals("4") ? uId : "0");
			signMap.put("pwd", password == null ? "" : password);
			signMap.put("amount", "0.00");
			signMap.put("balance", "0.00");
			signMap.put("commision", "0.00");
			signMap.put("zbalance", "0.00");
			signMap.put("integral", "0.00");
			signMap.put("sellerAmount", "0.00");

			String signStr = commonService.walletSign(signMap);
			if (count == 0) {
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
				} else if ("4".equals(userType)) {
					map.put("uid", "0");
					map.put("jointid", "0");
					map.put("sellerid", "0");
					map.put("allianceid",uId);
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
						&& (userType.equals("1") || userType.equals("3") || userType.equals("4"))) {
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
		// return 1;
	}

	/**
	 * 获取银行卡
	 */
	@Override
	public List<Map<String, String>> getMentionAccount(String uId, int userType)
			throws FailureException, TException {

		log.info("[getMentionAccount]用户Id：" + uId + "------用户类型：" + userType);
		return mentionAccountService.getMentionAccount(uId, userType);
	}

	/**
	 * 删除银行/支付账号接口
	 * 
	 */
	@Override
	@Transactional
	public int delMentionAccount(String id) throws FailureException, TException {
		log.info("方法：删除银行/支付账号接口 delMentionAccount-->id:" + id);
		return mentionAccountService.delMentionAccount(id);
	}

	/**
	 * 验证是否第一次提现
	 */
	@Override
	public int checkWallet(String uId, String userType)
			throws FailureException, TException {
		log.info("checkWallet参数：userType=" + userType + "------uId=" + uId);
		try {
			if ((uId).equals("") || (uId) == null || (userType).equals("")
					|| (userType) == null) {
				log.error("参数不允许为空");
				log.info("返回参数：" + 2);
				return 2;
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("uId", uId);
			map.put("userType", userType);

			Map<String, Object> result = checkWalletMapper.getUserPwd(map);
			if (result == null) {
				checkandaddwallet(uId, userType, "", "");
				log.info("用户钱包不存在，已新增！");
				log.info("返回参数：" + 0);
				return 0;
			} else {
				map.put("uId", uId);
				map.put("userType", userType);
				int count = checkWalletMapper.selectByUid(map);
				if (count == 0) {
					log.info("第一次提现");
					log.info("返回参数：" + 0);
					return 0;
				} else {
					log.info("不是第一次提现");
					log.info("返回参数：" + 1);
					return 1;
				}
			}
		} catch (Exception e) {
			log.error("验证失败", e);
			log.info("返回参数：" + 2);
			return 2;
		}
	}

	/**
	 * 修改钱包余额（分账接口）
	 * 
	 * @throws Exception
	 * 
	 */
	@Override
	@Transactional(propagation = Propagation.NEVER)
	public int updateWalletBalance(List<Map<String, String>> walletMap)
			throws FailureException {
		long sdate = System.currentTimeMillis();
		int code = 0;
		log.info("修改钱包余额（分账接口）入参：" + walletMap);
		if (walletMap.size() == 0) {
			log.error("参数异常，请检查参数是否合法");
			log.info("返回参数：" + 2);
			return 2; // 参数异常
		}
		try {
			Map<String, String> res = ledgerService.ledger(walletMap);
			if (res != null && !res.isEmpty()) {

				code = res.get("code") == null ? 1 : Integer.parseInt(res
						.get("code"));
			} else {
				code = 1;
			}
		} catch (Exception e) {
			log.error("保存分账数据异常" + e);
			code = 1;
		}

		long edate = System.currentTimeMillis();
		long result = edate - sdate;
		log.info("返回参数：" + code);
		log.info("接口运行时间： " + result + "ms ");
		return code;
	}

	/**
	 * 獲取錢包密碼修改次數
	 */
	@Override
	public int getUpdatePwdNum(String uId, String userType)
			throws FailureException, TException {

		log.info("[getUpdatePwdNum]uId:" + uId + ",userType:" + userType);

		if (StringUtils.isBlank(uId) || !userType.matches("^[1-3]$")) {
			log.error("传入参数错误！");
			log.info("返回参数：" + 0);
			return 0;
		}

		/* 全数字验证 */
		if (!uId.matches("^[0-9]*$")) {
			log.error("传入参数错误2！");
			log.info("返回参数：" + 0);
			return 0;
		}
		int result = 0;
		try {
			Map<String, Object> map = new HashMap<>();
			map.put("uId", uId);
			map.put("userType", userType);
			result = pwdRecordMapper.selectCountRecordByuId(map);
		} catch (Exception e) {
			log.error("获取钱包操作，执行异常");
			result = 0;
		}
		log.info("返回参数：" + result);
		return result;
	}

	/**
	 * 获取该商户/合作商当天的提现总金额
	 * 
	 * @return
	 */
	private double getTodayWithdrawals(String id, String type) {
		double allMoney = 0.0;

		if ("1".equals(type.trim()) || "2".equals(type.trim()) || "4".equals(type.trim())) {// 商户与用户   区域代理（新增 2017/3/24--cj）
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
	 * 查询钱包修改记录
	 */
	@Override
	public WalletRecord getWalletRecord(Map<String, String> paramMap)
			throws FailureException, TException {

		log.info("getWalletRecord---paramMap：" + paramMap);

		WalletRecord wr = new WalletRecord();
		if (paramMap == null || paramMap.size() == 0) {
			wr.setPageCount(0);
			wr.setCount(0);
			wr.setWalletList(null);
			log.error("参数不能为空");
			log.info("返回的参数：" + wr);
			return wr;
		}
		try {
			Map<String, Object> recordMap = new HashMap<String, Object>();
			recordMap.put(
					"cPage",
					(Integer.parseInt(paramMap.get("cPage")) - 1)
							* Integer.valueOf(paramMap.get("pageSize")));
			recordMap.put("pageSize",
					Integer.parseInt(paramMap.get("pageSize")));
			recordMap.put("sDate", paramMap.get("sDate"));
			recordMap.put("eDate", paramMap.get("eDate"));
			recordMap.put("rType", paramMap.get("rType"));
			recordMap.put("oType", paramMap.get("oType"));
			recordMap.put("userName", paramMap.get("userName"));
			recordMap.put("phoneNumber", paramMap.get("phoneNumber"));
			recordMap.put("orderId", paramMap.get("orderId"));

			if (StringUtils.isBlank(paramMap.get("uId"))
					&& StringUtils.isBlank(paramMap.get("userType"))) {
				recordMap.put("accountid", "");

			} else if (StringUtils.isNotBlank(paramMap.get("uId"))
					&& StringUtils.isNotBlank(paramMap.get("userType"))) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("uId", paramMap.get("uId"));
				map.put("userType", paramMap.get("userType"));
				Integer accountId = walletRecordMapper.getAccountid(map);
				if (accountId == null) {
					wr.setPageCount(0);
					wr.setCount(0);
					wr.setWalletList(null);
					log.error("用户信息不存在");
					return wr;
				}
				recordMap.put("accountid", accountId);
			} else if (((paramMap.get("uId") == null) || ("").equals(paramMap
					.get("uId"))) // 根据用户类型查询
					&& ((paramMap.get("userType") != null && paramMap.get(
							"userType").length() != 0))) {
				recordMap.put("userType", paramMap.get("userType"));
			} else if ((paramMap.get("uId") != null && paramMap.get("uId")
					.length() != 0) // 不能只根据用户id查询
					&& (paramMap.get("userType") == null || ("")
							.equals(paramMap.get("userType")))) {
				wr.setPageCount(0);
				wr.setCount(0);
				wr.setWalletList(null);
				log.error("参数不匹配");
				return wr;
			} else {
				log.error("不合理的参数");
				wr.setPageCount(0);
				wr.setCount(0);
				wr.setWalletList(null);
				return wr;
			}

			recordMap.put(
					"cPage",
					(Integer.parseInt(paramMap.get("cPage")) - 1)
							* Integer.valueOf(paramMap.get("pageSize")));
			recordMap.put("pageSize",
					Integer.parseInt(paramMap.get("pageSize")));
			recordMap.put("sDate", paramMap.get("sDate"));
			recordMap.put("eDate", paramMap.get("eDate"));
			recordMap.put("rType", paramMap.get("rType"));
			recordMap.put("oType", paramMap.get("oType"));
			recordMap.put("userName", paramMap.get("userName"));
			recordMap.put("phoneNumber", paramMap.get("phoneNumber"));

			Integer listCount = walletRecordMapper.getListCount(recordMap);
			int tempPageSize = Integer.parseInt(paramMap.get("pageSize"));
			int pageCount = listCount % tempPageSize == 0 ? (int) (listCount / tempPageSize)
					: (int) (listCount / tempPageSize) + 1;// 页数

			wr.setPageCount(pageCount);
			wr.setCount(listCount);
			if (listCount == 0) {
				wr.setWalletList(null);
				log.error("用户没有修改记录");
				return wr;
			}

			List<Map<String, Object>> walletList = walletRecordMapper
					.getWalletRecord(recordMap);
			if (walletList == null || walletList.size() == 0) {
				wr.setWalletList(null);
				log.error("用户没有修改记录");
				return wr;
			}
			List<Map<String, String>> returnalletlist = new ArrayList<Map<String, String>>();
			for (Map<String, Object> map1 : walletList) {
				Map<String, String> objMap = new HashMap<String, String>();
				objMap = MapUtil.Object2String(map1);
				returnalletlist.add(objMap);
			}
			walletList.clear();
			wr.setWalletList(returnalletlist);
			log.info("返回的参数：listCount=" + listCount + "---pageCount="
					+ pageCount);
			return wr;

		} catch (Exception e) {
			wr.setPageCount(0);
			wr.setCount(0);
			wr.setWalletList(null);
			log.error("查询异常", e);
			return wr;
		}
	}

	/**
	 * 查詢当天可提現金額
	 */
	@Override
	public double getMentionBalance(String uId, String userType)
			throws FailureException, TException {
		if (uId == null || userType == null) {
			log.info("传入参数不能为空");
			return 0;
		}
		log.info("[getMentionBalance] uId:" + uId + ",userType:" + userType);
		// 1.查询当天已提现金额
		double alreadyAmount = getTodayWithdrawals(uId, userType);
		if (alreadyAmount >= withdrawMoneyConfig.getMaxMoney()) {
			log.info("该用户已达今日提现限额");
			return 0;
		}
		// 2.查询该用户钱包金额
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uId", uId);
		map.put("userType", userType);
		double welletAmount = walletMapper.getWithdrawalsByid(map);
		if (welletAmount <= 0) {
			log.info("该用户钱包中已无可提现金额");
			return 0;
		}
		// 3.计算并返回当天可提现金额
		BigDecimal wAmount = BigDecimal.valueOf(welletAmount); // 钱包剩余可提现金额
		BigDecimal aAmount = BigDecimal.valueOf(alreadyAmount); // 已提现金额
		BigDecimal tAmount = BigDecimal.valueOf(
				withdrawMoneyConfig.getMaxMoney()).setScale(2,
				BigDecimal.ROUND_HALF_DOWN); // ROUND_HALF_DOWN--向“最接近的”数字舍入，如果与两个相邻数字的距离相等，则为上舍入的舍入模式。
		double resultAmount = wAmount.add(aAmount).doubleValue() > withdrawMoneyConfig
				.getMaxMoney() ? tAmount.subtract(aAmount)
				.setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue()
				: welletAmount;
		return resultAmount >= withdrawMoneyConfig.getMinMoney() ? resultAmount
				: 0.0; // <50.0时，直接反馈为0，即
		// 当天还可提现金额，超过>=50时，才允许提现

	}

	/**
	 * 1.2.10.修改钱包余额
	 */
	@Override
	@Transactional(propagation = Propagation.NEVER)
	public Map<String, String> updateBalance(
			List<Map<String, String>> paramMapList) {
		log.info("[updateBalance] paramMapList:" + paramMapList);

		// 返回参数
		Map<String, String> reMap = new HashMap<String, String>();

		try {

			reMap = walletService.updateBalance(paramMapList);

		} catch (FailureException e) {

			log.error("修改钱包金额异常：",e);

			reMap.put("state", e.state + "");
			reMap.put("msg", e.info);
		} catch (Exception e) {
			log.error("修改钱包金额异常：",e);

			reMap.put("state", "1");
			reMap.put("msg", e.getMessage());
		}

		return reMap;
	}

	/**
	 * 1.2.20. 查询钱包余额列表
	 */
	@Override
	public Wallet getWalletList(Map<String, String> paramMap)
			throws FailureException, TException {
		log.info("getWalletList:paramMap=" + paramMap);
		int pagesize = paramMap.get("pageSize") != null
				&& !"".equals(paramMap.get("pageSize")) ? Integer
				.valueOf(paramMap.get("pageSize")) : 10;
		int cPage = paramMap.get("cPage") != null
				&& !"".equals(paramMap.get("cPage")) ? Integer.valueOf(paramMap
				.get("cPage")) : 1;

		Map<String, Object> newMap = new HashMap<String, Object>();
		newMap.put("startNo", (cPage - 1) * pagesize);
		newMap.put("pageSize", pagesize);
		if (paramMap.get("userName") != null
				&& paramMap.get("userName").length() != 0) {
			newMap.put("userName", paramMap.get("userName"));
		}
		if (paramMap.get("uId") != null && paramMap.get("uId").length() != 0) {
			newMap.put("uId", paramMap.get("uId"));
		}
		if (paramMap.get("userType") != null
				&& paramMap.get("userType").length() != 0) {
			newMap.put("userType", paramMap.get("userType"));
		}
		if (paramMap.get("phoneNumber") != null
				&& paramMap.get("phoneNumber").trim().length() != 0) {
			newMap.put("account", paramMap.get("phoneNumber"));
		}

		log.info("查询参数---newMap:" + newMap);
		int countpage = walletMapper.getCountPage(newMap);
		int pageCount = countpage % pagesize == 0 ? countpage / pagesize
				: countpage / pagesize + 1;
		List<Map<String, String>> walletList = walletMapper
				.getwelletList(newMap);
		log.info("结果：pageCount=" + pageCount + "-----countpage=" + countpage);

		return new Wallet(pageCount, walletList, countpage);
	}

	/**
	 * 1.2.21. 查询钱包记录总金额
	 */
	@Override
	public double getWRAmount(String uId, String userType, int rType)
			throws FailureException, TException {
		log.info("[getWRAmount]uId:" + uId + ",userType:" + userType
				+ ",rType:" + rType);
		if ((uId == null && ("").equals(uId))
				|| (userType == null && ("").equals(userType))
				|| (String.valueOf(rType) == null && ("").equals(String
						.valueOf(rType)))) {
			log.error("参数不能为空");
			return 0.00;
		}
		if (rType < 0) {
			log.error("参数范围有误");
			return 0.00;
		}
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("uId", uId);
			map.put("userType", userType);
			Integer accountid = walletRecordMapper.getAccountid(map);
			if (accountid == null) {
				log.error("用户不存在");
				return 0.00;
			}
			Map<String, Object> amountMap = new HashMap<String, Object>();
			amountMap.put("accountid", accountid);
			amountMap.put("rType", rType);
			double amount = walletRecordMapper.getWalletAmount(amountMap);
			log.info("查询成功,钱包记录总金额为：" + amount);
			return amount;
		} catch (Exception e) {
			log.error("查询异常" + e);
			return 0.00;
		}
	}

	/**
	 * 查询钱包总个数（用于分账系统导出钱包数据）
	 * 
	 */
	@Override
	public int getWalletCount(Map<String, String> paramMap)
			throws FailureException, TException {
		log.info("[getWalletCount]paramMap:" + paramMap);
		String name = paramMap.get("userName");
		if (StringUtils.isBlank(name)) {
			paramMap.remove("userName");
		}
		String userType = paramMap.get("userType");
		if (StringUtils.isBlank(userType) || userType.equals("all")) {
			paramMap.remove("userType");
		}
		log.info("[getWalletCount]paramMap2:" + paramMap);
		return walletMapper.getWalletCount(paramMap);
	}

	/**
	 * 1.2.23. 查询钱包记录总个数（用于分账系统钱包记录导出）
	 */
	@Override
	public int getWalletRecordCount(Map<String, String> paramMap)
			throws FailureException, TException {
		log.info("[getWalletRecordCount]paramMap:" + paramMap);
		String name = paramMap.get("userName");
		if (StringUtils.isBlank(name)) {
			paramMap.remove("userName");
		}
		String userType = paramMap.get("userType");
		if (StringUtils.isBlank(userType) || userType.equals("all")) {
			paramMap.remove("userType");
		}
		String rType = paramMap.get("rType");
		if (StringUtils.isBlank(rType)) {
			paramMap.remove("rType");
		}
		String sDate = paramMap.get("sDate");
		if (StringUtils.isBlank(sDate)) {
			paramMap.remove("sDate");
		}
		String eDate = paramMap.get("eDate");
		if (StringUtils.isBlank(eDate)) {
			paramMap.remove("eDate");
		}
		String uId = paramMap.get("uId");
		if (StringUtils.isBlank(uId)) {
			paramMap.remove("uId");
		}
		log.info("[getWalletRecordCount]paramMap2:" + paramMap);
		return walletRecordMapper.getWalletRecordCount(paramMap);
	}

	/**
	 * 1.2.25. 查询银行卡列表
	 */
	@Override
	public MentionAccount getMentionAccountList(Map<String, String> paramMap)
			throws FailureException, TException {
		log.info("查询银行卡列表");
		log.info("[getMentionAccountList]paramMap" + paramMap);
		return mentionAccountService.getMentionAccountList(paramMap);
	}

	/**
	 * 1.2.25. 统计用户总金额
	 */
	@Override
	public Map<String, String> getUserMoney(String ids, int userType,
			int moneyType) throws FailureException, TException {

		log.info("[getUserMoney]ids:" + ids + ",userType:" + userType
				+ ",moneyType:" + moneyType);

		Map<String, String> resultMap = new HashMap<String, String>();

		try {
			String[] idArray = ids.split(",");

			for (String uId : idArray) {

				Map<String, String> walletMap = new HashMap<String, String>();

				walletMap.put("uId", uId);
				walletMap.put("userType", String.valueOf(userType));

				int wNum = walletMapper.getWalletNum(walletMap);

				if (wNum == 1) {
					continue;
				} else {

					resultMap.put("state", "2");
					resultMap.put("money", "0");
					resultMap.put("msg", "商家：" + uId + "没有钱包");
					return resultMap;
				}
			}

			Map<String, Object> param = new HashMap<String, Object>();

			param.put("moneyType", moneyType);
			param.put("userType", userType);
			param.put("ids", idArray);

			double amountTotal = walletMapper.getAmountTotal(param);

			resultMap.put("state", "0");
			resultMap.put("money", String.valueOf(amountTotal));
			resultMap.put("msg", "统计成功");
		} catch (Exception e) {
			log.error("统计用户总金额出错", e);
			resultMap.put("state", "1");
			resultMap.put("money", "0");
			resultMap.put("msg", "系统错误");
		}

		return resultMap;
	}

	/**
	 * 1.2.26. 查询商家钱包信息
	 */
	@Override
	public SellerWallet getSellerWalletList(String ids, int moneyType)
			throws FailureException, TException {

		log.info("[getSellerWalletList]ids:" + ids + ",moneyType:" + moneyType);
		SellerWallet sw = new SellerWallet();

		try {
			String[] idArray = ids.split(",");

			for (String uId : idArray) {

				Map<String, String> walletMap = new HashMap<String, String>();

				walletMap.put("uId", uId);
				walletMap.put("userType", "2");

				int wNum = walletMapper.getWalletNum(walletMap);

				if (wNum == 1) {
					continue;
				} else {

					int state = checkandaddwallet(uId, "2", null, null);

					if (state == 0) {
						continue;
					} else {
						sw.setMsg("商家：" + uId + "没有钱包");
						sw.setState(2);
						sw.setSellerWalletList(null);
						return sw;
					}
				}
			}

			Map<String, Object> param = new HashMap<String, Object>();

			param.put("moneyType", moneyType);
			param.put("userType", "2");
			param.put("ids", idArray);

			List<Map<String, String>> waMap = walletMapper
					.getWalletAccount(param);

			sw.setMsg("成功");
			sw.setState(0);
			sw.setSellerWalletList(waMap);

		} catch (Exception e) {
			log.error("查询商家钱包信息", e);
			sw.setMsg("系统错误");
			sw.setState(1);
			sw.setSellerWalletList(null);
		}

		return sw;
	}

	/**
	 * 1.2.27. 资金归集
	 * 
	 * @throws FailureException
	 */
	@Transactional(propagation = Propagation.NEVER)
	@Override
	public Map<String, String> mergeMoney(Map<String, String> paramMap) {
		log.info("[mergeMoney]map:" + paramMap);

		Map<String, String> resultMap = new HashMap<String, String>();

		try {
			// 调用资金归集接口
			resultMap = walletService.mergeMoney(paramMap);

		} catch (FailureException e) {

			log.error("资金归集失败" + e);
			resultMap.put("state", "" + e.state);
			resultMap.put("money", "0");
			resultMap.put("msg", e.info);
		} catch (Exception e) {

			log.error("资金归集失败" + e);
			resultMap.put("state", "1");
			resultMap.put("money", "0");
			resultMap.put("msg", "程序异常，资金归集失败。");
		}
		return resultMap;
	}

	/**
	 * 1.2.28. 查询用户银行卡
	 */
	@Override
	public List<Map<String, String>> getMentionAccounts(
			List<Map<String, String>> param) throws FailureException,
			TException {
		log.info("[getMentionAccounts]param：" + param.toString());
		return mentionAccountService.getMentionAccounts(param);
	}

	/**
	 * 1.2.29. 平台退款(打款失败退款到余额)
	 */
	@Override
	@Transactional(propagation = Propagation.NEVER)
	public Map<String, String> returnWithdrawals(Map<String, String> param)
			throws FailureException, TException {
		log.info("[SynthesizeService:returnWithdrawals]param:" + param);
		Map<String, String> resultMap = new HashMap<String, String>();
		String type = param.get("type");
		if (UtilString.stringIsNotBlank(type) && type.equals("2")) {
			try {
				resultMap = withdrawService.cancelWithdrawals(param);
			} catch (Exception e) {
				log.error("取消提现异常：" + e);
				resultMap.put("wId", param.get("wId"));
				resultMap.put("state", "1");
				resultMap.put("msg", "取消提现失败");
			}
			return resultMap;
		} else if (!UtilString.stringIsNotBlank(type) || type.equals("1")) {
			try {
				resultMap = withdrawService.returnWithdrawals(param);
				if (resultMap.get("state").equals("-1")) {
					resultMap.put("state", "1");
				}
			} catch (Exception e) {
				log.error("平台退款异常：" + e);
				resultMap.put("wId", param.get("wId"));
				resultMap.put("state", "1");
				resultMap.put("msg", "平台退款失败");
			}
			return resultMap;
		} else {
			resultMap.put("wId", param.get("wId"));
			resultMap.put("state", "2");
			resultMap.put("msg", "参数错误");
			return resultMap;

		}
	}

	/**
	 * 1.2.30. 提现金额统计（商户端）
	 * 
	 */
	@Override
	public Map<String, String> statisticsWithdrawalsMoney(
			Map<String, String> param) throws FailureException, TException {

		log.info("[statisticsWithdrawalsMoney]param:" + param);

		Map<String, String> resultMap = new HashMap<String, String>();

		if (param.isEmpty() || param.get("uId").equals("")
				|| param.get("userType").equals("")
				|| param.get("userType").equals("0")) {
			log.info("参数为空");
			resultMap.put("state", "2");
			resultMap.put("msg", "参数为空");
			return resultMap;
		}

		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.putAll(param);
			paramMap.put("status", new int[] { 1 });

			Map<String, Object> result = walletMapper
					.getWithdrawalsMoney(paramMap);
			resultMap.put("adIncome", String.valueOf(result.get("income")));
			resultMap.put("adCommision",
					String.valueOf(result.get("commision")));

			paramMap.put("status", new int[] { 0, 3 });

			Map<String, Object> result2 = walletMapper
					.getWithdrawalsMoney(paramMap);
			resultMap.put("naIncome", String.valueOf(result2.get("income")));
			resultMap.put("naCommision",
					String.valueOf(result2.get("commision")));

			resultMap.put("state", "0");
			resultMap.put("msg", "查询成功");
		} catch (Exception e) {
			log.error("提现金额统计出错" + e);
			log.info("提现金额统计出错");
			resultMap.put("state", "1");
			resultMap.put("msg", "出错");
			return resultMap;
		}

		return resultMap;
	}

	/**
	 * 添加银行卡(旧版本)
	 */
	@Override
	@Transactional
	public int AddMentionAccount(int isuse, String uId, int type,
			String account, int cardType, String userName, String bankName,
			String mobileId, int userType, int ispublic, int idtype,
			String identity, String bank, String abbrev, String province,
			String cityname) throws FailureException, TException {

		log.info("[AddMentionAccount]uId:" + uId + ",type:" + type
				+ ",account:" + account + ",cradType:" + cardType
				+ ",userName:" + userName + ",bankName:" + bankName
				+ ",mobileId:" + mobileId + ",isuse:" + isuse + ",userType:"
				+ userType + ",ispublic:" + ispublic + ",idtype:" + idtype
				+ ",identity:" + identity + ",bank:" + bank + ",abbrev:"
				+ abbrev + ",province:" + province + ",cityname:" + cityname);

//		uId = uId.trim();
//		account = account.trim();
//		userName = userName.trim();
//		bankName = bankName.trim();
//		mobileId = mobileId.trim();
//		identity = identity.trim();
//		bank = bank.trim();
//		abbrev = abbrev.trim();
//		province = province.trim();
//		cityname = cityname.trim();

		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("uId", uId);
		paramMap.put("type", type + "");
		paramMap.put("account", account);
		paramMap.put("cardType", cardType + "");
		paramMap.put("userName", userName);
		paramMap.put("bankName", bankName);
		paramMap.put("mobileId", mobileId);
		paramMap.put("isuse", isuse + "");
		paramMap.put("userType", userType + "");
		paramMap.put("ispublic", ispublic + "");
		paramMap.put("idtype", idtype + "");
		paramMap.put("identity", identity);
		paramMap.put("bank", bank);
		paramMap.put("abbrev", abbrev);
		paramMap.put("province", province);
		paramMap.put("cityname", cityname);
		// paramMap.put("license", "");
		// paramMap.put("upidcard", "");
		// paramMap.put("dwidcard", "");

		Map<String, String> resultMap = mentionAccountService
				.addMentionAccount(paramMap);
		if (resultMap.get("state").equals("0")) {
			return 0;
		} else {
			return 1;
		}
	}

	/**
	 * 修改银行卡(旧版本)
	 */
	@Override
	@Transactional
	public int updateMentionAccount(String id, int type, String account,
			int cardType, String userName, String bankName, String mobileId,
			int isuse, String cityname, int ispublic, int idtype,
			String identity, String bank, String abbrev, String province)
			throws FailureException, TException {

		log.info("[updateMentionAccount]id:" + id + ",type:" + type
				+ ",account:" + account + ",cradType:" + cardType
				+ ",userName:" + userName + ",bankName:" + bankName
				+ ",mobileId:" + mobileId + ",isuse:" + isuse);

//		id = id.trim();
//		account = account.trim();
//		userName = userName.trim();
//		bankName = bankName.trim();
//		mobileId = mobileId.trim();
//		identity = identity.trim();
//		bank = bank.trim();
//		abbrev = abbrev.trim();
//		province = province.trim();
//		cityname = cityname.trim();

		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("id", id);
		paramMap.put("type", type + "");
		paramMap.put("account", account);
		paramMap.put("cardType", cardType + "");
		paramMap.put("userName", userName);
		paramMap.put("bankName", bankName);
		paramMap.put("mobileId", mobileId);
		paramMap.put("isuse", isuse + "");
		paramMap.put("ispublic", ispublic + "");
		paramMap.put("idtype", idtype + "");
		paramMap.put("identity", identity);
		paramMap.put("bank", bank);
		paramMap.put("abbrev", abbrev);
		paramMap.put("province", province);
		paramMap.put("cityname", cityname);

		Map<String, String> resultMap = mentionAccountService
				.updateMentionAccount(paramMap);
		if (resultMap.get("state").equals("0")) {
			return 0;
		} else {
			return 1;
		}
	}

	/**
	 * 1.2.31. 查询合作商月收益与提现
	 */
	@Override
	public Map<String, String> getJointIncome(Map<String, String> param)
			throws FailureException, TException {

		log.info("[getJointIncome]param:" + param);

		Map<String, String> result = new HashMap<String, String>();
		if (param == null || param.get("uId").isEmpty()
				|| param.get("userType").isEmpty()
				|| param.get("months").isEmpty()) {
			result.put("state", "2");
			result.put("msg", "参数异常");
			log.equals("param:" + param);
			log.error("查询合作商月收益与提现失败,参数异常");
		} else {
			try {
				Map<String, Object> resultMap = wbRecordMapper
						.getJointIncome(param);

				if (resultMap != null && !resultMap.isEmpty()) {
					result.put("state", "0");
					result.put("msg", "查询成功");
					result.put("income",
							String.valueOf(resultMap.get("income")));
					result.put("wIncome",
							String.valueOf(resultMap.get("wIncome")));
				} else
					throw new Exception("查询结果集为空");
			} catch (Exception e) {
				log.equals("param:" + param);
				log.error("查询合作商月收益出错", e);
				result.put("state", "1");
				result.put("msg", "查询合作商月收益出错");
			}
		}
		return result;
	}

	/**
	 * 添加用户钱包Map
	 * 
	 */
	@Override
	@Transactional
	public Map<String, String> addWalletMap(Map<String, String> param)
			throws FailureException, TException {

		log.info("[addWalletMap]param:" + param);
		String uId = param.get("uId");
		String userType = param.get("userType");
		String password = param.get("password");
		String phoneNumber = UtilString.stringIsNotBlank(param
				.get("phoneNumber")) ? param.get("phoneNumber") : "";
		String sellerName = param.get("sellerName");
		Map<String, String> result = checkandaddwallet(uId, userType, password,
				sellerName, phoneNumber);
		if (result.get("state").equals("3")) {
			result.put("state", "1");
		}
		log.info("返回参数：" + result);
		return result;
	}

	@Override
	public JointWithdraw getJointWithdrawalsList(Map<String, String> param)
			throws FailureException {

		log.info("查询合作商提现信息 -- getJointWithdrawalsList  param:" + param);
		// 返回对象jointWithdraw
		JointWithdraw jointWithdraw = new JointWithdraw();
		List<Map<String, String>> jointWithdrawList = new ArrayList<Map<String, String>>();
		jointWithdraw.setJointWithdrawList(jointWithdrawList);
		// 查询参数
		Map<String, Object> params = null;

		try {

			String ids = param.get("ids");
			String sDate = param.get("sDate");
			String cPage = param.get("cPage");
			String pSize = param.get("pSize");

			if (ids == null || sDate == null || ids.trim().equals("")
					|| sDate.trim().equals("")) {
				jointWithdraw.setState(2);
				jointWithdraw.setMsg("参数异常");
				log.error("参数异常");
				return jointWithdraw;
			}
			String[] list = ids.split(",");
			int pSizei = pSize == null ? 10 : Integer.valueOf(pSize);
			int startPage = cPage == null ? 0 : (Integer.valueOf(cPage) - 1)
					* pSizei;
			params = new HashMap<String, Object>();
			params.put("list", list); // jointids
			params.put("sDate", sDate);// 时间
			params.put("startPage", startPage);
			params.put("pageSize", pSizei);
		} catch (Exception e) {
			jointWithdraw.setState(2);
			jointWithdraw.setMsg("参数异常");
			log.error("参数异常", e);
			return jointWithdraw;
		}
		try {
			Map<String, Object> allResult = jointInfoMapper
					.getIncomeWithdraw(params);
			if (allResult == null || allResult.isEmpty()) {
				jointWithdraw.setState(1);
				jointWithdraw.setMsg("查询结果为空");
				log.error("查询合作商提现信息 结果集为空 ");
				return jointWithdraw;
			} else {
				jointWithdraw.setWithdrawedMoney(String.valueOf(allResult
						.get("withdrawedMoney")));
				jointWithdraw.setWithdrawingMoney(String.valueOf(allResult
						.get("withdrawingMoney")));
				jointWithdraw.setSplitMoney(String.valueOf(allResult
						.get("splitMoney")));
				jointWithdraw.setNowSplitMoney(String.valueOf(allResult
						.get("nowSplitMoney")));
			}

			List<Map<String, Object>> results = jointInfoMapper
					.getSomeIncomeWithdraws(params);

			if (results != null) {
				Map<String, String> tempMap = null;
				for (Map<String, Object> map : results) {
					tempMap = MapUtil.Object2String(map);
					jointWithdrawList.add(tempMap);
				}
			}
		} catch (Exception e) {
			jointWithdraw.setState(1);
			jointWithdraw.setMsg("查询出错");
			log.error("查询合作商提现信息出错", e);
			return jointWithdraw;
		}
		jointWithdraw.setState(0);
		jointWithdraw.setMsg("查询成功");
		return jointWithdraw;

	}

	/**
	 * 1.2.35. 根据时间查询钱包余额列表
	 */
	@Override
	public Wallet getWalletListByDate(Map<String, String> paramMap)
			throws FailureException, TException {
		log.info("[getWalletListByDate]paramMap:" + paramMap);

		int selectType = 0; // 0 错误类型；1现在；2过去某天；3过去某段时期
		String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		String sDate = paramMap.get("sDate");
		String eDate = paramMap.get("eDate");
		if (UtilString.stringIsNotBlank(sDate)
				&& sDate.substring(0, 10).compareTo(today) > 0) {
			log.error("sDate不能大于当前日期");
			selectType = 0;
		} else if (UtilString.stringIsNotBlank(eDate)
				&& eDate.substring(0, 10).compareTo(today) > 0) {
			log.error("eDate不能大于或等于当前日期");
			selectType = 0;
		} else if (UtilString.stringIsNotBlank(sDate)
				&& UtilString.stringIsNotBlank(eDate)) {
			log.info("查询的为" + sDate + "到" + eDate + "的日期段");
			if (sDate.compareTo(eDate) > 0) {
				log.error("查询范围出错，结束日期应大于或等于开始日期");
				selectType = 0;
			} else {
				if (sDate.substring(0, 10).equals(eDate.substring(0, 10))) {
					if (sDate.substring(0, 10).equals(today)) {
						log.info("查询的的sDate字段为当前日期");
						selectType = 1;
					} else {
						log.info("查询的是" + sDate + "当天的信息");
						selectType = 2;
					}
				} else {
					String tempDate = null;
					try {
						tempDate = UtilString.theDateOfNextMonth(sDate, 3);
					} catch (ParseException e) {
						e.printStackTrace();
						log.error("日期格式化错误");
						throw new FailureException(1, "日期转换失败，请检查日期格式1");
					}
					if (tempDate.compareTo(eDate.substring(0, 10)) < 0) {
						log.error("每次查询日期区间跨度不超过三个月");
						selectType = 0;
					} else {
						selectType = 3;
					}
				}
			}
		} else if (!UtilString.stringIsNotBlank(sDate)
				&& UtilString.stringIsNotBlank(eDate)) {
			log.error("查询日期出错，查询结束日期的同时必须设置开始时期");
			selectType = 0;
		} else if (!UtilString.stringIsNotBlank(sDate)
				&& !UtilString.stringIsNotBlank(eDate)) {
			log.info("查询的sDate字段没传或sDate字段为空");
			selectType = 1;
		} else {
			if (sDate.substring(0, 10).equals(today)) {
				log.info("查询的的sDate字段为当前日期");
				selectType = 1;
			} else {
				selectType = 2;
			}
		}

		if (selectType == 1) {
			// 查询现在的钱包信息
			paramMap.remove("sDate");
			paramMap.remove("eDate");
			return getWalletList(paramMap);
		} else if (selectType == 2) {
			// 查询过去某天的钱包信息
			// return getPastWalletList(paramMap);
			try {
				paramMap.put("eDate", UtilString.theDateOfTomorrow(sDate));
			} catch (ParseException e) {
				e.printStackTrace();
				log.error("日期转换错误", e);
				throw new FailureException(1, "日期转换失败，请检查日期格式1");
			}
			return getPastDateWalletList(paramMap);
		} else if (selectType == 3) {
			log.info("查某一时间段记录");
			// 查询过去某段时期的钱包信息
			return getPastDateWalletList(paramMap);
		} else {
			return new Wallet(0, new ArrayList<Map<String, String>>(), 0);
		}

	}

	/**
	 * 查询过往日期的钱包余额
	 * 
	 * @param paramMap
	 * @return
	 * @throws FailureException
	 * @throws Exception
	 */
	private Wallet getPastDateWalletList(Map<String, String> paramMap)
			throws FailureException {

		log.info("getPastDateWalletList:paramMap=" + paramMap);

		String sDate = paramMap.get("sDate");
		String eDate = paramMap.get("eDate");

		int pagesize = paramMap.get("pageSize") != null
				&& !"".equals(paramMap.get("pageSize")) ? Integer
				.valueOf(paramMap.get("pageSize")) : 10;
		int cPage = paramMap.get("cPage") != null
				&& !"".equals(paramMap.get("cPage")) ? Integer.valueOf(paramMap
				.get("cPage")) : 1;

		Map<String, Object> newMap = new HashMap<String, Object>();
		try {
			// 由于数据库是在0点后才保存前一天的钱包数据，所以所有的日期均需在此基础上向后推一天再查
			// BETWEEN sDate AND eDate的区间为[sDate,eDate)左闭右开
			newMap.put("sDate", UtilString.theDateOfTomorrow(sDate));
			newMap.put("eDate", UtilString.theDateOfTomorrow(eDate));
		} catch (ParseException e1) {
			e1.printStackTrace();
			log.error("日期转换错误 ", e1);
			throw new FailureException(1, "日期转换失败，请检查日期格式1");
		}

		newMap.put("startNo", (cPage - 1) * pagesize);
		newMap.put("pageSize", pagesize);
		if (paramMap.get("userName") != null
				&& paramMap.get("userName").length() != 0) {
			newMap.put("userName", paramMap.get("userName"));
		}
		if (paramMap.get("uId") != null && paramMap.get("uId").length() != 0) {
			newMap.put("uId", paramMap.get("uId"));
		}
		if (paramMap.get("userType") != null
				&& paramMap.get("userType").length() != 0) {
			newMap.put("userType", paramMap.get("userType"));
		}
		if (paramMap.get("phoneNumber") != null
				&& paramMap.get("phoneNumber").trim().length() != 0) {
			newMap.put("account", paramMap.get("phoneNumber"));
		}

		// 获取需要查询的的月份，格式：yyMM
		List<String> month = new ArrayList<String>();
		try {
			// 1.sDate-包含sDate， 实际要查的是后一天的数据，如4.30号，需到1505的表中查询5.1的数据
			// 2.eDate-不包含eDate 实际要查的是当天的数据，如6.1号，需要查5.30的数据，因而需在1506的表中查询6.1的数据
			String sMonth = UtilString.theDateOfTomorrow(sDate)
					.substring(0, 10);
			String eMonth = eDate.substring(0, 10);

			while (true) {
				if (sMonth.substring(0, 7).compareTo(eMonth.substring(0, 7)) <= 0) {
					month.add(UtilString.dateFormatTodate(sMonth).substring(2,
							6));
					sMonth = UtilString.theDateOfNextMonth(sMonth);
				} else {
					break;
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
			log.error("日期转换错误 ", e);
			throw new FailureException(2, "日期转换失败，请检查日期格式2");
		}

		log.info("查询参数---newMap:" + newMap + ",dateMonth:" + month);

		int countpage = 0, pageCount = 0;
		List<Map<String, String>> walletList = new ArrayList<Map<String, String>>();
		try {
			countpage = wbRecordMapper.getPastDateWalletCount(newMap, month);
			pageCount = countpage % pagesize == 0 ? countpage / pagesize
					: countpage / pagesize + 1;
			walletList = wbRecordMapper.getPastDateWalletList(newMap, month);
			log.info("结果：pageCount=" + pageCount + "-----countpage="
					+ countpage);
		} catch (Exception e) {
			log.error("查询失败" + UtilException.getExceptionInformation1(e));
			return new Wallet(0, walletList, 0);
		}
		return new Wallet(pageCount, walletList, countpage);
	}

	/**
	 * 商家手动提现
	 */
	@Override
	@Transactional
	public int updateWithdrawalsRecord(List<Map<String, String>> paramMap,
			Map<String, String> orderMap) throws FailureException, TException {
		
		log.info("方法-->商家手动提现 updateWithdrawalsRecord-->orderMap:" + orderMap
				+ "-->amountMapList:" + paramMap);
		//2016年7月6日 Edit By CB 改用WithdrawMoneyService中的开放接口
			Map<String,String> resultMap = withdrawMoneyService.updateWithdrawalsRecord(paramMap, orderMap);
			return Integer.parseInt(resultMap.get("state"));
	}

	/**
	 * 合作商手动提现
	 */
	@Override
	@Transactional
	public int updateJointWithdrawalsRecord(List<Map<String, String>> paramMap,
			Map<String, String> orderMap) throws FailureException, TException {
		log.info("方法-->合作商手动提现 updateJointWithdrawalsRecord-->orderMap:"
				+ orderMap + "-->amountMapList:" + paramMap);
		//2016年7月6日 Edit By CB 改用WithdrawMoneyService中的开放接口
		Map<String,String> resultMap = withdrawMoneyService.updateJointWithdrawalsRecord(paramMap, orderMap);
		return Integer.parseInt(resultMap.get("state"));

	}


	@Transactional
	public Map<String, String> delMentionAccountByMap(
			Map<String, String> accountMap) {
		Map<String, String> returnMap = new HashMap<String, String>();

		Map<String, Object> maMap = mentionAccountMapper
				.getMentionAccount(accountMap);
		if (maMap == null || maMap.size() == 0) {
			returnMap.put("state", "1");
			returnMap.put("msg", "解除绑定失败，没有查询到该银行卡信息！");
			return returnMap;
		}
		maMap.put("del_date", UtilString.dateFormatNow());
		deletedAccountMapper.addDeletedRecord(maMap);

		int result = mentionAccountMapper.delMentionAccount(accountMap
				.get("id"));
		if (result == 1) {
			returnMap.put("state", "0");
			returnMap.put("msg", "解除绑定成功！");
		} else {
			returnMap.put("state", "1");
			returnMap.put("msg", "解除绑定失败");
			throw new RuntimeException();
		}
		return returnMap;
	}

	/**
	 * 调单 修改对应订单所关联的钱包金额
	 */
	@Transactional(propagation = Propagation.NEVER)
	@Override
	public Map<String, String> modifyOrder(Map<String, String> formerMap,
			Map<String, String> laterMap) throws FailureException, TException {
		log.info("调单入参：\r\nformerMap:" + formerMap + ",\r\nlaterMap:"
				+ laterMap);

		Map<String, String> resultMap = new HashMap<String, String>();
		try {
			String orderId = formerMap.get("orderId");
			if (UtilString.stringIsNotBlank(orderId)) {

				boolean modifyed = modifyService.isModifyed(orderId);
				if (!modifyed) { // 该单之前没被调单
					Map<String, Object> wRecord = new HashMap<String, Object>();
					wRecord.put("rtype", 0);
					wRecord.put("remarks", orderId + "_%");// 匹配 '12345678_%'
					int fenzhang = walletRecordMapper.getRecordCount(wRecord);
					if (fenzhang > 0) {
						// 已分账
						modifyService.modifyBalance(formerMap, laterMap);
						resultMap.put("code", "0");
						resultMap.put("remark", "调单成功");
					} else {
						log.error("该订单还未进行分账");
						resultMap.put("code", "1");
						resultMap.put("remark", "该订单还未进行分账");
					}
				} else {
					resultMap.put("code", "2");
					resultMap.put("remark", "重复申请调单");
				}
			} else {
				log.error("orderId不能为空");
				resultMap.put("code", "1");
				resultMap.put("remark", "orderId不能为空");
			}
		} catch (Exception e) {
			log.error("调单失败:" + UtilException.getExceptionInformation1(e));
			resultMap.put("code", "1");
			resultMap.put("remark", e.getMessage());
		}

		log.info("调单,resultMap:" + resultMap);
		return resultMap;
	}

	/**
	 * 平台扣款
	 */
	@Override
	@Transactional(propagation = Propagation.NEVER)
	public Map<String, String> platformDeductions(Map<String, String> param)
			throws FailureException, TException {
		log.info("平台扣款入参：\r\nparam:" + param);

		Map<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("orderId", param.get("orderId"));
		String userType = param.get("userType");
		String amount = param.get("amount");
		if (userType == null || amount == null || param.get("orderId") == null) {
			resultMap.put("code", "2");
			resultMap.put("msg", "参数不完整");
			return resultMap;
		}
		if (param.get("uId") == null && param.get("phoneNumber") == null) {
			resultMap.put("code", "2");
			resultMap.put("msg", "参数不完整");
			return resultMap;
		}
		String reg = "^\\d+(\\.\\d+)?$";
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(amount);
		if (!matcher.matches() || !userType.matches("^[1-3]$")) {
			resultMap.put("code", "2");
			resultMap.put("msg", "参数不合格式要求");
			return resultMap;
		}

		Map<String, Object> walletMap = new HashMap<String, Object>();
		if (UtilString.stringIsNotBlank(param.get("uId"))) {
			walletMap.put("uId", Integer.parseInt(param.get("uId")));
		}
		walletMap.put("userType", Integer.parseInt(param.get("userType")));
		walletMap.put("account", param.get("phoneNumber"));
		walletMap.put("remarks", param.get("orderId"));
		walletMap.put("amount", param.get("amount"));
		walletMap.put("description", param.get("description"));

		// 对应com.xmniao.entity.Wallet.java中字段名
		// 各字段扣款顺序
		String[] sequenceArray = null;
		if (userType.compareTo(UtilCommon.TYPE_SELLER) == 0) {
			sequenceArray = UtilCommon.SELLER_DEDUCTIONS_ARRAY;
		} else if (userType.compareTo(UtilCommon.TYPE_JOINT) == 0) {
			sequenceArray = UtilCommon.JOINT_DEDUCTIONS_ARRAY;
		} else {
			sequenceArray = UtilCommon.USER_DUCTIONS_ARRAY;
		}

		try {

			Map<String, String> map = walletService.walletDeductions(walletMap,
					sequenceArray);
			log.info("平台扣款钱包操作结果" + map);
			if (map.get("code").equals(StateCodeUtil.PR_SUCCESS)) {
				// 处理成功
				resultMap.put("code", "0");
				resultMap.put("msg", "平台扣款成功！");
			} else {
				// 处理失败
				resultMap.put("code", "1");
				resultMap.put("msg", map.get("Msg"));
			}
		} catch (Exception e) {
			log.error("钱包扣款异常！" + e);
			resultMap.put("code", "1");
			resultMap.put("msg", "钱包扣款异常！" + e);
		}
		return resultMap;
	}

	/**
	 * 设置钱包密码
	 */
	@Override
	@Transactional
	public Map<String, String> setWPwd(String uId, String userType,
			String password) throws FailureException, TException {
		log.info("设置钱包密码：uId:" + uId + ",userType:" + userType + ",password:"
				+ password);
		Map<String, String> resultMap = new HashMap<String, String>();

		if (uId == null || uId.equals("") || userType == null
				|| !userType.matches("^[1234]$")) {
			log.error("参数错误");
			resultMap.put("state", "2");
			resultMap.put("msg", "参数错误");
			return resultMap;
		}

		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("uId", uId);
		paramMap.put("typeId", userType);
		Map<String, String> wpMap = walletMapper.getWalletByUserId(paramMap);

		if (wpMap != null) {
			if (!isVerified(wpMap)) {
				log.error("当前签名不匹配");
				resultMap.put("state", "1");
				resultMap.put("msg", "当前钱包签名不匹配");
				return resultMap;
			}

			wpMap.put("pwd", password);
			
			wpMap.put("integral",wpMap.get("Integral"));
			
			String signStr = commonService.walletSign(wpMap);

			paramMap.put("sign", signStr);
			paramMap.put("oldSign", wpMap.get("sign"));
			paramMap.put("pwd", password);

			log.info("设置钱包密码参数：paramMap=" + paramMap);

			int state = walletMapper.updatePwdBySign(paramMap);
			if (state == 1) {

				Map<String, String> map = new HashMap<String, String>();
				map.put("uId", uId);
				map.put("typeId", userType);
				map.put("sdate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.format(new Date()));
				if (!UtilString.stringIsNotBlank(password)) {
					// 密码为空清空密码修改记录
					log.info("新密码为空，清除密码修改记录");
					pwdRecordMapper.delPwdRecord(map);
				} else {
					log.info("添加新密码修改记录");
					pwdRecordMapper.insertPwdRecord(map);
				}
				resultMap.put("state", "0");
				resultMap.put("msg", "修改成功");
				return resultMap;
			} else {
				resultMap.put("state", "1");
				resultMap.put("msg", "钱包被修改，请重试");
				return resultMap;
			}
		} else {
			log.error("找不到对应钱包");
			resultMap.put("state", "1");
			resultMap.put("msg", "找不到对应钱包");
			return resultMap;
		}
	}

	private boolean isVerified(Map<String, String> walletMap) {
		String oldSign = walletMap.get("sign");

		Map<String, String> sMap = new HashMap<String, String>();
		sMap.put("uid", String.valueOf(walletMap.get("uid")));
		sMap.put("sellerid", String.valueOf(walletMap.get("sellerid")));
		sMap.put("jointid", String.valueOf(walletMap.get("jointid")));
		sMap.put("pwd", String.valueOf(walletMap.get("pwd")));
		sMap.put("amount", String.valueOf(walletMap.get("amount")));
		sMap.put("balance", String.valueOf(walletMap.get("balance")));
		sMap.put("commision", String.valueOf(walletMap.get("commision")));
		sMap.put("zbalance", String.valueOf(walletMap.get("zbalance")));
		sMap.put("integral", String.valueOf(walletMap.get("Integral")));
		sMap.put("sellerAmount", String.valueOf(walletMap.get("sellerAmount")));
		// signMap.put("sign",SignWallSourse.sign(signMap));
		String nSign = commonService.walletSign(sMap);

		if (!oldSign.equals(nSign)) {
			log.error("当前签名不匹配");
			log.info("oldSign:" + oldSign);
			log.info("sign:" + nSign);
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 扣除分账金额 对已分账订单，进行扣除分账金额
	 */
	@Transactional(propagation = Propagation.NEVER)
	@Override
	public Map<String, String> deductionsMentionLedger(Map<String, String> param)
			throws FailureException, TException {
		log.info("扣除分账金额入参：\r\nparam:" + param);
		
		Map<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("code", "1");
		resultMap.put("remark", "该接口已废弃，请更新至版本接口");
		return resultMap;
	}

	/**
	 * 锁定/解锁钱包
	 */
	@Override
	@Transactional
	public Map<String, String> setLockWallet(Map<String, String> paramMap)
			throws FailureException, TException {
		log.info("[setLockWallet]paramMap:" + paramMap);
		Map<String, String> resultMap = new HashMap<String, String>();
		try {
			String userType = paramMap.get("userType");
			if (userType == null) {
				resultMap.put("code", "2");
				resultMap.put("msg", "参数不完整");
				return resultMap;
			}
			if (paramMap.get("uId") == null
					&& paramMap.get("phoneNumber") == null) {
				resultMap.put("code", "2");
				resultMap.put("msg", "参数不完整");
				return resultMap;
			}
			if (!userType.matches("^[1-4]$")) {
				resultMap.put("code", "2");
				resultMap.put("msg", "参数不合格式要求");
				return resultMap;
			}
			Map<String, Object> walletMap = new HashMap<String, Object>();
			walletMap.put("uId", paramMap.get("uId"));
			walletMap.put("userType",
					Integer.parseInt(paramMap.get("userType")));
			walletMap.put("account", paramMap.get("phoneNumber"));
			walletMap.put("status", Integer.parseInt(paramMap.get("status")));

			resultMap = walletService.setLockWallet(walletMap);
			resultMap.put("msg", resultMap.get("Msg"));
			return resultMap;
		} catch (Exception e) {
			log.error("程序运行异常：" + e.getMessage());
			resultMap.put("code", "1");
			resultMap.put("msg", "程序运行异常");
			return resultMap;
		}
	}

	/**
	 * 取消报障订单
	 */
	@Transactional(propagation = Propagation.NEVER)
	@Override
	public Map<String, String> recoveryFailOrder(Map<String, String> paramMap)
			throws FailureException, TException {
		log.info("取消报障传入参数:" + paramMap);
		Map<String, String> resultMap = new HashMap<String, String>();
		try {
			String orderId = paramMap.get("orderId");
			if (UtilString.stringIsNotBlank(orderId)) {
				return failOrderService.recoveryFailOrder(orderId.trim());
			} else {
				return getResultMap(resultMap, "201", "请传入orderId");
			}
		} catch (FailureException e) {
			log.error("取消报障业务服务对应接口返回失败或异常" + e);
			return getResultMap(resultMap, String.valueOf(e.getState()),
					e.getInfo());
		} catch (Exception e) {
			e.printStackTrace();
			log.error("取消报障订单执行异常:" + e);
		}
		return getResultMap(resultMap, "203", "取消报障异常");
	}

	private Map<String, String> getResultMap(Map<String, String> paramMap,
			String code, String msg) {
		paramMap.put("code", code);
		paramMap.put("msg", msg);
		return paramMap;
	}

	/**
	 * 查询商家营收返利提现统计（返回商家每天的营收返利统计）
	 */
	@Override
	public SellerStatistics getSellerStatisticsList(Map<String, String> paramMap)
			throws FailureException, TException {
		log.info("传入查询商家营收返利统计参数：" + paramMap);
		try {
			int count = 0;
			List<Map<String, Object>> list = null;

			String cPage = paramMap.get("cPage");
			String pSize = paramMap.get("pageSize");
			String sellerid = paramMap.get("sellerId");

			int pageSize = UtilString.stringIsNotBlank(pSize) ? Integer
					.parseInt(pSize.trim()) : 10;
			int curPage = UtilString.stringIsNotBlank(cPage) ? Integer
					.parseInt(cPage.trim()) : 1;
			int startNo = curPage > 0 ? (curPage - 1) * pageSize : 0;
			pageSize = pageSize > 0 ? pageSize : 10;

			String type = paramMap.get("amountType");
			if (UtilString.stringIsNotBlank(type)
					&& UtilString.stringIsNotBlank(sellerid)) {
				Map<String, Object> selectMap = new HashMap<String, Object>();

				selectMap.put("sellerid", sellerid.trim());
				selectMap.put("startNo", startNo);
				selectMap.put("pageSize", pageSize);
				type = type.trim();
				if (type.equals("1")) {
					count = sellerStatisticsService
							.countSellerIncomeStatistics(selectMap);
					list = sellerStatisticsService
							.getSellerIncomeStatistics(selectMap);
				} else if (type.equals("2")) {
					count = sellerStatisticsService
							.countSellerIncomeStatistics(selectMap);
					list = sellerStatisticsService
							.getSellerCommisionStatistics(selectMap);
				} else {
					SellerStatistics sellerStatistics = new SellerStatistics();
					sellerStatistics.setState(2);
					sellerStatistics.setMsg("传入参数错误");
					sellerStatistics.setPageCount(0);
					sellerStatistics.setCount(0);
					sellerStatistics
							.setStatisticsList(new ArrayList<Map<String, String>>());
					return sellerStatistics;
				}
			} else {
				SellerStatistics sellerStatistics = new SellerStatistics();
				sellerStatistics.setState(2);
				sellerStatistics.setMsg("传入参数错误");
				sellerStatistics.setPageCount(0);
				sellerStatistics.setCount(0);
				sellerStatistics
						.setStatisticsList(new ArrayList<Map<String, String>>());
				return sellerStatistics;
			}

			List<Map<String, String>> newList = new ArrayList<>();
			if (list != null) {
				for (Map<String, Object> map : list) {
					newList.add(MapUtil.Object2String(map));
				}
			}
			SellerStatistics sellerStatistics = new SellerStatistics();
			sellerStatistics.setState(0);
			sellerStatistics.setMsg("");
			sellerStatistics.setPageCount(count % pageSize == 0 ? count
					/ pageSize : count / pageSize + 1);
			sellerStatistics.setCount(count);
			sellerStatistics.setStatisticsList(list != null ? newList
					: new ArrayList<Map<String, String>>());
			return sellerStatistics;
		} catch (Exception e) {
			log.error("查询失败" + e);
			SellerStatistics sellerStatistics = new SellerStatistics();
			sellerStatistics.setState(1);
			sellerStatistics.setMsg("查询失败");
			sellerStatistics.setPageCount(0);
			sellerStatistics.setCount(0);
			sellerStatistics
					.setStatisticsList(new ArrayList<Map<String, String>>());
			return sellerStatistics;
		}
	}

	@Override
	public SellerStatistics getSellerDetailList(Map<String, String> paramMap)
			throws FailureException, TException {
		log.info("传入商家营收返利提现按日期查询明细接口参数：" + paramMap);
		try {
			int count = 0;
			List<Map<String, Object>> list = null;

			String cPage = paramMap.get("cPage");
			String pSize = paramMap.get("pageSize");
			String sellerid = paramMap.get("sellerId");
			String selectDate = paramMap.get("date");

			int pageSize = UtilString.stringIsNotBlank(pSize) ? Integer
					.parseInt(pSize.trim()) : 10;
			int curPage = UtilString.stringIsNotBlank(cPage) ? Integer
					.parseInt(cPage.trim()) : 1;
			pageSize = pageSize < 1 ? 10 : pageSize;
			int startNo = curPage > 0 ? (curPage - 1) * pageSize : 0;

			String type = paramMap.get("amountType");
			if (UtilString.stringIsNotBlank(type)
					&& (type.trim().equals("1") || type.trim().equals("2"))
					&& UtilString.stringIsNotBlank(sellerid)
					&& UtilString.stringIsNotBlank(selectDate)) {
				Map<String, Object> selectMap = new HashMap<String, Object>();

				selectMap.put("sellerid", sellerid.trim());
				selectMap.put("amountType", type);
				selectMap.put("date", selectDate.trim());
				selectMap.put("startNo", startNo);
				selectMap.put("pageSize", pageSize);
				type = type.trim();
				count = sellerStatisticsService.countSellerDetail(selectMap);
				list = sellerStatisticsService.getSellerDetailList(selectMap);
			} else {
				log.error("传入参数错误");
				SellerStatistics sellerStatistics = new SellerStatistics();
				sellerStatistics.setState(2);
				sellerStatistics.setMsg("传入参数错误");
				sellerStatistics.setPageCount(0);
				sellerStatistics.setCount(0);
				sellerStatistics
						.setStatisticsList(new ArrayList<Map<String, String>>());
				return sellerStatistics;
			}

			List<Map<String, String>> newList = new ArrayList<>();
			if (list != null) {
				for (Map<String, Object> map : list) {
					newList.add(MapUtil.Object2String(map));
				}
			}
			SellerStatistics sellerStatistics = new SellerStatistics();
			sellerStatistics.setState(0);
			sellerStatistics.setMsg("");
			sellerStatistics.setPageCount(count % pageSize == 0 ? count
					/ pageSize : count / pageSize + 1);
			sellerStatistics.setCount(count);
			sellerStatistics.setStatisticsList(list != null ? newList
					: new ArrayList<Map<String, String>>());
			return sellerStatistics;
		} catch (Exception e) {
			log.error("查询失败" + e);
			SellerStatistics sellerStatistics = new SellerStatistics();
			sellerStatistics.setState(1);
			sellerStatistics.setMsg("查询失败");
			sellerStatistics.setPageCount(0);
			sellerStatistics.setCount(0);
			sellerStatistics
					.setStatisticsList(new ArrayList<Map<String, String>>());
			return sellerStatistics;
		}
	}

	@Override
	public Map<String, String> getSellerMonthlyStatistics(
			Map<String, String> paramMap) throws FailureException, TException {

		log.info("传入查询商家营收返利提现当月统计接口参数：" + paramMap);

		Map<String, String> resultMap = new HashMap<String, String>();
		try {
			Map<String, Object> map = null;
			String sellerid = paramMap.get("sellerId");
			String type = paramMap.get("amountType");

			if (UtilString.stringIsNotBlank(type)
					&& UtilString.stringIsNotBlank(sellerid)) {
				Map<String, Object> selectMap = new HashMap<String, Object>();

				selectMap.put("sellerid", sellerid.trim());
				selectMap.put("month", UtilString.dateFormatNow());
				type = type.trim();
				if (type.equals("1")) {
					map = sellerStatisticsService
							.countSellerIncomeMonthlyStatistics(selectMap);
				} else if (type.equals("2")) {
					map = sellerStatisticsService
							.countSellerCommisionMonthlyStatistics(selectMap);
				} else {
					resultMap.put("state", "2");
					resultMap.put("msg", "传入查询类型错误");
					return resultMap;
				}
			} else {
				resultMap.put("state", "2");
				resultMap.put("msg", "相关传入参数不能为空");
				return resultMap;
			}

			if (map == null) {
				throw new Exception();
			}
			resultMap = MapUtil.Object2String(map);
			resultMap.put("state", "0");
			resultMap.put("msg", "");
			return resultMap;
		} catch (Exception e) {
			log.error("查询失败", e);
			resultMap.put("state", "1");
			resultMap.put("msg", "查询失败");
			return resultMap;
		}
	}

	/**
	 * 解除寻觅客返还押金至赠送金额
	 */
	@Override
	@Transactional
	public Map<String, String> xmerTransferDeposit(Map<String, String> paramMap)
			throws FailureException, TException {
		
		
		
		
		log.info("解除寻觅客身份:" + paramMap);
		Map<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("state", "2");
		resultMap.put("msg", "返还押金失败");
		return resultMap;
		
	}
	
	/**
	 * 寻蜜客返还押金
	 */
	@Override
	public Map<String, String> xmerReturnDeposit(Map<String, String> paramMap)
			throws FailureException, TException {
		
		return null;
	}

	/**
	 * 新版订单分账金额扣回接口
	 *  1.查询该订单是否有分账信息
	 *  2.查询该订单分账信息
	 *  3.扣除该订单的所有分账信息
	 */
	@Transactional(propagation = Propagation.NEVER)
	@Override
	public Map<String, String> deductionsOrderLedger(Map<String, String> param)
			throws FailureException, TException {
		log.info("（新版）扣除分账金额入参：\r\nparam:" + param);
		
		Map<String, String> resultMap = new HashMap<String, String>();
		try {
			if (param == null || param.size() == 0) {
				log.error("参数不能为空");
				resultMap.put("code", "2");
				resultMap.put("remark", "参数不能为空");
				return resultMap;
			}
			String orderId=param.get("orderId");
			String orderType=param.get("orderType");
			if (orderId==null || orderType==null) {
				log.error("参数不能为空");
				resultMap.put("code", "2");
				resultMap.put("remark", "参数不能为空");
				return resultMap;
			}
			
		/*	if(orderType.equals("1")){
				//消费订单
				ledgerDeductionService.deductionsXmnOrderLedger(orderId);
			}else if(orderType.equals("2")){
				//线下积分订单
				ledgerDeductionService.deductionsOfflineOrderLedger(orderId);
			}else if(orderType.equals("3")){
				//线上积分订单
				ledgerDeductionService.deductionsOnlineOrderLedger(orderId);
			}else{
				log.error("参数不能为空");
				resultMap.put("code", "2");
				resultMap.put("remark", "未知类型订单");
				return resultMap;
			}*/
			ResponseData responseData = ledgerDeductionService.deductionsXmnOrderLedger(param);
			if(0 != responseData.getState()){
				log.info("分账扣回失败：");
				resultMap.put("code", "1");
				resultMap.put("remark",responseData.getMsg());
				return resultMap;
			}
			log.info("扣回成功");
			resultMap.put("code", "0");
			resultMap.put("remark", "扣除分账成功");
			return resultMap;
		} catch (Exception e) {
			log.error("扣除分账金额异常。",e);
			resultMap.put("code", "1");
			resultMap.put("remark", "扣除分账金额失败" + e.getMessage());
			return resultMap;
		}
	}

	/**
	 * 更新钱包金额(所传金额参数均为正数，根据option判断加减)
	 */
	@Override
	public Map<String, String> updateWalletAmount(Map<String, String> param)
			throws FailureException, TException {
		log.info("更新钱包金额[updateWalletAmount]" + param);

		// 返回参数
		Map<String, String> resultMap = new HashMap<String, String>();

		try {

			resultMap = walletService.updateBalance2(param);

		} catch (WalletZbalanceLockException e) {
			log.info("[用户钱包佣金/赠送余额被锁定无法扣除]"+param,e);
			Map<String, String> failMap = ParamUtil.failMap(WalletStatusCodeEnum.ZBALANCE_LOCK_STATUS.getName());
			failMap.put("statusCode", WalletStatusCodeEnum.ZBALANCE_LOCK_STATUS.getStatus());
			return failMap;
		}catch (FailureException e) {

			log.error("修改钱包金额异常：",e);

			resultMap.put("state", e.state + "");
			resultMap.put("msg", e.info);
		} catch (Exception e) {
			log.error("修改钱包金额异常：",e);

			resultMap.put("state", "1");
			resultMap.put("msg", e.getMessage());
		}

		return resultMap;
	}
	
	/**
	 * 查询用户银行卡信息
	 */
	@Override
	public Map<String, String> getSingleMentionAccount(Map<String, String> param)
			throws FailureException, TException {
		
		log.info("查询用户银行卡信息getSingleMentionAccount"+param);
		
		return mentionAccountService.getSingleMentionAccount(param);
	}
	
	
	
	

}
