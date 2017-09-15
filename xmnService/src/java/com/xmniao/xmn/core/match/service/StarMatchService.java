package com.xmniao.xmn.core.match.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.live.dao.LiveUserDao;
import com.xmniao.xmn.core.match.dao.VstarSignUpDao;
import com.xmniao.xmn.core.match.request.MsgRequest;
import com.xmniao.xmn.core.match.request.StarMatchPageRequest;
import com.xmniao.xmn.core.match.request.StartMatchSignRequest;
import com.xmniao.xmn.core.thrift.ResponseData;
import com.xmniao.xmn.core.thrift.business.java.XmnCommonService;
import com.xmniao.xmn.core.util.DateUtil;
import com.xmniao.xmn.core.util.PropertiesUtil;
import com.xmniao.xmn.core.util.StrUtils;
import com.xmniao.xmn.core.util.ThriftBusinessUtil;

@Service
public class StarMatchService {

	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(StarMatchService.class);
	
	@Autowired
	private SessionTokenService sessionTokenService;
	@Autowired
	private PropertiesUtil propertiesUtil;
	@Autowired
	private LiveUserDao liveUserDao;
	@Autowired
	private VstarSignUpDao vstarSignUpDao;
	@Autowired
	private ThriftBusinessUtil thriftBusinessUtil;
	@Autowired
	private String fileUrl;
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	private final static String CHECK_MSG_CODE="1"; //需要核实验证码标识
	
