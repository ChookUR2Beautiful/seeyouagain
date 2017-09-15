/**
 * 2016年8月18日 上午10:43:08
 */
package com.xmniao.xmn.core.api.controller.live;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.common.Constant;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.live.SensitiveWordRequest;
import com.xmniao.xmn.core.live.service.ViewerLiveService;
import com.xmniao.xmn.core.util.EmojiFilter;
import com.xmniao.xmn.core.util.SensitiveWordUtil;


/**
 * 
*    
* 项目名称：xmnService   
* 类名称：SensitiveWordApi   
* 类描述：   敏感词汇处理
* 创建人：yezhiyong   
* 创建时间：2016年10月8日 下午3:34:46   
* @version    
*
 */
@Controller
@RequestMapping("/live")
public class SensitiveWordApi implements BaseVControlInf {

	/**
	 * 注入日志
	 */
	private final Logger log = Logger.getLogger(PersonalCenterApi.class);
		
	/**
	 * 注入验证
	 */
	@Autowired
	private Validator validator;
	
	/**
	 * 注入viewerLiveService
	 */
	@Autowired
	private ViewerLiveService viewerLiveService;
	
	/**
	 * 注入sensitiveWordUtils
	 */
	@Autowired
	private SensitiveWordUtil sensitiveWordUtil;
	
	/**
	 * 注入redis缓存
	 */
	@Autowired
	private StringRedisTemplate stringRedisTemplate;  
	
	/**
	 * 
	* @Title: insertFocusLive
	* @Description: 敏感词汇处理
	* @return Object    返回类型
	* @throws
	 */
	@RequestMapping(value="/sensitiveWord",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public Object insertFocusLive(SensitiveWordRequest sensitiveWordRequest){
		//日志
		log.info("sensitiveWordRequest data:" + sensitiveWordRequest.toString());
		//验证
		List<ConstraintViolation> result = validator.validate(sensitiveWordRequest);
		if(result.size() >0 && result != null){
			log.info("提交的数据有问题"+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据有问题");
		}
		return versionControl(sensitiveWordRequest.getApiversion(),sensitiveWordRequest);
	}
	
	public Object versionControl(int v, Object object) {
		switch(v){
		case 1:
			return versionControlOne(object);
			default :
				return new BaseResponse(ResponseCode.ERRORAPIV,"版本号不正确,请重新下载客户端");
		}
	}
	
	private Object versionControlOne(Object object) {
		SensitiveWordRequest sensitiveWordRequest = (SensitiveWordRequest) object;
		//结果集
		Map<Object, Object> resultMap = new HashMap<>();
		//响应
		MapResponse response = null;
		//过滤后的文本内容
		String newText = sensitiveWordRequest.getText();
	
		try {
			//敏感词汇过滤
			newText = sensitiveWordUtil.sensitiveWordDeal(newText);
			//新增用户发聊天标示key  用于匹配机器人是否该发自动聊天消息
//			public final static String ROBOT_SEND_GIFT_KEY = "robot_send_gift_key";
//			if (stringRedisTemplate.hasKey(Constant.ROBOT_SEND_MSG_KEY) == true) {
//				stringRedisTemplate.expire(Constant.ROBOT_SEND_MSG_KEY, 20, TimeUnit.SECONDS);
//			}else {
//				stringRedisTemplate.opsForValue().set(Constant.ROBOT_SEND_MSG_KEY, "true");
//				stringRedisTemplate.expire(Constant.ROBOT_SEND_MSG_KEY, 20, TimeUnit.SECONDS);
//			}
			
			//用户发送弹幕,保存redisKey ,用于匹配机器人是否 行动
			String key = Constant.ROBOT_ACTION_KEY + sensitiveWordRequest.getLiveRecordId();
			if (stringRedisTemplate.hasKey(key)) {
				stringRedisTemplate.expire(key, 90, TimeUnit.SECONDS);
			}else {
				stringRedisTemplate.opsForValue().set(key, "true");
				stringRedisTemplate.expire(key, 90, TimeUnit.SECONDS);
			}
			
			//响应
			resultMap.put("text", newText);
			response = new MapResponse(ResponseCode.SUCCESS, "敏感词汇处理成功");
			response.setResponse(resultMap);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("处理敏感词汇出错,信息如下" + e.getMessage());
			//响应
			resultMap.put("text", newText);
			response = new MapResponse(ResponseCode.SUCCESS, "敏感词汇处理成功");
			response.setResponse(resultMap);
		}
		
		return response;
	}

}
