package com.xmniao.xmn.core.coupon.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.Constant;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.TResponse;
import com.xmniao.xmn.core.coupon.request.CouponCardInfoRequest;
import com.xmniao.xmn.core.coupon.request.CouponCardInfoSubRequest;
import com.xmniao.xmn.core.coupon.request.CouponCardsRequest;
import com.xmniao.xmn.core.coupon.response.ApplyStoresResponse;
import com.xmniao.xmn.core.coupon.response.CouponCardInfoResponse;
import com.xmniao.xmn.core.coupon.response.CouponCardsResponse;
import com.xmniao.xmn.core.coupon.response.UseCardRecordResponse;
import com.xmniao.xmn.core.coupon.service.CouponCardsService;
import com.xmniao.xmn.core.live.dao.AnchorLiveRecordDao;
import com.xmniao.xmn.core.live.service.LiveGiftsInfoService;
import com.xmniao.xmn.core.thrift.FailureException;
import com.xmniao.xmn.core.thrift.ValueCardService;
import com.xmniao.xmn.core.util.PropertiesUtil;
import com.xmniao.xmn.core.util.ThriftUtil;
import com.xmniao.xmn.core.verification.dao.BillDao;
import com.xmniao.xmn.core.xmer.dao.CouponDao;
import com.xmniao.xmn.core.xmer.dao.SellerDao;

/**
 * 
* @projectName: xmnService 
* @ClassName: CouponCardsServiceImpl    
* @Description:我的卡卷实现   
* @author: liuzhihao   
* @date: 2017年2月27日 上午10:18:58
 */
@Service
public class CouponCardsServiceImpl implements CouponCardsService {

	/**
	 * 报错日志
	 */
	private final Logger log = Logger.getLogger(CouponCardsServiceImpl.class);
	
	/**
	 * 注入支付接口公共类
	 */
	@Autowired
	private ThriftUtil thriftUtil;
	
	//注入店铺Dao
	@Autowired
	private SellerDao sellerDao;
	
	//订单dao
	@Autowired
	private BillDao billDao;
	
	//注入优惠卷Dao
	@Autowired
	private CouponDao couponDao;
	
	//注入直播记录dao
	@Autowired
	private AnchorLiveRecordDao anchorLiveRecordDao; 
	
	@Autowired
	private String fileUrl;
	
	//注入缓存service
	@Autowired
	private SessionTokenService sessionTokenService;
	
	//注入直播钱包service
	@Autowired
	private LiveGiftsInfoService liveGiftsInfoService;
	
	@Autowired
	private PropertiesUtil propertiesUtil;
	
	private MapResponse mapResponse = null;
	
	/**
	 * 获取我的卡卷列表
	 */
	@Override
	public Object getCouponCards(CouponCardsRequest request) {
		Map<Object,Object> result = new HashMap<Object,Object>();
		
		List<CouponCardsResponse> cards = null;
		
		String uid = sessionTokenService.getStringForValue(request.getSessiontoken()) + "";
		if (StringUtils.isEmpty(uid) || "null".equalsIgnoreCase(uid)) {
			return new BaseResponse(ResponseCode.TOKENERR, "token已失效,请重新登录");
		}
		//分页页码 
		Integer page = request.getPage();
		//分页条数
		Integer pageSize = Constant.PAGE_LIMIT;
		
		int isOverdue = request.getIsOverdue();//优惠卷状态 0 已过期或者已使用 1 未过期且未使用
		
		//卡卷类型 1 充值卡 2寻蜜鸟礼券 3餐饮店铺礼券
		int type = request.getType();
			switch(type){
				case 1://充值卡
					try {
						cards = getStoreCards(uid, null,isOverdue,page,pageSize);
						result.put("cards", cards);
						//获取用户鸟币余额
						Map<String,Object> birdmap = liveGiftsInfoService.getLiveWalletBlance(uid);
						if(birdmap != null && birdmap.size() >0){
							result.put("totalCoin", birdmap.get("sellerCoin"));
						}else{
							result.put("totalCoin", "0.00");
						}
						break;
					} catch (Exception e) {
						e.printStackTrace();
						log.info("查询用户充值卡列表异常");
						return new BaseResponse(ResponseCode.FAILURE,"查询充值卡信息异常");
					}
				case 2://寻蜜鸟礼券
					try{
						cards = getXMNCoupons(uid, isOverdue, page, pageSize);	
						result.put("cards", cards);
						break;
					}catch(Exception e){
						e.printStackTrace();
						log.info("查询用户持有的平台礼券异常");
						return new BaseResponse(ResponseCode.FAILURE,"查询用户持有的平台礼券异常");
					}
				case 3://餐饮店铺礼券
					try{
						cards = getPlatCoupons(uid, isOverdue, page, pageSize);	
						result.put("cards", cards);
						break;
					}catch(Exception e){
						e.printStackTrace();
						log.info("查询用户持有的平台礼券异常");
						return new BaseResponse(ResponseCode.FAILURE,"查询用户持有的平台礼券异常");
					}
					default:
						break;
			}
			
			mapResponse = new MapResponse(ResponseCode.SUCCESS,"成功");
			mapResponse.setResponse(result);
			return mapResponse;
	}

