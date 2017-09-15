package com.xmniao.xmn.core.xmer.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xmniao.xmn.core.base.BaseRequest;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.ConstantDictionary;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.InquireRequest;
import com.xmniao.xmn.core.common.request.MaterialorderRequest;
import com.xmniao.xmn.core.common.request.PageRequest;
import com.xmniao.xmn.core.common.request.PayRequest;
import com.xmniao.xmn.core.common.request.RemarkRequest;
import com.xmniao.xmn.core.market.entity.pay.ReceivingAddress;
import com.xmniao.xmn.core.personal.dao.ReceivingAddressDao;
import com.xmniao.xmn.core.util.ArithUtil;
import com.xmniao.xmn.core.util.ClientCustomSSL;
import com.xmniao.xmn.core.util.DateUtil;
import com.xmniao.xmn.core.util.EnumUtil;
import com.xmniao.xmn.core.util.HttpConnectionUtil;
import com.xmniao.xmn.core.util.PropertiesUtil;
import com.xmniao.xmn.core.util.SaasBidType;
import com.xmniao.xmn.core.util.Signature;
import com.xmniao.xmn.core.xmer.dao.MaterialDao;
import com.xmniao.xmn.core.xmer.dao.SellerInfoDao;
import com.xmniao.xmn.core.xmer.dao.XmerDao;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：MaterialService   
* 类描述：   物料Service
* 创建人：xiaoxiong   
* 创建时间：2016年7月12日 下午8:08:18   
* @version    
*
 */
@Service
public class MaterialService {
	
	//日志
	private Logger log = Logger.getLogger(MaterialService.class);

	@Autowired
	private MaterialDao materialDao;
	
	@Autowired
	private SessionTokenService sessionTokenService;
	
	@Autowired
	private SellerInfoDao sellerDao;
	
	@Autowired
	private XmerDao xmerDao;
	
	@Autowired
	private String payDomain;	//支付地址
	
	@Autowired
	private String paySecret;	//加密字符串
	
	@Autowired
	private String fileUrl;

	
	//注入配置文件
	@Autowired
	private PropertiesUtil propertiesUtil;
	@Autowired
	private ReceivingAddressDao receivingAddressDao;
	/**
	 * 
	* @Title: materialList
	* @Description:查询物料列表 
	* @return Object    返回类型
	* @throws
	 */
	public Object materialList(PageRequest pageRequest) {
		try {
			String uid=sessionTokenService.getStringForValue(pageRequest.getSessiontoken())+"";
			if (StringUtils.isBlank(uid)) {
				return new BaseResponse(ResponseCode.TOKENERR, "token已失效,请重新登录");
			}
			pageRequest.setFileurl(fileUrl);
			Map<Object, Object> resultMap=new HashMap<>();
			List<Map<Object,Object>> materials=materialDao.materialList(pageRequest);
			resultMap.put("context", "预计到货时间3~5天，如需了解发货进度请联系客服");//描述旧版需要
			resultMap.put("freight", Double.valueOf(propertiesUtil.getValue("freight", "conf_xmer.properties")));//运费
			resultMap.put("data", materials);
			/*//用户的默认收货地址
			ReceivingAddress  userAddress=receivingAddressDao.selectUserDefaultAddress(uid);
			resultMap.put("userAddress", userAddress);*/
			MapResponse mapResponse=new MapResponse(ResponseCode.SUCCESS, "成功");
			mapResponse.setResponse(resultMap);
			return mapResponse;	
		} catch (Exception e) {
			e.printStackTrace();
			log.error("查询物料失败！");
			return new BaseResponse(ResponseCode.FAILURE, "查询物料失败");
		}
	}
		

