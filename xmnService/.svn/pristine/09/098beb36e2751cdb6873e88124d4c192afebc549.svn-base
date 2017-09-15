package com.xmniao.xmn.core.live.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import com.xmniao.xmn.core.common.ObjResponse;
import com.xmniao.xmn.core.common.request.live.*;
import com.xmniao.xmn.core.kscloud.entity.KSLiveEntity;
import com.xmniao.xmn.core.kscloud.service.KSCloudService;
import com.xmniao.xmn.core.util.*;
import org.apache.commons.lang.StringUtils;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseRequest;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.Constant;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.CreateGroupRequest;
import com.xmniao.xmn.core.common.request.PhoneRequest;
import com.xmniao.xmn.core.common.request.TlsSigRequest;
import com.xmniao.xmn.core.live.dao.AnchorLiveRecordDao;
import com.xmniao.xmn.core.live.dao.AnchorManagerDao;
import com.xmniao.xmn.core.live.dao.LiveRobotDao;
import com.xmniao.xmn.core.live.dao.LiveUserDao;
import com.xmniao.xmn.core.live.dao.PersonalCenterDao;
import com.xmniao.xmn.core.live.entity.LiveFansRank;
import com.xmniao.xmn.core.live.entity.LiveRecordInfo;
import com.xmniao.xmn.core.live.entity.LiverInfo;
import com.xmniao.xmn.core.thrift.LiveWalletService;
import com.xmniao.xmn.core.thrift.LiveWalletService.Client;
import com.xmniao.xmn.core.thrift.ResponseData;
import com.xmniao.xmn.core.thrift.client.proxy.ThriftClientProxy;
import com.xmniao.xmn.core.verification.dao.UrsDao;
import com.xmniao.xmn.core.verification.dao.UrsInfoDao;
import com.xmniao.xmn.core.verification.entity.Urs;
import com.xmniao.xmn.core.verification.entity.UrsInfo;

/**
 * 
*    
* 项目名称：xmnService_3.1.2_zb   
* 类名称：LiveUserService   
* 类描述：   直播用户Service
* 创建人：yezhiyong   
* 创建时间：2016年8月10日 下午5:59:35   
* @version    
*
 */
@Service
public class LiveUserService {
	
	/**
	 * 日志
	 */
	private static final Logger log = LoggerFactory.getLogger(LiveUserService.class);
	
	/**
	 * 注入ursDao
	 */
	@Autowired
	private UrsDao ursDao;
	
	/**
	 * 注入ursInfoDao
	 */
	@Autowired
	private UrsInfoDao ursInfoDao;
	
	/**
	 * 注入liveUserDao
	 */
	@Autowired
	private LiveUserDao liveUserDao;
	
	/**
	 * 支付服务接口
	 */
	@Resource(name = "liveWalletServiceClient")
	private ThriftClientProxy liveWalletServiceClient;
	
	/**
	 * 注入文件地址
	 */
	@Autowired
	private String fileUrl;
	
	/**
	 * 注入redis缓存
	 */
	@Autowired
	private SessionTokenService sessionTokenService;
	
	/**
	 * 加载属性文件内容
	 */
	@Autowired
	private PropertiesUtil propertiesUtil;
	
	/**
	 * 注入liveRobotDao
	 */
	@Autowired
	private LiveRobotDao liveRobotDao;
	
	/**
	 * 注入thriftUtil
	 */
	@Autowired
	private ThriftUtil thriftUtil;
	
	/**
	 * 注入anchorManagerDao
	 */
	@Autowired
	private AnchorManagerDao anchorManagerDao;
	
	/**
	 * 注入anchorLiveRecordDao
	 */
	@Autowired
	private AnchorLiveRecordDao anchorLiveRecordDao;
	
	/**
	 * 注入stringRedisTemplate
	 */
	@Autowired
	private PersonalCenterDao personalCenterDao;
	
	/**
	 * 注入stringRedisTemplate
	 */
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	@Autowired
	private KSCloudService ksCloudService;
	
	/**
	 * 
	* @Title: getTlsSig
	* @Description: 获取sig接口
	* @return Object    返回类型
	* @author
	* @throws
	 */
	public Object getTlsSig(TlsSigRequest tlsSigRequest) {
		//验证token
		String uid = sessionTokenService.getStringForValue(tlsSigRequest.getSessiontoken())+"";
		if (StringUtils.isEmpty(uid)) {
			return new BaseResponse(ResponseCode.TOKENERR, "token已经失效,请重新登录");
		}
		try {
			String sdkAppid = tlsSigRequest.getSdkAppid();
			String identifier = tlsSigRequest.getIdentifier();
			String locationSdkAppid = propertiesUtil.getValue("SdkAppid", "conf_live.properties");
			String locationIdentifier = propertiesUtil.getValue("identifier", "conf_live.properties");
			
			if (!sdkAppid.equals(locationSdkAppid) && !identifier.equals(locationIdentifier)) {
				return new BaseResponse(ResponseCode.FAILURE, "未知错误,请联系管理员");
			}
			
			//创建腾讯云账号信息
			return this.createTlsUser(uid);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new BaseResponse(ResponseCode.TLS_SIG_ERROR, "获取tls的sig失败");
		}
	}

