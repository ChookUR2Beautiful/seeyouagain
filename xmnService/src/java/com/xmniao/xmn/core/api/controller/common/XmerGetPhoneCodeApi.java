package com.xmniao.xmn.core.api.controller.common;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.mongodb.util.Hash;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.Constant;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.SendCodeRequest;
import com.xmniao.xmn.core.util.utilClass;
import com.xmniao.xmn.core.xmer.service.SendCodeService;

@Controller
public class XmerGetPhoneCodeApi {

	// 报错
	private final Logger log = Logger.getLogger(XmerGetPhoneCodeApi.class);

	// 注入短信发送service
	@Autowired
	private SendCodeService senCodeService;

	/**
	 * 注入redis缓存
	 */
	@Autowired
	private SessionTokenService sessionTokenService;

	// 验证码
	int code = 0;
	

	@RequestMapping(value="xmerGetPhoneCode",method=RequestMethod.GET,produces={"application/json;charset=utf-8"})
	@ResponseBody
	public Object xmerGetPhoneCode(SendCodeRequest sendCodeRequest) {
			if (sendCodeRequest.getSendType() == 1) {
				// 四位随机数
				code = utilClass.RandomNum();
			}
			if (sendCodeRequest.getSendType() == 2) {
				// 六位数
				code = utilClass.Random6Num();
			}
			String strphone = sendCodeRequest.getPhone();
			
			try {
//					if(n>=0){//如果发送3次以上
						if(sendCodeRequest.getCode()!=null&&sendCodeRequest.getCode().length()>0){
							String key=Constant.VALIDATE_CODE+strphone;
							String code=sessionTokenService.getStringForValue(key)+"";
							if(!code.toLowerCase().equals(sendCodeRequest.getCode().toLowerCase())){
								return new BaseResponse(ResponseCode.FAILURE, "验证码错误");
							}else{
								sessionTokenService.deleteSessionToken(key);
							}
						}else{
							return new BaseResponse(ResponseCode.FAILURE,"图片验证码不能为空");
						}
//				}else{
//					sessionTokenService.setStringForValue(numKey, "1", 1, TimeUnit.DAYS);
//				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			//判断手机号码是否为寻蜜客
			int result = senCodeService.checkPhone(sendCodeRequest.getPhone());
			sendCodeRequest.setSendType(code);
			String msg = senCodeService.sendCode(sendCodeRequest);
			if (msg != null && !msg.equals("")) {
				String state = JSON.parseObject(msg).getString("state");
				if (state.equals("100")) {
					String codekey = strphone + Constant.USER_XMER_KEY;
					log.info("验证码在redis的key:" + codekey);
					sessionTokenService.setStringForValue(codekey,String.valueOf(code), 5, TimeUnit.MINUTES); //缓存五分钟
					//返回发送次数
					Map<Object,Object> map=new HashMap<Object, Object>();
					
					log.info("send smg success!");
					if(result == 0){
						//非寻蜜鸟用户的时候
						MapResponse mapResponse=new MapResponse(ResponseCode.INVITED_SUCCESS, "验证码发送成功！");
						mapResponse.setResponse(map);
						return mapResponse;
					}else{
						MapResponse mapResponse=new MapResponse(ResponseCode.SUCCESS, "验证码发送成功！");
						mapResponse.setResponse(map);
						return mapResponse;
					}
				}
			}
			return new BaseResponse(ResponseCode.FAILURE, "发送验证码失败!");
	}

}