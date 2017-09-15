package com.xmniao.xmn.core.live.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.Constant;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.live.LiveBuyCouponRequest;
import com.xmniao.xmn.core.common.request.live.LiveFansOrderCancleRequest;
import com.xmniao.xmn.core.common.request.live.LiveFansOrderUseCouponRequest;
import com.xmniao.xmn.core.common.request.live.LiveQueryCouponRequest;
import com.xmniao.xmn.core.common.request.live.LiveRecordFansCouponRequest;
import com.xmniao.xmn.core.live.dao.AnchorLiveRecordDao;
import com.xmniao.xmn.core.live.dao.LiveUserDao;
import com.xmniao.xmn.core.live.entity.CouponInfo;
import com.xmniao.xmn.core.live.entity.LiveRecordInfo;
import com.xmniao.xmn.core.seller.entity.Seller;
import com.xmniao.xmn.core.seller.entity.SellerPic;
import com.xmniao.xmn.core.thrift.client.proxy.ThriftClientProxy;
import com.xmniao.xmn.core.util.DateUtil;
import com.xmniao.xmn.core.util.HttpConnectionUtil;
import com.xmniao.xmn.core.util.PropertiesUtil;
import com.xmniao.xmn.core.util.Signature;
import com.xmniao.xmn.core.util.ThriftBusinessUtil;
import com.xmniao.xmn.core.util.ThriftUtil;
import com.xmniao.xmn.core.xmer.service.SellerService;
import com.xmniao.xmn.core.thrift.LiveOrderService;

/**
 * 描述： 用户或主播进入房间
 * @author yhl
 * 2016年8月15日11:34:14
 * */
@Service
public class LiveBuyFansCouponService {
	
	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(LiveBuyFansCouponService.class);
	
	@Autowired
	private SessionTokenService sessionTokenService;
	
	@Autowired
	private AnchorLiveRecordDao anchorLiveRecordDao;
	
	@Autowired
	private LiveGiftsInfoService liveGiftsInfoService;
	
	@Autowired
	private LiveUserDao liveUserDao;
	
	@Autowired
	private String fileUrl;
	
	@Autowired
	private PropertiesUtil  propertiesUtil;
	
	@Autowired
	private UserPayBirdCoinService userPayBirdCoinService;
	
	@Autowired
	private SellerService sellerService;
	
