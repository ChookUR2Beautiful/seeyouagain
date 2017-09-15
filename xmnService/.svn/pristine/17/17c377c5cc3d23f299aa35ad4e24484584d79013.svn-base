package com.xmniao.xmn.core.xmer.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.Constant;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.TopicalRankRequest;
import com.xmniao.xmn.core.common.request.UidRequest;
import com.xmniao.xmn.core.common.request.XmkTutorRequest;
import com.xmniao.xmn.core.util.ArithUtil;
import com.xmniao.xmn.core.util.DateUtil;
import com.xmniao.xmn.core.util.PropertiesUtil;
import com.xmniao.xmn.core.verification.dao.BillDao;
import com.xmniao.xmn.core.verification.dao.UrsInfoDao;
import com.xmniao.xmn.core.wealth.dao.IncomeInfoDao;
import com.xmniao.xmn.core.xmer.dao.FriendshipDao;
import com.xmniao.xmn.core.xmer.dao.SaasOrderDao;
import com.xmniao.xmn.core.xmer.dao.SaasSoldOrderDao;
import com.xmniao.xmn.core.xmer.dao.SellerDao;
import com.xmniao.xmn.core.xmer.dao.UrsEarningsRelationDao;
import com.xmniao.xmn.core.xmer.dao.WalletRecordDao;
import com.xmniao.xmn.core.xmer.dao.XmerDao;
import com.xmniao.xmn.core.xmer.dao.XmerLevelDao;
import com.xmniao.xmn.core.xmer.entity.Xmer;

/**
 * 
 * 项目名称： xmnService 类名称： FriendsInfoService 类描述：我的伙伴列service 创建人： lifeng 创建时间：
 * 2016年5月23日上午10:04:52
 * 
 * @version
 */
@Service
public class FriendsInfoService {

	// 日志
	private final Logger log = Logger.getLogger(FriendsInfoService.class);

	/**
	 * 注入XmerDao
	 */
	@Autowired
	private XmerDao xmerDao;

	/**
	 * 注入FriendShipDao
	 */
	@Autowired
	private FriendshipDao friendshipDao;

	/**
	 * 注入UserInfoDao
	 */
	@Autowired
	private UrsInfoDao ursInfoDao;

	/**
	 * 注入XmerLevelDao
	 */
	@Autowired
	private XmerLevelDao xmerLevelDao;

	/**
	 * 注入XmerLevelDao
	 */
	@Autowired
	private SaasSoldOrderDao saasSoldOrderDao;

	/**
	 * 注入XmerLevelDao
	 */
	@Autowired
	private SaasOrderDao saasOrderDao;

	@Autowired
	private IncomeInfoDao incomeInfoDao;

	@Autowired
	private FlowInfoService flowInfoService;

	/**
	 * 注入walletRecordDao
	 */
	@Autowired
	private WalletRecordDao walletRecordDao;

	/**
	 * 注入索要微信号需要花费的积分数
	 */
	@Autowired
	private String weChatPrice;

	/**
	 * 
	 * 注入BillDao
	 */
	@Autowired
	private BillDao billDao;

	/**
	 * 注入SessionTokenService
	 */
	@Autowired
	private SessionTokenService sessionTokenService;

	@Resource
	private String fileUrl;

	@Autowired
	private PropertiesUtil properties;

	@Autowired
	private BannerServcie bannerService;

	@Autowired
	private PropertiesUtil propertiesUtil;

	@Autowired
	private XmerService xmerService;

	@Autowired
	private UrsEarningsRelationDao ursEarningsRelationDao;

	@Autowired
	private SellerDao sellerDao;

	@Autowired
	private XmerOrderRecordService xmerOrderRecordService;
	
	@Autowired
	private XmerAmountService xmerAmountService;
	
	@Autowired
	private XmerHomeService xmerHomeService;