	/**
	 * 获取充值卡列表
	 * @throws TException 
	 * @throws IOException 
	 * @throws FailureException 
	 * @throws NumberFormatException 
	 */
	@Override
	public List<CouponCardsResponse> getStoreCards(String uid,String sellerid,Integer isOverdue,Integer page,Integer pageSize) throws Exception {
		
		List<CouponCardsResponse> response = new ArrayList<CouponCardsResponse>();
		//查看用户的充值卡是否有效
		List<Map<String,String>> cards = getUserCardMsg(uid, sellerid,isOverdue,page,pageSize);
		
		if(cards != null && cards.size() > 0){
			//有钱包信息
			
			Iterator<Map<String,String>> i = cards.iterator();
			
			while(i.hasNext()){
				
				CouponCardsResponse card = new CouponCardsResponse();
				
				Map<String,String> map = i.next();
				
				card.setSellerid(map.get("sellerid"));//店铺ID
				card.setCname(map.get("sellername"));//充值卡名称
				card.setSellerType(map.get("sellertype"));//店铺类型
				card.setId(map.get("id"));//充值卡ID
				//可用充值卡面额
				Double quota = StringUtils.isEmpty(ObjectUtils.toString(map.get("quota"))
					)?0.0:Double.parseDouble(map.get("quota"));
				
				card.setDenomination(ObjectUtils.toString(quota));
				card.setIsOverdue(String.valueOf(isOverdue));//充值卡是否有效
				response.add(card);
			}
		}
		return response;
	}

