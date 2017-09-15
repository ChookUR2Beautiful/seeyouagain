package com.xmniao.xmn.core.api.controller.xmer;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.api.controller.weixin.AuthzController;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.Constant;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.login.LoginRequest;
import com.xmniao.xmn.core.login.UserLoginService;
import com.xmniao.xmn.core.util.ArithUtil;
import com.xmniao.xmn.core.util.CookieUtils;
import com.xmniao.xmn.core.util.MD5;
import com.xmniao.xmn.core.util.PropertiesUtil;
import com.xmniao.xmn.core.util.utilClass;
import com.xmniao.xmn.core.verification.entity.Urs;
import com.xmniao.xmn.core.verification.entity.UrsInfo;
import com.xmniao.xmn.core.verification.service.UrsService;
import com.xmniao.xmn.core.verification.service.WalletService;
import com.xmniao.xmn.core.xmer.dao.SaasOrderDao;
import com.xmniao.xmn.core.xmer.entity.Xmer;
import com.xmniao.xmn.core.xmer.service.SaasPackageService;
import com.xmniao.xmn.core.xmer.service.SendCodeService;
import com.xmniao.xmn.core.xmer.service.XmerInfoService;

import net.sf.oval.Validator;

@Controller
public class InvitedJoinXMER {

	// 日志
	private Logger log = Logger.getLogger(InvitedJoinXMER.class);

	// 注入寻蜜客service
	@Autowired
	private XmerInfoService xmerInfoService;

	// 注入用户service
	@Autowired
	private UrsService ursService;

	// 注入验证
	@Autowired
	private Validator validator;

	@Autowired
	private SaasPackageService saasPackageService;

	// 注入saas套餐订单dao
	@Autowired
	private SaasOrderDao saasOrderDao;

	// 注入缓存
	@Autowired
	private SessionTokenService sessionTokenService;

	@Autowired
	private WalletService walletService;

	@Autowired
	private PropertiesUtil propertiesUtil;

	// 注入短信发送
	@Autowired
	private SendCodeService sendCodeService;
	
	@Autowired
	private UserLoginService userLoginService;

