package com.xmniao.xmn.core.api.controller.xmer;

import java.util.List;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.ModifySellerInfoRequest;
import com.xmniao.xmn.core.xmer.service.ModifySellerInfoService;

/**
 * 
* 项目名称：xmnService   
* 类名称：ModifySellerInfoApi   
* 类描述： 店铺主页修改接口  
* 创建人：liuzhihao   
* 创建时间：2016年5月24日 下午5:48:32   
* @version    
*
 */
@Controller
public class ModifySellerInfoApi implements BaseVControlInf{
	
	//日志
	private final Logger log = Logger.getLogger(ModifySellerInfoApi.class);
	
	//注入service
	@Autowired
	private ModifySellerInfoService modifySellerInfoService;
	
	//注入缓存
	@Autowired
	private SessionTokenService sessionTokenService;
	
	//注入验证
	@Autowired
	private Validator validator;
	
	@RequestMapping(value="modifySellerInfo",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public Object modifySellerInfo(ModifySellerInfoRequest modifySellerInfoRequest){
		//验证参数
		List<ConstraintViolation> result = validator.validate(modifySellerInfoRequest);
		if(result.size() > 0 && result != null){
			log.info("提交的数据有问题"+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,result.get(0).getMessage());
		}
		//验证token
		String token = modifySellerInfoRequest.getSessiontoken();
		if(StringUtils.isEmpty(token)){
			return new BaseResponse(ResponseCode.TOKENERR,"无效token，请重新登录");
		}
		String uid = sessionTokenService.getStringForValue(token).toString();;
		if(uid.equals("") && uid.equals("null")){
			return new BaseResponse(ResponseCode.TOKENERR,"token已过期，请重新登录");
		}
		if(!modifySellerInfoRequest.getPhoneid().matches("^(1[\\d]{10})")){
			return new BaseResponse(ResponseCode.FAILURE,"手机号码不合法");
		}
		if(!modifySellerInfoRequest.getConsume().matches("[0-9]*(\\.?)[0-9]*")){
			return new BaseResponse(ResponseCode.FAILURE,"月消费金额不合法");
		}
		return versionControl(modifySellerInfoRequest.getApiversion(), modifySellerInfoRequest);
	}

	@Override
	public Object versionControl(int v, Object object) {
		switch(v){
//			case 1: return versionControlOne(object);
			case 1:return new BaseResponse(ResponseCode.ERRORAPIV,"版本号不正确,请更新客户端");
			case 2: return versionControlTwo(object);
			default : return new BaseResponse(ResponseCode.ERRORAPIV,"版本号不正确,请重新下载客户端");
		}
	}

//	public Object versionControlOne(Object object){
//		ModifySellerInfoRequest modifySellerInfoRequest = (ModifySellerInfoRequest) object;
//		return modifySellerInfoService.ModifySellerInfo(modifySellerInfoRequest);
//	}
	
	/**
	 * 
	* @Title: versionControlOne
	* @Description: 版本二:店铺资料修改
	* @return Object    返回类型
	* @author
	* @throws
	 */
	public Object versionControlTwo(Object object){
		ModifySellerInfoRequest modifySellerInfoRequest = (ModifySellerInfoRequest) object;
		return modifySellerInfoService.ModifySellerInfoTwo(modifySellerInfoRequest);
	}
}
