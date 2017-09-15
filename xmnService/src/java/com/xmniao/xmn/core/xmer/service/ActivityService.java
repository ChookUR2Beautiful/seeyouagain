package com.xmniao.xmn.core.xmer.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.IDRequest;
import com.xmniao.xmn.core.common.request.MoreActivityRequest;
import com.xmniao.xmn.core.util.DateUtil;
import com.xmniao.xmn.core.xmer.dao.ActivityDao;
import com.xmniao.xmn.core.xmer.dao.XmerDao;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：ActivityService   
* 类描述：   
* 创建人：xiaoxiong   
* 创建时间：2016年6月25日 上午11:46:24   
* @version    
*
 */

@Service
public class ActivityService {
	
	@Autowired
	private ActivityDao activityDao;
	
	@Autowired
	private SessionTokenService sessionTokenService;
	
	@Autowired
	private XmerDao xmerDao;
	
	
	public Object activityInfo(IDRequest idRquest) {
		try {
			MapResponse mapResponse=new MapResponse(ResponseCode.SUCCESS, "成功");
			String uid=	 sessionTokenService.getStringForValue(idRquest.getSessiontoken())+"";
			if(uid!=null&&uid.length()>0){
				//查询寻蜜客
				Map<Object,Object> param=xmerDao.selecXmerAndUrstByUid(Integer.valueOf(uid));
				if(param!=null){
					param.put("activity_id", idRquest.getId());
					int count =activityDao.queryActivityEntryCount(param);
					Map<Object,Object> activeMap = new HashMap<Object,Object>();
					activeMap.put("id", idRquest.getId());
					activeMap.put("sdate",  DateUtil.format(DateUtil.now(), "yyyy-MM-dd HH:mm:ss"));
					Map<Object,Object> map=activityDao.activityInfo(activeMap);
					if(map!=null){
						map.put("ustatus", count);
						mapResponse.setResponse(map);
						return mapResponse;
					}else{
						return new BaseResponse(ResponseCode.DATA_NULL, "没有找到活动详情");
					}
					
				}else{
					return new BaseResponse(ResponseCode.DATA_NULL, "没有找到寻蜜客");
				}
			}else{
				return new BaseResponse(ResponseCode.FAILURE,"令牌错误");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE,"错误");
			
		}
		
	}
	/**
	 * 
	* @Title: enrollActivity
	* @Description: 活动报名
	* @return Object    返回类型
	* @throws
	 */
	public Object enrollActivity(IDRequest idRquest) {
		try {
			String uid=	 sessionTokenService.getStringForValue(idRquest.getSessiontoken())+"";
			if(uid!=null&&uid.length()>0){
				//查询寻蜜客
				Map<Object,Object> param=xmerDao.selecXmerAndUrstByUid(Integer.valueOf(uid));
				if(param!=null){
					param.put("activity_id", idRquest.getId());
					param.put("date", DateUtil.now());
					int count =activityDao.queryActivityEntryCount(param);
					if(count==0){
						activityDao.addAgencyActivityEntry(param);//添加报名信息
						//修改已报名人数
						activityDao.addActivityCount(idRquest.getId());
						return  new BaseResponse(ResponseCode.SUCCESS, "成功");
					}else{
						return new BaseResponse(ResponseCode.XMER_HAS_enrolled,"亲，您已报名哦");
					}
				}else{
					return new BaseResponse(ResponseCode.DATA_NULL, "没有找到寻蜜客");
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new BaseResponse(ResponseCode.FAILURE, "错误");
		
	}
	public Object moreActivity(MoreActivityRequest request) {
		try {
			Map<Object,Object> activiMap = new HashMap<Object,Object>();
			activiMap.put("id", request.getId());
			activiMap.put("type", request.getType());
			activiMap.put("date", DateUtil.now());
			List<Map<Object,Object>> list=activityDao.moreActivity(activiMap);
			MapResponse mapResponse=new MapResponse(ResponseCode.SUCCESS, "成功");
			Map<Object,Object> map=new HashMap<>();
			map.put("activityList", list);
			mapResponse.setResponse(map);
			return mapResponse;
			
		} catch (Exception e) {
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE, "错误");
		}
		
	}

}
