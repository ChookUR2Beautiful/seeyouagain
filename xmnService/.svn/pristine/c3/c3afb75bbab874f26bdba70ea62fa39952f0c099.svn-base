package com.xmniao.xmn.core.api.controller.live;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.xmniao.xmn.core.base.BaseRequest;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.live.dao.AnchorLiveRecordDao;
import com.xmniao.xmn.core.live.dao.LiveUserDao;
import com.xmniao.xmn.core.live.entity.LiveRecordInfo;
import com.xmniao.xmn.core.util.PropertiesUtil;
import com.xmniao.xmn.core.util.TLSUtil;

/**
 * 
*    
* 项目名称：xmnService_3.1.2_zb   
* 类名称：ReLoginDealApi   
* 类描述：   直播过程中被挤下线后发送群组信息
* 创建人：yezhiyong   
* 创建时间：2016年9月17日 下午4:16:12   
* @version    
*
 */
@Controller
@RequestMapping("/live/reLogin")
public class ReLoginDealApi {
	
	/**
	 * 日志
	 */
	private static final Logger log = LoggerFactory.getLogger(ReLoginDealApi.class);
	
	/**
	 * 注入文件地址
	 */
	@Autowired
	private String fileUrl;
	
	/**
	 * 注入验证
	 */
	@Autowired
	private Validator validator;
	
	/**
	 * 注入propertiesUtil
	 */
	@Autowired
	private PropertiesUtil propertiesUtil;
	
	/**
	 * 注入anchorLiveRecordDao
	 */
	@Autowired
	private AnchorLiveRecordDao anchorLiveRecordDao;
	
	/**
	 * 注入liveUserDao
	 */
	@Autowired
	private LiveUserDao liveUserDao;
	
	/**
	 * 注入sessionTokenService
	 */
	@Autowired
	private SessionTokenService sessionTokenService;
	
	/**
	 * 注入stringRedisTemplate
	 */
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	/**
	 * 
	* @Title: sendGroupMsg
	* @Description: 直播过程中被挤下线后发送群组信息
	* @return Object    返回类型
	* @throws
	 */
	@RequestMapping(value="/sendGroupMsg",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public Object sendGroupMsg(BaseRequest baseRequest){
		//日志
		log.info("baseRequest data:" + baseRequest.toString());
		//验证
		List<ConstraintViolation> result = validator.validate(baseRequest);
		if(result != null && result.size()>0){
			log.info("提交的数据有问题"+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据不正确!");
		}
		
		//验证token
		String uid = sessionTokenService.getStringForValue(baseRequest.getSessiontoken()) + "";
		if (StringUtils.isEmpty(uid) || "null".equalsIgnoreCase(uid)) {
			return new BaseResponse(ResponseCode.TOKENERR, "token已失效");
		}
		
		try{
			//从redis中,获取管理员签名
			String adminSig = stringRedisTemplate.opsForValue().get("adminSig");
			
			String sdkAppid = propertiesUtil.getValue("SdkAppid", "conf_live.properties");
			String identifier = propertiesUtil.getValue("identifier", "conf_live.properties");
			
			if (adminSig == null) {
				//调用tls,获取管理员tls的sig
				adminSig = TLSUtil.getTLSSig(sdkAppid, identifier);
				stringRedisTemplate.opsForValue().set("adminSig", adminSig);
				stringRedisTemplate.expire("adminSig", 180, TimeUnit.DAYS);
			}
			
			//生成请求参数paramMap
			Map<Object,Object> paramMap=new HashMap<Object,Object>();
			paramMap.put("tlsSig", adminSig);
			paramMap.put("sdkappid", sdkAppid);
			paramMap.put("identifier", identifier);
			
			//查询用户直播信息
			Map<Object, Object> liverMap = liveUserDao.queryLiverInfoByUid(Integer.parseInt(uid));
			
			if (liverMap == null) {
				return new BaseResponse(ResponseCode.FAILURE, "查无此直播用户信息");
			}
			
			//查询正在观看直播记录信息
			Map<Object, Object> liveViewRecord = anchorLiveRecordDao.queryCurrentLiveViewRecordByUid(Integer.parseInt(uid));
			
			if (liveViewRecord == null && liverMap.get("group_id") != null) {
				//查看是否有正在直播记录,有则为主播抢登
				Map<Object, Object> map = new HashMap<>();
				map.put("anchorId", liverMap.get("id").toString());
				map.put("zhiboType", 1);
				LiveRecordInfo liveRecordInfo = anchorLiveRecordDao.queryLiveRecordIdByAnchorId(paramMap);
				
				if (liveRecordInfo == null) {
					return new BaseResponse(ResponseCode.FAILURE, "未知错误,请联系管理员");
				}
				paramMap.put("GroupId", liverMap.get("group_id").toString());
				paramMap.put("From_Account", liverMap.get("phone"));
				
			}else if(liveViewRecord != null && liveViewRecord.get("anchor_id") != null){
				//查询主播信息
				Map<Object, Object> anchorMap = liveUserDao.queryLiverInfoById(Integer.parseInt(liveViewRecord.get("anchor_id").toString()));
				if (anchorMap == null) {
					return new BaseResponse(ResponseCode.FAILURE, "查无主播信息");
				}
				//群组号
				paramMap.put("GroupId", anchorMap.get("group_id").toString());
				paramMap.put("From_Account", anchorMap.get("phone"));
				
			}else {
				return new BaseResponse(ResponseCode.FAILURE, "未知错误,请联系管理员");
				
			}
			
			log.info("抢登发送IM消息GroupId:" + paramMap.get("GroupId").toString() + ",From_Account=" + paramMap.get("From_Account").toString());
			
			paramMap.put("MsgType", "TIMCustomElem");
			Map<Object,Object> contentMap = new HashMap<Object,Object>();
			
			//自定义data类型参数
			String iconUrl = liverMap.get("avatar") == null?"":fileUrl+liverMap.get("avatar").toString();
			Map<Object,Object> dataMap = new HashMap<Object,Object>();
			dataMap.put("uid", uid);//用户id
			dataMap.put("iconUrl", iconUrl);//头像地址
			dataMap.put("dj", Integer.parseInt(liverMap.get("rank_no").toString()));//当前等级
			dataMap.put("loginAccount", liverMap.get("phone"));//用户名称
			dataMap.put("type", 7);
			contentMap.put("Data", JSONObject.toJSONString(dataMap));
			paramMap.put("MsgContent", contentMap);
			
			//发送群组聊天信息
			boolean sendResult = TLSUtil.sendGroupMsg(paramMap);
			if(!sendResult){
				log.error("抢登发送群组信息失败!");
				return new BaseResponse(ResponseCode.FAILURE, "抢登发送群组信息失败!");
			}
			log.info("抢登发送群组信息失败!");
			
			return new BaseResponse(ResponseCode.SUCCESS, "抢登发送群组信息成功!");
			
		}catch(Exception e){
			e.printStackTrace();
			log.error("抢登发送群组信息失败!",e.toString());
			return new BaseResponse(ResponseCode.FAILURE, "抢登发送群组信息失败!");
		}
		
	}

}
