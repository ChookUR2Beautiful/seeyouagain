package com.xmniao.service.pay;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xmniao.common.MapUtil;
import com.xmniao.common.StateCodeUtil;
import com.xmniao.common.XmnConstants;
import com.xmniao.dao.WalletMapper;
import com.xmniao.dao.WalletRecordMapper;
import com.xmniao.dao.XmerWallerMapper;
import com.xmniao.service.CommonService;
import com.xmniao.thrift.ledger.FailureException;
import com.xmniao.thrift.ledger.ResponseData;
import com.xmniao.thrift.ledger.XmerWalletService;

/**
 * 
 * 
 * 项目名称：payService
 * 
 * 类名称：XmerWalletServiceImpl
 * 
 * 类描述： 寻蜜客电子钱包服务Thrift接口实现类
 * 
 * 创建人： Chen Bo
 * 
 * 创建时间：2016年7月11日 下午5:33:44
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */

@Service("XmerWalletServiceImpl")
public class XmerWalletServiceImpl implements XmerWalletService.Iface {

	/**
	 * 日志记录
	 */
	private final Logger log = Logger.getLogger(XmerWalletServiceImpl.class);

	@Autowired
	private XmerWallerMapper xmWallerMapper;

	@Autowired
	private WalletMapper walletMapper;

	@Autowired
	private WalletRecordMapper walletRecordMapper;

	@Autowired
	private CommonService commonService;
	
	/**
	 * 成为寻客后，添加寻觅客钱包
	 * 
	 */
	@Override
	@Transactional(rollbackFor = { FailureException.class, Exception.class })
	public ResponseData addXmerWallet(Map<String, String> walletMap)
			throws FailureException, TException {

		log.info("添加寻觅客钱包addXmerWallet:"+walletMap);
		ResponseData responseData = new ResponseData();
		Map<String, String> resultMap = new HashMap<>();

		try {
			String uid = walletMap.get("uid");// 寻蜜客id
			// 验证参数
			if (StringUtils.isNotBlank(uid)) {
					if(StringUtils.isBlank(walletMap.get("uname"))){
						walletMap.put("uname","");
					}
				// 验证该寻蜜客是否已经有寻蜜客钱包
				Map<String, String> wMap = xmWallerMapper
						.selectXmerWallet(walletMap);
				if (wMap != null) {
					log.info("用户"+uid+"已拥有寻蜜客钱包");
					responseData.setState(0);
					resultMap.put("id", wMap.get("id"));
					responseData.setResultMap(resultMap);
					return responseData;
				}

				String fatherId = walletMap.get("fatherId");// 寻蜜客对应会员钱包id
				if (StringUtils.isBlank(fatherId)) {
					Map<String, Object> map = xmWallerMapper
							.getWalletByuid(uid);
					fatherId = map.get("accountid") + "";
					walletMap.put("fatherId", fatherId);
				}
				walletMap.put("applyDate",getFormatDate());

				Map<String, String> signMap = new HashMap<>();
				signMap.put("uid", uid);
				signMap.put("profit", "0");
				signMap.put("trunout", "0");
				// 生成签名
				String sign = commonService.XmerWalletSign(signMap);
				walletMap.put("sign", sign);
				walletMap.put("lastDate", getFormatDate());

				xmWallerMapper.addXmerWallet(walletMap);
				log.info("添加钱包成功");
				resultMap.put("id", walletMap.get("id"));
				responseData.setResultMap(resultMap);
				return responseData;
			} else {
				log.info("参数uid为空");
				responseData.setState(2);
				responseData.setMsg("参数uid为空");
				return responseData;
			}
		} catch (Exception e) {
			log.error("添加钱包账号异常", e);
			throw new FailureException(1, "添加寻蜜客钱包失败");
		}
	}

