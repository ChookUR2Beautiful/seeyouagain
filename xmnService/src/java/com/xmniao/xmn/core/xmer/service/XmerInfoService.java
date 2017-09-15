package com.xmniao.xmn.core.xmer.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.xmniao.xmn.core.base.BaseRequest;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.Constant;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.AddabnormalRequest;
import com.xmniao.xmn.core.common.request.EditXmerInfoRequest;
import com.xmniao.xmn.core.common.request.FeedBackRequest;
import com.xmniao.xmn.core.common.request.HandleCKRequest;
import com.xmniao.xmn.core.common.request.ModifyXmerInfoRequest;
import com.xmniao.xmn.core.common.request.PhoneRequest;
import com.xmniao.xmn.core.common.request.ShopPicRequest;
import com.xmniao.xmn.core.common.service.FileService;
import com.xmniao.xmn.core.live.dao.LiveUserDao;
import com.xmniao.xmn.core.thrift.FailureException;
import com.xmniao.xmn.core.thrift.ResponseData;
import com.xmniao.xmn.core.thrift.SynthesizeService;
import com.xmniao.xmn.core.thrift.XmerWalletService;
import com.xmniao.xmn.core.thrift.client.proxy.ThriftClientProxy;
import com.xmniao.xmn.core.util.DateUtil;
import com.xmniao.xmn.core.util.EmojiFilter;
import com.xmniao.xmn.core.util.PropertiesUtil;
import com.xmniao.xmn.core.util.TLSUtil;
import com.xmniao.xmn.core.verification.dao.BillDao;
import com.xmniao.xmn.core.verification.dao.UrsDao;
import com.xmniao.xmn.core.verification.dao.UrsInfoDao;
import com.xmniao.xmn.core.verification.dao.WalletDao;
import com.xmniao.xmn.core.verification.entity.Urs;
import com.xmniao.xmn.core.verification.entity.UrsInfo;
import com.xmniao.xmn.core.verification.entity.Wallet;
import com.xmniao.xmn.core.xmer.dao.AbnormalDao;
import com.xmniao.xmn.core.xmer.dao.FriendshipDao;
import com.xmniao.xmn.core.xmer.dao.SaasOrderDao;
import com.xmniao.xmn.core.xmer.dao.SellerInfoDao;
import com.xmniao.xmn.core.xmer.dao.WithDrawCashDao;
import com.xmniao.xmn.core.xmer.dao.XmerDao;
import com.xmniao.xmn.core.xmer.entity.Xmer;

