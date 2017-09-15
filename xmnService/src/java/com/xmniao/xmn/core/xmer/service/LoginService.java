package com.xmniao.xmn.core.xmer.service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.Constant;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.LoginRequest;
import com.xmniao.xmn.core.util.DateUtil;
import com.xmniao.xmn.core.util.utilClass;
import com.xmniao.xmn.core.xmer.dao.LoginDao;

@Service
public class LoginService {

	//注入loginDao
	@Autowired
	private LoginDao loginDao;
	
	/**
	 * 注入redis缓存
	 */
	@Autowired
	private StringRedisTemplate stringredisTemplate;
	
	@Autowired
	private SessionTokenService sessionTokenService;
	
	//登录
	public Object login(LoginRequest loginRequest){
		try{
			Map<Object,Object> map = new HashMap<Object,Object>();
			//判断账号是否正确
			String acount = loginRequest.getAccount();
			if(loginDao.checkAccount(acount) == 0){
				return new BaseResponse(ResponseCode.FAILURE,"登录失败，账号不正确");
			}
			//判断密码是否正确
			Integer pwdtype = loginRequest.getPwdtype();
			if(pwdtype == 0){
				//登录密码
				String password = loginRequest.getPassword();
				map.put("account", acount);
				map.put("password", password);
				Map<Object,Object> loginmap = loginDao.login(map);
				if(loginmap !=null){
//					String status = loginmap.get("status").toString();
//					if(status.equals(Constant.USER_NOT_AUDIT.toString())){
//						return new BaseResponse(ResponseCode.LOGINERR, "亲，您还未通过审核，请联系寻蜜鸟客服！");
//					}
					//生成token
					String time = String.valueOf(System.currentTimeMillis());
					String timeStr = time.substring(time.length() - 4,
							time.length());
					// key md5加密 3651472365XXXXXX
					String tokenKey = DigestUtils.md5Hex(loginmap.get("uid") + timeStr+ loginmap.get("uid") + utilClass.Random6Num());
					//判断生成的token是否已存在
					if(stringredisTemplate.hasKey(tokenKey)){
						stringredisTemplate.delete(tokenKey);//删除该token
					}else{
						Map<Object,Object> tokenmap = new HashMap<Object,Object>();
						//获取用户头像和寻蜜客id
						Map<Object,Object> xmermap = loginDao.queryXmerByUid(Integer.valueOf(loginmap.get("uid").toString()));
						if(xmermap == null){
							xmermap = new HashMap<Object,Object>();
							xmermap.put("avatar", "");
							xmermap.put("id", "");
						}
						//获取用户余额和积分
						Map<Object,Object> walletmap = loginDao.queryWalletByUid(Integer.valueOf(loginmap.get("uid").toString()));
						if(walletmap == null){
							walletmap = new HashMap<Object,Object>();
							walletmap.put("amount", 0D);
							walletmap.put("integral", 0D);
							walletmap.put("sign", "");
						}
						//把相关信息存入token中
						tokenmap.put("uid", loginmap.get("uid").toString());
						tokenmap.put("tokendate", DateUtil.format(DateUtil.now()));
						tokenmap.put("balance", walletmap.get("amount").toString());
						tokenmap.put("integral", walletmap.get("integral").toString());
						tokenmap.put("sign", walletmap.get("sign").toString());
						tokenmap.put("pic", xmermap.get("avatar").toString());//用户头像
						tokenmap.put("xmerid", xmermap.get("id").toString());
						stringredisTemplate.opsForHash().putAll(tokenKey, tokenmap);
						Calendar calendar = Calendar.getInstance();
						calendar.add(Calendar.MONTH, 1); // 缓存一个月
						// 缓存一天后失效
						stringredisTemplate.expireAt(tokenKey, calendar.getTime());
						//添加返回参数
						loginmap.put("balance", walletmap.get("amount").toString());
						loginmap.put("integral", walletmap.get("integral").toString());
						loginmap.put("sign", walletmap.get("sign").toString());
						loginmap.put("xmerid", xmermap.get("id"));
					}
					//	插入登录记录
					map.clear();//清空map
					map.put("uid", loginmap.get("uid"));
					map.put("logindate", DateUtil.now());
					map.put("logintype", loginRequest.getPwdtype());//登录类型 0密码登录 1随机密码登录
					loginDao.addLoginRecord(map);
					loginmap.put("token", tokenKey);
					MapResponse response = new MapResponse(ResponseCode.SUCCESS,"成功");
					response.setResponse(loginmap);
					return response;
				}
			}
			if(pwdtype == 1){
				//随机密码
				
				
			}
			return new BaseResponse(ResponseCode.FAILURE,"失败，提交的数据问题");
		}catch(Exception e){
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE,"未知错误，请联系管理员");
		}
	}
	
	
	public Object checkSession(String token){
		// 若session为空
		if (StringUtils.isEmpty(token)) {
			return new MapResponse(ResponseCode.TOKENERR, "会话令牌错误!");
		}
		// 从缓存中取出Session信息
		Map<Object, Object> tokenMap = sessionTokenService.getsessionToken(token);
		if (tokenMap != null) {
			MapResponse response = new MapResponse(ResponseCode.SUCCESS, "登录成功");
			response.setResponse(tokenMap);
			return response;
		}
		return new MapResponse(ResponseCode.TOKENERR, "会话令牌错误");
	}
}
