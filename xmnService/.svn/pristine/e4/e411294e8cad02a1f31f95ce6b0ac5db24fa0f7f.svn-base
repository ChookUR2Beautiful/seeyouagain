package com.xmniao.xmn.core.live.service;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.catehome.service.NewXmnHomeService;
import com.xmniao.xmn.core.common.Constant;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.live.GiftsInfoRequest;
import com.xmniao.xmn.core.common.request.live.LiveGiftsListRequest;
import com.xmniao.xmn.core.common.request.sellerPackage.SellerPackageBuyRequest;
import com.xmniao.xmn.core.live.dao.AnchorLiveRecordDao;
import com.xmniao.xmn.core.live.dao.LiveGiftsInfoDao;
import com.xmniao.xmn.core.live.dao.LiveUserDao;
import com.xmniao.xmn.core.live.entity.LiveGiftsInfo;
import com.xmniao.xmn.core.live.entity.LiverInfo;
import com.xmniao.xmn.core.market.dao.ProductBillDao;
import com.xmniao.xmn.core.market.dao.ProductInfoDao;
import com.xmniao.xmn.core.market.dao.SaleGroupDao;
import com.xmniao.xmn.core.market.entity.cart.SaleGroup;
import com.xmniao.xmn.core.market.entity.pay.OrderPriceList;
import com.xmniao.xmn.core.market.entity.pay.ProductBill;
import com.xmniao.xmn.core.market.entity.pay.ProductInfo;
import com.xmniao.xmn.core.market.entity.pay.ReceivingAddress;
import com.xmniao.xmn.core.market.service.pay.MarketOrderService;
import com.xmniao.xmn.core.market.service.pay.impl.MarketOrderServiceImpl;
import com.xmniao.xmn.core.personal.dao.ReceivingAddressDao;
import com.xmniao.xmn.core.seller.entity.Seller;
import com.xmniao.xmn.core.sellerPackage.dao.SellerPackageDao;
import com.xmniao.xmn.core.sellerPackage.dao.SellerPackageOrderDao;
import com.xmniao.xmn.core.sellerPackage.entity.SellerPackage;
import com.xmniao.xmn.core.sellerPackage.service.SellerPackageBuyService;
import com.xmniao.xmn.core.thrift.ResponseData;
import com.xmniao.xmn.core.thrift.SynthesizeService.AsyncProcessor.returnWithdrawals;
import com.xmniao.xmn.core.thrift.business.java.MallOrderService;
import com.xmniao.xmn.core.thrift.business.java.SellerPackageService;
import com.xmniao.xmn.core.thrift.client.proxy.ThriftClientProxy;
import com.xmniao.xmn.core.util.ArithUtil;
import com.xmniao.xmn.core.util.DateUtil;
import com.xmniao.xmn.core.util.PropertiesUtil;
import com.xmniao.xmn.core.util.ThriftBusinessUtil;
import com.xmniao.xmn.core.util.ThriftUtil;
import com.xmniao.xmn.core.xmer.dao.SellerDao;



/**
 * 项目描述：XmnService
 * API描述：直播界面赠送 实体商品类的  礼物
 * @author yhl
 * 创建时间：2016年8月10日16:53:55
 * @version 
 * */

@Service
public class LiveSendProductGiftsService {
	
	private final Logger log = Logger.getLogger(LiveSendProductGiftsService.class);
	
	/**
	 * 直播礼物Dao
	 * */
	@Autowired
	private LiveGiftsInfoDao liveGiftsInfoDao;
	
	/**
	 * 用户基础dao
	 * */
	@Autowired
	private LiveUserDao liveUserDao;
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;  
	
	/**
	 * 直播观看记录Dao
	 * */
	@Autowired
	private AnchorLiveRecordDao anchorLiveRecordDao;
	
	@Autowired
	private String fileUrl;
	
	/**
	 * 直播redis service
	 * */
	@Autowired
	private AnchorViewerMemberRankService anchorViewerMemberRankService;
	
	@Autowired 
	private SelfGiftService selfGiftService;
	
	/**
	 * sessionTokenService
	 * */
	@Autowired
	private SessionTokenService sessionTokenService;
	
	/**
	 * 商家套餐DAO
	 * */
	@Autowired
	private SellerPackageDao sellerPackageDao;
	
	
	/**
	 * 寻蜜鸟钱包： 支付服务
	 * */
	@Resource(name = "synthesizeServiceClient")
	private ThriftClientProxy synthesizeServiceClient;
	
	@Autowired
	private ThriftUtil thriftUtil;
	
	@Autowired
	private ThriftBusinessUtil thriftBusinessUtil;
	
	/**
	 * 注入propertiesUtil
	 */
	@Autowired
	private PropertiesUtil propertiesUtil;
	
	/**
	 * 注入tlsSendImService
	 */
	@Autowired
	private TlsSendImService tlsSendImService;
	
	/**
	 * 注入liveGiftsInfoService
	 */
	@Autowired
	private LiveGiftsInfoService liveGiftsInfoService;
	
	/**
	 * 注入sellerPackageBuyService  套餐下单类
	 */
	@Autowired
	private SellerPackageBuyService sellerPackageBuyService;
	
