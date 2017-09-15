package com.xmniao.xmn.core.live.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseRequest;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ObjResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.PageRequest;
import com.xmniao.xmn.core.common.request.live.MyMakeHaoFriendRequest;
import com.xmniao.xmn.core.live.dao.LiveLedgerRecordDao;
import com.xmniao.xmn.core.live.dao.MakeHaoDao;
import com.xmniao.xmn.core.live.entity.LiveFansRank;
import com.xmniao.xmn.core.live.entity.LiveLedgerRecord;
import com.xmniao.xmn.core.live.entity.LiverInfo;
import com.xmniao.xmn.core.live.response.MyMakeHaoFriendBean;
import com.xmniao.xmn.core.live.response.MyMakeHaoFriendResponse;
import com.xmniao.xmn.core.live.response.MyMakeHaoRankResponse;
import com.xmniao.xmn.core.live.response.MyMakeHaoResponse;
import com.xmniao.xmn.core.thrift.ThriftService;
import com.xmniao.xmn.core.util.ArithUtil;
import com.xmniao.xmn.core.util.DateUtil;
import com.xmniao.xmn.core.util.PropertiesUtil;
import com.xmniao.xmn.core.util.StringUtils;


/**
 * 壕赚service
* 类名称：MakeHaoService   
* 类描述：   
* 创建人：xiaoxiong   
* 创建时间：2016年12月24日 下午2:26:51
 */
@Service
public class MakeHaoService {
	
	@Autowired
	private SessionTokenService sessionTokenService;
	
	@Autowired
	private LiveUserService liveUserService;
	
	@Autowired
	private MakeHaoDao makeHaoDao;
	
	@Autowired
	private String fileUrl;
	
	@Autowired
	private ThriftService thriftService;
	
	@Autowired
	private LiveLedgerRecordDao liveLedgerRecordDao;
	
	@Autowired
	private String localDomain;
	
	//注入直播钱包service
	@Autowired
	private LiveGiftsInfoService liveGiftsInfoService;
	
	/**
	 * 注入propertiesUtil
	 */
	@Autowired
	private PropertiesUtil propertiesUtil;
	
