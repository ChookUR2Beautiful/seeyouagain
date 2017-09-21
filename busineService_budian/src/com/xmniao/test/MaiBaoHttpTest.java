/**
 * 
 */
package com.xmniao.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;

import com.alibaba.fastjson.JSONObject;
import com.xmniao.common.DateUtil;
import com.xmniao.common.HttpUtil;
import com.xmniao.common.MD5Util;
import com.xmniao.common.PreciseComputeUtil;
import com.xmniao.common.PropertiesUtil;
import com.xmniao.domain.maibao.MaibaoLedgerNotifyBean;

/**
 * 
 * 项目名称：busineService_maibao
 * 
 * 类名称：MaiBaoHttpTest
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人： Administrator
 * 
 * 创建时间：2017年7月13日 下午5:30:15 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
public class MaiBaoHttpTest {
	public static void main(String[] args) throws ClientProtocolException, IOException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		

		HttpUtil httpUtil = HttpUtil.getInstance();
		Map<String,String> map = new HashMap<String,String>();
	//	map.put("name", "大侠");
//		map.put("uId", "23323");
//		map.put("remark", "狼来了");

//		{"sign":"d748b3946add2b087dd134dd238905a5",
//		"amount":100,"merchantType":2,"ledgerTime":"2017-07-18 11:23:49",
//		"merchantName":"[0xe4][0xbf][0x9d][0xe6][0x8a][0xa4][0xe4][0xbc][0x9e]",
//		"ecno":"19500000030","signedEcno":"19500000030","ledgerAmount":16,"transNo":"170718000013",
//		"mobile":"19500000030","discount":0.8}"
		
//		{"sign":"d748b3946add2b087dd134dd238905a5",
//		"amount":100,"merchantType":2,"ledgerTime":"2017-07-18 11:23:49",
//		"merchantName":"[0xe4][0xbf][0x9d][0xe6][0x8a][0xa4][0xe4][0xbc][0x9e]",
//		"ecno":"19500000030","signedEcno":"19500000030","ledgerAmount":16,"transNo":"170718000013",
//		"mobile":"19500000030","discount":0.8}"

//amount=100.0&discount=0.8&ecno=19500000030&ledgerAmount=16.0&ledgerTime=2017-07-18 11:23:49
//&merchantName=保护伞&merchantType=2&mobile=19500000030&signedEcno=19500000030&transNo=170718000013&958BF6C6A88563F0


		long ecno = 12000000000L;
		double amount=100;
		double discount = 0.8;
		int i=0;
		MaibaoLedgerNotifyBean notifyBean =new MaibaoLedgerNotifyBean();
    	notifyBean.setEcno("19500000030");
    	notifyBean.setMobile("19500000030");
    	notifyBean.setTransNo("170718000013");//(DateUtil.format(new Date(),"yyMMddHHmmssSSS"));
    	notifyBean.setAmount(PreciseComputeUtil.keepTwoPointStr(amount, 2));
    	notifyBean.setDiscount(PreciseComputeUtil.keepTwoPointStr(discount,2));
    	notifyBean.setMerchantType(2) ;
    	notifyBean.setMerchantName("保护伞");
    	notifyBean.setSignedEcno("19500000030");
    	double ledgerAmount = PreciseComputeUtil.mul(PreciseComputeUtil.mul(amount, (1-discount)),0.8);
    	notifyBean.setLedgerAmount(PreciseComputeUtil.keepTwoPointStr(ledgerAmount, 2));
    	notifyBean.setLedgerTime("2017-07-18 11:23:49");//(DateUtil.format(new Date(),"yyyy-MM-dd HH:mm:ss")) ;
    	String signSource=notifyBean.getSignSource(PropertiesUtil.readValue("maibao.http.key", "busine_sundry.properties"));
    	System.out.println("待签名字符串:"+signSource);
    	notifyBean.setSign(MD5Util.md5(signSource));
		HttpResponse res = httpUtil.postJsonData(PropertiesUtil.readValue("maibao.http.url", "busine_sundry.properties"), JSONObject.toJSONString(notifyBean));
		HttpEntity entity = res.getEntity();
		InputStream is =  entity.getContent();
//		Reader reader = new InputStreamReader(is);
		BufferedReader in = new BufferedReader(new InputStreamReader(is));  
         StringBuffer sb = new StringBuffer("");  
         String line = "";  
         String NL = System.getProperty("line.separator");  
         while ((line = in.readLine()) != null) {  
             sb.append(line + NL);  
         }  
         in.close();  
        System.out.println("接收到返回值:"+sb+",请求参数:"+JSONObject.toJSONString(notifyBean));
        JSONObject json = JSONObject.parseObject(sb.toString());
        String code =  (String) json.get("code");
        if(code ==null){
        	System.out.println("返回信息异常");
        }else if(code.equals("1001")){
        	System.out.println("返回成功");
        }else{
        	System.out.println("返回失败");
        }
	
	}
}
