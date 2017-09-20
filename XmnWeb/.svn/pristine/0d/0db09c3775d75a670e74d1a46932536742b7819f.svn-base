/**
 * 
 */
package com.xmniao.xmn.test.fresh;

import java.util.HashMap;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xmniao.xmn.core.thrift.client.proxy.ThriftClientProxy;
import com.xmniao.xmn.core.thrift.service.liveService.LiveWalletService;
import com.xmniao.xmn.core.thrift.service.liveService.LiveWalletService.Client;
import com.xmniao.xmn.core.thrift.service.liveService.ResponseData;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：ThriftTest
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人： jianming
 * 
 * 创建时间：2017年3月31日 下午4:34:07 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/com/xmniao/xmn/resource/config/spring-context.xml" })
public class ThriftTest {
	
	@Resource(name="liveWalletServiceServiceClient")
	private ThriftClientProxy thriftClientProxy;
	
	
	public void testThrift() throws Exception{
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("uid", "601200");
		map.put("rtype", "0");
		map.put("zbalance", "88");
		map.put("availableCoin", "22");
		map.put("usedCoin", "22");
		map.put("option", "0");
		thriftClientProxy.doClient("liveWalletOption", map);
	}
	
	@Test
	public void testGetLiveWallet() throws Exception{
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("uid", "604865");
		ResponseData  doClient = thriftClientProxy.doClient("cleanLiveWallet", map);
		System.out.println(doClient.getResultMap());
	}

}