	/**
	 * 注入sellerPackageBuyService  
	 */
	@Autowired
	private MarketOrderService marketOrderService;
	
	/**
	 * 注入MarketOrderServiceImpl  
	 */
	@Autowired
	private MarketOrderServiceImpl marketOrderServiceImpl;
	
	
	/**
	 * 注入sellerDao  商家DAO
	 */
	@Autowired
	private SellerDao sellerDao;
	
	/**
	 * 注入ProductInfoDao  商品DAO
	 */
	@Autowired
	private ProductInfoDao productInfoDao;
	
	/**
	 * 注入ProductInfoDao  商品DAO
	 */
	@Autowired
	private SellerPackageOrderDao sellerPackageOrderDao;
	
	@Autowired
	private NewXmnHomeService newXmnHomeService;
	
	/**
	 * 规格 dao
	 * */
	@Autowired
	private SaleGroupDao saleGroupDao;
	
	/**
	 * 规格 dao
	 * */
	@Autowired
	private ProductBillDao productBillDao;
	
	/**
	 * 收货地址
	 * */
	@Autowired
	private ReceivingAddressDao receivingAddressDao;
	
	
	/**
	 * 获取套餐钱验证钱包信息
	 * @param liveGiftsListRequest
	 * @return MapResponse
	 * */
	public MapResponse validateUserWallet(LiveGiftsListRequest liveGiftsListRequest){
		BigDecimal zbalance = new BigDecimal(0);
		try {
			// 获取商品礼物前 先检查有无鸟币
			Map<String, Object> userliveWallerMap =  liveGiftsInfoService.getLiveWalletBlance(liveGiftsListRequest.getUid());
			if (userliveWallerMap!=null && userliveWallerMap.size()>0) {
				zbalance = new BigDecimal(userliveWallerMap.get("zbalanceCoin").toString()) ;
				if (zbalance.compareTo(new BigDecimal(0))<=0) {
					return new MapResponse(ResponseCode.FAILURE, "鸟币余额不足,可打赏获得更多鸟币哦") ;
				}else {
					
					//获取礼物配置  计算观众是否能发的起礼物
					if (liveGiftsListRequest.getGiftId()>0) {
						LiveGiftsInfo giftsInfo = liveGiftsInfoDao.getGiftsInfoByGiftId(liveGiftsListRequest.getGiftId());
						if (giftsInfo!=null) {
							BigDecimal price = giftsInfo.getGift_price();
							if (price.compareTo(zbalance)>0) {
								return new  MapResponse(ResponseCode.FAILURE, "鸟币余额不足,可打赏获得更多鸟币哦") ;
							}else {
								return new  MapResponse(ResponseCode.SUCCESS, "成功") ;
							}
						}else {
							return new  MapResponse(ResponseCode.FAILURE, "礼物不存在或已下架") ;
						}
					}
				}
			}else {
				new MapResponse(ResponseCode.FAILURE, "未获取到钱包信息") ;
			}
			
		} catch (Exception e) {
			log.info("获取直播钱包失败: "+liveGiftsListRequest.getUid());
			e.printStackTrace();
			return new MapResponse(ResponseCode.FAILURE, "直播钱包获取异常") ;
		}
		return new MapResponse(ResponseCode.SUCCESS, "操作成功") ;
	}
	
	
	
