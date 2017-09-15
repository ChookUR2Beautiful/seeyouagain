package com.xmniao.xmn.core.api.controller.common;

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
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.AppLoadUrlRequest;
import com.xmniao.xmn.core.xmer.service.AppLoadUrlService;

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
public class AppLoadUrlApi{
	
	/**
	 * 报错日志
	 */
	private final Logger log = Logger.getLogger(AppLoadUrlApi.class);
	
	/**
	 * 注入appLoadUrlService
	 */
	@Autowired
	private AppLoadUrlService appLoadUrlService;
	
	/**
	 * 注入validator验证
	 */
	@Autowired
	private Validator validator;
	/**
	 * 注入redis 缓存
	 */
	@Autowired
	private SessionTokenService sessionTokenService;
	
	@RequestMapping(value="appLoad/queryLoadUrl",method=RequestMethod.POST,produces={"application/json;charset=utf-8"})
	@ResponseBody
	public Object queryLoadUrl(AppLoadUrlRequest appLoadUrlRequest){
		//验证请求数据的合法性
		List<ConstraintViolation> result = validator.validate(appLoadUrlRequest);
		if(result.size() > 0){
			log.info("数据提交有问题"+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据有问题");
		}
		//获取app客户端下载地址
		return appLoadUrlService.queryLoadUrl(appLoadUrlRequest);
	}

}