	/**
	 * 
	* @Title: materialorder
	* @Description: 物料购买（下单）
	* @return Object    返回类型
	* @throws
	 */
	public Object materialorder(MaterialorderRequest materialOrderRequest) {
		String uid=sessionTokenService.getStringForValue(materialOrderRequest.getSessiontoken())+"";
		if (StringUtils.isBlank(uid)) {
			return new BaseResponse(ResponseCode.TOKENERR, "token已失效,请重新登录");
		}
		//查询寻蜜客信息
		Map<Object,Object> userMap=xmerDao.queryUserInfo(Integer.parseInt(uid));
		if(userMap==null||userMap.isEmpty()){
			return new BaseResponse(ResponseCode.FAILURE,"没有找到该寻蜜客信息。");
		}
		String ordersn="04"+SaasBidType.getBid();
		Double orderAmount = 0D;
		Integer sumQutatity=0;
		try {
		JSONArray jsonArray=JSONArray.parseArray(materialOrderRequest.getOrderitems());
		List<Map<Object,Object>> itemList=new ArrayList<>();
		for(int i=0;i<jsonArray.size();i++){
			Map<Object,Object> item=new HashMap<>();
			JSONObject json=jsonArray.getJSONObject(i);
			Integer id=json.getInteger("id");
			String name=json.getString("name");
			Double price=json.getDouble("price");
			Integer quantity=json.getInteger("quantity");
			if(id==null||id==0||name==null||name.equals("")||price==null||price==0||quantity==null||quantity==0){
				return new BaseResponse(ResponseCode.DATAERR,"提交的物料信息有误！"); 
			}
			item.put("id", id);
			item.put("name", name);
			item.put("price", price);
			item.put("quantity", quantity);
			item.put("ordersn", ordersn);
			itemList.add(item);
			orderAmount = ArithUtil.add(orderAmount, ArithUtil.mul(price, quantity));
			sumQutatity += quantity;
		}
		if (ArithUtil.add(orderAmount, materialOrderRequest.getFreight()) != materialOrderRequest.getAmount()) {
			return new BaseResponse(ResponseCode.DATAERR,"提交的物料信息有误！");
		}
		materialOrderRequest.setOrdersn(ordersn);	
		materialOrderRequest.setAppSourceStatus(EnumUtil.getEnumCode(ConstantDictionary.AppSourceState.class, materialOrderRequest.getAppSource()));
		setMaterialOrder(materialOrderRequest,userMap);			
		materialDao.addMaterOrder(materialOrderRequest);
		try {		
			materialDao.addMaterOrderItems(itemList);				
			} catch (Exception e) {
				e.printStackTrace();
				log.info("物料订单信息表错误");
				materialDao.delMaterial(ordersn);
			}
		} catch(Exception e) {
			e.printStackTrace();
			log.info("物料订单创建失败!"+e);
			return new BaseResponse(ResponseCode.FAILURE,"物料订单创建失败。");
		}
		MapResponse mapResponse=new MapResponse(ResponseCode.SUCCESS,"成功");
		Map<Object,Object> orderMap=new HashMap<>();
		orderMap.put("address", materialOrderRequest.getAddress());
        orderMap.put("amount",materialOrderRequest.getAmount());
		orderMap.put("phone", materialOrderRequest.getPhone());
		orderMap.put("name", materialOrderRequest.getUsername());
		orderMap.put("ordersn", ordersn);
		orderMap.put("freight", materialOrderRequest.getFreight());
		orderMap.put("goodsQuantity", sumQutatity);
		orderMap.put("goodsAmount", materialOrderRequest.getGoodsAmount());
		orderMap.put("remark", materialOrderRequest.getRemark());
		mapResponse.setResponse(orderMap);
		return mapResponse;
	}
	/**物料订单的信息
	 * @param materialOrderRequest
	 * @param userMap
	 */
	private void setMaterialOrder(MaterialorderRequest materialOrderRequest,Map<Object,Object> userMap) {
		//#{ordersn},#{uid},#{mid},#{createtime},#{username},#{phone},#{address},#{amount},#{freight}
		//用户id
		String uid=sessionTokenService.getStringForValue(materialOrderRequest.getSessiontoken())+"";
		if (StringUtils.isBlank(materialOrderRequest.getAddressId())) {
			//查询商户信息
			Map<Object,Object> sellerMap=sellerDao.querySellerInfoBySellerid(Integer.parseInt(materialOrderRequest.getMid()));
			//商铺地址
			String address=sellerMap.get("address")+"";
			materialOrderRequest.setAddress(address);
			materialOrderRequest.setUid(Integer.parseInt(uid));
			materialOrderRequest.setAddress(address);
			materialOrderRequest.setPhone(userMap.get("phone")+"");
			materialOrderRequest.setUsername(userMap.get("name")+"");
			materialOrderRequest.setCreatetime(new Date());
			materialOrderRequest.setFreight(materialOrderRequest.getFreight());
		}else {
			Map<Object,Object>  userAddress=receivingAddressDao.queryReceivingAddressById(Integer.parseInt(materialOrderRequest.getAddressId()));
			materialOrderRequest.setUid(Integer.parseInt(uid));
			materialOrderRequest.setAddress(userAddress.get("address")+"");
			materialOrderRequest.setPhone(userAddress.get("phoneId")+"");
			materialOrderRequest.setUsername(userAddress.get("username")+"");
			materialOrderRequest.setCreatetime(new Date());
			materialOrderRequest.setFreight(materialOrderRequest.getFreight());
		}
		
	}
			