	/**
	 * 直播钱包 ：支付服务接口
	 */
	@Resource(name = "liveWalletServiceClient")
	private ThriftClientProxy liveWalletServiceClient;
	
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
	 * 描述：购买粉丝券 - 下单操作
	 * @param liverBuyCouponRequest
	 * @return Object
	 * */
	public Object buyFansCoupon(LiveBuyCouponRequest liveBuyCouponRequest){
//		String uid = liveBuyCouponRequest.getUid().toString();
		//验证token
		String uid = sessionTokenService.getStringForValue(liveBuyCouponRequest.getSessiontoken()) + "";
		if (StringUtils.isEmpty(uid) || "null".equalsIgnoreCase(uid)) {
			return new BaseResponse(ResponseCode.TOKENERR, "token已失效,请重新登录");
		}
		
//		检查预售粉丝券是否已经过期  根据通告ID检查
		MapResponse response = null;
		try {
			//查询该主播直播的预售粉丝券
			Map<Object, Object> paramMap = new HashMap<Object, Object>();
			paramMap.put("liveRecordId", liveBuyCouponRequest.getLiveRecordId());
			paramMap.put("cid", liveBuyCouponRequest.getFansCouponId());
			//获取直播主播的预售粉丝券
			Map<Object, Object> couponDetailMap = anchorLiveRecordDao.queryLiveRecordFansCoupon(paramMap);
			if (couponDetailMap==null || couponDetailMap.size()<=0) {
				log.info("预售粉丝券不存在或已过期");
				return new MapResponse(ResponseCode.FAILURE, "预售粉丝券不存在或已过期");
			}
			
			if (couponDetailMap!=null && couponDetailMap.size()>0) {
				
				//最终返回结果集的总集合  
				Map<Object, Object> map = new HashMap<Object, Object>();
				
				//检查库存
				boolean stock = this.validateFansCouponStaock(liveBuyCouponRequest, couponDetailMap);
				try {
					if (stock == false) {//库存不足的时候
						liveBuyCouponRequest.setQuantity(Integer.parseInt(couponDetailMap.get("stock").toString()));
						map = this.calculatedFanCouponPrice(liveBuyCouponRequest,couponDetailMap,paramMap,uid);
						//库存不足的时候，把库存返回为0
						map.put("stock", 0);
					}else {//库存富余的时候
						map = this.calculatedFanCouponPrice(liveBuyCouponRequest,couponDetailMap,paramMap,uid);
					}
					
					//返回主播基本信息
					Map<Object, Object> liverMap = liveUserDao.queryLiverInfoByUid(Integer.parseInt(couponDetailMap.get("uid").toString()));
					map.put("id", liverMap.get("id")==null?"":liverMap.get("id").toString()); //'直播用户id',
					map.put("avatar", liverMap.get("avatar")==null?"":fileUrl+liverMap.get("avatar").toString()); //'头像',
					map.put("uid", liverMap.get("uid")==null?"":liverMap.get("uid").toString()); //' 寻蜜鸟会员id',
					map.put("utype", liverMap.get("utype")==null?"":liverMap.get("utype").toString()); //'直播用户类型： 1 主播 2 普通用户',
					map.put("phone", liverMap.get("phone")==null?"":liverMap.get("phone").toString()); //'手机号码',
					map.put("sex", liverMap.get("sex")==null?"":liverMap.get("sex").toString()); //'性别',
					map.put("nname", com.xmniao.xmn.core.util.StringUtils.getUserNameStr(liverMap.get("nname")==null?"":liverMap.get("nname").toString()));
					
					map.put("useTime", DateUtil.format(DateUtil.parse(couponDetailMap.get("start_date").toString()), DateUtil.minuteSimpleFormater) +"-"+
							DateUtil.format(DateUtil.parse(couponDetailMap.get("end_date").toString()), DateUtil.minuteSimpleFormater));
					
				} catch (Exception e) {
					log.info("计算价格出现异常");
					e.printStackTrace();
					return new MapResponse(ResponseCode.FAILURE, "操作异常");
				}
				response = new MapResponse(ResponseCode.SUCCESS, "操作成功");
				response.setResponse(map);
				return response;
				
			}else {
				log.info("获取粉丝券异常:直播记录 "+liveBuyCouponRequest.getLiveRecordId());
				return new MapResponse(ResponseCode.FAILURE, "该通告暂无预售或预售已结束");
			}
			
		} catch (Exception e) {
			log.info("查询粉丝券错误："+liveBuyCouponRequest.getLiveRecordId());
			e.printStackTrace();
			return new MapResponse(ResponseCode.FAILURE, "操作失败");
		}
	}
	
	
	/**
	 * 描述：校验粉丝券售卖库存
	 * @param LiveBuyCouponRequest   , couponDetailMap
	 * @return boolean 
	 * */
	public boolean validateFansCouponStaock(LiveBuyCouponRequest liveBuyCouponRequest,Map<Object, Object> couponDetailMap){
		boolean flag = false;
		//购买量
		int quantity = liveBuyCouponRequest.getQuantity();
		//库存量
		int stock = Integer.parseInt(couponDetailMap.get("stock").toString());
		//库存量必须大于0  并且必须大于等于 购买量
		if (stock>0 && stock>=quantity) {
			flag = true;
		}else {
			return flag;
		}
		return flag;
	}
	
