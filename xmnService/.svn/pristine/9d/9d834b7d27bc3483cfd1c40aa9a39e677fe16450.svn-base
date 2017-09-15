package com.xmniao.xmn.core.catehome.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.xmniao.xmn.core.common.service.CommonService;
import com.xmniao.xmn.core.kscloud.entity.KSLiveEntity;
import com.xmniao.xmn.core.kscloud.service.KSCloudService;
import com.xmniao.xmn.core.live.service.LiveHomeService;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.catehome.dao.SpecilTopicPicDao;
import com.xmniao.xmn.core.catehome.entity.SpecilTopicPic;
import com.xmniao.xmn.core.catehome.response.HomeImageResponse;
import com.xmniao.xmn.core.catehome.service.NewXmnHomeService;
import com.xmniao.xmn.core.common.ConstantDictionary.MustBuyType;
import com.xmniao.xmn.core.common.Constant;
import com.xmniao.xmn.core.common.ConstantDictionary;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.catehome.BestForYouRequest;
import com.xmniao.xmn.core.common.request.catehome.BestForYouTitleRequest;
import com.xmniao.xmn.core.common.request.catehome.CityRequest;
import com.xmniao.xmn.core.common.request.catehome.HomeCateCommetRequest;
import com.xmniao.xmn.core.common.request.catehome.HomeMustBuyRequest;
import com.xmniao.xmn.core.live.dao.LiveUserDao;
import com.xmniao.xmn.core.live.entity.LiverInfo;
import com.xmniao.xmn.core.market.dao.ProductInfoDao;
import com.xmniao.xmn.core.market.entity.pay.ProductInfo;
import com.xmniao.xmn.core.market.service.product.ProductService;
import com.xmniao.xmn.core.seller.dao.BannerDao;
import com.xmniao.xmn.core.seller.dao.ExperienceCommentDao;
import com.xmniao.xmn.core.seller.entity.ExperienceCommentMedia;
import com.xmniao.xmn.core.sellerPackage.response.ComboListResponse;
import com.xmniao.xmn.core.sellerPackage.service.ComboService;
import com.xmniao.xmn.core.util.Base64;
import com.xmniao.xmn.core.util.DateUtil;
import com.xmniao.xmn.core.util.PropertiesUtil;
import com.xmniao.xmn.core.xmer.dao.SellerDao;
import com.xmniao.xmn.core.xmer.dao.UnsignedSellerDao;

/**
 * 
* @projectName: xmnService 
* @ClassName: CateHomeActivityServiceImpl    
* @Description:首页活动实现   
* @author: liuzhihao   
* @date: 2017年2月21日 下午8:51:21
 */
@Service
public class NewXmnHomeServiceImpl implements NewXmnHomeService {
	
	//日志报错
	private final Logger log = Logger.getLogger(NewXmnHomeServiceImpl.class);

	//注入套餐接口
	@Autowired
	private ComboService comboService;
	
	//专题图片dao
	@Autowired
	private SpecilTopicPicDao specilTopicPicDao;
	
	//轮播图dao
	@Autowired
	private BannerDao bannerDao;
	
	//注入配置service
	@Autowired
	private PropertiesUtil propertiesUtil;
	
	//服务器地址
	@Autowired
	private String fileUrl;
	
	@Autowired
	private SellerDao sellerDao;
	@Autowired
	private SessionTokenService sessionTokenService;
	@Autowired
	private ProductInfoDao productInfoDao;
	@Autowired
	private ProductService productService;
	@Autowired
	private UnsignedSellerDao unsignedSellerDao;
	@Autowired
	private ExperienceCommentDao experienceCommentDao;
	@Autowired
	private LiveUserDao liveUserDao;
	@Autowired
	private String localDomain;
	@Autowired
	private CommonService commonService;
	@Autowired
	private LiveHomeService liveHomeService;
	@Autowired
	private KSCloudService ksCloudService;
	
	public static String TYPEGOOD_NEW="新品上市";
	public static String TYPEGOOD_HOT="人气热卖";
	/**
	 * 
	 * 旧版本获取banner图函数
	 */
	@Override
	public Object getBannerList(CityRequest request) {
		
		Map<Object,Object> map = new HashMap<Object,Object>();
		
		/**
		 * 1.bannner轮播图
		 * 2.套餐推荐栏目--判断当前城市是否存在,有 则显示,无 则不显示
		 * 3.专题推荐栏目--判断当前城市是否存在,有 则显示,无 则不显示
		 * 2.精选活动栏目--判断当前城市是否存在,有 则显示,无 则不显示
		 */
		
		//先来获取banner轮播图
		List<HomeImageResponse> banners = getRecomenBanners(request.getCityid());
		toZhiboBanner(banners);
		//推荐套餐
		List<ComboListResponse> combos = getRandomRecommendCombos(request.getLat(),request.getLon(),request.getCityid());
		
		//推荐专题
		
		List<HomeImageResponse> specials = getRecommenSpecials(request.getCityid());
		
		//推荐活动
		List<HomeImageResponse> activitys = getRecomenActivitys(request.getCityid());
		commonService.checkIOSManorVersion(request, banners);
		map.put("banners", banners);
		map.put("combos", combos);
		map.put("specials", specials);
		map.put("activitys", activitys);
		
		MapResponse response = new MapResponse(ResponseCode.SUCCESS,"查询成功");
		response.setResponse(map);
		return response;
	}