	/**
	 * 查询用户持有的平台卷(本地生活类优惠卷[不可使用积分组合支付]、生鲜现金卷[可以和组合支付]、粉丝券 、预售抵用券、套餐抵用券)
	 * uid 必须有值
	 * isOverdue 必须有值  未平台卷类型 0 过期或者已使用 1 为 未使用且未过期
	 * 
	 */
	@Override
	public List<CouponCardsResponse> getXMNCoupons(String uid,Integer isOverdue,Integer page,Integer pageSize) throws Exception{
		
		List<CouponCardsResponse> cards = new ArrayList<CouponCardsResponse>();
		/**
		 * 查询用户持有的优惠卷
		 * isOverdue 有两种状态 分别为:0 过期或者已使用 1 未过期且未使用
		 */
		List<Map<Object,Object>> coupons = couponDao.selectXMNCouponsByUid(uid, isOverdue,page,pageSize);
		String bamaCoupon = propertiesUtil.getValue("marketBamaProductCouponCid", "conf_integral_pay.properties");
		String marketBamaProductCouponDesc = propertiesUtil.getValue("marketBamaProductCouponDesc", "conf_integral_pay.properties");
		String marketAllProductCouponDesc = propertiesUtil.getValue("marketAllProductCouponDesc", "conf_integral_pay.properties");
		
		if(coupons != null && coupons.size() > 0){
			//存在优惠卷
			Iterator<Map<Object,Object>> i = coupons.iterator();
			while(i.hasNext()){
				
				CouponCardsResponse card = new CouponCardsResponse();
				//优惠卷类型:默认为0;0本地生活类优惠卷(不可使用积分组合支付);1生鲜现金卷(可以和组合支付) 2 粉丝券 3 预售抵用券 4套餐抵用券
				Map<Object,Object> map = i.next();
				card.setCtype(ObjectUtils.toString(map.get("ctype")));
				String sellerid = ObjectUtils.toString(map.get("sellerid"));//店铺ID
				
				if (map.get("cid")!=null) {
					String bamaCid = map.get("cid")==null?"0":map.get("cid").toString();
					if (bamaCoupon.indexOf(bamaCid) >= 0) {
						card.setUseRange(marketBamaProductCouponDesc);//店铺名称
					}else {
						
						if (map.get("ctype").toString().equals("1")) {
							
							card.setUseRange(marketAllProductCouponDesc);//店铺名称
						}else {
							if(StringUtils.isEmpty(sellerid) || sellerid.equals("0")){
								card.setUseRange("全平台店铺通用卷");//店铺名称
							}else{
								Map<Object,Object> sellermap = sellerDao.selectByPrimaryKey(sellerid);
								if (sellermap!=null && sellermap.size()==0) {
									card.setUseRange(sellermap.get("sellername")!=null?sellermap.get("sellername").toString():"");//店铺名称
								}
							}
						}
						
					}
					
				}
				
//				if(StringUtils.isEmpty(sellerid) || sellerid.equals("0")){
//					card.setUseRange("全平台店铺通用卷");//店铺名称
//				}else{
//					Map<Object,Object> sellermap = sellerDao.selectByPrimaryKey(sellerid);
//					card.setUseRange(ObjectUtils.toString(sellermap.get("sellername")));//店铺名称
//				}
				card.setSellerid(ObjectUtils.toString(map.get("sellerid")));//店铺ID
				card.setCname(ObjectUtils.toString(map.get("cname")));//优惠卷名称
				
				//优惠卷面额
				Double denomination = StringUtils.isEmpty(
 					ObjectUtils.toString(map.get("denomination"))
					)?0.0:Double.parseDouble(map.get("denomination").toString());
				
				card.setDenomination(ObjectUtils.toString(denomination));
				
				//优惠卷限额
				if (map.get("conditions")!=null) {
					if (new BigDecimal(map.get("conditions").toString()).compareTo(new BigDecimal(0))>0 ) {
						card.setCondition("使用条件,消费满"+ObjectUtils.toString(map.get("conditions"))+"元使用");
					}else {
						card.setCondition("不限制使用");
					}
				}else {
					card.setCondition("");
				}
				card.setSerial(ObjectUtils.toString(map.get("serial")));//优惠卷二维码
				//优惠卷使用时间
				String startDate = ObjectUtils.toString(map.get("startDate"));
				String endDate = ObjectUtils.toString(map.get("endDate"));
				card.setValidDate("使用期限:"+startDate+"至"+endDate);
				//优惠卷使用情况 0 未使用 1 已过期 2 已使用 
				card.setType(ObjectUtils.toString(map.get("useStatus")));
				card.setSeats(ObjectUtils.toString(map.get("seats")));
				card.setIsOverdue(String.valueOf(isOverdue));//是否有效  0 无 1 有
				card.setId(ObjectUtils.toString(map.get("cid")));//用户优惠卷ID
				cards.add(card);
			}
		}
		
		return cards;
	}

