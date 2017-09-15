package com.xmniao.xmn.core.api.controller.xmer;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.xmniao.xmn.core.api.controller.weixin.AuthzController;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.util.ClientCustomSSL;
import com.xmniao.xmn.core.util.CookieUtils;
import com.xmniao.xmn.core.verification.dao.UrsDao;
import com.xmniao.xmn.core.verification.dao.UrsInfoDao;
import com.xmniao.xmn.core.verification.entity.Urs;
import com.xmniao.xmn.core.verification.entity.UrsInfo;
import com.xmniao.xmn.core.xmer.dao.SaasOrderDao;
import com.xmniao.xmn.core.xmer.dao.SaasPackageDao;
import com.xmniao.xmn.core.xmer.entity.SaasPackage;


@Controller
public class ToOtherPayApi {
	
			// 日志
			private Logger log = Logger.getLogger(InvitedJoinXMER.class);
	
			@Autowired
			private SaasOrderDao sassOrderDao;
			
			@Autowired
			private SessionTokenService sessionService;
			
			@Autowired
			private UrsInfoDao ursInfoDao;
			
			@Autowired
			private UrsDao ursDao;
			
			@Autowired
			private SaasPackageDao saasPackageDao;
			
			@Autowired
			private String localDomain;

			@RequestMapping(value="/pay/toOtherPay")
			public String toOtherPay(String orderid,HttpServletRequest request,Model model,String parentid) throws Exception{
				
				Map<Object,Object> orderMap=sassOrderDao.querySaasOrderInfoByOrdersn(orderid);
				if(orderMap==null){
					model.addAttribute("info","没有找到代付订单信息");
					return "pay/error";
				}
				if(!"0".equals(orderMap.get("status")+"")){
					model.addAttribute("info","该订单已经支付过啦！");
					return "pay/error";
				}
				
				if("0".equals(orderMap.get("otherPay")+"")){//不是代付订单
					model.addAttribute("info","没有找到代付订单信息");
					return "pay/error";
				}
				String uid=orderMap.get("uid")+"";
				
				String id=orderMap.get("dpid")+"";		//套餐ID
				String name="";							//真是信息，没有则查询昵称
				String phone="";						//手机号码
				
				//查询购买的套餐信息
				SaasPackage saasPackage=saasPackageDao.selectSaasPackage(Integer.valueOf(id));
				if(saasPackage==null){//不是代付订单
					model.addAttribute("info","没有找到套餐信息");
					return "pay/error";
				}
				model.addAttribute("price",saasPackage.getPrice());//原价
				BigDecimal b=new BigDecimal(saasPackage.getPrice()*saasPackage.getAgio());
				model.addAttribute("agioPrice",b.setScale(2,BigDecimal.ROUND_HALF_UP).toString());//折扣价格
				model.addAttribute("createTime", orderMap.get("sdate")+"");//订单创建时间
				model.addAttribute("agio",saasPackage.getAgio());//折扣
				model.addAttribute("nums",saasPackage.getNums());//数量
				model.addAttribute("otherTel",orderMap.get("otherTel"));//数量
				//查询用户详细信息
				UrsInfo ursInfo=ursInfoDao.queryUrsInfoByUid(Integer.valueOf(uid));
				if(ursInfo!=null){
					if(ursInfo.getName()!=null&&ursInfo.getName().length()!=0){
						name=ursInfo.getName();
					}
				}
				Urs urs=ursDao.queryUrsByUid(Integer.valueOf(uid));
				if(urs==null){
					model.addAttribute("info","没有找到用户信息");
					return "pay/error";
				}
				if(urs.getPhone()!=null&&urs.getPhone().length()!=0){
					phone=urs.getPhone();
					if(name.length()==0){
						if(urs.getNname()!=null&&urs.getNname().length()!=0){
							name=urs.getNname();
						}
					}
				}
				model.addAttribute("name",name);
				model.addAttribute("phone",phone);
				// 微信openid获取
				String openid = CookieUtils.getVal(AuthzController.WEIXIN_OPENID_KEY,
						request);
				if (openid == null) {
					String callback = (localDomain+"/pay/toOtherPay?orderid=" + orderid+"&parentid="+parentid);
		
					callback = URLEncoder.encode(callback, "utf-8");
					String redirect = "/weixin/authz/authorize?callback=" + callback;
					log.info("请求微信openid:" + redirect);
					return "redirect:" + redirect;
				}
				model.addAttribute("orderid",orderid);
				model.addAttribute("parentid",parentid);
				if(openid.equals(orderMap.get("openid")+"")){
					getWxConfig(model, request,orderid,parentid);
					return "pay/pay_detail";
				}
				return "pay/pay_detail_wx";
			}
			
			private  void getWxConfig(Model model,HttpServletRequest request,String orderid,String parentid) throws Exception{

				Map ret = new HashMap();
			    String appId = "wx7b953ff4081b8184"; // 必填，公众号的唯一标识
			    String secret = "bdd31fcf4ce27dc7cdc13b1b8dd4b31d";
			    String requestUrl = request.getRequestURL().toString();
			    //String requestUrl="http://gzdev.xmniao.com/";
			    String params="";
			    Map map=request.getParameterMap();  
			    Set keSet=map.entrySet();  
			    for(Iterator itr=keSet.iterator();itr.hasNext();){  
			        Map.Entry me=(Map.Entry)itr.next();  
			        Object ok=me.getKey();  
			        Object ov=me.getValue();  
			        String[] value=new String[1];  
			        if(ov instanceof String[]){  
			            value=(String[])ov;  
			        }else{  
			            value[0]=ov.toString();  
			        }  
			        for(int k=0;k<value.length;k++){  
			            params+="&"+ok+"="+value[k];
			        }  
			      }  
			    params=params.substring(1);
			    requestUrl=request+"?"+params;
			    String access_token = "";
			    String jsapi_ticket = "";
			   String timestamp = Long.toString(System.currentTimeMillis() / 1000); // 必填，生成签名的时间戳
			    String nonceStr = UUID.randomUUID().toString(); // 必填，生成签名的随机串
			   String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appId+"&secret="+secret;
			    String jsons = ClientCustomSSL.doGet(url);
			    JSONObject json=JSONObject.parseObject(jsons);
			    if (json != null) {
			        access_token = json.getString("access_token");
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
			    model.addAttribute("appId", appId);
			    model.addAttribute("timestamp", timestamp);
			    model.addAttribute("nonceStr", nonceStr);
			    model.addAttribute("signature", signature);
			    
			   
//			    return ret;

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
