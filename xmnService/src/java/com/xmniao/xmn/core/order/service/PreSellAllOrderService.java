package com.xmniao.xmn.core.order.service;

import java.math.BigDecimal;
import java.util.*;

import com.xmniao.xmn.core.common.Constant;
import com.xmniao.xmn.core.common.entity.BisException;
import com.xmniao.xmn.core.market.common.Response;
import com.xmniao.xmn.core.order.dao.CouponFansOrderDao;
import com.xmniao.xmn.core.order.entity.ExperienceOfficerOrder;
import com.xmniao.xmn.core.order.entity.MultipleOrder;
import com.xmniao.xmn.core.sellerPackage.dao.SellerPackageOrderDao;
import com.xmniao.xmn.core.sellerPackage.entity.SellerPackage;
import com.xmniao.xmn.core.sellerPackage.entity.SellerPackageOrder;
import com.xmniao.xmn.core.util.DateUtil;
import com.xmniao.xmn.core.util.PropertiesUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.order.BillOrderQueryRequest;
import com.xmniao.xmn.core.common.request.order.PreSellOrderQueryRequest;
import com.xmniao.xmn.core.common.request.sellerPackage.SellerPackageQueryRequest;
import com.xmniao.xmn.core.sellerPackage.service.SellerPackageOrderService;
import org.springframework.stereotype.Service;

@Service
public class PreSellAllOrderService {

	private final Logger log = Logger.getLogger(PreSellAllOrderService.class);
	
	@Autowired
	private SessionTokenService sessionTokenService;
	
	@Autowired
	private CouponFansOrderInfoService couponFansOrderInfoService;
	
	@Autowired
	private SellerPackageOrderService sellerPackageOrderService;

	@Autowired
	private ExperienceConfigService experienceConfigService;
	@Autowired
	private PropertiesUtil propertiesUtil;