	/**
	 * 获取随机套餐
	 */
	@Override
	public List<ComboListResponse> getRandomRecommendCombos(Double lat, Double lon, Integer cityId) {
		//查询今日推荐的套餐
		List<ComboListResponse> combos = new ArrayList<ComboListResponse>();
		try{
			combos = comboService.homeRecommendCombo(lat, lon, cityId);
			
			if(combos != null && combos.size() > 0){
				combos = combos.size()>3?combos.subList(0, 3):combos;
			}
			
		}catch(Exception e){
			e.printStackTrace();
			log.info("查询今日推荐套餐异常");
		}
		return combos;
	}

	/**
	 * 获取推荐专题
	 */
	@Override
	public List<HomeImageResponse> getRecommenSpecials(Integer cityId) {
		//返回结果集
		List<HomeImageResponse> response = new ArrayList<HomeImageResponse>();
		String url = "";
		try{
			//从配置文件中获取跳转地址
			url = propertiesUtil.getValue("specialUrl", "conf_common.properties");
		}catch(Exception e){
			e.printStackTrace();
			log.info("获取配置文件专题Url地址异常");
		}
		
		try{
			List<SpecilTopicPic> specilTopicPics 
				= specilTopicPicDao.findRecommendActivityPic(cityId, 1, 9);	
			if(specilTopicPics == null || specilTopicPics.isEmpty()){
				specilTopicPics = specilTopicPicDao.findRecommendActivityPicByUncity(1,9);
			}
			
			if(specilTopicPics != null && specilTopicPics.size() > 0){
				
				for(SpecilTopicPic specilTopicPic : specilTopicPics){
					
					HomeImageResponse image = new HomeImageResponse();
					
					image.setId(specilTopicPic.getFid());//专题活动
					image.setPicUrl(StringUtils.isEmpty(
						specilTopicPic.getPicUrl())?"":fileUrl+specilTopicPic.getPicUrl());//图片地址
					image.setSort(specilTopicPic.getSort());//图片排序
					image.setContent(url);
					response.add(image);
				} 
			}
			
		}catch(Exception e){
			e.printStackTrace();
			log.info("获取推荐专题异常");
		}
		return response;
	}

	/**
	 * 获取推荐轮播图
	 */
	@Override
	public List<HomeImageResponse> getRecomenBanners(Integer cityId) {
		
		List<HomeImageResponse> response = new ArrayList<HomeImageResponse>();
		
		List<Map<Object,Object>> banners = new ArrayList<Map<Object,Object>>();
		try{
			Map<Object,Object> map = new HashMap<Object,Object>();
			map.put("city", cityId);
			//1用户首页，2积分商城主页，3寻蜜客主页,4直播首页,100商户经营页, 5:积分超市竞拍活动 6:积分超市夺宝活动
			map.put("position", 1);
			//先根据城市ID 查询，如果没有 则查询全国
			banners = bannerDao.findAllByCity(map);
			//城市中没有
			if(banners.isEmpty()){
				banners = bannerDao.findAll(String.valueOf(1));
			}
		}catch(Exception e){
			e.printStackTrace();
			log.info("获取轮播图异常");
		}
		
		if(banners != null && banners.size() > 0){
			for(Map<Object,Object> banner : banners){
				try{
					String objson = Base64.getFromBase64(banner.get("objJson").toString());
					JSONArray jsonArr = JSON.parseArray(objson);
					for(int i=0; i<jsonArr.size(); i++){
						HomeImageResponse image = new HomeImageResponse();
						image.setId(Integer.parseInt(banner.get("id").toString()));//图片ID
						image.setSort(Integer.parseInt(banner.get("sort").toString()));//图片排序
						image.setContent(JSON.parseObject(jsonArr.getString(i)).getString("content"));
						image.setPicUrl(fileUrl+JSON.parseObject(jsonArr.getString(i)).getString("pic_url"));
						image.setLogRequired(JSON.parseObject(jsonArr.getString(i)).getString("logRequired"));
						image.setType(JSON.parseObject(jsonArr.getString(i)).getString("type"));
						response.add(image);
					}
				}catch(Exception e){
 					e.printStackTrace();
					log.info("轮播图解码json串异常");
				}
			}
		}
		return response;
	}

