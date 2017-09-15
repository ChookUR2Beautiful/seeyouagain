package com.xmniao.xmn.core.login;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xmniao.xmn.core.base.BaseRequest;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.login.LoginRequest;
import com.xmniao.xmn.core.common.request.login.UserRegisterRequest;
import com.xmniao.xmn.core.common.rocketmq.ProducerServiceImpl;
import com.xmniao.xmn.core.common.rocketmq.model.TopicInfo;
import com.xmniao.xmn.core.live.dao.BusinessDao;
import com.xmniao.xmn.core.thrift.LiveWalletService;
import com.xmniao.xmn.core.thrift.ResponseData;
import com.xmniao.xmn.core.thrift.SynthesizeService;
import com.xmniao.xmn.core.util.DateUtil;
import com.xmniao.xmn.core.util.MD5;
import com.xmniao.xmn.core.util.PropertiesUtil;
import com.xmniao.xmn.core.util.ThriftUtil;
import com.xmniao.xmn.core.verification.dao.UrsDao;
import com.xmniao.xmn.core.verification.dao.UrsInfoDao;
import com.xmniao.xmn.core.verification.entity.Urs;
import com.xmniao.xmn.core.verification.entity.UrsInfo;
import com.xmniao.xmn.core.xmer.dao.LoginDao;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：LiveRoomService   
* 类描述：   直播间处理service
* 创建人：yezhiyong   
* 创建时间：2016年12月15日 上午11:00:20   
* @version    
*
 */
@Service
public class UserLoginService {
	
	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(UserLoginService.class);
	
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

	@Autowired
	private UrsDao ursDao;
	
	@Autowired
	private LoginDao loginDao;
	
	@Autowired
	private BusinessDao businessDao;
	
	@Autowired
	private UrsInfoDao ursInfoDao;
	
	@Autowired
	private PropertiesUtil propertiesUtil;
	
	@Autowired
	private ThriftUtil thriftUtil;
	
	@Autowired
	private ProducerServiceImpl producerServiceImpl;
	
	@Autowired
	private TopicInfo userRegisterInfo;
	