	/**
	 * 锁定或解锁寻蜜客钱包
	 */
	@Override
	@Transactional(rollbackFor = { FailureException.class, Exception.class })
	public ResponseData lockXmerWallet(Map<String, String> walletMap)
			throws FailureException, TException {
		log.info("锁定或解锁寻蜜客钱包"+walletMap);
		ResponseData responseData = new ResponseData();
		Map<String, String> resultMap = new HashMap<>();
		try {
			if (StringUtils.isNotBlank(walletMap.get("state"))) {
				//id和uid不同时为空
				if (StringUtils.isNotBlank(walletMap.get("id")) || StringUtils.isNotBlank(walletMap.get("uid"))) {
					
					Map<String, String> xmerWallet = xmWallerMapper.selectXmerWallet(walletMap);
					if(xmerWallet == null){
						log.error("该用户不存在寻蜜客钱包"+walletMap);
						responseData.setState(1);
						responseData.setMsg("该用户不存在寻蜜客钱包");
						return responseData;
					}
					walletMap.put("sign",xmerWallet.get("sign"));
					Integer result = xmWallerMapper.updateWalletstate(walletMap);
					if(result != 1){
						log.error("锁定寻蜜客钱包失败");
						throw new FailureException(1,"锁定寻蜜客钱包失败");
					}
				} else {
					log.info("锁定或解锁寻蜜客钱包失败,寻蜜客钱包id和寻蜜客uid同时为空");
					responseData.setState(2);
					responseData.setMsg("寻蜜客钱包id和寻蜜客uid同时为空");
					return responseData;
				}
				
				log.info("锁定或解锁寻蜜客钱包成功");
				responseData.setState(0);
				resultMap.put("state", walletMap.get("state"));
				responseData.setResultMap(resultMap);
				return responseData;
			} else {
				log.info("锁定或解锁寻蜜客钱包失败");
				responseData.setState(2);
				responseData.setMsg("操作状态为空");
				return responseData;
			}
		} catch (Exception e) {
			log.error("锁定或解锁寻蜜客钱包异常", e);
			throw new FailureException(1, "锁定或解锁寻蜜客钱包失败"); 
		}
	}

	/**
	 * 查询寻蜜客钱包信息
	 */
	@Override
	public ResponseData getXmerWallet(Map<String, String> walletMap)
			throws FailureException, TException {
		log.info("查询寻蜜客钱包信息getXmerWallet"+walletMap);
		ResponseData responseData = new ResponseData();
		Map<String, String> resultMap = new HashMap<>();
		try {
			if (!(StringUtils.isBlank(walletMap.get("id")) && StringUtils
					.isBlank("uid"))) {
				Map<String, String> wallet = xmWallerMapper
						.selectXmerWallet(walletMap);
				
				if(wallet == null){
					log.error("未查询到寻蜜客钱包"+walletMap);
					responseData.setState(1);
					responseData.setMsg("该寻蜜客没有钱包");
					return responseData;
				}
				resultMap.put("id", wallet.get("id"));
				resultMap.put("state", wallet.get("status"));
				resultMap.put("profit", wallet.get("profit"));
				resultMap.put("trunout", wallet.get("trunout"));
				resultMap.put("uname", wallet.get("uname"));
				resultMap.put("fatherId", wallet.get("fatherId"));

				responseData.setState(0);
				responseData.setResultMap(resultMap);
				return responseData;
			} else {
				log.info("查询寻蜜客钱包信息失败");
				responseData.setState(2);
				responseData.setMsg("寻蜜客钱包id和寻蜜客用户id同时为空");
				return responseData;
			}
		} catch (Exception e) {
			log.error("查询寻蜜客钱包信息失败", e);
			responseData.setState(1);
			responseData.setMsg("查询寻蜜客钱包信息失败");
			return responseData;
		}
	}

	/**
	 * 查询寻蜜客钱包的状态
	 */
	@Override
	public ResponseData getXmerWalletState(Map<String, String> walletMap)
			throws FailureException, TException {
		log.info("查询寻蜜客钱包的状态"+walletMap);
		ResponseData responseData = new ResponseData();
		Map<String, String> resultMap = new HashMap<>();

		try {
			if (!(StringUtils.isBlank(walletMap.get("id")) && StringUtils
					.isBlank("uid"))) {
				Map<String, String> map = xmWallerMapper
						.selectXmerWallet(walletMap);
				resultMap.put("state", map.get("status"));
			} else {
				log.info("查询寻蜜客钱包的状态失败");
				responseData.setState(2);
				responseData.setMsg("寻蜜客钱包id和寻蜜客用户id同时为空");
				return responseData;
			}
			log.info("查询寻蜜客钱包的状态成功");
			responseData.setState(0);
			responseData.setResultMap(resultMap);
			return responseData;
		} catch (Exception e) {
			log.error("查询寻蜜客钱包的状态失败", e);
			responseData.setState(1);
			responseData.setMsg("查询寻蜜客钱包的状态失败");
			return responseData;
		}
	}

