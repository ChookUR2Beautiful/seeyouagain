package com.xmniao.xmn.core.live.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.live.*;
import com.xmniao.xmn.core.live.dao.LiveAnchorVideoDao;
import com.xmniao.xmn.core.live.dao.LiveUserDao;
import com.xmniao.xmn.core.live.entity.LiverInfo;
import com.xmniao.xmn.core.util.PropertiesUtil;
import com.xmniao.xmn.core.util.RedisGlobalLockUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class LiveShareVideoService {

	/**
	 * 日志
	 */
	private static final Logger log = LoggerFactory.getLogger(LiveShareVideoService.class);

	@Autowired
	private String fileUrl;
	@Autowired
	private String localDomain;
	@Autowired
	private PropertiesUtil propertiesUtil;
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	@Autowired
	private AnchorViewerMemberRankService  anchorviewermemberrankService;
	@Autowired
	private PersonalCenterService personalCenterService;
	@Autowired
	private LiveAnchorVideoDao liveAnchorVideoDao;
	@Autowired
	private LiveUserDao liveUserDao;
	@Autowired
	private SessionTokenService sessionTokenService;

	public Object getShareInfo(LiveShareVideoRequest liveShareVideoRequest) {
		try {
			Integer id = liveShareVideoRequest.getId();
			String shareTitle = "", shareText = "", liveRoomShareText = "", shareUrl = "";
			// 查询精彩视频记录
			Map<Object, Object> videoMap = liveAnchorVideoDao.getAnchorVideoById(id);
			if (videoMap == null) {
				return new BaseResponse(ResponseCode.FAILURE, "分享失败,不存在分享视频。id:" + String.valueOf(id));
			}
			String uid = null;
			//判断分享者是否登录,登录则给其增加分享经验
			if (liveShareVideoRequest.getSessiontoken() != null && StringUtils.isNotEmpty(liveShareVideoRequest.getSessiontoken())) {
				uid = sessionTokenService.getStringForValue(liveShareVideoRequest.getSessiontoken()) + "";
			}

			Map<Object, Object> liverMap = new HashMap<>();
			if (uid != null && StringUtils.isNotEmpty(uid) && !"null".equals(uid)) {
				//查询分享用户信息
				liverMap = liveUserDao.queryLiverInfoByUid(Integer.parseInt(uid));
				if (liverMap == null) {
					return new BaseResponse(ResponseCode.FAILURE, "查无此直播用户信息");
				}
				//存在用户信息，分享标题 和 直播间分享语 前加上 用户昵称
				liveRoomShareText = liverMap.get("nname").toString();
			}


			// 查询用户信息
			//获取直播id，根据主播id查询直播的信息，获取直播性别
			Integer anchorId = Integer.parseInt(videoMap.get("anchor_id").toString());
			LiverInfo anchorInfo = liveUserDao.queryAnchorInfoByAnchorId(anchorId);
			// 逻辑
			Integer sex =anchorInfo.getSex();
			//分享logo
			String shareLogo = fileUrl + anchorInfo.getAvatar();
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
					break;
//					return new BaseResponse(ResponseCode.FAILURE,"获取主播性别失败");
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
			liveRoomShareText += liveRoomShareTextArr.getJSONObject(liveRoomShareTextIndex).getString("text");

			//替换分享语 和 分享标题中的  xxx-主播昵称  和 yyy-分享用户昵称
			shareTitle = shareTitle.replaceAll("xxx", anchorInfo.getNname());
			shareText = shareText.replaceAll("xxx", anchorInfo.getNname());

			String videoUrl = (String) videoMap.get("video_url");
			//分享链接
			if (videoUrl == null || videoUrl.trim().equals("")) {
				shareUrl = localDomain + "/live/liveUpLoad";
			}else {
				shareUrl = localDomain + "/live/shareVideoInit?id=" + liveShareVideoRequest.getId();
			}

			//如果是登录用户,则为其增加分享经验
			if (liverMap != null && liverMap.size() > 0) {
				try {
					//增加分享经验
					this.addShareExp(Integer.parseInt(uid));
				} catch (Exception e) {
					log.info("增加分享经验失败,分享用户uid=" + uid + ",错误信息如下:" + e.getMessage());
				}
				//用户登录，1.标题前加上来自xxx的分享  2.将分享语中 yyy--> 替换为用户昵称  3.分享后直播间分享语前加用户名
				shareTitle = "来自"+liverMap.get("nname")+"的分享：" + shareTitle;
				shareTitle = shareTitle.replaceAll("yyy", liverMap.get("nname").toString());
				shareText = shareText.replaceAll("yyy", liverMap.get("nname").toString());
			}else {//没登了， 将表标题中的yyy --> 我
				shareTitle = shareTitle.replaceAll("yyy", "我");
				shareText = shareText.replaceAll("yyy", "我");
			}

			// 拼装
			Map<Object, Object> resultMap = new HashMap<Object, Object>();
			resultMap.put("shareUrl", shareUrl);
			resultMap.put("shareTitle", shareTitle);
			resultMap.put("shareText", shareText);
			resultMap.put("shareLogo", shareLogo);
			resultMap.put("videoId", id);

			MapResponse response = new MapResponse(ResponseCode.SUCCESS, "分享成功");
			response.setResponse(resultMap);
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE, "分享失败");
		}
	}


	public void addShareExp(Integer uid) throws Exception {
		if (stringRedisTemplate.hasKey("live_share_" + uid)) {
			Integer exp = Integer.parseInt(stringRedisTemplate.opsForValue().get("live_share_" + uid).toString());
			log.info("查询redis中缓存的分享经验值:exp:" + exp + "，uid:" + uid);
			//增加分享经验
			if (exp < 15) {
				this.addLiveExp(uid,exp);
			}
		}else {
			this.addLiveExp(uid, 0);
		}
	}

	public void addLiveExp(Integer uid,Integer exp) throws Exception {
		if (stringRedisTemplate.hasKey("live_"+uid)) {
			String currentExp = stringRedisTemplate.opsForHash().entries("live_"+uid).get("current_expe").toString();
			Integer NewExpr = Integer.parseInt(currentExp) + 5;
			Map<Object, Object> map = stringRedisTemplate.opsForHash().entries("live_"+uid);
			map.put("current_expe", NewExpr);
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



	
}
