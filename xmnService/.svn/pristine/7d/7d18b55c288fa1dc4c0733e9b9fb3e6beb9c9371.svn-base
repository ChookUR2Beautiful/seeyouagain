/**
 * 2016年8月17日 下午2:19:15
 */
package com.xmniao.xmn.core.live.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.Constant;
import com.xmniao.xmn.core.common.ConstantDictionary;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.PageRequest;
import com.xmniao.xmn.core.common.request.live.LiveBuyBirdCoinCancleIOSRequest;
import com.xmniao.xmn.core.common.request.live.LiveBuyBirdCoinIOSRequest;
import com.xmniao.xmn.core.common.request.live.LiveBuyBirdCoinOrderIOSRequest;
import com.xmniao.xmn.core.common.request.live.LiveBuyBirdCoinRequest;
import com.xmniao.xmn.core.common.request.live.UserPayBirdCoinRequest;
import com.xmniao.xmn.core.live.dao.LiveUserDao;
import com.xmniao.xmn.core.live.dao.UserPayBirdCoinDao;
import com.xmniao.xmn.core.live.entity.LivePayOrderInfo;
import com.xmniao.xmn.core.live.entity.LiveRechargeComboInfo;
import com.xmniao.xmn.core.live.entity.UrsEarningsRelationInfo;
import com.xmniao.xmn.core.seller.dao.DebitcardSellerDao;
import com.xmniao.xmn.core.seller.entity.DebitcardSeller;
import com.xmniao.xmn.core.seller.entity.Seller;
import com.xmniao.xmn.core.thrift.LiveOrderService;
import com.xmniao.xmn.core.thrift.LiveOrderService.Client;
import com.xmniao.xmn.core.thrift.LiveWalletService;
import com.xmniao.xmn.core.thrift.ResponseData;
import com.xmniao.xmn.core.thrift.ResponseList;
import com.xmniao.xmn.core.thrift.WalletRecord;
import com.xmniao.xmn.core.util.DateUtil;
import com.xmniao.xmn.core.util.EnumUtil;
import com.xmniao.xmn.core.util.HttpConnectionUtil;
import com.xmniao.xmn.core.util.PropertiesUtil;
import com.xmniao.xmn.core.util.SaasBidType;
import com.xmniao.xmn.core.util.Signature;
import com.xmniao.xmn.core.util.ThriftBusinessUtil;
import com.xmniao.xmn.core.util.ThriftUtil;
import com.xmniao.xmn.core.xmer.dao.SellerDao;
import com.xmniao.xmn.core.xmer.service.SellerService;

import net.sf.oval.exception.ConstraintSetAlreadyDefinedException;

/**
 * @项目名称：xmnService_3.1.2_zb
 * @类名称：UserPayBirdCoinService
 * @类描述：用户购买鸟币服务类
 * @创建人： yeyu
 * @创建时间 2016年8月17日 下午2:19:15
 * @version
 */
@Service
public class UserPayBirdCoinService {
	
	private Logger log=(Logger) Logger.getLogger(UserPayBirdCoinService.class);
	
	@Autowired
	private UserPayBirdCoinDao userpaybirdcoinDao;
	
	@Autowired
	private PersonalCenterService personalcenterService;
	
	@Autowired
	private PropertiesUtil  propertiesUtil;
	
	@Autowired
	private EggToBirdMoneyService eggToBirdMoneyService;
	
	@Autowired
	private LiveGiftsInfoService liveGiftsInfoService;
	
	@Autowired
	private LiveUserDao liveUserDao;
	
	@Autowired
	private SellerService sellerService;
	
	@Autowired
	private SellerDao sellerDao;
	
	@Autowired
	private DebitcardSellerDao debitcardSellerDao;
	
	@Autowired
	private LiveBuyFansCouponService liveBuyFansCouponService;
	
	/**
	 * 注入redis缓存
	 */
	@Autowired
	private SessionTokenService sessionTokenService;
	
	/**
	 * 注入
	 */
	@Autowired
	private ThriftUtil thriftUtil;
	
