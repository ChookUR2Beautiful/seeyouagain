package com.xmniao.xmn.core.order.service;


import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.xmniao.xmn.core.common.Constant;
import com.xmniao.xmn.core.common.ConstantDictionary;
import com.xmniao.xmn.core.order.entity.ExperienceOfficerOrder;
import org.apache.commons.lang.StringUtils;
import org.apache.http.client.utils.DateUtils;
import org.apache.log4j.Logger;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.order.ExperienceCardRequest;
import com.xmniao.xmn.core.common.request.order.ExperienceRecommendRequest;
import com.xmniao.xmn.core.common.request.order.ExperienceRemindRequest;
import com.xmniao.xmn.core.live.dao.LiveUserDao;
import com.xmniao.xmn.core.live.dao.MessageManageDao;
import com.xmniao.xmn.core.live.service.LiveGiftsInfoService;
import com.xmniao.xmn.core.live.service.PushSingleService;
import com.xmniao.xmn.core.live.service.UserPayBirdCoinService;
import com.xmniao.xmn.core.order.dao.ExperienceActivityDao;
import com.xmniao.xmn.core.order.dao.ExperienceConfigDao;
import com.xmniao.xmn.core.order.entity.ExperienceofficerConfig;
import com.xmniao.xmn.core.thrift.ResponseData;
import com.xmniao.xmn.core.thrift.business.java.ExperienceOfficerOrderService;
import com.xmniao.xmn.core.util.DateUtil;
import com.xmniao.xmn.core.util.EnumUtil;
import com.xmniao.xmn.core.util.PropertiesUtil;
import com.xmniao.xmn.core.util.SaasBidType;
import com.xmniao.xmn.core.util.ThriftBusinessUtil;

@Service
public class ExperienceConfigService {

	private final Logger log = Logger.getLogger(ExperienceConfigService.class);
	
	/**
	 * 注入redis 缓存
	 */
	@Autowired
	private SessionTokenService sessionTokenService;
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	@Autowired
	private ExperienceConfigDao experienceConfigDao;
	
	@Autowired 
	private LiveGiftsInfoService liveGiftsInfoService;
	
	@Autowired
	private LiveUserDao liveUserDao ;
	
	@Autowired
	private ThriftBusinessUtil thriftBusinessUtil;
	
	@Autowired
	private PropertiesUtil propertiesUtil;
	
	@Autowired
	private UserPayBirdCoinService userPayBirdCoinService;
	
	@Autowired
	private ExperienceActivityDao experienceActivityDao;

	@Autowired
	private String fileUrl;
	@Autowired
	private PushSingleService pushSingleService;
	@Autowired
	private MessageManageDao messageManageDao;
	