	/**
	 * 获取套餐礼物列表
	 * @param LiveGiftsListRequest
	 * @return Object
	 * */
	public Object querySellerPackageGiftList(LiveGiftsListRequest liveGiftsListRequest){
		
//		String uid = "339586";
		//验证token
		String uid = sessionTokenService.getStringForValue(liveGiftsListRequest.getSessiontoken()) + "";
		if (StringUtils.isEmpty(uid) || "null".equalsIgnoreCase(uid)) {
			return new BaseResponse(ResponseCode.TOKENERR, "token已失效,请重新登录");
		}
		liveGiftsListRequest.setUid(uid);
		MapResponse responseWallet = this.validateUserWallet(liveGiftsListRequest);
		if(responseWallet.getState() == 300){
			return new MapResponse(ResponseCode.FAILURE, responseWallet.getInfo()) ;
		}
		
		MapResponse response = null;
		
		//返回集合
		Map<Object, Object> resultMap = new HashMap<Object, Object>();
		
		Map<Object, Object> paramMap = new HashMap<Object, Object>();
		
		try {
		
			//如果当前商家 和重点推荐的不足十个  继续查询补足
			int sendProductGiftListNum = Integer.parseInt(propertiesUtil.getValue("sendProductGiftListNum", "conf_common.properties"));
			
			paramMap.put("page", liveGiftsListRequest.getPage());
			paramMap.put("limit",sendProductGiftListNum);
			
			List<SellerPackage> sellerPackageRoomList = new ArrayList<SellerPackage>();
			
			List<Integer> sellerIdList = new ArrayList<Integer>();
			
			//如果有传入商家ID 则优先查询商家下的推荐套餐
//			if (liveGiftsListRequest.getSellerId()>0) {
				paramMap.put("sellerId", liveGiftsListRequest.getSellerId());
				paramMap.put("position", 0);
				sellerPackageRoomList = sellerPackageDao.queryPackageRecommendList(paramMap);
				
				if (sellerPackageRoomList.size()>=sendProductGiftListNum) {
					resultMap.put("sellerpackageList",sellerPackageRoomList);
				}else {
					
					//组装需要排除的套餐ID 查询我附近的套餐
					int addNum = sendProductGiftListNum-sellerPackageRoomList.size();
					for (int i = 0; i < sellerPackageRoomList.size(); i++) {
						SellerPackage sellerPackage = sellerPackageRoomList.get(i);
						sellerIdList.add(sellerPackage.getId());
						
						sellerPackageRoomList.get(i).setPicUrl(fileUrl+sellerPackage.getPicUrl());
					}
					
					paramMap.put("lon", liveGiftsListRequest.getLon());
					paramMap.put("lat", liveGiftsListRequest.getLat());
					paramMap.put("page", liveGiftsListRequest.getPage());
//					paramMap.put("cityId", liveGiftsListRequest.getCityId());
					paramMap.put("limit", addNum);
					if(sellerIdList.size()>0){
						paramMap.put("list", sellerIdList);
					}
					
					//查询附近的其他商家的套餐
					//查询推荐套餐
					List<Map<Object,Object>> recommendCombos = sellerPackageDao.queryMynearbySellerPackage(paramMap);
					
					if (recommendCombos.size()>0) {
						for (int i = 0; i < recommendCombos.size(); i++) {
							Map<Object, Object> sellerCombos = recommendCombos.get(i);
							SellerPackage sellerPackage = new SellerPackage();
							sellerPackage.setId(Integer.parseInt(sellerCombos.get("id").toString()) );
							sellerPackage.setSellerid(Integer.parseInt(sellerCombos.get("sellerid").toString()) );
							sellerPackage.setSellingCoinPrice(new BigDecimal(sellerCombos.get("coinprice").toString()) );
							sellerPackage.setTitle(sellerCombos.get("comboname").toString() ) ;
							sellerPackage.setSellername(sellerCombos.get("sellername").toString() ) ;
							sellerPackage.setPicUrl(fileUrl+sellerCombos.get("comboimage").toString());
							sellerPackageRoomList.add(sellerPackage);
						}
					}
				}
				resultMap.put("sellerpackageList", sellerPackageRoomList);
//			}
			
			response = new MapResponse(ResponseCode.SUCCESS, "查询成功") ;
			response.setResponse(resultMap);
			
		} catch (Exception e) {
			log.info("查询套餐礼物失败");
			e.printStackTrace();
			return new MapResponse(ResponseCode.FAILURE, "查询套餐礼物失败") ;
		}
		return response;
	}
	
	
	/**
	 * 获取商城商品礼物列表
	 * */
	public Object queryMarketProductGiftList(LiveGiftsListRequest liveGiftsListRequest){
		
//		String uid = "339586";
		//验证token
		String uid = sessionTokenService.getStringForValue(liveGiftsListRequest.getSessiontoken()) + "";
		if (StringUtils.isEmpty(uid) || "null".equalsIgnoreCase(uid)) {
			return new BaseResponse(ResponseCode.TOKENERR, "token已失效,请重新登录");
		}
		
		//验证钱包鸟币金额
		liveGiftsListRequest.setUid(uid);
		MapResponse responseWallet = this.validateUserWallet(liveGiftsListRequest);
		if(responseWallet.getState() == 300){
			return new MapResponse(ResponseCode.FAILURE, responseWallet.getInfo()) ;
		}
		
		MapResponse response = null;
		//返回集合
		Map<Object, Object> resultMap = new HashMap<Object, Object>();
		
		Map<Object, Object> paramMap = new HashMap<Object, Object>();
		try {
			paramMap.put("page", liveGiftsListRequest.getPage());
			paramMap.put("limit", Integer.parseInt(propertiesUtil.getValue("sendProductGiftListNum", "conf_common.properties")));
			paramMap.put("fileUrl", fileUrl);
			paramMap.put("giftId", liveGiftsListRequest.getGiftId());
			
			//获取当前商品礼物  排序按照 售价降序排列
			List<Map<Object, Object>> productMaps = liveGiftsInfoDao.queryLiveMarketProducts(paramMap);
			resultMap.put("productList", productMaps);
			response = new MapResponse(ResponseCode.SUCCESS, "查询成功") ;
			response.setResponse(resultMap);
			
		} catch (Exception e) {
			log.info("查询套餐礼物失败");
			e.printStackTrace();
			return new MapResponse(ResponseCode.FAILURE, "查询套餐礼物失败") ;
		}
		return response;
	}
	
