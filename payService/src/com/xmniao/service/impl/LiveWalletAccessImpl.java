package com.xmniao.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xmniao.common.DateUtil;
import com.xmniao.common.ParamUtil;
import com.xmniao.common.XmnConstants;
import com.xmniao.dao.LiveWalletLockMapper;
import com.xmniao.dao.LiveWalletMapper;
import com.xmniao.dao.LiveWalletRecordMapper;
import com.xmniao.entity.LiveWallet;
import com.xmniao.entity.LiveWalletLock;
import com.xmniao.entity.LiveWalletRecord;
import com.xmniao.entity.Wallet;
import com.xmniao.exception.CustomException;
import com.xmniao.exception.ParamBlankException;
import com.xmniao.service.CommonService;
import com.xmniao.service.LiveWalletAccess;
import com.xmniao.service.pay.LiveWalletServiceImpl;
import com.xmniao.service.pay.WalletExpansionServiceImpl;
import com.xmniao.thrift.ledger.FailureException;
import com.xmniao.thrift.ledger.ResponseData;
import com.xmniao.util.LiveWalletUtil;

/**
 * Created by yang.qiang on 2017/5/4.
 */
@Service
public class LiveWalletAccessImpl implements LiveWalletAccess {

	private final static Logger logger = LoggerFactory.getLogger(LiveWalletAccessImpl.class);

	@Autowired
	private LiveWalletMapper liveWalletMapper;

	@Autowired
	private LiveWalletRecordMapper liveWalletRecordMapper;

	@Autowired
	private WalletExpansionServiceImpl walletExpansionServiceImpl;

	@Autowired
	private LiveWalletServiceImpl liveWalletServiceImpl;
	
	@Autowired
	private LiveWalletLockMapper liveWalletLockMapper;
	

	@Autowired
	private CommonService commonService;

	/**
	 * 获取用户直播钱包 直播钱包为空, 钱包状态异常会返回错误
	 * 
	 * @param uid
	 */
	@Override
	public LiveWallet getLiveWallet(Integer uid) {
		logger.info("获取用户钱包, uid=" + uid);

		// 查询数据库
		LiveWallet record = new LiveWallet();
		record.setUid(uid);
		LiveWallet liveWallet = liveWalletMapper.selectLiveWalletBySelected(record);

		// 判断钱包是否为空
		if (liveWallet == null) {
			throw new CustomException("钱包为空, uid=" + uid, 1);
		}
		// 判断钱包状态
		if (!LiveWalletUtil.verifyStatus(liveWallet)) {
			throw new CustomException("钱包状态无效! status=" + liveWallet.getStatus());
		}
		// 判断钱包签名
		if (!LiveWalletUtil.verifySign(liveWallet)) {
			throw new CustomException("钱包签名错误");
		}
		return liveWallet;
	}

	/**
	 * V客兑换SAAS套餐: 扣除鸟币, 修改鸟币限额
	 * 
	 * @param liveWallet
	 * @param deductZbalance
	 * @param remarks
	 */
	@Override
	@Transactional
	public void exchangeSaas(LiveWallet liveWallet, BigDecimal deductZbalance, String remarks) {
		// 校验订单, 判断订单是否重复存在
		Long countRemarks = liveWalletMapper.countRemarks(remarks, liveWallet.getId().toString(),
				XmnConstants.RTYPE_51 + "");
		if (countRemarks > 0)
			throw new CustomException("请勿重复提交! 订单已存在! remarks=" + remarks);

		// 判断扣除鸟币金额
		if (deductZbalance.compareTo(BigDecimal.ZERO) <= 0) {
			throw new CustomException("扣除鸟币金额错误,deductZbalance=" + deductZbalance);
		}

		// 判断余额是否充足
		if (liveWallet.getZbalance().compareTo(deductZbalance) < 0)
			throw new CustomException("鸟币余额不足, 钱包鸟币余额:" + liveWallet.getZbalance() + "  需要扣除的余额:" + deductZbalance);

		// 扣除鸟币
		deductZbalance(liveWallet, deductZbalance);

		// 插入钱包更新数据
		LiveWalletRecord liveWalletRecord = new LiveWalletRecord();
		liveWalletRecord.setRtype(XmnConstants.RTYPE_51); // Type V客兑换SAAS套餐
		liveWalletRecord.setEggMoney(new BigDecimal(0)); // 交易鸟蛋金额
		liveWalletRecord.setCoinMoney(0L); // 交易鸟豆金额
		liveWalletRecord.setRemarks(remarks); // 订单号
		liveWalletRecord.setDescription("V客兑换SAAS套餐"); // 描述信息
		liveWalletRecord.setBeansMoney(deductZbalance); // 交易鸟币金额
		liveWalletRecord.setSellerBeansMoney(new BigDecimal(0)); // 交易商家鸟币金额

		insertLiveWalletRecord(liveWallet, liveWalletRecord);

		// 解除鸟币限制
		updateLimitBalance(liveWallet, deductZbalance);

	}