	/**
	 * 
	* @Title: createTlsUser
	* @Description: 创建腾讯云账号信息
	* @return Object    返回类型
	* @throws
	 */
	public Object createTlsUser(String uid) throws Exception {
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
		
		//查询用户信息
		Urs urs = ursDao.queryUrsByUid(Integer.parseInt(uid));
		UrsInfo ursInfo = ursInfoDao.queryUrsInfoByUid(Integer.parseInt(uid));
		
		if (urs ==null || ursInfo == null) {
			return new BaseResponse(ResponseCode.FAILURE, "查无此用户信息,请联系客服");
		}
		
		//用户账号
		String account = urs.getUname();
		if (urs.getUname() == null) {
			if (urs.getOpenid() != null) {
				//如果没有uname，采用openid注册
				account = urs.getOpenid();
			}else {
				//没有手机号码，并且没有oppenid
				account = urs.getUid() + "";
			}
		}
		//查询直播观众信息
		Map<Object, Object> liverMap = liveUserDao.queryLiverInfoByUid(Integer.parseInt(uid));
		
		//获取用户tls的sig
		String tlsUserKey = "tls_user_" + urs.getUid();
		String tlsSig = stringRedisTemplate.opsForValue().get(tlsUserKey);
		if (tlsSig == null) {
			//调用tls,获取用户tls的sig
			tlsSig = TLSUtil.getTLSSig(sdkAppid, account);
			stringRedisTemplate.opsForValue().set(tlsUserKey, tlsSig);
			stringRedisTemplate.expire(tlsUserKey, 180, TimeUnit.DAYS);
		}else {
			//防止观众 报名成主播   重新注册一次
			if (liverMap!=null && liverMap.size()>0 && liverMap.get("sign_type")!=null && liverMap.get("sign_type").toString().equals("4")) {
				stringRedisTemplate.delete(tlsUserKey);
				
				//调用tls,获取用户tls的sig
				tlsSig = TLSUtil.getTLSSig(sdkAppid, account);
				stringRedisTemplate.opsForValue().set(tlsUserKey, tlsSig);
				stringRedisTemplate.expire(tlsUserKey, 180, TimeUnit.DAYS);
				
			}
		}
		
		//缓存注册redis key
		String redisKey = "live_register_" + uid;
		log.info("redisKey:" + redisKey);
		if (liverMap == null || liverMap.size() <= 0) {
			Long resultNum = stringRedisTemplate.opsForValue().increment(redisKey, 1);
			log.info("redisKey:" + redisKey + ",resultNum:" + resultNum);
			if (resultNum > 1) {
				return new BaseResponse(ResponseCode.SUCCESS, "创建直播用户成功");
			}
		}
		
		//要删除注册rediskey
		try {
			Boolean isTlsRegister = true;
			
			//判断是否在腾讯云注册过
			if (liverMap !=null && liverMap.size()>0 && Integer.parseInt(liverMap.get("isTlsRegister").toString()) == 0) {
				Map<Object, Object> tlsMap = new HashMap<Object, Object>();
				tlsMap.put("sdkAppid", sdkAppid);
				tlsMap.put("identifier", identifier);
				tlsMap.put("tlsSig", adminSig);
				tlsMap.put("account",account);
				isTlsRegister = TLSUtil.getTlsUserInfo(tlsMap);
				
				if (isTlsRegister) {
					
					//设置redis key 为true   业务管理后台检测为true 更新缓存
					stringRedisTemplate.opsForValue().set(Constant.B_URS$CHANGED, "true");
					
					//修改腾讯
					tlsMap.clear();
					tlsMap.put("uid", Integer.parseInt(uid));
					tlsMap.put("isTlsRegister", 1);
					liveUserDao.modifyLiverByUid(tlsMap);
				}
				
			}
			
			if (liverMap == null || !isTlsRegister) {
				Map<Object, Object> paramMap = new HashMap<>();
				if (!isTlsRegister) {
					//修改直播用户表的腾讯注册状态
					paramMap.put("uid", Integer.parseInt(uid));
					paramMap.put("isTlsRegister", 1);
					liveUserDao.modifyLiverByUid(paramMap);
				}
				
				if (liverMap == null) {
					//首次登录直播
					paramMap.clear();
					//会员id
					paramMap.put("uid", Integer.parseInt(uid));
					if (account.length() == 11) {
						paramMap.put("phone", account);
					}else {
						paramMap.put("phone", null);
						
					}
					//直播用户类型: 普通用户'
					paramMap.put("utype", 2);
					//'是否注册腾讯云资料  0 没注册  1 已注册'
					paramMap.put("isTlsRegister", 1);
					//注册来源  0 app后台注册  1 php注册
					paramMap.put("registerSource", 0);
					//创建时间
					paramMap.put("createTime", DateUtil.format(new Date()));
					//更新时间
					paramMap.put("updateTime", DateUtil.format(new Date()));
					
					//会员关系链 规则:每一级用'',''隔开，包含自身，每一级均为11位uid的字符串，不足11位在uid前以''0''填充。如当前uid为112,其关系链为0000001000,0000002000,00000000112',
					StringBuffer sb = new StringBuffer();
					for (int i = 0; i < (11-uid.length()); i++) {
						sb.append(0);
					}
					String uidRelationChain = sb.append(uid).toString();
					paramMap.put("uidRelationChain", uidRelationChain);
					
					//会员昵称关系链  多个以逗号分隔，顺序与uid_relation_chain保持一致'
					if (null != urs.getNname() && !"".equals(urs.getNname())) {
						if (urs.getNname().indexOf(",")>=0) {//呢称有,需要去掉
							paramMap.put("uidRelationChainNname", urs.getNname().replace(",", ""));
						}else {
							paramMap.put("uidRelationChainNname", urs.getNname());
						}
					}else {
						paramMap.put("uidRelationChainNname", "");
					}
					
					//查询最低等级的粉丝等级数据
					Map<Object, Object> fansRankMap = liveUserDao.queryMinLiveFansRank();
					if (fansRankMap != null && fansRankMap.size() > 0) {
						//'粉丝等级： 1 普通 2 VIP 3 钻石'
						paramMap.put("rankNo", fansRankMap.get("rank_no")==null?1:fansRankMap.get("rank_no"));
						//粉丝等级名称
						paramMap.put("rankName", fansRankMap.get("rank_name")==null?"":fansRankMap.get("rank_name"));
						//粉丝等级 ID
						paramMap.put("rankId", fansRankMap.get("id"));
						
					}else {
						//查询不到最低等级，则存储默认
						paramMap.put("rankNo", 1);
						paramMap.put("rankName", "");
						paramMap.put("rankId", null);
					}
					
					try {
						//插入直播观众信息
						Integer insertLiverResult = liveUserDao.insertLiver(paramMap);
						
						if (insertLiverResult != 1) {
							return new BaseResponse(ResponseCode.FAILURE, "创建直播观众信息失败");
						}
						log.info("创建直播观众信息成功");
					} catch (Exception e) {
						e.printStackTrace();
						return new BaseResponse(ResponseCode.FAILURE, "创建直播观众信息失败");
					}
					
					//调用支付服务创建直播钱包
					LiveWalletService.Client client = null;
					log.info("调用支付服务创建直播钱包");
					try {
						log.info("获取调用支付服务连接");
						TMultiplexedProtocol tMultiplexedProtocol = thriftUtil.getProtocol("LiveWalletService");
						client = new Client(tMultiplexedProtocol);
						thriftUtil.openTransport();
						log.info("调用支付服务方法");
						ResponseData responseData =	client.addLiveWallet(uid);
						log.info("调用支付服务方法成功");
						if(responseData.getState()!=0){
							log.info("创建直播用户钱包失败,错误信息："+responseData.getMsg()+"，用户："+uid);
							return new BaseResponse(ResponseCode.FAILURE,"创建直播用户钱包失败");
						}
						log.info("创建直播用户钱包成功,用户："+uid);
					}catch(Exception e){
						log.info("创建直播用户钱包失败");
						e.printStackTrace();
						return new BaseResponse(ResponseCode.FAILURE,"创建直播用户钱包失败");
					}finally{
						thriftUtil.coloseTransport();
						stringRedisTemplate.delete(redisKey);
					}
				}
				
				//上传观众的腾讯云资料
				paramMap.clear();
				paramMap.put("sdkAppid", sdkAppid);
				paramMap.put("identifier", identifier);
				paramMap.put("account", account);
				paramMap.put("nickName", urs.getNname() == null?"":urs.getNname());
				paramMap.put("image", ursInfo.getAvatar() == null?"":fileUrl + ursInfo.getAvatar());
				paramMap.put("tlsSig", adminSig);
				paramMap.put("selfSignature", urs.getSign());
				
				//上传
				Object result = this.setTlsUserInfo(paramMap);
				if (result != null) {
					return result;
				}
					
			}
			
		} catch (Exception e1) {
			log.info("创建直播用户失败");
			e1.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE, "创建直播用户失败");
		}finally{
			//删除注册key
			stringRedisTemplate.delete(redisKey);
		}
		
		Map<Object, Object> resultMap = new HashMap<>();
		//签名
		resultMap.put("tlsSig", tlsSig);
		//默认房间编号
		resultMap.put("anchorRoomNo", 0);
		//群组id
		resultMap.put("groupId", "");
		//群组昵称
		resultMap.put("groupName", "");
		
