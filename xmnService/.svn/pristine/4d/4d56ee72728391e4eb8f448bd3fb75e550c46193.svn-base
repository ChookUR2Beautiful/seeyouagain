package com.xmniao.xmn.core.seller.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.seller.DebitCardSucessRquest;
import com.xmniao.xmn.core.common.request.seller.GetCardSellerRquest;
import com.xmniao.xmn.core.common.request.seller.TipRequest;
import com.xmniao.xmn.core.common.request.seller.UserDebitCardSellerRquest;
import com.xmniao.xmn.core.live.dao.AnchorLiveRecordDao;
import com.xmniao.xmn.core.live.dao.LiveUserDao;
import com.xmniao.xmn.core.live.dao.UserPayBirdCoinDao;
import com.xmniao.xmn.core.live.entity.LiveRechargeComboInfo;
import com.xmniao.xmn.core.live.service.LiveGiftsInfoService;
import com.xmniao.xmn.core.seller.dao.DebitcardSellerDao;
import com.xmniao.xmn.core.seller.entity.DebitcardSeller;
import com.xmniao.xmn.core.seller.entity.Seller;
import com.xmniao.xmn.core.seller.service.UserDebitCardService;
import com.xmniao.xmn.core.thrift.DebitcardService;
import com.xmniao.xmn.core.thrift.LiveWalletService;
import com.xmniao.xmn.core.thrift.LiveWalletService.Client;
import com.xmniao.xmn.core.thrift.ResponseData;
import com.xmniao.xmn.core.util.PropertiesUtil;
import com.xmniao.xmn.core.util.SaasBidType;
import com.xmniao.xmn.core.util.ThriftBusinessUtil;
import com.xmniao.xmn.core.util.ThriftUtil;
import com.xmniao.xmn.core.verification.dao.BillDao;
import com.xmniao.xmn.core.xmer.dao.SellerDao;
import com.xmniao.xmn.core.xmer.dao.SellerInfoDao;
@Service
public class UserDebitCardServiceImpl implements UserDebitCardService {

	private final Logger log = Logger.getLogger(UserDebitCardService.class);
	@Autowired
	private SellerInfoDao sellerInfoDao;
	
	@Autowired
	private LiveUserDao liveUserDao;
	
	@Autowired
	private SessionTokenService sessionTokenService;
	@Autowired
	private DebitcardSellerDao debitcardSellerDao;
	@Autowired
	private LiveGiftsInfoService liveGiftsInfoService;
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	@Autowired
	private ThriftUtil thriftUtil;
	@Autowired
	private ThriftBusinessUtil thriftBusinessUtil;
	@Autowired
	private UserPayBirdCoinDao userPayBirdCoinDao;
	@Autowired
	private AnchorLiveRecordDao anchorLiveRecordDao;
	@Autowired
	private SellerDao sellerDao;
	@Autowired
	private BillDao billDao;
	@Autowired
	private PropertiesUtil propertiesUtil;
	@Autowired
	private String fileUrl;
	@Autowired
	private String localDomain;
	
