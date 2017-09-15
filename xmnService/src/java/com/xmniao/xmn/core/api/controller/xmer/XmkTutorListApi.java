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
import com.xmniao.xmn.core.common.request.XmkTutorRequest;
import com.xmniao.xmn.core.xmer.service.FriendsInfoService;

/**
 * 
* 项目名称：xmnService   
* 类名称：XmkTutorListApi   
* 类描述：寻蜜客导师列表接口   
* 创建人：liuzhihao   
* 创建时间：2016年7月15日 上午11:03:18   
* @version    
*
 */
@Controller
public class XmkTutorListApi implements BaseVControlInf{
	
	//日志
	private final Logger log = Logger.getLogger(XmkTutorListApi.class);
	
	//注入service
	@Autowired
	private FriendsInfoService friendsInfoService;

	//注入验证
	@Autowired
	private Validator validator;
	
	//查询寻蜜客导师列表
	@RequestMapping(value="xmkTutorList",method=RequestMethod.POST,produces={"application/json;charset=utf-8"})
	@ResponseBody
	public Object queryXmkTutorList(XmkTutorRequest xmkTutorRequest){
		//验证参数
		List<ConstraintViolation>result = validator.validate(xmkTutorRequest);
		if(result.size() > 0 && result != null){
			log.info("提交的数据有问题"+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据有问题");
		}
		
		return versionControl(xmkTutorRequest.getApiversion(), xmkTutorRequest);
	}

	@Override
	public Object versionControl(int v, Object object) {
		switch(v){
		case 1: return versionControlOne(object);
		case 2: return versionControlOne(object);
		default : return new BaseResponse(ResponseCode.ERRORAPIV,"版本号不正确,请重新下载客户端");
		}
	}

	private Object versionControlOne(Object object) {
		XmkTutorRequest xmkTutorRequest = (XmkTutorRequest) object;
		return friendsInfoService.queryXmkTutorList(xmkTutorRequest);
	}

}