	/**
	 * 查询用户持有的SAAS发布的优惠卷
	 * uid 必须有值
	 * isOverdue 必须有值  未平台卷类型 0 过期或者已使用 1 为 未使用且未过期
	 */
	@Override
	public List<CouponCardsResponse> getPlatCoupons(String uid,Integer isOverdue,Integer page,Integer pageSize) throws Exception{
		
		//返回结果集
		List<CouponCardsResponse> cards = new ArrayList<CouponCardsResponse>();
		/**
		 * 查询用户持有的SAAS发布的优惠卷
		 * isOverdue 有两种状态 分别为:0 过期或者已使用 1 未过期且未使用
		 */
		List<Map<Object,Object>> coupons = couponDao.selectSAASCouponsByUid(uid, isOverdue, page, pageSize);
		
		if(coupons != null && coupons.size() > 0){
			
			Iterator<Map<Object,Object>> i = coupons.iterator();
			
			while(i.hasNext()){
				
				Map<Object,Object> map = i.next();
				
				CouponCardsResponse card = new CouponCardsResponse();
				card.setId(ObjectUtils.toString(map.get("cid")));//优惠卷ID
				card.setSellerid(ObjectUtils.toString(map.get("sellerid")));//店铺ID
				card.setUseRange(ObjectUtils.toString(map.get("sellername")));//店铺名称
				card.setCname(ObjectUtils.toString(map.get("cname")));//优惠卷名称
				card.setCtype(ObjectUtils.toString(map.get("ctype")));//优惠卷类型 1 普通优惠券 2 随机优惠券 3现金抵用券 4赠品券
				//优惠卷面额
				Double denomination = 
					StringUtils.isEmpty(ObjectUtils.toString(map.get("denomination"))
						)?0.0:Double.parseDouble(map.get("denomination").toString());
				card.setDenomination(ObjectUtils.toString(denomination));
				//优惠限额
				
//				card.setCondition(ObjectUtils.toString(map.get("conditions")));
				//优惠卷限额
				if (map.get("conditions")!=null) {
					if (new BigDecimal(map.get("conditions").toString()).compareTo(new BigDecimal(0))>0 ) {
						card.setCondition("使用条件,消费满"+ObjectUtils.toString(map.get("conditions"))+"元使用");
					}else {
						card.setCondition("不限制使用");
					}
				}else {
					card.setCondition("");
				}
				
				card.setSerial(ObjectUtils.toString(map.get("serialno")));//优惠卷二维码
				//优惠卷使用时间
				String startDate = ObjectUtils.toString(map.get("startDate"));
				String endDate = ObjectUtils.toString(map.get("endDate"));
				card.setValidDate("使用期限:"+startDate+"至"+endDate);
				//使用状态:0 未使用 1 已过期 2 已使用 
				card.setType(ObjectUtils.toString(map.get("useStatus")));
				card.setIsOverdue(String.valueOf(isOverdue));//是否有效 0 无 1 有
				cards.add(card);
			}
		}
		return cards;
	}
	
	/**
	 *获取用户储值卡信息
	 *uid true
	 *	
	 *sellerid false
	 *
	 *如果 uid 为 true sellerid 为false 则返回一个用户储值卡列表
	 *如果 uid 为 true sellerid 为true 则返回一条储值卡信息
	 * @return
	 * @throws IOException 
	 * @throws NumberFormatException 
	 * @throws TException 
	 * @throws FailureException 
	 */
	private List<Map<String,String>> getUserCardMsg(String uid,String sellerid,Integer isOverdue,Integer page,Integer pageSize) throws Exception{
		
		Map<String,String> paraMap = new HashMap<String,String>();
		paraMap.put("uid", uid);
		if(StringUtils.isNotEmpty(sellerid)){
			//查询充值店铺详情
			Map<Object, Object> cardmap = 
				sellerDao.queryCardSellerBySellerId(Integer.parseInt(sellerid));
			if(cardmap == null || cardmap.isEmpty()){
				return new ArrayList<Map<String,String>>();
			}
			paraMap.put("sellerid", cardmap.get("sellerid").toString());
			paraMap.put("sellertype", cardmap.get("sellertype").toString());
		}
		paraMap.put("isOverdue", String.valueOf(isOverdue));
		paraMap.put("page", String.valueOf(page));
		paraMap.put("pagesize", String.valueOf(pageSize));
		
		TMultiplexedProtocol tMultiplexedProtocol = thriftUtil.getProtocol("ValueCardService");
		
		ValueCardService.Client client = new ValueCardService.Client(tMultiplexedProtocol);
		
		thriftUtil.openTransport();
		
		//调业务接口获取用户储值卡信息
		List<Map<String,String>> cards = client.getValueCardMsg(paraMap);		
		
		thriftUtil.coloseTransport();
		
		return cards;
	}

