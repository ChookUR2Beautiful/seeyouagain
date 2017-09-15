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
import com.xmniao.xmn.core.common.request.PageRequest;
import com.xmniao.xmn.core.xmer.service.MaterialService;


@Controller
public class MaterialListApi implements BaseVControlInf{
	
	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(MaterialListApi.class);
	
	@Autowired
	private MaterialService materialService;
	
	//注入验证
	@Autowired
	private Validator validator;
	@ResponseBody
	@RequestMapping(value="materiallist",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	public Object materialList(PageRequest pageRequest){
		//验证参数
		List<ConstraintViolation> param = validator.validate(pageRequest);
		if(param.size() >0 && param != null){
			log.info("提交数据有误"+param.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交数据有误！");
		}
		return versionControl(pageRequest.getApiversion(),pageRequest);
	}

	@Override
	public Object versionControl(int v, Object object) {
		switch(v){
		case 1: return versionControlOne(object);
		default : return new BaseResponse(ResponseCode.ERRORAPIV,"版本号不正确,请重新下载客户端");
		}
	}

	private Object versionControlOne(Object object) {
		PageRequest pageRequest=(PageRequest) object;
		return materialService.materialList(pageRequest);
	}
}
