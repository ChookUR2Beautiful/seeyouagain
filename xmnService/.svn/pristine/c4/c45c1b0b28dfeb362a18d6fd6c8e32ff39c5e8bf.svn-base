package com.xmniao.xmn.core.api.controller.xmer;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.StoreInfoUpdateRequest;
import com.xmniao.xmn.core.xmer.service.StoreInfoService;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 更新店铺信息
 */
@Controller
public class StoreInfoUpdateApi implements BaseVControlInf{
	
	//日志
	private Logger log = Logger.getLogger(StoreInfoUpdateApi.class);
	
	//注入service
	@Autowired
	private StoreInfoService storeInfoService;

	//注入验证
	@Autowired
	private Validator validator;
	
	@RequestMapping(value="/store/update",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public Object xmerInfo(StoreInfoUpdateRequest storeInfoUpdateRequest){
		log.warn("编辑店铺：" + storeInfoUpdateRequest.toString());
		//验证
		List<ConstraintViolation> result = validator.validate(storeInfoUpdateRequest);
		if(result.size() >0 && result != null){
			log.info("提交的数据有问题"+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据有问题，请检查");
		}
		if (storeInfoUpdateRequest.getSystemversion() != null && storeInfoUpdateRequest.getSystemversion().contains("h5")) {
		} else {
			if(!storeInfoUpdateRequest.getPhoneid().matches("[\\d]{11}")){
				return new BaseResponse(ResponseCode.FAILURE,"手机号码不合法");
			}
		}
		return versionControl(storeInfoUpdateRequest.getApiversion(), storeInfoUpdateRequest);
	}

	@Override
	public Object versionControl(int v, Object object) {
		switch(v){
		case 1: return versionControlOne(object);
		default : return new BaseResponse(ResponseCode.ERRORAPIV,"版本号不正确,请重新下载客户端");
		}
	}


	private Object versionControlOne(Object object) {
		StoreInfoUpdateRequest storeInfoUpdateRequest = (StoreInfoUpdateRequest) object;
		return storeInfoService.updateStoreInfo(storeInfoUpdateRequest);
	}

}