	/**
	 * 修改鸟币限制金额
	 * 
	 * @param liveWallet
	 * @param deductZbalance
	 */
	private void updateLimitBalance(LiveWallet liveWallet, BigDecimal deductZbalance) {
		// 计算修改后限制金额
		BigDecimal limitBalacne = liveWallet.getLimitBalance().subtract(deductZbalance);
		limitBalacne = limitBalacne.compareTo(BigDecimal.ZERO) > 0 ? limitBalacne : BigDecimal.ZERO;

		int updateCount = liveWalletMapper.updateLimitBalane(liveWallet, limitBalacne);
		if (updateCount < 1) {
			throw new CustomException("修改鸟币限制金额失败!");
		}
		logger.info("已修改鸟币限制金额, 原限制金额为:" + liveWallet.getLimitBalance() + "现限制金额为:" + limitBalacne + "扣除金额为:"
				+ deductZbalance);

	}

	/**
	 * 扣除鸟币
	 * 
	 * @param liveWallet
	 *            // 用户钱包
	 * @param deductZbalance
	 *            // 需要扣除的鸟币
	 */
	private void deductZbalance(LiveWallet liveWallet, BigDecimal deductZbalance) {

		// 获取钱包新签名
		LiveWallet walletParam = new LiveWallet();
		walletParam.setUid(liveWallet.getUid()); // 用户uid
		walletParam.setBalance(liveWallet.getBalance()); // 鸟蛋余额
		walletParam.setZbalance(liveWallet.getZbalance().subtract(deductZbalance)); // 扣除后的鸟币余额
		walletParam.setCommision(liveWallet.getCommision()); // 鸟豆余额
		walletParam.setSellerCoin(liveWallet.getSellerCoin()); // 商家鸟币余额
		walletParam.setSign(LiveWalletUtil.generateLiveWalletSign(walletParam));

		// 扣除鸟币
		int deduResultCount = liveWalletMapper.updateZbalance(walletParam, liveWallet, deductZbalance);
		if (deduResultCount < 1)
			throw new CustomException("扣除鸟币失败!");
	}

