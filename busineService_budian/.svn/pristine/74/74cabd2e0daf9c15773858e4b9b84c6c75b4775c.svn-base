package com.xmniao.service.common;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;



import com.alibaba.fastjson.JSONObject;
import com.xmniao.thrift.common.FailureException;
import com.xmniao.thrift.common.ResponseData;
import com.xmniao.thrift.common.XmnCommonService;

@Service("XmnCommonServiceImpl")
public class XmnCommonServiceImpl implements XmnCommonService.Iface{
	
	private static Logger log = Logger.getLogger(XmnCommonServiceImpl.class);
	
	@Resource(name="smsqueue")
	private String smsQueue;
	
	@Autowired
	private StringRedisTemplate redisTemplate;
	
	@Override
	public ResponseData sendXmnSms(Map<String, String> paramMap)
			throws FailureException, TException {
		
		log.info("开始下发短信：sendXmnSms"+paramMap);
		
		ResponseData responseData = new ResponseData();
		if(StringUtils.isBlank(paramMap.get("phone"))||StringUtils.isBlank(paramMap.get("smsContent"))){
			log.error("发送短信失败，手机号或短信内容为空");
			responseData.setState(1);
			responseData.setMsg("发送短信失败，手机号或短信内容为空");
			return responseData;
		}
		
		String[] phones = paramMap.get("phone").split(",");
		
		 Map<String,String> sendSmsMap = new HashMap<String,String>();
		 sendSmsMap.put("smscontent",paramMap.get("smsContent"));
		for (String phone : phones) {
	        //手机号码
	        sendSmsMap.put("phoneid",phone);
	        
	        String smsJson = JSONObject.toJSONString(sendSmsMap);
	        log.info("SendSms Redis Key:" + smsQueue + "==Send Sms JSON:" + smsJson);
	        
	        try {
				redisTemplate.opsForList().rightPush(smsQueue, smsJson);
			} catch (Exception e) {
				log.error("用户"+phone+"发送短信失败",e);
			}
		}
		
		log.info("短信发送完成。");
		responseData.setState(0);
		return responseData;
	}

}
