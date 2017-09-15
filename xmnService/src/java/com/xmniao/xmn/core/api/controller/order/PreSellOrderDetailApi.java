package com.xmniao.xmn.core.api.controller.order;

import java.util.List;

import com.xmniao.xmn.core.common.request.order.ExperienceCardRequest;
import com.xmniao.xmn.core.order.service.ExperienceOrderService;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.order.BillOrderQueryRequest;
import com.xmniao.xmn.core.common.request.order.PreSellOrderQueryRequest;
import com.xmniao.xmn.core.common.request.sellerPackage.SellerPackageQueryRequest;
import com.xmniao.xmn.core.order.service.BillOrderInfoService;
import com.xmniao.xmn.core.order.service.CouponFansOrderInfoService;
import com.xmniao.xmn.core.order.service.PreSellAllOrderService;
import com.xmniao.xmn.core.sellerPackage.service.SellerPackageOrderService;

/**
 * 预售订单详情
 */
@Controller
public class PreSellOrderDetailApi{

	private final Logger log = Logger.getLogger(PreSellOrderDetailApi.class);
	
	@Autowired
	private Validator validator;
	
	@Autowired
	private CouponFansOrderInfoService couponFansOrderInfoService;
	
	@Autowired
	private SellerPackageOrderService sellerPackageOrderService;
	
	@Autowired
	private PreSellAllOrderService preSellAllOrderService;
	@Autowired
	private ExperienceOrderService experienceOrderService;

	
	@RequestMapping(value="/preSell/order/detail",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public Object billPayment(PreSellOrderQueryRequest request){
		//日志
		log.info("PreSellOrderQueryRequest Data:"+request.toString());
		//验证
		List<ConstraintViolation> result = validator.validate(request);
		if(result.size() >0 && result != null){
			log.info("提交的数据有问题"+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据有问题");
		}

		if (request.getType() == null ||  request.getType() == 0) {
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据有问题,类型不对");
		}
		if (request.getOrderNo() == null) {
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据有问题,订单编号不能为空");
		}

		//粉丝券订单详情参数
		BillOrderQueryRequest fansOrder = new BillOrderQueryRequest();
		fansOrder.setOrderNo(request.getOrderNo());
		fansOrder.setSessiontoken(request.getSessiontoken());
		//套餐券订单详情 参数
		SellerPackageQueryRequest packageRequest = new SellerPackageQueryRequest();
		packageRequest.setOrderNo(request.getOrderNo());
		packageRequest.setSessiontoken(request.getSessiontoken());
		//
		ExperienceCardRequest experienceCardRequest = new ExperienceCardRequest();
		experienceCardRequest.setOrderNo(request.getOrderNo());
		experienceCardRequest.setOrderSource(0);
		experienceCardRequest.setSessiontoken(request.getSessiontoken());

//			1 套餐 2 粉丝卷 3 体验官
		switch (request.getType()) {
			case 1: return sellerPackageOrderService.querySellerPackageOrderInfo(packageRequest, true);
			case 2: return couponFansOrderInfoService.queryCouponOrderView(fansOrder);
			case 3: return experienceOrderService.queryExperienceOrderDetail(experienceCardRequest, true);
			default: return new MapResponse(ResponseCode.FAILURE, "未查找到订单");
		}
	}
	
	
}
