package com.xmniao.xmn.core.api.controller.seller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.seller.UserSellerRequest;
import com.xmniao.xmn.core.seller.service.UserSellerService;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

/**
 * 
* @projectName: xmnService 
* @ClassName: MainSellerListApi    
* @Description:与我相关的商铺接口   
* @author: liuzhihao   
* @date: 2016年12月7日 上午11:51:23
 */
@RequestMapping("/main")
@Controller
public class MainSellerListApi implements BaseVControlInf{

	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(MainSellerListApi.class);
	
	/**
	 * 注入验证
	 */
	@Autowired
	private Validator validator;
	
	//与用户相关
	@Autowired
	private UserSellerService userSellerService;
	
	/**
	 * 定位
	 * @param locationRequest
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public Object mainSellerList(UserSellerRequest userSellerRequest){
		log.info("userSellerRequest data:"+userSellerRequest.toString());
		List<ConstraintViolation> result = validator.validate(userSellerRequest);
		if(result != null && result.size()>0){
			log.info("提交的数据有问题"+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据不正确!");
		}
		return versionControl(userSellerRequest.getApiversion(), userSellerRequest);
	}

	public Object versionOne(Object object){
		UserSellerRequest userSellerRequest = (UserSellerRequest) object;
		return userSellerService.mainSellerList(userSellerRequest);
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