	/**
	 * 插入直播钱包记录 更新数据 计算各种余额交易后的余额
	 * 
	 * @param beforeWallet
	 *            更新前的直播钱包
	 * @param liveWalletRecord
	 *            直播钱包记录Bean, 需包含扣除金额信息
	 */
	private void insertLiveWalletRecord(LiveWallet beforeWallet, LiveWalletRecord liveWalletRecord) {
		liveWalletRecord.setWalletId(beforeWallet.getId()); // 直播钱包id
		liveWalletRecord.setAnchorId(beforeWallet.getUid()); // 直播用户id

		BigDecimal hEggMoney = beforeWallet.getBalance().subtract(liveWalletRecord.getEggMoney());
		liveWalletRecord.setqEggMoney(beforeWallet.getBalance()); // 交易前鸟蛋余额
		liveWalletRecord.sethEggMoney(hEggMoney); // 交易后鸟蛋余额

		long hCoinMoney = beforeWallet.getCommision() - liveWalletRecord.getCoinMoney();
		liveWalletRecord.setqCoinMoney(beforeWallet.getCommision());// 交易前鸟豆余额
		liveWalletRecord.sethCoinMoney(hCoinMoney); // 交易后鸟豆余额

		BigDecimal hBeansMoney = beforeWallet.getZbalance().subtract(liveWalletRecord.getBeansMoney());
		liveWalletRecord.setqBeansMoney(beforeWallet.getZbalance());// 交易前鸟币余额
		liveWalletRecord.sethBeansMoney(hBeansMoney); // 交易后鸟币余额

		BigDecimal hSellerBeanMoney = beforeWallet.getSellerCoin().subtract(liveWalletRecord.getSellerBeansMoney());
		liveWalletRecord.sethSellerBeansMoney(beforeWallet.getSellerCoin());// 交易前商家鸟币余额
		liveWalletRecord.setqSellerBeansMoney(hSellerBeanMoney); // 交易后商家鸟币余额

		liveWalletRecord.setCreateTime(new Date()); // 创建时间
		liveWalletRecord.setUpdateTime(new Date()); // 更新时间

		// 向数据库插入直播交易记录
		logger.info("插入直播钱包更新记录(live_wallet_record)数据, 参数:" + liveWalletRecord);
		liveWalletRecordMapper.insertSelective(liveWalletRecord);
	}

	@Transactional
	@Override
	public Boolean liveGiftLedger(Map<String, String> param) {
		logger.info("开始消费主播礼物分账消息  :"+param);
		try {
			ParamUtil.checkMustParam(param, "uid", "balance");
			String source = param.get("source");
			String vkid = param.get("vkid");
			String amount = param.get("amount");
			String remark = param.get("uid");
			if (StringUtils.isNotBlank(source)) {
				Long l = liveWalletRecordMapper.countByRemarks(source);
				if (l > 0) {
					logger.error("[消费主播礼物分账消息已消费,莫重复消费]" + param);
					return true;
				}
			}

			if (StringUtils.isNotBlank(vkid) && StringUtils.isNotBlank(amount) && new BigDecimal(amount).compareTo(BigDecimal.ZERO)>0) {
				// 给V客入账
				ParamUtil.checkMustParam(param, "vkid", "amount");
				Map<String, String> exWalletParam = new HashMap<>();
				exWalletParam.put("uid", param.get("vkid"));
				exWalletParam.put("typeId","1");
				exWalletParam.put("type", String.valueOf(XmnConstants.EX_RTYPE_10));
				exWalletParam.put("option", "0");
				exWalletParam.put("amount", amount);
				exWalletParam.put("source", source);
				exWalletParam.put("remark", remark);
				Map<String, String> updateWalletExpansion = walletExpansionServiceImpl
						.updateWalletExpansion(exWalletParam);
				if (!ParamUtil.SUCCESS_STATE.toString().equals(updateWalletExpansion.get("state"))) {
					throw new FailureException();
				}
			}
			Map<String, String> liveParamMap = new HashMap<>();
			liveParamMap.put("uid", param.get("uid"));
			liveParamMap.put("rtype", String.valueOf(XmnConstants.RTYPE_5));
			liveParamMap.put("balance", param.get("balance"));
			liveParamMap.put("option", "0");
			liveParamMap.put("liveRecordId", param.get("liveRecordId"));
			ResponseData liveWalletOption = liveWalletServiceImpl.liveWalletOption(liveParamMap);
			if(!ParamUtil.SUCCESS_STATE.equals(liveWalletOption.getState())){
				throw new FailureException();
			}else{
				return true;
			}
		} catch (ParamBlankException e) {
			logger.info("[消费主播礼物分账消息参数异常]" + param, e);
			return false;
		} catch (TException e) {
			logger.error("[消费主播礼物分账消息出现异常]" + param, e);
			throw new RuntimeException("消费主播礼物分账消息出现异常,进行事务回滚");
		}
	}

