package com.xmniao.xmn.core.xmer.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.common.Constant;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.RegisterRequest;
import com.xmniao.xmn.core.util.DateUtil;
import com.xmniao.xmn.core.util.MD5;
import com.xmniao.xmn.core.xmer.dao.RegisterDao;

/**
 * 
* 项目名称：xmnService   
* 类名称：RegisterService   
* 类描述： 注册接口
* 创建人：liuzhihao   
* 创建时间：2016年5月23日 下午4:56:05   
* @version    
*
 */
@Service
public class RegisterService {

	//注入dao
	@Autowired
	private RegisterDao registerDao;
	
	/**
	 * 注入redis缓存
	 */
	@Autowired
	private StringRedisTemplate stringredisTemplate;  
	
	public Object xmerRegister(RegisterRequest registerRequest){
		//验证电话号码
		if(Constant.PHONE_REG.matches(registerRequest.getPhone())){
			return new BaseResponse(ResponseCode.PHONE_REG,"你输入的手机号码不合法");
		}
		//获取验证码
		String code = stringredisTemplate.opsForValue().get(Constant.USER_XMER_KEY+registerRequest.getPhone());
		if(!code.equals(registerRequest.getCode())){
			return new BaseResponse(ResponseCode.REGISTER_CODE,"亲，您输入的验证码不正确，请确认！");
		}
		//对密码进行MD5加密
		String password = MD5.Encode(registerRequest.getPassword()); 
		//插入注册信息
		Map<Object,Object> map = new HashMap<Object,Object>();
		map.put("uname", registerRequest.getPhone());
		map.put("password", password);
		if(insertBurs(map,registerRequest) != 0 && insertBursInfo(map,registerRequest) != 0 && insertWallet(map,registerRequest) != 0){
			return new BaseResponse(ResponseCode.SUCCESS,"注册成功");
		}
		return new BaseResponse(ResponseCode.REGISTER,"注册失败");
	}
	
	//插入用户表
	private int insertBurs(Map<Object,Object> map,RegisterRequest registerRequest){
		map.put("regtime", DateUtil.format(DateUtil.now()));
		if(registerRequest.getSystemversion().toUpperCase().contains("IOS")){
			map.put("regtype", 7);//寻蜜鸟ios客户端
			map.put("phonetype", 2);
			map.put("usertype", 1);
		}
		if(registerRequest.getSystemversion().toUpperCase().contains("ANDROID")){
			map.put("regtype", 6);//寻蜜鸟安卓客户端
			map.put("phonetype", 1);
			map.put("usertype", 1);
		}
		map.put("phone", registerRequest.getPhone());
		return registerDao.insertRegisterInfoToBurs(map);
	}
	
	//插入用户详情表
	private int insertBursInfo(Map<Object,Object> map,RegisterRequest registerRequest){
		map.put("phone", registerRequest.getPhone());
		if(registerRequest.getId() == null){
			registerRequest.setId(0);
		}
		map.put("referrer_member_id", registerRequest.getId());
		return registerDao.insertRegisterInfoToBursInfo(map);
	}
	
	//插入钱包表
	private int insertWallet(Map<Object,Object> map,RegisterRequest registerRequest){
		String sign  = MD5.Encode(String.valueOf(map.get("id")));
		map.put("sign", sign);
		map.put("singtype", 1);
		map.put("applydate", DateUtil.format(DateUtil.now()));
		map.put("lastdate", DateUtil.now());
		return registerDao.insertRegisterInfoToWallet(map);
	}
}
