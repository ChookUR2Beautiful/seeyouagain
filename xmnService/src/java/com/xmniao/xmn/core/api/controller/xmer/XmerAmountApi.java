package com.xmniao.xmn.core.api.controller.xmer;

import com.xmniao.xmn.core.base.BaseRequest;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.FlowInfoListRequest;
import com.xmniao.xmn.core.xmer.service.FlowInfoService;
import com.xmniao.xmn.core.xmer.service.XmerAmountService;
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
 * 我的收入
 */
@Controller
public class XmerAmountApi implements BaseVControlInf{

	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(XmerAmountApi.class);

	@Autowired
	private XmerAmountService xmerAmountService;
	
	/**
	 * 注入验证
	 */
	@Autowired
	private Validator validator;
	

	@RequestMapping(value="/xmer/amount",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public Object getMyAmount(BaseRequest baseRequest){
		log.info("getMyAmount参数:"+baseRequest.toString());
		List<ConstraintViolation> result = validator.validate(baseRequest);
		if(result != null && result.size()>0){
			log.info("提交的数据有问题"+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据不正确!");
		}
		return versionControl(baseRequest.getApiversion(), baseRequest);
	}
	
	public Object versionOne(Object obj){
		BaseRequest baseRequest = (BaseRequest) obj;
 		return xmerAmountService.getMyAmount(baseRequest);
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
