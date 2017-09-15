package com.xmniao.xmn.core.seller.service.impl;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xmniao.xmn.core.base.BaseRequest;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.catehome.entity.mongo.MSeller;
import com.xmniao.xmn.core.catehome.entity.mongo.XmnSeller;
import com.xmniao.xmn.core.catehome.entity.mongo.XmnTrade;
import com.xmniao.xmn.core.common.Constant;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.RemoveBCRecordRequeset;
import com.xmniao.xmn.core.common.request.seller.UserSellerRequest;
import com.xmniao.xmn.core.live.dao.AnchorLiveRecordDao;
import com.xmniao.xmn.core.live.entity.LiveRecordInfo;
import com.xmniao.xmn.core.seller.dao.UrsCollectDao;
import com.xmniao.xmn.core.seller.service.UserSellerService;
import com.xmniao.xmn.core.util.ArithUtil;
import com.xmniao.xmn.core.util.GeoHashUtil;
import com.xmniao.xmn.core.util.PropertiesUtil;
import com.xmniao.xmn.core.verification.dao.BillDao;
import com.xmniao.xmn.core.verification.dao.UrsDao;
import com.xmniao.xmn.core.verification.service.UrsService;
import com.xmniao.xmn.core.xmer.dao.SellerBrowsedDao;
import com.xmniao.xmn.core.xmer.dao.SellerInfoDao;
import com.xmniao.xmn.core.xmer.service.SellerService;

/**
 * 
* @projectName: xmnService 
* @ClassName: UserSellerServiceImpl    
* @Description:跟用户有关的商铺接口实现类  
* @author: liuzhihao   
* @date: 2016年11月23日 下午4:55:07
 */
@Service
public class UserSellerServiceImpl implements UserSellerService{
	
	//浏览记录表dao
	@Autowired
	private SellerBrowsedDao sellerBrowsedDao;
	
	//商铺dao
	@Autowired
	private SellerInfoDao sellerInfoDao;
	
	@Autowired
	private BillDao billDao;
	
	@Autowired
	private UrsCollectDao ursCollectDao;
	
	@Autowired
	private String fileUrl;
	
	@Autowired
	private PropertiesUtil propertiesUtil;
	
	@Autowired
	private SessionTokenService sessionTokenService;
	
	@Autowired
	private UrsService ursService;
	
	/**
	 * mongodb的操作对象，处理所有对mongodb的增删改查操作
	 */
	@Resource
	private MongoTemplate mongoTemplate;
	
	@Autowired
	private AnchorLiveRecordDao anchorLiveRecordDao;
	
	@Autowired
	private UrsDao ursDao;
	
	private MapResponse	response = null;
	
	@Autowired
	private SellerService sellerService;
	/**
	 * 关于我的商铺列表
	 */
	@Override
	public Object mainSellerList(UserSellerRequest userSellerRequest) {
		//用户id
		List<Map<Object,Object>> result = new ArrayList<Map<Object,Object>>();
		Map<Object,Object> map = new HashMap<Object,Object>();
		String uid = ObjectUtils.toString(sessionTokenService.getStringForValue(userSellerRequest.getSessiontoken()));
//		String uid = "604898";
		if(userSellerRequest.getStatus() != 1){
			if(StringUtils.isNotEmpty(uid)){
				Integer type = userSellerRequest.getType();//关于我的分类 1 消费 2 收藏 3 浏览 4口味相似
				switch(type){
				case 1://我消费过的
					result = querySellerByConsum(userSellerRequest);
					break;
				case 2://我收藏过的
					result = querySellerByCollect(userSellerRequest);
					break;
				case 3://我浏览过的
					result = querySellerByBrowse(userSellerRequest);
					break;
				case 4://口味相似的人也喜欢
					result = samePersonLikeList(userSellerRequest);
					break;
				}
				response = new MapResponse(ResponseCode.SUCCESS,"成功");
			}else{
				
				response = new MapResponse(ResponseCode.TOKENERR,"token,过期请重新登录");
			}
		}else{
			response = new MapResponse(ResponseCode.SUCCESS,"正在建设中,敬请期待.....");
		}
		map.put("sellers", result);
		response.setResponse(map);
		return response;
	}
	
