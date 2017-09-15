package com.xmniao.xmn.core.api.controller.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseRequest;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.util.PropertiesUtil;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：AppLoadUrlApi   
* 类描述：   获取app客户端下载地址
* 创建人：yezhiyong   
* 创建时间：2016年6月16日 下午2:30:54   
* @version    
*
 */
@Controller
public class XmniaoValidateSessionToken{
	
	/**
	 * 报错日志
	 */
	private final Logger log = Logger.getLogger(XmniaoValidateSessionToken.class);
	
	/**
	 * 注入validator验证
	 */
	@Autowired
	private Validator validator;
	
	
	@Autowired
	private String fileUrl;
	
	@Autowired
	private PropertiesUtil propertiesUtil;
	
	/**
	 * 注入redis 缓存
	 */
	@Autowired
	private SessionTokenService sessionTokenService;
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	@RequestMapping(value="/xmniao/validate/token",produces={"application/json;charset=utf-8"})
	@ResponseBody
	public Object queryLoadUrl(BaseRequest request){
		//验证请求数据的合法性
		List<ConstraintViolation> result = validator.validate(request);
		if(result.size() > 0){
			log.info("数据提交有问题"+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据有问题");
		}
		MapResponse response =  null;
		try {
			//初始化返回数据    默认token状态失效
			Map<Object, Object> resMap = new HashMap<Object, Object>();
			resMap.put("tokenState", 0);
			response = new MapResponse(ResponseCode.SUCCESS, "操作成功");
			response.setResponse(resMap);
			
			//验证sessionToken 是否是空置
			if (StringUtils.isEmpty(request.getSessiontoken()) || "null".equalsIgnoreCase(request.getSessiontoken())) {
				return response;
			}
			
			//验证token  获取value是否空置
			String uid = sessionTokenService.getStringForValue(request.getSessiontoken()) + "";
			if (StringUtils.isEmpty(uid) || "null".equalsIgnoreCase(uid)) {
				return response;
				
			}else {
				
				String  userRedisKey = "USERID_"+uid;
 				Map<Object, Object> userMap = stringRedisTemplate.opsForHash().entries(userRedisKey);
 				//验证 USERID_XXXXXX 是否是空置
 				if (userMap==null || userMap.size()<=0) {
 					return response;
				}
 				
 				//验证两处 token是否一致
				String code = userMap.get("code")!=null?userMap.get("code").toString():"";
				if (!request.getSessiontoken().equals(code)) {
					
					stringRedisTemplate.delete(userRedisKey);
					stringRedisTemplate.delete(request.getSessiontoken());
					
					return response;
				}
				
				//全部验证通过 返回通过标示
				response = new MapResponse(ResponseCode.SUCCESS, "操作成功");
				resMap.put("tokenState", 1);
				response.setResponse(resMap);
			}
			
		} catch (Exception e) {
			return new MapResponse(ResponseCode.FAILURE, "操作异常");
		}
		return response;
	}
}