		if (liverMap != null && Integer.parseInt(liverMap.get("utype").toString()) == 1 && liverMap.get("anchor_room_no") != null) {
			//房间编号
			resultMap.put("anchorRoomNo", liverMap.get("anchor_room_no").toString());
			//创建群组
			if (liverMap.get("group_id") == null || StringUtils.isEmpty(liverMap.get("group_id").toString())) {
				
				//上传主播的腾讯云资料
				Map<Object, Object> paramMap = new HashMap<>();
				paramMap.put("sdkAppid", sdkAppid);
				paramMap.put("identifier", identifier);
				paramMap.put("account", account);
				paramMap.put("nickName", urs.getNname() == null?"":urs.getNname());
				paramMap.put("image", ursInfo.getAvatar() == null?"":fileUrl + ursInfo.getAvatar());
				paramMap.put("tlsSig", adminSig);
				paramMap.put("selfSignature", urs.getSign() == null?"":urs.getSign());
				
				//上传
				Object result = this.setTlsUserInfo(paramMap);
				if (result != null) {
					return result;
				}
				
				//组装参数
				Map<Object, Object> groupMap = new HashMap<>();
				//应用id
				groupMap.put("sdkAppid", sdkAppid);
				//管理员账号
				groupMap.put("identifier", identifier);
				//群主id(主播账号)
				groupMap.put("account", account);
				//管理员签名
				groupMap.put("tlsSig", adminSig);
				//群名称
				groupMap.put("name", account);
				//群组形态
				groupMap.put("type", propertiesUtil.getValue("groupType", "conf_live.properties"));
				//自定义群组
				String groupId = propertiesUtil.getValue("groupPrefix", "conf_live.properties") + liverMap.get("anchor_room_no").toString();
				//群组id
				groupMap.put("groupId", groupId);
				
				try {
					boolean groupResult = TLSUtil.createGroup(groupMap);
					if (!groupResult) {
						return new BaseResponse(ResponseCode.TLS_CREATE_GROUP_ERROR, "创建腾讯云群组失败");
					}
				} catch (Exception e) {
					e.printStackTrace();
					return new BaseResponse(ResponseCode.TLS_CREATE_GROUP_ERROR, "创建腾讯群组失败");
				}
				
				//修改主播创建的腾讯云群组信息
				Map<Object, Object> anchorMap = new HashMap<>();
				anchorMap.put("uid", Integer.parseInt(uid));
				//群组id
				anchorMap.put("groupId", groupId);
				//群组昵称
				anchorMap.put("groupName", account);
				anchorMap.put("updateTime", DateUtil.format(new Date()));
				Integer modifyLiverResult= liveUserDao.modifyLiverByUid(anchorMap);
				if (modifyLiverResult != 1) {
					return new BaseResponse(ResponseCode.FAILURE,"修改主播创建腾讯云群组信息失败");
				}
				
				//群组id
				resultMap.put("groupId", groupId);
				//群组昵称
				resultMap.put("groupName", account);
				
			}else {
				//群组id
				resultMap.put("groupId", liverMap.get("group_id").toString());
				//群组昵称
				resultMap.put("groupName", account);
			}
		}
		
