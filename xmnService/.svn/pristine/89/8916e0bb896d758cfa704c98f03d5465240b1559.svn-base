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

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.FlowInfoListRequest;
import com.xmniao.xmn.core.xmer.service.FlowInfoService;

/**
 * 项目名称： xmnService
 * 类名称： FlowInfoListApi
 * 类描述：流水明细接口
 * 创建人： lifeng
 * 创建时间： 2016年5月24日下午1:00:04
 * @version
 */
@Controller
public class FlowInfoListApi implements BaseVControlInf{

	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(FlowInfoListApi.class);
		
	/**
	 * 注入service
	 */
	@Autowired
	private FlowInfoService flowInfoService;
	
	/**
	 * 注入验证
	 */
	@Autowired
	private Validator validator;
	

	@RequestMapping(value="/flowInfo",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public Object saasPackageList(FlowInfoListRequest flowInfoListRequest){
		log.info("flowInfoList参数:"+flowInfoListRequest.toString());
		List<ConstraintViolation> result = validator.validate(flowInfoListRequest);
		if(result != null && result.size()>0){
			log.info("提交的数据有问题"+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据不正确!");
		}
		return versionControl(flowInfoListRequest.getApiversion(),flowInfoListRequest);
	}
	
	public Object versionOne(Object obj){
		FlowInfoListRequest flowInfoListRequest = (FlowInfoListRequest) obj;
 		return flowInfoService.queryFlowInfo(flowInfoListRequest);
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
	
	@RequestMapping(value="flowInfoTest",produces={"application/json;charset=utf-8"})
	@ResponseBody
	public String flowInfoTest(){
		FlowInfoListRequest request = new FlowInfoListRequest();
		request.setApiversion(1);
		request.setAppversion("1.2.32");
		request.setSystemversion("ios9.3");
		request.setSessiontoken("49477948c49bd3599d1514790a909caa");
		request.setPage(1);
		request.setType("1");
		saasPackageList(request);
		return "是什么支撑你起早贪黑，在大冬天里仍然义无反顾的奔跑上班呢？是爱吗？是梦想吗？来跟我一起念，q～i～ong 。。。再念一遍，q～i～ong 穷。 。。唉！对撩！     看完合上!";
	}

}
