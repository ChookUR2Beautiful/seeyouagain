package com.xmniao.xmn.core.xmer.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.Constant;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.IDRequest;
import com.xmniao.xmn.core.common.request.NearbyStageRequest;
import com.xmniao.xmn.core.common.request.PageRequest;
import com.xmniao.xmn.core.util.DateUtil;
import com.xmniao.xmn.core.xmer.dao.StageDao;


/**
 * 
* 项目名称：xmnService   
* 类名称：StageService   
* 类描述：驿站相关接口Service   
* 创建人：liuzhihao   
* 创建时间：2016年6月23日 上午11:05:16   
* @version    
*
 */
@Service
public class StageService {

	//日志 
	private final Logger log = Logger.getLogger(StageService.class);
	//注入dao
	@Autowired
	private StageDao stageDao;
	
	//注入缓存
	@Autowired
	private SessionTokenService sessionTokenService;
	
	private MapResponse response= null;
	
	//注入fastDFS服务器地址
	@Autowired
	private String fileUrl;
	/**
	 * 
	* @Title: queryStageList
	* @Description: 驿站列表接口实现类
	* @return Object    返回类型
	* @author liuzhihao
	* @throws
	 */
	public Object queryStageList(PageRequest pageRequest){
		if(sessionTokenService.getStringForValue(pageRequest.getSessiontoken()) == null){
			log.info("无效token，请重新登录!");
			return new BaseResponse(ResponseCode.TOKENERR,"无效token，请重新登录");
		}
		String uid = sessionTokenService.getStringForValue(pageRequest.getSessiontoken()).toString();
		if(StringUtils.isEmpty(uid)){
			log.info("token已过期，请重新登录");
			return new BaseResponse(ResponseCode.TOKENERR,"token已过期，请重新登录");
		}
		Map<Object,Object> result = new HashMap<Object,Object>();//返回结果集
		List<String> areaList = null;//查询寻蜜签约成功的所有商铺的区域id
		List<Map<Object,Object>> stageList = null;//查询驿站详情
		List<Map<Object,Object>> activityList = null;//查询驿站活动详情
		try{
			//查询驿站列表
			Map<Object,Object> paraMap = new HashMap<Object,Object>();
			paraMap.put("uid", Integer.valueOf(uid));
			paraMap.put("page",pageRequest.getPage()-1);//页数
			paraMap.put("limit", pageRequest.getPage()*Constant.PAGE_LIMIT);//每页显示多少条数据
			areaList = stageDao.querySignSellerAreaId(paraMap);
			if(areaList.size() <1 || areaList == null){
				areaList = new ArrayList<String>();
				result.put("stages", areaList);
				response = new MapResponse(ResponseCode.SUCCESS,"寻蜜客还没有签约成功的商铺");
				return response;
			}
		}catch(Exception e){
			e.printStackTrace();
			log.info("查询签约成功商铺区域id异常");
			return new BaseResponse(ResponseCode.FAILURE,"查询签约成功商铺区域id失败");
		}
		//查询驿站详情
			try{
				stageList = stageDao.queryStageInfoList(areaList);
				if(stageList.size() <1 || stageList == null){
					stageList = new ArrayList<Map<Object,Object>>();
					result.put("stages", stageList);
					response = new MapResponse(ResponseCode.SUCCESS,"没有相关驿站，未知区域，请开荒");
					response.setResponse(result);
					return response;
				}
			}catch(Exception e){
				e.printStackTrace();
				log.info("查询驿站详情异常");
				return new BaseResponse(ResponseCode.FAILURE,"查询驿站详情失败");
			}
			//查询驿站活动详情
			try{
				activityList = stageDao.queryActivtyInfo(stageList);
				if(activityList.size() <1 || activityList == null){
					activityList = new ArrayList<Map<Object,Object>>();
					for(Map<Object,Object> map : stageList){
						map.put("activityname", "");
						map.put("sdate", "");
						map.put("edate", "");
						activityList.add(map);
					}
					result.put("stages", activityList);//当驿站活动没有时
					
				}else{
					List<Map<Object,Object>> resultList = new ArrayList<Map<Object,Object>>();
					for(Map<Object,Object> stageMap : stageList){
						for(Map<Object,Object> activityMap : activityList){
							if(stageMap.get("stageid").toString().equals(activityMap.get("jointid").toString())){
								stageMap.put("activityname", activityMap.get("title"));//活动名称
								stageMap.put("sdate", activityMap.get("startdate"));//活动开始时间
								stageMap.put("edate", activityMap.get("enddate"));//活动结束时间
								stageMap.put("stagepic", fileUrl+stageMap.get("stagepic"));
								resultList.add(stageMap);
							}
						} 
					}
					result.put("stages", resultList);
				}
			}catch(Exception e){
				e.printStackTrace();
				log.info("查询驿站活动详情异常");
			}
			response = new MapResponse(ResponseCode.SUCCESS,"成功");
			response.setResponse(result);
			return response;
	}
	