		//响应
		MapResponse response = new MapResponse(ResponseCode.SUCCESS, "获取tls的sig成功");
		response.setResponse(resultMap);
		return response;
	}
	
	/**
	 * @throws UnsupportedEncodingException 
	 * 
	* @Title: setTlsUserInfo
	* @Description: 上传腾云讯资料
	* @return Object    返回类型
	* @throws
	 */
	public Object setTlsUserInfo(Map<Object, Object> map) throws UnsupportedEncodingException {
		//导入账号信息
		Map<Object, Object> paramMap = new HashMap<>();
		paramMap.put("sdkAppid", map.get("sdkAppid").toString());
		paramMap.put("identifier", map.get("identifier").toString());
		paramMap.put("account", map.get("account").toString());
		paramMap.put("nickName", map.get("nickName").toString());
		paramMap.put("image", map.get("image").toString());
		
		paramMap.put("tlsSig", map.get("tlsSig").toString());
		
		try {
			//导入账号
			boolean importResult = TLSUtil.accountImport(paramMap);
			
			if (!importResult) {
				return new BaseResponse(ResponseCode.TLS_ACCOUNT_IMPORT_ERROR, "导入自有账号失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new BaseResponse(ResponseCode.TLS_ACCOUNT_IMPORT_ERROR, "导入自有账号失败");
		}
		
		//上传个性签名
		paramMap.put("selfSignature", map.get("selfSignature").toString());
		paramMap.remove("nickName");
		paramMap.remove("image");
		
		try {
			//上传腾讯云资料
			boolean setResult = TLSUtil.setTlsUserInfo(paramMap);
			
			if (!setResult) {
				return new BaseResponse(ResponseCode.TLS_SET_ERROR, "上传腾讯云资料失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new BaseResponse(ResponseCode.TLS_SET_ERROR, "上传腾讯云资料失败");
		}
		
		return null;
	}
	
	/**
	 * 
	* @Title: createGroup
	* @Description:创建群组
	* @return Object    返回类型
	* @author
	* @throws
	 */
	public Object createGroup(CreateGroupRequest createGroupRequest) {
		//验证token
		String uid = sessionTokenService.getStringForValue(createGroupRequest.getSessiontoken())+"";
		if (StringUtils.isEmpty(uid) || "null".equalsIgnoreCase(uid)) {
			return new BaseResponse(ResponseCode.TOKENERR, "token已经失效,请重新登录");
		}
		
		try {
			//查询主播信息
			Map<Object, Object> liverMap = liveUserDao.queryLiverInfoByUid(Integer.parseInt(uid));
			if (liverMap == null) {
				return new BaseResponse(ResponseCode.FAILURE, "查无此主播信息,请联系管理员");
			}
			
			//响应
			Map<Object, Object> resultMap = new HashMap<>();
			MapResponse response = null;
			
			//判断是否创建腾讯云群组
			if (liverMap.get("group_id") == null && liverMap.get("anchor_room_no") != null) {
				//获取管理员tls的sig
				String adminSig = TLSUtil.getTLSSig(createGroupRequest.getSdkAppid(), createGroupRequest.getIdentifier());
				
				//组装参数
				Map<Object, Object> paramMap = new HashMap<>();
				//应用id
				paramMap.put("sdkAppid", createGroupRequest.getSdkAppid());
				//管理员账号
				paramMap.put("identifier", createGroupRequest.getIdentifier());
				//群主id(主播账号)
				paramMap.put("account", createGroupRequest.getAccount());
				//管理员签名
				paramMap.put("tlsSig", adminSig);
				//群名称
				paramMap.put("name", createGroupRequest.getName());
				//群组形态
				paramMap.put("type", createGroupRequest.getType());
				//自定义群组
				String groupId = propertiesUtil.getValue("groupPrefix", "conf_live.properties") + liverMap.get("anchor_room_no").toString();
				//群组id
				paramMap.put("groupId", groupId);
				
				try {
					boolean groupResult = TLSUtil.createGroup(paramMap);
					if (!groupResult) {
						return new BaseResponse(ResponseCode.TLS_CREATE_GROUP_ERROR, "创建腾讯云群组失败");
					}
				} catch (Exception e) {
					e.printStackTrace();
					return new BaseResponse(ResponseCode.TLS_CREATE_GROUP_ERROR, "创建腾讯群组失败");
				}
				
				//修改主播创建的腾讯云群组信息
				Map<Object, Object> anchorMap = new HashMap<>();
				anchorMap.put("uid", Integer.parseInt(uid));
				//群组id
				anchorMap.put("groupId", groupId);
				//群组昵称
				anchorMap.put("groupName", createGroupRequest.getName());
				anchorMap.put("updateTime", DateUtil.format(new Date()));
				Integer modifyLiverResult= liveUserDao.modifyLiverByUid(anchorMap);
				if (modifyLiverResult != 1) {
					return new BaseResponse(ResponseCode.FAILURE,"修改主播创建腾讯云群组信息失败");
				}
				
				//群组号
				resultMap.put("groupId", groupId);
				//群昵称
				resultMap.put("groupName", createGroupRequest.getName());
				//主播房间编号
				resultMap.put("roomNo", liverMap.get("anchor_room_no").toString());
				
				//响应
				response = new MapResponse(ResponseCode.SUCCESS, "创建群组成功");
				response.setResponse(resultMap);
				return response;
				
			}else if (liverMap.get("group_id") == null && liverMap.get("anchor_room_no") == null) {
				return new BaseResponse(ResponseCode.FAILURE, "您不是主播,不能创建直播群组号");
			}
			
			//群组号
			resultMap.put("groupId", liverMap.get("group_id"));
			//群昵称
			resultMap.put("groupName", liverMap.get("group_name"));
			//主播房间编号
			resultMap.put("anchorRoomNo", liverMap.get("anchor_room_no"));
			
			//响应
			response = new MapResponse(ResponseCode.SUCCESS, "已有群组号,加入群组成功");
			response.setResponse(resultMap);
			return response;
			
		} catch (Exception e) {
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE, "未知错误,请联系管理员");
		}
	}

	/**
	 * 
	* @Title: queryLiverInfo
	* @Description: 获取主播/观众信息
	* @return Object    返回类型
	* @throws
	 */
	public Object queryLiverInfo(LiverInfoRequest liverInfoRequest) {
		try {
			//验证是否登录
			String uid = null;
			if (liverInfoRequest.getSessiontoken() != null && StringUtils.isNotEmpty(liverInfoRequest.getSessiontoken())) {
				uid = sessionTokenService.getStringForValue(liverInfoRequest.getSessiontoken()) + "";
				
			}
			
			Integer type = liverInfoRequest.getType();
			//结果集
			Map<Object, Object> resultMap = new HashMap<>();
			
			if (type == 0) {  //机器人
				Map<Object, Object> robotMap = liveRobotDao.queryRebotById(liverInfoRequest.getUid());
				if (robotMap == null) {
					return new BaseResponse(ResponseCode.FAILURE, "获取观众信息失败");
				}
				
				//昵称
				resultMap.put("nname",robotMap.get("robot_name"));
				//头像
				resultMap.put("avatar", fileUrl + robotMap.get("avatar"));
				//性别
				resultMap.put("sex", robotMap.get("sex"));
				//等级数
				resultMap.put("rankNo", robotMap.get("rank_no"));
				//关注数
				resultMap.put("attentionNums", robotMap.get("concern_nums"));
				//送出数
				resultMap.put("sendNums", robotMap.get("give_gifts_nums"));
				//禁言状态
				resultMap.put("banSpeakStatus", 0);
				//是否是机器人:默认否
				resultMap.put("isManager", 0);
				
			}else if (type == 1 || type == 2) {  //主播或者用户
				//查询查看用户信息
				Urs urs = ursDao.queryUrsByUid(liverInfoRequest.getUid());
				UrsInfo ursInfo = ursInfoDao.queryUrsInfoByUid(liverInfoRequest.getUid());
				Map<Object, Object> liverMap = liveUserDao.queryLiverInfoByUid(liverInfoRequest.getUid());
				
				//自己直播的用户信息
				Map<Object, Object> ownLiverMap = new HashMap<>();
				if (uid != null && StringUtils.isNotEmpty(uid) && !"null".equals(uid)) {
					//登录了,则查询自己的直播用户信息
					ownLiverMap = liveUserDao.queryLiverInfoByUid(Integer.parseInt(uid));
					
				}
				
				if (liverMap == null) {
					return new BaseResponse(ResponseCode.FAILURE, "查无直播用户信息");
				}

				//直播用户id(用户拉黑)
				resultMap.put("liverId", liverMap.get("id"));
				String nname = urs.getNname() == null || urs.getNname().equals("") ? StrUtils.regexReplacePhone(urs.getPhone()) : urs.getNname();
				//昵称
				resultMap.put("nname", nname);
				//头像
				resultMap.put("avatar", fileUrl + ursInfo.getAvatar());
				//性别
				resultMap.put("sex", ursInfo.getSex());
				//等级数
				resultMap.put("rankNo", liverMap.get("rank_no"));
				//关注数
				resultMap.put("attentionNums", liverMap.get("concern_nums"));
				//粉丝数
				resultMap.put("fansNums", liverMap.get("concerned_nums"));
				//禁言状态
				resultMap.put("banSpeakStatus", 0);
				//返回是否是管理员状态:默认 否    0:否   1:是
				resultMap.put("isManager", 0);
				resultMap.put("signType", liverMap.get("sign_type") == null ? 0 : liverMap.get("sign_type"));  //是否是签约主播 0 否 1 是
				
				if (uid != null && StringUtils.isNotEmpty(uid) &&  !"null".equals(uid) && ownLiverMap != null && ownLiverMap.size() > 0) {
					//查询是否已关注
					try {
						Map<String,Object> param=new HashMap<>();
						param.put("liver_str_id", ownLiverMap.get("id").toString());	//关注用户ID
						param.put("liver_end_id",liverMap.get("id").toString());	//被关注用户iD
						int focusNums=liveUserDao.queryFocusCount(param);
						resultMap.put("isFocus", focusNums);
						
					} catch (Exception e) {
						e.printStackTrace();
						log.info("查询是否以关注失败!");
						resultMap.put("isFocus",0);
					}
				}else {
					resultMap.put("isFocus",0);
				}
				
				if (type == 1) {
					//查询主播的直播动态数
					Map<Object, Object> map = personalCenterDao.queryLiveRecordNum(Integer.parseInt(liverMap.get("id").toString()));
					
					//直播动态数
					resultMap.put("liveRecordSum",map.get("liveRecordSum")==null?0:Integer.parseInt(map.get("liveRecordSum").toString()));
					
				}else {
					//查看观众信息
					Integer liveRecordId = liverInfoRequest.getLiveRecordId();
					//组装参数
					Map<Object, Object> paramMap = new HashMap<>();
					paramMap.put("liveRecordId", liveRecordId);
					paramMap.put("uid", liverInfoRequest.getUid());
					
					//查询某一个观众观看某一场直播的观看信息
					Map<Object, Object> viewRecordMap = liveUserDao.queryLiveViewRecord(paramMap);
					
					if (viewRecordMap == null) {
						//如果是业务后台发送群组消息(使用管理员发言，无需进入房间，无观看记录)
						viewRecordMap = new HashMap<>();
						viewRecordMap.put("ban_speak_status", 0);
						LiveRecordInfo liveRecord = anchorLiveRecordDao.queryLiveRecordById(liveRecordId);
						viewRecordMap.put("anchor_id", liveRecord.getAnchor_id());
						
					}
					//禁言状态
					resultMap.put("banSpeakStatus", Integer.parseInt(viewRecordMap.get("ban_speak_status").toString()));
					
					//查询是否已经是该主播的管理员
					paramMap.clear();
					paramMap.put("manager_id", liverMap.get("id"));
					paramMap.put("anchor_id", viewRecordMap.get("anchor_id"));
					Map<Object, Object> managerMap = anchorManagerDao.queryAnchorManager(paramMap);
					
					if (managerMap != null && managerMap.size() > 0) {
						//是管理员
						resultMap.put("isManager", 1);
					}
					
				}
				
				//调用支付服务获取直播钱包信息
				LiveWalletService.Client client = null;
				log.info("调用支付服务获取直播钱包信息");
				try {
					log.info("获取调用支付服务连接");
					
					TMultiplexedProtocol tMultiplexedProtocol = thriftUtil.getProtocol("LiveWalletService");
					client = new Client(tMultiplexedProtocol);
					thriftUtil.openTransport();
					
					log.info("调用支付服务方法");
					
					Map<String, String> walletMap = new HashMap<>();
					walletMap.put("uid", liverInfoRequest.getUid().toString());
					ResponseData responseData =	client.getLiveWallet(walletMap);
					
					log.info("调用支付服务方法成功");
					if(responseData.getState()!=0){
						log.info("获取直播用户钱包信息失败,错误信息："+responseData.getMsg()+"，用户："+liverInfoRequest.getUid());
						return new BaseResponse(ResponseCode.FAILURE,"获取直播用户钱包信息失败");
					}
					log.info("获取直播用户钱包信息失败,用户：" + liverInfoRequest.getUid());
					
					Map<String, String> map = responseData.getResultMap();
					//鸟蛋数
					DecimalFormat df = new DecimalFormat("0");
					BigDecimal b1 = new BigDecimal(map.get("balance"));
					resultMap.put("birdEggNums", df.format(b1));
					//送出数
					BigDecimal b2 = new BigDecimal(map.get("turnCoinOut"));
					resultMap.put("sendNums", df.format(b2));
					
				}catch(Exception e){
					log.info("获取直播用户钱包信息失败");
					e.printStackTrace();
					return new BaseResponse(ResponseCode.FAILURE,"获取直播用户钱包信息失败");
				}finally{
					thriftUtil.coloseTransport();
				}

			}else {
				return new BaseResponse(ResponseCode.FAILURE, "type参数有误,请重试");
				
			}
			
			//响应
			MapResponse response = new MapResponse(ResponseCode.SUCCESS, "获取直播用户信息成功");
			response.setResponse(resultMap);
			return response;
			
		} catch (Exception e) {
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE, "获取主播用户信息失败");
		}
	}

	/**
	 * 根据uid获取主播/用户信息
	 * @param liverInfoV2Request
	 * @return
	 */
	public Object queryLiverInfo(LiverInfoV2Request liverInfoV2Request) {
		try {
			//验证是否登录
			String liverUid = null;
			if (liverInfoV2Request.getSessiontoken() != null && StringUtils.isNotEmpty(liverInfoV2Request.getSessiontoken())) {
				liverUid = sessionTokenService.getStringForValue(liverInfoV2Request.getSessiontoken()) + "";
			}
			Integer uid = liverInfoV2Request.getUid();
			Integer anchorId = liverInfoV2Request.getAnchorId();
			if (uid == null && anchorId == null) {
				return new BaseResponse(ResponseCode.FAILURE, "直播用户信息, id或者uid不能都为空");
			}

			Map<Object, Object> resultMap = null;
			if (uid != null && uid != 0) {
				resultMap = getLiverInfoByUid(uid);
			} else if (anchorId != null && anchorId != 0) {
				resultMap = getLiverInfoById(anchorId);
			}
			if (resultMap == null) {
				return new BaseResponse(ResponseCode.FAILURE, "查无直播用户信息");
			}
			uid = Integer.parseInt(resultMap.get("uid").toString());
			Map<String, String> wallerMap = getLiveWallerInfoByUid(uid);
			if (wallerMap == null) {
				return new BaseResponse(ResponseCode.FAILURE,"获取直播用户钱包信息失败");
			}


			//查询主播的直播动态数
			int liveRecordSum = 0;
			try {
				Map<Object, Object> map = personalCenterDao.queryLiveRecordNum(Integer.parseInt(resultMap.get("anchorId").toString()));
				if (map != null) {
					liveRecordSum = map.get("liveRecordSum")==null?0:Integer.parseInt(map.get("liveRecordSum").toString());
				}
			} catch (Exception e) {
				log.warn("查询主播的直播动态数失败");
			}
			resultMap.remove("anchorId");  //不需要返回
			//直播动态数
			resultMap.put("liveRecordSum",liveRecordSum);

			int isFocus = 0;
			if (liverUid != null) {
				try {
					int lUid = Integer.parseInt(liverUid);
					isFocus = isFocus(lUid, uid);
				} catch (Exception e) {
					log.warn("sessiontoken转换Int失败，uid= " + liverUid);
				}
			}
			resultMap.put("isFocus", isFocus);   //是否已关注， 0否 1 是

			//鸟蛋数
			DecimalFormat df = new DecimalFormat("0");
			BigDecimal b1 = new BigDecimal(wallerMap.get("balance"));
			//送出数
			BigDecimal b2 = new BigDecimal(wallerMap.get("turnCoinOut"));
			resultMap.put("birdEggNums", df.format(b1));
			resultMap.put("sendNums", df.format(b2));
			//响应
			MapResponse response = new MapResponse(ResponseCode.SUCCESS, "获取直播用户信息成功");
			response.setResponse(resultMap);
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE, "获取主播用户信息失败");
		}

	}

	public Map<Object, Object> getLiverInfoByUid(Integer uid) {
		try {
			Map<Object, Object> userInfo = liveUserDao.queryLiverInfoByUid(uid);
			if (userInfo == null) {
				return null;
			}
			return toDefaultUserInfo(userInfo);
		} catch (Exception e) {
			return null;
		}
	}

	public Map<Object, Object> getLiverInfoById(Integer id) {
		try {
			Map<Object, Object> userInfo = liveUserDao.queryLiverInfoById(id);
			if (userInfo == null) {
				return null;
			}
			return toDefaultUserInfo(userInfo);
		} catch (Exception e) {
			return null;
		}
	}

	private Map<Object, Object> toDefaultUserInfo(Map<Object, Object> userInfo) {
		Map<Object, Object> resultMap = new HashMap<Object, Object>();
		resultMap.put("anchorId", userInfo.get("id"));
		resultMap.put("uid", userInfo.get("uid"));
		resultMap.put("nname", StrUtils.standardName(userInfo.get("nname"), userInfo.get("phone")));
		String avatar = userInfo.get("avatar") == null ? "" : String.valueOf(userInfo.get("avatar"));
		resultMap.put("avatar", fileUrl + avatar);
		resultMap.put("sex", userInfo.get("sex"));
		resultMap.put("rankNo", userInfo.get("rank_no"));
		resultMap.put("attentionNums", userInfo.get("concern_nums"));
		resultMap.put("fansNums", userInfo.get("concerned_nums"));
		resultMap.put("signType", userInfo.get("sign_type") == null ? 0 : userInfo.get("sign_type"));
		return resultMap;
	}


	private Integer isFocus(Integer flowUId, Integer focusUId) {
		//查询是否已关注
		try {
			Map<String,Object> param = new HashMap<>();
			param.put("str_uid", flowUId);	//关注用户ID
			param.put("end_uid",focusUId);	//被关注用户iD
			int focusNums = liveUserDao.queryFocusCountByUid(param);
			return focusNums;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("查询是否以关注失败!");
			return 0;
		}
	}

	private Map<String, String> getLiveWallerInfoByUid(Integer uid) {
		//调用支付服务获取直播钱包信息
		LiveWalletService.Client client = null;
		log.info("调用支付服务获取直播钱包信息");
		try {
			log.info("获取调用支付服务连接");
			TMultiplexedProtocol tMultiplexedProtocol = thriftUtil.getProtocol("LiveWalletService");
			client = new Client(tMultiplexedProtocol);
			thriftUtil.openTransport();
			log.info("调用支付服务方法");
			Map<String, String> walletMap = new HashMap<>();
			walletMap.put("uid", uid.toString());
			ResponseData responseData =	client.getLiveWallet(walletMap);
			log.info("调用支付服务方法成功");
			if(responseData.getState() != 0){
				log.info("获取直播用户钱包信息失败,错误信息："+responseData.getMsg()+"，用户：" + uid);
				return null;
			}
			log.info("获取直播用户钱包信息失败,用户：" + uid);
			Map<String, String> map = responseData.getResultMap();
			return  map;
		} catch(Exception e){
			e.printStackTrace();
			log.info("获取直播用户钱包信息失败");
			return null;
		}finally{
			thriftUtil.coloseTransport();
		}
	}



	/**
	 * 描述：使用电话号码 查询主播|观众信息
	 * @param liverInfoByPhoneRequest
	 * @return Object
	 * */
	public Object queryLiverInfoByPhone(LiverInfoByPhoneRequest liverInfoByPhoneRequest){
		//验证token  查看人自己的ID
//		String uid = liverInfoByPhoneRequest.getUid().toString();
		String uid = sessionTokenService.getStringForValue(liverInfoByPhoneRequest.getSessiontoken())+"";
		if (StringUtils.isEmpty(uid) || "null".equalsIgnoreCase(uid)) {
			return new BaseResponse(ResponseCode.TOKENERR, "token已经失效,请重新登录");
		}
		
		MapResponse response = null;
		
		//结果集
		Map<Object, Object> resultMap = new HashMap<>();
		
		//查看对方的人的基本信息  
		Map<Object, Object> liverMap = liveUserDao.queryLiveByPhone(liverInfoByPhoneRequest.getPhone());
		if (liverMap!=null ) {
			//查询用户信息
			Urs urs = ursDao.queryUrsByUid(Integer.parseInt(liverMap.get("uid").toString()));
			UrsInfo ursInfo = ursInfoDao.queryUrsInfoByUid(Integer.parseInt(liverMap.get("uid").toString()));
//			Map<Object, Object> liverMap = liveUserDao.queryLiverInfoByUid(Integer.parseInt(liverInfoMap.get("uid").toString()));
			//查询自己的直播用户信息
			Map<Object, Object> ownLiverMap = liveUserDao.queryLiverInfoByUid(Integer.parseInt(uid));
			
			//昵称
			resultMap.put("nname", urs.getNname());
			//头像
			resultMap.put("avatar", fileUrl + ursInfo.getAvatar());
			//性别
			resultMap.put("sex", ursInfo.getSex());
			//等级数
			resultMap.put("rankNo", liverMap.get("rank_no"));
			//关注数
			resultMap.put("attentionNums", liverMap.get("concern_nums"));
			//粉丝数
			resultMap.put("fansNums", liverMap.get("concerned_nums"));
			//禁言状态
			resultMap.put("banSpeakStatus", 0);
			//返回是否是管理员状态:默认 否    0:否   1:是
			resultMap.put("isManager", 0);
			resultMap.put("utype", liverMap.get("utype"));
			
			//查询是否已关注
			if (liverMap.get("utype").toString().equals("1")) {
				//查看主播信息
				try {
					Map<String,Object> param=new HashMap<>();
					param.put("liver_str_id", ownLiverMap.get("id").toString());	//关注用户ID
					param.put("liver_end_id",liverMap.get("id").toString());	//被关注用户iD
					int focusNums=liveUserDao.queryFocusCount(param);
					resultMap.put("isFocus", focusNums);
				} catch (Exception e) {
					e.printStackTrace();
					log.info("查询是否以关注失败!");
					resultMap.put("isFocus",0);
				}
			}
			
			//调用支付服务获取直播钱包信息
			LiveWalletService.Client client = null;
			log.info("调用支付服务获取直播钱包信息");
			try {
				log.info("获取调用支付服务连接");
				
				TMultiplexedProtocol tMultiplexedProtocol = thriftUtil.getProtocol("LiveWalletService");
				client = new Client(tMultiplexedProtocol);
				thriftUtil.openTransport();
				
				log.info("调用支付服务方法");
				
				Map<String, String> walletMap = new HashMap<>();
				walletMap.put("uid", liverMap.get("uid").toString());
				ResponseData responseData =	client.getLiveWallet(walletMap);
				
				log.info("调用支付服务方法成功");
				if(responseData.getState()!=0){
					log.info("获取直播用户钱包信息失败,错误信息："+responseData.getMsg()+"，用户："+liverMap.get("uid").toString());
					return new BaseResponse(ResponseCode.FAILURE,"获取直播用户钱包信息失败");
				}
				log.info("获取直播用户钱包信息失败,用户："+liverMap.get("uid").toString());
				
				Map<String, String> map = responseData.getResultMap();
				//鸟蛋数
				DecimalFormat df = new DecimalFormat("0");
				BigDecimal b1 = new BigDecimal(map.get("balance"));
				resultMap.put("birdEggNums", df.format(b1));
				//送出数
				BigDecimal b2 = new BigDecimal(map.get("turnCoinOut"));
				resultMap.put("sendNums", df.format(b2));
				
			}catch(Exception e){
				log.info("获取直播用户钱包信息失败");
				e.printStackTrace();
				return new BaseResponse(ResponseCode.FAILURE,"获取直播用户钱包信息失败");
			}finally{
				thriftUtil.coloseTransport();
			}
			
			//响应
			response = new MapResponse(ResponseCode.SUCCESS, "获取直播用户信息成功");
			response.setResponse(resultMap);
			
		}else {
			//响应
			response = new MapResponse(ResponseCode.FAILURE, "获取用户信息失败");
		}
		return response;
	}
	
	/**
	 * 描述：查询直播用户的当前基本信息
	 * @return response
	 * */
	public Object queryliveInfoDetail(LiverInfoByPhoneRequest liverInfoByPhoneRequest){
		MapResponse response = null;
		try {
			Map<Object, Object> map = liveUserDao.queryAnchorByPhone(liverInfoByPhoneRequest.getPhone());
			if (map!=null) {
				map.put("avatar", fileUrl+map.get("avatar"));
				response = new MapResponse(ResponseCode.SUCCESS, "获取成功");
				response.setResponse(map);
			}else {
				response = new MapResponse(ResponseCode.FAILURE, "获取用户基本信息失败");
			}
		} catch (Exception e) {
			log.info("获取用户基本信息失败");
			e.printStackTrace();
			response = new MapResponse(ResponseCode.FAILURE, "获取用户基本信息失败");
		}
		return response;
	}
	
	/**
	 * 
	 * @Description: 查询我的等级
	 * @author xiaoxiong
	 * @date 2016年8月29日
	 * @version
	 */
	public Object queryMyLevel(BaseRequest request) {
		try {
			//验证token
			String uid = sessionTokenService.getStringForValue(request.getSessiontoken())+"";
			if (StringUtils.isEmpty(uid) || "null".equalsIgnoreCase(uid)) {
				return new BaseResponse(ResponseCode.TOKENERR, "token已经失效,请重新登录");
			}
			
			Map<Object,Object> resultMap=new HashMap<>();
			
			//查询用户等级
			Map<Object,Object> liveMap=liveUserDao.queryLiverInfoByUid(Integer.parseInt(uid));
			if(liveMap==null){
				return new BaseResponse(ResponseCode.DATA_NULL, "没有找到用户信息");
			}
			
			String rank_no=liveMap.get("rank_no")!=null?"1":liveMap.get("rank_no")+"";//等级
			String current_expe=liveMap.get("current_expe")!=null?"0":liveMap.get("current_expe")+"";//当前经验
			String achievement=liveMap.get("achievement")+"";//当前等级头衔
			if(achievement.equals("")||achievement.equals("null")){
				Map<Object, Object> rankMap = liveUserDao.queryMemberRankByExp(Integer.valueOf(current_expe));
				if(rankMap==null)rankMap=new HashMap<>();
				achievement=rankMap.get("member_rank_name")==null?"":rankMap.get("member_rank_name")+"";
			}
			//查询下一级所需经验
			int nextLevleExpe=0;
			
			Map<String,Object> levelMap=liveUserDao.queryLevelByRankNo(Integer.parseInt(rank_no)+1);
			if(levelMap!=null){
				nextLevleExpe=Integer.parseInt(levelMap.get("upgrade_experience")+"")-Integer.parseInt(current_expe);
				resultMap.put("nextLevleName", levelMap.get("member_rank_name"));//下一级等级名称
			}
			resultMap.put("nextLevleExpe", nextLevleExpe);//下级所需经验
			
			resultMap.put("achievement", achievement);//当前等级名称
			
			resultMap.put("rankNo", rank_no);
			MapResponse mapRespnse=new MapResponse(ResponseCode.SUCCESS,"成功");
			mapRespnse.setResponse(resultMap);
			return mapRespnse;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("查询我的等级失败！");
		}
		return new BaseResponse(ResponseCode.FAILURE, "失败");
	}

	/**
	 * 
	* @Title: queryPrivilege
	* @Description: 直播房间权限
	* @return Object    返回类型
	* @throws
	 */
	public Object queryPrivilege(LiveRoomPrivilegeRequest liveRoomPrivilegeRequest) {
		try {
			//结果集
			Map<Object, Object> resultMap = new HashMap<>();
			
			//查询权限
			List<Map<Object, Object>> privilegeList = liveUserDao.queryPrivilege(liveRoomPrivilegeRequest.getType());
			//权限
			resultMap.put("privilegeList", privilegeList);
			//是否存在房间锁 0否 1是
			resultMap.put("existRoomLock", 0);
			
			int android = liveRoomPrivilegeRequest.getSystemversion().indexOf("android");
			int ios = liveRoomPrivilegeRequest.getSystemversion().indexOf("ios");

			LiveRecordInfo liveRecordInfo = null;
			if (liveRoomPrivilegeRequest.getLiveRecordId() != null) {
				liveRecordInfo = anchorLiveRecordDao.queryLiveRecordById(liveRoomPrivilegeRequest.getLiveRecordId());
			}

			if (android!=-1 || ios!=-1) {//判断是否是Android
				String appversion = liveRoomPrivilegeRequest.getAppversion();
				appversion = appversion.replace(".", "");
				int appv = Integer.parseInt(appversion);
				if (appversion!=null && appv>357 ) { //判断版本号是否小于等于3.5.7
					//获取用户权限的时候，查看是否直播记录是否
					if (liveRoomPrivilegeRequest.getType() == 1) {
						if (liveRoomPrivilegeRequest.getLiveRecordId() != null) {
							//查询直播通告是否加锁了
							if (liveRecordInfo != null) {
								if (liveRecordInfo.getZhibo_type() == 1 && liveRecordInfo.getAnchor_room_password() != null && StringUtils.isNotEmpty(liveRecordInfo.getAnchor_room_password())) {
									resultMap.put("existRoomLock", 1);
								}
							}else {
								return new BaseResponse(ResponseCode.FAILURE, "直播不存在");
							}
						}else {
							return new BaseResponse(ResponseCode.FAILURE, "提交数据不对");
						}
					}
				}
			}
			//响应
			MapResponse response = new MapResponse(ResponseCode.SUCCESS, "查询直播房间权限成功");
			response.setResponse(resultMap);
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE, "查询直播房间权限失败");
		}
	}

	/**
	 * 
	* @Title: querySecretMarkLiverInfo
	* @Description: 在消息中心的，私信模块获取主播/观众信息
	* @return Object    返回类型
	* @throws
	 */
	public Object querySecretMarkLiverInfo(PhoneRequest phoneRequest) {
		//结果集
		Map<Object, Object> resultMap = new HashMap<>();
		
		//根据手机号码查询直播用户信息
		Map<Object, Object> liverMap = liveUserDao.queryAnchorByPhone(phoneRequest.getPhone());
		if (liverMap == null || liverMap.size() <= 0) {
			return new BaseResponse(ResponseCode.FAILURE, "查无此直播用户信息");
		}
		
		//直播用户类型： 1 主播 2 普通用户
		Integer utype = Integer.parseInt(liverMap.get("utype").toString());
		
		//查询用户信息
		Integer uid = Integer.parseInt(liverMap.get("uid").toString());
		
		Urs urs = ursDao.queryUrsByUid(uid);
		UrsInfo ursInfo = ursInfoDao.queryUrsInfoByUid(uid);
		
		//直播用户类型： 1 主播 2 普通用户
		resultMap.put("utype", utype);
		//直播用户id
		resultMap.put("liverId", liverMap.get("id").toString());
		//会员id
		resultMap.put("uid", uid);
		//昵称
		resultMap.put("nname", urs.getNname());
		//头像
		resultMap.put("avatar", fileUrl + ursInfo.getAvatar());
		//性别
		resultMap.put("sex", ursInfo.getSex());
		//等级数
		resultMap.put("rankNo", liverMap.get("rank_no"));
		//关注数
		resultMap.put("attentionNums", liverMap.get("concern_nums"));
		//粉丝数
		resultMap.put("fansNums", liverMap.get("concerned_nums"));
		//关注状态:默认为0  0:否  1:是
		resultMap.put("isFocus",0);
		
		//查询是否已关注
		if (utype == 1) {
			//查看当前用户信息
			String ownUid = sessionTokenService.getStringForValue(phoneRequest.getSessiontoken()) + "";
			Map<Object, Object> ownLiverMap = liveUserDao.queryLiverInfoByUid(Integer.parseInt(ownUid));
			
			if (ownLiverMap == null || ownLiverMap.size() <= 0) {
				return new BaseResponse(ResponseCode.FAILURE, "查无此直播用户信息");
			}
			
			//查看关注信息
			try {
				Map<String,Object> param=new HashMap<>();
				param.put("liver_str_id", ownLiverMap.get("id").toString());	//关注用户ID
				param.put("liver_end_id",liverMap.get("id").toString());	//被关注用户iD
				int focusNums=liveUserDao.queryFocusCount(param);
				resultMap.put("isFocus", focusNums);
			} catch (Exception e) {
				e.printStackTrace();
				log.info("查询是否以关注失败!");
				resultMap.put("isFocus",0);
			}
		}
		
		//调用支付服务获取直播钱包信息
		LiveWalletService.Client client = null;
		log.info("调用支付服务获取直播钱包信息");
		try {
			log.info("获取调用支付服务连接");
			
			TMultiplexedProtocol tMultiplexedProtocol = thriftUtil.getProtocol("LiveWalletService");
			client = new Client(tMultiplexedProtocol);
			thriftUtil.openTransport();
			
			log.info("调用支付服务方法");
			
			Map<String, String> walletMap = new HashMap<>();
			walletMap.put("uid", uid + "");
			ResponseData responseData =	client.getLiveWallet(walletMap);
			
			log.info("调用支付服务方法成功");
			if(responseData.getState()!=0){
				log.info("获取直播用户钱包信息失败,错误信息："+responseData.getMsg()+"，用户："+ uid);
				return new BaseResponse(ResponseCode.FAILURE,"获取直播用户钱包信息失败");
			}
			log.info("获取直播用户钱包信息失败,用户："+uid);
			
			Map<String, String> map = responseData.getResultMap();
			//鸟蛋数
			DecimalFormat df = new DecimalFormat("0");
			BigDecimal b1 = new BigDecimal(map.get("balance"));
			resultMap.put("birdEggNums", df.format(b1));
			//送出数
			BigDecimal b2 = new BigDecimal(map.get("turnCoinOut"));
			resultMap.put("sendNums", df.format(b2));
			
		}catch(Exception e){
			log.info("获取直播用户钱包信息失败");
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE,"获取直播用户钱包信息失败");
		}finally{
			thriftUtil.coloseTransport();
		}
		
		//响应
		MapResponse response = new MapResponse(ResponseCode.SUCCESS, "查询用户信息成功");
		response.setResponse(resultMap);
		return response;
	}
	/**
	 * 
	 * @Description: 查询直播用户信息
	 * @author xiaoxiong
	 * @date 2016年11月16日
	 */
	public LiverInfo queryLiverByUid(String uid) {
		try {
			
			return liveUserDao.queryLiverByUid(Long.parseLong(uid));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * |
	 * @Description: 根据主播ID查询主播基本信息
	 * @author xiaoxiong
	 * @date 2016年11月14日
	 */
	public LiverInfo queryLiverById(Long anchorId) {
		
		return liveUserDao.queryLiverInfoByAnchorId(Integer.parseInt(anchorId.toString()));
	}
	
	/**
	 * 根据主播信息查询直播用户信息
	 * @author xiaoxiong
	 * @date 2016年12月10日
	 */
	public LiverInfo queryLiverInfoByAnchorId(int anchorId) {
		
		return liveUserDao.queryLiverInfoByAnchorId(anchorId);
	}

	public Map<Object, Object> queryLiverInfoByUid(int uid) {
		try {
			return liveUserDao.queryLiverInfoByUid(uid);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 查询我的壕友
	 * @author xiaoxiong
	 * @date 2016年12月24日
	 */
	public List<Map<Object,Object>> queryMakeHaoFriend(String uid) {
		try {
			String zero ="";
			for(int i=0;i<11-uid.length();i++){
				zero+="0";
			}
			log.info("用户ID："+zero+uid);
			return liveUserDao.queryMakeHaoFriend(zero+uid);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
//		String uid ="33575";
//		String zero ="";
//		for(int i=0;i<11-uid.length();i++){
//			zero+="0";
//		}
//		System.out.println(zero+uid);
		
	}

	public List<Map<Object, Object>> queryLiverInfoByUidList(
			List<Object> list) {
		
		return liveUserDao.queryLiveInfoByUidList(list);
	}
	
	public List<LiveFansRank> queryLiveFansRankList() {
		try {
			return liveUserDao.queryLiveFansRankList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 查询用户最大充值金额
	 * @author xiaoxiong
	 * @date 2017年1月10日
	 */
	public List<Map<String,Object>> queryMaxPamentList(List<Object> list) {
		try {
			return liveUserDao.queryMaxPamentList(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	* @Title: getVisitorTlsSig
	* @Description: 获取游客模式的腾讯sig签名
	* @return Object    返回类型
	* @throws
	 */
	public Object getVisitorTlsSig(GetVisitorTlsSigRequest getVisitorTlsSigRequest) {
		try {
			//获取签名类型   0 指定设备sig签名    1 自定义sig签名 2已有的自定义账号签名
			Integer type = getVisitorTlsSigRequest.getType();
			//指定设备账号
			String account = getVisitorTlsSigRequest.getAccount();
			//appid
			String sdkAppid = propertiesUtil.getValue("SdkAppid", "conf_live.properties");
			
			//游客签名
			String visitorTlsSig = "";
			//游客自定义sig签名redis key
			String tlsRandomKey = Constant.TLS_RANDOM_KEY;
			
			//指定设备sig签名,已有的自定义账号签名必须传账号
			if ((type == 0 || type == 2) && (account == null || StringUtils.isEmpty(account))) {
				return new BaseResponse(ResponseCode.FAILURE, "参数有误");
			}
			
			//结果集
			Map<Object, Object> resultMap = new HashMap<>();
			
			//指定设备sig签名 
			if (type == 0) {
				//查询redis是否存在设备账号的sig签名
				String tlsVisitorKey = Constant.TLS_VISITOR_KEY + account;
				visitorTlsSig = stringRedisTemplate.opsForValue().get(tlsVisitorKey);
				
				//如果redis不存在设备账号的签名,则调用tls动态库,生成一个签名
				if (visitorTlsSig == null || StringUtils.isEmpty(visitorTlsSig)) {
					//调用tls,获取设备账号tls的sig
					visitorTlsSig = TLSUtil.getTLSSig(sdkAppid, account);
					stringRedisTemplate.opsForValue().set(tlsVisitorKey, visitorTlsSig);
					stringRedisTemplate.expire(tlsVisitorKey, 180, TimeUnit.DAYS);
				}
				//签名账号
				resultMap.put("account", account);
				
			}else if (type == 1) {
				//1自定义sig签名    	账号:使用当前时间戳+随机4位数
				account = this.getRandomAccount();
				tlsRandomKey = tlsRandomKey + account;
				
				//调用tls动态库,生成一个自定义签名,并且存入redis中
				visitorTlsSig = TLSUtil.getTLSSig(sdkAppid, account);
				stringRedisTemplate.opsForValue().set(tlsRandomKey, visitorTlsSig);
				stringRedisTemplate.expire(tlsRandomKey, 180, TimeUnit.DAYS);
				//签名账号
				resultMap.put("account", account);
				
			}else if (type == 2) {
				//查询redis,已有的自定义账号的sig签名
				tlsRandomKey = tlsRandomKey + account;
				visitorTlsSig = stringRedisTemplate.opsForValue().get(tlsRandomKey);
				
				//如果redis不存在自定义账号的签名,则调用tls动态库,生成一个签名
				if (visitorTlsSig == null || StringUtils.isEmpty(visitorTlsSig)) {
					account = this.getRandomAccount();
					tlsRandomKey = tlsRandomKey + account;
					//调用tls,获取设备账号tls的sig
					visitorTlsSig = TLSUtil.getTLSSig(sdkAppid, account);
					stringRedisTemplate.opsForValue().set(tlsRandomKey, visitorTlsSig);
					stringRedisTemplate.expire(tlsRandomKey, 180, TimeUnit.DAYS);
				}
				
				//签名账号
				resultMap.put("account", account);
				
			}else {
				return new BaseResponse(ResponseCode.FAILURE, "参数有误");
				
			}
			
			//游客签名
			resultMap.put("visitorTlsSig", visitorTlsSig);
			
			//响应
			MapResponse response = new MapResponse(ResponseCode.SUCCESS, "获取游客模式签名成功");
			response.setResponse(resultMap);
			return response;
			
		} catch (Exception e) {
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE, "未知错误,请联系管理员");
		}
	}
	
	/**
	 * 
	* @Title: getRandomAccount
	* @Description: 获取自定义账号 ：使用当前时间戳 + 随机4位数
	* @return String    返回类型
	* @throws
	 */
	public String getRandomAccount() {
		Random random = new Random();
		int randomNum = random.nextInt(8999) + 1000;
		long currentTimeMillis = System.currentTimeMillis();
		String account = currentTimeMillis + "" + randomNum;
		return account;
	}
	
	
}
