package com.xmniao.xmn.core.live.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import com.xmniao.xmn.core.common.ObjResponse;
import com.xmniao.xmn.core.common.service.CommonService;
import com.xmniao.xmn.core.kscloud.entity.KSLiveEntity;
import com.xmniao.xmn.core.kscloud.service.KSCloudService;
import com.xmniao.xmn.core.util.*;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.live.ContributeListRequest;
import com.xmniao.xmn.core.common.request.live.LiveHomeRequest;
import com.xmniao.xmn.core.common.request.live.RecommendLiveRecordListRequest;
import com.xmniao.xmn.core.integral.dao.IntegralMallDao;
import com.xmniao.xmn.core.integral.entity.BannerEntity;
import com.xmniao.xmn.core.live.dao.AnchorLiveRecordDao;
import com.xmniao.xmn.core.live.dao.LiveRoomDao;
import com.xmniao.xmn.core.live.dao.LiveUserDao;
import com.xmniao.xmn.core.live.dao.PersonalCenterDao;
import com.xmniao.xmn.core.live.entity.LiveRecordInfo;
import com.xmniao.xmn.core.live.entity.LiverInfo;
import com.xmniao.xmn.core.verification.dao.BillDao;
import com.xmniao.xmn.core.verification.dao.UrsDao;
import com.xmniao.xmn.core.verification.service.UrsService;
import com.xmniao.xmn.core.xmer.dao.SellerDao;
import com.xmniao.xmn.core.xmer.service.SellerService;
import sun.misc.Version;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：LiveHomeService   
* 类描述：   直播首页service处理类
* 创建人：yezhiyong   
* 创建时间：2016年12月1日 下午3:30:39   
* @version    
*
 */
@Service
public class LiveHomeService {
	
	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(LiveHomeService.class);
	
	/**
	 * 注入sessionTokenService
	 */
	@Autowired
	private SessionTokenService sessionTokenService;
	
	/**
	 * 注入图片服务器地址
	 */
	@Autowired
	private String fileUrl;
	
	/**
	 * 注入integralMallDao
	 */
	@Autowired
	private IntegralMallDao integralMallDao;
	
	/**
	 * 注入本地服务地址
	 */
	@Autowired
	private String localDomain;
	
	/**
	 * 注入anchorLiveRecordDao
	 */
	@Autowired
	private AnchorLiveRecordDao anchorLiveRecordDao;
	
	/**
	 * 注入liveUserDao
	 */
	@Autowired
	private LiveUserDao liveUserDao;
	
	/**
	 * 注入propertiesUtil
	 */
	@Autowired
	private PropertiesUtil propertiesUtil;
	
	/**
	 * 注入sellerService
	 */
	@Autowired
	private SellerService sellerService;
	
	/**
	 * 注入ursService
	 */
	@Autowired
	private UrsService ursService;
	
	/**
	 * 注入sellerDao
	 */
	@Autowired
	private SellerDao sellerDao;
	
	/**
	 * 注入personalCenterDao
	 */
	@Autowired
	private PersonalCenterDao personalCenterDao;
	
	/**
	 * 注入personalCenterDao
	 */
	@Autowired
	private AnchorPersonService anchorPersonService;
	
	/**
	 * 注入liveRobotService
	 */
	@Autowired
	private LiveRobotService liveRobotService;
	
	/**
	 * 注入personalcenterDao
	 */
	@Autowired
	private PersonalCenterDao personalcenterDao;
	
	/**
	 * 注入billDao
	 */
	@Autowired
	private BillDao billDao;
	
	/**
	 * 注入ursDao
	 */
	@Autowired
	private UrsDao ursDao;
	
	/**
	 * 注入liveRoomDao
	 */
	@Autowired
	private LiveRoomDao liveRoomDao;
	@Autowired
	private AnchorSignTypeService anchorSignTypeService;
	@Autowired
	private CommonService commonService;

	@Autowired
	private KSCloudService ksCloudService;
	
