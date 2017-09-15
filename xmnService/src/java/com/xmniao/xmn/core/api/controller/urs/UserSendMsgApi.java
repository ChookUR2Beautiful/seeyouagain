package com.xmniao.xmn.core.api.controller.urs;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.oval.Validator;

import org.apache.log4j.Logger;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.common.Constant;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.login.UserValidateRequest;
import com.xmniao.xmn.core.login.UserLoginService;
import com.xmniao.xmn.core.login.VerifyCodeService;
import com.xmniao.xmn.core.thrift.ResponseData;
import com.xmniao.xmn.core.thrift.business.java.XmnCommonService;
import com.xmniao.xmn.core.util.ThriftBusinessUtil;
import com.xmniao.xmn.core.verification.dao.UrsDao;
import com.xmniao.xmn.core.verification.entity.Urs;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：LoginApi   
* 类描述：用户登录
* 创建人：yhl  
* 创建时间：2017年4月27日16:31:22
* @version    
*
 */
@Controller
public class UserSendMsgApi{

	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(UserSendMsgApi.class);
	
	/**
	 * 注入验证
	 */
	@Autowired
	private Validator validator;
	
	@Autowired
	private UserLoginService loginService;
	
	@Autowired
	private VerifyCodeService verifyCodeService;
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	@Autowired
	private ThriftBusinessUtil thriftBusinessUtil;
	
	@Autowired
	private UrsDao ursDao;
	
	/**
	 * 
	* @Description: 发送验证码请求
	* @return Object    返回类型
	* @throws 
	 */
	@RequestMapping(value = "/user/send/msg" ,produces={"application/json;charset=utf-8"})
	@ResponseBody
	public Object productionCode(UserValidateRequest request)throws Exception{
		
		try {
			
			MapResponse response = null;
			//验证手机号码是否填写
			String regExp = "^[1][0-9]{10}$";  
			Pattern p = Pattern.compile(regExp);  
			Matcher hasNum = p.matcher(request.getPhone());
			if (!hasNum.matches() && request.getPhone().length()!=11) {
				return new MapResponse(ResponseCode.FAILURE, "请输入正确手机号码");
			}else {
				
				//查询手机号码是否被注册过
				Urs urs = ursDao.getUrsByUname(request.getPhone());
				if (request.getSource()==1) {
					if (urs!=null) {
						return new MapResponse(ResponseCode.FAILURE, "该手机已经被注册");
					}
				}
				if (request.getSource()==0) {
					if (urs==null) {
						return new MapResponse(ResponseCode.FAILURE, "该手机未被注册,请核对!");
					}
				}
				
				
				//获取redis 信息 对数据    匹配验证码  
				String valiCode = stringRedisTemplate.opsForValue().get(Constant.VALIDATE_CODE+ request.getPhone());
				if (valiCode!=null && valiCode.equalsIgnoreCase(request.getCode())) {
					
					//匹配完毕 发短信
					XmnCommonService.Client client = null;
					try {
						
						//生成短信验证码key
						int index = (int)((Math.random()*9+1)*1000);
						String smsCodeKey = "SMS_"+request.getPhone();
						stringRedisTemplate.opsForValue().set(smsCodeKey, index+"");
						stringRedisTemplate.expire(smsCodeKey, 5, TimeUnit.MINUTES);
						
						//发送短信
						Map<String, String> sendMap = new HashMap<String, String>();
						sendMap.put("phone", request.getPhone());
						sendMap.put("smsContent", "您当前注册寻蜜鸟会员验证码:"+index+",有效期5分钟,请尽快验证!");
						TMultiplexedProtocol tMultiplexedProtocol = thriftBusinessUtil.getProtocol("XmnCommonService");
						client = new XmnCommonService.Client(tMultiplexedProtocol); 
						thriftBusinessUtil.openTransport();
						ResponseData responseData =	client.sendXmnSms(sendMap);
						if (responseData!=null && responseData.getState()!=0) {
							return new MapResponse(ResponseCode.FAILURE, "发送短信失败");
						}
						
						//删除图文验证码的key
						stringRedisTemplate.delete(request.getPhone());
						
						response = new MapResponse(ResponseCode.SUCCESS, "操作成功");
						return response;
						
					}catch(Exception e){
						log.info("发送短信验证码失败");
						e.printStackTrace();
						return new MapResponse(ResponseCode.FAILURE, "操作失败");
					}
					
				}else {
					return new MapResponse(ResponseCode.FAILURE, "验证码不正确");
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new MapResponse(ResponseCode.FAILURE, "发送短信异常");
		}
		
	}
	
}
