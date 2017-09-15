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
import com.xmniao.xmn.core.common.request.SellerListRequest;
import com.xmniao.xmn.core.xmer.service.SellerService;

/**
 * 
 * 
 * 项目名称：xmnService 类名称：SellerListApi 类描述： 店铺列表接口 创建人：yezhiyong 创建时间：2016年5月23日
 * 下午2:25:25
 * 
 * @version
 *
 */
@Controller
public class SellerListApi implements BaseVControlInf {

	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(SellerListApi.class);

	/**
	 * 注入service
	 */
	@Autowired
	private SellerService sellerService;

	/**
	 * 注入验证
	 */
	@Autowired
	private Validator validator;

	@RequestMapping(value = "/sellerList", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public Object querySellerList(SellerListRequest sellerListRequest) {
		log.info("querySellerList data:" + sellerListRequest.toString());
		List<ConstraintViolation> result = validator.validate(sellerListRequest);
		if (result != null && result.size() > 0) {
			log.info("提交的数据有问题" + result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR, "提交的数据不正确!");
		}
		return versionControl(sellerListRequest.getApiversion(), sellerListRequest);
	}

	public Object versionOne(Object obj) {
		SellerListRequest sellerListRequest = (SellerListRequest) obj;
		return sellerService.querySellerList(sellerListRequest);
	}

	@Override
	public Object versionControl(int v, Object object) {
		switch (v) {
		case 1:
			return versionOne(object);
		case 2:
			return versionTwo(object);
		default:
			return new BaseResponse(ResponseCode.ERRORAPIV, "版本号不正确,请重新下载客户端");
		}
	}
	
	public Object versionTwo(Object object){
		SellerListRequest sellerListRequest = (SellerListRequest) object;
		return sellerService.queryNewSellerList(sellerListRequest);
	}

	@RequestMapping(value = "sellerListTest", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String sellerListTest() {
		SellerListRequest request = new SellerListRequest();
		request.setApiversion(1);
		request.setAppversion("1.2.3");
		request.setSessiontoken("ios9.3.21");
		request.setType(3);
		request.setPage(1);
		request.setSessiontoken("49477948c49bd3599d1514790a909caa");
		querySellerList(request);
		return "ok！";
	}

}
