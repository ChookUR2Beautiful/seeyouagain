/**
 * 2016年9月6日 下午1:56:32
 */
package com.xmniao.xmn.core.live.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.ObjectUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.common.Constant;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.rocketmq.ProducerServiceImpl;
import com.xmniao.xmn.core.common.rocketmq.model.TopicInfo;
import com.xmniao.xmn.core.live.dao.AnchorLiveRecordDao;
import com.xmniao.xmn.core.live.dao.LiveGiftsInfoDao;
import com.xmniao.xmn.core.live.dao.LiveRobotDao;
import com.xmniao.xmn.core.live.dao.LiveUserDao;
import com.xmniao.xmn.core.live.entity.LiveGiftsInfo;
import com.xmniao.xmn.core.util.DateUtil;
import com.xmniao.xmn.core.util.PropertiesUtil;
import com.xmniao.xmn.core.util.TLSUtil;

/**
 * @项目名称：xmnService_3.1.2_zb
 * @类名称：LiveRobotService
 * @类描述：
 * @创建人： yeyu
 * @创建时间 2016年9月6日 下午1:56:32
 * @version
 */
@Service
public class LiveRobotService {
	private Logger log=Logger.getLogger(LiveRobotService.class);
	
	@Autowired
	private LiveRobotDao  liveRobotDao;
	@Autowired
	private AnchorLiveRecordDao anchorLiveRecordDao;
	
	/** 图片服务器根路径地址 */
	@Autowired
	private String fileUrl;
	
	/** 注入tlsSendImService */
	@Autowired
	private TlsSendImService tlsSendImService;
	
	@Autowired
	private PropertiesUtil propertiesUtil;
	
	@Autowired
	private LiveGiftsInfoDao liveGiftsInfoDao;
	
	@Autowired
	private LiveUserDao liveUserDao;
	
	/** 发送MQ消息 service */
	@Autowired
	private ProducerServiceImpl producerServiceImpl;
	
	/** 机器人行动 MQ队列连接消息  */
	@Autowired
	private TopicInfo robotActionInfo;

	/** 机器人入场提示 MQ队列连接消息  */
	@Autowired
	private TopicInfo robotEntryRoomMsgInfo;
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	/** 机器人出入观看表MQ队列链接消息  */
	@Autowired
	private TopicInfo robotInsertLiveViewInfo;
	/**
	 * 
	* @Title: queryRebotListByids
	* @Description: 查看机器人观众列表
	* @return List<Map<Object,Object>>
	 */
	public List<Map<Object,Object>> queryRebotListByids(List<String> list) throws Exception{
		List<Map<Object,Object>>  rebotList=null;
		try {
			if(list!=null && list.size()>0){
				rebotList=liveRobotDao.queryRebotListByids(list);
			}
		} catch (Exception e) {
			log.error("获取机器人观众列表失败");
			e.printStackTrace();
			throw new Exception("获取机器人观众列表异常");
		}
		return rebotList;
	}
	
	/**
	 * @throws Exception 
	 * 
	* @Title: addRebotLiveFocusShow
	* @Description: 添加机器人观看人数
	* @return Integer    返回类型
	* @throws
	 */
	public List<Map<Object,Object>> insertRobotLiveFocusShow(String anchor_id,String live_record_id,int rebotNum) throws Exception {
		
		//随机获取机器人
		List<Map<Object,Object>> rebotList=this.queryRebotListByObj(anchor_id, live_record_id,rebotNum);
		List<Map<Object,Object>> paramlist=new ArrayList<>();
		if(rebotList!=null && rebotList.size()>0){
			for(Map<Object,Object> rebotMap:rebotList){
				Map<Object,Object> paramMap=new HashMap<>();
				paramMap.put("liveId", rebotMap.get("viewer_id"));//机器人ID
				paramMap.put("liveRecordId", live_record_id);//直播记录id
				paramMap.put("status", 2);//'状态  1 可点击想看  2 不可点击想看'
				paramMap.put("isRebot", 1);//'是否是机器人 0否  1是 '
				paramMap.put("createTime", DateUtil.format(new Date()));//创建时间
				paramMap.put("updateTime", DateUtil.format(new Date()));//更新时间
				paramlist.add(paramMap);
			}
		}
		//添加机器人想看记录
		anchorLiveRecordDao.insertRebotLiveFocusShow(paramlist);
		return rebotList;
	}
	