	/**
	 * 获取体验卡配置信息
	 * @author yhl
	 * @param ExperienceCardRequest
	 * @return Object
	 * */
	public Object buyExprienceCard(ExperienceCardRequest experienceCardRequest ){
		
		String uid = sessionTokenService.getStringForValue(experienceCardRequest.getSessiontoken()) + "";
		if (StringUtils.isEmpty(uid) || "null".equalsIgnoreCase(uid)) {
			return new BaseResponse(ResponseCode.TOKENERR, "token已失效,请重新登录");
		}
		
		MapResponse response = null;
		Map<Object, Object> resultMap = new HashMap<Object, Object>();
		
		//查询体验卡详情
		Map<Object, Object> paramsMap = new HashMap<Object, Object>();
		paramsMap.put("id", experienceCardRequest.getExprId());
		ExperienceofficerConfig config = null;
		List<ExperienceofficerConfig> configList = experienceConfigDao.queryExprienceList(paramsMap);
		if (configList.size()>0) {
			config = configList.get(0);
		}else {
			log.info("未获取到美食体验卡信息");
			return new MapResponse(ResponseCode.FAILURE, "未获取到美食体验卡信息");
		}
		
		if (config!=null) {
			
			try {
				
				//获取钱包余额
				Map<String, Object> liveWalletMap = liveGiftsInfoService.getLiveWalletBlance(uid);
				if (liveWalletMap!=null && liveWalletMap.size()>0) {
					config.setBalanceCoin(new BigDecimal(liveWalletMap.get("zbalanceCoin").toString()));
				}else {
					config.setBalanceCoin(new BigDecimal(0));
				}
				
				//获取直播钱包余额
				Map<String, String> walletMap = liveGiftsInfoService.getXmnWalletBlance(uid);
				if (walletMap!=null) {
					BigDecimal balanceMoney = new BigDecimal(walletMap.get("zbalance")).add(new BigDecimal(walletMap.get("commision")));
					config.setBalanceMoney(balanceMoney);
					
				}else {
					config.setBalanceMoney(new BigDecimal(0));
				}
				
				if (null!=experienceCardRequest.getOperation()) {
					if (experienceCardRequest.getOperation() == 1) {
						config.setOutDate("有效期至:"+config.getOutDate());
						resultMap.put("experiConfig", config);
						response = new MapResponse(ResponseCode.SUCCESS, "操作成功");
						response.setResponse(resultMap);
						return response;
					}else {
						
						//查询是否有过购买记录  是否使用完或已过期
						Map<String, String> cardMap = new HashMap<String, String>();
						cardMap.put("uid", uid);
						ResponseData resData = liveGiftsInfoService.queryExperienceCard(cardMap);
						if (resData.getState() == 3) {
							return this.createAndPayExperienceOrder(experienceCardRequest, config,uid,liveWalletMap,walletMap);
						}else if (resData.getState() == 0){
							//获取体验卡的剩余次数
							Integer nums = Integer.parseInt(resData.getResultMap().get("stock").toString());
							Long currdate = System.currentTimeMillis();
							Long outTime = DateUtil.parse(resData.getResultMap().get("dueDate").toString()).getTime();
							Long diffTime = outTime-currdate;
							if (nums<=0 || diffTime<=0 ) {
								return this.createAndPayExperienceOrder(experienceCardRequest, config,uid,liveWalletMap,walletMap);
							}else {
								return new MapResponse(ResponseCode.FAILURE, "美食体验卡次数用完或者过期才能再次购买哦");
							}
						}else {
							return new MapResponse(ResponseCode.FAILURE, "获取体验卡信息异常");
						}
					}
					
				}
			} catch (Exception e) {
				e.printStackTrace();
				return new MapResponse(ResponseCode.FAILURE, e.getMessage());
			}
			
		}else {
			log.info("未获取到体验卡信息:"+experienceCardRequest.getExprId());
			return new MapResponse(ResponseCode.FAILURE, "未获取到体验卡信息");
		}
		return response;
	}
	
