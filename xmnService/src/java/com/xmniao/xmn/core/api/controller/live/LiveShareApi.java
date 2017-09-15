package com.xmniao.xmn.core.api.controller.live;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.xmniao.xmn.core.util.StrUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.live.LiveShareRequest;
import com.xmniao.xmn.core.live.dao.LiveUserDao;
import com.xmniao.xmn.core.live.entity.LiverInfo;
import com.xmniao.xmn.core.live.service.AnchorViewerMemberRankService;
import com.xmniao.xmn.core.live.service.PersonalCenterService;
import com.xmniao.xmn.core.util.PropertiesUtil;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

/**
 * 
*    
* 项目名称：xmnService_3.1.2_zb   
* 类名称：LiveShareApi   
* 类描述：   直播分享
* 创建人：yezhiyong   
* 创建时间：2016年8月30日 下午1:53:21   
* @version    
*
 */
@Controller
@RequestMapping("/live")
public class LiveShareApi implements BaseVControlInf {

	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(LiveShareApi.class);

	/**
	 * 注入验证
	 */
	@Autowired
	private Validator validator;

	/**
	 * 注入缓存
	 */
	@Autowired
	private SessionTokenService sessionTokenService;

	/**
	 * 注入文件头
	 */
	@Autowired
	private String fileUrl;
	
	/**
	 * 注入liveUserDao
	 */
	@Autowired
	private LiveUserDao liveUserDao;
	
	/**
	 * 注入appLoadUrlDao
	 */
//	@Autowired
//	private AppLoadUrlDao appLoadUrlDao;
	
	/**
	 * 注入anchorLiveRecordDao
	 */
//	@Autowired
//	private AnchorLiveRecordDao anchorLiveRecordDao;
	
	/**
	 * 注入stringRedisTemplate
	 */
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	@Autowired
	private AnchorViewerMemberRankService  anchorviewermemberrankService;
	
	@Autowired
	private PersonalCenterService personalCenterService;

	/**
	 * 注入本地服务地址
	 */
	@Autowired
	private String localDomain;
	
	/**
	 * 注入propertiesUtil
	 */
	@Autowired
	private PropertiesUtil propertiesUtil;

	@RequestMapping(value = "/liveShare", method = { RequestMethod.POST,RequestMethod.GET }, produces = { "application/json;charset=utf-8" })
	@ResponseBody
	public Object liveShare(LiveShareRequest liveShareRequest) {
		//日志
		log.info("liveShareRequest data : " + liveShareRequest.toString());
		//验证
		List<ConstraintViolation> result = validator.validate(liveShareRequest);
		if (result.size() > 0 && result != null) {
			log.info("提交的数据有问题" + result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR, "提交的数据有问题");
		}
		
		return versionControl(liveShareRequest.getApiversion(), liveShareRequest);
	}
	
	@Override
	public Object versionControl(int v, Object object) {
		switch(v){
		case 1:
			return versionOne(object);
			default :
			return new BaseResponse(ResponseCode.ERRORAPIV,"版本号不正确,请重新下载客户端");
		}
	}

