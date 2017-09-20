package com.xmniao.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.xmniao.service.TongPayService;
import com.xmniao.service.impl.TongPayServiceImpl;

public class TonglianWithdraw {
	
	
	public static void main(String[] args) throws Exception {

		
		double amount = 0.01;
		
		
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id",format.format(new Date()));//id = date:yyyyMMddHHmmss
		map.put("province", "广东省");
		map.put("cityname", "广州市");
		map.put("bank", "中国建设银行");
		map.put("bankname", "东山支行");
		map.put("bankCode","105");  //存折需要银行编号  建行105
		map.put("ispublic", "1");
		map.put("account", "44001400109053003385");
		map.put("amount", amount);
		map.put("fullname", "广东寻蜜鸟网络技术有限公司");
		map.put("userType", 1);
		
		
		FileSystemXmlApplicationContext context = new FileSystemXmlApplicationContext(new String[] {"WebRoot/WEB-INF/pay-*.xml"}, true);
	    context.start();
	    TongPayService tongPayService = context.getBean(TongPayServiceImpl.class);
	    tongPayService.tongPay(map);
		
	}
	
}
