package com.xmniao.xmn.core.market.service.pay.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.Constant;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.TResponse;
import com.xmniao.xmn.core.common.request.market.pay.OrderInfoRequest;
import com.xmniao.xmn.core.common.request.market.pay.OrderListRequest;
import com.xmniao.xmn.core.common.service.FileService;
import com.xmniao.xmn.core.live.dao.LiveUserDao;
import com.xmniao.xmn.core.market.dao.MarketBillFreshDao;
import com.xmniao.xmn.core.market.dao.MarketBillFreshSubDao;
import com.xmniao.xmn.core.market.dao.ProductBillDao;
import com.xmniao.xmn.core.market.dao.ProductDetailsDao;
import com.xmniao.xmn.core.market.entity.pay.OrderInfoResponse;
import com.xmniao.xmn.core.market.entity.pay.OrderResponse;
import com.xmniao.xmn.core.market.entity.pay.ProductDetails;
import com.xmniao.xmn.core.market.entity.pay.ProductResponse;
import com.xmniao.xmn.core.market.service.pay.OrderListService;
import com.xmniao.xmn.core.util.PropertiesUtil;
import com.xmniao.xmn.core.util.StrUtils;

/**
 * 
 * @projectName: xmnService
 * @ClassName: OrderListServiceImpl
 * @Description:积分订单列表
 * @author: liuzhihao
 * @param <T>
 * @date: 2017年1月13日 下午5:09:27
 */
@Service
public class OrderListServiceImpl<T> implements OrderListService{

	@Autowired
	private SessionTokenService sessionTokenService;

	@Autowired
	private MarketBillFreshDao marketBillFreshDao;
	
	@Autowired
	private MarketBillFreshSubDao marketBillFreshSubDao;

	@Autowired
	private ProductBillDao productBillDao;

	@Autowired
	private PropertiesUtil propertiesUtil;
	
	@Autowired
	private ProductDetailsDao productDetailsDao;
	
	//注入用户dao
	@Autowired
	private LiveUserDao liveUserDao;
	
	@Autowired
	private String fileUrl;

	private MapResponse mapResponse = null;
	

	/**
	 * 订单列表
	 * 
	 * @param orderInfoRequest
	 * @return
	 */
	public Object list(OrderListRequest orderListRequest) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		// 获取用户的uid
		String uid = ObjectUtils.toString(sessionTokenService.getStringForValue(orderListRequest.getSessiontoken()));

		// 订单类型
		Integer type = orderListRequest.getType();