	/**
	 * 描述:用户送礼物,发送实体商品礼物, 1 套餐,2 商城商品
	 * @param GiftsInfoRequest giftsInfoRequest
	 * @return  object 
	 * */
	public Object sendProductGift(GiftsInfoRequest giftsInfoRequest,String liverKey){
		
		//获取当前用户的redis 
		String liver_key = "liver_"+Long.valueOf(giftsInfoRequest.getUid());
		Map<Object, Object>  liverMap= stringRedisTemplate.opsForHash().entries(liver_key);
		
		//获取礼物信息
		LiveGiftsInfo giftsInfo = liveGiftsInfoDao.getGiftsInfoByGiftId(giftsInfoRequest.getSourceId());
		
		LiverInfo liverInfo  = null;
		if (liverMap != null) {
			liverMap.put("live_record_id", giftsInfoRequest.getLiveRecordId());
			liverMap.put("get_experience_type", "1");
			//获取主播信息 拿到群组ID
			liverInfo  = liveUserDao.queryLiverInfoByAnchorId(Integer.parseInt(giftsInfoRequest.getAnchorId()));
			if (liverInfo == null) {
				log.info("获取直播间主播信息异常："+giftsInfoRequest.getAnchorId());
				return new MapResponse(ResponseCode.FAILURE,"操作异常");
			}
			//检查主播有没有同步鸟蛋
			if (stringRedisTemplate.hasKey(Constant.SEND_LIVE_REDPACKET_PROCESS+"_"+liverInfo.getUid()) == true) {
				return new BaseResponse(ResponseCode.LIVE_SEND_GIFTS_FILTER, "系统繁忙");
			}
		}else {
			log.info("获取用户信息失败："+liver_key);
			return new MapResponse(ResponseCode.FAILURE,"未能获取到用户信息");
		}
		
		//商家套餐礼物逻辑
		if (giftsInfoRequest.getGiftType()==1) {
			return this.sendSellerPackageGift(giftsInfoRequest,giftsInfo,liverMap,liverInfo,liverKey);
		}
		
		//发送商城鸟币礼物
		if (giftsInfoRequest.getGiftType()==2) {
			return this.sendMarketProductGift(giftsInfoRequest,giftsInfo,liverMap,liverInfo,liverKey);
		}
		return new BaseResponse(ResponseCode.FAILURE, "系统繁忙");
	}
	
