package com.xmn.saas.controller.api.v1.pay;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.jsoup.helper.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.xmn.saas.base.AbstractController;
import com.xmn.saas.base.Response;
import com.xmn.saas.base.ResponseCode;
import com.xmn.saas.base.ThriftBuilder;
import com.xmn.saas.constants.PaymentTypeConsts;
import com.xmn.saas.constants.RedpacketConsts;
import com.xmn.saas.entity.common.SellerAccount;
import com.xmn.saas.entity.redpacket.Redpacket;
import com.xmn.saas.service.base.RedisService;
import com.xmn.saas.service.redpacket.RedpacketService;
import com.xmn.saas.service.wallet.WalletService;
import com.xmn.saas.utils.HttpConnectionUtil;
import com.xmn.saas.utils.PropertiesUtil;
import com.xmn.saas.utils.Signature;
import com.xmn.saas.utils.WebUtils;


/**
 * 红包支付控制器
 * @ClassName: PayRedpacketController 
 * @Description: TODO 
 * @author dengqiang 
 * @date 2016年10月19日 上午9:44:57 
 */
@SuppressWarnings("all")
@RequestMapping("/api/v1/pay/redpacket")
@Controller(value = "api-v1-pay-redpacket-controller")
public class RedpacketController extends AbstractController{

	/**
	 * 日志
	 */
	private static final Logger logger = LoggerFactory.getLogger(RedpacketController.class);
	
	@Autowired
	private RedpacketService redpacketService;

	@Autowired
	private WalletService walletService;
	
	@Autowired
	private RedisService redisService;
	
	@Autowired
	private PropertiesUtil propertiesUtil;
	
	private Redpacket redpacket;
	
	private Map<String, Object> resultMap;
	
	private Map<String, Object> paramMap;
	
	private static final BigDecimal ZERO = new BigDecimal("0.00");
	
