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
import com.xmniao.xmn.core.common.request.BalanceDetailInfoRequest;
import com.xmniao.xmn.core.xmer.service.BalanceDetailInfoService;

/**
 * 
 * 项目名称：xmnService
 * 
 * 类名称：BalanceDetailInfoApi
 *
 * 类描述：余额明细接口
 *
 * 创建人： huang'tao
 * 
 * 创建时间：2016-5-23下午5:42:37
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@Controller
public class BalanceDetailInfoApi implements BaseVControlInf {
	
	/**
	 * 日志报错
	 */
	private Logger log = Logger.getLogger(BalanceDetailInfoApi.class);
	
	/**
	 * 注入service
	 */
	@Autowired
	private BalanceDetailInfoService balanceDetailInfoService;
	
	/**
	 * 注入验证
	 */
	@Autowired
	private Validator validator;
	

	@RequestMapping(value="/balanceDetailInfo",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public Object balanceDetailInfo(BalanceDetailInfoRequest balanceInfoRequest){
		//验证传入的数据合法性
		List<ConstraintViolation> result = validator.validate(balanceInfoRequest);
		if(result != null && result.size()>0){
			log.info("提交的数据有问题"+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据有问题！");
		}
		
		return versionControl(balanceInfoRequest.getApiversion(),balanceInfoRequest);
	}
	
	/**
	 * 控制版本
	 */
	@Override
	public Object versionControl(int v, Object object) {
		switch(v){
		case 1:
			return versionBalanceInfo(object);
		default :
			return new BaseResponse(ResponseCode.ERRORAPIV,"版本号不正确,请重新下载客户端");
		}
	}
	
	public Object versionBalanceInfo(Object obj){
		BalanceDetailInfoRequest balanceInfoRequest = (BalanceDetailInfoRequest) obj;
		if(null!=balanceInfoRequest){
			try {
				return balanceDetailInfoService.getBalancesInfo(balanceInfoRequest);
			} catch (Exception e) {
				e.printStackTrace();
				return new BaseResponse(ResponseCode.DATAERR,"数据转换格式不正确");
			}
		}
		return null;
	}

}