	/**
	 * 描述：计算购买粉丝券的基本金额 
	 * @param liveBuyCouponRequest ,couponDetailMap , paramMap ,uid
	 * @return Map<Object, Object>  
	 * @throws Exception 
	 * */
	public Map<Object, Object> calculatedFanCouponPrice(LiveBuyCouponRequest liveBuyCouponRequest ,Map<Object, Object> couponDetailMap
			,Map<Object, Object> paramMap , String uid) throws Exception{
		
		Map<Object, Object> resultMap = new HashMap<Object, Object>();
		
		//获取商家
		Seller seller = sellerService.querySellerBySellerid(Long.valueOf(couponDetailMap.get("sellerid").toString()) );
		
		//获取直播记录
		paramMap.put("id",liveBuyCouponRequest.getLiveRecordId());
		LiveRecordInfo liveRecordInfo = anchorLiveRecordDao.queryAnchorLiveRecordById(paramMap);
		try {
			
			resultMap.put("uid", uid);
			//粉丝券金额
			resultMap.put("amount", couponDetailMap.get("denomination"));
			// 传过来的 购买数量    初始值为1
			resultMap.put("quantity", liveBuyCouponRequest.getQuantity());
			//.multiply(new BigDecimal(liverBuyCouponRequest.getQuantity()))
			resultMap.put("cid", couponDetailMap.get("anchor_cid"));//ID
			resultMap.put("cname", couponDetailMap.get("cname"));//名称
			resultMap.put("pic", fileUrl+couponDetailMap.get("pic"));//'优惠劵详情图
			resultMap.put("breviary", fileUrl+couponDetailMap.get("breviary"));//优惠劵列表图
			resultMap.put("stock", couponDetailMap.get("stock"));//剩余数量
			resultMap.put("sendNum", couponDetailMap.get("send_num"));//销售总量
			resultMap.put("voucherAmount", 0);//赠送粉丝券金额  首先默认为0
			resultMap.put("liveRecordId", liveBuyCouponRequest.getLiveRecordId());
			resultMap.put("orderAmount", 0);//赠送粉丝券金额  首先默认为0
			resultMap.put("useCouponAmount", 0);//初始化  返回使用抵用券金额
			resultMap.put("useCouponId", "-1");//初始化  返回使用抵用券ID
			
			resultMap.put("anchorId", liveRecordInfo.getAnchor_id());//初始化  返回使用抵用券ID
			resultMap.put("sellerid", seller.getSellerid());//初始化  返回使用抵用券ID
			resultMap.put("sellername", liveRecordInfo.getSellername());//商家名称
			resultMap.put("logo", "");//商家logo
			
			//获取商家logo
			List<SellerPic> sellerPics = sellerService.querySellerPicBySelleridAndType(seller.getSellerid(),1);
			if (sellerPics.size()>0) {
				resultMap.put("logo", sellerPics.get(0).getUrl());//商家logo
			}
			
			//赠送积分 乘以购买数量 此处为初始化
			resultMap.put("isIntegral", propertiesUtil.getValue("isIntegral", "conf_common.properties"));
			resultMap.put("integral", 0);
//			BigDecimal integral = new BigDecimal(couponDetailMap.get("denomination").toString()).multiply(new BigDecimal(liveBuyCouponRequest.getQuantity()));
//			resultMap.put("integral", integral);//初始化  返回使用抵用券ID
			//订单的原始金额 - 用于获取优惠券
			resultMap.put("originalAmount", new BigDecimal(couponDetailMap.get("denomination").toString()).multiply(new BigDecimal(liveBuyCouponRequest.getQuantity())));
			
			//使用粉丝券ID 查询关联的抵用券ID
			Map<Object, Object> useMap = new HashMap<Object, Object>();
			useMap.put("uid", uid);
			useMap.put("cid", couponDetailMap.get("cid"));
			//根据粉丝券的ID 查询对应配置的的抵用券  计算返回的抵用券 总额
			List<Map<Object, Object>> useCouponMapList = anchorLiveRecordDao.queryUseCouponListByCid(useMap);
			
			List<String> giveList = new ArrayList<String>();
			for (int i = 0; i < useCouponMapList.size(); i++) {
				//组装粉丝券 赠送抵用券的ID 装入List集合
				giveList.add(useCouponMapList.get(i).get("gift_cid").toString());
			}
			if (giveList.size()>0) {
				BigDecimal voucherAmount = new BigDecimal(0);
				List<Map<Object, Object>> resultGiveList =  anchorLiveRecordDao.queryCouponByCidList(giveList);
				for (int i = 0; i < useCouponMapList.size(); i++) {
					for (int j = 0; j < resultGiveList.size(); j++) {
						if (useCouponMapList.get(i).get("gift_cid").toString().equals(resultGiveList.get(j).get("cid").toString()) ) {
							//计算购买粉丝券 赠送的抵用券返回金额 并且要乘以配置抵用券时所配置的抵用券个数
							voucherAmount = voucherAmount.add(new BigDecimal(resultGiveList.get(j).get("denomination").toString()).multiply(new BigDecimal(useCouponMapList.get(i).get("num").toString())));
						}
					}
				}
				//计算购买粉丝券 赠送的抵用券返回金额 并且要乘以购买个数
				resultMap.put("voucherAmount", voucherAmount);
			}
			
			//获取默认抵用券     取ID抵用金额     根据 抵用券判断  具体返回 需要支付的总金额  是多少
			if (liveBuyCouponRequest.getCouponId() == 0) {
				//标示为选择抵用券  默认设置抵用券 根据当前订单金额查询有无抵用券  获取到最早发放 并且是大面额的   根据订单总金额查询抵用券列表
				BigDecimal buyAmount = new BigDecimal(couponDetailMap.get("denomination").toString()).multiply(new BigDecimal(liveBuyCouponRequest.getQuantity()));
				resultMap.put("orderAmount", buyAmount);
				Map<Object, Object> fansListMap = new HashMap<Object, Object>();
				fansListMap.put("amount", buyAmount);
				fansListMap.put("uid", uid);
				fansListMap.put("source", 3);
				//获取粉丝抵用券列表
				List<CouponInfo> couponInfoList = anchorLiveRecordDao.queryFansCouponListByAmount(fansListMap);
				if (couponInfoList.size()>0) {
					CouponInfo info = new CouponInfo();
					for (int i = 0; i < couponInfoList.size(); i++) {
						info  = couponInfoList.get(i);
						if (info.getIsAvailable() == 0) {
							//返回需要支付的总额
							BigDecimal orderAmount = new BigDecimal(couponDetailMap.get("denomination").toString()).multiply(new BigDecimal(liveBuyCouponRequest.getQuantity()));
							if (orderAmount.subtract(info.getDenomination()).compareTo(new BigDecimal("0"))>=0) {
								resultMap.put("orderAmount", orderAmount.subtract(info.getDenomination()));
							}else {
								resultMap.put("orderAmount", 0);
							}
							//返回使用的抵用券ID
							resultMap.put("useCouponId", info.getCdid());
							resultMap.put("useCouponAmount", info.getDenomination());
							
							//赠送积分 乘以购买数量
//							if (integral.subtract(info.getDenomination()).compareTo(new BigDecimal("0"))>=0) {
//								resultMap.put("integral", integral.subtract(info.getDenomination()));
//							}else {
//								resultMap.put("integral", 0);
//							}
							//计算购买粉丝券 赠送的抵用券返回金额  如果使用了抵用券 不赠送 设置赠送金额为0
							resultMap.put("voucherAmount", new BigDecimal(0));
							break;
						}
					}
					
				}else {
					resultMap.put("orderAmount", new BigDecimal(couponDetailMap.get("denomination").toString()).multiply(new BigDecimal(liveBuyCouponRequest.getQuantity())));
				}
			}else if(liveBuyCouponRequest.getCouponId()>0){
				//标示有选择预售抵用券
				Map<Object, Object> couponMap = new HashMap<Object, Object>();
				couponMap.put("cid", liveBuyCouponRequest.getCouponId());
				CouponInfo couponInfo = anchorLiveRecordDao.queryFansCouponInfoByCouponId(couponMap);
				if (couponInfo!=null && null != couponInfo.getCdid()) {
					
					//返回需要支付的总额
					BigDecimal orderAmount = new BigDecimal(couponDetailMap.get("denomination").toString()).multiply(new BigDecimal(liveBuyCouponRequest.getQuantity()));
					//判断使用抵用券后  金额是否会变成负数
					if (orderAmount.subtract(couponInfo.getDenomination()).compareTo(new BigDecimal("0"))>=0) {
						resultMap.put("orderAmount", orderAmount.subtract(couponInfo.getDenomination()));
					}else {
						resultMap.put("orderAmount", 0);
					}
					//返回使用的抵用券ID
					resultMap.put("useCouponId", couponInfo.getCdid());
					resultMap.put("useCouponAmount", couponInfo.getDenomination());
					
					//赠送积分 乘以购买数量
//					if (integral.subtract(couponInfo.getDenomination()).compareTo(new BigDecimal("0"))>=0) {
////						resultMap.put("integral", integral.subtract(couponInfo.getDenomination()));
//						resultMap.put("integral", 0);//赠送积分功能屏蔽
//					}else {
//						resultMap.put("integral", 0);
//					}
//					resultMap.put("integral", integral.subtract(couponInfo.getDenomination()));
					//计算购买粉丝券 赠送的抵用券返回金额  如果使用了抵用券 不赠送 设置赠送金额为0
					resultMap.put("voucherAmount", new BigDecimal(0));
				}else {
					resultMap.put("orderAmount", new BigDecimal(couponDetailMap.get("denomination").toString()).multiply(new BigDecimal(liveBuyCouponRequest.getQuantity())));
				}
			}else if(liveBuyCouponRequest.getCouponId()<0){
				//标示放弃使用了抵用券
				resultMap.put("orderAmount", new BigDecimal(couponDetailMap.get("denomination").toString()).multiply(new BigDecimal(liveBuyCouponRequest.getQuantity())));
			}
		
		} catch (Exception e) {
			log.info("订单操作异常");
			e.printStackTrace();
			throw new Exception("订单操作异常");
		}
		
		//返回钱包余额信息
		Map<String, String> xmnWalletMap = null;
		try {
			xmnWalletMap = liveGiftsInfoService.getXmnWalletBlance(uid);
			//钱包余额总金额
			BigDecimal amountMoney = new BigDecimal("0");
			if (xmnWalletMap!=null) {
				amountMoney = new BigDecimal(xmnWalletMap.get("zbalance"))
							.add(new BigDecimal(xmnWalletMap.get("commision")));
//							.add(new BigDecimal(xmnWalletMap.get("balance")));
				
				resultMap.put("amountMoney", amountMoney.toString());
			}else {
				log.info("获取会员钱包余额失败");
				throw new Exception("获取会员钱包余额失败");
			}
		} catch (Exception e) {
			log.info("获取钱包余额失败");
			e.printStackTrace();
			throw new Exception("获取钱包余额失败");
		}
		
		//返回直播钱包余额信息
		Map<String, Object> liveWalletMap = null;
		try {
			liveWalletMap = liveGiftsInfoService.getLiveWalletBlance(uid.toString());
			//直播钱包余额总金额
			if (liveWalletMap!=null) {
				
				BigDecimal balanceCoin = liveWalletMap.get("zbalance")==null?new BigDecimal("0"):new BigDecimal(liveWalletMap.get("zbalance").toString());
				BigDecimal originalBbalanceCoin = liveWalletMap.get("zbalanceCoin")==null?new BigDecimal("0"):new BigDecimal(liveWalletMap.get("zbalanceCoin").toString());
				resultMap.put("zbalance", originalBbalanceCoin);
				
				//判断鸟币余额 是否小于限制金额   
				resultMap.put("restrictive",0);
				if (null!=seller.getLiveCoinPay()) {//首先判断商家是否开启了鸟币支付
					if (seller.getLiveCoinPay()==1) {//开启鸟币支付：如果开启了鸟币支付则 还需判断 用户是否被限制使用鸟币
						resultMap.put("restrictive",1);//设置为1开启
						
						if (liveWalletMap.get("limitBalance")!=null) {
							//如果限制金额大于0 标示开启了限额
							if ((new BigDecimal(liveWalletMap.get("limitBalance").toString()).compareTo(new BigDecimal("0"))>0)) {
								BigDecimal limitBalance = new BigDecimal(liveWalletMap.get("limitBalance").toString());
								//如果当前鸟币 大于 限额 
								BigDecimal orderAmount = new BigDecimal(resultMap.get("orderAmount")==null?"0":resultMap.get("orderAmount").toString());
								//如果当前余额大于限额  并且  当前鸟币余额 减去订单金额 也大于限额 标示可以使用鸟币支付方式
								if (balanceCoin.compareTo(limitBalance)>0 && balanceCoin.subtract(orderAmount).compareTo(limitBalance)>=0) {
									resultMap.put("restrictive",1);
								}else {
									resultMap.put("restrictive",0);
								}
							}
						}
					}
				}
				
			}else {
				log.info("获取直播钱包余额失败");
				throw new Exception("获取直播钱包余额失败");
			}
		} catch (Exception e) {
			log.info("获取钱包余额失败");
			e.printStackTrace();
			throw new Exception("获取钱包余额失败");
		}
		return resultMap;
	}
	
	
	/**
	 * 描述：查询我的粉丝抵用券  金额为大于0时 标示查询所有抵用券
	 * @param liveQueryCouponRequest
	 * @return Object
	 * */
	public Object queryUseCoupon(LiveQueryCouponRequest liveQueryCouponRequest){
		
//		String uid = liveQueryCouponRequest.getUid().toString();
		//验证token
		String uid = sessionTokenService.getStringForValue(liveQueryCouponRequest.getSessiontoken()) + "";
		if (StringUtils.isEmpty(uid) || "null".equalsIgnoreCase(uid)) {
			return new BaseResponse(ResponseCode.TOKENERR, "token已失效,请重新登录");
		}
		MapResponse response = null;
		try {
			Map<Object, Object> resultMap = new HashMap<Object, Object>();
			List<CouponInfo> resultCouponList = new ArrayList<CouponInfo>();
			Map<Object, Object> fansListMap = new HashMap<Object, Object>();
			fansListMap.put("page", liveQueryCouponRequest.getPage());
			fansListMap.put("limit", Constant.PAGE_LIMIT);
			
//			if (liveQueryCouponRequest.getOrderAmount() > 0) {
				fansListMap.put("amount", liveQueryCouponRequest.getOrderAmount());
//			}
			fansListMap.put("uid", uid);
			fansListMap.put("source", liveQueryCouponRequest.getSource());//标示是购买粉丝券
			String appversion = liveQueryCouponRequest.getAppversion();
			appversion = appversion.replace(".", "");
			int appv = Integer.parseInt(appversion);
			if (appv<=360) {
				fansListMap.put("version", 0);
			}else {
				fansListMap.put("version", 1);
			}
			List<CouponInfo> couponInfoList = anchorLiveRecordDao.queryFansCouponListByAmount(fansListMap);
			if (couponInfoList.size()>0) {
				for (int i = 0; i < couponInfoList.size(); i++) {
					CouponInfo couponInfo = couponInfoList.get(i);
					couponInfo.setPic(fileUrl+couponInfo.getPic());
					couponInfo.setBreviary(fileUrl+couponInfo.getBreviary());
					
					if (couponInfo.getCondition().compareTo(new BigDecimal(0))>0) {
						couponInfo.setConditionStr("满"+couponInfo.getCondition()+"可用");
					}else {
						couponInfo.setConditionStr("不限使用");
					}
					couponInfo.setUseTimeDesc("有效期:"+DateUtil.format(couponInfo.getStartDate(),DateUtil.minuteSimpleFormater)+"至:"+DateUtil.format(couponInfo.getEndDate(),DateUtil.minuteSimpleFormater));
//					couponInfo.setEndDateStr("有效期至:"+DateUtil.format(couponInfo.getEndDate(),DateUtil.daySimpleFormater));
					resultCouponList.add(couponInfo);
				}
			}
			resultMap.put("couponList", resultCouponList);
			response = new MapResponse(ResponseCode.SUCCESS, "获取粉丝券成功");
			response.setResponse(resultMap);
			return response;
		} catch (Exception e) {
			log.info("获取用户抵用券异常!");
			e.printStackTrace();
			response = new MapResponse(ResponseCode.FAILURE, "获取粉丝券失败");
		}
		return response;
	}
	
