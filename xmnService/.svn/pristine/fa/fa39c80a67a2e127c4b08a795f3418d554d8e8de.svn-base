package com.xmniao.xmn.core.order.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.Constant;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.OrderProcessInfoRequest;
import com.xmniao.xmn.core.market.dao.BillFreshActivityDao;
import com.xmniao.xmn.core.order.dao.FreshOrderBackProdSumDao;
import com.xmniao.xmn.core.order.dao.FreshOrderProcessDao;
import com.xmniao.xmn.core.order.dao.FreshOrderProductViewDao;
import com.xmniao.xmn.core.order.entity.FreshOrder;
import com.xmniao.xmn.core.order.entity.FreshOrderProduct;
import com.xmniao.xmn.core.order.entity.IntegralOrder;
import com.xmniao.xmn.core.order.entity.OrderBackProduct;
import com.xmniao.xmn.core.order.entity.OrderExpress;
import com.xmniao.xmn.core.verification.dao.BillDao;

/**
 * 项目描述：XmnService
 * API描述： 订单操作
 * @author yhl
 * 创建时间：2016年6月17日10:13:55
 * @version
 * */
@Service
public class FreshOrderProcessService {

	@Autowired
	private FreshOrderProcessDao freshOrderProcessDao;
	
	@Autowired
	private BillFreshActivityDao billFreshActivityDao;
	
	@Autowired
	private FreshOrderProductViewDao freshOrderProductViewDao;
	
	@Autowired
	private FreshOrderBackProdSumDao orderBackProdSumDao;
	
	//注入服务器地址  图片服务器配置地址
	@Autowired
	private String fileUrl;
	
	//订单物流查询地址
	@Autowired
	private String orderExpressApi;
	
	@Autowired
	private BillDao billDao;
	
	/**
	 * 注入redis缓存
	 */
	@Autowired
	private SessionTokenService sessionTokenService;
	
