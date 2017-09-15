package com.xmniao.xmn.core.api.controller.xmer;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONObject;
import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.util.ClientCustomSSL;
import com.xmniao.xmn.core.xmer.dao.SaasOrderDao;


@Controller
public class OtherPayPhoneApi {
	
		// 日志
		private Logger log = Logger.getLogger(OtherPayPhoneApi.class);
		
		@Autowired
		private SaasOrderDao sassOrderDao;		
	
		@RequestMapping(value="otherPayPhone")
		@ResponseBody
		public Object otherPayPhone(String orderid,String phone,HttpServletRequest request){
			if(orderid==null||orderid.length()==0){
				return new BaseResponse(ResponseCode.FAILURE,"订单ID不能为空");
			}
			if(phone==null||phone.length()==0){
				return new BaseResponse(ResponseCode.FAILURE,"代付人手机号码不能为空");
			}
			
			try {
				//查询订单信息
				Map<Object,Object> orderMap=sassOrderDao.querySaasOrderInfoByOrdersn(orderid);
				if(orderMap==null){
					return new BaseResponse(ResponseCode.FAILURE,"没有找到订单信息");
				}
				Map<String,String> params=new HashMap<String, String>();
				params.put("orderid", orderid);
				params.put("phone", phone);
				sassOrderDao.updateOrderOtherTel(params);
				Map<Object,Object> result=new HashMap<>();
				try {
//					getWxConfig(result,request);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				//System.out.println(result.toString()+"===============");
				MapResponse mapResponse=new MapResponse(ResponseCode.SUCCESS, "成功");
				mapResponse.setResponse(result);
				return mapResponse;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return new BaseResponse(ResponseCode.FAILURE,"未知错误"); 
		}
		

		private   void getWxConfig(Map<Object,Object> map,HttpServletRequest request) throws Exception{

			Map ret = new HashMap();
		    String appId = "wx7b953ff4081b8184"; // 必填，公众号的唯一标识
		    String secret = "bdd31fcf4ce27dc7cdc13b1b8dd4b31d";
		    String requestUrl = request.getRequestURL().toString();
//		    String requestUrl="http://gzdev.xmniao.com:8101/xmnService/pay/toOtherPay";
		    String access_token = "";
		    String jsapi_ticket = "";
		   String timestamp = Long.toString(System.currentTimeMillis() / 1000); // 必填，生成签名的时间戳
		    String nonceStr = UUID.randomUUID().toString(); // 必填，生成签名的随机串
		   String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appId+"&secret="+secret;
		    String jsons = ClientCustomSSL.doGet(url);
		    JSONObject json=JSONObject.parseObject(jsons);
		    if (json != null) {
		        access_token = json.getString("access_token");
		        System.out.println("access_token:"+access_token);
		        url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+access_token+"&type=jsapi";
		        jsons = ClientCustomSSL.doGet(url);
		        json=JSONObject.parseObject(jsons);
		        if (json != null) {
		            jsapi_ticket = json.getString("ticket");
		        }          
		    }
		    String signature = "";

		     //注意这里参数名必须全部小写，且必须有序
		    String sign = "jsapi_ticket=" + jsapi_ticket +"&noncestr=" + nonceStr + "&timestamp=" + timestamp +"&url=" +requestUrl;
		    try
		    {
		      signature = SHA1(sign);
		      System.out.println("jsapi_ticket:"+jsapi_ticket);
		      System.out.println("nonceStr:"+nonceStr);
		      System.out.println("timestamp:"+timestamp);
		      System.out.println("signature:"+signature);
		      System.out.println("requestUrl:"+requestUrl);
		      
		    }catch (Exception e){
		        e.printStackTrace();
		    }
		    map.put("appId", appId);
		    map.put("timestamp", timestamp);
		    map.put("nonceStr", nonceStr);
		    map.put("signature", signature);
		   
//		    return ret;

		}
	
		  /** 
	     * @author：罗国辉 
	     * @date： 2015年12月17日 上午9:24:43 
	     * @description： SHA、SHA1加密
	     * @parameter：   str：待加密字符串
	     * @return：  加密串
	    **/
	    public static String SHA1(String str) {
	        try {
	            MessageDigest digest = java.security.MessageDigest
	                    .getInstance("SHA-1"); //如果是SHA加密只需要将"SHA-1"改成"SHA"即可
	            digest.update(str.getBytes());
	            byte messageDigest[] = digest.digest();
	            // Create Hex String
	            StringBuffer hexStr = new StringBuffer();
	            // 字节数组转换为 十六进制 数
	            for (int i = 0; i < messageDigest.length; i++) {
	                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
	                if (shaHex.length() < 2) {
	                    hexStr.append(0);
	                }
	                hexStr.append(shaHex);
	            }
	            return hexStr.toString();
	 
	        } catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }

}