	/**
	 * 
	 * @Title:
	 * @Description: 查看我的伙伴列表
	 * @return Object
	 * @param baseRequest
	 * @return
	 * @Description 修改查询伙伴的本月流水
	 * @update zhengyaowen
	 * @date 2016-05-30 11:09
	 */
	public Object queryfriendsInfoList(TopicalRankRequest baseRequest) {
		try {
			// 通过token查询缓存数据
			String strUid = sessionTokenService.getStringForValue(baseRequest.getSessiontoken()) + "";
			Integer uid = Integer.parseInt(strUid);

//			uid = 606777;
			/**
			 * 返回的列表
			 */
			List<Map<Object, Object>> friendsList = new ArrayList<>(); // 节点friends
			Map<Object, Object> resultMap = new HashMap<>();// 好友申请

			// 查询当前用户的寻蜜客信息
			Xmer xmer = xmerDao.selectEnXmerByUid(uid);

			// 条件参数
			Map<Object, Object> param = new HashMap<>();
			param.put("uid", uid);
			param.put("pageIndex", (baseRequest.getPage() - 1) * Constant.PAGE_LIMIT);
			param.put("pageSize", Constant.PAGE_LIMIT);
			if (Integer.valueOf(xmer.getRtype()) == 1) { // 个人寻密客(查询下级和下下级)
				
				
				/*
				// 查询下级与下下级
				List<Xmer> friendXmerList = xmerDao.queryXmerByParentid(param);
				// 获取下级与下下级寻蜜客信息
				friendsList = getLowerLevelFriendXmer(uid, friendXmerList, Integer.valueOf(xmer.getRtype()));
				resultMap.put("applys", new ArrayList<>());
				 */
				/****** V3.6修改 *******/
				// 查询下级与下下级寻蜜客
				List<Integer> relationList = ursEarningsRelationDao.queryRelationList(uid);
				if(relationList.isEmpty()){
					return new BaseResponse(ResponseCode.SUCCESS,"还没有邀请小伙伴哦，快去邀请吧！");
				}
				param.put("ids", relationList);
				List<Xmer> friendXmerList = xmerDao.listFriendsInfoByIds(param);
				friendsList = getLowerLevelFriendXmer(uid, friendXmerList, Integer.valueOf(xmer.getRtype()));
				resultMap.put("applys", new ArrayList<>());

			} else if (Integer.valueOf(xmer.getRtype()) == 2) { // 企业寻密客
				// 好友申请
				List<Integer> uidToList = friendshipDao.queryUidToByUidFrom(param);

				// 好友寻蜜客信息
				List<Xmer> friendXmerList = xmerDao.selectEnXmerByUids(uidToList);
				// 获取下级与下下级寻蜜客信息
				friendsList = getLowerLevelFriendXmer(uid, friendXmerList, Integer.valueOf(xmer.getRtype()));

				/**
				 * 从缓存中查询好友申请
				 */
				Set<String> set = sessionTokenService.getZSetForValue(Constant.FRIEND_APPLICANTS_KEY + uid, 0, -1);
				if (set != null && set.size() > 0) {
					resultMap.put("applys", JSONArray.parseArray(set.toString()));
				} else {
					// 查询数据库
					List<Map<Object, Object>> friendApplyList = friendshipDao.queryFriendApplyByUid(uid);
					if (friendApplyList != null && friendApplyList.size() > 0) {
						// 存放到缓存中
						sessionTokenService.setZSetForValue(Constant.FRIEND_APPLICANTS_KEY + uid,
								JSONObject.toJSON(friendApplyList).toString(), new Date().getTime());
						resultMap.put("applys", friendApplyList);
					}
				}
			}
			MapResponse response = new MapResponse(ResponseCode.SUCCESS, "查询成功");
			resultMap.put("friends", friendsList);
			response.setResponse(resultMap);
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE, "查看我的伙伴信息列表失败");
		}
	}

	/**
	 * @Title: queryFriendInfo @Description: 查询我的小伙伴详情信息 @param
	 *         FriendInfoRequest @return Object 返回类型 @author
	 *         zhengyaowen @Description 修改描述 @update 修改人 @date 修改日期 @throws
	 */
	@SuppressWarnings("static-access")
	public Object queryFriendInfo(UidRequest uidRequest) {
		DecimalFormat df = new DecimalFormat("0.00");// 格式化数据
		// 伙伴用户ID
		Integer uid = uidRequest.getUid();
		String jsonStr = "";
		try {
			jsonStr = propertiesUtil.getValue("wz000" + uid.toString(), "conf_xmer.properties");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (StringUtils.isEmpty(jsonStr)) {
			try {
				// 用户ID
				int parentid = Integer
						.valueOf(sessionTokenService.getStringForValue(uidRequest.getSessiontoken()) + "");
				Map<Object, Object> result = new HashMap<Object, Object>();
				
				/*** V3.6版本 ***/
				//直接寻蜜客 uid集合
				List<Integer> directRelation = ursEarningsRelationDao.listDirectRelation(parentid);
				
				//查询 伙伴发展的寻蜜客数量
				List<Integer> relationList = ursEarningsRelationDao.queryRelationList(uid);
				
				Xmer xmer = xmerDao.queryFriendInfoById(uid);
				
				String phone = xmer.getPhoneid();
				if(StringUtils.isEmpty(phone)){
					phone="";
				}
				result.put("phone", phone);
				
				result.put("partnernums", relationList.size());// 伙伴的伙伴数量
				result.put("achieve", xmer.getAchievement());// 伙伴的成就名称
				
				//伙伴签约商家数量(伙伴自己和关系链)
				Integer signnums = sellerDao.queryReadyNumByUids(relationList,uid);
				result.put("signnums", signnums);// 伙伴签约商家数量
				
				//伙伴头像地址
				String avatar = xmer.getAvatar();
				result.put("avatar", StringUtils.isEmpty(avatar)?"":fileUrl+avatar);// 伙伴的头像地址
				
				String wechatno = xmer.getWeixinid();
				result.put("wechatno", wechatno);//微信号为空显示手机号码
				
				String name = "";
				if(StringUtils.isNotEmpty(xmer.getName())){
					name = xmer.getName();
				} else {
					String uname = xmer.getUname();
					if (StringUtils.isNotEmpty(uname)){
						name = uname.substring(0, 3) + "****" + uname.substring(7);
					}
				}
				result.put("name", name); // 名字
				
				result.put("sex", xmer.getSex());
				
				if(directRelation.contains(uid)){	//包含在直接关系链中，直接下级
					result.put("level", 1);
					result.put("isshow", 1);
				}else{		//下下级好友
					Map<String, Object> reqMap = new HashMap<String, Object>();
					result.put("level", 2);
					// 查询是否支付过(支付过才显示微信号)
					int count = 0;// 判断是否支付过，默认为0
					reqMap.put("buyUid", parentid);
					reqMap.put("soldUid", uid);
					// 通过用户ID查询是否支付过
					count = xmerDao.queryViewWechat(reqMap);
					if (count != 0) {
						result.put("isshow", 1);
					} else {
						result.put("isshow", 0);
					}
				}
				
				//查询好友总收入，软件销售收入，预计可以再赚
				Double totalincome = 0D;
				Double saasAmount = 0D;
				try {
					Map<Object, Object> amountMap = xmerAmountService.amount(uid);
					totalincome = Double.parseDouble(amountMap.get("totalIncome").toString());
					saasAmount = Double.parseDouble(amountMap.get("softIncome").toString());
				} catch (Exception e) {
					log.warn("查询寻蜜客收入异常");
					e.printStackTrace();
				}
				
				//总收入额
				BigDecimal b1 = new BigDecimal(totalincome);// 格式化寻蜜客总收入
				String income = df.format(b1.setScale(2, RoundingMode.HALF_UP).doubleValue());// 格式化后的寻蜜客总收入
				result.put("income", income);
				
				//软件销售额
				BigDecimal b2 = new BigDecimal(saasAmount);// 格式化寻蜜客软件销售总金额
				String sAmount = df.format(b2.setScale(2, RoundingMode.HALF_UP).doubleValue());// 格式化后的寻蜜客软件销售总金额
				result.put("saasAmount", sAmount);
				
				//是否显示 预计可以再赚金额
				Integer showWzfInfo= Integer.parseInt(properties.getValue("showWzfInfo", "conf_xmer.properties"));
				result.put("showWzfInfo",showWzfInfo);
				
				String amount = "";
				if(showWzfInfo.equals(1)){
					Double wfzAmount = xmerHomeService.getWfzAmount(uid);
					BigDecimal b3 = new BigDecimal(wfzAmount);
					amount = df.format(b3.setScale(2, RoundingMode.HALF_UP).doubleValue());// 格式化后的未到账金额
				}
				//预计可以再赚金额
				result.put("wfzAmount", amount);
				
				// 流水总额
				
				Map<Object, Object> flowResult = flowInfoService.totalFlowAmount(uid);
				Double flow = Double.parseDouble(flowResult.get("mouthincome").toString());
				
				BigDecimal b4 = new BigDecimal(flow);
				String tincome = df.format(b4.setScale(2, RoundingMode.HALF_UP).doubleValue());// 格式化后的总流水
				result.put("totalflow", tincome);
				
				// 索要微信号所需积分
				result.put("weChatPrice", weChatPrice);
				// 查询广告图
				List list = bannerService.getBannerList();
				result.put("banners", list);
				
				
				
				
				
				
				/*
				Map<String, Object> reqMap = new HashMap<String, Object>();
				reqMap.put("uid", uid);
				// reqMap.put("parentid", parentid); //如果有这个条件，二级寻蜜客就查不到信息
				Xmer xmer = xmerDao.queryXmerByUid(reqMap);
				if (null == xmer) {
					return new BaseResponse(ResponseCode.XMER_EXIST, "我的小伙伴详情信息不存在");
				}

				
				result.put("phone", xmer.getPhoneid());// 寻蜜客的电话号码
				result.put("partnernums", xmer.getPartnerNums());// 伙伴的伙伴数量
				result.put("achieve", xmer.getAchievement());// 伙伴的成就名称
				result.put("signnums", xmer.getSoldNums());// 伙伴签约商家数量
				result.put("avatar", fileUrl + ursInfoDao.queryUrsAvatarByUid(uid));// 伙伴的头像地址
				result.put("wechatno", xmer.getWeixinid() == null ? xmer.getPhoneid() : xmer.getWeixinid());// 伙伴的微信号
				result.put("name", xmer.getName()); // 名字
				result.put("sex", xmer.getSex());
				// 判断是否是
				if (xmer.getParentid() != null && xmer.getParentid() == parentid) {
					result.put("level", 1);
					result.put("isshow", 1);
				} else {
					result.put("level", 2);
					result.put("name", "陌生人");
					// 查询是否支付过(支付过才显示微信号)
					int count = 0;// 判断是否支付过，默认为0
					reqMap.put("buyUid", parentid);
					reqMap.put("soldUid", uid);
					// 通过用户ID查询是否支付过
					count = xmerDao.queryViewWechat(reqMap);
					if (count != 0) {
						result.put("isshow", 1);
					} else {
						result.put("isshow", 0);
					}
				}
				// 查询寻蜜客购买的总套餐数量跟库存数量
				Map<Object, Object> saasOrderMap = saasOrderDao.sumNumsAndStockByUid(uid);
				if (saasOrderMap != null && saasOrderMap.size() > 0
						&& StringUtils.isNotBlank(saasOrderMap.get("nums").toString())
						&& StringUtils.isNotBlank(saasOrderMap.get("stock").toString())) {
					result.put("nums", saasOrderMap.get("nums"));// 套餐数量
					result.put("stock", saasOrderMap.get("stock"));// 库存数量
				} else {
					return new BaseResponse(ResponseCode.FAILURE, "查询套餐/库存数量异常");
				}

				// 查询用户钱包id
				// Integer accountid = incomeInfoDao.queryAccountidByUid(uid);
				// 总收入
				Double totalincome = incomeInfoDao.sumIncome(uid);
				if (totalincome == null) {
					totalincome = 0D;
				}
				result.put("income", totalincome);

				// 查询寻蜜客软件销售总金额
				// 获取寻蜜客以及下级,下下级的uid
				List<Integer> uids = null;
				// 中脉或v客,不需要查询下级，下下级的uid
				Map<Integer, Boolean> identityMap = xmerService.identityMap(uid);
				// 1 寻觅客 2中脉 3 V客
				boolean isXmer = identityMap.get(1);
				boolean isV = identityMap.get(3);
				boolean isM = identityMap.get(2);
				if (isV || isM) {
					uids = flowInfoService.getUids(uid);
				} else {
					uids = new ArrayList<Integer>();
					uids.add(uid);
				}

				Map<Object, Object> paramMap = new HashMap<>();
				paramMap.put("uids", uids);
				paramMap.put("uid", uid);
				paramMap.put("price", Constant.SAAS_RETURN_AMOUNT);

				// 查询软件销售总金额
				Double saasAmount = saasSoldOrderDao.querySaasAmount(paramMap);
				if (saasAmount == null) {
					saasAmount = 0D;
				}

				BigDecimal b4 = new BigDecimal(saasAmount);// 格式化寻蜜客软件销售总金额
				String sAmount = df.format(b4.setScale(2, RoundingMode.HALF_UP).doubleValue());// 格式化后的寻蜜客软件销售总金额
				result.put("saasAmount", sAmount);

				Double alltotalincome = 0D;
				// 获取所有需要统计流水的寻蜜客id
				// List<Integer> uids =
				// flowInfoService.getUids(Integer.valueOf(uid));
				for (Integer xmeruid : uids) {
					Map<Object, Object> totalmap = new HashMap<Object, Object>();// 存查询总消费额参数
					totalmap.put("xmeruid", xmeruid);
					Double totalFlow = billDao.sumSellerOrder(totalmap);
					if (totalFlow == null) {
						totalFlow = 0D;
					}
					alltotalincome += totalFlow;
				}
				BigDecimal b5 = new BigDecimal(alltotalincome);// 格式化总流水
				String totalflow = df.format(b5.setScale(2, RoundingMode.HALF_UP).doubleValue());// 格式化后的总流水
				// 流水总额
				result.put("totalflow", totalflow);

				// 索要微信号所需积分
				result.put("weChatPrice", weChatPrice);
				// 查询广告图
				List list = bannerService.getBannerList();
				result.put("banners", list);
				// 查询待确认金额
				double wfzAmount = getWfzAmout(uid + "");
				result.put("wfzAmount", wfzAmount);
				*/
				
				MapResponse response = new MapResponse(ResponseCode.SUCCESS, "查询成功");
				response.setResponse(result);
				return response;
			} catch (Exception e) {
				e.printStackTrace();
				return new BaseResponse(ResponseCode.FAILURE, "查询我的小伙伴详情失败！");
			}
		} else {
			Map<Object, Object> wzInfoMap = new HashMap<Object, Object>();
			JSONObject json = new JSONObject();
			wzInfoMap.put("uid", json.parseObject(jsonStr).getString("uid"));// 伪造用户id
			wzInfoMap.put("name", json.parseObject(jsonStr).getString("name"));// 伪造用户名称
			wzInfoMap.put("sex", json.parseObject(jsonStr).getString("sex"));// 伪造用户名称
			wzInfoMap.put("avatar", json.parseObject(jsonStr).getString("avatar"));// 伪造用户头像
			wzInfoMap.put("rownum", json.parseObject(jsonStr).getString("rownum"));// 伪造序列号
			wzInfoMap.put("saasAmount", json.parseObject(jsonStr).getString("saasAmount"));// 伪造伙伴的软件销售金额
			wzInfoMap.put("totalflow", json.parseObject(jsonStr).getString("totalflow"));// 伪造伙伴的总流水
			wzInfoMap.put("partnernums", json.parseObject(jsonStr).getString("partnernums"));// 伪造伙伴的伙伴数量
			wzInfoMap.put("signnums", json.parseObject(jsonStr).getString("signnums"));// 伪造伙伴的签约商铺数量
			wzInfoMap.put("nums", json.parseObject(jsonStr).getString("nums"));// 伪造套餐额定数量
			wzInfoMap.put("stock", json.parseObject(jsonStr).getString("stock"));// 伪造剩余套餐数量
			wzInfoMap.put("income", json.parseObject(jsonStr).getString("income"));// 伪造伙伴的总收入
			wzInfoMap.put("wechatno", json.parseObject(jsonStr).getString("wechatno"));
			wzInfoMap.put("phone", json.parseObject(jsonStr).getString("phone"));
			wzInfoMap.put("achieve", json.parseObject(jsonStr).getString("achieve"));
			wzInfoMap.put("wechatno", json.parseObject(jsonStr).getString("wechatno"));
			wzInfoMap.put("weChatPrice", weChatPrice);
			MapResponse response = new MapResponse(ResponseCode.SUCCESS, "成功");
			response.setResponse(wzInfoMap);
			return response;
		}
	}

	public double getWfzAmout(String uid) {
		try {

			Double orderMount = 0D;
			List<Integer> uids = new ArrayList<>();
			// 查询自己签约店铺的流水分账
			Map<Object, Object> orderMap = new HashMap<>();
			orderMap.put("uid", uid);
			List<Map<Object, Object>> orderList = incomeInfoDao.queryMikeAmountByuid(orderMap);
			for (Map<Object, Object> order : orderList) {
				if (order.get("commission") == null)
					continue;
				Double mikeAmount = 0D;
				try {
					JSONObject json = JSONObject.parseObject(order.get("commission").toString());
					if (json.getDouble("mike_amount") != null) {
						mikeAmount = json.getDouble("mike_amount");
					}

				} catch (Exception e) {
					e.printStackTrace();
					mikeAmount = 0D;
				}
				orderMount += mikeAmount;
			}
			// 查询下一级的店铺流水分账
			List<Integer> uidToOnes = flowInfoService.queryFriendShipByUid(Integer.valueOf(uid));
			for (Integer uidToOne : uidToOnes) {
				uids.add(uidToOne);
				orderMap = new HashMap<>();// 订单map
				orderMap.put("uid", uidToOne);
				orderList = incomeInfoDao.queryMikeAmountByuid(orderMap);
				for (Map<Object, Object> order : orderList) {
					if (order.get("commission") == null)
						continue;
					Double parentMikeAmount = 0D;
					try {
						JSONObject json = JSONObject.parseObject(order.get("commission").toString());
						if (json.getDouble("parent_mike_amount") != null) {
							parentMikeAmount = json.getDouble("parent_mike_amount");
						}
					} catch (Exception e) {
						e.printStackTrace();
						parentMikeAmount = 0D;
					}
					orderMount += parentMikeAmount;
				}
				// 查询下下级的店铺流水分账
				List<Integer> uidToTwos = flowInfoService.queryFriendShipByUid(uidToOne);
				for (Integer uidToTwo : uidToTwos) {
					uids.add(uidToTwo);
					orderMap = new HashMap<>();// 订单map
					orderMap.put("uid", uidToTwo);
					orderList = incomeInfoDao.queryMikeAmountByuid(orderMap);
					for (Map<Object, Object> order : orderList) {
						if (order.get("commission") == null)
							continue;
						Double topMikeAmount = 0D;
						try {
							JSONObject json = JSONObject.parseObject(order.get("commission").toString());
							if (json.getDouble("top_mike_amount") != null) {
								topMikeAmount = json.getDouble("top_mike_amount");
							}
						} catch (Exception e) {
							e.printStackTrace();
							topMikeAmount = 0D;
						}
						orderMount += topMikeAmount;
					}
				}
			}
			Double saasAmount = 0.00D;
			if (uids != null && uids.size() > 0) {

				/** 分账规则修改日期（9月7号（不含7号）之前购买的套餐按108算，7号及7号后按72算） */
				String wfzDate = properties.getValue("wfzDate", "conf_xmer.properties");
				/** 请求参数 */
				Map<String, Object> params = new HashMap<>();
				params.put("uids", uids);
				params.put("wfzDate", wfzDate);
				params.put("type", 0);

				/** 查询9月7日前的 */
				// 总的寻蜜客签约套数
				Integer allSaasNums = saasOrderDao.getXmerSaasNums(params);
				if (allSaasNums != null && allSaasNums > 0) {
					// 查寻9月7日前签约套数的订单id
					List<String> ordersns = saasOrderDao.getXmerSaasOrdersns(params);
					// 总的已上线店铺数
					int allSellerNums = saasSoldOrderDao.getXmerSellerNums(ordersns);
					// 9月7日前未到账金额
					saasAmount = ArithUtil.mul(allSaasNums - allSellerNums, Constant.SAAS_RETURN_AMOUNT);
				}

				/** 9月7日后未到账金额 */
				params.put("type", 1);
				// 总的寻蜜客签约套数
				Integer nallSaasNums = saasOrderDao.getXmerSaasNums(params);
				if (nallSaasNums != null && nallSaasNums > 0) {
					// 查寻9月7日前签约套数的订单id
					List<String> nordersns = saasOrderDao.getXmerSaasOrdersns(params);
					// 9月7日后已到帐数量
					int nallSellerNums = saasSoldOrderDao.getXmerSellerNums(nordersns);
					// 9月7日前未到账金额需要减去优惠卷金额
					Double newsaasAmount = ArithUtil.mul(nallSaasNums - nallSellerNums,
							ArithUtil.sub(Constant.SAAS_RETURN_AMOUNT, Constant.SAAS_PRE_AMOUNT));
					saasAmount = ArithUtil.add(saasAmount, newsaasAmount);
				}

			}
			return ArithUtil.add(orderMount, saasAmount);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	/**
	 * @Title: getLowerLevelFriendXmer @Description: 获取下级与下下级寻蜜客信息 @param
	 *         List<Xmer> @return Object 返回类型 @author zhengyaowen @Description
	 *         修改描述 @update 修改人 @date 修改日期 @throws
	 */
	private List<Map<Object, Object>> getLowerLevelFriendXmer(Integer uid, List<Xmer> friendXmerList, int rtype) {

		List<Integer> uidList = ursEarningsRelationDao.listDirectRelation(uid);

		List<Map<Object, Object>> friendsList = new ArrayList<>(); // 节点friends
		if (friendXmerList != null && friendXmerList.size() > 0) {
			for (Xmer friendXmer : friendXmerList) {
				Map<Object, Object> resMap = new HashMap<>();

				Integer friendUid = friendXmer.getUid();
				resMap.put("uid", friendUid);// 伙伴id

				String name = "";
				if(StringUtils.isNotEmpty(friendXmer.getName())){
					name = friendXmer.getName();
				} else {
					String uname = friendXmer.getUname();
					if (StringUtils.isNotEmpty(uname)){
						name = uname.substring(0, 3) + "****" + uname.substring(7);;
					}
				}
				resMap.put("name", name);// 用户名称

				// 头像地址
				String avatar = friendXmer.getAvatar();

				resMap.put("avatar", StringUtils.isEmpty(avatar) ? "" : fileUrl + avatar);// 头像地址

				resMap.put("type", friendXmer.getRtype());// 伙伴类型（个人寻蜜客/企业寻蜜客）

				String achievement = friendXmer.getAchievement();

				resMap.put("achieve", StringUtils.isEmpty(achievement) ? "" : achievement);// 伙伴成就

				Integer sex = friendXmer.getSex();
				resMap.put("sex", sex.equals(2) ? 2 : 1); // 性别

				// 查询 好友 签约上线店铺数量(包含下级和下下级)
				List<Integer> uids = ursEarningsRelationDao.queryRelationList(friendUid);
				Integer signnums = sellerDao.queryReadyNumByUids(uids,friendUid);
				resMap.put("signnums", signnums);// 伙伴签约商家数量

				//查询本月流水
				Map<Object, Object> result = flowInfoService.totalFlowAmount(friendUid);
				Double flow = Double.parseDouble(result.get("mouthincome").toString());
				
				
				/*
				   Double flow = xmerOrderRecordService.getTotalAmountByDate(friendUid, uids, 1, 2,
						DateUtil.format(DateUtil.getMonthFirstDay(DateUtil.now()), "yyyy-MM-dd"),
						DateUtil.format(DateUtil.firstDayOfMonth(DateUtil.addMonth(DateUtil.now(), 1)), "yyyy-MM-dd"));
				 */
				DecimalFormat df = new DecimalFormat("0.00");// 格式化数据
				BigDecimal b1 = new BigDecimal(flow);
				String mouthflow = df.format(b1.setScale(2, RoundingMode.HALF_UP).doubleValue());// 格式化后的总流水
				resMap.put("mouthflow", mouthflow);

				resMap.put("level", uidList.contains(friendUid) ? 1 : 2); // 伙伴级别,1:下级,2:下下级

				friendsList.add(resMap);
			}
		}
		return friendsList;
	}

	// 导师列表接口实现类
	@SuppressWarnings("static-access")
	public Object queryXmkTutorList(XmkTutorRequest xmkTutorRequest) {
		// 验证token
		if (StringUtils.isEmpty(xmkTutorRequest.getSessiontoken())) {
			log.info("无效token" + xmkTutorRequest.getSessiontoken());
			return new BaseResponse(ResponseCode.TOKENERR, "无效token，请重新登录");
		}

		// 验证用户是否登录
		String uid = sessionTokenService.getStringForValue(xmkTutorRequest.getSessiontoken()).toString();// 获取用户uid
		if (StringUtils.isEmpty(uid)) {
			log.info("token，已过期，请重新登录" + "uid:" + uid);
			return new BaseResponse(ResponseCode.TOKENERR, "token，已过期，请重新登录");
		}
		List<String> list = new ArrayList<String>();
		String josnStr = "";
		// 获取导师列表
		for (int i = 1; i <= 10; i++) {
			try {
				josnStr = propertiesUtil.getValue("wz000" + i, "conf_xmer.properties");
				log.info("josnStr====================================:" + josnStr);
				if (!StringUtils.isEmpty(josnStr) && !"null".equals(josnStr)) {
					list.add(josnStr);
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.info("获取配置文件异常");
			}
		}
		log.info("list====================================:" + list.size());

		// if(list.size() < 1 || list == null){
		// //查询导师列表
		// Map<Object,Object> map = new HashMap<Object,Object>();//零时Map
		// map.put("page", xmkTutorRequest.getPage());//页数
		// map.put("limit", Constant.PAGE_LIMIT);//每页条数
		// try{
		// List<Map<Object,Object>> tutors = new
		// ArrayList<Map<Object,Object>>();//结果集
		// List<Map<Object,Object>> tutorList =
		// xmerDao.queryXmkInfoListByPaternerNum(map);
		// if(tutorList != null&&tutorList.size() >0){
		// //组装查询导师昵称数据
		// List<Integer> uidList = new ArrayList<Integer>();
		// for(Map<Object,Object> uidMap : tutorList){
		// Integer uids =
		// Integer.valueOf(uidMap.get("uid").toString());//获取导师的uid
		// uidList.add(uids);//把导师uid存入list集合
		// }
		// //批量查询导师昵称和头像
		// List<Map<Object,Object>> tutorNnameList =
		// friendshipDao.queryTutorAvatarListByUid(uidList);
		// //批量查询导师总收入
		// List<Map<Object,Object>> tutorIncomeList =
		// friendshipDao.queryXmkTutorIncomeListByUid(uidList);
		//
		// for (int i = 0; i < tutorList.size(); i++) {
		// Map<Object,Object> tutorMap = tutorList.get(i);
		// Map<Object,Object> xmkTutorMap = new HashMap<Object,Object>();
		// xmkTutorMap.put("uid", tutorMap.get("uid"));//导师uid
		// xmkTutorMap.put("rownum", tutorMap.get("rownum"));//导师序列号
		// xmkTutorMap.put("partnernum", tutorMap.get("partnernum"));//已发展寻蜜客人数
		// xmkTutorMap.put("soldnums", tutorMap.get("soldnums"));//已签约店铺数量
		// xmkTutorMap.put("name", "导师");//导师名称
		// xmkTutorMap.put("avatar", "");//导师头像
		// xmkTutorMap.put("income", 0.00D);//总收入
		// //查询名称等信息
		// for(Map<Object,Object> tutorNnameMap : tutorNnameList){
		// if(tutorMap.get("uid").toString().equals(tutorNnameMap.get("uid").toString())){
		// xmkTutorMap.put("name",
		// tutorNnameMap.get("name")==null?"导师":tutorNnameMap.get("name"));//导师名称
		// xmkTutorMap.put("avatar", fileUrl+tutorNnameMap.get("avatar"));//导师头像
		// break;
		// }
		// }
		// //查询收入信息
		// for(Map<Object,Object> tutorIncomeMap : tutorIncomeList){
		// if(tutorMap.get("uid").toString().equals(tutorIncomeMap.get("uid").toString())){
		// double income =
		// ArithUtil.add(Double.valueOf(tutorIncomeMap.get("profit").toString()),
		// Double.valueOf(tutorIncomeMap.get("trunout").toString()));
		// xmkTutorMap.put("income", income);//导师总收入
		// break;
		// }
		// }
		// tutors.add(xmkTutorMap);
		// }
		// }
		// map.clear();//清空临时map
		// String isopen =propertiesUtil.getValue("isOpen",
		// "conf_xmer.properties") ;
		// map.put("isopen", isopen);//是否打开跳过开关
		// map.put("tutors", tutors);
		// MapResponse response = new MapResponse(ResponseCode.SUCCESS,"成功");
		// response.setResponse(map);
		// return response;
		// }catch(Exception e){
		// e.printStackTrace();
		// log.info("查询导师列表异常");
		// return new BaseResponse(ResponseCode.FAILURE,"查询导师列表异常");
		// }
		// }else{
		// 按照项目总监的要求伪造的导师数据
		List<Map<Object, Object>> wzlist = new ArrayList<Map<Object, Object>>();
		if (xmkTutorRequest.getPage() != null && xmkTutorRequest.getPage().intValue() == 1) {
			for (String str : list) {
				JSONObject json = new JSONObject();
				Map<Object, Object> wzMap = new HashMap<Object, Object>();
				wzMap.put("uid", json.parseObject(str).getString("uid").replace("wz000", ""));// 伪造用户id
				wzMap.put("name", json.parseObject(str).getString("name"));// 伪造用户名称
				wzMap.put("avatar", json.parseObject(str).getString("avatar"));// 伪造用户头像
				wzMap.put("rownum", json.parseObject(str).getString("rownum"));// 伪造序列号
				wzMap.put("saasAmount", json.parseObject(str).getString("saasAmount"));// 伪造伙伴的软件销售金额
				wzMap.put("totalflow", json.parseObject(str).getString("totalflow"));// 伪造伙伴的总流水
				wzMap.put("partnernum", json.parseObject(str).getString("partnernums"));// 伪造伙伴的伙伴数量
				wzMap.put("soldnums", json.parseObject(str).getString("signnums"));// 伪造伙伴的签约商铺数量
				wzMap.put("nums", json.parseObject(str).getString("nums"));// 伪造套餐额定数量
				wzMap.put("stock", json.parseObject(str).getString("stock"));// 伪造剩余套餐数量
				wzMap.put("income", json.parseObject(str).getString("income"));// 伪造伙伴的总收入
				wzMap.put("wechatno", json.parseObject(str).getString("wechatno"));
				wzMap.put("phone", json.parseObject(str).getString("phone"));
				wzMap.put("achieve", json.parseObject(str).getString("achieve"));
				wzMap.put("wechatno", json.parseObject(str).getString("wechatno"));
				wzlist.add(wzMap);
			}
		}

		Map<Object, Object> wzResultMap = new HashMap<Object, Object>();
		wzResultMap.put("tutors", wzlist);
		String isopen = "0";
		try {
			isopen = propertiesUtil.getValue("isOpen", "conf_xmer.properties");
		} catch (IOException e) {
			e.printStackTrace();
		}
		wzResultMap.put("isopen", isopen);// 是否打开跳过开关
		MapResponse response = new MapResponse(ResponseCode.SUCCESS, "成功");
		response.setResponse(wzResultMap);
		return response;
		// }
	}
}
