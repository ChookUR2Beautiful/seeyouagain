/**
 * 2016年5月23日 下午7:44:30
 */
package com.xmniao.xmn.core.xmer.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.xmniao.xmn.core.api.controller.xmer.StoreInfoModifyApi;
import com.xmniao.xmn.core.live.service.LiveHomeV2Service;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.Constant;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.OrderProcessInfoRequest;
import com.xmniao.xmn.core.common.request.PayResultRequest;
import com.xmniao.xmn.core.common.request.SaasSignRequest;
import com.xmniao.xmn.core.common.request.xmer.SaasPayRequest;
import com.xmniao.xmn.core.util.ArithUtil;
import com.xmniao.xmn.core.util.ClientCustomSSL;
import com.xmniao.xmn.core.util.DateUtil;
import com.xmniao.xmn.core.util.SaasBidType;
import com.xmniao.xmn.core.util.Signature;
import com.xmniao.xmn.core.xmer.dao.SaasOrderDao;
import com.xmniao.xmn.core.xmer.dao.SellerInfoDao;
import com.xmniao.xmn.core.xmer.dao.XmerDao;
import com.xmniao.xmn.core.xmer.entity.SaasSignOrder;
import com.xmniao.xmn.core.xmer.entity.Xmer;

/**
 * @项目名称：xmnService
 * @类名称：SaasOrderService
 * @类描述：saas订单处理
 * @创建人： zhangchangyuan
 * @创建时间 2016年5月23日 下午7:44:30
 * @version
 */
@Service
public class SaasOrderService {

	private Logger log = Logger.getLogger(SaasOrderService.class);
	
	/**
	 * 注入redis缓存
	 */
	@Autowired
	private SessionTokenService sessionTokenService;
	
	@Autowired
	private SellerInfoDao sellerInfoDao;
	
	@Autowired
	private SaasOrderDao saasOrderDao;
	
	@Autowired
	private XmerDao xmerDao;
	
	@Autowired
	private String payDomain;	//支付地址
	
	@Autowired
	private String paySecret;	//加密字符串
	
	@Autowired
	private String fileUrl;
	
	@Autowired
	private XmerService xmerService;

