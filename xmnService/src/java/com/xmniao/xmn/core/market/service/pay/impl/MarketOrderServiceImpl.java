package com.xmniao.xmn.core.market.service.pay.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.ConstantDictionary;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.market.pay.ChangePayTypeRequest;
import com.xmniao.xmn.core.common.request.market.pay.CreateOrderRequest;
import com.xmniao.xmn.core.common.request.market.pay.DirectOrderRequest;
import com.xmniao.xmn.core.common.request.market.pay.GenerateOrderRequest;
import com.xmniao.xmn.core.common.request.market.pay.OrderInfoRequest;
import com.xmniao.xmn.core.common.request.market.pay.OrderNoRequest;
import com.xmniao.xmn.core.common.request.market.pay.OrderSettlementRequest;
import com.xmniao.xmn.core.common.request.market.pay.PayOrderRequest;
import com.xmniao.xmn.core.common.request.market.pay.SettlementRequest;
import com.xmniao.xmn.core.coupon.dao.CouponRelationDao;
import com.xmniao.xmn.core.coupon.entity.CouponRelation;
import com.xmniao.xmn.core.live.service.LiveGiftsInfoService;
import com.xmniao.xmn.core.market.common.MarketConsts;
import com.xmniao.xmn.core.market.dao.CouponDetailDao;
import com.xmniao.xmn.core.market.dao.FreshActivityCommonDao;
import com.xmniao.xmn.core.market.dao.FreshActivityGroupDao;
import com.xmniao.xmn.core.market.dao.FreshActivityProductDao;
import com.xmniao.xmn.core.market.dao.MarketBillFreshDao;
import com.xmniao.xmn.core.market.dao.MarketBillFreshSubDao;
import com.xmniao.xmn.core.market.dao.MarketReceivAddressDao;
import com.xmniao.xmn.core.market.dao.PostageFreeRuleDao;
import com.xmniao.xmn.core.market.dao.PostageRuleDao;
import com.xmniao.xmn.core.market.dao.ProductBillDao;
import com.xmniao.xmn.core.market.dao.ProductInfoDao;
import com.xmniao.xmn.core.market.dao.SaleGroupDao;
import com.xmniao.xmn.core.market.entity.cart.FreshActivityGroup;
import com.xmniao.xmn.core.market.entity.cart.SaleGroup;
import com.xmniao.xmn.core.market.entity.pay.BillFresh;
import com.xmniao.xmn.core.market.entity.pay.BillFreshSub;
import com.xmniao.xmn.core.market.entity.pay.CouponDetail;
import com.xmniao.xmn.core.market.entity.pay.FreshActivityCommon;
import com.xmniao.xmn.core.market.entity.pay.FreshActivityProduct;
import com.xmniao.xmn.core.market.entity.pay.OrderPriceList;
import com.xmniao.xmn.core.market.entity.pay.ProductBill;
import com.xmniao.xmn.core.market.entity.pay.ProductInfo;
import com.xmniao.xmn.core.market.entity.pay.ReceivingAddress;
import com.xmniao.xmn.core.market.service.pay.MarketOrderService;
import com.xmniao.xmn.core.thrift.business.java.MallOrderService;
import com.xmniao.xmn.core.util.ArithUtil;
import com.xmniao.xmn.core.util.DateUtil;
import com.xmniao.xmn.core.util.EnumUtil;
import com.xmniao.xmn.core.util.FrameUtil;
import com.xmniao.xmn.core.util.HttpConnectionUtil;
import com.xmniao.xmn.core.util.PropertiesUtil;
import com.xmniao.xmn.core.util.Signature;
import com.xmniao.xmn.core.util.ThriftBusinessUtil;
import com.xmniao.xmn.core.util.utilClass;
import com.xmniao.xmn.core.verification.dao.UrsDao;
import com.xmniao.xmn.core.verification.entity.Urs;

/**
 * 
* @projectName: xmnService 
* @ClassName: MarketOrderServiceImpl    
* @Description:积分订单   
* @author: liuzhihao   
* @date: 2016年12月22日 上午10:13:30
 */
@Service
public class MarketOrderServiceImpl implements MarketOrderService{
	
	 /**
     * 初始化日志类
     */
    private final Logger log = LoggerFactory.getLogger(MarketOrderServiceImpl.class);
	
	//父订单dao
	@Autowired
	private MarketBillFreshDao marketBillFreshDao;
	
	@Autowired
	private MarketBillFreshSubDao marketBillFreshSubDao;

	//用户收货地址dao
	@Autowired
	private MarketReceivAddressDao marketReceivAddressDao;
	
	//平台优惠卷发放表
	@Autowired
	private CouponDetailDao couponDetailDao;
	
	@Autowired
	private UrsDao ursDao;
	
	@Autowired
	private PostageFreeRuleDao postageFreeRuleDao;
	
	@Autowired
	private PostageRuleDao postageRuleDao;
	
	@Autowired
	private SaleGroupDao saleGroupDao;
	
	@Autowired
	private ProductBillDao productBillDao;
	
	@Autowired
	private ProductInfoDao productInfoDao;
	
	//商品活动dao
	@Autowired
	private FreshActivityCommonDao freshActivityCommonDao;
	
	@Autowired
	private FreshActivityGroupDao freshActivityGroupDao;
	
	@Autowired
	private FreshActivityProductDao freshActivityProductDao;
	
	//redis
	@Autowired
	private SessionTokenService sessionTokenService;
	
	//获取用户钱包余额service
	@Autowired
	private LiveGiftsInfoService liveGiftsInfoService;
	
	@Autowired
	private ThriftBusinessUtil thriftBusinessUtil;
	
	//获取配置文件
	@Autowired
	private PropertiesUtil propertiesUtil;
	
	@Autowired
	private CouponRelationDao couponRelationDao;
	
	@Autowired
	private String fileUrl;
	
	private MapResponse response = null;

	/**
	 * 创建订单
	 */
	@Override
	public Object createOrder(CreateOrderRequest createOrderRequest){
		Map<Object,Object> result = new HashMap<Object,Object>();
		
		//获取用户ID
		String uid = ObjectUtils.toString(sessionTokenService.getStringForValue(createOrderRequest.getSessiontoken()));
		//客户端版本
		String appversion = createOrderRequest.getAppversion();
		
		//用户购买的商品ID集合
		List<String> carIds = createOrderRequest.getCarts();
		
		if(carIds.isEmpty()){
			return new BaseResponse(ResponseCode.FAILURE,"你还没有选择购买的商品");
		}
		
		//获取用户购买的商铺信息
		List<ProductInfo> products = getProductInfoList(uid, carIds,false,createOrderRequest.getBuyType());
		
		if(products == null || products.isEmpty()){
			return new BaseResponse(ResponseCode.FAILURE,"购物车没有商品,去商场逛逛吧");
		}
		
		//判断库存、活动是否过期
		String flag = isActivityStock(products,uid);
		
		if(StringUtils.isNotEmpty(flag)){
			return new BaseResponse(ResponseCode.IS_INTEGRAL_STOCK,flag);
		}
		
		// 更新库存
		updateProductStock(products);
		
		//通过收货地址ID查询用户收货地址详情
		ReceivingAddress ra = marketReceivAddressDao.selectByPrimaryKey(createOrderRequest.getRid());
			
		Integer areaid = ra.getCityid();//用户收货地址区域ID
		
		String areaname = ra.getCity();//城市名称
		
		//获取订单价格
		OrderPriceList opl = getOrderPriceList(products,areaid,uid,createOrderRequest.getCdid(),createOrderRequest.getIsUserCoupon(),areaname);
		
		//运费
		Double postPrice = opl.getTotalPostPrice().doubleValue();
		
		//优惠卷面额
		Double denomination = opl.getDenomination().doubleValue();
		
		//插入订单
		Long bid = 
			insertBillFresh(ra, opl, uid, createOrderRequest.getCdid(), appversion, denomination, postPrice,0, createOrderRequest.getAppSource());
		
		if(bid == null){
			//订单创建异常的时候回滚之前库存的操作
			for(ProductInfo product : products){
				saleGroupDao.updateExceptionStock(product.getCodeid().intValue(), product.getAttrIds(), product.getNum());
			}
			return new BaseResponse(ResponseCode.FAILURE,"创建订单异常");
		}
			
		//插入订单商品详情表
		insertProductBill(bid, products,opl.getTids(),areaid,areaname);
			
		//从原来集合干掉购买的商品集合
		products = getProductInfoList(uid, carIds, true,createOrderRequest.getBuyType());
		
		//把新的集合 替换redis中的老集合
		if(createOrderRequest.getBuyType() != 1){
			//购物车购买的时候替换缓存
			sessionTokenService.setStringForValue(MarketConsts.CART_INFO_HEADER+uid, JSON.toJSONString(products), 0, TimeUnit.DAYS);
		}
		//锁定优惠卷
		if(createOrderRequest.getCdid() != null){
			//应收金额=购买商品总价+运费+总积分
			Double payPrice = opl.getTotalPrice().doubleValue()+postPrice+opl.getTotalIntegral().doubleValue();
			
			CouponDetail cd = couponDetailDao.selectByPrimaryKey(createOrderRequest.getCdid());
			
			cd.setUserStatus((byte)1);//使用状态，0未使用，1锁定，2已使用 3 已过期并退款
			
			couponDetailDao.updateByPrimaryKeySelective(cd);
			
			//比较是否能够抵扣金额
			if(denomination>=payPrice){
				//封装支付接口参数
				Map<String,String> paymap = new HashMap<String,String>();
				
				DecimalFormat df = new DecimalFormat("0.00");
				//更新订单状态
				paymap.put("bid", bid.toString());
				paymap.put("status", "1");//支付成功
				paymap.put("payType", "1000011");//支付类型 优惠卷支付
				paymap.put("payid", "0");
				paymap.put("orderAmount", df.format(opl.getTotalPrice()));//支付金额
				paymap.put("integral", df.format(opl.getTotalIntegral()));//支付积分
				paymap.put("freight", df.format(postPrice));//运费
				paymap.put("cuser", df.format(denomination));//优惠卷面额
				paymap.put("payment", "0.00");
				paymap.put("balance", "0.00");
				paymap.put("zbalance", "0.00");
				paymap.put("commision", "0.00");
				paymap.put("birdCoin", "0.00");
				
				try {
					int isSuccess = notifyForPayComplete(paymap);
					if(isSuccess == 1){
						result.put("payType", 0);//支付类型  0优惠卷支付 1非用优惠卷支付
						result.put("orderNo", bid);
						response = new MapResponse(ResponseCode.SUCCESS,"支付成功");
						response.setResponse(result);
						return response;
					}
				} catch (Exception e) {
					e.printStackTrace();
					return new BaseResponse(ResponseCode.FAILURE,"支付成功,修改订单状态异常");
				}
				return new BaseResponse(ResponseCode.FAILURE,"支付成功,修改订单状态失败");
			}
		}
		result.put("orderNo", bid);//订单号
		result.put("payType", 1);//支付类型 0 优惠卷支付 1 非优惠卷支付
		response = new MapResponse(ResponseCode.SUCCESS,"订单创建成功");
		response.setResponse(result);
		return response;
	}
	
	
	/**
	 * 结算
	 * @param baseRequest
	 * @return
	 */
	@Override
	public Object settlement(SettlementRequest settlementRequest){
		
		//获取用户ID
		String uid = ObjectUtils.toString(sessionTokenService.getStringForValue(settlementRequest.getSessiontoken()));
		
		/**
		 * 1.判断购物车中是否存在商品,如果购物车内有商品则继续,如果没有商品则提示用户去添加商品
		 */
		List<ProductInfo> products = 
			getProductInfoList(uid, settlementRequest.getCarts(), false,settlementRequest.getBuyType());
		
		if(products.isEmpty()){
			return new BaseResponse(ResponseCode.FAILURE,"购物车没有商品,去商场逛逛吧");
		}
		
		/**
		 * 获取用户购买商品订单信息
		 */
		Map<Object,Object> map = getBuyProductOrderInfo(uid, products, settlementRequest.getRid(), settlementRequest.getCdid(), settlementRequest.getIsUserCoupon(),false);
		
		String state = ObjectUtils.toString(map.get("state"));
		String info = ObjectUtils.toString(map.get("info"));
		
		map.remove("state");
		map.remove("info");
		
		response = new MapResponse(Integer.parseInt(state),info);
		response.setResponse(map);
		return response;
		
	}
	
	/**
	 * 支付
	 */
	
	@SuppressWarnings("unchecked")
	@Override
	public Object payOrder(PayOrderRequest payOrderRequest) {
		Map<Object,Object> result = new HashMap<Object,Object>();
		//获取用户ID
		String uid = ObjectUtils.toString(sessionTokenService.getStringForValue(payOrderRequest.getSessiontoken()));
		
		//通过订单号查询订单详情
		BillFresh bf = new BillFresh();
		
		try{
			bf = marketBillFreshDao.selectByPrimaryKey(payOrderRequest.getOrderNo().longValue());
		}catch(Exception e){
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE,"查询订单异常");
		}
		
