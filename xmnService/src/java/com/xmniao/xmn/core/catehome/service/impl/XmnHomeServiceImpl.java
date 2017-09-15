package com.xmniao.xmn.core.catehome.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.Point;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Order;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sun.istack.internal.logging.Logger;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.MongoBaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.catehome.entity.mongo.MSeller;
import com.xmniao.xmn.core.catehome.entity.mongo.XmnSeller;
import com.xmniao.xmn.core.catehome.service.XmnHomeService;
import com.xmniao.xmn.core.common.Constant;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.AreaVersionRequest;
import com.xmniao.xmn.core.common.request.XmnHomeRequest;
import com.xmniao.xmn.core.common.request.catehome.CityRequest;
import com.xmniao.xmn.core.common.request.catehome.CustomisedRequest;
import com.xmniao.xmn.core.common.request.catehome.LocationRequest;
import com.xmniao.xmn.core.common.request.catehome.SearchLocationRequest;
import com.xmniao.xmn.core.common.request.catehome.SwitchPositionRequest;
import com.xmniao.xmn.core.common.request.catehome.UneatRequest;
import com.xmniao.xmn.core.common.request.catehome.UserConsumeSellerRequest;
import com.xmniao.xmn.core.common.request.seller.RecomSellerRequest;
import com.xmniao.xmn.core.live.dao.AnchorLiveRecordDao;
import com.xmniao.xmn.core.live.dao.BusinessDao;
import com.xmniao.xmn.core.seller.dao.BannerDao;
import com.xmniao.xmn.core.seller.dao.TradeDao;
import com.xmniao.xmn.core.seller.service.ChooseSellerService;
import com.xmniao.xmn.core.seller.service.InterestSellerService;
import com.xmniao.xmn.core.seller.service.PopularSellerService;
import com.xmniao.xmn.core.seller.service.SameTasteSellerService;
import com.xmniao.xmn.core.seller.service.SellerService;
import com.xmniao.xmn.core.seller.service.UneatSellerService;
import com.xmniao.xmn.core.util.Base64;
import com.xmniao.xmn.core.util.PropertiesUtil;
import com.xmniao.xmn.core.xmer.dao.AreaDao;
import com.xmniao.xmn.core.xmer.dao.SellerInfoDao;
import com.xmniao.xmn.core.xmer.entity.Area;

/**
 * 
* @projectName: xmnService 
* @ClassName: XmnHomeServiceImpl    
* @Description:寻蜜鸟首页service实现类 
* @author: liuzhihao   
* @date: 2016年11月9日 下午3:01:12
 */
@Service
public class XmnHomeServiceImpl implements XmnHomeService{
	
	private final Logger log = Logger.getLogger(XmnHomeServiceImpl.class);
	
	@Autowired
	private String fileUrl;

	/**
	 * 注入广告图片dao
	 */
	@Autowired
	private BannerDao bannerDao;
	
	@Autowired
	private AreaDao areaDao;
	
	@Autowired
	private BusinessDao businessDao;
	
	//分类dao
	@Autowired
	private TradeDao tradeDao;
	
	@Autowired
	private SellerInfoDao sellerInfoDao;
	
	@Autowired
	private AnchorLiveRecordDao anchorLiveRecordDao;
	
	@Autowired
	private PropertiesUtil propertiesUtil;
	
	@Autowired
	private SessionTokenService sessionTokenService;
	
	@Resource
	private MongoTemplate mongoTemplate;
	
	@Autowired
	private SellerService sellerService;
	
	@Autowired
	private ChooseSellerService chooseSellerService;
	
	@Autowired
	private PopularSellerService popularSellerService;
	
	@Autowired
	private InterestSellerService interestSellerService;
	
	@Autowired
	private SameTasteSellerService sameTasteSellerService;
	
