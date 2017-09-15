package com.xmniao.xmn.core.push.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.util.Hash;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.live.PushUrsRequest;
import com.xmniao.xmn.core.live.dao.LiveUserDao;
import com.xmniao.xmn.core.push.PushSingleDevice;


/**
 * 
*      
* 类名称：PushService   
* 类描述：   推送服务类
* 创建人：xiaoxiong   
* 创建时间：2016年8月29日 下午3:51:16   
* 修改人：xiaoxiong   
* 修改时间：2016年8月29日 下午3:51:16   
* 修改备注：   
* @version    
*
 */
@Service
public class PushService {
	
	
	@Autowired
	private SessionTokenService sessionService;
	
	@Autowired
	private LiveUserDao liveUserDao;
	
	/**
	 * 
	 * @Description: 关注主播开播提醒
	 * @author xiaoxiong
	 * @date 2016年8月29日
	 * @version
	 */
	public Object pushUrs(PushUrsRequest request) {
		try {
//			String uid=sessionService.getStringForValue(request.getSessiontoken())+"";
//			if(uid.equals("")||uid.equals("null")){
//				return new BaseResponse(ResponseCode.FAILURE, "token有误或已过期");
//			}
			Map<Object,Object> liveMap=liveUserDao.queryLiverInfoByUid(Integer.valueOf(request.getUid()));
			if(liveMap==null||liveMap.isEmpty()){
				return new BaseResponse(ResponseCode.FAILURE, "没有找到主播ID");
			}
			HashSet<Integer> set=new HashSet<>();
			
			//查询关注主播的ID
			String aid=liveMap.get("id")+"";//主播ID
			List<Integer> idList=liveUserDao.queryUidListByaid(Integer.parseInt(aid));//查询出liver表ID
			if(idList!=null&&idList.size()>0){
				for(Integer id :idList){
					set.add(id);
				}
			}
			//查询想看的ID
			List<Integer> focusShowList=liveUserDao.queryLiveFocusShowByRid(request.getRid());
			if(focusShowList!=null&&focusShowList.size()>0){
				for(Integer id :focusShowList){
					set.add(id);
				}
			}
			List<Integer> ids=new ArrayList<>();
			if(set.size()>0){
				for(Integer id:set){
					ids.add(id);
				}
			}
			
			
			//需要推送的用户ID,需要跟手机的token绑定
			List<Integer> uidList=liveUserDao.queryLiverUidByaid(ids);
			
			int count=0;
			if(uidList!=null&&uidList.size()>0){
				for(Integer account:uidList){
					try {
						int  deviceType=1;
						String title=request.getTitle();	//标题
						String msg=request.getMsg();	//消息
						String sendTime=request.getSendtime();		//发送时间
						int action_type=request.getAction_type();//代开类型
						String activity=request.getActivity();	//打开路径
						if(request.getSendtime()!=null)sendTime=request.getSendtime();
						JSONObject result=PushSingleDevice.AndroidAccount(deviceType, title, msg, account.toString(),sendTime,action_type,activity);
						if(result.getInt("ret_code")==0){
							count++;
						}
//						else{
//							System.out.println(new String(result.getString("err_msg").getBytes(),"utf-8"));
//						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			MapResponse mapResponse=new MapResponse(ResponseCode.SUCCESS,"成功");
			Map<Object,Object> map=new HashMap<>();
			map.put("count", count);
			mapResponse.setResponse(map);
			return mapResponse;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new BaseResponse(ResponseCode.FAILURE, "失败");
	}

	
	/**
	 * 
	 * @Description: 主播开播提醒
	 * @author xiaoxiong
	 * @date 2016年9月9日
	 * @version
	 */
	public Object pushAnchor(PushUrsRequest request) {
		try {
			Map<Object,Object> liveMap=liveUserDao.queryLiverInfoByUid(Integer.valueOf(request.getUid()));
			if(liveMap==null||liveMap.isEmpty()){
				return new BaseResponse(ResponseCode.FAILURE, "没有找到主播ID");
			}
			
			int  deviceType=1;
			String title=request.getTitle();	//标题
			String msg=request.getMsg();	//消息
			String sendTime=request.getSendtime();		//发送时间
			int action_type=request.getAction_type();//代开类型
			String activity=request.getActivity();	//打开路径
			
			if(request.getSendtime()!=null)sendTime=request.getSendtime();
			JSONObject result=PushSingleDevice.AndroidAccount(deviceType, title, msg, request.getUid(),sendTime,action_type,activity);
			System.out.println(result.toString());
			if("0".equals(result.get("ret_code").toString())){
				return new BaseResponse(ResponseCode.SUCCESS,"成功");
			}else{
				return new BaseResponse(ResponseCode.FAILURE,result.getString("err_msg"));
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new BaseResponse(ResponseCode.FAILURE,"未知错误");
	}

}