	/**
	 * 充值卡详情
	 */
	public Object getCardInfo(CouponCardInfoRequest request){
		CouponCardInfoResponse card = new CouponCardInfoResponse();
		//获取用户UID
		String uid = ObjectUtils.toString(sessionTokenService.getStringForValue(request.getSessiontoken()));
		
		if(request.getIsOverdue() == 0){
			return new BaseResponse(ResponseCode.IS_INVALID_CARD,"无效卡券");
		}
		
		//获取用户充值卡详情
		try {
			List<Map<String,String>> userCardMsg = getUserCardMsg(uid, request.getSellerid(), request.getIsOverdue(),1,1);
			if(userCardMsg != null && userCardMsg.size() > 0){
				//详情时 用户只有一条信息
				Map<String,String> cardMap = userCardMsg.get(0);
				
				//通过商铺ID获取商铺的充值卡ID
				Map<Object,Object> debitcardMap = sellerDao.queryCardSellerBySellerId(Integer.parseInt(request.getSellerid()));
				
				if(debitcardMap != null && debitcardMap.size() >0){
					card.setCardId(debitcardMap.get("id").toString());
				}
				card.setSellerId(cardMap.get("sellerid"));//店铺ID
				card.setSellerName(cardMap.get("sellername"));//店铺名称
				card.setCoinPrice(cardMap.get("quota"));//鸟币余额
				card.setSellerType(cardMap.get("sellertype"));
				
				
				//查询充值卡店铺相关的店铺ID
				List<String> ids = getSubSellerIds(card.getSellerId(), Integer.parseInt(card.getSellerType()));
				//默认没有直播店铺
				card.setIsLive(String.valueOf(0));//是否存在直播 0 无  1 有
				
				if(ids != null && ids.size() > 0){
					//通过关联店铺的ID查询是否存在正在直播的店铺
					int counts = anchorLiveRecordDao.sumSellerCountsIsLive(ids);
					if(counts > 0){
						card.setIsLive(String.valueOf(1));//是否存在直播 0 无  1 有
					}
				}
				
				if(request.getIsRemark() == 1){
					String remark = propertiesUtil.getValue("cardDescription", "conf_common.properties");
					card.setCardDescription(remark);//充值卡说明
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("获取用户充值卡信息异常");
			return new BaseResponse(ResponseCode.FAILURE,"查询用户充值卡信息异常");
		}
		TResponse<CouponCardInfoResponse> response = new TResponse<CouponCardInfoResponse>(ResponseCode.SUCCESS,"成功");
		response.setResponse(card);
		return response;
	}

	
	/**
	 * 充值卡详情子列表
	 * @param request
	 * @return
	 */
	public Object getCardInfoSubList(CouponCardInfoSubRequest request){
		Map<Object,Object> map = new HashMap<Object,Object>();
		//获取用户UID
		String uid = ObjectUtils.toString(sessionTokenService.getStringForValue(request.getSessiontoken()));
		
		//获取子列表类型
		int subType = request.getSubType();//子列表类型 1 适合门店 2 用户充值卡消费记录 默认为1
		switch(subType){
			case 1:
				//获取门店信息
				List<ApplyStoresResponse> sellers 
				= queryApplyStoreList(uid, request.getSellerid(), request.getLon(), request.getLat(), request.getSellerType(),request.getPage());
				
				if(sellers != null && sellers.size() > 0){
					map.put("subs", sellers);
				}else{
					map.put("subs", new ArrayList<ApplyStoresResponse>());
				}
				break;
			case 2:
				//用卡记录
				List<UseCardRecordResponse> records =
					queryUseCardRecordList(uid, request.getSellerid(), 2, request.getPage(), Constant.PAGE_LIMIT);
				if(records != null && records.size() > 0){
					map.put("subs", records);
				}else{
					map.put("subs", new ArrayList<UseCardRecordResponse>());
				}
				break;
			default:
				break;
		}
		MapResponse response = new MapResponse(ResponseCode.SUCCESS,"成功");
		response.setResponse(map);
		return response;
	}
	
	
	/**
	 * 充值卡适用门店列表
	 */
	@Override
	public List<ApplyStoresResponse> queryApplyStoreList(String uid,String sellerid,Double lon,Double lat,Integer sellerType,Integer page) {
		
		List<ApplyStoresResponse> sellers = new ArrayList<ApplyStoresResponse>();
		//判断充值卡所属店铺类型  商家类型 1.普通商家 2.连锁总店 3 区域代理
			switch(sellerType){
				case 1://普通商家  如 广州酒家
					sellers = selectSubSellerList(sellerid, uid, lon, lat, sellerType,page);
					break;
				case 2://连锁总店 如 小肥羊连锁店 
					sellers = selectSubSellerList(sellerid, uid, lon, lat, sellerType,page);
					break;
				case 3://区域代理 如 太阳新天地
					sellers = selectSubSellerList(sellerid, uid, lon, lat, sellerType,page);
					break;
					default:
						break;
			}
		return sellers;
		
	}

	/**
	 * 充值卡用卡记录列表
	 */
	@Override
	public List<UseCardRecordResponse> queryUseCardRecordList(String uid,String sellerid,Integer rtype,Integer page,Integer pageSize) {
		
		List<UseCardRecordResponse> records = new ArrayList<UseCardRecordResponse>();
		
		//调业务接口查询用户的用卡记录
		try {
			TMultiplexedProtocol tMultiplexedProtocol = thriftUtil.getProtocol("ValueCardService");
			ValueCardService.Client client = new ValueCardService.Client(tMultiplexedProtocol);
			thriftUtil.openTransport();
			
			Map<String,String> paraMap = new HashMap<String,String>();
			paraMap.put("uid", uid);//用户ID
			paraMap.put("sellerid", sellerid);//店铺ID
			paraMap.put("rtype", String.valueOf(rtype));//充值卡记录类型 1 充值 2 消费
			paraMap.put("page", String.valueOf(page));//页码
			paraMap.put("pageSize", String.valueOf(pageSize));//页数
			List<Map<String,String>> consumRecords = client.getCardRecord(paraMap);
			if(consumRecords != null && consumRecords.size() > 0){
				for(Map<String,String> map : consumRecords){
					UseCardRecordResponse record = new UseCardRecordResponse();
					record.setConsumCoin(map.get("quota"));//消费金额
					record.setSellerName(map.get("consumeSellername"));//消费店铺
					record.setConsumDate(map.get("updateTime"));//消费时间
					
					records.add(record);
				}
			}
			
			thriftUtil.coloseTransport();
		} catch (Exception e) {
			e.printStackTrace();
			log.info("查询用户消费记录信息异常");
		} 
		
		return records;
	}

	
	/**
	 * 查询相关店铺信息
	 * @return
	 */
	private List<ApplyStoresResponse> selectSubSellerList(String sellerid,String uid,Double lon,Double lat,Integer sellerType,Integer page){
		
		List<ApplyStoresResponse> response = new ArrayList<ApplyStoresResponse>();
		//获取子关联店铺ID
		List<String> ids = getSubSellerIds(sellerid, sellerType);
		if(ids != null && ids.size() > 0){
			try{
				List<Map<Object,Object>> sellers = sellerDao.selectSellerInfoBySellerIds(ids, uid, lon, lat, page, 8);
				
				if(sellers != null && sellers.size() > 0){
					
					for(Map<Object,Object> map : sellers){
						//店铺ID
						String id = map.get("sellerid").toString();
						
						ApplyStoresResponse seller = new ApplyStoresResponse();
						
						seller.setSellerId(ObjectUtils.toString(map.get("sellerid")));
						seller.setSellerName(ObjectUtils.toString(map.get("sellername")));
						seller.setTradeName(ObjectUtils.toString(map.get("tradename")));
						seller.setZoneName(ObjectUtils.toString(map.get("zonename")));
						seller.setZhiboType(ObjectUtils.toString(map.get("rsort")));
						//查询店铺消费人次
						int consums = billDao.sumAllOrdersBySellerId(Integer.parseInt(id));
						
						seller.setSellerConsums(String.valueOf(consums));//消费人次
						
						//查询店铺logo图
						int type = 1;//默认为logo图
						do{
							List<Map<Object,Object>> images = sellerDao.selectSellerPicBySellerId(id, type);
							
							if(images != null && images.size() > 0){
								
								Map<Object,Object> image = images.get(0);//取第一张图片
								seller.setSellerImage(fileUrl+ObjectUtils.toString(image.get("picurl")));
								break;
							}else{
								--type;
							}
							
						}while(type>=0);
						
						response.add(seller);
					}
				}
			}catch(Exception e){
				e.printStackTrace();
				log.info("查询相关店铺信息异常");
			}
		}
		return response;
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
	
	
}