	/**
	 * 注入
	 */
	@Autowired
	private ThriftBusinessUtil thriftBusinessUtil;
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	/**
	 * 描述：获取购买鸟币的套餐列表
	 * @param String system_type (1 IOS | 2 ANDROID)
	 * @return Object
	 * 2016-8-29 18:02:52
	 * */
	public Object queryBirdCoinComboList(LiveBuyBirdCoinRequest birdCoinRequest){
		
		//验证token
//		String uid = birdCoinRequest.getUid().toString();
		String uid = sessionTokenService.getStringForValue(birdCoinRequest.getSessiontoken())+"";
		if (StringUtils.isEmpty(uid)) {
			return new BaseResponse(ResponseCode.TOKENERR, "token已经失效,请重新登录");
		}
		MapResponse response = null;
		try {
			//获取用户
//			LiverInfo liverInfo = liveUserDao.queryLiverByUid(Long.valueOf(uid));
			
			Map<Object, Object> comMap = new HashMap<Object, Object>();
			
			//获取鸟币购买的套餐   需要匹配用户的粉丝等级
			Map<Object, Object> comboMap = new HashMap<Object, Object>();
			comboMap.put("systemType", birdCoinRequest.getSystemType());
			comboMap.put("source", birdCoinRequest.getSource());
			comboMap.put("uid", uid);
			comboMap.put("limit", 1);
			comboMap.put("page", 1);
			if (birdCoinRequest.getIsCard() ==0) {
				comboMap.put("objectOriented", 1);
			}		
			String isBuyBirdCoinOpeningDate = propertiesUtil.getValue("isBuyBirdCoinOpeningDate", "conf_live.properties");
			String buyBirdCoinOpeningDate = propertiesUtil.getValue("buyBirdCoinOpeningDate", "conf_live.properties");
			comboMap.put("buyBirdCoinOpeningDate", buyBirdCoinOpeningDate);//排除以往旧充值记录
			comboMap.put("isBuyBirdCoinOpeningDate", isBuyBirdCoinOpeningDate);//
			
			//根据用户ID获取他充值过的年都记录   排除小于最大充值记录
			if (isBuyBirdCoinOpeningDate.equals("1")) {//等于 1 表示开启用户等级加载鸟币套餐
				Map<Object, Object> payOrderMap = userpaybirdcoinDao.queryBirdCoinBuyRecords(comboMap);
				if(payOrderMap!=null) {
					comboMap.put("amount", payOrderMap.get("payment"));
				}else {
					comboMap.put("amount", 0);
				}
			}
			
//			Map<Object, Object> payOrderMap = userpaybirdcoinDao.queryBirdCoinBuyRecords(comboMap);
//			if(payOrderMap!=null) {
//				comboMap.put("amount", payOrderMap.get("payment"));
//			}else {
//				comboMap.put("amount", 0);
//			}
			//获取鸟币套餐  增加参数 标示获取公共套餐
			
			List<LiveRechargeComboInfo>  comboInfos = new ArrayList<LiveRechargeComboInfo>();
			Map<Object, Object> sellerCardMap = new HashMap<Object, Object>();
			if (birdCoinRequest.getIsCard()>0 && birdCoinRequest.getCardId()>0) {
				//获取储值卡配置的套餐列表
				DebitcardSeller debitcardSeller = debitcardSellerDao.selectByPrimaryKey(birdCoinRequest.getCardId());
				if (debitcardSeller!=null && debitcardSeller.getStatus() == 0) {
					sellerCardMap.put("id", debitcardSeller.getId());
					sellerCardMap.put("sellername", debitcardSeller.getSellername());
					sellerCardMap.put("sellerType", debitcardSeller.getSellertype());
					if (null!=debitcardSeller.getRechargeItem()) {//分割逗号 获取配置过ID的套餐列表
						String [] comboList = debitcardSeller.getRechargeItem().split(",");
						List<Integer> list=new ArrayList<Integer>();
						for(int i=0;i<comboList.length;i++){
							if (comboList[i] != null && !comboList[i].equals("")) {
								list.add(Integer.parseInt(comboList[i]));
							}
						}
						comboMap.put("list", list);
						comboMap.put("object", 2);
						//根据组装的list(id)查询 充值套餐
						comboInfos = userpaybirdcoinDao.queryBirdCoinComboList(comboMap);
					}else {
						response = new MapResponse(ResponseCode.FAILURE, "未找到商家储值卡,或储值卡充值服务已关闭");
					}
					comMap.put("sellerCardInfo", sellerCardMap);
				}else {
					log.info("商家储值卡不存在或者已关闭");
					return new MapResponse(ResponseCode.FAILURE, "未找到商家储值卡,或储值卡充值服务已关闭");
				}
			}else {
				comboInfos =  userpaybirdcoinDao.queryBirdCoinComboList(comboMap);
			}
			
			Map<String, String> xmnWalletMap = null;
			Map<String, Object> liveWalletMap = null;
			try {
				xmnWalletMap = liveGiftsInfoService.getXmnWalletBlance(uid);
				liveWalletMap = liveGiftsInfoService.getLiveWalletBlance(uid);
				//钱包余额总金额
				BigDecimal amountMoney = new BigDecimal(0);
				if (xmnWalletMap!=null && xmnWalletMap.size()>0) {
					amountMoney = new BigDecimal(xmnWalletMap.get("zbalance")).add(new BigDecimal(xmnWalletMap.get("commision")));
				}
				
				if (liveWalletMap!=null && liveWalletMap.size()>0) {
					comMap.put("personBirdCoin", new BigDecimal(liveWalletMap.get("commision").toString()).intValue() ); //直播钱包鸟豆
				}else {
					comMap.put("personBirdCoin", new BigDecimal(0)); //直播钱包鸟豆
				}
				
				xmnWalletMap.put("amountMoney", amountMoney.toString());
//				xmnWalletMap.put("amountMoney", "0");
				comMap.put("uid", uid);
			} catch (Exception e) {
				e.printStackTrace();
				response = new MapResponse(ResponseCode.FAILURE, "获取钱包余额失败");
			}
			comMap.put("xmnWallet", xmnWalletMap);
			comMap.put("liveWallet", liveWalletMap);
			comMap.put("comboInfoList", comboInfos);
			
			comMap.put("minBirdCoinCombo", propertiesUtil.getValue("minBirdCoinCombo", "conf_live.properties"));
			comMap.put("maxBirdCoinCombo", propertiesUtil.getValue("maxBirdCoinCombo", "conf_live.properties"));
			comMap.put("isChooseQuantity", propertiesUtil.getValue("isChooseQuantity", "conf_live.properties"));
			comMap.put("useBalanceStatus", propertiesUtil.getValue("useBalanceStatus", "conf_live.properties"));
			
			//控制IOS内购跳转方向 原生：0 ，H5： 1
			comMap.put("buyComboRedirectStatus", "1");//初始化为 默认跳转H5
			if (!StringUtils.isEmpty(birdCoinRequest.getSystemversion()) && birdCoinRequest.getSystemversion().indexOf("ios")>=0) {
					
				String accept = propertiesUtil.getValue("is_iospay", "conf_live.properties");//获取IOS 审核标示0审核状态
				if (accept.equals("0")) {//如果IOS 发布审核期间
					String version  = propertiesUtil.getValue("checkIOSManorVersion", "conf_common.properties");//获取IOS审核版本
					String appversion = birdCoinRequest.getAppversion();
					appversion = appversion.replace(".", "");
					if (version.equals(appversion)) {
						comMap.put("buyComboRedirectStatus", "0");
					}
				}
			}
			
			comMap.put("buyComboRedirecSellertUrl", propertiesUtil.getValue("buyComboRedirecSellertUrl", "conf_live.properties"));
			comMap.put("buyComboRedirectUrl", propertiesUtil.getValue("buyComboRedirectUrl", "conf_live.properties"));
			
			response = new MapResponse(ResponseCode.SUCCESS, "获取鸟豆套餐成功");
			response.setResponse(comMap);
		} catch (Exception e) {
			e.printStackTrace();
			return new MapResponse(ResponseCode.FAILURE, "客户端提交操作系统有误!");
		}
		return response;
	}
	