	/**用户进入领取 专享卡页面
	 * 
	 */
	public Object userGeTDebitCardList(UserDebitCardSellerRquest request) {
		/*验证用户*/
		String uid = sessionTokenService.getStringForValue(request.getSessiontoken()) + "";
		//uid="606673";
		if (org.apache.commons.lang.StringUtils.isEmpty(uid) || "null".equalsIgnoreCase(uid)) {
			return new BaseResponse(ResponseCode.TOKENERR, "token已失效,请重新登录");
		}
		 
		/*专享卡结果集*/	
		List<Map<Object,Object>> cardsResult = new ArrayList<Map<Object,Object>>();
		/*返回到页面结果集*/
		Map<Object,Object> resultMap = new HashMap<Object,Object>();	
			// 查询当前城市指定商圈和分类所有的专享卡按距离排序
			Map<Object,Object> map = new HashMap<Object,Object>();
			map.put("lat", request.getLatitude());
			map.put("lon", request.getLongitude());
			map.put("cityId", request.getCityId());
			map.put("tradeid", request.getTradeid());
			map.put("zoneid", request.getZoneid());
			List<Map<Object,Object>> cards=sellerInfoDao.queryDebitCardSellerRanges(map);
			if (cards==null || cards.isEmpty()) {
				resultMap.put("resultList", null);
				//响应
				MapResponse response = new MapResponse(ResponseCode.SUCCESS, "专享卡列表数据");
				response.setResponse(resultMap);
			}
			
			// 查询用户所在城市指定商圈和分类充值过所有的专项卡按支付时间排序
			Map<Object,Object> conditionMap = new HashMap<Object,Object>();
			conditionMap.put("cityId", request.getCityId());
			conditionMap.put("tradeid", request.getTradeid());
			conditionMap.put("zoneid", request.getZoneid());
			conditionMap.put("uid", uid);
			List<Map<Object,Object>> chargeddebitcards = liveUserDao.queryChargeDebitCards(conditionMap);
			
			if (chargeddebitcards!=null && !chargeddebitcards.isEmpty()) {
				Map<Object,Object> topcard = getTopCard(chargeddebitcards.get(0),cards); //置顶专享卡
				List<Map<Object,Object>> unChargeddebitcards = getUnchargedCards(cards,chargeddebitcards); //未充值过的专享受卡
				cards.removeAll(unChargeddebitcards);//只剩下充值过的专享卡
				List<Map<Object,Object>> otherChargedCards =getOtherChargedCards(cards,topcard); //其他充值的专享卡
				//排序： 最早充值专项卡 + 其他充值过的专享卡 + 未充值过的专享卡
				cardsResult.add(topcard);
				cardsResult.addAll(otherChargedCards);
				cardsResult.addAll(unChargeddebitcards);
				//结果集处理
				List<Map<Object,Object>> resultList = dealWithCardsResult(cardsResult,request);
				resultMap.put("resultList", resultList); //列表专享卡
			}else{
				cardsResult=cards;
				List<Map<Object,Object>> resultList =dealWithCardsResult(cardsResult,request);
				resultMap.put("resultList", resultList);
			}					
			//响应
			MapResponse response = new MapResponse(ResponseCode.SUCCESS, "专享卡列表数据");
			response.setResponse(resultMap);	
		return response;
	}
		

	/**获取充值过的专享卡中最早的一个专享卡
	 * @param map
	 * @param cards
	 * @return
	 */
	private Map<Object, Object> getTopCard(Map<Object, Object> map, List<Map<Object, Object>> cards) {
		Map<Object,Object> topMap = null;
		for(int i=0;i<cards.size();i++){
			if (map.get("id").equals(cards.get(i).get("id"))) {
				topMap=cards.get(i);
				break;			
			}
		}
		return topMap;
	}

	/**除去最早充值专享受卡 的其他充值过的专享卡
	 * @param cards 
	 * @param topcard
	 * @return
	 */
	private List<Map<Object, Object>> getOtherChargedCards(List<Map<Object, Object>> cards,Map<Object, Object> topcard) {
		for(int i=0;i<cards.size();i++){
			if (cards.get(i).get("id").equals(topcard.get("id"))){
				cards.remove(i);
				break;
			}
		}			
		return cards;
	}


	/**筛选得到未充值过的专享卡
	 * @param cards
	 * @param chargeddebitcards
	 * @return
	 */
	private List<Map<Object, Object>> getUnchargedCards(List<Map<Object, Object>> cards,List<Map<Object, Object>> chargeddebitcards) {
		List<Map<Object,Object>> unchargeCards = new ArrayList<Map<Object,Object>>();
		for(int i=0;i<cards.size();i++){
			Map<Object,Object> card = cards.get(i);
			Boolean flag =false;
			for(int j=0;j<chargeddebitcards.size();j++){
				Map<Object,Object> chargeCard = chargeddebitcards.get(j);
				if(card.get("id").equals(chargeCard.get("id"))){				
					flag=true;
					break;
				}
			}
			if(!flag){
				unchargeCards.add(card);
			}
		}
		return unchargeCards;
	}


