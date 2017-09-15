package com.xmniao.xmn.core.common.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.Constant;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.PhoneRequest;

@Controller
public class GetPhoneCodeNums {
	/**
	 * redis缓存
	 */
	@Autowired
	private SessionTokenService sessionService;
	/**
	 * 注入validator验证
	 */
	@Autowired
	private Validator validator;
	
	@RequestMapping(value="/getPhoneCodeNums",method=RequestMethod.GET,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public Object validateCodeService(PhoneRequest request){
	try {
			if(request.getPhone()==null&&request.getPhone().length()==0){
				return new BaseResponse(ResponseCode.FAILURE, "手机号码不能为空");
			}
			String numKey=Constant.XMER_CODE_NUM+request.getPhone();
			String codeNums=sessionService.getStringForValue(numKey)+"";
			if(codeNums.equals("")||codeNums.equals("null")){
				codeNums="0";
			}
			Map<Object,Object> map=new HashMap<Object, Object>();
			map.put("nums", codeNums);
			MapResponse mapResponse=new MapResponse(ResponseCode.SUCCESS, "获取成功");
			mapResponse.setResponse(map);
			return mapResponse;
			
	} catch (Exception e) {
		e.printStackTrace();
	}
		return new BaseResponse(ResponseCode.FAILURE, "失败");
		
	}
}
