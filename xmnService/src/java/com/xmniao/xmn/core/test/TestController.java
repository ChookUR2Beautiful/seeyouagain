package com.xmniao.xmn.core.test;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.api.controller.catehome.SwitchPositionApi;
import com.xmniao.xmn.core.base.BaseRequest;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.common.ResponseCode;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

/**
 * 
* @projectName: xmnService 
* @ClassName: TestController    
* @Description:   
* @author: liuzhihao   
* @date: 2016年12月3日 下午4:55:27
 */

@Controller
public class TestController implements BaseVControlInf{

	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(SwitchPositionApi.class);
	
	/**
	 * 注入验证
	 */
	@Autowired
	private Validator validator;
	
	
	@Autowired
	private TestSerivice testService;
	
	@RequestMapping(value="/test/list",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public Object inserTest(BaseRequest baseRequest){
		
		log.info("baseRequest data:"+baseRequest.toString());
		List<ConstraintViolation> result = validator.validate(baseRequest);
		if(result != null && result.size()>0){
			log.info("提交的数据有问题"+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据不正确!");
		}
		return versionControl(baseRequest.getApiversion(), baseRequest);
	}

	public Object versionOne(Object object){
		BaseRequest baseRequest = (BaseRequest) object;
		return testService.findAll(baseRequest);
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

	
	public static void main(String[] args) {
		BaseRequest request = new BaseRequest();
		request.setApiversion(1);
		request.setAppversion("1.1.1");
		request.setSessiontoken("");
		request.setSystemversion("1.2.3");
		TestSerivice ts = new TestSerivice();
		ts.insert(request);
		System.out.println("插入成功");
	}
}
