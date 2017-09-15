package com.xmniao.xmn.core.xmer.service;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.xmniao.xmn.core.base.BaseRequest;
import com.xmniao.xmn.core.util.Base64;
import com.xmniao.xmn.core.xmer.entity.MultipleSeller;
import com.xmniao.xmn.core.kscloud.entity.KSLiveEntity;
import com.xmniao.xmn.core.kscloud.service.KSCloudService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.comparators.ComparableComparator;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.Point;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Order;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.MongoBaseService;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.catehome.dao.HotWordsDao;
import com.xmniao.xmn.core.catehome.entity.HotWords;
import com.xmniao.xmn.core.catehome.response.SearchResponse;
import com.xmniao.xmn.core.common.Constant;
import com.xmniao.xmn.core.common.ConstantDictionary;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.SellerListRequest;
import com.xmniao.xmn.core.common.request.live.SelleridPageRequest;
import com.xmniao.xmn.core.common.request.seller.SellerNearListRequest;
import com.xmniao.xmn.core.live.dao.AnchorLiveRecordDao;
import com.xmniao.xmn.core.live.dao.BusinessDao;
import com.xmniao.xmn.core.live.dao.LiveUserDao;
import com.xmniao.xmn.core.live.entity.LiveRecordInfo;
import com.xmniao.xmn.core.live.entity.LiverInfo;
import com.xmniao.xmn.core.seller.SellerDetailFoodResponse;
import com.xmniao.xmn.core.seller.dao.FoodDao;
import com.xmniao.xmn.core.seller.entity.ActivityFullreductionInfo;
import com.xmniao.xmn.core.seller.entity.Food;
import com.xmniao.xmn.core.seller.entity.Redpacket;
import com.xmniao.xmn.core.seller.entity.Seller;
import com.xmniao.xmn.core.seller.entity.SellerCouponDetail;
import com.xmniao.xmn.core.seller.entity.SellerDetailed;
import com.xmniao.xmn.core.seller.entity.SellerLandMark;
import com.xmniao.xmn.core.seller.entity.SellerNearResponse;
import com.xmniao.xmn.core.seller.entity.SellerPic;
import com.xmniao.xmn.core.util.ArithUtil;
import com.xmniao.xmn.core.util.GetRangeUtil;
import com.xmniao.xmn.core.util.PropertiesUtil;
import com.xmniao.xmn.core.verification.dao.BillDao;
import com.xmniao.xmn.core.verification.dao.UrsDao;
import com.xmniao.xmn.core.xmer.dao.SellerDao;
import com.xmniao.xmn.core.xmer.dao.SellerInfoDao;
import com.xmniao.xmn.core.xmer.dao.UrsEarningsRelationDao;
import com.xmniao.xmn.core.xmer.entity.MSeller;

/**
 * 
 * 
 * 项目名称：xmnService 类名称：SellerService 类描述： 我的店铺列表service 创建人：yezhiyong
 * 创建时间：2016年5月23日 下午2:51:19
 * 
 * @version
 *
 */
@Service
public class SellerService {

	/**
	 * 日志报错
	 */
	private final Logger log = Logger.getLogger(SellerService.class);

	/**
	 * 注入sellerDao
	 */
	@Autowired
	private SellerDao sellerDao;

	/**
	 * 注入billDao
	 */
	@Autowired
	private BillDao billDao;

	@Autowired
	private FoodDao foodDao;

	/**
	 * 注入redis缓存
	 */
	@Autowired
	private SessionTokenService sessionTokenService;

	@Autowired
	private BusinessDao businessDao;

	@Autowired
	private String fileUrl;

	@Autowired
	private AnchorLiveRecordDao anchorLiveRecordDao;

	@Autowired
	private LiveUserDao liveUserDao;

	@Autowired
	private MongoBaseService mongoBaseService;

	@Autowired
	private UrsDao ursDao;

	@Autowired
	private HotWordsDao hotWordsDao;
	
	@Autowired
	private UrsEarningsRelationDao ursEarningsRelationDao;
	
	@Autowired
	private SellerInfoDao sellerInfoDao;
	@Resource
	private MongoTemplate mongoTemplate;