/**
 * 项目名称：xmnService
 * 
 * 类名称：XmerInfoService
 * 
 * 创建人： huang'tao
 * 
 * 创建时间：2016-5-19下午6:27:18
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@Service
public class XmerInfoService {

	// 日志
	private Logger log = Logger.getLogger(XmerInfoService.class);

	/**
	 * 注入寻密客用户Dao层
	 */
	@Autowired
	private XmerDao xmerDao;

	/**
	 * 注入用户详细信息Dao
	 */
	@Autowired
	private UrsInfoDao ursInfoDao;

	/**
	 * 注入订单Dao
	 */
	@Autowired
	private BillDao billDao;

	/**
	 * 注入saas套餐Dao
	 */
	@Autowired
	private SaasOrderDao saasOrderDao;

	/**
	 * 注入缓存
	 */
	@Autowired
	private SessionTokenService sessionTokenService;

	@Autowired
	private FriendshipDao friendShinpDao;

	@Autowired
	private WalletDao walletDao;

	@Autowired
	private UrsDao ursDao;

	@Autowired
	private WithDrawCashDao withDrawCashDao;

	@Autowired
	private LiveUserDao liveUserDao;
	
	@Autowired
	private PropertiesUtil propertiesUtil;

	@Autowired
	private SellerInfoService sellerInfoService;
	
	/**
	 * 支付服务接口
	 */
	@Resource(name = "xmerWalletServiceClient")
	private ThriftClientProxy xmerWalletServiceClient;
	
	
	@Resource(name="synthesizeServiceClient")
	private ThriftClientProxy synthesizeServiceClient;

	/**
	 * 服务器地址
	 */
	@Autowired
	private String fileUrl;

	/**
	 * 注入商户信息
	 */
	@Autowired
	private SellerInfoDao sellerInfoDao;

	/**
	 * 注入redis缓存
	 */
	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	/**
	 * 报错投诉
	 */
	@Autowired
	private AbnormalDao abnormalDao;
	
	@Autowired
	private FileService fileService;

	/**
	 * 编辑寻蜜客信息
	 * 
	 * @param request
	 * @return
	 */
	public Object editXmer(EditXmerInfoRequest request) {
		String sessiontoken = request.getSessiontoken();
		String uid = sessionTokenService.getStringForValue(sessiontoken).toString();
		request.setUid(Integer.valueOf(uid));
		// 查询是否存在已启用的寻蜜客信息
		Xmer oldXmer = selectByUid(Integer.valueOf(uid));
		// 查询是否存在已解除的寻蜜客信息
		Xmer oldRelieveXmer = xmerDao.queryByUidAndStatus(Integer.valueOf(uid));
		Xmer xmer = new Xmer();
		// 查询用户详细信息
		UrsInfo ursInfo = ursInfoDao.queryUrsInfoByUid(Integer.valueOf(uid));
		if (ursInfo == null) {
			return new BaseResponse(ResponseCode.NOTUSER, "账号不存在,请联系商家");
		}
		// 保存寻蜜客手机号码
		xmer.setPhoneid(ursInfo.getPhone());
		// 修改usr缓存key
		String userKey = Constant.USERID_ + uid;
		if (stringRedisTemplate.hasKey(userKey)) {
			stringRedisTemplate.delete(userKey);
		}
		// 查询是否购买过saas套餐
		Map<Object, Object> saasOrderMap = saasOrderDao.queryOrderNums(Integer
				.valueOf(uid));
		Integer parentid = null;
		Integer level = 0;
		// 没有记录，
		if (null == oldXmer && null == oldRelieveXmer) {
			if (saasOrderMap != null) {
				parentid = (Integer) saasOrderMap.get("parentid");
				if (parentid != null) {
					Xmer parentXmer = xmerDao.selectByUid(parentid);
					level = parentXmer.getLevels();
					Integer topPid = parentXmer.getParentid(); // 顶级寻蜜客的uid
					if (topPid != null && topPid != 0) { // 判断如果还有上一级则继续给上一级添加伙伴数量
						xmerDao.addpartner(topPid); // 顶级寻蜜客伙伴数量加一
					}
					xmerDao.addpartner(parentid);
				}
				xmer.setAchievement("初来乍到");
				xmer.setLevels(level + 1);
				xmer.setRtype(1);
				xmer.setUid(Integer.parseInt(uid));// 用户id
				xmer.setStatus(0);
				xmer.setSdate(new Date());
				xmer.setSoldNums(0);
				xmer.setUpdate_date(new Date());
				xmer.setParentid(parentid);
				xmer.setPartnerNums(0);
				xmer.setWeixinid(request.getWechatnum());// 微信账号
				xmer.setEmail(request.getUseremail());// 邮箱
				// 插入寻蜜客信息
				xmerDao.insertSelective(xmer);
				// 更改头像
				ModifyXmerInfoRequest modifyXmerInfoRequest = new ModifyXmerInfoRequest();
				modifyXmerInfoRequest.setUid(Integer.parseInt(uid));
				modifyXmerInfoRequest.setFieldname("avatar");
				modifyXmerInfoRequest.setFieldvalue(request.getAvatar());
				modifyUrsInfo(modifyXmerInfoRequest);
				// 更改姓名
				modifyXmerInfoRequest.setFieldname("name");
				modifyXmerInfoRequest.setFieldvalue(request.getUserrname());
				modifyUrsInfo(modifyXmerInfoRequest);

				// 调用支付服务创建钱包
				XmerWalletService.Client client = null;
				try {
					client = (XmerWalletService.Client) (xmerWalletServiceClient
							.getClient());
					Map<String, String> walletMap = new HashMap<String, String>();
					walletMap.put("uid", uid);
					walletMap.put("uname", request.getUserrname());
					Wallet wallet = walletDao.getWalletByUid(Integer
							.parseInt(uid));
					walletMap.put("fatherId", wallet.getAccountid() + "");
					ResponseData responseData = client.addXmerWallet(walletMap);
					if (responseData.getState() == 1) {
						log.error("寻创建寻蜜客钱包失败,错误信息：" + responseData.getMsg()
								+ "，用户：" + uid);
					}
					return new BaseResponse(ResponseCode.SUCCESS, "编辑寻蜜客资料成功");
				} catch (FailureException e) {
					log.error("寻创建寻蜜客钱包失败,错误信息：" + e.getInfo() + "，用户：" + uid);
					if (xmerWalletServiceClient != null) {
						xmerWalletServiceClient.returnCon();
					}
					return new BaseResponse(ResponseCode.FAILURE, "寻蜜客转出金额失败");

				} catch (TException e) {
					log.error("创建寻蜜客钱包失败,错误信息：" + e.getMessage() + "，用户：" + uid);
					if (xmerWalletServiceClient != null) {
						xmerWalletServiceClient.returnCon();
					}

					return new BaseResponse(ResponseCode.FAILURE, "寻蜜客转出金额失败");

				} catch (Exception e) {
					log.error("创建寻蜜客钱包失败,错误信息：" + e.getMessage() + "，用户：" + uid);
					if (xmerWalletServiceClient != null) {
						xmerWalletServiceClient.returnCon();
					}
					return new BaseResponse(ResponseCode.FAILURE, "寻蜜客转出金额失败");

				} finally {
					if (xmerWalletServiceClient != null) {
						xmerWalletServiceClient.returnCon();
					}
				}

			}
			return new BaseResponse(ResponseCode.XMER_NOT_EXIST,
					"SAAS套餐支付失败，无法创建寻蜜客");
			// 存在已解除的寻蜜客资料
		} else if (null == oldXmer && null != oldRelieveXmer) {
			return new BaseResponse(ResponseCode.XMER_HAD_RELIEVE,
					"寻蜜客已解除，无法编辑资料");

			/*
			 * if (saasOrderMap.get("parentid") != null) { parentid =
			 * Integer.parseInt(saasOrderMap.get("parentid").toString()); }
			 * oldRelieveXmer.setAchievement("初来乍到");
			 * oldRelieveXmer.setLevels(level+1); oldRelieveXmer.setSoldNums(0);
			 * oldRelieveXmer.setStatus(0); oldRelieveXmer.setSdate(new Date());
			 * oldRelieveXmer.setSoldNums(0); oldRelieveXmer.setUpdate_date(new
			 * Date()); oldRelieveXmer.setLevelSwap("");
			 * oldRelieveXmer.setParentid(parentid);
			 * oldRelieveXmer.setPartnerNums(0);
			 * xmerDao.updateByUid(oldRelieveXmer); //更改头像 ModifyXmerInfoRequest
			 * modifyXmerInfoRequest = new ModifyXmerInfoRequest();
			 * modifyXmerInfoRequest.setFieldname("avatar");
			 * modifyXmerInfoRequest.setFieldvalue(request.getAvatar());
			 * modifyUrsInfo(modifyXmerInfoRequest); //更改姓名
			 * modifyXmerInfoRequest.setFieldname("name");
			 * modifyXmerInfoRequest.setFieldvalue(request.getUserrname());
			 * modifyUrsInfo(modifyXmerInfoRequest); return new
			 * BaseResponse(ResponseCode.SUCCESS, "编辑寻蜜客资料成功");
			 */
		} else {
			// 更新寻蜜客信息
			oldXmer.setUid(request.getUid());
			oldXmer.setEmail(request.getUseremail());
			oldXmer.setWeixinid(request.getWechatnum());
			xmerDao.updateByUid(oldXmer);
			// 更新用户的信息
			updateUrsInfo(request);
			// 存储到缓存中
			Map<Object, Object> redisMap = new HashMap<Object, Object>();
			String avatar = request.getAvatar();
			avatar = avatar.replace(fileUrl, "");
			String name = request.getUserrname();
			String weixinid = request.getWechatnum();
			redisMap.put("avatar", avatar); // 头像
			redisMap.put("name", name); // 姓名
			redisMap.put("weixinid", weixinid); // 微信号
			redisMap.put("deposit", 0D + "");
			redisMap.put("returnDeposit", 0D + "");
			redisMap.put("incomeAmount", 0D + "");
			redisMap.put("flowAmount", 0D + "");
			redisMap.put("partners", 0 + "");
			redisMap.put("merchants", 0 + "");
			return new BaseResponse(ResponseCode.SUCCESS, "修改成功");
		}
	}

	/**
	 * 更新用户详细信息
	 * 
	 * @param request
	 */
	private void updateUrsInfo(EditXmerInfoRequest request) {
		String avatar = request.getAvatar();
		UrsInfo ursInfo = new UrsInfo();
		ursInfo.setUid(request.getUid());
		avatar = avatar.replace(fileUrl, "");
		ursInfo.setAvatar(avatar);
		ursInfo.setName(request.getUserrname());
		ursInfoDao.updateByPrimaryKey(ursInfo);
	}

	/**
	 * 通过用户ID查询返回寻蜜客信息
	 * 
	 * @param uid
	 * @return
	 */
	public Xmer selectByUid(Integer uid) {
		return xmerDao.selectByUid(uid);
	}

	/**
	 * 通过用户ID查询返回用户详细信息
	 * 
	 * @param uid
	 * @return
	 */
	public UrsInfo selectUrsInfoByUid(Integer uid) {
		return ursInfoDao.queryUrsInfoByUid(uid);
	}

	/**
	 * 修改寻蜜客信息
	 * 
	 * @return
	 */
	public Object modifyXmer(ModifyXmerInfoRequest request) {
		try {
			Integer uid = Integer.valueOf(sessionTokenService.getStringForValue(request.getSessiontoken()) + "");
			if (uid.equals("") || uid.equals("null")) {
				return new BaseResponse(ResponseCode.DATAERR,"token错误或已过期请重新获取！");
			}
			request.setUid(uid);
			// 获取需要更新的字段名称
			String[] ursField = new String[] { "nname", "phone", "email", "sign"};// 用户基本资料列
			String[] ursInfoField = new String[] { "avatar", "name", "sex",
					"birthday", "phone" };// 用户详细资料基本资料列
			String[] xmerField = new String[] { "wechatno", "email", "age",
					"phone" };// 寻蜜客资料修改列
			String[] anchorField = new String[] { "anchorAge"};// 主播资料修改列
			UrsInfo ursInfoTemp = selectUrsInfoByUid(uid);
			if (null == ursInfoTemp) {
				return new BaseResponse(ResponseCode.DATA_NULL, "用户详细信息不存在");
			}
			
			//修改tls腾讯的资料(头像，昵称)
			Map<Object, Object> tlsMap = new HashMap<>();
			
			// 存储缓存信息
			Map<Object, Object> redisMap = new HashMap<Object, Object>();
			// 缓存中key
			String redisKey = "";
			// 更新用户信息资料
			for (String fileName : ursField) {
				if (fileName.equals(request.getFieldname())) {
					Map<Object, Object> paramMap = new HashMap<>();
					paramMap.put("uid", uid);
					paramMap.put(request.getFieldname(),
							request.getFieldvalue());
					
					if ("nname".equals(request.getFieldname())) {
						//同步腾讯云昵称
						tlsMap.put("nickName", request.getFieldvalue());
					}
					
					if ("sign".equals(request.getFieldname())) {
						//同步腾讯云签名
						tlsMap.put("selfSignature", request.getFieldvalue());
					}
					
					int flag = ursDao.updateUrsByUid(paramMap);
					if (flag == 1) {
						redisMap.clear();
						redisMap.put(request.getFieldname(),
								request.getFieldvalue());
						// 更新缓存
						if (redisMap != null && !redisMap.isEmpty()) {
							redisKey = Constant.USERID_ + request.getUid();
							stringRedisTemplate.opsForHash().putAll(redisKey,
									redisMap);
						}
					}
					break;
				}
			}
			// 更新用户详细资料
			for (String fileName : ursInfoField) {
				if (fileName.equals(request.getFieldname())) {
					String fieldname = request.getFieldname();
					String fieldvalue = request.getFieldvalue();
					UrsInfo ursInfo = new UrsInfo();
					ursInfo.setUid(request.getUid());
					if ("avatar".equals(fieldname)) {
						ursInfo.setAvatar(fieldvalue.replace(fileUrl, ""));
						
						//同步腾讯云头像
						tlsMap.put("image", fieldvalue);
						
						// 存储到缓存中
						redisMap.put("avatar", fieldvalue.replace(fileUrl, ""));
					} else if ("name".equals(fieldname)) {
						ursInfo.setName(fieldvalue);
						// 存储到缓存中
						redisMap.put("name", fieldvalue);
					} else if ("sex".equals(fieldname)) {
						ursInfo.setSex(Integer.parseInt(fieldvalue));
						// 存储到缓存中
						redisMap.put("sex", fieldvalue);
					} else if ("phone".equals(fieldname)) {
						ursInfo.setPhone(fieldvalue);
						// 存储到缓存中
						redisMap.put("phone", fieldvalue);
					} else if ("birthday".equals(fieldname)) {
						ursInfo.setBirthday(fieldvalue);
						// 存储到缓存中
						redisMap.put("birthday", fieldvalue);
					}
					ursInfoDao.updateByPrimaryKey(ursInfo);
					// 更新缓存
					if (redisMap != null) {
						redisKey = Constant.USERID_ + request.getUid();
						stringRedisTemplate.opsForHash().putAll(redisKey,
								redisMap);
					}
					break;
				}
			}
			
			// 更新寻蜜客资料
			for (String fileName : xmerField) {
				if (fileName.equals(request.getFieldname())) {
					String fieldname = request.getFieldname();
					String fieldvalue = request.getFieldvalue();
					redisMap.clear();
					Xmer xmer = new Xmer();
					xmer.setUid(request.getUid());
					if ("email".equals(fieldname)) {
						xmer.setEmail(fieldvalue);
						redisMap.put("email", fieldvalue);
					} else if ("wechatno".equals(fieldname)) {
						xmer.setWeixinid(fieldvalue);
						// 存储到缓存中
						redisMap.put("weixinid", fieldvalue);
					} else if ("age".equals(fieldname)) {
						xmer.setAge(Integer.parseInt(fieldvalue));
						// 存储到缓存中
						redisMap.put("age", fieldvalue);
					} else if ("phone".equals(fieldname)) {
						xmer.setPhoneid(fieldvalue);
					}
					int flag = xmerDao.updateByUid(xmer);
					// 更新缓存
					if (flag == 1) {
						if (redisMap != null && !redisMap.isEmpty()) {
							redisKey = Constant.XMER_INFO_KEY
									+ request.getUid();
							stringRedisTemplate.opsForHash().putAll(redisKey,
									redisMap);
						}
					}
					break;
				}
			}
			
			// 主播资料
			for (String fileName : anchorField) {
				if (fileName.equals(request.getFieldname())) {
					String fieldname = request.getFieldname();
					String fieldvalue = request.getFieldvalue();
					redisMap.clear();
					Map<Object, Object> paramMap = new HashMap<>();
					if ("anchorAge".equals(fieldname)) {
						paramMap.put("uid", request.getUid());
						paramMap.put("age", fieldvalue);
					} 
					int flag = liveUserDao.modifyLiverByUid(paramMap);
					// 更新缓存
					if (flag == 1) {
						if (redisMap != null && !redisMap.isEmpty()) {
							redisKey = Constant.LIVER_INFO_KEY
									+ request.getUid();
							stringRedisTemplate.opsForHash().putAll(redisKey,
									redisMap);
						}
					}
					break;
				}
			}
			
			//同步腾讯云资料
			try {
				if (tlsMap.size() > 0) {
					//查询是否有直播用户信息
					Map<Object, Object> liverMap = liveUserDao.queryLiverInfoByUid(uid);
					if (liverMap != null) {
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
						
						tlsMap.put("tlsSig", adminSig);
						tlsMap.put("sdkAppid", sdkAppid);
						tlsMap.put("identifier", identifier);
						tlsMap.put("account", ursInfoTemp.getUname());
						
						log.info("同步直播用户腾讯云资料信息:" + tlsMap.toString());
						
						TLSUtil.setTlsUserInfo(tlsMap);
						
						log.info("同步直播用户腾讯云资料成功,信息如下:" + tlsMap.toString());
						
					}else {
						log.info("查无此直播用户信息,uid:" + uid);
					}
					
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.info("同步直播用户腾讯云资料失败,错误信息如下:" + e.getMessage());
			}
			// 修改主播信息
//			for (String fileName : anchorField) {
//				if (fileName.equals(request.getFieldname())) {
//					redisMap.clear();
//					Map<Object, Object> param = new HashMap<>();
//					param.put("uid", uid);
//					if ("avatar".equals(fileName)) {
//						request.setFieldvalue(request.getFieldvalue().replace(
//								fileUrl, ""));
//					}
//					param.put(fileName, request.getFieldvalue());
//					int flag = ursDao.updateAnchorByUid(param);
//					// 更新缓存
//					if (flag == 1) {
//						redisMap.put(fileName, request.getFieldvalue());
//						if (redisMap != null && !redisMap.isEmpty()) {
//							// TODO 修改主播缓存
//							// stringRedisTemplate.opsForHash().
//							// putAll(redisKey, redisMap);
//						}
//					}
//					break; 
//				}
//
//			}

			/*
			 * String fieldname = request.getFieldname(); String[] allFieldName
			 * = new
			 * String[]{"avatar","name","email","age","wechatno","sex","phone"};
			 * for (String str : allFieldName) { if (str.equals(fieldname)) {
			 * boolean toModifyUrs = toModifyUrsInfo(request); if (toModifyUrs)
			 * { UrsInfo ursInfo = selectUrsInfoByUid(uid); if (null == ursInfo)
			 * { return new BaseResponse(ResponseCode.DATA_NULL, "用户详细信息不存在"); }
			 * modifyUrsInfo(request); } else { Xmer xmer = selectByUid(uid); if
			 * (null == xmer) { return new
			 * BaseResponse(ResponseCode.XMER_NOT_EXIST, "寻蜜客信息不存在"); }
			 * modifyXmerInfo(request); } //修改usr缓存key String userKey =
			 * Constant.USERID_+uid; if(stringRedisTemplate.hasKey(userKey)){
			 * stringRedisTemplate.delete(userKey); } return new
			 * MapResponse(ResponseCode.SUCCESS, "修改成功"); } } return new
			 * BaseResponse(ResponseCode.FAILURE, "更新字段不合法");
			 */
			
			//设置redis key 为true   业务管理后台检测为true 更新缓存
			stringRedisTemplate.opsForValue().set(Constant.B_URS$CHANGED, "true");
			
			return new MapResponse(ResponseCode.SUCCESS, "修改成功");
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE, "更新个人资料失败");
		}

	}

	/**
	 * 修改b_urs_info表标志
	 * 
	 * @param request
	 * @return
	 */
	private boolean toModifyUrsInfo(ModifyXmerInfoRequest request) {
		boolean modifyUrsFlag = false;
		String fieldname = request.getFieldname();
		if ("avatar".equals(fieldname) || "name".equals(fieldname)
				|| "phone".equals(fieldname) || "sex".equals(fieldname)) {
			modifyUrsFlag = true;
		}
		return modifyUrsFlag;
	}

	/**
	 * 修改用户详细信息
	 * 
	 * @param request
	 */
	private void modifyXmerInfo(ModifyXmerInfoRequest request) {
		String fieldname = request.getFieldname();
		String fieldvalue = request.getFieldvalue();

		// 存储缓存信息
		Map<Object, Object> redisMap = new HashMap<Object, Object>();
		// 缓存中key
		String redisKey = Constant.XMER_INFO_KEY + request.getUid();

		Xmer xmer = new Xmer();
		xmer.setUid(request.getUid());
		if ("email".equals(fieldname)) {
			xmer.setEmail(fieldvalue);
			redisMap.put("email", fieldvalue);
		} else if ("wechatno".equals(fieldname)) {
			xmer.setWeixinid(fieldvalue);

			// 存储到缓存中
			redisMap.put("weixinid", fieldvalue);
		} else if ("age".equals(fieldname)) {
			xmer.setAge(Integer.parseInt(fieldvalue));

			// 存储到缓存中
			redisMap.put("age", fieldvalue);
		}
		xmerDao.updateByUid(xmer);
		// 更新缓存
		if (redisMap != null) {
			stringRedisTemplate.opsForHash().putAll(redisKey, redisMap);
		}
	}

	/**
	 * 修改寻蜜客信息
	 * 
	 * @param request
	 */
	private void modifyUrsInfo(ModifyXmerInfoRequest request) {
		String fieldname = request.getFieldname();
		String fieldvalue = request.getFieldvalue();

		// 存储缓存信息
		Map<Object, Object> redisMap = new HashMap<Object, Object>();
		// 缓存中key
		String redisKey = Constant.XMER_INFO_KEY + request.getUid();

		UrsInfo ursInfo = new UrsInfo();
		ursInfo.setUid(request.getUid());
		if ("avatar".equals(fieldname)) {
			ursInfo.setAvatar(fieldvalue.replace(fileUrl, ""));

			// 存储到缓存中
			redisMap.put("avatar", fieldvalue.replace(fileUrl, ""));
		} else if ("name".equals(fieldname)) {
			ursInfo.setName(fieldvalue);

			// 存储到缓存中
			redisMap.put("name", fieldvalue);
		} else if ("sex".equals(fieldname)) {
			ursInfo.setSex(Integer.parseInt(fieldvalue));

			// 存储到缓存中
			redisMap.put("sex", fieldvalue);
		} else if ("phone".equals(fieldname)) {
			ursInfo.setPhone(fieldvalue);

			// 存储到缓存中
			redisMap.put("phone", fieldvalue);
		}

		ursInfoDao.updateByPrimaryKey(ursInfo);

		// 更新缓存
		if (redisMap != null) {
			stringRedisTemplate.opsForHash().putAll(redisKey, redisMap);
		}
	}

	/**
	 * 
	 * @Title: addCk
	 * @Description: 添加创客
	 * @return Object 成功失败信息
	 * @throws
	 */
	public Object addCk(PhoneRequest phoneRequest) {
		try {
			JSONObject json = new JSONObject();
			// 查询寻蜜客
			String uid = sessionTokenService.getStringForValue(phoneRequest
					.getSessiontoken()) + "";

			int isq = xmerDao.queryISQ(Integer.parseInt(uid));// 查询是否是企业用户
			if (isq == 0) {
				return new BaseResponse(ResponseCode.NOT_ENTERPRISE_XMER,
						"您不是企业寻蜜客");
			}
			Map<Object, Object> xmerMap = xmerDao
					.queryXmerByphone(phoneRequest);
			if (xmerMap != null && !xmerMap.isEmpty()) {
				xmerMap.put("sendid", uid);
				// 查询是否是好友
				int isfriend = xmerDao.queryFriendship(xmerMap);
				if (isfriend > 0) {
					return new BaseResponse(ResponseCode.IS_FRIEND_SHIP,
							"你们已是好友");
				}
				// 查询是否有正在申请的申请
				int count = xmerDao.queryFriendApply(xmerMap);
				if (count > 0) {
					return new BaseResponse(ResponseCode.IS_PATENTED,
							"你已发送过申请，请耐心等待");
				}

				// json.put("uid", xmerMap.get("uid"));
				json.put("name", xmerMap.get("nname"));
				// Map<Object,Object>
				// tokenMap=sessionTokenService.getsessionToken(phoneRequest.getSessiontoken());
				if (uid != null && uid.length() > 0) {
					xmerMap.put("sendUid", uid);// 发送方Uid
					xmerMap.put("applydate", DateUtil.now());
					json.put("sendUid", uid);
					String nname = ursInfoDao.queryUrsByUid(Integer
							.valueOf(uid));
					xmerMap.put("sendName", nname);
					json.put("sendName", nname);
					json.put("date",
							new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
									.format(new Date()));
				}
				// 保存到数据库
				xmerDao.addFriendApply(xmerMap);

				json.put("id", xmerMap.get("id"));
				sessionTokenService.deleteZSet(Constant.FRIEND_APPLICANTS_KEY
						+ xmerMap.get("uid"), Long.parseLong(uid),
						Long.parseLong(uid));
				// 添加到redis缓存
				sessionTokenService.setZSetForValue(
						Constant.FRIEND_APPLICANTS_KEY + xmerMap.get("uid"),
						json.toString(), Double.parseDouble(uid));

			} else {
				return new BaseResponse(ResponseCode.DATA_NULL,
						"没有找到该寻蜜客！,或不是企业寻蜜客");
			}
			// 添加好友信

		} catch (Exception e) {
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE, "添加创客失败");
		}
		return new BaseResponse(ResponseCode.SUCCESS, "成功");
	}

	/**
	 * 
	 * @Title: handleCK
	 * @Description: 修改好友申请
	 * @return Object 返回类型
	 * @throws
	 */
	public Object handleCK(HandleCKRequest hand) {
		try {
			String uid = sessionTokenService.getStringForValue(hand
					.getSessiontoken()) + "";
			if (uid != null && uid.length() > 0) {
				hand.setUid(Integer.valueOf(uid));
			} else {
				return new BaseResponse(ResponseCode.DATAERR, "token过期或有误");
			}

			if (hand.getResult() == 1) {
				// 修改好友申请信息
				try {
					friendShinpDao.modifyFriendApplyByid(hand);
				} catch (Exception e) {
					e.printStackTrace();
				}
				// 添加到好友申请表
				try {
					Map<Object, Object> friendMap = new HashMap<Object, Object>();
					friendMap.put("id", hand.getId());
					friendMap.put("sdate", DateUtil.format(DateUtil.now(),
							"yyyy-MM-dd HH:mm:ss"));
					friendShinpDao.addFriendShip(friendMap);
				} catch (Exception e) {
					e.printStackTrace();
				}
				// 修改伙伴数量
				try {
					xmerDao.modifyXmerPartnernums(hand);
				} catch (Exception e) {
					e.printStackTrace();
				}

			} else {
				if (hand.getResult() == 2) {
					// 不同意修改好友申请审核信息
					friendShinpDao.modifyFriendApplyByid(hand);
				} else {
					return new BaseResponse(ResponseCode.DATAERR, "类型有误");
				}
			}
			// 删除Redis缓存
			sessionTokenService.deleteZSet(Constant.FRIEND_APPLICANTS_KEY,
					hand.getSendid(), hand.getSendid());

			// Set<String> set =
			// sessionTokenService.getZSetForValue(Constant.FRIEND_APPLICANTS_KEY+hand.getUid());
			// if(set!=null&&set.size()>0){
			// for(String jsonStr:set){
			// try {
			// JSONObject json=JSONObject.parseObject(jsonStr);
			// if(json.getIntValue("sendid")==hand.getSendid()){
			//
			// }
			// } catch (Exception e) {
			// e.printStackTrace();
			// return new BaseResponse(ResponseCode.DATAERR,"数据参数有误");
			// }
			// }
			// }
			sessionTokenService.deleteZSet(Constant.FRIEND_APPLICANTS_KEY
					+ hand.getUid(), hand.getSendid(), hand.getSendid());
			return new BaseResponse(ResponseCode.SUCCESS, "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE, "操作失败");
		}
	}

	/**
	 * 
	 * @Title: queryXmerInfo
	 * @Description: 寻蜜客用户资料获取
	 * @return Object 返回类型
	 * @throws
	 */
	public Object queryXmerInfo(BaseRequest baseRequest) {

		try {
			String uid = sessionTokenService.getStringForValue(
					baseRequest.getSessiontoken()).toString();
			if (uid.equals("") && uid.equals("null")) {
				return new BaseResponse(ResponseCode.FAILURE,
						"用户id为空，token已过期，请重新登录");
			}

			Map<Object, Object> ursMap = xmerDao.queryUserInfo(Integer
					.valueOf(uid));
			if (ursMap == null) {
				return new BaseResponse(ResponseCode.FAILURE, "用户不存在");
			}
			
			//查询普通用户信息
			Urs urs = ursDao.queryUrsByUid(Integer.parseInt(uid));
			
			Map<Object, Object> map = new HashMap<>();
			map.put("name", ursMap.get("name"));
			map.put("phone", ursMap.get("phone"));
			map.put("sex", ursMap.get("sex"));
			map.put("avatar", fileUrl + ursMap.get("avatar"));
			map.put("birthday", ursMap.get("birthday"));
			map.put("nname", ursMap.get("nname"));
			map.put("email", ursMap.get("email"));
			map.put("sign", urs.getSign());//个性签名
			map.put("age", 0);//年龄，默认为0
			map.put("isxmer", 0);
			map.put("isanchor", 0);
			
			// 查询是否是寻蜜客
			Map<Object, Object> xmermap = xmerDao.queryXmerInfo(Integer
					.valueOf(uid));
			if (xmermap != null) {
				// 查询绑定的银行卡号
				/*
				String bank = xmerDao.queryBankAccountByUid(Integer
						.valueOf(uid));
				if (StringUtils.hasLength(bank)) {
					bank = bank.replace(bank.substring(4, bank.length() - 4),
							"******");
					map.put("bank", bank);
				}
				*/
				map.put("wechatno", xmermap.get("wechatno"));
				map.put("email", ursMap.get("email"));
				map.put("age", xmermap.get("age"));
				map.put("isxmer", 1);
			}
			
			//查询主播信息
			Map<Object, Object> anchorMap = liveUserDao.queryLiverInfoByUid(Integer.parseInt(uid));
			if (anchorMap != null && Integer.parseInt(anchorMap.get("utype").toString()) == 1) {
				/*
				String bank = xmerDao.queryBankAccountByUid(Integer
						.valueOf(uid));
				if (StringUtils.hasLength(bank)) {
					bank = bank.replace(bank.substring(4, bank.length() - 4),
							"******");
					map.put("bank", bank);
				}
				*/
				map.put("age", anchorMap.get("age"));
				map.put("isanchor", 1);
			}
			MapResponse response = new MapResponse(ResponseCode.SUCCESS, "获取成功");
			response.setResponse(map);
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE, "未知错误，请联系管理员");
		}
	}

	/**
	 * 
	 * @Title: addShopPic
	 * @Description: 添加商铺图片
	 * @return Object 返回类型
	 * @throws
	 */
	public Object addShopPic(ShopPicRequest shopPicRequest) {
		try {

			Map<Object, Object> paramMap = new HashMap<>();
			paramMap.put("sellerid", shopPicRequest.getSellerid());
			try {
				Map<Object, Object> sellerMap = sellerInfoDao
						.querySellerInfoBySellerid(Integer
								.parseInt(shopPicRequest.getSellerid()
										.toString()));
				if (sellerMap == null || sellerMap.isEmpty()) {
					return new BaseResponse(ResponseCode.DATA_NULL,
							"没有找到此ID的商户信息");
				}

			} catch (Exception e) {
				e.printStackTrace();

			}
			// 判断商户是否已存在图片
			List<Map<Object, Object>> picList = null;
			try {
				picList = sellerInfoDao.querySellerPic(Integer
						.valueOf(shopPicRequest.getSellerid()));
			} catch (Exception e) {
				e.printStackTrace();
				log.info("图片查询异常");
			}
			if (picList.size() < 1 || picList == null) {
				addPicByDefault(paramMap, shopPicRequest);
				return new BaseResponse(ResponseCode.SUCCESS, "添加成功");
			}
			return new BaseResponse(ResponseCode.FAILURE, "商铺图片已存在，不容许添加");
		} catch (Exception e) {
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE, "未知错误");
		}
	}


	/**
	 * 添加图片
	 * @param shopPicRequest
	 * @return
	 */
	public Object addShopPicV2(ShopPicRequest shopPicRequest) {
		try {

			Map<Object, Object> paramMap = new HashMap<>();
			paramMap.put("sellerid", shopPicRequest.getSellerid());
			try {
				Map<Object, Object> sellerMap = sellerInfoDao
						.querySellerInfoBySellerid(Integer
								.parseInt(shopPicRequest.getSellerid()
										.toString()));
				if (sellerMap == null || sellerMap.isEmpty()) {
					return new BaseResponse(ResponseCode.DATA_NULL,
							"没有找到此ID的商户信息");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			addPicByDefault(paramMap, shopPicRequest);
			return new BaseResponse(ResponseCode.SUCCESS, "添加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE, "未知错误");
		}
	}

	// 插入环境图和卫生许可证
	public void insertEnvirpicAndLicensefurl(int sellerid, String licensefurl, String envirpic) {
		List<Map<Object, Object>> sellerPciList = sellerInfoDao.querySellerPic(sellerid);
		List<Integer> picList = getHadPicList(sellerPciList);
		//
		Map<Object, Object> paramMap = new HashMap<Object, Object>();
		String sdate = DateUtil.format(new Date());
		paramMap.put("sdate", sdate);
		paramMap.put("sellerid", sellerid);
		addAndDeletePicList(envirpic, paramMap, picList, 0);  //添加环境图

		ShopPicRequest shopPicRequest = new ShopPicRequest();
		shopPicRequest.setUdate(DateUtil.format(new Date()));
		shopPicRequest.setSellerid(String.valueOf(sellerid));
		shopPicRequest.setLicensefurl(licensefurl == null ? null : sellerInfoService.safeToSavePicUrl(licensefurl));
		// 更新卫生许可证
		sellerInfoDao.updateSellerInfoBySellerid(shopPicRequest);
	}

	private List<Integer> getHadPicList(List<Map<Object, Object>> sellerPciList) {
		List<Integer> picList = new ArrayList<Integer>();
		for (Map<Object, Object> info : sellerPciList) {
			Integer picid = Integer.parseInt(info.get("id").toString());
			Integer islogo = Integer.parseInt(info.get("islogo").toString());
			if (islogo == 0) {
				picList.add(picid);
			}
		}
		return picList;
	}

	private void addPicByDefault(Map<Object, Object> paramMap, ShopPicRequest shopPicRequest) {
		// 添加环境图，logo,店铺封面
		List<Map<Object, Object>> sellerPciList = sellerInfoDao.querySellerPic(Integer.parseInt(shopPicRequest.getSellerid()));
		Map<Integer, Integer> picMap = new HashMap<Integer, Integer>(); // islogo,   picid
		List<Integer> picList = new ArrayList<Integer>(); //环境图
		List<Integer> contractList = new ArrayList<Integer>();  //合同图
		if (sellerPciList != null) {
			for (Map<Object, Object> info : sellerPciList) {
				Integer picid = Integer.parseInt(info.get("id").toString());
				Integer islogo = Integer.parseInt(info.get("islogo").toString());
				picMap.put(islogo, picid);
				if (islogo == 0) {   //logo图
					picList.add(picid);
				} else if (islogo == 3) {  //合同图
					contractList.add(picid);
				}
			}
		}
		String sdate = DateUtil.format(new Date());
		paramMap.put("sdate", sdate);
		//添加环境图
		addAndDeletePicList(shopPicRequest.getEnvirpic(), paramMap, picList, 0);
		// 添加合同图
		addAndDeletePicList(shopPicRequest.getContractPic(), paramMap, contractList, 3);
		// 添加logo
		try {
			paramMap.put("islogo", 1);// 设置图片类型为1logo
			String url = sellerInfoService.safeToSavePicUrl(shopPicRequest.getLogo());
			paramMap.put("pic", url);// logo
			Integer picId = picMap.get(1);
			if (picId != null) {
				paramMap.put("picid", picId);
				sellerInfoDao.updateSellerPic(paramMap);
			} else {
				sellerInfoDao.addSerllerpic(paramMap);
			}
		} catch (Exception e) {
			log.error("添加logo失败", e);
		}
		// 添加店铺封面
		try {
			paramMap.put("islogo", 2);// 设置图片类型为2店铺封面
			String url = sellerInfoService.safeToSavePicUrl(shopPicRequest.getCoverpic());
			paramMap.put("pic", url);
			Integer picId = picMap.get(2);
			if (picId != null) {
				paramMap.put("picid", picId);
				sellerInfoDao.updateSellerPic(paramMap);
			} else {
				sellerInfoDao.addSerllerpic(paramMap);
			}
		} catch (Exception e) {
			log.error("添加logo失败", e);
		}
		try {
			String getIdentityfurl = shopPicRequest.getIdentityfurl() == null ? null : sellerInfoService.safeToSavePicUrl(shopPicRequest.getIdentityfurl());
			String getIdentitynurl = shopPicRequest.getIdentitynurl() == null ? null : sellerInfoService.safeToSavePicUrl(shopPicRequest.getIdentitynurl());
			String getIdentityzurl = shopPicRequest.getIdentityzurl() == null ? null : sellerInfoService.safeToSavePicUrl(shopPicRequest.getIdentityzurl());
			String getLicensefurl = shopPicRequest.getLicensefurl() == null ? null : sellerInfoService.safeToSavePicUrl(shopPicRequest.getLicensefurl());
			String getLicenseurl = shopPicRequest.getLicenseurl() == null ? null : sellerInfoService.safeToSavePicUrl(shopPicRequest.getLicenseurl());

			// 去掉图片文件头
			shopPicRequest.setIdentityfurl(getIdentityfurl);
			shopPicRequest.setIdentitynurl(getIdentitynurl);
			shopPicRequest.setIdentityzurl(getIdentityzurl);
			shopPicRequest.setLicensefurl(getLicensefurl);
			shopPicRequest.setLicenseurl(getLicenseurl);
//			shopPicRequest.setStatus(0); //
			shopPicRequest.setUdate(DateUtil.format(new Date()));
			// 更新商家身份证、营业执照、卫生许可证、
			sellerInfoDao.updateSellerInfoBySellerid(shopPicRequest);
		} catch (Exception e) {
			log.error("更新商家图片失败", e);
		}


	}

	// 添加环境图
	public void addAndDeletePicList(String envirpicStr, Map<Object, Object> paramMap, List<Integer> picList, int islogo) {
		try {
			String[] pics = null;
			if (envirpicStr != null) {
				pics = envirpicStr.split(",");
			} else {
				pics = new String[0];
			}
			ShopPicRequest request = new ShopPicRequest();
			request.setUdate(DateUtil.getCurrentTimeStr());
			request.setSellerid(paramMap.get("sellerid").toString());
			request.setAgreement(pics.length > 0 ? sellerInfoService.safeToSavePicUrl(pics[0]) : "");
			request.setAgreement2(pics.length > 1 ? sellerInfoService.safeToSavePicUrl(pics[1]) : "");
			request.setAgreement3(pics.length > 2 ? sellerInfoService.safeToSavePicUrl(pics[2]) : "");
			request.setAgreement4( pics.length > 3 ? sellerInfoService.safeToSavePicUrl(pics[3]) : "");
			request.setAgreement5(pics.length > 4 ? sellerInfoService.safeToSavePicUrl(pics[4]) : "");
			request.setAgreement6( pics.length > 5 ? sellerInfoService.safeToSavePicUrl(pics[5]) : "");
			sellerInfoDao.updateSellerInfoBySellerid(request);
		} catch (Exception e) {
			log.error("添加合同图片失败:", e);
		}
	}

	/**
	 * 
	 * @Title: updateRedisXmerInfo
	 * @Description: 修改缓存寻蜜客信息（如果缓存没有找到寻蜜客的缓存将不会修改）
	 * @return boolean true 成功 false 失败
	 * @throws
	 */
	public boolean updateRedisXmerInfo(String xmerInfo) {
		log.info("updateRedisXmerInfo is begin");
		try {
			JSONObject jsonObject = null;
			try {
				// 商品销售分账 json对象
				jsonObject = JSONObject.parseObject(xmerInfo);
			} catch (Exception e) {
				e.printStackTrace();
				log.error("参数不是JSON字符串格式");
				return false;
			}
			if (jsonObject != null) {
				String uid = jsonObject.getString("uid");
				String firstLvuid = jsonObject.getString("firstLvuid");
				String secondLvuid = jsonObject.getString("secondLvuid");
				// 用户修改
				if (uid != null && uid.length() > 0) {
					Map<Object, Object> xmerinfo = sessionTokenService
							.getsessionToken(Constant.XMER_INFO_KEY + uid);
					if (xmerinfo != null && !xmerinfo.isEmpty()) {
						// double
						// incomeAmount=jsonObject.getDoubleValue("amount")
						// 修改总收入金额
						double incomeAmount = xmerinfo.get("incomeAmount") == null ? 0
								: Double.parseDouble(xmerinfo
										.get("incomeAmount") + "");
						double amount = jsonObject.getDoubleValue("amount");
						xmerinfo.put("incomeAmount", incomeAmount + amount + "");
						// 修改伙伴数量
						int partners = xmerinfo.get("merchants") == null ? 1
								: Integer.parseInt(xmerinfo.get("merchants")
										+ "") + 1;
						xmerinfo.put("merchants", partners + "");
						sessionTokenService.addXmerInfoRedis(
								Constant.XMER_INFO_KEY + uid, xmerinfo);
					}
				}
				// 一级用户修改
				if (firstLvuid != null && firstLvuid.length() > 0) {
					Map<Object, Object> xmerinfo = sessionTokenService
							.getsessionToken(Constant.XMER_INFO_KEY
									+ firstLvuid);
					if (xmerinfo != null && !xmerinfo.isEmpty()) {
						double incomeAmount = xmerinfo.get("incomeAmount") == null ? 0
								: Double.parseDouble(xmerinfo
										.get("incomeAmount") + "");
						double amount = jsonObject
								.getDoubleValue("firstLvamount");
						xmerinfo.put("incomeAmount", incomeAmount + amount + "");
						sessionTokenService.addXmerInfoRedis(
								Constant.XMER_INFO_KEY + firstLvuid, xmerinfo);
					}
				}
				// 二级用户修改
				if (secondLvuid != null && secondLvuid.length() > 0) {
					Map<Object, Object> xmerinfo = sessionTokenService
							.getsessionToken(Constant.XMER_INFO_KEY
									+ secondLvuid);
					if (xmerinfo != null && !xmerinfo.isEmpty()) {
						double incomeAmount = xmerinfo.get("incomeAmount") == null ? 0
								: Double.parseDouble(xmerinfo
										.get("incomeAmount") + "");
						double amount = jsonObject
								.getDoubleValue("secondLvamount");
						xmerinfo.put("incomeAmount", incomeAmount + amount + "");
						sessionTokenService.addXmerInfoRedis(
								Constant.XMER_INFO_KEY + secondLvuid, xmerinfo);
					}
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("修改缓存寻蜜客信息失败");
		}
		return false;
	}

	/**
	 * 
	 * @Title: modifyXmerStatus
	 * @Description: 寻蜜客解除接口实现类
	 * @return Object 返回类型
	 * @author liuzhihao
	 * @throws
	 */
	public Object modifyXmerStatus(BaseRequest baseRequest) {

		String uid = sessionTokenService.getStringForValue(
				baseRequest.getSessiontoken()).toString();
		try {
			
		
			
			Map<String, Object> xmerMap = new HashMap<String, Object>();
			xmerMap.put("uid", uid);
			Xmer xmer =null;
			try {
				xmer = xmerDao.queryXmerByUid(xmerMap);
			} catch (Exception e) {
				e.printStackTrace();
				log.info("查询寻蜜客信息异常");
			}
			if(xmer==null||xmer.getStatus()==0){
				return new BaseResponse(ResponseCode.DATA_NULL, "您还不是寻蜜客或已解除寻蜜客");
			}
				Map<Object, Object> map = sessionTokenService
						.getsessionToken(Constant.XMER_INFO_KEY + uid);
				if (map != null){// 如果该用户的缓存不为空的时候，清空该用户的押金和返还押金额
					map.put("deposit", "0");
					map.put("returnDeposit", "0");
					sessionTokenService.addXmerInfoRedis(Constant.XMER_INFO_KEY
							+ uid, map);
				}
			
				// 判断是否成为寻蜜客有三个月
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm");
				Calendar calender = Calendar.getInstance();
				calender.setTime(xmer.getSdate());
				calender.add(Calendar.MONTH, 3);
				String addDate = simpleDateFormat.format(calender.getTime());
				if (simpleDateFormat.parse(addDate).getTime() > System
						.currentTimeMillis()) {
					return new BaseResponse(ResponseCode.FAILURE,
							"成为寻蜜客三个月后才可以解除！");
				}
				 Map<Object,Object> walletMap =withDrawCashDao.queryXmerWalletId(Integer.valueOf(uid));
				 double profit=0;
				 if(walletMap!=null){
					 if(walletMap.get("profit")==null){
						 profit=Double.parseDouble(walletMap.get("profit").toString());
					 }
				 }
				 if(profit!=0){
					 //调用支付服务修改转出寻蜜客收益金额
					boolean tumOutFalg = tumOutXmerWallet(uid,profit);
					if (!tumOutFalg) {
						//转出金额失败
						return new BaseResponse(ResponseCode.FAILURE, "寻蜜客转出金额失败！");
					}
				 }
				// 调用支付服务把押金套餐改成钱包赠送金额
				if (!updateBalance(uid)) {
					return new BaseResponse(ResponseCode.FAILURE, "返还套餐押金失败！");
				}
				//修改寻蜜客状态为已退款剩余套数改为0
				// 修改saasorder订单状态
				saasOrderDao.udpateXmerOrderStatusAndsoldnums(uid);
				//清空签约的店铺
				sellerInfoDao.updateSellerUidByUid(uid);
				//清空下级的父ID等级上升一级
				xmerDao.updateParentidByUid(uid);
				if (xmer.getParentid() != null) {
					Xmer parentXmer = new Xmer();// 上级
					xmerMap.put("uid", xmer.getParentid());
					try {
						parentXmer = xmerDao.queryXmerByUid(xmerMap);
					} catch (Exception e) {
						e.printStackTrace();
						log.info("查询伙伴数量异常");
					}
					parentXmer.setPartnerNums(parentXmer.getPartnerNums() - 1);// 上级伙伴数量减一
					xmerDao.updateByUid(xmer);
					if (parentXmer.getParentid() != null) {
						xmerMap.put("uid", parentXmer.getParentid());
						Xmer xmk = new Xmer();// 上上级
						try {
							xmk = xmerDao.queryXmerByUid(xmerMap);
						} catch (Exception e) {
							e.printStackTrace();
							log.info("查询伙伴数量异常");
						}
						xmk.setPartnerNums(xmk.getPartnerNums() - 1);// 上上级伙伴数量减一
						try {
							xmerDao.updateByUid(xmk);
						} catch (Exception e) {
							e.printStackTrace();
							log.info("更新数据异常");
						}
					}
				}
				//解除寻蜜客
				xmerDao.updateXmerStatus(Integer.valueOf(uid));
				//添加解除记录
				
				
				
				return new BaseResponse(ResponseCode.SUCCESS, "解除寻蜜客成功");
			
		} catch (Exception e) {
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE, "解除失败，未知错误，请联系管理员");
		}
	}

	// 调用共支付服务把剩余押金金额转换为平台赠送金额
	private boolean updateBalance(String uid) {
		Double stockprice = saasOrderDao.getSaasaStockAmount(uid);
		SynthesizeService.Client client = null;
		try {
			// 调用支付服务申请提现
			client = (SynthesizeService.Client) (synthesizeServiceClient
					.getClient());
			Map<String, String> orderMap = new HashMap<String, String>();
			orderMap.put("uid", uid.toString());
			orderMap.put("zbalance", stockprice + "");
			orderMap.put("uId", "593820");
			orderMap.put("userType", "1");
			orderMap.put("amount", "0");
			orderMap.put("balance", "0");
			orderMap.put("commision", "0");
			orderMap.put("integral", "0");
			orderMap.put("sellerAmount", "0");
			orderMap.put("orderId", "" + System.currentTimeMillis());
			orderMap.put("rType", "7");
			// orderMap.put("money",
			// ArithUtil.sub(profit,Double.valueOf(manmageAmount))+"");
			List<Map<String, String>> paramList = new ArrayList<>();
			paramList.add(orderMap);
			Map<String, String> resultMap = client.updateBalance(paramList);
			if (resultMap.get("state").toString().equals("0")) {
				return true;
			} else {
				log.info("失败：" + resultMap.get("msg"));
				return false;
			}
		} catch (FailureException e) {
			log.error("寻蜜客转出金额失败,错误信息：" + e.getInfo() + "，用户：" + uid);
			if (synthesizeServiceClient != null) {
				synthesizeServiceClient.returnCon();
			}
			return false;
		} catch (TException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	private boolean tumOutXmerWallet(String uid,double profit) throws TException {
		XmerWalletService.Client client = null;
		try {
			// 调用支付服务申请提现
			client = (XmerWalletService.Client) (xmerWalletServiceClient
					.getClient());
			Map<String, String> orderMap = new HashMap<String, String>();
			orderMap.put("uid", uid.toString());
//			orderMap.put("turnOutAll", "1");
			orderMap.put("money", profit+"");
			// orderMap.put("money",
			// ArithUtil.sub(profit,Double.valueOf(manmageAmount))+"");
			ResponseData responseData = client.turnOutXmerWallet(orderMap);
			if (responseData.getState() == 0) {
				return true;
			} else {
				log.info(responseData.getMsg());
				return false;
			}
		} catch (FailureException e) {
			log.error("寻蜜客转出金额失败,错误信息：" + e.getInfo() + "，用户：" + uid);
			if (xmerWalletServiceClient != null) {
				xmerWalletServiceClient.returnCon();
			}
			// TODO 还原寻蜜客状态
			// 还原订单状态
			// 还原缓存状态
			return false;
		}
	}

	/**
	 * 
	 * @Title: getRelieveXmer
	 * @Description: 获取寻蜜客解除数据
	 * @return Xmer
	 */
	public Xmer getRelieveXmer(String uid) {
		return xmerDao.queryByUidAndStatus(Integer.valueOf(uid));
	}

	/**
	 * 
	 * @Title: addFeedBack
	 * @Description: 插入一条意见反馈信息
	 * @return Object 返回类型
	 * @author liuzhihao
	 * @throws
	 */
	public Object addFeedBack(FeedBackRequest feedBackRequest) {

		String uid = sessionTokenService.getStringForValue(feedBackRequest.getSessiontoken())+"";// 用户id
		if (uid.equals("")||uid.equals("null")) {
			return new BaseResponse(ResponseCode.FAILURE, "过期token,请重新登录");
		}
		// 查询用户电话号码
		Map<Object, Object> xmerMap = xmerDao.queryUserInfo(Integer.valueOf(uid));
		String content = feedBackRequest.getContent();
		String phone = ObjectUtils.toString(feedBackRequest.getPhone(), null);
		if (StringUtils.isBlank(phone)) {
			phone = ObjectUtils.toString(xmerMap.get("phone"), null);
		}
		// 过滤表情包
		if (EmojiFilter.containsEmoji(content) || EmojiFilter.containsEmojiByMatcher(content)) {	
				return new BaseResponse(ResponseCode.FAILURE, "不能含有表情包哦");	
		}
		
		Map<Object, Object> feedMap = new HashMap<Object, Object>();// 意见反馈map
		feedMap.put("staffid", uid);
		feedMap.put("content",content);
		feedMap.put("tel", phone);
		feedMap.put("sdate",DateUtil.format(DateUtil.now(), "yyyy-MM-dd HH:mm:ss"));
		feedMap.put("source", 4);// 默认用户安卓版本
		feedMap.put("version", feedBackRequest.getAppversion());// 版本号
		if (feedBackRequest.getSystemversion().toUpperCase().contains("IOS")) {
			feedMap.put("source", 5);// 用户版ios
		}
		try {
			//xmerDao.addFeedBack(feedMap);// 插入意见反馈信息
		} catch (Exception e) {
			e.printStackTrace();
			log.info("插入意见反馈信息异常");
			return new BaseResponse(ResponseCode.FAILURE, "反馈意见提交失败,请联系管理员");
		}
		return new BaseResponse(ResponseCode.SUCCESS, "反馈意见提交成功");
	}

	/**
	 * 
	 * @Title: addabnormal
	 * @Description: 添加报错信息
	 * @return Object 返回类型
	 * @throws
	 */
	public Object addabnormal(AddabnormalRequest request) {
		try {
			// 验证token
			String uid = sessionTokenService.getStringForValue(request
					.getSessiontoken()) + "";
			if (uid.equals("") || uid.equals("null")) {
				return new BaseResponse(ResponseCode.DATAERR,
						"token有误或已过期，请重新申请");
			}
			request.setUid(Integer.valueOf(uid));
			Map<String, Object> paramMap = new HashMap<>();
			// 查询商户信息
			Map<Object, Object> sellerMap = sellerInfoDao
					.querySellerInfoBySellerid(Integer.valueOf(request
							.getSellerid()));
			if (sellerMap == null || sellerMap.isEmpty()) {
				return new BaseResponse(ResponseCode.DATAERR, "没有找到商家信息");
			}
			String sellername = sellerMap.get("sellername") + "";
			paramMap.put("sellername", sellername);
			// 查询用户信息
			UrsInfo ursInfo = ursInfoDao
					.queryUrsInfoByUid(Integer.valueOf(uid));
			if (ursInfo == null) {
				return new BaseResponse(ResponseCode.DATA_NULL, "没有找到该用户信息");
			}
			int abnormalnum = abnormalDao
					.queryAbnormalByUidAndSellerid(request);
			if (abnormalnum > 0) {
				return new BaseResponse(ResponseCode.IS_PATENTED,
						"亲，您已经提交到后台了，等待客服处理吧！");
			}
			paramMap.put("sellerid", request.getSellerid());
			paramMap.put("type", request.getType());
			paramMap.put("uid", uid);
			paramMap.put("uname", ursInfo.getUname());
			paramMap.put("phone", ursInfo.getPhone());
			paramMap.put("sdate", new Date());
			paramMap.put("status", 0);
			// 添加报错或投诉信息
			abnormalDao.addabnormal(paramMap);
		} catch (Exception e) {
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE, "添加报错或报错信息失败");
		}
		return new BaseResponse(ResponseCode.SUCCESS, "成功");
	}

	public boolean tramXmerAmount() {

		try {

		} catch (Exception e) {
			// TODO: handle exception
		}

		return false;
	}
	
	

	

}
