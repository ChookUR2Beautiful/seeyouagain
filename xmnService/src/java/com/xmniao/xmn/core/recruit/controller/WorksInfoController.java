package com.xmniao.xmn.core.recruit.controller;

import java.util.List;
import java.util.Map;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.IDRequest;
import com.xmniao.xmn.core.recruit.service.WorksService;

/**
 * 
* 项目名称：xmnService   
* 类名称：WorksInfoController   
* 类描述：岗位详情接口(H5页面)   
* 创建人：liuzhihao   
* 创建时间：2016年5月21日 下午12:41:43   
* @version    
*
 */
@Controller
public class WorksInfoController implements BaseVControlInf{
	
	//日志
	private Logger log = Logger.getLogger(WorksInfoController.class);
	
	//注入岗位service
	@Autowired
	private WorksService worksService;
	
	//注入验证
	@Autowired
	private Validator validator;
	
	//注入缓存
	@Autowired
	private SessionTokenService sessionTokenService;
	
	private BaseResponse response;
	
	@RequestMapping(value="queryWorksInfo")
	public String queryWorksInfo(Model model,IDRequest idRequest){
		List<ConstraintViolation> param = validator.validate(idRequest);
		//验证参数
		if(param.size() >0 && param != null){
			log.info("提交的数据不能为空"+param.get(0).getMessage());
			response = new BaseResponse(ResponseCode.DATAERR,"提交的数据不能为空");
			model.addAttribute("data", response);
			return "error";
		}
		//验证token
		String token = idRequest.getSessiontoken();
		if(StringUtils.isEmpty(token)){
			response = new BaseResponse(ResponseCode.TOKENERR,"无效token，请重新登录");
			model.addAttribute("data", response);
			return "error";
		}
//		//验证缓存
//		String uid = sessionTokenService.getStringForValue(token).toString();
//		if(uid.equals("") && uid.equals("null")){
//			response = new BaseResponse(ResponseCode.TOKENERR,"亲，你的token已过期，重新登录");
//			model.addAttribute("data", response);
//			return "error";
//		}
		model.addAttribute("data", versionControl(idRequest.getApiversion(), idRequest));
		return "recruit/worksinfo";
	}

	@Override
	public Object versionControl(int v, Object object) {
		switch(v){
		case 1: return VersionControlOne(object);
		case 2: return VersionControlOne(object);
		default : return new BaseResponse(ResponseCode.ERRORAPIV,"版本号不正确,请重新下载客户端");
		}
	}

	Object VersionControlOne(Object object){
		IDRequest request = (IDRequest) object;
		return worksService.queryWorksInfo(request);
	}
}