	/**
	 * 描述：获取主播的 有预售信息的直播记录    已经直播完,已经过了试用期的不显示 
	 * @param LiveRecordFansCouponRequest
	 * @return Object 
	 * */
	public Object queryLiveRecordFansCoupon(LiveRecordFansCouponRequest liveRecordFansCouponRequest){
//		String uid = liveRecordFansCouponRequest.getUid().toString();
		//验证token
		MapResponse response = null;
		String uid = sessionTokenService.getStringForValue(liveRecordFansCouponRequest.getSessiontoken()) + "";
//		if (StringUtils.isEmpty(uid) || "null".equalsIgnoreCase(uid)) {
//			return new BaseResponse(ResponseCode.TOKENERR, "token已失效,请重新登录");
//		}
		try {
			//根据 主播会员ID 查询直通通告的预售列表
			Map<Object, Object> paramMap = new HashMap<Object, Object>();
			paramMap.put("uid", liveRecordFansCouponRequest.getAnchorUid());
			paramMap.put("page", liveRecordFansCouponRequest.getPage());
			paramMap.put("limit", Constant.PAGE_LIMIT);
			Map<Object, Object> liverInfoMap = liveUserDao.queryLiverInfoByUid(liveRecordFansCouponRequest.getAnchorUid());
			paramMap.put("anchorId", liverInfoMap.get("id"));
			paramMap.put("phone", liverInfoMap.get("phone"));
			//查询该主播的直播及预售 记录  
			
			List<Map<Object, Object>> recordList = new ArrayList<Map<Object, Object>>();
			if (liveRecordFansCouponRequest.getPosition() == 0) {//标示只需要展示悬浮
				paramMap.put("position",1);//只查询推荐的
				recordList = anchorLiveRecordDao.queryLiveRecordFansCouponList(paramMap);
			}else {
				paramMap.put("position",0);//查本场主播和其他主播推荐  包含本场主播其他的粉丝券
				recordList = anchorLiveRecordDao.queryLiveRecordFansCouponList(paramMap);
			}
			
			if (recordList.size()>0) {
				Map<Object, Object> resultMap = new HashMap<Object, Object>();
				List<Map<Object, Object>> list = new ArrayList<Map<Object, Object>>();
				//根据预售显示位置分别的返回 不同结果行数   0 直播间悬浮窗
				if (liveRecordFansCouponRequest.getPosition() == 0) {
					Map<Object, Object> recordMap = new HashMap<Object, Object>();
					for (int i = 0; i < recordList.size(); i++) {
						recordMap = recordList.get(i);
						int isPreSell = Integer.parseInt(recordMap.get("isPreSell").toString());
						//获取有预售的 并且是最新的记录 
						if (isPreSell == 1) {
							recordMap.put("zhiboCover", fileUrl+(recordMap.get("zhiboThumbnail")==null?recordMap.get("zhiboCover"):recordMap.get("zhiboThumbnail")));
//							recordMap.put("zhiboCover", fileUrl+recordMap.get("zhiboCover"));
							recordMap.put("planStartDate", "直播时间："+DateUtil.format(DateUtil.parse(recordMap.get("planStartDate").toString()),"yyyy-MM-dd HH:mm"));
							list.add(recordMap);
							break;
						}
					}
					resultMap.put("recordFansCoupons", list);
					response = new MapResponse(ResponseCode.SUCCESS, "获取成功");
					response.setResponse(resultMap);
					
				}else {
					
					//根据直播用户ID查询 是否有想看的记录
					
					if (liverInfoMap!=null) {
						
						if (!StringUtils.isEmpty(uid) && !"null".equalsIgnoreCase(uid)) {

							//查询当前用户的直播用户Id
							Map<Object, Object> cerrLiver = liveUserDao.queryLiverInfoByUid(Integer.parseInt(uid));
							
							//查询是否有改用户想去列表
							List<Object> liveRecordIdList = new ArrayList<Object>();
//							Integer liverId =  Integer.parseInt(liverInfoMap.get("id").toString());
							Map<Object, Object> focusMap = new HashMap<Object, Object>();
							focusMap.put("liverStrId", cerrLiver.get("id"));
							//将直播列表组装到List  便于查询 是否 点击了想去按钮
							for (int i = 0; i <  recordList.size(); i++) {
								liveRecordIdList.add(recordList.get(i).get("liveRecordId"));
							}
							
							List<Map<Object, Object>> recordResultList = new ArrayList<Map<Object, Object>>();
							focusMap.put("liveRecordIdList", liveRecordIdList);
							//查询 该主播下直播记录  是否点击了想去  并且统计人数
							List<Map<Object, Object>> focusList = liveUserDao.queryFocusShowList(focusMap);
							if (focusList.size()>0) {
								for (int i = 0; i < recordList.size(); i++) {
									for (int j = 0; j < focusList.size(); j++) {
										if (null != focusList.get(j).get("live_record_id") && null != recordList.get(i).get("liveRecordId") 
												&&  focusList.get(j).get("live_record_id").toString().equals(recordList.get(i).get("liveRecordId").toString())) {
											recordList.get(i).put("isWant", "2");//已经想去
											recordList.get(i).put("zhiboCover", fileUrl+(recordList.get(i).get("zhiboThumbnail")==null?recordList.get(i).get("zhiboCover"):recordList.get(i).get("zhiboThumbnail")));
											
											Map<Object, Object> wantGoMap =liveUserDao.queryFocusShowListCount(recordList.get(i));
											if (wantGoMap!=null) {
												recordList.get(i).put("counts",wantGoMap.get("counts"));
											}else {
												recordList.get(i).put("counts",0);
											}
											
											recordResultList.add(recordList.get(i));
										}else {
											recordList.get(i).put("isWant", "1");//未想去
											recordList.get(i).put("breviary", fileUrl+recordList.get(i).get("breviary"));
											recordList.get(i).put("pic", fileUrl+recordList.get(i).get("pic"));
											recordList.get(i).put("zhiboCover", fileUrl+(recordList.get(i).get("zhiboThumbnail")==null?recordList.get(i).get("zhiboCover"):recordList.get(i).get("zhiboThumbnail")));
											recordResultList.add(recordList.get(i));
										}
									}
									recordList.get(i).put("planStartDate", "直播时间："+DateUtil.format(DateUtil.parse(recordList.get(i).get("planStartDate").toString()),"yyyy-MM-dd HH:mm"));
								}
								resultMap.put("recordFansCoupons", recordResultList);
								response = new MapResponse(ResponseCode.SUCCESS, "获取成功");
								response.setResponse(resultMap);
								
							}else {
								for (int i = 0; i < recordList.size(); i++) {
									Map<Object, Object> map = recordList.get(i);
									map.put("breviary", fileUrl+map.get("breviary"));
									map.put("pic", fileUrl+map.get("pic"));
									map.put("zhiboCover", fileUrl+(map.get("zhiboThumbnail")==null?map.get("zhiboCover"):map.get("zhiboThumbnail")));
									map.put("planStartDate", "直播时间："+DateUtil.format(DateUtil.parse(map.get("planStartDate").toString()),"yyyy-MM-dd HH:mm"));
									map.put("isWant", "1");//没想去
									list.add(map);
								}
								
								resultMap.put("recordFansCoupons", list);
								response = new MapResponse(ResponseCode.SUCCESS, "获取成功");
								response.setResponse(resultMap);
							}
						}else {
						
							for (int i = 0; i < recordList.size(); i++) {
								Map<Object, Object> map = recordList.get(i);
								map.put("breviary", fileUrl+map.get("breviary"));
								map.put("pic", fileUrl+map.get("pic"));
								map.put("zhiboCover", fileUrl+(map.get("zhiboThumbnail")==null?map.get("zhiboCover"):map.get("zhiboThumbnail")));
								map.put("planStartDate", "直播时间："+DateUtil.format(DateUtil.parse(map.get("planStartDate").toString()),"yyyy-MM-dd HH:mm"));
								map.put("isWant", "1");//没想去
								list.add(map);
							}
							resultMap.put("recordFansCoupons", list);
							response = new MapResponse(ResponseCode.SUCCESS, "获取成功");
							response.setResponse(resultMap);
							
						}
							
					}
				}
				
			} else {
				Map<Object, Object> map = new HashMap<Object, Object>();
				map.put("recordFansCoupons", recordList);
				response = new MapResponse(ResponseCode.SUCCESS, "获取直播通告预售成功");
				response.setResponse(map);
			}
			
		} catch (Exception e) {
			log.info("获取直播通告预售失败："+uid);
			e.printStackTrace();
			return new MapResponse(ResponseCode.FAILURE, "获取直播通告预售失败");
		}
		return response;
	}
	