	/**专享卡列表页结果集处理
	 * @param cards
	 * @param rq
	 * @return
	 */
	private List<Map<Object, Object>> dealWithCardsResult(List<Map<Object, Object>> cards,UserDebitCardSellerRquest rq) {
		/*置顶专享卡集合*/
		List<Map<Object,Object>> topList = new ArrayList<Map<Object,Object>>();
		/*专享卡列表集合*/
		List<Map<Object,Object>> remainList = new ArrayList<Map<Object,Object>>();
		/*专享卡页面集合*/
		List<Map<Object,Object>> resultList = new ArrayList<Map<Object,Object>>();
		//查询套餐表中面向商家所有有效的套餐
		List<LiveRechargeComboInfo> infos = debitcardSellerDao.findSellerAllCharge();
		//查询等级粉丝表
		List<Map<Object,Object>> ranks = debitcardSellerDao.findAllRanks();
		Map<String,String> infoMap = new HashMap<String,String>();
		//过滤粉丝等级不存在的套餐
		for (LiveRechargeComboInfo info : infos) {
			Boolean flag = false;
			if (ranks != null && !ranks.isEmpty()) {
				for (int n=0;n<ranks.size();n++) {
					
					BigDecimal lowestAmount = new BigDecimal(ranks.get(n).get("reward_lowest").toString());
					BigDecimal highestAmount = new BigDecimal(ranks.get(n).get("reward_highest").toString());
					BigDecimal amount = info.getRechAmount();
					//如果充值金额在 等级区间内的
					if (amount.compareTo(lowestAmount)>=0 && amount.compareTo(highestAmount)<=0) {
						flag=true;
						break;
					}
				}
			}
			if (flag) {
				infoMap.put(info.getId().toString(), info.getRechAmount().toString());
			}	
			
		}
		int index =0; //0用于标示置顶专享卡，其他标识列表专享卡
		for (int i =0;i<cards.size();i++) {
			String[] items = cards.get(i).get("recharge_item").toString().split(",");
			List<Map<Object,Object>> quotas = new ArrayList<Map<Object,Object>>();
			for(int k =0;k<items.length;k++){			
				if(infoMap.containsKey(items[k])){				
					Map<Object,Object> quota = new HashMap<Object,Object>();
					quota.put("comboId",items[k]);
					quota.put("comboPrice", infoMap.get(items[k]));
					//排除额度相同的套餐
					if(!isExistAmount(quotas,items[k])) {
						quotas.add(quota);
					}
					
				}
			}		
			//按照价格的大小 从小到大排序
			Collections.sort(quotas, new Comparator<Map<Object,Object>>() {
				@Override
				public int compare(Map<Object, Object> o1, Map<Object, Object> o2) {
					return (new BigDecimal(o1.get("comboPrice").toString()))
							.compareTo(new BigDecimal(o2.get("comboPrice").toString()));
				}
			});
			
			if (quotas!=null && !quotas.isEmpty()){
				for(int j=quotas.size()-1;j>=0;j--){
					Map<Object,Object> map = new HashMap<Object,Object>();
					map.put("id", cards.get(i).get("id"));
					map.put("sellertype", cards.get(i).get("sellertype"));
					map.put("sellername", cards.get(i).get("sellername"));
					map.put("entry_sellername", cards.get(i).get("entry_sellername"));
					map.put("sellerid", cards.get(i).get("sellerid"));
					map.put("entry_sellerid", cards.get(i).get("entry_sellerid"));
					map.put("city", cards.get(i).get("city"));
					map.put("showQuota", quotas.get(j));			      
					map.put("index", index);
					if(i!=0){						
						remainList.add(map);
						index++;
					}else{
						topList.add(map);
						index++;
						break;
					}
				
				}
			}
		}
		//分页处理
		List<Map<Object,Object>> pageList = new ArrayList<Map<Object,Object>>();
		if(remainList!=null && !remainList.isEmpty()) {
			Integer page = rq.getPage();  //页码
			Integer pageSize =rq.getPageSize(); //每页数量
			Integer start = (page-1)*pageSize;  //列表集合截取的起始位置
			Integer totalCount = remainList.size(); //列表集合数量
			Integer pageCount = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize +1; //总共的页数
			if(page == pageCount){  
				pageList = remainList.subList(start,totalCount); 
			} else if (page<pageCount) {
				pageList = remainList.subList(start,start+pageSize); 
			}
		}
		if(rq.getPage()==1){//首次进来加载 置顶 + 列表 专享卡，分页只加载列表专享卡
			resultList.addAll(topList);
		}	
		resultList.addAll(pageList);
		return resultList;
	}
//////////////////////////////////////////////////////////////////////////////////////
	

 /**排除相同额度的套餐
 * @param quotas
 * @param amount
 * @return
 */
private boolean isExistAmount(List<Map<Object,Object>> quotas,String amount) {
	 Boolean isExist =false;
	for(int i=0;i<quotas.size();i++){
		if (quotas.get(i).containsValue(amount)) {
			isExist=true;
			break;
		}
	} 
		
	 return isExist;
 }


