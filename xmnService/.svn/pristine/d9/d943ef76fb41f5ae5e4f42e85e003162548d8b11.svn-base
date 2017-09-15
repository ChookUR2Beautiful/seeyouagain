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
import com.xmniao.xmn.core.xmer.service.XmerLevelService;

/**
 * 
 * 项目名称： xmnService
 * 类名称： XmerLevelListApi
 * 类描述：成就详情列表接口
 * 创建人： lifeng
 * 创建时间： 2016年5月20日上午9:34:51
 * @version
 */
@Controller
public class XmerLevelListApi implements BaseVControlInf{
	
	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(SaasPackageListApi.class);
		
	/**
	 * 注入service
	 */
	@Autowired
	private XmerLevelService xmerLevelService;
	
	/**
	 * 注入验证
	 */
	@Autowired
	private Validator validator;
	
	@RequestMapping(value="/xmerLevel",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public Object xmerLevelList(BaseRequest baseRequest){
		log.info("xmerLevelList参数:"+baseRequest.toString());
		List<ConstraintViolation> result = validator.validate(baseRequest);
		if(result != null && result.size()>0){
			log.info("提交的数据有问题"+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据不正确!");
		}
		return versionControl(baseRequest.getApiversion(),baseRequest);
	}

	public Object versionOne(Object obj){
		BaseRequest baseRequest = (BaseRequest) obj;
		return xmerLevelService.queryXmerLevel(baseRequest);
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