	/**
	 * @throws Exception 
	 * 
	* @Title: materialPay
	* @Description: 物料支付接口
	* @return Object 返回类型
	* @throws
	 */
	public Object materialPay(PayRequest payRequest) {
		
		DecimalFormat  df   = new DecimalFormat("0.00");   
		String uid=sessionTokenService.getStringForValue(payRequest.getSessiontoken())+"";
		if(uid==null||uid.equals("")){
			return new BaseResponse(ResponseCode.DATA_NULL,"sessiontoken错误或已过期！");
		}
		Map<Object,Object> xmerMap=xmerDao.queryUserInfo(Integer.valueOf(uid));
		if(xmerMap==null){
			return new BaseResponse(ResponseCode.DATA_NULL,"查找用户信息失败！");
		}
		//验证订单信息合法
		Map<Object, Object> materialMap = materialDao.findOrderMateral(payRequest.getOrdersn());
		if (materialMap==null || materialMap.isEmpty()) {
			return new BaseResponse(ResponseCode.FAILURE,"物料订单不存在！");
		}else {
			Integer status = Integer.parseInt(materialMap.get("status").toString());
			String orderUid =materialMap.get("uid").toString();
			if (!orderUid.equals(uid) || status != 0) {
				return new BaseResponse(ResponseCode.FAILURE,"物料订单异常，请检查订单信息！");
			}
		}
		
		Map<String,String> map=new HashMap<>();
		map.put("orderSn",payRequest.getOrdersn());
		map.put("amount",df.format(payRequest.getAmount()));
		map.put("paymentType",payRequest.getPaymenttype());
		map.put("orderType",payRequest.getOrdertype()+"");
		map.put("source",payRequest.getSource());
		map.put("subject","MATERIAL_BUY");
		map.put("uid",uid);
		//加密字符串
		map.put("sign", Signature.sign(map,paySecret));
		String request = "";
        for(Entry<String, String> entry : map.entrySet()){
        	request += "&" + entry.getKey() + "=" + entry.getValue();
        }
        request = request.substring(1, request.length());
        String url=payDomain+"/unified_order/prepare"+""+"?"+request;
        String result="";
    
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
				//物料订单：失败删除订单，成功才存在此订单
				materialDao.delMaterial(payRequest.getOrdersn());
				materialDao.delMaterialItems(payRequest.getOrdersn());		
				return new BaseResponse(ResponseCode.FAILURE,json.getString("info")); 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new BaseResponse(ResponseCode.FAILURE,"支付失败"); 
	}
	
	
	/**物料支付完成查询订单支付状态
	 * @param inquireRequest
	 * @return
	 */
	public Object inquire(InquireRequest inquireRequest){
		try{
		
		Map<Object, Object> resultMap = new HashMap<>();
			resultMap.put("orderSn", inquireRequest.getOrdersn());
			Map<Object, Object> materialMap = materialDao.findOrderMateral(inquireRequest.getOrdersn());
			if (materialMap == null || materialMap.isEmpty()) {
				return new BaseResponse(ResponseCode.FAILURE, "支付订单不存在");
			}
			Map<String,String> map=new HashMap<>();
			map.put("orderNumber",inquireRequest.getOrdersn());
			String request = "";
	        for(Entry<String, String> entry : map.entrySet()){
	        	request += "&" + entry.getKey() + "=" + entry.getValue();
	        }
	        request = request.substring(1, request.length());
	        String url=payDomain+"/unified_order/query"+""+"?"+request;
	        String result = "";
	        MapResponse mapResponse = new MapResponse(ResponseCode.SUCCESS, "成功");
			result =  ClientCustomSSL.doPost(url);
			JSONObject json = JSONObject.parseObject(result);
			if (json.getString("state").equals("200")) {		
				resultMap.put("ispay", 1);//支付成功	
				Object payTime = materialMap.get("modify_time")==null?materialMap.get("create_time"):materialMap.get("modify_time");
				resultMap.put("payTime", DateUtil.format((Date) payTime, "yyyy-MM-dd HH:mm:ss"));
				setMaterialSaleNum(inquireRequest.getOrdersn());
			} else {			
				resultMap.put("ispay", 3);
				resultMap.put("msg", json);
			}
			mapResponse.setResponse(resultMap);
			return mapResponse;
			/*Integer status = Integer.parseInt(String.valueOf(materialMap.get("status")));
			if (status == 0) {
				responseMap.put("ispay", 0);// 未支付
			} else if (status == 1) {
				Object payTime = materialMap.get("modify_time");
				responseMap.put("payTime", DateUtil.format((Date) payTime, "yyyy-MM-dd HH:mm:ss"));
				responseMap.put("ispay", 1);// 支付完成
				setMaterialSaleNum(inquireRequest.getOrdersn());
			} else {
				responseMap.put("ispay", 3);// 支付失败
			}
			mapResponse.setResponse(responseMap);
			return mapResponse;*/
		} catch (Exception e) {
			e.printStackTrace();
			log.info("查询物料订单出错"+inquireRequest.getOrdersn());
			return new BaseResponse(ResponseCode.FAILURE, "未知错误，请联系管理员");
		}

	}
	