	/**
	 * 将寻蜜客钱包中的余额转出到对应寻蜜鸟会员的电子钱包账号中
	 */
	@Override
	@Transactional(rollbackFor = { FailureException.class, Exception.class })
	public ResponseData turnOutXmerWallet(Map<String, String> walletMap)
			throws FailureException, TException {
		log.info("寻蜜客钱包余额转出turnOutXmerWallet："+walletMap);
		ResponseData responseData = new ResponseData();
		Map<String, String> resultMap = new HashMap<>();
		Map<String, String> signMap = new HashMap<>();
		BigDecimal profit = new BigDecimal(0.00);//0.00
		try {
			//id和uid不可同时为空
			if (StringUtils.isNotBlank(walletMap.get("id")) || StringUtils
					.isNotBlank(walletMap.get("uid"))) {
				
				resultMap = xmWallerMapper.selectXmerWallet(walletMap);
				
				if(resultMap == null){
					log.info("用户"+walletMap.get("uid")+"没有寻蜜客钱包");
					responseData.setState(1);
					responseData.setMsg("该用户没有寻蜜客钱包");
					return responseData;
				}
				
				// 验证钱包签名
				signMap.put("uid", resultMap.get("uid"));
				signMap.put("profit", resultMap.get("profit"));
				signMap.put("trunout", resultMap.get("trunout"));
				String sign = commonService.XmerWalletSign(signMap);
				if (!sign.equals(resultMap.get("sign"))) {
					log.info("钱包签名验证失败");
					responseData.setState(1);
					responseData.setMsg("钱包签名验证失败");
					return responseData;
				}
				// 判断钱包使用状态：1 正常 2 锁定 3 注销
				if ("1".equals(resultMap.get("status"))) {
					//判断是否全部转出 0 否  1 是
					//全部转出
					if (StringUtils.isNotBlank(walletMap.get("turnOutAll"))
							&& "1".equals(walletMap.get("turnOutAll"))) {

						String pre_profit = resultMap.get("profit");// 转出前收益余额
						String pre_trunout = resultMap.get("trunout");// 转出前转出总额
						BigDecimal h_turnout = new BigDecimal(pre_profit).add(new BigDecimal(pre_trunout));//转出后转出总额
						Map<String, String> map = new HashMap<>();
						Map<String, String> recordMap = new HashMap<>();// 钱包记录参数map

						recordMap.put("xid", resultMap.get("id"));
						recordMap.put("profit", resultMap.get("profit"));
						recordMap.put("qprofit", pre_profit);
						recordMap.put("hprofit", "0");
						recordMap.put("rtype", "1");
						recordMap.put("sdate",getFormatDate());

						map.put("uid", resultMap.get("uid"));
						map.put("profit", "0");
						map.put("trunout",h_turnout.toString());
						String sign2 = commonService.XmerWalletSign(map);
						map.put("sign", sign2);// 签名
						map.put("oldSign",resultMap.get("sign"));
						map.put("id", resultMap.get("id"));
						map.put("lastDate",getFormatDate());
						/**
						 * 修改寻蜜客钱包和对应的会员钱包数据并添加修改记录
						 */
						xmerWalletOption(map, recordMap);
					
					//部分转出
					} else if (StringUtils.isNotBlank(walletMap.get("money"))) {
						if (Double.valueOf(walletMap.get("money")) > Double
								.valueOf(resultMap.get("profit"))) {
							log.info("余额转出失败");
							responseData.setState(2);
							responseData.setMsg("余额不足");
							return responseData;
						}
						BigDecimal money = new BigDecimal(walletMap.get("money"));//转出金额
						profit = new BigDecimal(resultMap.get("profit"));
						profit = profit.subtract(money);
						String pre_profit = resultMap.get("profit");// 转出前收益余额
						String pre_trunout = resultMap.get("trunout");// 转出前转出总额
						Map<String, String> map = new HashMap<>();
						Map<String, String> recordMap = new HashMap<>();// 钱包记录参数map

						recordMap.put("xid", resultMap.get("id"));
						recordMap.put("profit", walletMap.get("money"));
						recordMap.put("qprofit", pre_profit);

						recordMap.put("hprofit", profit.toString());
						recordMap.put("rtype", "1");
						recordMap.put("sdate",getFormatDate());

						map.put("uid", resultMap.get("uid"));
						map.put("profit", profit.toString());
						map.put("trunout",
								(Double.valueOf(walletMap.get("money")) + Double
										.valueOf(pre_trunout)) + "");
						String sign2 = commonService.XmerWalletSign(map);
						map.put("oldSign",resultMap.get("sign"));
						map.put("sign", sign2);// 签名
						map.put("id", resultMap.get("id"));
						map.put("lastDate",getFormatDate());
						/**
						 * 修改寻蜜客钱包和对应的会员钱包数据并添加修改记录
						 */
						xmerWalletOption(map, recordMap);
					} else {
						log.info("余额转出失败");
						responseData.setState(2);
						responseData.setMsg("出入转出金额参数错误");
						return responseData;
					}
				} else {
					log.info("余额转出失败");
					responseData.setState(1);
					responseData.setMsg("该钱包已被锁定");
					return responseData;
				}

			} else {
				log.info("余额转出失败");
				responseData.setState(2);
				responseData.setMsg("寻蜜客钱包id和寻蜜客用户id同时为空");
				return responseData;
			}
			log.info("余额转出转出成功");
			Map<String, String> map1 = new HashMap<>();
			map1.put("id", resultMap.get("id"));
			map1.put("profit", profit.toString());
			responseData.setState(0);
			responseData.setResultMap(map1);
			return responseData;
		} catch (Exception e) {
			log.error("余额转出失败", e);
			throw new FailureException(1, "余额转出失败失败");
		}
	}

