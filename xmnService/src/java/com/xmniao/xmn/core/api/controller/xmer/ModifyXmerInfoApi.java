
/**
 * 
 */
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
import com.xmniao.xmn.core.common.request.ModifyXmerInfoRequest;
import com.xmniao.xmn.core.xmer.service.XmerInfoService;

/**
 * 
 * 项目名称：xmnService
 * 
 * 类名称：ModifyXmerInfoApi
 * 
 * 类描述：用户个人资料修改接口
 * 
 * 创建人： huang'tao
 * 
 * 创建时间：2016-5-23上午9:52:53
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@Controller
public class ModifyXmerInfoApi implements BaseVControlInf {

	/**
	 * 日志报错
	 */
	private Logger log = Logger.getLogger(ModifyXmerInfoApi.class);

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

	@RequestMapping(value = "/modifyXmerInfo", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public Object modifyXmerInfo(ModifyXmerInfoRequest request) {
		// 验证传入的数据合法性
		List<ConstraintViolation> result = validator.validate(request);
		if (result != null && result.size() > 0) {
			log.info("提交的数据有问题" + result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR, "提交的数据有问题！");
		}
		
		return versionControl(request.getApiversion(), request);
	}

	/**
	 * 控制版本
	 */
	@Override
	public Object versionControl(int v, Object object) {
		switch (v) {
		case 1:
			return versionModifyXmerInfo(object);
		default:
			return new BaseResponse(ResponseCode.ERRORAPIV, "版本号不正确,请重新下载客户端");
		}
	}
	
	public Object versionModifyXmerInfo(Object obj) {
		ModifyXmerInfoRequest modifyXmerInfoRequest = (ModifyXmerInfoRequest) obj;
		if (null != modifyXmerInfoRequest) {
			try {
				return xmerInfoService.modifyXmer(modifyXmerInfoRequest);
			} catch (Exception e) {
				e.printStackTrace();
				return new BaseResponse(ResponseCode.DATAERR, "数据转换格式不正确");
			}
		}
		return null;
	}

}