	/**统计物料销售数量
	 * @param orderNo
	 */
	public void setMaterialSaleNum(String orderNo) {
		List<Map<Object,Object>> items  = materialDao.findMaterialItem(orderNo);
		if (items!=null && !items.isEmpty()) {
			for(int i=0;i<items.size();i++){
				materialDao.updateMaterialByQutatity(items.get(i));
			}
			
		}
		
	}
	
	/**
	 * 
	* @Title: inquire
	* @Description: 查询支付接口
	* @return Object    返回类型
	* @throws
	 */
	/*public object inquire(inquirerequest inquirerequest) {
		Map<String,String> map=new HashMap<>();
		map.put("orderNumber",inquireRequest.getOrdersn());
		map.put("sign", Signature.sign(map,paySecret));
		String request = "";
        for(Entry<String, String> entry : map.entrySet()){
        	request += "&" + entry.getKey() + "=" + entry.getValue();
        }
        request = request.substring(1, request.length());
        String url=payDomain+"/unified_order/inquire"+""+"?"+request;
        String result = "";
        BufferedReader in = null;
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性*/
			//connection.setRequestProperty("accept", "*/*");
			/*connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立实际的连接
			connection.connect();
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += new String(line.getBytes("GBK"), "utf-8");  
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		JSONObject json=JSONObject.parseObject(result);
		
		if(json.getString("is_success").equals("F")){
				return new BaseResponse(ResponseCode.FAILURE, json.getString("info"));
		}else{
			MapResponse mapResponse=new MapResponse(ResponseCode.SUCCESS,"成功");
			Map<Object,Object> responseMap=new HashMap<>();
			responseMap.put("ispay", json.getInteger("status"));
			responseMap.put("orderSn",json.getString("orderSn"));
			mapResponse.setResponse(responseMap);
			return mapResponse;
		}
		
	}*/
	/** 
     * 把中文转成Unicode码 
     * @param str 
     * @return 
     */  
	public String chinaToUnicode(String str){  
        String result="";  
        for (int i = 0; i < str.length(); i++){  
            int chr1 = (char) str.charAt(i);  
            if(chr1>=19968&&chr1<=171941){//汉字范围 \u4e00-\u9fa5 (中文)  
                result+="\\u" + Integer.toHexString(chr1);  
            }else{  
                result+=str.charAt(i);  
            }  
        }  
        return result;  
    }  
	/** 
     * 判断是否为中文字符 
     * @param c 
     * @return 
     */  
	public  boolean isChinese(char c) {  
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);  
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS  
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS  
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A  
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION  
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION  
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {  
            return true;  
        }  
        return false;  
    }

	
	/**旧版本物料支付接口 ：注意apiversion值为2
	 * @param payRequest
	 * @return
	 */
	public Object materialPayV2(PayRequest payRequest) {
		DecimalFormat  df   = new DecimalFormat("0.00");   
		String uid=sessionTokenService.getStringForValue(payRequest.getSessiontoken())+"";
		if(uid==null||uid.equals("")){
			return new BaseResponse(ResponseCode.DATA_NULL,"sessiontoken错误或已过期！");
		}
		if(payRequest.getName()==null||payRequest.getName().length()==0){
			return new BaseResponse(ResponseCode.DATA_NULL,"联系人不能为空");
		}
		if(payRequest.getName()==null||payRequest.getPhone().length()==0){
			return new BaseResponse(ResponseCode.DATA_NULL,"联系人手机号码");
		}
		if(payRequest.getName()==null||payRequest.getAddress().length()==0){
			return new BaseResponse(ResponseCode.DATA_NULL,"联系人地址不能为空");
		}
		Map<Object,Object> xmerMap=xmerDao.queryUserInfo(Integer.valueOf(uid));
		if(xmerMap==null){
			return new BaseResponse(ResponseCode.DATA_NULL,"查找用户信息失败！");
		}
	    try {
				materialDao.updateMateril(payRequest);
			} catch (Exception e) {
				e.printStackTrace();
				log.error("修改订单信息错误");
			}
		Map<String,String> map=new HashMap<>();
		map.put("orderSn",payRequest.getOrdersn());
		map.put("amount",df.format(payRequest.getAmount()));
		map.put("paymentType",payRequest.getPaymenttype());
		map.put("orderType",payRequest.getOrdertype()+"");
		map.put("source",payRequest.getSource());
		//map.put("subject",xmerMap.get("name")+"物料购买");material
		map.put("subject","MATERIAL_BUY");
		//map.put("integral","1.00");
		map.put("uid",uid);
		//加密字符串
		map.put("sign", Signature.sign(map,paySecret));
		String request = "";
        for(Entry<String, String> entry : map.entrySet()){
        	request += "&" + entry.getKey() + "=" + entry.getValue();
        }
        request = request.substring(1, request.length());
        String url=payDomain+"/unified_order/prepare"+""+"?"+request;
        String result="";
    
		try {
			result = ClientCustomSSL.doGet(url);
			JSONObject json=JSONObject.parseObject(result);
			if(json.getString("state").equals("200")){			
				return new BaseResponse(ResponseCode.SUCCESS,json.getString("result"));
			}else{
				return new BaseResponse(ResponseCode.FAILURE,json.getString("info")); 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new BaseResponse(ResponseCode.FAILURE,"支付失败"); 
	}

	/**查询签约店铺
	 * @param request
	 * @return
	 */
	public Object sellerSuccess(BaseRequest request){
		String uid=sessionTokenService.getStringForValue(request.getSessiontoken())+"";
		if(uid==null||uid.equals("") || "null".equals(uid)){
			return new BaseResponse(ResponseCode.DATA_NULL,"sessiontoken错误或已过期！");
		}
		Map<Object,Object> map = new HashMap<Object,Object>();
		map.put("uid", uid);
		map.put("status", 3);//签约状态
		List<Map<Object,Object>> sellers = sellerDao.findMaterialSellerRequest(map);
		Map<Object, Object> resultMap=new HashMap<>();
		resultMap.put("sellers", sellers);
		MapResponse mapResponse=new MapResponse(ResponseCode.SUCCESS,"成功");
		mapResponse.setResponse(resultMap);
		return mapResponse;
	}
	
	
	/**修 改物料订单备注
	 * @param request
	 * @return
	 */
	public Object modifyMaterialOrderRemark(RemarkRequest request) {
		String uid=sessionTokenService.getStringForValue(request.getSessiontoken())+"";
		if(uid==null||uid.equals("")){
			return new BaseResponse(ResponseCode.DATA_NULL,"sessiontoken错误或已过期！");
		}
		
		Map<Object,Object> materialOrder = materialDao.findOrderMateral(request.getOrderNo());
		if (materialOrder==null || materialOrder.isEmpty()){
			return new BaseResponse(ResponseCode.FAILURE,"该物料订单不存在"); 
		}
		Integer status = Integer.parseInt(materialOrder.get("status").toString());
		if (status !=1 && status!=3) {//非已支付和已发货状态不能修改备注
			return new BaseResponse(ResponseCode.FAILURE,"该物料订单不能修改备注信息"); 
		}
		Map<Object,Object> map = new HashMap<Object,Object>();
		map.put("remark", request.getRemark());
		map.put("ordersn", request.getOrderNo());
		materialDao.updateMaterialRemark(map);
		return new BaseResponse(ResponseCode.SUCCESS, "修改成功");
	}
}