	/**
	 * 修改寻蜜客钱包和对应的会员钱包数据并添加修改记录
	 * 
	 * @param walletMap
	 * @param recordMap
	 * @throws Exception
	 */
	
	private void xmerWalletOption(Map<String, String> walletMap,
			Map<String, String> recordMap) throws Exception {
		// 更新钱包数据
		try {
			log.info("要更新的钱包数据："+walletMap);
			Integer temp = xmWallerMapper.updateWalletById(walletMap);
			if(1 !=temp){
				log.info("更新钱包数据失败"+walletMap);
				throw new FailureException(1, "更新钱包金额失败");
			}

			// 插入更新记录
			xmWallerMapper.insertXmerWalletRecord(recordMap);
			
			//更新会员钱包并插入记录
			Map<String, Object> map = new HashMap<>();
			map.put("uId", walletMap.get("uid"));
			map.put("typeId", 1);
			Map<String, Object> wallet = walletMapper.selectByuid(map);// 查询用户钱包信息
			
			//寻蜜客转出金额转回会员钱包佣金余额
			Map<String, Object> map2 = new HashMap<>();
			BigDecimal profit = new BigDecimal(recordMap.get("profit"));
			BigDecimal money = new BigDecimal(wallet.get("commision")
					.toString());
			BigDecimal commision = profit.add(money);
			map2.put("uid",wallet.get("uid"));
			map2.put("sellerid",wallet.get("sellerid"));
			map2.put("jointid",wallet.get("jointid"));
			map2.put("pwd",null==wallet.get("pwd")?"":wallet.get("pwd"));
			map2.put("amount",wallet.get("amount"));
			map2.put("balance",wallet.get("balance"));
			map2.put("zbalance",wallet.get("zbalance"));
			map2.put("integral",wallet.get("integral"));
			map2.put("commision",commision.toString());//佣金余额
			map2.put("sellerAmount",wallet.get("seller_amount"));
			String sign = commonService.walletSign(MapUtil
					.Object2String(map2));
			map2.put("sign", sign);
			map2.put("lastDate", getFormatDate());
			map2.put("accountid", wallet.get("accountid"));
			map2.put("oldSign", wallet.get("sign"));
			// 更新钱包数据
			log.info("更新会员钱包数据："+map2);
			int result=walletMapper.updateWalletMoneyFromXmer(map2);
			if(result!=1){
				log.info("余额转出失败");
				throw new FailureException(1, "余额转出失败");
			}

			// 会员钱包修改记录
			Map<String, Object> walletRecordMap = new HashMap<>();
			walletRecordMap.put("accountid", wallet.get("accountid"));
			walletRecordMap.put("rtype", XmnConstants.RTYPE_32);
			walletRecordMap.put("commision", recordMap.get("profit"));
			walletRecordMap.put("qcommision", wallet.get("commision")
					.toString());
			walletRecordMap.put("hcommision", commision.toString());
			walletRecordMap.put("sdate",getFormatDate());
			walletRecordMap.put("remarks",recordMap.get("id"));
			// 添加会员钱包修改记录
			 walletRecordMapper.addWalletRecord(walletRecordMap);
			 
		} catch (Exception e) {
			log.info("寻蜜客余额转出失败");
			throw new FailureException(1, "余额转出失败失败"+e);
		}
	}
	