	/**
	 * 预售订单列表
	 * */
	public Object queryPreSellAllOrderList(PreSellOrderQueryRequest request){
		try {
			String sUid = sessionTokenService.getStringForValue(request.getSessiontoken()) + "";
			if (StringUtils.isEmpty(sUid) || "null".equalsIgnoreCase(sUid)) {
				return new BaseResponse(ResponseCode.TOKENERR, "token已失效,请重新登录");
			}
			Integer uid = Integer.parseInt(sUid);
			String maxTime = request.getMaxTime();
			List<MultipleOrder> mulOrders = new ArrayList<MultipleOrder>();

			List<MultipleOrder> cOrderList = couponOrderList(request.getSessiontoken(), maxTime);
			List<MultipleOrder> sOrderList = sellerPackageOrderList(request.getSessiontoken(), maxTime);
			List<MultipleOrder> eOrderList = experienceOfficerOrders(uid, maxTime);
			mulOrders.addAll(cOrderList);
			mulOrders.addAll(sOrderList);
			mulOrders.addAll(eOrderList);

			List<MultipleOrder> orderSortList = sortList(mulOrders);

			MapResponse mapResponse = new MapResponse(ResponseCode.SUCCESS, "获取预售订单列表成功");
			Map<Object, Object> resultMap = new HashMap<Object, Object>();
			resultMap.put("preOrderList", orderSortList);
			mapResponse.setResponse(resultMap);
			return mapResponse;
		} catch (BisException e) {
			log.warn(e.getMessage());
			return new BaseResponse(ResponseCode.FAILURE, e.getMessage());
		} catch (Exception e) {
			log.warn("获取预售订单列表失败");
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE, "获取预售订单列表失败");
		}
	}

	// 粉丝卷预售订单列表
	private List<MultipleOrder> couponOrderList(String sessiontoken, String maxTime) throws BisException {
		try {
			List<MultipleOrder> mulOrders = new ArrayList<MultipleOrder>();
			// 粉丝卷预售订单列表
			BillOrderQueryRequest fansRequest = new BillOrderQueryRequest();
			fansRequest.setSessiontoken(sessiontoken);
			fansRequest.setPage(1);
			BaseResponse fansOrderResponse = (BaseResponse) couponFansOrderInfoService.queryCouponOrderList(fansRequest, maxTime, 1);
			if (fansOrderResponse.getState() == ResponseCode.SUCCESS) {
				MapResponse mapResponse = (MapResponse) fansOrderResponse;
				List<Map<Object, Object>> billOrderList = (List<Map<Object, Object>>) mapResponse.getResponse().get("billList");
				if (billOrderList != null) {
					for (Map<Object, Object> map : billOrderList) {
						MultipleOrder order = new MultipleOrder();
						order.setOrderNo(map.get("bid").toString());
						order.setTitle(map.get("sellername") == null ? "" : map.get("sellername").toString());
						order.setNums(map.get("seats") == null ? 0 : Integer.parseInt(map.get("seats").toString()));
						order.setPicUrl(map.get("zhiboThumbnail") == null ? "" : map.get("zhiboThumbnail").toString());
						String totalAmount = "";
						String reallyPayAmount = "";
						try {
							totalAmount = "¥" + map.get("money").toString();
							reallyPayAmount = "¥" + map.get("payment").toString();
						} catch (Exception e) {
							log.warn("粉丝卷，解析总额失败：" + map.toString());
						}
						order.setReallyPayAmount(reallyPayAmount);
						order.setTotalAmount(totalAmount);
						order.setCname(map.get("cname") == null ? "" : map.get("cname").toString());
						order.setStatus(1);  //1 已支付 2  已完成
						order.setOrderStautsDesc("已支付");
						Date date = new Date();
						try {
							date = DateUtil.parse(map.get("create_time").toString());
						} catch (Exception e) {
							e.printStackTrace();
							log.warn("粉丝卷，解析时间失败" + map.toString());
							continue;
						}
						order.setTmpDate(date);
						order.setCreateTime(DateUtil.format(date, DateUtil.defaultSimpleFormater));
						order.setUseTimeDesc(map.get("useTime") == null ? "" : map.get("useTime").toString());
						order.setWhichType(2);
						mulOrders.add(order);
					}
				}
			}
			return mulOrders;
		} catch (Exception e) {
			e.printStackTrace();
			throw new BisException("获取粉丝卷预售订单列表失败");
		}
	}

	// 套餐预售订单列表
	private List<MultipleOrder> sellerPackageOrderList(String sessiontoken, String maxTime) throws BisException {
		try {
			List<MultipleOrder> mulOrders = new ArrayList<MultipleOrder>();
			// 套餐预售订单列表
			SellerPackageQueryRequest sellerRequest = new SellerPackageQueryRequest();
			sellerRequest.setSessiontoken(sessiontoken);
			sellerRequest.setPage(1);
			BaseResponse sellerResponse = (BaseResponse) sellerPackageOrderService.querySellerPackageOrderList(sellerRequest, maxTime, 1);
			if (sellerResponse.getState() == ResponseCode.SUCCESS) {
				MapResponse mapResponse = (MapResponse) sellerResponse;
				List<SellerPackageOrder> orderList = (List<SellerPackageOrder>) mapResponse.getResponse().get("orderList");
				if (orderList != null) {
					for (SellerPackageOrder sellerPackageOrder : orderList) {
						MultipleOrder order = new MultipleOrder();
						order.setOrderNo(sellerPackageOrder.getOrderNo());
						order.setTitle(sellerPackageOrder.getTitle() == null ? "" : sellerPackageOrder.getTitle());
						order.setNums(sellerPackageOrder.getNums() == null ? 0 : sellerPackageOrder.getNums());
						order.setPicUrl(sellerPackageOrder.getPicUrl());
						String reallyPayAmount = sellerPackageOrder.getReallyPayAmount();
						if (reallyPayAmount.contains("鸟币:") && reallyPayAmount.contains("¥")) {
							reallyPayAmount = reallyPayAmount.replace("鸟币:", " + ");
							reallyPayAmount = reallyPayAmount + "鸟币";
						} else if (reallyPayAmount.contains("鸟币:")) {
							reallyPayAmount = reallyPayAmount.replace("鸟币:", "");
							reallyPayAmount = reallyPayAmount + "鸟币";
						} 
						order.setReallyPayAmount(StringUtils.isEmpty(reallyPayAmount)?"¥:0":reallyPayAmount);
						order.setTotalAmount("¥" + sellerPackageOrder.getTotalAmount());
						order.setCname("套餐");
						String desc = null;
						Integer status = 1;  //1 已支付 2  已完成
						try {
							if (sellerPackageOrder.getOrderStautsDesc().contains("已完成")) {  //已完成
								desc = "已完成";
								status = 2;
							} else {  //
								desc = "已支付";
								status = 1;
							}
						} catch (Exception e) {
							log.warn("套餐，订单支付状态描述失败");
							continue;
						}
						order.setStatus(status);
						order.setOrderStautsDesc(desc);
						order.setTmpDate(sellerPackageOrder.getCreateTime());
						order.setCreateTime(DateUtil.format(sellerPackageOrder.getCreateTime(), DateUtil.defaultSimpleFormater));
						StringBuilder sb = new StringBuilder();
						// 使用期限
						if (sellerPackageOrder.getUse_start_time() != null && sellerPackageOrder.getUse_end_time() != null) {
							sb.append("使用期限：");
							String str = DateUtil.format(sellerPackageOrder.getUse_start_time(), DateUtil.daySimpleFormater);
							sb.append(str);
							sb.append(" - ");
							sb.append(DateUtil.format(sellerPackageOrder.getUse_end_time(), DateUtil.daySimpleFormater));
						}
						order.setUseTimeDesc(sb.toString());
						order.setWhichType(1);
						mulOrders.add(order);
					}
				}
			}
			return mulOrders;
		} catch (Exception e) {
			e.printStackTrace();
			throw new BisException("获取套餐预售订单列表失败");
		}
	}

	// 美食体验官预售订单列表
	private List<MultipleOrder> experienceOfficerOrders(Integer uid, String maxTime) throws BisException {
		try {
			List<MultipleOrder> mulOrders = new ArrayList<MultipleOrder>();
			// 美食体验官预售订单列表
			List<ExperienceOfficerOrder> experienceOfficerOrders = experienceConfigService.getExperienceOrderList(uid, maxTime, 1, 1);
			if (experienceOfficerOrders != null && experienceOfficerOrders.size() > 0) {
				for (ExperienceOfficerOrder experienceOfficerOrder : experienceOfficerOrders) {
					MultipleOrder order = new MultipleOrder();
					order.setOrderNo(experienceOfficerOrder.getOrderNo());
					order.setTitle("美食体验官");
					order.setNums(1);
					String picUrl = "";
					try {
						picUrl = propertiesUtil.getValue("experienceOfficerPic", "conf_common.properties");
					}catch (Exception e) {
						log.warn("读取配置信息失败：experienceOfficerPic");
					}
					order.setPicUrl(picUrl);
					String totalAmount = "";
					String reallyPayAmount = "";
					try {
						totalAmount = "¥" + experienceOfficerOrder.getAmount().toString();
						reallyPayAmount = "¥" + experienceOfficerOrder.getAmount().toString();
					} catch (Exception e) {
						log.warn("解析美食体验官金额失败");
					}
					order.setReallyPayAmount(reallyPayAmount);
					order.setTotalAmount(totalAmount);
					Integer days = experienceOfficerOrder.getDays();  //有效天数
					Integer nums = experienceOfficerOrder.getNums(); //可体验次数
					StringBuilder sb = new StringBuilder();

					if (days != null && days > 0 && nums != null) {
						sb.append(days);
						sb.append("天有效,");
						sb.append(nums);
						sb.append("次机会");
					}
					StringBuilder useTime = new StringBuilder();
					try {
						Date lastDay = DateUtils.addDays(experienceOfficerOrder.getCreateTime(), days);
						useTime.append("使用期限:");
						useTime.append(DateUtil.format(experienceOfficerOrder.getCreateTime(), DateUtil.daySimpleFormater));
						useTime.append(" - ");
						useTime.append(DateUtil.format(lastDay, DateUtil.daySimpleFormater));;
					} catch (Exception e) {
						log.warn("获取使用期限失败" + experienceOfficerOrder.toString());
						continue;
					}
					order.setCname(sb.toString());
					order.setStatus(1);  //1 已支付 2  已完成
					order.setOrderStautsDesc("已支付");
					order.setTmpDate(experienceOfficerOrder.getCreateTime());
					order.setCreateTime(DateUtil.format(experienceOfficerOrder.getCreateTime(), DateUtil.defaultSimpleFormater));
					order.setUseTimeDesc(useTime.toString());
					order.setWhichType(3);
					mulOrders.add(order);
				}
			}
			return mulOrders;
		} catch (Exception e) {
			e.printStackTrace();
			throw new BisException("获取美食体验官预售订单列表失败");
		}
	}


	private List<MultipleOrder> sortList(List<MultipleOrder> multipleOrders) {
		Collections.sort(multipleOrders, new Comparator<MultipleOrder>() {
			@Override
			public int compare(MultipleOrder o1, MultipleOrder o2) {
				Date d1 = o1.getTmpDate();
				Date d2 = o2.getTmpDate();
				long t1 = d1 == null ? 0 : d1.getTime();
				long t2 = d2 == null ? 0 : d2.getTime();

				// 降序
				if (t1 > t2) {
					return -1;
				} else if (t1 < t2) {
					return 1;
				}
				return 0;
			}
		});
		List<MultipleOrder> orderList = new ArrayList<MultipleOrder>();
		for (int i = 0; i < multipleOrders.size(); i++) {
			if (i < Constant.PAGE_LIMIT) {
				orderList.add(multipleOrders.get(i));
			} else {
				break;
			}
		}
		return orderList;
	}

}