		Integer page = orderListRequest.getPage();
		Integer limit = Constant.PAGE_LIMIT;
		List<OrderResponse> orderResponse = new ArrayList<OrderResponse>();
		try {
			orderResponse = marketBillFreshDao.queryOrderList(uid, type, page, limit);
		} catch (Exception e) {
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE, "查询订单列表异常");
		}
		
		String platformPhone = "";
		try {
			platformPhone = propertiesUtil.getValue("platform_telphone", "conf_common.properties");
		} catch (Exception e) {
			e.printStackTrace();
			platformPhone = "4007766333";
		}
		
		if (orderResponse.isEmpty()) {
			map.put("list", orderResponse);
			map.put("platformPhone", platformPhone);
			mapResponse = new MapResponse(ResponseCode.SUCCESS, "查询订单成功");
			mapResponse.setResponse(map);
			return mapResponse;
		}

		for (OrderResponse or : orderResponse) {

			//判断是否用优惠卷抵用了
			Double payPrice = or.getPayPrice().doubleValue();
			if(payPrice == 0){
				or.setIntegral(BigDecimal.valueOf(0.00));
			}
			
			Integer status = or.getStatus();// 订单状态

			switch (status) {
				case 1:
					or.setOrderMark("待发货");
					break;
				case 2:
					or.setOrderMark("待收货");
					break;
				case 3:
					or.setOrderMark("已完成");
					break;
				case 4:
					or.setOrderMark("待付款");
					break;
				case 5:
					or.setOrderMark("已关闭");
					break;
				default:
					break;
			}

			//判断送礼人姓名
			Integer sendUid = or.getSendUid();
			if(sendUid != null){
				or.setSendName(getSendGitName(sendUid));
			}
		}
		getBillProduct(orderResponse);

		map.put("list", orderResponse);
		map.put("platformPhone", platformPhone);
		mapResponse = new MapResponse(ResponseCode.SUCCESS, "查询订单成功");
		mapResponse.setResponse(map);
		return mapResponse;
	}

	/**
	 * 订单详情
	 * 
	 * @param orderInfoRequest
	 * @return
	 */
	public Object view(OrderInfoRequest orderInfoRequest) {

		// 用户ID
		String uid = ObjectUtils.toString(sessionTokenService.getStringForValue(orderInfoRequest.getSessiontoken()));
		// 订单ID
		String bid = orderInfoRequest.getBid().toString();

		OrderInfoResponse orderInfo = getBillFresh(uid, bid);
		TResponse<OrderInfoResponse> response = new TResponse<OrderInfoResponse>(ResponseCode.SUCCESS, "查看成功");
		response.setResponse(orderInfo);
		return response;

	}

	protected OrderInfoResponse getBillFresh(String uid, String bid) {

		OrderInfoResponse response = null;
		List<ProductResponse> products = new ArrayList<ProductResponse>();
		// 先查子单，判断订单是否支付成功，产生拆单
		response = marketBillFreshDao.findOneBySubBid(uid, bid);

		if (response == null) {
			// 在主单未拆单的时候查询主单
			response = marketBillFreshDao.findOneByBid(uid, bid);
		}

		if (response != null) {

			Integer source = response.getSource();// 数据来源 0 来自于子单 1来自于主单

			Integer status = response.getStatus();// 订单状态

			if (source == 1) {
				response.setSellerPhone("");
				switch (status) {
				case 0:
					response.setStatus(4);// 待支付
					response.setOrderMark("订单将在24小时后关闭,请及时付款");
					break;
				case 1:
					response.setStatus(1);
					response.setOrderMark("您的订单开始处理，即将发货");
					response.setRemindExpress(2);
					break;

				case 2:
					response.setStatus(5);// 已关闭
					response.setOrderMark("订单支付超时已关闭");
					break;
				default:
					response.setStatus(0);
					response.setOrderMark("订单支付出错,请联系客服退款!");
					break;
				}

				products = productBillDao.queryProductBillByBid(Long.parseLong(bid));
			} else {
				Integer supplierId = response.getSupplierId();// 供应商ID
				
				if (supplierId == null) {
					response.setSellerPhone("");
				} else {
					String phone = marketBillFreshDao.querySupplierPhone(supplierId.toString());

					response.setSellerPhone(phone);// 供应商电话
				}

				switch (status) {
					case 0:
						response.setStatus(1);// 待发货
						response.setOrderMark("您的订单开始处理，即将发货");
						break;
					case 1:
						response.setStatus(2);// 待收货
						response.setOrderMark("将会在发货20天后自动确认收货");
						break;
					case 6:
						response.setStatus(3);// 待收货
						response.setOrderMark("祝你购物愉快");
						break;
					default:
						response.setStatus(0);// 待支付
						response.setOrderMark("订单支付出错,请联系客服退款!");
						break;
				}

				//获取子单消费积分
				Double integral = getIntegral(Long.parseLong(bid));
				
				response.setIntegral(BigDecimal.valueOf(integral));
				
				products = productBillDao.queryProductBillBySubBid(Long.parseLong(bid));
			}

			String platformPhone = "";
			try {
				platformPhone = propertiesUtil.getValue("platform_telphone", "conf_common.properties");
			} catch (Exception e) {
				e.printStackTrace();
				platformPhone = "4007766333";
			}
			
			if (!products.isEmpty()) {

				for (ProductResponse product : products) {

					String breviary = product.getBreviary();
					
					if(StringUtils.isEmpty(breviary)){
						
						ProductDetails pd = productDetailsDao.selectByCodeId(product.getCodeId().longValue());
						if(pd != null){
							breviary = getProductImage(pd);
						}
					}
					product.setBreviary(fileUrl+breviary);
				}
				response.setWareNum(products.size());
				response.setProducts(products);
			}

			//判断是否存在送礼人ID
			Integer sendUid = response.getSendUid();
			if(sendUid != null){
				response.setSendName(getSendGitName(sendUid));
			}
			
		}
		return response;
	}

	/**
	 * 查询订单商品
	 * 
	 * @author xiaoxiong
	 * @date 2016年11月25日
	 */
	protected void getBillProduct(List<OrderResponse> list) {

		for (OrderResponse bill : list) {

			List<ProductResponse> response = new ArrayList<ProductResponse>();
			try {
				// 先查询子单
				response = productBillDao.queryProductBillBySubBid(bill.getBid());

				if (response.isEmpty()) {
					// 查询父单
					response = productBillDao.queryProductBillByBid(bill.getBid());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (!response.isEmpty()) {
				for (ProductResponse pb : response) {

					String breviary = pb.getBreviary();
					if(StringUtils.isEmpty(breviary)){
						ProductDetails productDetail = productDetailsDao.selectByCodeId(pb.getCodeId().longValue());
						
						if(productDetail != null){
							breviary = getProductImage(productDetail);
						}
					}
					if (breviary.contains(fileUrl)) {
						pb.setBreviary(breviary);
					} else {
						pb.setBreviary(fileUrl + breviary);
					}
					Integer activityId = pb.getActivityId();

					if (activityId != null) {
						String goodsName = pb.getGoodsName();
						if (StringUtils.isNotEmpty(goodsName)) {
							pb.setPname(goodsName);
						}
					}
				}
			}
			bill.setProducts(response);
		}
	}

	//获取拆单后的子单总消费积分
	public Double getIntegral(Long bid){
		
		Double integral = 0.0;
		
		try{
			integral = marketBillFreshSubDao.sumOrderIntegral(bid);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return integral;
	}

	/**
	 * 获取图片
	 * @param productDetail
	 * @return
	 */
	public String getProductImage(ProductDetails productDetail){
		List<String> images = new ArrayList<String>();
		images.add(ObjectUtils.toString(productDetail.getPic1()));
		images.add(ObjectUtils.toString(productDetail.getPic2()));
		images.add(ObjectUtils.toString(productDetail.getPic3()));
		images.add(ObjectUtils.toString(productDetail.getPic4()));
		images.add(ObjectUtils.toString(productDetail.getPic5()));
		images.add(ObjectUtils.toString(productDetail.getImg1()));
		images.add(ObjectUtils.toString(productDetail.getImg2()));
		images.add(ObjectUtils.toString(productDetail.getImg3()));
		images.add(ObjectUtils.toString(productDetail.getImg4()));
		images.add(ObjectUtils.toString(productDetail.getImg5()));
		images.add(ObjectUtils.toString(productDetail.getImg6()));
		images.add(ObjectUtils.toString(productDetail.getImg7()));
		images.add(ObjectUtils.toString(productDetail.getImg8()));
		images.add(ObjectUtils.toString(productDetail.getImg9()));
		images.add(ObjectUtils.toString(productDetail.getImg10()));
		images.add(ObjectUtils.toString(productDetail.getImg11()));
		images.add(ObjectUtils.toString(productDetail.getImg12()));
		
		if(!images.isEmpty()){
			for(String image : images){
				if(StringUtils.isNotEmpty(image)){
					return image;
				}
			}
		}
		return "";
	}
	
	/**
	 * 获取送礼人名称
	 * @param sendUid
	 * @return
	 */
	private String getSendGitName(Integer sendUid){
		String name = "";
		//通过送礼人UID查询送礼人姓名
		Map<Object,Object> fusermap = liveUserDao.findUserInfoByUid(sendUid);
		if (fusermap != null && fusermap.size() > 0){
			
			name = ObjectUtils.toString(fusermap.get("nname"));
			
			if(StringUtils.isEmpty(name)){
				name = ObjectUtils.toString(fusermap.get("uname"));
//				if(StringUtils.isEmpty(name)){
//					name = ObjectUtils.toString(fusermap.get("openid"));
//				}
				
				if(StringUtils.isNotEmpty(name)){
					//格式化name
					if(name.matches("^(1[\\d]{10})")){
						name = StrUtils.regexReplacePhone(name);
					}else{
//						name = "微信用户"+name.substring(3, 7);
						name = "匿名用户";
					}
				}
			}
		}
		
		return name;
	}
}
