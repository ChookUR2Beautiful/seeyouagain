package com.xmniao.xmn.core.api.controller.xmer;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseRequest;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.PayRequest;
import com.xmniao.xmn.core.common.request.RemarkRequest;
import com.xmniao.xmn.core.xmer.service.MaterialService;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;


/**
 * 
*    
* 项目名称：xmnService   
* 类名称：MaterialpayApi   
* 类描述：物料支付   
* 创建人：xiaoxiong   
* 创建时间：2016年7月13日 下午3:14:01   
* @version    
*
 */
@Controller
public class MaterialModifyRemarkApi implements BaseVControlInf{
	
	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(MaterialModifyRemarkApi.class);
		
	/**
	 * 注入service
	 */
	@Autowired
	private MaterialService materialService;
	
	/**
	 * 注入验证
	 */
	@Autowired
	private Validator validator;
	
	
	@ResponseBody
	@RequestMapping(value="modifyRemark",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	public Object materiaModifyRemark(RemarkRequest remarkRequest){
		//验证
		List<ConstraintViolation> result = validator.validate(remarkRequest);
		if(result != null && result.size()>0){
			log.info("提交的数据有问题"+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,result.get(0).getMessage());
		}
		return versionControl(remarkRequest.getApiversion(),remarkRequest);
	}

	@Override
	public Object versionControl(int v, Object object) {
		switch(v){
		case 1:
			return version(object);
			default :
			return new BaseResponse(ResponseCode.ERRORAPIV,"版本号不正确,请重新下载客户端");
		}
	}


	private Object version(Object object) {
		RemarkRequest request=(RemarkRequest) object;
		return materialService.modifyMaterialOrderRemark(request);
	}


}