	/**
	 * 寻蜜鸟用户登录接口
	 * @param LoginRequest
	 * @return Object
	 * */
	public Object userLogin(LoginRequest request){
		
		MapResponse response = null;
		Map<Object, Object> resultMap = new HashMap<Object, Object>();
		
		if (null==request.getPhone() || "".equals(request.getPhone())) {
			return new MapResponse(ResponseCode.FAILURE, "请输入手机号码");
		}
		if (null==request.getPassWord() || "".equals(request.getPassWord())) {
			return new MapResponse(ResponseCode.FAILURE, "请输入登录密码");
		}
		
		String phone = request.getPhone();
		String passWord = request.getPassWord();
		
		//检查手机号码 长度11位  纯数字
		if (phone.length()==11) {
			 Pattern pattern = Pattern.compile("[0-9]*"); 
			   Matcher isNum = pattern.matcher(phone);
			   if(!isNum.matches()){
				   return new MapResponse(ResponseCode.FAILURE, "手机号码只能是数字");
			   } 
		}else {
			return new MapResponse(ResponseCode.FAILURE, "请输入正确的手机号码");
		}
		
		//根据手机号码查询用户信息
		Urs urs = ursDao.getUrsByUname(phone);
		
		if (urs!=null) {
			//同时验证下号码当前状态
			if (null!=urs.getStatus() && urs.getStatus()!=1) {
				return pullToBlackState(urs.getStatus());
			}
			
			String pwd = urs.getPassword();
			if (!pwd.equals(passWord)) {
				return new MapResponse(ResponseCode.FAILURE, "密码错误");
			}
		}else {
			return new MapResponse(ResponseCode.FAILURE, "用户不存在");
		}
		
		//生成MD5签名串   (根据当前uname,UID,加UUId 生成sessionToken 存储uid)
		StringBuffer sBuffer = new StringBuffer();
		try {
			sBuffer.append(UUID.randomUUID().toString()).append(urs.getUname()).append(urs.getUid());
			String sessionToken = MD5.Encode(sBuffer.toString());
			if (sessionToken!=null && !"".equals(sessionToken)) {
				stringRedisTemplate.opsForValue().set(sessionToken, urs.getUid().toString());
				stringRedisTemplate.expire(sessionToken, 30, TimeUnit.DAYS);
				
				resultMap.put("uid", urs.getUid());
				resultMap.put("sessionToken", sessionToken);
				resultMap.put("phone", phone);
				
				//记录登录信息
				Map<Object, Object> loginRecordMap = new HashMap<Object, Object>();
				loginRecordMap.put("uid", urs.getUid());
				loginRecordMap.put("logindate", DateUtil.format(new Date(), DateUtil.defaultSimpleFormater));
				loginRecordMap.put("logintype", (null!=request.getSystemversion() && request.getSystemversion().indexOf("ios")>=0)?2:1);
				loginRecordMap.put("version", request.getAppversion());
				loginRecordMap.put("brand", request.getBrand());
				loginRecordMap.put("model", request.getModel());
				loginDao.addLoginRecord(loginRecordMap);
				
				try {
					this.saveLoginUserRedisMap(urs,sessionToken);
				} catch (Exception e) {
					log.info("登录存redis失败:"+urs.getUid());
					e.printStackTrace();
					return new MapResponse(ResponseCode.FAILURE, "操作失败,请重试!");
				}
				response = new MapResponse(ResponseCode.SUCCESS, "操作成功");
				response.setResponse(resultMap);
				
			}else {
				return new MapResponse(ResponseCode.FAILURE, "操作失败,请重试");
			}
			
		} catch (Exception e) {
			log.info("登录失败:"+phone);
			e.printStackTrace();
			return new MapResponse(ResponseCode.FAILURE, "操作失败,请重试");
		}
		return  response;
	}
	
	
	/**
	 * 获取用户基本信息 将用户基本信息存入redis  code
	 * */
	public void saveLoginUserRedisMap(Urs urs,String token) throws Exception{
		
		String  userRedisKey = "USERID_"+urs.getUid();
		stringRedisTemplate.delete(userRedisKey);
		
		Map<Object, Object> map = new HashMap<Object,Object>();
		Map<Object, Object> redisMap = new HashMap<Object, Object>();
		
		map.put("uid", urs.getUid());
		Map<Object, Object> ursResultMap = ursDao.queryBursInfoByUid(map);
		if (ursResultMap!=null && ursResultMap.size()>0){
			redisMap.put("code", token);
			redisMap.put("loginTime", DateUtil.format(new Date(), DateUtil.defaultSimpleFormater));
			
			for (Map.Entry<Object, Object> entry : ursResultMap.entrySet()) {
				redisMap.put(entry.getKey()+"", entry.getValue()+"");
			}
			stringRedisTemplate.opsForHash().putAll(userRedisKey, redisMap);
			stringRedisTemplate.expire(userRedisKey, 30, TimeUnit.DAYS);
		}else {
			throw new Exception("登录失败,获取用户信息失败");
		}
	}
	
