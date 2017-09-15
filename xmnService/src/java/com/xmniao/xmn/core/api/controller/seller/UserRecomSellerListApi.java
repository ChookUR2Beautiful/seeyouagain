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
import com.xmniao.xmn.core.common.request.TypeRequest;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

/**
 * 
* @projectName: xmnService 
* @ClassName: UserRecomSellerListApi    
* @Description:关于我的商铺列表   
* @author: liuzhihao   
* @date: 2016年12月5日 下午5:58:42
 */
@RequestMapping("/user/recom/seller")
@Controller
public class UserRecomSellerListApi implements BaseVControlInf{
	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(UserRecomSellerListApi.class);
	
	/**
	 * 注入验证
	 */
	@Autowired
	private Validator validator;
	
	
	/**
	 * 定位
	 * @param locationRequest
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public Object userRecomSellerList(TypeRequest typeRequest){
		log.info("typeRequest data:"+typeRequest.toString());
		List<ConstraintViolation> result = validator.validate(typeRequest);
		if(result != null && result.size()>0){
			log.info("提交的数据有问题"+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据不正确!");
		}
		return versionControl(typeRequest.getApiversion(), typeRequest);
	}

	public Object versionOne(Object object){
		return null;
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
