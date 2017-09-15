package com.xmniao.xmn.core.api.controller.live;

import java.util.List;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.PhoneRequest;
import com.xmniao.xmn.core.live.service.PrivateMessageService;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：GetBlackMarkApi   
* 类描述：   验证是否给拉黑
* 创建人：yezhiyong   
* 创建时间：2016年11月3日 下午5:17:48   
* @version    
*
 */
@Controller
@RequestMapping("/live")
public class GetBlackMarkApi implements BaseVControlInf {
	
	/**
	 * 注入日志
	 */
	private final Logger log = Logger.getLogger(GetBlackMarkApi.class);
		
	/**
	 * 注入验证
	 */
	@Autowired
	private Validator validator;
	
	/**
	 * 注入privateMessageService
	 */
	@Autowired
	private PrivateMessageService privateMessageService;
	
	/**
	 * 
	* @Title: getXmerMark
	* @Description: 验证是否给拉黑
	* @return Object    返回类型
	* @throws
	 */
	@RequestMapping(value="/secretmark/getBlackMark", method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public Object getBlackMark(PhoneRequest phoneRequest){
		//日志
		log.info("phoneRequest data:" + phoneRequest.toString());
		//验证
		List<ConstraintViolation> result = validator.validate(phoneRequest);
		if(result.size() >0 && result != null){
			log.info("提交的数据有问题"+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据有问题");
		}
		return versionControl(phoneRequest.getApiversion(),phoneRequest);
	}
	
	/**
	 * 控制版本
	 * */
	@Override
	public Object versionControl(int v, Object object) {
		switch(v){
			case 1 :
				return versionGetXmerMark(object);
			default :
				return new BaseResponse(ResponseCode.ERRORAPIV,"版本号不正确，请重新下载客户端");
		}
	}
	
	public Object versionGetXmerMark(Object obj){
		PhoneRequest phoneRequest = (PhoneRequest)obj;
		return privateMessageService.getBlackMark(phoneRequest);
		
	}

}
