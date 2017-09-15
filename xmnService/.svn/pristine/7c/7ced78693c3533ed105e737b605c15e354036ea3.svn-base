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
import com.xmniao.xmn.core.common.request.PhoneRequest;
import com.xmniao.xmn.core.xmer.service.XmerInfoService;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：AddCKApi   
* 类描述：   创客添加接口
* 创建人：xiaoxiong   
* 创建时间：2016年5月24日 上午9:26:34   
* @version    
*
 */
@Controller
public class AddCKApi implements BaseVControlInf{
	
	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(SellerInfoApi.class);
		
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
	@RequestMapping(value="addCK",method=RequestMethod.POST,produces={"application/json;charset=utf-8"})
	public Object addCK(PhoneRequest phoneRequest){
		//验证
		List<ConstraintViolation> result = validator.validate(phoneRequest);
		if(result != null && result.size()>0){
			log.info("提交的数据有问题"+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据不正确!");
		}
		return versionControl(phoneRequest.getApiversion(),phoneRequest);
			
	}

	@Override
	public Object versionControl(int v, Object object) {
		switch(v){
		case 1:
			return addCK(object);
			default :
			return new BaseResponse(ResponseCode.ERRORAPIV,"版本号不正确,请重新下载客户端");
		}
	}
	//添加创客
	private Object addCK(Object object) {
		 PhoneRequest phoneRequest=(PhoneRequest)object;
		return xmerInfoService.addCk(phoneRequest);
	}
	
	/**
	 * 
	* @Title: toAddCreateMan
	* @Description: 跳转添加创客页面
	* @return String    返回类型
	* @throws
	 */
	@RequestMapping(value = "/toAddCreateMan", method = RequestMethod.GET)
    public String toAddCreateMan() {  
		return "share/add_createman";
    } 

}
