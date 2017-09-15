/**
 * 2016年8月25日 下午2:17:27
 */
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
import com.xmniao.xmn.core.common.request.live.RelieveblackRequst;
import com.xmniao.xmn.core.live.service.PersonalCenterService;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：RelieveblackApi   
* 类描述：   拉入黑名单/解除黑名
* 创建人：yezhiyong   
* 创建时间：2016年11月3日 下午2:14:50   
* @version    
*
 */
@Controller
public class RelieveblackApi implements BaseVControlInf {

	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(RelieveblackApi.class);

	/**
	 * 注入personalcenterService
	 */
	@Autowired
	private PersonalCenterService personalcenterService;
	
	/**
	 * 注入验证
	 */
	@Autowired
	private Validator validator;
	
	@RequestMapping(value="/live/personal/relieveBlack",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public Object queryRelieveBlack(RelieveblackRequst relieveblackrequst){
		//验证
		List<ConstraintViolation> result = validator.validate(relieveblackrequst);
		if(result.size() >0 && result != null){
			log.info("提交的数据有问题"+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据有问题");
		}
		return versionControl(relieveblackrequst.getApiversion(), relieveblackrequst);
	}
	
	@Override
	public Object versionControl(int v, Object object) {
		switch(v){
		case 1:
			return versionControlOne(object);
			default :
				return new BaseResponse(ResponseCode.ERRORAPIV,"版本号不正确,请重新下载客户端");
		}
	}
	private Object versionControlOne(Object object) {
		RelieveblackRequst  relieveblackrequst=(RelieveblackRequst) object;
//		return personalcenterService.addAndReviceblack_List(relieveblackrequst);//yeyu
		return personalcenterService.addAndReviceblack(relieveblackrequst);
	}

}