	@Override
	public Boolean hasZbalanceLock(Integer id) {
		return liveWalletLockMapper.countZbalanceLock(id)>0;
	}

	@Override
	public int lockWallet(Integer updateOption, Integer lockOption, Integer walletId, Integer type) {
		switch (updateOption) {
		case 0:
			if(hasZbalanceLock(walletId)){
				return 1;
			}
			LiveWallet wallet = liveWalletMapper.getWalletById(walletId);
			return createLock(lockOption, walletId, type, wallet);
		case 1:
			return deleteLock(walletId, type);
		default:
			return 0;
		}
		
	}

	private int deleteLock(Integer walletId, Integer type) {
		liveWalletLockMapper.deleteByWalletIdType(walletId,type);
		return 1;
	}

	private int createLock(Integer lockOption, Integer walletId, Integer type, LiveWallet wallet) {
		LiveWalletLock liveWalletLock = new LiveWalletLock();
		liveWalletLock.setCreateTime(new Date());
		liveWalletLock.setLockAdd(lockOption==0);
		liveWalletLock.setLockSubtract(lockOption==1);
		liveWalletLock.setqBeansMoney(wallet.getZbalance());
		liveWalletLock.setqCoinMoney(wallet.getCommision());
		liveWalletLock.setqEggMoney(wallet.getBalance());
		liveWalletLock.setqSellerBeansMoney(wallet.getSellerCoin());
		liveWalletLock.setType(type.byteValue());
		liveWalletLock.setUpdateTime(new Date());
		liveWalletLock.setWalletId(walletId);
		return liveWalletLockMapper.insert(liveWalletLock);
	}