	/**
	 * 创建体验卡订单 并且 发起支付
	 * @author yhl
	 * @param ExperienceCardRequest ; ExperienceofficerConfig; uid ; liveWalletMap ; walletMap 
	 * @return Object
	 * */
	public Object createAndPayExperienceOrder(ExperienceCardRequest experienceCardRequest,
			ExperienceofficerConfig config ,String uid,Map<String, Object> liveWalletMap,Map<String, String> walletMap) throws Exception{
		
		//获取用户基础信息
		Map<Object, Object> userMap = liveUserDao.queryLiverInfoByUid(Integer.parseInt(uid));
		if (userMap==null) {
			return new MapResponse(ResponseCode.FAILURE,"获取用户信息失败");
		}
		
		Map<Object, Object> orderMap = installOrderParam(experienceCardRequest,config,userMap);
		
		if (orderMap!=null && orderMap.size()>0) {
			
				
			//如果是免费的 就直接修改订单状态
			
			if (config.getIsFree() == 1) {
				orderMap.put("amount", "0");
				int addOrderResulft = this.createExperienceOrder(orderMap);
				if (addOrderResulft<=0) {
					return new MapResponse(ResponseCode.FAILURE,"创建订单异常");
				}
				
				Map<String, String> paymap = new HashMap<String, String>();
				paymap.put("orderNo", orderMap.get("order_no").toString());
				paymap.put("uid", uid);
				paymap.put("amount", config.getAmount()!=null?config.getAmount().toString():"0");
				paymap.put("payType", "");
				paymap.put("liveCoin", "0.00");
				paymap.put("walletAmount", "0.00");
				paymap.put("samount", "0.00");
				paymap.put("payState", "1");
				try {
					ResponseData  data = this.updateExperienceOfficerOrder(paymap);
					Map<Object, Object> editResultMap = new HashMap<Object, Object>();
					if(data.state == 0){
						editResultMap.put("orderNo", orderMap.get("order_no").toString());
						editResultMap.put("msg", "操作成功");
						editResultMap.put("state", 200);
						return editResultMap;
					}else{
						log.info("调用业务服务异常:"+orderMap.get("order_no").toString());
						editResultMap.put("msg", "操作异常");
						editResultMap.put("state", 500);
						return new MapResponse(ResponseCode.FAILURE,"生成订单异常,请重试");
					}
				} catch (Exception e) {
					e.printStackTrace();
					return new MapResponse(ResponseCode.FAILURE,"生成订单异常");
				}
			}
			
			//不是免费的发起调用支付接口
			if (config.getIsFree() == 0) {
				
				BigDecimal orderAmount = config.getPrice();
				
				//处理用户订单重复提交支付 使用redis做判断  
				String consumeOrderkey = "experience_order_"+uid;
				
				Long resultNum = stringRedisTemplate.opsForValue().increment(consumeOrderkey, 1);
				if (resultNum == 1) {//设置key首次存活时长为3分钟   避免程序异常导致死锁
					stringRedisTemplate.expire(consumeOrderkey, 2, TimeUnit.MINUTES);
				}
				log.info("该用户提交支付请求个数累计："+resultNum);
				if (resultNum>1) {
					log.info("用户:"+uid+",目前有订单正在进行支付操作,将强退其他操作");
					return new MapResponse(ResponseCode.FAILURE,"你有订单正在处理,请稍后");
				}
				
				//是否创建订单 大于0  创建订单
					
				//发起支付的时候 组装参数
				Map<String, String> paymentMap = new HashMap<String, String>();
				//组装时优先初始化订单发起验单时公共信息
				Map<Object, Object> typeMap = new HashMap<Object, Object>();
				typeMap.put("isLiveCoin", "false");
				typeMap.put("isBalance", "false");
				typeMap.put("isQuota", "false");
				
				paymentMap.put("orderName", "购买美食体验卡");
				
				try {
					
					//如果是鸟币支付
					if (experienceCardRequest.getIsFansCard() > 0) {
						
						if (orderAmount.compareTo(new BigDecimal(0))>0) {
							BigDecimal balanceCoinMoney = new BigDecimal(liveWalletMap.get("zbalanceCoin").toString());
							if (experienceCardRequest.getIsFansCard() > 0 && experienceCardRequest.getIsBalance() == 0 
									&& (null==experienceCardRequest.getPayType() || experienceCardRequest.getPayType().equals(""))) {
								if (balanceCoinMoney.compareTo(orderAmount)<0) {
									return new MapResponse(ResponseCode.FAILURE,"鸟粉卡余额不足!");
								}
							}
							
							// 当前订单金额 - 鸟币余额 是否还有为支付完
							BigDecimal orderCoin_ = orderAmount.subtract(balanceCoinMoney);
							if(orderCoin_.compareTo(new BigDecimal(0))>0){ //如果没有支付完的  
								orderAmount = orderCoin_; //将剩余金额赋值到订单总额上  继续
							}else {
								orderAmount = new BigDecimal(0);
							}
							typeMap.put("isLiveCoin", "true");//是否使用余额 必须
							paymentMap.put("base", "1");
							paymentMap.put("discount", "1");
						}else {
							log.info("选择支付方式错误,请重新选择支付方式!");
							return new MapResponse(ResponseCode.FAILURE,"选择支付方式错误,请重新现在支付方式!");
						}
					}
					
					//如果选择了   余额支付  
					if (experienceCardRequest.getIsBalance() > 0) {
						 
						if (orderAmount.compareTo(new BigDecimal(0))>0) {
							BigDecimal balanceMoney = new BigDecimal(walletMap.get("zbalance")).add(new BigDecimal(walletMap.get("commision")));
							
							if (balanceMoney.compareTo(new BigDecimal(0)) > 0) {
								if (experienceCardRequest.getIsBalance() > 0 && (null==experienceCardRequest.getPayType() || experienceCardRequest.getPayType().equals(""))) {
									if (balanceMoney.compareTo(orderAmount)<0) {
										return new MapResponse(ResponseCode.FAILURE,"钱包余额不足!");
									}
								}
								typeMap.put("isBalance", "true");
								
							}else {
								log.info("当前钱包余额不足，UID="+uid);
								return new MapResponse(ResponseCode.FAILURE,"钱包余额不足,请选择其他支付方式");
							}
						}else {
							log.info("选择支付方式错误，请重新选择UID="+uid);
							return new MapResponse(ResponseCode.FAILURE,"选择支付方式错误，请重新选择");
						}
					}
					
					//新增订单
					try {
						int addOrderResulft = this.createExperienceOrder(orderMap);
						if (addOrderResulft<=0) {
							return new MapResponse(ResponseCode.FAILURE,"创建订单异常");
						}
					} catch (Exception e) {
						log.info("创建订单异常");
						e.printStackTrace();
						return new MapResponse(ResponseCode.FAILURE,"生成订单异常,请重试!");
					}
					//发起支付
					return this.paymentUrl(experienceCardRequest, paymentMap, uid, orderMap, typeMap);
					
				} catch (Exception e) {
					log.info("创建订单异常");
					e.printStackTrace();
					return new MapResponse(ResponseCode.FAILURE,"生成订单异常,请重试!");
				}finally{
					stringRedisTemplate.delete(consumeOrderkey);
				}
			}
			
		}else {
			throw new Exception("生成订单信息异常");
		}
		return orderMap;
		
		
	}
		