	/**
	 * 
	* @Title: queryLiveHome
	* @Description: 获取直播首页信息(banner图,预告/预售,直播)
	* @return Object    返回类型
	* @throws
	 */
	public Object queryLiveHome(LiveHomeRequest liveHomeRequest) {
		try {
			//判断用户是否登录过,登录过则获取uid
			String uid = "";
			Map<Object, Object> liverMap = null;
			if (liveHomeRequest != null && StringUtils.isNotEmpty(liveHomeRequest.getSessiontoken())) {
				uid = sessionTokenService.getStringForValue(liveHomeRequest.getSessiontoken()) + "";
				//如果token过期,则当没有登录处理
				if (StringUtils.isNotEmpty(uid) && !"null".equals(uid)) {
					liverMap = liveUserDao.queryLiverInfoByUid(Integer.parseInt(uid));
				}
			}
			
			//纬度
			Double latitude = liveHomeRequest.getLatitude();
			//经度
			Double longitude = liveHomeRequest.getLongitude();
			
			//结果集
			Map<Object, Object> resultMap = new HashMap<>();
			
			//组装参数
			Map<Object,Object> paramMap = new HashMap<Object,Object>();
			paramMap.put("position", 4);//banner位置信息，1 附近美食，2 积分商城 3寻蜜客主页 4直播首页
			paramMap.put("status", 1);//上线状态 0.待上线，1.已上线，2.已下线
			List<BannerEntity> bannerList = integralMallDao.queryBannerList(paramMap);
			
			if (bannerList != null && bannerList.size() > 0) {
				//解析直播首页的banner图数据
				List<Map<Object, Object>> bannerListResult = this.getBanner(bannerList,1);
//				commonService.checkIOSManorVersion(liveHomeRequest, 9, bannerListResult);
				//放入结果集
				resultMap.put("bannerList", bannerListResult);
				
			}else {
				//如查询直播首页banner图没有或者是异常，则给定默认的图片
				resultMap.put("defultBannerImg", localDomain + "/img/liveHomeBanner.png");
				
			}
			
			//组装参数
			paramMap.clear();
			//预告列表
			paramMap.put("recommended", 1);
			paramMap.put("zhiboType", 0);
			paramMap.put("page", 1);
			paramMap.put("limit", Integer.parseInt(propertiesUtil.getValue("preLiveRecordNums", "conf_common.properties")));
			if (latitude != null && longitude != null && latitude != 0 && longitude != 0) {
				paramMap.put("longitude", longitude);
				paramMap.put("latitude", latitude);
			}
			
			List<Map<Object, Object>> preLiveRecordList = null;
			//查询精彩预告列表(8条,规则:有预售的预告优先,优已关注的主播)
			if (liverMap != null && liverMap.get("id") != null) {
				if (Integer.parseInt(liverMap.get("utype").toString()) == 1) {
					//查询15分钟内的最新一条自定义直播记录信息
					Map<Object, Object> customLiveRecordMap = anchorLiveRecordDao.queryCustomLiveRecordByAnchorId(Integer.parseInt(liverMap.get("id").toString()));
					if (customLiveRecordMap != null && customLiveRecordMap.size() > 0) {
						//是否存在自定义直播记录信息  0否 1是
						resultMap.put("existCustomLiveRecord", 1);
						//自定义直播记录id
						resultMap.put("liveRecordId", customLiveRecordMap.get("id"));
						//自定义标题
						resultMap.put("title", customLiveRecordMap.get("zhibo_title")==null?"":customLiveRecordMap.get("zhibo_title"));
						//自定义商家名称
						resultMap.put("sellerName", customLiveRecordMap.get("sellername")==null?"":customLiveRecordMap.get("sellername"));
						//自定义商家地址
						resultMap.put("address", customLiveRecordMap.get("zhibo_address")==null?"":customLiveRecordMap.get("zhibo_address"));
						
					}else {
						resultMap.put("existCustomLiveRecord", 0);
						
					}
				}
				
				//直播用户id,用于查询关注的主播
				paramMap.put("liverId", Integer.parseInt(liverMap.get("id").toString()));
				//手机号码,排除过滤测试账号
				paramMap.put("phone", liverMap.get("phone"));
				//获取登录的用户预告列表
				preLiveRecordList = anchorLiveRecordDao.queryLiveRecordList(paramMap);
				
			}else {
				//获取没有登录的用户预告列表
				preLiveRecordList = anchorLiveRecordDao.queryLiveRecordList(paramMap);
				
			}
			
			//存储预告结果集
			List<Map<Object, Object>> preLiveRecordResultList = new ArrayList<>();
			
			if (preLiveRecordList != null && preLiveRecordList.size() > 0) {
				//调整预告列表返回字段
				preLiveRecordResultList = this.updateLiveRecordList(preLiveRecordList, liverMap);
			}
			
			//存入结果集
			resultMap.put("preLiveRecordList", preLiveRecordResultList);

			List<Object> testIds = new ArrayList<Object>();
			try {
				//排除UID 为 测试的内部账号
				List<Map<Object, Object>> liverInfoList = liveUserDao.queryLiverInfosByIsinsideAndSignType();
				if (liverInfoList.size()>0) {
					for (Map<Object, Object> map : liverInfoList) {
						testIds.add(map.get("id"));
					}
				}
			} catch (Exception e) {
				log.warn("直播首页排除内部账号，查询异常");
			}
			int zhiboGiftRange = 250000;
			try{
				//从配置文件中获取当场直播区间
				zhiboGiftRange = Integer.parseInt(propertiesUtil.getValue("zhiboGiftRange", "conf_common.properties"));
			}catch(Exception e){
				e.printStackTrace();
				log.info("获取直播打赏鸟币最大区间配置失败");
			}
			paramMap.put("zhiboGiftRange", zhiboGiftRange);
			paramMap.put("testIds", testIds);  //排除内部账号直播
			//直播列表(6条,规则:有预售的直播优先,优已关注的主播)
			paramMap.put("zhiboType", 1);
			paramMap.put("limit", Integer.parseInt(propertiesUtil.getValue("currentLiveRecordNums", "conf_common.properties")));
			List<Map<Object, Object>> currentLiveRecordList = anchorLiveRecordDao.queryLiveRecordList(paramMap);
			
			//存储直播结果集
			List<Map<Object, Object>> currentLiveRecordResultList = new ArrayList<>();
			
			if (currentLiveRecordList != null && currentLiveRecordList.size() > 0) {
				//调整直播列表返回字段
				currentLiveRecordResultList = this.updateLiveRecordList(currentLiveRecordList, liverMap);
			}
			
			//存入结果集
			resultMap.put("currentLiveRecordList", currentLiveRecordResultList);

			paramMap.clear();
			paramMap.put("testIds", testIds);
			//查询所有正在直播的记录总数
			Integer currentLiveRecordAllSum = anchorLiveRecordDao.queryCurrentLiveRecordAllSum(paramMap);
			
			//正在直播总数
			resultMap.put("currentLiveRecordAllSum", currentLiveRecordAllSum);
			
			//响应
			MapResponse response = new MapResponse(ResponseCode.SUCCESS, "获取直播首页信息成功");
			response.setResponse(resultMap);
			return response;
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("获取直播首页信息(banner图,预告/预售,直播)失败,错误信息如下:" + e.getMessage());
			return new BaseResponse(ResponseCode.FAILURE, "获取直播首页信息失败");
		}
	}

	private void addLivePlatform(String uid, Map<Object, Object> result, Map<Object, Object> anchorMap) {
		try {
			result.put("livePlatform", 1); //直播使用平台  1 腾讯直播  2 金山云直播
			result.put("liveRtmpUrl", "");  //拉流地址
			if (uid != null && !uid.equals("") && anchorMap != null) {
				try {
					KSLiveEntity entity = ksCloudService.createKSLPullUrl(uid, anchorMap);
					if (entity != null) {
						result.put("livePlatform", entity.getPlatform()); //直播使用平台  1 腾讯直播  2 金山云直播
						result.put("liveRtmpUrl", entity.getUrl());  //拉流地址
					}
				} catch (UnsupportedEncodingException e) {
					log.warn("生成金山云拉流失败", e);
				}
			}
		} catch (Exception e) {
			log.warn("添加直播平台失败", e);
		}
	}


	public List<Map<Object, Object>> updateLiveRecordList(List<Map<Object, Object>> liveRecordList,Map<Object, Object> liverMap) throws IOException{
		return updateLiveRecordList(liveRecordList, liverMap, 360);
	}