	/**
	 * 
	* @Title: getLiveShare
	* @Description: 直播分享
	* @return Object    返回类型
	* @throws
	 */
	private Object versionOne(Object object) {
		LiveShareRequest liveShareRequest = (LiveShareRequest) object;
		
		//分享标题
		String shareTitle = "";
		//分享内容
		String shareText = "";
		//分享后直播间显示的分享语
		String liveRoomShareText = "";
		
		try {
			String uid = null;
			//判断分享者是否登录,登录则给其增加分享经验
			if (liveShareRequest.getSessiontoken() != null && StringUtils.isNotEmpty(liveShareRequest.getSessiontoken())) {
				uid = sessionTokenService.getStringForValue(liveShareRequest.getSessiontoken()) + "";
			}
			
			//结果集
			Map<Object, Object> resultMap = new HashMap<Object, Object>();
			
			Map<Object, Object> liverMap = new HashMap<>();
			if (uid != null && StringUtils.isNotEmpty(uid) && !"null".equals(uid)) {
				//查询分享用户信息
				liverMap = liveUserDao.queryLiverInfoByUid(Integer.parseInt(uid));
				if (liverMap == null) {
					return new BaseResponse(ResponseCode.FAILURE, "查无此直播用户信息");
				}
			}
			
			//查询直播记录信息
			Map<Object, Object> liveRecordMap = liveUserDao.queryAnchorByLiveRecordId(liveShareRequest.getLiveRecordId());
			if (liveRecordMap == null) {
				return new BaseResponse(ResponseCode.FAILURE, "查无此直播记录信息");
			}
			
			//直播类型
			Integer zhiboType = Integer.parseInt(liveRecordMap.get("zhibo_type").toString());
			
			//获取直播id，根据主播id查询直播的信息，获取直播性别
			Integer anchorId = Integer.parseInt(liveRecordMap.get("anchor_id").toString());
			LiverInfo anchorInfo = liveUserDao.queryAnchorInfoByAnchorId(anchorId);
			
			Integer sex = anchorInfo.getSex() == null ? 0 : anchorInfo.getSex();
			/*
			Integer readViewerCount = 0;
			if (zhiboType == 3) {
				//查询观看直播的观看人数
				readViewerCount = anchorLiveRecordDao.queryReadViewerCountByLiveRecordId(liveShareRequest.getLiveRecordId());
				
			}

			*/
			String avatar = liveRecordMap.get("avatar") == null ? null : liveRecordMap.get("avatar").toString();
			//分享logo
			String shareLogo = avatar == null ? "" : fileUrl + avatar;
		
			
			/*
			//判断是否是主播分享
			boolean isAnchor = liverMap.get("id").toString().equals(liveRecordMap.get("anchor_id").toString());
			
			if (isAnchor && (zhiboType == 1 || zhiboType == 5)) {
				//正在直播,主播分享
				shareTitle = "我正在寻蜜鸟直播，快来围观！";//分享标题
				shareText = liveRecordMap.get("nname").toString() + "在寻蜜鸟直播，有" + liveRecordMap.get("view_count").toString() + "人在观看!";//分享内容
				
			}else if (isAnchor && zhiboType == 3) {
				//回放,主播分享
				shareTitle = "我在寻蜜鸟发布了直播，这是我的直播片段，快来围观!";//分享标题
				shareText ="这个视频有" + liveRecordMap.get("view_count").toString() + "人看过,点击查看详情";//分享内容
				
			}else if (!isAnchor && (zhiboType == 1 || zhiboType == 5)) {
				//正在直播,观众分享
				shareTitle = "我在寻蜜鸟发现了一个好看的直播，快来围观吧！";//分享标题
				shareText = liveRecordMap.get("nname").toString() + "在寻蜜鸟直播，有" + liveRecordMap.get("view_count").toString() + "人在观看!";//分享内容
				
			}else if (!isAnchor && zhiboType == 3) {
				//回放,观众分享
				shareTitle = "我在寻蜜鸟发现一个好看的视频，快来围观吧！";//分享标题
				shareText = "这个视频有" + liveRecordMap.get("view_count").toString() + "人看过,点击查看详情";//分享内容
				
			}
			*/
			
			//配置文件中读取的分享标题json数组
			JSONArray shareTitleArr = null;
			
			//配置文件中读取分享语 json数组
			JSONArray shareTextArr = null;
			
			//根据主播性别，获取不同的分享语
			switch (sex) {
			case 1: //男 
				shareTitleArr = JSONObject.parseArray(propertiesUtil.getValue("live_shareTitle_man", "conf_live.properties"));
				shareTextArr = JSONObject.parseArray(propertiesUtil.getValue("live_shareText_man", "conf_live.properties"));
				break;
			case 2: //女
				shareTitleArr = JSONObject.parseArray(propertiesUtil.getValue("live_shareTitle_woman", "conf_live.properties"));
				shareTextArr = JSONObject.parseArray(propertiesUtil.getValue("live_shareText_woman", "conf_live.properties"));
				break;
			default:
				shareTitleArr = JSONObject.parseArray(propertiesUtil.getValue("live_shareTitle_woman", "conf_live.properties"));
				shareTextArr = JSONObject.parseArray(propertiesUtil.getValue("live_shareText_woman", "conf_live.properties"));
				break;//女
//				return new BaseResponse(ResponseCode.FAILURE,"获取主播性别失败");
			}
			//随机获取分享标题
			int titleIndex = new Random().nextInt(shareTitleArr.size());
			shareTitle += shareTitleArr.getJSONObject(titleIndex).getString("title");
			
			//随机获取分享语
			int textIndex = new Random().nextInt(shareTextArr.size());
			shareText = shareTextArr.getJSONObject(textIndex).getString("title");
			
			//随机获取分享后 直播间的分享语
			JSONArray liveRoomShareTextArr = JSONObject.parseArray(propertiesUtil.getValue("liveRoom_shareText", "conf_live.properties"));
			int liveRoomShareTextIndex = new Random().nextInt(liveRoomShareTextArr.size());
			liveRoomShareText = liveRoomShareTextArr.getJSONObject(liveRoomShareTextIndex).getString("text");
			
			//替换分享语 和 分享标题中的  xxx-主播昵称  和 yyy-分享用户昵称
			shareTitle = shareTitle.replaceAll("xxx", liveRecordMap.get("nname").toString());
			shareText = shareText.replaceAll("xxx", liveRecordMap.get("nname").toString());
			//替换分享语中的 n --> 当前观看直播人数 viewCount
			shareText = shareText.replaceAll("n", liveRecordMap.get("view_count").toString());
			
			
			/*
			//分享标题:随机抽取5个标题的其中一个
			int random = new Random().nextInt(5) + 1;
			JSONArray jsonArr = JSONObject.parseArray(propertiesUtil.getValue("live_wechat_share_title_list", "conf_live.properties"));
			//遍历所有微信直播分享标题,随机抽取
			for (int i = 0; i < jsonArr.size(); i++) {
				int type = Integer.parseInt(JSONObject.parseObject(jsonArr.get(i).toString()).get("type").toString());
				if (type == random) {
					shareTitle = liveRecordMap.get("nname").toString() + JSONObject.parseObject(jsonArr.get(i).toString()).get("title").toString();
					break;
				}
			}
			
			//分享内容
			shareText =liveRecordMap.get("nname").toString() + propertiesUtil.getValue("live_wechat_share_text", "conf_live.properties");
			*/
			
			
			
			//分享链接
			if (liveRecordMap.get("vedio_url") == null && zhiboType == 1) {
				resultMap.put("shareUrl", localDomain + "/live/liveUpLoad");
			}else {
				resultMap.put("shareUrl", localDomain + "/live/shareInit?zhiboRecordId="+liveShareRequest.getLiveRecordId()+"&longitude="+liveShareRequest.getLongitude()+"&latitude="+liveShareRequest.getLatitude()+"&zhiboType="+zhiboType);
			}
			
			log.info("share content=" + resultMap.toString());
			
			//如果是登录用户,则为其增加分享经验
			if (liverMap != null && liverMap.size() > 0) {
				try {
					//增加分享经验
					this.addShareExp(Integer.parseInt(uid),liveShareRequest.getLiveRecordId());
				} catch (Exception e) {
					log.info("增加分享经验失败,分享用户uid=" + uid + ",错误信息如下:" + e.getMessage());
				}
				String nname = StrUtils.standardName(liverMap.get("nname"), liverMap.get("phone"));
				//用户登录，1.标题前加上来自xxx的分享  2.将分享语中 yyy--> 替换为用户昵称  3.分享后直播间分享语前加用户名
				shareTitle = "来自" + nname + "的分享：" + shareTitle;
				shareTitle = shareTitle.replaceAll("yyy", nname);
				shareText = shareText.replaceAll("yyy", nname);
			}else {//没登录， 将分享标题中的yyy --> 我
				shareTitle = shareTitle.replaceAll("yyy", "我");
				shareText = shareText.replaceAll("yyy", "我");
			}
			
			//分享标题
			resultMap.put("shareTitle", shareTitle);
			//分享内容
			resultMap.put("shareText", shareText);
			//分享logo
			resultMap.put("shareLogo", shareLogo);
			//分享后，直播间分享语
			resultMap.put("liveRoomShareText", liveRoomShareText);
			
			//直播ID
			resultMap.put("zhiboRecordId", liveShareRequest.getLiveRecordId());
			
			//响应
			MapResponse response = new MapResponse(ResponseCode.SUCCESS, "分享成功");
			response.setResponse(resultMap);
			return response;
			
		} catch (Exception e) {
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE, "分享失败");
		}
	}
	
	/**
	 * 分享直播得经验
	 * @param uid
	 * @throws Exception 
	 */
	public void addShareExp(Integer uid,int liveRecordId) throws Exception {
		log.info("开始添加分享经验=================================");
		if (stringRedisTemplate.hasKey("live_share_" + uid)) {
			Integer exp = Integer.parseInt(stringRedisTemplate.opsForValue().get("live_share_" + uid).toString());
			log.info("查询redis中缓存的分享经验值:exp:" + exp + "，uid:" + uid);
			//增加分享经验
			if (exp < 15) {
				this.addLiveExp(uid,exp,liveRecordId);
			}
		}else {
			this.addLiveExp(uid, 0,liveRecordId);
		}
	}
	
	/**
	 * 添加分享经验5
	 * @param uid
	 * @throws Exception 
	 */
	public void addLiveExp(Integer uid,Integer exp,int liveRecordId) throws Exception {
		if (stringRedisTemplate.hasKey("live_"+uid)) {
			String currentExp = stringRedisTemplate.opsForHash().entries("live_"+uid).get("current_expe").toString();
			Integer NewExpr = Integer.parseInt(currentExp) + 5;
			Map<Object, Object> map = stringRedisTemplate.opsForHash().entries("live_"+uid);
			map.put("current_expe", NewExpr);
			map.put("live_record_id", liveRecordId);
			map.put("get_experience_type", "4");
			//增加分享获取经验记录
			anchorviewermemberrankService.insertExpericeRecord(map, 5);
			stringRedisTemplate.opsForHash().putAll("live_"+uid, map);
			log.info("分享经验成功，添加观看redis中5经验");
			
			if (exp == 0) {
				//第一次分享加5经验
				stringRedisTemplate.opsForValue().set("live_share_" + uid, 5 + "");
				//第一次分享经验缓存24小时
				stringRedisTemplate.expire("live_share_" + uid, 24*3600, TimeUnit.SECONDS);
			}else{
				//获取增加分享经验剩余时间
				Long remaining=stringRedisTemplate.getExpire("live_share_" + uid);
				//分享加经验(不是第一次)
				stringRedisTemplate.opsForValue().set("live_share_" + uid,exp + 5 + "");
				//重新缓存增加分享经验剩余时间
				stringRedisTemplate.expire("live_share_" + uid, remaining, TimeUnit.SECONDS);
			}
		}else {
			Map<Object, Object> map = new HashMap<Object, Object>();
			
			Map<Object, Object> personMap=personalCenterService.queryLiverPersonByUid(uid);
			if(personMap==null || personMap.size()<=0){
				log.error("未获取到用户基本信息，分享获取经验失败");
				return;
			}
			map.put("uid", uid);
			map.put("currentExpe", 5);
			map.put("id", personMap.get("anchorid"));//用户id
			map.put("utype", personMap.get("utype"));//直播用户类型： 1 主播 2 普通用户
			map.put("live_record_id", liveRecordId);
			map.put("get_experience_type", "4");
			//增加分享获取经验记录
			anchorviewermemberrankService.insertExpericeRecord(map, 5);
			liveUserDao.modifyLiverByUid(map);
			if (exp == 0) {
				//第一次分享加5经验
				stringRedisTemplate.opsForValue().set("live_share_" + uid, 5 + "");
				//第一次分享经验缓存24小时
				stringRedisTemplate.expire("live_share_" + uid, 24*3600, TimeUnit.SECONDS);
			}else{
				//获取增加分享经验剩余时间
				Long remaining=stringRedisTemplate.getExpire("live_share_" + uid);
				//分享加经验(不是第一次)
				stringRedisTemplate.opsForValue().set("live_share_" + uid,exp + 5 + "");
				//重新缓存增加分享经验剩余时间
				stringRedisTemplate.expire("live_share_" + uid, remaining, TimeUnit.SECONDS);
			}
			log.info("分享经验成功，添加数据库5经验");
		}
	}
	
	/**
	 * 
	* @Title: liveUpLoad
	* @Description: 跳转直播app下载页
	* @return String    返回类型
	* @throws
	 */
	@RequestMapping(value = "/liveUpLoad", method = RequestMethod.GET)
	public String liveUpLoad() {
		return "live/liveupload";
	}

}
