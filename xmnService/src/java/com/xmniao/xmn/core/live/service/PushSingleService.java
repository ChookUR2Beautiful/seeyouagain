/**
 * 2016年9月13日 上午11:30:16
 */
package com.xmniao.xmn.core.live.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ObjectUtils;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.remoting.exception.RemotingException;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.ConstantDictionary;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.PushRequest;
import com.xmniao.xmn.core.common.request.experience.PushExperienceRequest;
import com.xmniao.xmn.core.common.request.live.PushSingleRequest;
import com.xmniao.xmn.core.common.rocketmq.ProducerServiceImpl;
import com.xmniao.xmn.core.common.rocketmq.model.TopicInfo;
import com.xmniao.xmn.core.live.dao.AnchorLiveRecordDao;
import com.xmniao.xmn.core.live.dao.PersonalDetailDao;
import com.xmniao.xmn.core.live.entity.LiveRecordInfo;
import com.xmniao.xmn.core.live.entity.PushMessageVo;
import com.xmniao.xmn.core.order.dao.ExperienceActivityDao;
import com.xmniao.xmn.core.util.DateUtil;
import com.xmniao.xmn.core.util.PropertiesUtil;
import com.xmniao.xmn.core.util.PushSingleUtil;

/**
 * @项目名称：xmnService_3.1.2_zb
 * @类名称：PushSingleService
 * @类描述：
 * @创建人： yeyu
 * @创建时间 2016年9月13日 上午11:30:16
 * @version
 */
@Service
public class PushSingleService {

	private Logger log=Logger.getLogger(PushSingleService.class);
	@Autowired
	private PersonalDetailDao  personaldetailDao;
	@Autowired
	private PersonalCenterService  personalCenterService;
	@Autowired
	private AnchorLiveRecordDao anchorLiveRecordDao;
	
	@Autowired
	private PropertiesUtil propertiesUtil;
	
	@Autowired
	private ProducerServiceImpl producerServiceImpl;
	@Autowired
	private TopicInfo topicStartLiveInfo;
	@Autowired
	private ExperienceActivityDao experienceActivityDao;
	@Autowired
	private SessionTokenService sessionTokenService;
	