	/**
	 * @throws IOException
	 *
	* @Title: updateLiveRecordList
	* @Description: 调整预告/直播/回放列表返回字段
	* @return List<Map<Object,Object>>    返回类型
	* @throws
	 */
	public List<Map<Object, Object>> updateLiveRecordList(List<Map<Object, Object>> liveRecordList,Map<Object, Object> liverMap, Integer apiVersion) throws IOException {
		Integer lastVersion = 360;
		// 361版本，签约类型，大赛主播，签约主播，美食体验官
		boolean isV361 = apiVersion > lastVersion;
		//存储预告结果集
		List<Map<Object, Object>> resultList = new ArrayList<>();
		//查看想看的人数
		List<Map<Object, Object>> viewCountList = anchorLiveRecordDao.queryViewerCount();
		//存储所有主播id
		List<String> anchorIds = new ArrayList<>();
		//店铺set集合,用于去重店铺
		Set<String> sellerSet = new HashSet<>();
		//存储所有主播id
		List<Integer> liveRecordIds = new ArrayList<>();
		
		for (Map<Object, Object> liveRecordMap : liveRecordList) {
			//存储记录id，用于批量查询直播内容标签信息
			liveRecordIds.add(Integer.parseInt(liveRecordMap.get("id").toString()));
			//存储主播id
			anchorIds.add(liveRecordMap.get("anchor_id").toString());
			
			//是否需要查询用户标签
			if (liverMap != null && liverMap.get("uid") != null) {
				//添加店铺,去除没有店铺id的
				if (liveRecordMap.get("sellerid") != null) {
					sellerSet.add(liveRecordMap.get("sellerid").toString());
				}
				
			}
			
			Map<Object, Object> map = new HashMap<>();
			//主播Id
			map.put("anchorId", liveRecordMap.get("anchor_id"));
			//房间编号
			map.put("anchorRoomNo", liveRecordMap.get("anchor_room_no"));
			//观看人数
			map.put("viewCount", liveRecordMap.get("view_count"));
			//预告记录id
			map.put("liveRecordId", liveRecordMap.get("id"));
			//预告封面
			map.put("cover", liveRecordMap.get("zhibo_cover")==null?"":fileUrl + liveRecordMap.get("zhibo_cover").toString());
			//主播昵称
			map.put("nname", liveRecordMap.get("nname")==null?"":liveRecordMap.get("nname").toString());
			//店铺名称
			map.put("sellername", liveRecordMap.get("sellername")==null?"":liveRecordMap.get("sellername").toString());
			//如果有商家别名,使用商家别名
			if (liveRecordMap.get("seller_alias") != null && StringUtils.isNotEmpty(liveRecordMap.get("seller_alias").toString())) {
				map.put("sellerName", liveRecordMap.get("seller_alias").toString());
			}
			//商家id
			map.put("sellerId", liveRecordMap.get("sellerid"));
			//主播头像
			map.put("avatar", liveRecordMap.get("avatar")==null?"":fileUrl + liveRecordMap.get("avatar").toString());
			//开播类型 0 通告开播  1自定义开播
			map.put("liveStartType", liveRecordMap.get("liveStartType"));
			//直播类型
			int zhiboType = Integer.parseInt(liveRecordMap.get("zhibo_type").toString());
			map.put("zhiboType", zhiboType);
			//预售状态 1预售中  0无
			map.put("isSell", 0);
			//如果没有用户标签或者是标签异常,则不显示标签,用户标签
			map.put("tag", "");
			//用户标签标号
			map.put("lable", 0);
			map.put("start_date", liveRecordMap.get("start_date"));
			
			//距离
			if (liveRecordMap.get("distance") != null && StringUtils.isNotEmpty(liveRecordMap.get("distance").toString()) && Double.parseDouble(liveRecordMap.get("distance").toString()) > 0) {
				double distance = Double.parseDouble(liveRecordMap.get("distance").toString());
				String distanceStr = "";
				//格式化距离
				if (distance > 1000) {
					distanceStr = ArithUtil.div(distance, 1000) + "km";
				}else {
					DecimalFormat df = new DecimalFormat("0");
					BigDecimal b = new BigDecimal(distance);
					distanceStr = df.format(b) + "m";
					
					//处理优质标签
					if (liverMap != null && liverMap.get("uid") != null) {
						if (distance < 500) {
							map.put("lable", "4");
						}
					}
				}
				
				map.put("distance", distanceStr);
				
			}
			
			//直播
			if (zhiboType == 1) {
				//计算观看时长
				Long duration = 0L;
				
				//计算本次观看时长
				if (liveRecordMap.get("start_date")!=null) {
					duration = new Date().getTime() - DateUtil.parse(liveRecordMap.get("start_date").toString()).getTime();
				}else {
					duration = 0L;
				}
				
				//判断直播间是否加锁了
				if (liveRecordMap.get("anchor_room_password") != null && StringUtils.isNotEmpty(liveRecordMap.get("anchor_room_password").toString())) {
					map.put("existRoomLock", 1);
				}else {
					map.put("existRoomLock", 0);
				}
				
				//格式化直播时间
				int liveTime = (int) Math.floor(ArithUtil.div(Double.parseDouble(duration + ""), 60000));
				//直播地址
				map.put("liveTime", liveTime);
				map.put("vedioUrl", liveRecordMap.get("vedio_url")==null?"":liveRecordMap.get("vedio_url").toString());
				// 添加默认，下面添加正式的
				addLivePlatform(null, map, null);
			}else if (zhiboType == 0) {
				//预告开播时间
				map.put("liveTime", liveRecordMap.get("plan_start_date")==null?"":DateUtil.format(DateUtil.parse(liveRecordMap.get("plan_start_date").toString()), "yyyy-MM-dd HH:mm"));
				
				if (viewCountList != null && viewCountList.size() > 0) {
					//想看人数
					for (Map<Object, Object> viewCountMap : viewCountList) {
						if (liveRecordMap.get("id").toString().equals(viewCountMap.get("liveRecordId").toString())) {
							//想去人数
							Integer viewCount = viewCountMap.get("viewCount")==null?0:Integer.parseInt(viewCountMap.get("viewCount").toString());
							map.put("viewCount", viewCount);
							break;
						}
					}
				}
				
				if (map.get("viewCount") == null || Integer.parseInt(map.get("viewCount").toString()) < 20) {
					try {
						//添加想去机器人
						String robotShowViewCount = propertiesUtil.getValue("robot_show_view_count", "conf_live.properties");
						List<Map<Object, Object>> robots = liveRobotService.insertRobotLiveFocusShow(map.get("anchorId").toString(), liveRecordMap.get("id").toString(), Integer.parseInt(robotShowViewCount) + new Random().nextInt(100));
						//想去人数 = 机器人想去人数 + 真实想去人数
						map.put("viewCount", robots.size() + (map.get("viewCount") == null?0:Integer.parseInt(map.get("viewCount").toString())));
					} catch (Exception e) {
						log.info("添加想看机器人异常,错误信息如下:" + e.getMessage());
						e.printStackTrace();
					}
				}
				
				//判断是否登录过,如果登录了,则需要返回是否想去状态
				if (liverMap != null && liverMap.get("id") != null) {
					//登录后，获取是否存在想看预告记录
					Map<Object, Object> focusShowMap = new HashMap<>();
					focusShowMap.put("liverId", liverMap.get("id"));
					focusShowMap.put("liveRecordId", liveRecordMap.get("id").toString());
					focusShowMap.put("status", 2);
					Integer focusShowResult = liveUserDao.queryFocusShow(focusShowMap);
					if (focusShowResult == 1) {
						//想去状态  1为已想看  2为未想看
						map.put("viewStatus", 1);
					}else {
						map.put("viewStatus", 2);
					}
				}
				
			}else if (zhiboType == 3) {
				//回放开播时间
				map.put("liveTime", DateUtil.format(DateUtil.parse(liveRecordMap.get("start_date")==null?liveRecordMap.get("plan_start_date").toString():liveRecordMap.get("start_date").toString()), "yyyy-MM-dd HH:mm"));
				//回放地址
				map.put("vedioUrl", liveRecordMap.get("zhibo_playback_url")==null?"":liveRecordMap.get("zhibo_playback_url").toString());
				
			}else {
				//回放开播时间
				map.put("liveTime", DateUtil.format(DateUtil.parse(liveRecordMap.get("start_date")==null?liveRecordMap.get("plan_start_date").toString():liveRecordMap.get("start_date").toString()), "yyyy-MM-dd HH:mm"));
				//视频流地址
				map.put("vedioUrl", "");
			}
			
			//将大于优惠券下架时间的过滤预售消息
			boolean soldOutFlag = true;
			if (liveRecordMap.get("soldOutTime") != null && StringUtils.isNotEmpty(liveRecordMap.get("soldOutTime").toString())) {
				long soldOutTime = DateUtil.parse(liveRecordMap.get("soldOutTime").toString()).getTime();
				long nowTime = new Date().getTime();
				if (nowTime > soldOutTime) {
					soldOutFlag = false;
				}
			}
			
			//标题
			if ((zhiboType == 0 || zhiboType ==1) && soldOutFlag && liveRecordMap.get("ticketMoney") != null && Double.parseDouble(liveRecordMap.get("ticketMoney").toString()) != 0) {
				//预售状态 1预售中  0无
				map.put("isSell", 1);
				//预售标题
				map.put("title", liveRecordMap.get("preSellTitle"));
				//预售库存 为空则:没有预售,为0:则售完 ,大于0:则显示价格
				int preSellStock = Integer.parseInt(liveRecordMap.get("preSellStock").toString());
				map.put("preSellStock", preSellStock);
				if (preSellStock > 0) {
					//预售价格
					map.put("ticketMoney", liveRecordMap.get("ticketMoney")==null?"":"¥" + liveRecordMap.get("ticketMoney").toString());
					
				}else {
					map.put("ticketMoney", "已售罄");
					
				}
				
			}else {
				if (liveRecordMap.get("zhibo_title") != null && StringUtils.isNotEmpty(liveRecordMap.get("zhibo_title").toString())) {
					//预告标题/直播标题
					map.put("title", liveRecordMap.get("zhibo_title"));
				}else {
					//如果没有预告标题/直播标题，则取店铺别名，或者是店铺 
					if (liveRecordMap.get("seller_alias") != null && StringUtils.isNotEmpty(liveRecordMap.get("seller_alias").toString())) {
						map.put("title", liveRecordMap.get("seller_alias").toString());
					}else {
						map.put("title", liveRecordMap.get("sellername")==null?"":liveRecordMap.get("sellername").toString());
					}
				}
				
			}
			
			resultList.add(map);
			
		}
		
		//批量查询主播基本信息
		List<Map<Object,Object>> anchorList = personalcenterDao.queryLiverPersonByListId(anchorIds);
				
		//存储所有店铺id
		List<String> sellerList = new ArrayList<>();
		if (sellerSet.size() > 0) {
			sellerList.addAll(sellerSet);
		}
		
		//批量查询用户标签
		List<Map<Object, Object>> tagList = new ArrayList<>();
		if (liverMap != null && liverMap.get("uid") != null) {
			try {
				//获取用户关于商家标签
				tagList = this.getConsumerTagName(Integer.parseInt(liverMap.get("uid").toString()),sellerList);
			} catch (Exception e) {
				log.info("获取直播用户标签失败,错误信息如下:" + e.getMessage());
			}
		}
		
		//批量查询直播内容标签信息
		List<Map<Object, Object>> liveTagList = liveRoomDao.queryLiveRecordTagConf(liveRecordIds);
		
		//整合主播基本信息,用户标签
		for (Map<Object, Object> resultMap : resultList) {
			///整合主播基本信息
			for (Map<Object, Object> anchorMap : anchorList) {
				if (Integer.parseInt(resultMap.get("anchorId").toString()) == Integer.parseInt(anchorMap.get("id").toString())) {
					//寻蜜鸟uid
					resultMap.put("uid", anchorMap.get("uid"));
					//群组号
					resultMap.put("groupId", anchorMap.get("group_id"));
					//主播性别
					resultMap.put("sex", anchorMap.get("sex")==null?0:anchorMap.get("sex"));
					//主播登录账号
					resultMap.put("zbPhone", anchorMap.get("uname")==null?0:anchorMap.get("uname"));
					if (isV361) {
						Integer signType = anchorSignTypeService.getSignType(anchorMap);
						resultMap.put("signType", signType);
					} else {
						// 是否签约主播 1 签约 0 未签约
						resultMap.put("signType", anchorMap.get("signType")==null?0:anchorMap.get("signType"));
					}
					// 添加直播平台
					addLivePlatform(anchorMap.get("uid") == null ? null : anchorMap.get("uid").toString(), resultMap, anchorMap);
					break;
				}
			}
			
			//整合用户标签
			if (tagList != null && tagList.size() > 0) {
				for (Map<Object, Object> tagMap : tagList) {
					if (resultMap.get("sellerId") != null && Integer.parseInt(resultMap.get("sellerId").toString()) == Integer.parseInt(tagMap.get("sellerId").toString())) {
						
						if (Integer.parseInt(resultMap.get("lable").toString()) == 0 || Integer.parseInt(resultMap.get("lable").toString()) > Integer.parseInt(tagMap.get("lable").toString())) {
							//消费过,收藏过,浏览过,优质的标签
							resultMap.put("tag", tagMap.get("tag"));
							resultMap.put("lable", tagMap.get("lable"));
						}else {
							/**
							 * 处理附近的标签，如果存在附近的标签,并且没有消费过,收藏过,浏览过标签,则取附近的标签
							 * 获取标签列表
							 */
							String consumerTagListStr = propertiesUtil.getValue("consumerTagList", "conf_common.properties");
							
							//解析标签
							JSONArray jsonArr = JSONObject.parseArray(consumerTagListStr);
							
							//根据标签标号,获取标签名称
							for (int i = 0; i < jsonArr.size(); i++) {
								int type = Integer.parseInt(JSONObject.parseObject(jsonArr.get(i).toString()).get("type").toString());
								if (type == Integer.parseInt(resultMap.get("lable").toString())) {
									String consumerTagName = JSONObject.parseObject(jsonArr.get(i).toString()).get("title").toString();
									resultMap.put("tag", consumerTagName);
									resultMap.put("lable", tagMap.get("lable"));
									break;
								}
							}
							
						}
						break;
					}
				}
				
			}
			
			//整合直播内容标签信息
			if (liveTagList != null && liveTagList.size() > 0) {
				for (Map<Object, Object> liveTagMap : liveTagList) {
					if (Integer.parseInt(resultMap.get("liveRecordId").toString()) == Integer.parseInt(liveTagMap.get("liveRecordId").toString())) {
						if (resultMap.get("liveTagNameList") == null) {
							//添加第一个直播内容标签
							List<String> liveTagNameList = new ArrayList<>();
							liveTagNameList.add(liveTagMap.get("tagName").toString());
							//添加至返回结果集
							resultMap.put("liveTagNameList", liveTagNameList);
							
						}else {
							//添加第二个直播内容标签
							String jsonStr = JSONObject.toJSONString(resultMap.get("liveTagNameList"));
							List<String> liveTagNameList = JSONObject.parseArray(jsonStr, String.class);
							liveTagNameList.add(liveTagMap.get("tagName").toString());
							//添加至返回结果集
							resultMap.put("liveTagNameList", liveTagNameList);
							break;
						}
					}
				}
			}
			
		}
		
		return resultList;
	}
	
