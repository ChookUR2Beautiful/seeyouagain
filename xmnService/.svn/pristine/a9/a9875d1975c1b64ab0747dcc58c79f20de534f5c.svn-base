package com.xmniao.xmn.core.personal.service;

import com.alibaba.fastjson.JSONObject;
import com.xmniao.xmn.core.api.controller.personal.ReceivingAddressListApi;
import com.xmniao.xmn.core.api.controller.seller.SellerDetailApi;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.Constant;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.personal.PersonalInfoDetailRequest;
import com.xmniao.xmn.core.common.request.personal.PersonalInfoRequset;
import com.xmniao.xmn.core.live.dao.*;
import com.xmniao.xmn.core.live.service.AnchorSignTypeService;
import com.xmniao.xmn.core.live.service.LiveHomeService;
import com.xmniao.xmn.core.live.service.LiveUserService;
import com.xmniao.xmn.core.live.service.PersonalCenterService;
import com.xmniao.xmn.core.market.dao.FreshIndianaRobotDao;
import com.xmniao.xmn.core.order.dao.CouponFansOrderDao;
import com.xmniao.xmn.core.personal.dao.PersonalLiveDao;
import com.xmniao.xmn.core.seller.dao.UrsCollectDao;
import com.xmniao.xmn.core.util.DateUtil;
import com.xmniao.xmn.core.util.PropertiesUtil;
import com.xmniao.xmn.core.util.StrUtils;
import com.xmniao.xmn.core.util.VersionUtil;
import com.xmniao.xmn.core.verification.dao.BillDao;
import com.xmniao.xmn.core.verification.dao.UrsDao;
import com.xmniao.xmn.core.verification.entity.Urs;
import com.xmniao.xmn.core.wealth.dao.IncomeInfoDao;
import com.xmniao.xmn.core.xmer.dao.SellerDao;
import com.xmniao.xmn.core.xmer.dao.SellerInfoDao;
import com.xmniao.xmn.core.xmer.dao.XmerDao;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：PersonalInfoDetailService   
* 类描述：   用户/主播详情处理service
* 创建人：yezhiyong   
* 创建时间：2016年11月17日 下午6:17:32   
* @version    
*
 */
@Service
public class PersonalInfoDetailService {
	
	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(ReceivingAddressListApi.class);
	
	/**
	 * 注入缓存
	 */
	@Autowired
	private SessionTokenService sessionTokenService;
	
	/**
	 * 注入liveUserDao
	 */
	@Autowired
	private LiveUserDao liveUserDao;
	
	/**
	 * 注入图片服务器地址
	 */
	@Autowired
	private String fileUrl;
	
	/**
	 * 注入billDao
	 */
	@Autowired
	private BillDao billDao;
	
	/**
	 * 注入personalcenterDao
	 */
	@Autowired
	private PersonalCenterDao personalcenterDao;
	
	/**
	 * 注入anchorLiveRecordDao
	 */
	@Autowired
	private AnchorLiveRecordDao anchorLiveRecordDao;
	
	/**
	 * 注入sellerDao
	 */
	@Autowired
	private SellerDao sellerDao;
	
	/**
	 * 注入xmerDao
	 */
	@Autowired
	private XmerDao xmerDao;
	
	/**
	 * 注入incomeInfoDao
	 */
	@Autowired
	private IncomeInfoDao incomeInfoDao;
	
	/**
	 * 注入ursDao
	 */
	@Autowired
	private UrsDao ursDao;
	
	/**
	 * 注入liveGiftsInfoDao
	 */
	@Autowired
	private LiveGiftsInfoDao liveGiftsInfoDao;
	
	/**
	 * 注入propertiesUtil
	 */
	@Autowired
	private PropertiesUtil propertiesUtil;
	
	/**
	 * 注入personaldetailDao
	 */
	@Autowired
	private PersonalDetailDao personaldetailDao;
	
	/**
	 * 注入sellerInfoDao
	 */
	@Autowired
	private SellerInfoDao sellerInfoDao;
	
	/**
	 * 注入ursCollectDao
	 */
	@Autowired
	private UrsCollectDao ursCollectDao;
	
	/**
	 * 注入couponFansOrderDao
	 */
	@Autowired
	private CouponFansOrderDao couponFansOrderDao;
	
	/**
	 * 注入liveHomeService
	 */
	@Autowired
	private LiveHomeService liveHomeService;
	
	/**
	 * 注入liveUserService
	 */
	@Autowired
	private LiveUserService liveUserService;
	@Autowired
	private FreshIndianaRobotDao freshIndianaRobotDao;
	@Autowired
	private PersonalLiveDao personalLiveDao;
	@Autowired
	private PersonalCenterService personalCenterService;
	@Autowired
	private AnchorSignTypeService anchorSignTypeService;
	@Autowired
	private PersonalDetailDao personalDetailDao;

