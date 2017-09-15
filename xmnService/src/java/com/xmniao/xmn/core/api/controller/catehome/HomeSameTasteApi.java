package com.xmniao.xmn.core.api.controller.catehome;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.catehome.service.XmnHomeService;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.XmnHomeRequest;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

/**
 * 
* @projectName: xmnService 
* @ClassName: HomeSameTasteApi    
* @Description:首页口味相似的接口   
* @author: liuzhihao   
* @date: 2016年12月9日 下午3:39:40
 */
@RequestMapping("/home/taste")
@Controller
public class HomeSameTasteApi implements BaseVControlInf{

	/**
	 * 注入寻蜜鸟首页service
	 */
	@Autowired
	private XmnHomeService xmnHomeService;
	
	@Autowired
	private Validator validator;
	
	/**
	 * 查询寻蜜鸟首页-banner
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public Object getSameTasteList(XmnHomeRequest xmnHomeRequest){
		//验证请求数据
		List<ConstraintViolation> requestParam = validator.validate(xmnHomeRequest);
		if(requestParam != null && !requestParam.isEmpty()){
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据不正确!");
		}
		return versionControl(xmnHomeRequest.getApiversion(),xmnHomeRequest);

	}

	public Object versionOne(Object object){
		XmnHomeRequest xmnHomeRequest = (XmnHomeRequest) object;
		return xmnHomeService.getSameTasteList(xmnHomeRequest);
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
}