	public Object signUp(StartMatchSignRequest request) {
		// 获取uid验证用户
		String uid = sessionTokenService.getStringForValue(request.getSessiontoken()) + "";
		if (StringUtils.isEmpty(uid) || "null".equals(uid)) {
			return new BaseResponse(ResponseCode.TOKENERR, "token失效,请重新登录");
		}
		//验证参数的正确
		if (!checkStarMatchSignArgs(request)) {
			return new BaseResponse(ResponseCode.FAILURE, "报名信息错误");
		};
		
		//计数器
		String keyTimes="signUpTimes"+uid;
		try {
		Long resultNum = stringRedisTemplate.opsForValue().increment(keyTimes, 1);
		if (resultNum>1) {
			return new BaseResponse(ResponseCode.FAILURE,"正在提交中，不要重复提交");
		}	
		//是否需要验证短信验证码
		if (CHECK_MSG_CODE.equals(request.getIsNeedMsgCode())){
			String smsCodeKey = "MatchSign_" + request.getPhone();
			String smsCodeRedis = stringRedisTemplate.opsForValue().get(smsCodeKey);
			if (!request.getMsgCode().equals(smsCodeRedis)) {
				return new BaseResponse(ResponseCode.FAILURE, "验证码错误");
			}
		}	
		//判断用户是否报名过(拒绝的可以重新报名)
		Integer isExistEnroll = vstarSignUpDao.countVstarEnroll(Integer.parseInt(uid));
		if (isExistEnroll != 0) {
			return new BaseResponse(ResponseCode.FAILURE, "已存在您的报名信息，请联系管理员!");
		}
		
		//过滤用户空格字符串
		String name = StrUtils.replaceBlank(request.getNname());
		Map<Object,Object> divisionMap = vstarSignUpDao.findVstarDivison(Integer.parseInt(request.getAreaId()));
		Map<Object,Object> liveMap = liveUserDao.queryLiverInfoByUid(Integer.parseInt(uid));
		Map<Object,Object> palySettingMap = vstarSignUpDao.findPlaySetting();
		Integer autoPassFirst = Integer.parseInt(palySettingMap.get("auto_pass_first").toString()); //是否通过海选审核 0 否 ,1 是
		Integer autoPassSecond = Integer.parseInt(palySettingMap.get("auto_pass_second").toString());//是否自动通过试播审核 0 否 1 是
		Map<Object,Object> resultMap = new HashMap<Object,Object>();
		String signUpSuccessUrl = null;
		String h5Domain = propertiesUtil.getValue("Matchh5DoMain", "conf_common.properties");
		
		//验证区域信息
		if (!ObjectUtils.toString(divisionMap.get("province_id")).equals(request.getProvinceId()) || !ObjectUtils.toString(divisionMap.get("city_id")).equals(request.getCityId())) {
			return new BaseResponse(ResponseCode.FAILURE, "报名区域信息错误！");
		}
		//删除历史报名数据
		vstarSignUpDao.deleteVstarEnrollImg(Integer.parseInt(uid));
		vstarSignUpDao.deleteVstarEnrollByUid(Integer.parseInt(uid));
		vstarSignUpDao.deleteVstarPlayInfo(Integer.parseInt(uid));
		
		//1.插入选手表
		Map<Object,Object> vstarMap = new HashMap<Object,Object>();
		vstarMap.put("nname", name); //昵称
		vstarMap.put("phone", request.getPhone()); //手机号
		vstarMap.put("proviceId", request.getProvinceId()); //省份id
		vstarMap.put("cityId", request.getCityId()); //城市id
		vstarMap.put("enroll_time", DateUtil.format(new Date())); //报名时间
		vstarMap.put("areaId", request.getAreaId()); //区域id
		vstarMap.put("uid", Integer.parseInt(uid)); //用户uid
		vstarMap.put("status", 1); //1报名完成
		if (autoPassFirst==1) {
			vstarMap.put("status", 2);
		}
		if(autoPassSecond == 1 && Integer.parseInt(liveMap.get("utype").toString()) == 1){
			vstarMap.put("status", 5);
		}
		vstarSignUpDao.insertVstarEntroll(vstarMap);
		Integer vstarId = Integer.parseInt(vstarMap.get("id").toString());
		//2.插入选手图片
		String[] varr = request.getFileUrls().split(",");
		for(int i=0;i<varr.length;i++) {
			Map<Object,Object> vstarImg = new HashMap<Object,Object>();
			vstarImg.put("sort", i);
			vstarImg.put("imageUrl", varr[i]);
			vstarImg.put("imageType", 101); //报名默认是风采照片类型
			vstarImg.put("pid", vstarId);
			vstarSignUpDao.insertVstarImg(vstarImg);
		}
			//3. 插入选手详情表
			Map<Object, Object> param = new HashMap<Object, Object>();
			param.put("enrollId", vstarId);
			param.put("nname", name);
			param.put("phone", request.getPhone());
			param.put("provinceId", request.getProvinceId());
			param.put("provinceName", divisionMap.get("province_name") );
			param.put("cityId", request.getCityId());
			param.put("cityName", divisionMap.get("city_name"));
			param.put("liverId", liveMap.get("id"));
			param.put("areaId", request.getAreaId());
			param.put("areaName", divisionMap.get("area_name"));
			param.put("divisionId", divisionMap.get("division_id"));
			param.put("divisionName", divisionMap.get("division_name"));
			param.put("createTime", DateUtil.format(new Date()));
			param.put("uid",Integer.parseInt(uid));
			param.put("totalLives", 0);
			param.put("totalLiveTimes", 0);	
			param.put("totalFans", 0);
			param.put("totalSaas", 0);
			param.put("playerType", 0);
			param.put("likeCount", 0);
			param.put("eggCount", 0);
			param.put("commentCount", 0);
			if(autoPassSecond == 1 && Integer.parseInt(liveMap.get("utype").toString()) == 1){//自动审核并且报名用户为主播身份
				//统计主播的相关信息
				Integer totalLive = vstarSignUpDao.countLiveNum(Integer.parseInt(liveMap.get("id").toString())); //统计直播数量
				Integer totalFans = vstarSignUpDao.coutLiveFans(Integer.parseInt(uid));
				Integer totalSignSeller = vstarSignUpDao.countSassSeller(Integer.parseInt(uid));
				Integer totalLiveDuration = liveMap.get("live_duration")==null?0:Integer.parseInt(liveMap.get("live_duration").toString());
				param.put("totalLives", totalLive);
				param.put("totalLiveTimes", totalFans);	
				param.put("totalFans", totalSignSeller);
				param.put("totalSaas", totalLiveDuration);
				signUpSuccessUrl= h5Domain + "?action="+propertiesUtil.getValue("matchSignUpSucessRealCheckedAction", "conf_common.properties");
			}else{
				signUpSuccessUrl= h5Domain +"?action="+ propertiesUtil.getValue("matchSignUpSucessRealUnCheckAction", "conf_common.properties");
			}
			vstarSignUpDao.insertVstarPlayInfo(param);
			
			//4.修改用户为大赛选手--普通用户类型修改为大赛选手
			Map<Object,Object> userMap = new HashMap<Object,Object>();
			if (Integer.parseInt(liveMap.get("utype").toString()) != 1) {
				userMap.put("singleType", 4);
			}
			userMap.put("isVstar", 1);
			userMap.put("uid", Integer.parseInt(uid));
			liveUserDao.updateVstarType(userMap);

			
			//5.发送报名成功短信
			XmnCommonService.Client client = null;
			Map<String, String> sendMap = new HashMap<String, String>();
			sendMap.put("phone", request.getPhone());//request.getPhone()
			sendMap.put("smsContent", "尊敬的用户，你已报名百城中国星食尚大赛。为了顺利参加赛程，请下载寻蜜鸟APP并登录账号。下载地址：www.xunminiao！");
			TMultiplexedProtocol tMultiplexedProtocol = thriftBusinessUtil.getProtocol("XmnCommonService");
			client = new XmnCommonService.Client(tMultiplexedProtocol);
			thriftBusinessUtil.openTransport();
			ResponseData responseData = client.sendXmnSms(sendMap);
			if (responseData != null && responseData.getState() != 0) {
				return new MapResponse(ResponseCode.FAILURE, "发送短信失败");
			}
			resultMap.put("signUpSucessUrl",signUpSuccessUrl );
			MapResponse response = new MapResponse(ResponseCode.SUCCESS,"报名成功");
			response.setResponse(resultMap);
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("报名错误"+e);
			return new BaseResponse(ResponseCode.FAILURE, "报名失败");
		}finally{
			stringRedisTemplate.delete(keyTimes);
		}
	}