	/**
	 * 
	* @Title: queryPersonalInfo
	* @Description: 获取用户/主播详情
	* @return Object    返回类型
	* @throws
	 */
	public Object queryPersonalInfo(PersonalInfoRequset personalInfoRequset) {
		//被查看用户详情的uid
		Integer toUid = personalInfoRequset.getUid();
		String uid = "";
		//判断自己是否登录
		if (personalInfoRequset.getSessiontoken() != null) {
			//验证token
			uid = sessionTokenService.getStringForValue(personalInfoRequset.getSessiontoken()) + "";
			
		}
		try {
			//结果集
			Map<Object, Object> resultMap = new HashMap<>();
			//组装参数
			Map<Object, Object> paramMap = new HashMap<>();
			
			//查询用户信息
			Map<Object, Object> liverMap = new HashedMap();
            if (personalInfoRequset.getUserType() != null && personalInfoRequset.getUserType() ==1) {
                liverMap = freshIndianaRobotDao.selectById(personalInfoRequset.getUid());
                liverMap.put("utype","1");
            }else {
                liverMap = liveUserDao.queryLiverInfoByUid(toUid);
            }
			if (liverMap == null || liverMap.size() <= 0) {
				//给旧用户创建直播用户信息
				BaseResponse response = (BaseResponse) liveUserService.createTlsUser(toUid + "");
				if (response.getState() != 100) {
					return response;
				}
				
				liverMap = liveUserDao.queryLiverInfoByUid(toUid);
			}
			
			/**
			 * 给定默认没有登录的默认值
			 */
			//是否关注过对方
			resultMap.put("isFocus", 0);
			//是否是自己查看自己的详情页
			resultMap.put("isMyseft", 0);
			//查询去过相同消费的店铺数量
			resultMap.put("sameConsumerSellerCount", 0);
			//查询关注相同的好友数量
			resultMap.put("sameFocusCount", 0);
			
			//判断是否登录过
			if (StringUtils.isNotEmpty(uid) && !"null".equalsIgnoreCase(uid)) {
				/**
				 * 登录后的需要数据
				 */
				//判断是否是自己查看自己的详情页
				if (Integer.parseInt(uid) == toUid) {
					resultMap.put("isMyseft", 1);
					resultMap.put("describe", propertiesUtil.getValue("personalDescribe", "conf_common.properties"));
				}
				
				//查询是否关注过对方
				Map<Object, Object> ownLiverMap = liveUserDao.queryLiverInfoByUid(Integer.parseInt(uid));
				if (ownLiverMap == null || ownLiverMap.size() <= 0) {
					return new BaseResponse(ResponseCode.FAILURE, "未知错误,请联系管理员");
				}
				
				paramMap.put("liverStrId", ownLiverMap.get("id"));
				paramMap.put("liverEndId", liverMap.get("id"));
				
				//查询是否关注过此用户
				Integer focusResult = liveUserDao.queryFocusAnchor(paramMap);
				if (focusResult > 0) {
					resultMap.put("isFocus", 1);
				}
				
				//查询去过相同消费的店铺数量
				paramMap.clear();
				paramMap.put("uid", Integer.parseInt(uid));
				paramMap.put("toUid", toUid);
				Integer sameConsumerSellerCount = billDao.querySameConsumerSellerCount(paramMap);
				resultMap.put("sameConsumerSellerCount", sameConsumerSellerCount);
				
				//查询关注相同的好友数量
				paramMap.clear();
				paramMap.put("liverId", ownLiverMap.get("id"));
				paramMap.put("toLiverId", liverMap.get("id"));
				Integer sameFocusCount = liveUserDao.querySameFocusCount(paramMap);
				resultMap.put("sameFocusCount", sameFocusCount);
				
			}
			
			//返回用户跟主播相同字段
			resultMap.put("avatar", liverMap.get("avatar")==null?"":fileUrl + liverMap.get("avatar").toString());
			resultMap.put("sex", liverMap.get("sex")==null?0:Integer.parseInt(liverMap.get("sex").toString()));
			//私信需要
			resultMap.put("phone", liverMap.get("uname"));
			
			//判断是微信用户还是手机号码用户,如果是手机号码用户,并且昵称为空,则设置用户昵称为手机号码，中间4位数为*,如果是微信用户，并且用户昵称为空,则取微信用户 + 中间4位
			String nnameStr = "";
			if (liverMap.get("nname") == null || StringUtils.isEmpty(liverMap.get("nname").toString())) {
				if (liverMap.get("uname") != null) {
					String unameStr = liverMap.get("uname").toString();
					if (unameStr.length() == 11) {
						nnameStr = unameStr.substring(0, 3) + "****" + unameStr.substring(unameStr.length() - 4);
						
					}else {
						nnameStr="微信用户" + unameStr.substring(3,7);
						//私信需要
						resultMap.put("phone", unameStr);
					}
				}else {
					if (liverMap.get("phone")!=null) {
						nnameStr = liverMap.get("phone").toString();
						nnameStr = nnameStr.substring(0, 3) + "****" + nnameStr.substring(nnameStr.length() - 4);
						
//						nnameStr = liverMap.get("phone").substring(0, 3) ;
					}else {
//						nnameStr="微信用户" + liverMap.get("openid").toString().substring(3,7);
						nnameStr="匿名用户";
					}

					
					//私信需要
//					resultMap.put("phone", liverMap.get("openid").toString());
					resultMap.put("phone", liverMap.get("uid").toString());
				}
				
			}else {
				nnameStr = liverMap.get("nname").toString();
			}
			
			resultMap.put("nname", nnameStr);
			resultMap.put("rankNo", liverMap.get("rank_no")==null?1:Integer.parseInt(liverMap.get("rank_no").toString()));
			
			//查询去过的店铺总数
			Integer consumerSellerCount = billDao.queryConsumerSellerCount(toUid);
			resultMap.put("consumerSellerCount", consumerSellerCount);
			
			//'直播用户类型： 1 主播 2 普通用户',
			int utype = Integer.parseInt(liverMap.get("utype").toString());
			//是否是主播   0 否 1 是 
			resultMap.put("isAnchor", 0);
			//是否是寻蜜客 0 否  1是
			resultMap.put("isXmer", 0);
			if (utype == 1) {
				Integer fansCount = personalDetailDao.selectFansCount(toUid);
				fansCount = fansCount*5;
				Integer focusCount = personalDetailDao.selectFocusCount(toUid);
				Integer commentCount = personalLiveDao.selectCommentRecordCount(toUid);

				//主播详情
				resultMap.put("wechatGroup", liverMap.get("wechat_group")==null?"":liverMap.get("wechat_group"));//微信群号
//				resultMap.put("concernNums", liverMap.get("concern_nums")==null?0:liverMap.get("concern_nums"));//关注数
//				resultMap.put("FanNums", liverMap.get("concerned_nums")==null?0:liverMap.get("concerned_nums"));//粉丝数
				resultMap.put("concernNums", focusCount);//关注数
				resultMap.put("FanNums", fansCount);//粉丝数
				
				//查询主播的直播动态数
				Map<Object, Object> map = personalcenterDao.queryLiveRecordNum(Integer.parseInt(liverMap.get("id").toString()));
				// 查询店铺数

				Integer liveRecordSum = map.get("liveRecordAllSum")==null?0:Integer.parseInt(map.get("liveRecordAllSum").toString());
				liveRecordSum = commentCount + liveRecordSum;

				//直播动态数
				resultMap.put("liveRecordSum", liveRecordSum);
				resultMap.put("isAnchor", 1);//是否是主播   0 否 1 是 
				resultMap.put("anchorId", liverMap.get("id"));//是否是主播   0 否 1 是
				Integer signType = anchorSignTypeService.getSignType(liverMap);
				resultMap.put("signType", signType);  // 是否是签约主播 0否 1是 3美食体验官, 4大赛主播
			}else if (utype == 2) {
				//普通用户详情,参加过的直播活动总数
				Integer liveActivityCount = billDao.queryCouponOrderCount(toUid);
				// 参加点评活动
				Integer commentCount = personalLiveDao.selectCommentRecordCount(toUid);
				liveActivityCount = liveActivityCount == null ? 0 : liveActivityCount;
				liveActivityCount = liveActivityCount + commentCount;
				resultMap.put("liveActivityCount", liveActivityCount);
			}
			
			// 查询是否是寻蜜客
			Map<Object, Object> xmermap = xmerDao.queryXmerInfo(toUid);
			if (xmermap != null && xmermap.size() > 0) {
				resultMap.put("isXmer", 1);//是否是寻蜜客 0 否  1是
				resultMap.put("xmerId", xmermap.get("id"));//是否是寻蜜客 0 否  1是
				//查询寻蜜客总收入
				Double totalincome = incomeInfoDao.sumIncome(toUid);
				if(totalincome == null){
					totalincome = 0D;
				}
				DecimalFormat df = new DecimalFormat("0.00");//格式化数据
				BigDecimal b = new BigDecimal(totalincome);//格式化寻蜜客总收入
				String xmerIncome = df.format(b.setScale(2, RoundingMode.HALF_UP).doubleValue());//格式化后的寻蜜客总收入
				//寻蜜客收入描述
				String xmerIncomeDescribe = propertiesUtil.getValue("xmerIncomeDescribe", "conf_common.properties");
				resultMap.put("xmerIncome", xmerIncomeDescribe + xmerIncome);
			}
			
			//响应
			MapResponse response = new MapResponse(ResponseCode.SUCCESS, "获取用户详情信息成功");
			response.setResponse(resultMap);
			return response;
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("获取用户详情信息失败,用户uid=" + toUid + ",错误信息如下" + e.getMessage());
			return new BaseResponse(ResponseCode.FAILURE, "获取用户详情信息失败");
		}
	}

	/**
	 * 
	* @Title: queryPersonalInfoDetail
	* @Description: 用户/主播详情 活动行为   1 主播直播动态  2去过的店铺  3粉丝  4关注 5参加的直播活动  6成长足迹
	* @return Object    返回类型
	* @throws
	 */
	public Object queryPersonalInfoDetail(PersonalInfoDetailRequest personalInfoDetailRequest) {
		String uid = "";
		//判断自己是否登录
		if (personalInfoDetailRequest.getSessiontoken() != null) {
			//验证token
			uid = sessionTokenService.getStringForValue(personalInfoDetailRequest.getSessiontoken()) + "";
			
		}
		
		try {
			//活动行为  1 主播直播动态  2去过的店铺  3粉丝  4关注 5参加的直播活动  6成长足迹
			Integer type = personalInfoDetailRequest.getType();
			//被查看用户/主播详情活动行为的uid
			Integer toUid = personalInfoDetailRequest.getUid();
			//页码
			Integer page = personalInfoDetailRequest.getPage();
			//纬度
			Double latitude = personalInfoDetailRequest.getLatitude();
			//经度
			Double longitude = personalInfoDetailRequest.getLongitude();
			int apiVersion = VersionUtil.getVersionCode(personalInfoDetailRequest);
			if (type == 1) {
				//查询主播直播动态
				return this.queryliveDynamic(toUid,page,longitude,latitude, personalInfoDetailRequest.getMaxTime(), apiVersion);
				
			}else if (type == 2) {
				//查询用户/主播去过的店铺信息
				return this.queryCustomerSellerList(toUid,page);
				
			}else if (type == 3) {
				//查询主播的粉丝列表
				return this.queryFansList(toUid,page,1,uid);
				
			}else if (type == 4) {
				//查询主播的关注列表
				return this.queryFocusList(toUid,page,1,uid);
				
			}else if (type == 5) {
				//查询参加过的直播活动
				return this.queryAttentionLiveActivityList(toUid,page,longitude,latitude, personalInfoDetailRequest.getMaxTime(), apiVersion);
				
			}else if (type == 6) {
				if (page != null && page != 1) {
					return new BaseResponse(ResponseCode.FAILURE, "页码错误");
				}
				//获取成长足迹
				return this.queryGrowthTrack(toUid);
				
			}else {
				return new BaseResponse(ResponseCode.FAILURE, "参数有误");
				
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE, "未知错误,请联系管理员");
			
		}
	}
	