	/**
	 * 
	* @Title: getConsumerTagName
	* @Description: 批量查询用户标签  消费过 lable = 1     收藏 lable = 2          浏览过 lable = 3       存在商家活动 lable = 5
	* @return List<Map<Object,Object>>    返回类型
	* @throws
	 */
	public List<Map<Object, Object>> getConsumerTagName(int uid,List<String> sellerList) throws Exception{
		//结果集
		List<Map<Object, Object>> tagList = new ArrayList<>();

		if (sellerList.size() > 0) {
			Map<Object, Object> paramMap = new HashMap<>();
			paramMap.put("uid", uid);
			paramMap.put("sellerIds", sellerList);
			
			//用于排除店铺id
			List<String> removeSellers = new ArrayList<>();
			
			//批量查询用户是否在这些商家消费过
			List<Map<Object, Object>> billList = billDao.queryBillCountBySellerIdsAndUid(paramMap);
			
			if (billList != null && billList.size() > 0) {
				for (Map<Object, Object> billMap : billList) {
					//已经消费过
					if (billMap.get("isConsumer") != null && Integer.parseInt(billMap.get("isConsumer").toString()) > 0) {
						//已消费过，则加入排除店铺中
						removeSellers.add(billMap.get("sellerId").toString());
						//给已消费过店铺指定标签标号
						Map<Object, Object> sellerMap = new HashMap<>();
						sellerMap.put("lable", 1);
						sellerMap.put("sellerId", billMap.get("sellerId"));
						//添加至结果集中
						tagList.add(sellerMap);
					}
				}
				
			}
			
			if (removeSellers.size() > 0) {
				sellerList.removeAll(removeSellers);
				//重置删除店铺集合
				removeSellers = new ArrayList<>();
			}
			
			//批量查询用户是否收藏过这些店铺
			if (sellerList.size() > 0) {
				paramMap.put("sellerIds", sellerList);
				//批量查询是否收藏过这些店铺
				List<Map<Object, Object>> collectList = ursDao.queryCollectCountBySellerIdsAndUid(paramMap);
				
				if (collectList != null && collectList.size() > 0) {
					for (Map<Object, Object> collectMap : collectList) {
						//已收藏
						if (collectMap.get("isCollect") != null && Integer.parseInt(collectMap.get("isCollect").toString()) > 0) {
							//已收藏，则加入排除店铺中
							removeSellers.add(collectMap.get("sellerId").toString());
							//给已收藏过店铺指定标签标号
							Map<Object, Object> sellerMap = new HashMap<>();
							sellerMap.put("lable", 2);
							sellerMap.put("sellerId", collectMap.get("sellerId"));
							//添加至结果集中
							tagList.add(sellerMap);
						}
						
					}
				}
				
			}
			
			if (removeSellers.size() > 0) {
				sellerList.removeAll(removeSellers);
				//重置删除店铺集合
				removeSellers = new ArrayList<>();
			}
			
			//批量查询是否浏览过这些店铺
			if (sellerList.size() > 0) {
				paramMap.put("sellerIds", sellerList);
				//批量查询是否浏览过这些店铺
				List<Map<Object, Object>> browsedList = ursDao.queryBrowsedCountBySellerIdsAndUid(paramMap);
				
				if (browsedList != null && browsedList.size() > 0) {
					for (Map<Object, Object> browsedMap : browsedList) {
						//已浏览
						if (browsedMap.get("isBrowsed") != null && Integer.parseInt(browsedMap.get("isBrowsed").toString()) > 0) {
							//浏览，则加入排除店铺中
							removeSellers.add(browsedMap.get("sellerId").toString());
							//给已浏览过店铺指定标签标号
							Map<Object, Object> sellerMap = new HashMap<>();
							sellerMap.put("lable", 3);
							sellerMap.put("sellerId", browsedMap.get("sellerId"));
							//添加至结果集中
							tagList.add(sellerMap);
						}
						
					}
				}
				
			}
			
			if (removeSellers.size() > 0) {
				sellerList.removeAll(removeSellers);
				//重置删除店铺集合
				removeSellers = new ArrayList<>();
			}
			
			//批量查询这些商家是否存在商家活动
			if (sellerList.size() > 0) {
				for (String sellerId : sellerList) {
					//查询此商家是否存在活动
					List<Map<String, Object>> activityList = sellerDao.queryActivityList(Integer.parseInt(sellerId));
					if (activityList != null && activityList.size() > 0 ) {
						//给存在商家活动的指定标签
						Map<Object, Object> sellerMap = new HashMap<>();
						sellerMap.put("lable", 5);
						sellerMap.put("sellerId", sellerId);
						//添加至结果集中
						tagList.add(sellerMap);
					}
					
				}
				
			}
			
			//获取标签列表
			String consumerTagListStr = propertiesUtil.getValue("consumerTagList", "conf_common.properties");
			
			//解析标签
			JSONArray jsonArr = JSONObject.parseArray(consumerTagListStr);
			
			//根据标签标号,获取标签名称
			for (Map<Object, Object> tagMap : tagList) {
				for (int i = 0; i < jsonArr.size(); i++) {
					int type = Integer.parseInt(JSONObject.parseObject(jsonArr.get(i).toString()).get("type").toString());
					if (type == Integer.parseInt(tagMap.get("lable").toString())) {
						String consumerTagName = JSONObject.parseObject(jsonArr.get(i).toString()).get("title").toString();
						tagMap.put("tag", consumerTagName);
						break;
					}
				}
			}
			
		}
		
		return tagList;
		
	}
	
