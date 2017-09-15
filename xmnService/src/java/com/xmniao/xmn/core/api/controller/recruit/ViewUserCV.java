package com.xmniao.xmn.core.api.controller.recruit;

import java.util.List;
import java.util.Map;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseRequest;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.recruit.service.UserService;

/**
 * 
* 项目名称：xmnService   
* 类名称：ViewUserCV   
* 类描述：查看我的简历信息接口   
* 创建人：liuzhihao   
* 创建时间：2016年5月21日 下午2:42:39   
* @version    
*
 */
@Controller
public class ViewUserCV implements BaseVControlInf{
	
	//日志
	private Logger log = Logger.getLogger(ViewUserCV.class);
	
	//注入用户service
	@Autowired
	private UserService userService;
	
	//注入验证
	@Autowired
	private Validator validator;
	
	//注入缓存
	@Autowired
	private SessionTokenService sessionTokenService;
	
	@RequestMapping(value="/viewUserCV",method=RequestMethod.POST,produces={"application/json;charset=utf-8"})
	@ResponseBody
	public Object viewUserCV(BaseRequest baseRequest){
		//验证参数
		List<ConstraintViolation> param = validator.validate(baseRequest);
		if(param.size() >0 && param != null){
			log.info("提交的数据不能为空"+param.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据不能为空，请检查提交的数据");
		}
		
		//验证token
		String token = baseRequest.getSessiontoken();
		if(StringUtils.isEmpty(token)){
			return new BaseResponse(ResponseCode.TOKENERR,"无效token，请检查token是否正确");
		}
		Map<Object,Object> map = sessionTokenService.getsessionToken(token);
		if(map != null){
			return versionControl(baseRequest.getApiversion(),baseRequest);
		}
		
		return  new BaseResponse(ResponseCode.TOKENERR,"亲，你的token已过期，请重新登录");
	}

	@Override
	public Object versionControl(int v, Object object) {
		switch(v){
		case 1: return versionControlOne(object);
		case 2: return versionControlOne(object);
		default : return new BaseResponse(ResponseCode.ERRORAPIV,"版本号不正确,请重新下载客户端");
		}
	}

	private Object versionControlOne(Object object) {
		BaseRequest baseRequest = (BaseRequest) object;
		return userService.viewUserInfo(baseRequest);
	}
}
