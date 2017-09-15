package com.xmniao.xmn.core.personal.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xmniao.xmn.core.api.controller.personal.ReceivingAddressListApi;
import com.xmniao.xmn.core.base.BaseRequest;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.Constant;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.SendCodeRequest;
import com.xmniao.xmn.core.common.request.TypeRequest;
import com.xmniao.xmn.core.common.request.integral.PageTypeRequest;
import com.xmniao.xmn.core.common.request.live.CheckRoomPwdRequest;
import com.xmniao.xmn.core.common.request.personal.AddPersonalMentionAccountRequest;
import com.xmniao.xmn.core.common.request.personal.CheckWalletPwdRequest;
import com.xmniao.xmn.core.common.request.personal.PersonalConfirmWithdrawRequest;
import com.xmniao.xmn.core.common.request.personal.PersonalUnbundlingAccountRequest;
import com.xmniao.xmn.core.common.request.personal.ResetLoginPasswordRequest;
import com.xmniao.xmn.core.common.request.personal.ResetWithdrawPasswordRequest;
import com.xmniao.xmn.core.live.dao.AnchorLiveRecordDao;
import com.xmniao.xmn.core.live.dao.LiveUserDao;
import com.xmniao.xmn.core.live.entity.LiveRecordInfo;
import com.xmniao.xmn.core.live.service.PersonalCenterService;
import com.xmniao.xmn.core.thrift.MentionAccount;
import com.xmniao.xmn.core.thrift.MentionAccountService;
import com.xmniao.xmn.core.thrift.ResponsePageList;
import com.xmniao.xmn.core.thrift.SynthesizeService;
import com.xmniao.xmn.core.thrift.WithdrawMoneyService;
import com.xmniao.xmn.core.thrift.XmniaoWalletService;
import com.xmniao.xmn.core.util.ArithUtil;
import com.xmniao.xmn.core.util.DateUtil;
import com.xmniao.xmn.core.util.PropertiesUtil;
import com.xmniao.xmn.core.util.ThriftUtil;
import com.xmniao.xmn.core.util.utilClass;
import com.xmniao.xmn.core.verification.dao.UrsDao;
import com.xmniao.xmn.core.verification.entity.Urs;
import com.xmniao.xmn.core.xmer.dao.XmerDao;
import com.xmniao.xmn.core.xmer.service.SendCodeService;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：PersonalInfoService   
* 类描述：   我的个人信息处理service
* 创建人：yezhiyong   
* 创建时间：2016年11月15日 上午11:14:47   
* @version    
*
 */
@Service
public class PersonalInfoService {
	
	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(ReceivingAddressListApi.class);
	
	/**
	 * 注入缓存
	 */
	@Autowired
	private SessionTokenService sessionTokenService;
	
	/**
	 * 注入ursDao
	 */
	@Autowired
	private UrsDao ursDao;
	
	/**
	 * 注入sendCodeService
	 */
	@Autowired
	private SendCodeService sendCodeService;
	
	/**
	 * 注入thriftUtil
	 */
	@Autowired
	private ThriftUtil thriftUtil;
	
	/**
	 * 注入personalCenterService
	 */
	@Autowired
	private PersonalCenterService personalCenterService;
	
	/**
	 * 注入redis缓存
	 */
	@Autowired
	private StringRedisTemplate stringredisTemplate; 
	
	/**
	 * 注入liveUserDao
	 */
	@Autowired
	private LiveUserDao liveUserDao; 
	
	/**
	 * 注入xmerDao
	 */
	@Autowired
	private XmerDao xmerDao;
	
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
	 * 
	* @Title: resetLoginPassword
	* @Description: 重置登录密码
	* @return Object    返回类型
	* @throws
	 */
	public Object resetLoginPassword(ResetLoginPasswordRequest resetLoginPasswordRequest) {
		//验证token
		String uid = sessionTokenService.getStringForValue(resetLoginPasswordRequest.getSessiontoken()) + "";
		if (StringUtils.isEmpty("uid") || "null".equalsIgnoreCase(uid)) {
			return new BaseResponse(ResponseCode.TOKENERR, "token已失效,请重新登录");
		}
		
		try {
			//验证新密码与确认密码
			if (!resetLoginPasswordRequest.getNewPassword().equals(resetLoginPasswordRequest.getConfirmPassword())) {
				return new BaseResponse(ResponseCode.FAILURE, "您输入的新密码与确认密码不一致");
			}
			
			if (resetLoginPasswordRequest.getOldPassword().equals(resetLoginPasswordRequest.getNewPassword())) {
				return new BaseResponse(ResponseCode.FAILURE, "您输入的新密码不能与当前密码一致");
			}
			
			//查询用户信息
			Urs urs = ursDao.queryUrsByUid(Integer.parseInt(uid));
			if (urs == null) {
				return new BaseResponse(ResponseCode.FAILURE, "查无此用户信息");
			}
			
			//验证当前密码是否正确
			if (!resetLoginPasswordRequest.getOldPassword().equals(urs.getPassword())) {
				return new BaseResponse(ResponseCode.FAILURE, "您输入的当前密码错误,请重试");
			}
			
			//重置登录密码
			Map<Object, Object> paramMap = new HashMap<>();
			paramMap.put("uid", uid);
			paramMap.put("password", resetLoginPasswordRequest.getNewPassword());
			ursDao.updateUrsByUid(paramMap);
			
			//响应
			return new BaseResponse(ResponseCode.SUCCESS, "重置登录密码成功");
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
			log.info("重置登录密码失败,用户uid=" + uid + ",错误信息如下:" + e.getMessage());
			return new BaseResponse(ResponseCode.FAILURE, "未知错误,请联系客服");
		}
	}

