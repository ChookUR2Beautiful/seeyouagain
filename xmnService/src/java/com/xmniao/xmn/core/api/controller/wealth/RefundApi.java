package com.xmniao.xmn.core.api.controller.wealth;

import java.util.List;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.api.controller.recruit.EditUserCVApi;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.EditUserCVRequest;
import com.xmniao.xmn.core.common.request.RefundRequest;
import com.xmniao.xmn.core.recruit.service.UserService;
import com.xmniao.xmn.core.wealth.service.RefundService;

/**
 * 
 * 
 * 项目名称：xmnService 类名称：RefundApi 类描述： 退款 创建人：xiaoxiong 创建时间：2016年6月20日 下午6:19:15
 * 
 * @version 1.0
 * 
 */
@Controller
public class RefundApi implements BaseVControlInf {

	// 报错日志
	private final Logger log = Logger.getLogger(RefundApi.class);

	// 注入用户Service
	@Autowired
	private RefundService refundService;

	// 注入验证
	@Autowired
	private Validator validator;

	// 注入缓存
	@Autowired
	private SessionTokenService sessionTokenService;

	@RequestMapping(value="refund",method=RequestMethod.POST,produces={"application/json;charset=utf-8"})
	@ResponseBody
	public Object refund(RefundRequest refundRequest) {

		// 验证参数
		List<ConstraintViolation> param = validator.validate(refundRequest);
		if (param.size() > 0 && param != null) {
			log.info("提交的数据不能为空" + param.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR, "提交的数据不能为空，请检查提交的数据");
		}

		return versionControl(refundRequest.getApiversion(), refundRequest);
	}

	@Override
	public Object versionControl(int v, Object object) {
		switch (v) {
		case 1:
			return versionControlOne(object);
		default:
			return new BaseResponse(ResponseCode.ERRORAPIV, "版本号不正确,请重新下载客户端");
		}
	}

	private Object versionControlOne(Object object) {
		RefundRequest request = (RefundRequest) object;
		return refundService.refund(request);
	}

}