	/**
	 * 我的壕友
	 * @author xiaoxiong
	 * @date 2016年12月24日
	 */
//	public Object myMakeHaoFriend(MyMakeHaoFriendRequest request) {
//		try {
//			/*验证用户信息*/
//			String uid = sessionTokenService.getStringForValue(request.getSessiontoken())+""; 
//			if(uid.equals("null")||uid.equals(""))
//			{
//				return new BaseResponse(ResponseCode.DATAERR,"身份令牌错误或已过期，请重新登入");
//			}
//			/*响应信息*/
//			ObjResponse objResponse = new ObjResponse(ResponseCode.SUCCESS,"成功");
//			
//			List<MyMakeHaoFriendBean> resultList = new ArrayList<>();
//			
//			MyMakeHaoFriendResponse response = new MyMakeHaoFriendResponse();
//			
//			List<String> uids = new ArrayList<>();
//			if(request.getUids()!=null){
//				Collections.addAll(uids, request.getUids().split(","));
//			}
//			
//			List<Object> tempUid = new  ArrayList<>();
//			//source等于0查询有贡献的壕友，等于1查询无贡献的好友liver表
//			if(request.getSource()==0)
//			{
//				//查询贡献排行榜 根据贡献排序
//				Map<String,Object> ledgerParams = new HashMap<>();
//				ledgerParams.put("page",request.getPage());
//				ledgerParams.put("pageSize",request.getPageSize());
//				ledgerParams.put("uid",uid);
//				
//				List<Map<String,Object>> ledgerList = makeHaoDao.getLedgerMakeHaoFriend(ledgerParams);
//				if(ledgerList!=null&&ledgerList.size()>0){
//					for(int i =0 ;i<ledgerList.size();i++){
//						uids.add(ledgerList.get(i).get("uid")+"");
//						tempUid.add(ledgerList.get(i).get("uid")+"");
//					}
//					resultList = getMakeHaoList(tempUid,ledgerList,null,uid);
//					
//					//如果source数据不够 查询没有贡献鸟币的壕友
//					if(ledgerList.size()<request.getPageSize()){
//						//请求参数
//						Map<String,Object> params = new HashMap<>();
//						params.put("limitSize",0);
//						params.put("pageSize",request.getPageSize()-ledgerList.size());
//						params.put("list", uids);
//						params.put("uid", uid);
//						//查liver表没有贡献的壕友
//						List<Map<String,Object>> templiverList = makeHaoDao.queryLiverMakeHaoFriend(params);
//						List<Object> liverList = new ArrayList<>();
//						if(templiverList!=null && templiverList.size()>0){
//							for(Map<String,Object> tempLiverMap : templiverList){
//								liverList.add(tempLiverMap.get("uid"));
//							}
//							List<MyMakeHaoFriendBean> liverFriendList = getMakeHaoList(liverList,ledgerList,templiverList,uid);
//							if(liverFriendList!=null&&liverFriendList.size()>0){
//								resultList.addAll(liverFriendList);
//							}
//							response.setLimitSize(liverFriendList.size());
//						}
//						/*判断服务器是否还有数据*/
//						if(templiverList!=null&&templiverList.size()==request.getPageSize()-ledgerList.size()){
//							response.setIsDate(1);
//						}
//						
//						response.setSource(1);
//						response.setPage(2);
//						response.setUids(getStringUids(uids));
//						
//					}else{
//						response.setLimitSize(1);
//						response.setSource(0);
//						response.setPage(request.getPage()+1);
//						response.setUids(getStringUids(uids));
//					}
//					
//				}else{
//					MyMakeHaoFriendRequest params = new MyMakeHaoFriendRequest();
//					params.setSource(1);
//					params.setLimitSize(0);
//					params.setPageSize(request.getPageSize());
//					params.setUids(request.getUids());
//					params.setSessiontoken(request.getSessiontoken());
//					return myMakeHaoFriend(params);
//				}
//			}else
//			{
//				//请求参数
// 				Map<String,Object> params = new HashMap<>();
//				params.put("limitSize",request.getLimitSize());
//				params.put("pageSize",request.getPageSize());
//				params.put("list", uids);
//				params.put("uid", uid);
//				//查liver表没有贡献的壕友
//				List<Map<String,Object>> templiverList = makeHaoDao.queryLiverMakeHaoFriend(params);
//				List<Object> liverList = new ArrayList<>();
//				if(templiverList!=null && templiverList.size()>0){
//					for(Map<String,Object> tempLiverMap : templiverList){
//						liverList.add(tempLiverMap.get("uid"));
//					}
//					List<MyMakeHaoFriendBean> liverFriendList = getMakeHaoList(liverList,null,templiverList,uid);
//					if(liverFriendList!=null){
//						resultList.addAll(liverFriendList);
//					}
//					
//					if(templiverList.size()==request.getPageSize()){
//						response.setIsDate(1);
//					}
//				}
//				response.setLimitSize(request.getLimitSize()+templiverList.size());
//				response.setSource(1);
//				response.setPage(request.getPage()+1);
//				response.setUids(getStringUids(uids));
//			}
//			response.setList(resultList);
//			objResponse.setResponse(response);
//			return objResponse;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return new BaseResponse(ResponseCode.FAILURE,"获取我的壕友失败！");
//	}
	