	/**
	 * 获取推荐活动
	 */
	@Override
	public List<HomeImageResponse> getRecomenActivitys(Integer cityId) {
		
		List<HomeImageResponse> response = new ArrayList<HomeImageResponse>();
		
		try{
			List<Map<Object,Object>> images = bannerDao.findBannersByIsNotEmphasis(cityId);
			
			if(images != null && images.size() > 0){
				for(Map<Object,Object> banner : images){
					try{
						String objson = Base64.getFromBase64(banner.get("objJson").toString());
						JSONArray jsonArr = JSON.parseArray(objson);
						for(int i=0; i<jsonArr.size(); i++){
							HomeImageResponse image = new HomeImageResponse();
							image.setId(Integer.parseInt(banner.get("id").toString()));//图片ID
							image.setSort(Integer.parseInt(banner.get("sort").toString()));//图片排序
							image.setContent(JSON.parseObject(jsonArr.getString(i)).getString("content"));
							image.setPicUrl(fileUrl+JSON.parseObject(jsonArr.getString(i)).getString("pic_url"));
							image.setLogRequired(JSON.parseObject(jsonArr.getString(i)).getString("logRequired"));
							image.setType(JSON.parseObject(jsonArr.getString(i)).getString("type"));
							response.add(image);
						}
					}catch(Exception e){
	 					e.printStackTrace();
						log.info("轮播图解码json串异常");
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			log.info("查询非重点推荐的banner图异常");
		}
		return response;
	}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/** 
	 *大版本首页banner + 专题 + 活动 模块
	 */
	@Override
	public Object getRecomenBanners(CityRequest request) {
		/**
		 * 1.bannner轮播图
		 * 2.专题推荐栏目--判断当前城市是否存在,有 则显示,无 则不显示
		 * 3.精选活动栏目--判断当前城市是否存在,有 则显示,无 则不显示
		 */
		//判断当前城市是否开通本地生活业务
		Integer hasLocalServiceCount =1;
		hasLocalServiceCount = sellerDao.countLocalSeller(request.getCityid());
		if (hasLocalServiceCount == null || hasLocalServiceCount == 0) {
			hasLocalServiceCount = 0;
		} else {
			hasLocalServiceCount=1;
		}
		//banner轮播图
		List<HomeImageResponse> banners = getRecomenBanners(request.getCityid());
		toZhiboBanner(banners);
		commonService.checkIOSManorVersion(request, banners);
		//推荐专题		
		List<SpecilTopicPic> specials = getHomeSpecial(request.getCityid(),hasLocalServiceCount);	
		//推荐活动
		List<Map<Object,Object>> activitys = bannerDao.findHomeActivity(request.getCityid());
		List<Map<Object,Object>> activityList = new ArrayList<Map<Object,Object>>();
		if (activitys!=null && !activitys.isEmpty()) {	
			for (int i=0;i<activitys.size();i++) {
				String imageUrl = activitys.get(i).get("imageUrl")+"";
				activitys.get(i).put("imageUrl",fileUrl+imageUrl);
				activityList.add(activitys.get(i));
			}
			
		}			
		Map<Object,Object> map = new HashMap<Object,Object>();	
		map.put("banners", banners);
		map.put("specials", specials);
		map.put("activitys", activityList);
		map.put("hasLocalService", hasLocalServiceCount);
		map.put("goodType", getNewProductType());
		//版本控制  
		String appversion = request.getAppversion();
		appversion = appversion.replace(".", "");
		int appv = Integer.parseInt(appversion);
		//版本大于370（寻蜜鸟）或者鸟人直播
		if (appv >= 370 || ConstantDictionary.AppSourceState.BIRD_APP.getName().equals(request.getAppSource())){
			map.put("homeConfigure", getCateHomeModelNew(hasLocalServiceCount));
		} else {
			map.put("homeConfigure", getCateHomeModel(hasLocalServiceCount));
		}
		
		MapResponse response = new MapResponse(ResponseCode.SUCCESS,"查询成功");
		response.setResponse(map);
		return response;
	}

	// 直播类型处理
	private void toZhiboBanner(List<HomeImageResponse> banners) {
		for (HomeImageResponse response : banners) {
			if ("9".equals(response.getType())) {

				try {
					String content = response.getContent();
					Map<Object, Object> picMap = liveHomeService.getZhiboBanner(content);
					String liveStartType = picMap.get("liveStartType") == null ? null : picMap.get("liveStartType").toString();
					String groupId = picMap.get("groupId") == null ? null : picMap.get("groupId").toString();
					String anchorRoomNo = picMap.get("anchorRoomNo") == null ? null : picMap.get("anchorRoomNo").toString();

					response.setLiveStartType(liveStartType);
					response.setGroupId(groupId);
					response.setAnchorRoomNo(anchorRoomNo);
				} catch (Exception e) {
					log.warn("解析轮播图失败", e);
				}

			}
		}
	}

	
	/**旧版本 美食首页 图标
	 * @param hasLocalService
	 * @return
	 */
	private List<Map<Object, Object>> getCateHomeModel(Integer hasLocalService) {
		List<Map<Object, Object>> homeModels = new ArrayList<Map<Object, Object>>();
		try {
			String homeModel = propertiesUtil.getValue("cateHome_HasService", "conf_common.properties");
			if (hasLocalService == 0) {
				homeModel = propertiesUtil.getValue("cateHome_HasService", "conf_common.properties");
			}
			JSONArray jsonArray = JSON.parseArray(homeModel);
			for (Object obj : jsonArray) {
				@SuppressWarnings("unchecked")
				Map<Object, Object> objMap = (Map<Object, Object>) obj;
				String url = localDomain + objMap.get("url");
				String name = objMap.get("name")+"";
				String img = fileUrl + objMap.get("img");
				if ("商户红包".equals(name)){
					url="tips:即将登场";
				}
				objMap.put("url", url);
				objMap.put("img", img);
				homeModels.add(objMap);
			}
		} catch (Exception e) {
			log.info("文件读取出错");
			e.printStackTrace();
		}

		return homeModels;
	}
	
	/**新版本美食首页图标
	 * @param hasLocalService
	 * @return
	 */
	private List<Map<Object, Object>> getCateHomeModelNew(Integer hasLocalService) {
		List<Map<Object, Object>> homeModels = new ArrayList<Map<Object, Object>>();
		try {
			String homeModel = propertiesUtil.getValue("cateHome_HasService_New", "conf_common.properties");
			if (hasLocalService == 0) {
				homeModel = propertiesUtil.getValue("cateHome_noService_New", "conf_common.properties");
			}
			JSONArray jsonArray = JSON.parseArray(homeModel);
			for (Object obj : jsonArray) {
				@SuppressWarnings("unchecked")
				Map<Object, Object> objMap = (Map<Object, Object>) obj;
				String url = ObjectUtils.toString(objMap.get("url"));
				String name = objMap.get("name")+"";
				if (StringUtils.isNotBlank(url)) {
					url = localDomain +url;
				}else {
					url = null;
				}
				if ("商户红包".equals(name)){
					url="tips:即将登场";
				}
				objMap.put("url", url);
				objMap.put("img", objMap.get("img"));
				homeModels.add(objMap);
			}
		} catch (Exception e) {
			log.info("文件读取出错");
			e.printStackTrace();
		}

		return homeModels;
	}
	/**获取新品分类 和人气热卖分类
	 * @return
	 */
	private Object getNewProductType() {
		try {
			 TYPEGOOD_NEW = propertiesUtil.getValue("TYPEGOOD_NEW", "conf_common.properties");
			 TYPEGOOD_HOT = propertiesUtil.getValue("TYPEGOOD_HOT", "conf_common.properties");
		} catch (Exception e) {
			log.warn("读取文件出错！");
			e.printStackTrace();
		}
		Map<Object, Object> calssNew = productInfoDao.queryClassaByName(TYPEGOOD_NEW);
		Map<Object, Object> calssHot = productInfoDao.queryClassaByName(TYPEGOOD_HOT);
		Map<Object, Object> goodType = new HashMap<Object, Object>();
		goodType.put("newGoodType", calssNew);
		goodType.put("hotGoodType", calssHot);
	return goodType;
}

	/**获取美食首页专题
	 * @param cityId
	 * @param hasLocalServiceCount 是否有本地生活服务
	 * @return
	 */
	private List<SpecilTopicPic> getHomeSpecial(Integer cityId, Integer hasLocalServiceCount) {
		// 返回结果集
		String url = "";
		try {
			// 从配置文件中获取跳转地址
			url = propertiesUtil.getValue("specialUrl", "conf_common.properties");
		} catch (Exception e) {
			e.printStackTrace();
			log.info("获取配置文件专题Url地址异常");
		}
		try {
			//后台有设置专题才显示
			List<SpecilTopicPic> specilTopicPics = specilTopicPicDao.findTopicAndNumSellerPackage(cityId, 1, 3);//默认pageSize为
			List<SpecilTopicPic> Topics= new ArrayList<SpecilTopicPic>();
			
			//attention:用对象返回的为空也是一个对象，需要判断里面的id是否存在不存在为空对象去掉
			if (specilTopicPics==null || specilTopicPics.isEmpty()) {
				specilTopicPics = specilTopicPicDao.findTopicAndNumSellerPackage(null, 1, Integer.MAX_VALUE);
			}
			if(specilTopicPics != null && specilTopicPics.size() > 0){				
				for(SpecilTopicPic specilTopicPic : specilTopicPics){
					if (specilTopicPic.getId()!=null){
						Topics.add(specilTopicPic);
					}
				}
			}
			if (Topics==null || Topics.isEmpty()) {
				Topics = specilTopicPicDao.findTopicAndNumSellerPackage(null, 1, Integer.MAX_VALUE);
			}
			if(Topics != null && Topics.size() > 0){				
				for(SpecilTopicPic specilTopicPic : Topics){
					specilTopicPic.setLinkUrl(url);
					specilTopicPic.setPicUrl(fileUrl+specilTopicPic.getPicUrl());			
					setSpecialRelationContent(specilTopicPic);

				}
			}
			return Topics;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("获取推荐专题异常");
		}
		return null;
	}

	private void setSpecialRelationContent(SpecilTopicPic specilTopicPic) {
		if (specilTopicPic.getTopicType() != null && specilTopicPic.getSellerNum()!=0) {
			Integer num = specilTopicPic.getSellerNum();
			if (specilTopicPic.getTopicType() == 1) {
				specilTopicPic.setSpecialRelation(num+"家精选餐厅");
			}
			if (specilTopicPic.getTopicType() == 2) {
				specilTopicPic.setSpecialRelation(num+"家精选餐厅");
			}
			if (specilTopicPic.getTopicType() == 3) {
				specilTopicPic.setSpecialRelation(num+"个精选商品");
			}
			if (specilTopicPic.getTopicType() == 4) {
				specilTopicPic.setSpecialRelation(num+"位精选主播");
			}
			if (specilTopicPic.getTopicType() == 5) {
				specilTopicPic.setSpecialRelation(num+"个精选预告");
			}
			if (specilTopicPic.getTopicType() == 6) {
				specilTopicPic.setSpecialRelation(num+"张精选粉丝券");
			}
			if (specilTopicPic.getTopicType() == 7) {
				specilTopicPic.setSpecialRelation(num+"个精选套餐");
			}
			if (specilTopicPic.getTopicType() == 8) {
				specilTopicPic.setSpecialRelation(num+"个精选商品");
			}

		}

	}

	/** 
	 *首页为你优选  选择对应的标签查询商家
	 */
	@Override
	public Object getBestForYou(BestForYouRequest request) {
		Map<Object,Object> paraMap = new HashMap<Object,Object>();
		paraMap.put("cityid", request.getCityid());
		paraMap.put("lat", request.getLat());
		paraMap.put("lon", request.getLon());
		paraMap.put("zoneid", request.getZoneid());
		paraMap.put("page", request.getPage());
		paraMap.put("pageSize", request.getPageSize());
		//统计当前城市的商铺数量是否大于10
		List<Map<Object,Object>> sellers = null;
		//当前城市所有的有效商家,有套餐 + 有直播 + 本地商圈内人气+ 本地店家 计算距离
		if (request.getSellerTopicId()==null || "".equals(request.getSellerTopicId())) {
			 sellers = sellerDao.queryHomeSeller(paraMap);
		}else {	
			if (request.getSellerlabel()==null || "".equals(request.getSellerlabel())) {
				return new BaseResponse(ResponseCode.FAILURE, "参数不正确");
			}	
			String[] tags = request.getSellerlabel().split(",");
			paraMap.put("tagids", Arrays.asList(tags));
			sellers = sellerDao.queryHomeSellerByClassify(paraMap);
		}
		
		List<Map<Object,Object>> resultSellers = new ArrayList<Map<Object,Object>>();
		resultSellers = getResultOfBestSellers(sellers,request.getCityid(),request.getZoneid());
		MapResponse response = new MapResponse(ResponseCode.SUCCESS,"查询成功");
		Map<Object,Object> resultMap = new HashMap<Object,Object>();
		resultMap.put("sellers", resultSellers);
		response.setResponse(resultMap);
		return response;
	}

	
	/** 为你优选显示分类信息
	 * 
	 */
	@Override
	public Object getBestForYouTitle(BestForYouTitleRequest request) {
		Integer totalSellers =sellerDao.countLocalSeller(request.getCityid());
		Map<Object,Object> resultMap = new HashMap<Object,Object>();
		MapResponse response = new MapResponse(ResponseCode.SUCCESS, "查询成功");
		if (totalSellers >= 10) {//当前城市店铺小于10的只显示精选，不显示分类					
			Map<Object,Object> paraMap = new HashMap<Object,Object>();
			paraMap.put("cityid", request.getCityid());
			//查询为你优选 title分类
			List<Map<Object,Object>> classifies = sellerDao.findBestForYouTitle(paraMap);
			if(classifies!=null && !classifies.isEmpty()) {
				for (int i=0;i<classifies.size();i++) {
					String logoUrl = classifies.get(i).get("logo_url")+"";
					classifies.get(i).put("logo_url", fileUrl+logoUrl);
					String tagids = classifies.get(i).get("tag_ids")+"";
					String[] tabLabelids = tagids.split(",");
					if (tabLabelids!=null) {
						List<Map<Object,Object>> classTags = sellerDao.findClassifyByIDs(tabLabelids);
						classifies.get(i).put("tabMap", classTags);
					}		
				}
			}
			resultMap.put("classifies", classifies)	;	
			response.setResponse(resultMap);
		}	
		return response;
	}
	
	/**组装最终结果
	 * @param sellers
	 * @return
	 */
	private List<Map<Object, Object>> getResultOfBestSellers(List<Map<Object, Object>> sellers,Integer cityid,Integer zoneId) {
		if(sellers==null || sellers.isEmpty()) {
			return null;
		}
		String comboUrl = "";
		try {
			comboUrl= propertiesUtil.getValue("comboUrl", "conf_common.properties").toString();
		} catch(Exception e) {
			log.info("文件读取出错");
			e.printStackTrace();
			return null;
		}
		List<Integer> anchorIds = new ArrayList<Integer>();
		Map<Integer, Map<Object, Object>> anchorMap = new HashMap<Integer, Map<Object, Object>>();
		List<Map<Object,Object>> sellerList = new ArrayList<Map<Object,Object>>();
		for (int i=0;i<sellers.size();i++) {
			//组装页面需要的信息 封面图
			Map<Object,Object> map = new HashMap<Object,Object>();
			Map<Object,Object> sellersIndex = sellers.get(i);
			String rangs =  sellersIndex.get("ranges")==null?"0":sellersIndex.get("ranges").toString();
			Integer islive = (Integer) sellersIndex.get("islive");
			Integer sellerid = (Integer) sellersIndex.get("sellerid");
			String tags = ObjectUtils.toString(sellersIndex.get("tag_ids"), "");
			String[] str = tags.split(",");
			List<Map<Object,Object>> showMessages = new ArrayList<Map<Object,Object>>();
			if (Double.parseDouble(rangs)>1000) {
				double longth = Double.parseDouble(rangs);
				longth=longth/1000;
				rangs=new BigDecimal(longth).setScale(1, BigDecimal.ROUND_HALF_UP).toString()+"km";
			}else {
				rangs += "m";
			}
			map.put("rangs", rangs);
			if((Integer)sellersIndex.get("iscombo")==1){
				map.put("cover", fileUrl+sellersIndex.get("comboimage"));
			}else {
				map.put("cover", fileUrl+sellersIndex.get("picurl"));
			}
			map.put("labels", sellerDao.findSellerTagNameBySellerId(Arrays.asList(str)));
			map.put("comboprice", sellersIndex.get("coinprice"));//只取鸟币价格
			/*map.put("coinboprice", sellersIndex.get("coinboprice"));*/
			map.put("iscombo", sellersIndex.get("iscombo"));
			map.put("sellerid", sellersIndex.get("sellerid"));
			map.put("sellername", sellersIndex.get("sellername"));
			map.put("zonename", sellersIndex.get("zonename"));
			map.put("comboname", sellersIndex.get("comboname"));
			map.put("comboId", sellersIndex.get("comboId"));
			map.put("genrename", sellersIndex.get("genrename"));
			map.put("zhiboType", sellersIndex.get("zhibo_type"));//0 预告，1直播中，3回放，4历史通告,5无直播
			if ((Integer)sellersIndex.get("iscombo")==1) {
				//套餐加上跳转地址
				map.put("comboUrl", comboUrl);//0 预告，1直播中，3回放，4历史通告,5无直播
				
			}
			//////////////////////////////////////////////////////////////////////////////////////////
			/*map.put("showFlag", 3); //标识：0吃，1,播，2爆,3无标识*/
			map.put("consumnumber", getConsuCount(Integer.parseInt(sellersIndex.get("num").toString()), sellerid)); //店家消费人数 +统一加上200人
			if (islive==3) {
				Map<Object,Object> mapLiving = new HashMap<Object,Object>();
				mapLiving.put("showFlagContent", "主播"+sellersIndex.get("nname")+"正在店里直播中"); //需要显示的标识
				mapLiving.put("showFlag", 1); //标识“播”
				map.put("groupId", getGroupIdByAnchorId((Integer)sellersIndex.get("anchorId")));
				map.put("anchorId", sellersIndex.get("anchorId"));
				map.put("liveType", ObjectUtils.toString(sellersIndex.get("liveType"),""));
				map.put("liveRoom", ObjectUtils.toString(sellersIndex.get("liveRoom"),""));
				map.put("liveRecordId", sellersIndex.get("liveRecordId"));
				showMessages.add(mapLiving);
			} 
			if (islive >0) {
				Map<Object,Object> mapHasLiving = new HashMap<Object,Object>();
				mapHasLiving.put("showFlagContent", sellersIndex.get("nname")+"等主播吃过这家店"); //需要显示的标识
				mapHasLiving.put("showFlag", 0); //标识 “吃”
				showMessages.add(mapHasLiving);
			} 
			if(islive==0){
				map.put("zhiboType", 5);//0 预告，1直播中，3回放，4历史通告,5无直播
			}
			getShowFlagPopularContent(map,showMessages,cityid,zoneId);
			map.put("showArray", showMessages);

			// 直播
			if (map.get("zhiboType").equals("1")) {
				try {
					anchorIds.add(Integer.parseInt(map.get("anchorId").toString()));
				} catch (Exception e) {
					log.warn("获取anchorId失败", e);
				}
			}


			if (anchorIds != null && anchorIds.size() > 0) {
				try {
					List<Map<Object, Object>> tmpInfo = liveUserDao.queryLiverInfoByIdList(anchorIds);
					if (tmpInfo != null) {
						for (Map<Object, Object> info : tmpInfo) {
							try {
								int id = Integer.parseInt(info.get("id").toString());
								int uid = Integer.parseInt(info.get("uid").toString());
//								uidMap.put(id, uid);
								anchorMap.put(id, info);
							} catch (Exception e) {
								log.warn("生成金山拉流失败: ", e);
							}
						}
					}
				} catch (Exception e) {
					log.warn("查询主播信息失败, ", e);
				}
			}
			sellerList.add(map);	 
		}
		// 金山拉流处理
		for (Map<Object,Object> map : sellerList) {
			try {
				map.put("livePlatform", 1); //直播使用平台  1 腾讯直播  2 金山云直播
				map.put("liveRtmpUrl", "");  //拉流地址
				if (!map.get("zhiboType").equals("1")) {
					continue;
				}
				String anchorId = map.get("anchorId").toString();
//				Integer uid = .get(Integer.parseInt(anchorId));

				Map<Object, Object> anchor = anchorMap.get(Integer.parseInt(anchorId));
				if (anchor == null || anchor.get("uid") == null) {
					continue;
				}
				String uidStr = anchor.get("uid").toString();
				//  生成拉流地址
				KSLiveEntity entity = ksCloudService.createKSLPullUrl(uidStr, anchor);
				if (entity != null) {
					map.put("livePlatform", entity.getPlatform());
					map.put("liveRtmpUrl", entity.getUrl());
				}
			} catch (Exception e) {
				log.warn("添加拉流失败", e);
			}
		}

		return sellerList;
	}



	/**获取商品多种标识
	 * @param map
	 * @return
	 */


	/**获取正在直播的groupId
	 * @param anchorId
	 * @return
	 */
	private Object getGroupIdByAnchorId(Integer anchorId) {
		LiverInfo live = liveUserDao.queryAnchorInfoByAnchorId(anchorId);
		if (live==null){
			return "";
		}
		return live.getGroup_id()==null?"":live.getGroup_id();
	}


	/**获取人气标识
	 * @param map 返回页面结果集
	 * @param list  标识集合
	 * @param cityId 城市id
	 */
	private void getShowFlagPopularContent(Map<Object, Object> map, List<Map<Object,Object>> list,Integer cityId,Integer zoneId) {
		//查询当前城市人气餐厅 
		Map<Object,Object> paramMap = new HashMap<Object,Object>();
		Map<Object,Object> popularMap = new HashMap<Object,Object>();
		paramMap.put("cityid", cityId);
		paramMap.put("zoneid", zoneId);
		List<Map<Object,Object>> resultMaps = sellerDao.queryConsumNumberByLocalCity(paramMap);
		if (resultMaps !=null && !resultMaps.isEmpty()) {
			Boolean flag = false;
			for (int i=0;i<resultMaps.size();i++){
				if(StringUtils.isNotBlank(map.get("sellerid")+""))
				if (map.get("sellerid").equals(resultMaps.get(i).get("sellerid"))) {
					flag=true;
				}
			}
			if (flag){
				popularMap.put("showFlag", 2); //标识“爆”
				popularMap.put("showFlagContent", map.get("genrename")+"中人气最高餐厅");
				list.add(popularMap);
			}
		}
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
	 * 获取网红点评
	 */
	@Override
	public Object getCommentSellers(HomeCateCommetRequest request) {
		Map<Object,Object> paramMap = new HashMap<Object,Object>();
		paramMap.put("cityid", request.getCityid());
		Map<Object,Object> resultMap = new HashMap<Object,Object>();
		MapResponse response = new MapResponse(ResponseCode.SUCCESS,"查询成功");			
		//读取后台配置的商家 （t_seller + t_unsign_seller)
		List<Map<Object,Object>> commnetSellers = sellerDao.findTopCommentSellers(paramMap);
		List<Integer>  signSellerids = new ArrayList<Integer>();
		List<Integer>  unsignSellerids = new ArrayList<Integer>();
		for (int i=0;i<commnetSellers.size();i++) {
			Integer type = (Integer) commnetSellers.get(i).get("seller_type");
			if (type==1) {
				signSellerids.add((Integer) commnetSellers.get(i).get("sellerid"));
			}else {
				unsignSellerids.add((Integer) commnetSellers.get(i).get("sellerid"));
			}
		}
		List<Map<Object,Object>> homeAllSellers = new ArrayList<Map<Object,Object>>();
		//分别处理签约商家 和 未签约商家
        if (signSellerids!=null && !signSellerids.isEmpty()) {
        	List<Map<Object,Object>> homeCatCommentSellers = sellerDao.findHomeCommentBySignSelelrids(signSellerids);
        	if (homeCatCommentSellers!=null && !homeCatCommentSellers.isEmpty()) {
    			for (int i=0;i<homeCatCommentSellers.size();i++){
    				Integer sellerid = Integer.parseInt(homeCatCommentSellers.get(i).get("sellerid").toString());
    				String picUrl = homeCatCommentSellers.get(i).get("picurl").toString();
    				List<String> avatars = sellerDao.findLiverBysellerid(sellerid);
    				List<String> avatarsList = new ArrayList<String>();
    				if (avatars!=null && !avatars.isEmpty()){
    					for(String avatar:avatars){
    						avatar=fileUrl+avatar;
    						avatarsList.add(avatar);
    					}
    				}
    				homeCatCommentSellers.get(i).put("sellerType", 1);
    				homeCatCommentSellers.get(i).put("avatars", avatarsList);
    				homeCatCommentSellers.get(i).put("picurl", fileUrl+picUrl);
    			}
    			
    		}
        	homeAllSellers.addAll(homeCatCommentSellers);      	
		}
        
		if (unsignSellerids!=null && !unsignSellerids.isEmpty()) {
			List<Map<Object,Object>> homeUnsignSellers = unsignedSellerDao.findSellerbyIds(unsignSellerids);
			if (homeUnsignSellers!=null && !homeUnsignSellers.isEmpty()) {
				for(int i =0;i<homeUnsignSellers.size();i++){
					homeUnsignSellers.get(i).put("anchorNum", 0);
					homeUnsignSellers.get(i).put("sellerType", 2);
					homeUnsignSellers.get(i).put("zhibo_type", "");
					homeUnsignSellers.get(i).put("avatars", new ArrayList<String>());
					//TODU 封面图
					homeUnsignSellers.get(i).put("picurl", getPhotoOfUnsignSeller((Integer) homeUnsignSellers.get(i).get("sellerid")));
				}
				homeAllSellers.addAll(homeUnsignSellers);
			}
		}
		
		if (homeAllSellers.size()>3){
			homeAllSellers = homeAllSellers.subList(0, 3);
		}
		resultMap.put("commetSellers", homeAllSellers);
		response.setResponse(resultMap);		
		return response;
	}

	/**获取非签约商家的封面图
	 * @param sellerid
	 * @return
	 */
	private String getPhotoOfUnsignSeller(Integer sellerid) {
		List<ExperienceCommentMedia> experiencePhotos = experienceCommentDao.listSellerPhotos(sellerid, 0, null);
		if (experiencePhotos!=null && !experiencePhotos.isEmpty()) {		
			return fileUrl+experiencePhotos.get(0).getMediaUrl();
		}	
		return "";
	}
	/**
	 * 首页必买清单
	 */
	@Override
	public Object getMustBuy(HomeMustBuyRequest request) {
		List<Map<Object,Object>> mustBuyTitle =Lists.newArrayList();
		Map<Object,Object> resultMap =Maps.newHashMap();
		Map<Object,Object> paraMap = Maps.newHashMap();	
		paraMap.put("type", request.getType());
		//查询包含的所有商品分类
		List<Integer> mustBuyTypes = sellerDao.findMustBuyCode(paraMap);
		List<Map<Object,Object>> mustBuyGoods =null;
		//读取必买清单title固定4种类型
		List<Integer> titleTypes = sellerDao.findHomeMustBuyTitle();
		for(int i=0;i<titleTypes.size();i++){
			Integer type = titleTypes.get(i);
			Map<Object,Object> paraMapTitle = Maps.newHashMap();
			if (type==MustBuyType.CAREFULLY_CHOSEN.getCode()){
				paraMapTitle.put("typeId", MustBuyType.CAREFULLY_CHOSEN.getCode().toString());
				paraMapTitle.put("typeName", MustBuyType.CAREFULLY_CHOSEN.getName());
			}else if (type==MustBuyType.FANNY_PLAY.getCode()) {
				paraMapTitle.put("typeId", MustBuyType.FANNY_PLAY.getCode().toString());
				paraMapTitle.put("typeName",  MustBuyType.FANNY_PLAY.getName());
			}else if (type==MustBuyType.GIFTS.getCode()) {
				paraMapTitle.put("typeId", MustBuyType.GIFTS.getCode().toString());
				paraMapTitle.put("typeName", MustBuyType.GIFTS.getName());
			}else if (type==MustBuyType.SEA_SEARCH.getCode()) {
				paraMapTitle.put("typeId", MustBuyType.SEA_SEARCH.getCode().toString());
				paraMapTitle.put("typeName", MustBuyType.SEA_SEARCH.getName());
			}
			mustBuyTitle.add(paraMapTitle);
		}
		//匹配商品信息
		if (mustBuyTypes!=null && !mustBuyTypes.isEmpty()) {
			paraMap.clear();			
			paraMap.put("page", request.getPage());
			paraMap.put("pageSize", request.getPageSize());
			paraMap.put("mustBuyTypes", mustBuyTypes);
			mustBuyGoods = productInfoDao.queryProductsByclassaes(paraMap);
			for (int i=0;i<mustBuyGoods.size();i++) {
				Map<Object,Object> goodMap = mustBuyGoods.get(i);
				String classname = ObjectUtils.toString(goodMap.get("classname"),"");
				ProductInfo info = new ProductInfo();
				setProductInfo(goodMap, info);
				productService.loadLabel(info);
				if(isHasHotLabel(info) && !"".equals(classname)){
					mustBuyGoods.get(i).put("showContent", classname+"中人气最高");
				}
				mustBuyGoods.get(i).put("labels", info.getLabels());
				mustBuyGoods.get(i).put("breviary", fileUrl+info.getBreviary());
				
			}						
		}	
		resultMap.put("mustBuyTitle", mustBuyTitle);
		resultMap.put("mustBuyGoods", mustBuyGoods);
		MapResponse response = new MapResponse(ResponseCode.SUCCESS,"查询成功");
		response.setResponse(resultMap);	
		return response;
	}
	
	/**判断产品是否存在'热销'标签
	 * @param info
	 * @return
	 */
	private boolean isHasHotLabel(ProductInfo info) {
		Set<Integer> labels  = info.getLabels();
		if (labels!=null&&!labels.isEmpty()&&labels.contains(2)){
			return true;
		}
		return false;
	}

	/**产品参数设置
	 * @param goodMap
	 * @param info
	 */
	private void setProductInfo (Map<Object,Object> goodMap,ProductInfo info) {
		info.setPid((Integer) goodMap.get("pid"));
		info.setCodeid(Long.parseLong(ObjectUtils.toString(goodMap.get("codeId"), "")));
		info.setIntegral(new BigDecimal(ObjectUtils.toString(goodMap.get("integral"), "")));
		info.setPrice((Double) goodMap.get("price"));
		info.setBreviary(ObjectUtils.toString(goodMap.get("breviary"), ""));
		info.setClassa((Integer) goodMap.get("classa"));
		info.setChoice((Integer) goodMap.get("choice"));
		info.setRdate(DateUtil.parse(goodMap.get("rdate")+""));;
		info.setCash(new BigDecimal(ObjectUtils.toString(goodMap.get("cash"), "")));
	}
}
