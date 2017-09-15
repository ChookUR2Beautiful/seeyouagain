package com.xmniao.xmn.core.seller.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.annotation.Resource;

import com.xmniao.xmn.core.kscloud.service.KSCloudService;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Order;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.MongoBaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.catehome.entity.mongo.MSeller;
import com.xmniao.xmn.core.catehome.entity.mongo.XmnSeller;
import com.xmniao.xmn.core.common.Constant;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.seller.RecomSellerRequest;
import com.xmniao.xmn.core.live.dao.AnchorLiveRecordDao;
import com.xmniao.xmn.core.live.dao.LiveUserDao;
import com.xmniao.xmn.core.live.entity.LiveRecordInfo;
import com.xmniao.xmn.core.seller.dao.ExperienceCommentDao;
import com.xmniao.xmn.core.seller.dao.UrsCollectDao;
import com.xmniao.xmn.core.seller.entity.ExperienceCommentMedia;
import com.xmniao.xmn.core.seller.entity.SearchCommentSellerRequest;
import com.xmniao.xmn.core.seller.entity.SellerPhotoRequest;
import com.xmniao.xmn.core.seller.service.ChooseSellerService;
import com.xmniao.xmn.core.seller.service.CustomisedSellerService;
import com.xmniao.xmn.core.seller.service.InterestSellerService;
import com.xmniao.xmn.core.seller.service.PopularSellerService;
import com.xmniao.xmn.core.seller.service.SameTasteSellerService;
import com.xmniao.xmn.core.seller.service.SellerService;
import com.xmniao.xmn.core.seller.service.UneatSellerService;
import com.xmniao.xmn.core.sellerPackage.dao.SellerPackageDao;
import com.xmniao.xmn.core.util.DateUtil;
import com.xmniao.xmn.core.util.GeoHashUtil;
import com.xmniao.xmn.core.util.PropertiesUtil;
import com.xmniao.xmn.core.verification.dao.UrsInfoDao;
import com.xmniao.xmn.core.xmer.dao.SellerInfoDao;
import com.xmniao.xmn.core.xmer.dao.UnsignedSellerDao;

/**
 * 
 * @projectName: xmnService
 * @ClassName: SellerServiceImpl
 * @Description:商铺实现类
 * @author: liuzhihao
 * @date: 2016年11月30日 上午10:14:59
 */
@Service
public class SellerServiceImpl implements SellerService {

	@Resource
	private MongoTemplate mongoTemplate;

	@Autowired
	private PropertiesUtil propertiesUtil;

	@Autowired
	private SessionTokenService sessionTokenService;

	@Autowired
	private AnchorLiveRecordDao anchorLiveRecordDao;

	@Autowired
	private UrsCollectDao ursCollectDao;
	
	@Autowired
	private UrsInfoDao ursInfoDao;
	
	@Autowired
	private LiveUserDao liveUserDao;

	@Autowired
	private String fileUrl;
	
	@Autowired
	private ChooseSellerService chooseSellerService;
	
	@Autowired
	private PopularSellerService popularSellerService;
	
	@Autowired
	private InterestSellerService interestSellerService;
	
	@Autowired
	private SameTasteSellerService sameTasteSellerService;
	
	@Autowired
	private UneatSellerService uneatSellerService;
	
	@Autowired
	private CustomisedSellerService customisedSellerService;
	/**
	 * 同样名字 不同包中的接口
	 */
	@Autowired
	private com.xmniao.xmn.core.xmer.service.SellerService xmnSellerSerivce;
	
	//注入套餐dao
	@Autowired
	private SellerPackageDao sellerPackageDao;

	//注入网红点评dao
	@Autowired
	private ExperienceCommentDao experienceCommentDao;
	
	@Autowired
	private SellerInfoDao sellerInfoDao;
	
	@Autowired
	private UnsignedSellerDao unsignedSellerDao;
	@Autowired
	private KSCloudService ksCloudService;
	