	/**
	 * 组装订单基础信息 / 生产订单号 
	 * */
	public Map<Object, Object> installOrderParam(ExperienceCardRequest experienceCardRequest,
			ExperienceofficerConfig config ,Map<Object, Object> userMap) throws Exception{
		
		Map<Object, Object> orderMap = new HashMap<Object, Object>();
		try {
			orderMap.put("order_no", "014"+SaasBidType.getBid());//'订单号',
			orderMap.put("appSourceStatus", EnumUtil.getEnumCode(ConstantDictionary.AppSourceState.class, experienceCardRequest.getAppSource()));
			orderMap.put("uid", userMap.get("uid"));//'UID
			orderMap.put("phone", userMap.get("phone"));//'手机号
			orderMap.put("nname", userMap.get("nname"));//'呢称
			orderMap.put("is_free", config.getIsFree());//'是否免费
			orderMap.put("nums", config.getNums());//'次数
			orderMap.put("days", config.getDays());//'有效天数
			if (config.getIsFree()==0) {
				orderMap.put("amount", config.getPrice());//'金额
			}
			if (config.getIsFree()==1) {
				orderMap.put("amount", new BigDecimal(0));//'金额
			}
			orderMap.put("pay_state", "0");//'订单状态
			orderMap.put("create_time", DateUtil.format(new Date(), DateUtil.defaultSimpleFormater));//'订单号',
			orderMap.put("description", "购买美食体验卡");//'订单号',
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("创建订单异常");
		}
		
		return orderMap;
	}
	
	/**
	 * 创建订单
	 * @param orderMap 
	 * @return int 
	 * */
	private int createExperienceOrder(Map<Object, Object> orderMap) throws Exception{
		//新增订单
		int addOrderResulft =0;
		try {
			addOrderResulft = experienceConfigDao.inserExperienceOrder(orderMap);
			if (addOrderResulft>0) {
				return 1;
			}
		} catch (Exception e) {
			log.info("创建订单异常");
			e.printStackTrace();
			throw new Exception("生成订单异常,请重试!");
		}
		return addOrderResulft;
	}
	