	/**
	 * 确认支付页面数据加载
	 * @Title: loadPayData 
	 * @Description: TODO 
	 * @param     设定文件 
	 * @return void    返回类型 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/load_paydata", method = RequestMethod.POST)
	public void loadPaydata() {
		try {
			String orderNo=WebUtils.getRequest().getParameter("orderNo");
			String sellerid=WebUtils.getRequest().getParameter("sellerid");
			logger.info("确认支付页面数据加载  orderNo:" + orderNo);
			logger.info("确认支付页面数据加载  sellerid:" + sellerid);
			
			if(StringUtil.isBlank(orderNo)){
				new Response(ResponseCode.FAILURE, "订单编号为空").write();
				return;
			}
			
			if(sellerid==null){
				new Response(ResponseCode.FAILURE, "商家id为空").write();
				return;
			}
			
			resultMap = new HashMap<String, Object>();
			paramMap = new HashMap<String, Object>();
			paramMap.put("ORDER_NO", orderNo);
			paramMap.put("SELLERID", sellerid);
			List<Redpacket> redpacketList = redpacketService.findRedpacketByParams(paramMap);
			if(redpacketList.isEmpty()){
				new Response(ResponseCode.FAILURE, "该红包订单信息不存在").write();
				return;
			}else{
				boolean isEnough = false;
				Redpacket redpacket=redpacketList.get(0);
				Integer redpacketType = redpacket.getRedpacketType();
				String redpacketName=redpacket.getRedpacketName();
				BigDecimal usableAmount = new BigDecimal("0.00"); //可用余额
				BigDecimal totalAmount = redpacket.getTotalAmount();//红包总金额
				SellerAccount sellerAccount = new SellerAccount(); // 获取商家钱包信息
				sellerAccount.setSellerid(Integer.valueOf(sellerid));
				sellerAccount.setType(1);
				Map<String, String> walletMap = walletService.getWalletBalance(sellerAccount);
				if (walletMap.isEmpty()) {
					new Response(ResponseCode.FAILURE, "未获取到商家钱包信息").write();
					return;
				} else {
					BigDecimal zbalance = new BigDecimal(walletMap.get("zbalance")).setScale(2, BigDecimal.ROUND_HALF_UP);// 商家赠送余额
					BigDecimal walletAmount = new BigDecimal(walletMap.get("amount")).setScale(2, BigDecimal.ROUND_HALF_UP);// 商家钱包余额
					BigDecimal balance = new BigDecimal(walletMap.get("balance")).setScale(2, BigDecimal.ROUND_HALF_UP);// 商家分账余额
					BigDecimal commision = new BigDecimal(walletMap.get("commision")).setScale(2, BigDecimal.ROUND_HALF_UP);// 商家佣金余额
					//BigDecimal integral = new BigDecimal(walletMap.get("integral")).setScale(2, BigDecimal.ROUND_HALF_UP);// 商家积分余额
					BigDecimal sellerAmount = new BigDecimal(walletMap.get("seller_amount")).setScale(2, BigDecimal.ROUND_HALF_UP);// 商家收益金额
				    usableAmount=walletAmount.add(zbalance).add(balance).add(commision).add(sellerAmount).setScale(2, BigDecimal.ROUND_HALF_UP);
					if (usableAmount.compareTo(totalAmount.setScale(2, BigDecimal.ROUND_HALF_UP)) > 0) { // 判断商家钱包余额是否足够支付红包总金额
						isEnough = true;
					}
				}
				resultMap.put("sypay_type", PaymentTypeConsts.sypay); // 钱包余额类型
				resultMap.put("wxpay_type", PaymentTypeConsts.wxpay); // 微信支付类型
				resultMap.put("alipay_type", PaymentTypeConsts.alipay); // 支付宝类型
				resultMap.put("redpacketType", redpacketType); // 红包类型
				resultMap.put("redpacketName", redpacketName); // 红包名称
				resultMap.put("totalAmount", totalAmount.setScale(2, BigDecimal.ROUND_HALF_UP)); // 红包总金额
				resultMap.put("amount", usableAmount.setScale(2, BigDecimal.ROUND_HALF_UP)); // 商家可用余额
				resultMap.put("isEnough", isEnough); // 是否足够支付
				resultMap.put("orderNo", orderNo); // 订单编号
				new Response(ResponseCode.SUCCESS, "确认支付页面数据加载成功", resultMap).write();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}finally {
			ThriftBuilder.close();
		}
	}
	
	/**
	 * 支付确认(请求统一下单支付接口获取发起支付请求参数数据)
	 * @Title: payConfirm 
	 * @Description: TODO 
	 * @param @param orderNo
	 * @param @param payType    设定文件 
	 * @return void    返回类型 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/pay_confirm", method = RequestMethod.POST)
	public void payConfirm(@RequestParam(value = "orderNo", required = true)  String orderNo,
			               @RequestParam(value = "payType", required = true) Integer payType){
		try {
			
			logger.info("支付确认  orderNo:" + orderNo);
			logger.info("支付确认  payType:" + payType);
			
			if(StringUtil.isBlank(orderNo)){
				new Response(ResponseCode.FAILURE, "订单编号为空").write();
				return;
			}
			
			if(payType==null){
				new Response(ResponseCode.FAILURE, "支付类型为空").write();
				return;
			}
			
			Redpacket redpacket=redpacketService.findRedpacketByOrderNo(orderNo); //获取红包订单信息
			if(redpacket.getId()==null){
				new Response(ResponseCode.FAILURE, "该红包订单信息不存在").write();
			}else{
				if(redpacket.getPayStatus().intValue()==RedpacketConsts.PAYNO || redpacket.getPayStatus().intValue()==RedpacketConsts.PAYCALCEN || redpacket.getPayStatus().intValue()==RedpacketConsts.PAYFAILURE){
					    BigDecimal zbalance = new BigDecimal(0.00).setScale(2, BigDecimal.ROUND_HALF_UP);// 商家赠送余额
						BigDecimal amount = new BigDecimal(0.00).setScale(2, BigDecimal.ROUND_HALF_UP);// 商家钱包余额
						BigDecimal balance = new BigDecimal(0.00).setScale(2, BigDecimal.ROUND_HALF_UP);// 商家分账余额
						BigDecimal commision = new BigDecimal(0.00).setScale(2, BigDecimal.ROUND_HALF_UP);// 商家佣金余额
						BigDecimal sellerAmount = new BigDecimal(0.00).setScale(2, BigDecimal.ROUND_HALF_UP);// 商家收益金额
						Integer sellerId=redpacket.getSellerid();
						SellerAccount sellerAccount = new SellerAccount(); // 获取商家钱包信息
						sellerAccount.setSellerid(sellerId);
						sellerAccount.setType(1);
						Map<String, String> walletMap = walletService.getWalletBalance(sellerAccount);
						if (walletMap.isEmpty()) {
							new Response(ResponseCode.FAILURE, "未获取到商家钱包信息").write();
							return;
						} else {
							 zbalance = new BigDecimal(walletMap.get("zbalance")).setScale(2, BigDecimal.ROUND_HALF_UP);// 商家赠送余额
							 amount = new BigDecimal(walletMap.get("amount")).setScale(2, BigDecimal.ROUND_HALF_UP);// 商家钱包余额
							 balance = new BigDecimal(walletMap.get("balance")).setScale(2, BigDecimal.ROUND_HALF_UP);// 商家分账余额
							 commision = new BigDecimal(walletMap.get("commision")).setScale(2, BigDecimal.ROUND_HALF_UP);// 商家佣金余额
							 sellerAmount = new BigDecimal(walletMap.get("seller_amount")).setScale(2, BigDecimal.ROUND_HALF_UP);// 商家收益金额
						}
						
						BigDecimal totalAmount=redpacket.getTotalAmount().setScale(2, BigDecimal.ROUND_HALF_UP);//红包总金额
						Map<String, String> payParamMap = new HashMap<String, String>();
						
						// 所有传入金额必须格式化为2位小数
						if (payType == 1000000) {
							//扣款顺序： 赠送余额-->钱包余额-->分账余额-->佣金余额-->收益余额
							BigDecimal temp = totalAmount.subtract(zbalance);
							boolean flag=true; //是否足够支付标识
							if (temp.compareTo(ZERO) > 0) {
								temp = temp.subtract(amount);
							} else {
								zbalance = zbalance.add(temp);
								amount=ZERO;
								balance=ZERO;
								commision=ZERO;
								sellerAmount=ZERO;
								flag=false;
							}
	
							if (temp.compareTo(ZERO) > 0) {
								temp = temp.subtract(balance);
							}else if(flag){
								amount = amount.add(temp);
								balance=ZERO;
								commision=ZERO;
								sellerAmount=ZERO;
								flag=false;
							}
							
							if (temp.compareTo(ZERO) > 0) {
								temp = temp.subtract(commision);
							}else if(flag){
								balance = balance.add(temp);
								commision=ZERO;
								sellerAmount=ZERO;
								flag=false;
							}
							
							if (temp.compareTo(ZERO) > 0) {
								temp = temp.subtract(sellerAmount);
							}else if(flag){
								commision = commision.add(temp);
								sellerAmount=ZERO;
								flag=false;
							}
							
							if (temp.compareTo(ZERO) > 0) {
								new Response(ResponseCode.FAILURE, "余额不足").write();
								return;
							}else if(flag){
								sellerAmount = temp.add(sellerAmount);
								flag=false;
							}
							
							redpacket.setZbalance(zbalance);
							redpacket.setAmount(amount);
							redpacket.setBalance(balance);
							redpacket.setCommision(commision);
							redpacket.setProfit(sellerAmount);
							// 以下参数可选，值为0时可不传
							if (zbalance.compareTo(ZERO) > 0) {
								payParamMap.put("zbalance", zbalance + "");// 赠送支付金额
							}
							if (amount.compareTo(ZERO) > 0) {
								payParamMap.put("sellerAmount", amount + "");// 钱包支付余额
							}
							if (balance.compareTo(ZERO) > 0) {
								payParamMap.put("sellerBalance", balance + "");// 分账支付余额
							}
							if (commision.compareTo(ZERO) > 0) {
								payParamMap.put("commision", commision + "");// 佣金支付金额
							}
							if (sellerAmount.compareTo(ZERO) > 0) {
								payParamMap.put("profit", sellerAmount + "");// 收益支付金额
							}
						}
						
						Long currentVersion =redpacket.getVersionLock(); //乐观锁当前版本号
						redpacket.setVersionLock(currentVersion);
						redpacket.setPayType(payType);
						redpacket.setPayStatus(RedpacketConsts.PAYNO);
						
						int count=redpacketService.updateByPrimaryKeyAndVersionLock(redpacket);
						
						if(count>0){
							
							payParamMap.put("orderSn", redpacket.getOrderNo());// 订单编号
							payParamMap.put("userType", RedpacketConsts.USER_TYPE);// 用户类型
							payParamMap.put("uid", String.valueOf(sellerId));// 商家id
							payParamMap.put("amount", totalAmount.toString()); // 订单金额
							payParamMap.put("source", RedpacketConsts.SOURCE);// 订单来源，标识内部业务系统不同类型订单,001:业务系统-SAAS套餐订单;002:业务系统-SAAS软件订单;003:业务系统-积分商品订单；004:业务系统-物料订单；005:业务系统-直播鸟币购买订单 ； 006:业务系统-红包支付订单
							payParamMap.put("paymentType", payType + "");// 支付方式，1000001:支付宝SDK支付;1000003:微信SDK支付;1000013:微信公众号支付，1000000：钱包支付
							payParamMap.put("orderType", RedpacketConsts.ORDER_TYPE);// 订单类型，目前传固定值2
							String body=null;
							Integer redpacketType=redpacket.getRedpacketType();
							switch (redpacketType) {
								case 0:
									body = "分享引流红包";// 分享引流红包
									break;
								case 1:
									body = "限时到店红包";// 限时到店红包
									break;
								case 2:
									body = "消费满赠红包";// 消费满赠红包
									break;
								case 3:
									body = "推荐消费红包";// 推荐消费红包
									break;
								case 4:
									body = "普通抽奖红包";// 普通抽奖红包
									break;
								default:
									break;
							}
							payParamMap.put("body", body);// 订单描述
							payParamMap.put("subject", redpacket.getRedpacketName());// 订单标题
							
							String key = propertiesUtil.getValue("payRedpacketKey", "conf_pay.properties");
							logger.info("key:"+key);
							payParamMap.put("sign", Signature.sign(payParamMap, key));// 签名
							String url = propertiesUtil.getValue("payRedpacketUrl", "conf_pay.properties");
							logger.info("url："+url);
							String requestParam = "";
							for (Entry<String, String> entry : payParamMap.entrySet()) {
								requestParam += "&" + entry.getKey() + "=" + entry.getValue();
							}
							requestParam = requestParam.substring(1,requestParam.length());
							String payurl = url + "?" + requestParam;
							payurl=payurl.replaceAll(" ", "%20");
							logger.info("payurl:"+payurl);
							String result = HttpConnectionUtil.doPost(payurl, "");// 请求支付接口
							logger.info("result:"+result);
							if (StringUtils.isNotEmpty(result)) {
								resultMap = new HashMap<String, Object>();
								JSONObject json = JSONObject.parseObject(result);
								logger.info("json:"+json);
								if(json.containsKey("result")){
									Object data= json.get("result");
									resultMap.put("data", data);
								}
								logger.info("返回客户端数据:"+resultMap);
								new Response(ResponseCode.SUCCESS,json.getString("info"),resultMap).write();
							} else {
							    new Response(ResponseCode.FAILURE, "调用支付接口失败").write();
							}
						}else{
							new Response(ResponseCode.FAILURE,"订单版本异常[并发操作]").write();
						}
				    }else if(redpacket.getPayStatus().intValue()==RedpacketConsts.PAYYES){
						new Response(ResponseCode.FAILURE, "该红包订单已支付，请勿重复支付").write();
					}else if(redpacket.getPayStatus().intValue()==RedpacketConsts.REFUND){
						new Response(ResponseCode.FAILURE, "该红包订单已退款，无需再支付").write();
					}else{
				    	new Response(ResponseCode.FAILURE,"订单状态异常").write();
				    }
			    }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.getMessage(), e);
	    } finally {
			ThriftBuilder.close();
	    }
	}
}