	/**
	 * 浏览记录
	 */
	@Override
	public List<Map<Object, Object>> querySellerByBrowse(UserSellerRequest userSellerRequest) {
		List<Map<Object,Object>> result = new ArrayList<Map<Object,Object>>();
		String uid = sessionTokenService.getStringForValue(userSellerRequest.getSessiontoken()).toString();
		Integer zoneid = userSellerRequest.getZoneid();//商圈ID
		Integer tradeid = userSellerRequest.getTradeid();//分类ID
		//查询商铺信息
		Map<Object,Object> queryMap = new HashMap<Object,Object>();
		queryMap.put("uid", uid);
		queryMap.put("type", userSellerRequest.getStatus());
		queryMap.put("pageNo", userSellerRequest.getPage());
		queryMap.put("pageSize", Constant.PAGE_LIMIT);
		if(zoneid != null){
			queryMap.put("zoneid", zoneid);
		}
		if(tradeid != null){
			queryMap.put("tradeid", tradeid);
		}
		List<Integer> browses = sellerBrowsedDao.findAllByUid(queryMap);
		if(browses != null && !browses.isEmpty()){
			result = getSellers(browses,userSellerRequest.getLat(),userSellerRequest.getLon(), zoneid, tradeid);
			for(Map<Object,Object> map : result){
				
				//判断我是否消费过这家商铺
				Map<Object,Object> queryConsummap = new HashMap<Object,Object>();
				queryConsummap.put("uid", uid);//用户id
				queryConsummap.put("sellerid", map.get("sellerid"));//商铺id
				
				Map<Object,Object> billmap = billDao.queryBillBySellerIdAndUid(queryConsummap);
				if(billmap != null && !billmap.isEmpty()){
					//有消费过
					map.put("mark", 1);//一级优先权
					map.put("title", getMark(1));
				}else{
					//判断是否收藏过
					queryConsummap.put("type", userSellerRequest.getType());//收藏类型 0 商家 1 积分
					int collects = ursCollectDao.queryCollectBySellerIdAndUid(queryConsummap);
					if(collects != 0){
						map.put("mark", 2);//二级优先权，我收藏过
						map.put("title", getMark(2));
					}else{
						map.put("mark", 3);//浏览过
						map.put("title", getMark(3));
					}
				}
			}
		}
		return result;
	}

	/**
	 * 收藏记录
	 */
	@Override
	public List<Map<Object, Object>> querySellerByCollect(UserSellerRequest userSellerRequest) {
		List<Map<Object,Object>> result = new ArrayList<Map<Object,Object>>();
		String uid = sessionTokenService.getStringForValue(userSellerRequest.getSessiontoken()).toString();
		Integer zoneid = userSellerRequest.getZoneid();//商圈ID
		Integer tradeid = userSellerRequest.getTradeid();//分类ID
		//通过用户id查询用户收藏的商铺信息
		Map<Object,Object> queryMap = new HashMap<Object,Object>();
		queryMap.put("uid", uid);
		queryMap.put("type", userSellerRequest.getStatus());
		queryMap.put("pageNo", userSellerRequest.getPage());
		queryMap.put("pageSize", Constant.PAGE_LIMIT);
		queryMap.put("zoneid", zoneid);
		queryMap.put("tradeid", tradeid);
		
		List<Integer> collects = ursCollectDao.queryCollectSellerByUid(queryMap);
		if(collects != null && !collects.isEmpty()){
			result = getSellers(collects,userSellerRequest.getLat(),userSellerRequest.getLon(), zoneid, tradeid);
			for(Map<Object,Object> map : result){
				//判断我是否消费过这家商铺
				Map<Object,Object> queryConsummap = new HashMap<Object,Object>();
				queryConsummap.put("uid", uid);//用户id
				queryConsummap.put("sellerid", map.get("sellerid"));//商铺id
				
				Map<Object,Object> billmap = billDao.queryBillBySellerIdAndUid(queryConsummap);
				if(billmap != null && !billmap.isEmpty()){
					//有消费过
					map.put("mark", 1);//一级优先权
					map.put("title", getMark(1));
				}else{
					map.put("mark", 2);//二级优先权，我收藏过
					map.put("title", getMark(2));
				}
			}
		}
		return result;
	}

	/**
	 * 消费记录
	 * @param userSellerRequest
	 * @return
	 */
	public List<Map<Object,Object>> querySellerByConsum(UserSellerRequest userSellerRequest){
		Integer zoneid = userSellerRequest.getZoneid();//商圈ID
		Integer tradeid = userSellerRequest.getTradeid();//分类ID
		List<Map<Object,Object>> result = new ArrayList<Map<Object,Object>>();
		String uid = sessionTokenService.getStringForValue(userSellerRequest.getSessiontoken()).toString();
		//通过用户id查询用户收藏的商铺信息
		Map<Object,Object> queryMap = new HashMap<Object,Object>();
		queryMap.put("uid", uid);
		queryMap.put("type", userSellerRequest.getStatus());
		queryMap.put("pageNo", userSellerRequest.getPage());
		queryMap.put("pageSize", Constant.PAGE_LIMIT);
		queryMap.put("zoneid", zoneid);
		queryMap.put("tradeid", tradeid);
		List<Integer> consums = billDao.querySellersByUid(queryMap);
		if(consums != null && !consums.isEmpty()){
			result = getSellers(consums,userSellerRequest.getLat(),userSellerRequest.getLon(), zoneid, tradeid);
			for(Map<Object,Object> map : result){
				map.put("mark", 1);//一级优先权
				map.put("title", getMark(1));
			}
		}
		return result;
	}
	