	/**
	 * 描述：组合套餐购买 支付请求参数
	 * @param Map<Object Object> map
	 * @return
	 * */
	public Object paymentUrl(ExperienceCardRequest experienceCardRequest,Map<String, String> paymentMap ,
			String uid ,Map<Object, Object> map ,Map<Object, Object> typeMap ) throws Exception {
		
		
		//订单编号
		paymentMap.put("orderNumber",map.get("order_no").toString());
		//用户UID
		paymentMap.put("userId", uid);
		//获取每个套餐每个店铺的单价金额
		
		//订单来源，标识内部业务系统不同类型订单,001:业务系统-SAAS套餐订单;002:业务系统-SAAS软件订单;003:业务系统-积分商品订单；004:业务系统-物料订单；005:业务系统-直播鸟币购买订单
		paymentMap.put("source", "014");
		//订单总额
		paymentMap.put("amount", map.get("amount").toString());
		//订单类型，目前传固定值2
		paymentMap.put("orderType", "2");	
		//订单标题
		paymentMap.put("orderName",paymentMap.get("orderName"));
		
		String  signUrl =propertiesUtil.getValue("payBirdurl2", "conf_live.properties");
		String url = userPayBirdCoinService.transMap2(paymentMap,signUrl);
		
		if (typeMap!=null) {
			StringBuffer sb = new StringBuffer();
			sb = sb.append("&").append("isQuota=").append(typeMap.get("isQuota"))
				   .append("&").append("isLiveCoin=").append(typeMap.get("isLiveCoin"))
				   .append("&").append("isBalance=").append(typeMap.get("isBalance"));
			if (null != experienceCardRequest.getPayType() && !experienceCardRequest.getPayType().equals("")) {
				sb = sb.append("&").append("paymentType=").append(experienceCardRequest.getPayType());//支付类型  必须
			}
			url = url+sb.toString();
		}
		if (url.indexOf("orderName")>0) {//如果包含该字符   取出该字符的参数 做编码
			url=url.replace(paymentMap.get("orderName"), URLEncoder.encode(paymentMap.get("orderName").toString(),"UTF-8")).replaceAll(" ", "%20");
		}
		
		//开始调用支付接口------------------------------------------------------------------
		log.info("开始调用支付接口URL："+url);
		paymentMap.put("url", url);
		
		String result = userPayBirdCoinService.payBirdCoin(paymentMap).toString();
		return JSONObject.parseObject(result);
		
	}
	
	
	/**
	 * 美食体验卡免费的情况下 调用业务服务自己更新订单
	 * @param 订单信息MAP
	 * @return ResponseData 业务服务返回
	 * */
	private ResponseData updateExperienceOfficerOrder(Map<String, String> params) {
		log.info("开始调用RPC接口,更新体验卡订单状态");
		try {
			TMultiplexedProtocol protocol = thriftBusinessUtil.getProtocol("ExperienceOfficerOrderService");
			ExperienceOfficerOrderService.Client client = new ExperienceOfficerOrderService.Client(protocol);
			thriftBusinessUtil.openTransport();
			com.xmniao.xmn.core.thrift.ResponseData  data = client.updateExperienceOfficerOrder(params);
			return data;
		} catch (Exception e) {
			log.info("调用RPC接口结束,更新体验卡订单状态异常:"+params.get("orderNo"));
		} 
		return null;
	}