	/**
	 * 我的壕友
	 * @author xiaoxiong
	 * @date 2017年1月11日
	 */
	public Object myMakeHaoFriend(MyMakeHaoFriendRequest request) {
		try {
			/*验证用户信息*/
			String uid = sessionTokenService.getStringForValue(request.getSessiontoken())+""; 
			if(uid.equals("null")||uid.equals(""))
			{
				return new BaseResponse(ResponseCode.DATAERR,"身份令牌错误或已过期，请重新登入");
			}
			/*响应信息*/
			ObjResponse objResponse = new ObjResponse(ResponseCode.SUCCESS,"成功");
			
			List<MyMakeHaoFriendBean> resultList = new ArrayList<>();
			
			MyMakeHaoFriendResponse response = new MyMakeHaoFriendResponse();
			
				//请求参数
 				Map<String,Object> params = new HashMap<>();
				params.put("pageNo",request.getPage());
				params.put("pageSize",request.getPageSize());
				params.put("uid",uid);
				
				List<Map<String,Object>> templiverList = makeHaoDao.queryLiverMakeHaoFriend(params);
				
				if(templiverList!=null && templiverList.size()>0){
					
					resultList = getMakeHaoListTwo(templiverList,uid);
					
					if(templiverList.size()==request.getPageSize()){
						response.setIsDate(1);
					}
				}
				response.setLimitSize(request.getLimitSize()+templiverList.size());
				response.setSource(0);
				response.setUids("");
				response.setPage(request.getPage()+1);
			
			response.setList(resultList);
			objResponse.setResponse(response);
			return objResponse;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new BaseResponse(ResponseCode.FAILURE,"获取我的壕友失败！");
	}
	
	public List<MyMakeHaoFriendBean> getMakeHaoListTwo(List<Map<String,Object>> list,String uid){
	
		/* 返回结果 */
		List<MyMakeHaoFriendBean> result = new ArrayList<>();
		try {
			if(list!=null&&list.size()>0){
				List<Object> liverList = new ArrayList<>();
				for(Map<String,Object> tempLiverMap : list){
					liverList.add(tempLiverMap.get("uid"));
				}
				
				//查询壕友信息，头像，姓名，性别
				List<Map<Object, Object>> tempList = liveUserService.queryLiverInfoByUidList(liverList);
				if(tempList!=null&&tempList.size()>0){
					//查询所有粉丝等级
					List<LiveFansRank> liveFansRankList= queryLiveFansRankList();
					//查询最大充值金额
					List<Map<String,Object>> maxPamentList = liveUserService.queryMaxPamentList(liverList);
					
					Map<String,Object> ledgerParams = new HashMap<>();
					ledgerParams.put("list",liverList);
					ledgerParams.put("uid",uid);
					//查询分账金额
					List<Map<String,Object>> ledgerList = liveLedgerRecordDao.queryLiveledgerList(ledgerParams);
					//查询自己的粉丝等级和
					LiverInfo liverInfo = liveUserService.queryLiverByUid(uid);
					
					//查询是否有贡献
					for (Object tempUid : liverList) {
						try {
							for (Map<Object, Object> map : tempList) {
								if (tempUid.toString().equals(map.get("uid").toString())) {
									MyMakeHaoFriendBean responseObj = new MyMakeHaoFriendBean();
									//用户昵称处理
									String nname = "";
									if (map.get("nname") != null && !map.get("nname").equals("")) {
										nname = StringUtils.getUserNameStr(map.get("nname") + "");
									} else {
										nname = map.get("uname").toString().substring(0, 3)
												+ "****"
												+ map.get("uname").toString().substring(7);
									}
									//获取头像
									responseObj.setAvatar(map.get("avatar") == null ? "": fileUrl + map.get("avatar"));
									responseObj.setNname(nname);
									//性别
									responseObj.setSex(map.get("sex") == null ? 0: Integer.parseInt(map.get("sex") + ""));
									//粉丝等级
									int level = map.get("fans_rank_no")==null?1:Integer.valueOf(map.get("fans_rank_no")+"");
									String levelName = map.get("fans_rank_name")==null?"普通会员":map.get("fans_rank_name")+"";
									responseObj.setLevel(level);
									responseObj.setLevelName(levelName);
									//粉丝等级图片
									LiveFansRank liveFansRank = getLiveFansRank(level, liveFansRankList);
									//老版本粉丝等级图片
									responseObj.setLevelImg(fileUrl+liveFansRank.getPicUrl());
									
									//贡献鸟币
									responseObj.setBirdCoin("0.00");
									responseObj.setGaveBirdCoin("0.00");
									responseObj.setUid(Integer.valueOf(map.get("uid") + ""));
									responseObj.setLabel(1);
									String [] uidChin = (map.get("uid_relation_chain") + "").split(",");
									if(uidChin.length>=2){
										if (uidChin[uidChin.length - 2].equals(getUids(uid))) {
											responseObj.setLabel(0);
										}
									}
									if(ledgerList!=null&&ledgerList.size()>0){
										for(Map<String,Object> ledgerMap:ledgerList){
											if(map.get("uid").toString().equals(ledgerMap.get("ledger_uid").toString())){
												responseObj.setBirdCoin(ledgerMap.get("amount")+"");
											}
										}
									}
									//如果实际贡献金额为0（查询预计贡献鸟币）
									if(responseObj.getBirdCoin().equals("0.00")){
										double gaveBirdCoin = 0.00;
										int liveNo = liverInfo.getFans_rank_no()==null?1:liverInfo.getFans_rank_no();
										LiveFansRank myliveFansRank = getLiveFansRank(liveNo,liveFansRankList);
										
										if(level>3)
										{
											if(maxPamentList!=null&&maxPamentList.size()>0){
												for(Map<String, Object>maxMap : maxPamentList ){
													if(map.get("uid").toString().equals(maxMap.get("uid").toString())){
														//如果金粉以下上级推荐比例*5000大于金粉（下级最大充值金额*推荐比例）
													
															if(responseObj.getLabel()==0){//直接壕友
																gaveBirdCoin = ArithUtil.mul(myliveFansRank.getReferrerRatio()/100.00,Double.valueOf(maxMap.get("payment")+""));
															}else{
																gaveBirdCoin = ArithUtil.mul(myliveFansRank.getParentReferrerRatio()/100.00, Double.valueOf(maxMap.get("payment")+""));
															}
														break;
													}
												}
											}
										}else
										{
											if(responseObj.getLabel()==0){//直接壕友
												gaveBirdCoin = ArithUtil.mul(myliveFansRank.getReferrerRatio()/100.00, 5000);
											}else{
												gaveBirdCoin = ArithUtil.mul(myliveFansRank.getParentReferrerRatio()/100.00, 5000);
											}
										}
										responseObj.setGaveBirdCoin(String.format("%.2f", gaveBirdCoin));
									}
									result.add(responseObj);
									break;
								}
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	/**
	 * 获取壕友用户信息和 贡献鸟币
	 * @author xiaoxiong
	 * @date 2016年12月24日
	 */
	private List<MyMakeHaoFriendBean> getMakeHaoList(List<Object> list,List<Map<String,Object>> legderList,List<Map<String,Object>> templiverList,String uid) {

		/* 返回结果 */
		List<MyMakeHaoFriendBean> result = new ArrayList<>();
		try {
			/* 根据贡献奖励排序分页 */
			List<Map<Object, Object>> tempList = liveUserService.queryLiverInfoByUidList(list);
			
			//查询最大充值金额
			List<Map<String,Object>> maxPamentList = null;
			if(templiverList!=null&&templiverList.size()>0){
				 maxPamentList = liveUserService.queryMaxPamentList(list);
			}
			
			//查询所有粉丝等级
			List<LiveFansRank> liveFansRankList= queryLiveFansRankList();
			
			if (tempList != null && tempList.size() > 0) {
				for (Object tempUid : list) {
					try {
						for (Map<Object, Object> map : tempList) {
							if (tempUid.toString().equals(map.get("uid").toString())) {
								MyMakeHaoFriendBean responseObj = new MyMakeHaoFriendBean();
								//用户昵称处理
								String nname = "";
								if (map.get("nname") != null&& !map.get("nname").equals("")) {
									nname = map.get("nname") + "";
								} else {
									nname = map.get("uname").toString().substring(0, 3)
											+ "****"
											+ map.get("uname").toString().substring(7);
								}
								//获取头像
								responseObj.setAvatar(map.get("avatar") == null ? "": fileUrl + map.get("avatar"));
								responseObj.setNname(nname);
								//性别
								responseObj.setSex(map.get("sex") == null ? 0: Integer.parseInt(map.get("sex") + ""));
								//粉丝等级
								int level = map.get("fans_rank_no")==null?1:Integer.valueOf(map.get("fans_rank_no")+"");
								String levelName = map.get("fans_rank_name")==null?"普通会员":map.get("fans_rank_name")+"";
								responseObj.setLevel(level);
								responseObj.setLevelName(levelName);
								//粉丝等级图片
								LiveFansRank liveFansRank = getLiveFansRank(level, liveFansRankList);
								responseObj.setLevelNewImg(fileUrl+liveFansRank.getPicUrl());
								//老版本粉丝等级图片
								responseObj.setLevelImg(fileUrl+liveFansRank.getPicUrl());
								
								//鸟币
								responseObj.setBirdCoin("0.00");
								responseObj.setGaveBirdCoin("0.00");
								responseObj.setUid(Integer.valueOf(map.get("uid") + ""));

								// 找到用户贡献的金额和关系
								responseObj.setLabel(1);
								if (legderList != null && legderList.size() > 0) {
									for (Map<String, Object> legderMap : legderList) {
										if (map.get("uid").equals(legderMap.get("uid"))) {
											responseObj.setBirdCoin(legderMap.get("amount") + "");
											responseObj.setLabel(Integer.valueOf(legderMap.get("uid_role")+ "") == 2 ? 0 : 1);
											break;
										}
									}
								}
								// 找到用户的关系（直接壕友还是间接壕友）
								if (templiverList != null&& templiverList.size() > 0) {
									for (Map<String, Object> liverMap : templiverList) {
										if (map.get("uid").equals(liverMap.get("uid"))) {
											String[] uidChin = (liverMap
													.get("uid_relation_chain") + "")
													.split(",");
											// 如果最后一个uid相等说明是直接好友
											if(uidChin.length>=2){
												if (uidChin[uidChin.length - 2].equals(getUids(uid))) {
													responseObj.setLabel(0);
												}
											}
											break;
										}
									}
								}
								
								if(responseObj.getBirdCoin().equals("0.00")){
									double gaveBirdCoin = 0.00;
									//查询自己的粉丝等级和
									LiverInfo liverInfo = liveUserService.queryLiverByUid(uid);
									int liveNo = liverInfo.getFans_rank_no()==null?1:liverInfo.getFans_rank_no();
									LiveFansRank myliveFansRank = getLiveFansRank(liveNo,liveFansRankList);
									
									if(level>3)
									{
										if(maxPamentList!=null&&maxPamentList.size()>0){
											for(Map<String, Object>maxMap : maxPamentList ){
												if(map.get("uid").toString().equals(maxMap.get("uid").toString())){
													//如果金粉以下上级推荐比例*5000大于金粉（下级最大充值金额*推荐比例）
												
														if(responseObj.getLabel()==0){//直接壕友
															gaveBirdCoin = ArithUtil.mul(myliveFansRank.getReferrerRatio()/100.00,Double.valueOf(maxMap.get("payment")+""));
														}else{
															gaveBirdCoin = ArithUtil.mul(myliveFansRank.getParentReferrerRatio()/100.00, Double.valueOf(maxMap.get("payment")+""));
														}
													break;
												}
											}
										}
									}else
									{
										if(responseObj.getLabel()==0){//直接壕友
											gaveBirdCoin = ArithUtil.mul(myliveFansRank.getReferrerRatio()/100.00, 5000);
										}else{
											gaveBirdCoin = ArithUtil.mul(myliveFansRank.getParentReferrerRatio()/100.00, 5000);
											System.out.println(myliveFansRank.getParentReferrerRatio()/100.00);
										}
									}
									responseObj.setGaveBirdCoin(String.format("%.2f", gaveBirdCoin));
								}
								result.add(responseObj);
								break;
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 壕赚排名
	 * @author xiaoxiong
	 * @date 2016年12月26日
	 */
	public Object makeHaoRank(PageRequest request) {
		try {
			List<Map<String,Object>> temp = makeHaoDao.makeHaoRank();
			
			List<MyMakeHaoRankResponse> list = myMakeHaoRankResponse(temp);
			
			MapResponse objResponse = new MapResponse(ResponseCode.SUCCESS, "成功");
			Map<Object,Object> resultMap = new HashMap<>();
			resultMap.put("list", list);
			objResponse.setResponse(resultMap);
			return objResponse;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new BaseResponse(ResponseCode.FAILURE,"获取壕赚排名失败！");
	}

	private List<MyMakeHaoRankResponse> myMakeHaoRankResponse(
			List<Map<String, Object>> temp) {
		List<MyMakeHaoRankResponse> result = new ArrayList<>();
		try {
			if(temp!=null && temp.size()>0){
				List<Object> paramsList = new ArrayList<>();
				for(Map<String,Object> map: temp){
					paramsList.add(map.get("uid"));
				}
				List<Map<Object,Object>> tempList = liveUserService.queryLiverInfoByUidList(paramsList);
				if(tempList!=null&&tempList.size()>0){
					int j = 0;
					for(Map<String,Object> map: temp){
						for(int i=0;i<tempList.size();i++){
							Map<Object,Object>resultMap = tempList.get(i);
							try {
								if(resultMap.get("uid").equals(map.get("uid"))){
									
									MyMakeHaoRankResponse mhr = new MyMakeHaoRankResponse();
									mhr.setRankNo(j+1);
									mhr.setAvatar(fileUrl+resultMap.get("avatar"));
									mhr.setUid(Integer.valueOf(resultMap.get("uid")+""));
									mhr.setBirdCoin(map.get("amount")+"");
									result.add(mhr);
									j++;
									break;
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				}
				if(tempList == null){
					tempList = new ArrayList<>();
				}
				
				if(tempList.size()<3){
					for(int i =0;i<3-tempList.size();i++){
						MyMakeHaoRankResponse mhr = new MyMakeHaoRankResponse();
						mhr.setRankNo(tempList.size()+i+1);
						mhr.setAvatar(localDomain+"/images/makeHao_"+i+".jpg");
						if(i==0){
							mhr.setBirdCoin("11990.86");
						}else{
							if(i==1){
								mhr.setBirdCoin("11809.05");
							}else{
								mhr.setBirdCoin("11665.69");
							}
						}
						mhr.setUid(i);
						result.add(mhr);
					}
				}
				
			}else{
				//如果真实数据没有3条高于12000的则显示假数据
				for(int i =0;i<3;i++){
					MyMakeHaoRankResponse mhr = new MyMakeHaoRankResponse();
					mhr.setRankNo(i+1);
					mhr.setAvatar(localDomain+"/images/makeHao_"+i+".jpg");
					if(i==0){
						mhr.setBirdCoin("11990.86");
					}else{
						if(i==1){
							mhr.setBirdCoin("11809.05");
						}else{
							mhr.setBirdCoin("11665.69");
						}
					}
					
					mhr.setUid(i);
					result.add(mhr);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 我的壕赚数据查询
	 * @author xiaoxiong
	 * @date 2016年12月26日
	 */
	public Object myMakeHao(BaseRequest request) {
		try {
			/*验证用户信息*/
			String uid = sessionTokenService.getStringForValue(request.getSessiontoken())+"";
			if(uid.equals("")||uid.equals("null")){
				return new BaseResponse(ResponseCode.DATAERR,"身份令牌错误或已过期，请重新登入！");
			}
			ObjResponse objResponse = new ObjResponse(ResponseCode.SUCCESS,"成功");
			MyMakeHaoResponse response = new MyMakeHaoResponse();
			
			/*调用支付接口回去鸟币收入统计*/
			Map<String,String> result = thriftService.countBirdCoin(Integer.parseInt(uid));
			if(result!=null && !result.isEmpty())
			{
				BigDecimal birdCoin = new BigDecimal(result.get("birdCoin")==null?"0":result.get("birdCoin").toString());
				BigDecimal sellerCoin = new BigDecimal(result.get("sellerCoin")==null?"0":result.get("sellerCoin").toString());
				response.setSumBirdCoin(result.get("total"));//累计奖励
				response.setCanUseBirdCoin(birdCoin.add(sellerCoin).toString());// 可用鸟币余额  专享卡余额+鸟粉卡余额
				objResponse.setResponse(response);
			}else
			{
				response.setSumBirdCoin("0");//累计奖励
				response.setCanUseBirdCoin("0");//可使用鸟币
			}
			/*查询充值金额*/
//			double payMent = getPayAmount(Integer.parseInt(uid));
//			if(payMent>=100){
//				response.setIsInvitFriend(1);//是可邀请好友
//			}else{
//				response.setIsInvitFriend(0);
//			}
			//是否可邀请壕友（金粉才可以邀请）
			response.setIsInvitFriend(1);//是可邀请好友 （产品说不隐藏了）
			//查询直播用户信息   
			LiverInfo liverInfo = liveUserService.queryLiverByUid(uid);
			if(liverInfo!=null)
			{	//用户等级
				int level = liverInfo.getFans_rank_no()==null?1:liverInfo.getFans_rank_no();
				String levelName = liverInfo.getFans_rank_name()==null?"普通会员":liverInfo.getFans_rank_name();
				response.setLevel(level);
				response.setLevelName(levelName);
				List<LiveFansRank> fansRankList = queryLiveFansRankList();
				LiveFansRank liveFansRand = getLiveFansRank(level, fansRankList);
				if(liveFansRand != null){
					response.setLevelImg(fileUrl+liveFansRand.getPicUrl());
				}else{
					response.setLevelImg("");
				}
				
			}else{
				response.setLevel(1);
				response.setLevelName("普通会员");
				response.setLevelImg("");
			}
			//查询预计获得鸟币
			response.setGaveBirdCoin(getGaveAmount(uid));
			//查询是否有天降壕礼
			Map<Object, Object> recordMap = new HashMap<Object, Object>();
			String currDateHour = DateUtil.format(new Date(), DateUtil.daySimpleFormater);
			recordMap.put("currDate", currDateHour);
			recordMap.put("uid", uid);
			
			LiveLedgerRecord liveLedgerRecord = liveLedgerRecordDao.queryLiveLedgerRecordByUid(recordMap);
			if(liveLedgerRecord!=null){
				response.setIsHaoLi(1);
				response.setLedgerId(Integer.parseInt(liveLedgerRecord.getId()+""));

				
			}else{
				response.setIsHaoLi(0);
			}
			//查询我的好友数（下级，下下级）
			int friendCount = getMakeHaoFriendCount(uid);
			response.setHaoCount(friendCount);
			
			//查询用户的直播钱包，获取用户的充值卡总余额已公共部分的鸟币总额
			Map<String, Object> walletMap = liveGiftsInfoService.getLiveWalletBlance(uid);
			
			if(walletMap != null && walletMap.size() > 0){
				response.setSellerCoin(
					org.apache.commons.lang.StringUtils.isEmpty(ObjectUtils.toString(walletMap.get("sellerCoin"))
						)?"0.00":ObjectUtils.toString(walletMap.get("sellerCoin"))
					);//商铺专享鸟币
				response.setZbalance(
					org.apache.commons.lang.StringUtils.isEmpty(ObjectUtils.toString(walletMap.get("zbalance"))
						)?"0.00":ObjectUtils.toString(walletMap.get("zbalance")));//鸟粉卡鸟币
			}
			
			objResponse.setResponse(response);
			return objResponse;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new BaseResponse(ResponseCode.FAILURE,"数据获取失败，请稍候重试！");
	}
	
	/**
	 * 查询我的好友数
	 * @author xiaoxiong
	 * @date 2016年12月26日
	 */
	public int getMakeHaoFriendCount(String uid) {
		
		int count = 0;
		try {
			count = makeHaoDao.getMakeHaoFriendCount(Integer.parseInt(uid));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	/**
	 * 获取充值金额
	 * @author xiaoxiong
	 * @date 2016年12月26日
	 */
	public double getPayAmount(int uid){
		Double amount = 0D;
		try {
			/*查询充值记录获取充值金额*/
			amount = makeHaoDao.getPayAmount(uid);
			
			if(amount==null){
				amount = 0D;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return amount;
	}
	
	/**
	 * 获取打赏金额
	 * @author xiaoxiong
	 * @date 2016年12月26日
	 */
	public double getRewardAmount(int anchorId){
		Double amount = 0D;
		try {
			/*查询打赏记录获取打赏总额*/
			amount = makeHaoDao.getRewardAmount(anchorId);
			
			if(amount==null){
				amount = 0D;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return amount;
	}
	
	public String getStringUids(List<String> list){
		String uids ="";
		try {
			if(list!=null && list.size()>0){
				for(int i =0;i<list.size();i++){
					if(i==0){
						uids+=list.get(i);
					}else{
						uids+=","+list.get(i);
					}
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return uids;
	}
	public String getUids(String uid) {
		try {
			String zero ="";
			for(int i=0;i<11-uid.length();i++){
				zero+="0";
			}
			return zero+uid;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String getGaveAmount(String uid){
		double gaveAmount = 0;
		try {
			Map<String,Object> map =  makeHaoDao.getGaveAmount(Integer.parseInt(uid));
			if(map!=null){
				String privilege_ledger = map.get("privilege_ledger")==null?"0":map.get("privilege_ledger")+"";
				String current_privilege_ledger = map.get("current_privilege_ledger")==null?"0":map.get("current_privilege_ledger")+"";
				gaveAmount = ArithUtil.sub(Double.parseDouble(privilege_ledger), Double.parseDouble(current_privilege_ledger));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return String.format("%.2f", gaveAmount);
	}
	
	public LiveFansRank getLiveFansRank(int levelNo,List<LiveFansRank> list){
		try {
			if(list!=null&&list.size()>0){
				for(LiveFansRank liveFansRank : list){
					if(liveFansRank.getRankNo().compareTo(levelNo) == 0){
						
						return liveFansRank;
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<LiveFansRank> queryLiveFansRankList(){
		
		return liveUserService.queryLiveFansRankList();
	}


}