	//通过商铺id查询商铺信息
	protected List<Map<Object,Object>> getSellers(List<Integer> sellers,Double lat,Double lon,Integer zoneid,Integer tradeid) {
		List<Map<Object,Object>> result = new ArrayList<Map<Object,Object>>();
		if(sellers != null && !sellers.isEmpty()){
			for(Integer sellerid : sellers){
				Map<Object,Object> map = new HashMap<Object,Object>();
				//通过商铺id查询商铺信息
				Map<Object,Object> paramMap = new HashMap<Object,Object>();
				paramMap.put("lon", lon);
				paramMap.put("lat", lat);
				paramMap.put("sellerid", sellerid);
				if(zoneid != null){
					paramMap.put("zoneid", zoneid);
				}
				if(tradeid != null){
					paramMap.put("tradeid", tradeid);
				}
				
				Map<Object,Object> sellermap = sellerInfoDao.querySellerAndRangesBySellerId(paramMap);
				
				//通过商铺id查询订单数量
				Integer consums = billDao.consumeCount(sellerid);
				//店铺消费人次 = 真实消费人数 + redis存放的伪造数据
				Integer consumption = sellerService.getConsuCount(consums==null?0:consums, sellerid);
				map.put("consumption", consumption);
				
				//通过商铺id查询商铺图片
				List<Map<Object,Object>> logos = new ArrayList<Map<Object,Object>>();//logo图
				List<Map<Object,Object>> coves = new ArrayList<Map<Object,Object>>();//封面图
				List<Map<Object,Object>> environments = new ArrayList<Map<Object,Object>>();//环境图
				if(sellermap != null && !sellermap.isEmpty()){
					map.put("sellerid", sellermap.get("sellerid"));//商铺id
					map.put("sellername", sellermap.get("sellername"));//商铺名称
					map.put("tradename", sellermap.get("tradename"));//商铺二级分类名称
					map.put("zonename", sellermap.get("zonename"));//商圈名字
					map.put("zhibotype", 2);//商圈名字
					if (sellermap.get("liveCount") != null && sellermap.get("preCount") != null) {
						if (Integer.parseInt(sellermap.get("preCount").toString()) > 1) {
							//有预告
							map.put("zhibotype", 0);
						}
						
						if (Integer.parseInt(sellermap.get("liveCount").toString()) > 1) {
							//有正在直播的
							map.put("zhibotype", 1);
						}
						
					}
					
					if (sellermap.get("ranges") != null && Double.parseDouble(sellermap.get("ranges").toString()) > 0) {
						//格式化距离
						String ranges = sellermap.get("ranges").toString();
						if(Double.parseDouble(ranges) < 1000){
							ArithUtil.subZeroAndDot(ranges);
							map.put("ranges", "约"+ranges+"m");
						}else{
							Double r = Double.parseDouble(ranges);
							r = ArithUtil.div(r, 1000);
							DecimalFormat df = new DecimalFormat("#.#");
							ranges = df.format(r);
							map.put("ranges", "约"+ranges+"km");
						}
						
					}else {
						map.put("ranges", "");
					}
				
				List<Map<Object, Object>> images = sellerInfoDao.querySellerPic(sellerid);
				if(images != null && !images.isEmpty()){
					for(Map<Object,Object> image : images){
						Integer islogo = Integer.parseInt(image.get("islogo").toString());
						Map<Object,Object> imagemap = new HashMap<Object,Object>();
						imagemap.put("islogo", islogo);//图片类型
						imagemap.put("id", image.get("id"));//图片id
						imagemap.put("cover", fileUrl+image.get("url"));//图片url
						switch(islogo){
							case 0:
								coves.add(imagemap);
								break;
							case 1:
								logos.add(imagemap);
								break;
							case 2:
								environments.add(imagemap);
								break;
						}
					}
				}
				
				}
				
				if(!coves.isEmpty()){
					Map<Object,Object> covermap = coves.get(0);
					map.put("id", covermap.get("id"));//封面id
					map.put("islogo", covermap.get("islogo"));//图片类型
					map.put("cover",covermap.get("cover"));
				}else{
					if(environments != null && !environments.isEmpty()){
						for(Map<Object,Object> image : environments){ 
							map.put("islogo", 2);//图片类型
							map.put("id", image.get("id"));//图片id
							map.put("cover", image.get("cover"));//图片url
							break;
						}
					}else{
						map.put("islogo", "");//图片类型
						map.put("id", "");//图片id
						map.put("cover", "");//图片url
					}
				}
				
				if(map != null && !map.isEmpty()){
					result.add(map);
				}
		}
	}
		return result;
}
	