	/**推荐活动
	 * @param request
	 * @return
	 */
	public Object getRecommendExperience(ExperienceRecommendRequest request) {
		String uid = sessionTokenService.getStringForValue(request.getSessiontoken()) + "";
		if (StringUtils.isEmpty(uid) || "null".equalsIgnoreCase(uid)) {
			return new BaseResponse(ResponseCode.TOKENERR, "token已失效,请重新登录");
		}
		Map<Object,Object> paraMap = new HashMap<Object,Object>();
		paraMap.put("uid", uid);
		paraMap.put("lat", request.getLat());
		paraMap.put("lon", request.getLon());
		List<Map<Object,Object>> expers = experienceActivityDao.findRecommendAcitivities(paraMap);
		List<Map<Object,Object>> expersActivites = new ArrayList<Map<Object,Object>>();
		expers=isShowRecommd(expers);
		if(expers!=null && !expers.isEmpty()){
			for(int i=0;i<expers.size();i++){
				if (expers.get(i).get("live_topic")==null) {
					continue;
				}
				Date entroTime = (Date) expers.get(i).get("enroll_time");
				Date planLiveTime = (Date) expers.get(i).get("plan_start_date");
				Integer pUid = (expers.get(i).get("uid")==null?0:(Integer)expers.get(i).get("uid"));
				Integer activityState =(expers.get(i).get("activity_state")==null?0:(Integer)expers.get(i).get("activity_state"));
				Integer remainNum = (expers.get(i).get("remainder_num")==null?0:(Integer)expers.get(i).get("remainder_num"));
				Integer liveTopic = Integer.parseInt(expers.get(i).get("live_topic")+"");
				if (entroTime==null || planLiveTime==null) {
					continue;
				}
				expers.get(i).put("showContent", 0);
				//1.大于报名 时间，体验时间未到：显示‘和她吃’,小于报名时间设置‘已提醒’,‘提醒我’
				if (entroTime.before(new Date()) && planLiveTime.after(new Date()) && remainNum >0) {
					expers.get(i).put("showContent", "1");
				}
				if (entroTime.after(new Date()) && pUid==Integer.parseInt(uid) && activityState==2) {
					expers.get(i).put("showContent", "2");
				}
				if (entroTime.after(new Date()) &&  pUid==0 && activityState==0) {
					expers.get(i).put("showContent", "3");
				}
				if (entroTime.before(new Date()) && remainNum == 0) {
					expers.get(i).put("showContent", "4");
					continue;
				}
				String rangs =  expers.get(i).get("ranges")==null?"0":expers.get(i).get("ranges").toString();
				if (Double.parseDouble(rangs)>1000) {
					double longth = Double.parseDouble(rangs);
					longth=longth/1000;
					rangs=new BigDecimal(longth).setScale(1, BigDecimal.ROUND_HALF_UP).toString()+"km";
				}else {
					rangs += "m";
				}
				expers.get(i).put("enroll_time", DateUtil.format(entroTime));
				expers.get(i).put("picurl", fileUrl+expers.get(i).get("picurl"));
				if (liveTopic==2){
					expers.get(i).put("picurl", fileUrl+expers.get(i).get("zhibo_cover"));
				}
				expers.get(i).put("avatar", fileUrl+expers.get(i).get("avatar"));
				expers.get(i).put("plan_start_date", DateUtil.format(planLiveTime));
				expers.get(i).put("ranges", rangs);
				expersActivites.add(expers.get(i));
			}
		}
		if(expersActivites.size()>3) {
			expersActivites = expersActivites.subList(0, 3);
		}
		MapResponse response = new MapResponse(ResponseCode.SUCCESS, "查询成功");		
		Map<Object,Object> resultMap = new HashMap<Object,Object>();
		resultMap.put("recommendActivities", expersActivites);
		response.setResponse(resultMap);
		return response;
	}
	
	/** 1、优先显示当前场的名额未抢完的场次
		2、当前场全抢完/未设置场次，下一场有设置，则优先出下一场的场次
		3、当前场全抢完，且没有设置下一场，则不显示推荐活动
		4、当前场未设置场次，没有设置下一场，则不显示推荐活动一栏
		5、没有设置过任何场次则不显示推荐活动
		6、同类型优先显示距离离我更近的场次,距离一致则按照体验时间更近的优先，两者都一致则按后台创建时间由近到远排序
	 */
	
	
	/**判断是否显示推荐
	 * @return
	 */
	private List<Map<Object,Object>> isShowRecommd(List<Map<Object,Object>> actis){
		List<Map<Object,Object>> expers = new ArrayList<Map<Object,Object>>();
		List<Map<Object,Object>> currentExpers = new ArrayList<Map<Object,Object>>();
		List<Map<Object,Object>> nextExpers = new ArrayList<Map<Object,Object>>();
		List<Map<Object,Object>> otherExpers = new ArrayList<Map<Object,Object>>();
		if (actis==null){
			return null;
		}
		
		for(int i=0;i<actis.size();i++){
			Integer isCurrentActi = (Integer) actis.get(i).get("iscurrentActi");
			Integer isnextActi = (Integer) actis.get(i).get("isnextActi");
			Integer remainNum = (actis.get(i).get("remainder_num")==null?0:(Integer)actis.get(i).get("remainder_num"));
			if (isCurrentActi==1 && remainNum>0) {
				currentExpers.add(actis.get(i));
			}else if (isnextActi==1) {
				nextExpers.add(actis.get(i));
			}else{
				otherExpers.add(actis.get(i));
			}
		}
		
		//没有当场并且也没有下一场
		if (currentExpers.size()==0 && nextExpers.size()==0){
			return null;
		}
		
		//有当场但是剩余数量为0，并且没有下一场
		if(currentExpers.isEmpty()){
			if(nextExpers.size()==0){
				return null;
			}else {
				expers.addAll(nextExpers);
				expers.addAll(currentExpers);
				expers.addAll(otherExpers);
				return expers;
			}
		}
		
		expers.addAll(currentExpers);
		expers.addAll(nextExpers);
		expers.addAll(otherExpers);
		return expers;
	}