	/**
	 * 
	* @Title: pushLiveFocus
	* @Description: 
	* @param 推送类型：ostype 1:预告推送 2：开始直播推送
	* @return Object
	 */
	public Object pushLiveMesgge(PushSingleRequest psRequest,int ostype){
		try{
			List<Map<Object,Object>> focusList=this.queryAttentionFansListAll(psRequest.getAnchorId());
			
			log.info("获取粉丝列表成功");
			List<String> focusids=new ArrayList<String>();
			if(focusList!=null && focusList.size()>0){
				for(Map<Object,Object> fansMap:focusList)
				{
					//获取主播ID
					Integer liver_str_id=Integer.parseInt(fansMap.get("liver_str_id").toString());
					focusids.add(liver_str_id+"");
				}
			}
			List<String> wantseesids=new ArrayList<String>();
			String context="";
			String wtcontext="";
			String sendTime=null;
			if(ostype==1){
				sendTime=this.getSendTime(psRequest.getStartTime());
				context="你关注的主播【"+psRequest.getAnchorName()+"】在【"+psRequest.getSellerName()+"】直播，喊你到现场参加";
			}else if(ostype==2){
				List<Map<Object,Object>> focuShowList=this.queryFansShowListAll(psRequest.getRecordId());
				if(focuShowList!=null && focuShowList.size()>0){
					for(Map<Object,Object> wantseeMap:focuShowList){
						//获取想看用户iD
						wantseesids.add(wantseeMap.get("liver_id").toString());
					}
				}
				
				//关注
				context="你关注的主播【"+psRequest.getAnchorName()+"】正在直播，点击进入";
				
			}else{
				return new BaseResponse(ResponseCode.FAILURE, "消息推送失败,推送类型有误");
			}
			
			StringBuffer reuslt=new StringBuffer("");
			String msg=this.isWantpush(psRequest, context, focusids, sendTime);//关注的粉丝推送
			if(!StringUtils.isEmpty(msg)&& !"".equalsIgnoreCase(msg)){
			log.info("关注："+msg+":");
			reuslt.append("关注："+msg+":");
			}
			//想看
			wtcontext="你想看的主播【"+psRequest.getAnchorName()+"】正在直播，点击进入";
			String wtmsg=this.isWantpush(psRequest, wtcontext, wantseesids, sendTime);//想看的推送
			if(!StringUtils.isEmpty(wtmsg)&& !"".equalsIgnoreCase(wtmsg)){
				log.info("想看："+wtmsg);
				reuslt.append(wtmsg);
			}
			if(reuslt!=null && !reuslt.equals("")){
				log.info("推送---------------------------："+reuslt.toString());
				return new BaseResponse(ResponseCode.SUCCESS, reuslt.toString());
			}
			return new BaseResponse(ResponseCode.FAILURE, "消息推送失败");

		}catch(Exception e){
			log.error("消息推送失败");
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE, e.getMessage());
		}
	}
	
	/**提醒我
	 * @param activityId
	 * @param uid
	 * @return 信鸽推送成功返回0，失败返回-1
	 */
	public int pushSingleAccountRemindActivity(Integer activityId, String uid,String appSource) {
		// 查询活动报名时间 提前10分钟
		try {
			Map<Object, Object> activity = experienceActivityDao.findExperenceActivityById(activityId);
			if (activity==null || activity.isEmpty()){
				log.error("活动查询出错");
				return -1;
			}
			Date entroTime = DateUtil.parse(activity.get("enroll_time").toString(),"yyyy-MM-dd HH:mm:ss");
			Calendar c = Calendar.getInstance();
			c.setTime(entroTime);
			c.add(Calendar.MINUTE, -10);
			String sendTime = DateUtil.format(c.getTime(),"yyyy-MM-dd HH:mm:ss");
			log.info("时间查询开始了-----");
			// 测试时间
			// sendTime="2016-09-13 14:52:01";//小于服务器时间马上发送
			// 测试时间

			/* 拼装消息格式内容 */
			List<String> accountList = new ArrayList<String>();
			accountList.add(uid);
			Map<String, Object> jsob = new HashMap<String, Object>();
			String context = "尊敬的美食体验馆,距离你感兴趣的活动开抢时间还有10分钟,请准备好哟！";
			String title = "美食体验抢位提醒";
			jsob.put("activityId", activityId);
			jsob.put("sdate", sendTime);
			jsob.put("content", context);
			jsob.put("title", title);
			jsob.put("is_show", 0);
			jsob.put("activity_type", 1);
			jsob.put("action", propertiesUtil.getValue("exprienceActivityUrl", "conf_common.properties").toString()
					+ "?action=app-officer-shop-details");
			/* 发送消息 */
			PushMessageVo vo = new PushMessageVo();
			vo.setAccount(uid.toString());
			vo.setParams(jsob);
			vo.setTitle(title);
			vo.setContent(context);
			vo.setSendTime(sendTime);
			vo.setAppSource(appSource);
			int[] sum = pushActivityMessage(vo);
			/*int[] sum = this.pushMessage(title, context, accountList, jsob, 1, sendTime);*/
			StringBuffer sb = new StringBuffer("推送信息成功：总共" + accountList.size() + "条。");
			sb.append("Android推送成功" + sum[0] + "条，失败" + (accountList.size() - sum[0]) + "条。");
			sb.append("IOS推送成功" + sum[1] + "条，失败" + (accountList.size() - sum[1]) + "条。");
			log.info(sb.toString());
			
			
			if (!isSendSuccess(sum)) {
				log.error("信鸽推送结果返回值为0");
				return -1;
			}
		} catch (Exception e) {
			log.error("调用信鸽API出错："+uid+"--"+e);
			e.printStackTrace();
			return -1;
		}
		return 0;

	}
	
	private Boolean isSendSuccess(int[] sum) {
		Boolean flag=false;
		if (sum!=null) {
			for(int i :sum){
				log.info("推送消息sum值--"+i);
				if (i==1){
					flag=true;
					break;
				}
			}
		}
		return flag;
		
	}
	/**活动报名成功消息推送
	 * @param request
	 * @return
	 */
	public Object pushActivityEntroll(PushExperienceRequest request) {
		String uid = sessionTokenService.getStringForValue(request.getSessiontoken()) + "";
		// H5直接从request拿uid
		if (StringUtils.isEmpty(uid) || "null".equalsIgnoreCase(uid)) {
			uid = request.getUid() + "";
		}
		// 都为空报错
		if (StringUtils.isEmpty(uid) || "null".equalsIgnoreCase(uid)) {
			return new BaseResponse(ResponseCode.TOKENERR, "token已失效,请重新登录");
		}

		Integer activityId = request.getActivityId();
		// 活动信息
		try {
			Map<Object, Object> activity = experienceActivityDao.findExperenceActivityById(activityId);
			// 美食体验关参与信息
			Map<Object, Object> paraMap = new HashMap<Object, Object>();
			paraMap.put("activityId", activityId);
			paraMap.put("uid", uid);
			Map<Object, Object> activityEntrollRecord = experienceActivityDao.findExperenceEntrollerRecord(paraMap);
			if (activity == null || activityEntrollRecord == null) {
				log.info("活动异常" + activityId);
				return new BaseResponse(ResponseCode.FAILURE, "活动信息异常");
			}
			Date planLiveTime = (Date) activity.get("plan_start_date");
			// 拼装消息内容
			String title = "用餐提醒";
			String activityTitle =activity.get("sellername")+"";
			String content = "恭喜您成功获得"+DateUtil.format(planLiveTime, "MM月dd日 HH时mm分")+"的"
			+activityTitle+"的美食体验资格，核对码"+activityEntrollRecord.get("verification_code")+",就餐地点："+ activity.get("address")
			+"，届时将会有主播和您联系，请提前到店参加。";
			// 提前俩个小时
			Calendar c = Calendar.getInstance();
			c.setTime(planLiveTime);
			c.add(Calendar.HOUR, -2);
			String sendTime = DateUtil.format(c.getTime());
			// 测试时间
			// sendTime="2016-09-13 14:52:01";//小于服务器时间马上发送
			// 测试时间
			List<String> accountList = new ArrayList<String>();
			accountList.add(uid);
			Map<String, Object> jsob = new HashMap<String, Object>();
			///// 传递参数/////
			jsob.put("activityId", activityId);
			jsob.put("sdate", DateUtil.format(c.getTime(), "MM月dd日 HH:mm"));
			jsob.put("content", content);
			jsob.put("title", title);
			jsob.put("is_show", 0);
			jsob.put("activity_type", 0); //用餐提醒
			jsob.put("action", "");
			///// 传递参数/////
			PushMessageVo vo = new PushMessageVo();
			vo.setAccount(uid.toString());
			vo.setParams(jsob);
			vo.setTitle(title);
			vo.setContent(content);
			vo.setSendTime(sendTime);
			vo.setAppSource(request.getAppSource());
			int[] sum = this.pushActivityMessage(vo);
			StringBuffer sb = new StringBuffer("推送信息成功：总共" + accountList.size() + "条。");
			sb.append("Android推送成功" + sum[0] + "条，失败" + (accountList.size() - sum[0]) + "条。");
			sb.append("IOS推送成功" + sum[1] + "条，失败" + (accountList.size() - sum[1]) + "条。");
			log.info(sb.toString());
			if (!isSendSuccess(sum)) {
				log.error("信鸽推送结果返回值为0");
				return -1;
			}
		} catch (Exception e) {
			log.error("调用信鸽API出错："+uid+"--"+e);
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE,"用餐提醒 --信鸽推送失败");
		}
		return new BaseResponse(ResponseCode.SUCCESS, "推送成功");
	}
	
	
	
	/**体验卡推送通知--出现异常重复提醒3次如果失败为最终失败。
	 * @param title
	 * @param context
	 * @param accountList
	 * @param jsob
	 * @param type
	 * @param sendTime
	 * @return
	 */
	private int[] pushActivityMessage(PushMessageVo vo){
		int[] sum = {0,0};
		int i=0;
		try {
		do{
			sum = this.pushMessageNew(vo);
			i++;
		} while(sum!=null && (sum[0]==0&&sum[1]==0) && i<=3);
		} catch (Exception e) {
			log.info("调用活动推送通知异常"+e);
			e.printStackTrace();
		}
		return sum;
	}
	
	
	
	/**推送消息
	 * @param request
	 * @return
	 */
	public Object pushMessage (PushRequest request) {
		try {
			Integer uid = request.getPushUid(); // 推送uid
			String sendTime = request.getSendTime(); // 推送时间
			String title = request.getTitle(); // 推送title
			String content = request.getContent(); // 推送内容
			String jumpleUrl = request.getUrl(); // 跳转地址
			String param = request.getParam();
			Map<String,Object> paramMap = JSON.parseObject(param,new TypeReference<Map<String,Object>>(){});
			List<String> accountList = new ArrayList<String>();
			accountList.add(uid.toString());
			Map<String, Object> jsob = new HashMap<String, Object>();
			///// 传递参数/////
			jsob.put("action", jumpleUrl);
			jsob.put("activity_type", 2);//type=2跳转
			if (paramMap !=null && !paramMap.isEmpty()) {
				jsob.putAll(paramMap);
			}	
			///// 传递参数/////
			PushMessageVo vo = new PushMessageVo();
			vo.setAccount(uid.toString());
			vo.setParams(jsob);
			vo.setTitle(title);
			vo.setContent(content);
			vo.setSendTime(sendTime);
			vo.setAppSource(request.getAppSource());
			int[] sum = this.pushActivityMessage(vo);
			StringBuffer sb = new StringBuffer("推送信息成功：总共" + accountList.size() + "条。");
			sb.append("Android推送成功" + sum[0] + "条，失败" + (accountList.size() - sum[0]) + "条。");
			sb.append("IOS推送成功" + sum[1] + "条，失败" + (accountList.size() - sum[1]) + "条。");
			log.info(sb.toString());
			if (!isSendSuccess(sum)) {
				log.error(request.getAppSource()+uid+":信鸽推送结果返回值为" + sum);
				return new BaseResponse(ResponseCode.FAILURE, "推送失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("信鸽推送结果返回值为0" + e);
			return new BaseResponse(ResponseCode.FAILURE, "推送失败");
		}
		return new BaseResponse(ResponseCode.SUCCESS, "推送成功");
	}
	
	
	/**
	 * 
	* @方法名称: isWantpush
	* @描述: 推送想看的或关注的
	* @返回类型 String
	* @创建时间 2016年10月17日
	* @param psRequest
	* @param context
	* @param accountList
	* @param sendTime
	* @return
	* @throws Exception
	 */
	public String  isWantpush(PushSingleRequest psRequest,String context,List<String> ids,String sendTime) throws Exception{
		try{
		Map<String, Object> jsob=new HashMap<>();
		jsob.put("anchorId", psRequest.getAnchorId());
		jsob.put("recordId", psRequest.getRecordId());
		jsob.put("roomNo", psRequest.getRoomNo());
		jsob.put("groupId", psRequest.getGroupId());
		//查询直播记录信息
		LiveRecordInfo liveRecord = anchorLiveRecordDao.queryLiveRecordById(psRequest.getRecordId());
		if (liveRecord == null) {
			return "";
		}
		jsob.put("zhiboType", liveRecord.getZhibo_type());
		
		List<Map<Object,Object>> pushList=personalCenterService.queryLiverPersonByListId(ids);
		List<String> accountList=new ArrayList<>();
		if(pushList!=null && pushList.size()>0){
			log.info("获取粉丝列表或者想看粉丝列表基本信息成功");
			for(Map<Object,Object> personMap:pushList){
				accountList.add(personMap.get("uid").toString());
			}
			//推送关注人员信息
			if(accountList!=null && accountList.size()>0){
				int[] sum=this.pushMessage( "您有一条新消息",context, accountList, jsob,psRequest.getType(),sendTime);
				if(psRequest.getType()==1){	
					StringBuffer sb=new StringBuffer("推送信息成功：总共"+accountList.size()+"条。");
					sb.append("Android推送成功"+sum[0]+"条，失败"+(accountList.size()-sum[0])+"条。");
					sb.append("IOS推送成功"+sum[1]+"条，失败"+(accountList.size()-sum[1])+"条。");
					log.info(sb.toString());
					return sb.toString();
				}else if(psRequest.getType()==2){	
					log.info("取消定时推送消息成功"+sum+"条，失败"+(accountList.size()-sum[0])+"条");
					return "取消定时推送消息成功"+sum+"条，失败"+(accountList.size()-sum[0])+"条";
				}
			}
		}
		}catch(Exception e){
			log.error("推送异常，请联系管理员");
			e.printStackTrace();
			throw new Exception("推送异常，请联系管理员");
		}
			return null;
	}
	/**
	 * 
	* @Title: queryAttentionFansListAll
	* @Description: 获取所有粉丝列表
	* @return List<Map<Object,Object>>
	 */
	public List<Map<Object,Object>> queryAttentionFansListAll(int anchorId) throws Exception{
		List<Map<Object,Object>> focusList=null;
		try{
		Map<Object,Object> param=new HashMap<Object,Object>();
		param.put("liver_end_id", anchorId);
		focusList=personaldetailDao.queryAttentionFansListAll(param);
		}catch(Exception e){
			log.error("查询粉丝列表失败");
			e.printStackTrace();
			throw new Exception("消息推送失败，获取关注粉丝失败");
		}
		return focusList;
	}
	
	
	/**
	 * 
	* @Title: queryFansShowListAll
	* @Description: 获取所有想看该直播列表列表
	* @return List<Map<Object,Object>>
	 */
	public List<Map<Object,Object>> queryFansShowListAll(int liveRecordId) throws Exception{
		List<Map<Object,Object>> focuShowList=null;
		try{
		focuShowList=personaldetailDao.queryFansShowListAll(liveRecordId);
		}catch(Exception e){
			log.error("查询粉丝列表失败");
			e.printStackTrace();
			throw new Exception("消息推送失败，获取关注粉丝失败");
		}
		return focuShowList;
	}
	
	
	/**
	 * 
	* @Title: getsendTime
	* @Description: 获取发送时间
	* @return String
	 * @throws IOException 
	 * @throws  
	 */
	private String getSendTime(String starttime) throws IOException{
		if(starttime==null ||"".equals(starttime)){
			return null;
		}
				//字符串转换成时间格式
		Date stime=DateUtil.parse(starttime);
		if(stime==null){
			return null;
		}
		Double pushtime=Double.valueOf(propertiesUtil.getValue("push_time", "conf_live.properties").toString());
		Long time=(long) (pushtime*60*60*1000);
		//将计划时间减2小时，获取到推送时间
		Calendar c=Calendar.getInstance();
		c.setTimeInMillis(stime.getTime()-time);
		System.out.println(DateUtil.format(c.getTime()));
		String sendTime=DateUtil.format(c.getTime());
		return sendTime;
	}
	
	
	
	
	/**推送单个账号消息
	 * @param vo
	 * @param type 
	 * @return
	 */
	private  int[] pushMessageNew (PushMessageVo vo) {
		int[] sum = new int[2];
		int android = 0;
		int iso = 0;
		// 默认xmn秘钥
		try {
			long androidAccessId = Long.valueOf(propertiesUtil.getValue("andorid_accessid", "conf_live.properties"));
			String androidSecreKey = propertiesUtil.getValue("andorid_secretkey", "conf_live.properties");
			long iosAccessId = Long.valueOf(propertiesUtil.getValue("ios_accessid", "conf_live.properties"));
			String iosSecreKey = propertiesUtil.getValue("ios_secretkey", "conf_live.properties");
			if (ConstantDictionary.AppSourceState.BIRD_APP.getName().equals(vo.getAppSource())) {
				// 鸟人直播秘钥
				androidAccessId = Long.valueOf(propertiesUtil.getValue("bird_andorid_accessid", "conf_live.properties"));
				androidSecreKey = propertiesUtil.getValue("bird_andorid_secretkey", "conf_live.properties");
				iosAccessId = Long.valueOf(propertiesUtil.getValue("bird_ios_accessid", "conf_live.properties"));
				iosSecreKey = propertiesUtil.getValue("bird_ios_secretkey", "conf_live.properties");
			}
			JSONObject ret = PushSingleUtil.AndroidAccount(androidAccessId, androidSecreKey, 0, vo.getTitle(),vo.getContent(), vo.getAccount(), vo.getParams(), vo.getSendTime());
			JSONObject isoret = PushSingleUtil.IOSAccount(iosAccessId, iosSecreKey, 1, vo.getTitle(),vo.getContent(), vo.getAccount(), "0", vo.getParams(), vo.getSendTime());
			if (ObjectUtils.toString(ret.get("ret_code").toString()).equals("0")) {
				android++;
			}
			if (ObjectUtils.toString(isoret.get("ret_code").toString()).equals("0")) {
				iso++;
			}
			log.info(ObjectUtils.toString(vo.getAppSource()) + vo.getAccount()+ ":推送结果---" + ret.toString());
			log.info(ObjectUtils.toString(vo.getAppSource()) + vo.getAccount()+ ":推送结果---" + isoret.toString());
		} catch (Exception e) {
			e.printStackTrace();
			log.info("推送消息异常" + e);
		}
		sum[0] = android;
		sum[1] = iso;
		return sum;
	}
	
	
	
	
	
	/**
	 * 
	* @Title: pushMessage
	* @Description: 推送消息
	* @return void
	 * @throws Exception 
	 */
	private int[] pushMessage(String title,String context,List<String> accountList,Map<String, Object>  jsob,int type,String sendTime) throws Exception{
		int[] sum=new int[2];
		//安卓accessid
		long accessid=Long.valueOf(propertiesUtil.getValue("andorid_accessid", "conf_live.properties"));
		//安卓secretkey
		String secretkey=propertiesUtil.getValue("andorid_secretkey", "conf_live.properties");
		//IOS accessid
		long isoAccessid=Long.valueOf(propertiesUtil.getValue("ios_accessid", "conf_live.properties"));
		//ISO secretkey
		String isoSecretkey=propertiesUtil.getValue("ios_secretkey", "conf_live.properties");
		
		int android=0;
		int iso=0;
		if(type==1){
			for(String uid:accountList){
				//推送时间推送消息Android
				JSONObject ret=PushSingleUtil.AndroidAccount(accessid, secretkey, 0, title, context, uid,jsob, sendTime);
				log.info("安卓推送信息"+ret.toString());
				if(ret.get("ret_code").toString().equals("0")){
					log.info("Android推送信息,账户（uid）："+uid+"推送成功");
					android++;
				}else{
					log.info("Android推送信息,账户（uid）："+uid+"推送失败"+ret);
				}
				//推送时间推送消息IOS
				JSONObject isoret=PushSingleUtil.IOSAccount(isoAccessid, isoSecretkey, 1, title, context, uid, "0", jsob, sendTime);
				log.info("ISO推送信息"+isoret.toString());
				if(isoret.get("ret_code").toString().equals("0")){
					log.info("iso推送信息,账户（uid）："+uid+"推送成功");
					iso++;
				}else{
					log.info("iso推送信息,账户（uid）："+uid+"推送失败"+ret);
				}
			}
		}else if(type==2){
			for(String uid:accountList){
				JSONObject ret=PushSingleUtil.cancelTimingPush(accessid, secretkey, uid);
				System.out.println(ret.toString());
				log.info("取消定时推送信息"+ret.toString());
				if(ret.get("ret_code").toString().equals("0")){
					android++;
				}
			}
		}else{
			throw new Exception("操作类型错误");
		}
		sum[0]=android;
		sum[1]=iso;
		
		return sum;
	}
	
	/**
	 * 
	* @Title: wantSeeLive
	* @Description: 为客户端推送想看主播
	* @return Object
	 */
	public void wantSeeLive(String uid,String recordId){
			try{
				Map<Object,Object> paramMap=new HashMap<>();
				paramMap.put("id", recordId);
				LiveRecordInfo recordInfo=anchorLiveRecordDao.queryAnchorLiveRecordById(paramMap);
				if(recordInfo==null){
					log.info("未获取到主播的直播信息:uid:"+uid);
					return;
				}
				
				Map<Object,Object> perMap=personalCenterService.queryLiverPersonById(Integer.parseInt(recordInfo.getAnchor_id()+""));
				if(perMap==null || perMap.size()<=0){
					log.info("未获取主播信息:anchor_id:"+recordInfo.getAnchor_id());
					log.info("想看推送失败");
				}else{
					List<String> accountList=new ArrayList<>();
					accountList.add(uid);
					Map<String, Object> jsob=new HashMap<>();
					jsob.put("anchorId", recordInfo.getAnchor_id());
					jsob.put("recordId", recordInfo.getId());
					jsob.put("roomNo", recordInfo.getAnchor_room_no());
					jsob.put("groupId", perMap.get("group_id").toString());
					String startTime=DateUtil.format(recordInfo.getPlan_start_date());
					String sendTime=this.getSendTime(startTime);
					if(!this.isPush(sendTime)){
						log.info("已过计划直播时间，提前2小时推送失效");
						return;
					}
					int[] sum=this.pushMessage( "您有一条新消息","你想看的主播【"+recordInfo.getNname()+"】在【"+recordInfo.getSellername()+"】直播，喊你到现场参加", accountList, jsob,1,sendTime);
					StringBuffer sb=new StringBuffer("推送信息成功：总共"+accountList.size()+"条。");
					sb.append("Android推送成功"+sum[0]+"条，失败"+(accountList.size()-sum[0])+"条。");
					sb.append("IOS推送成功"+sum[1]+"条，失败"+(accountList.size()-sum[1])+"条。");
					log.info(sb.toString());
				}
				
		}catch(Exception e){
			log.error("推送想看信息失败");
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	* @方法名称: isPush
	* @描述: 是否推送
	* @返回类型 boolean
	* @创建时间 2016年9月30日
	* @param sendTime 发送时间
	* @return
	 */
	public boolean isPush(String sendTime){
		Calendar c=Calendar.getInstance();
		c.setTime(DateUtil.parse(sendTime));
		long sendTimeS=c.getTimeInMillis();
		//推送时间大于或等于当前时间，可推送
		if(sendTimeS>=System.currentTimeMillis()){
			return true;
		}
		return false;
	}
	/**
	 * 
	* @方法名称: SendMqStartLive
	* @描述: 推送直播消息到mq
	* @返回类型 void
	* @创建时间 2016年9月20日
	* @param liverMap
	* @param recordInfo
	* @throws UnsupportedEncodingException
	* @throws JSONException
	* @throws MQClientException
	* @throws RemotingException
	* @throws MQBrokerException
	* @throws InterruptedException
	 */
	public void SendMqStartLive(Map<String, String> liverMap,LiveRecordInfo recordInfo){
		try{
		Map<Object,Object> param=new HashMap<>();
		param.put("anchorId", recordInfo.getAnchor_id());
		param.put("anchorName", liverMap.get("nname"));
		param.put("groupId", liverMap.get("groupId"));
		param.put("recordId", recordInfo.getId());
		param.put("roomNo", liverMap.get("anchor_room_no"));
		param.put("sellerName", recordInfo.getSellername());
		param.put("startTime", recordInfo.getStart_date());
		SendResult result=producerServiceImpl.send(topicStartLiveInfo, JSONObject.valueToString(param));
		log.info("推送ID"+result.getMsgId()+"推送结果状态："+result.getSendStatus());
		}catch(Exception e){
			log.error("直播消息推送失败");
			e.printStackTrace();
			return;
		}
	}




	
}