	@Autowired
	private UneatSellerService uneatSellerService ;

	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	/**
	 * 查询定位信息
	 * @param locationRequest
	 * @return
	 */
	@Override
	public Object getLocation(LocationRequest locationRequest) {
		try{
			//通过定位获取距离最近的商圈信息
			Map<Object,Object> locationMap = new HashMap<Object,Object>();
			locationMap.put("lon", locationRequest.getLon());//经度
			locationMap.put("lat", locationRequest.getLat());//纬度
			List<Map<Object,Object>> list = businessDao.selectByLonAndLat(locationMap);
			if(list != null && !list.isEmpty()){
				Map<Object, Object> one = list.get(0);
				locationMap.put("area_id", one.get("provinceId"));
				Map<Object, Object> provinceMap = businessDao.findAreaByAreaId(locationMap);
				String provinceName = provinceMap != null && provinceMap.get("title") != null ? provinceMap.get("title").toString() : "";
				one.put("provinceName", provinceName);
				//按照距离用户最近的那一个商圈作为用户定位的位置
				MapResponse response = new MapResponse(ResponseCode.SUCCESS,"定位成功");
				//所以取集合中的第一个
				response.setResponse(one);
				return response;
			}
			return new BaseResponse(ResponseCode.FAILURE,"定位失败");
		}catch(Exception e){
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE,"定位异常");
		}
	}


	/**
	 * 切换定位
	 */
	@Override
	public Object switchPosition(SwitchPositionRequest switchPositionRequest) {
		try{
			Map<Object,Object> map = new HashMap<Object,Object>();
			//通过用户的坐标获取定位商圈
			Map<Object,Object> queryLocationMap = new HashMap<Object,Object>();
			queryLocationMap.put("lon", switchPositionRequest.getLon());//用户坐标经度
			queryLocationMap.put("lat", switchPositionRequest.getLat());//用户坐标纬度
			queryLocationMap.put("cityId", switchPositionRequest.getCityId());//城市id
			//查询距离用户定位最近的多个商圈
			List<Map<Object,Object>> locations = businessDao.selectByLonAndLat(queryLocationMap);
			List<Map<Object,Object>> others = new ArrayList<Map<Object,Object>>();
			if(locations != null && !locations.isEmpty()){
				for(int i=0; i<locations.size(); i++){
					if(i==0){
						//默认用户定位的商圈
						map.put("zoneName", locations.get(i).get("zoneName"));//默认定位的商圈名称
						map.put("zoneId", locations.get(i).get("zoneId"));//默认的商圈id
						map.put("cityId", locations.get(i).get("cityId"));//默认的商圈城市
						map.put("cityName", locations.get(i).get("cityName"));
						map.put("areaId", locations.get(i).get("areaId"));
						map.put("areaName", locations.get(i).get("areaName"));
						map.put("provinceId", locations.get(i).get("provinceId"));
						map.put("lon", locations.get(i).get("lon"));
						map.put("lat", locations.get(i).get("lat"));
					}else{
						//用于用户选择的商圈
						Map<Object,Object> other = new HashMap<Object,Object>();
						other.put("zoneName", locations.get(i).get("zoneName"));//默认定位的商圈名称
						other.put("zoneId", locations.get(i).get("zoneId"));//默认的商圈id
						other.put("cityId", locations.get(i).get("cityId"));//默认的商圈城市
						other.put("cityName", locations.get(i).get("cityName"));
						other.put("areaId", locations.get(i).get("areaId"));
						other.put("areaName", locations.get(i).get("areaName"));
						other.put("provinceId", locations.get(i).get("provinceId"));
						other.put("lon", locations.get(i).get("lon"));
						other.put("lat", locations.get(i).get("lat"));
						others.add(other);
					}
				}
				
				map.put("others", others);
			}
			MapResponse response = new MapResponse(ResponseCode.SUCCESS,"查询成功");
			response.setResponse(map);
			return response;
		}catch(Exception e){
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE,"切换异常");
		}
	}

	/**
	 * 搜索定位
	 */
	@Override
	public Object searchLocation(SearchLocationRequest searchLocationRequest) {
		Map<Object,Object> map = new HashMap<Object,Object>();
		//查询用户搜索的商圈信息
		Map<Object,Object> queryMap = new HashMap<Object,Object>();
		queryMap.put("pid", searchLocationRequest.getCityId());//城市id
		queryMap.put("title", searchLocationRequest.getZoneName());//用户输入搜索的商圈名称
		//匹配先关的商圈信息
		List<Map<Object,Object>> searchLocations = businessDao.searchLocationByTitle(queryMap);
		map.put("locations", searchLocations);
		MapResponse response = new MapResponse(ResponseCode.SUCCESS,"搜索成功");
		response.setResponse(map);
		return response;
	}

	/**
	 * 专属口味
	 */
	@Override
	public Object customisedForm(CustomisedRequest customisedRequest) {
		try{
			Map<Object,Object> map = new HashMap<Object,Object>();
			//通过配置文件获取用户消费区间
			String price = "";
			//那些人在吃
			String eats = "";
			try {
				price = propertiesUtil.getValue("customPrice", "conf_common.properties");
				
				eats = propertiesUtil.getValue("eatTegether", "conf_common.properties");
			} catch (IOException e) {
				e.printStackTrace();
				price = "["+"{"+"minPrice:"+0+","+"maxPrice:"+0+"}"+","
						+"{"+"minPrice:"+1+","+"maxPrice:"+50+"}"+","
						+"{"+"minPrice:"+51+","+"maxPrice:"+100+"}"+","
						+"{"+"minPrice:"+101+","+"maxPrice:"+150+"}"+","
						+"{"+"minPrice:"+151+","+"maxPrice:"+200+"}"+","
						+"{"+"minPrice:"+201+","+"maxPrice:"+250+"}"+","
						+"{"+"minPrice:"+251+","+"maxPrice:"+300+"}"+","+"]"; 
				
				
				eats = "["+"{"+"type:"+0+","+"title:"+"不限"+"}"+","
					+"{"+"type:"+1+","+"title:"+"主播在吃"+"}"+","
					+"{"+"type:"+2+","+"title:"+"我关注的人在吃"+"}"+","
					+"{"+"type:"+3+","+"title:"+"与我相似的人在吃"+"}"+"]";
			}
			
			if(StringUtils.isNotEmpty(price)){
				List<Map<Object,Object>> pricelist = new ArrayList<Map<Object,Object>>();
				//解析字符串
				JSONArray json = JSONObject.parseArray(price);
				for(int i=0; i<json.size(); i++){
					Map<Object,Object> pricemap = new HashMap<Object,Object>();
					JSONObject object = json.getJSONObject(i);
					String minPrice = object.getString("minPrice");
					String maxPrice = object.getString("maxPrice");
					pricemap.put("minPrice", minPrice);//最低价格
					pricemap.put("maxPrice", maxPrice);//最高价格
					
					pricelist.add(pricemap);
				}
				
				if(pricelist != null && !pricelist.isEmpty()){
					map.put("price", pricelist);
				}
			}
			
			if(StringUtils.isNotEmpty(eats)){
				
				List<Map<Object,Object>> eatlist = new ArrayList<Map<Object,Object>>();
				//解析字符串
				JSONArray json = JSONObject.parseArray(eats);
				for(int i=0; i<json.size(); i++){
					Map<Object,Object> eatmap = new HashMap<Object,Object>();
					JSONObject object = json.getJSONObject(i);
					String type = object.getString("type");
					String title = object.getString("title");
					eatmap.put("type", type);//最低价格
					eatmap.put("title", title);//最高价格
					
					eatlist.add(eatmap);
				}
				
				if(eatlist != null && !eatlist.isEmpty()){
					map.put("eat", eatlist);
				}
				
			}
			//查询当前所有分类
			
			List<Map<Object,Object>> tradelist = new ArrayList<Map<Object,Object>>();
			List<Map<Object,Object>> trades = tradeDao.findAllTradeByDeliciousFood();
			if(trades != null && !trades.isEmpty()){
				Map<Object,Object> trademap = new HashMap<Object,Object>();
				trademap.put("tid",0);
				trademap.put("tradename","不限");
				trademap.put("pid",1);
				trades.add(trademap);
				tradelist.add(trademap);
				for(Map<Object,Object> trade :trades){
					tradelist.add(trade);
				}
				map.put("trades", tradelist);//美食分类
			}
			MapResponse response = new MapResponse(ResponseCode.SUCCESS,"查询成功");
			response.setResponse(map);
			return response;
		}catch(Exception e){
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE,"获取异常");
		}
	}


	@Override
	public Object switchCity(AreaVersionRequest areaVersionRequest) {
		
		/**
		 * 需求:显示所有存在商铺的城市名称
		 * 1.查询商铺信息,对商铺的城市ID进行去重,获取去重后的城市ID集合:citys
		 * 
		 * 2.查询城市信息从1中获取的citys中
		 */
		Map<Object,Object> map = new HashMap<Object,Object>();
		
		//版本号：初始版本:v1.1.1 如果更新了区域 则 版本为 v1.1.2 如果更新了城市 则版本为 v1.2.1 如果更新的省 则版本为 v2.1.1
		stringRedisTemplate.opsForValue().set(Constant.AREA_IS_UPDATE_KEY,"version7");
		String areaversion = ObjectUtils.toString(sessionTokenService.getStringForValue(Constant.AREA_IS_UPDATE_KEY));
		
		/**
		 * 1.判断区域更新版本
		 */
		if(StringUtils.isNotEmpty(areaversion)){
			
			//判断版本是否一致,是 则无需更新,反之则需更新
			if(areaversion.equals(areaVersionRequest.getAreaversion())){
				map.put("isUpdate", 0);//无更新
				map.put("areaversion", areaversion);
				MapResponse response = new MapResponse(ResponseCode.SUCCESS,"查询成功,无需更新");
				response.setResponse(map);
				return response;
			}else{
				//获取城市列表
				map = getCityList(areaVersionRequest.getIsAll());
				if(map.isEmpty()){
					return new BaseResponse(ResponseCode.FAILURE, "获取城市列表异常");
				}
				map.put("isUpdate", 1);//有更新
				map.put("areaversion", areaversion);
				MapResponse response = new MapResponse(ResponseCode.SUCCESS, "查询成功,需要更新");
				response.setResponse(map);
				return response;
			}
			
		}else{
			//首次调取城市列表查询函数时,默认有更新
			map = getCityList(areaVersionRequest.getIsAll());
			
			if(map.isEmpty()){
				return new BaseResponse(ResponseCode.FAILURE, "获取城市列表异常");
			}
			map.put("isUpdate", 1);//有更新
			map.put("areaversion", areaversion);
			MapResponse response = new MapResponse(ResponseCode.SUCCESS, "查询成功,需要更新");
			response.setResponse(map);
			return response;
		}
	}
	
	/**
	 * 获取城市列表
	 * @return
	 */
	private Map<Object,Object> getCityList(Integer isAll){
		//结果缓存区
		Map<Object,Object> map = new HashMap<Object,Object>();
		try{
			//结果缓存区
			List<Map<Object,Object>> result = new ArrayList<Map<Object,Object>>();
			List<Integer> citys = new ArrayList<Integer>();
			if(isAll==null || isAll!=1){
				//查询存在商铺的城市ID
				 citys = sellerInfoDao.findAllCitysDistinst();
				if(citys.isEmpty()){
					citys = new ArrayList<Integer>();
				}
			}
			//查询城市列表
			List<Area> areas = areaDao.findAllCity(citys);
			
			if(areas != null && !areas.isEmpty()){
				for(Area area : areas){
					Map<Object,Object> areamap = new HashMap<Object,Object>();
					
					areamap.put("cityid", area.getAreaId());//城市id
					areamap.put("cityname", area.getTitle());//城市名称
					areamap.put("pid", area.getPid());//父id
					areamap.put("regionid", area.getRegionId()==null?"":area.getRegionId());//区号
					areamap.put("firstletter", area.getFirstletter());//区域首字母
					areamap.put("hot", area.getHot());
					result.add(areamap);
				}
				map.put("area", result);
			}
		}catch(Exception e){
			e.printStackTrace();
			log.info("查询城市ID异常,请查看获取城市列表函数:getCityList()");
		}
		return map;
	}
	
	/**
	 * 
	 */
	@Override
	public Object queryBusinessByArea(CustomisedRequest customisedRequest) {
		try{
			Map<Object,Object> map = new HashMap<Object,Object>();
			List<Map<Object,Object>> result = new ArrayList<Map<Object,Object>>();//区集合
			
			
			//通过城市ID获取所有的区域
			List<Area> areas = areaDao.findAllByPid(customisedRequest.getCityId());
			if(areas != null && !areas.isEmpty()){
				//查询城市所有商圈
				Map<Object,Object> businessmap = new HashMap<Object,Object>();
				businessmap.put("lat", customisedRequest.getLat());
				businessmap.put("lon", customisedRequest.getLon());
				for(Area area : areas){
					List<Map<Object,Object>> bresult = new ArrayList<Map<Object,Object>>();//商圈集合
					Map<Object,Object> areamap = new HashMap<Object,Object>();
					areamap.put("areaId", area.getAreaId());
					areamap.put("areaName", area.getTitle());
					businessmap.put("id", area.getAreaId());
					
					//查询区域内所有的商铺数量
					int tatolCounts = sellerInfoDao.sumSellerCountsByBid(businessmap);
					areamap.put("tatolCounts", tatolCounts);//区域内商铺的总数量
					
					List<Map<Object,Object>> business = businessDao.findAllByAreaId(businessmap);
					if(business != null && !business.isEmpty()){
						for(Map<Object,Object> busines : business){
							//通过商圈id获取商圈内商铺数量
							businessmap.put("id", busines.get("zoneId"));
							int counts = sellerInfoDao.sumSellerCountsByBid(businessmap);
							busines.put("counts", counts);//商圈内商铺数量
						}
						Map<Object,Object> allmap = new HashMap<Object,Object>();
						allmap.put("areaid", "");
						allmap.put("counts", tatolCounts);
						allmap.put("lat", "");
						allmap.put("lon", "");
						allmap.put("ranges", "");
						allmap.put("zoneId", "");
						allmap.put("zoneName", "全部");
						
						bresult.add(allmap);//全部
						bresult.addAll(business);
						areamap.put("zones", bresult);//商圈						
					}
					
					result.add(areamap);
				}
				map.put("zones", result);
			}
			MapResponse response = new MapResponse(ResponseCode.SUCCESS,"查询成功");
			response.setResponse(map);
			return response;
		}catch(Exception e){
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE,"查询异常");
		}
	}


	/**
	 * 我消费过的店铺
	 * */
	@Override
	public Object queryUserConsumeSellerList(UserConsumeSellerRequest userConsumeSellerRequest) {
		MapResponse response = null;
		//最终返回的map集合
		Map<Object, Object> resultMap = new HashMap<Object, Object>();
		//最终返回的list集合
		List<Map<Object, Object>> resultList = new ArrayList<Map<Object, Object>>();
		List<MSeller> mSellers = new ArrayList<MSeller>();
		//验证token
//		String uid = userConsumeSellerRequest.getUid().toString();
		String tuid = ObjectUtils.toString(sessionTokenService.getStringForValue(userConsumeSellerRequest.getSessiontoken()));
		Integer uid = null;
		if(StringUtils.isEmpty(tuid)){
			resultMap.put("sellerList", new ArrayList<>());
			response = new MapResponse(ResponseCode.SUCCESS, "操作成功");
			response.setResponse(resultMap);
			return response;
		}else{
			uid = Integer.parseInt(tuid);
		}
		Criteria criteria = new Criteria();
		criteria.and("uid").is(uid);
		criteria.and("operate").is(1);
		
		Query query = new Query(criteria);
		
		//查询用户消费过的商铺id
		Set<String> sellers = new HashSet<String>();
		
		
		List<XmnSeller> sellerlist = new ArrayList<XmnSeller>();
		try {
			sellerlist = mongoTemplate.find(query, XmnSeller.class,propertiesUtil.getValue("xmnSeller", "conf_common.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(!sellerlist.isEmpty()){
			for(XmnSeller xmnSeller : sellerlist){
				sellers.add(ObjectUtils.toString(xmnSeller.getSellerid()));
			}
		}
		
		//查询商铺信息
		if(!sellers.isEmpty()){
			Criteria sellerCriteria = new Criteria();
			sellerCriteria.and("status").is(3).and("isonline").is(1).and("is_public").is(1);
			sellerCriteria.and("sellerid").in(sellers);
			sellerCriteria.and("coordinate").near(new Point(userConsumeSellerRequest.getLatitude(), userConsumeSellerRequest.getLongitude()));
			
			Query sellerQuery = new Query(sellerCriteria);
			
			sellerQuery.sort().on("weights", Order.DESCENDING);
			sellerQuery.skip((userConsumeSellerRequest.getPage()-1)*Constant.PAGE_LIMIT).limit(Constant.PAGE_LIMIT);
			try{
				mSellers = 
					mongoTemplate.find(sellerQuery, MSeller.class,propertiesUtil.getValue("seller", "conf_common.properties"));
			}catch(Exception e){
				e.printStackTrace();
			}
			if(!mSellers.isEmpty()){
				resultList = sellerService.getSellers(mSellers, userConsumeSellerRequest.getLongitude(), userConsumeSellerRequest.getLatitude(), uid);
			}else{
				//查询当前定位消费量最高的
				Criteria consumCta = new Criteria();
				sellerCriteria.and("status").is(3).and("isonline").is(1).and("is_public").is(1);
				sellerCriteria.and("coordinate").near(
					new Point(userConsumeSellerRequest.getLatitude(), userConsumeSellerRequest.getLongitude()));
				
				Query consumQuery = new Query(consumCta);
				
				consumQuery.sort().on("consumption", Order.DESCENDING).on("weights", Order.DESCENDING);
				
				consumQuery.skip((userConsumeSellerRequest.getPage()-1)*Constant.PAGE_LIMIT).limit(Constant.PAGE_LIMIT);
				try{
					mSellers =  
						mongoTemplate.find(consumQuery, MSeller.class,propertiesUtil.getValue("seller", "conf_common.properties"));
				}catch(Exception e){
					e.printStackTrace();
				}
				
				if(!mSellers.isEmpty()){
					resultList = sellerService.getSellers(mSellers, userConsumeSellerRequest.getLongitude(), userConsumeSellerRequest.getLatitude(), uid);
				}
				
			}
			
		}else{
			//查询当前定位消费量最高的
			Criteria cta = new Criteria();
			cta.and("status").is(3).and("isonline").is(1).and("is_public").is(1);
			cta.and("coordinate").near(
				new Point(userConsumeSellerRequest.getLatitude(), userConsumeSellerRequest.getLongitude()));
			
			Query q = new Query(cta);
			
			q.sort().on("consumption", Order.DESCENDING).on("weights", Order.DESCENDING);
			
			q.skip((userConsumeSellerRequest.getPage()-1)*Constant.PAGE_LIMIT).limit(Constant.PAGE_LIMIT);
			try{
				mSellers =  
					mongoTemplate.find(q, MSeller.class,propertiesUtil.getValue("seller", "conf_common.properties"));
			}catch(Exception e){
				e.printStackTrace();
			}
			
			if(!mSellers.isEmpty()){
				resultList = sellerService.getSellers(mSellers, userConsumeSellerRequest.getLongitude(), userConsumeSellerRequest.getLatitude(), uid);
			}
		}
		
		response = new MapResponse(ResponseCode.SUCCESS, "查询成功");
		
		resultMap.put("sellers", resultList);
		
		response.setResponse(resultMap);
		return response;
	}
	
	protected Criteria getCustomisedCriteria(RecomSellerRequest recomSellerRequest) {
		
		Criteria criteria = new Criteria();
		
		criteria.and("status").is(3).and("isonline").is(1).and("is_public").is(1);
		
		if(recomSellerRequest.getMinprice() != null && recomSellerRequest.getMaxprice() != null){
			criteria.and("consume").gte(recomSellerRequest.getMinprice()).lte(recomSellerRequest.getMaxprice());
		}
		
		if(recomSellerRequest.getTradeid() != null){
			criteria.and("genre").is(recomSellerRequest.getTradeid());
		}
		
		if(recomSellerRequest.getZoneid() != null){
			criteria.and("zoneid").is(recomSellerRequest.getZoneid());
		}
		
		if(recomSellerRequest.getLat() != null && recomSellerRequest.getLon() != null){
			criteria.and("coordinate").near(new Point(recomSellerRequest.getLat(), recomSellerRequest.getLon()));
		}
		return criteria;
	}
	
	protected Query getCustomisedQuery(Criteria criteria,RecomSellerRequest recomSellerRequest) {
		
		Query query = new Query(criteria);
		
		query.sort().on("weights", Order.DESCENDING);
		query.skip((recomSellerRequest.getPage()-1)*recomSellerRequest.getPageSize()).limit(recomSellerRequest.getPageSize());
		
		return query;
	}


	/**
	 * 首页banenr图接口实现类
	 */
	@Override
	public Object getBannerList(CityRequest cityRequest) {
		Map<Object,Object> map = new HashMap<Object,Object>();
		List<Map<Object,Object>> bannerlist = new ArrayList<Map<Object,Object>>();
		//通过城市id查询广告轮播图
		Map<Object,Object> bannermap = new HashMap<Object,Object>();
		String position = "1";
		bannermap.put("city", cityRequest.getCityid());
		bannermap.put("position", position);//banner位置信息:1用户首页，2积分商城主页，3寻蜜客主页,100商户经营页
		List<Map<Object,Object>> banners = new ArrayList<Map<Object,Object>>();
		try{
			 banners = bannerDao.findAllByCity(bannermap);
		}catch(Exception e){
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE,"查询城市banner图异常");
		}
		
		if(banners == null || banners.isEmpty()){
			try{
				banners = bannerDao.findAll(position);//banner位置信息:1用户首页，2积分商城主页，3寻蜜客主页,100商户经营页
			}catch(Exception e){
				e.printStackTrace();
				return new BaseResponse(ResponseCode.FAILURE,"查询全国banner图异常");
			}
			
		}
		if(banners != null && !banners.isEmpty()){
			for(Map<Object,Object> banner : banners){
				if(Integer.parseInt(banner.get("bannerStyle").toString()) == 2){
					Map<Object,Object> image = new HashMap<Object,Object>();
					image.put("id", ObjectUtils.toString(banner.get("id")));//图片ID
					image.put("bannerStyle", ObjectUtils.toString(banner.get("bannerStyle")));//图片摆放风格 0图片横排一格，1图片横排两格
					image.put("sort", ObjectUtils.toString(banner.get("sort")));//排序，越大，越优先展示
					String contents = Base64.getFromBase64(ObjectUtils.toString(banner.get("objJson")));//解密
					JSONArray jsonArr = JSON.parseArray(contents);
					for(int i=0; i<jsonArr.size(); i++){
						Map<Object,Object> picmap = new HashMap<Object,Object>();
						String content = JSON.parseObject(jsonArr.getString(i)).getString("content");
						String logRequired = JSON.parseObject(jsonArr.getString(i)).getString("logRequired");
						String picUrl = fileUrl+JSON.parseObject(jsonArr.getString(i)).getString("pic_url");
						String type = JSON.parseObject(jsonArr.getString(i)).getString("type");
						picmap.put("id", ObjectUtils.toString(banner.get("id")));
						picmap.put("content", content);
						picmap.put("logRequired", logRequired);
						picmap.put("picUrl", picUrl);
						picmap.put("sort", ObjectUtils.toString(banner.get("sort")));
						picmap.put("type", type);
						bannerlist.add(picmap);
					}
				}
			}
			map.put("banners", bannerlist);
		}
		
		MapResponse response = new MapResponse(ResponseCode.SUCCESS,"查询成功");
		response.setResponse(map);
		return response;
	}


	@Override
	public Object getLiveList(CityRequest cityRequest) {
		Map<Object,Object> map = new HashMap<Object,Object>();
		Criteria[] criterialist = new Criteria[3];
		criterialist[0] = new Criteria().and("is_live").is(1);
		criterialist[1] = new Criteria().and("is_fans_coupon").is(1);
		criterialist[2] = new Criteria().and("is_advance").is(1);
		
		Criteria criteria = Criteria.where("city").is(cityRequest.getCityid());//按城市查询
		criteria.and("status").is(3).and("isonline").is(1).and("is_public").is(1);
		criteria.and("coordinate").near(new Point(cityRequest.getLon(),cityRequest.getLat()));
		criteria.orOperator(criterialist);
		
		
		Query query = new Query(criteria);
		query.sort().on("weights", Order.DESCENDING);
		query.skip(0).limit(4);
		
		List<MSeller> lives = new ArrayList<MSeller>();
		try{
			lives = 
				mongoTemplate.find(query, MSeller.class,propertiesUtil.getValue("seller", "conf_common.properties"));
		}catch(Exception e){
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE,"查询主播去哪儿了异常");
		}
			
		if(lives != null && !lives.isEmpty()){
			List<Map<Object,Object>> livelist = new ArrayList<Map<Object,Object>>();
			for(MSeller live : lives){
				Map<Object,Object> livemap = new HashMap<Object,Object>();
				livemap.put("sellerid", live.getSellerid());
				livemap.put("sellername", ObjectUtils.toString(live.getSellername()));
				Map<Object,Object> querylivemap = new HashMap<Object,Object>();
				querylivemap.put("sellerid", live.getSellerid());
				if(live.getIs_live() == 1){
					//正在直播
					querylivemap.put("type", 1);
					Map<Object,Object> map1 = anchorLiveRecordDao.findLiveRecordBySellerId(querylivemap);
					if(map1 != null && !map1.isEmpty()){
						livemap.put("id", map1.get("id"));
						livemap.put("nname", map1.get("nname")+"即将去试吃");
						livemap.put("zhiboType", map1.get("zhiboType"));
					}
				}else if(live.getIs_advance() == 1){
					//预告
					querylivemap.put("type", 0);
					Map<Object,Object> map2 = anchorLiveRecordDao.findLiveRecordBySellerId(querylivemap);
					if(map2 != null && !map2.isEmpty()){
						livemap.put("id", map2.get("id"));
						livemap.put("nname", map2.get("nname")+"即将去试吃");
						livemap.put("zhiboType", map2.get("zhiboType"));
					}
				}
				livelist.add(livemap);
			}
			map.put("lives", livelist);
		}
		
		MapResponse response = new MapResponse(ResponseCode.SUCCESS,"查询成功");
		response.setResponse(map);
		return response;
	}


	@SuppressWarnings("unchecked")
	@Override
	public Object getSellerList(XmnHomeRequest xmnHomeRequest) {
		
		Map<Object,Object> map = new HashMap<Object,Object>();
		Map<Object,Object> source = new HashMap<Object,Object>();
		
		List<Integer> sellers = new ArrayList<Integer>();
		//随机九个帮你挑选的店铺
		Map<String,Object> choosesMap = getChooseSellers(xmnHomeRequest);
		List<Map<Object,Object>> chooses = (List<Map<Object,Object>>)choosesMap.get("chooses");
		if(chooses != null && !chooses.isEmpty()){
			for(Map<Object,Object> choose : chooses){
				sellers.add(Integer.parseInt(choose.get("sellerid").toString()));
			}
			map.put("chooses", chooses);
			source.put("chooses", choosesMap.get("source"));
		}
		
		//随机九个常去的店铺
		Map<String,Object> popularmap = getPopularSellers(xmnHomeRequest,sellers);
		List<Map<Object,Object>> populars = (List<Map<Object, Object>>) popularmap.get("popluars");
		if(populars != null && !populars.isEmpty()){
			for(Map<Object,Object> popular : populars){
				sellers.add(Integer.parseInt(popular.get("sellerid").toString()));
			}
			map.put("populars", populars);
			source.put("populars", popularmap.get("source"));
		}
		
		//随机九个感兴趣的店铺
		Map<String,Object> interestmap = getInterestSellers(xmnHomeRequest,sellers);
		List<Map<Object,Object>> interests = (List<Map<Object, Object>>) interestmap.get("interests");
		if(interests != null && !interests.isEmpty()){
			for(Map<Object,Object> interest : interests){
				sellers.add(Integer.parseInt(interest.get("sellerid").toString()));
				
			}
			map.put("interests", interests);
			source.put("interests", interestmap.get("source"));
		}
		
		map.put("source", source);
		
		if(sellers != null && !sellers.isEmpty()){
			StringBuffer sb = new StringBuffer();
			for(Integer id: sellers){
				sb.append(id).append(",");
			}
			
			String str = sb.toString();
			
			map.put("sellerids", str.substring(0,str.lastIndexOf(",")));
		}
		
		MapResponse response = new MapResponse(ResponseCode.SUCCESS,"查询成功");
		response.setResponse(map);
		return response;
	}

	protected Map<String,Object> getChooseSellers(XmnHomeRequest xmnHomeRequest) {
		Map<String,Object> map = new HashMap<String,Object>();
		
		List<Map<Object,Object>> result = new ArrayList<Map<Object,Object>>();
		String tuid = ObjectUtils.toString(sessionTokenService.getStringForValue(xmnHomeRequest.getSessiontoken()));
		Integer uid = null;
		if(StringUtils.isNotEmpty(tuid)){
			uid = Integer.parseInt(tuid);
		}
		Integer zoneid = xmnHomeRequest.getZoneid();
		Double lon = xmnHomeRequest.getLon();
		Double lat = xmnHomeRequest.getLat();
		Integer pageNo = 1;
		Integer pageSize = 4;
		Integer cityid = xmnHomeRequest.getCityid();
		
		//计算帮你挑选的商铺的总数
		Long counts = chooseSellerService.sumChooseCounts(uid, zoneid, null);

		if(counts<pageSize){
			pageNo=1;
		}else{
			pageNo =  (int) (counts.longValue()/pageSize);
			Random  ran = new Random();
			pageNo = ran.nextInt(pageNo);
			if(pageNo <= 0){
				pageNo = 1;
			}
		}
		List<MSeller> mSellers = new ArrayList<MSeller>();
		if(StringUtils.isNotEmpty(tuid)){
			MongoBaseResponse<MSeller> mbr = null;
			
			if(zoneid != null){
				mbr = 
					chooseSellerService.queryChooseSellers(null, uid, lat, lon, cityid, zoneid, null, pageNo, pageSize, null,null);
				map.put("source", 1);//数据来源商圈
			}
			
			if(mbr == null || mbr.getResult().isEmpty()){
				mbr = 
					chooseSellerService.queryChooseSellers(null, uid, lat, lon, cityid, null, null, pageNo, pageSize, null,null);
				map.put("source", 2);//数据来源省市
			}

			
			if(mbr != null){
				mSellers = mbr.getResult();
			}
			
		}else{
			
		
			if(zoneid != null){
				mSellers = 
					 chooseSellerService.queryChooseSellers(lat, lon, cityid, zoneid, null, pageNo, pageSize, null,null,null);
				map.put("source", 1);//数据来源商圈
			}
			
			if(mSellers.isEmpty()){
				mSellers = chooseSellerService.queryChooseSellers(lat, lon, cityid, null, null, pageNo, pageSize, null,null,null);
				map.put("source", 2);//数据来源省市
			}
		}
		
		if(!mSellers.isEmpty()){
			result  = sellerService.getSellers(mSellers, lon, lat, uid);
		}
		
		
		map.put("chooses", result);
		return map;
		
	}
	
	protected Map<String,Object> getPopularSellers(XmnHomeRequest xmnHomeRequest,List<Integer> sellers) {
		Map<String,Object> map = new HashMap<String,Object>();
		
		List<Map<Object,Object>> result = new ArrayList<Map<Object,Object>>();
		String tuid = ObjectUtils.toString(sessionTokenService.getStringForValue(xmnHomeRequest.getSessiontoken()));
		Integer uid = null;
		if(StringUtils.isNotEmpty(tuid)){
			uid = Integer.parseInt(tuid);
		}
		Double lat = xmnHomeRequest.getLat();
		Double lon = xmnHomeRequest.getLon();
		Integer zoneid = xmnHomeRequest.getZoneid();
		Integer cityid = xmnHomeRequest.getCityid();
		Integer pageNo = 1;
		Integer pageSize = 8;
		
		MongoBaseResponse<MSeller> mbr = null;
		if(zoneid != null){
			mbr = 
				popularSellerService.queryPopularSellers(uid,null, lat, lon,cityid,zoneid, null, pageNo, pageSize, sellers,true,null);
			map.put("source", 1);//数据来源商圈
		}
		
		
		if(mbr == null || mbr.getResult().isEmpty()){
			mbr = popularSellerService.queryPopularSellers(uid,4, lat, lon,cityid,null, null, pageNo, pageSize, sellers,true,null);
			map.put("source", 2);//仅按城市ID查询
		}
		
		if(mbr != null){
			List<MSeller> mSellers = mbr.getResult();
			if(mSellers != null && !mSellers.isEmpty()){
				result = sellerService.getSellers(mSellers, lon, lat, uid);
				
				map.put("popluars", result);
			}
		}
		return map;
	}

	protected Map<String,Object> getInterestSellers(XmnHomeRequest xmnHomeRequest,List<Integer> sellers) {
		Map<String,Object> map = new HashMap<String,Object>();
		
		List<Map<Object,Object>> result = new ArrayList<Map<Object,Object>>();
		String tuid = ObjectUtils.toString(sessionTokenService.getStringForValue(xmnHomeRequest.getSessiontoken()));
		Integer uid = null;
		if(StringUtils.isNotEmpty(tuid)){
			uid = Integer.parseInt(tuid);
		}
		Double lat = xmnHomeRequest.getLat();
		Double lon = xmnHomeRequest.getLon();
		Integer zoneid = xmnHomeRequest.getZoneid();//为了测试 该商圈没有数据所以屏蔽
		Integer cityid = xmnHomeRequest.getCityid();
		Integer pageNo = 1;
		Integer pageSize = 8;
		MongoBaseResponse<MSeller> mbr= null;
		if(zoneid != null){
			mbr = interestSellerService.queryInterestSellers(cityid,zoneid, null, lon, lat, sellers, pageNo, pageSize,true);
			map.put("source", 1);//数据来源商圈
		}
		
		if(mbr == null || mbr.getResult().isEmpty()){
			mbr = interestSellerService.queryInterestSellers(cityid,null, null, lon, lat, sellers, pageNo, pageSize,true);
			map.put("source", 2);//数据来源 城市
		}
		
		if(mbr != null){
			List<MSeller> mSellers = mbr.getResult();
			if(mSellers != null && !mSellers.isEmpty()){
				result = sellerService.getSellers(mSellers, lon, lat, uid);
				map.put("interests", result);
			}
		}
		return map;
	}
	
	/**
	 * 口味相似的人
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Object getSameTasteList(XmnHomeRequest xmnHomeRequest) {
		Map<Object,Object> map = new HashMap<Object,Object>();
		List<Map<Object,Object>> result = new ArrayList<Map<Object,Object>>();
		String uid = ObjectUtils.toString(sessionTokenService.getStringForValue(xmnHomeRequest.getSessiontoken()));
		Integer zoneid = xmnHomeRequest.getZoneid();
		Double lon = xmnHomeRequest.getLon();
		Double lat = xmnHomeRequest.getLat();
		Integer pageSize = 8;
		Integer pageNo = 1;
		List<Map<Object,Object>> userlist = new ArrayList<Map<Object,Object>>();
		List<Map<Object,Object>> sellerlist = new ArrayList<Map<Object,Object>>();
		if(StringUtils.isNotEmpty(uid)){
			//登录
			MongoBaseResponse<Map<Object,Object>> mbr = 
				sameTasteSellerService.querySameTasteList(Integer.parseInt(uid), null, lon, lat, pageNo, pageSize, true);
			if(mbr != null){
				userlist = mbr.getResult();
			}
		}else{
			userlist = sameTasteSellerService.querySameTasteList(zoneid, lon, lat, pageNo, pageSize, true);
		}
		if(userlist != null){
			for(Map<Object,Object> user : userlist){
				sellerlist = (List<Map<Object, Object>>) user.get("sellers");
				if(sellerlist != null && !sellerlist.isEmpty()){
					user.put("billmark", ObjectUtils.toString(sellerlist.get(0).get("zdate")));
					user.put("sellername", ObjectUtils.toString(sellerlist.get(0).get("sellername")));
					
				}else{
					user.put("billmark", "");
					user.put("sellername", "");
				}
				user.remove("sellers");
				result.add(user);
				
			}
		}
		
		map.put("users", result);
		MapResponse response = new MapResponse(ResponseCode.SUCCESS,"查询成功");
		response.setResponse(map);
		return response;
	}
	
	/**
	 * 看过不如一尝
	 */
	public Object getUneatList(UneatRequest uneatRequest){
		Map<Object,Object> map = new HashMap<Object,Object>();
		List<Map<Object,Object>> result = new ArrayList<Map<Object,Object>>();
		String tuid = ObjectUtils.toString(sessionTokenService.getStringForValue(uneatRequest.getSessiontoken()));
		Integer uid = null;
		if(StringUtils.isNotEmpty(tuid)){
			uid = Integer.parseInt(tuid);
		}
		Integer zoneid = uneatRequest.getZoneid();
		Integer cityid = uneatRequest.getCityid();
		Double lat = uneatRequest.getLat();
		Double lon = uneatRequest.getLon();
		Integer pageSize = 8;
		Integer pageNo = 1;
		MongoBaseResponse<MSeller> mbr =  
			uneatSellerService.queryUneatSellers(uid, zoneid,cityid,null, lon, lat, uneatRequest.getSellers(), pageNo, pageSize, true,null);
		
		if(mbr != null){
			List<MSeller> mSellers = mbr.getResult();
			
			result = sellerService.getSellers(mSellers, lon, lat, uid);
			map.put("sellers", result);
		}
		MapResponse response = new MapResponse(ResponseCode.SUCCESS,"查询成功");
		response.setResponse(map);
		return response;
	}


}
