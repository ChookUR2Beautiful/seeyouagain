/**
 * 2016年8月15日 上午11:38:32
 */
package com.xmniao.xmn.core.live.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.common.Constant;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.live.dao.ViewerUserListDao;
import com.xmniao.xmn.core.util.PropertiesUtil;

/**
 * @项目名称：xmnService_3.1.2_zb
 * @类名称：ViewerUserListService
 * @类描述：
 * @创建人： yeyu
 * @创建时间 2016年8月15日 上午11:38:32
 * @version
 */
@Service
public class ViewerUserListService {
	//日志
	private final Logger log = Logger.getLogger(ViewerUserListService.class);
	@Autowired
	private ViewerUserListDao vieweruserlistDao;
	
	@Autowired
	private PersonalCenterService personalcenterService;
	@Autowired
	private LiveRobotService liverobotService;
	//注入服务器地址  图片服务器配置地址
	@Autowired
	private String fileUrl;
	
	@Autowired
	private PropertiesUtil propertiesUtil;
	/**
	 * 
	* @Title: queryViewerUserList
	* @Description: 查看主播房间观众列表
	* @return Object
	 */
	public Object queryViewerUserList(Long anchor_id,Long live_record_id,int page){
		List<Map<Object,Object>> viewerList=new ArrayList<Map<Object,Object>>();
		Map<Object,Object> viewuserMap=new HashMap<Object,Object>();
		try{
			if(page>5){
				return new BaseResponse(ResponseCode.DATA_NULL, "未获取到大于第5页的内容");
			}
			List<Map<Object,Object>> viewerUserList=this.queryViewerUserListByAhId(Integer.parseInt(anchor_id.toString()), Integer.parseInt(live_record_id.toString()), page);
			if(viewerUserList==null || viewerUserList.size()<=0){
				viewuserMap.put("liveViewer", viewerList);
				MapResponse response = new MapResponse(ResponseCode.SUCCESS,"还未有用户观看");
				response.setResponse(viewuserMap);
				return response;
			}
			viewerList=this.ViewerUserList(viewerUserList);
			viewuserMap.put("liveViewer", viewerList);
			//如果是第五页，显示固定图片（感觉没太大意义）
			if(page==5){
				String url = propertiesUtil.getValue("local.domain", "conf_redis.properties");
				viewuserMap.put("lastImg", url+"/img/viewerLastImg.png");
			}
		}catch(Exception e){
			log.error("查看主播房间观众列表失败");
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE, "查看主播房间观众列表失败");
		}
		MapResponse response = new MapResponse(ResponseCode.SUCCESS,"获取主播房间观众列表成功");
		response.setResponse(viewuserMap);
		return response;
	}
	
	/**
	 * 
	* @Title: ViewerUserList
	* @Description: 转换观众列表
	* @return List<Map<Object,Object>>
	 */
	public List<Map<Object,Object>> ViewerUserList(List<Map<Object,Object>> viewerUserList) throws Exception{
		List<Map<Object,Object>> viewerList=new ArrayList<Map<Object,Object>>();
		List<String> lerverids=new ArrayList<String>();
		List<String> rebotids=new ArrayList<String>();
		for(Map<Object,Object> viewerMap:viewerUserList){
			Integer id=Integer.parseInt(viewerMap.get("viewer_id")==null?"0":viewerMap.get("viewer_id").toString());
			int utype=viewerMap.get("utype")==null?0:Integer.parseInt(viewerMap.get("utype").toString());
			if(StringUtils.isNotEmpty(viewerMap.get("utype").toString()) && utype==0){
				rebotids.add(id+"");
			}else{
				lerverids.add(id+"");
			}
			
		}
		//获取真实观众列表
		List<Map<Object,Object>> personList=personalcenterService.queryLiverPersonByListId(lerverids);
		//获取机器人观众列表
		List<Map<Object,Object>>  rebotList=liverobotService.queryRebotListByids(rebotids);
			for(Map<Object,Object> viewerMap:viewerUserList){
				Map<Object,Object> userMap=new HashMap<Object,Object>();
				userMap.put("anchor_room_no", viewerMap.get("anchor_room_no"));//主播房间编号
				userMap.put("anchor_id", viewerMap.get("anchor_id"));//主播用户id
				userMap.put("viewer_id", viewerMap.get("viewer_id"));//观众用户id
				userMap.put("zhibo_record_id", viewerMap.get("zhibo_record_id"));//直播记录id
				userMap.put("utype", viewerMap.get("utype"));//用户类型
				
				int id=Integer.parseInt(viewerMap.get("viewer_id")==null?"0":viewerMap.get("viewer_id").toString());

				int utype=viewerMap.get("utype")==null?0:Integer.parseInt(viewerMap.get("utype").toString());
				if(utype==0){//如果是机器人观众
					if(rebotList!=null && rebotList.size()>0){
						for(Map<Object,Object> rebotInfo:rebotList){
							int rebot_id=rebotInfo.get("anchorid")==null?-1:Integer.parseInt(rebotInfo.get("anchorid").toString());
							if(id==rebot_id){
								String avatar=rebotInfo.get("avatar")==null||rebotInfo.get("avatar").equals("")?"":(fileUrl+rebotInfo.get("avatar").toString());
								userMap.put("uid", rebot_id);//观众用户会员id
								userMap.put("nname", rebotInfo.get("nname"));//用户昵称
								userMap.put("avatar", avatar);//头像
								userMap.put("rank_id", rebotInfo.get("rank_id"));//等级ID
								userMap.put("rank_no", rebotInfo.get("rank_no"));//等级数
								userMap.put("achievement", rebotInfo.get("achievement"));//头衔
								viewerList.add(userMap);
								break;
							}
							
						}
					}
				}else{//真实观众
					if(personList!=null && personList.size()>0){
						for(Map<Object,Object> viewerInfo:personList){
							int liver_id=viewerInfo.get("anchorid")==null?-1:Integer.parseInt(viewerInfo.get("anchorid").toString());
								if(id==liver_id){
									String avatar=viewerInfo.get("avatar")==null||viewerInfo.get("avatar").equals("")?"":(fileUrl+viewerInfo.get("avatar").toString());
									userMap.put("uid", viewerInfo.get("uid"));//观众用户会员id
									userMap.put("nname", viewerInfo.get("nname"));//用户昵称
									userMap.put("avatar", avatar);//头像
									userMap.put("rank_id", viewerInfo.get("rank_id"));//等级ID
									userMap.put("rank_no", viewerInfo.get("rank_no"));//等级数
									userMap.put("achievement", viewerInfo.get("achievement"));//头衔
									viewerList.add(userMap);
									break;
								}
						}
				}
			}
		
		}
		
		return viewerList;
	}
	
	/**
	 * 
	* @Title: queryViewerUserListByAhId
	* @Description: 查看主播房间观众列表
	* @return List<Map<Object,Object>>
	 * @throws Exception 
	 */
	public List<Map<Object,Object>> queryViewerUserListByAhId(int anchor_id,int live_record_id,int page) throws Exception{
		List<Map<Object,Object>> viewerList=null;
		Map<Object,Object> param=new HashMap<Object, Object>();
		try {
			param.put("anchor_id", anchor_id);
			param.put("live_record_id", live_record_id);
			param.put("page", page);
			param.put("limit", Constant.PAGE_LIMIT);
			viewerList=vieweruserlistDao.queryViewerUserListByAhId(param);
		} catch (Exception e) {
			log.error("查看主播房间观众列表失败");
			e.printStackTrace();
			throw new Exception("查看主播房间观众列表失败");
		}
		return viewerList;
	}
}