	@RequestMapping(value = "/pay/paySaasOrder", method = { RequestMethod.GET, RequestMethod.POST }, produces = {
			"application/json;charset=utf-8" })
	public String invitedJoinXmer(Model model, String phone, String uid, String phoneCode, String psw,
			HttpServletRequest request) throws Exception {
		log.info("pay/paySaasOrder请求开始：" + phone + "===" + uid);
		if (phone == null || phone.length() == 0) {
			model.addAttribute("data", new BaseResponse(ResponseCode.DATAERR, "手机号码有误"));
			return "xmer/error";
		}
		if (uid == null || uid.length() == 0) {
			model.addAttribute("data", new BaseResponse(ResponseCode.DATAERR, "推荐人不能为空"));
			return "xmer/error";
		}
		if (phoneCode == null || phoneCode.length() == 0) {
			model.addAttribute("data", new BaseResponse(ResponseCode.DATAERR, "验证码不能为空"));
			return "xmer/error";
		}
		String code = sessionTokenService.getStringForValue(phone + Constant.USER_XMER_KEY) + "";
		if (!code.equals(phoneCode)) {
			model.addAttribute("data", new BaseResponse(ResponseCode.FAILURE, "验证码错误，返回后重新输入"));
			return "xmer/error";
		}

		// 微信openid获取
		String openid = CookieUtils.getVal(AuthzController.WEIXIN_OPENID_KEY, request);
		if (openid == null) {
			String callback = ("/pay/paySaasOrder?uid=" + uid + "&phoneCode=" + phoneCode + "&phone=" + phone + "&psw="
					+ psw);

			callback = URLEncoder.encode(callback, "utf-8");

			String redirect = "/weixin/authz/authorize?callback=" + callback;
			log.info("请求微信openid:" + redirect);
			return "redirect:" + redirect;
		}
		log.info("请求微信openid结束：" + openid);
		int id = 0;// 用户UID
		try {
			Urs urs = ursService.getUrsByUname(phone);
			if (urs != null) {// 判断是否存在该用户
				id = urs.getUid();
				Xmer xmer = xmerInfoService.selectByUid(id);
				if (xmer != null) {// 判断是否是寻蜜客
					model.addAttribute("data", new BaseResponse(ResponseCode.FAILURE, "邀请失败，该手机号码已是寻蜜客"));
					return "xmer/error";
				}
				Xmer relieveXmer = xmerInfoService.getRelieveXmer(id + "");
				if (relieveXmer != null) {// 判断是否是寻蜜客
					model.addAttribute("data", new BaseResponse(ResponseCode.FAILURE, "邀请失败，该手机号码解除过寻蜜客"));
					return "xmer/error";
				}
				// 查询该用户是否有成功支付过的saas套餐购买记录，如果有，则直接邀请失败，该手机号码已是寻蜜客
				List<Map<Object, Object>> saasOrderList = saasOrderDao.querySaasOrderList(Integer.valueOf(id));
				if (saasOrderList != null && saasOrderList.size() > 0) {
					model.addAttribute("data", new BaseResponse(ResponseCode.FAILURE, "邀请失败，该手机号码已是寻蜜客"));
					return "xmer/error";
				}

				// 查询套餐
				Map<Object, Object> paramMap = new HashMap<>();
				paramMap.put("status", Constant.SAASPACKAGE_STATUS_NORMAL);// 数据状态正常
				List<Map<Object, Object>> saasList = saasPackageService.querySaasPackageList(paramMap);
				// 计算订单差价
				List<Map<Object, Object>> discountList = new ArrayList<Map<Object, Object>>();
				for (Map<Object, Object> discountMap : saasList) {
					// 订餐优惠差价= （原价-结算价）除以套数
					double discountPrice = ArithUtil.div(
							ArithUtil.sub(Double.valueOf(discountMap.get("mprice").toString()),
									Double.valueOf(Double.valueOf(discountMap.get("price").toString()))),
							Double.valueOf(discountMap.get("nums").toString()));
					Map<Object, Object> priceMap = new HashMap<Object, Object>();
					priceMap.put("discountPrice", discountPrice);
					priceMap.put("count", discountMap.get("nums"));
					discountList.add(priceMap);
				}
				MapResponse mapResponse = new MapResponse(ResponseCode.SUCCESS, "成功");
				Map<Object, Object> map = new HashMap<Object, Object>();
				map.put("uid", id);
				map.put("phone", phone);
				map.put("uname", phone);
				map.put("saasPackageList", saasList);
				map.put("parentid", uid);
				map.put("discountList", discountList);// 差价
				// 登入该用户保存到redis
				// 生成token
				String time = String.valueOf(System.currentTimeMillis());
				String timeStr = time.substring(time.length() - 4, time.length());
				// key md5加密 3651472365XXXXXX
//				String tokenKey = DigestUtils.md5Hex(id + timeStr + id + utilClass.Random6Num());
//				String sessionToken = MD5.Encode(tokenKey);
//				sessionTokenService.setStringForValue(sessionToken, id + "", 30, TimeUnit.DAYS);
//				map.put("sessiontoken", sessionToken);
				
				//自动登录
				LoginRequest loginRequest = new LoginRequest();
				loginRequest.setPhone(urs.getPhone());
				loginRequest.setPassWord(urs.getPassword());
				MapResponse response = (MapResponse)userLoginService.userLogin(loginRequest);
				if (response!=null && response.getState()==100) {
					map.put("sessiontoken", response.getResponse().get("sessionToken"));
				}else {
					map.put("sessiontoken", "");
				}
				
				mapResponse.setResponse(map);
				model.addAttribute("data", mapResponse);
				return "signprogress/sign_pay";
			} else {// 如果不存在使用手机号号码注册帐号
				try {
					if (psw == null || psw.length() == 0) {
						model.addAttribute("data", new BaseResponse(ResponseCode.DATAERR, "密码不能为空"));
						return "xmer/error";
					}

					// 添加urs
					Map<Object, Object> usrMap = new HashMap<Object, Object>();
					usrMap.put("phone", phone);// 用户电话号码
					usrMap.put("psw", psw);// 用户预设密码
					id = ursService.addUrs(usrMap); // 根据手机号码注册帐号,返回用户UID
					// 添加到ursInfo
					UrsInfo ursInfo = new UrsInfo();
					ursInfo.setUid(id);
					ursInfo.setReferrerMemberId(uid + "");
					ursInfo.setPhone(phone);
					ursInfo.setUname(phone);
					ursService.addUrsInfo(ursInfo);// 添加用户信息
					// 默认钱包的签名
					StringBuffer sb = new StringBuffer();
					sb.append(String.valueOf(id)).append("0").append("0").append("0.00").append("0.00").append("0.00")
							.append("0.00").append("0").append("0.00");
					String sign = DigestUtils.md5Hex(sb.toString());
					/**
					 * sb.append(String.valueOf(wallet.getUid())).append(String.
					 * valueOf(wallet.getSellerId()))
					 * .append(String.valueOf(wallet
					 * .getJointId())).append(StringUtils
					 * .trimToEmpty(wallet.getPayPwd()))
					 * .append(wallet.getAmount
					 * ().setScale(2).toString()).append(
					 * wallet.getBalance().setScale(2).toString())
					 * .append(wallet.getCommision().setScale(2).toString())
					 * .append(wallet.getZbalance().setScale(2).toString())
					 * .append(String.valueOf(wallet.getIntegral().longValue()))
					 * .append(wallet.getSellerAmount().setScale(2).toString());
					 */

					// 给用户创建一个空钱包
					walletService.addUserWallert(id, sign);
					// 查询套餐
					Map<Object, Object> paramMap = new HashMap<>();
					paramMap.put("status", Constant.SAASPACKAGE_STATUS_NORMAL);// 数据状态正常
					List<Map<Object, Object>> saasList = saasPackageService.querySaasPackageList(paramMap);
					MapResponse mapResponse = new MapResponse(ResponseCode.SUCCESS, "成功");
					Map<Object, Object> map = new HashMap<Object, Object>();
					// 计算订单差价
					List<Map<Object, Object>> discountList = new ArrayList<Map<Object, Object>>();
					for (Map<Object, Object> discountMap : saasList) {
						// 订餐优惠差价= （原价-结算价）除以套数
						double discountPrice = ArithUtil.div(
								ArithUtil.sub(Double.valueOf(discountMap.get("mprice").toString()),
										Double.valueOf(Double.valueOf(discountMap.get("price").toString()))),
								Double.valueOf(discountMap.get("nums").toString()));
						Map<Object, Object> priceMap = new HashMap<Object, Object>();
						priceMap.put("discountPrice", discountPrice);
						priceMap.put("count", discountMap.get("nums"));
						discountList.add(priceMap);
					}
					map.put("discountList", discountList);// 差价
					map.put("uid", id);
					map.put("phone", phone);
					map.put("uname", phone);
					map.put("saasPackageList", saasList);
					map.put("parentid", uid);
					// 登入该用户保存到redis
					// 生成token
//					String time = String.valueOf(System.currentTimeMillis());
//					String timeStr = time.substring(time.length() - 4, time.length());
					// key md5加密 3651472365XXXXXX
//					String tokenKey = DigestUtils.md5Hex(id + timeStr + id + utilClass.Random6Num());
//					String sessionToken = MD5.Encode(tokenKey);
//					sessionTokenService.setStringForValue(sessionToken, id + "", 30, TimeUnit.DAYS);
//					map.put("sessiontoken", sessionToken);
					
					//自动登录
					LoginRequest loginRequest = new LoginRequest();
					
					//对用户密码进行加密  前6位和后六为对调
					String newpew = MD5.Encode(psw);
					String str1=newpew.substring(0,6);
					String str2=newpew.substring(6,26);
					String str3=newpew.substring(26);
					String password=str3+str2+str1;
					
					loginRequest.setPhone(phone);
					loginRequest.setPassWord(password);
					MapResponse response = (MapResponse)userLoginService.userLogin(loginRequest);
					if (response!=null && response.getState()==100) {
						map.put("sessiontoken", response.getResponse().get("sessionToken"));
					}else {
						map.put("sessiontoken", "");
					}
					
					mapResponse.setResponse(map);
					model.addAttribute("data", mapResponse);

					// 发送一条注册信息
					String message = propertiesUtil.getValue("message", "conf_xmer.properties");
					log.info("message:" + message);
					Map<Object, Object> massageMap = new HashMap<Object, Object>();
					massageMap.put("account", phone);
					massageMap.put("password", psw);
					String text = String.format(message, phone, psw);
					massageMap.put("text", text);
					sendCodeService.sendMassage(massageMap);

				} catch (Exception e) {
					e.printStackTrace();
					model.addAttribute("data", new BaseResponse(ResponseCode.FAILURE, "注册帐号失败"));
					return "xmer/error";
				}
			}
			return "signprogress/sign_pay";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("data", new BaseResponse(ResponseCode.FAILURE, "邀请失败！"));
			return "xmer/error";
		}

	}