	@Autowired
	private StoreInfoService storeInfoService;
	@Autowired
	private LiveHomeV2Service liveHomeV2Service;
	/**
	 * 
	* @Title: createSaasOrder
	* @Description: 寻蜜客签约创建saas订单
	* @return Object
	 */
	public Object createSaasOrder(SaasSignRequest saasSignRequest){
		try {
			String sessionToken = saasSignRequest.getSessiontoken();
			String uid =  sessionTokenService.getStringForValue(sessionToken)+"";
			Integer sendType = 	saasSignRequest.getSendtype();
			saasSignRequest.setUid(Integer.valueOf(uid));
			//商铺名称，电话号码，详细地址不相同时
			if(!(saasSignRequest.getPhoneid().matches("^(1[\\d]{10})"))){
				return new BaseResponse(ResponseCode.FAILURE,"手机号码不合法");
			}
			if(!saasSignRequest.getConsume().matches("[0-9]*(\\.?)[0-9]*")){
				return new BaseResponse(ResponseCode.FAILURE,"月消费金额不合法");
			}
			//验证店铺信息是否重复
			Integer counts = checkSellerInfoIsRepeat(saasSignRequest);
			if(counts==null||counts == 0){
				//查询省，市，区等编号
				getSellerAddress(saasSignRequest);
				//设置默认值
				saasSignRequest.setXmn_service(1);//默认同意寻蜜鸟协议
				saasSignRequest.setData_source(3);//数据来源：寻蜜客签约
				saasSignRequest.setIsonline(0);//未上线
				saasSignRequest.setAgio_type(1);//折扣类型 默认1
				saasSignRequest.setUid(Integer.valueOf(uid));
				saasSignRequest.setSigndate(new Date());
				saasSignRequest.setTradename(saasSignRequest.getBewrite());//二级类别名称
				//查询商铺行业经营类别名称
				Map<Object,Object> tradeMap = new HashMap<Object,Object>();
				tradeMap.put("tid", saasSignRequest.getCategory());//一级id
				String typename = sellerInfoDao.queryTradeName(tradeMap);
				saasSignRequest.setTypename(typename);
				Map<Object, Object> paramMap = new HashMap<>();
				paramMap.put("sdate", DateUtil.format(DateUtil.now(),"yyyy-MM-dd HH:mm:ss"));
				if(sendType==1){	//提交审核
					saasSignRequest.setStatus(4);//	未签约
					saasSignRequest.setOperadate(new Date());//更新时间
					//插入信息表
					sellerInfoDao.insertSeller(saasSignRequest);
					Integer sellerid  = saasSignRequest.getId();
					//保存店铺的人均消费
					paramMap.put("sellerid", sellerid);
					paramMap.put("consume", saasSignRequest.getConsume());
					sellerInfoDao.insertSellerInfoToDetail(paramMap);//添加个人消费
					paramMap.put("longitude", saasSignRequest.getLongitude());//经度
					paramMap.put("latitude", saasSignRequest.getLatitude());//纬度
					sellerInfoDao.insertSellerLandmark(paramMap);//添加经度和纬度
					//保存折扣到折扣表
					paramMap.put("agio", saasSignRequest.getAgio());//折扣
					sellerInfoDao.insertSellerAgio(paramMap);//插入折扣表
					sellerInfoDao.insertSellerAgioRecord(paramMap);//插入折扣记录表
					//创建订单
					SaasSignOrder saasSignOrder = new SaasSignOrder();
					String orderId = "02"+SaasBidType.getBid();
					saasSignOrder.setId(orderId);
					saasSignOrder.setUid(String.valueOf(uid));
					saasSignOrder.setAmount(Constant.SIGN_AMOUNT);
					saasSignOrder.setSellerid(String.valueOf(sellerid));
					saasSignOrder.setSellername(saasSignRequest.getSellername().trim());
					saasSignOrder.setStatus(0);//存入草稿
					
					//查询寻蜜客最早购买的saas套餐的未使用完的订单信息
					Map<Object,Object> orderMap = saasOrderDao.queryOrderNums(Integer.valueOf(uid));
					if(orderMap==null||orderMap.size()==0){
						return new BaseResponse(ResponseCode.SAAS_STOCK_EMPTY, "SAAS套餐已使用完毕，请重新购买");
					}
					//签约店铺使用的套餐订单号
					saasSignOrder.setSaasOrdersn(orderMap.get("ordersn").toString());
					saasSignOrder.setZdate(DateUtil.format(DateUtil.now()));
					saasSignOrder.setCreateDate(DateUtil.format(DateUtil.now()));
					//添加签约店铺订单
					saasOrderDao.addSaasSignOrder(saasSignOrder);
					int soldnums = Integer.valueOf(orderMap.get("soldnums").toString())+1;//卖出订单数量(原有的基础上+1)
					int stock = Integer.valueOf(orderMap.get("stock").toString())-1;//库存数量(原有的基础上-1)
					orderMap.put("soldnums", soldnums);
					orderMap.put("stock", stock);
					saasOrderDao.updateOrderNums(orderMap);//根据订单id更新卖出数量和库存量
					xmerDao.addSignNum(Integer.valueOf(uid));	//新增签约数量
					//更新寻蜜客头衔map
					Map<Object,Object> levelMap = new HashMap<Object,Object>();
					levelMap.put("uid", uid);
					levelMap.put("achievement", getXmerLevelName(Integer.parseInt(uid)));
					saasOrderDao.modifyXmer(levelMap);//更新寻蜜客头衔
					MapResponse response = new MapResponse(ResponseCode.SUCCESS, "提交成功");
					Map<Object, Object> result = new HashMap<>();
					result.put("amount", saasSignOrder.getAmount());
					result.put("orderid", orderId);
					result.put("sellerid", sellerid);
					result.put("ordername", "寻蜜客签约商户");
					result.put("source", "002");
					result.put("uid", uid);
					response.setResponse(result);
					return response;
					
				}else{	//保存草稿
					saasSignRequest.setStatus(0);//	保存草稿，不是审核中
					sellerInfoDao.insertSeller(saasSignRequest);
					//保存店铺的人均消费
					paramMap.put("sellerid", saasSignRequest.getId());
					paramMap.put("consume", saasSignRequest.getConsume());
					sellerInfoDao.insertSellerInfoToDetail(paramMap);//添加个人消费
					paramMap.put("longitude", saasSignRequest.getLongitude());//经度
					paramMap.put("latitude", saasSignRequest.getLatitude());//纬度
					sellerInfoDao.insertSellerLandmark(paramMap);//添加经度和纬度
					return new BaseResponse(ResponseCode.SUCCESS, "保存成功");
				}
			}
			return new BaseResponse(ResponseCode.SELLER_NAME_REPEAT,"商铺名称已存在，请重新填写");
		} catch (Exception e) {
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE, "提交失败，请重新提交");
		}
	}
	
	/**
	 * 
	* @Title: createSaasOrderTwo
	* @Description: 版本二:签约店铺
	* @return Object    返回类型
	* @author
	* @throws
	 */
	public Object createSaasOrderTwo(SaasSignRequest saasSignRequest){
		try {
			String sessionToken = saasSignRequest.getSessiontoken();
			String uid =  sessionTokenService.getStringForValue(sessionToken)+"";
			Integer sendType = 	saasSignRequest.getSendtype();
			saasSignRequest.setUid(Integer.valueOf(uid));
			//商铺名称，电话号码，详细地址不相同时
			if(!(saasSignRequest.getPhoneid().matches("^(1[\\d]{10})"))){
				return new BaseResponse(ResponseCode.FAILURE,"手机号码不合法");
			}
			if(!saasSignRequest.getConsume().matches("[0-9]*(\\.?)[0-9]*")){
				return new BaseResponse(ResponseCode.FAILURE,"月消费金额不合法");
			}
			Integer counts = checkSellerInfoIsRepeat(saasSignRequest);
			if(counts==null||counts == 0){
				//此处的省市区编号由前端传过来
				saasSignRequest.setProvince(saasSignRequest.getProvince());//省编号
				saasSignRequest.setCity(saasSignRequest.getCity());//市编号
				saasSignRequest.setArea(saasSignRequest.getArea());//区域编号
				saasSignRequest.setXmn_service(1);//默认同意寻蜜鸟协议
				saasSignRequest.setData_source(3);//数据来源：寻蜜客签约
				saasSignRequest.setIsonline(0);//未上线
				saasSignRequest.setAgio_type(1);//折扣类型 默认1
				saasSignRequest.setUid(Integer.valueOf(uid));
				saasSignRequest.setSigndate(new Date());
				saasSignRequest.setTradename(saasSignRequest.getBewrite());//二级类别名称
				//查询商铺行业经营类别名称
				Map<Object,Object> tradeMap = new HashMap<Object,Object>();
				tradeMap.put("tid", saasSignRequest.getCategory());//一级id
				String typename = sellerInfoDao.queryTradeName(tradeMap);
				saasSignRequest.setTypename(typename);
				Map<Object, Object> paramMap = new HashMap<>();
				paramMap.put("sdate", DateUtil.format(DateUtil.now(),"yyyy-MM-dd HH:mm:ss"));
				if(sendType==1){	//提交审核
					saasSignRequest.setStatus(4);//	未签约
					saasSignRequest.setOperadate(new Date());//更新时间
					//插入信息表
					sellerInfoDao.insertSeller(saasSignRequest);
					Integer sellerid  = saasSignRequest.getId();
					//保存店铺的人均消费
					paramMap.put("sellerid", sellerid);
					paramMap.put("consume", saasSignRequest.getConsume());
					sellerInfoDao.insertSellerInfoToDetail(paramMap);//添加个人消费
					paramMap.put("longitude", saasSignRequest.getLongitude());//经度
					paramMap.put("latitude", saasSignRequest.getLatitude());//纬度
					sellerInfoDao.insertSellerLandmark(paramMap);//添加经度和纬度
					//保存折扣到折扣表
					paramMap.put("agio", saasSignRequest.getAgio());//折扣
					sellerInfoDao.insertSellerAgio(paramMap);//插入折扣表
					sellerInfoDao.insertSellerAgioRecord(paramMap);//插入折扣记录表
					//创建订单
					SaasSignOrder saasSignOrder = new SaasSignOrder();
					String orderId = "02"+SaasBidType.getBid();
					saasSignOrder.setId(orderId);
					saasSignOrder.setUid(String.valueOf(uid));
					saasSignOrder.setAmount(Constant.SIGN_AMOUNT);
					saasSignOrder.setSellerid(String.valueOf(sellerid));
					saasSignOrder.setSellername(saasSignRequest.getSellername().trim());
					saasSignOrder.setStatus(0);//存入草稿
					
					//查询寻蜜客最早购买的saas套餐的未使用完的订单信息
					Map<Object,Object> orderMap = saasOrderDao.queryOrderNums(Integer.valueOf(uid));
					if(orderMap==null||orderMap.size()==0){
						return new BaseResponse(ResponseCode.SAAS_STOCK_EMPTY, "SAAS套餐已使用完毕，请重新购买");
					}
					//签约店铺使用的套餐订单号
					saasSignOrder.setSaasOrdersn(orderMap.get("ordersn").toString());
					saasSignOrder.setZdate(DateUtil.format(DateUtil.now()));
					saasSignOrder.setCreateDate(DateUtil.format(DateUtil.now()));
					
					int soldnums = Integer.valueOf(orderMap.get("soldnums").toString())+1;//卖出订单数量(原有的基础上+1)
					//优先扣除退款套数
					int returnnums = Integer.parseInt(orderMap.get("returnnums").toString());
					int stock = Integer.valueOf(orderMap.get("stock").toString());
					if (returnnums == 0) {
						stock = stock - 1;//库存数量(原有的基础上-1)
						//'SAAS来源 0 正常库存 1 销售退回库存'
						saasSignOrder.setSaasSource(0);
					}else {
						returnnums = returnnums - 1;
						//'SAAS来源 0 正常库存 1 销售退回库存'
						saasSignOrder.setSaasSource(1);
					}
					
					//添加签约店铺订单
					saasOrderDao.addSaasSignOrder(saasSignOrder);
					
					int version=Integer.valueOf(orderMap.get("version").toString());
					orderMap.put("soldnums", soldnums);
					orderMap.put("stock", stock);
					orderMap.put("returnnums", returnnums);
					orderMap.put("version", version);//版本控制，防止并发
					int updateOrderFlag=saasOrderDao.updateOrderNums(orderMap);//根据订单id更新卖出数量和库存量
					if(updateOrderFlag==0){ //如果更新失败
						paramMap.put("sellerid",sellerid);
						paramMap.put("status", 0);
						sellerInfoDao.modifySeller(paramMap);
						return new BaseResponse(ResponseCode.XMER_VERSION_ERROR,"提交审核出错，请刷新重新提交！");
					}
					
					xmerDao.addSignNum(Integer.valueOf(uid));	//新增签约数量
					//更新寻蜜客头衔map
					Map<Object,Object> levelMap = new HashMap<Object,Object>();
					levelMap.put("uid", uid);
					levelMap.put("achievement", getXmerLevelName(Integer.parseInt(uid)));
					saasOrderDao.modifyXmer(levelMap);//更新寻蜜客头衔
					MapResponse response = new MapResponse(ResponseCode.SUCCESS, "提交成功");
					Map<Object, Object> result = new HashMap<>();
					result.put("amount", saasSignOrder.getAmount());
					result.put("orderid", orderId);
					result.put("sellerid", sellerid);
					result.put("ordername", "寻蜜客签约商户");
					result.put("source", "002");
					result.put("uid", uid);
					response.setResponse(result);
					return response;
					
				}else{	//保存草稿
					saasSignRequest.setStatus(0);//	保存草稿，不是审核中
					sellerInfoDao.insertSeller(saasSignRequest);
					//保存店铺的人均消费
					paramMap.put("sellerid", saasSignRequest.getId());
					paramMap.put("consume", saasSignRequest.getConsume());
					sellerInfoDao.insertSellerInfoToDetail(paramMap);//添加个人消费
					paramMap.put("longitude", saasSignRequest.getLongitude());//经度
					paramMap.put("latitude", saasSignRequest.getLatitude());//纬度
					sellerInfoDao.insertSellerLandmark(paramMap);//添加经度和纬度
					return new BaseResponse(ResponseCode.SUCCESS, "保存成功");
				}
			}
			return new BaseResponse(ResponseCode.SELLER_NAME_REPEAT,"商铺名称已存在，请重新填写");
		} catch (Exception e) {
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE, "提交失败，请重新提交");
		}
	}
	
	/**
	 * 
	* @Title: queryPayResult
	* @Description: 订单支付状态接口实现类
	* @return Object    返回类型
	* @throws
	 */
	public Object queryPayResult(PayResultRequest payResultRequest){
			try {
				String uid = sessionTokenService.getStringForValue(payResultRequest.getSessiontoken()).toString();
				if(uid.equals("") && uid.equals("null")){
					return new BaseResponse(ResponseCode.TOKENERR,"token已经失效，请重新登录");
				}
				Map<Object,Object> orderResult = null;
				if (payResultRequest.getType() == 1) {
					//查询saas订单套餐
					orderResult = saasOrderDao.querySaasOrderInfoByOrdersn(payResultRequest.getOrderid());
				}else {
					//查询签约商户订单
					orderResult = saasOrderDao.queryOrderByOrdersn(payResultRequest.getOrderid());
				}
				if (orderResult == null || orderResult.size()==0) {
					return new BaseResponse(ResponseCode.FAILURE,"查询失败,订单号不存在");
				}
				Integer status 	= Integer.parseInt(String.valueOf(orderResult.get("status")));			
				MapResponse response = new MapResponse(ResponseCode.SUCCESS,"成功");
				Map<Object, Object> map = new HashMap<>();
				if(status==0){
					map.put("ispay", "1");//未支付
					response.setResponse(map);
					return response;	
				}else if(status==1){
					map.put("ispay", "2");//支付成功
					response.setResponse(map);
					return response;	
					
				}else{
					map.put("ispay", "3");//支付失败
					response.setResponse(map);
					return response;	
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
				return new BaseResponse(ResponseCode.FAILURE, "查询失败，请联系管理员");
			}
	}

	/**
	 * 
	* @Title: anotherPay
	* @Description: 寻蜜客签约店铺代付
	* @return Object    返回类型
	* @author
	* @throws
	 */
	public Object anotherPay(OrderProcessInfoRequest orderProcessInfoRequest) {
		try {
			Integer uid = liveHomeV2Service.getUid(orderProcessInfoRequest.getSessiontoken());
			if (uid == null) {
				return new BaseResponse(ResponseCode.BILL_PAY_REPEAT, "sessiontoken失效，请重新登陆");
			}
			//查询是否存在该订单号
			Map<Object, Object> saasOrderMap = saasOrderDao.queryOrderByOrdersn(orderProcessInfoRequest.getBid());
			if (saasOrderMap != null && saasOrderMap.size() > 0) {
				if ("0".equals(saasOrderMap.get("status").toString())) {
					Map<Object, Object> parammMap = new HashMap<>();
					parammMap.put("ordersn", orderProcessInfoRequest.getBid());
					parammMap.put("paytype", "10000000");	//代付支付类型
					parammMap.put("samount", 0);
					parammMap.put("status", 1);
					parammMap.put("zdate", DateUtil.format(DateUtil.now(), "yyyy-MM-dd HH:mm:ss"));
					//修改saas签约订单状态
					int saasOrderResult = saasOrderDao.modifySaasOrder(parammMap);
					int sellerResult = 0;
					if (StringUtils.isEmpty(saasOrderMap.get("sellerid").toString())) {
						return new BaseResponse(ResponseCode.SELLER_NO_NOT_EXIST, "店铺信息不存在,请联系客服员");
					}
					parammMap.clear();
					Integer sellerid = Integer.parseInt(saasOrderMap.get("sellerid").toString());
					parammMap.put("sellerid", sellerid);
					parammMap.put("status", 1);
					//修改店铺信息状态
					sellerResult = sellerInfoDao.modifySeller(parammMap);
					if (saasOrderResult == 0 && sellerResult == 0) {
						return new BaseResponse(ResponseCode.FAILURE, "代付失败,请联系客服员");
					}
					String saas_ordersn = saasOrderMap.get("saas_ordersn").toString();
					Integer updateOrderFlag = storeInfoService.decrementBySaas_ordersn(saas_ordersn);
					if(updateOrderFlag == 0){ //如果更新失败
						log.warn("代付，更新库存失败" + saas_ordersn);
						parammMap.put("status", 0);
						sellerInfoDao.modifySeller(parammMap);
						parammMap.put("status", 0);
						saasOrderDao.modifySaasOrder(parammMap);
						return new BaseResponse(ResponseCode.XMER_VERSION_ERROR,"代付失败,请联系客服员");
					}
					storeInfoService.updateXmer(uid);  // 更新等级
				}else {
					return new BaseResponse(ResponseCode.BILL_PAY_REPEAT, "该订单号已经支付了,请勿重复支付");
				}
			}else {
				return new BaseResponse(ResponseCode.BILL_NO_NOT_EXIST, "查无订单号,请确认后操作");
			}
			return new BaseResponse(ResponseCode.SUCCESS, "提交成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new BaseResponse(ResponseCode.FAILURE, "代付失败,请联系客服");
		}
	}
	
	/**
	 * 
	* @Title: checkSellerInfoIsRepeat
	* @Description: 查询店铺信息是否重复
	* @return Integer
	 */
	public Integer checkSellerInfoIsRepeat(SaasSignRequest saasSignRequest){
		Map<Object,Object> checkSellerInfoMap = new HashMap<Object,Object>();
		checkSellerInfoMap.put("sellername", saasSignRequest.getSellername().trim());
		checkSellerInfoMap.put("phoneid", saasSignRequest.getPhoneid().trim());
		checkSellerInfoMap.put("address", saasSignRequest.getAddress().trim());
		return saasOrderDao.checkSellerInfoIsRepeat(checkSellerInfoMap);
	}
	
	/**
	 * 
	* @Title: getProvince
	* @Description: 获取省编号
	* @return String    返回类型
	* @author liuzhihao
	* @throws
	 */
	private int getProvince(String province){
		Map<Object,Object> map = new HashMap<Object,Object>();
		Integer result = 0;
		if(StringUtils.isEmpty(province)){
			map.put("title", "广东");
			map.put("id", 0);
			try{
				result = sellerInfoDao.getAreaIdByName(map);//省编号
			}catch(Exception e){
				e.printStackTrace();
				result=0;
			}
		}else{
			if(province.contains("北京")){
				result = 1;
			}else if(province.contains("天津")){
				result = 21;
			}else if(province.contains("上海")){
				result  = 802;
			}else if(province.contains("重庆")){
				result = 2257;
			}else if(province.contains(" 省")){
				map.put("title",province.replace("省", ""));
				map.put("id", 0);
				result = sellerInfoDao.getAreaIdByName(map);//省编号
			}
		}
		return result;
	}
	
	/**
	 * 
	* @Title: getSellerAddress
	* @Description: 根据名称获取商铺省市区地址，查询不到默认为 广东省广州市天河区
	* @return void
	 */
	public void getSellerAddress(SaasSignRequest saasSignRequest){
		Map<Object,Object> areamap = new HashMap<Object,Object>();
			Integer province = Integer.valueOf(getProvince(saasSignRequest.getProvince()));//省编号
			Integer city =0;//市编号
			Integer area = 0;//区编号
			if(province == 0 || province == null){
				province = 1964;//默认为广东
				city = 1963;//默认为广州
				area = 1968;//默认为天河
			}else{
				areamap.put("id", province);
				if(StringUtils.isEmpty(saasSignRequest.getCity())){
					areamap.put("title",saasSignRequest.getProvince().replace("市", ""));
				}else{
					areamap.put("title",saasSignRequest.getCity().replace("市", ""));
				}
				city = sellerInfoDao.getAreaIdByName(areamap);//市编号 
				areamap.put("id", city);
				areamap.put("title",saasSignRequest.getArea().replace("区", "").replace("县", ""));
				area = sellerInfoDao.getAreaIdByName(areamap);
			}
			
			if (province != null) {
			saasSignRequest.setProvince(String.valueOf(province));
		}
		if (city != null) {
			saasSignRequest.setCity(String.valueOf(city));
		}
		if (area != null) {
			saasSignRequest.setArea(String.valueOf(area));
		}
	}
	
	/**
	 * 
	* @Title: getXmerLevelName
	* @Description: 获取寻蜜客的签约成就登记,通过卖出订单数量更新寻蜜客等级
	* @return String
	 */
	public String getXmerLevelName(Integer uid){
		List<Map<Object,Object>> levelList = saasOrderDao.queryXmerLevelList();//降序获取
		Xmer newXmer =	xmerDao.selectByUid(uid);
		//默认未最低级的称号
		String levelname = levelList.get(levelList.size()-1).get("levelname").toString();
		for(int i=0; i<levelList.size(); i++){
			if(newXmer.getSoldNums() >=Integer.valueOf(levelList.get(i).get("signnum").toString())){
				levelname = levelList.get(i).get("levelname").toString();
				break;
			}
		}
		return levelname;
	}

	/**
	 * 
	 * @Title:paySaasOrder
	 * @Description:saas套餐支付
	 * @param saasPayRequest
	 * @return Object
	 * 2017年5月5日下午2:01:36
	 */
	public Object paySaasOrder(SaasPayRequest saasPayRequest) {
		String sessionToken = saasPayRequest.getSessiontoken();
		String uid =  sessionTokenService.getStringForValue(sessionToken)+"";
		if(StringUtils.isEmpty(uid)){
			return new BaseResponse(ResponseCode.TOKENERR,"token已经失效，请重新登录");
		}
		
		String ordersn = saasPayRequest.getOrdersn();
		//根据订单编号查询订单金额
		Map<Object, Object> saasOrderInfo = saasOrderDao.querySaasOrderInfoByOrdersn(ordersn);
		if(saasOrderInfo==null || saasOrderInfo.size()<=0 || saasOrderInfo.get("amount")==null){
			return new BaseResponse(ResponseCode.PAY_FAILURE,"获取订单信息失败");
		}
		if(saasOrderInfo.get("status")==null || !saasOrderInfo.get("status").equals(0)){
			return new BaseResponse(ResponseCode.PAY_FAILURE,"订单状态已支付");
		}
		
		String am = saasOrderInfo.get("amount")+"";
		String ag = saasOrderInfo.get("agio")+"";
		
		if(StringUtils.isEmpty(am) || StringUtils.isEmpty(ag)){
			return new BaseResponse(ResponseCode.PAY_FAILURE,"获取订单金额和折扣失败");
		}
		BigDecimal amount = new BigDecimal(am);
		BigDecimal agio = new BigDecimal(ag);
		
		Map<String,String> map=new HashMap<>();
		map.put("orderSn",saasPayRequest.getOrdersn());
		map.put("amount",amount.multiply(agio).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
		map.put("paymentType",saasPayRequest.getPaymenttype());
		map.put("orderType",saasPayRequest.getOrdertype()+"");
		map.put("source",saasPayRequest.getSource());
		map.put("subject","购买saas套餐");
		map.put("uid",uid);
		//加密字符串
		map.put("sign", Signature.sign(map,paySecret));
		
		String request = "";
		//判断是否是V客寻蜜客   
		String consumerType ="1";
		List<Integer> xmerTypeList= xmerService.identityList(Integer.parseInt(uid));
		if (xmerTypeList.contains(4)) {
			consumerType = "2";
		}
		map.put("consumerType", consumerType); // 客户类型（1：个人寻蜜客2:V客寻蜜客3:中脉寻蜜客）
		
        for(Entry<String, String> entry : map.entrySet()){
        	request += "&" + entry.getKey() + "=" + entry.getValue();
        }
        
        request = request.substring(1, request.length());
        String url=payDomain+"/unified_order/prepare"+""+"?"+request;
        String result="";
        log.info("请求支付url："+url);
    
		try {
			result = ClientCustomSSL.doGet(url);
			JSONObject json=JSONObject.parseObject(result);
			if(json.getString("state").equals("200")){
				//setMaterialSaleNum(payRequest.getOrdersn());
				Map<Object,Object> resultMap = new HashMap<Object,Object>();
				resultMap.put("payJson", json.getString("result"));
				MapResponse mapResponse=new MapResponse(ResponseCode.SUCCESS,"成功");
				mapResponse.setResponse(resultMap);
				return mapResponse ;
			}else{
				return new BaseResponse(ResponseCode.FAILURE,json.getString("info")); 
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new BaseResponse(ResponseCode.PAY_FAILURE,"支付失败");
		}
	}
	
}
