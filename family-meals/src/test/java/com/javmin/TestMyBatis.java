package com.javmin;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.javmin.entity.User;
import com.javmin.service.IUserService;

@RunWith(SpringJUnit4ClassRunner.class) // 表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:spring-context.xml"})
public class TestMyBatis {
	private static Logger logger = Logger.getLogger(TestMyBatis.class);

	// privateApplicationContext ac = null;

	
	@Resource(name="userService")
	private IUserService userService; 

	// @Before

	// public voidbefore() {

	// ac= new ClassPathXmlApplicationContext("applicationContext.xml");

	// userService= (IUserService) ac.getBean("userService");

	// }

	@Test
	public void test1() {

		User user = userService.getUserById(1);

		// System.out.println(user.getUserName());

		// logger.info("值："+user.getUserName());

		logger.info(JSON.toJSONString(user));

	}
}
