package com.xmniao.xmn.core.xmer.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.Constant;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.TopicalRankRequest;
import com.xmniao.xmn.core.xmer.dao.SellerDao;
import com.xmniao.xmn.core.xmer.dao.UrsEarningsRelationDao;
import com.xmniao.xmn.core.xmer.dao.XmerDao;
import com.xmniao.xmn.core.xmer.entity.TopRankXmer;


/**
 * 
*    
* 项目名称：xmnService   
* 类名称：TopicalRankService   
* 类描述：   热点人物排行列表service
* 创建人：yezhiyong   
* 创建时间：2016年5月24日 上午10:40:33   
* @version    
*
 */
@Service
public class TopicalRankService {
	
	
	/**
	 * 注入redis 缓存
	 */
	@Autowired
	private SessionTokenService sessionTokenService;
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	/**
	 * 注入寻蜜客Dao
	 */
	@Autowired
	private XmerDao xmerDao;
	 
	@Resource
	private String fileUrl;
	
	@Autowired
	private UrsEarningsRelationDao ursEarningsRelationDao;
	
	@Autowired
	private SellerDao sellerDao;
	
	@Autowired
	private XmerAmountService xmerAmountService;

	/**
	 * 
	* @Title: queryTopicalRank
	* @Description: 分页查询热点人物排行
	* @return Object    返回类型
	* @throws
	 */
	/*
	   public Object queryTopicalRankList(TopicalRankRequest topicalRankRequest) {
		//获取redis中的数据
		int pagesize=topicalRankRequest.getPage();//当前页
		int pagenum=Constant.PAGE_LIMIT;//每页显示数量
		Set<String> set=sessionTokenService.getZSetForValue(Constant.XMER_RANK_KEY, 0,-1);
		String uid=sessionTokenService.getStringForValue(topicalRankRequest.getSessiontoken())+"";
		
		//结果集
		Map<Object,Object> map=new HashMap<>();
		List<Object> ranksList=new ArrayList<>();//排行数据
		int i=0;
		map.put("rankno","无");
		
		//查询寻蜜客销售数量
		Integer soldnums =  topicalRankDao.queryXmerByUid(Integer.parseInt(uid));
		map.put("soldnums", soldnums);
		if(soldnums==null)map.put("soldnums", 0);
		
		//获取热点排行的排行以及销售数量
		if(set!=null&&set.size()>0){
			for(String jsonStr :set){
				i++;
				JSONObject jsonObject=JSONObject.parseObject(jsonStr);
				if(uid.equals(jsonObject.getString("uid"))){
					map.put("rankno", jsonObject.get("rankno"));
					map.put("soldnums", jsonObject.get("soldnums"));
					if(i>=pagesize*pagenum)break;	//如果找到了该uid并且排名找齐 了停止循环
				}
				if(i>(pagesize-1)*pagenum&&i<pagesize*pagenum+1){
					jsonObject.put("userpic", fileUrl+jsonObject.get("userpic"));
					ranksList.add(jsonObject);
				}
			}
		}else{//没有则查询数据库
			List<Map<Object,Object>> xmerlist=xmerDao.queryXmerPageList();
			for(Map<Object,Object> xmerMap:xmerlist){
				i++;
				if(uid.equals(xmerMap.get("uid"))){
					map.put("rankno", xmerMap.get("rankno"));
					map.put("soldnums", xmerMap.get("soldnums"));
					if(i>=pagesize*pagenum)break;	//如果找到了该uid并且排名找齐 了
				}
				if(i>(pagesize-1)*pagenum&&i<pagesize*pagenum+1){
					xmerMap.put("userpic",fileUrl+xmerMap.get("userpic"));
					ranksList.add(xmerMap);
				}
				
				sessionTokenService.setZSetForValue(Constant.XMER_RANK_KEY, JSONObject.toJSONString(xmerMap), i);
			}
							
		}
		
		//响应
		MapResponse mapResponse=new MapResponse(ResponseCode.SUCCESS, "成功");
		map.put("ranks",ranksList);
		mapResponse.setResponse(map);
		return mapResponse;
	}
*/
	
