package com.xmniao.xmn.core.api.controller.wealth;

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
import com.xmniao.xmn.core.wealth.service.BalanceService;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：BalanceInfoApi   
* 类描述：我的余额接口
* 创建人：yezhiyong   
* 创建时间：2016年5月23日 上午9:10:30   
* @version    
*
 */
@Controller
public class BalanceInfoApi implements BaseVControlInf{

	/**
	 * 报错日志类
	 */
	private final Logger log = Logger.getLogger(BalanceInfoApi.class);
	
	/**
	 * 注入service
	 */
	@Autowired
	private BalanceService balanceService;
	
	/**
	 * 注入验证validator
	 */
	@Autowired
	private Validator validator;
	
	@RequestMapping(value="/balanceInfo",method=RequestMethod.POST,produces={"application/json;charset=utf-8"})
	@ResponseBody
	private Object queryBalanceInfo(BaseRequest baseRequest){
		log.info("baseRequest data:"+baseRequest.toString());
		//验证请求数据
		List<ConstraintViolation> result = validator.validate(baseRequest);
		if(result != null && result.size() > 0){
			log.info("提交的数据有问题"+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据有问题");
		}
		
		return versionControl(baseRequest.getApiversion(), baseRequest);
		
	}
	
	public Object versionBalanceInfo(Object object) {
		BaseRequest baseRequest = (BaseRequest) object;
		return balanceService.queryBalanceInfo(baseRequest);
	}
	
	@Override
	public Object versionControl(int v, Object object) {
		switch(v){
		case 1:
			return versionBalanceInfo(object);
			default :
				return new BaseResponse(ResponseCode.ERRORAPIV,"版本号不正确,请重新下载客户端");
		}
	}

}