	@Autowired
	private PropertiesUtil propertiesUtil;
	@Autowired
	private XmerService xmerService;
	@Autowired
	private KSCloudService ksCloudService;
	/**
	 * 
	 * @Title: querySellerList @Description: 查询店铺列表 @return Object 返回类型 @throws
	 */
	public Object querySellerList(SellerListRequest sellerListRequest) {
		try {
			DecimalFormat df = new DecimalFormat("0.00");// 格式化double类型数据
			String sUid = xmerService.getUidBySUid(sellerListRequest.getSessiontoken(), sellerListRequest.getsUid());
			if(sUid == null || sUid.equals("")||sUid.equals("null")){
				return new BaseResponse(ResponseCode.FAILURE, "查询店铺列表信息失败,sessiontoken失效");
			}
			Map<Object, Object> map = new HashMap<>();
			map.put("uid", Integer.parseInt(sUid));
			map.put("status", sellerListRequest.getType());
			map.put("page", sellerListRequest.getPage());
			map.put("limit", Constant.PAGE_LIMIT);

			// 查询店铺列表
			List<Map<Object, Object>> sellerList = sellerDao.querySellerList(map);
			if (sellerList != null && sellerList.size() > 0) {
				for (Map<Object, Object> sellerMap : sellerList) {
					if ("3".equals(sellerMap.get("status").toString())) {
						// 查询交易流水
						Double dealflow = billDao.queryTotalflow((Integer) sellerMap.get("sellerid"));
						BigDecimal b1 = new BigDecimal(dealflow);
						String dealf = df.format(b1.setScale(2, RoundingMode.HALF_UP).doubleValue());
						sellerMap.put("totalflow", dealf);

						// 查询本月流水
						Double mouthflow = billDao.queryMonthflow((Integer) sellerMap.get("sellerid"));
						BigDecimal b2 = new BigDecimal(mouthflow);
						String mouthf = df.format(b2.setScale(2, RoundingMode.HALF_UP).doubleValue());
						sellerMap.put("mouthflow", mouthf);

					}
				}
			}
			MapResponse response = new MapResponse(ResponseCode.SUCCESS, "查询成功");
			Map<Object, Object> resultMap = new HashMap<>();
			resultMap.put("sellers", sellerList);
			response.setResponse(resultMap);
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE, "查询店铺列表信息失败");
		}
	}

	/**
	 * 
	 * @Description: 查询商家基本信息
	 * @author xiaoxiong
	 * @date 2016年11月14日 sellerid 商家ID
	 */
	public Seller querySellerBySellerid(Long sellerid) {
		try {

			return sellerDao.querySellerBySellerid(sellerid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @Description: 根据商家ID和类型查询商家图片
	 * @author xiaoxiong
	 * @date 2016年11月15日
	 * @param type
	 *            0 环境图 1logo 2封面
	 */
	public List<SellerPic> querySellerPicBySelleridAndType(Integer sellerid, Integer type) {
		try {
			Map<String, Object> params = new HashMap<>();
			params.put("fileUrl", fileUrl);
			params.put("sellerid", sellerid);
			params.put("type", type);
			return sellerDao.querySellerPicBySelleridAndType(params);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @Description: 根据商圈ID查询商圈信息
	 * @author xiaoxiong
	 * @date 2016年11月15日
	 */
	public Map<Object, Object> queryBusinessByZoneid(Integer zoneid) {

		return businessDao.selectBusinessByid(zoneid);
	}

	/**
	 * 
	 * @Description: 查询用户最近消费时间
	 * @author xiaoxiong
	 * @date 2016年11月14日
	 */
	public List<Map<String, Object>> queryUidBySellerid(SelleridPageRequest request) {

		return billDao.queryUidBySellerid(request);
	}

	/**
	 * 
	 * @Description: 查询商家详细信息
	 * @author xiaoxiong
	 * @date 2016年11月16日
	 */
	public SellerDetailed querySellerDetailBySellerid(Integer sellerid) {
		try {

			return sellerDao.querySellerDetailBySellerid(sellerid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @Description: 查询满减活动表
	 * @author xiaoxiong
	 * @date 2016年11月14日
	 */
	public List<ActivityFullreductionInfo> querySellerSaleBySellerId(Map<Object, Object> map) {
		return sellerDao.querySellerSaleBySellerId(map);
	}

	/**
	 * 
	 * @Description: 查询满商家有无设置红包
	 * @author xiaoxiong
	 * @date 2016年11月14日
	 */
	public Redpacket querySellerRedPacketInfo(Map<Object, Object> map) {
		return sellerDao.querySellerRedPacketInfo(map);
	}

	/**
	 * 
	 * @Description: 查询满商家有无设置红包
	 * @author xiaoxiong
	 * @date 2016年11月14日
	 */
	public int querySellerRedPacketBySellerId(Integer sellerId) {
		return sellerDao.querySellerRedPacketBySellerId(sellerId);
	}

	/**
	 * @Description: 查询商家消费人数
	 * @author xiaoxiong
	 * @date 2016年11月16日
	 */
	public int consumeCount(int sellerid) {

		int consuNumber = 0;
		try {
			// 实际订单数量
			int billNumber = billDao.consumeCount(sellerid);
			// 缓存随机消费人数key
			String randomKey = Constant.SELLER_RANDOM_CONSU_NUMBER;

			Map<Object, Object> redisNumber = sessionTokenService.getsessionToken(randomKey);
			if (redisNumber == null) {
				redisNumber = new HashMap<>();
			}
			if (redisNumber.get(sellerid + "") == null) {

				int randomNumber = (int) (Math.random() * (500 - 100 + 1) + 100);
				consuNumber = billNumber + randomNumber;
				redisNumber.put(sellerid + "", randomNumber + "");
				// 保存到redis
				sessionTokenService.setMapForObject(randomKey, redisNumber, 0, null);
				;
			} else {
				consuNumber = billNumber + Integer.valueOf(redisNumber.get(sellerid + "") + "");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return consuNumber;
	}

	public int getConsuCount(int billCount, int sellerid) {
		int consuNumber = 0;
		try {
			// 缓存随机消费人数key
			String randomKey = Constant.SELLER_RANDOM_CONSU_NUMBER;

			Map<Object, Object> redisNumber = sessionTokenService.getsessionToken(randomKey);
			if (redisNumber == null) {
				redisNumber = new HashMap<>();
			}
			if (redisNumber.get(sellerid + "") == null) {

				int randomNumber = (int) (Math.random() * (500 - 100 + 1) + 100);
				consuNumber = billCount + randomNumber;
				redisNumber.put(sellerid + "", randomNumber + "");
				// 保存到redis
				sessionTokenService.setMapForObject(randomKey, redisNumber, 0, null);
			} else {
				consuNumber = billCount + Integer.valueOf(redisNumber.get(sellerid + "") + "");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return consuNumber;
	}

	/**
	 * 
	 * @Description: 查询商家正在直播的数量
	 * @author xiaoxiong
	 * @date 2016年11月17日
	 */
	public int nowLiveCount(int sellerid, int type) {

		return anchorLiveRecordDao.nowLiveCount(sellerid, type);
	}

	/**
	 * 
	 * @Description: 查询用户在店铺消费的数量
	 * @author xiaoxiong
	 * @date 2016年11月17日
	 */
	public int billCountBySelleridAndUid(int sellerid, int uid) {
		try {
			return billDao.billCountBySelleridAndUid(sellerid, uid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 
	 * @Description: 查询商家地标
	 * @author xiaoxiong
	 * @date 2016年11月17日
	 */
	public SellerLandMark querySellerLandMarkBySellerid(Integer sellerid) {
		try {

			return sellerDao.querySellerLandMarkBySellerid(sellerid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @Description: 查询用户浏览店铺记录数量
	 * @author xiaoxiong
	 * @date 2016年11月18日
	 */
	public int querySellerBrowsedCount(String uid, Integer sellerid) {
		try {
			return sellerDao.querySellerBrowsedCount(uid, sellerid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 
	 * @Description: 用户浏览店铺的数量加1
	 * @author xiaoxiong
	 * @date 2016年11月18日
	 */
	public void updateSellerBrowSed(String uid, Integer sellerid) {

		Map<String, Object> params = new HashMap<>();
		params.put("uid", uid);
		params.put("sellerid", sellerid);
		params.put("cdate", new Date());

		sellerDao.updateSellerBrowSed(params);
	}

	/***
	 * @Description: 添加用户浏览店铺记录
	 * @author xiaoxiong
	 * @date 2016年11月18日
	 */
	public void insertSellerBrowsed(String uid, Integer sellerid) {

		Map<String, Object> params = new HashMap<>();
		params.put("uid", uid);
		params.put("sellerid", sellerid);
		params.put("cdate", new Date());

		sellerDao.insertSellerBrowsed(params);

	}

	/**
	 * @Description: 查询商家优惠卷
	 * @author xiaoxiong
	 * @date 2016年11月18日
	 * @param
	 */
	public List<SellerCouponDetail> querySellerCoupon(Map<String, Object> params) {
		try {
			return sellerDao.querySellerCoupon(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 查询红包信息
	 * 
	 * @author xiaoxiong
	 * @date 2016年11月18日
	 */
	public List<Redpacket> queryRedPacket(Map<String, Object> params) {
		try {
			return sellerDao.querySellerRedPacket(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Map<String, Object>> queryCouponBySellerid(Map<String, Object> params) {
		try {
			params.put("fileUrl", fileUrl);
			return sellerDao.queryCouponBySellerid(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 查询商家所有活动
	 * 
	 * @author xiaoxiong
	 * @date 2016年11月21日
	 * @param sellerid
	 *            商家ID
	 */
	public List<Map<String, Object>> queryActivityList(Integer sellerid) {
		try {
			return sellerDao.queryActivityList(sellerid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 关键字查询
	 * 
	 * @author xiaoxiong
	 * @date 2016年11月22日
	 */
	public List<Map<String, Object>> search(Map<String, Object> params) {
		try {
			return sellerDao.search(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 使用mongodb
	 * 
	 * @author xiaoxiong
	 * @date 2016年12月8日
	 */
	public Object sellerNearList(SellerNearListRequest request) {
		try {
			Criteria criteria = new Criteria();
			request.setRange(Integer.MAX_VALUE);//默认查询999km以内的商家（本城市的所有商家）
			/* 用near，默认以度为单位，公里数除以111 */
			criteria.and("coordinate").near(new Point(request.getLongitude(), request.getLatitude()))
					.maxDistance(Double.parseDouble(request.getRange() + "") / 111);
			/* 商家状态 */
			criteria.and("status").is(3);
			/* 商家是否在线 */
			criteria.and("isonline").is(1);
			/* 如果一级类别不为空 */
			if (request.getCategory() != null) {
				criteria.and("category").is(request.getCategory());
			}
			/* 如果二级类别不为空 */
			if (request.getGenre() != null) {
				criteria.and("genre").is(request.getGenre());
			}
			/* 如果商圈不为空 */
			if (request.getZoneid() != null) {
				criteria.and("zoneid").is(request.getZoneid());
			}
			if (StringUtils.isNotBlank(request.getCityId())) {
				criteria.and("city").is(Integer.parseInt(request.getCityId()));
			}
			//--店铺处于公开状态
			criteria.and("is_public").is(1);	
			//--店铺处于公开状态
									
			Query query = new Query(criteria);
			// query.sort().on("is_live", Order.DESCENDING);//是否有直播
			//query.skip((request.getPage() - 1) * request.getPageSize());
			//query.limit(request.getPageSize());
			List<MSeller> list = mongoBaseService.findQuery(query, MSeller.class, "seller");
			if (list!=null && !list.isEmpty()) {//假分页
				Integer page =request.getPage();
				Integer pageSize = request.getPageSize();
				Integer fromIndex = (page - 1) * pageSize;
				Integer totalIndex = fromIndex+pageSize;
				Integer totalCount = list.size();
				Integer pageCount = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize +1; //总共的页数
				if(page == pageCount){  
					list=list.subList(fromIndex,totalCount); 
				}else if (page<pageCount) {
					list=list.subList(fromIndex,totalIndex );
				}else{
					list=null; 
				}
			}
			String uid = "";
			if (request.getSessiontoken() != null) {
				uid = sessionTokenService.getStringForValue(request.getSessiontoken()) + "";
			}

			List<SellerNearResponse> resultList = responseList(list, request.getLongitude(), request.getLatitude(),
					request.getType(), uid,request);
			//根据距离排序
			java.util.Collections.sort(resultList, new Comparator<SellerNearResponse>() {
				public int compare(SellerNearResponse o1, SellerNearResponse o2) {
					BigDecimal num1= new BigDecimal(o1.getDistance());
					BigDecimal num2= new BigDecimal(o2.getDistance());
					return num1.compareTo(num2);
				}
			});	
			//判断是不是奇数，如果是补一个为偶数
			int listLength = resultList.size();
			if ((listLength % 2) != 0) {
				String defaultUrl = propertiesUtil.getValue("defaultHomeImg", "conf_common.properties");
				String defaultTitle = propertiesUtil.getValue("defaultHomeDesc", "conf_common.properties");
				SellerNearResponse lastSellerNearResponse = new SellerNearResponse();
				lastSellerNearResponse.setSellerid("");
				lastSellerNearResponse.setSellername(defaultTitle);
				lastSellerNearResponse.setUrl(defaultUrl);
				resultList.add(lastSellerNearResponse);
			}		
			Map<Object, Object> response = new HashMap<>();
			response.put("list", resultList);

			MapResponse mapResponse = new MapResponse(ResponseCode.SUCCESS, "成功");
			mapResponse.setResponse(response);
			return mapResponse;

		} catch (Exception e) {
			log.info("附近店铺出错啦"+e);
			e.printStackTrace();
		}
		return new BaseResponse(ResponseCode.FAILURE, "获取附近店铺信息失败！");
	}

	private List<SellerNearResponse> responseList(List<MSeller> list, double lng, double lat, int type, String uid,SellerNearListRequest request) {
		List<SellerNearResponse> result = new ArrayList<>();

		if (list != null && list.size() > 0) {

			try {
				for (MSeller mseller : list) {
					SellerNearResponse response = new SellerNearResponse();
					response.setSellername(mseller.getSellername());
					response.setSellerid(mseller.getSellerid());
					response.setTradename(mseller.getTradename());
					response.setConCount(Integer.parseInt(
							mseller.getConsumption() == null ? "0" : mseller.getSeller_random_num_consumption()));
					response.setZoneName(mseller.getBusiness());

					/* 距离处理如果小于1000显示m,大于1000m除以1000显示KM */
					double range = GetRangeUtil.distance(mseller.getCoordinate().getLongitude(),
							mseller.getCoordinate().getLatitude(), lng, lat);
					response.setDistance(range);
					if (range < 1000) {
						response.setRange((int) range + "m");
					} else {
						response.setRange((int) range / 1000 + "km");
					}
					/* 商家封面图*/
					JSONObject logoJson = JSONObject.parseObject(mseller.getPic_cover());
					if (logoJson != null && logoJson.getString("picurl") != null) {
						response.setUrl(fileUrl + logoJson.getString("picurl"));
					} else { /* 商家logo图片 */
						JSONObject picsJson = JSONArray.parseObject(mseller.getPic_logo());				
						response.setUrl(fileUrl + picsJson.getString("picurl"));
					

					}
					//版本控制区分寻蜜鸟和鸟人直播
					/**------------------*/
					String appversion = request.getAppversion();
					appversion = appversion.replace(".", "");
					int appv = Integer.parseInt(appversion);
					if ((appv<=360 && request.getAppSource().equals(ConstantDictionary.AppSourceState.XMN_APP.getName()))) {
						response.setZhibo_type(3);
					}
					/**------------------*/
						//组装商家直播信息
						Map<Object,Object> liveMap = anchorLiveRecordDao.findSellerLive(mseller.getSellerid());
						
						if (liveMap!=null && !liveMap.isEmpty()) {
							if (!ObjectUtils.NULL.equals(liveMap.get("zhiboType"))) {
								String nname = liveMap.get("nname")+"";
								Integer zhiboType = Integer.parseInt(liveMap.get("zhiboType")+"");
								//版本控制
								/**------------------*/
								//直播类型控制								
								getzhiboTypeAndRemarkByAppversion(appv,response,nname,zhiboType,request.getAppSource());																																															
								/**------------------*/								
								Map<String, Object> subMap = getSubMap(liveMap);
								response.setSubSet(subMap);
							}
						}							
					/* 查询标签 */
					int lable = lable(Integer.parseInt(mseller.getSellerid()), uid, range);
					response.setLable(lable);

					result.add(response);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return result;
	}
	

	/**根据不同的版本控制显示直播状态和remark
	 * @param appv
	 * @param response
	 * @param nname
	 */
	private void getzhiboTypeAndRemarkByAppversion(int appv, SellerNearResponse response, String nname,Integer zhiboType,String appSource) {
		if (appv>360 || ConstantDictionary.AppSourceState.BIRD_APP.getName().equals(appSource)){
			response.setZhibo_type(zhiboType);
			if (zhiboType==1){
				response.setZhibomark("直播 " + nname + " 正在直播");
			}else if (zhiboType==0){
				response.setZhibomark("直播 " + nname + " 即将直播");
			}else if (zhiboType==3){
				response.setZhibomark("主播 " + nname + " 来过");
			}else if (zhiboType==4){
				response.setZhibomark("主播 " + nname + " 来过");
			}
					
		}else {
			response.setZhibo_type(3);//旧版默认是3
			if (zhiboType==1){
				response.setZhibomark("直播 " + nname + " 正在直播");
				response.setZhibo_type(0);
			}else if (zhiboType==0){
				response.setZhibomark("直播 " + nname + " 即将直播");
				response.setZhibo_type(1);
			}else if (zhiboType==3){
				response.setZhibomark("主播 " + nname + " 来过");
				response.setZhibo_type(2);
			}else if (zhiboType==4){
				response.setZhibomark("主播 " + nname + " 来过");
				response.setZhibo_type(2);
			}else{
				response.setZhibo_type(3);
				response.setZhibomark("");
			}
				
		}
		
	}

	private Map<String, Object> getSubMap(Map<Object, Object> liveMap) {
		Map<String,Object> subMap = new HashMap<String,Object>();
		subMap.put("roomNo", liveMap.get("roomNo"));
		subMap.put("title", liveMap.get("title"));
		subMap.put("anchorId", liveMap.get("anchorId"));
		subMap.put("id", liveMap.get("id"));
		subMap.put("avetar", fileUrl + liveMap.get("avetar"));
		subMap.put("liveType", liveMap.get("liveType"));
		subMap.put("nname", liveMap.get("nname"));
		subMap.put("livePlatform", 1); //直播使用平台  1 腾讯直播  2 金山云直播
		subMap.put("liveRtmpUrl", "");  //拉流地址
		// 如果是正在直播的需要查询groupid进房间使用
		if (Integer.parseInt(liveMap.get("zhiboType")+"") == 1) {
			LiverInfo liveInfo = liveUserDao
					.queryLiverInfoByAnchorId(Integer.parseInt( liveMap.get("anchorId")+ ""));
			if (liveInfo != null) {
				subMap.put("groupId", liveInfo.getGroup_id());
				// 是否使用金山云拉流
				try {
					KSLiveEntity ksLiveEntity = ksCloudService.createKSLPullUrl(String.valueOf(liveInfo.getUid()), liveInfo);
					if (ksLiveEntity != null) {
						subMap.put("livePlatform", ksLiveEntity.getPlatform()); //直播使用平台  1 腾讯直播  2 金山云直播
						subMap.put("liveRtmpUrl", ksLiveEntity.getUrl());  //拉流地址
					}
				} catch (UnsupportedEncodingException e) {
					log.warn("生成金山云拉流失败", e);
				}
			}
		}
		return subMap;
	}


	/**
	 * 查询直播信息
	 * 
	 * @author xiaoxiong
	 * @date 2016年12月7日
	 */
	public void isLive(Map<String, Object> map) {
		try {
			int sellerid = Integer.parseInt(map.get("sellerid") + "");

			int count = anchorLiveRecordDao.nowLiveCount(sellerid, null);
			if (count > 0) {

				map.put("isLive", 1);
				getLiveInfo(map);

			} else {
				map.put("isLive", 0);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 查询直播信息
	 * 
	 * @author xiaoxiong
	 * @date 2016年12月7日
	 */
	public void getLiveInfo(Map<String, Object> map) {

		Map<String, Object> params = new HashMap<>();
		params.put("page", 1);
		params.put("pageSize", 1);
		params.put("sellerid", map.get("sellerid"));
		try {
			// 直播类型 -1 初始 0 预告 1 正在直播 2暂停直播 3 回放 4历史通告 5结束直播',
			// 查询正在直播的
			params.put("type", 1);
			List<LiveRecordInfo> list = anchorLiveRecordDao.queryLiveRecordInfoBySellerIdAndType(params);
			if (list == null || list.size() == 0) {
				// 如果没有正在直播的查询预告
				params.put("type", 0);
				list = anchorLiveRecordDao.queryLiveRecordInfoBySellerIdAndType(params);
				if (list == null || list.size() == 0) {
					params.put("type", 3);
					list = anchorLiveRecordDao.queryLiveRecordInfoBySellerIdAndType(params);
				}
			}
			if (list != null && list.size() > 0) {
				LiveRecordInfo liveRecoreInfo = list.get(0);
				Map<String, Object> subMap = new HashMap<>();
				subMap.put("type", liveRecoreInfo.getZhibo_type());
				subMap.put("roomNo", liveRecoreInfo.getZhibo_type());
				subMap.put("title", liveRecoreInfo.getZhibo_title());
				subMap.put("anchorId", liveRecoreInfo.getAnchor_id());
				subMap.put("id", liveRecoreInfo.getId());
				subMap.put("avetar", fileUrl + liveRecoreInfo.getAvatar());
				subMap.put("liveType", liveRecoreInfo.getLive_start_type());
				// 如果是正在直播的需要查询groupid进房间使用
				if (liveRecoreInfo.getZhibo_type() == 1) {
					LiverInfo liveInfo = liveUserDao.queryLiverByUid(liveRecoreInfo.getAnchor_id());
					if (liveInfo != null) {
						subMap.put("groupId", liveInfo.getGroup_id());
					}
				}
				/*
				 * if(liveRecoreInfo.getVedio_url()!=null){ subMap.put("url",
				 * liveRecoreInfo.getVedio_url()); }
				 */
				map.put("subSet", subMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 查询直播信息
	 * 
	 * @author xiaoxiong
	 * @date 2016年12月8日
	 */
	public Map<String, Object> getLiveInfo(int type, String sellerid) {

		Map<String, Object> params = new HashMap<>();
		params.put("page", 1);
		params.put("pageSize", 1);
		params.put("sellerid", sellerid);
		params.put("type", type);
		List<LiveRecordInfo> list = anchorLiveRecordDao.queryLiveRecordInfoBySellerIdAndType(params);
		if (list != null && list.size() > 0) {
			LiveRecordInfo liveRecoreInfo = list.get(0);
			Map<String, Object> subMap = new HashMap<>();
			/*
			 * subMap.put("type", liveRecoreInfo.getZhibo_type());
			 */
			subMap.put("roomNo", liveRecoreInfo.getAnchor_room_no());
			subMap.put("title", liveRecoreInfo.getZhibo_title());
			subMap.put("anchorId", liveRecoreInfo.getAnchor_id());
			subMap.put("id", liveRecoreInfo.getId());
			subMap.put("avetar", fileUrl + liveRecoreInfo.getAvatar());
			subMap.put("liveType", liveRecoreInfo.getLive_start_type());
			subMap.put("nname", liveRecoreInfo.getNname());
			subMap.put("livePlatform", 1); //直播使用平台  1 腾讯直播  2 金山云直播
			subMap.put("liveRtmpUrl", "");  //拉流地址
			// 如果是正在直播的需要查询groupid进房间使用
			if (liveRecoreInfo.getZhibo_type() == 1) {
				LiverInfo liveInfo = liveUserDao
						.queryLiverInfoByAnchorId(Integer.parseInt(liveRecoreInfo.getAnchor_id() + ""));
				if (liveInfo != null) {
					try {
						Long uid = liveInfo.getUid();
						//  生成拉流地址
						KSLiveEntity entity = ksCloudService.createKSLPullUrl(String.valueOf(uid), liveInfo);
						if (entity != null) {
							subMap.put("livePlatform", entity.getPlatform());
							subMap.put("liveRtmpUrl", entity.getUrl());
						}
					} catch (Exception e) {
						log.warn("添加拉流失败", e);
					}
					subMap.put("groupId", liveInfo.getGroup_id());
				}
			}
			return subMap;
		}
		return null;

	}

	public List<LiveRecordInfo> queryLiveRecordInfoBySellerIdAndType(Map<String, Object> params) {
		try {
			return anchorLiveRecordDao.queryLiveRecordInfoBySellerIdAndType(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @Description: 查询商家标签
	 * @author xiaoxiong
	 * @date 2016年11月16日
	 */
	public int lable(int sellerid, String uid, double range) {
		int lable = 0;
		try {
			if (!uid.equals("") && !uid.equals("null")) {
				// 是否消费过
				if (billCountBySelleridAndUid(sellerid, Integer.parseInt(uid)) > 0) {
					lable = 1;
				} else { // 是否收藏

					Map<String, Object> params = new HashMap<String, Object>();
					params.put("uid", uid);
					params.put("sellerid", sellerid);
					if (ursDao.isCollectSeller(params) > 0) {
						lable = 2;
					} else { // 是否浏览
						if (ursDao.queryBrowsedCountByUidAndSellerid(Integer.parseInt(uid), sellerid) > 0) {
							lable = 3;
						}
					}
				}
			}
			if (lable == 0) {
				if (range <= 1000) {/* 距离小于500M */
					lable = 4;
				} else {
					/* 如果商家有活动 */
					List<Map<String, Object>> activityList = queryActivityList(sellerid);
					if (activityList != null && activityList.size() > 0) {
						lable = 5;
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return lable;
	}

	/**
	 * 查寻商家秒杀活动
	 * 
	 * @author xiaoxiong
	 * @date 2016年12月26日
	 */
	public List<SellerCouponDetail> queryActivityKill(Map<String, Object> params) {
		try {
			return sellerDao.queryActivityKill(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 商家详情查询推荐菜
	 * 
	 * @author xiaoxiong
	 * @date 2017年1月12日
	 */
	public Object sellerDetailFoodList(SelleridPageRequest request) {
		try {
			Map<String, Object> params = new HashMap<>();
			params.put("pageNo", request.getPage());
			params.put("pageSize", request.getPageSize());
			params.put("sellerid", request.getSellerid());

			List<Food> list = foodDao.queryFoodBySellerid(params);
			List<SellerDetailFoodResponse> responseList = new ArrayList<>();

			if (list != null && list.size() > 0) {
				for (Food food : list) {
					SellerDetailFoodResponse sdr = new SellerDetailFoodResponse();
					sdr.setName(food.getFoodname());
					sdr.setId(food.getId());
					sdr.setUrl(fileUrl + food.getBigpic());
					responseList.add(sdr);
				}
			}
			Map<Object, Object> result = new HashMap<>();
			result.put("list", responseList);
			MapResponse response = new MapResponse(ResponseCode.SUCCESS, "成功");
			response.setResponse(result);

			return response;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new BaseResponse(ResponseCode.FAILURE, "查询商家推荐菜失败！");
	}

	/**
	 * 添加热搜词
	 */
	private void saveHotWords(String keyword, Integer cityId) {

		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("keyWord", keyword);
			params.put("areaId", cityId);
			HotWords hotWord = hotWordsDao.queryKeyWordByKeyWord(params);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if (hotWord == null) {
				hotWord = new HotWords();
				hotWord.setAreaId(cityId);
				hotWord.setUpdateTime(sdf.format(new Date()));
				hotWord.setHotWords(keyword);
				hotWord.setCreatedTime(sdf.format(new Date()));
				hotWord.setHotNum(1);
				hotWord.setHotStatus(1);
				hotWord.setHotType(2);
				hotWordsDao.insertSelective(hotWord);
			} else {
				if (hotWord.getHotType() == 1) {
					int orderCount = hotWord.getHotOrder() == null ? 1 : hotWord.getHotOrder() + 1;
					hotWord.setHotOrder(orderCount);
				} else {
					int hotNumCount = hotWord.getHotNum() == null ? 1 : hotWord.getHotNum() + 1;
					hotWord.setHotNum(hotNumCount);
				}
				hotWordsDao.updateByPrimaryKeySelective(hotWord);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("操作热搜词异常,请检查添加热搜词函数:saveHotWords()");
		}
	}

	/**
	 * 处理商铺信息
	 * 
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	private List<SearchResponse> initSellers(List<Map<String, Object>> sellers)
			throws IllegalAccessException, InvocationTargetException {

		List<SearchResponse> sellerList = new ArrayList<SearchResponse>();

		if (sellers.size() > 0) {

			for (Map<String, Object> seller : sellers) {
				// 获取商铺ID
				Integer sellerid = Integer.parseInt(seller.get("sellerid").toString());

				// 假设一个最短距离
				Double range = 3000.0;
				if (StringUtils.isNotEmpty(ObjectUtils.toString(seller.get("range")))) {
					range = Double.parseDouble(seller.get("range").toString());
				}
				// 实例一个查询结果对象
				SearchResponse sr = new SearchResponse();
				// 把一个map对象转换成一个sr对象
				BeanUtils.copyProperties(sr, seller);
				// 商铺标签
				int index = 0;
				if (Integer.parseInt(seller.get("bil_s_order").toString()) == 0) {
					index = 1;
				} else if (Integer.parseInt(seller.get("urs_s_order").toString()) == 0) {
					index = 2;
				} else if (Integer.parseInt(seller.get("bro_s_order").toString()) == 0) {
					index = 3;
				} else if (range <= 1000) {
					index = 4;
				} else {
					List<Map<String, Object>> activitys = sellerDao.queryActivityList(sellerid);
					if (activitys.size() > 0) {
						index = 5;
					}
				}

				// 1.设置商铺标签
				sr.setLable(index);

				// 2.格式化商铺信息
				formatSellerLiveRecord(seller, sr, sellerid);

				// 3.获取商铺图片
				formatSellerLogoPic(sr, sellerid);

				// 4.计算距离
				if (range < 1000) {
					sr.setRange(range + "m");
				} else {
					sr.setRange(ArithUtil.div(range, 1000) + "km");
				}

				// 商铺消费人数
				sr.setCount(getConsuCount(sr.getCount(), sellerid));

				sellerList.add(sr);
			}
		}

		return sellerList;
	}

	/**
	 * 格式化商铺信息
	 * 
	 * @param seller
	 * @param sr
	 * @param sellerid
	 */
	private void formatSellerLiveRecord(Map<String, Object> seller, SearchResponse sr, Integer sellerid) {

		/**
		 * 直播类型 -1 初始 0 预告 1 正在直播 2暂停直播 3 回放 4历史通告 5结束直播
		 */
		// 商铺是否在直播状态 0 否 1 是
		Integer islive = Integer.parseInt(seller.get("live_s_order").toString());

		switch (islive) {
		case 0:
			sr.setIsLive(1);// 是否有直播 0 否 1 是
			break;
		case 1:
			sr.setIsLive(0);// 是否有直播 0 否 1 是
			break;
		default:
			break;
		}

		// 有直播状态时
		List<LiveRecordInfo> lives = new ArrayList<LiveRecordInfo>();
		// 封装查询商铺直播状态数据
		Map<String, Object> livemap = new HashMap<String, Object>();
		livemap.put("page", 1);
		livemap.put("pageSize", 1);
		livemap.put("sellerid", sellerid);

		if (sr.getIsLive() == 1) {
			// 查询正在直播状态
			livemap.put("type", 1);// 直播类型 -1 初始 0 预告 1 正在直播 2暂停直播 3 回放 4历史通告
									// 5结束直播

			int type = 1;// 默认 为1

			do {
				try {
					lives = anchorLiveRecordDao.queryLiveRecordInfoBySellerIdAndType(livemap);
				} catch (Exception e) {
					e.printStackTrace();
					log.info("查询商铺直播记录信息异常,请查看处理商铺信息函数:initSellers()");
				}

				switch (type) {
				case 1:
					livemap.put("type", 0);// 直播类型 -1 初始 0 预告 1 正在直播 2暂停直播 3 回放
											// 4历史通告 5结束直播
					type = 2;
					break;
				case 2:
					livemap.put("type", 3);// 直播类型 -1 初始 0 预告 1 正在直播 2暂停直播 3 回放
											// 4历史通告 5结束直播
					type = 3;
					break;
				case 3:
					livemap.put("type", 2);// 直播类型 -1 初始 0 预告 1 正在直播 2暂停直播 3 回放
											// 4历史通告 5结束直播
					type = 4;
					break;
				default:
					break;
				}

				if (!lives.isEmpty()) {
					sr.setZhibo_type(type - 2);
					break;
				}
			} while (type < 4);
		}

		if (!lives.isEmpty()) {
			LiveRecordInfo liveRecoreInfo = lives.get(0);
			Map<String, Object> subMap = new HashMap<>();
			subMap.put("type", liveRecoreInfo.getZhibo_type());// 直播类型
			subMap.put("roomNo", liveRecoreInfo.getAnchor_room_no());// 房间号
			subMap.put("title", liveRecoreInfo.getZhibo_title());
			if (liveRecoreInfo.getZhibo_type() == 0) {
				sr.setZhibomark("主播 " + liveRecoreInfo.getNname() + " 即将直播");
			} else if (liveRecoreInfo.getZhibo_type() == 1) {
				sr.setZhibomark("主播 " + liveRecoreInfo.getNname() + " 正在直播");
			} else if (liveRecoreInfo.getZhibo_type() == 3) {
				sr.setZhibomark("主播 " + liveRecoreInfo.getNname() + " 来过");
			}
			subMap.put("anchorId", liveRecoreInfo.getAnchor_id());// 主播ID
			subMap.put("id", liveRecoreInfo.getId());// 记录ID
			subMap.put("avetar", fileUrl + liveRecoreInfo.getAvatar());// 封面
			subMap.put("liveType", liveRecoreInfo.getLive_start_type());// 直播类型
																		// 预告或自定义开播
			// 如果是正在直播的需要查询groupid进房间使用
			if (liveRecoreInfo.getZhibo_type() == 1) {
				LiverInfo liveInfo = null;
				try {
					liveInfo = liveUserDao
							.queryLiverInfoByAnchorId(Integer.parseInt(liveRecoreInfo.getAnchor_id().toString()));
				} catch (Exception e) {
					e.printStackTrace();
					log.info("查询主播信息异常,请查看处理商铺信息函数:initSellers()");
				}

				if (liveInfo != null) {
					subMap.put("groupId", liveInfo.getGroup_id());
				}
			}
			if (liveRecoreInfo.getVedio_url() != null) {
				subMap.put("url", liveRecoreInfo.getVedio_url());
			}
			sr.setSubSet(subMap);
		} else {
			livemap.put("type", 4);
			try {
				lives = anchorLiveRecordDao.queryLiveRecordInfoBySellerIdAndType(livemap);
			} catch (Exception e) {
				e.printStackTrace();
				log.info("查询商铺直播记录信息异常,请查看处理商铺信息函数:initSellers()");
			}
			if (!lives.isEmpty()) {
				sr.setZhibomark("主播 " + lives.get(0).getNname() + " 来过");
			}
		}
	}

	/**
	 * 格式化商铺logo图
	 * 
	 * @param sr
	 * @param sellerid
	 */
	private void formatSellerLogoPic(SearchResponse sr, Integer sellerid) {

		List<SellerPic> pics = new ArrayList<SellerPic>();

		int type = 1;// 默认为1

		do {
			// 封装查询商铺logo图数据
			Map<String, Object> params = new HashMap<>();
			params.put("fileUrl", fileUrl);
			params.put("sellerid", sellerid);
			params.put("type", type);

			try {
				pics = sellerDao.querySellerPicBySelleridAndType(params);
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (pics.isEmpty()) {
				type = 0;
			} else {
				sr.setUrl(pics.get(0).getUrl());
				break;
			}

		} while (type < 2);
	}

	/**
	 * V3.6.0 版本 寻蜜客签约店铺列表接口
	 * 
	 * @Title:queryNewSellerList
	 * @Description:V3.6.0 版本 寻蜜客签约店铺列表接口
	 * @param sellerListRequest
	 * @return Object 2017年5月2日下午3:27:06
	 */
	public Object queryNewSellerList(SellerListRequest sellerListRequest) {
		
		try {
			DecimalFormat df = new DecimalFormat("0.00");// 格式化double类型数据

			String sUid = xmerService.getUidBySUid(sellerListRequest.getSessiontoken(), sellerListRequest.getsUid());
			if(sUid == null || sUid.equals("")||sUid.equals("null")){
				return new BaseResponse(ResponseCode.FAILURE, "查询店铺列表信息失败,sessiontoken失效");
			}
			Integer uid = Integer.parseInt(sUid);
			Integer type = sellerListRequest.getType();
//			uid = 606777;
			
			Map<Object, Object> map = new HashMap<>();
			map.put("uid", uid);
			map.put("type", sellerListRequest.getType());
			map.put("page", sellerListRequest.getPage());
			map.put("limit", Constant.PAGE_LIMIT);

			/*
			 * 根据传入的 type 状态查询不同的列表
			 * type
			 *  1,已上线: isonline=1&&status = 3
			 *  2,待上线: status=1 or status=2 or status=3&&isonline!=1
			 * 	3,草稿箱: status=0 or status=4
			 * 
			 *  type=1时，还要查下级寻蜜客的 uid，查询下级寻蜜客的签约店铺
			 */
			
			List<Integer> uidList = ursEarningsRelationDao.queryRelationList(uid);
			map.put("uidList", uidList);
			
			//查询寻蜜客关系链中 上线店铺数量
			Integer readyNum = sellerDao.queryReadyNumByUids(uidList,uid);
			//查询待上线 店铺数量
			Integer waitNum = sellerDao.queryWaitNumByUid(uid);
			//查询草稿箱 店铺数量
			Integer draftNum = sellerDao.queryDraftNumByUid(uid);
			
			List<Map<Object, Object>> sellerList = sellerDao.queryNewSellerList(map);
			
			if (sellerList != null && sellerList.size() > 0) {
				
				//查询物料状态参数
			    Map<Object,Object> materialMap = new HashMap<>();
			    materialMap.put("uid", uid);
				
				for (Map<Object, Object> sellerMap : sellerList) {
					Integer isonline = Integer.parseInt(sellerMap.get("isonline")+"");
					Integer status = Integer.parseInt(sellerMap.get("status")+"");
					Integer xmerUid = Integer.parseInt(sellerMap.get("uid")+"");
					
					Double agio = Double.parseDouble(sellerMap.get("agio") + "");
					DecimalFormat df1 = new DecimalFormat("0.0");
					sellerMap.put("agio", df1.format(agio*10));
					
					if (type==1) {
						// 查询交易流水
						Double dealflow = billDao.queryTotalflow((Integer) sellerMap.get("sellerid"));
						BigDecimal b1 = new BigDecimal(dealflow);
						String dealf = df.format(b1.setScale(2, RoundingMode.HALF_UP).doubleValue());
						sellerMap.put("totalflow", dealf);

						// 查询本月流水
						Double mouthflow = billDao.queryMonthflow((Integer) sellerMap.get("sellerid"));
						BigDecimal b2 = new BigDecimal(mouthflow);
						String mouthf = df.format(b2.setScale(2, RoundingMode.HALF_UP).doubleValue());
						sellerMap.put("mouthflow", mouthf);
						
						//下级和下下级签约的 店铺 名称打码
						if(!xmerUid.equals(uid)){
							sellerMap.put("sellername", replaceSellerName(sellerMap.get("sellername")+""));
							sellerMap.put("isUnderXmer", 1);
						}
					}
					
					//非草稿箱中的 签约店铺，要查询购买物料状态,0 待支付 1 已支付（待发货） 2 取消支付/支付失败 3 已发货（待收货）4已收货（订单完成）
					if(type!=3){
						materialMap.put("mid", sellerMap.get("sellerid"));
						Map<Object,Object> materialOrderMap = sellerInfoDao.queryMaterialOrderBySellerId(materialMap);
						
						Integer mStatus = 0;
						String remark = "";
						String orderNo = "";
						if(materialOrderMap!=null && materialOrderMap.size()>0 ){
							mStatus = (Integer) materialOrderMap.get("status");
							remark = materialOrderMap.get("remark")+"";
							orderNo = materialOrderMap.get("order_sn")+"";
						}
						
						//非草稿箱的 签约店铺物料状态购买码
						Integer materialStatus = 1;	//默认1 购买中
						
						if(mStatus==null || mStatus==0 || mStatus==2){//待购买
							materialStatus = 0;
						}else if(mStatus==4){//购买成功
							materialStatus = 2;
						}
						sellerMap.put("materialStatus", materialStatus);
						sellerMap.put("remark", remark);
						sellerMap.put("orderNo", orderNo);
						
						
						//非草稿箱的 签约店铺状态码。 审核中，已通过，未通过，已上线
						Integer sellerStatus = 0; //默认0已通过
						if(isonline==1 && status == 3){//已上线
							sellerStatus = 1;
						}else if(status==1){//审核中
							sellerStatus = 2;
						}else if(status==2){//未通过
							sellerStatus = 3;
						}else if(isonline!=1 && status == 3){//已通过
							
						}
						sellerMap.put("sellerStatus", sellerStatus);
					}
					
					
					
				}
			}
			MapResponse response = new MapResponse(ResponseCode.SUCCESS, "查询成功");
			Map<Object, Object> resultMap = new HashMap<>();
			resultMap.put("sellers", sellerList);
			resultMap.put("readyNum", readyNum==null?0:readyNum);
			resultMap.put("waitNum", waitNum==null?0:waitNum);
			resultMap.put("draftNum", draftNum==null?0:draftNum);
			response.setResponse(resultMap);
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE, "查询店铺列表信息失败");
		}

	}
	
	public String replaceSellerName(String sellerName){
		if (sellerName == null || sellerName.trim().equals("")) {
			return "";
		}
		StringBuilder sellernameSB = new StringBuilder(sellerName);
		return sellernameSB.replace(1, sellernameSB.length(), "**").toString();
	}

	/**
	 * 查询所有连锁商店
	 */
	public BaseResponse findAllMultipleSeller(BaseRequest request) {
		try {

			List<Map<Object, Object>> sellers = sellerDao.findAllMultipleSeller();
			Map<Object, Object> result = new HashMap<Object, Object>();
			List<MultipleSeller> multipleSellers = new ArrayList<MultipleSeller>();
			for (int i = 0; i < sellers.size(); i++) {
				String sellername = sellers.get(i).get("sellername") == null ? "" : sellers.get(i).get("sellername").toString().trim();
				String sellerid = sellers.get(i).get("sellerid") == null ? "" : sellers.get(i).get("sellerid").toString().trim();
				if (sellername.equals("")) {
					continue;
				}
				MultipleSeller multipleSeller = new MultipleSeller();
				multipleSeller.setId(Integer.parseInt(sellerid));
				multipleSeller.setName(sellername);
				multipleSellers.add(multipleSeller);
			}
			result.put("multipleSellers", multipleSellers);
			MapResponse mapResponse = new MapResponse(ResponseCode.SUCCESS, "查询成功");
			mapResponse.setResponse(result);
			return mapResponse;
		} catch (Exception e) {
			e.printStackTrace();
			log.warn("查询所有连锁商店失败，", e);
			return new BaseResponse(ResponseCode.FAILURE, "查询所有连锁商店信息失败");
		}
	}

}