	/**
	 * @throws IOException 
	 * 
	* @Title: getConsumerTagName
	* @Description: 获取用户关于商家标签
	* @return String    返回类型
	* @throws
	 */
	public Map<Object, Object> getConsumerTagName(int sellerid,int uid,String distance) throws IOException{
		Map<Object, Object> tagMap = new HashMap<>();
		//标签名称
		String consumerTagName = "";
		//标签号
		int lable = 0;
		try {
			//是否消费过
			if(sellerService.billCountBySelleridAndUid(sellerid,uid) > 0){
				lable = 1;
				
			}else{	
				//是否收藏
				if(ursService.isCollectSeller(uid,sellerid) > 0){
					lable = 2;
					
				}else{	
					//是否浏览
					if(ursService.queryBrowsedCountByUidAndSellerid(uid,sellerid) > 0){
						lable = 3;
						
					}else {
						//与我距离是否低于500m 
						if (StringUtils.isNotEmpty(distance) && Double.parseDouble(distance) < 500) {
							lable = 4;
						}else {
							//查询此商家是否存在活动
							List<Map<String, Object>> activityList = sellerDao.queryActivityList(sellerid);
							if (activityList != null && activityList.size() > 0 ) {
								lable = 5;
							}
						}
					}
					
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//获取标签列表
		String consumerTagListStr = propertiesUtil.getValue("consumerTagList", "conf_common.properties");
		
		//解析标签
		JSONArray jsonArr = JSONObject.parseArray(consumerTagListStr);
		for (int i = 0; i < jsonArr.size(); i++) {
			int type = Integer.parseInt(JSONObject.parseObject(jsonArr.get(i).toString()).get("type").toString());
			if (lable == type) {
				consumerTagName = JSONObject.parseObject(jsonArr.get(i).toString()).get("title").toString();
				break;
			}
		}
		
		//返回标签
		tagMap.put("lable", lable);
		tagMap.put("tag", consumerTagName);
		return tagMap;
		
	}
	
	/**
	 * 
	* @Title: getBanner
	* @Description: 解析banner数据,getType=1为所有banner图，getType=2为随机抽取一条banner信息
	* @return List<Map<Object,Object>>    返回类型
	* @throws
	 */
	public List<Map<Object,Object>> getBanner(List<BannerEntity> bannerList,int getType){
		//存储banner图的集合map
		List<Map<Object,Object>> resultList = new ArrayList<Map<Object,Object>>();
		try{
			//随机抽取的banner信息
			if (getType == 2 && bannerList.size() > 1) {
				int random = new Random().nextInt(bannerList.size());
				BannerEntity bannerEntity = bannerList.get(random);
				bannerList.clear();
				bannerList.add(bannerEntity);
			}
			
			//解析获取banner图数据
			for(BannerEntity banner : bannerList){
				Map<Object,Object> map  = new HashMap<Object,Object>();
				map.put("id", banner.getId());//图片id
				map.put("bannerStyle", banner.getBanner_style()==null?0:banner.getBanner_style());//展示风格。0图片横排一格，1图片横排两格
				map.put("sort", banner.getSort()==null?0:banner.getSort());//排序，数值越大，越优先展示
				String obj_json = banner.getObj_json();
				String contents = Base64.getFromBase64(obj_json);//解密
				List<Map<Object,Object>> picList = new ArrayList<Map<Object,Object>>();//用于存储图片集合
				JSONArray arr = JSON.parseArray(contents);
				
				//随机获取一张banner图
				Integer bannerRandom = null;
				if (getType == 2 && arr.size() > 1 ) {
					bannerRandom = new Random().nextInt(2);
				}
				for(int i=0; i<arr.size(); i++){
					if (bannerRandom != null && bannerRandom != i) {
						continue;
					}
					Map<Object,Object> picMap = new HashMap<Object,Object>();
					JSONObject json = JSON.parseObject(arr.get(i).toString());
					String url = json.getString("pic_url").toString();//图片地址
					//图片类型 3外部链接 9直播间 10预告详情 11不跳转
					Integer type = json.getInteger("type");
					String content = json.getString("content").toString();//描述
					Integer sort = json.getInteger("sort");//图片排序
					Integer logRequired = json.getInteger("logRequired");//图片是否需要登录
					
					if (type == 9) {
						// TODO
						Map<Object,Object> tmpPicMap = getZhiboBanner(content);
						picMap.putAll(tmpPicMap);
					}else if (type == 10) {
						//返回跳转预告详情所需参数
						picMap.put("liveRecordId", content);
						
					}
					picMap.put("url", fileUrl+url);
					picMap.put("type", type);
					picMap.put("content", content);
					picMap.put("sort", sort);
					if(sort==null){
						picMap.put("sort", 0);
					}
					if (logRequired == null) {
						picMap.put("logRequired", 0);
					}else {
						picMap.put("logRequired", logRequired);
					}
					picList.add(picMap);
				}
				
				if (getType == 1) {
					Collections.sort(picList, new Comparator<Map<Object, Object>>(){
						public int compare(Map<Object, Object> arg0, Map<Object, Object> arg1) {
							return arg1.get("sort").toString().compareTo(arg0.get("sort").toString());
						}
					});
				}
				
				map.put("bannerList", picList);
				resultList.add(map);
			}
			
			if (getType == 1) {
				//排序
				Collections.sort(resultList, new Comparator<Map<Object, Object>>(){
					public int compare(Map<Object, Object> arg0, Map<Object, Object> arg1) {
						return arg1.get("sort").toString().compareTo(arg0.get("sort").toString());
					}
				});
			}
			return resultList;
			
		}catch(Exception e){
			e.printStackTrace();
			log.info("解析banner图异常");
			return resultList;
		}
	}

	// 处理直播类型banner
	public Map<Object, Object> getZhiboBanner(String content) {
		Map<Object, Object> picMap = new HashMap<Object, Object>();
		//返回跳转直播间所需参数
		picMap.put("liveRecordId", content);
		//是否内部异常  0 否  1是  默认0
		picMap.put("isError", 0);

		try {
			Map<Object, Object> params = new HashMap<>();
			params.put("id", content);
			//查询直播记录信息
			LiveRecordInfo liveRecordInfo = anchorLiveRecordDao.queryAnchorLiveRecordById(params);
			if (liveRecordInfo == null) {
				return picMap;
			}
			//房间编号
			picMap.put("anchorRoomNo", liveRecordInfo.getAnchor_room_no());
			//主播id
			picMap.put("anchorId", liveRecordInfo.getAnchor_id());
			//开播类型 0通告开播  1自定义开播
			picMap.put("liveStartType", liveRecordInfo.getLive_start_type());

			//查询主播信息
			Map<Object, Object> liverMap = liveUserDao.queryLiverInfoById(liveRecordInfo.getAnchor_id().intValue());
			//主播手机号码
			picMap.put("zbPhone", liverMap.get("uname"));
			//群组号
			picMap.put("groupId", liverMap.get("group_id"));
		} catch (Exception e) {
			e.printStackTrace();
			log.info("直播首页banner图跳转直播间异常,直播记录id=" + content + ",错误信息如下:" + e.getMessage());
			picMap.put("isError", 1);
		}
		return picMap;
	}


	/**
	 * 
	* @Title: queryLiveHomeRecommend
	* @Description: 查询直播首页下(热门回放,热门主播,守护榜,精彩时刻)
	* @return Object    返回类型
	* @throws
	 */
	public Object queryLiveHomeRecommend(LiveHomeRequest liveHomeRequest) {
		try {
			//判断用户是否登录过,登录过则获取uid
			String uid = "";
			Map<Object, Object> liverMap = null;
			if (liveHomeRequest != null && StringUtils.isNotEmpty(liveHomeRequest.getSessiontoken())) {
				uid = sessionTokenService.getStringForValue(liveHomeRequest.getSessiontoken()) + "";
				//如果token过期,则当没有登录处理
				if (StringUtils.isNotEmpty(uid) && !"null".equals(uid)) {
					liverMap = liveUserDao.queryLiverInfoByUid(Integer.parseInt(uid));
				}
			}
			//纬度
			Double latitude = liveHomeRequest.getLatitude();
			//经度
			Double longitude = liveHomeRequest.getLongitude();
			
			//结果集
			Map<Object, Object> resultMap = new HashMap<>();
			
			//组装参数
			Map<Object, Object> paramMap = new HashMap<>();
			paramMap.put("page", 1);
			paramMap.put("recommended", 1);
			//热门回放 type = 1 热门回放   type = 2 精彩时刻回放
			paramMap.put("type", 1);
			paramMap.put("limit", Integer.parseInt(propertiesUtil.getValue("topicPlayBackRecordNums", "conf_common.properties")));
			if (latitude != null && longitude != null && latitude != 0 && longitude != 0) {
				paramMap.put("longitude", longitude);
				paramMap.put("latitude", latitude);
			}
			
			if (liverMap != null && liverMap.get("phone") != null) {
				//手机号码,排除过滤测试账号
				paramMap.put("phone", liverMap.get("phone"));
			}
			
			//查询热门回放列表(4条)
			List<Map<Object, Object>> topicPlayBackRecordList = anchorLiveRecordDao.queryPlayBackRecord(paramMap);
			
			if (topicPlayBackRecordList != null && topicPlayBackRecordList.size() > 0) {
				//返回调整后的回放列表数据
				resultMap.put("topicPlayBackRecordList", this.updateLiveRecordList(topicPlayBackRecordList, liverMap));
				
			}else {
				//没有回放记录放回空集合
				resultMap.put("topicPlayBackRecordList", new ArrayList<>());
				
			}
			
			//查询4位热门主播
			paramMap.put("limit", Integer.parseInt(propertiesUtil.getValue("topicAnchorNums", "conf_common.properties")));
			
			 //查询排除内部测试的账号
			List<Integer> anchorList = new ArrayList<>();
			//排除UID 为 测试的内部账号
			List<LiverInfo> liverInfoList = liveUserDao.queryLiverInfosByIsinside();
			if (liverInfoList != null && liverInfoList.size() > 0 ) {
				for (int i = 0; i < liverInfoList.size(); i++) {
					anchorList.add(liverInfoList.get(i).getId().intValue());
				}
			}
			
			if (anchorList.size() > 0) {
				paramMap.put("anchorList", anchorList);
			}
			//查询4位热门主播
			List<Map<Object, Object>> topicAnchorList = anchorLiveRecordDao.queryTopicAnchor(paramMap);
			
			if (topicAnchorList != null && topicAnchorList.size() > 0) {
				//4位热门主播的主播id
				paramMap.put("anchorIds", topicAnchorList);
				List<Map<Object, Object>> liveRecordList = anchorLiveRecordDao.queryTopicAnchorLiveRecord(paramMap);
				
				if (liveRecordList != null && liveRecordList.size() > 0) {
					//存储排序后的结果集
					List<Map<Object, Object>> topicAnchorLiveRecordResultList = new ArrayList<>();
					
					//调整热门主播的直播/回放记录信息
					List<Map<Object, Object>> topicAnchorLiveRecordList = this.updateLiveRecordList(liveRecordList, liverMap);
					
					//添加热门直播记录数以及是否关注状态
					for (Map<Object, Object> topicAnchorMap : topicAnchorList) {
						for (Map<Object, Object> topicAnchorLiveRecordMap : topicAnchorLiveRecordList) {
							if (Integer.parseInt(topicAnchorLiveRecordMap.get("anchorId").toString()) == Integer.parseInt(topicAnchorMap.get("anchorId").toString())) {
								//添加热门直播记录数
								topicAnchorLiveRecordMap.put("liveRecordNums", topicAnchorMap.get("count"));
								
								//若登录了，则需要判断是否需要关注此主播状态
								topicAnchorLiveRecordMap.put("focusStatus", 0);
								if (liverMap != null && liverMap.get("id") != null) {
									Map<Object, Object> map = new HashMap<>();
									map.put("liverStrId", Integer.parseInt(liverMap.get("id").toString()));
									map.put("liverEndId", Integer.parseInt(topicAnchorLiveRecordMap.get("anchorId").toString()));
									Integer count = liveUserDao.queryFocusAnchor(map);
									
									if (count != null && count > 0) {
										topicAnchorLiveRecordMap.put("focusStatus", 1);
										
									}
								}
								
								topicAnchorLiveRecordResultList.add(topicAnchorLiveRecordMap);
								break;
							}
							
						}
						
					}
					
					//返回调整后的热门主播直播/回放列表数据
					resultMap.put("topicAnchorLiveRecordList", topicAnchorLiveRecordResultList);
				}
				
			}else {
				//没有热门主播，放回空集合
				resultMap.put("topicPlayBackRecordList", new ArrayList<>());
			}
			
			//返回主播守护榜
			try {
				ContributeListRequest contributeListRequest = new ContributeListRequest();
				contributeListRequest.setPage(1);
				contributeListRequest.setPageSize(Integer.parseInt(propertiesUtil.getValue("liveAnchorRankingRecord", "conf_common.properties")));
				//守护榜
				MapResponse mapResponse = (MapResponse)anchorPersonService.queryLiveAnchorList(contributeListRequest);
				
				if (mapResponse.getState() == 100) {
					resultMap.put("guardList", mapResponse.getResponse().get("topList"));
				}else {
					resultMap.put("guardList", new ArrayList<>());
				}
			} catch (Exception e) {
				log.info("获取守护榜失败");
				e.printStackTrace();
				resultMap.put("guardList", new ArrayList<>());
			}
			
			//查询精彩时刻的回放记录信息(最新3条) type = 1 热门回放   type = 2 精彩时刻回放
			paramMap.put("type", 2);
			paramMap.put("limit", Integer.parseInt(propertiesUtil.getValue("wonderfulLiveRecordNums", "conf_common.properties")));
			List<Map<Object, Object>> wonderfulLiveRecordList = anchorLiveRecordDao.queryPlayBackRecord(paramMap);
			
			if (wonderfulLiveRecordList != null && wonderfulLiveRecordList.size() > 0) {
				//返回调整后精彩时刻的回放列表数据
				resultMap.put("wonderfulLiveRecordList", this.updateLiveRecordList(wonderfulLiveRecordList, liverMap));
				
			}else {
				//没有精彩时刻的回放记录放回空集合
				resultMap.put("wonderfulLiveRecordList", new ArrayList<>());
				
			}
			
			//响应
			MapResponse response = new MapResponse(ResponseCode.SUCCESS, "获取直播首页信息成功");
			response.setResponse(resultMap);
			return response;
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("获取直播首页信息下(热门回放,热门主播,守护榜,精彩时刻)失败,错误信息如下:" + e.getMessage());
			return new BaseResponse(ResponseCode.FAILURE, "获取直播首页信息失败");
		}
	}

	/**
	 * 
	* @Title: queryRecommendLiveRecordList
	* @Description: 推荐的直播列表/预告列表/精彩时刻列表
	 * liveType列表类型  0预告列表  1直播列表   2精彩时刻列表
	 * type 预告列表帅选条件 1 我喜欢的主播(关注)   2我感兴趣的店铺(消费,浏览)  3.我附近  4.最多人想去
	* @return Object    返回类型
	* @throws
	 */
	public Object queryRecommendLiveRecordList(RecommendLiveRecordListRequest recommendLiveRecordListRequest) {
		try {
			//判断用户是否登录过,登录过则获取uid
			String uid = "";
			Map<Object, Object> liverMap = null;
			if (recommendLiveRecordListRequest != null && StringUtils.isNotEmpty(recommendLiveRecordListRequest.getSessiontoken())) {
				uid = sessionTokenService.getStringForValue(recommendLiveRecordListRequest.getSessiontoken()) + "";
				//如果token过期,则当没有登录处理
				if (StringUtils.isNotEmpty(uid) && !"null".equals(uid)) {
					liverMap = liveUserDao.queryLiverInfoByUid(Integer.parseInt(uid));
				}
			}
			//纬度
			Double latitude = recommendLiveRecordListRequest.getLatitude();
			//经度
			Double longitude = recommendLiveRecordListRequest.getLongitude();
			
			//结果集
			Map<Object, Object> resultMap = new HashMap<>();
			
			//列表类型  0预告列表  1直播列表   3精彩时刻列表
			Integer liveType = recommendLiveRecordListRequest.getLiveType();
			
			//组装参数
			Map<Object,Object> paramMap = new HashMap<Object,Object>();
			
			//组装参数
			paramMap.put("page", recommendLiveRecordListRequest.getPage());
			paramMap.put("limit", Integer.parseInt(propertiesUtil.getValue("liveRecordListNum", "conf_common.properties")));
			if (latitude != null && longitude != null && latitude != 0 && longitude != 0) {
				paramMap.put("longitude", longitude);
				paramMap.put("latitude", latitude);
			}
			
			if (liverMap != null && liverMap.get("id") != null) {
				//直播用户id,用于查询关注的主播
				paramMap.put("liverId", Integer.parseInt(liverMap.get("id").toString()));
				//手机号码,排除过滤测试账号
				paramMap.put("phone", liverMap.get("phone"));
			}
			
			if (liveType == 0) {
				Integer apiVersion = VersionUtil.getVersionCode(recommendLiveRecordListRequest);

				//预告列表筛选条件 1 我喜欢的主播(关注)   2我感兴趣的店铺(消费,浏览)  3.我附近   4.最多人想去 5为某位直播的预告列表(只包含预售的) 6预售列表  
				Integer type = recommendLiveRecordListRequest.getType();
				
				//日期筛选条件
				if (recommendLiveRecordListRequest.getSdate() != null && StringUtils.isNotEmpty(recommendLiveRecordListRequest.getSdate()) ) {
					//开始时间帅选条件
					paramMap.put("sdate", recommendLiveRecordListRequest.getSdate());
					
				}
				
				if (recommendLiveRecordListRequest.getEdate() != null && StringUtils.isNotEmpty(recommendLiveRecordListRequest.getEdate()) ) {
					//结束时间筛选条件
					paramMap.put("edate", recommendLiveRecordListRequest.getEdate());
				}
				
				//查询精彩预告列表(规则:有预售的预告优先,优已关注的主播)
				paramMap.put("zhiboType", 0);
				
				if (type != null && type != 0) {
					//预告列表帅选条件  1 我喜欢的主播(关注)   2我感兴趣的店铺(消费,浏览)  3.我附近   4.最多人想去 5为某位直播的预告列表(只包含预售的) 6预售列表
					paramMap.put("type", type);
					
					if (type == 1 || type == 2) {
						if (liverMap != null && liverMap.get("id") != null) {
							if (type == 1) {
								//我喜欢的主播(关注),需要传入用户的直播id
								paramMap.put("liver_str_id", Integer.parseInt(liverMap.get("id").toString()));
								
							}else {
								//我感兴趣的店铺(消费,浏览),需要传入uid
								paramMap.put("uid", Integer.parseInt(uid));
								
							}
							
						}else {
							//如果没有登录,则获取不了我喜欢的主播(关注)/我感兴趣的店铺(消费,浏览)的预告列表
							return new BaseResponse(ResponseCode.TOKENERR, "请先登录");
							
						}
						
					}else if (type == 3) {
						if (latitude == null || longitude == null || latitude == 0 || longitude == 0) {
							//如果没有获取用户的经纬度,则无法查询我附近的预告列表
							return new BaseResponse(ResponseCode.FAILURE, "无法获取我附近的预告列表信息");
							
						}
						
					}else if (type == 5) {
						if (recommendLiveRecordListRequest.getAnchorId() != null) {
							//5为某位直播的预告列表(只包含预售的)
							paramMap.put("anchorId", recommendLiveRecordListRequest.getAnchorId());
							
						}else {
							return new BaseResponse(ResponseCode.FAILURE, "参数异常");
						}
						
					}
					
				}
				
				//查询筛选条件下的预告列表
				List<Map<Object, Object>> preLiveRecordList = anchorLiveRecordDao.queryLiveRecordList(paramMap);
				
				//查询有预售的正在直播的列表
				paramMap.put("zhiboType", 1);
				List<Map<Object, Object>> currentLiveRecordList = anchorLiveRecordDao.queryLiveRecordList(paramMap);
				
				//整合数据
				List<Map<Object, Object>> preLiveRecordResultList = new ArrayList<>();
				List<Map<Object, Object>> currentResultList = new ArrayList<>();
				if (preLiveRecordList != null && preLiveRecordList.size() > 0) {
					//预告列表 = 预告 + 有预售的直播 ,将预告加入预告总结果集
					preLiveRecordResultList = this.updateLiveRecordList(preLiveRecordList, liverMap, apiVersion);
				}
				
				if (currentLiveRecordList != null && currentLiveRecordList.size() > 0) {
					//调整有预售的直播返回字段
					currentResultList = this.updateLiveRecordList(currentLiveRecordList, liverMap, apiVersion);
					for (Map<Object, Object> map : currentResultList) {
						//找出有预售的正在直播的记录
						if (Integer.parseInt(map.get("isSell").toString()) == 1) {
							preLiveRecordResultList.add(map);
						}
					}
					
					
				}
				// 客户端，将签约类型为4的写成3
				if (VersionUtil.getVersionCode(recommendLiveRecordListRequest) <= 361) {
					if (preLiveRecordResultList != null) {
						for (Map<Object, Object> map : preLiveRecordResultList) {
							try {
								int signType = map.get("signType") == null ? 0 : Integer.parseInt(map.get("signType").toString());
								if (signType == 4) {
									map.put("signType", 3);
								}
							} catch (Exception e) {
							}
						}
					}
				}
				
				//返回预告列表 = 预告 + 有预售的直播
				resultMap.put("preLiveRecordList", preLiveRecordResultList);
				
			}else if (liveType == 1) {   //直播列表
				List<Object> testIds = new ArrayList<Object>();
				try {
					//排除UID 为 测试的内部账号
					List<Map<Object, Object>> liverInfoList = liveUserDao.queryLiverInfosByIsinsideAndSignType();
					if (liverInfoList.size()>0) {
						for (Map<Object, Object> map : liverInfoList) {
							testIds.add(map.get("id"));
						}
					}
				} catch (Exception e) {
					log.warn("直播首页排除内部账号，查询异常");
				}

				int zhiboGiftRange = 250000;
				try{
					//从配置文件中获取当场直播区间
					zhiboGiftRange = Integer.parseInt(propertiesUtil.getValue("zhiboGiftRange", "conf_common.properties"));
				}catch(Exception e){
					e.printStackTrace();
					log.info("获取直播打赏鸟币最大区间配置失败");
				}
				//直播列表(规则:在打赏区间内优先，有预售的直播优先,优已关注的主播)
				paramMap.put("zhiboType", 1);
				paramMap.put("zhiboGiftRange", zhiboGiftRange);
				paramMap.put("testIds", testIds);  //屏蔽内部测试账号直播
				List<Map<Object, Object>> currentLiveRecordList = anchorLiveRecordDao.queryLiveRecordList(paramMap);
				if (currentLiveRecordList != null && currentLiveRecordList.size() > 0 ) {
					//调整直播列表放回字段,存入结果集
					resultMap.put("currentLiveRecordList", this.updateLiveRecordList(currentLiveRecordList, liverMap));
					
				}else {
					resultMap.put("currentLiveRecordList", new ArrayList<>());
				}
				
			}else if (liveType == 2) {
				//精彩时刻回放列表 type = 1 热门回放   type = 2 精彩时刻回放
				paramMap.put("type", 2);
				List<Map<Object, Object>> wonderfulLiveRecordList = anchorLiveRecordDao.queryPlayBackRecord(paramMap);
				if (wonderfulLiveRecordList != null && wonderfulLiveRecordList.size() > 0) {
					//返回调整后精彩时刻的回放列表数据
					resultMap.put("wonderfulLiveRecordList", this.updateLiveRecordList(wonderfulLiveRecordList, liverMap));
					
				}else {
					resultMap.put("wonderfulLiveRecordList", new ArrayList<>());
					
				}
				
			}else {
				return new BaseResponse(ResponseCode.FAILURE, "参数不对");
			}
			
			//响应
			MapResponse response = new MapResponse(ResponseCode.SUCCESS, "获取预告列表/直播列表/精彩时刻列表成功");
			log.warn(resultMap.toString());
			response.setResponse(resultMap);
			return response;
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("获取预告列表/直播列表/精彩时刻列表成功失败,错误信息如下:" + e.getMessage());
			return new BaseResponse(ResponseCode.FAILURE, "获取预告列表/直播列表/精彩时刻列表失败");
		}
	}

}
