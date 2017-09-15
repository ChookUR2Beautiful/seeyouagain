package com.xmniao.xmn.core.api.controller.common;

import java.net.MalformedURLException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.common.Constant;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.xmer.service.SendCodeService;

@Controller
public class LanguageCodeApi{
	
	//注入日志
	private final Logger log = Logger.getLogger(LanguageCodeApi.class);
	
	//注入短信发送service
	@Autowired
	private SendCodeService senCodeService;
	
	@RequestMapping(value="languageCode",method=RequestMethod.POST,produces={"application/json;charset=utf-8"})
	public Object languageCode(String phone) throws MalformedURLException{
		try{
			//验证电话号码是否为空
			if(StringUtils.isEmpty(phone)){
				log.info("电话号码不能为空");
				return new BaseResponse(ResponseCode.DATAERR,"电话号不能为空");
			}
			//验证电话号码是否正确
			if(!phone.matches(Constant.PHONE_REG)){
				log.info("电话号码格式不正确");
				return new BaseResponse(ResponseCode.DATAERR,"电话号码格式不正确");
			}
			//生成语音短信
			String msg = senCodeService.languageCode(phone);
			if(!StringUtils.isEmpty(msg)){
				JSONObject json = new JSONObject();
				String status = json.parseObject(msg).getString("state");
				if(Integer.valueOf(status) == 100){
					return new BaseResponse(ResponseCode.SUCCESS,"发送语音验证码成功");
				}
			}
			return new BaseResponse(ResponseCode.FAILURE,"发送语音验证码失败");
		}catch(Exception e){
			e.printStackTrace();
			log.info("生成语音验证码异常");
			return new BaseResponse(ResponseCode.FAILURE,"生成语音验证码异常");
		}
	}


}
