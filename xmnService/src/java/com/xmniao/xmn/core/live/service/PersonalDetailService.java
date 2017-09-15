/**
 * 2016年8月16日 上午10:48:46
 */
package com.xmniao.xmn.core.live.service;

import java.io.IOException;
import java.util.*;

import com.xmniao.xmn.core.common.ObjResponse;
import com.xmniao.xmn.core.market.dao.ProductInfoDao;
import com.xmniao.xmn.core.market.entity.pay.ProductInfo;
import com.xmniao.xmn.core.sellerPackage.dao.SellerPackagePicDao;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.Constant;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.live.AnchorLiveRecordRequest;
import com.xmniao.xmn.core.live.dao.AnchorLiveRecordDao;
import com.xmniao.xmn.core.live.dao.PersonalDetailDao;
import com.xmniao.xmn.core.live.entity.LiveRecordInfo;
import com.xmniao.xmn.core.personal.service.PersonalInfoDetailService;
import com.xmniao.xmn.core.util.DateUtil;

/**
 * @项目名称：xmnService_3.1.2_zb
 * @类名称：PersonalListService
 * @类描述：
 * @创建人： yeyu
 * @创建时间 2016年8月16日 上午10:48:46
 * @version
 */
@Service
public class PersonalDetailService {
	//日志
	private final Logger log = Logger.getLogger(PersonalDetailService.class);
	@Autowired
	private PersonalDetailDao personaldetailDao;
	@Autowired
	private PersonalCenterService personalcenterService;
	
	@Autowired
	private EggToBirdMoneyService eggtobirdmoneyService;
	
	private List<Map<Object,Object>> resultList=null;
	
	private Map<Object,Object> resultMap=null;
	//注入服务器地址  图片服务器配置地址
	@Autowired
	private String fileUrl;
	
	/**
	 * 注入anchorLiveRecordService
	 */
	@Autowired
	private AnchorLiveRecordService anchorLiveRecordService;
	
	/**
	 * 注入anchorLiveRecordDao
	 */
	@Autowired
	private AnchorLiveRecordDao anchorLiveRecordDao;
	
	/**
	 * 注入personalInfoDetailService
	 */
	@Autowired
	private PersonalInfoDetailService personalInfoDetailService;
	
	/**
	 * 注入liveHomeService
	 */
	@Autowired
	private LiveHomeService liveHomeService;
	
	/**
	 * 注入sessionTokenService
	 */
	@Autowired
	private SessionTokenService sessionTokenService;

	@Autowired
	private ProductInfoDao productInfoDao;

	@Autowired
	private SellerPackagePicDao sellerPackagePicDao;
	