	/**提醒我
	 * @param request
	 * @return
	 */
	public Object remindUserExperienceActivity(ExperienceRemindRequest request) {
		String uid = sessionTokenService.getStringForValue(request.getSessiontoken()) + "";
		//H5直接传递uid过来
		if (StringUtils.isEmpty(uid) || "null".equalsIgnoreCase(uid)) {
			uid=request.getUid()+"";
		}
		if (StringUtils.isEmpty(uid) || "null".equalsIgnoreCase(uid)) {
			return new BaseResponse(ResponseCode.TOKENERR, "token已失效,请重新登录");
		}
		Integer activityId = request.getActivityId();
		int result =pushSingleService.pushSingleAccountRemindActivity(activityId,uid,request.getAppSource());
		if (result!=0) {
			log.error("提醒失败：信鸽推送失败---推送消息返回值为0");
			return new BaseResponse(ResponseCode.FAILURE, "提醒失败--信鸽推送失败");
		}
		//插入t_exprience_push表
		Map<Object,Object> paraMap = new HashMap<Object,Object>();
		paraMap.put("activityId", activityId);
		paraMap.put("uid", uid);
		paraMap.put("activityState", 2);
		paraMap.put("createTime", new Date());
		try {
			//1.插入消息提醒表--先数据库查询是否已经提醒过，如果有直接返回成功。
			Integer countRemind = experienceActivityDao.countExperenceActivityPushByUid(paraMap);
			if (countRemind != 0){
				log.error("提醒失败：用户已经提醒过--报错");
				return new BaseResponse(ResponseCode.SUCCESS,"用户已经提醒过!");
			}
		   experienceActivityDao.insertExperiencePush(paraMap);
		  
		   
		 /*2.插入系统消息表--先数据库里面查询：如果存在activity 和uid一致的有效消息直接返回成功。*/
		   paraMap.clear();
		   paraMap = getRemindContent(activityId,uid);
			Map<Object,Object> hasActivityMap = messageManageDao.findMessagebyActivityId(paraMap);
			if(hasActivityMap!=null && !hasActivityMap.isEmpty()) {
				return new BaseResponse(ResponseCode.SUCCESS, "系统消息已经存在!");
			}
		   messageManageDao.insertAppPush(paraMap);
		}catch(Exception e) {
			log.error("提醒我失败：未知错误"+uid+"--"+e);
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE,"提醒失败--未知异常");
		}	
		return new BaseResponse(ResponseCode.SUCCESS, "成功");
	}

	/**组装系统消息内容
	 * @param activityId
	 * @param uid
	 * @return
	 */
	private Map<Object, Object> getRemindContent(Integer activityId, String uid) {
		Map<Object,Object> activityMap = experienceActivityDao.findExperenceActivityById(activityId);
		Date entroTime =  DateUtil.parse(activityMap.get("enroll_time")+"");
		Calendar c = Calendar.getInstance();
		c.setTime(entroTime);
		c.add(Calendar.MINUTE, -10);
		Date sendTime = c.getTime();
		try{
			Map<Object,Object> paraMap = new HashMap<Object,Object>();
			paraMap.put("uid", uid);
			paraMap.put("sdate",sendTime);
			paraMap.put("title", "美食体验抢位提醒");
			paraMap.put("activityId", activityId);
			paraMap.put("content",  "尊敬的美食体验馆,距离你感兴趣的活动开抢时间还有10分钟,请准备好哟！");
			paraMap.put("action", propertiesUtil.getValue("exprienceActivityUrl", "conf_common.properties").toString()+"?action=app-officer-shop-details"); //消息跳转地址
			paraMap.put("status",1);
			paraMap.put("activityType",1);
			return paraMap;
		}catch(Exception e){
			log.info("读取文件出错---exprienceActivityUrl");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取美食体验官列表
	 * @return
	 */
	public List<ExperienceOfficerOrder> getExperienceOrderList(Integer uid, String maxTime, Integer page, Integer pay_state) {
		Map<Object, Object> param = new HashMap<Object, Object>();
		param.put("uid", uid);
		param.put("maxTime", maxTime);
		param.put("page", page);
		param.put("limit", Constant.PAGE_LIMIT);
		param.put("pay_state", pay_state);
		List<ExperienceOfficerOrder> orderList = experienceConfigDao.queryExperienceOrderList(param);
		return orderList;
	}

}