		if(bf == null){
			return new BaseResponse(ResponseCode.FAILURE,"订单不存在");
		}
		
		if(bf.getStatus() != 0){
			
			return new BaseResponse(ResponseCode.FAILURE,"订单异常,请重新下单");
		}
		
		//获取用户的消费总金额
		Double totalPrice = bf.getMoney().doubleValue();
		//获取用户消费的总积分
		Double totalIntegral = bf.getIntegral();
		//优惠卷金额
		Double danomination = bf.getCuser().doubleValue();//优惠卷面额
		//运费
		Double postPrice = bf.getFreight().doubleValue();
		
		//调用支付接口
		//封装支付接口参数
		Map<String,String> paymap = new HashMap<String,String>();
		
		paymap.put("orderId", payOrderRequest.getOrderNo().toString());//订单号
		paymap.put("orderAmount", new BigDecimal(bf.getMoney().doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());//支付总金额
		paymap.put("integral", new BigDecimal(totalIntegral.doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());//积分总额
		paymap.put("userId", uid);//用户ID
		paymap.put("orderName", "超市购物订单");//订单名称
		paymap.put("orderDetail", "超市购物订单");//订单详情
//		paymap.put("returnUrl", "");//回调地址
		paymap.put("expenses", new BigDecimal(postPrice.doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());//运费
		paymap.put("preferential", new BigDecimal(danomination.doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());//优惠卷面额
		paymap.put("isLiveCoin", "false");
		paymap.put("isBalance", "false");
		
		boolean isPayType = false;
		//鸟币支付
		if(payOrderRequest.getBirdType() == 1){
			paymap.put("isLiveCoin", "true");
			try{
				Map<String,Object> birdmap = liveGiftsInfoService.getLiveWalletBlance(uid);
				if(birdmap == null || birdmap.isEmpty()){
					return new BaseResponse(ResponseCode.FAILURE,"支付失败,用户没有钱包");
				}
				
				Double zbalanceCoin = Double.parseDouble(birdmap.get("zbalanceCoin").toString());//用户可用鸟币余额
				
				Double payPrice = ArithUtil.sub(ArithUtil.add(totalPrice, postPrice), danomination);
				
				if(zbalanceCoin.compareTo(payPrice)<0){
					return new BaseResponse(ResponseCode.FAILURE,"支付失败,可用余额不足");
				}
				
			}catch(Exception e){
				e.printStackTrace();
				log.info("查询用户鸟币钱包异常");
				return new BaseResponse(ResponseCode.FAILURE,"支付失败,获取用户鸟币异常");
			}
		}
		//余额支付
		if(payOrderRequest.getAmountType() == 1){
			try{
				Map<String, String> amountmap = liveGiftsInfoService.getXmnWalletBlance(uid);
				if(amountmap != null && !amountmap.isEmpty()){
					paymap.put("isBalance", "true");
				}else{
					isPayType = true;
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		//微信支付
		if(payOrderRequest.getWxType() == 1){
			paymap.put("payType", "1000003");
			isPayType = false;
		}
		
		//支付宝支付
		if(payOrderRequest.getZfbType() == 1){
			paymap.put("payType", "1000001");
			isPayType = false;
		}
		
		//支付宝支付(鸟人科技)
		if(payOrderRequest.getZfbType() == 2){
			paymap.put("payType", "1000022");
			isPayType = false;
		}
		
		if(isPayType){
			return new BaseResponse(ResponseCode.FAILURE,"选择支付方式错误，请重新支付");
		}
		
		
		//签名key
		String key = "";
		String url = "";
			try{
				key = propertiesUtil.getValue("integralPayKey", "conf_integral_pay.properties");
				url = propertiesUtil.getValue("integralpayUrl", "conf_integral_pay.properties");
			}catch(Exception e){
				e.printStackTrace();
				return new BaseResponse(ResponseCode.FAILURE,"支付key获取异常");
			}
			
		
		//签名
		String sign = Signature.sign(paymap, key); 
		
		log.info("签名:"+sign+"==="+paymap);
		
		paymap.put("sign_mall", sign);
		
		String payurl = HttpConnectionUtil.getUrl(paymap, url);
		
		log.info("支付地址:"+payurl);
		
		String resultStr = HttpConnectionUtil.doPost(payurl, "");
		
		log.info("支付结果:"+resultStr);
		
		if (StringUtils.isNotEmpty(resultStr)) {
            JSONObject json = JSONObject.parseObject(resultStr);
            log.info("格式化请求支付接口返回json:"+json);
            result = (Map<Object, Object>) JSON.parse(json.toString());
            if(result != null && !result.isEmpty()){
            	String state = ObjectUtils.toString(result.get("state"));
            	if(state.equals("200")){
            		response = new MapResponse(ResponseCode.SUCCESS,"支付成功"); 
            		response.setResponse(result);            		
            		return response;
            	}else{
            		return new BaseResponse(ResponseCode.FAILURE,result.get("msg").toString());
            	}
            }
        }
		return new BaseResponse(ResponseCode.FAILURE,"支付异常");
		
	}
	
	/**
	 * 取消订单
	 * @param orderInfoRequest
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Object cancelOrder(OrderNoRequest orderNoRequest) {
		
		//订单ID
		Long bid = orderNoRequest.getBid();
		
		//通过订单id查询订单信息
		
		BillFresh bf = marketBillFreshDao.selectByPrimaryKey(bid);
		
		if(bf != null && bf.getStatus() != 2){
			
			String t1 = DateUtil.format(bf.getDdate());//下单支付时间毫秒数
			String t2 = DateUtil.format(new Date());//当前系统毫秒数
			if(t2.compareTo(t1) < 0){
				return new BaseResponse(ResponseCode.SUCCESS,"下单十分钟后才可以取消订单!");
			}
			
			Map<String,String> map = new HashMap<String,String>();
			map.put("orderNumber", bid.toString());
			map.put("amount", new BigDecimal(bf.getMoney().doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
			map.put("randomNumber", utilClass.RandomNum()+"");//四位随机数
			map.put("integral", String.valueOf(bf.getIntegral().doubleValue()));//消费积分
			map.put("expenses", String.valueOf(bf.getFreight()));
			map.put("preferential", String.valueOf(bf.getCuser()));//优惠卷
			
			//签名key
			String key = "";
			String url = "";
			try{
				key = propertiesUtil.getValue("integralPayKey", "conf_integral_pay.properties");
				url = propertiesUtil.getValue("cancelUrl", "conf_integral_pay.properties");
			}catch(Exception e){
				e.printStackTrace();
				return new BaseResponse(ResponseCode.FAILURE,"支付key获取异常");
			}
			String sign = Signature.sign(map, key); 
			map.put("sign", sign);
			
			String cancelurl = HttpConnectionUtil.getUrl(map, url);
			
			log.info("取消订单地址:"+cancelurl);
			
			String resultStr = HttpConnectionUtil.doPost(cancelurl, "");
			
			log.info("取消订单结果:"+resultStr);
			
			if (StringUtils.isNotEmpty(resultStr)) {
				map.clear();
	            JSONObject json = JSONObject.parseObject(resultStr);
	            log.info("格式化请求支付接口返回json:"+json);
	            map = (Map<String,String>) JSON.parse(json.toString());
	            if(map != null && !map.isEmpty()){
	            	String state = ObjectUtils.toString(map.get("state"));
	            	if(state.equals("200")){
	            		return new BaseResponse(ResponseCode.SUCCESS,"取消订单成功");
	            	}else{
	            		return new BaseResponse(ResponseCode.FAILURE,"取消订单失败:"+"["+map.get("msg").toString()+"]");
	            	}
	            }
	        }
		}
		return new BaseResponse(ResponseCode.FAILURE,"取消订单失败");
	}

	/**
	 * 提醒发货
	 */
	@Override
	public Object remindExpress(OrderInfoRequest orderInfoRequest) {
		//订单ID
		Long bid = orderInfoRequest.getBid();
		
		//用户Id
		String uid = ObjectUtils.toString(sessionTokenService.getStringForValue(orderInfoRequest.getSessiontoken()));
		//查子订单
		
		try{
			BillFreshSub bfs = marketBillFreshSubDao.findOneSubOrderSnAanUid(bid, uid);
			if(bfs != null){
				
				if(bfs.getStatus() == 0){
					bfs.setRemindExpress(1);//提醒商家
					
					int result = marketBillFreshSubDao.updateByPrimaryKeySelective(bfs);
					
					if(result != 0){
						return new BaseResponse(ResponseCode.SUCCESS,"提醒发货成功,客服将会在收到您的提醒后,尽快进行发货操作");
					}
				}
			}
			return new BaseResponse(ResponseCode.FAILURE,"提醒失败");
		}catch(Exception e){
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE,"提醒失败");
		}
	}
	
	/**
	 * 判断用户是否存在收货地址
	 * @param uid
	 * @return
	 */
	protected ReceivingAddress isAddress(Integer uid) {
		
		ReceivingAddress ra = null;
		//查询用户收货地址
		Map<Object,Object> map = new HashMap<Object,Object>();
		map.put("uid", uid);
		map.put("dstatus", 0);//收货地址状态 0 正常 1删除
		map.put("isDefault", 1);//默认地址
		List<ReceivingAddress> ras = new ArrayList<ReceivingAddress>();
		try{
			
			ras = marketReceivAddressDao.findAllIsNotDefaultByUid(map);//查询用户默认地址
			if(!ras.isEmpty()){
				ra = ras.get(0);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		if(ras.isEmpty()){
			map.put("isDefault", 0);//默认地址
			//查询用户非默认地址
			ras = marketReceivAddressDao.findAllIsNotDefaultByUid(map);
			
			if(!ras.isEmpty()){
				ra = ras.get(0);
			}
		}
		return ra;
	}
	
	
	private int notifyForPayComplete(Map<String,String> map) throws Exception {
		int result = 0;
		try {
			TMultiplexedProtocol tMultiplexedProtocol = thriftBusinessUtil.getProtocol("MallOrderService");
			MallOrderService.Client  client= new MallOrderService.Client(tMultiplexedProtocol); 
			thriftBusinessUtil.openTransport();
			Map<String,String> successmap = client.notifyForPayComplete(map);
			String isSuccess = successmap.get("is_success").toString();
			if(isSuccess.equals("T")){
				result = 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("支付成功,修改订单状态异常");
		}finally{
			thriftBusinessUtil.coloseTransport();
		}
		return result;
	}
	
	/**
	 * 查询购买商品库存
	 * @param codeId
	 * @param activityId
	 * @return
	 */
	private String isActivityStock(List<ProductInfo> products,String uid) {
		String flag = "";
		
		if(products != null && !products.isEmpty()){
			
			int stock = 0;//库存
			
			for(ProductInfo product : products){
				//商品唯一标识
				Long codeId = product.getCodeid();
				//活动ID
				Integer activityId = product.getActivityId();
				//商品规格组合ID
				String attrIds = product.getAttrIds();
				//购买数量
				Integer buys = product.getNum();
				
				if(activityId != null){
					//商品参与了活动
					
					//通过活动ID获取活动信息
					FreshActivityCommon freshActivityCommon = freshActivityCommonDao.selectByPrimaryKey(activityId);
					
					if(freshActivityCommon == null){
						//活动已删除或者活动不存在
						flag = "订单中"+"["+product.getPname()+"]"+"的库存不足,请编辑后重新提交";
						return flag;
					}
					
					//判断活动是否过期
					if(freshActivityCommon.getStatus() != 0 || freshActivityCommon.getEndDate().compareTo(DateUtil.now()) < 0){
						//活动过期
						flag = "由于活动结束，订单中的"+"["+product.getPname()+"]价格变化，请编辑后重新提交";
						return flag;
					}
					
					//通过活动ID、商品唯一标识、规格查询活动商品的库存
					FreshActivityGroup freshActivityGroup = 
						freshActivityGroupDao.selectActivityStockByPvids(activityId, codeId,attrIds);
					
					if(freshActivityGroup == null){
						//活动商品规格不存在
						flag = "订单中"+"["+product.getPname()+"]"+"的库存不足,请编辑后重新提交";
						return flag;
					}
					
					stock = freshActivityGroup.getStock();
					
					if(buys>stock){
						flag = "订单中"+"["+product.getPname()+"]"+"的库存不足,请编辑后重新提交";
						return flag;
					}
					
					//一元夺宝活动中的商品是否被同一用户重复购买
					Integer limitOrder = freshActivityCommon.getOrderLimit();
					
					//查询用户订单
					Integer wareNum = 
						productBillDao.sumProductCountsByActivityIdAndCodeId(String.valueOf(activityId), String.valueOf(codeId),String.valueOf(uid));
				
					if(limitOrder > 0){
						//限购
						wareNum += product.getNum();
						
						if(wareNum.compareTo(limitOrder) > 0){
							
							flag = "非常抱歉!您购买的"+"["+product.getPname()+"]"+"已超过购买数量!";
							return flag;
						}
					}
				
				}else{
					//判断非活动库存
					
					//通过商品唯一标识查询商品信息
					ProductInfo productInfo = productInfoDao.selectByCodeId(codeId.intValue());
					
					if(productInfo == null){
						//商品已删除，或者不存在了
						flag = "订单中"+"["+product.getPname()+"]"+"的库存不足,请编辑后重新提交";
						return flag;
					}
					
					//判断商铺状态
					
					int pstatus = productInfo.getPstatus();//商品状态
					
					if(pstatus != 1){
						switch(pstatus){
						case 2://已售罄 
							flag = "订单中"+"["+product.getPname()+"]"+"的库存不足,请编辑后重新提交";
							return flag;
						case 3://已下线
							flag = "订单中"+"["+product.getPname()+"]"+"的库存不足,请编辑后重新提交";
							return flag;
						default:
							break;
						}
					}
					
					//判断商铺库存
					
					//判断用户购买的商品的库存
					stock = saleGroupDao.selectStockByCoodeIdAndPvIds(codeId.toString(), attrIds);
					
					if(buys>stock){
						flag = "订单中"+"["+product.getPname()+"]"+"的库存不足,请编辑后重新提交";
						return flag;
					}
				}
			}
		}
		return flag;
	}

	/**
	 * 获取用户购买的商品信息
	 * @param uid
	 * @return
	 */
	public List<ProductInfo> getProductInfoList(String uid,List<String> cartIds,boolean isDelete,Integer buyType) {
		
		List<ProductInfo> result = new ArrayList<ProductInfo>();
		
		//待删除的购买商品集合
		List<ProductInfo> deleteProducts = new ArrayList<ProductInfo>();
		//在缓存获取用户购买的商品所用的积分总数
		String productStr = "";
		
		if(buyType == 1){
			productStr = ObjectUtils.toString(sessionTokenService.getStringForValue(MarketConsts.DIRECT_ORDER+uid));//直接购买
			
		}else{
			productStr = ObjectUtils.toString(sessionTokenService.getStringForValue(MarketConsts.CART_INFO_HEADER+uid));//购物车购买
		}
		
		//判断缓存中是否存在数据
		if(StringUtils.isNotEmpty(productStr)){
			//JSON对象转换成集合
			List<ProductInfo> products = JSON.parseArray(productStr, ProductInfo.class);
			
			for(String cartid : cartIds){
				
				for(ProductInfo product : products){
					
					if(cartid.equals(product.getCartId())){
						
						if(isDelete){
							
							deleteProducts.add(product);//待删的缓存商品
							
						}else{
							
							//用户购买商品唯一标识
							Long codeId = product.getCodeid();
							//用户购买商品活动ID
							Integer activityId = product.getActivityId();
							//用户购买商品规格ID组合
							String attrIds = product.getAttrIds();
							//通过商品ID查询商品详情
							ProductInfo productInfo = productInfoDao.selectByCodeId(codeId.intValue());
							
							if(productInfo == null){
								return result;
							}
							//查询用户购买商品的加价
							Double amount = 0.0;
							
							if(activityId == null){
								
								//非活动商品
								SaleGroup saleGroup = saleGroupDao.selectByAttr(codeId.intValue(), attrIds);
								if(saleGroup != null){
									amount = saleGroup.getAmount().doubleValue();//非活动加价
								}
							}else{
								
								//2017-01-15 修订bug，忘记补活动售价和活动积分
								FreshActivityProduct fap = 
									freshActivityProductDao.findByActivityIdAndCodeId(activityId, codeId);
								if(fap != null){
									productInfo.setCash(fap.getSalePrice());//商品活动售价
									productInfo.setIntegral(fap.getSellIntegral());//活动时需积分
									//通过活动ID、商品唯一标识、规格查询活动商品的库存
									FreshActivityGroup freshActivityGroup = 
										freshActivityGroupDao.selectActivityStockByPvids(activityId, codeId,attrIds);
									if(freshActivityGroup != null){
										amount = freshActivityGroup.getAmount().doubleValue();//活动加价
									}
								}
								
							}
							
							productInfo.setNum(product.getNum());//从缓存中获取用户购买商品的个数
							productInfo.setAttrIds(product.getAttrIds());//从缓存中获取用户购买商品的规格ID组合
							productInfo.setAttrVals(product.getAttrVals());//从缓存中获取用户购买商品的规格值组合
							productInfo.setCartId(product.getCartId());//从缓存中获取用户购买商品的ID结合
							productInfo.setAmount(BigDecimal.valueOf(amount));//商品加价
							productInfo.setActivityId(activityId);
							result.add(productInfo);
						
						}
					}
				}
			}
			
			//为了之后的操作，删除缓存中的购买商品
			if(isDelete){
				
				products.removeAll(deleteProducts);
				
				return products;
			}
			
		}
		
		return result;
	}

	/**
	 * 更新库存
	 * @return
	 */
	private void updateProductStock(List<ProductInfo> products) {
		
		for(ProductInfo product : products){
			//商品唯一标识
			Long codeId = product.getCodeid();
			//活动ID
			Integer activityId = product.getActivityId();
			//商品规格组合ID
			String attrIds = product.getAttrIds();
			//购买数量
			Integer buys = product.getNum();
			
			int type = 0;
			
			if(activityId != null){
				
				type = 1;
				
			}
			
			switch(type){
				case 0://扣除商品库存库存	
					saleGroupDao.updateStock(codeId.intValue(), attrIds, buys);
					//扣除总库存
					productInfoDao.updateProductInfoStore(codeId.toString(), buys);
					break;
				case 1://扣除活动商品库存
					freshActivityGroupDao.updateActivityProductStock(activityId, codeId, attrIds, buys);
					//更新活动总库存
					freshActivityProductDao.updateFreshActivityProductStore(codeId.toString(), activityId, buys);
					break;
				default:
					break;
			}
		}
	}

	
	/**
	 * 创建订单，插入订单表信息
	 */
	public Long insertBillFresh(
		ReceivingAddress ra,OrderPriceList opl,String uid,Integer cdid,String appversion,Double denomination,Double postPrice,Integer sendUid,String appSource) {
		//通过用户ID查询用户详情
		Urs urs = ursDao.queryUrsByUid(Integer.parseInt(uid));
		//创建父订单,存在收货地址才 创建订单
		BillFresh bf = new BillFresh();
		bf.setUid(Integer.parseInt(uid));//用户ID
		bf.setNname(ObjectUtils.toString(urs.getNname()));//用户昵称
		bf.setPhoneid(ObjectUtils.toString(urs.getPhone()));//用户电话号码
		bf.setWarenum(opl.getTotalNum());//购买商品总数
		bf.setMoney(opl.getTotalPrice());//消费总金额,减去优惠卷之后的总价格
		bf.setIntegral(opl.getTotalIntegral().doubleValue());//消费总积分
		bf.setExpress("快递");//配送方式
		String address = ra.getProvince()+ra.getCity()+ra.getAreaname()+ra.getAddress();
		bf.setAddress(address);//配送地址
		bf.setTel(ObjectUtils.toString(ra.getPhoneid()));//联系电话
		bf.setMobile(ObjectUtils.toString(ra.getPhoneid()));//联系手机，配送时的联系方式
		bf.setStatus(0);//待支付
		bf.setWstatus(0);//未发货
		bf.setSdate(DateUtil.now());//下单时间
		bf.setHstatus(0);
		bf.setAppSourceStatus(EnumUtil.getEnumCode(ConstantDictionary.AppSourceState.class, appSource));//app来源
		//客户端类型
		if(appversion.toUpperCase().contains("IOS")){
			bf.setPhoneType((byte)2);//IOS端
		}else{
			bf.setPhoneType((byte)1);//Android端
		}
		bf.setDstatus(0);//正常
		bf.setUsername(ObjectUtils.toString(ra.getUsername()));//收货人姓名
		bf.setFreight(BigDecimal.valueOf(postPrice));//运费
		bf.setCuser(BigDecimal.valueOf(denomination));//优惠卷面额
		if (sendUid>0) {
			bf.setSendUid(sendUid);
		}
		//优惠卷ID
		if(cdid != null){
			bf.setCdid(cdid);
		}
		bf.setCityid(ra.getCityid());//城市
		try{
			marketBillFreshDao.insertSelective(bf);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return bf.getBid();
	}
	
	/**
	 * 创建订单，插入订单商品详情表
	 * @param bid
	 * @param products
	 */
	public void insertProductBill(Long bid,List<ProductInfo> products,Set<Integer> tids,Integer areaid,String areaname) {
		
		//通过订单ID查询订单详情
		
		BillFresh bf = marketBillFreshDao.selectByPrimaryKey(bid);
		
		for(Integer id : tids){
			
			Double totalPrice = 0.0;//商品总价格
			
			Double totalWeight = 0.0;//商品总重量
			
			Integer tid = null;//运费模板
			
			boolean isFree = false;//包邮
			
			boolean isExceptId = false;//是否专用水的
			
			Double postPrice = 0.0;//运费
			
			Integer billId = null;//商品唯一标识
			
			for(ProductInfo product : products){
				
				if(id == product.getExptid()){
					
					Double price = 
						ArithUtil.mul(ArithUtil.add(product.getCash().doubleValue(), product.getAmount().doubleValue()), product.getNum().doubleValue());
					
					Double weight = 
						ArithUtil.mul(product.getExpweight(), product.getNum().doubleValue());
					
					ProductBill pb = new ProductBill();
					pb.setBid(bid);//父订单ID
					pb.setCodeid(product.getCodeid().intValue());//商品标识ID
					pb.setWarenum(product.getNum());//购买商品数量
					pb.setActivityid(product.getActivityId());//活动ID
					pb.setPname(product.getPname());//商品名称
					pb.setRdate(DateUtil.now());//创建时间
					//商品现金价格
					Double tPrice = ArithUtil.add(product.getCash().doubleValue(), product.getAmount().doubleValue());
					pb.setPrice(BigDecimal.valueOf(ArithUtil.add(tPrice, product.getIntegral().doubleValue())));
					pb.setGoodsname(product.getGoodsname());//商品促销活动名称
					pb.setBreviary(product.getBreviary());//商品缩略图
					pb.setSuttle(product.getSuttle());//商品净重
					pb.setSupplierId(product.getSupplierid()==null?0:product.getSupplierid().longValue());//供应商ID
					pb.setIntegral(product.getIntegral());//最高积分抵扣单价
					pb.setPurchaseprice(product.getPurchaseprice());//采购价
					pb.setAttrStr(product.getAttrVals());//规格信息
					pb.setAttrids(product.getAttrIds());
					pb.setAttrAmount(product.getAmount());//规格加价
					pb.setExpweight(product.getExpweight());
					pb.setExptid(product.getExptid());
					//插入商铺消费信息
					productBillDao.insertSelective(pb);
					
					totalPrice += price;
					totalWeight += weight;
					
					Integer supplierId = product.getSupplierid();//供应商ID
					if(supplierId != 27){
						isExceptId = true;
					}
					
					tid = product.getExptid();//运费模板ID
					
					billId = pb.getId();
				}
			}
			
			if(isExceptId){
			
				isFree = isFree(tid, areaid, totalWeight, totalPrice);
				
				if(isFree){
					postPrice = getPostPrice(tid, totalWeight,areaid,areaname);
				}
			}else{
				Integer cdid = bf.getCdid();
				if(cdid != null){
					
					Map<Object,Object> coupon = couponDetailDao.findOneCoupon(bf.getUid(),bf.getCdid());
					Integer cid = 0;//优惠卷ID
					Double denomination = 0.0;
					if(coupon != null && !coupon.isEmpty()){
						cid = Integer.parseInt(coupon.get("cid").toString());
						denomination = Double.parseDouble(coupon.get("denomination").toString());
					}
					
					try{
						String waterCoupons = propertiesUtil.getValue("waterCoupons", "conf_integral_pay.properties");
						
						if(StringUtils.isNotEmpty(waterCoupons)){
							
							JSONArray json = JSONObject.parseArray(waterCoupons);
							
							for(int i=0; i<json.size();i++){
							
								JSONObject object = json.getJSONObject(i);
								
								Integer waterCouponCid = Integer.parseInt(object.getString("cid"));//巴马水优惠卷ID
								
								Double waterCouponDenomination = Double.parseDouble(object.getString("denomination"));//巴马水优惠卷面额
								
								Double waterPostPrice = Double.parseDouble(object.getString("postPrice"));//运费
								
								if(cid.compareTo(waterCouponCid) ==0 || denomination.compareTo(waterCouponDenomination) == 0){
									//使用巴马水优惠卷产生的运费
									postPrice = waterPostPrice;
								}
							}
							
						}
						
					}catch(Exception e){
						e.printStackTrace();
					}
				}else{
					
					isFree = isFree(tid, areaid, totalWeight, totalPrice);
					
					if(isFree){
						postPrice = getPostPrice(tid, totalWeight,areaid,areaname);
					}
				}
			}
			
			ProductBill productBill = productBillDao.selectByPrimaryKey(billId);
			
			productBill.setFreight(BigDecimal.valueOf(postPrice));//商品运费
			
			productBillDao.updateByPrimaryKeySelective(productBill);
		}
	}
	
	/**
	 * 获取运费模板
	 * @param tids
	 * @param areaid
	 * @param totalPrice
	 * @param totalWeight
	 * @return
	 */


	public boolean isFree(Integer tid,Integer areaId,Double totalWeight,Double totalPrice) {
		
		boolean isFree = true;
		
		Map<Object,Object> expmap = new HashMap<Object,Object>();
		
		expmap.put("amount", totalPrice);
		expmap.put("weight", totalWeight);
		expmap.put("area", areaId);
		expmap.put("tid", tid);
		
		//查询商品的快递信息
		Map<Object,Object> isfreemap = postageFreeRuleDao.findOneIsFree(expmap);
		
		if(isfreemap != null && !isfreemap.isEmpty()){
			isFree = false;
		}else{
			isFree = true;
		}
		return isFree;
	}
	
	/**
	 * 获取运费
	 * @param tid
	 * @param totalWeight
	 * @return
	 */

	public Double getPostPrice(Integer tid,Double totalWeight,Integer areaid,String areaname) {
		//计算运费
		Map<Object,Object> postpricemap = new HashMap<Object,Object>();
		postpricemap.put("tid", tid);
		postpricemap.put("weight",Math.ceil(totalWeight));
		postpricemap.put("areaid", areaid);
		postpricemap.put("areaname", areaname);
		Double postPrice = 0.0;
		try{
			postPrice = postageRuleDao.getPostPriceByTid(postpricemap);
			
			if(postPrice == null || postPrice == 0){
				postPrice = postageRuleDao.querySpecifyTemplate(postpricemap);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return postPrice;
	}

	
	/**
	 * 获取订单价格对象
	 * @param products
	 * @return
	 */
	private OrderPriceList getOrderPriceList(List<ProductInfo> products,Integer areaid,String uid,Integer cdid,Integer isUserCoupon,String areaname) {
		OrderPriceList opl = new OrderPriceList();
		//用户购物车内商品总价格 
		Double totalPrice=0.0;
		//用户购物车内商品花费总积分
		Double totalIntegral=0.0;
		//用户购物车内商品总重量
		Double totalWeight=0.0;
		//用户购买商品总数量
		Integer totalNum = 0;
		//优惠卷面额
		Double denomination = 0.0;
		//是否可用优惠卷
		boolean isCoupon = false;
		//运费
		Double totalPostPrice=0.0;
		
		//优惠卷名称
		String waterName="";
		
		Set<Integer> tids = new HashSet<Integer>();//运费模板ID
		
		for(ProductInfo product : products){
			
			//商品价格 = 商品单价 * 商品个数
			Double price = product.getCash().doubleValue()*product.getNum();
			//商铺积分= 商品单积分 * 商品个数
			Double integral = product.getIntegral().doubleValue()*product.getNum();
			//加价
			Double amount = product.getAmount().doubleValue()*product.getNum();
			//商品重量 = 商品单重量 * 商品个数
			Double weight = product.getExpweight()*product.getNum();
			
			totalPrice += (price+amount);
			totalIntegral += integral;
			totalWeight += weight;
			totalNum += product.getNum();
			//快递模板ID
			Integer tid = product.getExptid();//供应商ID
			if(tid != null){
				tids.add(tid);
			}
			
			Integer gxId = product.getSupplierid();
			
			if(gxId != 27){
				//不可使用巴马百岁源矿泉水供应商
				isCoupon = true;
			}
		}
		
		//计算运费
		if(areaid != null && !tids.isEmpty()){
			for(Integer tid : tids){
				
				boolean isFree = false;
				
				Double weigth = 0.0;//重量
				
				Double price = 0.0;//价格
				
				Double postPrice = 0.0;//运费
				
				for(ProductInfo product : products){
					
					if(tid == product.getExptid()){
						
						//总重量
						weigth += 
							ArithUtil.mul(product.getExpweight(), product.getNum().doubleValue());
						
						//总价格
						price += 
							ArithUtil.mul(ArithUtil.add(product.getCash().doubleValue(), product.getAmount().doubleValue()),product.getNum().doubleValue());
					}
				}
				
				isFree = isFree(tid, areaid, weigth, price);
				if(isFree){
					//不包邮
					postPrice = getPostPrice(tid, weigth,areaid,areaname);
				}
				
				totalPostPrice += postPrice;//总运费
			}
		}
		
		if(isUserCoupon == 1){
			//获取优惠卷
			List<Map<Object,Object>> coupons = new ArrayList<Map<Object,Object>>();
			//优惠卷信息
			Map<Object,Object> coupon = new HashMap<Object,Object>();
			//查询用户可用优惠卷
			Map<Object,Object> map = new HashMap<Object,Object>();
			map.put("totalPrice", totalPrice);
			map.put("uid", uid);
			map.put("isDate", DateUtil.format(DateUtil.now(),"yyyy-MM-dd")+" 00:00:00");
			
			//获取巴马水优惠卷的ID集合
			List<Integer> couponIds = new ArrayList<Integer>();
			try{
				String waterCoupons = propertiesUtil.getValue("waterCoupons", "conf_integral_pay.properties");
				waterName = propertiesUtil.getValue("waterName", "conf_integral_pay.properties");
				if(StringUtils.isNotEmpty(waterCoupons)){
					
					JSONArray json = JSONObject.parseArray(waterCoupons);
					
					for(int i=0; i<json.size();i++){
					
						JSONObject object = json.getJSONObject(i);
						
						Integer waterCouponCid = Integer.parseInt(object.getString("cid"));//巴马水优惠卷ID
						
						couponIds.add(waterCouponCid);
					}
					
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
			map.put("cids", couponIds);
			map.put("cname", waterName);
			if(cdid == null){
				
				if(isCoupon){
					//不可使用巴马百岁源矿泉水
					map.put("type", 1);
					coupons = couponDetailDao.findAllIsUserCoupons(map);
				}else{
					//可以使用巴马百岁源矿泉水卷
					map.put("type", 0);
					coupons = couponDetailDao.findAllIsUserCoupons(map);
				}
				
			}else{
				//有cdid
				map.put("cdid", cdid);
				if(isCoupon){
					//不可使用巴马百岁源矿泉水
					map.put("type", 1);//可以
					coupons = couponDetailDao.findAllIsUserCoupons(map);
				}else{
					//可以使用巴马百岁源矿泉水卷
					map.put("type", 0);
					coupons = couponDetailDao.findAllIsUserCoupons(map);
				}
			}
			
			if(coupons != null && !coupons.isEmpty()){
				
				coupon = coupons.get(0);
				denomination = Double.parseDouble(coupon.get("denomination").toString());
				Integer cid = Integer.parseInt(coupon.get("cid").toString());//用户优惠卷ID
				opl.setCdid(Integer.parseInt(coupon.get("cdid").toString()));//优惠卷ID
				opl.setDenomination(BigDecimal.valueOf(denomination));//优惠卷面额
				opl.setIsCoupon(1);//有优惠卷用
				opl.setIsUserCoupon(1);//使用优惠卷
				
				try{
					String waterCoupons = propertiesUtil.getValue("waterCoupons", "conf_integral_pay.properties");
					
					if(StringUtils.isNotEmpty(waterCoupons)){
						
						JSONArray json = JSONObject.parseArray(waterCoupons);
						
						for(int i=0; i<json.size();i++){
						
							JSONObject object = json.getJSONObject(i);
							
							Integer waterCouponCid = Integer.parseInt(object.getString("cid"));//巴马水优惠卷ID
							
							Double waterCouponDenomination = Double.parseDouble(object.getString("denomination"));//巴马水优惠卷面额
							
							Double waterPostPrice = Double.parseDouble(object.getString("postPrice"));//运费
							
							if(cid.compareTo(waterCouponCid) ==0 || denomination.compareTo(waterCouponDenomination) == 0){
								//使用巴马水优惠卷产生的运费
								totalPostPrice = waterPostPrice;
							}
						}
						
					}
					
				}catch(Exception e){
					e.printStackTrace();
				}
				
			}else{
				opl.setIsCoupon(0);//没有优惠卷可用
				opl.setIsUserCoupon(1);//使用优惠卷
				opl.setDenomination(BigDecimal.valueOf(0.0));//优惠卷面额
			}
		}else{
			opl.setIsUserCoupon(0);//不使用优惠卷
			opl.setIsCoupon(1);//不使用优惠卷
			opl.setDenomination(BigDecimal.valueOf(0.0));//优惠卷面额
		}
		opl.setTotalNum(totalNum);
		opl.setTotalIntegral(BigDecimal.valueOf(totalIntegral));
		opl.setTotalPrice(BigDecimal.valueOf(totalPrice));
		opl.setTotalWeight(BigDecimal.valueOf(totalWeight));
		opl.setTotalPostPrice(BigDecimal.valueOf(totalPostPrice));
		opl.setTids(tids);;
		return opl;
	}
		

	/**
	 * 立即购买
	 */
	@Override
	public Object directOrder(DirectOrderRequest directOrderRequest) {
		
		//获取用户的ID
		String uid = ObjectUtils.toString(sessionTokenService.getStringForValue(directOrderRequest.getSessiontoken()));
		
		//通过codeId 查询商品详情
		ProductInfo productInfo = productInfoDao.selectByCodeId(directOrderRequest.getCodeId());
		
		//变更商品详情
		if(productInfo == null){
			return new BaseResponse(ResponseCode.FAILURE,"购买的商品不存在");
		}
		
		//创建cartId
		productInfo.setCartId(FrameUtil.getUUID());
		productInfo.setNum(directOrderRequest.getNum());//用户购买数量
		productInfo.setAttrIds(directOrderRequest.getAttrIds());//规格ID组合
		productInfo.setAttrVals(directOrderRequest.getAttrVals());
		
		SaleGroup saleGroup = saleGroupDao.selectByAttr(directOrderRequest.getCodeId(), directOrderRequest.getAttrIds());
		
		if(saleGroup != null){
			productInfo.setAmount(saleGroup.getAmount());
		}else{
			productInfo.setAmount(BigDecimal.valueOf(0.0));
		}
		
		if(directOrderRequest.getActivityId() != null){
			productInfo.setActivityId(directOrderRequest.getActivityId());//活动ID
			//活动商品
			FreshActivityProduct fap = 
				freshActivityProductDao.findByActivityIdAndCodeId(directOrderRequest.getActivityId(), directOrderRequest.getCodeId().longValue());
			if(fap != null){
				
				productInfo.setCash(fap.getSalePrice());//商品活动售价
				productInfo.setIntegral(fap.getSellIntegral());//活动时需积分
				
				//通过商品ID和商品规格组合查询活动商品加价
				FreshActivityGroup fag = 
					freshActivityGroupDao.selectByActivityIdAndCodeIdAndPvIds(directOrderRequest.getActivityId(), directOrderRequest.getCodeId().longValue(), directOrderRequest.getAttrIds());
				if(fag != null){
					productInfo.setAmount(fag.getAmount());
				}
			}
		}
		
		List<ProductInfo> products = new ArrayList<ProductInfo>();
		
		products.add(productInfo);
		
		//从缓存中清除上次直接购买的商品信息
		sessionTokenService.deleteSessionToken(MarketConsts.DIRECT_ORDER+uid);
		//存入缓存中
		sessionTokenService.setStringForValue(MarketConsts.DIRECT_ORDER+uid, JSON.toJSONString(products), 0, TimeUnit.SECONDS);
		
		Map<Object,Object> map = getBuyProductOrderInfo(uid, products, directOrderRequest.getRid(), directOrderRequest.getCdid(), directOrderRequest.getIsUserCoupon(),true);
		
		String state = ObjectUtils.toString(map.get("state"));
		String info = ObjectUtils.toString(map.get("info"));
		
		map.remove("state");
		map.remove("info");
		
		response = new MapResponse(Integer.parseInt(state), info);
		
		response.setResponse(map);
		
		return response;
		
	}

	
	
	@Override
	public Object changePayType(ChangePayTypeRequest changePayTypeRequest) {
		
		Map<Object,Object> map = new HashMap<Object,Object>();
		
		//获取用户ID
		String uid = ObjectUtils.toString(sessionTokenService.getStringForValue(changePayTypeRequest.getSessiontoken()));
		
		//通过订单ID查询订单信息
		
		BillFresh bf = marketBillFreshDao.selectByPrimaryKey(changePayTypeRequest.getBid());
		
		if(bf != null && bf.getStatus() == 0){
			
			try{
				Map<String, String> amountmap = liveGiftsInfoService.getXmnWalletBlance(uid);
				Map<String,Object> birdmap = liveGiftsInfoService.getLiveWalletBlance(uid);
				
				if(birdmap.isEmpty()){
					birdmap = new HashMap<String,Object>();
					birdmap.put("zbalance", 0.0);
					birdmap.put("limitBalance", 0.0);
				}
				
				if(amountmap.isEmpty()){
					amountmap = new HashMap<String,String>();
					amountmap.put("commision", "0.0");
					amountmap.put("zbalance", "0.0");
					amountmap.put("integral", "0.0");
				}
				
				map.put("orderNo", changePayTypeRequest.getBid());//订单号
				Double zbalance = 
					ArithUtil.sub(Double.parseDouble(birdmap.get("zbalance").toString()), Double.parseDouble(birdmap.get("limitBalance").toString()));
				if(zbalance<0){
					zbalance = 0.0;
				}
				map.put("zbalance", zbalance);//鸟币余额
				Double balance = 
					Double.parseDouble(amountmap.get("commision").toString())+Double.parseDouble(amountmap.get("zbalance").toLowerCase());
				map.put("amount", ObjectUtils.toString(balance));//用户钱包余额 
				map.put("integral", amountmap.get("integral"));//用户积分余额
				
				Double payPrice = bf.getMoney().doubleValue()+bf.getFreight().doubleValue()-bf.getCuser().doubleValue();
				map.put("payPrice", payPrice<0?0.0:new BigDecimal(payPrice).setScale(2, BigDecimal.ROUND_HALF_UP));//最终支付金额
				map.put("totalIntegral", bf.getIntegral());//最终支付的积分余额
				response = new MapResponse(ResponseCode.SUCCESS,"成功");
				response.setResponse(map);
			}catch(Exception e){
				e.printStackTrace();
				return new BaseResponse(ResponseCode.FAILURE,"获取用户钱包异常");
			}
			return response;
		}
		return new BaseResponse(ResponseCode.FAILURE,"订单已关闭,请重新下单");
	}

	/**
	 * 查询订单状态
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Object queryOrderStatus(ChangePayTypeRequest changePayTypeRequest) {
		
		Map<String,String> map = new HashMap<String,String>();
		//签名key
		String url = "";
		
		try{
			url = propertiesUtil.getValue("queryStatusUrl", "conf_integral_pay.properties");
		}catch(Exception e){
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE,"支付key获取异常");
		}
		
		map.put("orderNumber", changePayTypeRequest.getBid().toString());
		
		String queryurl = HttpConnectionUtil.getUrl(map, url);
		
		log.info("查询地址地址:"+queryurl);
		
		String resultStr = HttpConnectionUtil.doPost(queryurl, "");
		
		log.info("支付结果:"+resultStr);
		
		if (StringUtils.isNotEmpty(resultStr)) {
			map.clear();
            JSONObject json = JSONObject.parseObject(resultStr);
            log.info("格式化请求支付接口返回json:"+json);
            map = (Map<String, String>) JSON.parse(json.toString());
            if(map != null && !map.isEmpty()){
            	String code = ObjectUtils.toString(map.get("state"));
            	String msg = ObjectUtils.toString(map.get("msg"));
            	MapResponse response = new MapResponse(ResponseCode.SUCCESS, "支付成功");
            	Map<Object,Object> result = new HashMap<Object, Object>();
            	result.put("code", code);
            	result.put("msg", msg);
            	response.setResponse(result);
            	return response;
            }
        }
		return new BaseResponse(ResponseCode.FAILURE,"支付失败");
	}

	/**
	 * 确认收货
	 */
	@Override
	public Object confirmOrder(OrderNoRequest orderNoRequest) {

		//通过订单ID获取订单信息
		BillFresh bf = marketBillFreshDao.selectByPrimaryKey(orderNoRequest.getBid());
		
		bf.setWstatus(2);//已收货
		bf.setYdate(DateUtil.now());//收货时间
		int result = marketBillFreshDao.updateByPrimaryKeySelective(bf);
		
		if(result != 0){
			return new BaseResponse(ResponseCode.SUCCESS,"确认收货成功");
		}
		
		return new BaseResponse(ResponseCode.FAILURE,"确认收货失败");
	}
	
	//获取用户收货地址
	private ReceivingAddress getReceivingAddress(String uid,Integer rid){
		
		ReceivingAddress ra = null;
		
		if(rid == null){
			//收货地址ID为空时，获取用户默认地址作为收货地址
			Map<Object,Object> map = new HashMap<Object,Object>();
			map.put("uid", uid);
			map.put("dstatus", 0);//收货地址状态 0 正常 1删除
			map.put("isDefault", 1);//默认地址
			List<ReceivingAddress> ras = new ArrayList<ReceivingAddress>();
			ras = marketReceivAddressDao.findAllIsNotDefaultByUid(map);//查询用户默认地址
			if(!ras.isEmpty()){
				ra = ras.get(0);
			}else{
				map.put("isDefault", 0);//非默认地址
				//查询用户非默认地址
				ras = marketReceivAddressDao.findAllIsNotDefaultByUid(map);
				if(!ras.isEmpty()){
					ra = ras.get(0);
				}
			}
			
		}else{
			//通过收货地址ID获取用户收货地址信息
			ra = marketReceivAddressDao.selectByPrimaryKey(rid);
			
		}
		
		return ra;
		
	}

	
	//获取用户可用积分
	private Double getIntegral(String uid){
		
		Double integral = null;//用户可用积分
		
		try{
			
			Map<String, String> walletmap = liveGiftsInfoService.getXmnWalletBlance(uid);
			integral = Double.parseDouble(walletmap.get("integral").toString());
			
		}catch(Exception e){
			e.printStackTrace();
			
			log.info("查询用户可用积分异常,检查获取用户积分函数:getIntegral();uid:"+uid);
		}
		return integral;
	}

	
	//判断用户鸟币是否足够支付
	private Map<Object,Object> isBirdCoin(String uid,Double payPrice){
		Map<Object,Object> map = new HashMap<Object,Object>();
		try{
			//获取用户直播钱包
			Map<String,Object> birdmap = liveGiftsInfoService.getLiveWalletBlance(uid);
			if(birdmap.size() > 0){ 
				Double zbalance = Double.parseDouble(birdmap.get("zbalance").toString());//鸟币余额
				Double limitBalance = Double.parseDouble(birdmap.get("limitBalance").toString());//鸟币支付限额
				Double balance = Double.parseDouble(birdmap.get("commision").toString());//鸟豆
				//判断是否存在可用鸟币
				boolean isCan = ArithUtil.sub(zbalance, limitBalance) <= 0;
				
				if(isCan){
					//鸟币限制使用
					if(balance > 0){//有鸟豆的时候
						//有鸟豆
						map.put("coin", 0.0);//用户可用鸟币
						map.put("isCoin", 0);//鸟币支付
						map.put("isBirdBeans", 1);//是否有鸟豆 0 否  1是 2其他
						
						return map;
					}
					//没有鸟豆的时候
					map.put("coin", 0.0);//用户可用鸟币
					map.put("isCoin", 0);//鸟币支付
					map.put("isBirdBeans", 0);//是否有鸟豆 0 否  1是 2其他
					return map;
				}

				//判断是否足够支付
				boolean isPayByCoin = ArithUtil.sub(ArithUtil.sub(zbalance, limitBalance), payPrice) < 0;
				
				//可以使用鸟币支付,鸟币足够支付
				if(isPayByCoin){
					map.put("coin", ArithUtil.sub(zbalance, limitBalance));
					map.put("isCoin", 0);//鸟币不足
					
					//没有鸟豆 
					if(balance <= 0){
						map.put("isBirdBeans", 0);//是否有鸟豆 0 否  1是 2其他
					}else{
						map.put("isBirdBeans", 1);//是否有鸟豆 0 否  1是 2其他
					}
					
					return map;
				}
				
				map.put("coin", ArithUtil.sub(zbalance, limitBalance));//用户可用鸟币
				map.put("isCoin", "1");//鸟币支付
				map.put("isBirdBeans", 2);//是否有鸟豆 0 否  1是 2其他
				return map;
				
			}
		}catch(Exception e){
			e.printStackTrace();
			log.info("查询用户鸟币异常,检查获取用户鸟币函数:getCoin();uid:"+uid);
		}
		
		return map;
	}
	
	//初始化商品信息
	private List<Map<Object,Object>> initProduct(List<ProductInfo> products){
		List<Map<Object,Object>> list = new ArrayList<Map<Object,Object>>();
		//用户购买的商品
		for(ProductInfo product : products){
			Map<Object,Object> map = new HashMap<Object,Object>();
			map.put("pid", product.getPid());//商品ID
			map.put("codeid", product.getCodeid());//商品ID
			map.put("pname", product.getPname());//商品名称
			map.put("pimage", fileUrl+product.getBreviary());//商品缩略图
			map.put("spec", product.getAttrVals());//商品规格
			Double price = ArithUtil.add(product.getCash().doubleValue(),product.getAmount().doubleValue());
			map.put("price", price);//商品价格
			map.put("pintegral", product.getIntegral());//商品所需积分
			map.put("num", product.getNum());//商品购买数量
			map.put("carids",product.getCartId());//商品购买ID
			list.add(map);
		}
		return list;
	}

	//获取用户购买的商品订单详情
	private Map<Object,Object> getBuyProductOrderInfo(String uid,List<ProductInfo> products,Integer rid,Integer cdid,Integer isUserCoupon,boolean isDirect){
		
		Map<Object,Object> map = new HashMap<Object,Object>();
		
		/**
		 * 1.初始化用户购买商品信息,并存入map
		 */
		if(isDirect){
			map.put("pid", products.get(0).getPid());//商品ID
			map.put("codeid", products.get(0).getCodeid());//商品ID
			map.put("pname", products.get(0).getPname());//商品名称
			map.put("pimage", fileUrl+products.get(0).getBreviary());//商品缩略图
			map.put("spec", products.get(0).getAttrVals());//商品规格
			Double price = ArithUtil.add(products.get(0).getCash().doubleValue(),products.get(0).getAmount().doubleValue());
			map.put("price", price);//商品价格
			map.put("pintegral", products.get(0).getIntegral());//商品所需积分
			map.put("num", products.get(0).getNum());//商品购买数量
			map.put("carids", products.get(0).getCartId());
		}
		List<Map<Object,Object>> list = initProduct(products);
		map.put("products", list);
		//1245,253612,
		StringBuilder sb = new StringBuilder();
		for(ProductInfo product : products){
			sb.append(product.getCartId()).append(",");
		}
		map.put("cartids", sb.toString());
		map.put("carids", sb.toString());
		
		/**
		 * 2.获取用户收货地址信息,有则显示,无则显示提示添加收货地址标语
		 */
		ReceivingAddress ra = getReceivingAddress(uid, rid);
		
		/**
		 * 2.1)判断是否存在收货地址
		 */
		Integer cityid = null;
		String cityname = null;
		
		if(ra == null){
			map.put("isAddress", 0);//用户是否有收货地址 0 否 1 是
		}else{
			map.put("isAddress", 1);//用户是否有收货地址 0 否 1 是
			map.put("rid", ObjectUtils.toString(ra.getId()));//收件地址ID
			map.put("address", ObjectUtils.toString(ra.getAddress()));//收件人详细地址
			map.put("province", ObjectUtils.toString(ra.getProvince()));//收件人所在省名称
			map.put("city", ObjectUtils.toString(ra.getCity()));//收件人所在市名称
			map.put("area", ObjectUtils.toString(ra.getAreaname()));//收件人所在区名称
			map.put("phone", ObjectUtils.toString(ra.getPhoneid()));//收件人联系电话
			map.put("username", ObjectUtils.toString(ra.getUsername()));//收件人姓名
			
			cityid = ra.getCityid();//收货地区ID
			cityname = ra.getCity();
		}
		
		/**
		 * 3.格式化用户购买商品数据
		 */
		OrderPriceList opl = 
			getOrderPriceList(products,cityid,uid,cdid,isUserCoupon,cityname);
		//所需要的积分
		map.put("totalIntegral", opl.getTotalIntegral());
		//一共购买的商品数量
		map.put("totalNum", opl.getTotalNum());
		//购买商品的总消费
		map.put("totalPrice", opl.getTotalPrice());
		//运费
		map.put("postPrice", opl.getTotalPostPrice());
		//用户是否有可用优惠卷
		map.put("danomination", opl.getDenomination().doubleValue());
		map.put("isCoupon", opl.getIsCoupon());//是否有优惠卷可用 0 否 1 是
		map.put("cdid", ObjectUtils.toString(opl.getCdid()));//用户优惠卷ID
		
		/**
		 * 4.判断用户积分是否足够支付,如果足够支付则继续,如果不够支付则提示用户积分不足,无法购买
		 */
		Double integral = getIntegral(uid);
		
		if(integral == null){
			map.clear();
			map.put("state", ResponseCode.FAILURE);
			map.put("info", "获取用户积分异常");
			return map;
		}
		
		//可用积分
		map.put("integral", integral);
		map.put("isIntegral", 1);//积分是否用 0否 1是 ; 默认 1
		
		boolean isIntegral = ArithUtil.sub(integral, opl.getTotalIntegral().doubleValue()) < 0;
		
		if(isIntegral){
			//积分不够用
			map.clear();
			map.put("integral", "你当前剩余积分为"+integral+",");
			map.put("totalIntegral", "所需支付积分为"+opl.getTotalIntegral());
			map.put("isIntegral", 0);//积分是否够用 0否 1是
			map.put("state", ResponseCode.SUCCESS);
			map.put("info", "积分不足,无法支付");
			return map;
		}

		/**
		 * 5.判断用户优惠卷是否能够抵消消费金额,如果可以则直接支付,否则需判断用户鸟币
		 * 6.判断用户鸟币是否充足,如果鸟币够支付则继续,如果用户鸟币不足支付,则判断用户是否有鸟豆
		 * 7.判断用户是否有鸟豆,如果用户有鸟豆则提示用户前去打赏获取更多鸟币,如果没有鸟币则提示用户前去充值
		 */
		//求和:用户购买商品消费金额
		Double payPrice = 
			ArithUtil.sub(
				ArithUtil.add(
					opl.getTotalPrice().doubleValue(), opl.getTotalPostPrice().doubleValue()
					), opl.getDenomination().doubleValue());
		
		//获取用户可用鸟币
		Map<Object,Object> coinmap = isBirdCoin(uid, payPrice);
		
		if(coinmap.isEmpty()){
			map.clear();
			map.put("state", ResponseCode.FAILURE);
			map.put("info", "获取用户鸟币异常");
			return map;
		}
		
		//优惠卷面额大于应付金额时
		if(payPrice <= 0){
			//直接购买
			map.put("payPrice", 0.0);
			map.put("isCoin", coinmap.get("isCoin"));//是否有鸟币可用 0 否 1 
			map.put("isBirdBeans", coinmap.get("isBirdBeans"));//是否有鸟豆 0 否  1是 2其他
			map.put("coin", coinmap.get("coin"));
		}else{
			map.put("payPrice", payPrice);
			map.put("isCoin", coinmap.get("isCoin"));
			map.put("isBirdBeans", coinmap.get("isBirdBeans"));//是否有鸟豆 0 否  1是 2其他
			map.put("coin", coinmap.get("coin"));
		}
		
		map.put("state", ResponseCode.SUCCESS);
		map.put("info", "成功");
		return map;
	}
	
	
	
	/**
	 * 
	* @Title: getDenomation 
	* @Description:计算优惠卷
	* @param:@param uid
	* @param:@param totalPrice
	* @param:@param cdids
	* @param:@param expTids
	* @param:@param supplierIds
	* @return:void返回类型 
	* @throws
	 */
	private Map<Object,Object> getDenomation(String uid,Double totalPrice,List<Integer> cdids,List<Integer> supplierIds){
		Map<Object,Object> result = new HashMap<Object,Object>();
		Double bmsfreit = 0.00;//巴马水运费
		Double denomination = 0.00;//优惠卷面额
		int isUserBms=0;
		int isCoupon = 1;
		/**
		 * 优惠卷使用方式
		 * 		1:使用巴马水卷,只能用于购买巴马水,且购买巴马水时优先使用
		 * 		2:使用非巴马水卷
		 * 优惠卷使用条件
		 * 		1:当采用方式1的时候，需判断用户购买的商品是否包含非巴马水商品
		 * 		2:当采用方式2的时候，需判断用户使用的是否同一编号的优惠卷,不可混用
		 * 		
		 */
		
		String couponStatus = "";//优惠卷使用状态  0 无可用优惠卷  1有可用优惠卷 2 显示优惠卷金额
		
		boolean isNotAllBMS = false;//是否购买商品都是巴马水,默认为是
		
		if(supplierIds != null && supplierIds.size() > 0){
			
			for(Integer supplierId : supplierIds){
				
				if(supplierId != 27){
					
					//购买的商品有非巴马水
					
					isNotAllBMS = true;
				}
			}
		}
		
		//优先考虑巴马水卷的情况,获取巴马水卷信息
		List<Integer> bmsids = new ArrayList<Integer>();//巴马水优惠卷id集合
		List<Map<Object,Object>> bmslist = new ArrayList<Map<Object,Object>>();//巴马水卷信息集合
		//获取巴马水ID
		String waterCoupons = "";
		String waterName = "";
		try{
			waterCoupons = propertiesUtil.getValue("waterCoupons", "conf_integral_pay.properties");
			waterName = propertiesUtil.getValue("waterName", "conf_integral_pay.properties");
		}catch(Exception e){
			e.printStackTrace();
			log.info("获取巴马水配置文件异常");
		}
		//解析JSON
		if(StringUtils.isNotEmpty(waterCoupons)){
			JSONArray json = JSONObject.parseArray(waterCoupons);
			for(int i=0; i<json.size();i++){
				
				Map<Object,Object> map = new HashMap<Object,Object>();
				
				JSONObject object = json.getJSONObject(i);
				Integer bmsid = Integer.parseInt(object.getString("cid"));//巴马水优惠卷ID
				Double bmsdemonation = Double.parseDouble(object.getString("denomination"));//巴马水优惠卷面额
				Double bmspostprice = Double.parseDouble(object.getString("postPrice"));//巴马水专用运费
				
				bmsids.add(bmsid);
				
				map.put("bmsid", bmsid);
				map.put("bmsdemonation", bmsdemonation);
				map.put("bmspostprice", bmspostprice);
				
				bmslist.add(map);
				
			}
		}
		
		//查询可用优惠卷条件
		Map<Object,Object> parammap = new HashMap<Object,Object>();
		parammap.put("totalPrice", totalPrice);
		parammap.put("uid", uid);
		parammap.put("isDate", DateUtil.format(DateUtil.now(),"yyyy-MM-dd")+" 00:00:00");
		parammap.put("cids", bmsids);
		parammap.put("cname", waterName);
		
		List<Map<Object,Object>> coupons = new ArrayList<Map<Object,Object>>();
		
		if(cdids != null && cdids.size() > 0){
			//优惠卷面额
			if(isNotAllBMS){
				//不可使用巴马水卷
				parammap.put("type", 1);
				parammap.put("cdids", cdids);
				
				coupons = couponDetailDao.findAllIsUserCoupons(parammap);
				
				if(coupons != null && coupons.size() > 0){
					for(Map<Object,Object> coupon : coupons){
						denomination += Double.parseDouble(coupon.get("denomination").toString());
					}
					
					couponStatus = "￥"+denomination;
					isUserBms = 2;
				}else{
					couponStatus="暂无优惠券可用";
					isCoupon = 0;
				}
			}else{
				
				//可使用巴马水卷
				parammap.put("type", 0);
				parammap.put("cdids", cdids);
				
				coupons = couponDetailDao.findAllIsUserCoupons(parammap);
				
				//巴马水不可叠加使用 
				if(coupons != null && coupons.size() > 0){
					denomination = Double.parseDouble(coupons.get(0).get("denomination").toString());
					
					couponStatus = "￥"+denomination;
					
					//重新计算运费
					for(Map<Object,Object> bmsmap : bmslist){
						if(coupons.get(0).get("cdid").toString().compareTo(bmsmap.get("bmsid").toString()) ==0 
								|| denomination.compareTo(Double.parseDouble(bmsmap.get("bmsdemonation").toString())) == 0){
							bmsfreit = Double.parseDouble(bmsmap.get("bmspostprice").toString());
							isUserBms = 1;
						}
					}
					
				}else{
					couponStatus="暂无优惠卷可用";
					isCoupon=0;
				}
			}
			
		}else{
			//用户未选择优惠卷
			if(isNotAllBMS){
				//不可使用巴马水卷
				parammap.put("type", 1);
			}else{
				//可使用巴马水卷
				parammap.put("type", 0);
			}
			coupons = couponDetailDao.findAllIsUserCoupons(parammap);
			
			if(coupons != null && coupons.size() > 0){
				couponStatus="有可用优惠卷";
			}else{
				
				couponStatus="暂无优惠卷可用";
				isCoupon = 0;
			}
		}
		
		
		result.put("couponStatus", couponStatus);
		result.put("bmsfreit", bmsfreit);
		result.put("isUserBms", isUserBms);
		result.put("isCoupon", isCoupon);
		result.put("denomination", denomination);
		return result;
	}

	@Override
	public List<ProductInfo> getProductInfoList(OrderSettlementRequest orderSettlementRequest,String uid) {
		
		List<ProductInfo> products = new ArrayList<ProductInfo>();
		
		//默认商品缓存key,购物车购买
		
		String key = MarketConsts.CART_INFO_HEADER;
		
		//用户购买类型 0 购物车购买 1直接购买
		Integer buyType = orderSettlementRequest.getBuyType();
		
		/**
		 * buyType == 1 时，属用户直接购买，用户购买的商品信息，需把用户直接购买的商铺信息，存入缓存之中
		 */
		if(buyType == 1){
			
			//直接购买集合 
			List<ProductInfo> directproducts = new ArrayList<ProductInfo>();
			
			key = MarketConsts.DIRECT_ORDER;//直接购买缓存key
			
			//通过商品的唯一标识查询商品信息
			ProductInfo product = productInfoDao.selectByCodeId(orderSettlementRequest.getCodeId());
			
			if(product != null){
				
				product.setCartId(FrameUtil.getUUID());//创建cartId，存入缓存的购买商品标识
				product.setNum(orderSettlementRequest.getNum());//用户购买数量
				product.setAttrIds(orderSettlementRequest.getAttrIds());//规格ID组合
				product.setAttrVals(orderSettlementRequest.getAttrVals());
				
				//用户直接购买的商品存入一个集合，用于存入缓存中
				directproducts.add(product);
				
				orderSettlementRequest.setCartids(product.getCartId());
				
				//在存入缓存中时，需清空用户原有缓存
				sessionTokenService.deleteSessionToken(key+uid);
				//存入缓存中
				sessionTokenService.setStringForValue(key+uid, JSON.toJSONString(directproducts), 0, TimeUnit.SECONDS);
			}
		}
		
		//从缓存中获取商品信息
		String productStr = ObjectUtils.toString(sessionTokenService.getStringForValue(key+uid));
		
		if(StringUtils.isNotEmpty(productStr)){
			//JSON对象转换成集合
			List<ProductInfo> productlist = JSON.parseArray(productStr, ProductInfo.class);
				
			for(String cartid : orderSettlementRequest.getCarts()){
				
				for(ProductInfo product : productlist){
					
					if(cartid.equals(product.getCartId())){
						//用户购买商品唯一标识
						Long codeId = product.getCodeid();
						//用户购买商品活动ID
						Integer activityId = product.getActivityId();
						//用户购买商品规格ID组合
						String attrIds = product.getAttrIds();
						//通过商品ID查询商品详情
						ProductInfo productInfo = productInfoDao.selectByCodeId(codeId.intValue());
						
						if(productInfo != null){
							//查询用户购买商品的加价
							Double amount = 0.0;
							
							if(activityId != null){
								//存在活动
								FreshActivityProduct fap = 
										freshActivityProductDao.findByActivityIdAndCodeId(activityId, codeId);
								if(fap != null){
									productInfo.setCash(fap.getSalePrice());//商品活动售价
									productInfo.setIntegral(fap.getSellIntegral());//活动时需积分
									//通过活动ID、商品唯一标识、规格查询活动商品的库存
									FreshActivityGroup freshActivityGroup = 
										freshActivityGroupDao.selectActivityStockByPvids(activityId, codeId,attrIds);
									if(freshActivityGroup != null){
										amount = freshActivityGroup.getAmount().doubleValue();//活动加价
									}
								}
							}else{
								
								SaleGroup saleGroup = saleGroupDao.selectByAttr(codeId.intValue(), attrIds);
								if(saleGroup != null){
									amount = saleGroup.getAmount().doubleValue();//非活动加价
								}
							}
							
							productInfo.setNum(product.getNum());//从缓存中获取用户购买商品的个数
							productInfo.setAttrIds(product.getAttrIds());//从缓存中获取用户购买商品的规格ID组合
							productInfo.setAttrVals(product.getAttrVals());//从缓存中获取用户购买商品的规格值组合
							productInfo.setCartId(product.getCartId());//从缓存中获取用户购买商品的ID结合
							productInfo.setAmount(BigDecimal.valueOf(amount));//商品加价
							productInfo.setActivityId(activityId);
							
							products.add(productInfo);
						}
					}
				}
			}
		}
		
		return products;
	}
	
	

	@Override
	public Object generateOrder(GenerateOrderRequest generateOrderRequest) {
		Map<Object,Object> result = new HashMap<Object,Object>();
		
		//获取用户ID
		String uid = ObjectUtils.toString(sessionTokenService.getStringForValue(generateOrderRequest.getSessiontoken()));
		//客户端版本
		String appversion = generateOrderRequest.getAppversion();
		
		//用户购买的商品ID集合
		List<String> carIds = generateOrderRequest.getCarts();
		
		if(carIds.isEmpty()){
			return new BaseResponse(ResponseCode.FAILURE,"你还没有选择购买的商品");
		}
		
		//获取用户购买的商铺信息
		List<ProductInfo> products = getProductInfoList(uid, carIds,false,generateOrderRequest.getBuyType());
		
		if(products == null || products.isEmpty()){
			return new BaseResponse(ResponseCode.FAILURE,"购物车没有商品,去商场逛逛吧");
		}
		
		//判断库存、活动是否过期
		String flag = isActivityStock(products,uid);
		
		if(StringUtils.isNotEmpty(flag)){
			return new BaseResponse(ResponseCode.IS_INTEGRAL_STOCK,flag);
		}
		
		// 更新库存
		updateProductStock(products);
		
		//通过收货地址ID查询用户收货地址详情
		ReceivingAddress ra = marketReceivAddressDao.selectByPrimaryKey(generateOrderRequest.getRid());
			
		Integer cityid = ra.getCityid();//用户收货地址区域ID
		
		String cityname = ra.getCity();//城市名称
		
		/**
		 * 商品总价格，商品总积分,购买商品总数量,商品总重量,购买商品供应商提供的运费模板,购买商品供应商id
		 */
		Double denomination = 0.00;//优惠卷面额
		Double totalPrice = 0.00;//商品总价格
		Double totalIntegral = 0.00;//商品总积分
		Integer totalNum = 0;//商品购买总数量
		Set<Integer> expTids = new HashSet<Integer>();//运费模板id集合
		List<Integer> supplierIds = new ArrayList<Integer>();//供应商id集合
		for(ProductInfo product : products){
			//商品价格 = 商品单价 * 商品个数
			Double price = product.getCash().doubleValue()*product.getNum();
			//商铺积分= 商品单积分 * 商品个数
			Double coin = product.getIntegral().doubleValue()*product.getNum();
			//加价
			Double amount = product.getAmount().doubleValue()*product.getNum();
			//商品重量 = 商品单重量 * 商品个数
			
			totalPrice += (price+amount);
			totalIntegral += coin;
			totalNum += product.getNum();
			
			Integer tid = product.getExptid();//运费模板id
			if(tid != null){
				expTids.add(tid);
			}
			
			Integer gxId = product.getSupplierid();//供应商id
			
			if(gxId != null){
				supplierIds.add(gxId);
			}
			
		}
		
		/**
		 * 计算运费
		 * 运费计算方式:
		 * 		1:普通计算方式(包含普通商品、无可用巴马水卷的含巴马水商品)
		 * 		2:特殊计算方式(购买巴马水)
		 */
		Double postPrice = 0.0;//运费
		
		//默认为普通计算方式
		if(cityid != null && expTids.size() > 0){
			for(Integer tid : expTids){
				
				boolean isFree = false;
				
				Double weigth = 0.0;//重量
				
				Double price = 0.0;//价格
				
				for(ProductInfo product : products){
					
					if(tid == product.getExptid()){
						//总重量
						weigth += 
							ArithUtil.mul(product.getExpweight(), product.getNum().doubleValue());
						//总价格
						price += 
							ArithUtil.mul(ArithUtil.add(product.getCash().doubleValue(), product.getAmount().doubleValue()),product.getNum().doubleValue());
					}
				}
				
				isFree = isFree(tid, cityid, weigth, price);
				Double freit = 0.00;
				if(isFree){
					//不包邮
					freit = getPostPrice(tid, weigth,cityid,cityname);
				}
				
				postPrice += freit;//总运费
			}
		}
		
		//优惠卷
		Map<Object,Object> map = getDenomation(uid, totalPrice, generateOrderRequest.getCdids(), supplierIds);
		
		if(Integer.parseInt(map.get("isUserBms").toString()) == 1){
			postPrice = Double.parseDouble(map.get("bmsfreit").toString());
		}
		denomination = Double.parseDouble(map.get("denomination").toString());
		
		//插入订单
		Long bid = insertBillFresh(uid, generateOrderRequest.getCid(), appversion, denomination, postPrice, ra, totalPrice, totalIntegral, totalNum,generateOrderRequest.getAppSource());
		
		if(bid == null){
			//订单创建异常的时候回滚之前库存的操作
			for(ProductInfo product : products){
				saleGroupDao.updateExceptionStock(product.getCodeid().intValue(), product.getAttrIds(), product.getNum());
			}
			return new BaseResponse(ResponseCode.FAILURE,"创建订单异常");
		}
		
		//实付金额
		Double payPrice = totalPrice+totalIntegral+postPrice; 
		
		if(generateOrderRequest.getCdids() != null && generateOrderRequest.getCdids().size() > 0){
			//修改用户优惠卷信息
			List<CouponDetail> coupons = couponDetailDao.findAllByCdids(generateOrderRequest.getCdids());
			//锁定优惠卷
			BigDecimal cuser = new BigDecimal(payPrice);
			if(coupons != null && coupons.size() > 0){
				
				for(CouponDetail coupon : coupons){
					coupon.setBid(bid);
					coupon.setUserStatus((byte)1);//使用状态，0未使用，1锁定，2已使用 3 已过期并退款
					//修改
					couponDetailDao.updateByPrimaryKeySelective(coupon);
					
					cuser = cuser.compareTo(coupon.getDenomination()) > 0 ? cuser.subtract(coupon.getDenomination()) : new BigDecimal(0.00);
					//插入优惠卷关系表
					CouponRelation crelation = new CouponRelation();
					crelation.setBid(String.valueOf(bid));
					crelation.setCdenom(coupon.getDenomination());
					crelation.setCuser(cuser.compareTo(coupon.getDenomination()) > 0 ? coupon.getDenomination() : cuser);
					crelation.setCdid(coupon.getCdid());
					crelation.setCserial(coupon.getSerial());
					crelation.setCtype((byte)1);//平台卷
					crelation.setOrderType(coupon.getCtype()+"");
					crelation.setSerial(coupon.getSerial());
					
					couponRelationDao.insertSelective(crelation);
				}
			}
		}
		
		//插入订单商品详情表
		addProductBill(bid, products,expTids,cityid,cityname);
			
		//从原来集合干掉购买的商品集合
		products = getProductInfoList(uid, carIds, true,generateOrderRequest.getBuyType());
		
		//把新的集合 替换redis中的老集合
		if(generateOrderRequest.getBuyType() != 1){
			//购物车购买的时候替换缓存
			sessionTokenService.setStringForValue(MarketConsts.CART_INFO_HEADER+uid, JSON.toJSONString(products), 0, TimeUnit.DAYS);
		}
		
		//比较是否能够抵扣金额
		if(denomination>=payPrice){
			//封装支付接口参数
			Map<String,String> paymap = new HashMap<String,String>();
			
			DecimalFormat df = new DecimalFormat("0.00");
			//更新订单状态
			paymap.put("bid", bid.toString());
			paymap.put("status", "1");//支付成功
			paymap.put("payType", "1000011");//支付类型 优惠卷支付
			paymap.put("payid", "0");
			paymap.put("orderAmount", df.format(totalPrice));//支付金额
			paymap.put("integral", df.format(totalIntegral));//支付积分
			paymap.put("freight", df.format(postPrice));//运费
			paymap.put("cuser", df.format(denomination));//优惠卷面额
			paymap.put("payment", "0.00");
			paymap.put("balance", "0.00");
			paymap.put("zbalance", "0.00");
			paymap.put("commision", "0.00");
			paymap.put("birdCoin", "0.00");
			
			try {
				int isSuccess = notifyForPayComplete(paymap);
				if(isSuccess == 1){
					result.put("payType", 0);//支付类型  0优惠卷支付 1非用优惠卷支付
					result.put("orderNo", bid);
					response = new MapResponse(ResponseCode.SUCCESS,"支付成功");
					response.setResponse(result);
					return response;
				}
			} catch (Exception e) {
				e.printStackTrace();
				return new BaseResponse(ResponseCode.FAILURE,"支付成功,修改订单状态异常");
			}
		}
		result.put("orderNo", bid);//订单号
		result.put("payType", 1);//支付类型 0 优惠卷支付 1 非优惠卷支付
		response = new MapResponse(ResponseCode.SUCCESS,"订单创建成功");
		response.setResponse(result);
		return response;
	}
	
	/**
	 * 
	* @Title: insertBillFresh 
	* @Description:插入订单
	* @param:@param uid
	* @param:@param cid
	* @param:@param appversion
	* @param:@param denomination
	* @param:@param postPrice
	* @param:@param ra
	* @param:@param totalPrice
	* @param:@param totalIntegral
	* @param:@param totalNum
	* @param:@return
	* @return:Long返回类型 
	* @throws
	 */
	private Long insertBillFresh(String uid, Integer cid,String appversion, Double denomination, Double postPrice,ReceivingAddress ra,Double totalPrice,Double totalIntegral,Integer totalNum,String appSource){
		//通过用户ID查询用户详情
		Urs urs = ursDao.queryUrsByUid(Integer.parseInt(uid));
		//创建父订单,存在收货地址才 创建订单
		BillFresh bf = new BillFresh();
		bf.setUid(Integer.parseInt(uid));//用户ID
		bf.setNname(ObjectUtils.toString(urs.getNname()));//用户昵称
		bf.setPhoneid(ObjectUtils.toString(urs.getPhone()));//用户电话号码
		bf.setWarenum(totalNum);//购买商品总数
		bf.setMoney(BigDecimal.valueOf(totalPrice));//消费总金额,减去优惠卷之后的总价格
		bf.setIntegral(totalIntegral);//消费总积分
		bf.setExpress("快递");//配送方式
		String address = ra.getProvince()+ra.getCity()+ra.getAreaname()+ra.getAddress();
		bf.setAddress(address);//配送地址
		bf.setTel(ObjectUtils.toString(ra.getPhoneid()));//联系电话
		bf.setMobile(ObjectUtils.toString(ra.getPhoneid()));//联系手机，配送时的联系方式
		bf.setStatus(0);//待支付
		bf.setWstatus(0);//未发货
		bf.setSdate(DateUtil.now());//下单时间
		bf.setHstatus(0);
		bf.setAppSourceStatus(EnumUtil.getEnumCode(ConstantDictionary.AppSourceState.class, appSource));
		//客户端类型
		if(appversion.toUpperCase().contains("IOS")){
			bf.setPhoneType((byte)2);//IOS端
		}else{
			bf.setPhoneType((byte)1);//Android端
		}
		bf.setDstatus(0);//正常
		bf.setUsername(ObjectUtils.toString(ra.getUsername()));//收货人姓名
		bf.setFreight(BigDecimal.valueOf(postPrice));//运费
		bf.setCuser(BigDecimal.valueOf(denomination));//优惠卷面额
		//优惠卷ID
		if(cid != null){
			bf.setCdid(cid);
		}
		bf.setCityid(ra.getCityid());//城市
		try{
			marketBillFreshDao.insertSelective(bf);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return bf.getBid();
	}


	/**
	 * 
	* @Title: orderSettlement 
	* @Description:订单结算(新)
	* @param:@param orderSettlementRequest
	* @param:@return
	* @return:Object返回类型 
	* @throws
	 */
	@Override
	public Object orderSettlement(OrderSettlementRequest orderSettlementRequest) {
		
		Map<Object,Object> result = new HashMap<Object,Object>();
		
		//获取用户ID
		String uid = ObjectUtils.toString(sessionTokenService.getStringForValue(orderSettlementRequest.getSessiontoken()));
		
		//获取用户购买的商品详情
		List<ProductInfo> products = getProductInfoList(orderSettlementRequest,uid);
		
		if(products.isEmpty()){
			return new BaseResponse(ResponseCode.FAILURE,"购物车没有商品,去商场逛逛吧");
		}
		
		result.put("products", initProductInfo(products));
		
		//组装购买商品购物车id组合
		StringBuilder sb = new StringBuilder();
		for(ProductInfo p : products){
			sb.append(p.getCartId()).append(",");
		}
		result.put("cartids", sb.toString());
		//获取用户积分
		Double integral = getIntegral(uid);
		if(integral == null){
			return new BaseResponse(ResponseCode.FAILURE,"积分不够，无法购买");
		}
		
		result.put("integral", integral);//用户可用积分
		
		/**
		 * 获取用户收货地址信息,有则显示,无则显示提示添加收货地址标语
		 */
		ReceivingAddress ra = getReceivingAddress(uid, orderSettlementRequest.getRid());
		
		Integer cityid = null;//用户收货城市ID
		String cityname = null;//用户收货城市名称
		
		if(ra == null){
			result.put("isAddress", 0);//用户是否有收货地址 0 否 1 是
		}else{
			result.put("isAddress", 1);//用户是否有收货地址 0 否 1 是
			result.put("rid", ObjectUtils.toString(ra.getId()));//收件地址ID
			result.put("address", ObjectUtils.toString(ra.getAddress()));//收件人详细地址
			result.put("province", ObjectUtils.toString(ra.getProvince()));//收件人所在省名称
			result.put("city", ObjectUtils.toString(ra.getCity()));//收件人所在市名称
			result.put("area", ObjectUtils.toString(ra.getAreaname()));//收件人所在区名称
			result.put("phone", ObjectUtils.toString(ra.getPhoneid()));//收件人联系电话
			result.put("username", ObjectUtils.toString(ra.getUsername()));//收件人姓名
			
			cityid = ra.getCityid();//收货地区ID
			cityname = ra.getCity();
		}
		
		/**
		 * 商品总价格，商品总积分,购买商品总数量,商品总重量,购买商品供应商提供的运费模板,购买商品供应商id
		 */
		Double denomination = 0.00;//优惠卷面额
		Double totalPrice = 0.00;//商品总价格
		Double totalIntegral = 0.00;//商品总积分
		Double totalNum = 0.00;//商品购买总数量
		Set<Integer> expTids = new HashSet<Integer>();//运费模板id集合
		List<Integer> supplierIds = new ArrayList<Integer>();//供应商id集合
		for(ProductInfo product : products){
			//商品价格 = 商品单价 * 商品个数
			Double price = product.getCash().doubleValue()*product.getNum();
			//商铺积分= 商品单积分 * 商品个数
			Double coin = product.getIntegral().doubleValue()*product.getNum();
			//加价
			Double amount = product.getAmount().doubleValue()*product.getNum();
			//商品重量 = 商品单重量 * 商品个数
			
			totalPrice += (price+amount);
			totalIntegral += coin;
			totalNum += product.getNum();
			
			Integer tid = product.getExptid();//运费模板id
			if(tid != null){
				expTids.add(tid);
			}
			
			Integer gxId = product.getSupplierid();//供应商id
			
			if(gxId != null){
				supplierIds.add(gxId);
			}
			
		}
		
		result.put("totalPrice", new BigDecimal(totalPrice).setScale(2, BigDecimal.ROUND_HALF_UP));
		result.put("totalIntegral", new BigDecimal(totalIntegral).setScale(2, BigDecimal.ROUND_HALF_UP));
		result.put("totalNum", totalNum);
		result.put("isIntegral", 1);//积分是否够用 0否 1是
		
		//判断用户积分是否购买
		boolean isIntegral = ArithUtil.sub(integral, totalIntegral) < 0;
		if(isIntegral){
			result.clear();
			result.put("integral", "你当前剩余积分为"+integral+",");
			result.put("totalIntegral", "所需支付积分为"+totalIntegral);
			result.put("isIntegral", 0);//积分是否够用 0否 1是
			response = new MapResponse(ResponseCode.SUCCESS, "积分不足,无法支付");
			response.setResponse(result);
			return response;
		}
		
		/**
		 * 计算运费
		 * 运费计算方式:
		 * 		1:普通计算方式(包含普通商品、无可用巴马水卷的含巴马水商品)
		 * 		2:特殊计算方式(购买巴马水)
		 */
		Double postPrice = 0.00;//运费
		
		//默认为普通计算方式
		if(cityid != null && expTids.size() > 0){
			for(Integer tid : expTids){
				
				boolean isFree = false;
				
				Double weigth = 0.0;//重量
				
				Double price = 0.0;//价格
				
				for(ProductInfo product : products){
					
					if(tid == product.getExptid()){
						//总重量
						weigth += 
							ArithUtil.mul(product.getExpweight(), product.getNum().doubleValue());
						//总价格
						price += 
							ArithUtil.mul(ArithUtil.add(product.getCash().doubleValue(), product.getAmount().doubleValue()),product.getNum().doubleValue());
					}
				}
				
				isFree = isFree(tid, cityid, weigth, price);
				Double freit = 0.00;
				if(isFree){
					//不包邮
					freit = getPostPrice(tid, weigth,cityid,cityname);
				}
				
				postPrice += freit;
			}
		}
		
		//优惠卷
		Map<Object,Object> map = getDenomation(uid, totalPrice, orderSettlementRequest.getCdids(), supplierIds);
		denomination = Double.parseDouble(map.get("denomination").toString());
		result.put("isCoupon", map.get("isCoupon"));
		
		if(Integer.parseInt(map.get("isUserBms").toString()) == 1){
			postPrice = Double.parseDouble(map.get("bmsfreit").toString());
		}
		
		result.put("postPrice", postPrice);//运费
		result.put("denomination", map.get("couponStatus"));//优惠卷
		
		//最终支付的价格
		Double payPrice = ArithUtil.sub(ArithUtil.add(totalPrice,postPrice), denomination);
		
		result.put("payPrice", payPrice<0?0.00:payPrice);
		//获取用户鸟币
		Map<String,Object> birdmap = null;
		try {
			birdmap = liveGiftsInfoService.getLiveWalletBlance(uid);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("获取用户鸟币钱包异常 ");
		}
		
		if(birdmap == null || birdmap.isEmpty()){
			return new BaseResponse(ResponseCode.FAILURE,"获取用户鸟币钱包异常");
		}
		
		Double zbalance = Double.parseDouble(birdmap.get("zbalance").toString());//鸟币余额
		Double limitBalance = Double.parseDouble(birdmap.get("limitBalance").toString());//鸟币支付限额
		Double balance = Double.parseDouble(birdmap.get("commision").toString());//鸟豆
		
		String restrictive = ObjectUtils.toString(birdmap.get("restrictive"));//是否开启鸟币消费余额限制001否002是
		
		if(StringUtils.isNotEmpty(restrictive) && restrictive.equals("002")){
			zbalance = 
				ArithUtil.sub(zbalance, limitBalance) <= 0 ? 0 : ArithUtil.sub(zbalance, limitBalance);
		}
		
		//是否有鸟豆 0 否  1是 
		int isBirdBeans = balance > 0.0 ? 1 : 0;
		//是否有足够的鸟币支付 0 否  1 是
		int isCoin = ArithUtil.sub(zbalance, payPrice) >= 0 ? 1 : 0;
		
		result.put("coin", zbalance);//用户可用鸟币
		result.put("isCoin", isCoin);//鸟币支付
		result.put("isBirdBeans", isBirdBeans);//是否有鸟豆 0 否  1是 2其他
		result.put("liveWalletZbalanceLock", birdmap.get("zbalanceLock"));//用户可用鸟币
		response = new MapResponse(ResponseCode.SUCCESS, "成功");
		response.setResponse(result);
		return response;
	}
	
	private List<Map<Object,Object>> initProductInfo(List<ProductInfo> products){
		List<Map<Object,Object>> list = new ArrayList<Map<Object,Object>>();
		//用户购买的商品
		for(ProductInfo product : products){
			Map<Object,Object> map = new HashMap<Object,Object>();
			map.put("pid", product.getPid());//商品ID
			map.put("codeid", product.getCodeid());//商品ID
			map.put("pname", product.getPname());//商品名称
			map.put("pimage", fileUrl+product.getBreviary());//商品缩略图
			map.put("spec", product.getAttrVals());//商品规格
			Double price = ArithUtil.add(product.getCash().doubleValue(),product.getAmount().doubleValue());
			map.put("price", price);//商品价格
			map.put("pintegral", product.getIntegral());//商品所需积分
			map.put("num", product.getNum());//商品购买数量
			map.put("cartid",product.getCartId());//商品购买ID
			list.add(map);
		}
		return list;
	}
	
	/**
	 * 
	* @Title: addProductBill 
	* @Description: 插入订单产品表(新)
	* @param:@param bid
	* @param:@param products
	* @param:@param tids
	* @param:@param areaid
	* @param:@param areaname
	* @return:void返回类型 
	* @throws
	 */
	public void addProductBill(Long bid,List<ProductInfo> products,Set<Integer> tids,Integer areaid,String areaname) {
		
		for(Integer id : tids){
			
			Double totalPrice = 0.0;//商品总价格
			
			Double totalWeight = 0.0;//商品总重量
			
			Integer tid = null;//运费模板
			
			boolean isFree = false;//包邮
			
			boolean isExceptId = false;//是否专用水的
			
			Double postPrice = 0.0;//运费
			
			Integer billId = null;//商品唯一标识
			
			for(ProductInfo product : products){
				
				if(id == product.getExptid()){
					
					Double price = 
						ArithUtil.mul(ArithUtil.add(product.getCash().doubleValue(), product.getAmount().doubleValue()), product.getNum().doubleValue());
					
					Double weight = 
						ArithUtil.mul(product.getExpweight(), product.getNum().doubleValue());
					
					ProductBill pb = new ProductBill();
					pb.setBid(bid);//父订单ID
					pb.setCodeid(product.getCodeid().intValue());//商品标识ID
					pb.setWarenum(product.getNum());//购买商品数量
					pb.setActivityid(product.getActivityId());//活动ID
					pb.setPname(product.getPname());//商品名称
					pb.setRdate(DateUtil.now());//创建时间
					//商品现金价格
					Double tPrice = ArithUtil.add(product.getCash().doubleValue(), product.getAmount().doubleValue());
					pb.setPrice(BigDecimal.valueOf(ArithUtil.add(tPrice, product.getIntegral().doubleValue())));
					pb.setGoodsname(product.getGoodsname());//商品促销活动名称
					pb.setBreviary(product.getBreviary());//商品缩略图
					pb.setSuttle(product.getSuttle());//商品净重
					pb.setSupplierId(product.getSupplierid()==null?0:product.getSupplierid().longValue());//供应商ID
					pb.setIntegral(product.getIntegral());//最高积分抵扣单价
					pb.setPurchaseprice(product.getPurchaseprice());//采购价
					pb.setAttrStr(product.getAttrVals());//规格信息
					pb.setAttrids(product.getAttrIds());
					pb.setAttrAmount(product.getAmount());//规格加价
					pb.setExpweight(product.getExpweight());
					pb.setExptid(product.getExptid());
					//插入商铺消费信息
					productBillDao.insertSelective(pb);
					
					totalPrice += price;
					totalWeight += weight;
					
					Integer supplierId = product.getSupplierid();//供应商ID
					if(supplierId != 27){
						isExceptId = true;
					}
					
					tid = product.getExptid();//运费模板ID
					
					billId = pb.getId();
				}
			}
			
			//默认运费为普通运费计算模式
			isFree = isFree(tid, areaid, totalWeight, totalPrice);//是否为包邮
			
			if(isFree){
				//非包邮
				postPrice = getPostPrice(tid, totalWeight,areaid,areaname);
			}
			
			
			if(!isExceptId){
				//当购买商品为巴马水时
				//通过订单查询使用的优惠卷信息
				List<Map<Object,Object>> coupons = couponDetailDao.findAllCouponsByBid(String.valueOf(bid));
				//巴马水只可以使用一张
				if(coupons != null && coupons.size() > 0){
					//优惠卷ID
					Integer cid = Integer.parseInt(coupons.get(0).get("cid").toString());
					Double denomination = Double.parseDouble(coupons.get(0).get("denomination").toString());
					
					//通过配置文件获取巴马水信息
					try{
						String waterCoupons = propertiesUtil.getValue("waterCoupons", "conf_integral_pay.properties");
						
						if(StringUtils.isNotEmpty(waterCoupons)){
							
							JSONArray json = JSONObject.parseArray(waterCoupons);
							
							for(int i=0; i<json.size();i++){
							
								JSONObject object = json.getJSONObject(i);
								
								Integer waterCouponCid = Integer.parseInt(object.getString("cid"));//巴马水优惠卷ID
								
								Double waterCouponDenomination = Double.parseDouble(object.getString("denomination"));//巴马水优惠卷面额
								
								Double waterPostPrice = Double.parseDouble(object.getString("postPrice"));//运费
								
								if(cid.compareTo(waterCouponCid) ==0 || denomination.compareTo(waterCouponDenomination) == 0){
									//使用巴马水优惠卷产生的运费
									postPrice = waterPostPrice;
								}
							}
						}
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}
			
			ProductBill productBill = productBillDao.selectByPrimaryKey(billId);
			
			productBill.setFreight(BigDecimal.valueOf(postPrice));//商品运费
			
			productBillDao.updateByPrimaryKeySelective(productBill);
		}
	}

}


