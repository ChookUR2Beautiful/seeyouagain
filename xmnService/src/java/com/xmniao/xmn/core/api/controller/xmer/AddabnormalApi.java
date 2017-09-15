package com.xmniao.xmn.core.api.controller.xmer;

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
import com.xmniao.xmn.core.common.request.AddabnormalRequest;
import com.xmniao.xmn.core.xmer.service.XmerInfoService;



/**
 * 添加报错或投诉信息
*    
* 项目名称：xmnService   
* 类名称：AddabnormalApi   
* 类描述：   
* 创建人：xiaoxiong   
* 创建时间：2016年8月4日 下午3:05:33   
* @version    
*
 */
@Controller
public class AddabnormalApi implements BaseVControlInf{

	
	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(AddabnormalApi.class);
		
	/**
	 * 注入service
	 */
	@Autowired
	private XmerInfoService xmerInfoService;
	
	/**
	 * 注入验证
	 */
	@Autowired
	private Validator validator;
	
	@ResponseBody
	@RequestMapping(value="addabnormal",method=RequestMethod.POST,produces={"application/json;charset=utf-8"})
	public Object addabnormal(AddabnormalRequest request){
		//验证
		List<ConstraintViolation> result = validator.validate(request);
		if(result != null && result.size()>0){
			log.info("提交的数据有问题"+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据不正确!");
		}
		return versionControl(request.getApiversion(),request);
			
	}
	
	
	@Override
	public Object versionControl(int v, Object object) {
		switch(v){
		case 1:
			return versionOne(object);
			default :
			return new BaseResponse(ResponseCode.ERRORAPIV,"版本号不正确,请重新下载客户端");
		}
	}

	private Object versionOne(Object object) {
		AddabnormalRequest request=(AddabnormalRequest)object;
		return xmerInfoService.addabnormal(request);
	}

}