	/**
	 * API描述 获取订单详细信息 
	 * @author yhl
	 * @param object
	 * @return object
	 * */
	public Object queryOrderView(OrderProcessInfoRequest oRequest){
		try {
			String uid = sessionTokenService.getStringForValue(oRequest.getSessiontoken())+"";
			Map<Object, Object> map =  new HashMap<Object, Object>();
			map.put(Constant.BID, oRequest.getBid());
			map.put(Constant.UID, uid);
			
			Map<Object, Object> order_view = new HashMap<Object, Object>();
			MapResponse response = null;
			FreshOrder  orders = freshOrderProcessDao.queryOrderSubView(map);
			
			//如果是子订单  返回子订单信息
			if (orders!=null) {
				//返回子订单里的商品信息
				List<FreshOrderProduct> products_list = freshOrderProductViewDao.queryOrderSubProductList(map);
				
				List<FreshOrderProduct> prod_list = productImage(products_list);
				orders.setProduct(prod_list);
				orders.setOrdersource(1);
				order_view.put("order", orders);//解析组装
				response = new MapResponse(ResponseCode.SUCCESS,"获取成功");
				response.setResponse(order_view);
				return response;
			}
			
			//子订单为空  查询主订单信息
			FreshOrder order  =  freshOrderProcessDao.queryOrderView(map);
			if (order!=null) {
				//查询主订单 商品信息
				List<FreshOrderProduct> products_list = freshOrderProductViewDao.queryOrderProductList(map);
				
				List<FreshOrderProduct> prod_list = productImage(products_list);//组装商品图路径
				order.setOrdersource(0);
				order.setProduct(prod_list);
				
				order_view.put("order", order);
				response = new MapResponse(ResponseCode.SUCCESS,"获取成功");
				response.setResponse(order_view);
				return response;
			}
			return new BaseResponse(ResponseCode.DATA_NULL, "该订单不存在。");
		} catch (Exception e) {
			e.printStackTrace();
			return new BaseResponse(ResponseCode.DATA_NULL, "未知错误，请稍后或联系管理员");
		}
		
	}
	
	
	/**
	 * API描述 修改订单  -取消订单
	 * @author yhl
	 * @param object
	 * @return object
	 * */
	public Object editOrderForClose(OrderProcessInfoRequest oRequest){
		try {
			String uid = sessionTokenService.getStringForValue(oRequest.getSessiontoken())+"";
			Map<Object, Object> map =  new HashMap<Object, Object>();
			map.put(Constant.BID, oRequest.getBid());
			map.put(Constant.UID, uid);
			
			//获取订单状态 有无发生变化  订单状态为0 未支付时 操作   查询主订单 
			FreshOrder freshOrder =  freshOrderProcessDao.queryOrderView(map);
			//获取订单有无使用优惠券
			Map<Object, Object> couponInfo = freshOrderProcessDao.queryOrderCouponView(map);
			
			//判断订单状态  是否未支付状态
			if (freshOrder!=null) {
				if (Constant.ORDER_PAY_NO != freshOrder.getStatus()) {
					return new BaseResponse(ResponseCode.DATA_NULL, "订单当前状态不允许取消");
				}
				
				//修改入参数为取消状态   
				map.put(Constant.STATUS, Constant.ORDER_CLOSE);
				//修改订单状态 为 取消订单
				int edit_rows  =   freshOrderProcessDao.editOrderForClose(map);
				if (edit_rows>0) {
					try {
						//回退库存操作   查找订单商品
						List<FreshOrderProduct> products_list =  freshOrderProductViewDao.queryOrderProductList(map);
						if (products_list.size()>0) {
							List<OrderBackProduct> products = new ArrayList<OrderBackProduct>();
							for (int i = 0; i < products_list.size(); i++) {
								FreshOrderProduct product = products_list.get(i);
								
								OrderBackProduct prod = new OrderBackProduct();
								prod.setCodeId(product.getCodeId());
								prod.setWareNum(product.getWareNum());
								//放入实体对象  sql批量修改  回退商品库存
								products.add(prod);
							}
							//执行批量 回退商品库存
							orderBackProdSumDao.orderbackProductSum(products);
							
							//如果订单是使用了优惠券  回退优惠券面额 表示
							if (couponInfo!=null) {
								Map<Object, Object> paramMap = new HashMap<Object, Object>();
								paramMap.put("cdid", couponInfo.get("cdid").toString());
								freshOrderProcessDao.editOrderCouponForClose(paramMap);
							}
							
						}
						Map<Object, Object> order_view = new HashMap<Object, Object>();
						MapResponse response = new MapResponse(ResponseCode.SUCCESS,"取消订单成功");
						response.setResponse(order_view);
						return response;
					} catch (Exception e) {
						e.printStackTrace();
						return new BaseResponse(ResponseCode.DATA_NULL, "未知错误，请稍后或联系管理员");
					}
				}else {
					return new BaseResponse(ResponseCode.DATA_NULL, "此订单不存在");
				}
			}else {
				return new BaseResponse(ResponseCode.DATA_NULL, "未找到该订单");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new BaseResponse(ResponseCode.DATA_NULL, "订单取消失败");
		}
	}
	
	
	/**
	 * API描述 获取订单物流详细信息
	 * @author yhl
	 * @param object
	 * @return object
	 * */
	public Object queryOrderExpress(OrderProcessInfoRequest oRequest){
		try {
			Map<Object, Object> map =  new HashMap<Object, Object>();
			MapResponse response = null;
			map.put(Constant.BID, oRequest.getBid());
			//获取订单物流信息
			OrderExpress orderExpress = freshOrderProcessDao.queryOrderExpressview(map);
			
			if (orderExpress!=null && null!=orderExpress.getCourier_number() && null!=orderExpress.getCourier_type()) {
				//解析物流信息内容 
				String courier_number = orderExpress.getCourier_number();
				//替换快递 极速数据api 入参
				String queryUrl = orderExpressApi.replace("NUMBER", courier_number);
				String result = sendGet(queryUrl).trim().replace("&nbsp;", "");//执行快递100接口调用 返回物流结果
				if (result.contains("status")) {
					JSONObject jsonObject =JSON.parseObject(result);
					String status = jsonObject.get("status").toString();
					//整合个人物流或企业物流返回状态结果  便于接口切换
 					if (status.equals("0") || status.equals("205")) {
						
						jsonObject.put("express_name", orderExpress.getExpress_name()==null?" ":orderExpress.getExpress_name());
						jsonObject.put("orderCode", oRequest.getBid().toString());
						
						Map<Object, Object> express = new HashMap<Object, Object>();
						express.put("express", jsonObject);
						
						response = new MapResponse(ResponseCode.SUCCESS,"获取成功");
						response.setResponse(express);
						return response;
					}else {
						response = new MapResponse(ResponseCode.DATA_NULL,"物流信息查询失败");
						return response;
					}
				}else {
					response = new MapResponse(ResponseCode.DATA_NULL,"暂无相关物流信息");
					return response;
				}
			}else {
				response = new MapResponse(ResponseCode.DATA_NULL,"暂无相关物流信息");
				return response;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return new BaseResponse(ResponseCode.DATA_NULL, "查询物流信息接口调用失败");
		}
	}

	/**
	 * 
	* @Title: pickUpConfirm
	* @Description: 确认收货
	* @return Object    返回类型
	* @author
	* @throws
	 */
	public Object pickUpConfirm(OrderProcessInfoRequest orderProcessInfoRequest) {
		try {
			//根据已支付的订单号去查询订单状态
			Byte status = orderBackProdSumDao.queryOrderByBid(Long.parseLong(orderProcessInfoRequest.getBid()));
			if (status == null) {
				return new BaseResponse(ResponseCode.FAILURE, "查无次订单,请联系管理员");
			}else if (status == 1) {
				//修改确定收货状态
				Integer result = orderBackProdSumDao.mofifyOrderStatusByBid(Long.parseLong(orderProcessInfoRequest.getBid()));
				if (result == 0) {
					return new BaseResponse(ResponseCode.FAILURE, "确认收货失败,请联系管理员");
				}
			}else if(status != 1 && status != 5) {
				return new BaseResponse(ResponseCode.FAILURE, "系统错误，请联系管理员");
			}
			
			//响应
			BaseResponse response = new BaseResponse(ResponseCode.SUCCESS, "确认收货成功");
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE, "确认收货失败,请联系管理员");
		}
	}
	
	/**
	 * 获取积分兑换订单详情
	 * @author yhl
	 * @param object
	 * @return object
	 * 2016年6月28日15:08:15 
	 * */
	public Object queryIntegralOrderView(OrderProcessInfoRequest oRequest){
		try {
//			String uid = sessionTokenService.getStringForValue(oRequest.getSessiontoken())+"";
			Map<Object, Object> map =  new HashMap<Object, Object>();
			MapResponse response = null;
			map.put(Constant.BID, oRequest.getBid());
			IntegralOrder order =  freshOrderProcessDao.queryIntegralOrderList(map);
			if (order!=null) {
				Long sellerid = order.getSellerid();
				Map<Object, Object> logo_url = freshOrderProcessDao.querySellerLogoUrl(sellerid);
				if (logo_url!=null) {
					order.setBreviary(fileUrl+(logo_url.get("picurl").toString()==null?"":logo_url.get("picurl").toString()));
				}else {
					order.setBreviary(" ");
				}
				order.setIntegral(order.getIntegral()*-1);
				order.setSellername(order.getSellername()==null?" ":order.getSellername());
				Map<Object, Object> order_integral = new HashMap<Object, Object>();
				order_integral.put("integral", order);
				response = new MapResponse(ResponseCode.SUCCESS,"获取成功");
				response.setResponse(order_integral);
				return response;
			}else {
				return new BaseResponse(ResponseCode.FAILURE, "订单不存在，请确认");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE, "操作失败,请联系管理员");
		}
		
	}
	
	/**
	 * 获取积分兑换订单详情
	 * @author yhl
	 * @param object
	 * @return object
	 * 2016年6月28日15:08:15 
	 * */
	public Object queryRecommend(OrderProcessInfoRequest oRequest){
		MapResponse response = null;
		try {
//			String uid = sessionTokenService.getStringForValue(oRequest.getSessiontoken())+"";
			Map<Object, Object> map =  new HashMap<Object, Object>();
			Map<Object, Object> resultMap =  new HashMap<Object, Object>();
			map.put(Constant.BID, oRequest.getBid());
			//获取美食订单基本信息
			
			Map<Object, Object> billInfoMap = billDao.queryBillInfoByOrderNo(map);
			if (billInfoMap!=null) {
				List<Map<Object, Object>> freshList = new ArrayList<Map<Object, Object>>();
				map.put("fileUrl", fileUrl);
				map.put("page", 1);
				map.put("limit", 2);
				
				BigDecimal integral = new BigDecimal(billInfoMap.get("integral")==null?"0":billInfoMap.get("integral").toString());
				if (integral.compareTo(new BigDecimal(0))>0) {
					//查询积分商品 积分价格小于 获取积分的商品
					map.put("integral", integral);
					freshList = freshOrderProcessDao.queryFreshProductListByIntegral(map);
					if (freshList.size()>0) {
						resultMap.put("freshProductList", freshList);
					}else {
						//如果为查找到 就取积分等于5积分左右的商品
						map.put("integral", 5);
						freshList = freshOrderProcessDao.queryFreshProductListByIntegral(map);
						resultMap.put("freshProductList", freshList);
					}
					
				}else {
					//如果积分
					map.put("integral", 5);
					freshList = freshOrderProcessDao.queryFreshProductListByIntegral(map);
					resultMap.put("freshProductList", freshList);
					
				}
				response =  new MapResponse(ResponseCode.SUCCESS, "获取成功");
				response.setResponse(resultMap);
			}else {
				response =  new MapResponse(ResponseCode.FAILURE, "未获取到订单信息");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			response = new MapResponse(ResponseCode.FAILURE, "操作失败,请联系管理员");
		}
		return response;
	}
	
	
	/**
	 * 发送http请求 快递100 接口
	 * @author yhl
	 * @param  接口URL  值已匹配
	 * @return json
	 * 2016年6月21日19:50:17			 
	 * */
	public static String sendGet(String url) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url ;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("查询物流接口失败！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

	/**
	 * 组装商品图片的路径 返回商品list
	 * @author YHL
	 * @param map 
	 * @retturn map
	 * */
	public List<FreshOrderProduct> productImage(List<FreshOrderProduct> productsList){
		List<FreshOrderProduct> prod_list = new ArrayList<FreshOrderProduct>();
		for (int i = 0; i < productsList.size(); i++) {
			FreshOrderProduct product = productsList.get(i);
			if(product!=null){
				String attr_val="";//商品属性
				try {
					if( product.getBreviary()!=null) {
						String breviary = fileUrl+product.getBreviary();
						product.setBreviary(breviary);
					}
					if(null != product.getAttr_val()){
						JSONArray jsonArray=JSONArray.parseArray(product.getAttr_val());
						if(jsonArray!=null && jsonArray.size()>0){
							for(int j=0;j<jsonArray.size();j++){
								JSONObject json=jsonArray.getJSONObject(j);
								attr_val+=json.getString("property")+":"+json.getString("value")+";";
							}
							attr_val=attr_val.substring(0,attr_val.length()-1);
						}
						product.setAttr_val(attr_val);
					}
				} catch (Exception e) {
					e.printStackTrace();
					attr_val="";
				}
				prod_list.add(product);
			}
		}
		return prod_list;
	}
	
	
	/**
	 * 查询活动订单物流详情
	 * @param oRequest
	 * @return
	 */
	public Object queryActivityOrderExpress(OrderProcessInfoRequest oRequest){
        try {
            Map<Object, Object> map =  new HashMap<Object, Object>();
            MapResponse response = null;
            map.put(Constant.BID, oRequest.getBid());
            //获取订单物流信息
            OrderExpress orderExpress = billFreshActivityDao.queryOrderExpressview(map);
            
            if (orderExpress!=null && null!=orderExpress.getCourier_number() && null!=orderExpress.getCourier_type()) {
                //解析物流信息内容 
                String courier_number = orderExpress.getCourier_number();
                //替换快递 极速数据api 入参
                String queryUrl = orderExpressApi.replace("NUMBER", courier_number);
                String result = sendGet(queryUrl).trim().replace("&nbsp;", "");//执行快递100接口调用 返回物流结果
                if (result.contains("status")) {
                    JSONObject jsonObject =JSON.parseObject(result);
                    String status = jsonObject.get("status").toString();
                    //整合个人物流或企业物流返回状态结果  便于接口切换
                    if (status.equals("0") || status.equals("205")) {
                        
                        jsonObject.put("express_name", orderExpress.getExpress_name()==null?" ":orderExpress.getExpress_name());
                        jsonObject.put("orderCode", oRequest.getBid().toString());
                        
                        Map<Object, Object> express = new HashMap<Object, Object>();
                        express.put("express", jsonObject);
                        
                        response = new MapResponse(ResponseCode.SUCCESS,"获取成功");
                        response.setResponse(express);
                        return response;
                    }else {
                        response = new MapResponse(ResponseCode.DATA_NULL,"物流信息查询失败");
                        return response;
                    }
                }else {
                    response = new MapResponse(ResponseCode.DATA_NULL,"暂无相关物流信息");
                    return response;
                }
            }else {
                response = new MapResponse(ResponseCode.DATA_NULL,"暂无相关物流信息");
                return response;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResponse(ResponseCode.DATA_NULL, "查询物流信息接口调用失败");
        }
    }

}
