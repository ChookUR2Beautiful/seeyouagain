package com.xmniao.xmn.test.xmermanagerment.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xmniao.xmn.core.billmanagerment.entity.Bill;
import com.xmniao.xmn.core.system_settings.entity.TLog;
import com.xmniao.xmn.core.system_settings.entity.TUser;
import com.xmniao.xmn.core.system_settings.service.LogService;
import com.xmniao.xmn.core.system_settings.service.UserService;
import com.xmniao.xmn.core.xmermanagerment.entity.BXmerWallet;
import com.xmniao.xmn.core.xmermanagerment.service.BXmerWalletService;
import com.xmniao.xmn.core.xmermanagerment.service.FinanceService;
import com.xmniao.xmn.core.xmnpay.entity.Bwallet;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：UserServiceTest
 * 
 * 类描述： 测试
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年11月13日 下午5:24:26
 * 
 * Copyright (c) 深圳市XXX有限公司-版权所有
 */
public class FinanceServiceTest {

	private ApplicationContext application;
	private FinanceService financeService;
	private BXmerWalletService xmerWalletService;

	@Before
	public void before() {
		application = new ClassPathXmlApplicationContext("classpath:/com/xmniao/xmn/resource/config/spring-context.xml");
		financeService = application.getBean(FinanceService.class);
		xmerWalletService = application.getBean(BXmerWalletService.class); 
	}

//	@Test
	public void testSelectWallet() {
		Object[] objects=new String[]{"100033","100034"};
		List<Bwallet> wallets = financeService.selectWallet(objects);
		for(Bwallet wallet:wallets){
			System.out.println("用户"+wallet.getUid()+",钱包ID:"+wallet.getAccountid());
		}
		
	}
	
	@Test
	public void testSelectXmerWallet() {
		Object[] uids=new String[]{"604628","593268","593815"};
		List<BXmerWallet> xmerWallet = xmerWalletService.getListByUids(uids);
		for(BXmerWallet wallet:xmerWallet){
			System.out.println("用户"+wallet.getUid()+",钱包ID:"+wallet.getId());
		}
		
	}

}