	/**
	 * 
	* @Title: addUserPayBirdCoin
	* @Description: 用户购买鸟币
	* @return Object
	 */
	public Object addUserPayBirdCoin(UserPayBirdCoinRequest upclRequest)
	{
		Map<Object,Object> rechargeMap=null;
//		String uid = upclRequest.getUid().toString();
		String uid = sessionTokenService.getStringForValue(upclRequest.getSessiontoken()) + "";
		if (StringUtils.isEmpty(uid) || "null".equalsIgnoreCase(uid)) {
			return new BaseResponse(ResponseCode.TOKENERR, "token已失效,请重新登录");
		}
		
		String concurrencekey="concurrencePayBirdKey_"+uid;
		
		try{
			long concurrenceNum=stringRedisTemplate.opsForValue().increment(concurrencekey, 1);
			if(concurrenceNum>1){
				return new BaseResponse(ResponseCode.FAILURE, "正在充值，请稍后...");
			}
			Map<Object, Object> liveMap = new HashMap<Object, Object>();
			Map<Object, Object> livePayMap = new HashMap<Object, Object>();
			try {
				
				//获取当前用户的基本信息
				liveMap = liveUserDao.queryLiverInfoByUid(Integer.parseInt(uid));
				if (liveMap==null) {
					return new BaseResponse(ResponseCode.FAILURE, "当前用户信息未找到");
				}
				
				//获取支付套餐信息
				rechargeMap=userpaybirdcoinDao.getRechargeComboById(upclRequest.getId());
				if(rechargeMap==null || rechargeMap.size()<=0){
					return new BaseResponse(ResponseCode.FAILURE, "用户购买鸟豆失败");
				}
			} catch (Exception e1) {
				log.error("用户购买鸟豆失败");
				e1.printStackTrace();
				return new BaseResponse(ResponseCode.FAILURE, "用户购买鸟豆失败");
			}
			
			//最低购买数量 与最大购买数量
			if (upclRequest.getQuantity()>0) {
				if (upclRequest.getQuantity()>10) {
					upclRequest.setQuantity(10L);
				}
			}else {
				return new BaseResponse(ResponseCode.FAILURE, "购买鸟豆套餐数量不能为0");
			}
			
			//订单总金额 用于 计算各种金额变量
			BigDecimal payment = new BigDecimal(rechargeMap.get("rech_amount").toString()).multiply(new BigDecimal(upclRequest.getQuantity()));
			//订单总金额 传到支付接口
			BigDecimal amount = new BigDecimal(rechargeMap.get("rech_amount").toString()).multiply(new BigDecimal(upclRequest.getQuantity()));
			
			//开始获取寻蜜鸟钱包余额--------------------------------------------------------------------
			BigDecimal commision=new BigDecimal(0);
			BigDecimal zbalance=new BigDecimal(0);
			Map<String, String> resultMap = new HashMap<>();
			
			//标示是否选择了 余额支付方式
			if(upclRequest.getIsBalance()>0){
				log.info("获取寻蜜鸟钱包余额，UID="+uid);
				//获取寻蜜客钱包余额
				Map<String, String> walletBalanceMap=personalcenterService.getWalletMoney(uid+"", 1);
				//获取寻蜜客钱包余额
				
				if(walletBalanceMap==null || walletBalanceMap.size()<=0){
					log.info("未获取寻蜜鸟钱包余额，UID="+uid);
					return new BaseResponse(ResponseCode.FAILURE, "未获取寻蜜鸟钱包信息");
				}
				commision=new BigDecimal(walletBalanceMap.get("commision").toString());//佣金余额
				zbalance=new BigDecimal(walletBalanceMap.get("zbalance").toString()); //赠送余额
				
				log.info("获取寻蜜鸟钱包余额成功，UID="+uid+",佣金余额："+commision+",赠送余额："+zbalance);
				
				//计算佣金能否支付
				if (commision.compareTo(new BigDecimal(0)) > 0) {
					if (payment.subtract(commision).compareTo(new BigDecimal(0))>=0) {//标示佣金余额不够支付订单金额
						resultMap.put("commision", commision.setScale(2, BigDecimal.ROUND_HALF_UP)+"");
						payment = payment.subtract(commision);
					}else {
						resultMap.put("commision", payment.setScale(2, BigDecimal.ROUND_HALF_UP)+"");
						payment = new BigDecimal(0);
					}
				}
				//计算赠送金额能否支付
				if (payment.compareTo(new BigDecimal(0))>0) {
					if (zbalance.compareTo(new BigDecimal(0)) > 0) {
						if (payment.subtract(zbalance).compareTo(new BigDecimal(0))>=0) {//标示佣金余额不够支付订单金额
							resultMap.put("zbalance", zbalance.setScale(2, BigDecimal.ROUND_HALF_UP)+"");
							payment = payment.subtract(zbalance);
						}else {
							resultMap.put("zbalance", payment.setScale(2, BigDecimal.ROUND_HALF_UP)+"");
							payment = new BigDecimal(0);
						}
					}
				}
				
				if (null!=upclRequest.getPay_type() && upclRequest.getPay_type() == 1000000 && payment.compareTo(new BigDecimal(0))>0) {
					return new BaseResponse(ResponseCode.FAILURE, "钱包余额不足");
				}
			}
//			
			Map<Object,Object> param=new HashMap<Object,Object>();
			String order_no = "05"+SaasBidType.getBid();
			param.put("order_no", order_no);//订单编号
			param.put("appSourceStatus", EnumUtil.getEnumCode(ConstantDictionary.AppSourceState.class, upclRequest.getAppSource()));
//			param.put("pay_no", 0);//支付流水号
			param.put("combo_id", rechargeMap.get("id"));//鸟币充值套餐ID
//			param.put("pay_code",null);//第三方交易号
			param.put("uid", uid);//寻蜜鸟会员ID
			param.put("quantity", upclRequest.getQuantity());//充值支付金额
			param.put("payment", amount.setScale(2,BigDecimal.ROUND_HALF_UP).toString());//充值支付金额
			param.put("free_coin", Double.parseDouble(rechargeMap.get("rech_free_coin").toString())*upclRequest.getQuantity());//赠送赠送鸟币数
			param.put("real_coin", Double.parseDouble(rechargeMap.get("rech_real_coin").toString())*upclRequest.getQuantity());//实际获得鸟币数
			param.put("pay_type", upclRequest.getPay_type());//支付方式1000001:支付宝SDK支付;1000003:微信SDK支付;1000013:公众号支付，1000000：钱包支付
			param.put("pay_state", "0");//充值支付状态 ：0 未支付; 1 已支付成功
//			param.put("pay_time", null);//支付完成时间
			param.put("create_time", DateUtil.format(new Date()));///创建时间
			
			param.put("uid_relation_chain","");///会员等级关系链
			param.put("object_oriented", upclRequest.getRechargeType());
//			param.put("indirect_uid", "");// V客间接关系键，用于V客团队奖
			if (null!=upclRequest.getRechargeType()) {//PC端充值改参数必填
				param.put("object_oriented", upclRequest.getRechargeType());
				Map<Object, Object> relationMap = new HashMap<Object, Object>();
				relationMap.put("uid", uid);
				relationMap.put("object_oriented", upclRequest.getRechargeType());
				List<UrsEarningsRelationInfo>  infos = liveUserDao.queryBursEarningsRelationList(relationMap);
				if (infos.size()>0) {
					UrsEarningsRelationInfo info = infos.get(0);
					param.put("uid_relation_chain",info.getUidRelationChain());///会员等级关系链
					// V客间接关系键，用于V客团队奖
					param.put("indirect_uid", info.getIndirectUid());
				}
			}
//			else {
//				param.put("object_oriented", (rechargeMap.get("object_oriented")==null || rechargeMap.get("object_oriented").toString().isEmpty())?"0":rechargeMap.get("object_oriented"));
//			}
			param.put("excitation_project", upclRequest.getRewardType());
			
			//如果是代充值请求
			if (null !=upclRequest.getIsHelpPay() && upclRequest.getIsHelpPay()>0) {
				livePayMap = liveUserDao.queryLiverInfoByUid(upclRequest.getPayUid());
				if (livePayMap==null) {
					return new BaseResponse(ResponseCode.FAILURE, "代充值用户信息未找到");
				}else {
					param.put("pay_uid", uid);
					param.put("is_to_pay", 1);
					param.put("uid", livePayMap.get("uid"));//寻蜜鸟会员ID
				}
			}
			
			// 判断是否是充值储值卡的请求 
			if (upclRequest.getIsCard()==1 && upclRequest.getSellerId()>0) {
				Map<Object, Object> sellerCardMap = this.setSellerCardParam(upclRequest, rechargeMap);
				if (sellerCardMap!=null && sellerCardMap.size()>0) {
					param.put("debitcard_id", sellerCardMap.get("debitcard_id"));///总店ID
					param.put("sellerid", sellerCardMap.get("sellerid"));///总店ID
					param.put("sellertype", sellerCardMap.get("sellertype"));//普通商户
					param.put("sellername", sellerCardMap.get("sellername"));//总店名称
					param.put("q_quota", sellerCardMap.get("q_quota"));//充值前额度
					param.put("quota", sellerCardMap.get("quota"));//本次额度
					param.put("object_oriented", upclRequest.getRechargeType());
					param.put("entry_sellerid", sellerCardMap.get("entry_sellerid"));//充值入口商家
					param.put("entry_selleragio", sellerCardMap.get("entry_selleragio"));//充值入口商家折扣
				}
			}
			//
			if (null!=upclRequest.getPay_type() && upclRequest.getPay_type().toString().equals("1000028")) {
				param.put("object_oriented", upclRequest.getRechargeType());
			}
			
			//新增用户支付记录
			Integer payNum=userpaybirdcoinDao.addUserPayBirdCoin(param);
			
			if(payNum<=0){
				return new BaseResponse(ResponseCode.FAILURE, "用户充值鸟豆订单失败");
			}
			//响应
//			MapResponse br = new MapResponse(ResponseCode.SUCCESS, "用户充值鸟币订单成功");
			
			//订单编号
			resultMap.put("orderSn",order_no);
			//用户UID
			resultMap.put("uid", uid);
			
			//获取每个套餐每个店铺的单价金额
			//订单金额
			resultMap.put("amount", amount.setScale(2,BigDecimal.ROUND_HALF_UP).toString());
			//订单来源，标识内部业务系统不同类型订单,001:业务系统-SAAS套餐订单;002:业务系统-SAAS软件订单;003:业务系统-积分商品订单；004:业务系统-物料订单；005:业务系统-直播鸟币购买订单
			resultMap.put("source", "005");
			//支付方式，1000001:支付宝SDK支付;1000003:微信SDK支付;1000013:微信公众号支付，1000000：钱包支付
			resultMap.put("paymentType", upclRequest.getPay_type().toString());
			//订单类型，目前传固定值2
			resultMap.put("orderType", "2");		
			//订单标题
			resultMap.put("subject","商品购买");
			if (null!=upclRequest.getPay_type() && upclRequest.getPay_type().toString().equals("1000028")) {
				if (null!=upclRequest.getReturnUrl() && !upclRequest.getReturnUrl().equals("")) {
					resultMap.put("returnUrl",upclRequest.getReturnUrl());
				}else {
					log.error("通联支付回调地址为空");
					return new BaseResponse(ResponseCode.FAILURE, "通联支付失败,请选择其他支付方式");
				}
			}
			
			//以下三个参数可选，值为0时可不传，
			//若paymentType为1000000时 profit + commision + zbalance + integral 必须等于 amount
			//若混合支付，paymentType传第三方支付类型，且 amount 必须大于 profit + commision + zbalance + integral
			//所有传入金额必须格式化为2位小数
			
			String url = this.transMap(resultMap);
			resultMap.put("url", url);
			return this.payBirdCoin(resultMap);
		}catch(Exception e){
			log.error("用户购买鸟豆失败");
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE, "用户购买鸟豆失败");
		}finally{
			stringRedisTemplate.delete(concurrencekey);
		}
	}
	
	/**
	 * 描述：查询商家有无开通储值卡 并且设置订单相关参数
	 * @author Administrators
	 * @param UserPayBirdCoinRequest ,rechargeMap套餐
	 * @return Map
	 * */
	public Map<Object, Object> setSellerCardParam(UserPayBirdCoinRequest upclRequest, Map<Object,Object> rechargeMap) throws Exception{
		
		Map<Object, Object> param = new HashMap<>();
		//如果 商户ID大于0 标示该套餐需要充值到 商户储值卡
		if (upclRequest.getIsCard()==1 && upclRequest.getSellerId()>0) {
			//调用支付服务获取该用户在此商户有无充值记录 获取充值前额度和累计充值后额度
			Map<String, String> sellerCardMap = new HashMap<String, String>();
			Map<String, String> sellerCardParamMap = new HashMap<String, String>();
			sellerCardParamMap.put("uid", upclRequest.getUid().toString());
			sellerCardParamMap.put("sellerid", upclRequest.getSellerId().toString());
			
			Seller seller = sellerService.querySellerBySellerid(upclRequest.getSellerId());
			if (seller == null) {
				log.info("没有获取到商家基本信息："+upclRequest.getSellerId());
				throw new Exception("获取商家信息异常");
			}else {
				param.put("entry_sellerid", seller.getSellerid());//充值入口商家
				param.put("entry_selleragio", seller.getAgio());//充值入口商家折扣
			}
			try {
				//获取用户对应的商家储值卡  
				sellerCardMap = liveGiftsInfoService.getSellerCardBlanceInfo(sellerCardParamMap);
				if (sellerCardMap.size()>0) {
					//订单存入储值卡相关参数
					if (sellerCardMap!=null && sellerCardMap.size()>0) {
						param.put("q_quota", sellerCardMap.get("quota"));//充值前额度 
						param.put("quota", rechargeMap.get("rech_amount"));//本次额度 
						param.put("sellerid", sellerCardMap.get("sellerid"));
						param.put("sellertype", sellerCardMap.get("sellertype"));
						param.put("sellername", sellerCardMap.get("sellername"));
						DebitcardSeller debitcardSeller = debitcardSellerDao.findBySellerId(upclRequest.getSellerId().toString());
						if (debitcardSeller!=null) {
							param.put("debitcard_id", debitcardSeller.getId());//配置储值卡商户记录的主键ID
						}
						return param;
					}
				}else {
					//没有充值记录 则查询商户开通储值卡的信息记录  返回储值卡配置的商家(单店，连锁店，区域代理)信息
					DebitcardSeller debitcardSeller = debitcardSellerDao.findBySellerId(upclRequest.getSellerId().toString());
					if (debitcardSeller!=null) {
						param.put("quota", rechargeMap.get("rech_amount"));//本次累计额度
						param.put("q_quota", 0);//充值前额度
						param.put("debitcard_id", debitcardSeller.getId());//本次累计额度
						param.put("sellerid", debitcardSeller.getSellerid());//商户类型
						param.put("sellertype", debitcardSeller.getSellertype());//商户类型
						param.put("sellername", debitcardSeller.getSellername());//联盟商名称
					}else {
						throw new Exception("未获取到商户储值卡信息");
					}
				}
			} catch (Exception e) {
				log.info("获取用户储值卡失败：uid:"+upclRequest.getUid()+",sellerId:"+upclRequest.getSellerId());
				e.printStackTrace();
				throw new Exception("获取商家信息异常");
			}
		}
		return param;
	}
	
	
	/**
	 * @throws IOException 
	 * 
	* @Title: transMap
	* @Description: 换map类型 
	* @return String    返回类型
	* @author
	* @throws
	 */
	public String transMap(Map<String,String> transmap) throws IOException{
		//订单优惠金额，目前不用传
		//transmap.put("preferential", "1.00");
		//微信公众号支付必传
		//transmap.put("wxuid",wxuid);		
		
		String key = propertiesUtil.getValue("payBirdKey", "conf_live.properties");
				
		//签名
		transmap.put("sign", Signature.sign(transmap,key));	

		String url ="";
		
		String paySwitch = propertiesUtil.getValue("payBirdurl_weixinWap_switch", "conf_live.properties");
		
		//用于区分测试环境与生存环境的访问路径   测试环境为兼容微信WAP调用测试  如果是测试环境 就进入open逻辑  生存环境则直接进入无需判断
		if (paySwitch.equals("open")) {
			if (transmap.get("paymentType")!=null && transmap.get("paymentType").toString().equals("1000013")) {
				url =propertiesUtil.getValue("payBirdurl_sq", "conf_live.properties");
			}else {
				url =propertiesUtil.getValue("payBirdurl", "conf_live.properties");
			}
		}else {
			url =propertiesUtil.getValue("payBirdurl", "conf_live.properties");
		}
		
		String request = "";
		  for(Entry<String, String> entry : transmap.entrySet()){
		        	request += "&" + entry.getKey() + "=" + entry.getValue();
		   }
		        
		request = request.substring(1, request.length());
		        
		System.out.println(url + "?" + request);
		String payurl=url + "?" + request;
			    
		return payurl;
	}
	
	
	/**
	 * @throws IOException 
	 * 
	* @Title: transMap
	* @Description: 换map类型
	* @return String    返回类型
	* @author
	* @throws
	 */
	public String transMap2(Map<String,String> transmap,String signUrl) throws IOException{
		//订单优惠金额，目前不用传
		//transmap.put("preferential", "1.00");
		//微信公众号支付必传
		//transmap.put("wxuid",wxuid);		
		
		String key = propertiesUtil.getValue("payBirdKey", "conf_live.properties");
				
		//签名
		transmap.put("sign", Signature.sign(transmap,key));	

//		String url ="";
		
//		String paySwitch = propertiesUtil.getValue("payBirdurl_weixinWap_switch", "conf_live.properties");
		
		//用于区分测试环境与生存环境的访问路径   测试环境为兼容微信WAP调用测试  如果是测试环境 就进入open逻辑  生存环境则直接进入无需判断
//		if (paySwitch.equals("open")) {
//			if (transmap.get("paymentType")!=null && transmap.get("paymentType").toString().equals("1000013")) {
//				url =propertiesUtil.getValue("payBirdurl_sq2", "conf_live.properties");
//			}else {
//				url =propertiesUtil.getValue("payBirdurl2", "conf_live.properties");
//			}
//		}else {
//			url =propertiesUtil.getValue("payBirdurl2", "conf_live.properties");
//		}
		
		String request = "";
		  for(Entry<String, String> entry : transmap.entrySet()){
		        	request += "&" + entry.getKey() + "=" + entry.getValue();
		   }
		        
		request = request.substring(1, request.length());
		        
		System.out.println(signUrl + "?" + request);
		String payurl=signUrl + "?" + request;
			    
		return payurl;
	}
	
	
	
	/**
	 * 
	* @Title: payBirdCoin
	* @Description: 调用支付接口
	* @return boolean
	 */
	public Object payBirdCoin(Map<String, String> resultMap){
		//请求接口
		String url = resultMap.get("url").toString();
		String result = HttpConnectionUtil.doPost(url, "");
		log.info(result);
		if (StringUtils.isNotEmpty(result)) {
			JSONObject json = JSONObject.parseObject(result);
			return json;
		}else {
			return new BaseResponse(ResponseCode.FAILURE, "调用支付接口失败");
		}
	}
	
	/**
	 * 
	* @Title: modifyBirdCoin
	* @Description: 支付接口
	* @return void
	 */
	public Object modifyBirdCoin(Integer uid,Integer recordId,String payment){
		try {
			//支付成功后，修改支付记录状态
			Map<Object,Object> payparam=new HashMap<Object,Object>();
			payparam.put("uid", uid);//寻蜜鸟会员ID
			payparam.put("id",recordId);
			payparam.put("pay_no", "");//支付流水号
			payparam.put("pay_code","");//第三方交易号
			payparam.put("pay_state", "1");//充值支付状态 ：0 未支付; 1 已支付成功
			payparam.put("pay_time", DateUtil.format(new Date()));//支付完成时间
			payparam.put("update_time", DateUtil.format(new Date()));//修改时间
			
			Integer paybirdNum= userpaybirdcoinDao.updatUserPayBirdCoin(payparam);
			
			if(paybirdNum<=0){
				return new BaseResponse(ResponseCode.FAILURE, "用户购买鸟豆失败");
			}
			
			//调用支付接口增加鸟币
			Map<String,String> walletParam=new HashMap<String,String>();
			walletParam.put("uid", uid+"");//寻蜜客id
			walletParam.put("rtype", "0");//类型：0 充值 1 转出   2 赠送礼物 3 发送私信 4 发送弹幕 ,5 主播礼物收入，6 主播私信收入 7 主播弹幕收入
			//鸟币
			double rech_real_coin=Double.parseDouble(payment);
			walletParam.put("commision", rech_real_coin+"");//鸟币
	
			ResponseData responsedata=personalcenterService.updateWalletAmount(walletParam);
			if(responsedata.getState()==0){
				log.info("用户购买鸟豆成功:"+uid);
				return new BaseResponse(ResponseCode.SUCCESS, "用户购买鸟豆成功");	
			}
			
			return new BaseResponse(ResponseCode.FAILURE, "鸟蛋转出余额失败,错误信息："+responsedata.getMsg());
		} catch (Exception e) {
			log.error("用户购买鸟豆失败");
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE, "用户购买鸟豆失败");
		}
		
	}
	
	/**
	 * 
	* @Title: queryUserPayBirdList
	* @Description: 查询用户充值记录
	* @return Object
	 */
	public Object queryUserPayBirdList(Long uid,Integer page)
	{
		Map<Object,Object> resultMap=new HashMap<Object,Object>();
		List<Map<Object,Object>> resultList=null;
		try {
			Map<Object,Object> param=new HashMap<Object,Object>();
			param.put("uid", uid);
			param.put("page", page);
			param.put("limit",Constant.RECHARGE_RECORD_LIMIT);
			resultList=userpaybirdcoinDao.queryUserPayBirdList(param);
			if(resultList==null || resultList.size()<=0){
				resultMap.put("payOrder", resultList);
				MapResponse response = new MapResponse(ResponseCode.SUCCESS,"用户暂无充值鸟豆记录");
				response.setResponse(resultMap);
				return response;
			}
		} catch (Exception e) {
			log.error("获取用户充值鸟豆记录异常");
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE, "获取用户充值鸟豆记录异常");
		}
		//响应
		for (Map<Object, Object> map : resultList) {
			String real_coin = map.get("real_coin").toString();
			map.put("real_coin", "+"+real_coin+"鸟豆");
		}
		resultMap.put("payOrder", resultList);
		MapResponse response = new MapResponse(ResponseCode.SUCCESS,"获取充值鸟豆记录成功");
		response.setResponse(resultMap);
		return response;
	}	
	
	
	/**
	 * 描述：处理IOS 内购预生成订单
	 * @param LiveBuyBirdCoinOrderIOSRequest
	 * @return object
	 * 2016-8-30 10:58:12
	 * */
	public Object createIOSOrderData(LiveBuyBirdCoinOrderIOSRequest orderIOSRequest){
		//验证uid
		String uid = sessionTokenService.getStringForValue(orderIOSRequest.getSessiontoken()) + "";
		if (StringUtils.isEmpty(uid) || "null".equalsIgnoreCase(uid)) {
			return new BaseResponse(ResponseCode.TOKENERR, "token已失效,请重新登录");
		}
		MapResponse response = null;
		try {
			//根据内购ID 获取当前购买的套餐信息 存入套餐订单表   
//			 
			Map<Object, Object> map = new HashMap<Object, Object>();
			map.put("product_id", orderIOSRequest.getProductId());
			LiveRechargeComboInfo comboInfo = userpaybirdcoinDao.queryLiveRechargeComboInfo(map);
			
			if (comboInfo!=null) {
				Map<Object, Object> iosBuyBordCoinMap = new HashMap<Object, Object>();
				String order_no = "05"+SaasBidType.getBid();
				iosBuyBordCoinMap.put("order_no", order_no);//订单编号
				iosBuyBordCoinMap.put("combo_id", comboInfo.getId());//鸟币套餐Id
				iosBuyBordCoinMap.put("uid", uid);
				iosBuyBordCoinMap.put("appSourceStatus", EnumUtil.getEnumCode(ConstantDictionary.AppSourceState.class, orderIOSRequest.getAppSource()));
				iosBuyBordCoinMap.put("payment", comboInfo.getRechAmount());
				iosBuyBordCoinMap.put("free_coin", comboInfo.getRechFreeCoin());
				iosBuyBordCoinMap.put("real_coin", comboInfo.getRechRealCoin());
				iosBuyBordCoinMap.put("pay_state", 0);
				iosBuyBordCoinMap.put("create_time", DateUtil.format(new Date()));
				iosBuyBordCoinMap.put("object_oriented", orderIOSRequest.getRechargeType());
//				iosBuyBordCoinMap.put("object_oriented", (null==comboInfo.getObjectOriented()||comboInfo.getObjectOriented().isEmpty())?"0":comboInfo.getObjectOriented());
				
				// 判断是否是充值储值卡的请求 
				if (orderIOSRequest.getIsCard()==1 && orderIOSRequest.getSellerId()>0) {
					
					Map<Object, Object> rechargeMap = new HashMap<Object, Object>();
					rechargeMap.put("rech_amount", comboInfo.getRechAmount());
					
					UserPayBirdCoinRequest request = new UserPayBirdCoinRequest();
					request.setIsCard(orderIOSRequest.getIsCard());
					request.setSellerId(orderIOSRequest.getSellerId());
					request.setUid(Integer.parseInt(uid));
					Map<Object, Object> sellerCardMap = this.setSellerCardParam(request, rechargeMap);
					if (sellerCardMap!=null && sellerCardMap.size()>0) {
						iosBuyBordCoinMap.put("object_oriented", (null==comboInfo.getObjectOriented()||comboInfo.getObjectOriented().isEmpty())?"0":comboInfo.getObjectOriented());
						iosBuyBordCoinMap.put("debitcard_id", sellerCardMap.get("debitcard_id"));///总店ID
						iosBuyBordCoinMap.put("sellerid", sellerCardMap.get("sellerid"));///总店ID
						iosBuyBordCoinMap.put("sellertype", sellerCardMap.get("sellertype"));//普通商户
						iosBuyBordCoinMap.put("sellername", sellerCardMap.get("sellername"));//总店名称
						iosBuyBordCoinMap.put("q_quota", sellerCardMap.get("q_quota"));//充值前额度
						iosBuyBordCoinMap.put("quota", sellerCardMap.get("quota"));//本次额度
						iosBuyBordCoinMap.put("entry_sellerid", sellerCardMap.get("entry_sellerid"));//充值入口商家
						iosBuyBordCoinMap.put("entry_selleragio", sellerCardMap.get("entry_selleragio"));//充值入口商家折扣
					}
				}
				
				iosBuyBordCoinMap.put("uid_relation_chain","");///会员等级关系链
				iosBuyBordCoinMap.put("object_oriented", orderIOSRequest.getRechargeType());
				if (null!=orderIOSRequest.getRechargeType()) {//PC端充值改参数必填
					
					Map<Object, Object> relationMap = new HashMap<Object, Object>();
					relationMap.put("uid", uid);
					relationMap.put("object_oriented", orderIOSRequest.getRechargeType());
					List<UrsEarningsRelationInfo>  infos = liveUserDao.queryBursEarningsRelationList(relationMap);
					if (infos.size()>0) {
						UrsEarningsRelationInfo info = infos.get(0);
						iosBuyBordCoinMap.put("uid_relation_chain",info.getUidRelationChain());///会员等级关系链
						// V客间接关系键，用于V客团队奖
						iosBuyBordCoinMap.put("indirect_uid", info.getIndirectUid());
					}
				}
				
				//新增套餐购买信息
				int result = userpaybirdcoinDao.addUserPayBirdCoin(iosBuyBordCoinMap);
				if (result>0) {
					response = new MapResponse(ResponseCode.SUCCESS, "IOS内购预生成订单成功");
					response.setResponse(iosBuyBordCoinMap);
				}else {
					response = new MapResponse(ResponseCode.FAILURE, "IOS内购预生成订单失败");
				}
			}else {
				response = new MapResponse(ResponseCode.FAILURE, "未获取到当前购买鸟豆套餐信息");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response = new MapResponse(ResponseCode.FAILURE, "IOS内购请求出现错误");
		}
		return response;
	}
	
	
	
	/**
	 * 描述：处理IOS验证票据  调用IOS验证票据接口
	 * @param request
	 * @return object
	 * 2016-8-30 10:58:12
	 * */
	public Object validateIOSReceiptData(LiveBuyBirdCoinIOSRequest iosRequest){
		//验证uid
		String uid = sessionTokenService.getStringForValue(iosRequest.getSessiontoken()) + "";
		if (StringUtils.isEmpty(uid) || "null".equalsIgnoreCase(uid)) {
			return new BaseResponse(ResponseCode.TOKENERR, "token已失效,请重新登录");
		}
		
		//采用redis incr函数初始化值  如果出现同一订单的多次IOS内购票据请求，返回非法操作
		Long resultNum = stringRedisTemplate.opsForValue().increment(iosRequest.getOrderNo(), 1);
		if (resultNum>1) {
			return new MapResponse(ResponseCode.FAILURE, "非法操作");
		}
		
		//首先验证IOS自字符流是否有 订单被验证过
		Map<Object, Object> iosReciepMap = new HashMap<Object, Object>();
		iosReciepMap.put("receipt", iosRequest.getReceiptStr());
		iosReciepMap.put("order_no", iosRequest.getOrderNo());
		int recieptNum = userpaybirdcoinDao.queryLivePayOrderInfoByReciptStr(iosReciepMap);
		System.out.println("当前查询到的记录："+recieptNum);
		if (recieptNum>0) {
			return new MapResponse(ResponseCode.FAILURE, "非法操作");
		}
		
		//根据订单标号 orderNo 获取预生成的订单 订单存在则进行接下来操作
		LivePayOrderInfo orderInfo = userpaybirdcoinDao.queryLivePayOrderInfoByOrderNo(iosRequest.getOrderNo());
		if (orderInfo==null) {
			log.info("操作失败,订单信息不存在:"+iosRequest.getOrderNo());
			return new MapResponse(ResponseCode.FAILURE, "操作失败,订单信息不存在");
		}
		try {
			//组装IOS内购请求参数
			Map<Object, Object> baseMap = new HashMap<Object, Object>();
			baseMap.put("receipt-data", iosRequest.getReceiptStr().trim());
			String jsonText = JSON.toJSONString(baseMap, true);
			JSONObject  jsonData= JSONObject.parseObject(jsonText);
			
			 //IOS 内购是否 正式|沙盒都验证  优先验证正式环境  验证通过不在验证沙盒
			String verify_all = propertiesUtil.getValue("IOSAppStore_verify_all", "conf_live.properties"); 
			String appStoreUrl_itunes = propertiesUtil.getValue("IOSAppStore_itunes", "conf_live.properties");//正式
			String appStoreUrl_sandbox = propertiesUtil.getValue("IOSAppStore_sandbox", "conf_live.properties");//沙盒
			
			String result = "";
			
			//(new Boolean(str)).booleanValue();
			if (verify_all.equals("true")) {//验证内购开关为true 验证正式路径失败后 再次去验证测试路径
				log.info("IOS内购当前配置为："+verify_all+"优先进入正式请求环境");
				try {
					//发起IOS内购验证票据请求
					result = HttpConnectionUtil.doPost(appStoreUrl_itunes, jsonData);
				} catch (Exception e) {
					log.info("调用Apple内购链接出错："+appStoreUrl_itunes+"  param:"+jsonData.toString()+" result:"+result);
					e.printStackTrace();
					return new MapResponse(ResponseCode.FAILURE, "appStore内购出错");
				}
				if (result!=null && !"".equals(result)) {
					if (result.indexOf("status") != -1) {
						//调用AppStore内购 返回参数为0 则成功
						JSONObject resData = JSONObject.parseObject(result);
						int status = Integer.parseInt(resData.get("status").toString());
						if (status == 0) {
							return this.verifyIOSReceiptStr(resData, iosRequest, uid, orderInfo);
							
						}else if(status == 21007){//表示测试发到了生产环境 
							//返回状态为21008时  说明IOS内购 正式环境票据被发到沙盒环境验证   重新验证沙盒环境
							
							log.info("IOS内购当前配置为："+verify_all+"进入正式请求环境失败，再次进入沙盒环境");
							result = HttpConnectionUtil.doPost(appStoreUrl_sandbox, jsonData);
							if (result!=null && !"".equals(result)) {
								if (result.indexOf("status") != -1) {
									//调用AppStore内购 返回参数为0 则成功
									JSONObject resData_sanbox = JSONObject.parseObject(result);
									int status_sanbox = Integer.parseInt(resData_sanbox.get("status").toString());
									if (status_sanbox == 0) {
										return this.verifyIOSReceiptStr(resData_sanbox, iosRequest, uid, orderInfo);
									}else{
										log.info("AppStore 沙盒内购失败,返回结果状态："+status);
										return new MapResponse(ResponseCode.FAILURE, "AppStore内购失败,代码："+status_sanbox);
									}
								}
							}else {
								return new MapResponse(ResponseCode.FAILURE, "AppStore内购失败");
							}
							
						}else if(status == 21007){
							log.info("AppStore 正式 内购失败,返回结果状态："+status+"  订单号："+iosRequest.getOrderNo());
							return new MapResponse(ResponseCode.FAILURE, "AppStore内购失败,代码："+status);
						}
					}
				
				}else {
					log.info("AppStore内购失败,返回结果："+result+"  订单号："+iosRequest.getOrderNo());
					return new MapResponse(ResponseCode.FAILURE, "AppStore内购失败");
				}
				
			}else {
				log.info("IOS内购当前配置为："+verify_all+"直接进入正式请求路径");
				try {
					//发起IOS 正式 内购验证票据请求
					result = HttpConnectionUtil.doPost(appStoreUrl_itunes, jsonData);
				} catch (Exception e) {
					log.info("调用Apple内购链接出错："+appStoreUrl_itunes+"  param:"+jsonData.toString()+" result:"+result);
					e.printStackTrace();
					return new MapResponse(ResponseCode.FAILURE, "调用Apple内购链接出错："+appStoreUrl_itunes);
				}
				if (result!=null && !"".equals(result)) {
					if (result.indexOf("status") != -1) {
						//调用AppStore内购 返回参数为0 则成功
						JSONObject resData = JSONObject.parseObject(result);
						int status = Integer.parseInt(resData.get("status").toString());
						if (status == 0) {
							return this.verifyIOSReceiptStr(resData, iosRequest, uid, orderInfo);
						}else {
							log.info("AppStore内购失败,代码："+status);
							return new MapResponse(ResponseCode.FAILURE, "AppStore内购失败,代码："+status);
						}
					}else {
						log.info("AppStore返回订单失败,返回结果："+result);
						return new MapResponse(ResponseCode.FAILURE, "AppStore内购失败");
					}
				}else {
					log.info("AppStore返回订单失败,返回结果："+result+"  订单号："+iosRequest.getOrderNo());
					return new MapResponse(ResponseCode.FAILURE, "AppStore内购失败");
				}
			}
			
		} catch (Exception e) {
			log.info("IOS内购请求出现错误:"+orderInfo.getOrder_no());
			e.printStackTrace();
			return new MapResponse(ResponseCode.FAILURE, "IOS内购请求出现错误");
		}finally{
			stringRedisTemplate.delete(iosRequest.getOrderNo());
		}
		return null;
	}
	
	/**
	 * IOS 内购
	 * */
	public Object verifyIOSReceiptStr(JSONObject resData,LiveBuyBirdCoinIOSRequest iosRequest,String uid,LivePayOrderInfo orderInfo){
		//获取receipt 返回的票据记录
		JSONObject receiptData = JSONObject.parseObject(resData.get("receipt").toString());
		//获取in_app 里面的返回操作  标示产品信息
		JSONArray myJsonArray = new JSONArray().parseArray(receiptData.getString("in_app").toString());
		if (myJsonArray.size()>0) {
			JSONObject inApp = JSONObject.parseObject(myJsonArray.get(0).toString());
			Map<Object, Object> iosBuyBordCoinMap = new HashMap<Object, Object>();
			
			iosBuyBordCoinMap.put("order_no", iosRequest.getOrderNo());
			iosBuyBordCoinMap.put("pay_no", inApp.get("transaction_id").toString());//支付流水号								
			iosBuyBordCoinMap.put("pay_type", "1000004");//appStore支付
			iosBuyBordCoinMap.put("pay_state", 1);
			iosBuyBordCoinMap.put("receipt",iosRequest.getReceiptStr().trim());
			iosBuyBordCoinMap.put("pay_time", DateUtil.format(new Date()));
			iosBuyBordCoinMap.put("update_time", DateUtil.format(new Date()));
			
			try {
				//更改为调用业务服务接口
				LiveOrderService.Client  client = null;
				TMultiplexedProtocol tMultiplexedProtocol = thriftBusinessUtil.getProtocol("LiveOrderService");
				client = new Client(tMultiplexedProtocol);
				thriftBusinessUtil.openTransport();
				Map<String, String> getWalletMap = new HashMap<String, String>();
				getWalletMap.put("bid", iosRequest.getOrderNo());
				getWalletMap.put("status","1");
				getWalletMap.put("payId", inApp.get("transaction_id").toString());
				getWalletMap.put("payType", "1000004");
				getWalletMap.put("zdate", DateUtil.format(new Date(),DateUtil.defaultSimpleFormater));
				
				ResponseData responseData =	client.updateLiveOrder(getWalletMap);
				if (responseData.getState() == 0) {
					return new MapResponse(ResponseCode.SUCCESS, "充值成功");
				}else {
					return new MapResponse(ResponseCode.FAILURE, "鸟豆购买成功,但累计鸟豆数量异常,请您联系客服");
				}
			} catch (Exception e) {
				log.info("鸟豆购买成功,累计鸟豆数量异常,订单号："+iosRequest.getOrderNo());
				e.printStackTrace();
				return new MapResponse(ResponseCode.FAILURE, "鸟豆购买成功,但累计鸟豆数量异常,请您联系客服");
			}finally{
				thriftUtil.coloseTransport();
			}
		}else {
			return new MapResponse(ResponseCode.FAILURE, "AppStore返回订单异常");
		}
	}
	
	
	
	/**
	 * 描述：处理IOS 内购取消订单接口
	 * @param request
	 * @return object
	 * 2016-8-30 10:58:12
	 * */
	public Object cancelIOSOrder(LiveBuyBirdCoinCancleIOSRequest cancleIOSRequest){
		//验证uid
		String uid = sessionTokenService.getStringForValue(cancleIOSRequest.getSessiontoken()) + "";
		if (StringUtils.isEmpty(uid) || "null".equalsIgnoreCase(uid)) {
			return new BaseResponse(ResponseCode.TOKENERR, "token已失效,请重新登录");
		}
		MapResponse response = null;
		try {
			//根据订单标号 orderNo 获取订单
			LivePayOrderInfo orderInfo = userpaybirdcoinDao.queryLivePayOrderInfoByOrderNo(cancleIOSRequest.getOrderNo());
			if (orderInfo!=null) {
				if (orderInfo.getPay_state() == 1) {
					response = new MapResponse(ResponseCode.FAILURE, "订单已经支付成功,不能取消");
				}
				
				Map<Object, Object> paramMap = new HashMap<Object, Object>();
				paramMap.put("order_no", orderInfo.getOrder_no());
				paramMap.put("pay_state", 2);
				userpaybirdcoinDao.modifyLivePayOrderInfoByOrderNo(paramMap);
				response = new MapResponse(ResponseCode.SUCCESS, "订单取消成功");
				return response;
			}else {
				response = new MapResponse(ResponseCode.FAILURE, "未获取到订单信息");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response = new MapResponse(ResponseCode.FAILURE, "操作订单失败");
		}
		return response;
	}
	
	/**
	 * 描述：查询IOS内购返回的Product_id对应的套餐
	 * @param String Product_id
	 * @return LiveRechargeComboInfo
	 * 2016-8-30 17:26:05
	 * */
//	public LiveRechargeComboInfo queryLiveRechargeComboInfo(String product_id){
//		return userpaybirdcoinDao.queryLiveRechargeComboInfo(product_id);
//	}



	public Object returnRecord(PageRequest request) {
		//调用支付服务创建直播钱包
		LiveWalletService.Client client = null;
		try {
			//用户ID
			String uid=sessionTokenService.getStringForValue(request.getSessiontoken())+"";
			
			//组装参数
			Map<String, String> paraMap = new HashMap<>();
			paraMap.put("uid", uid);
			paraMap.put("pageNo", request.getPage()+"");
			paraMap.put("pageSize", 20+"");
			TMultiplexedProtocol tMultiplexedProtocol = thriftUtil.getProtocol("LiveWalletService");
			client = new LiveWalletService.Client(tMultiplexedProtocol);
			thriftUtil.openTransport();
			ResponseList  dataList =client.getBirdBeansList(paraMap);
			if(dataList!=null){
				if(dataList.getDataInfo().getState()==0){
					Map<Object,Object> resultMap=new HashMap<>();
					MapResponse mapResponse=new MapResponse(ResponseCode.SUCCESS,"成功");
					if(dataList.getDataListSize()==0){
						resultMap.put("dataList", new ArrayList<>());
					}else{
						resultMap.put("dataList", dataList.getDataList());
					}
						mapResponse.setResponse(resultMap);
						return mapResponse;
				}else{
					return new BaseResponse(ResponseCode.FAILURE,dataList.getDataInfo().getMsg());
				}
					
			}

		}catch (Exception e){
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE,"获取返现记录失败");
			
		}finally {
			thriftUtil.coloseTransport();
		}
		
		return new BaseResponse(ResponseCode.FAILURE,"错误");
	}


	/**
	 * 
	 * @Description: 查询消费记录
	 * @author xiaoxiong
	 * @date 2016年11月1日
	 */
	public Object consumeRecord(PageRequest request) {
		LiveWalletService.Client client = null;
		try {
			
			String uid=sessionTokenService.getStringForValue(request.getSessiontoken())+"";
			//组装参数
			Map<String, String> paraMap = new HashMap<>();
			paraMap.put("uid", uid);
			paraMap.put("pageNo", request.getPage()+"");
			paraMap.put("pageSize", "20");
			TMultiplexedProtocol tMultiplexedProtocol = thriftUtil.getProtocol("LiveWalletService");
			client = new LiveWalletService.Client(tMultiplexedProtocol);
			thriftUtil.openTransport();
			WalletRecord  data =client.getConsumerDetail(paraMap);
			if(data!=null){
				List<Map<String,String>> dataList=new ArrayList<>();
				for(Map<String,String> tempMap :data.getWalletList()){
					Map<String, String> rmap=new HashMap<>();
					rmap.put("name", "购买粉丝劵");
					rmap.put("zdate", tempMap.get("createTime"));
					rmap.put("money",tempMap.get("beansMoney"));
					dataList.add(rmap);
				}
				
				Map<Object,Object> map=new HashMap<>();
				MapResponse mapResponse=new MapResponse(ResponseCode.SUCCESS, "成功");
				map.put("dataList", dataList);
				mapResponse.setResponse(map);
				return mapResponse;
			}
		}catch (Exception e){
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE,"获取返现记录失败");
			
		}finally {
			thriftUtil.coloseTransport();
		}
		return new BaseResponse(ResponseCode.FAILURE,"失败");
	}
	
	
	
	/**
	 * 描述：  取消订单操作,调用支付接口   android 取消订单接口
	 * */
	public Object cancelLivePayOrderInfo(LiveBuyBirdCoinCancleIOSRequest request){
		
		MapResponse response = null;
		
		//验证token
		try {
			Map<Object, Object> paramMap = new HashMap<Object, Object>();
			paramMap.put("order_no", request.getOrderNo());
			//获取订单基本信息
			LivePayOrderInfo orderInfo = userpaybirdcoinDao.queryLivePayOrderInfoByOrderNo(request.getOrderNo());
			if (orderInfo!=null) {
				//取消订单组装
				Map<String, String> cancelMap = new HashMap<String, String>();
				
				cancelMap.put("orderNumber",orderInfo.getOrder_no());
				cancelMap.put("randomNumber", (int)((Math.random()*9+1)*100000)+"");
				String url = liveBuyFansCouponService.transMap(cancelMap);
				//请求接口
				String result = HttpConnectionUtil.doPost(url, "");
				if (StringUtils.isNotEmpty(result)) {
					if (result.indexOf("state") != -1) {
						JSONObject json = JSONObject.parseObject(result);
						int state = Integer.parseInt(json.get("state").toString());
						if (state == 201 || state == 200) {
							return new MapResponse(ResponseCode.SUCCESS, "操作成功");
						}else {
							return new MapResponse(ResponseCode.FAILURE, json.get("info")==null?"操作失败":json.get("info").toString());
						}
					}
				}else {
					return new BaseResponse(ResponseCode.FAILURE, "调用支付接口失败");
				}
				
			}else {
				log.info("没有找到订单相关信息："+request.getOrderNo());
				response = new MapResponse(ResponseCode.FAILURE, "未找到订单信息!");
			}
		} catch (Exception e) {
			log.info("查询订单详情异常:"+request.getOrderNo());
			e.printStackTrace();
			return new MapResponse(ResponseCode.FAILURE, "获取订单信息异常");
		}
		return response;
	}
	
	
}