	/**
	 * 寻蜜鸟用户退出接口
	 * @param BaseRequest
	 * @return Object
	 * */
	public Object userLogout(BaseRequest request){
		
		MapResponse response = null;
		Map<Object, Object> resultMap = new HashMap<Object, Object>();
		String sessionToken = request.getSessiontoken();
		try {
			if(!StringUtils.isEmpty(sessionToken) && !"null".equals(sessionToken)){
				stringRedisTemplate.delete("USERID_"+sessionTokenService.getStringForValue(request.getSessiontoken()));
				stringRedisTemplate.delete(sessionToken);
			}else {
				return new MapResponse(ResponseCode.FAILURE, "退出异常,请重试");
			}
		} catch (Exception e) {
			log.info("退出异常:删除redis key异常"+e);
			return new MapResponse(ResponseCode.FAILURE, "退出异常,请重试");
		}
		response = new MapResponse(ResponseCode.SUCCESS, "退出成功");
		response.setResponse(resultMap);
		return  response;
	}
	
	
	/**
	 * 寻蜜鸟用户注册 用户
	 * @param BaseRequest
	 * @return Object
	 * */
	public Object userRegister(UserRegisterRequest request){
		
		String registerkey = "register_"+request.getPhone();
		Long resultNum = stringRedisTemplate.opsForValue().increment(registerkey, 1);
		log.info("检查正在直播的房间,拉取机器人随机发送礼物，累计定时任务数："+resultNum);
		if (resultNum>1) {
			return new MapResponse(ResponseCode.FAILURE, "正在注册,请稍后...");
		}
		
//		MapResponse response = null;
//		Map<Object, Object> resultMap = new HashMap<Object, Object>();
		try {
			
			//首次进来设置时间
			stringRedisTemplate.expire(registerkey, 3, TimeUnit.MINUTES);
			
			if (request.getHasAgree()==0) {
				return new MapResponse(ResponseCode.FAILURE, "如不同意服务协议将不能成为本平台会员");
			}
			
			//验证短信验证码
			String code = stringRedisTemplate.opsForValue().get("SMS_"+request.getPhone());
			if (StringUtils.isEmpty(code) || !code.equals(request.getPcode())) {
				return new MapResponse(ResponseCode.FAILURE, "验证码错误或已过期,请重新发送");
			}
			
			//注册用户基础信息map
			Urs urs = new Urs();
			Map<Object, Object> userMap = new HashMap<Object, Object>();
			
			if (null!=request.getPassWord() || null!=request.getConfirmPwd()) {
				if (request.getConfirmPwd().equals(request.getPassWord())) {
					
					urs.setUname(request.getPhone());
					urs.setPassword(request.getPassWord());
					urs.setRegtime(new Date());
					urs.setRegcity(request.getRegcity());
					urs.setRegip(request.getRegIp());
					urs.setRegtype(request.getRegtype());
					urs.setPhone(request.getPhone());
					urs.setPhoneType(request.getSystemversion().indexOf("ios")>-1?2:1);
					urs.setAppVersion(request.getAppversion());
					
					//新增用户实体信息
					UrsInfo info = new UrsInfo();
					info.setPhone(request.getPhone());
					info.setUname(request.getPhone());
					
					info.setModel(request.getModel());
					info.setBrand(request.getBrand());
					info.setLastBrand(request.getBrand());
					info.setLastModel(request.getModel());
					try {
						JSONArray jsonArray = JSON.parseArray(propertiesUtil.getValue("userRegisterImage", "conf_common.properties"));  ;
						int index = new Random().nextInt(jsonArray.size())%(jsonArray.size()-1+1) + 0;
						info.setAvatar(jsonArray.get(index).toString());
					} catch (Exception e) {
						e.printStackTrace();
						return new MapResponse(ResponseCode.FAILURE, "操作异常");
					}
					
					if (request.getLat()>0 && request.getLon()>0  ) {
						//获取城市当前商圈 
						//通过定位获取距离最近的商圈信息
						Map<Object,Object> locationMap = new HashMap<Object,Object>();
						locationMap.put("lon", request.getLon());//经度
						locationMap.put("lat", request.getLat());//纬度
						List<Map<Object,Object>> list = businessDao.selectByLonAndLat(locationMap);
						if (list.size()>0) {
							Map<Object,Object> areaMap = list.get(0);
							userMap.put("regzoneid", areaMap.get("zoneId"));
						}
					}
					//新增用户基础信息
					ursDao.addUrsByEntity(urs);
					if (null== urs.getUid() && urs.getUid() == 0) {
						return new BaseResponse(ResponseCode.FAILURE,"注册异常");
					}
					
					int usrId = urs.getUid();
					info.setUid(usrId);
					try {
						int usrInfoNum = ursInfoDao.insertSelective(info);
						if (usrInfoNum==0) {
							//如果返回为0 则删除urs用户  避免注册出现手机号重复
							ursDao.deleteUrsByUid(usrId);
							return new BaseResponse(ResponseCode.FAILURE,"注册异常");
						}
					} catch (Exception e) {
						ursDao.deleteUrsByUid(usrId);
						e.printStackTrace();
						return new BaseResponse(ResponseCode.FAILURE,"注册异常");
					}
					
					//用户注册完成 生产钱包
					//调用生成会员钱包    生成直播钱包在创建腾讯云账户的时候 有生成
					//调用支付服务创建直播钱包
					SynthesizeService.Client client = null;
					try {
						TMultiplexedProtocol tMultiplexedProtocol = thriftUtil.getProtocol("SynthesizeService");
						client = new SynthesizeService.Client(tMultiplexedProtocol);
						thriftUtil.openTransport();
						int responseNum =	client.addWallet(usrId+"", "1", "","" );
						if(responseNum>0){
							log.info("创建用户钱包失败,错误信息："+usrId+"，用户："+usrId);
							return new BaseResponse(ResponseCode.FAILURE,"创建用户钱包失败");
						}
						log.info("创建用户钱包成功,用户："+usrId);
					}catch(Exception e){
						log.info("创建直播用户钱包失败"+usrId);
						e.printStackTrace();
						return new BaseResponse(ResponseCode.FAILURE,"创建用户钱包失败");
					}
					
					//调用支付服务生成会员直播钱包   新用户注册立松50鸟豆
					//调用支付服务创建直播钱包
					LiveWalletService.Client LiveClient = null;
					log.info("调用支付服务创建直播钱包");
					try {
						log.info("获取调用支付服务连接");
						TMultiplexedProtocol tMultiplexedProtocol = thriftUtil.getProtocol("LiveWalletService");
						LiveClient = new LiveWalletService.Client(tMultiplexedProtocol);
						thriftUtil.openTransport();
						log.info("调用支付服务方法");
						ResponseData responseData =	LiveClient.addLiveWallet(usrId+"");
						log.info("调用支付服务方法成功");
						if(responseData.getState()!=0){
							log.info("创建直播用户钱包失败,错误信息："+responseData.getMsg()+"，用户："+usrId);
							return new BaseResponse(ResponseCode.FAILURE,"创建直播用户钱包失败");
						}
						log.info("创建直播用户钱包成功,用户："+usrId);
					}catch(Exception e){
						log.info("创建直播用户钱包失败");
						e.printStackTrace();
						return new BaseResponse(ResponseCode.FAILURE,"创建直播用户钱包失败");
					}finally{
						thriftUtil.coloseTransport();
					}
					
					//MQ实现赠送注册大礼包
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("uid", usrId);
					producerServiceImpl.send(userRegisterInfo, jsonObject.toString());
					
					//自动登录
					LoginRequest loginRequest = new LoginRequest();
					loginRequest.setPhone(request.getPhone());
					loginRequest.setPassWord(request.getPassWord());
					loginRequest.setBrand(request.getBrand());
					loginRequest.setModel(request.getModel());
					return this.userLogin(loginRequest);
					
				}else {
					return new MapResponse(ResponseCode.FAILURE, "密码不一致");
				}
			}else {
				return new MapResponse(ResponseCode.FAILURE, "密码不一致");
			}
		} catch (Exception e) {
			stringRedisTemplate.delete(registerkey);
			e.printStackTrace();
			log.info("注册用户出错:"+request.getPhone());
			return new MapResponse(ResponseCode.FAILURE, "注册异常");
		}
//		response = new MapResponse(ResponseCode.SUCCESS, "注册成功");
//		response.setResponse(resultMap);
//		return  response;
	}
	
	/**
	 * 判断账户是否被限制登录
	 * */
	public MapResponse pullToBlackState(int state){
		switch (state) {
			case 2:return new MapResponse(ResponseCode.FAILURE, "该账户被锁定,请联系客服!"); 
			case 3:return new MapResponse(ResponseCode.FAILURE, "该账户被注销,请联系客服!"); 
			case 4:return new MapResponse(ResponseCode.FAILURE, "该账户被平台限制登录,请联系客服!"); 
			default:return new MapResponse(ResponseCode.FAILURE, "登录异常请重试!");
		}
	}
	
}