	/**
	 * 
	* @Title: queryGrowthTrack
	* @Description: 获取成长足迹
	* @return Object    返回类型
	* @throws
	 */
	public Object queryGrowthTrack(Integer toUid) {
		try {
			//结果集
			Map<Object, Object> resultMap = new HashMap<>();
			List<Map<Object, Object>> resultList = new ArrayList<>();
			
			//查询用户信息
			Urs urs = ursDao.queryUrsByUid(toUid);
			Map<Object, Object> liverMap = liveUserDao.queryLiverInfoByUid(toUid);
			if (urs == null || liverMap == null || liverMap.size() <= 0) {
				return new BaseResponse(ResponseCode.FAILURE, "查无此用户信息");
			}
			
			String url = propertiesUtil.getValue("local.domain", "conf_redis.properties");
			
			Map<Object, Object> regMap = new HashMap<>();
			//位置类型
			regMap.put("type", 1);
			//注册文字
			regMap.put("text", propertiesUtil.getValue("regText", "conf_common.properties"));
			//注册图片
			regMap.put("img", url+"/img/regImg.png");
			//格式化注册用户时间
			String time = "";
			if (urs.getRegtime() != null) {
				String[] timeStr = DateUtil.format(urs.getRegtime(),"yyyy-MM-dd").split("-");
				for (int i = 0; i < timeStr.length; i++) {
					if (i == 0) {
						time = timeStr[i] + "\n";
					}else {
						time += timeStr[i] + ".";
					}
				}
				regMap.put("time", time.substring(0, time.length()-1));
				
			}else {
				regMap.put("time", "");
				
			}
			//图片描述
			regMap.put("describe", "");
			//性别
			regMap.put("sex", "");
			
			//添加至返回结果集中
			resultList.add(regMap);
			
			try {
				//查询第一次收藏的商户封面图
				Map<Object, Object> firstCollectSellerMap = sellerDao.queryFirstCollectSellerByUid(toUid);
				
				if (firstCollectSellerMap != null && firstCollectSellerMap.size() > 0) {
					Map<Object, Object> map = new HashMap<>();
					//位置类型
					map.put("type", 2);
					//收藏店铺文字
					map.put("text", propertiesUtil.getValue("collectSellerText", "conf_common.properties"));
					//店铺关注时间
					time = "";
					if (firstCollectSellerMap.get("collectTime") != null) {
						Date collectDate = DateUtil.parse(firstCollectSellerMap.get("collectTime").toString());
						String[] collectTime = DateUtil.format(collectDate,"yyyy-MM-dd").split("-");
						for (int i = 0; i < collectTime.length; i++) {
							if (i == 0) {
								time = collectTime[i] + "\n";
							}else {
								time += collectTime[i] + ".";
							}
						}
						map.put("time", time.substring(0, time.length()-1));
						
					}else {
						map.put("time", "");
						
					}
					//店铺名称
					map.put("describe", firstCollectSellerMap.get("sellerName")==null?"":firstCollectSellerMap.get("sellerName").toString());
					//店铺封面图
					map.put("img", firstCollectSellerMap.get("sellerCover")==null?"":fileUrl + firstCollectSellerMap.get("sellerCover").toString());
					//性别
					map.put("sex", "");
					//存放第一次收藏的商户信息
					resultList.add(map);
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.info("查询用户详情页个人足迹,第一次收藏的商户信息失败,错误信息如下:" + e.getMessage());
			}
			
			try {
				//查询最喜欢消费的店铺信息
				Map<Object, Object> favoriteSellerMap = billDao.queryFavoriteSellerByUid(toUid);
				
				if (favoriteSellerMap != null && favoriteSellerMap.size() > 0) {
					Map<Object, Object> map = new HashMap<>();
					//位置类型
					map.put("type", 3);
					//最喜欢消费的店铺文字
					map.put("text", propertiesUtil.getValue("favoriteSellerText", "conf_common.properties"));
					//店铺名称
					map.put("describe", favoriteSellerMap.get("sellerName")==null?"":favoriteSellerMap.get("sellerName").toString());
					//店铺封面图
					map.put("img", favoriteSellerMap.get("sellerCover")==null?"":fileUrl + favoriteSellerMap.get("sellerCover").toString());
					//性别
					map.put("sex", "");
					//时间
					map.put("time", "");
					//存放最喜欢消费的店铺信息
					resultList.add(map);
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.info("查询用户详情页个人足迹,最喜欢消费的店铺信息失败,错误信息如下:" + e.getMessage());
			}
			
			try {
				//查询送给最多礼物的一个主播
				Map<Object, Object> favoriteAnchorMap = liveGiftsInfoDao.queryFavoriteAnchorByLiverId(Integer.parseInt(liverMap.get("id").toString()));
				
				if (favoriteAnchorMap != null && favoriteAnchorMap.size() > 0) {
					Map<Object, Object> map = new HashMap<>();
					Map<Object, Object> anchorMap = liveUserDao.queryLiverInfoById(Integer.parseInt(favoriteAnchorMap.get("anchorId").toString()));
					//位置类型
					map.put("type", 4);
					//最喜欢看的主播文字
					map.put("text", propertiesUtil.getValue("favoriteAnchorText", "conf_common.properties"));
					//主播头像
					map.put("img", anchorMap.get("avatar")==null?"":fileUrl + anchorMap.get("avatar").toString());
					//主播昵称
					map.put("describe", anchorMap.get("nname")==null?"":anchorMap.get("nname").toString());
					//性别
					map.put("sex", anchorMap.get("sex").toString());
					//时间
					map.put("time", "");
					
					//存放最喜欢看的主播
					resultList.add(map);
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.info("查询用户详情页个人足迹,最喜欢看的主播信息失败,错误信息如下:" + e.getMessage());
			}
			
			try {
				//查询最喜欢外出消费时间段
				Map<Object, Object> customerTimeMap = billDao.queryCustomerTimeByUid(toUid);
				
				if (customerTimeMap != null && customerTimeMap.size() > 0) {
					Map<Object, Object> map = new HashMap<>();
					//查询最喜欢外出的消费时间周
					Map<Object, Object> customerWeekMap = billDao.queryCustomerWeekByUid(toUid);
					//位置类型
					map.put("type", 5);
					//最喜欢外出消费时间段文字
					map.put("text", propertiesUtil.getValue("customerTimeText", "conf_common.properties"));
					//性别
					map.put("sex", "");
					//时间
					map.put("time", "");
					//消费时间段
					map.put("describe", customerWeekMap.get("weekDay")==null?"周日":customerWeekMap.get("weekDay").toString() + " " + customerTimeMap.get("zdate").toString());
					//喜欢外出消费图片
					map.put("img", url+"/img/customerImg.png");
					
					//存放最喜欢外出消费
					resultList.add(map);
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.info("查询用户详情页个人足迹,最喜欢外出消费信息失败,错误信息如下:" + e.getMessage());
			}
			
			try {
				// 查询是否是寻蜜客
				Map<Object, Object> xmerMap = xmerDao.queryXmerInfo(toUid);
				if (xmerMap != null && xmerMap.size() > 0) {
					Map<Object, Object> map = new HashMap<>();
					//位置类型
					map.put("type", 6);
					//已经是寻蜜客文字
					map.put("text", propertiesUtil.getValue("isXmerText", "conf_common.properties"));
					//寻蜜客注册时间
					time = "";
					if (xmerMap.get("sdate") != null) {
						Date sdate = DateUtil.parse(xmerMap.get("sdate").toString());
						String[] sTime = DateUtil.format(sdate,"yyyy-MM-dd").split("-");
						for (int i = 0; i < sTime.length; i++) {
							if (i == 0) {
								time = sTime[i] + "\n";
							}else {
								time += sTime[i] + ".";
							}
						}
						map.put("time", time.substring(0, time.length()-1));
						
					}else {
						map.put("time", "");
						
					}
					//已经加入寻蜜客图片
					map.put("img", url+"/img/isXmerImg.png");
					//时间
					map.put("sex", "");
					//描述
					map.put("describe", "");
					//存放最喜欢外出消费
					resultList.add(map);
					
				}else {
					Map<Object, Object> map = new HashMap<>();
					//位置类型
					map.put("type", 7);
					//不是寻蜜客文字
					map.put("text", propertiesUtil.getValue("isNotXmerText", "conf_common.properties"));
					//没有加入寻蜜客图片
					map.put("img", url+"/img/isNotXmerImg.png");
					//时间
					map.put("sex", "");
					//描述
					map.put("describe", "");
					//时间
					map.put("time", "");
					
					//存放最喜欢外出消费
					resultList.add(map);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				log.info("查询用户详情页个人足迹,是否是寻蜜客信息失败,错误信息如下:" + e.getMessage());
			}
			
			//返回结果集
			resultMap.put("growthTrackList", resultList);
			//响应
			MapResponse response = new MapResponse(ResponseCode.SUCCESS, "获取成长足迹信息成功");
			response.setResponse(resultMap);
			return response;
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("获取成长足迹信息失败,被查看用户uid=" + toUid + ",错误信息如下:" + e.getMessage());
			return new BaseResponse(ResponseCode.FAILURE, "获取成长足迹信息失败");
		}
		
	}
	
	/**
	 * 
	* @Title: queryAttentionLiveActivity
	* @Description: 查询参加过的直播活动
	* @return Object    返回类型
	* @throws
	 */
	public Object queryAttentionLiveActivityList(Integer toUid,Integer page,Double longitude,Double latitude, String maxTime, int apiVersion) {
		try {
			//结果集
			Map<Object, Object> resultMap = new HashMap<>();
			List<Map<Object, Object>> resultList = new ArrayList<>();
			Integer limit = Integer.parseInt(propertiesUtil.getValue("anchorLiveRecordListNum", "conf_common.properties"));
			//组装参数
			Map<Object, Object> paramMap = new HashMap<>();
			paramMap.put("uid", toUid);
			paramMap.put("page", page);
			paramMap.put("limit", limit);
			
			//传入经纬度,计算距离
			if (latitude != null && longitude != null && latitude != 0 && longitude != 0) {
				//计算距离
				paramMap.put("latitude", latitude);
				paramMap.put("longitude", longitude);
			}
			
			//查询直播用户信息
			Map<Object, Object> liverMap = liveUserDao.queryLiverInfoByUid(toUid);
			if (liverMap == null) {
				return new BaseResponse(ResponseCode.FAILURE, "查无此用户信息");
			}

			//查询参加过的直播活动列表(购买过粉丝卷,直播中置顶，直播预售优先排序，回放记录按时间排序)
			paramMap.put("maxTime", maxTime);
			List<Map<Object, Object>> attendLiveRecordList = queryAttendLiveRecordList(paramMap, apiVersion);
			if (attendLiveRecordList != null && attendLiveRecordList.size() > 0) {
				//调整返回数据
				resultList = liveHomeService.updateLiveRecordList(attendLiveRecordList, liverMap);
			}

			// 点评记录
			List<Map<Object, Object>> reCommentList = queryCommentRecord(toUid, paramMap, liverMap, latitude, longitude);
			resultList = toResultRecordList(resultList, reCommentList);
			List<Map<Object, Object>> toList = new ArrayList<Map<Object, Object>>();
			for (int i = 0; i < resultList.size(); i++) {
				if (i < limit) {
					toList.add(resultList.get(i));
				}
			}
			resultMap.put("liveRecordList", toList);

			resultMap.put("liveRecordList", resultList);
			//响应
			MapResponse response = new MapResponse(ResponseCode.SUCCESS, "获取参加过的直播活动列表成功");
			response.setResponse(resultMap);
			return response;
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("获取参加过的直播活动列表失败,被查看用户uid=" + toUid + ",错误信息如下:" + e.getMessage());
			return new BaseResponse(ResponseCode.FAILURE, "获取参加过的直播活动列表失败");
		}
	}
	
	/**
	 * 
	 * @Title: queryFocusList
	 * @Description: 获取关注列表(type=1  为主播详情主页的关注列表   type=2  为主播个人信息中心的关注列表)
	 * @return Object    返回类型
	 * @throws
	 */
	public Object queryFocusList(Integer toUid,Integer page,Integer type,String uid) {
		try {
			//结果集
			Map<Object, Object> resultMap = new HashMap<>();
			List<Map<Object, Object>> resultList = new ArrayList<>();
			resultMap.put("focusInfoList", resultList);
			
			Map<Object, Object> liverMap = new HashMap<>();
			//判断是否登录过
			if (StringUtils.isNotEmpty(uid) && !"null".equalsIgnoreCase(uid)) {
				//查询用户信息
				liverMap = liveUserDao.queryLiverInfoByUid(Integer.parseInt(uid));
				if (liverMap == null || liverMap.size() <= 0) {
					return new BaseResponse(ResponseCode.FAILURE, "查无用户信息");
				}
			}
			
			//查询被查看的用户信息
			Map<Object, Object> toLiverMap = liveUserDao.queryLiverInfoByUid(toUid);
			
			if (toLiverMap == null || toLiverMap.size() <= 0) {
				return new BaseResponse(ResponseCode.FAILURE, "查无用户信息");
			}
			
			List<Map<Object, Object>> focusList = new ArrayList<>();
			if (type == 1) {
				//主播详情主页的关注列表
				Map<Object, Object> paramMap = new HashMap<>();
				paramMap.put("toLiverId", toLiverMap.get("id"));
				//判断是否登录过
				if (StringUtils.isNotEmpty(uid) && !"null".equalsIgnoreCase(uid)) {
					//登录过
					paramMap.put("liverId", liverMap.get("id"));
					
				}else {
					//未登录
					paramMap.put("liverId", 0);
					
				}
				paramMap.put("page", page);
				paramMap.put("limit", Constant.PAGE_LIMIT);
				
				//查询主播与查看用户的关注列表
				focusList = personalcenterDao.queryFocusList(paramMap);
				
				//获取主播关注直播用户信息及关系标签
				if (focusList != null && focusList.size() > 0   ) {
					//判断是否登录过
					if (StringUtils.isNotEmpty(uid) && !"null".equalsIgnoreCase(uid)) {
						//登录过
						resultList = this.getUrsInfoAndTagName(Integer.parseInt(uid), Integer.parseInt(liverMap.get("id").toString()), focusList,2);
						
					}else {
						//未登录
						resultList = this.getUrsInfoAndTagName(0,0, focusList,2);
						
					}
					
				}
				
			}else if (type == 2) {
				//主播主页的关注列表
				Map<Object, Object> paramMap = new HashMap<>();
				paramMap.put("liver_str_id", liverMap.get("id"));
				paramMap.put("page", page);
				paramMap.put("limit", Constant.PAGE_LIMIT);
				
				//主播主页的关注列表
				focusList = personaldetailDao.queryAttentionFocusList(paramMap);
				if (focusList != null && focusList.size() > 0   ) {
					//获取主播关注直播用户信息及关系标签
					resultList = this.getUrsInfoAndTagName(Integer.parseInt(uid), Integer.parseInt(liverMap.get("id").toString()), focusList,4);
				}
				
			}
			
			if (resultList != null && resultList.size() > 0) {
				resultMap.put("focusInfoList", resultList);
			}
			
			//响应
			MapResponse response = new MapResponse(ResponseCode.SUCCESS, "获取关注列表成功");
			response.setResponse(resultMap);
			return response;
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("获取关注列表失败,被查看用户uid=" + toUid + ",查看用户uid=" + uid + ",错误信息如下:" + e.getMessage());
			return new BaseResponse(ResponseCode.FAILURE, "获取关注列表失败");
		}
		
	}
	
	/**
	 * 
	* @Title: queryFansList
	* @Description: 获取粉丝列表(type=1  为主播详情主页的粉丝列表   type=2  为主播个人信息中心的粉丝列表)
	* @return Object    返回类型
	* @throws
	 */
	public Object queryFansList(Integer toUid,Integer page,Integer type,String uid) {
		try {
			//结果集
			Map<Object, Object> resultMap = new HashMap<>();
			List<Map<Object, Object>> resultList = new ArrayList<>();
			resultMap.put("fansInfoList", resultList);
			
			Map<Object, Object> liverMap = new HashMap<>();
			//判断是否登录过
			if (StringUtils.isNotEmpty(uid) && !"null".equalsIgnoreCase(uid)) {
				//查询用户信息
				liverMap = liveUserDao.queryLiverInfoByUid(Integer.parseInt(uid));
				if (liverMap == null || liverMap.size() <= 0) {
					return new BaseResponse(ResponseCode.FAILURE, "查无用户信息");
				}
			}
			
			//被查看的用户信息
			Map<Object, Object> toLiverMap = liveUserDao.queryLiverInfoByUid(toUid);
			
			if (toLiverMap == null || toLiverMap.size() <= 0 || Integer.parseInt(toLiverMap.get("utype").toString()) != 1) {
				return new BaseResponse(ResponseCode.FAILURE, "查无用户信息");
			}
			
			List<Map<Object, Object>> fansList = new ArrayList<>();
			if (type == 1) {
				//主播详情主页的粉丝列表
				Map<Object, Object> paramMap = new HashMap<>();
				paramMap.put("toLiverId", toLiverMap.get("id"));
				//判断是否登录过
				if (StringUtils.isNotEmpty(uid) && !"null".equalsIgnoreCase(uid)) {
					//登录过
					paramMap.put("liverId", liverMap.get("id"));
					
				}else {
					//未登录
					paramMap.put("liverId", 0);
					
				}
				paramMap.put("page", page);
				paramMap.put("limit", Constant.PAGE_LIMIT);
				
				//查询主播与查看用户关注的粉丝列表
				fansList = personalcenterDao.queryFansList(paramMap);
				
				//获取粉丝直播用户信息及关系标签
				if (fansList != null && fansList.size() > 0   ) {
					//判断是否登录过
					if (StringUtils.isNotEmpty(uid) && !"null".equalsIgnoreCase(uid)) {
						//登录过
						resultList = this.getUrsInfoAndTagName(Integer.parseInt(uid), Integer.parseInt(liverMap.get("id").toString()), fansList,1);
						
					}else {
						//未登录
						resultList = this.getUrsInfoAndTagName(0,0, fansList,1);
						
					}
				}
				
			}else if (type == 2) {
				//主播主页的粉丝列表
				Map<Object, Object> paramMap = new HashMap<>();
				paramMap.put("liver_end_id", toLiverMap.get("id"));
				paramMap.put("page", page);
				paramMap.put("limit", Constant.PAGE_LIMIT);
				paramMap.put("uid", toUid);
				
				//主播主页的粉丝列表
				resultList = this.getEachFocusAndFansList(paramMap);
				
				/*
				fansList = personaldetailDao.queryAttentionFansList(paramMap);
				if (fansList != null && fansList.size() > 0   ) {
					//获取粉丝直播用户信息及关系标签
					resultList = this.getUrsInfoAndTagName(uid, Integer.parseInt(liverMap.get("id").toString()), fansList,3);
				}
				*/
			}
			
			if (resultList != null && resultList.size() > 0) {
				resultMap.put("fansInfoList", resultList);
			}
			
			//响应
			MapResponse response = new MapResponse(ResponseCode.SUCCESS, "获取粉丝列表成功");
			response.setResponse(resultMap);
			return response;
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("获取粉丝列表失败,被查看用户uid=" + toUid + ",查看用户uid=" + uid + ",错误信息如下:" + e.getMessage());
			return new BaseResponse(ResponseCode.FAILURE, "获取粉丝列表失败");
		}
		
	}
	
	/**
	 * @throws IOException 
	 * 
	* @Title: getEachFocusAndFansList
	* @Description: //主播主页的粉丝列表
	* @return List<Map<Object,Object>>    返回类型
	* @throws
	 */
	public List<Map<Object, Object>> getEachFocusAndFansList(Map<Object, Object> paramMap) throws IOException {
		//结果集
		List<Map<Object, Object>> resultList = new ArrayList<>();
		//查询主播主页的粉丝列表中的互相关注和粉丝列表
		List<Map<Object, Object>> fansList = personaldetailDao.queryEachFocusAndFansList(paramMap);
		
		if (fansList == null || fansList.size() <= 0) {
			return resultList;
		}
		
		//获取直播用户id
		List<String> ids = new ArrayList<String>();
		for(Map<Object,Object> fansMap:fansList){
			ids.add(fansMap.get("str_uid").toString());
		}
		
		//根据直播用户id查询用户信息
		List<Map<Object,Object>> personList = personalcenterDao.queryLiverPersonByListUid(ids);
		ids.clear();
		
		for(Map<Object,Object> fansMap:personList){
			ids.add(fansMap.get("id").toString());
		}
		
		//获取用户的uid
		List<Integer> uids = new ArrayList<>();
		if(personList!=null && personList.size() > 0){
			//用户信息
			for(Map<Object,Object> personMap:personList){
				if (null==personMap.get("uid")) {
					break;
				}
				uids.add(Integer.parseInt(personMap.get("uid").toString()));
				Map<Object,Object> rgMap= new HashMap<Object,Object>();
				rgMap.put("uid", personMap.get("uid"));//uid
				String nname = StrUtils.standardName(personMap.get("nname"), personMap.get("phone"));
				rgMap.put("nname", nname);//昵称
				rgMap.put("avatar", personMap.get("avatar")==null?"":fileUrl + personMap.get("avatar").toString());//头像
				rgMap.put("rankNo", personMap.get("rank_no")==null?1:	personMap.get("rank_no").toString());//等级
				rgMap.put("sex", StringUtils.isEmpty(personMap.get("sex").toString())?0:personMap.get("sex"));//性别 0位置 :1男    2:女
				rgMap.put("utype", personMap.get("utype"));//直播用户类型 1主播  2普通用户
				rgMap.put("liverId", personMap.get("id"));//直播用户id
				rgMap.put("tag", "");
				
				for (Map<Object, Object> fansMap : fansList) {
					if (Integer.parseInt(fansMap.get("str_uid").toString()) == Integer.parseInt(personMap.get("uid").toString())) {
						rgMap.put("eachFocus", fansMap.get("eachFocus"));
						break;
						
					}
				}
				resultList.add(rgMap);
			}
		}
		
		//获取粉丝关系标签{"tag1":"送过礼物给我","tag2":"购买过我的预售","tag3":"看过我的直播"}
		String fansTagList = propertiesUtil.getValue("fansTagList", "conf_common.properties");
		JSONObject jsonObj = JSONObject.parseObject(fansTagList);
		//标签1  送过礼物给我"
		String fanTag1 = jsonObj.getString("tag1");
		//标签2 购买过我的预售
		String fanTag2 = jsonObj.getString("tag2");
		//标签3 看过我的直播
		String fanTag3 = jsonObj.getString("tag3");
		
		//组装参数
		Map<Object, Object> map = new HashMap<>();
		map.put("anchorId", paramMap.get("liver_end_id"));
		map.put("liverIds", ids);
		
		//批量查询这些用户是否送过礼物给主播
		List<Map<Object, Object>> giftsList= liveGiftsInfoDao.queryCheckGivedGifts(map);
		
		//组装参数
		map.clear();
		map.put("anchorId", paramMap.get("liver_end_id"));
		map.put("uids", uids);
		
		//批量查询这些用户是否购买过粉丝卷
		List<Map<Object, Object>> couponFansList = couponFansOrderDao.queryCheckCouponFansOrder(map);
		
		//批量查询这些用户是否观看过指定主播的直播
		List<Map<Object, Object>> liveViewList = anchorLiveRecordDao.queryCheckLiveViewRecord(map);
		
		//组装数据
		for (Map<Object, Object> liverMap : resultList) {
			
			//记录是否有送过礼物给主播的标签
			if (giftsList != null && giftsList.size() > 0) {
				for (Map<Object, Object> giftMap : giftsList) {
					if (Integer.parseInt(liverMap.get("liverId").toString()) == Integer.parseInt(giftMap.get("liverId").toString())
							&& Integer.parseInt(giftMap.get("sendGiftPrice").toString()) >0) {
						liverMap.put("tag", liverMap.get("tag").toString() + fanTag1);
						break;
					}
				}
			}
			
			//记录是否购买过我的预售
			if (couponFansList != null && couponFansList.size() > 0) {
				for (Map<Object, Object> couponFansMap : couponFansList) {
					if (Integer.parseInt(liverMap.get("uid").toString()) == Integer.parseInt(couponFansMap.get("uid").toString())
							&& Integer.parseInt(couponFansMap.get("count").toString()) >0) {
						liverMap.put("tag", StringUtils.isEmpty(liverMap.get("tag").toString())?fanTag2:liverMap.get("tag").toString() + "、" + fanTag2);
						break;
					}
				}
			}
			
			//记录是否观看直播记录
			if (liveViewList != null && liveViewList.size() > 0) {
				for (Map<Object, Object> liveViewMap : liveViewList) {
					if (Integer.parseInt(liverMap.get("uid").toString()) == Integer.parseInt(liveViewMap.get("uid").toString())
							&& Integer.parseInt(liveViewMap.get("count").toString()) >0) {
						liverMap.put("tag", StringUtils.isEmpty(liverMap.get("tag").toString())?fanTag3:liverMap.get("tag").toString() + "、" + fanTag3);
						break;
					}
				}
			}
			
		}
		
		return resultList;
		
	}
	
	/**
	 * @throws Exception 
	 * 
	* @Title: getUrsInfoAndTagName
	* @Description: 获取直播用户信息及关系标签,type == 1 粉丝直播用户信息   type == 2关注直播用户信息
	* @return List<Map<Object,Object>>    返回类型
	* @throws
	 */
	public List<Map<Object, Object>> getUrsInfoAndTagName(Integer uid,Integer liverId,List<Map<Object, Object>> ursList,Integer type) throws Exception {
		//结果集
		List<Map<Object, Object>> resultList = new ArrayList<>();
		
		//获取直播用户id
		List<String> ids = new ArrayList<String>();
		for(Map<Object,Object> ursMap:ursList){
			if (type == 1) {
				//获取粉丝直播用户id(主播详情主页)
				ids.add(ursMap.get("liverStrId").toString());
			}else if (type == 2) {
				//获取关注直播用户id(主播详情主页)
				ids.add(ursMap.get("liverEndId").toString());
			}else if (type == 3) {
				//获取粉丝直播用户id(主播主页,暂无使用)
				ids.add(ursMap.get("liver_str_id").toString());
			}else if (type == 4) {
				//获取关注直播用户id(主播主页)
				ids.add(ursMap.get("liver_end_id").toString());
			} else {
				log.warn("跳过直播用户id" + ursMap.toString());
			}
		}
		log.info("关注用户id列表：" + ids.toString());
		//根据直播用户id查询用户信息
		List<Map<Object,Object>> personList = personalcenterDao.queryLiverPersonByListId(ids);
		
		//获取用户的uid
		List<Integer> uids = new ArrayList<>();
		if(personList!=null && personList.size() > 0){
			//用户信息
			for(Map<Object,Object> personMap:personList){
				if (null==personMap.get("uid")) {
					log.warn("查询用户信息，获取不到uid" + personMap.toString());
					continue;
				}
				
				uids.add(Integer.parseInt(null!=personMap.get("uid")?personMap.get("uid").toString():"0"));
				Map<Object,Object> rgMap= new HashMap<Object,Object>();
				rgMap.put("uid", personMap.get("uid"));//uid
				String nname = StrUtils.standardName(personMap.get("nname"), personMap.get("phone"));
				rgMap.put("nname", nname);//昵称
				rgMap.put("avatar", personMap.get("avatar")==null?"":fileUrl + personMap.get("avatar").toString());//头像
				rgMap.put("sex", StringUtils.isEmpty(personMap.get("sex").toString())?0:personMap.get("sex"));//性别 0位置 :1男    2:女
				rgMap.put("utype", personMap.get("utype"));//直播用户类型 1主播  2普通用户
				rgMap.put("tag", "");
				
				for (Map<Object, Object> ursMap : ursList) {
					if (type == 1 && Integer.parseInt(ursMap.get("liverStrId").toString()) == Integer.parseInt(personMap.get("id").toString())) {
						rgMap.put("liverId", ursMap.get("liverStrId"));
						rgMap.put("sameFocus", ursMap.get("sameFocus"));
						break;
						
					}else if (type == 2 && Integer.parseInt(ursMap.get("liverEndId").toString()) == Integer.parseInt(personMap.get("id").toString())) {
						rgMap.put("liverId", ursMap.get("liverEndId"));
						rgMap.put("sameFocus", ursMap.get("sameFocus"));
						break;
						
					}else if (type == 3 && Integer.parseInt(ursMap.get("liver_str_id").toString()) == Integer.parseInt(personMap.get("id").toString())) {
						rgMap.put("liverId", ursMap.get("liver_str_id"));
						break;
						
					}else if (type == 4 && Integer.parseInt(ursMap.get("liver_end_id").toString()) == Integer.parseInt(personMap.get("id").toString())) {
						rgMap.put("liverId", ursMap.get("liver_end_id"));
						break;
						
					}
				}
				resultList.add(rgMap);
			}
			
			//判断是否登录,若未登录，则不查询
			if (uid != 0 && liverId != 0) {
				//获取用户标签
				resultList = this.getTagName(uid, liverId, uids, resultList);
				
			}
			
			if (type == 4) {
				//组装参数
				Map<Object, Object> paramMap = new HashMap<>();
				//查询店铺的数量
				paramMap.put("limit", Constant.CUSTOMER_SELLER_NUM);
				
				for (Map<Object, Object> map : resultList) {
					List<Map<Object, Object>> sellerList = new ArrayList<>();
					paramMap.put("uid", Integer.parseInt(map.get("uid").toString()));
					if (Integer.parseInt(map.get("utype").toString()) == 1) {
						paramMap.put("liverId", Integer.parseInt(map.get("liverId").toString()));
						//查询主播直播过跟消费过的店铺信息(按照消费时间排序)
						sellerList = personalcenterDao.queryLiveAndCustomerSeller(paramMap);
						
					}else {
						//查询用户的消费店铺信息
						sellerList = personalcenterDao.queryCustomerSellerList(paramMap);
					}
					
					if (sellerList != null && sellerList.size() > 0) {
						for (Map<Object, Object> sellerMap : sellerList) {
							sellerMap.put("sellerCover", sellerMap.get("sellerCover")==null?"":fileUrl + sellerMap.get("sellerCover"));
						}
						
					}else {
						sellerList = new ArrayList<>();
					}
					
					//添加至结果集
					map.put("sellerList", sellerList);
					
				}
				
			}
		}
		
		return resultList;
		
	}
	
	/**
	 * 
	* @Title: name
	* @Description: 获取用户标签
	* @return List<Map<Object,Object>>    返回类型
	* @throws
	 */
	public List<Map<Object, Object>> getTagName(Integer uid,Integer liverId,List<Integer> uids,List<Map<Object, Object>> resultList) {
		try {
			//非常相似标签名称
			String withOthersMatchTag = propertiesUtil.getValue("withOthersMatchTag", "conf_common.properties");
			//一般相似标签名称
			String withOthersCommonTag = propertiesUtil.getValue("withOthersCommonTag", "conf_common.properties");
			
			//获取用户关系标签
			Map<Object, Object> paramMap = new HashMap<>();
			//批量查询去过相同消费的店铺数量
			paramMap.put("uid", uid);
			paramMap.put("toUids", uids);
			List<Map<Object, Object>> sameConsumerSellerListCountList = billDao.querySameConsumerSellerListCount(paramMap);
			
			List<Integer> removeUids = new ArrayList<>();
			if (sameConsumerSellerListCountList != null && sameConsumerSellerListCountList.size() > 0) {
				for (Map<Object, Object> map : resultList) {
					for (Map<Object, Object> sameConsumerSellerMap : sameConsumerSellerListCountList) {
						if (Integer.parseInt(map.get("uid").toString()) == Integer.parseInt(sameConsumerSellerMap.get("uid").toString())) {
							if (Integer.parseInt(sameConsumerSellerMap.get("count").toString()) > 0) {
								map.put("tag", withOthersMatchTag);
								removeUids.add(Integer.parseInt(sameConsumerSellerMap.get("uid").toString()));
								break;
							}
						}
					}
				}
			}
			
			//去除已有的标签的uid
			if (removeUids.size() > 0) {
				uids.removeAll(removeUids);
				//重置删除uid
				removeUids = new ArrayList<>();
			}
			
			if (uids.size() > 0) {
				//批量查询有共同收藏的店铺数量
				paramMap.clear();
				paramMap.put("uid", uid);
				paramMap.put("toUids", uids);
				List<Map<Object, Object>> collectSellerCountList = sellerDao.queryCollectSellerCount(paramMap);
				
				if (collectSellerCountList != null && collectSellerCountList.size() > 0) {
					for (Map<Object, Object> map : resultList) {
						for (Map<Object, Object> collectSellerCountMap : collectSellerCountList) {
							if (Integer.parseInt(map.get("uid").toString()) == Integer.parseInt(collectSellerCountMap.get("uid").toString())) {
								if (Integer.parseInt(collectSellerCountMap.get("count").toString()) > 0) {
									map.put("tag", withOthersCommonTag);
									removeUids.add(Integer.parseInt(collectSellerCountMap.get("uid").toString()));
									break;
								}
							}
						}
					}
				}
			}
			
			//去除已有的标签的uid
			if (removeUids.size() > 0) {
				uids.removeAll(removeUids);
				//重置删除uid
				removeUids = new ArrayList<>();
			}
			
			if (uids.size() > 0) {
				//批量查询有共同预览的店铺数量
				paramMap.clear();
				paramMap.put("uid", uid);
				paramMap.put("toUids", uids);
				List<Map<Object, Object>> veiwSellerCountList = sellerDao.queryViewSellerCount(paramMap);
				
				if (veiwSellerCountList != null && veiwSellerCountList.size() > 0) {
					for (Map<Object, Object> map : resultList) {
						for (Map<Object, Object> veiwSellerCountMap : veiwSellerCountList) {
							if (Integer.parseInt(map.get("uid").toString()) == Integer.parseInt(veiwSellerCountMap.get("uid").toString())) {
								if (Integer.parseInt(veiwSellerCountMap.get("count").toString()) > 0) {
									map.put("tag", withOthersCommonTag);
									removeUids.add(Integer.parseInt(veiwSellerCountMap.get("uid").toString()));
									break;
								}
							}
						}
					}
				}
			}
			
			//去除已有的标签的uid
			if (removeUids.size() > 0) {
				uids.removeAll(removeUids);
				//重置删除uid
				removeUids = new ArrayList<>();
			}
			
			if (uids.size() > 0) {
				List<Integer> toLiverIds = new ArrayList<>();
				for (Integer u : uids) {
					for (Map<Object, Object> map : resultList) {
						if (u == Integer.parseInt(map.get("uid").toString())) {
							toLiverIds.add(Integer.parseInt(map.get("liverId").toString()));
						}
					}
				}
				
				//批量查询有共同关注的好友数量
				paramMap.clear();
				paramMap.put("liverId", liverId);
				paramMap.put("toLiverIds", toLiverIds);
				List<Map<Object, Object>> sameFocusListCountList = liveUserDao.querySameFocusListCount(paramMap);
				
				if (sameFocusListCountList != null && sameFocusListCountList.size() > 0) {
					for (Map<Object, Object> map : resultList) {
						for (Map<Object, Object> sameFocusListCountMap : sameFocusListCountList) {
							if (Integer.parseInt(map.get("liverId").toString()) == Integer.parseInt(sameFocusListCountMap.get("liverStrId").toString())) {
								if (Integer.parseInt(sameFocusListCountMap.get("count").toString()) > 0) {
									map.put("tag", withOthersCommonTag);
									break;
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultList;
	}
	
	/**
	 * 
	* @Title: queryCustomerSellers
	* @Description: 查询消费过的店铺信息
	* @return Object    返回类型
	* @throws
	 */
	public Object queryCustomerSellerList(Integer toUid,Integer page) {
		try {
			//结果集
			Map<Object, Object> resultMap = new HashMap<Object, Object>();
			List<Map<Object, Object>> resultList = new ArrayList<>();
			resultMap.put("customerSellerList", resultList);
			
			//组装参数
			Map<Object, Object> paramMap = new HashMap<Object, Object>();
			paramMap.put("uid", toUid);
			paramMap.put("page", page);
			paramMap.put("limit", Integer.parseInt(propertiesUtil.getValue("anchorLiveRecordListNum", "conf_common.properties")));
			
			//查询消费的店铺
			List<Map<Object, Object>> customerSellersList = billDao.queryCustomerSellers(paramMap);
			//存放店铺id,查询商圈,商家logo图
			List<Integer> sellerIds = new ArrayList<>();
			if (customerSellersList != null && customerSellersList.size() > 0) {
				for (Map<Object, Object> map : customerSellersList) {
					sellerIds.add(Integer.parseInt(map.get("sellerId").toString()));
				}
				
				//批量查询店铺所在的商圈
				List<Map<Object, Object>> sellerBusinessList = sellerDao.querySellerBusiness(sellerIds);
				
				//批量查询商家的封面图
				List<Map<Object, Object>> sellerCoverList = anchorLiveRecordDao.querySellerCover(sellerIds);
				
				//批量查询店铺信息
				List<Map<Object, Object>> sellerInfoList= sellerDao.querySellerInfo(sellerIds);
				
				for (Map<Object, Object> customerSellersMap : customerSellersList) {
					//消费日期,如果支付时间为null,则取下单时间
					Date customerDate = null;
					if (customerSellersMap.get("customerDate") != null) {
						//支付时间
						customerDate = DateUtil.parse(customerSellersMap.get("customerDate").toString());
					}else {
						//下单时间
						customerDate = DateUtil.parse(customerSellersMap.get("orderDate").toString());
					}
					int customerMonth = DateUtil.month(customerDate);
					int customerDay = DateUtil.day(customerDate);
					customerSellersMap.put("customerMonth", customerMonth + 1);
					customerSellersMap.put("customerDay", customerDay);
					
					//返回字段添加商家名称，二级分类
					customerSellersMap.put("sellerName", "");
					customerSellersMap.put("tradeName", "");
					if (sellerInfoList != null && sellerInfoList.size() > 0) {
						for (Map<Object, Object> sellerInfoMap : sellerInfoList) {
							if (Integer.parseInt(sellerInfoMap.get("sellerId").toString()) == Integer.parseInt(customerSellersMap.get("sellerId").toString())) {
								//商家名称
								customerSellersMap.put("sellerName", sellerInfoMap.get("sellerName")==null?"":sellerInfoMap.get("sellerName").toString());
								//商家二级分类
								customerSellersMap.put("tradeName", sellerInfoMap.get("tradeName")==null?"":sellerInfoMap.get("tradeName").toString());
							}
						}
					}
					
					//返回字段添加商家商圈名称
					customerSellersMap.put("sellerBusiness", "");
					if (sellerBusinessList != null && sellerBusinessList.size() > 0) {
						for (Map<Object, Object> sellerBusinessMap : sellerBusinessList) {
							if (Integer.parseInt(sellerBusinessMap.get("sellerId").toString()) == Integer.parseInt(customerSellersMap.get("sellerId").toString())) {
								//商家商圈名称
								customerSellersMap.put("sellerBusiness", sellerBusinessMap.get("title")==null?"":sellerBusinessMap.get("title").toString());
							}
						}
					}
					
					//返回字段添加商家封面图
					customerSellersMap.put("sellerCover", "");
					if (sellerCoverList != null && sellerCoverList.size() > 0) {
						for (Map<Object, Object> sellerLogoMap : sellerCoverList) {
							if (Integer.parseInt(sellerLogoMap.get("sellerId").toString()) == Integer.parseInt(customerSellersMap.get("sellerId").toString())) {
								customerSellersMap.put("sellerCover", sellerLogoMap.get("sellerCover")==null?"":fileUrl + sellerLogoMap.get("sellerCover").toString());
							}
						}
					}
				}
				
				resultMap.put("customerSellerList", customerSellersList);
			}
			
			//响应
			MapResponse response = new MapResponse(ResponseCode.SUCCESS, "获取去过的店铺信息成功");
			response.setResponse(resultMap);
			return response;
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("获取去过的店铺信息失败,用户uid=" + toUid + ",错误信息如下:" + e.getMessage());
			return new BaseResponse(ResponseCode.FAILURE, "获取去过的店铺信息失败");
		}
		
	}
	
	/**
	 * 
	* @Title: queryliveDynamic
	* @Description: 查询主播的直播动态
	* @return Object    返回类型
	* @throws
	 */
	public Object queryliveDynamic(Integer toUid,Integer page,Double longitude,Double latitude, String maxTime, int apiversion) {
		try {
			//结果集
			Map<Object, Object> resultMap = new HashMap<>();
			List<Map<Object, Object>> resultList = new ArrayList<>();
			
			//查询主播信息
			Map<Object, Object> liverMap = liveUserDao.queryLiverInfoByUid(toUid);
			if (liverMap == null || liverMap.size() <= 0 || Integer.parseInt(liverMap.get("utype").toString()) != 1) {
				return new BaseResponse(ResponseCode.FAILURE, "查无此主播信息");
			}
			
			if (Integer.parseInt(liverMap.get("utype").toString()) != 1) {
				return new BaseResponse(ResponseCode.FAILURE, "未知错误,请联系客服");
			}
			Integer limit = Integer.parseInt(propertiesUtil.getValue("anchorLiveRecordListNum", "conf_common.properties"));
			//查询主播的直播动态
			Map<Object, Object> paramMap = new HashMap<>();
			paramMap.put("anchorId", Integer.parseInt(liverMap.get("id").toString()));
			paramMap.put("page", page);
			paramMap.put("limit", limit);
			
			//传入经纬度,计算距离
			if (latitude != null && longitude != null && latitude != 0 && longitude != 0) {
				//计算距离
				paramMap.put("latitude", latitude);
				paramMap.put("longitude", longitude);
			}
			paramMap.put("maxTime", maxTime);
			paramMap.put("uid", toUid);
			//查询主播的直播动态
			List<Map<Object, Object>> liveRecordDynamic = queryLiveRecordDynamicByType(paramMap, apiversion);
			if (liveRecordDynamic != null && liveRecordDynamic.size() > 0) {
				resultList = liveHomeService.updateLiveRecordList(liveRecordDynamic, liverMap);
			}
			// 点评记录
			List<Map<Object, Object>> reCommentList = queryCommentRecord(toUid, paramMap, liverMap, latitude, longitude);
			resultList = toResultRecordList(resultList, reCommentList);
			List<Map<Object, Object>> toList = new ArrayList<Map<Object, Object>>();
			for (int i = 0; i < resultList.size(); i++) {
				if (i < limit) {
					toList.add(resultList.get(i));
				}
			}
			resultMap.put("liveRecordList", toList);
			//响应
			MapResponse response = new MapResponse(ResponseCode.SUCCESS, "获取主播直播动态成功");
			response.setResponse(resultMap);
			return response;
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("获取主播直播动态失败,用户uid=" + toUid + ",错误信息如下:" + e.getMessage());
			return new BaseResponse(ResponseCode.FAILURE, "获取主播直播动态失败");
		}
	}

	/**
	 * 查询点评记录
	 * @param paramMap
	 * @param liverMap
	 * @return
	 */
	private List<Map<Object, Object>> queryCommentRecord(Integer uid, Map<Object, Object> paramMap, Map<Object, Object> liverMap, Double lat, Double lon) {
		List<Map<Object, Object>> resultList = new ArrayList<Map<Object, Object>>();
		// 查询美食探店点评
		List<Map<Object, Object>> reCommentList = personalLiveDao.getCommentRecord(paramMap);
		if (reCommentList == null || reCommentList.size() == 0) {
			return resultList;
		}
		// 商家id列表
		Set<Integer> sellerSet = new HashSet<Integer>();
		for (Map<Object, Object> comment : reCommentList) {
			String sellerid = comment.get("sellerid") == null ? null : comment.get("sellerid").toString();
			if (sellerid == null) {
				continue;
			}
			sellerSet.add(Integer.parseInt(sellerid));
		}
		List<Integer> sellerIdList = new ArrayList<Integer>();
		for (Integer sellerid : sellerSet) {
			sellerIdList.add(sellerid);
		}
		// 店铺信息map
		Map<String, Map<Object,Object>> sellerMap = new HashMap<String, Map<Object,Object>>();
		if (sellerIdList.size() > 0) {
			List<Map<Object,Object>> sellerList = sellerInfoDao.findAllZoneIdBySellerId(sellerIdList);
			if (sellerIdList != null) {
				for (Map<Object, Object> sellerInfo : sellerList) {
					String sellerid = sellerInfo.get("sellerid") == null ? null : sellerInfo.get("sellerid").toString();
					if (sellerid == null) {
						continue;
					}
					sellerMap.put(sellerid, sellerInfo);
				}
			}
		}
		Integer signType = anchorSignTypeService.getSignType(liverMap);
		List<Integer> commentIds = new ArrayList<Integer>();  //评论Id列表
		// 点评信息
		for (Map<Object, Object> comment : reCommentList) {
			int commentId = Integer.parseInt(comment.get("id").toString());
			String avatar = liverMap.get("avatar") == null ? "" : liverMap.get("avatar").toString();
			String nname = StrUtils.standardName(liverMap.get("nname"), liverMap.get("phone"));
			String sellerid = comment.get("sellerid") == null ? "" : comment.get("sellerid").toString();
			Map<Object,Object> sellerInfo = sellerMap.get(sellerid);
			if (sellerInfo == null) {
				log.warn("点评信息，店铺不存在, sellerid=" + String.valueOf(sellerid) + " commentId=" + String.valueOf(commentId));
				continue;
			}
			Object zoneName = sellerInfo == null ? null : sellerInfo.get("title");

			Map<Object, Object> result = new HashMap<Object, Object>();
			result.put("recordType", comment.get("record_type") == null ? 0 : Integer.parseInt(comment.get("record_type").toString()));  //通告类型  通告类型1.商家2.活动
			result.put("commentId",commentId );  //评论id
			result.put("avatar",fileUrl + avatar );  //头像
			result.put("nname", nname);  //名称
			result.put("signType", signType);  //签约类型
//			result.put("mediaUrl", comment.get("media_url") == null ? "" : comment.get("media_url").toString());  //点评图片/视频
//			result.put("mediaType", comment.get("media_type") == null ? 0 : Integer.parseInt(comment.get("media_type").toString()));  //媒体标识 0 未知 1 图片 2 视频
			result.put("sellerName", comment.get("sellername") == null ? 0 : comment.get("sellername").toString());  //店铺名称
			result.put("zoneName", zoneName == null ? "" : zoneName.toString());  //店铺商圈
			result.put("create_time", comment.get("create_time") == null ? 0 : comment.get("create_time").toString());  //点评发布时间
			result.put("picNumber", 0);  //图片总数
			result.put("sex", liverMap.get("sex") == null ? 0 : liverMap.get("sex").toString());  //性别
			result.put("tradename", sellerMap.get("tradename") == null ? "" : sellerMap.get("tradename").toString());   //2级美食分类信息
			String distance = "";
			if (lat != null && lon != null &&  sellerInfo.get("latitude") != null && sellerInfo.get("longitude") != null) {
				try {
					double d = SellerDetailApi.distance(lon, lat,
							Double.parseDouble(sellerInfo.get("longitude").toString()),
							Double.parseDouble(sellerInfo.get("latitude").toString())
							);
					String d2 = new java.text.DecimalFormat("#.00").format(d);
					distance = d2 + "km";
				} catch (Exception e) {
					log.warn("计算 商铺距离 失败");
				}
			}
			result.put("distance", distance);  //距离
			resultList.add(result);
			commentIds.add(commentId);  //查询图片/视频列表
		}
		// 获取图片或视频
		if (commentIds.size() > 0) {
			paramMap.clear();
			paramMap.put("idList", commentIds);
			List<Map<Object, Object>> picList = personalLiveDao.getCommentPicByIdList(paramMap);
			if (picList != null) {
				Map<Integer, List<Map<Object, Object>>> idPicListMap = new HashMap<Integer, List<Map<Object, Object>>>();
				for (int i = 0; i < picList.size(); i++) {
					Map<Object, Object> tmp = picList.get(i);
					Integer comment_id = Integer.parseInt(tmp.get("comment_id").toString()); //
					List<Map<Object, Object>> mediaInfo = idPicListMap.get(comment_id);
					if (mediaInfo == null) {
						mediaInfo = new ArrayList<Map<Object, Object>>();
						idPicListMap.put(comment_id, mediaInfo);
					}
					mediaInfo.add(tmp);
				}
				// 遍历所有的点评
				for (Map<Object, Object> result : resultList) {
					Integer commentId = Integer.parseInt(result.get("commentId").toString());
					List<Map<Object, Object>> mediaInfoList = idPicListMap.get(commentId);
					List<Map<Object, Object>> mediaResult = new ArrayList<Map<Object, Object>>();  //返回
					result.put("commentId", commentId);
					result.put("picList", mediaResult);
					if (mediaInfoList == null ) {
						continue;
					}
					// 遍历点评下所有视频或图片
					for (int i = 0; i < mediaInfoList.size(); i++) {
						Map<Object, Object> mediaInfo = mediaInfoList.get(i);
						String media_url = mediaInfo.get("media_url") == null ? "" : mediaInfo.get("media_url").toString();
						Integer media_type = mediaInfo.get("media_url") == null ? 0 : Integer.parseInt(mediaInfo.get("media_type").toString());
						String create_time = mediaInfo.get("create_time") == null ? "" : mediaInfo.get("create_time").toString();
//						Integer sort = mediaInfo.get("sort") == null ? null : Integer.parseInt(mediaInfo.get("sort").toString());
						Integer id = Integer.parseInt(mediaInfo.get("id").toString());
						String toMediaUrl = media_url.trim().replace(fileUrl, "");  //避免脏数据
						media_url = toMediaUrl.equals("") ? "" : fileUrl + toMediaUrl;
						Map<Object, Object> m = new HashMap<Object, Object>();
						m.put("id", id);
						m.put("createTime", create_time);
						m.put("mediaType", media_type);
						m.put("mediaUrl", media_url);
						mediaResult.add(m);
					}
				}
			}
		}
		return resultList;
	}

	/**
	 * v3.6, 直播动态列表，需要根据时间，显示t_experience_comment 和 t_live_record中的数据，造成了跨库分页的问题
	 * @param paramMap
	 * @return
	 */
	private List<Map<Object, Object>> queryLiveRecordDynamicByType(Map<Object, Object> paramMap, int apiversion) throws Exception {
		if (apiversion < 360) { // v3.6之前
			return anchorLiveRecordDao.queryLiveRecordDynamic(paramMap);
		}
		return personalLiveDao.queryLiveRecordDynamic(paramMap);
	}

	/**
	 * 参加活动列表
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	private List<Map<Object, Object>> queryAttendLiveRecordList(Map<Object, Object> paramMap, int apiversion) throws Exception {
		if (apiversion < 360) { // v3.6之前
			return anchorLiveRecordDao.queryAttendLiveRecordList(paramMap);
		}
		// v3.6的处理
		return personalLiveDao.queryAttendLiveRecordList(paramMap);
	}

	// 排序直播和点评
	private void sortLiveRecordList(List<Map<Object, Object>> resultList) {
		if (resultList != null && resultList.size() > 0) {
			Collections.sort(resultList, new Comparator<Map<Object, Object>>() {
				@Override
				public int compare(Map<Object, Object> o1, Map<Object, Object> o2) {
					String start_date1 = o1.get("start_date") == null ? null : o1.get("start_date").toString();  //直播的时间
					String create_time1 = o1.get("create_time") == null ? null : o1.get("create_time").toString();  //点评的时间
					String start_date2 = o2.get("start_date") == null ? null : o2.get("start_date").toString();  //直播的时间
					String create_time2 = o2.get("create_time") == null ? null : o2.get("create_time").toString();  //点评的时间

					String d1 = start_date1 == null ? create_time1 : start_date1;
					String d2 = start_date2 == null ? create_time2 : start_date2;
					Date a = null;
					Date b = null;
					try {
						a = DateUtil.parse(d1);
					} catch (Exception e) {
						log.warn("解析时间为null： " + o1.toString());
					}
					try {
						b = DateUtil.parse(d2);
					} catch (Exception e) {
						log.warn("解析时间为null：" + o2.toString() );
					}


					long t1 = a == null ? 0 : a.getTime();
					long t2 = b == null ? 0 : b.getTime();
					// 降序
					if (t1 > t2) {
						return -1;
					} else if (t1 < t2) {
						return 1;
					}
					return 0;
				}
			});
		}
	}

	private List<Map<Object, Object>> toResultRecordList(List<Map<Object, Object>> resultList, List<Map<Object, Object>> reCommentList) {
		reCommentList = reCommentList == null ? new ArrayList<Map<Object, Object>>() : reCommentList;
		resultList = resultList == null ? new ArrayList<Map<Object, Object>>() : resultList;
		resultList.addAll(reCommentList);
		sortLiveRecordList(resultList);  //排序
		List<Map<Object, Object>> result = new ArrayList<Map<Object, Object>>();
		String toMaxTime = null;
		for (int i = 0; i < resultList.size(); i++) {
			Map<Object, Object> tmp = resultList.get(i);
			Object anchor_room_no = tmp.get("anchorRoomNo");
			String start_date = tmp.get("start_date") == null ? null : tmp.get("start_date").toString();  //直播的时间
			String create_time = tmp.get("create_time") == null ? null : tmp.get("create_time").toString();  //点评的时间
			toMaxTime = start_date != null ? start_date : create_time;
			if (toMaxTime == null) {
				log.warn("直播动态，数据异常，start_date = null, create_time = null");
				continue;
			}
			resultList.get(i).put("maxTime", toMaxTime);  //设置时间
			if (anchor_room_no != null){  //直播记录的数据
				resultList.get(i).put("whichType", 1);
			} else {  //点评数据
				resultList.get(i).put("whichType", 2);
			}
			result.add(resultList.get(i));
		}
		return result;
	}


}