	public Object queryPersonList(Integer uid,Integer type,Integer page)
	{
		try{
		Map<Object,Object> personMap=personalcenterService.queryLiverPersonByUid(uid);
		if(personMap==null || personMap.size()<=0){
			return 	new BaseResponse(ResponseCode.FAILURE, "获取用户信息失败");
		}
		//获取客户ID
		Integer id=personMap.get("anchorid")==null?0:Integer.parseInt(personMap.get("anchorid").toString());
		
		//查询充值套餐
		if(type==1){
			return this.queryRechargeList(uid, page);
		}else if(type==2){//送出鸟蛋列表
			return this.queryGiveEggList(id, page);
		}else if(type==3){//查询关注列表
			//叶熠之前版本
//			return this.queryAttentionFocusList(id, page);
			return personalInfoDetailService.queryFocusList(uid, page, 2, uid + "");
			
		}else if(type==4){//查询粉丝列表
			//叶熠之前版本
//			return this.queryAttentionFansList(id, page);
			return personalInfoDetailService.queryFansList(uid, page, 2, uid + "");
			
		}else if(type==5){//黑名单列表
			return personalcenterService.queryAttentionBlackAnchor(id+"",page);
		}
		/*else if(type==5){//查询直播记录
			if(month==null || "".equals(month)){
				return new BaseResponse(ResponseCode.FAILURE, "月份不能为空");
			}
			if(personMap.get("utype")!=null && personMap.get("utype").equals(1)){//如果是主播的话查询直播记录
				return this.queryLiveRecordList(id, month, page);
			}
			return new BaseResponse(ResponseCode.FAILURE, "未查找到直播记录");
		}*/
		}catch(Exception e){
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE, e.getMessage());
		}
		return 	new BaseResponse(ResponseCode.FAILURE, "获取信息失败");
	}
	
	
	
	
	/**
	 * 
	* @Title: queryAchorLiveRecord
	* @Description: 查看直播列表
	* @return Object
	 */
	public Object queryAchorLiveRecord(AnchorLiveRecordRequest anchorLiveRecordRequest){
		//获取uid
		String uid = sessionTokenService.getStringForValue(anchorLiveRecordRequest.getSessiontoken()) + "";
		if (StringUtils.isEmpty(uid) || "null".equalsIgnoreCase(uid)) {
			return new BaseResponse(ResponseCode.TOKENERR, "token已失效,请重新登录");
		}
		
		//主播uid
		int uuid = Integer.parseInt(uid);
		Integer type = anchorLiveRecordRequest.getType();
		Integer month = anchorLiveRecordRequest.getMonth();
		Integer page = anchorLiveRecordRequest.getPage();
		Integer year = anchorLiveRecordRequest.getYear();
		
		try{
			Map<Object,Object> personMap=personalcenterService.queryLiverPersonByUid(uuid);
			if(personMap==null || personMap.size()<=0){
				return 	new BaseResponse(ResponseCode.FAILURE, "获取用户信息失败");
			}
			
			//获取客户ID
			Integer anchorId = personMap.get("anchorid")==null?0:Integer.parseInt(personMap.get("anchorid").toString());
			//直播记录
			if (type == 1) {
				return this.queryLiveRecordList(anchorId, month, page,year);
				
			//直播回放
			}else if (type == 2) {
				return this.queryPlaybackLiveRecord(personMap, month, page,year);
				
			}else {
				return new BaseResponse(ResponseCode.FAILURE, "type参数有误,请重试");
				
			}
		}catch(Exception e){
			log.error("获取直播记录/直播回放失败"+e.getMessage());
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE, "获取直播记录/直播回放失败");
		}
	}
	
	/**
	 * 
	* @Title: queryPlaybackLiveRecord
	* @Description: 查询直播回放
	* @return Object    返回类型
	* @throws
	 */
	public Object queryPlaybackLiveRecord(Map<Object, Object> personMap,Integer month,Integer page,Integer year) {
		try {
			//结果
			Map<Object, Object> resultMap = new HashMap<Object, Object>();
			List<Map<Object, Object>> resultList = new ArrayList<>();
			resultMap.put("playbackRecordList", resultList);
			
			Map<Object, Object> paramMap = new HashMap<>();
			
			//格式化日期
			String startDate = DateUtil.format(year, month - 1, null);
			String endDate = DateUtil.format(year, month, null);
			
			paramMap.put("anchorId", personMap.get("anchorid")==null?0:Integer.parseInt(personMap.get("anchorid").toString()));
			paramMap.put("startDate", startDate);
			paramMap.put("endDate", endDate);
			paramMap.put("page", page);
			paramMap.put("limit", Constant.PAGE_LIMIT);
			
			//查询直播回放列表
			List<LiveRecordInfo> playbackLiveRecordList = anchorLiveRecordDao.queryPlaybackLiveRecord(paramMap);
			
			if (playbackLiveRecordList != null && playbackLiveRecordList.size() > 0) {
				//更新需要返回直播/回放的列表字段数据
				resultList = anchorLiveRecordService.updateLiveRecordList(playbackLiveRecordList);
				
				for (Map<Object, Object> liveRecordMap : resultList) {
					//获取用户与商家标签
					Map<Object, Object> tagMap = liveHomeService.getConsumerTagName(Integer.parseInt(liveRecordMap.get("sellerId").toString()), Integer.parseInt(personMap.get("uid").toString()), "");
					liveRecordMap.put("tag", tagMap.get("tag"));
					liveRecordMap.put("lable", tagMap.get("lable"));
					
					//查询商家的直播预售数量(预告和直播的记录总数)
					Integer preLiveRecordCount = anchorLiveRecordDao.queryPreLiveRecordCountBySellerId(Integer.parseInt(liveRecordMap.get("sellerId").toString()));
					liveRecordMap.put("preLiveRecordCount", preLiveRecordCount);
				}
				
				resultMap.put("playbackRecordList", resultList);
			}
			
			//响应
			MapResponse response = new MapResponse(ResponseCode.SUCCESS, "查询直播回放列表成功");
			response.setResponse(resultMap);
			return response;
			
		} catch (IOException e) {
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE, "查询直播回放列表失败");
			
		}
	}
	
	
	/**
	 * 
	* @Title: queryRechargeList
	* @Description: 查询充值套餐
	* @return Object
	 * @throws Exception 
	 */
	public Object queryRechargeList(int uid,int page) throws Exception{
		resultMap=new HashMap<Object,Object>();
		resultList=new ArrayList<Map<Object,Object>>();
		
		List<Map<Object,Object>> rechargeList=null;
		Map<Object,Object> param=new HashMap<Object,Object>();
		try {
			param.put("page", page);
			param.put("limit", Constant.PAGE_LIMIT);
			rechargeList=personaldetailDao.queryRechargeList(param);
	
			Map<Object,Object> eggmap=eggtobirdmoneyService.queryBirdEggByUid(uid);
			//获取鸟币余额
			resultMap.put("personBirdCoin", Double.parseDouble(eggmap.get("commision").toString()));
			if(rechargeList!=null && rechargeList.size()>0){
				for(Map<Object,Object> rechargeMap:rechargeList){
					Map<Object,Object> rgMap= new HashMap<Object,Object>();
					//充值金额
					rgMap.put("rechargePrice", Double.parseDouble(rechargeMap.get("rech_amount").toString()));
					//充值鸟币
					rgMap.put("rechargeBirdCoin", Double.parseDouble(rechargeMap.get("rech_norm_coin").toString()));
					resultList.add(rgMap);
				}
			}else{
				resultMap.put("birdRechargePackageList", resultList);
				MapResponse response = new MapResponse(ResponseCode.SUCCESS,"未获取到充值套餐");
				response.setResponse(resultMap);
				return response;
			}
			resultMap.put("birdRechargePackageList", resultList);
		} catch (Exception e) {
			log.error("查询充值套餐失败");
			e.printStackTrace();
			throw new Exception("查询充值套餐失败");
		}
		//响应
		MapResponse response = new MapResponse(ResponseCode.SUCCESS,"获取充值套餐成功");
		response.setResponse(resultMap);
		return response;
	}
	
	/**
	 * 
	* @Title: queryGiveEggList
	* @Description: 送出鸟蛋列表
	* @return Object
	 * @throws Exception 
	 */
	public Object queryGiveEggList(int liver_id,int page) throws Exception{
		resultMap=new HashMap<Object,Object>();
		resultList=new ArrayList<Map<Object,Object>>();
		
		List<Map<Object,Object>> giveFitList=null;
		Map<Object,Object> param=new HashMap<Object,Object>();
		try {
			param.put("liver_id", liver_id);
			param.put("page", page);
			param.put("limit", Constant.PAGE_LIMIT);
			
			//查询用户送出鸟蛋记录列表
			giveFitList=personaldetailDao.queryGiveEggList(param);
			
			if(giveFitList != null && giveFitList.size() >0){
				
				Map<Object,Object> bmMap=this.queryBarrageMessageNum(liver_id);
				Map<Object,Object> liverMap=personalcenterService.queryLiverPersonById(liver_id);
				
				String avatar=(liverMap.get("avatar")==null||liverMap.get("avatar").equals(""))?"":(fileUrl+liverMap.get("avatar").toString());
				
				resultMap.put("barrageNums", bmMap.get("barragenum"));//弹幕数量
				resultMap.put("privateMessageNums", bmMap.get("messagenum"));//私信数量
				
				//计算本周，本月，共送的鸟豆值
				Map<Object, Object> sumMap = personaldetailDao.queryGiftSendDiffSum(liver_id);
				resultMap.put("sendWeekSum", Integer.parseInt(sumMap.get("sendWeekSum")==null?"0":sumMap.get("sendWeekSum").toString()));//本周送的鸟豆值
				resultMap.put("sendMonthSum", Integer.parseInt(sumMap.get("sendMonthSum")==null?"0":sumMap.get("sendMonthSum").toString()));//本月送的鸟豆值
				resultMap.put("sendAllSum", Integer.parseInt(sumMap.get("sendAllSum")==null?"0":sumMap.get("sendAllSum").toString()));//共送的鸟豆值
				
				List<String> ids=new ArrayList<String>();
				HashSet<Integer> pIdSet = new HashSet<Integer>(); //实体礼物
				HashSet<Integer> ptIdSet = new HashSet<Integer>(); //套餐
				for(Map<Object,Object> giveFitMap:giveFitList){
					//获取主播ID
					Integer anchor_id=Integer.parseInt(giveFitMap.get("anchor_id").toString());
					ids.add(anchor_id+"");
					try {
						Integer gift_source = Integer.parseInt(giveFitMap.get("gift_source").toString());
						Integer sale_product_id = Integer.parseInt(giveFitMap.get("sale_product_id").toString());
						if (gift_source == 1) {  //套餐
							ptIdSet.add(sale_product_id);
						} else if (gift_source == 2) {  //实体礼物
							pIdSet.add(sale_product_id);
						}
					} catch (Exception e) {
						e.printStackTrace();
						log.warn("获取实体礼物Id失败");
					}
				}
				Map<Object, ProductInfo> productInfoMap = new HashMap<Object, ProductInfo>();
				Map<Object, Object> packagePicMap = new HashMap<Object, Object>();
				// 查询套餐礼物图片
				List<Object> ptIds = Arrays.asList(ptIdSet.toArray());
				if (ptIds.size() > 0) {
					try {
						Map<Object, Object> paramMap = new HashMap<Object, Object>();
						paramMap.put("ids", ptIds);
						List<Map<Object, Object>> packageList = sellerPackagePicDao.getCoverImageByIds(paramMap);
						if (packageList != null) {
							for (Map<Object, Object> map : packageList) {
								packagePicMap.put(map.get("pid"), map.get("pic_url"));
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
						log.warn("查询套餐礼物图片失败");
					}
				}
				// 查询实体礼物图片
				List<Object> pIds = Arrays.asList(pIdSet.toArray());
				try {
					Map<Object, Object> paramMap = new HashMap<Object, Object>();
					paramMap.put("pids", pIds);
					List<ProductInfo> productInfoList = productInfoDao.selectByPrimaryKeyList(paramMap);
					for (ProductInfo productInfo : productInfoList) {
						productInfoMap.put(productInfo.getPid(), productInfo);
					}
				} catch (Exception e) {
					e.printStackTrace();
					log.warn("查询实体礼物图片失败");
				}

				//根据主播id查询用户信息 
				List<Map<Object,Object>> personList=personalcenterService.queryLiverPersonByListId(ids);
						for(Map<Object,Object> giveFitMap:giveFitList){
							Map<Object,Object> rgMap= new HashMap<Object,Object>();
							//获取主播ID
							int anchor_id=Integer.parseInt(giveFitMap.get("anchor_id").toString());
							if(personList!=null && personList.size()>0){
								int gift_source = 0;
								try {
									gift_source = Integer.parseInt(giveFitMap.get("gift_source").toString());
								} catch (Exception e) {
									log.warn("gift_source转换失败");
								}
								String gift_avatar = "";
								Integer sale_product_id = Integer.parseInt(giveFitMap.get("sale_product_id").toString());
								if (gift_source ==  1) {
									Object pic_url = packagePicMap.get(sale_product_id);
									gift_avatar = pic_url == null ? "" : fileUrl + String.valueOf(pic_url);
								} else if (gift_source == 2) {
									ProductInfo productInfo =  productInfoMap.get(sale_product_id);
									String breviary = productInfo == null ? "" : productInfo.getBreviary(); //缩略图
									gift_avatar = breviary == null ? "" : fileUrl + String.valueOf(breviary);
								} else {
									gift_avatar = giveFitMap.get("gift_avatar")==null||giveFitMap.get("gift_avatar").equals("")?"":(fileUrl+giveFitMap.get("gift_avatar").toString());
								}
								if (gift_avatar.trim().equals("")) {
									log.warn("获取图片路径为空" + giveFitMap.toString());
								}

								rgMap.put("avatar", avatar);//用户头像
								rgMap.put("giftPic", gift_avatar);//礼物图片
								rgMap.put("giftName", giveFitMap.get("gift_name"));//礼物名称
								rgMap.put("giftNums", giveFitMap.get("gift_nums"));//礼物数量
								rgMap.put("sendDate", giveFitMap.get("create_time"));//赠送时间
								for(Map<Object,Object> viewerInfo:personList){
									int id=viewerInfo.get("anchorid")==null?-1:Integer.parseInt(viewerInfo.get("anchorid").toString());
									if(id==anchor_id){
										rgMap.put("nname", viewerInfo.get("nname"));//主播昵称
										break;
									}
								}
							}
							resultList.add(rgMap);
							}
						resultMap.put("sendList", resultList);
			}
		} catch (Exception e) {
			log.error("查询送出鸟蛋列表失败");
			e.printStackTrace();
			throw new Exception("查询送出鸟蛋列表失败");
		}
		//响应
		MapResponse response = new MapResponse(ResponseCode.SUCCESS,"获取送出鸟蛋记录成功");
		response.setResponse(resultMap);
		return response;
	}
	
	/**
	 * 
	* @Title: queryAttentionFocusList
	* @Description: 查询关注列表
	* @return Object
	 * @throws Exception 
	 */
	public Object queryAttentionFocusList(int liver_str_id,int page) throws Exception{
		resultMap=new HashMap<Object,Object>();
		resultList=new ArrayList<Map<Object,Object>>();
		List<Map<Object,Object>> attentionList=null;
		Map<Object,Object> param=new HashMap<Object,Object>();
		try {
			
			param.put("liver_str_id", liver_str_id);
			param.put("page", page);
			param.put("limit", Constant.PAGE_LIMIT);
			attentionList=personaldetailDao.queryAttentionFocusList(param);
			if(attentionList!=null && attentionList.size()>0){
				List<String> ids=new ArrayList<String>();
				for(Map<Object,Object> attentionMap:attentionList)
				{
					//获取主播ID
					Integer liver_end_id=Integer.parseInt(attentionMap.get("liver_end_id").toString());
					ids.add(liver_end_id+"");
				}
				//根据主播id查询用户信息
				List<Map<Object,Object>> personList=personalcenterService.queryLiverPersonByListId(ids);
				if(personList!=null && personList.size()>0){
					for(Map<Object,Object> personMap:personList){
						Map<Object,Object> rgMap= new HashMap<Object,Object>();
						String avatar=personMap.get("avatar")==null||personMap.get("avatar").equals("")?"":(fileUrl+personMap.get("avatar").toString());
						rgMap.put("id", personMap.get("anchorid"));//主播id
						rgMap.put("nname", personMap.get("nname"));//昵称
						rgMap.put("avatar", avatar);//头像
						rgMap.put("gender", personMap.get("sex"));//性别0:男    1:女
						rgMap.put("levels", personMap.get("rank_no"));//等级
						resultList.add(rgMap);
					}
				}else{
					resultMap.put("attentionList", resultList);
					MapResponse response = new MapResponse(ResponseCode.SUCCESS,"未关注其他用户");
					response.setResponse(resultMap);
					return response;
				}
				resultMap.put("attentionList", resultList);
			}else{
				resultMap.put("attentionList", resultList);
				MapResponse response = new MapResponse(ResponseCode.SUCCESS,"未关注其他用户");
				response.setResponse(resultMap);
				return response;
			}
		} catch (Exception e) {
			log.error("查询查询关注列表失败");
			e.printStackTrace();
			throw new Exception("查询查询关注列表失败");
		}
		//响应
		MapResponse response = new MapResponse(ResponseCode.SUCCESS,"获取关注列表成功");
		response.setResponse(resultMap);
		return response;
	}
	
	/**
	 * 
	* @Title: queryAttentionFocusList
	* @Description: 查询粉丝列表
	* @return Object
	 * @throws Exception 
	 */
	public Object queryAttentionFansList(int liver_end_id,int page) throws Exception{
		resultMap=new HashMap<Object,Object>();
		resultList=new ArrayList<Map<Object,Object>>();
		
		List<Map<Object,Object>> fansList=null;
		Map<Object,Object> param=new HashMap<Object,Object>();
		try {
			
			param.put("liver_end_id", liver_end_id);
			param.put("page", page);
			param.put("limit", Constant.PAGE_LIMIT);
			fansList=personaldetailDao.queryAttentionFansList(param);
			
			if(fansList!=null && fansList.size()>0){
				List<String> ids=new ArrayList<String>();
				for(Map<Object,Object> fansMap:fansList)
				{
					//获取主播ID
					Integer liver_str_id=Integer.parseInt(fansMap.get("liver_str_id").toString());
					ids.add(liver_str_id+"");
				}
				
				//根据主播id查询用户信息
				List<Map<Object,Object>> personList=personalcenterService.queryLiverPersonByListId(ids);
				if(personList!=null && personList.size()>0){
					for(Map<Object,Object> personMap:personList){
						Map<Object,Object> rgMap= new HashMap<Object,Object>();
						String avatar=personMap.get("avatar")==null||personMap.get("avatar").equals("")?"":(fileUrl+personMap.get("avatar").toString());
						rgMap.put("id", personMap.get("anchorid"));//id
						rgMap.put("nname", personMap.get("nname"));//昵称
						rgMap.put("avatar", avatar);//头像
						rgMap.put("gender", personMap.get("sex"));//性别0:男    1:女
						rgMap.put("levels", personMap.get("rank_no"));//等级
						resultList.add(rgMap);
					}
				}else{
					resultMap.put("fansList", resultList);
					MapResponse response = new MapResponse(ResponseCode.SUCCESS,"未有粉丝关注");
					response.setResponse(resultMap);
					return response;
				}
				resultMap.put("fansList", resultList);
			}else{
				resultMap.put("fansList", resultList);
				MapResponse response = new MapResponse(ResponseCode.SUCCESS,"未有粉丝关注");
				response.setResponse(resultMap);
				return response;
			}
			
		} catch (Exception e) {
			log.error("查询粉丝列表失败");
			e.printStackTrace();
			throw new Exception("查询粉丝列表失败");
		}
		//响应
		MapResponse response = new MapResponse(ResponseCode.SUCCESS,"获取粉丝列表成功");
		response.setResponse(resultMap);
		return response;
	}
	
	
	
	/**
	 * 
	* @Title: queryLiveRecordList
	* @Description: 查看直播记录
	* @return Object
	 * @throws Exception 
	 */
	public Object queryLiveRecordList(int anchor_id,int month,int page,Integer year) throws Exception{
		resultMap=new HashMap<Object,Object>();
		resultList=new ArrayList<Map<Object,Object>>();
		List<Map<Object,Object>> recordList=null;
		List<Map<Object,Object>> recordDetailList=null;
		Map<Object,Object> param=new HashMap<Object,Object>();
		try {
			
			param.put("anchor_id", anchor_id);
			param.put("month", month);
			param.put("year", year);
			param.put("page", page);
			param.put("limit", Constant.PAGE_LIMIT);
			Map<Object,Object> recordNum=this.queryLiveRecordNum(anchor_id, month, page,year);//查询直播总数
			recordList=personaldetailDao.queryLiveRecordList(param);
			recordDetailList=this.queryLiveRecordDetailList(anchor_id, month,year, page);
			if(recordNum==null||recordNum.get("zhibo_durationnum")==null){
				resultMap.put("zhiboDurationNum", "0");
			}else{
				resultMap.put("zhiboDurationNum", recordNum.get("zhibo_durationnum").toString());
			}
			if(recordList!=null && recordList.size()>0&& recordDetailList!=null && recordDetailList.size()>0){
				for(Map<Object,Object> recordMap:recordList)
				{
					Map<Object,Object> rgMap= new HashMap<Object,Object>();
					rgMap.put("startDate", recordMap.get("start_date"));//直播开始时间
					rgMap.put("zhiboDuration", recordMap.get("zhibo_duration")+"");//直播时长
					String start_date=recordMap.get("start_date")==null?null:recordMap.get("start_date").toString();
						List<Map<Object,Object>> resultDtetailList=new ArrayList<Map<Object,Object>>();
						for(Map<Object,Object> recordDtMap:recordDetailList){
							String s_date=recordDtMap.get("s_date")==null?null:recordDtMap.get("s_date").toString();
							if(StringUtils.isNotEmpty(start_date) && StringUtils.isNotEmpty(s_date) && start_date.equals(s_date)){
								Map<Object,Object> dtMap=new HashMap<Object,Object>();
								dtMap.put("dstartDate", recordDtMap.get("start_date"));//直播开始时间
								dtMap.put("dzhiboDuration", recordDtMap.get("zhibo_duration").toString());//直播时长
								resultDtetailList.add(dtMap);
							}
						}
					rgMap.put("viewRecordDetailList", resultDtetailList);
					resultList.add(rgMap);
				}
			}else{
				resultMap.put("viewRecordList", resultList);
				MapResponse response = new MapResponse(ResponseCode.SUCCESS,"未有直播记录");
				response.setResponse(resultMap);
				return response;
			}
			resultMap.put("viewRecordList", resultList);
		} catch (Exception e) {
			log.error("查看直播记录失败");
			e.printStackTrace();
			throw new Exception("查看直播记录失败");
		}
		//响应
		MapResponse response = new MapResponse(ResponseCode.SUCCESS,"获取直播记录成功");
		response.setResponse(resultMap);
		return response;
	}
	
	/**
	 * 
	* @Title: queryLiveRecordList
	* @Description: 直播时长详情列表
	* @return List<Map<Object,Object>>
	 * @throws Exception 
	 */
	public List<Map<Object,Object>> queryLiveRecordDetailList(int anchor_id,int month,int year,int page ) throws Exception{
		List<Map<Object,Object>> recordDetailList=null;
		Map<Object,Object> param=new HashMap<Object,Object>();
		try {
			
			param.put("anchor_id", anchor_id);
			param.put("month", month);
			param.put("year", year);
//			param.put("start_date", start_date);
			param.put("page", page);
			param.put("limit", Constant.PAGE_LIMIT);
			recordDetailList=personaldetailDao.queryLiveRecordDetailList(param);
		} catch (Exception e) {
			log.error("查看直播时长详情列表失败");
			e.printStackTrace();
			throw new Exception("查看直播时长详情列表失败");
		}
		return recordDetailList;
	}
	/**
	 * 
	* @Title: queryLiveRecordNum
	* @Description: 查询直播总时长
	* @return Map<Object,Object>
	 * @throws Exception 
	 */
	public Map<Object,Object> queryLiveRecordNum(int anchor_id,int month,int page,int year) throws Exception{
		Map<Object,Object> recordNum=null;
		Map<Object,Object> param=new HashMap<Object,Object>();
		try {
			
			param.put("anchor_id", anchor_id);
			param.put("month", month);
			param.put("year", year);
			param.put("page", page);
			param.put("limit", Constant.PAGE_LIMIT);
			recordNum=personaldetailDao.queryLiveRecordNum(param);
		} catch (Exception e) {
			log.error("查看直播时长失败");
			e.printStackTrace();
			throw new Exception("查看直播时长失败");
		}
		return recordNum;
	}
	
	/**
	 * 
	* 描述: 查询弹幕数及私信数
	* @return Map<Object,Object>
	 * @throws Exception 
	 */
	public Map<Object,Object> queryBarrageMessageNum(int liver_id) throws Exception
	{
		Map<Object,Object> bmMap=null;
		try {
			bmMap=personaldetailDao.queryBarrageMessageNum(liver_id);
		} catch (Exception e) {
			log.error("查询弹幕数及私信数失败");
			e.printStackTrace();
			throw new Exception("查询弹幕数及私信数失败");
		}
		return bmMap;
	}
}