	protected String getMark(Integer type) {
		String mark = "";
		try {
			mark = propertiesUtil.getValue("userMark", "conf_common.properties");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			mark = "{"+"1:"+"消费过"+","+"2:"+"已收藏"+","+"3:"+"浏览过"+"}";
		}
		
		JSONObject json = JSONObject.parseObject(mark);
		switch(type){
			case 1:
				mark = json.getString("1");
				break;
			case 2:
				mark = json.getString("2");
				break;
			case 3:
				mark = json.getString("3");
				break;
		}
		return mark;
	}

	/**
	 * 删除浏览记录
	 */
	@Override
	public int removeBrowse(RemoveBCRecordRequeset removeBCRecordRequeset) {
		try{
			//删除浏览记录
			Map<Object,Object> removeMap = new HashMap<Object,Object>();
			String uid = sessionTokenService.getStringForValue(removeBCRecordRequeset.getSessiontoken()).toString();
//			String uid = "337542";//测试用
			Integer sellerid = removeBCRecordRequeset.getId();
			Integer type = removeBCRecordRequeset.getType();
			removeMap.put("uid", uid);
			removeMap.put("sellerid", sellerid);
			removeMap.put("type", type);
			
			return sellerBrowsedDao.removeBrowseBySelleridAndUid(removeMap);
			
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * 删除收藏记录
	 */
	@Override
	public int removeCollect(RemoveBCRecordRequeset removeBCRecordRequeset) {
		try{
			//删除浏览记录
			Map<Object,Object> removeMap = new HashMap<Object,Object>();
			String uid = sessionTokenService.getStringForValue(removeBCRecordRequeset.getSessiontoken()).toString();
//			String uid = "337542";//测试用
			Integer sellerid = removeBCRecordRequeset.getId();
			Integer type = removeBCRecordRequeset.getType();
			removeMap.put("uid", uid);
			removeMap.put("sellerid", sellerid);
			removeMap.put("type", type);
			
			
			int flag= ursCollectDao.removeCollectByUidAndSellerId(removeMap);
			/*修改mongodb权重*/
			if(flag==1){
				ursService.removeViewActionService(sellerid, Integer.parseInt(uid), 0);
			}
			return flag;
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<Map<Object, Object>> queryAboutUs(BaseRequest baseRequest) {
		List<Map<Object,Object>> result = new ArrayList<Map<Object,Object>>();
		//从配置文件中获取与我相关的菜单列表
		String menu ="";
		try {
			menu = propertiesUtil.getValue("aboutUs", "conf_common.properties");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			menu ="{"+"type:"+0+","+"title:"+"全部"+"}"+"," 
				+"["+"{"+"type:"+1+","+"title:"+"我消费过"+"}"+","
				+"{"+"type:"+2+","+"title:"+"我已收藏"+"}"+","
				+"{"+"type:"+3+","+"title:"+"我浏览过"+"}"+","
				+"{"+"type:"+4+","+"title:"+"我附近的"+"}"+","
				+"{"+"type:"+5+","+"title:"+"与我相似口味的人也喜欢"+"}"+"]";
				
		}
		
		JSONArray json = JSONObject.parseArray(menu);
		for(int i=0; i<json.size(); i++){
			Map<Object,Object> map = new HashMap<Object,Object>();
			JSONObject jsonMenu = json.getJSONObject(i);
			map.put("menuId", jsonMenu.get("type"));
			map.put("menuName", jsonMenu.get("title"));
			result.add(map);
		}
		
		return result;
	}
	
	/**
	 * 浏览过的mongodb
	 * @param userSellerRequest
	 * @return
	 */
	@Override
	public List<Map<Object, Object>> queryBrowseList(UserSellerRequest userSellerRequest) {
		List<Map<Object,Object>> result = new ArrayList<Map<Object,Object>>();
		//获取用户ID
		String uid = ObjectUtils.toString(sessionTokenService.getStringForValue(userSellerRequest.getSessiontoken()));
		Double lon = userSellerRequest.getLon();
		Double lat = userSellerRequest.getLat();
		//从mongDB中查询用户浏览的商铺信息
		Criteria criteria = new Criteria();
		criteria.and("uid").is(uid);
		criteria.and("operate").is("1");
		
		Query query = new Query(criteria);
		
		List<XmnSeller> xmnSellers = new ArrayList<XmnSeller>();
		try{
			xmnSellers = mongoTemplate.find(query, XmnSeller.class, propertiesUtil.getValue("xmnSeller", "conf_common.properties"));
		}catch(Exception e){
			e.printStackTrace();
		}
		Set<String> sellerids = new HashSet<String>();
		if(!xmnSellers.isEmpty()){
			for(XmnSeller xmnSeller : xmnSellers){
				sellerids.add(xmnSeller.getSellerid().toString());
			}
		}
		
		if(!sellerids.isEmpty()){
			//查询我消费过的商铺
			Criteria cta = new Criteria();
			cta.and("status").is(3);
			cta.and("isonline").is(1);
			cta.and("is_public").is(1);
			cta.and("sellerid").in(sellerids);
			cta.and("coordinate").near(new Point(lat, lon));
			if(userSellerRequest.getZoneid() != null){
				cta.and("zoneid").is(userSellerRequest.getZoneid());
			}
			if(userSellerRequest.getTradeid() != null){
				cta.and("genre").is(userSellerRequest.getTradeid().toString());
			}
			Query q = new Query(cta);
			q.sort().on("weights", Order.DESCENDING).on("views", Order.DESCENDING);
			q.skip((userSellerRequest.getPage()-1)*Constant.PAGE_LIMIT).limit(Constant.PAGE_LIMIT);
			
			List<MSeller> mSellers = new ArrayList<MSeller>();
			try{
				mSellers = mongoTemplate.find(q, MSeller.class, propertiesUtil.getValue("seller", "conf_common.properties"));
			}catch(Exception e){
				e.printStackTrace();
			}
			
			if(!mSellers.isEmpty()){
				result = getSellers(mSellers, lat, lon, uid);
			}
		}
		return result;
	}

	/**
	 * 消费mongodb
	 * @param userSellerRequest
	 * @return
	 */
	@Override
	public List<Map<Object, Object>> queryConsumList(UserSellerRequest userSellerRequest) {
		List<Map<Object,Object>> result = new ArrayList<Map<Object,Object>>();
		//获取用户ID
		String uid = ObjectUtils.toString(sessionTokenService.getStringForValue(userSellerRequest.getSessiontoken()));
//		String uid = "604898";
		Double lon = userSellerRequest.getLon();
		Double lat = userSellerRequest.getLat();
		//从mongDB中查询用户浏览的商铺信息
		Criteria criteria = new Criteria();
		criteria.and("uid").is(Integer.parseInt(uid));
		criteria.and("operate").is(2);
		
		Query query = new Query(criteria);
		
		List<XmnSeller> xmnSellers = new ArrayList<XmnSeller>();
		try{
			xmnSellers = mongoTemplate.find(query, XmnSeller.class, propertiesUtil.getValue("xmnSeller", "conf_common.properties"));
		}catch(Exception e){
			e.printStackTrace();
		}
		Set<Integer> sellerids = new HashSet<Integer>();
		if(!xmnSellers.isEmpty()){
			for(XmnSeller xmnSeller : xmnSellers){
				sellerids.add(xmnSeller.getSellerid());
			}
		}
		
		if(!sellerids.isEmpty()){
			//查询我消费过的商铺
			Criteria cta = new Criteria();
			cta.and("status").is(3);
			cta.and("isonline").is(1);
			cta.and("is_public").is(1);
			cta.and("sellerid").in(sellerids);
			cta.and("coordinate").near(new Point(lat, lon));
			if(userSellerRequest.getZoneid() != null){
				cta.and("zoneid").is(userSellerRequest.getZoneid());
			}
			if(userSellerRequest.getTradeid() != null){
				cta.and("genre").is(userSellerRequest.getTradeid());
			}
			Query q = new Query(cta);
			q.sort().on("weights", Order.DESCENDING).on("consumption", Order.DESCENDING);
			q.skip((userSellerRequest.getPage()-1)*Constant.PAGE_LIMIT).limit(Constant.PAGE_LIMIT);
			
			List<MSeller> mSellers = new ArrayList<MSeller>();
			try{
				mSellers = mongoTemplate.find(q, MSeller.class, propertiesUtil.getValue("seller", "conf_common.properties"));
			}catch(Exception e){
				e.printStackTrace();
			}
			
			if(!mSellers.isEmpty()){
				result = getSellers(mSellers, lat, lon, uid);
			}
		}
		return result;
	}

	/**
	 * 收藏mongodb
	 * @param userSellerRequest
	 * @return
	 */
	@Override
	public List<Map<Object, Object>> queryCollectList(UserSellerRequest userSellerRequest) {
		List<Map<Object,Object>> result = new ArrayList<Map<Object,Object>>();
		//获取用户ID
		String uid = ObjectUtils.toString(sessionTokenService.getStringForValue(userSellerRequest.getSessiontoken()));
//		String uid = "604898";
		Double lon = userSellerRequest.getLon();
		Double lat = userSellerRequest.getLat();
		Set<Integer> sellerids = new HashSet<Integer>();
		//通过用户ID查询用户收藏的商铺信息
		List<Map<Object,Object>> bills = billDao.queryBillByUid(Integer.parseInt(uid));
		if(bills != null && !bills.isEmpty()){
			for(Map<Object,Object> bill : bills){
				sellerids.add(Integer.parseInt(bill.get("sellerid").toString()));
			}
		}
		
		if(!sellerids.isEmpty()){
			Criteria criteria = new Criteria();
			criteria.and("status").is(3).and("isonline").is(1).and("is_public").is(1);
			criteria.and("sellerid").in(sellerids);
			criteria.and("coordinate").near(new Point(lat, lon));
			if(userSellerRequest.getZoneid() != null){
				criteria.and("zoneid").is(userSellerRequest.getZoneid());
			}
			if(userSellerRequest.getTradeid() != null){
				criteria.and("genre").is(userSellerRequest.getTradeid());
			}
			Query query = new Query(criteria);
			query.sort().on("weights", Order.DESCENDING).on("saved", Order.DESCENDING);
			query.skip((userSellerRequest.getPage()-1)*Constant.PAGE_LIMIT).limit(Constant.PAGE_LIMIT);
			
			List<MSeller> mSellers = new ArrayList<MSeller>();
			try{
				mSellers = mongoTemplate.find(query, MSeller.class, propertiesUtil.getValue("seller", "conf_common.properties"));
			}catch(Exception e){
				e.printStackTrace();
			}
			
			if(!mSellers.isEmpty()){
				result = getSellers(mSellers, lat, lon, uid);
			}
		}
		return result;
	}
	
	protected List<Map<Object,Object>> getSellers(List<MSeller> list,Double lat,Double lon,String uid) {
		List<Map<Object,Object>> result = new ArrayList<Map<Object,Object>>();
		if(list != null && !list.isEmpty()){
			for(MSeller seller : list){
				Map<Object,Object> sellermap = new HashMap<Object,Object>();
				sellermap.put("sellerid", seller.getSellerid());//商铺ID
				sellermap.put("sellername", ObjectUtils.toString(seller.getSellername()));//商铺名称
				sellermap.put("tradename", ObjectUtils.toString(seller.getTradename()));//商铺二级分类名称
				sellermap.put("zonename", ObjectUtils.toString(seller.getBusiness()));//商圈名称
				sellermap.put("consumption", ObjectUtils.toString(seller.getConsumption()));//销量
				//获取商铺封面图
				String cover = "";
				if(seller.getPic_cover() == null || seller.getPic_cover().isEmpty()){
					//从环境图中取一张图片做为封面图
					if(seller.getPic_pics() == null || seller.getPic_pics().isEmpty()){
						cover = "";
					}else{
						cover = seller.getPic_pics();
						JSONArray jsonArr = JSONObject.parseArray(cover);
						if(jsonArr != null && !jsonArr.isEmpty()){
							JSONObject jsonpic = JSONObject.parseObject(jsonArr.getString(0));
							cover = fileUrl+jsonpic.getString("picurl");
						}
					}
				}else{
					cover = seller.getPic_cover();
					JSONObject json = JSONObject.parseObject(cover);
					if(json != null && !json.isEmpty()){
						cover = fileUrl+json.getString("picurl"); 
					}
				}
				
				sellermap.put("cover", cover);//封面图
				//计算用户距离商铺的距离
				Double lon1 = 0.0;//经度
				Double lat1 = 0.0;//纬度
				//计算坐标距离商铺的距离
				if(StringUtils.isNotEmpty(ObjectUtils.toString(seller.getCoordinate().getLatitude()))){
					lat1 = Double.parseDouble(ObjectUtils.toString(seller.getCoordinate().getLatitude()));
				}
				if(StringUtils.isNotEmpty(ObjectUtils.toString(seller.getCoordinate().getLongitude()))){
					lon1 = Double.parseDouble(ObjectUtils.toString(seller.getCoordinate().getLongitude()));
				}
				Double ranges = GeoHashUtil.getDistance(lon, lat, lon1, lat1);
				DecimalFormat df = new DecimalFormat("#.0");
				
				if(ranges < 1000){
					sellermap.put("ranges", df.format(ranges)+"m");
					sellermap.put("mark", 4);//我附近
					sellermap.put("title", "我附近");
				}else /*if(ranges >=1000 && ranges <= 5000)*/{
					sellermap.put("ranges", df.format(ArithUtil.div(ranges, 1000))+"km");
					if(ObjectUtils.toString(seller.getRecommend()).equals("1")){//平台推荐店铺
						sellermap.put("mark", 5);
						sellermap.put("title", "优质的");
					}else{
						sellermap.put("mark", "");
						sellermap.put("title", "");
					}
				}
				
				
				//用户标签
				if(StringUtils.isNotEmpty(uid)){
					Map<Object,Object> querymap = new HashMap<Object,Object>();
					querymap.put("uid", uid);
					querymap.put("sellerid", ObjectUtils.toString(seller.getSellerid()));
					Map<Object,Object> boillmap = billDao.queryBillBySellerIdAndUid(querymap);
					if(boillmap != null && !boillmap.isEmpty()){
						sellermap.put("mark", 1);//消费过
						sellermap.put("title", "消费过");
					}else{
						int collects = ursCollectDao.queryCollectBySellerIdAndUid(querymap);
						if(collects != 0){
							sellermap.put("mark", 2);//收藏过
							sellermap.put("title", "收藏过");
						}else{
							int browses = sellerBrowsedDao.queryBrowsedBySellerIdAndUid(querymap);
							if(browses != 0){
								sellermap.put("mark", 3);//收藏过
								sellermap.put("title", "我浏览过");
							}
						}
					}
				}
				
				
				//通过商铺id查询商铺直播状态
				Map<Object,Object> querylivemap = new HashMap<Object,Object>();
				querylivemap.put("sellerid", ObjectUtils.toString(seller.getSellerid()));//商铺id
				List<LiveRecordInfo> live = new ArrayList<LiveRecordInfo>();
				if(ObjectUtils.toString(seller.getIs_live()).equals("1")){//正在直播
					querylivemap.put("type", 1);
					//获取正在直播的主播信息
					live = anchorLiveRecordDao.queryLivesBySellerIdAndType(querylivemap);
					if(!live.isEmpty()){
						sellermap.put("zhibomark", "主播"+live.get(0).getNname()+"正在店里直播中");
						sellermap.put("zhibotype", 1);//正在直播
					}else{
						sellermap.put("zhibomark", "");
						sellermap.put("zhibotype", 2);
					}
					
				}else if(ObjectUtils.toString(seller.getIs_advance()).equals("1")){//预告
					//查询直播结束的
					querylivemap.put("type", 5);//直播结束的
					live = anchorLiveRecordDao.queryLivesBySellerIdAndType(querylivemap);
					if(!live.isEmpty()){
						String zhibomark = "";
						for(LiveRecordInfo l : live){
							zhibomark += l.getNname()+"、";
						}
						zhibomark.subSequence(0, zhibomark.lastIndexOf("、")-1);
						sellermap.put("zhibomark", "主播"+zhibomark+"等主播来过");
						sellermap.put("zhibotype", 0);//预告
					}else{
						sellermap.put("zhibomark", "");
						sellermap.put("zhibotype", 2);
					}
					
				}else{
					sellermap.put("zhibomark", "");
					sellermap.put("zhibotype", 2);
				}
				
				result.add(sellermap);
			}
		}
		
		return result;
	}

	@Override
	public List<Map<Object, Object>> samePersonLikeList(UserSellerRequest userSellerRequest) {
		List<Map<Object,Object>> result = new ArrayList<Map<Object,Object>>();
		//获取用户的ID
		String uid = ObjectUtils.toString(sessionTokenService.getStringForValue(userSellerRequest.getSessiontoken()));
		if(StringUtils.isNotEmpty(uid)){
			//通过登录用户ID查询他关注的人
			List<Map<Object,Object>> users = ursDao.findMyFocusByUid(Integer.parseInt(uid));
			Set<Integer> set = new HashSet<Integer>();
			if(users != null && !users.isEmpty()){
				//我关注的人
				for(Map<Object,Object> user : users){
					//我关注的用户的ID
					Integer fuid = Integer.parseInt(user.get("fuid").toString());
					set.add(fuid);
				}
				
			}
			/**
			 * 如果我是新用户，或者我没关注过人，则从和我分类一级浏览和消费过相同商铺的用户中搜
			 */
			
			//查询mongDB的分类表 和商铺表
			List<XmnTrade> xmnTrades = new ArrayList<XmnTrade>();
			Criteria tradeCta = new Criteria();
			tradeCta.and("uid").is(Integer.parseInt(uid));
			try{
				xmnTrades = 
					mongoTemplate.find(new Query(tradeCta), XmnTrade.class, propertiesUtil.getValue("xmnTrade", "conf_common.properties"));
			}catch(Exception e){
				e.printStackTrace();
			}
			List<Integer> tradeids = new ArrayList<Integer>();//分类id
			if(xmnTrades != null && !xmnTrades.isEmpty()){
				for(XmnTrade xmnTrade : xmnTrades){
					tradeids.add(xmnTrade.getGenre());
				}
			}
			
			//查询和我相同分类的用户
			Criteria sameCta = new Criteria();
			sameCta.and("uid").ne(Integer.parseInt(uid));
			sameCta.and("genre").in(tradeids);
			try{
				xmnTrades = 
					mongoTemplate.find(new Query(tradeCta), XmnTrade.class, propertiesUtil.getValue("xmnTrade", "conf_common.properties"));
			}catch(Exception e){
				e.printStackTrace();
			}
			
			if(xmnTrades != null && !xmnTrades.isEmpty()){
				for(XmnTrade xmnTrade : xmnTrades){
 					set.add(xmnTrade.getUid());//与我分类相同的用户ID
				}
			}
			
			//查询与我浏览或者消费相同过商铺的人
			
			Criteria sellerTrade = new Criteria();
			sellerTrade.and("uid").is(Integer.parseInt(uid));
			List<XmnSeller> xmnSellers = new ArrayList<XmnSeller>();
			
			try{
				xmnSellers =
					mongoTemplate.find(new Query(sellerTrade), XmnSeller.class, propertiesUtil.getValue("xmnSeller", "conf_common.properties"));
			}catch(Exception e){
				e.printStackTrace();
			}
			
			List<Integer> sellerids = new ArrayList<Integer>();
			if(xmnSellers != null && !xmnSellers.isEmpty()){
				for(XmnSeller xmnSeller : xmnSellers){
					sellerids.add(xmnSeller.getSellerid());
				}
			}
			
			Criteria ssCta = new Criteria();
			ssCta.and("uid").ne(Integer.parseInt(uid));
			ssCta.and("sellerid").in(sellerids);//我浏览或者消费过的商铺ID
			
			try{
				xmnSellers = 
					mongoTemplate.find(new Query(ssCta), XmnSeller.class, propertiesUtil.getValue("xmnSeller", "conf_common.properties"));
			}catch(Exception e){
				e.printStackTrace();
			}
			
			if(xmnSellers != null && !xmnSellers.isEmpty()){
				for(XmnSeller xmnSeller : xmnSellers){
					set.add(xmnSeller.getUid());//与我浏览和消费过同样商铺的用户ID
				}
			}
			
			if(set != null && !set.isEmpty()){
				Criteria criteria = new Criteria();
				criteria.and("operate").is(2);
				criteria.and("uid").in(set);//查询 跟我相似的用户消费过的商铺id
				
				try{
					xmnSellers = 
						mongoTemplate.find(new Query(criteria), XmnSeller.class, propertiesUtil.getValue("xmnSeller", "conf_common.properties"));
				}catch(Exception e){
					e.printStackTrace();
				}
				//最终与我相似的人也喜欢的商铺ID
				Set<Integer> ids = new HashSet<Integer>();
				if(xmnSellers != null && !xmnSellers.isEmpty()){
					for(XmnSeller xmnSeller : xmnSellers){
						ids.add(xmnSeller.getSellerid());
					}
				}
				
				if(ids != null && !ids.isEmpty()){
					
					Criteria c = new Criteria();
					c.and("status").is(3).and("isonline").is(1).and("is_public").is(1);
					c.and("sellerid").in(ids);
					if(userSellerRequest.getZoneid() != null){
						c.and("zoneid").is(userSellerRequest.getZoneid());
					}
					if(userSellerRequest.getTradeid() != null){
						c.and("genre").is(userSellerRequest.getTradeid());
					}
					Query q = new Query(c);
					q.sort().on("weights", Order.DESCENDING).on("consumption", Order.DESCENDING);
					q.skip((userSellerRequest.getPage()-1)*Constant.PAGE_LIMIT).limit(Constant.PAGE_LIMIT);
					List<MSeller> mSellers = new ArrayList<MSeller>();
					try{
						mSellers = 
							mongoTemplate.find(q, MSeller.class, propertiesUtil.getValue("seller", "conf_common.properties"));
					}catch(Exception e){
						e.printStackTrace();
					}
					
					if(mSellers != null && !mSellers.isEmpty()){
						result = getSellers(mSellers, userSellerRequest.getLat(), userSellerRequest.getLon(), uid);
					}
				}
			}
		}
		return result;
	}

}
