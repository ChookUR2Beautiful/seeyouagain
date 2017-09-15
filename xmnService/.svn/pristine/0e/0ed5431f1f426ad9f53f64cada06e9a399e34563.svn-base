package com.xmniao.xmn.core.xmer.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xmniao.xmn.core.api.controller.weixin.AuthzController;
import com.xmniao.xmn.core.util.CookieUtils;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.PayConfirmRequest;
import com.xmniao.xmn.core.xmer.service.PayConfirmService;

/***
 * 
* 项目名称：xmnService   
* 类名称：PayConfirmController   
* 类描述：订单支付页面(H5)
* 创建人：liuzhihao   
* 创建时间：2016年6月6日 下午3:25:15   
* @version    
*
 */
@Controller
public class PayConfirmController implements BaseVControlInf{
	//日志报错s
	private final Logger log = Logger.getLogger(PayConfirmController.class);
	
	//注入验证
	@Autowired
	private Validator validator;
	
	//注入service
	@Autowired
	private PayConfirmService payConfirmService;
	
	//注入缓存
	@Autowired
	private SessionTokenService sessionTokenService;
	
	@RequestMapping(value="payConfirm",method=RequestMethod.GET)
	public String payConfirm(Model model,PayConfirmRequest payConfirmRequest, HttpServletRequest request){
		log.info("payConfirm,type=" + request.getParameter("type"));
		List<ConstraintViolation> result = validator.validate(payConfirmRequest);
		if(!result.isEmpty() && result != null){
			log.info("提交的数据有问题"+result.get(0).getMessage());
			model.addAttribute("data", new BaseResponse(ResponseCode.DATAERR,"提交的数据有问题"));
			return "xmer/error";
		}
		String uid = sessionTokenService.getStringForValue(payConfirmRequest.getSessiontoken()) + "";
		if(StringUtils.isBlank(uid) || "null".equals(uid)){
			model.addAttribute("data", new BaseResponse(ResponseCode.TOKENERR,"token已过期，请重新登录"));
			return "xmer/error";
		}
		
		Map<Object, Object> resultMap = new HashMap<>();
		resultMap.put("ordersn", payConfirmRequest.getOrdersn());
		resultMap.put("apiversion", payConfirmRequest.getApiversion());
		resultMap.put("appversion", payConfirmRequest.getAppversion());
		resultMap.put("sessiontoken", payConfirmRequest.getSessiontoken());
		resultMap.put("systemversion", payConfirmRequest.getSystemversion());
		model.addAttribute("data", resultMap);
		return "xmer/agreement";
	}
	
	@RequestMapping(value="/scanAgreement",method=RequestMethod.GET)
	public String agreement(Model model, PayConfirmRequest payConfirmRequest, HttpServletRequest request, HttpServletResponse httpServletResponse){
		log.info("scanAgreement,type=" + request.getParameter("type"));
		List<ConstraintViolation> result = validator.validate(payConfirmRequest);
		if(!result.isEmpty() && result != null){
			log.info("提交的数据有问题"+result.get(0).getMessage());
			model.addAttribute("data", new BaseResponse(ResponseCode.DATAERR,"提交的数据有问题"));
			return "xmer/error";
		}
		String uid = sessionTokenService.getStringForValue(payConfirmRequest.getSessiontoken()) + "";
		if(StringUtils.isBlank(uid) || "null".equals(uid)){
			model.addAttribute("data", new BaseResponse(ResponseCode.TOKENERR,"token已过期，请重新登录"));
			return "xmer/error";
		}
		MapResponse response = null;
		try {
			response = (MapResponse)versionControl(payConfirmRequest.getApiversion(), payConfirmRequest);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("data", versionControl(payConfirmRequest.getApiversion(), payConfirmRequest));
			return "xmer/error";
		}
		model.addAttribute("data",response);
		CookieUtils.delVal(AuthzController.WEIXIN_OPENID_KEY, httpServletResponse);
		return "pay/payconfirm";
	}

	@Override
	public Object versionControl(int v, Object object) {
		switch(v){
		case 1: return versionControlOne(object);
		case 2: return versionControlOne(object);
		default :return new BaseResponse(ResponseCode.ERRORAPIV,"版本号不正确,请重新下载客户端");
		}
	}

	public Object versionControlOne(Object object){
		PayConfirmRequest payConfirmRequest = (PayConfirmRequest) object;
		return payConfirmService.payConfirm(payConfirmRequest);
	}
	
}