	/**
	 * 分账退回
	 * @param map
	 */
	public Map<String,String> ledgerRefund(Map<String,Object> map) throws FailureException{
		log.info("ledgerRefund:"+map);
		//验证参数
		if(map.get("id") == null){
			log.error("根据id查询寻蜜客钱包时id为空");
			return returnMap(StateCodeUtil.PR_SYSTEM_ERROR,"根据id查询寻蜜客钱包时id为空");
		}
		Map<String, Object> wallet = xmWallerMapper.selectXmerWalletById(map.get("id")+"");
		
		if (wallet == null) {
			// 找不到钱包
			log.error("找不到对应钱包！");
			return returnMap(StateCodeUtil.PR_WALLET_ERROR, "找不到对应钱包！");
		}
		//验证钱包状态
		if("1".equals(wallet.get("status"))){
			log.error(map.get("id")+"寻蜜客钱包状态异常");
			return returnMap(StateCodeUtil.PR_SYSTEM_ERROR,map.get("id")+"寻蜜客钱包状态异常");
		}
		//验证钱包签名
		if(!verifySign(wallet)){
			log.error(wallet.get("id")+"钱包签名验证失败");
			return returnMap(StateCodeUtil.PR_SYSTEM_ERROR,wallet.get("id")+"钱包签名验证失败");
		}
		
		BigDecimal profit = (BigDecimal) map.get("profit");//需扣除的金额
		BigDecimal qprofit = (BigDecimal)wallet.get("profit");//扣除前
		
		//钱包余额不足，扣回失败
		/*if(-1 == qprofit.compareTo(profit)){
			log.error(wallet.get("id")+"钱包余额不足");
			return returnMap(StateCodeUtil.PR_SYSTEM_ERROR,"钱包余额不足");
		}*/
		
		BigDecimal hprofit = qprofit.subtract(profit);//扣除后
		
		Map<String,String> updateMap = new HashMap<>();
		updateMap.put("uid",wallet.get("uid")+"");
		updateMap.put("profit",hprofit.toString());
		updateMap.put("trunout",wallet.get("trunout")+"");
		updateMap.put("oldSign",wallet.get("sign")+"");
		//生成新的签名
		String newSign = commonService.XmerWalletSign(updateMap);
		updateMap.put("sign",newSign);
		updateMap.put("id",wallet.get("id")+"");
		
		//更新钱包数据
		Integer result = xmWallerMapper.updateWalletById(updateMap);
		if(result!=1){
			log.error("更新钱包数据失败");
			return returnMap(StateCodeUtil.PR_SYSTEM_ERROR,"更新钱包数据失败");
		}
		
		//添加更新记录
		Map<String,String> recordMap = new HashMap<>();
		recordMap.put("xid",wallet.get("id")+"");
		recordMap.put("rtype",map.get("rtype")+"");
		recordMap.put("profit",profit.toString());
		recordMap.put("qprofit",qprofit.toString());
		recordMap.put("hprofit",hprofit.toString());
		recordMap.put("sdate",getFormatDate());
		recordMap.put("remark",map.get("remark")+"");
		recordMap.put("description",map.get("description")+"");
		
		//插入钱包更新记录
		xmWallerMapper.insertXmerWalletRecord(recordMap);
		
		return returnMap(StateCodeUtil.PR_SUCCESS,"更新寻蜜客钱包数据成功");
	}
	
	public Map<String, String> returnMap(String code, String msg) {
		Map<String,String> map = new HashMap<>();
		map.put("code",code);
		map.put("msg",msg);
		return map;
	}
	
	//验证钱包签名信息
	public boolean verifySign(Map<String,Object> map){
		log.info("verifySign:"+map);
		Map<String,String> signMap = new HashMap<>();
		signMap.put("uid",map.get("uid").toString());
		signMap.put("profit",map.get("profit").toString());
		signMap.put("trunout",map.get("trunout").toString());
		String sign = commonService.XmerWalletSign(signMap);
		return sign.equals(map.get("sign"));
	}
	
	//生成格式化日期（yyyy-MM-dd HH:mm:ss）
	public String getFormatDate(){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}
}