	/**
	 * 
	* @Title: resetWithdrawPassword
	* @Description: 重置提现密码
	* @return Object    返回类型
	* @throws
	 */
	public Object resetWithdrawPassword(ResetWithdrawPasswordRequest resetWithdrawPasswordRequest) {
		//验证token
		String uid = sessionTokenService.getStringForValue(resetWithdrawPasswordRequest.getSessiontoken()) + "";
		if (StringUtils.isEmpty("uid") || "null".equalsIgnoreCase(uid)) {
			return new BaseResponse(ResponseCode.TOKENERR, "token已失效,请重新登录");
		}
		
		try {
			//查询用户信息
			Urs urs = ursDao.queryUrsByUid(Integer.parseInt(uid));
			if (urs == null) {
				return new BaseResponse(ResponseCode.FAILURE, "查无此用户信息");
			}
			
			if (!resetWithdrawPasswordRequest.getPhone().equals(urs.getUname())) {
				return new BaseResponse(ResponseCode.FAILURE, "手机号码与登录的手机号码不一致");
			}
			
			//短信发给的手机号码
			String strphone = resetWithdrawPasswordRequest.getPhone();
			
			//获取此用户的redis验证码缓存
			String codekey = Constant.WITHDRAW_VALIDATE_CODE + strphone;
			String code = sessionTokenService.getStringForValue(codekey) + "";
			
			if (StringUtils.isEmpty(code) || "null".equalsIgnoreCase(code)) {
				return new BaseResponse(ResponseCode.FAILURE, "验证码已过期,请重试");
			}
			
			//验证验证码
			if (!resetWithdrawPasswordRequest.getCode().equalsIgnoreCase(code)) {
				return new BaseResponse(ResponseCode.FAILURE, "验证码错误");
			}
			
			try {
				//重置提现密码(userType 用户类型 1用户 2商家3合作商)
				Map<String, String> walletBalanceMap = this.setWPwd(uid, "1", resetWithdrawPasswordRequest.getPassword());
				if (Integer.parseInt(walletBalanceMap.get("state")) != 0) {
					log.info("调用支付业务系统,设置提现密码失败,用户uid=" + uid);
					return new BaseResponse(ResponseCode.FAILURE, "未知错误,请联系客服");
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.info("调用支付业务系统,设置提现密码失败,用户uid=" + uid);
				return new BaseResponse(ResponseCode.FAILURE, "调用支付业务系统,设置提现密码失败");
			}
			
			//清除redis提现密码错误次数
			String key = Constant.WALLET_PWD_ERROR_NUM_KEY + uid;
			if (stringredisTemplate.hasKey(key)) {
				stringredisTemplate.delete(key);
			}
			
			//重置提现密码成功
			return new BaseResponse(ResponseCode.SUCCESS, "重置提现密码成功");
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("重置提现密码失败,用户uid=" + uid + ",错误信息如下:" + e.getMessage());
			return new BaseResponse(ResponseCode.FAILURE, "重置提现密码失败");
		}
	}
	
	/**
	 * @throws Exception 
	 * 
	* @Title: setWPwd
	* @Description: 调用支付业务系统，设置钱包密码(userType 用户类型 1用户 2商家 3合作商)
	* @return Map<String,String>    返回类型
	* @throws
	 */
	public Map<String, String> setWPwd(String uid,String userType,String password) throws Exception {
		//设置钱包密码
		SynthesizeService.Client client = null;
		Map<String, String> walletBalanceMap=null;
		try {
			TMultiplexedProtocol tMultiplexedProtocol = thriftUtil.getProtocol("SynthesizeService");
			 client =new SynthesizeService.Client(tMultiplexedProtocol);
			thriftUtil.openTransport();
			//userType用户类型 1用户 2商家3合作商
			walletBalanceMap =	client.setWPwd(uid, userType, password);
			
		}catch(Exception e){
			log.error("调用支付业务系统,设置钱包密码失败");
			e.printStackTrace();
			throw new Exception("设置钱包密码失败");
		}finally{
			thriftUtil.coloseTransport();
		}
		
		return walletBalanceMap;
	}

	/**
	 * 
	* @Title: sendWithdrawCode
	* @Description: 发送提现验证码
	* @return Object    返回类型
	* @throws
	 */
	public Object sendWithdrawCode(SendCodeRequest sendCodeRequest) {
		//验证token
		String uid = sessionTokenService.getStringForValue(sendCodeRequest.getSessiontoken()) + "";
		if (StringUtils.isEmpty("uid") || "null".equalsIgnoreCase(uid)) {
			return new BaseResponse(ResponseCode.TOKENERR, "token已失效,请重新登录");
		}
		
		try {
			//查询用户信息
			Urs urs = ursDao.queryUrsByUid(Integer.parseInt(uid));
			if (urs == null) {
				return new BaseResponse(ResponseCode.FAILURE, "查无此用户信息");
			}
			
			if (!sendCodeRequest.getPhone().equals(urs.getUname())) {
				return new BaseResponse(ResponseCode.FAILURE, "手机号码与登录的手机号码不一致");
			}
			
			//生成验证码
			Integer code = 0;
			if (sendCodeRequest.getSendType() == 1) {
				// 四位随机数
				code = utilClass.RandomNum();
			}else {
				// 六位数随机数
				code = utilClass.Random6Num();
			}
			
			//短信发给的手机号码
			String strphone = sendCodeRequest.getPhone();
			
			//发送验证码
			sendCodeRequest.setSendType(code);
			String msg = sendCodeService.sendCode(sendCodeRequest);
			if (msg != null && !msg.equals("")) {
				String state = JSON.parseObject(msg).getString("state");
				if (state.equals("100")) {
					String codekey = Constant.WITHDRAW_VALIDATE_CODE + strphone;
					log.info("验证码在redis的key:" + codekey);
					sessionTokenService.setStringForValue(codekey,String.valueOf(code), 2, TimeUnit.MINUTES); //缓存二分钟
					return new BaseResponse(ResponseCode.SUCCESS, "验证码发送成功！");
					
				}
			}
			
			return new BaseResponse(ResponseCode.FAILURE, "发送验证码失败!");
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("发送验证码失败,用户uid=" + uid + "手机号码" + sendCodeRequest.getPhone() + ",错误信息如下:" + e.getMessage());
			return new BaseResponse(ResponseCode.FAILURE, "发送验证码失败!");
		}
	}

	/**
	 * 
	* @Title: queryPersonalBalanceInfo
	* @Description: 我的余额信息(点击用户主页的余额)
	* @return Object    返回类型
	* @throws
	 */
	public Object queryPersonalBalanceInfo(BaseRequest baseRequest) {
		//验证token
		String uid = sessionTokenService.getStringForValue(baseRequest.getSessiontoken()) + "";
		if (StringUtils.isEmpty("uid") || "null".equalsIgnoreCase(uid)) {
			return new BaseResponse(ResponseCode.TOKENERR, "token已失效,请重新登录");
		}
		
		try {
			//结果集
			Map<Object, Object> resultMap = new HashMap<>();
			resultMap.put("isAnchorOrXmer", 0);
			
			//普通用户,余额满100条件时,提现按钮
			resultMap.put("balanceLimit", propertiesUtil.getValue("balanceLimit", "conf_common.properties"));
			
			//查询直播用户信息
			Map<Object, Object> liverMap = liveUserDao.queryLiverInfoByUid(Integer.parseInt(uid));
			if (liverMap != null && liverMap.size() > 0 && Integer.parseInt(liverMap.get("utype").toString()) == 1) {
				resultMap.put("isAnchorOrXmer", 1);
			}
			
			// 查询是否是寻蜜客
			Map<Object, Object> xmerMap = xmerDao.queryXmerInfo(Integer.parseInt(uid));
			if (xmerMap != null && xmerMap.size() > 0) {
				resultMap.put("isAnchorOrXmer", 1);
			}
			
			//设置Android 是否可用提现  
			if(propertiesUtil.getValue("isGetBalance", "conf_live.properties").equals("0")){
				resultMap.put("isAnchorOrXmer", 0);
			}
			
			DecimalFormat df = new DecimalFormat("0.00");//格式化数据
			//查询寻蜜鸟钱包信息
			Map<String, String> walletMap= personalCenterService.getWalletMoney(uid, 1);
			
			//格式化可提现金额
			BigDecimal commision = new BigDecimal(walletMap.get("commision"));
			String withdrawMoney = df.format(commision.setScale(2, RoundingMode.HALF_UP).doubleValue());
			resultMap.put("withdrawMoney", withdrawMoney);
			
			//不可提现金额 = 分账余额 + 赠送余额
			String cannotWithdrawMoney = df.format(ArithUtil.add(Double.parseDouble(walletMap.get("balance")), Double.parseDouble(walletMap.get("zbalance"))));
			resultMap.put("cannotWithdrawMoney", cannotWithdrawMoney);
			
			//有无提现密码, 0 无 1有
			resultMap.put("noWithdrawPwd", 0);
			
			Integer updatePwdNum = null;
			try {
				//调用支付业务系统,获取修改提现密码次数(userType 用户类型 1用户 2商家 3合作商)
				updatePwdNum = this.getUpdatePwdNum(uid, "1");
				if (updatePwdNum != null && updatePwdNum > 0) {
					resultMap.put("noWithdrawPwd", 1);
				}
				
			} catch (Exception e1) {
				e1.printStackTrace();
				log.info("调用支付业务系统,获取修改提现密码次数失败,用户uid=" + uid);
				return new BaseResponse(ResponseCode.FAILURE,"调用支付业务系统,获取我的余额信息失败");
			}
			
			//剩余可输入提现密码次数
			resultMap.put("remainNums", 3);
			
			//获取redis提现密码错误次数
			String key = Constant.WALLET_PWD_ERROR_NUM_KEY + uid;
			if (stringredisTemplate.hasKey(key)) {
				Integer pwdErrorNums = Integer.parseInt(stringredisTemplate.opsForValue().get(key));
				//计算剩余可输入提现密码次数
				resultMap.put("remainNums", 3-pwdErrorNums < 0?0:3-pwdErrorNums);
			}
			
			//响应
			MapResponse response = new MapResponse(ResponseCode.SUCCESS, "获取我的余额信息成功");
			response.setResponse(resultMap);
			return response;
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("获取我的余额信息失败,用户uid=" + uid + "错误信息如下:" + e.getMessage());
			return new BaseResponse(ResponseCode.FAILURE,"获取我的余额信息失败");
		}
	}

	/**
	 * @throws Exception 
	 * 
	* @Title: getUpdatePwdNum
	* @Description: 调用支付业务系统,获取修改提现密码次数(userType 用户类型 1用户 2商家 3合作商)
	* @return Integer    返回类型
	* @throws
	 */
	public Integer getUpdatePwdNum(String uid,String userType) throws Exception {
		//查询钱包密码修改次数
		SynthesizeService.Client client = null;
		Integer updatePwdNum = null;
		try {
			TMultiplexedProtocol tMultiplexedProtocol = thriftUtil.getProtocol("SynthesizeService");
			 client =new SynthesizeService.Client(tMultiplexedProtocol);
			thriftUtil.openTransport();
			updatePwdNum = client.getUpdatePwdNum(uid, userType);
			log.info("查询用户修改提现密码次数成功,用户："+uid);
		}catch(Exception e){
			log.error("调用支付业务系统,获取修改提现密码次数失败");
			e.printStackTrace();
			throw new Exception("查询用户修改提现密码次数失败");
		}finally{
			thriftUtil.coloseTransport();
		}
		
		return updatePwdNum;
	}
	
	/**
	 * 
	* @Title: queryPersonalBalanceDetailInfo
	* @Description: 我的余额明细/我的积分明细    type类型 1 余额支出明细   2余额收入明细   3 积分支出明细  4积分收入
	* @return Object    返回类型
	* @throws
	 */
	public Object queryPersonalBalanceAndIntegralDetailInfo(PageTypeRequest pageTypeRequest) {
		//验证token
		String uid = sessionTokenService.getStringForValue(pageTypeRequest.getSessiontoken()) + "";
		if (StringUtils.isEmpty("uid") || "null".equalsIgnoreCase(uid)) {
			return new BaseResponse(ResponseCode.TOKENERR, "token已失效,请重新登录");
		}
		
		//结果集
		Map<Object, Object> resultMap = new HashMap<>();
		List<Map<Object, Object>> resultList = new ArrayList<>();
		
		//type类型 1 余额支出明细   2余额收入明细   3 积分支出明细  4积分收入明细
		Integer type = pageTypeRequest.getType();
		
		try {
			
			if ( type < 1 || type > 4) {
				return new BaseResponse(ResponseCode.FAILURE,"请求参数不对");
			}
			
			//组装参数
			Map<String, String> walletMap = new HashMap<>();
			//用户Id或商家Id或合作商Id
			walletMap.put("uId", uid);
			//用户类型1用户 2商家 3合作商
			walletMap.put("userType", "1");
			
			if (type == 1 || type == 2) {
				//金额类型 0全部(不传默认查询全部金额) 1 余额 2 积分
				walletMap.put("walletType", "1");
				//收支类型 0 全部(不传默认查询全部) 1 支出 2 收入
				walletMap.put("listType", type + "");
				
			}else if (type == 3 || type == 4) {
				//金额类型 0全部(不传默认查询全部金额) 1 余额 2 积分
				walletMap.put("walletType", "2");
				//收支类型 0 全部(不传默认查询全部) 1 支出 2 收入
				walletMap.put("listType", type - 2 + "");
				
			} 
			
			//当前页码（默认0）
			walletMap.put("cPage", pageTypeRequest.getPage() + "");
			//页大小
			walletMap.put("pageSize", Constant.PAGE_LIMIT + "");
			
			ResponsePageList userWalletRecordList = null;
			
			try {
				//调用支付业务系统,获取用户钱包收入/支出列表
				userWalletRecordList = this.getUserWalletRecordList(walletMap);
				
			} catch (Exception e) {
				e.printStackTrace();
				if (type == 1 || type == 2) {
					log.info("调用支付业务系统,获取我的余额明细失败,用户uid=" + uid + ",错误信息如下:" + e.getMessage());
					return new BaseResponse(ResponseCode.FAILURE,"调用支付业务系统,获取我的余额明细失败");
					
				}else if (type == 3 || type == 4){
					log.info("调用支付业务系统,获取我的积分明细失败,用户uid=" + uid + ",错误信息如下:" + e.getMessage());
					return new BaseResponse(ResponseCode.FAILURE,"调用支付业务系统,获取我的积分明细失败");
					
				}
			}
			
			if (type == 1 || type == 2) {
				//余额明细
				resultMap.put("balanceDetailInfoList", resultList);
				
			}else if (type == 3 || type == 4) {
				//查询用户钱包余额
				Map<String, String> walletBalanceMap=personalCenterService.getWalletMoney(uid+"", 1);
				log.info("获取用户钱包余额,用户uid="+uid);
				
				//积分余额
				if(walletBalanceMap == null || walletBalanceMap.size() <= 0){
					resultMap.put("integral", "0.00");//积分余额
					
				}else {
					String integral=walletBalanceMap.get("integral")==null?"0":walletBalanceMap.get("integral").toString();
					resultMap.put("integral", new BigDecimal(integral).setScale(2, BigDecimal.ROUND_HALF_UP)+"");
					
				}
				
				//积分明细
				resultMap.put("integralDetailInfoList", resultList);
				
			}
			
			if (userWalletRecordList != null && userWalletRecordList.getPageListSize() > 0) {
				//余额明细列表,积分明细列表
				List<Map<String, String>> recordList = userWalletRecordList.getPageList();
				//调整余额明细列表,积分明细列表返回字段
				resultList = this.getBalanceAndIntegralDetailInfo(recordList,type);
				if (type == 1 || type == 2) {
					//加入至返回结果集
					resultMap.put("balanceDetailInfoList", resultList);
					
				}else if (type == 3 || type == 4) {
					//加入至返回结果集
					resultMap.put("integralDetailInfoList", resultList);
				}
				
			}
			
			//响应
			MapResponse response = new MapResponse(ResponseCode.SUCCESS, "获取我的余额明细成功");
			response.setResponse(resultMap);
			return response;
			
		} catch (Exception e) {
			e.printStackTrace();
			if (type == 1 || type == 2) {
				log.info("获取我的余额明细失败,用户uid=" + uid + "错误信息如下:" + e.getMessage());
				return new BaseResponse(ResponseCode.FAILURE,"获取我的余额明细失败");
				
			}else if (type == 3 || type == 4){
				log.info("获取我的积分明细失败,用户uid=" + uid + "错误信息如下:" + e.getMessage());
				return new BaseResponse(ResponseCode.FAILURE,"获取我的积分明细失败");
				
			}
			
			return new BaseResponse(ResponseCode.FAILURE,"请求参数不对");
		}
	}
	
	/**
	 * @throws IOException 
	 * 
	* @Title: getBalanceAndIntegralDetailInfo
	* @Description: 调整返回余额/积分明细字段(type类型 1 余额支出明细   2余额收入明细   3 积分支出明细  4积分收入)
	* @return List<Map<Object,Object>>    返回类型
	* @throws
	 */
	public List<Map<Object, Object>> getBalanceAndIntegralDetailInfo(List<Map<String, String>> recordList,Integer type) throws IOException {
		//返回结果集
		List<Map<Object, Object>> resultList = new ArrayList<>();
		
		//余额/积分支出/收入明细rtype定义
		JSONArray rtypeListStr = JSONObject.parseArray(propertiesUtil.getValue("rtypeList", "conf_common.properties"));
		
		//#余额支出类型
		JSONObject balancePayTypeListStr = JSONObject.parseObject(propertiesUtil.getValue("balancePayTypeList", "conf_common.properties"));
		
		for (Map<String, String> recordMap : recordList) {
			Map<Object, Object> map = new HashMap<>();
			//收支金额
			String amount = recordMap.get("amount");
			//格式化
			if (type == 1) {
				amount = "-¥" + amount;
				
			}else if (type == 2) {
				amount = "+¥" + amount;
				
			}else if (type == 3) {
				amount = "-" + amount;
				
			}else if (type == 4) {
				amount = "+" + amount;
				
			}
			
			map.put("amount", amount);
			//明细记录时间
			map.put("sdate", DateUtil.format(DateUtil.parse(recordMap.get("sdate")), "yyyy-MM-dd HH:mm"));
			//记录类型
			int rtype = Integer.parseInt(recordMap.get("rtype"));
			
			/**
			 * E10-2 余额-支出明细，现在支出余额途径有：
				提现到银行卡、6
				消费买单、5
				购买积分商品、5
				购买粉丝券、5
				购买鸟豆 37
				
				E16 积分 ，积分获得途径：
				消费买单返的、4
				活动活动（比方现在首页新人注册送积分）28、29、30，35
				
				E10-3  余额-收入类型：
				鸟蛋转入、36
				寻蜜客收入转入、32
				红包活动获得 3
				天降壕礼	40
				
				E16 积分 ，支出途径：
				购买积分商品、5
				直播打赏礼物 38
			 */
			for (int i = 0; i < rtypeListStr.size(); i++) {
				JSONObject jsonObj = JSONObject.parseObject(rtypeListStr.get(i).toString());
				if (rtype == Integer.parseInt(jsonObj.get("rtype").toString())) {
					//记录描述
					map.put("describe", jsonObj.get("title").toString());
					
					//由于rtype = 5 有多种类型消费，统一写成消费买单，如果是积分的rtype则显示为购买积分商品
					if (rtype == 5) {
						if (type == 3 || type == 4) {
							map.put("describe", "购买积分商品");
						}
					}
					break;
				}
			}
			
			//判断直播钱包余额返回类型 
//			if (recordMap.get("exType")!=null && !recordMap.get("exType").equals("")) {
//				if (recordMap.get("exType").toString().equals("1")) {
//					map.put("describe", userWalletRecordRemark(Integer.parseInt(map.get("rtype").toString()) ,1));
					map.put("describe", userWalletRecordRemark(rtype ,1));
//				}
//				if (recordMap.get("exType").toString().equals("2")) {
//					map.put("describe", userWalletRecordRemark(Integer.parseInt(map.get("rtype").toString()),2));
//					map.put("describe", userWalletRecordRemark(rtype ,2));
//				}
//			}else {
//				map.put("describe", userWalletRecordRemark(-1,1));
//			}
//			if (map.get("describe") == null) {
//				map.put("describe", balancePayTypeListStr.get("payType4"));
//			}
			
			resultList.add(map);
		}
		
		return resultList;
		
	}
	
	/**
	 * 描述:钱包余额 收/支 明细描述类型
	 * @param int v (表示钱包rtype 类型)
	 * @return String  返回描述信息
	 * */
	public String userWalletRecordRemark(int v,int walletType){
//		if (walletType==1) {
			switch(v){
				case 0: return "分账";
				case 1: return "订单退款";
				case 2: return "充值";
				case 3: return "赠送";
				case 4: return "赠送积分";
				case 5: return "消费支出";
				case 6: return "提现转出";
				case 7: return "平台赠送";
				case 8: return "平台退款";
				case 9: return "用户退款手续费";
				case 10: return "资金归集";
				case 11: return "打赏";
				case 12: return "平台补贴";
				case 13: return "平台发放佣金";
				case 14: return "支付退回";
				case 15: return "取消提现";
				case 16: return "平台扣款";
				case 17: return "平台发送福利";
				case 18: return "错账处理";
				case 19: return "调单扣回";
				case 20: return "调单收入";
				case 21: return "用户退款";
				case 22: return "线下积分订单分账";
				case 23: return "销售成本返还";
				case 24: return "SAAS分账";
				case 25: return "缴纳押金";
				case 26: return "返还押金";
				case 27: return "押金提现";
				case 28: return "抽奖赠送";
				case 29: return "签到赠送";
				case 30: return "分享赠送";
				case 31: return "内部整理";
				case 32: return "寻蜜客转入";
				case 33: return "线下积分订单退款";
				case 34: return "线上积分订单分账";
				case 35: return "注册赠送";
				case 36: return "鸟蛋提现";
				case 37: return "鸟币充值";
				case 38: return "购买直播礼物";
				case 39: return "商家金额退回";
				case 40: return "平台每日分红";
				case 41: return "商家推荐奖励";
				case 42: return "充值分账";
				case 43: return "扩展钱包综合收益";
				case 44: return "V客推荐";
				case 45: return "V客红包";
				case 46: return "壕赚VIP红包";
				case 47: return "壕赚商户充值红包";
				case 48: return "V客创业管理奖金";
				case 49: return "购买套餐";
				case 50: return "购买粉丝券";
				case 51: return "V客兑换SAAS套餐";
				case 52: return "寻蜜客签约收益转出";
				case 53: return "寻蜜客分账收益";
				case 54: return "V客分账收益";
				case 55: return "中脉分账收益";
				default :return "其他";
			}
//		}
//		if(walletType==2) {
//			switch(v){
//				case 0: return "综合收益";
//				case 1: return "V客推荐";
//				case 2: return "V客红包";
//				case 3: return "壕赚VIP红包";
//				case 4: return "壕赚商户充值红包";
//				case 5: return "V客创业管理奖金";
//				default :return "其他";
//			}
//		}
//		return "其他";
		
	}
	
	
	
	/**
	 * @throws Exception 
	 * 
	* @Title: getUserWalletRecordList
	* @Description: 调用支付业务系统,获取用户钱包收入/支出列表
	* @return ResponsePageList    返回类型
	* @throws
	 */
	public ResponsePageList getUserWalletRecordList(Map<String,String> walletMap) throws Exception {
		//验证钱包密码
		XmniaoWalletService.Client client = null;
		ResponsePageList userWalletRecordList = null;
		try {
			TMultiplexedProtocol tMultiplexedProtocol = thriftUtil.getProtocol("XmniaoWalletService");
			 client =new XmniaoWalletService.Client(tMultiplexedProtocol);
			thriftUtil.openTransport();
			userWalletRecordList = client.getUserWalletRecordList(walletMap);
		}catch(Exception e){
			log.error("调用支付业务系统,获取用户钱包收入/支出列表失败");
			e.printStackTrace();
			throw new Exception("获取用户钱包收入/支出列表失败");
		}finally{
			thriftUtil.coloseTransport();
		}
		
		return userWalletRecordList;
		
	}
	
	/**
	 * 
	* @Title: queryCheckWalletPwd
	* @Description: 验证提现密码
	* @return Object    返回类型
	* @throws
	 */
	public Object queryCheckWalletPwd(CheckWalletPwdRequest checkWalletPwdRequest) {
		//验证token
		String uid = sessionTokenService.getStringForValue(checkWalletPwdRequest.getSessiontoken()) + "";
		if (StringUtils.isEmpty("uid") || "null".equalsIgnoreCase(uid)) {
			return new BaseResponse(ResponseCode.TOKENERR, "token已失效,请重新登录");
		}
		
		try {
			//结果集
			Map<Object, Object> resultMap = new HashMap<>();
			
			//验证的提现密码
			String password = checkWalletPwdRequest.getPassword();
			
			Integer result = null;
			try {
				//调用支付业务系统,验证钱包密码(typeId 用户类型 1用户  2商家 3合作商)
				result = this.checkWalletPwd(uid, password, 1);
			} catch (Exception e1) {
				e1.printStackTrace();
				log.info("调用支付业务系统,验证提现密码失败,用户uid=" + uid);
				return new BaseResponse(ResponseCode.FAILURE,"调用支付业务系统,验证提现密码失败");
			}
			
			//redis提现密码错误次数key
			String key = Constant.WALLET_PWD_ERROR_NUM_KEY + uid;
			if (result == null) {
				log.info("调用支付业务系统,验证提现密码失败,用户uid=" + uid);
				return new BaseResponse(ResponseCode.FAILURE,"未知错误,请联系客服");
				
			}else if (result == 0) {
				//验证成功
				return new BaseResponse(ResponseCode.SUCCESS,"验证提现密码成功");
				
			}else if (result == 1) {
				//存储今晚12点时间秒数
				long endTime = DateUtil.getDayFirstTime(DateUtil.addDay(new Date(), 1)).getTime();
				//现在至今晚12点的时间 
				long timeout = (endTime - new Date().getTime())/1000;
				//今日剩余输入提现密码次数
				int remainNums = 0;
				
				//验证失败,记录提现密码错误次数
				if (stringredisTemplate.hasKey(key)) {
					//提现密码错误次数
					Integer pwdErrorNums  = stringredisTemplate.opsForValue().get(key)==null?0:Integer.parseInt(stringredisTemplate.opsForValue().get(key));
					
					if (pwdErrorNums == 3) {
						return new BaseResponse(ResponseCode.FAILURE,"密码错误达到3次，请明天再试");
					}
					
					//以防记录提现密码错误次数超过2次
					if (pwdErrorNums <= 2) {
						pwdErrorNums = pwdErrorNums + 1;
					}else if (pwdErrorNums > 3) {
						pwdErrorNums = 3;
					}
					
					//今日剩余输入提现密码次数
					remainNums = 3 - pwdErrorNums;
					
					//缓存验证提现密码错误次数
					stringredisTemplate.opsForValue().set(key, pwdErrorNums + "");
					//设置redis key存活时间
					stringredisTemplate.expire(key, timeout, TimeUnit.SECONDS);
					
				}else {
					//今日剩余输入提现密码次数2次
					remainNums = 2;
					//缓存验证提现密码错误次数
					stringredisTemplate.opsForValue().set(key, "1");
					//设置redis key存活时间
					stringredisTemplate.expire(key, timeout, TimeUnit.SECONDS);
				}
				
				//响应
				resultMap.put("remainNums", remainNums);
				MapResponse response = new MapResponse(ResponseCode.WALLET_PWD_ERROR, "提现密码错误");
				response.setResponse(resultMap);
				return response;
				
			}else {
				//异常
				return new BaseResponse(ResponseCode.FAILURE,"未知错误,请联系客服");
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("验证提现密码失败,用户uid=" + uid + "错误信息如下:" + e.getMessage());
			return new BaseResponse(ResponseCode.FAILURE,"验证提现密码失败");
		}
	}

	/**
	 * @throws Exception 
	 * 
	* @Title: checkWalletPwd
	* @Description: 调用支付业务系统,验证钱包密码(typeId 用户类型 1用户  2商家 3合作商)
	* @return Integer    返回类型
	* @throws
	 */
	public Integer checkWalletPwd(String uid,String password,Integer typeId) throws Exception {
		//验证钱包密码
		SynthesizeService.Client client = null;
		Integer result = null;
		try {
			TMultiplexedProtocol tMultiplexedProtocol = thriftUtil.getProtocol("SynthesizeService");
			 client =new SynthesizeService.Client(tMultiplexedProtocol);
			thriftUtil.openTransport();
			result = client.checkWalletPwd(uid, password,typeId);
			log.info("验证钱包密码成功,用户："+uid);
		}catch(Exception e){
			log.error("调用支付业务系统,验证钱包密码失败");
			e.printStackTrace();
			throw new Exception("验证钱包密码失败");
		}finally{
			thriftUtil.coloseTransport();
		}
		
		return result;
	}
	
	/**
	 * 
	* @Title: queryPersonalMentionAccountList
	* @Description: 用户余额提现银行卡列表
	* @return Object    返回类型
	* @throws
	 */
	public Object queryPersonalMentionAccountList(BaseRequest baseRequest) {
		//验证token
		String uid = sessionTokenService.getStringForValue(baseRequest.getSessiontoken()) + "";
		if (StringUtils.isEmpty("uid") || "null".equalsIgnoreCase(uid)) {
			return new BaseResponse(ResponseCode.TOKENERR, "token已失效,请重新登录");
		}
		
		try {
			//结果集
			Map<Object, Object> resultMap = new HashMap<>();
			List<Map<Object, Object>> resultList = new ArrayList<>();
			resultMap.put("mentionAccountList", resultList);
			
			MentionAccount mentionAccountList = null;
			try {
				//调用支付业务系统,获取用户的银行卡列表(islable 是否需要查询银行卡特性标签 0否 1是,userType 用户类型  1用户  2商家 3合作商)
				mentionAccountList = this.getMentionAccountList(uid,"1", "1");
			} catch (Exception e) {
				e.printStackTrace();
				log.info("调用支付业务系统,获取用户余额提现银行卡列表失败,用户uid=" + uid);
				return new BaseResponse(ResponseCode.FAILURE,"调用支付业务系统,获取用户余额提现银行卡列表失败");
			}
			
			//调整返回数据
			if (mentionAccountList != null && mentionAccountList.getAccountListSize() > 0) {
				for (Map<String, String> mentionAccountMap : mentionAccountList.getAccountList()) {
					Map<Object, Object> map = new HashMap<>();
					//银行卡账号id
					map.put("bankId", Integer.parseInt(mentionAccountMap.get("id")));
					//持卡人姓名
					map.put("userName", mentionAccountMap.get("username"));
					//银行卡号
					String bankAccount = mentionAccountMap.get("account");
					//完整银行卡号
					map.put("bankFullAccount",bankAccount);
					//格式化银行卡号
					bankAccount = bankAccount.substring(0, 6) + "***" + bankAccount.substring(bankAccount.length() - 4);
					//银行卡号
					map.put("bankAccount",bankAccount);
					//银行卡尾号
					map.put("tailBankAccount",bankAccount.substring(bankAccount.length() - 4));
					//开户行名称
					map.put("bank", mentionAccountMap.get("bank"));
					//是否是默认绑定银行卡(最近使用或编辑过的)
					map.put("isDefult", Integer.parseInt(mentionAccountMap.get("recently")));
					
					resultList.add(map);
				}
				
				//添加至返回集合集中
				resultMap.put("mentionAccountList", resultList);
			}
			
			DecimalFormat df = new DecimalFormat("0.00");//格式化数据
			//查询寻蜜鸟钱包信息
			Map<String, String> walletMap= personalCenterService.getWalletMoney(uid, 1);
			
			//格式化可提现金额
			BigDecimal commision = new BigDecimal(walletMap.get("commision"));
			String withdrawMoney = df.format(commision.setScale(2, RoundingMode.HALF_UP).doubleValue());
			resultMap.put("withdrawMoney", withdrawMoney);
			
			//不可提现金额 = 分账余额 + 赠送余额
			String cannotWithdrawMoney = df.format(ArithUtil.add(Double.parseDouble(walletMap.get("balance")), Double.parseDouble(walletMap.get("zbalance"))));
			resultMap.put("cannotWithdrawMoney", cannotWithdrawMoney);
			
			//响应
			MapResponse response = new MapResponse(ResponseCode.SUCCESS, "获取用户余额提现银行卡列表成功");
			response.setResponse(resultMap);
			return response;
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("获取用户余额提现银行卡列表失败,用户uid=" + uid + "错误信息如下:" + e.getMessage());
			return new BaseResponse(ResponseCode.FAILURE,"获取用户余额提现银行卡列表失败");
		}
		
	}
	
	/**
	 * @throws Exception 
	 * 
	* @Title: getMentionAccountList
	* @Description: 调用支付业务系统,获取用户的银行卡列表(userType 用户类型  1用户  2商家 3合作商)
	* @return MentionAccount    返回类型
	* @throws
	 */
	public MentionAccount getMentionAccountList(String uid,String islable,String userType) throws Exception {
		MentionAccountService.Client client = null;
		MentionAccount mentionAccountList = null;
		try {
			//组装参数
			Map<String, String> paramMap = new HashMap<>();
			paramMap.put("uId", uid);
			//是否需要查询银行卡特性标签 0否 1是
			paramMap.put("islable", islable); 
			//用户类型  1用户  2商家 3合作商
			paramMap.put("userType", userType);
			TMultiplexedProtocol tMultiplexedProtocol = thriftUtil.getProtocol("MentionAccountService");
			 client =new MentionAccountService.Client(tMultiplexedProtocol);
			thriftUtil.openTransport();
			//获取用户余额提现银行卡列表
			mentionAccountList = client.getMentionAccountList(paramMap);
			log.info("查询用户银行卡列表成功,用户："+uid);
		}catch(Exception e){
			log.error("调用支付业务系统,获取用户的银行卡列表失败");
			e.printStackTrace();
			throw new Exception("获取用户的银行卡列表失败");
		}finally{
			thriftUtil.coloseTransport();
		}
		
		return mentionAccountList;
	}

	/**
	 * 
	* @Title: personalUnbundlingAccount
	* @Description: 解绑用户提现银行卡
	* @return Object    返回类型
	* @throws
	 */
	public Object personalUnbundlingAccount(PersonalUnbundlingAccountRequest personalUnbundlingAccountRequest) {
		//验证token
		String uid = sessionTokenService.getStringForValue(personalUnbundlingAccountRequest.getSessiontoken()) + "";
		if (StringUtils.isEmpty("uid") || "null".equalsIgnoreCase(uid)) {
			return new BaseResponse(ResponseCode.TOKENERR, "token已失效,请重新登录");
		}
		
		try {
			//结果
			Integer result = null;
			//组装参数
			Map<String, String> paramMap = new HashMap<>();
			//账号id
			paramMap.put("id", personalUnbundlingAccountRequest.getBankId() + "");
			//寻蜜鸟会员id
			paramMap.put("uId", uid);
			//用户类型 : 1.用户；2.商家；3合作商
			paramMap.put("userType", "1");
			//帐号类型1 支付宝帐号 2 银行卡号3 U付帐号4 其他帐号
			paramMap.put("type", "2");
			//银行卡号
			paramMap.put("account", personalUnbundlingAccountRequest.getBankAccount());
			
			
			try {
				//调用支付业务系统,解绑用户提现银行卡
				Map<String, String> resultMap = this.unbundlingAccount(paramMap);
				if (resultMap != null && resultMap.size() > 0) {
					result = Integer.parseInt(resultMap.get("state"));
				}else {
					log.info("调用支付业务系统,解绑用户提现银行卡失败,用户uid=" + uid);
					return new BaseResponse(ResponseCode.FAILURE,"未知错误,请联系客服");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				log.info("调用支付业务系统,解绑用户提现银行卡失败,用户uid=" + uid);
				return new BaseResponse(ResponseCode.FAILURE,"调用支付业务系统,解绑用户提现银行卡失败");
			}
			
			if (result == 0) {
				//删除成功
				return new BaseResponse(ResponseCode.SUCCESS,"解绑用户提现银行卡成功");
				
			}else {
				log.info("调用支付业务系统,解绑用户提现银行卡失败,用户uid=" + uid);
				return new BaseResponse(ResponseCode.FAILURE,"未知错误,请联系客服");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("解绑用户提现银行卡失败,用户uid=" + uid + "错误信息如下:" + e.getMessage());
			return new BaseResponse(ResponseCode.FAILURE,"解绑用户提现银行卡失败");
		}
	}
	
	/**
	 * 
	* @Title: unbundlingAccount
	* @Description: 调用支付业务系统,解绑提现银行卡
	* @return Map<String,String>    返回类型
	* @throws
	 */
	public Map<String, String> unbundlingAccount(Map<String, String> paramMap) throws Exception {
		MentionAccountService.Client client = null;
		Map<String, String> resultMap = null;
		try {
			TMultiplexedProtocol tMultiplexedProtocol = thriftUtil.getProtocol("MentionAccountService");
			 client =new MentionAccountService.Client(tMultiplexedProtocol);
			thriftUtil.openTransport();
			//获取用户余额提现银行卡列表
			resultMap = client.unbundlingAccount(paramMap);
		}catch(Exception e){
			log.error("调用支付业务系统,解绑用户提现银行卡失败");
			e.printStackTrace();
			throw new Exception("解绑用户提现银行卡失败");
		}finally{
			thriftUtil.coloseTransport();
		}
		
		return resultMap;
	}
	
	/**
	 * 
	* @Title: addPersonalMentionAccount
	* @Description: 新添银行卡
	* @return Object    返回类型
	* @throws
	 */
	public Object addPersonalMentionAccount(AddPersonalMentionAccountRequest addPersonalMentionAccountRequest) {
		//验证token
		String uid = sessionTokenService.getStringForValue(addPersonalMentionAccountRequest.getSessiontoken()) + "";
		if (StringUtils.isEmpty("uid") || "null".equalsIgnoreCase(uid)) {
			return new BaseResponse(ResponseCode.TOKENERR, "token已失效,请重新登录");
		}
		
		try {
			//组装参数
			Map<String, String> paramMap = new HashMap<>();
			paramMap.put("uId", uid);
			//帐号类型 1 支付宝帐号 2 银行卡号 3 U付帐号 4 其他帐号
			paramMap.put("type", "2");
			//银行卡号
			paramMap.put("account", addPersonalMentionAccountRequest.getBankAccount());
			//银行卡类1 借记卡(储蓄卡)2 信用卡 0 支付宝帐号为0
			paramMap.put("cardType", "1");
			//持卡人姓名
			paramMap.put("userName", addPersonalMentionAccountRequest.getUserName());
			//支行名称
			paramMap.put("bankName", addPersonalMentionAccountRequest.getBankBranch());
			//银行预留手机号
			paramMap.put("mobileId", addPersonalMentionAccountRequest.getMobileId());
			//银行卡提现用途  0  对于用户和合作商此字无效  1 营业收入 2 佣金
			paramMap.put("isuse", "0");
			//用户类型 1用户 2商家 3合作商
			paramMap.put("userType", "1");
			//0 对私  1对公
			paramMap.put("ispublic", "0");
			//银行卡所属省
			paramMap.put("province", addPersonalMentionAccountRequest.getProvince());
			//银行卡所属市
			paramMap.put("cityname", addPersonalMentionAccountRequest.getCity());
			//证件类型列表
			String idTypeList = propertiesUtil.getValue("idTypeList", "conf_common.properties");
			JSONArray jsonArr = JSONObject.parseArray(idTypeList);
			for (int i = 0; i < jsonArr.size(); i++) {
				if (Integer.parseInt(jsonArr.getJSONObject(i).get("idType").toString()) == addPersonalMentionAccountRequest.getIdType()) {
					if (addPersonalMentionAccountRequest.getIdType() == 1 || addPersonalMentionAccountRequest.getIdType() == 2) {
						//1 身份证2 护照3 驾驶证（银行卡必填） {"idType"\:"1","title"\:"居民身份证"},{"idType"\:"2","title"\:"港澳台身份证"},{"idType"\:"3","title"\:"护照"}
						paramMap.put("idtype", "1");
						
					}else {
						paramMap.put("idtype", "2");
						
					}
					break;
				}
			}
			
			//对证件类型进行验证
			if (paramMap.get("idtype") == null || StringUtils.isEmpty(paramMap.get("idtype").toString())) {
				return new BaseResponse(ResponseCode.FAILURE, "证件类型错误");
			}
			
			//证件号码
			paramMap.put("identity", addPersonalMentionAccountRequest.getIdentity());
			
			//查询所属银行的名称更缩写
			Map<Object, Object> bankInfoMap = liveUserDao.queryBankInfoById(addPersonalMentionAccountRequest.getBankListId());
			
			if (bankInfoMap == null || bankInfoMap.size() < 0) {
				return new BaseResponse(ResponseCode.FAILURE, "银行卡所属银行错误");
			}
			
			//开户行名称
			paramMap.put("bank", bankInfoMap.get("bankname")==null?"":bankInfoMap.get("bankname").toString());
			//开户行名称缩写
			paramMap.put("abbrev", bankInfoMap.get("abbrev")==null?"":bankInfoMap.get("abbrev").toString());
			
			
			//调用支付业务系统,新添用户银行卡
			Map<String, String> resultMap = null;
			try {
				//调用支付业务系统,新添用户银行卡
				resultMap = this.addMentionAccount(paramMap);
				if (resultMap != null && Integer.parseInt(resultMap.get("state")) == 0) {
					//新添银行卡成功
					return new BaseResponse(ResponseCode.SUCCESS,"新添银行卡成功");
					
				}else {
					//新添银行卡失败
					log.info("调用支付业务系统,新添银行卡失败,用户uid=" + uid);
					return new BaseResponse(ResponseCode.FAILURE,resultMap.get("msg"));
					
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.info("调用支付业务系统,新添银行卡失败,用户uid=" + uid);
				return new BaseResponse(ResponseCode.FAILURE,"调用支付业务系统,新添银行卡失败");
			}
				
		} catch (Exception e) {
			e.printStackTrace();
			log.info("新添银行卡失败,用户uid=" + uid + "错误信息如下:" + e.getMessage());
			return new BaseResponse(ResponseCode.FAILURE,"新添银行卡失败");
		}
	}
	
	/**
	 * @throws Exception 
	 * 
	* @Title: addMentionAccount
	* @Description: 调用支付业务系统,新添用户银行卡
	* @return Map<String,String>    返回类型
	* @throws
	 */
	public Map<String, String> addMentionAccount(Map<String, String> paramMap) throws Exception {
		MentionAccountService.Client client = null;
		Map<String, String> resultMap = null;
		try {
			TMultiplexedProtocol tMultiplexedProtocol = thriftUtil.getProtocol("MentionAccountService");
			 client =new MentionAccountService.Client(tMultiplexedProtocol);
			thriftUtil.openTransport();
			//新添用户银行卡
			resultMap = client.addMentionAccount(paramMap);
		}catch(Exception e){
			log.error("调用支付业务系统,新添用户银行卡失败");
			e.printStackTrace();
			throw new Exception("新添用户银行卡失败");
		}finally{
			thriftUtil.coloseTransport();
		}
		
		return resultMap;
	}

	/**
	 * 
	* @Title: queryPersonalBankAndIdTypeList
	* @Description: 获取银行列表以及证件类型列表(type = 1  银行卡列表  type = 2 证件类型列表)
	* @return Object    返回类型
	* @throws
	 */
	public Object queryPersonalBankAndIdTypeList(TypeRequest typeRequest) {
		try {
			//结果集
			Map<Object, Object> resultMap = new HashMap<>();
			List<Map<Object, Object>> resultList = new ArrayList<>();
			
			//响应
			MapResponse response = null;
			
			//列表类型(type = 1  银行卡列表  type = 2 证件类型列表)
			Integer type = typeRequest.getType();
			if (type == 1) {
				//查询银行卡所属银行列表
				List<Map<Object, Object>> bankList = liveUserDao.queryBankList();
				if (bankList != null && bankList.size() > 0) {
					for (Map<Object, Object> bankMap : bankList) {
						Map<Object, Object> map = new HashMap<>();
						map.put("bank", bankMap.get("bankname")==null?"":bankMap.get("bankname").toString());
						map.put("bankListId", bankMap.get("id")==null?0:Integer.parseInt(bankMap.get("id").toString()));
						resultList.add(map);
					}
				}
				
				resultMap.put("bankList", resultList);
				response = new MapResponse(ResponseCode.SUCCESS, "获取银卡列表成功");
				
			}else if (type == 2) {
				//证件类型列表
				String idTypeList = propertiesUtil.getValue("idTypeList", "conf_common.properties");
				JSONArray jsonArr = JSONObject.parseArray(idTypeList);
				for (int i = 0; i < jsonArr.size(); i++) {
					Map<Object, Object> map = new HashMap<>();
					map.put("idType", Integer.parseInt(jsonArr.getJSONObject(i).get("idType").toString()));
					map.put("title", jsonArr.getJSONObject(i).get("title"));
					resultList.add(map);
				}
				
				resultMap.put("idTypeList", resultList);
				response = new MapResponse(ResponseCode.SUCCESS, "获取证件列表成功");
				
			}else {
				return new BaseResponse(ResponseCode.FAILURE, "参数有误");
				
			}
			
			//响应
			response.setResponse(resultMap);
			return response;
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("获取银行列表以及证件类型列表失败,错误信息如下:" + e.getMessage());
			return new BaseResponse(ResponseCode.FAILURE,"获取银行列表以及证件类型列表失败");
		}
	}

	/**
	 * 
	* @Title: personalConfirmWithdraw
	* @Description: 用户申请提现
	* @return Object    返回类型
	* @throws
	 */
	public Object personalConfirmWithdraw(PersonalConfirmWithdrawRequest personalConfirmWithdrawRequest) {
		//验证token
		String uid = sessionTokenService.getStringForValue(personalConfirmWithdrawRequest.getSessiontoken()) + "";
		if (StringUtils.isEmpty("uid") || "null".equalsIgnoreCase(uid)) {
			return new BaseResponse(ResponseCode.TOKENERR, "token已失效,请重新登录");
		}
				
		try {
			if (personalConfirmWithdrawRequest.getAmount() < 100) {
				return new BaseResponse(ResponseCode.FAILURE, "提现金额不能低于100"); 
			}
			
			//查询用户信息
			Urs urs = ursDao.queryUrsByUid(Integer.parseInt(uid));
			if (urs == null) {
				return new BaseResponse(ResponseCode.FAILURE, "查无此用户信息");
			}
			
			//组装参数
			List<Map<String, String>> amountMapList = new ArrayList<>();
			Map<String, String> amountMap = new HashMap<>();
			//需要提现的余额类型1 佣金 2 返利 3营收 4钱包
			amountMap.put("balanceType", "1");
			//提现金额
			amountMap.put("amount", personalConfirmWithdrawRequest.getAmount() + "");
			amountMapList.add(amountMap);
			
			Map<String, String> orderMap = new HashMap<>();
			//寻蜜鸟会员id
			orderMap.put("uId", uid);
			//用户类型 1用户  2商家
			orderMap.put("userType", "1");
			//提现目的
			orderMap.put("purpose", "主播/寻蜜鸟用户提现");
			//提现描述
			orderMap.put("tdesc", "主播/寻蜜鸟用户提现");
			//提现账号id
			orderMap.put("mentionAccountId", personalConfirmWithdrawRequest.getBankId() + "");
			//1 商户提现 2 会员提现 3 向蜜客提现 5 连锁店提现
			orderMap.put("cash", "2");
			//提现渠道1 用户版APP 2商户版APP 4 分账系统
			orderMap.put("recchannel", "1");
			//用户昵称或商户名称
			orderMap.put("name", urs.getNname()==null?"":urs.getNname());
			
			//调用支付业务系统,申请提现
			Map<String, String> resultMap = null;
			try {
				//调用支付业务系统,申请提现
				resultMap = this.updateWithdrawalsRecord(amountMapList,orderMap);
			} catch (Exception e) {
				e.printStackTrace();
				log.info("调用支付业务系统,申请提现失败,用户uid=" + uid);
				return new BaseResponse(ResponseCode.FAILURE,"调用支付业务系统,申请提现失败");
			}
			
			if (resultMap == null || Integer.parseInt(resultMap.get("state")) == 2) {
				//申请提现内部错误
				log.info("调用支付业务系统,申请提现失败,用户uid=" + uid);
				return new BaseResponse(ResponseCode.FAILURE,"未知错误,请联系客服");
				
			}else if (Integer.parseInt(resultMap.get("state")) == 0) {
				//申请提现成功
				log.info("调用支付业务系统,申请提现成功,用户uid=" + uid);
				return new BaseResponse(ResponseCode.SUCCESS,"申请提现成功");
				
			}else{
				//申请提现失败
				log.info("调用支付业务系统,申请提现失败,用户uid=" + uid);
				return new BaseResponse(ResponseCode.FAILURE,resultMap.get("msg"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("用户申请提现失败,用户uid=" + uid + "错误信息如下:" + e.getMessage());
			return new BaseResponse(ResponseCode.FAILURE,"用户申请提现失败");
		}
	}
	
	/**
	 * @throws Exception 
	 * 
	* @Title: updateWithdrawalsRecord
	* @Description: 调用支付业务系统,申请提现
	* @return Map<String,String>    返回类型
	* @throws
	 */
	public Map<String, String> updateWithdrawalsRecord(List<Map<String,String>> amountMapList,Map<String,String> orderMap) throws Exception {
		WithdrawMoneyService.Client client = null;
		Map<String, String> resultMap = null;
		try {
			TMultiplexedProtocol tMultiplexedProtocol = thriftUtil.getProtocol("WithdrawMoneyService");
			 client =new WithdrawMoneyService.Client(tMultiplexedProtocol);
			thriftUtil.openTransport();
			//调用支付业务系统,申请提现
			resultMap = client.updateWithdrawalsRecord(amountMapList,orderMap);
		}catch(Exception e){
			log.error("调用支付业务系统,申请提现失败");
			e.printStackTrace();
			throw new Exception("申请提现失败");
		}finally{
			thriftUtil.coloseTransport();
		}
		
		return resultMap;
	}

	/**
	 * 
	* @Title: queryCheckRoomPwd
	* @Description: 验证直播间密码
	* @return Object    返回类型
	* @throws
	 */
	public Object queryCheckRoomPwd(CheckRoomPwdRequest checkRoomPwdRequest) {
		try {
			//直播间密码
			String password = checkRoomPwdRequest.getPassword();
			//直播记录id
			Integer liveRecordId = checkRoomPwdRequest.getLiveRecordId();
			
			//查询直播记录信息
			LiveRecordInfo liveRecordInfo = anchorLiveRecordDao.queryLiveRecordById(liveRecordId);
			if (liveRecordInfo != null) {
				if (password.equals(liveRecordInfo.getAnchor_room_password())) {
					return new BaseResponse(ResponseCode.SUCCESS, "验证直播间密码成功");
					
				}else {
					return new BaseResponse(ResponseCode.FAILURE, "密码错误,请重试");
				}
			}else {
				return new BaseResponse(ResponseCode.FAILURE, "查无此直播记录");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("验证直播间密码失败，错误信息如下:" + e.getMessage());
			return new BaseResponse(ResponseCode.FAILURE, "未知错误,请联系管理员");
		}
	}
}
