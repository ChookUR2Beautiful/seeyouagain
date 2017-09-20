package com.xmniao.xmn.test.system_settings.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xmniao.xmn.core.system_settings.entity.TLog;
import com.xmniao.xmn.core.system_settings.entity.TUser;
import com.xmniao.xmn.core.system_settings.service.LogService;
import com.xmniao.xmn.core.system_settings.service.UserService;

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
public class UserServiceTest {

	private ApplicationContext application;
	private UserService userService;

	@Before
	public void before() {
		application = new ClassPathXmlApplicationContext("classpath:/com/xmniao/xmn/resource/config/spring-context.xml");
		userService = application.getBean(UserService.class);
	}

	@Test
	public void addBatchUser() {
		List<TUser> userList = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			TUser user = new TUser();
			user.setRoleId(new Long(1));
			user.setUsername("shen" + i);
			user.setPassword("password");
			user.setEmail("xxxxx@xx.com");
			user.setName("name");
			user.setIsEnabled(true);
			user.setIsLocked(true);
			user.setLockedDate(new Date());
			user.setLoginDate(new Date());
			user.setLoginIp("192.168.1.1");
			userList.add(user);
		}
		System.out.println(userService.addBatch(userList));
	}

}
