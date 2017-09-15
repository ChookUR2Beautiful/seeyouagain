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

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.IncomeInfoRequest;
import com.xmniao.xmn.core.wealth.service.IncomeInfoService;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：IncomeInfoApi   
* 类描述：  软件销售金额收入明细接口
* 创建人：yezhiyong   
* 创建时间：2016年5月27日 上午9:36:47   
* @version    
*
 */
@Controller
public class IncomeInfoApi implements BaseVControlInf {

	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(IncomeInfoApi.class);
		
	/**
	 * 注入service
	 */
	@Autowired
	private IncomeInfoService incomeInfoService;
	
	/**
	 * 注入验证
	 */
	@Autowired
	private Validator validator;
	

	@RequestMapping(value="/incomeInfo",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public Object queryIncomeInfo(IncomeInfoRequest incomeInfoRequest){
		log.info("incomeInfoRequest data:"+incomeInfoRequest.toString());
		List<ConstraintViolation> result = validator.validate(incomeInfoRequest);
		if(result != null && result.size()>0){
			log.info("提交的数据有问题"+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据不正确!");
		}
		return versionControl(incomeInfoRequest.getApiversion(),incomeInfoRequest);
	}
	
	public Object versionOne(Object obj){
		IncomeInfoRequest incomeInfoRequest = (IncomeInfoRequest) obj;
		return incomeInfoService.queryIncomeInfo(incomeInfoRequest);
	}

	@Override
	public Object versionControl(int v, Object object) {
		switch(v){
		case 1:
			return versionOne(object);
		case 2:
			return versionOne(object);
			default :
			return new BaseResponse(ResponseCode.ERRORAPIV,"版本号不正确,请重新下载客户端");
		}
	}

}