	@Override
	@Transactional(rollbackFor = { FailureException.class, Exception.class,RuntimeException.class })
	public ResponseData liveWalletOption(Map<String, String> paraMap) throws FailureException {
		ResponseData responseData = new ResponseData();
		Map<String,String> resultMap = new HashMap<>();
			//验证参数 uid,option,rtype不能为空
			if (StringUtils.isBlank(paraMap.get("uid")) || StringUtils.isBlank(paraMap.get("option")) || StringUtils.isBlank(paraMap.get("rtype"))) {
				responseData.setState(2);
				responseData.setMsg("传入参数有误，部分参数为空");
				return responseData;
			}

			//获取用户直播钱包
			Map<String, Object> liveWallet = liveWalletMapper
					.getLiveWallet(paraMap);

			if (liveWallet == null) {
				logger.error("用户没有直播钱包" + liveWallet);
				responseData.setState(2);
				responseData.setMsg("用户没有直播钱包");
				return responseData;
			}

			// 验证钱包状态
			int status = (int) liveWallet.get("status");
			if (1 != status) {
				logger.error("该直播钱包已被锁定或注销" + liveWallet);
				responseData.setState(1);
				responseData.setMsg("该直播钱包" + liveWallet.get("id") + "已被锁定或注销");
				return responseData;
			}

			// 验证钱包签名
			if (!verifySign(liveWallet)) {
				logger.error("用户直播钱包签名异常" + liveWallet);
				responseData.setState(2);
				responseData.setMsg("用户直播钱包签名异常");
				return responseData;
			}

			// 验证订单
			if (StringUtils.isNotBlank(paraMap.get("remarks"))) {
				Long countRemarks = liveWalletMapper
						.countRemarks(paraMap.get("remarks"),liveWallet.get("id").toString(),paraMap.get("rtype"));
				if (countRemarks >0) {
					logger.info("订单" + paraMap.get("remarks") + "已更新成功成功，请勿重复提交");
					responseData.setState(0);
					responseData.setMsg("更新成功");
					return responseData;
				}
			}

			Map<String,String> walletMap = new HashMap<>();
			Map<String,Object> walletRecordMap = new HashMap<>();

			BigDecimal balance = new BigDecimal(paraMap.get("balance")==null?"0.00":paraMap.get("balance"));//鸟蛋
			BigDecimal zbalance = new BigDecimal(paraMap.get("zbalance")==null?"0.00":paraMap.get("zbalance"));//鸟币
			BigDecimal commision = new BigDecimal(paraMap.get("commision")==null?"0.00":paraMap.get("commision"));//鸟豆
			BigDecimal sellerCoin = new BigDecimal(paraMap.get("sellerCoin")==null?"0.00":paraMap.get("sellerCoin"));//商家专享鸟币

			BigDecimal qbalance = (BigDecimal)liveWallet.get("balance");//更新前鸟蛋余额
			BigDecimal qzbalance = (BigDecimal)liveWallet.get("zbalance");//更新前鸟币余额
			BigDecimal qcommision = (BigDecimal)liveWallet.get("commision");//更新前鸟豆余额
			BigDecimal qsellerCoin = (BigDecimal)liveWallet.get("sellerCoin");//更新前鸟豆余额

			BigDecimal hbalance ;//更新后鸟蛋余额
			BigDecimal hzbalance ;//更新后鸟币余额
			BigDecimal hcommision;//更新后鸟豆余额
			BigDecimal hsellerCoin;//更新后商家专享鸟币余额
			BigDecimal turnOutBalance = (BigDecimal)liveWallet.get("turnEggOut");//转出鸟蛋总额
			BigDecimal turnOutCoin = (BigDecimal)liveWallet.get("turnCoinOut");//转出鸟豆总额
//			BigDecimal totalZbalance = (BigDecimal) liveWallet.get("cumulativeZbalance");// 累加鸟币
			//加 操作
			if("0".equals(paraMap.get("option"))){

				/**
				 * 鸟蛋累加：单位时间内主播获得鸟蛋不得超过限额
				 */
//				if(balance.compareTo(BigDecimal.ZERO)>0){
//					Map<String,String> tMap = new HashMap<>();
//					tMap.put("walletId",liveWallet.get("id").toString());
////					tMap.put("timeUnit",getDate((timeUnit-1)*-1));
//					tMap.put("date",new SimpleDateFormat("yyyy-MM").format(new Date()));
//					BigDecimal unitTimeEgg = liveWalletMapper.countUnitTimeEgg(tMap);
//					//判断是否超过限额
//					BigDecimal leftEgg = birdEggLimit.subtract(unitTimeEgg);
//					if(leftEgg.compareTo(BigDecimal.ZERO)>0){
//						if(leftEgg.compareTo(balance)<0){
//							log.info("本次更新超出主播单位时间鸟蛋收入额度，实际更新："+leftEgg+" ,请求参数："+paraMap);
//							balance = leftEgg;
//						}
//					}else {
//						log.info("单位时间内主播鸟蛋收入已达限定额度，不再累加。参数："+paraMap);
//						responseData.setState(0);
//						responseData.setMsg("更新数据成功");
//						return responseData;
//					}
//				}

				hbalance = balance.add(qbalance);
				hzbalance = zbalance.add(qzbalance);
				hcommision = commision.add(qcommision);
				hsellerCoin = sellerCoin.add(qsellerCoin);
				walletMap.put("availableExchangeCoin", paraMap.get("availableCoin"));
				//减操作
			}else if("1".equals(paraMap.get("option"))){

				/**
				 *鸟币消费余额限制
				 */

				if(zbalance.compareTo(BigDecimal.ZERO)>0){
					Object restrictive = liveWallet.get("restrictive");//鸟币消费余额限制
					if("002".equals(restrictive)){//开启限制
						BigDecimal limitBalance = new BigDecimal(liveWallet.get("limitBalance").toString());//限额
						if(limitBalance.compareTo(BigDecimal.ZERO)>0&&(qzbalance.subtract(zbalance)).compareTo(limitBalance)<0){//超过限额
							logger.error("本次鸟币消费超过限额:"+limitBalance+"鸟币余额："+qzbalance+"，扣减鸟币失败"+paraMap);
							responseData.setState(1);
							responseData.setMsg("本次鸟币消费超过限额，扣减鸟币失败");
							return responseData;
						}
					}
				}

				walletMap.put("turnEggOut", turnOutBalance.add(balance).toString());
				walletMap.put("turnCoinOut", turnOutCoin.add(commision).toString());

				hbalance = qbalance.subtract(balance);
				hzbalance = qzbalance.subtract(zbalance);
				hcommision = qcommision.subtract(commision);
				hsellerCoin = qsellerCoin.subtract(sellerCoin);

				if(hbalance.compareTo(BigDecimal.ZERO)<0||hzbalance.compareTo(BigDecimal.ZERO)<0
						||hcommision.compareTo(BigDecimal.ZERO)<0||hsellerCoin.compareTo(BigDecimal.ZERO)<0){
					logger.error("扣减失败，余额不足");
					responseData.setState(1);
					responseData.setMsg("扣减失败，余额不足");
					return responseData;
				}
				walletMap.put("usedExchangeCoin", paraMap.get("usedCoin"));
			}else{
				logger.error("传入参数有误，option只能为0或1");
				responseData.setState(2);
				responseData.setMsg("传入参数有误，option只能为0或1");
				return responseData;
			}

			walletMap.put("uid",paraMap.get("uid"));
			walletMap.put("balance",hbalance.toString());
			walletMap.put("commision",hcommision.toString());
			walletMap.put("zbalance",hzbalance.toString());
			walletMap.put("sellerCoin",hsellerCoin.toString());
			walletMap.put("sign",commonService.LiveWalletSign(walletMap));//生成新签名
			walletMap.put("oldSign",liveWallet.get("sign").toString());
			walletMap.put("updateTime", DateUtil.getNow(DateUtil.Y_M_D_HMS));
			// 更新钱包数据
			int result = liveWalletMapper.updateWallet(walletMap);
			if (result != 1) {
				logger.error("更新钱包数据失败");
				throw new FailureException(1, "更新钱包数据失败");
			}

			walletRecordMap.put("eggMoney", balance);
			walletRecordMap.put("qeggMoney", qbalance);
			walletRecordMap.put("heggMoney", hbalance);
			walletRecordMap.put("coinMoney", commision);
			walletRecordMap.put("qcoinMoney", qcommision);
			walletRecordMap.put("hcoinMoney", hcommision);
			walletRecordMap.put("beansMoney", zbalance);
			walletRecordMap.put("qbeansMoney", qzbalance);
			walletRecordMap.put("hbeansMoney", hzbalance);
			walletRecordMap.put("sellerCoin", sellerCoin);
			walletRecordMap.put("qsellerCoin", qsellerCoin);
			walletRecordMap.put("hsellerCoin", hsellerCoin);
			walletRecordMap.put("createTime",DateUtil.getNow(DateUtil.Y_M_D_HMS));
			walletRecordMap.put("walletId", liveWallet.get("id"));
			walletRecordMap.put("liveRecordId", paraMap.get("liveRecordId"));
			walletRecordMap.put("anchorId", paraMap.get("anchorId"));
			walletRecordMap.put("rtype", paraMap.get("rtype"));
			walletRecordMap.put("remarks",paraMap.get("remarks"));
			walletRecordMap.put("description",paraMap.get("description"));

			//插入钱包更新记录
			liveWalletMapper.insertWalletRecord(walletRecordMap);
			resultMap.put("id",walletRecordMap.get("id").toString());
			responseData.setState(0);
			responseData.setMsg("更新成功");
			responseData.setResultMap(resultMap);
			return responseData;
	}
	
	
	// 验证钱包签名信息
		public boolean verifySign(Map<String, Object> walletMap) {
			logger.info("verifySign:" + walletMap);
			Map<String, String> signMap = new HashMap<>();
			signMap.put("uid", walletMap.get("uid").toString());
			signMap.put("balance", walletMap.get("balance").toString());
			signMap.put("zbalance", walletMap.get("zbalance").toString());
			signMap.put("commision", walletMap.get("commision").toString());
			signMap.put("sellerCoin", walletMap.get("sellerCoin").toString());
			String sign = commonService.LiveWalletSign(signMap);
			return sign.equals(walletMap.get("sign"));
		}
	
}