	/** 
	 * 领取专项卡
	 */
	public Object getCardSeller(GetCardSellerRquest rq) {
		/* 验证用户 */
		String uid = sessionTokenService.getStringForValue(rq.getSessiontoken()) + "";
		//uid ="606673";
		if (org.apache.commons.lang.StringUtils.isEmpty(uid) || "null".equalsIgnoreCase(uid)) {
			return new BaseResponse(ResponseCode.TOKENERR, "token已失效,请重新登录");
		}

		Map<Object, Object> resultMap = new HashMap<Object, Object>();
		String key = "getDebitCardkey_";
		// 采用redis incr函数初始化值 保证领取操作只有一个执行
		Long resultNum = stringRedisTemplate.opsForValue().increment(key, 1);
		log.info("领取专享卡,任务执行次数" + resultNum);
		try {
			if (resultNum > 1) {
				return new BaseResponse(ResponseCode.FAILURE, "领取繁忙，稍后再试!");
			}
			Integer chargeId = rq.getRechargeId();
			// 根据id查询充值套餐
			Map<Object, Object> chargeMap = userPayBirdCoinDao.getRechargeComboById(Long.parseLong(chargeId.toString()));
			if (chargeMap == null || chargeMap.size() == 0) {
				return new BaseResponse(ResponseCode.FAILURE, "该充值套餐不存在！");
			}
			// 根据专项卡id查询判断是否有效
			DebitcardSeller debitseller = debitcardSellerDao.selectDebitcardByid(rq.getDebitId());
			if (debitseller == null) {
				return new BaseResponse(ResponseCode.FAILURE, "该专项卡不存在！");
			} else {
				String rechargItems = debitseller.getRechargeItem();
				if (StringUtils.isNotBlank(rechargItems)) {
					Boolean flag = false;
					String[] recharges = rechargItems.split(",");
					for (String str : recharges) {
						if (str.equals(chargeId.toString())) {
							flag = true;
							break;
						}
					}

					if (!flag) {
						return new BaseResponse(ResponseCode.FAILURE, "该专项卡跟充值套餐匹配错误！");
					}
				}

			}

			Seller seller = sellerDao.querySellerBySellerid(Long.parseLong(rq.getEntry_sellerid().toString()));
			if (seller == null) {
				log.info(uid + "领取专享卡商家sellerid信息错误！");
				return new BaseResponse(ResponseCode.FAILURE, "该专享卡入口商家不存在!");
			}
			// 获取用户的钱包信息
			Map<String, Object> userLiveMap = liveGiftsInfoService.getLiveWalletBlance(uid);
			String zbalanceCoinStr = ((userLiveMap.get("zbalanceCoin") == null ? 0 : userLiveMap.get("zbalanceCoin"))).toString();
			/*String availableExchangeCoinStr = ((userLiveMap.get("availableExchangeCoin") == null ? 0:userLiveMap.get("availableExchangeCoin"))).toString();
			String usedExchangeCoinStr = ((userLiveMap.get("usedExchangeCoin") == null ?0:userLiveMap.get("usedExchangeCoin"))).toString();*/
			/*钱包剩余鸟币余额：鸟币余额-限制鸟币数额*/
			BigDecimal zbalanceCoin = new BigDecimal(zbalanceCoinStr);
			/*可用于兑换储值卡的鸟币额(实际兑换时，需结合zbalance值使用)
			BigDecimal availableExchangeCoin = new BigDecimal(availableExchangeCoinStr);
			已用于兑换储值卡的鸟币额
			BigDecimal usedExchangeCoin = new BigDecimal(usedExchangeCoinStr);
			钱包剩余用于兑换鸟币余额
			BigDecimal subEnableExchangeCoin = availableExchangeCoin.subtract(usedExchangeCoin);
			套餐额度*/
			BigDecimal amount = new BigDecimal(chargeMap.get("rech_amount").toString());
			/*if (zbalanceCoin.compareTo(subEnableExchangeCoin) >= 0) {
				zbalanceCoin = subEnableExchangeCoin;
			}*/
			if (zbalanceCoin.compareTo(amount) < 0) {
				//余额不足
				resultMap.put("isEnoughPay", "0");
				resultMap.put("debitId", rq.getDebitId());// 储值卡Id
				resultMap.put("entry_sellerid", rq.getEntry_sellerid());// 需要充值的商家id
				MapResponse response = new MapResponse(ResponseCode.SUCCESS, "领取操作信息");
				response.setResponse(resultMap);
				return response;
			} else {
				// 更新用户钱包
			/*	Map<String, String> paramMap = new HashMap<String, String>();
				paramMap.put("uid", uid);
				paramMap.put("rtype", "26");
				paramMap.put("option", "1");
				paramMap.put("zbalance", amount.toString());// 使用的鸟币
				paramMap.put("usedCoin", amount.toString());// 兑换的鸟币
				ResponseData responseData = this.getOprationUpdateLiveWallet(paramMap);
				if (responseData.getState() != 0) {
					log.info(uid + "领取专享卡更新钱包出错");
					return new BaseResponse(ResponseCode.FAILURE, "未知错误,请联系管理员");
				}*/
				Map<Object, Object> liveMap = liveUserDao.queryLiverInfoByUid(Integer.parseInt(uid));
				if (liveMap == null || liveMap.size() == 0) {
					log.info(uid + "用户信息出错");
					return new BaseResponse(ResponseCode.FAILURE, "用户不存在");
				}
				
				// 创建订单
				String order_no = "05" + SaasBidType.getBid();
				Map<Object, Object> cardOrder = new HashMap<Object, Object>();
				SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM_dd HH:ss:mm");
				Map<Object,Object> rankMap = getFansRankMap(amount);
				if (rankMap==null || rankMap.isEmpty()) {
					return new BaseResponse(ResponseCode.FAILURE,"兑换额度无对应粉丝等级");
				}		
				//红包比例
				BigDecimal rate = new BigDecimal(rankMap.get("conversion_rate").toString());
				BigDecimal resultBigcimal = amount.multiply(rate);
				//最终获取的鸟币额度
				BigDecimal lastAmount = amount.add(resultBigcimal).setScale(2, BigDecimal.ROUND_HALF_UP);			
				// 设置参数
				cardOrder.put("order_no", order_no); // 订单号
				cardOrder.put("uid", uid);// 用户id
				cardOrder.put("debitcard_id", rq.getDebitId());//
				cardOrder.put("sellertype", debitseller.getSellertype());
				cardOrder.put("sellerid", debitseller.getSellerid());
				cardOrder.put("sellername", debitseller.getSellername());
				cardOrder.put("entry_sellerid", rq.getEntry_sellerid());
				cardOrder.put("entry_sellername", seller.getSellername());
				cardOrder.put("pay_coin", amount);
				cardOrder.put("denomination", lastAmount);
				cardOrder.put("pay_state", 0);
				cardOrder.put("entry_selleragio", seller.getAgio());
				cardOrder.put("create_time", sp.format(new Date()));
				cardOrder.put("pay_time", sp.format(new Date()));
				cardOrder.put("seller_income",amount.multiply(new BigDecimal(0.7)).setScale(2, BigDecimal.ROUND_HALF_UP));
				cardOrder.put("ledger_level", rankMap.get("fans_rank_id"));
				cardOrder.put("uid_relation_chain", liveMap.get("uid_relation_chain"));
				cardOrder.put("uname", liveMap.get("nname"));
				cardOrder.put("phone", liveMap.get("phone"));
				////////////////////////////////////////////////////////////
				// 获取用户对应的商家储值卡
				Map<String, String> sellerCardParamMap = new HashMap<String, String>();
				sellerCardParamMap.put("uid", uid);
				sellerCardParamMap.put("sellerid", debitseller.getSellerid().toString());
				Map<String, String> sellerCardMap = liveGiftsInfoService.getSellerCardBlanceInfo(sellerCardParamMap);
				if (sellerCardMap != null && !sellerCardMap.isEmpty()) {
						// 订单存入储值卡相关参数
						cardOrder.put("q_quota", sellerCardMap.get("quota"));// 充值前额度
						cardOrder.put("quota", lastAmount);// 本次额度
				} else {
					// 没有充值记录 则查询商户开通储值卡的信息记录 返回储值卡配置的商家(单店，连锁店，区域代理)信息
					if (debitseller != null) {
						cardOrder.put("quota", lastAmount);// 本次累计额度
						cardOrder.put("q_quota", 0);// 充值前额度

					}
				}
				///////////////////////////////////////////////////////////////
				// 保存订单到debitcard_order中
				debitcardSellerDao.insertDebitcardOrder(cardOrder);
				
				// 更新储值卡商家额度信息
				Map<String, String> cardMap = new HashMap<String, String>();
				cardMap.put("orderNo", order_no);
				cardMap.put("payState", "1");		
				ResponseData responseData2 = this.UpdateUserDebitQuotaAndAward(cardMap);
				if (responseData2 == null || responseData2.getState() != 0) {
					log.info(uid + "调用业务专享卡出错");
					return new BaseResponse(ResponseCode.FAILURE, "未知错误,请联系管理员");
				}
				resultMap.put("isEnoughPay", "1");// 兑换成功
				resultMap.put("debitId", rq.getDebitId());// 储值卡Id
				resultMap.put("entry_sellerid", rq.getEntry_sellerid());// 需要充值的商家id
				MapResponse response = new MapResponse(ResponseCode.SUCCESS, "领取操作信息");
				response.setResponse(resultMap);
				return response;
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.info("获取用户钱包失败" + uid);
			return new BaseResponse(ResponseCode.FAILURE, "领取专享卡出错");
		} finally {
			stringRedisTemplate.delete(key);
		}

	}

	
	/**
	 * 更新钱包操作
	 * @param uid 
	 * @return map
	 * */
	public ResponseData getOprationUpdateLiveWallet(Map<String, String> paramMap) throws Exception{
		LiveWalletService.Client client = null;
		try {
			TMultiplexedProtocol tMultiplexedProtocol = thriftUtil.getProtocol("LiveWalletService");
			client = new Client(tMultiplexedProtocol);
			thriftUtil.openTransport();
			ResponseData responseData =	client.liveWalletOption(paramMap);
			log.info("获取直播钱包成功,用户："+paramMap.get("uid"));
			return responseData;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("更新钱包失败"+paramMap);
			throw new Exception("更新钱包失败"+paramMap.get("uid"));
		}finally{
			thriftUtil.coloseTransport();
		}
	}
	
	/**
	 * 领取专享卡创建领取记录 更新额度信息  
	 * @return map
	 * */
	public ResponseData UpdateUserDebitQuotaAndAward(Map<String, String> map) throws Exception{
		DebitcardService.Client client = null;
		try{
			TMultiplexedProtocol tMultiplexedProtocol = thriftBusinessUtil.getProtocol("DebitcardService");
			client = new DebitcardService.Client(tMultiplexedProtocol);
			thriftBusinessUtil.openTransport();
			ResponseData responseData = client.exchangeDebitcard(map);
			log.info("更新用户储值卡额度成功");
			return responseData;
		} catch (Exception e) {
			log.info("更新用户储值卡额度失败");
			e.printStackTrace();
		} finally {
			thriftBusinessUtil.coloseTransport();
		}
		return null;
		
	}



	
	/**
	 * 领取成功、充值成功页面
	 */
	public Object showSuccessDebitCard(DebitCardSucessRquest rq) {
		/*验证用户*/
		String uid = sessionTokenService.getStringForValue(rq.getSessiontoken()) + "";
		//uid="606673";
		if (org.apache.commons.lang.StringUtils.isEmpty(uid) || "null".equalsIgnoreCase(uid)) {
			return new BaseResponse(ResponseCode.TOKENERR, "token已失效,请重新登录");
		}
		//判断类型0：充值成功，1：领取成功
		   Integer chargeId = rq.getRecharegeId();
		//根据id查询充值套餐

		   Map<Object,Object> chargeMap = userPayBirdCoinDao.getRechargeComboById(Long.parseLong(chargeId.toString()));
        if (chargeMap==null || chargeMap.isEmpty()){
            return new BaseResponse(ResponseCode.FAILURE, "该充值套餐不存在！");
        }
            Map<Object,Object> resultMap = new HashMap<Object,Object>();
		try {
			String remark = propertiesUtil.getValue("cardDescription", "conf_common.properties");
			resultMap.put("remark", remark);
			if (rq.getType() == 0) {
				Integer result = 0;
				resultMap.put("isEnoughPay", result);// 0不足，1足额		
				// 判断是否大于最低鸟币余额
				Map<String, Object> userLiveMap = liveGiftsInfoService.getLiveWalletBlance(uid);
				if (userLiveMap == null || userLiveMap.isEmpty()) {
					return new BaseResponse(ResponseCode.FAILURE, "该用户不存在！");
				}
				BigDecimal zbalanceCoin = new BigDecimal(userLiveMap.get("zbalanceCoin").toString());
		/*		BigDecimal availableExchangeCoin = new BigDecimal(userLiveMap.get("availableExchangeCoin").toString());
				BigDecimal usedExchangeCoin = new BigDecimal(userLiveMap.get("usedExchangeCoin").toString());
				BigDecimal subEnableExchangeCoin = availableExchangeCoin.subtract(usedExchangeCoin);
				if (zbalanceCoin.compareTo(subEnableExchangeCoin) >= 0) {
					zbalanceCoin = subEnableExchangeCoin;
				}*/
			// 对比最低鸟币
			Map<Object,Object> amoutMap = debitcardSellerDao.findMinRechargeAmount();
			String minAmount =(amoutMap.get("rech_amount")==null?0:amoutMap.get("rech_amount")).toString();
			BigDecimal minAount = new BigDecimal(minAmount);
			if(zbalanceCoin.compareTo(minAount)>=0){
				result=1;		
			}
			resultMap.put("isEnoughPay",result);//0不足，1足额
		
			BigDecimal amount_rech = new BigDecimal ( chargeMap.get("rech_amount").toString());
			amount_rech = amount_rech.multiply(new BigDecimal(10));	
			resultMap.put("rechargeAmout", amount_rech); //充值成功页面的鸟豆
            //判断是否有正在进行的直播
			//查询充值卡店铺相关的店铺ID
			List<String> ids = getSubSellerIds(rq.getEntry_sellerid().toString(), rq.getSellerType());
			//默认没有直播店铺
			resultMap.put("isLive",0);//是否存在直播 0 无  1 有
			
			if(ids != null && ids.size() > 0){
				//通过关联店铺的ID查询是否存在正在直播的店铺
				int counts = anchorLiveRecordDao.sumSellerCountsIsLive(ids);
				if(counts > 0){
					resultMap.put("isLive",1);//是否存在直播 0 无  1 有
				}
			}
			MapResponse response = new MapResponse(ResponseCode.SUCCESS, "充值成功信息");
			response.setResponse(resultMap);
			return response;
		}
		if (rq.getType()==1){
			//领卡成功
			resultMap.put("rechargeAmout", chargeMap.get("rech_amount"));
			Seller seller = sellerDao.querySellerBySellerid(Long.parseLong(rq.getEntry_sellerid().toString()));
			if (seller==null) {
				return new  BaseResponse(ResponseCode.FAILURE, "该商家不存在！");
			}
			String sdate = seller.getSdate();
			if (sdate==null){
				//为空默认早上8点到晚上10点
				sdate="8:00-22:00";
			}
			
			String title = propertiesUtil.getValue("debitcard_title", "conf_common.properties");
			String content =propertiesUtil.getValue("debitcard_content", "conf_common.properties");	
			String imageurl=propertiesUtil.getValue("debitcard_imageUrl", "conf_common.properties");		
			resultMap.put("title", title);
			resultMap.put("content", content);
			resultMap.put("Imgurl", fileUrl+imageurl);
			resultMap.put("url", localDomain+"/sellerCard/debitBanner?type=0");
			
			//根据套餐额度匹配粉丝等级
			Map<Object,Object> rankMap = getFansRankMap(new BigDecimal(chargeMap.get("rech_amount").toString()));		
			if (rankMap == null || rankMap.isEmpty()) {
				return new BaseResponse(ResponseCode.FAILURE,"该充值套餐对应的粉丝等级不存在！");
			}
			//红包比例
			BigDecimal rate = new BigDecimal(rankMap.get("conversion_rate").toString());
			//计算金额
			BigDecimal rechamount = new BigDecimal(chargeMap.get("rech_amount").toString());
			BigDecimal doubleAmout=rechamount.multiply(new BigDecimal(2));
			BigDecimal resultBigcimal = rechamount.multiply(rate);
			BigDecimal amount = rechamount.add(resultBigcimal).setScale(2, BigDecimal.ROUND_HALF_UP);
			BigDecimal remainAmout = doubleAmout.subtract(amount).setScale(2, BigDecimal.ROUND_HALF_UP);
			resultMap.put("amount",amount);
			resultMap.put("remainAmout",remainAmout);
			//判断店铺是否在营业时间，是否超过当天的额度
			resultMap.put("isSell", 0);//不营业
			if (StringUtils.isNotBlank(sdate)) {
				SimpleDateFormat sp = new SimpleDateFormat("HH:mm");
				String currentTime = sp.format(new Date());
				if(isInTime(sdate,currentTime)){
					resultMap.put("isSell", 1);
				};
		
			};
				Map<Object, Object> dayMap = billDao.queryDayOrderAmountBySellerId(Long.valueOf(rq.getEntry_sellerid().toString()));
				resultMap.put("isPay", 1);//默认没有超额
				if (dayMap != null) {
					if (dayMap.get("dayMoney") != null && new BigDecimal(dayMap.get("dayMoney").toString()).compareTo(seller.getDailyLimitTurnover()) >= 0) {
						resultMap.put("isPay", 0); // 0限额超过，1限额没有超过
					}
				}
				// 查询商家订单单日下单总额 限制消费
				Map<Object, Object> totalMap = billDao.queryTotalOrderAmountBySellerId(Long.valueOf(rq.getEntry_sellerid().toString()));
				if (totalMap != null) {
					if (totalMap.get("totalMoney") != null && new BigDecimal(totalMap.get("totalMoney").toString())
							.compareTo(seller.getTotalLimitTurnover()) >= 0) {
						resultMap.put("isPay", 0);
					}
				}
				resultMap.put("entry_sellerid", rq.getEntry_sellerid());
				// 响应
				MapResponse response = new MapResponse(ResponseCode.SUCCESS, "领取成功信息");
				response.setResponse(resultMap);
				return response;
		}
            }catch(Exception e){
            	e.printStackTrace();
            	log.info("充值成功、领取成功页面报错！");
            }	
		return null;
	}
	
	private Map<Object, Object> getFansRankMap(BigDecimal  amount) {
		//根据实际的充值金额匹配粉丝等级
		Map<Object,Object> param = new HashMap<Object,Object>();
		param.put("amount", amount);
		Map<Object,Object> result = debitcardSellerDao.findFansRankByAmout(param);
		return result;
	}

	/**
	 * 判断某一时间是否在一个区间内 
	 * @param sourceTime 时间区间,半闭合,如[10:00-20:00)
	 * @param curTime 需要判断的时间 如10:00
	 * @return 
	 */
	public  boolean isInTime(String sourceTime, String curTime) {
	    String[] args = sourceTime.split("-");
	    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
	    try {
	        long now = sdf.parse(curTime).getTime();
	        long start = sdf.parse(args[0]).getTime();
	        long end = sdf.parse(args[1]).getTime();
	        if (args[1].equals("00:00")) {
	            args[1] = "24:00";
	        }
	        if (end < start) {
	            if (now >= end && now < start) {
	                return false;
	            } else {
	                return true;
	            }
	        } 
	        else {
	            if (now >= start && now < end) {
	                return true;
	            } else {
	                return false;
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return false;
	}
	
	/**
	 * 获取充值卡店铺的相关店铺ID
	 * @param sellerid
	 * @param sellerType
	 * @return
	 */
	private List<String> getSubSellerIds(String sellerid,Integer sellerType){
		try{
			String sellerids = sellerDao.selectSubSellersBySellerId(sellerid, sellerType);
			if(StringUtils.isNotEmpty(sellerids)){
				
				List<String> ids = new ArrayList<String>();
				
				String[] arr = sellerids.split(",");
				
				for(String s : arr){
					ids.add(s.trim());
				}
				return ids;
			}
		}catch(Exception e){
			e.printStackTrace();
			log.info("查询充值卡店铺相关ID异常");
		}
		return null;
	}



	
	/** 
	 * 专享卡内容提示框
	 */
	public Object showcardTip(TipRequest rq) {
		/* 验证用户 */
		String uid = sessionTokenService.getStringForValue(rq.getSessiontoken()) + "";
		//uid="660783";
		if (org.apache.commons.lang.StringUtils.isEmpty(uid) || "null".equalsIgnoreCase(uid)) {
			return new BaseResponse(ResponseCode.TOKENERR, "token已失效,请重新登录");
		}
		Map<Object, Object> resultMap = new HashMap<Object, Object>();
		try {
			if (rq.getType() == 0) {
				//专享卡活动提示框
				String tip = propertiesUtil.getValue("activity_tip", "conf_common.properties");
				resultMap.put("tip", tip);

			} else {
				//专享卡http协议提示框
				String tip = propertiesUtil.getValue("http_tip", "conf_common.properties");
				resultMap.put("tip", tip);
			}
			// 响应
			MapResponse response = new MapResponse(ResponseCode.SUCCESS, "提示内容");
			response.setResponse(resultMap);
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("读取文件出错");
		}
		return null;
	}



	
}
