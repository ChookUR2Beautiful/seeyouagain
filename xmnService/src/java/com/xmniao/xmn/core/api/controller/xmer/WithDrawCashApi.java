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

import com.xmniao.xmn.core.base.BaseRequest;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.xmer.service.WithDrawCashService;

/**
 * 
* 项目名称：xmnService   
* 类名称：WithDrawCashApi   
* 类描述：提现接口  
* 创建人：liuzhihao   
* 创建时间：2016年5月23日 上午9:21:28   
* @version    
*
 */
@Controller
public class WithDrawCashApi implements BaseVControlInf{

	//日志
	private Logger log = Logger.getLogger(XmerHomeApi.class);
	
	//注入验证
	@Autowired
	private Validator validator;
	
	//注入提现Service
	@Autowired
	private WithDrawCashService withDrawCashService;
	
	@RequestMapping(value="withdrawCash",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public Object withdrawCash(BaseRequest baseRequest){
		//验证参数
		List<ConstraintViolation> param = validator.validate(baseRequest);
		if(param.size() >0 && param != null){
			log.info("提交的数据不能为空"+param.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据不能为空，请检查提交的数据");
		}
		return versionControl(baseRequest.getApiversion(),baseRequest);
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
		return withDrawCashService.withDrawCash(baseRequest);
	}
}