	/**
	 * 描述: 发送套餐礼物处理逻辑
	 * @param giftsInfoRequest(请求参数),liverMap(观众),liverInfo(主播)
	 * */
	public Object sendSellerPackageGift(GiftsInfoRequest giftsInfoRequest,LiveGiftsInfo liveGiftsInfo,Map<Object, Object> liverMap,LiverInfo liverInfo,String liverKey){
		
		//查询商家的套餐信息是否存在 或者是否已售罄
		SellerPackage sellerPackage = sellerPackageDao.querySellerPackageInfoById(Integer.parseInt(giftsInfoRequest.getId()));
		
		if (sellerPackage!=null) {
			
			Seller seller = sellerDao.querySellerBySellerid(sellerPackage.getSellerid().longValue());
			if (seller==null) {
				log.info("未获取到商家信息:"+seller.getSellerid());
				return new BaseResponse(ResponseCode.FAILURE, "未获取到商家信息");
			}
			
			//获取当前套餐的鸟币价格
			BigDecimal sellCoinPrice = sellerPackage.getSellingCoinPrice();
			//当前用户的鸟币
			Map<String, Object> liveWalletMap = new HashMap<String, Object>();
			try {
				//查询当前可用鸟币
				liveWalletMap = liveGiftsInfoService.getLiveWalletBlance(giftsInfoRequest.getUid());
				if (liveWalletMap!=null) {
					//可用鸟币     如果可用鸟币 大于等于 套餐鸟币价格  处理生成订单操作
					BigDecimal zbalanceCoin =  new BigDecimal(liveWalletMap.get("zbalanceCoin")==null?"0":liveWalletMap.get("zbalanceCoin").toString()) ;
					if (zbalanceCoin.compareTo(sellCoinPrice)>=0) { 
						//生成订单
						SellerPackageBuyRequest sellerPackageBuyRequest = new SellerPackageBuyRequest();
//						sellerPackageBuyRequest.setCouponId(-1);
						sellerPackageBuyRequest.setCouponType(-1);
						sellerPackageBuyRequest.setPackageId(Integer.parseInt(giftsInfoRequest.getId()));
						sellerPackageBuyRequest.setAppversion(giftsInfoRequest.getAppversion());
						sellerPackageBuyRequest.setSystemversion(giftsInfoRequest.getSystemversion());
						sellerPackageBuyRequest.setSource(4);
						sellerPackageBuyRequest.setQuantity(1);
						
						//组装订单参数
						Map<Object, Object> orderParamMap = new HashMap<Object, Object>();
						try {
							orderParamMap = sellerPackageBuyService.calculatedFanCouponPrice(sellerPackageBuyRequest, sellerPackage, giftsInfoRequest.getUid().toString(), seller,new ArrayList<Integer>());
							if (orderParamMap!=null && orderParamMap.size()>0) {
								orderParamMap.put("uid", giftsInfoRequest.getUid());
								orderParamMap.put("sellername", seller.getSellername());
								
								//创建订单
								Map<Object, Object> anchorInfoMap = new HashMap<>();
								anchorInfoMap.put("uid", liverInfo.getUid());//'会员编号',
								anchorInfoMap.put("phone", liverInfo.getPhone()); //'会员手机号',
								anchorInfoMap.put("uname", liverInfo.getNname()); //'会员昵称',
								anchorInfoMap.put("uid_relation_chain", null==liverInfo.getUid_relation_chain()?"":liverInfo.getUid_relation_chain()) ;// '会员粉丝等级关系链',
								//创建订单返回结果
								Map<Object, Object> orderResultMap = new HashMap<Object, Object>();
								
								//更新套餐总数量
								Map<Object, Object> paramPackageMap = new HashMap<Object, Object>();
								try {
									
									paramPackageMap.put("nums", 1);
									paramPackageMap.put("id", giftsInfoRequest.getId());
									paramPackageMap.put("buys", 1);//标示买套餐 库存要扣减，销量要加
									int fansNum=sellerPackageDao.modifySellerPackageInfo(paramPackageMap);
									if(fansNum<=0){
										log.info("更新套餐库存失败："+sellerPackageBuyRequest.getPackageId());
										return new BaseResponse(ResponseCode.FAILURE, "更新套餐库存失败");
									}
									//新增订单
									orderResultMap = sellerPackageBuyService.insertSellerPackageOrder(sellerPackageBuyRequest,orderParamMap,seller,sellerPackage,anchorInfoMap,1);
								} catch (Exception e) {
									log.info("创建订单信息异常");
									e.printStackTrace();
									paramPackageMap.put("cancel", 1);//标示买套餐 库存要扣减，销量要加
									int fansNum=sellerPackageDao.modifySellerPackageInfo(paramPackageMap);
									if(fansNum<=0){
										log.info("更新套餐库存失败："+sellerPackageBuyRequest.getPackageId());
										return new BaseResponse(ResponseCode.FAILURE, "更新套餐库存失败");
									}
									
									return new BaseResponse(ResponseCode.FAILURE, "赠送主播套餐券失败");
								}
								
								Map<String, String> paymentMap = new HashMap<String, String>(); 
								paymentMap.put("amount", sellCoinPrice.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
								
								paymentMap.put("base", propertiesUtil.getValue("platformPaybirdCoin", "conf_common.properties"));
								paymentMap.put("discount", seller.getAgio().setScale(2, BigDecimal.ROUND_HALF_UP).toString() );

								try{
									//调起扣除直播鸟币钱包接口
									//扣减钱包鸟币余额
									Map<String, String> substractMap = new HashMap<String, String>();
									substractMap.put("uid", giftsInfoRequest.getUid());
									substractMap.put("rtype", "24");//这里是支付服务的消费类型
									substractMap.put("liveRecordId", giftsInfoRequest.getLiveRecordId());
									substractMap.put("remarks", orderResultMap.get("order_no").toString());//这里是支付服务的消费鸟豆
									substractMap.put("description", sellerPackage.getTitle());
									substractMap.put("option", "1");
									substractMap.put("zbalance", sellerPackage.getSellingCoinPrice().toString());
									substractMap.put("anchorId", liverInfo.getId().toString());
									
									//更新用户钱包余额
									ResponseData subResponseData = null;
									try {
										
										subResponseData =  liveGiftsInfoService.subtractLiveWalletCoinBlance(substractMap);
										if (subResponseData!=null && subResponseData.getState()!=0) {
											log.info("发礼物扣减鸟币失败:"+giftsInfoRequest.getUid());
											return new BaseResponse(ResponseCode.FAILURE, "发送套餐礼物支付鸟币失败");
										}
										
										ResponseData resData = new ResponseData();
										Map<String, String> obMap = new HashMap<String, String>();
										obMap.put("walletRecordId", subResponseData.getResultMap().get("id"));
										resData.setState(0);
										resData.setResultMap(obMap);
										
										//更新订单状态
										Map<String, String> updateOrderMap = new HashMap<String, String>();
										String zeroNum = new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
										updateOrderMap.put("orderNo", orderResultMap.get("order_no").toString());
										updateOrderMap.put("status", "1");
										updateOrderMap.put("uid", liverInfo.getUid().toString());
										updateOrderMap.put("payType", "1000020");
										updateOrderMap.put("totalAmount", sellCoinPrice.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
										updateOrderMap.put("cash", zeroNum);
										updateOrderMap.put("balance", zeroNum);
										updateOrderMap.put("commision", zeroNum);
										updateOrderMap.put("zbalance", zeroNum);
										updateOrderMap.put("beans", sellCoinPrice.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
										updateOrderMap.put("sellerCoin", zeroNum);
										updateOrderMap.put("base", propertiesUtil.getValue("platformPaybirdCoin", "conf_common.properties").toString());
										updateOrderMap.put("discounts", seller.getAgio().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
										try {
											
											//调用业务服务更新订单状态
											SellerPackageService.Client client = null;
											TMultiplexedProtocol tMultiplexedProtocol = thriftBusinessUtil.getProtocol("SellerPackageService");
											client = new SellerPackageService.Client(tMultiplexedProtocol); 
											thriftBusinessUtil.openTransport();
											ResponseData  responseData  =	client.updateSellerPackageOrder(updateOrderMap);
											
											//组装更新订单状态
											LiveGiftsInfo giftsInfo = new LiveGiftsInfo();
											giftsInfo.setGift_nums(1);
											giftsInfo.setGift_name(sellerPackage.getTitle());
											giftsInfo.setGift_price(sellerPackage.getSellingCoinPrice());//原价 鸟币价格
											giftsInfo.setId(liveGiftsInfo.getId());
											giftsInfo.setBackCoinStatus(1);
											giftsInfo.setGiftSource(1);
											giftsInfo.setGift_experience(liveGiftsInfo.getGift_experience());
											giftsInfo.setSaleProductId(sellerPackage.getId());
											giftsInfo.setIsRadio(liveGiftsInfo.getIsRadio());
											
											liverMap.put("percentAmount", 0);
											
											if (responseData.getState()==0) {
												return liveGiftsInfoService.sendBirdCoinGiftsHandle(giftsInfoRequest,liverMap,resData,giftsInfo,liverInfo,liverKey,0,3);
											}else {
												liveGiftsInfoService.sendBirdCoinGiftsHandle(giftsInfoRequest,liverMap,resData,giftsInfo,liverInfo,liverKey,0,1);
												return new BaseResponse(ResponseCode.FAILURE, "更新商城礼物订单失败");
											}
											
										} catch (Exception e) {
											log.info("调用业务服务更新订单失败:"+orderResultMap.get("order_no").toString());
											e.printStackTrace();
											return new BaseResponse(ResponseCode.FAILURE, "更新商城礼物订单失败");
										}
										
									}catch(Exception e){
										log.info("发礼物扣减鸟币失败:"+giftsInfoRequest.getUid());
										e.printStackTrace();
										
										paramPackageMap.put("nums", 1);
										paramPackageMap.put("id", giftsInfoRequest.getId());
										paramPackageMap.put("cancel", 1);//标示买套餐 库存要扣减，销量要加
										sellerPackageDao.modifySellerPackageInfo(paramPackageMap);
										
										return new BaseResponse(ResponseCode.FAILURE, "发送礼物支付鸟币失败");
									}
									
								}catch(Exception e){
									log.info("发套餐礼物支付失败");
									e.printStackTrace();
									return new BaseResponse(ResponseCode.FAILURE, "扣减鸟币失败");
								}
								
							}
						} catch (Exception e) {
							log.info("发送套餐礼物失败:"+giftsInfoRequest.getUid());
							e.printStackTrace();
							return new BaseResponse(ResponseCode.FAILURE, "发送套餐礼物失败");
						}
						
					}else {
						log.info("鸟币不足:"+giftsInfoRequest.getUid());
						return new BaseResponse(ResponseCode.FAILURE, "鸟币余额不足,打赏主播可获得鸟币哦");
					}
				}
				
			} catch (Exception e) {
				log.info("获取直播钱包失败:"+giftsInfoRequest.getUid());
				e.printStackTrace();
				return new BaseResponse(ResponseCode.FAILURE, "获取钱包鸟币余额异常");
			}
			
		}else {
			log.info("没有获取到商家套餐信息");
			return new BaseResponse(ResponseCode.FAILURE, "套餐礼物已售罄或已下架");
		}
		return new BaseResponse(ResponseCode.FAILURE, "发送套餐礼物失败");
	}
	
	
	/**
	 * 描述: 发送商城礼物处理逻辑
	 * */
	public Object sendMarketProductGift(GiftsInfoRequest giftsInfoRequest,LiveGiftsInfo liveGiftsInfo,Map<Object, Object> liverMap,LiverInfo liverInfo,String liverKey){
		
		//商品总价
		BigDecimal orderMoney = new BigDecimal(0);
		
		//当前用户的鸟币
		Map<String, Object> liveWalletMap = new HashMap<String, Object>();
		
		//查询商城礼物配置的基础数据  根据选择商城礼物的ID
		Map<Object, Object> detailMap = new HashMap<Object, Object>();
		detailMap.put("pid", giftsInfoRequest.getId());
		Map<Object, Object> liveGiftDetailMap = liveGiftsInfoDao.queryLiveGiftDetailInfo(detailMap);
		if (liveGiftDetailMap==null || liveGiftDetailMap.size()==0) {
			return new BaseResponse(ResponseCode.FAILURE, "未获取到商品礼物,请重试");
		}
		
		//获取规格
		SaleGroup saleGroup = saleGroupDao.selectByAttr(Integer.parseInt(liveGiftDetailMap.get("codeId").toString()) ,liveGiftDetailMap.get("pvIds").toString());
		if (saleGroup!=null) {
			if (saleGroup.getStock()<=0) {
				return new BaseResponse(ResponseCode.FAILURE, "该礼物库存不足");
			}
		}else {
			return new BaseResponse(ResponseCode.FAILURE, "未获取到商品礼物,请重试");
		}
		
		//获取商品信息  
		ProductInfo productInfo = productInfoDao.selectByCodeId(Integer.parseInt(liveGiftDetailMap.get("codeId").toString()) );
		
		if (productInfo!=null && null!=productInfo.getPstatus() && productInfo.getPstatus() ==1) {
			
			if (productInfo.getStore()<=0) { //productInfo.getSales()!=null &&    productInfo.getSales()>=productInfo.getStore() 
				return new BaseResponse(ResponseCode.FAILURE, "商品存储不足");
			}
			try {
				BigDecimal freight = liveGiftsInfo.getFreight();//运费
				BigDecimal cash = productInfo.getCash();//商品价格
				BigDecimal integral = productInfo.getIntegral();//商品价格
				BigDecimal addPrice = saleGroup.getAmount();//加价金额
				orderMoney = freight.add(cash).add(integral).add(addPrice).setScale(2, BigDecimal.ROUND_HALF_UP);
				
				//获取直播钱包
				liveWalletMap = liveGiftsInfoService.getLiveWalletBlance(giftsInfoRequest.getUid());
				if (liveWalletMap!=null) {
					//检查 鸟币的余额是否够用
					BigDecimal zbalanceCoin = new BigDecimal(liveWalletMap.get("zbalanceCoin").toString());
					if (orderMoney.compareTo(zbalanceCoin)>0) {
						return new BaseResponse(ResponseCode.FAILURE, "鸟币余额不足,打赏主播获得更多鸟币");
					}
				}
			} catch (Exception e) {
				log.info("发送商城礼物 获取直播钱包异常:"+giftsInfoRequest.getUid());
				e.printStackTrace();
				return new BaseResponse(ResponseCode.FAILURE, "获取直播钱包异常");
			}
			
		}else {
			log.info("商品不存在或已下架:"+liveGiftDetailMap.get("codeId"));
			return new BaseResponse(ResponseCode.FAILURE, "商品不存在或已下架");
		}
		
		//组装商品下单请求参数
		Map<Object, Object> createOrderMap = new HashMap<Object, Object>();
		createOrderMap.put("sendUid", giftsInfoRequest.getUid());
		
		//用户的收货地址列表
		Map<Object, Object> paramMap = new HashMap<Object, Object>();
		paramMap.put("uid", liverInfo.getUid());
		paramMap.put("isdefault", 1);
		
		//查询有无默认收货地址
		ReceivingAddress address = new ReceivingAddress();
		List<ReceivingAddress> receivingAddressList = receivingAddressDao.queryReceivingAddressListByUid(paramMap);
		if(receivingAddressList.size()>0){
			address =  receivingAddressList.get(0);
		}else {
			//默认全平台通用收货地址
			paramMap.put("uid", -1);
			List<ReceivingAddress>  receivingAddress = receivingAddressDao.queryReceivingAddressListByUid(paramMap);
			if(receivingAddress.size()>0){
				address =  receivingAddress.get(0);
				address.setPhoneid(liverInfo.getPhone());
			}
		}
		
		// 更新库存
		int updateStock = saleGroupDao.updateStock(productInfo.getCodeid().intValue(), saleGroup.getPvIds(), 1);
		//扣除总库存
		int productStore = productInfoDao.updateProductInfoStore(productInfo.getCodeid().toString(), 1);
		
		OrderPriceList opl =new OrderPriceList();
		opl.setTotalNum(1);//购买商品总数
		opl.setTotalPrice(orderMoney);//消费总金额,减去优惠卷之后的总价格
		opl.setTotalIntegral(new BigDecimal(0));//消费总积分
		
		//插入订单
		Long bid = marketOrderServiceImpl.insertBillFresh(address, opl, liverInfo.getUid().toString(), null, giftsInfoRequest.getAppversion(), 0D, 0D,Integer.parseInt(giftsInfoRequest.getUid()),giftsInfoRequest.getAppSource() );
		
		if(bid == null){
			//订单创建异常的时候回滚之前库存的操作
			saleGroupDao.updateExceptionStock(productInfo.getCodeid().intValue(),saleGroup.getPvIds(), 1);
			return new BaseResponse(ResponseCode.FAILURE,"创建订单异常");
		}
		
		//插入订单商品详情表
		ProductBill pb = new ProductBill();
		pb.setBid(bid);//父订单ID
		pb.setCodeid(saleGroup.getCodeid().intValue());//商品标识ID
		pb.setWarenum(1);//购买商品数量
		pb.setPname(productInfo.getPname());//商品名称
		pb.setRdate(DateUtil.now());//创建时间
		//商品现金价格
		Double tPrice = ArithUtil.add(productInfo.getCash().doubleValue(), saleGroup.getAmount().doubleValue());
		pb.setPrice(BigDecimal.valueOf(ArithUtil.add(tPrice, productInfo.getIntegral().doubleValue())).add(liveGiftsInfo.getFreight()));
		pb.setGoodsname(productInfo.getGoodsname());//商品促销活动名称
		pb.setBreviary(productInfo.getBreviary());//商品缩略图
		pb.setSupplierId(productInfo.getSupplierid()==null?0:productInfo.getSupplierid().longValue());//供应商ID
		pb.setIntegral(productInfo.getIntegral());//最高积分抵扣单价
		pb.setPurchaseprice(productInfo.getPurchaseprice());//采购价
		pb.setAttrids(saleGroup.getPvIds());
		pb.setAttrAmount(saleGroup.getAmount());//规格加价
		
		pb.setSuttle(productInfo.getWeight());//商品净重
		pb.setAttrStr(liveGiftDetailMap.get("pv_value")==null?"":liveGiftDetailMap.get("pv_value").toString());//规格信息
		
		//插入商铺消费信息
		productBillDao.insertSelective(pb);
		
		try{
			
			//调起扣除直播鸟币钱包接口
			//扣减钱包鸟币余额
			Map<String, String> substractMap = new HashMap<String, String>();
			substractMap.put("uid", giftsInfoRequest.getUid());
			substractMap.put("rtype", "23");//这里是支付服务的消费类型
			substractMap.put("liveRecordId", giftsInfoRequest.getLiveRecordId());
			substractMap.put("remarks", bid.toString());//这里是支付服务的消费鸟豆
			substractMap.put("description", productInfo.getPname());
			substractMap.put("option", "1");
			substractMap.put("zbalance", orderMoney.toString());
			substractMap.put("anchorId", liverInfo.getId().toString());
			
			//更新用户钱包余额
			ResponseData subResponseData = null;
			try {
				
				subResponseData =  liveGiftsInfoService.subtractLiveWalletCoinBlance(substractMap);
				if (subResponseData!=null && subResponseData.getState()!=0) {
					log.info("发礼物扣减鸟币失败:"+giftsInfoRequest.getUid());
					
					//订单创建异常的时候回滚之前库存的操作   --扣减鸟币异常处理商品库存回滚
					saleGroupDao.updateExceptionStock(productInfo.getCodeid().intValue(),saleGroup.getPvIds(), 1);
					return new BaseResponse(ResponseCode.FAILURE, "发送套餐礼物支付鸟币失败");
				}
				
				ResponseData resData = new ResponseData();
				Map<String, String> obMap = new HashMap<String, String>();
				obMap.put("walletRecordId", subResponseData.getResultMap().get("id"));
				resData.setState(0);
				resData.setResultMap(obMap);
				
				//更新订单状态  调用业务服务
				Map<String, String> updateOrderMap = new HashMap<String,String>();
				String zeroNum = new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP).toString();//用于其他没有金额的数据填充
				updateOrderMap.put("bid", bid.toString());
				updateOrderMap.put("status", "1");
				updateOrderMap.put("payType", "1000020");
				updateOrderMap.put("payid", System.currentTimeMillis()+"");
				updateOrderMap.put("orderAmount", orderMoney.toString());
				updateOrderMap.put("balance", zeroNum);
				updateOrderMap.put("zbalance", zeroNum);
				updateOrderMap.put("commision", zeroNum);
				updateOrderMap.put("integral", zeroNum);
				updateOrderMap.put("birdCoin", orderMoney.toString());
				updateOrderMap.put("freight", zeroNum);
				updateOrderMap.put("cuser", zeroNum);
				updateOrderMap.put("payment", zeroNum);
				
				//调用业务服务更新订单状态
				MallOrderService.Client client = null;
				Map<String, String> resultMap = new HashMap<String, String>();
				try {
					TMultiplexedProtocol tMultiplexedProtocol = thriftBusinessUtil.getProtocol("MallOrderService");
					client = new MallOrderService.Client(tMultiplexedProtocol); 
					thriftBusinessUtil.openTransport();
					resultMap =	client.notifyForPayComplete(updateOrderMap);
				} catch (Exception e) {
					log.info("更新商城礼物订单失败:"+bid);
					e.printStackTrace();
					return new BaseResponse(ResponseCode.FAILURE, "更新商城礼物订单失败");
				}
				
				LiveGiftsInfo giftsInfo = new LiveGiftsInfo();
				giftsInfo.setGift_nums(1);
				giftsInfo.setGift_name(productInfo.getPname());
				giftsInfo.setGift_price(orderMoney);//原价 鸟币价格
				giftsInfo.setId(liveGiftsInfo.getId());
				giftsInfo.setBackCoinStatus(1);
				giftsInfo.setGiftSource(2);
				giftsInfo.setGift_experience(liveGiftsInfo.getGift_experience());
				giftsInfo.setSaleProductId(productInfo.getPid());
				giftsInfo.setIsRadio(liveGiftsInfo.getIsRadio());
				
				Map<Object, Object> responseMap = new HashMap<>();
				responseMap.put("walletRecordId", subResponseData.getResultMap().get("id"));//钱包ID
				liverMap.put("percentAmount", 0);
				
				return liveGiftsInfoService.sendBirdCoinGiftsHandle(giftsInfoRequest,liverMap,resData,giftsInfo,liverInfo,liverKey,0,3);
				
			}catch(Exception e){
				log.info("发礼物扣减鸟币失败:"+giftsInfoRequest.getUid());
				e.printStackTrace();
				return new BaseResponse(ResponseCode.FAILURE, "发送礼物支付鸟币失败");
			}
			
		}catch(Exception e){
			log.info("发套餐礼物支付失败");
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE, "扣减鸟币失败");
		}
		
	}
	
}