	/**
	 * 
	* @Title: queryStageInfo
	* @Description:驿站主页接口实现类
	* @return Object    返回类型
	* @author liuzhihao
	* @throws
	 */
	public Object queryStageInfo(IDRequest idRequest){
		if(sessionTokenService.getStringForValue(idRequest.getSessiontoken()) == null){
			log.info("无效token，请重新登录!");
			return new BaseResponse(ResponseCode.TOKENERR,"无效token，请重新登录");
		}
		String uid = sessionTokenService.getStringForValue(idRequest.getSessiontoken()).toString();
		if(StringUtils.isEmpty(uid)){
			log.info("token已过期，请重新登录");
			return new BaseResponse(ResponseCode.TOKENERR,"token已过期，请重新登录");
		}
		try{
			//查看驿站详情
			Map<Object,Object> stageMap = stageDao.queryStageInfo(String.valueOf(idRequest.getId()));
			stageMap.put("stagepic", fileUrl+stageMap.get("stagepic"));
			stageMap.put("jointid", idRequest.getId());
			//查询经销商头衔
			String achievement = stageDao.queryStageAchievement(idRequest.getId());
			if(StringUtils.isEmpty(achievement)){
				stageMap.put("achievement", "");//经销商头衔
			}else{
				stageMap.put("achievement", achievement);//经销商头衔
			}
//			try{
//				//查询经销商钱包id
//			Integer accountid = stageDao.queryAccountIdByJointid(idRequest.getId());//通过经销商的id查询经销商的钱包id
//			if(accountid != null && accountid > 0){
//				//通过经销商钱包id查询经销商钱包记录统计经销商总收入，总流水，以及月收入
//				Map<Object,Object> walletMap = new HashMap<Object,Object>();//查询总收入，总流水参数map
//				walletMap.put("accountid", accountid);
//				Map<Object,Object> walletRecodeMap = stageDao.queryStageWalletRecodeInfo(walletMap);
//				if(walletRecodeMap == null || walletRecodeMap.size() <1){
//					walletRecodeMap = new HashMap<Object,Object>();
//					walletRecodeMap.put("totalmoney", 0.0);
//					walletRecodeMap.put("totalflow", 0.0);
//				}
//				//通过钱包id查询经销商月流水
//				Map<Object,Object> mouthMap = new HashMap<Object,Object>();//查询月流水参数map
//				mouthMap.put("accountid", accountid);
//				mouthMap.put("sdate", DateUtil.format(DateUtil.getMonthFirstDay(DateUtil.now()), "yyyy-MM-dd"));
//				mouthMap.put("edate", DateUtil.format(DateUtil.getMonthLastDay(DateUtil.now()), "yyyy-MM-dd"));
//				Map<Object,Object> mouthRecodeMap = stageDao.queryStageWalletRecodeInfo(mouthMap);
//				if(mouthRecodeMap == null || mouthRecodeMap.size() <1){
//					mouthRecodeMap = new HashMap<Object,Object>();
//					mouthRecodeMap.put("totalflow", 0.0);
//				}
//				stageMap.put("totalmoney", walletRecodeMap.get("totalmoney"));
//				stageMap.put("totalflow", walletRecodeMap.get("totalflow"));//总流水
//				stageMap.put("mouthflow", mouthRecodeMap.get("totalflow"));//月流水
//			}else{
//				stageMap.put("totalmoney",0.0);
//				stageMap.put("totalflow", 0.0);
//				stageMap.put("mouthflow", 0.0);
//			}
//			}catch(Exception e){
//				e.printStackTrace();
//				log.info("查询经销商的钱包id异常");
//				return new BaseResponse(ResponseCode.FAILURE,"查询经销商钱包id异常，检查经销商id是否绑定钱包");
//			}
			//经销商活动查询
			Map<Object,Object> activtyMap = new HashMap<Object,Object>();
			activtyMap.put("stageid", idRequest.getId());
			List<Map<Object,Object>> activtyParaList = new ArrayList<Map<Object,Object>>();
			activtyParaList.add(activtyMap);
			List<Map<Object,Object>> activtyList = stageDao.queryActivtyInfo(activtyParaList);
			if(activtyList.size() <1 || activtyList == null){
				stageMap.put("activityid", 0);//活动id
				stageMap.put("activityname", "");//活动名称
				stageMap.put("joinnums", 0);//活动人数
				stageMap.put("sdate", "");//开始时间
				stageMap.put("edate", "");//结束时间
				stageMap.put("activityposter", "");
			}else{
				Map<Object,Object> stageActivtyMap = activtyList.get(0);
				stageMap.put("activityid", stageActivtyMap.get("id"));
				stageMap.put("activityname", stageActivtyMap.get("title"));
				stageMap.put("joinnums", stageActivtyMap.get("joinnums"));
				String[] startdate = stageActivtyMap.get("startdate").toString().split(" ");
				String[] enddate = stageActivtyMap.get("enddate").toString().split(" ");
				stageMap.put("sdate", startdate[0]);
				stageMap.put("edate", enddate[0]);
				stageMap.put("activityposter", stageActivtyMap.get("activityposter"));//活动海报
			}
			response = new MapResponse(ResponseCode.SUCCESS,"成功");
			response.setResponse(stageMap);
			return response;
		}catch(Exception e){
			e.printStackTrace();
			log.info("查询寻蜜客信息异常");
			return new BaseResponse(ResponseCode.FAILURE,"查询寻蜜客异常，请重新登录");
		}
	}

	/**
	 * 
	* @Title: nearbyStageList
	* @Description: 查询附近驿站
	* @return Object    返回类型
	* @throws
	 */
	public Object nearbyStageList(NearbyStageRequest request) {
		try {
			MapResponse mapResponse=new MapResponse(ResponseCode.SUCCESS, "成功");
			Map<Object,Object> map=new HashMap<>();
			List<Map<Object,Object>> list=stageDao.nearbyStageList(request);
			map.put("stageList", list);
			mapResponse.setResponse(map);
			return mapResponse;
		} catch (Exception e) {
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE, "错误");
		}
		
	}
}