	/**
	 * 推荐商铺
	 */
	@Override
	public Object recommendSellers(RecomSellerRequest recomSellerRequest) {
		try{
			Map<Object,Object> map = new HashMap<Object,Object>();
			Integer type = recomSellerRequest.getType();//推荐类型 1:帮你挑选店铺 2:商圈人气店铺 3:猜你喜欢商铺
			switch(type){
				case 1://帮你挑选店铺
					map = getChooseSellers(recomSellerRequest);
					break;
				case 2://常去商圈的人气店铺（更名 “商圈人气店铺”）
					map = getPopularSellers(recomSellerRequest);
					break;
				case 3://感兴趣的新店（更名“猜你喜欢”）
					map = getInterestSellers(recomSellerRequest);
					break;
//				case 4://与我口味相似
//					map = getSameTasteSellers(recomSellerRequest);
//					break;
				default :
					break;
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
	 * 帮你挑选店铺实现类  
	 * @param recomSellerRequest
	 * @return
	 */
	protected Map<Object,Object> getChooseSellers(RecomSellerRequest recomSellerRequest) {
		Map<Object,Object> map = new HashMap<Object,Object>();
		List<Map<Object,Object>> result = new ArrayList<Map<Object,Object>>();
		Integer kind = recomSellerRequest.getKind();
		Integer status = recomSellerRequest.getStatus();
		String tokenuid = ObjectUtils.toString(sessionTokenService.getStringForValue(recomSellerRequest.getSessiontoken()));
		Integer uid = null;
		if(StringUtils.isNotEmpty(tokenuid)){
			uid = Integer.parseInt(tokenuid);
		}
		Double lat = recomSellerRequest.getLat();
		Double lon = recomSellerRequest.getLon();
		Integer zoneid = recomSellerRequest.getZoneid();
		Integer tradeid = recomSellerRequest.getTradeid();
		Integer pageNo = recomSellerRequest.getPage();
		Integer pageSize = recomSellerRequest.getPageSize();
		Integer cityid = recomSellerRequest.getCityid();
		List<Integer> sellers = recomSellerRequest.getSellerIds();
		List<MSeller> mSellers = new ArrayList<MSeller>();
		if(uid != null){
			MongoBaseResponse<MSeller> mbr = 
				chooseSellerService.queryChooseSellers(kind, uid, lat, lon, cityid,zoneid, tradeid, pageNo, pageSize, sellers,status);
			if(mbr != null){
				kind = mbr.getKind();
				pageNo = mbr.getPage();
				mSellers = mbr.getResult();
				map.put("kind", kind);
				map.put("page", pageNo);
			}
			
		}else{
			Double minDistance = null;
			Double maxDistance = null;
			if(zoneid != null){
				minDistance = 0.0D;
				maxDistance = 6000.0D;
			}
			mSellers =
				chooseSellerService.queryChooseSellers(lat, lon,cityid,zoneid, tradeid, pageNo, pageSize, sellers, minDistance, maxDistance);
			map.put("kind", "");
			map.put("page", "");
		}
		
		if(mSellers != null && !mSellers.isEmpty()){
			result = getSellers(mSellers, lon, lat, uid);
			map.put("sellers", result);
		}
		return map;
		
	}
	
	/**
	 * 常去商圈的人气店铺（更名 “商圈人气店铺”）
	 * @param recomSellerRequest
	 * @return
	 */
	protected Map<Object,Object> getPopularSellers(RecomSellerRequest recomSellerRequest) {
		Map<Object,Object> map = new HashMap<Object,Object>();
		List<Map<Object,Object>> result = new ArrayList<Map<Object,Object>>();
		Integer kind = recomSellerRequest.getKind();
		Integer status = recomSellerRequest.getStatus();
		String tokenuid = ObjectUtils.toString(sessionTokenService.getStringForValue(recomSellerRequest.getSessiontoken()));
		Integer uid = null;
		if(StringUtils.isNotEmpty(tokenuid)){
			uid = Integer.parseInt(tokenuid);
		}
		Double lat = recomSellerRequest.getLat();
		Double lon = recomSellerRequest.getLon();
		Integer zoneid = recomSellerRequest.getZoneid();
		Integer tradeid = recomSellerRequest.getTradeid();
		Integer cityid = recomSellerRequest.getCityid();
		Integer pageNo = recomSellerRequest.getPage();
		Integer pageSize = recomSellerRequest.getPageSize();
		List<Integer> sellers = recomSellerRequest.getSellerIds();
		MongoBaseResponse<MSeller> mbr = 
			popularSellerService.queryPopularSellers(uid,kind, lat, lon, cityid,zoneid, tradeid, pageNo, pageSize, sellers,false,status);
		if(mbr != null){
			kind = mbr.getKind();
			pageNo = mbr.getPage();
			
			List<MSeller> mSellers = mbr.getResult();
			if(mSellers != null && !mSellers.isEmpty()){
				result = getSellers(mSellers, lon, lat, uid);
			}
			map.put("kind", kind);
			map.put("page", pageNo);
			map.put("sellers", result);
		}
		return map;
		
	}
	
	/**
	 * 感兴趣的新店（更名“猜你喜欢”）
	 * @param recomSellerRequest
	 * @return
	 */
	protected Map<Object,Object> getInterestSellers(RecomSellerRequest recomSellerRequest) {
		Map<Object,Object> map = new HashMap<Object,Object>();
		List<Map<Object,Object>> result = new ArrayList<Map<Object,Object>>();
		Integer kind = recomSellerRequest.getKind();
		String tuid = ObjectUtils.toString(sessionTokenService.getStringForValue(recomSellerRequest.getSessiontoken()));
		Integer uid = null;
		if(StringUtils.isNotEmpty(tuid)){
			uid = Integer.parseInt(tuid);
		}
		Double lat = recomSellerRequest.getLat();
		Double lon = recomSellerRequest.getLon();
		Integer zoneid = recomSellerRequest.getZoneid();
		Integer cityid = recomSellerRequest.getCityid();
		Integer tradeid = recomSellerRequest.getTradeid();
		Integer pageNo = recomSellerRequest.getPage();
		Integer pageSize = recomSellerRequest.getPageSize();
		List<Integer> sellers = recomSellerRequest.getSellerIds();
		MongoBaseResponse<MSeller> mbr = 
			interestSellerService.queryInterestSellers(cityid,zoneid, tradeid, lon, lat, sellers, pageNo, pageSize,false);
		if(mbr != null){
			kind = mbr.getKind();
			pageNo = mbr.getPage();
			List<MSeller> mSellers = mbr.getResult();
			if(mSellers != null && !mSellers.isEmpty()){
				result = getSellers(mSellers, lon, lat, uid);
			}
			map.put("kind", kind);
			map.put("page", pageNo);
			map.put("sellers", result);
		}
		return map;
	}
	
	/**
	 * 与我口味相似的人
	 * @param recomSellerRequest
	 * @return
	 */
	public Map<Object,Object> getSameTasteSellers(RecomSellerRequest recomSellerRequest) {
		Map<Object,Object> map = new HashMap<Object,Object>();
		Integer kind = recomSellerRequest.getKind();
		String tuid = ObjectUtils.toString(sessionTokenService.getStringForValue(recomSellerRequest.getSessiontoken()));
		Integer uid = null;
		if(StringUtils.isNotEmpty(tuid)){
			uid = Integer.parseInt(tuid);
		}
		Double lat = recomSellerRequest.getLat();
		Double lon = recomSellerRequest.getLon();
		Integer zoneid = recomSellerRequest.getZoneid();
		Integer pageNo = recomSellerRequest.getPage();
		Integer pageSize = recomSellerRequest.getPageSize();
		List<Map<Object,Object>> userlist = new ArrayList<Map<Object,Object>>();
		if(uid != null){
			MongoBaseResponse<Map<Object,Object>> mbr = 
				sameTasteSellerService.querySameTasteList(uid, kind, lat,lon,pageNo, pageSize,false);
			if(mbr != null){
				kind = mbr.getKind();
				pageNo = mbr.getPage();
				userlist = mbr.getResult();
				map.put("kind", kind);
				map.put("page", pageNo);
				
			}
		}else{
			userlist = sameTasteSellerService.querySameTasteList(zoneid,lat,lon,pageNo, pageSize,false);
			map.put("kind", "");
			map.put("page", "");
		}
		
		map.put("users", userlist);
		return map;
	}
	
	
	public Map<Object,Object> getUneatSellers(RecomSellerRequest recomSellerRequest){
		Map<Object,Object> map = new HashMap<Object,Object>();
		List<Map<Object,Object>> result = new ArrayList<Map<Object,Object>>();
		String tuid = ObjectUtils.toString(sessionTokenService.getStringForValue(recomSellerRequest.getSessiontoken()));
		Integer uid = null;
		if(StringUtils.isNotEmpty(tuid)){
			uid = Integer.parseInt(tuid);
		}
		Double lat = recomSellerRequest.getLat();
		Double lon = recomSellerRequest.getLon();
		Integer zoneid = recomSellerRequest.getZoneid();
		Integer cityid = recomSellerRequest.getCityid();
		Integer tradeid = recomSellerRequest.getTradeid();
		Integer pageNo = recomSellerRequest.getPage();
		Integer pageSize = recomSellerRequest.getPageSize();
		Integer status = recomSellerRequest.getStatus();
		List<Integer> list = new ArrayList<Integer>();
		MongoBaseResponse<MSeller> mbr = 
		uneatSellerService.queryUneatSellers(uid, zoneid, cityid,tradeid, lon, lat, list, pageNo, pageSize,false,status);
		
		if(mbr != null){
			List<MSeller> mSellers = mbr.getResult();
			if(mSellers != null && !mSellers.isEmpty()){
				result = getSellers(mSellers, lon, lat, uid);
			}
			map.put("kind", mbr.getKind());
			map.put("page", pageNo);
		}
		map.put("sellers", result);
		
		return map;
	}
	
	/**
	 *格式化商铺信息
	 * @param <T>
	 * @param list
	 * @return
	 */
	public List<Map<Object,Object>> getSellers(List<MSeller> list,Double lon,Double lat,Integer uid) {
		List<Map<Object,Object>> result = new ArrayList<Map<Object,Object>>();
		//拼装数据
		if(list != null && !list.isEmpty()){
			for(MSeller seller : list){
				Map<Object,Object> sellermap = new HashMap<Object,Object>();
				sellermap.put("sellerid", seller.getSellerid());//商铺ID
				sellermap.put("sellername", ObjectUtils.toString(seller.getSellername()));//商铺名称
				sellermap.put("tradename", ObjectUtils.toString(seller.getTradename()));//商铺二级分类名称
				sellermap.put("zonename", ObjectUtils.toString(seller.getBusiness()));//商圈名称
//				sellermap.put("consumption", ObjectUtils.toString(seller.getConsumption()));//销量
				sellermap.put("consumption", ObjectUtils.toString(seller.getSeller_random_num_consumption()));//假销量
				
				//查询商铺是否存在套餐
				//TODO待定
				Map<Object,Object> combomap = sellerPackageDao.findOneBySellerId(seller.getSellerid());//通过店铺ID判断店铺是否存在套餐
				sellermap.put("combos",0);//套餐标签: 0 没有 1 有
				if(combomap != null && combomap.size() > 0){
					sellermap.put("combos",1);
					sellermap.put("combocoin",ObjectUtils.toString(combomap.get("combocoin")));
				}
				
				//获取商铺封面图
				String cover = "";
				
				/**
				 * 应需求要求，改为logo图
				 */
//				if(seller.getPic_logo() != null && !seller.getPic_logo().isEmpty()){
//					JSONObject json = JSONObject.parseObject(seller.getPic_logo());
//					if(json != null && !json.isEmpty()){
//						cover = fileUrl+json.getString("picurl");
//					}
//				}
				
				
				if(seller.getPic_cover() == null || seller.getPic_cover().isEmpty()){
					//从商铺详情图中获取一张图片做为封面图
					if(seller.getPic_pics() != null && !seller.getPic_pics().isEmpty()){
						JSONArray jsonArr = JSONObject.parseArray(seller.getPic_pics());
						if(jsonArr != null && !jsonArr.isEmpty()){
							JSONObject jsonpic = JSONObject.parseObject(jsonArr.getString(0));
							cover = jsonpic.getString("picurl");
							
								if(StringUtils.isNotEmpty(cover)){
									cover = fileUrl+cover;
								}
						}
					}
				}else{
					JSONObject json = JSONObject.parseObject(seller.getPic_cover());
					if(json != null && !json.isEmpty()){
					cover = json.getString("picurl");
					
						if(StringUtils.isNotEmpty(cover)){
							cover = fileUrl+cover;
						}
					
					}
				}
				
				sellermap.put("cover", cover);
				
				//定位坐标距离商铺距离
				Double slat = 0.0D;//商铺的坐标---纬度
				Double slon = 0.0D;//商铺的坐标---经度
				if(seller.getCoordinate() != null){
					slat = seller.getCoordinate().getLatitude();
					slon = seller.getCoordinate().getLongitude();
				}
				//计算距离
				if(lon != null && lat != null){
					DecimalFormat df = new DecimalFormat("#.#");
					Double distance = GeoHashUtil.getDistance(lon, lat, slon, slat);
					if(distance <= 1000){
						//我附近
						sellermap.put("ranges", df.format(distance)+"m");
						sellermap.put("mark", 4);//我附近
						sellermap.put("title", "我附近");
					}else{
						String recommend = seller.getRecommend();
						if(StringUtils.isNotEmpty(recommend) && recommend.equals("1")){
							sellermap.put("ranges", df.format(distance/1000)+"km");
							sellermap.put("mark", 5);//我附近
							sellermap.put("title", "优质的");
						}else{
							sellermap.put("ranges", df.format(distance/1000)+"km");
							sellermap.put("mark", "");//我附近
							sellermap.put("title", "");
						}
					}
				}
				//用户标签
				if(uid != null){
					
					//获取用户改商铺的状态
					Criteria criteria = new Criteria();
					criteria.and("sellerid").is(seller.getSellerid());//商铺ID
					criteria.and("uid").is(uid);
					
					//排序
					Query query = new Query(criteria);
					query.sort().on("operate", Order.ASCENDING);//升序
					
					List<XmnSeller> xmnSellers = new ArrayList<XmnSeller>();
					try{
						xmnSellers = mongoTemplate.find(query, XmnSeller.class, propertiesUtil.getValue("xmnSeller", "conf_common.properties"));
					}catch(Exception e){
						e.printStackTrace();
					}
					
					if(xmnSellers != null && !xmnSellers.isEmpty()){
						for(XmnSeller xmnSeller : xmnSellers){
							int operate = xmnSeller.getOperate();
							if(operate == 2){
								sellermap.put("mark", 1);//消费过
								sellermap.put("title", "消费过");
								if(xmnSeller.getLast_time() != null){
									Date zdate = DateUtil.parse(xmnSeller.getLast_time());
									sellermap.put("zdate",DateUtil.format(zdate, "MM.dd")+"到");//最后的支付时间
								}else{
									sellermap.put("zdate","");//最后的支付时间
								}
								break;
							}else{
								if(operate == 3){
									sellermap.put("mark", 2);//收藏过
									sellermap.put("title", "收藏过");
									break;
								}else{
									sellermap.put("mark", 3);//浏览过
									sellermap.put("title", "浏览过");
								}
							}
						}
					}
				}
				
				sellermap.put("zhibomark", "");
				sellermap.put("zhibotype", 3);
				
				String flag = getFriendMark(seller.getSellerid(), uid);
				if(StringUtils.isNotEmpty(flag)){
					sellermap.put("zhibomark", flag);
					sellermap.put("zhibotype", 2);
				}
				//通过商铺id查询商铺直播状态
				Map<Object,Object> querylivemap = new HashMap<Object,Object>();
				querylivemap.put("sellerid", ObjectUtils.toString(seller.getSellerid()));//商铺id
				List<LiveRecordInfo> live = new ArrayList<LiveRecordInfo>();
				if(seller.getIs_live() == 1){//正在直播
					sellermap.put("zhibotype", 1);//正在直播
					sellermap.put("zhibomark", "");
					StringBuffer sb = new StringBuffer();
					sb.append("主播");
					Map<String, Object> livermap = xmnSellerSerivce.getLiveInfo(1, String.valueOf(seller.getSellerid()));
					if(livermap != null && !livermap.isEmpty()){
						// 金山云拉流处理
						sellermap.put("subSet", livermap);
						sb.append(ObjectUtils.toString(livermap.get("nname")));
						sb.append("正在店里直播中");
						sellermap.put("zhibomark", sb.toString());
					}
					
				}else{
					String flg = "";
					if(seller.getIs_advance() == 1 || seller.getIs_fans_coupon() == 1){//预告
						sellermap.put("zhibotype", 0);//预告
						sellermap.put("zhibomark", "");
						StringBuffer sb = new StringBuffer();
						sb.append("主播");
						//查询直播结束的
						querylivemap.put("type", 5);//直播结束的
						live = anchorLiveRecordDao.queryLivesBySellerIdAndType(querylivemap);
						if(!live.isEmpty()){
							live = live.size()>4?live.subList(0, 3):live;
							for(LiveRecordInfo l : live){
								sb.append(l.getNname());
								sb.append("、");
							}
							flg = sb.toString();
							flg = flg.subSequence(0, flg.length()-1).toString();
							flg += "等主播来过";
							sellermap.put("zhibomark", flg);
						}else{
							querylivemap.put("type", 0);//预告的
							live = anchorLiveRecordDao.queryLivesBySellerIdAndType(querylivemap);
							if(!live.isEmpty()){
								live = live.size()>3?live.subList(0, 2):live;
								for(LiveRecordInfo l : live){
									sb.append(l.getNname());
									sb.append("、");
								}
								flg = sb.toString();
								flg = flg.subSequence(0, flg.length()-1).toString();
								flg += "等主播即将来直播";
								sellermap.put("zhibomark", flg);
							}else{
								querylivemap.put("type", 4);//历史通告
								live = anchorLiveRecordDao.queryLivesBySellerIdAndType(querylivemap);
								if(!live.isEmpty()){
									live = live.size()>3?live.subList(0, 2):live;
									for(LiveRecordInfo l : live){
										sb.append(l.getNname());
										sb.append("、");
									}
									flg = sb.toString();
									flg = flg.subSequence(0, flg.length()-1).toString();
									flg += "等主播来过";
									sellermap.put("zhibomark", flg);
								}else{
									sellermap.put("zhibomark","");
									sellermap.put("zhibotype", 3);
								}
							}
						}
						
					}
				}
				
				result.add(sellermap);
			}
		}
		return result;
	}

	/**
	 * 专属口味列表
	 */
	@Override
	public Object queryCustomisedSellers(RecomSellerRequest recomSellerRequest) {
		
		Map<Object,Object> map = new HashMap<Object,Object>();
		List<Map<Object,Object>> result = new ArrayList<Map<Object,Object>>();
		String tuid = ObjectUtils.toString(sessionTokenService.getStringForValue(recomSellerRequest.getSessiontoken()));
		Integer uid = null;
		if(StringUtils.isNotEmpty(tuid)){
			uid = Integer.parseInt(tuid);
		}
		Integer kind = recomSellerRequest.getKind();
		Integer zoneid = recomSellerRequest.getZoneid();
		if(recomSellerRequest.getZoneName() != null){
			
			if(recomSellerRequest.getZoneName().equals("全部")){
				zoneid = null;
			}
		}
		Integer cityid = recomSellerRequest.getCityid();
		Integer tradeid = recomSellerRequest.getTradeid();
		if(recomSellerRequest.getTradeName() != null){
			if(recomSellerRequest.getTradeName().equals("不限")){
				tradeid = 0;
			}
		}
		Integer pageNo = recomSellerRequest.getPage();
		Integer pageSize = recomSellerRequest.getPageSize();
		Integer type = recomSellerRequest.getEattype();
		if(recomSellerRequest.getFriendName() != null){
			if(recomSellerRequest.getFriendName().equals("不限")){
				type=0;
			}
		}
		Double minPrice = recomSellerRequest.getMinprice();
		Double maxPrice = recomSellerRequest.getMaxprice();
		if(recomSellerRequest.getPriceName() != null){
			
			if(recomSellerRequest.getPriceName().equals("不限")){
				maxPrice = 0.0;
			}
		}
		Double lat = recomSellerRequest.getLat();
		Double lon = recomSellerRequest.getLon();
		List<MSeller> mSellers = new ArrayList<MSeller>();
		MongoBaseResponse<MSeller> mbr =  new MongoBaseResponse<MSeller>();
		if(uid != null){
			//登录
			mbr =
				customisedSellerService.queryCustomizedSellers(kind, cityid,uid, zoneid, tradeid, minPrice, maxPrice, type, lat, lon, pageNo, pageSize);
		
			if(mbr == null || mbr.getResult().isEmpty()){
				
				mbr = customisedSellerService.queryCustomizedSellers(zoneid, cityid,tradeid, minPrice, maxPrice, lon, lat, pageNo, pageSize);
			}
		}else{
			//未登录
			mbr = customisedSellerService.queryCustomizedSellers(zoneid, cityid,tradeid, minPrice, maxPrice, lon, lat, pageNo, pageSize);
		}
		if(mbr != null){
			mSellers = mbr.getResult();
			if(mSellers != null && !mSellers.isEmpty()){
				result = getSellers(mSellers, lon, lat, uid);
			}
		}
		map.put("kind", mbr.getKind()==null?"":mbr.getKind());
		map.put("pageNo", mbr.getPage()==null?"":mbr.getPage());
		map.put("sellers", result);
		MapResponse response = new MapResponse(ResponseCode.SUCCESS,"成功");
		response.setResponse(map);
		return response;
	}


	/**
	 * 当商圈没有数据的时候，直接给出城市的店铺数据
	 * @return
	 */
	public MongoBaseResponse<MSeller> querySellersByCityId(Integer cityid,Integer pageNo,Integer pageSize,boolean isRandom){
		MongoBaseResponse<MSeller> mbr = new MongoBaseResponse<MSeller>();

		List<MSeller> mSellers = new ArrayList<MSeller>();
		Criteria criteria = new Criteria();
		criteria.and("status").is(3).and("isonline").is(1).and("is_public").is(1);
		criteria.and("city").is(String.valueOf(cityid));
		
		//随机首页的时候，重置pageNo
		if(isRandom){
			Query q = new Query(criteria);
			long counts=0l;
			try{
				counts = mongoTemplate.count(q, propertiesUtil.getValue("seller", "conf_common.properties"));
			}catch(Exception e){
				e.printStackTrace();
			}
			
			if(counts>pageSize){
				pageNo = (int) (counts/pageSize.longValue());
				Random ran = new Random();
				pageNo = ran.nextInt(pageNo);
				if(pageNo<=0){
					pageNo = 1;
				}
			}
		}

		//分页查询商铺信息
		Query query = new Query(criteria);
		query.sort().on("weights", Order.DESCENDING).on("label", Order.DESCENDING).on("consumption", Order.DESCENDING)
		.on("views", Order.DESCENDING).on("saved", Order.DESCENDING);
		query.skip((pageNo-1)*pageSize).limit(pageSize);
		
		try{
			mSellers = 
				mongoTemplate.find(query, MSeller.class,propertiesUtil.getValue("seller", "conf_common.properties"));
		}catch(Exception e){
			e.printStackTrace();
		}
		
		if(mSellers != null){
			mbr.setResult(mSellers);
			mbr.setSource(1);//商圈没有数据,通过城市id查询到的
		}
		
		return mbr;
	}
	
	
	/***
	 * 店铺推荐列表
	 */
	public Object recommendSellerList(RecomSellerRequest recomSellerRequest){
		Map<Object,Object> map = new HashMap<Object,Object>();
		List<Map<Object,Object>> result = new ArrayList<Map<Object,Object>>();
		//用户ID
		String uid = ObjectUtils.toString(sessionTokenService.getStringForValue(recomSellerRequest.getSessiontoken()));
		Integer zoneid = recomSellerRequest.getZoneid();//商圈ID
		Integer tradeid = recomSellerRequest.getTradeid();//商铺ID
		Integer type = recomSellerRequest.getType();//关于我的类型
		Integer pageNo = recomSellerRequest.getPage();
		Integer pageSize = Constant.PAGE_LIMIT;
		Double lon = recomSellerRequest.getLon();
		Double lat = recomSellerRequest.getLat();
		//查询结果集组装查询条件
		Criteria criteria = new Criteria();
		
		criteria.and("status").is(3).and("isonline").is(1).and("is_public").is(1);
		if(zoneid != null){
			criteria.and("zoneid").is(zoneid);
		}
		if(tradeid != null){
			criteria.and("genre").is(tradeid);
		}
		//关于我的过滤条件
		//登录方可有此条件
		if(StringUtils.isNotEmpty(uid)){
			if(type != null){
				List<Integer> sellers = getCriteriaByType(type, uid);
				if(sellers != null){
					criteria.and("sellerid").in(sellers);
				}
			}
		}
		//组装排序条件
		Query query = new Query(criteria);
		
		if(type != null){
			switch(type){
				case 1://我消费过的 按消费量降序排序
					query.sort().on("weights", Order.DESCENDING).on("consumption", Order.DESCENDING);
					break;
				case 2://我收藏过的 按收藏量降序排序
					query.sort().on("weights", Order.DESCENDING).on("saved", Order.DESCENDING);
					break;
				case 3://我浏览过的 按浏览量降序排序
					query.sort().on("weights", Order.DESCENDING).on("views", Order.DESCENDING);
					break;
				case 4://与我口味相似的人也喜欢的 按平台活跃降序排序
					query.sort().on("weights", Order.DESCENDING).on("recommend", Order.DESCENDING).on("consumption", Order.DESCENDING);
					break;
					default:
						break;
			}
		}else{
			query.sort().on("weights", Order.DESCENDING).on("recommend", Order.DESCENDING).on("consumption", Order.DESCENDING).on("views", Order.DESCENDING);
		}
		
		query.skip((pageNo - 1)*pageSize).limit(pageSize);
		
		List<MSeller> mSellers = new ArrayList<MSeller>();
		try{
			
			mSellers = 
				mongoTemplate.find(query, MSeller.class, propertiesUtil.getValue("seller", "conf_common.properties"));
		}catch(Exception e){
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE,"查询数据失败");
		}
		
		if(mSellers != null && !mSellers.isEmpty()){
			result = getSellers(mSellers, lon, lat, Integer.parseInt(uid));
		}
		map.put("sellers", result);
		MapResponse response = new MapResponse(ResponseCode.SUCCESS,"查询成功");
		response.setResponse(map);
		return response;
	}
	
	
	public List<Integer> getCriteriaByType(Integer type,String uid){
		List<Integer> sellers = new ArrayList<Integer>();
		switch(type){
			case 1:
				sellers = getConsumIds(uid);//我消费过
				break;
			case 2:
				sellers = getCollectIds(uid);//我收藏过
				break;
			case 3:
				sellers = getBrowseIds(uid);//我浏览过
				break;
			case 4:
				sellers = getSameLikeSellerIds(uid);
				break;
			default:
				break;
		}
		return sellers;
	}
	
	/**
	 * 获取我消费过的商铺ID
	 * @param uid
	 * @return
	 */
	public List<Integer> getConsumIds(String uid){
		//组装我消费过的商铺ID集合
		List<Integer> sellers = new ArrayList<Integer>();
		//通过用户ID查询mongdb中我消费过的商铺
		
		Criteria criteria = new Criteria();
		criteria.and("uid").is(Integer.parseInt(uid));
		criteria.and("operate").is(2);//我消费过的
		
		//查询我消费过的商铺信息
		List<XmnSeller> xmnSellers = new ArrayList<XmnSeller>();
		try{
			xmnSellers = 
				mongoTemplate.find(new Query(criteria), XmnSeller.class, propertiesUtil.getValue("xmnSeller", "conf_common.properties"));
		}catch(Exception e){
			e.printStackTrace();
		}
		
		if(xmnSellers != null && !xmnSellers.isEmpty()){
			for(XmnSeller xmnSeller : xmnSellers){
				sellers.add(xmnSeller.getSellerid());//消费过的商铺信息
			}
		}
		
		return sellers;
	}
	
	/**
	 * 获取我浏览过的商铺ID
	 * @param uid
	 * @return
	 */
	public List<Integer> getBrowseIds(String uid){
		//组装我消费过的商铺ID集合
		List<Integer> sellers = new ArrayList<Integer>();
		//通过用户ID查询mongdb中我消费过的商铺
		
		Criteria criteria = new Criteria();
		criteria.and("uid").is(Integer.parseInt(uid));
		criteria.and("operate").is(1);//我浏览过的
		
		//查询我消费过的商铺信息
		List<XmnSeller> xmnSellers = new ArrayList<XmnSeller>();
		try{
			xmnSellers = 
				mongoTemplate.find(new Query(criteria), XmnSeller.class, propertiesUtil.getValue("xmnSeller", "conf_common.properties"));
		}catch(Exception e){
			e.printStackTrace();
		}
		
		if(xmnSellers != null && !xmnSellers.isEmpty()){
			for(XmnSeller xmnSeller : xmnSellers){
				sellers.add(xmnSeller.getSellerid());//消费过的商铺信息
			}
		}
		
		return sellers;
	}
	
	/**
	 * 获取我收藏过的商铺ID
	 * @param uid
	 * @return
	 */
	public List<Integer> getCollectIds(String uid){
		//组装我消费过的商铺ID集合
		List<Integer> sellers = new ArrayList<Integer>();
		//查询我收藏的商铺信息
		Map<Object,Object> map = new HashMap<Object,Object>();
		map.put("uid", uid);
		map.put("type", 3);//商铺类型
		
		try{
			sellers = ursCollectDao.queryCollectSellerByUid(map);
		}catch(Exception e){
			e.printStackTrace();
		}
		return sellers;
	}
	
	/**
	 * 获取与我口味相似的人也喜欢的商铺ID
	 * @param uid
	 * @return
	 */
	public List<Integer> getSameLikeSellerIds(String uid){
		//组装我消费过的商铺ID集合
		List<Integer> sellers = new ArrayList<Integer>();
		//通过用户ID查询mongdb中我消费过的商铺
		
		Criteria criteria = new Criteria();
		criteria.and("uid").is(Integer.parseInt(uid));
		criteria.and("operate").is(2);//我消费过的
		
		//查询我消费过的商铺信息
		List<XmnSeller> xmnSellers = new ArrayList<XmnSeller>();
		try{
			xmnSellers = 
				mongoTemplate.find(new Query(criteria), XmnSeller.class, propertiesUtil.getValue("xmnSeller", "conf_common.properties"));
		}catch(Exception e){
			e.printStackTrace();
		}
		
		if(xmnSellers != null && !xmnSellers.isEmpty()){
			
			List<Integer> consumids = new ArrayList<Integer>();
			
			for(XmnSeller xmnSeller : xmnSellers){
				consumids.add(xmnSeller.getSellerid());//消费过的商铺信息
			}
			
			//与我有相同消费的用户
			if(!consumids.isEmpty()){
				Criteria consumCriteria = new Criteria();
				consumCriteria.and("uid").ne(Integer.parseInt(uid));//排除自己
				consumCriteria.and("operate").is(2);
				consumCriteria.and("sellerid").in(consumids);
				
				Query consumQuery = new Query(consumCriteria);
				consumQuery.skip(0).limit(200);//限制200个与我相似的用户
				try{
					xmnSellers = 
						mongoTemplate.find(new Query(consumCriteria), XmnSeller.class, propertiesUtil.getValue("xmnSeller", "conf_common.properties"));
				}catch(Exception e){
					e.printStackTrace();
				}
				//通过我消费的商铺ID查询与我同样消费过的用户ID
				if(xmnSellers != null && !xmnSellers.isEmpty()){
					Set<Integer> set = new HashSet<Integer>();//因为通过ID查询用户，所以需要去重用户
					for(XmnSeller xmnSeller : xmnSellers){
						set.add(xmnSeller.getUid());
					}
					
					//通过组装好的用户ID查询这些用户消费过的商铺ID
					if(!set.isEmpty()){
						Criteria likemeCriteria = new Criteria();
						likemeCriteria.and("operate").is(2);//消费过
						likemeCriteria.and("uid").in(set);
						
						Query likemeQuery = new Query(likemeCriteria);
						likemeQuery.skip(0).limit(400);//限制四百个
						try{
							xmnSellers = 
								mongoTemplate.find(new Query(consumCriteria), XmnSeller.class, propertiesUtil.getValue("xmnSeller", "conf_common.properties"));
						}catch(Exception e){
							e.printStackTrace();
						}
						
						//组装与我口味相似的人也喜欢的商铺ID
						if(!xmnSellers.isEmpty()){
							for(XmnSeller xmnSeller : xmnSellers){
								sellers.add(xmnSeller.getSellerid());
							}
						}
					}
				}
			}
		}
		
		return sellers;
	}
	
	protected String getFriendMark(Integer sellerid,Integer uid) {

		String flag = "";
		
		if(uid != null && sellerid != null){
			Map<Object,Object> map = new HashMap<Object,Object>();
			map.put("uid", uid);//登录用户ID
			map.put("sellerid", sellerid);//登录用户消费的商铺ID
			List<Integer> uids = liveUserDao.findFollowUidBySellerId(map);//我的好友与我消费过同一家商铺的用户ID
			if(uids != null && !uids.isEmpty()){
				List<Map<Object,Object>> ursinfos = ursInfoDao.findFollowNameByUid(uids);
				
				if(ursinfos != null && !ursinfos.isEmpty()){
					ursinfos = ursinfos.size()==3?ursinfos.subList(0, 2):ursinfos;
					StringBuffer sb = new StringBuffer(flag);
					sb.append("好友");
					for(Map<Object,Object> ursinfo : ursinfos){
						//用户正式姓名
						String name = ObjectUtils.toString(ursinfo.get("nname"));
						
						if(StringUtils.isNotEmpty(name)){
							sb.append(name);
						}else{
							
							if(StringUtils.isNotEmpty(ObjectUtils.toString(ursinfo.get("phone")))){
								sb.append(ursinfo.get("phone").toString().substring(7,11));
							}else{
								if(StringUtils.isNotEmpty(ObjectUtils.toString(ursinfo.get("uname")))){
									sb.append(ursinfo.get("uname").toString().substring(3, 7));
								}else{
//									sb.append(ursinfo.get("openid").toString().substring(3, 7));
									sb.append("匿名用户");
								}
							}
						}
						
						sb.append("、");
					}
					
					flag = sb.toString();
					flag = flag.subSequence(0, flag.length()-1).toString();
					flag += "等来过";
				}
			}
	}
		return flag;
	}

	@Override
	public Object querySellerPhotos(SellerPhotoRequest request) {
		Integer sellerid = request.getSellerid();
		Integer page = request.getPage();
		Integer pageSize = request.getPageSize();
		List<ExperienceCommentMedia> photos = experienceCommentDao.listSellerPhotos(sellerid,page,pageSize);
		if(!photos.isEmpty()){
			for (ExperienceCommentMedia media : photos) {
				media.setMediaUrl(fileUrl + media.getMediaUrl());
			}
		}
		Map<Object,Object> result = new HashMap<>();
		result.put("photos", photos);
		
		MapResponse mapResponse = new MapResponse(ResponseCode.SUCCESS, "成功");
		mapResponse.setResponse(result);
		
		return mapResponse;
	}

	@Override
	public Object searchCommentSellerByKeyword(SearchCommentSellerRequest request) {
		Map<Object,Object> paraMap = new HashMap<>();
		paraMap.put("zoneid", request.getZoneid());
		paraMap.put("keyword", request.getKeyword());
		
		//查询商圈范围内的签约店铺
		List<Map<Object,Object>> sellerList = sellerInfoDao.listSellerByKeyword(paraMap);
		if (!sellerList.isEmpty()) {
			for (Map<Object, Object> map : sellerList) {
				String priovince = map.get("province") + "";
				String city = map.get("city") + "";
				if (priovince.equals(city)) {
					map.put("priovince", "");
				}
				
				String picUrl = map.get("picUrl") + "";
				if(StringUtils.isNotEmpty(picUrl)){
					picUrl = fileUrl + picUrl;
				}
				map.put("picUrl", picUrl);
				
				map.put("sellerType", 1);
			} 
		}
		//查询商圈范围内通过点评的非签约店铺
		List<Map<Object,Object>> unsignedSellerList = unsignedSellerDao.listSellerbyKeyword(paraMap);
		
		if (!unsignedSellerList.isEmpty()) {
			for (Map<Object, Object> map : unsignedSellerList) {
				
				String priovince = map.get("province") + "";
				String city = map.get("city") + "";
				
				if (priovince.equals(city)) {
					map.put("priovince", "");
				}
				
				String picUrl = map.get("picUrl") + "";
				if(StringUtils.isNotEmpty(picUrl)){
					picUrl = fileUrl + picUrl;
				}
				map.put("picUrl", picUrl);
				
				map.put("sellerType", 2);
				sellerList.add(map);
			} 
		}
		Map<Object,Object> result = new HashMap<>();
		result.put("sellerList", sellerList);
		
		MapResponse mapResponse = new MapResponse(ResponseCode.SUCCESS, "成功");
		mapResponse.setResponse(result);
		
		return mapResponse;
	}
}