	@RequestMapping(value = "invitedJoinXMER", method = RequestMethod.POST, produces = {
			"application/json;charset=utf-8" })
	@ResponseBody
	public Object getInvitedJoinXmer(String phone, String uid, String phoneCode) {
		if (phone == null || phone.length() == 0) {
			return new BaseResponse(ResponseCode.DATAERR, "手机号码有误");
		}
		if (uid == null || uid.length() == 0) {
			return new BaseResponse(ResponseCode.DATAERR, "用户ID不能为空");
		}
		if (phoneCode == null || phoneCode.length() == 0) {
			return new BaseResponse(ResponseCode.DATAERR, "验证码不能为空");
		}
		String code = sessionTokenService.getStringForValue(phone + Constant.USER_XMER_KEY) + "";
		if (!code.equals(phoneCode)) {
			return new BaseResponse(ResponseCode.DATAERR, "验证码错误");
		}
		return new BaseResponse(ResponseCode.SUCCESS, "数据格式校验成功");

	}
	//
	// @RequestMapping(value="getInvitedJoinXMER",method=RequestMethod.GET,produces={"application/json;charset=utf-8"})
	// public String invitedJoinXMER(Model model,String response){
	// JSONObject json = JSON.parseObject(response);
	// JSONArray arr = JSON.parseArray(json.getString("saasPackageList"));
	// List<Object> list = new ArrayList<Object>();
	// for(Object map : arr){
	// list.add(map);
	// }
	// model.addAttribute("data", list);
	// return "signprogress/sign_pay";
	// }
}
