package com.xmniao.xmn.core.api.controller.xmer;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.FlowInfoListPayRequest;
import com.xmniao.xmn.core.common.request.FlowInfoListRequest;
import com.xmniao.xmn.core.xmer.service.FlowInfoPayService;
import com.xmniao.xmn.core.xmer.service.FlowInfoService;
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
 * 店铺流水分成明细接口
 */
@Controller
public class FlowInfoPayListApi implements BaseVControlInf{

	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(FlowInfoPayListApi.class);
		
	/**
	 * 注入service
	 */
	@Autowired
	private FlowInfoPayService flowInfoPayService;
	
	/**
	 * 注入验证
	 */
	@Autowired
	private Validator validator;
	

	@RequestMapping(value="/flowInfoPay",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public Object saasPackageList(FlowInfoListPayRequest flowInfoListPayRequest){
		log.info("flowInfoList参数:"+flowInfoListPayRequest.toString());
		List<ConstraintViolation> result = validator.validate(flowInfoListPayRequest);
		if(result != null && result.size()>0){
			log.info("提交的数据有问题"+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据不正确!");
		}
		return versionControl(flowInfoListPayRequest.getApiversion(), flowInfoListPayRequest);
	}
	
	public Object versionOne(Object obj){
		FlowInfoListPayRequest flowInfoListPayRequest = (FlowInfoListPayRequest) obj;
 		return flowInfoPayService.queryFlowInfo(flowInfoListPayRequest);
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
