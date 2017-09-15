package com.xmniao.xmn.core.api.controller.catehome;

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
import com.xmniao.xmn.core.catehome.service.CateHomeService;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.CateHomeRequest;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：CateHomeApi   
* 类描述：   发现美食首页信息Api
* 创建人：yezhiyong   
* 创建时间：2016年6月21日 上午9:04:14   
* @version    
*
 */
@Controller
public class CateHomeApi {
	
	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(CateHomeApi.class);
		
	/**
	 * 注入service
	 */
	@Autowired
	private CateHomeService cateHomeService;
	
	/**
	 * 注入验证
	 */
	@Autowired
	private Validator validator;
	
	@RequestMapping(value="/cateHome",method=RequestMethod.GET,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public Object queryCateHome(CateHomeRequest cateHomeRequest){
		//验证参数
		log.info("cateHomeRequest data:"+cateHomeRequest.toString());
		List<ConstraintViolation> result = validator.validate(cateHomeRequest);
		if(result != null && result.size()>0){
			log.info("提交的数据有问题"+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据不正确!");
		}
		return cateHomeService.queryCateHome(cateHomeRequest);
	}

}
