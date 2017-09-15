package com.xmniao.xmn.core.xmer.controller;

import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xmniao.xmn.core.api.controller.weixin.AuthzController;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.PayResultRequest;
import com.xmniao.xmn.core.util.CookieUtils;
import com.xmniao.xmn.core.xmer.service.PayTypeService;

/**
 * 
 * 
 * 项目名称：xmnService 类名称：PayTypeController 类描述： 跳转支付页面 创建人：yezhiyong
 * 创建时间：2016年6月6日 下午5:33:38
 * 
 * @version
 *
 */
@Controller
public class PayTypeController implements BaseVControlInf {
	// 日志
	private static final Logger logger = LoggerFactory.getLogger(PayTypeController.class);

	// 验证
	@Autowired
	private Validator validator;

	// 注入支付跳转service
	@Autowired
	private PayTypeService payTypeService;

	@RequestMapping(value = "/pay/payType")
	public String payType(Model model, PayResultRequest payResultRequest, HttpServletRequest request) throws Exception {
		// 微信openid获取
		String openid = CookieUtils.getVal(AuthzController.WEIXIN_OPENID_KEY, request);
		logger.info("Get weixin openid from Cookie,Value : {}",openid);
		logger.info("openid:"+openid);
		logger.info("type:"+request.getParameter("type"));
		if (openid == null) {
			String callback = ("/pay/payType?orderid=" + request.getParameter("orderid") + "&type="
					+ request.getParameter("type") + "&appversion=" + request.getParameter("appversion")
					+ "&systemversion=" + request.getParameter("systemversion") + "&sessiontoken="
					+ request.getParameter("sessiontoken") );
			
			callback = URLEncoder.encode(callback,"utf-8");
			
			String redirect = "/weixin/authz/authorize?callback=" + callback;
			
			return "redirect:" + redirect;
		}

		// 验证
		List<ConstraintViolation> result = validator.validate(payResultRequest);
		logger.info("payResultRequest data" + payResultRequest.toString());
		if (result.size() > 0 && result != null) {
			logger.info("提交的数据有问题" + result.get(0).getMessage());
			model.addAttribute("data", new BaseResponse(ResponseCode.DATAERR, "提交的数据有问题"));
			return "xmer/error";
		}
		model.addAttribute("data", versionControl(payResultRequest.getApiversion(), payResultRequest));
		return "pay/paytype";
	}

	@Override
	public Object versionControl(int v, Object object) {
		switch (v) {
		case 1:
			return versionControlOne(object);
		case 2:
			return versionControlOne(object);
		default:
			return new BaseResponse(ResponseCode.ERRORAPIV, "版本号不正确,请重新下载客户端");
		}
	}

	public Object versionControlOne(Object object) {
		PayResultRequest payResultRequest = (PayResultRequest) object;
		return payTypeService.payType(payResultRequest);
	}
	
	/**
	 * 
	* @Title: toPaySuccess
	* @Description: 跳转支付成功页面
	* @return String    返回类型
	* @author
	* @throws
	 */
	@RequestMapping(value="/toPaySuccess",method = RequestMethod.GET)
	public String toPaySuccess() {
		return "pay/paysuccess";
	}
	
	/**
	 * 
	 * @Title: toPayFail
	 * @Description: 跳转支付失败页面
	 * @return String    返回类型
	 * @author
	 * @throws
	 */
	@RequestMapping(value="/toPayFail",method = RequestMethod.GET)
	public String toPayFail() {
		return "pay/payfail";
	}
}