	/**检查参数的正确
	 * @param request
	 * @return
	 */
	private boolean checkStarMatchSignArgs(StartMatchSignRequest request) {
		
		String nname = request.getNname();//昵称
		String phone = request.getPhone();//手机号码	
		String fileUrls = request.getFileUrls();//报名图片
		String[] array = StringUtils.split(fileUrls, ",");
		String regExp = "^[1][0-9]{10}$";
		Pattern p = Pattern.compile(regExp);
		Matcher hasNum = p.matcher(phone);
		if (StringUtils.isBlank(nname)){
			return false;
		}	
		if (!hasNum.matches() && phone.length() != 11) {
			return false;
		}
		if (array.length <2 ){//图片不能小于俩张
			return false;
		}		
		return true;
	}

	/**进入报名页面
	 * @param request
	 * @return
	 */
	public Object startMatchPage(StarMatchPageRequest request) {
		// 获取uid验证用户
		String uid = sessionTokenService.getStringForValue(request.getSessiontoken()) + "";
		if (StringUtils.isEmpty(uid) || "null".equals(uid)) {
			return new BaseResponse(ResponseCode.TOKENERR, "token失效,请重新登录");
		}
		Map<Object,Object> resultMap = new HashMap<Object,Object>();
		try {
			String backgroundImg = propertiesUtil.getValue("backGroudMatchSignUp", "conf_common.properties");//报名背景图片
			String version=propertiesUtil.getValue("area.version", "conf_common.properties");//默认版本号
			String agreeMentUrl = propertiesUtil.getValue("MatchAgreeMentUrl", "conf_common.properties");
			String isNeedMsgCode = propertiesUtil.getValue("isNeedMatchSendMsg", "conf_common.properties");
			if (StringUtils.isNotBlank(request.getRuid())) {//分享人信息
				Map<Object,Object> shareUser = liveUserDao.queryLiverInfoById(Integer.parseInt(uid));
				if (shareUser!=null && !shareUser.isEmpty()) {
					resultMap.put("shareUserNname", ObjectUtils.toString(shareUser.get("nname")));
					resultMap.put("shareAntar", ObjectUtils.toString(shareUser.get("avatar")));
					resultMap.put("phone", ObjectUtils.toString(shareUser.get("phone")));
				}	
			}
			JSONArray jsonArr =JSON.parseArray(backgroundImg);
			List<String> imgList = new ArrayList<String>();
			for(int i=0;i<jsonArr.size();i++){
				Object obj = jsonArr.get(i);
				imgList.add(fileUrl+obj);
			}		
			resultMap.put("backgroundImg", imgList);
			resultMap.put("areaList", getStartMatchArea(request,version));
			resultMap.put("areaVersion", version);
			resultMap.put("isNeedMsgCode", isNeedMsgCode);
			if (StringUtils.isNotBlank(agreeMentUrl)){
				resultMap.put("agreement", agreeMentUrl);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info(e+"出错啦");
			return new BaseResponse(ResponseCode.FAILURE, "进入报名页面出错");		
		}
		MapResponse response = new MapResponse(ResponseCode.SUCCESS,"查询成功");
		response.setResponse(resultMap);
		return response;
	}

	/**查询地区
	 * @param request
	 * @return
	 */
	private List<Map<Object,Object>> getStartMatchArea(StarMatchPageRequest request,String version) {
		List<Map<Object,Object>> resultList = null;
		if(!request.getAreaVersion().equals(version)){
			resultList = new ArrayList<Map<Object,Object>>();
			List<Map<Object,Object>> provices = vstarSignUpDao.findVstarProvices();
			if (provices!=null && !provices.isEmpty()) {
				for(int i=0;i<provices.size();i++){
					Integer proviceId = Integer.parseInt(provices.get(i).get("province_id").toString());
					List<Map<Object,Object>> citys = vstarSignUpDao.findVstarCitys(proviceId);
					if (citys!=null && !citys.isEmpty()) {
						for (int j = 0; j < citys.size(); j++) {
					       Integer cityId= Integer.parseInt(citys.get(j).get("city_id").toString());
							List<Map<Object,Object>> countyList=vstarSignUpDao.findAllAreaByCity(cityId);
							countyList.remove(null);
							citys.get(j).put("subSet", countyList);
						}
						citys.remove(null);
						provices.get(i).put("subSet", citys);
					}	
					resultList.add(provices.get(i));
				}	
			}	
		}
		return resultList;
	}
	
	

	/**发送短信验证码
	 * @param request
	 * @return
	 */
	public Object send(MsgRequest request) {
		String regExp = "^[1][0-9]{10}$";
		Pattern p = Pattern.compile(regExp);
		Matcher hasNum = p.matcher(request.getPhone());
		if (!hasNum.matches() && request.getPhone().length() != 11) {
			return new MapResponse(ResponseCode.FAILURE, "请输入正确手机号码");
		}
		// 匹配完毕 发短信
		XmnCommonService.Client client = null;
		// 生成短信验证码key
		int index = (int) ((Math.random() * 9 + 1) * 1000);
		String smsCodeKey = "MatchSign_" + request.getPhone();
		stringRedisTemplate.opsForValue().set(smsCodeKey, index + "");
		stringRedisTemplate.expire(smsCodeKey, 2, TimeUnit.MINUTES);
		// 发送短信
		try {
			Map<String, String> sendMap = new HashMap<String, String>();
			sendMap.put("phone", request.getPhone());
			sendMap.put("smsContent", "验证码:" + index + ",一分钟内有效，欢迎使用寻蜜鸟-鸟人直播!");
			TMultiplexedProtocol tMultiplexedProtocol = thriftBusinessUtil.getProtocol("XmnCommonService");
			client = new XmnCommonService.Client(tMultiplexedProtocol);
			thriftBusinessUtil.openTransport();
			ResponseData responseData = client.sendXmnSms(sendMap);
			if (responseData != null && responseData.getState() != 0) {
				return new MapResponse(ResponseCode.FAILURE, "发送短信失败");
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE, "发送失败");
		}
		return new BaseResponse(ResponseCode.SUCCESS, "发送成功");
	}
}