	/**
	 * 
	* @Title: addRebotLiveRoom
	* @Description: 机器人观众进入直播房间
	* @return void
	* @param type 1:初始化人数  ，否则取随机机器人数
	 */
	public Map<String,Object> addRebotLiveRoom(String anchor_id,String live_record_id,String anchor_room_no,int liverNum,int type){
		Map<String,Object>  addmap = new HashMap<String,Object>();
		Integer recordNums=0;
		addmap.put("recordNums", recordNums);
		try {
			int random=0;
			//先查询是否设置机器人参数，无启用通用参数	
			Map<Object,Object> resultMap=this.queryRobetSet(live_record_id);
			int multiple = 1;
			if(resultMap!=null && resultMap.size()>0){
				
				int robot_min_nums=Integer.parseInt(resultMap.get("robot_min_nums").toString());//机器人初始人数
				multiple = Integer.parseInt(resultMap.get("multiple").toString());//机器人显示倍数
				if(type==1){
					random=robot_min_nums;
				}else{
					int robot_max_nums=Integer.parseInt(resultMap.get("robot_max_nums").toString());//机器人上限人数
					if(liverNum>=robot_max_nums){
					return addmap;	
					}	
					int robot_set_mix_nums=Integer.parseInt(resultMap.get("robot_set_mix_nums").toString());//机器人递增下限人数： 即 每多一个真用户，最少加几个机器人
					int robot_set_max_nums=Integer.parseInt(resultMap.get("robot_set_max_nums").toString());//机器人递增上限人数： 即 每多一个真用户，最多加几个机器人
					int remainRobots = robot_max_nums-liverNum;
					if (remainRobots <= robot_set_max_nums) {
						random = remainRobots;
					}else {
						random=(int)(robot_set_mix_nums+Math.random()*(robot_set_max_nums-robot_set_mix_nums+1)) ;
					}
					
				}
				
			}
			
			if (random > 0) {			
				//随机获取随机数机器人观众
				List<Map<Object,Object>> rebotList=this.queryRebotListByObj(anchor_id, live_record_id,random);
				//把随机机器人放入缓存中
				Map<String,String> robots = new HashMap<String,String>();
				String jsonString =JSON.toJSONString(rebotList);
				robots.put("robots", jsonString);
				String robotKey = "robots_";
				String time = System.currentTimeMillis()+"";
				robotKey+=time+"_"+random;														
				stringRedisTemplate.opsForHash(). putAll(robotKey, robots);
				stringRedisTemplate.expire(robotKey, 24, TimeUnit.DAYS);//默认24小时
				addmap.put("robots", jsonString);
				
				//异步插入机器人到数据库中
				//组装MQ消息发送参数
				JSONObject jsonData = new JSONObject();
				jsonData.put("anchorId", anchor_id);
				jsonData.put("random", random);
				jsonData.put("liveRecordId", live_record_id);
				jsonData.put("liveRoomNo", anchor_room_no);
				jsonData.put("robotKey", robotKey);
				SendResult resultRobotInsert = producerServiceImpl.send(robotInsertLiveViewInfo, jsonData.toString());
				log.info("异步插入机器人到数据库中推送ID"+resultRobotInsert.getMsgId()+"推送结果状态："+resultRobotInsert.getSendStatus());
				
				//对外显示的情况下 1个机器人对外显示  （真实用户+机器人）x倍数=对外显示人数
				//查询用户真实用户人数
				int realNum = anchorLiveRecordDao.queryReadViewerCountByLiveRecordId(Integer.parseInt(live_record_id));
				recordNums += rebotList.size() + realNum + liverNum;
				int viewAddNumber = (recordNums)*multiple;
				addmap.put("recordNums", viewAddNumber);
				
				//修改直播间观看人数
				Map<Object,Object> paramMap=new HashMap<>();
				paramMap.put("viewCount", viewAddNumber);
				paramMap.put("id", live_record_id);
				paramMap.put("updateTime", new Date());
				int result = anchorLiveRecordDao.modifyLiveViewCount(paramMap);
				
				if (result != 1) {
					log.info(live_record_id+"插入机器人显示直播观看人数为error");
				}
				log.info(live_record_id+"插入机器人显示直播观看人数为："+viewAddNumber);
				
				//是否开启机器人入场提示
				int showRobotEnterRoom = Integer.parseInt(propertiesUtil.getValue("showRobotEnterRoom", "conf_live.properties").toString());
				
				if (showRobotEnterRoom==1) {
					String groupId = liveUserDao.queryGroupIdByAnchorId(anchor_id);
					/** 机器人进场时，随机抽取1-5个提示进场，提示后，40%概率发消息，20%概率发礼物 */
					//进入房间是有 20% 概率会发送进入房间提示消息
					//随机拉取 1-5 个机器人
					int robotNum = (int) (Math.random() * 5 + 1);
					
					List<Integer> indexs = new ArrayList<>(5);
					for (int i = 0; i < robotNum; i++) {
						int index = (int) (Math.random() * rebotList.size());
						//存放以选取过的机器人,避免重复
						if(indexs.contains(index)){
							continue;
						}
						indexs.add(index);
						
						Map<Object, Object> robotMap = rebotList.get(index);

					
						//组装MQ消息发送参数
						JSONObject jsonObject = new JSONObject();
						jsonObject.put("dj", robotMap.get("rank_no"));
						jsonObject.put("iconUrl", fileUrl + robotMap.get("avatar"));
						jsonObject.put("uid", robotMap.get("viewer_id").toString());
						jsonObject.put("uname", robotMap.get("nname").toString());
						jsonObject.put("groupId", groupId);
						jsonObject.put("liveRecordId", live_record_id);
						
						//机器人入场 ,先提示 后礼物
						SendResult robotEntryRoomMsgResult = producerServiceImpl.send(robotEntryRoomMsgInfo, jsonObject.toString());
						log.info("推送ID"+robotEntryRoomMsgResult.getMsgId()+"推送结果状态："+robotEntryRoomMsgResult.getSendStatus());
					} 
				}
			}
			
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return addmap;
	}
	
	
	/**
	 * 
	* @Title: queryRebotListByObj
	* @Description: 随机获取机器人观众
	* @return List<Map<Object,Object>>
	 * @throws Exception 
	 */
	public List<Map<Object,Object>> queryRebotListByObj(String anchor_id,String live_record_id,int random) throws Exception{
		List<Map<Object,Object>> rebotList=null;
		try {
			Map<Object,Object> param=new HashMap<>();
			param.put("live_record_id", live_record_id);
			param.put("anchor_id", anchor_id);
			param.put("limit", random);
			rebotList=liveRobotDao.queryRebotListByObj(param);
		} catch (Exception e) {
			log.error("随机获取机器人观众异常");
			e.printStackTrace();
			throw new Exception("随机获取机器人观众异常");
		}
		return rebotList;
	}
	/**
	 * 
	* @Title: addRebotRecord
	* @Description: 直播房间加入机器人
	* @return Integer
	 */
	
	public Integer addRebotRecord(List<Map<Object,Object>> list){
		Integer recordNums=0;
		Integer maxInsertSize = 500;
		try {
			if(list!=null && !list.isEmpty()){
				List<List<Map<Object,Object>>> resultLists = splitList(list, maxInsertSize);
				for (int i=0;i<resultLists.size();i++) {
					recordNums+=liveRobotDao.addRebotRecord(resultLists.get(i));
				}	
			}
		} catch (Exception e) {
			log.error("机器人观众进入房间异常");
			e.printStackTrace();
		}
		return recordNums;
	}
	
	
	/*** 按指定大小，分隔集合，将集合按规定个数分为n个部分*
	 * @param list* 
	 * @param len* 
	 * @return
	 * */
	public  List<List<Map<Object, Object>>> splitList(List<Map<Object, Object>> list, int len) {
		if (list == null || list.size() == 0 || len < 1) {
			return null;
		}
		List<List<Map<Object, Object>>> result = new ArrayList<List<Map<Object, Object>>>();
		int size = list.size();
		int count = (size + len - 1) / len;
		for (int i = 0; i < count; i++) {
			List<Map<Object, Object>> subList = list.subList(i * len, ((i + 1) * len > size ? size : len * (i + 1)));
			result.add(subList);
		}
		return result;
	}
	/**
	 * 
	* @Title: queryRobetSet
	* @Description: 获取机器人配置信息:先读取设置的，无读取通用的
	* @return Map<Object,Object>
	 */
	public Map<Object,Object> queryRobetSet(String liveRecordId) throws Exception{
		Map<Object,Object> resultMap=null;
		try {
			//通告机器人配置
			resultMap=liveRobotDao.queryLiveRecordRobetSet(Integer.parseInt(liveRecordId));
			if (resultMap==null || resultMap.isEmpty()) {
				//通用配置
				resultMap=liveRobotDao.queryRobetSet();
			}	
		} catch (Exception e) {
			log.error("获取机器人配置信息异常");
			e.printStackTrace();
			throw new Exception("获取机器人配置信息异常");
		}
		return resultMap;
	}
	public static void main(String[] args) {
		int i = (int)(10+Math.random()*(20-10+1)) ;
		System.out.println(i);
	}
	
	/**
	 * 机器人直播间发送礼物
	 * @Title:robotSendGift
	 * @Description:机器人直播间发送礼物
	 * @param dj 等级
	 * @param iconUrl 头像url
	 * @param uid uid
	 * @param uname 昵称
	 * @param groupId 主播群组id
	 * @throws IOException void
	 * 2017年6月23日上午11:46:36
	 */
	public void robotSendGift(String dj, String iconUrl, String uid, String uname, String groupId, String liveRecordId) throws IOException {
		//查看后台配置是否开启 机器人发送礼物
		int isliveRobotSendGifts = Integer.parseInt(propertiesUtil.getValue("isliveRobotSendGifts", "conf_live.properties").toString());
		if (isliveRobotSendGifts==0) {
			return;
		}
		// 获取发礼物最大的发量和最小发送量
		int maxNum = Integer
				.parseInt(propertiesUtil.getValue("liveRobotSendMaxNum", "conf_live.properties").toString());
		int minNum = Integer
				.parseInt(propertiesUtil.getValue("liveRobotSendMinNum", "conf_live.properties").toString());
		// 是否可以连击 1 是 0 否
		int iSDoubleHit = Integer
				.parseInt(propertiesUtil.getValue("liveRobotSendISDoubleHit", "conf_live.properties").toString());

		// 先获取礼物列表
		Map<Object, Object> paramMap = new HashMap<Object, Object>();
		paramMap.put("liver_id", 0);
		paramMap.put("giftPrice", 20);// 获取20豆一下的礼物
		List<LiveGiftsInfo> gifts_list = liveGiftsInfoDao.getGiftsInfoList(paramMap);
		if (gifts_list.size() <= 0) {
			log.info("机器人发礼物,未获取到礼物列表");
			return;
		}
		//随机获取一个礼物
		int index = (int) (Math.random() * gifts_list.size());
		LiveGiftsInfo giftsInfo = gifts_list.get(index);

		if (giftsInfo != null && null != giftsInfo.getId()) {

			// 生成随机数 制定发礼物循环次数 判断是否可用连击
			int randomNum = 1;
			if (iSDoubleHit == 1) {
				randomNum = (int) (Math.random() * (maxNum - minNum + 1) + minNum);
			}
			for (int k = 0; k < randomNum; k++) {
				if (k > 3) {
					break;
				}
				// 开始发送礼物
				// 生成发送IM群组自定义信息请求参数
				Map<Object, Object> imGiftMap = new HashMap<Object, Object>();
				try {
					imGiftMap.put("GroupId", groupId);
					imGiftMap.put("type", 4);
					imGiftMap.put("utype", 0);
					imGiftMap.put("iconUrl", iconUrl);
					imGiftMap.put("dj", dj);
					imGiftMap.put("giftId", 1);
					imGiftMap.put("giftPrice", 1);
					imGiftMap.put("giftName", giftsInfo.getGift_name());
					imGiftMap.put("giftIconUrl", fileUrl + giftsInfo.getGift_avatar());
					imGiftMap.put("giftType", 2);
					imGiftMap.put("color", 2416770);
					imGiftMap.put("isFree", 1);
					imGiftMap.put("eggCount", 0);
					imGiftMap.put("uid", uid);
					imGiftMap.put("text", "送了1个" + giftsInfo.getGift_name());
					imGiftMap.put("uname", uname);
					Map<Object, Object> resultMap = tlsSendImService.createSendGroupMsgParam(imGiftMap);
					// 发送IM消息
					boolean flag = TLSUtil.sendGroupMsg(resultMap);
					if (!flag) {
						log.info("机器人发送礼物失败");
					}else {
						//发送礼物,保存redisKey ,用于匹配机器人是否 行动
						String key = Constant.ROBOT_ACTION_KEY + liveRecordId;
						if (stringRedisTemplate.hasKey(key)) {
							stringRedisTemplate.expire(key, 90, TimeUnit.SECONDS);
						}else {
							stringRedisTemplate.opsForValue().set(key, "true");
							stringRedisTemplate.expire(key, 90, TimeUnit.SECONDS);
						}
					}

				} catch (Exception e) {
					log.info("机器人发送礼物失败");
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 机器人发送留言消息
	 * @Title:robotSendMsg
	 * @Description:机器人发送留言消息
	 * @param robotMap
	 * @param groupId
	 * @throws IOException
	 * @throws InterruptedException void
	 * 2017年6月21日下午5:22:02
	 */
	public void robotSendMsg(String dj, String iconUrl, String uid, String uname, String groupId, String liveRecordId) throws IOException, InterruptedException {
		// 是否开启发字幕聊天信息
		int isliveRobotSendMsg = Integer.parseInt(propertiesUtil.getValue("isliveRobotSendMsg", "conf_live.properties").toString());
		if (isliveRobotSendMsg == 0) {
			return;
		}

		// 执行发文本
		// 开始发弹幕 查询信息 赋值到text参数
		Map<Object, Object> queryMap = liveRobotDao.queryRobotInfomation();
		if (queryMap != null) {
			Map<Object, Object> infomationMap = new HashMap<Object, Object>();
			try {
				infomationMap.put("text", queryMap.get("content"));
				infomationMap.put("color", "16777215");
				infomationMap.put("dj", dj);
				infomationMap.put("iconUrl", iconUrl);
				infomationMap.put("GroupId", groupId);
				infomationMap.put("type", 1);
				infomationMap.put("utype", 0);
				infomationMap.put("uid", uid);
				infomationMap.put("uname", uname);
				Map<Object, Object> resultMap = tlsSendImService.createSendGroupMsgParam(infomationMap);
				// 发送IM消息
				boolean flag = TLSUtil.sendGroupMsg(resultMap);
				if (!flag) {
					log.info("机器人发送弹幕失败");
				}else {
					//发送弹幕,保存redisKey ,用于匹配机器人是否 行动
					String key = Constant.ROBOT_ACTION_KEY + liveRecordId;
					if (stringRedisTemplate.hasKey(key)) {
						stringRedisTemplate.expire(key, 90, TimeUnit.SECONDS);
					}else {
						stringRedisTemplate.opsForValue().set(key, "true");
						stringRedisTemplate.expire(key, 90, TimeUnit.SECONDS);
					}
				}
			} catch (Exception e) {
				log.info("机器人发送礼物失败");
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 机器人入场发送提示消息
	 * @throws Exception 
	 * @Title:robotEntryRoomMsg
	 * @Description:
	 * 2017年6月26日下午4:21:22
	 */
	public void robotEntryRoomMsg(String dj, String iconUrl, String groupId, String uid, String uname) throws Exception{
		
		// 查看配置是否开启 机器人 进入房间提示信息
		int showRobotEnterRoom = Integer.parseInt(propertiesUtil.getValue("showRobotEnterRoom", "conf_live.properties").toString());
		if (showRobotEnterRoom == 0) {
			return;
		}
		
		Map<Object, Object> infomationMap = new HashMap<Object, Object>();
		infomationMap.put("text", "进入了房间");
		infomationMap.put("color", "16034601");
		infomationMap.put("dj", dj);
		infomationMap.put("iconUrl", iconUrl);
		infomationMap.put("GroupId", groupId);
		infomationMap.put("type", 1);
		infomationMap.put("utype", 0);
		infomationMap.put("uid", uid);
		infomationMap.put("uname", uname);
		Map<Object, Object> map = tlsSendImService.createSendGroupMsgParam(infomationMap);
		//发送IM消息
		boolean result = TLSUtil.sendGroupMsg(map);
		if (!result) {
			log.info("机器人进场提示失败");
		}else {
			log.info("机器人进场提示IM发送成功!"+map.toString());
		}
	}
}