	public Object queryNewTopicalRankList(TopicalRankRequest topicalRankRequest) {
		
		int pagesize=topicalRankRequest.getPage();//当前页
		int pagenum=Constant.PAGE_LIMIT;//每页显示数量
		String uid=sessionTokenService.getStringForValue(topicalRankRequest.getSessiontoken())+"";
		
		Set<String> set=sessionTokenService.getZSetForValue(Constant.XMER_RANK_KEY, 0,-1);
		
		//结果集
		Map<Object,Object> map=new HashMap<>();
		List<Object> ranksList=new ArrayList<>();//排行数据
		int j=0;
		
		if(set!=null&&set.size()>0){
			for(String jsonStr :set){
				j++;
				JSONObject jsonObject = JSONObject.parseObject(jsonStr);
				if(uid.equals(jsonObject.getString("uid"))){
					map.put("sort", jsonObject.get("sort"));
					map.put("sellerNums", jsonObject.get("sellerNums"));
					map.put("rankno", jsonObject.get("sort"));
					map.put("soldnums", jsonObject.get("sellerNums"));
				}
				if(j>(pagesize-1)*pagenum && j<pagesize*pagenum+1){
					ranksList.add(jsonObject);
				}
			}
		}else{//没有则查询数据库
			
			if(stringRedisTemplate.hasKey(Constant.LIVE_XMERTOP_REDIS)){
				return new BaseResponse(ResponseCode.FAILURE,"排行榜数据正在更新，请稍后再试！");
			}
			
			stringRedisTemplate.opsForValue().increment(Constant.LIVE_XMERTOP_REDIS, 1);
			
			try {
				List<TopRankXmer> xmerList = listTopRankXmer();
				
				for (TopRankXmer xmer : xmerList) {
					xmer.setSort(++j);
					if(uid.equals(xmer.getUid() + "")){
						map.put("sort",xmer.getSort());
						map.put("sellerNums", xmer.getSellerNums());
						map.put("rankno", xmer.getSort());
						map.put("soldnums", xmer.getSellerNums());
					}
					if(j>(pagesize-1)*pagenum && j<pagesize*pagenum+1){
						ranksList.add(xmer);
					}
					sessionTokenService.setZSetForValue(Constant.XMER_RANK_KEY, JSONObject.toJSONString(xmer), xmer.getSort());
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				return new BaseResponse(ResponseCode.FAILURE,"排行榜数据失败");
			}finally {
				stringRedisTemplate.delete(Constant.LIVE_XMERTOP_REDIS);
			}
		}
		
		
		/** 361 之前版本， 数据字段不一样，要进行转换 */
		String appversion = topicalRankRequest.getAppversion();
		appversion = appversion.replace(".", "");
		int appv = Integer.parseInt(appversion);
		if(appv<361){
			
			Object ob = ranksList.get(0);
			if(ob instanceof TopRankXmer){
				List<Object> list = new ArrayList<>(ranksList.size());
				for (int i = 0; i < ranksList.size(); i++) {
					JSONObject jsonObject = new JSONObject();
					TopRankXmer xmer = (TopRankXmer) ranksList.get(i);
					jsonObject.put("rankno", xmer.getSort());
					jsonObject.put("username", xmer.getName());
					jsonObject.put("soldnums", xmer.getSellerNums());
					jsonObject.put("userpic", xmer.getAvatar());
					jsonObject.put("partnernums", xmer.getPartnerNums());
					jsonObject.put("allincome", xmer.getIncome());
					list.add(jsonObject);
				}
				ranksList = list;
			}else {
				for(Object o :ranksList){
					JSONObject jsonObject = (JSONObject) o;
					jsonObject.put("rankno", jsonObject.get("sort"));
					jsonObject.put("username", jsonObject.get("name"));
					jsonObject.put("soldnums", jsonObject.get("sellerNums"));
					jsonObject.put("userpic", jsonObject.get("avatar"));
					jsonObject.put("partnernums", jsonObject.get("partnerNums"));
					jsonObject.put("allincome", jsonObject.get("income"));
				}
			}
		}
		
		//响应
		MapResponse mapResponse=new MapResponse(ResponseCode.SUCCESS, "成功");
		map.put("ranks",ranksList);
		mapResponse.setResponse(map);
		return mapResponse;
	}
	
	/**
	 * 查询所有寻蜜客的 伙伴数量，签约店铺数量，总流水，并按签约店铺数量排序
	 * @Title:listTopRankXmer
	 * @Description: 寻蜜客排行数据
	 * @return List<TopRankXmer> 排序后的寻蜜客集合
	 * 2017年5月31日下午7:43:27
	 */
	public List<TopRankXmer> listTopRankXmer(){
		List<TopRankXmer> xmerList = xmerDao.listXmerRank();
		
		//遍历所有的寻蜜客，根据id，查询伙伴数量、签约店铺数量、总流水
		for(TopRankXmer xmer:xmerList){
			
			Integer xmerUid = xmer.getUid();
			
			//用户昵称
			if(StringUtils.isEmpty(xmer.getName())){
				String uname = xmer.getUname();
				if (StringUtils.isNotEmpty(uname)){
					uname = uname.substring(0, 3) + "****" + uname.substring(7); 
					xmer.setName(uname);
				}
			}
			
			//查询发展的伙伴数量
			List<Integer> relationList = ursEarningsRelationDao.queryRelationList(xmerUid);
			xmer.setPartnerNums(relationList.size());
			
			//通过关系连查询中的签约上线店铺数量
			Integer soldnums = sellerDao.queryReadyNumByUids(relationList,xmerUid);
			xmer.setSellerNums(soldnums);
			
			//调用支付接口，查询总收入
			DecimalFormat df = new DecimalFormat("0.00");// 格式化数据
			String income = "0.00";
			
			Map<Object, Object> amountMap = xmerAmountService.amount(xmerUid);
			
			if(null != amountMap && null != amountMap.get("totalIncome")){
				Double amount = Double.parseDouble(amountMap.get("totalIncome")+"");
				income = df.format(new BigDecimal(amount).setScale(2, RoundingMode.HALF_UP).doubleValue());// 格式化后的寻蜜客总收入
			}
			xmer.setIncome(income);
			
			xmer.setAvatar(fileUrl + xmer.getAvatar());
			
		}
		// 根据签约店铺数量排序。
		Collections.sort(xmerList);
		return xmerList;
	}
}