	/**
	 * 描述：粉丝订单取消接口
	 * @param LiveFansOrderCancleRequest
	 * @return Object
	 * */
	public Object fansOrderCancle(LiveFansOrderCancleRequest liveFansOrderCancleRequest){
//		String uid = liveFansOrderCancleRequest.getUid().toString();
		//验证token
		String uid = sessionTokenService.getStringForValue(liveFansOrderCancleRequest.getSessiontoken()) + "";
		if (StringUtils.isEmpty(uid) || "null".equalsIgnoreCase(uid)) {
			return new BaseResponse(ResponseCode.TOKENERR, "token已失效,请重新登录");
		}
		MapResponse response = null;
			
		try{
			Map<Object, Object> map = new HashMap<Object, Object>();
			map.put("orderNo", liveFansOrderCancleRequest.getOrderNo());
			//获取订单基本信息
			Map<Object, Object> orderMap = anchorLiveRecordDao.queryCouponFansOrderByNo(map);
			if (orderMap!=null && orderMap.size()>0) {
				//取消订单组装
				Map<String, String> cancelMap = new HashMap<String, String>();
				cancelMap.put("orderNumber",orderMap.get("order_sn").toString());
//					cancelMap.put("amount", orderMap.get("total_amount").toString());
				cancelMap.put("randomNumber", (int)((Math.random()*9+1)*100000)+"");
				
				String url = this.transMap(cancelMap);
				//请求接口
				String result = HttpConnectionUtil.doPost(url, "");
				if (StringUtils.isNotEmpty(result)) {
					if (result.indexOf("state") != -1) {
						JSONObject json = JSONObject.parseObject(result);
						int state = Integer.parseInt(json.get("state").toString());
						if (state == 201 || state == 200 ) {
							return new MapResponse(ResponseCode.SUCCESS, "操作成功");
						}else {
							return new MapResponse(ResponseCode.FAILURE, json.get("info")==null?"操作失败":json.get("info").toString());
						}
					}
				}else {
					return new BaseResponse(ResponseCode.FAILURE, "调用支付接口失败");
				}
			}
				
			
		} catch (Exception e) {
			log.info("取消订单异常");
			e.printStackTrace();
			response = new MapResponse(ResponseCode.FAILURE, "操作成功");
		}
		return response;
	}
	
	
	/**
	 * 描述：粉丝订单取消接口
	 * @param LiveFansOrderCancleRequest
	 * @return Object
	 * */
	public Object fansOrderUseCoupon(LiveFansOrderUseCouponRequest liveFansOrderUseCouponRequest){
		
		Map<Object, Object> resultMap = new HashMap<Object, Object>();
		List<Map<Object, Object>> resultList = new ArrayList<Map<Object, Object>>();
		String uid = sessionTokenService.getStringForValue(liveFansOrderUseCouponRequest.getSessiontoken()) + "";
		if (StringUtils.isEmpty(uid) || "null".equalsIgnoreCase(uid)) {
			return new BaseResponse(ResponseCode.TOKENERR, "token已失效,请重新登录");
		}
		MapResponse response = null;
			
		try{
			//获取粉丝券购买赠送的抵用券基本信息
			String useCouponDesc = propertiesUtil.getValue("fansBuySucessDesc", "conf_common.properties");
			List<Map<Object, Object>> orderListMap = anchorLiveRecordDao.queryFansOrderUseCouponByNo(liveFansOrderUseCouponRequest.getOrderNo());
			if (orderListMap.size()>0) {
				resultMap.put("isFansUseCoupon", 1);
				for (int i = 0; i < orderListMap.size(); i++) {
					Map<Object, Object> fansMap = orderListMap.get(i);
					fansMap.put("cdid", fansMap.get("cdid"));
					fansMap.put("denomination", fansMap.get("denomination"));
					fansMap.put("cname", fansMap.get("cname"));
					if (new BigDecimal(fansMap.get("condition").toString()).compareTo(new BigDecimal(0))>0 ) {
						fansMap.put("condition", "消费满¥"+fansMap.get("condition")+"使用");
					}else {
						fansMap.put("condition", "不限制使用");
					}
					resultList.add(fansMap);
				}
			}else {
				resultMap.put("isFansUseCoupon", 0);
			}
			resultMap.put("useCouponList", resultList);
			resultMap.put("useCouponDesc", useCouponDesc);
			response = new MapResponse(ResponseCode.SUCCESS,"操作成功");
			response.setResponse(resultMap);
			return response;
			
		} catch (Exception e) {
			log.info("取消订单异常");
			e.printStackTrace();
			response = new MapResponse(ResponseCode.FAILURE, "操作成功");
		}
		return response;
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
		String key = propertiesUtil.getValue("payBirdKey", "conf_live.properties");
		//签名
		transmap.put("sign", Signature.sign(transmap,key));	
		
		String url = propertiesUtil.getValue("cancleFansOrderUrl", "conf_live.properties");
		String request = "";
		  for(Entry<String, String> entry : transmap.entrySet()){
		        	request += "&" + entry.getKey() + "=" + entry.getValue();
		   }
		        
		request = request.substring(1, request.length());
		        
		System.out.println(url + "?" + request);
		String payurl=url + "?" + request;
			    
		return payurl;
	}
	
	
}
