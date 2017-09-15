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
import com.xmniao.xmn.core.common.request.HandleCKRequest;
import com.xmniao.xmn.core.xmer.service.XmerInfoService;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：HandleCKApi   
* 类描述：   操作好友申请
* 创建人：xiaoxiong   
* 创建时间：2016年5月26日 下午2:33:27   
* @version    
*
 */
@Controller
public class HandleCKApi implements BaseVControlInf{
	private final Logger log = Logger.getLogger(HandleCKApi.class);
	/**
	 * 注入service
	 */
	@Autowired
	private XmerInfoService xmerInfoService;

	/**
	 * 注入验证
	 */
	@Autowired
	private Validator validator;
	
	@ResponseBody
	@RequestMapping(value="handleCK",method=RequestMethod.POST,produces={"application/json;charset=utf-8"})
	 public Object handleCK(HandleCKRequest handldCkRequest){
		//验证传入的数据合法性
			List<ConstraintViolation> result = validator.validate(handldCkRequest);
			if(result != null && result.size()>0){
				log.info("提交的数据有问题"+result.get(0).getMessage());
				return new BaseResponse(ResponseCode.DATAERR,"提交的数据有问题！");
			}
			return versionControl(handldCkRequest.getApiversion(), handldCkRequest);
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

	private Object versionOne(Object object) {
		HandleCKRequest hand=(HandleCKRequest) object;
		return xmerInfoService.handleCK(hand);
	}

}
