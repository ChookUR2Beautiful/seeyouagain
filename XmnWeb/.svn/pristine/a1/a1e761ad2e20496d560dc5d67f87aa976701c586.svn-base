/**
 * 
 */
package com.xmniao.xmn.test.vstar;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xmniao.xmn.core.manor.dao.ManorFlowerBranchMapper;
import com.xmniao.xmn.core.manor.entity.ManorFlowerBranch;
import com.xmniao.xmn.core.vstar.constant.VstarConstant;
import com.xmniao.xmn.core.vstar.entity.TVstarEnroll;
import com.xmniao.xmn.core.vstar.service.TVstarEnrollService;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：RedisTemplateTest
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2017-4-24 下午4:04:57 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */

public class TVstarEnrollServiceTest {
	
	private ApplicationContext application;
	
	private TVstarEnrollService vstarEnrollService;
	
	private ManorFlowerBranchMapper branchMapper;
	
	
	@Before
	public void before() {
		application = new ClassPathXmlApplicationContext("classpath:/com/xmniao/xmn/resource/config/spring-context.xml");
		branchMapper = application.getBean(ManorFlowerBranchMapper.class);
	}
	
	@Test
	public void mtest(){
		ManorFlowerBranch selectByPrimaryKey = branchMapper.selectByPrimaryKey("365f2f3fd7244098b6861824055c649c");
		System.out.println(selectByPrimaryKey);
	}
	
	

//	@Test
	public void add(){
		TVstarEnroll vstarEnroll = new TVstarEnroll();
		vstarEnroll.setNname("vstar2");
		vstarEnroll.setPhone("13856985620");
		vstarEnroll.setProvinceId(1963);
		vstarEnroll.setCityId(1964);
		vstarEnroll.setAreaId(1967);
		vstarEnroll.setStatus(0);
		vstarEnroll.setEnrollTime(new Date());
		
		vstarEnrollService.add(vstarEnroll );
	}
	
//	@Test
	public void getList(){
		TVstarEnroll vstarEnroll = new TVstarEnroll();
		List<TVstarEnroll> list = vstarEnrollService.getList(vstarEnroll );
		for(TVstarEnroll enroll:list){
			System.out.println(enroll.toString());
		}
		
	}
	
//	@Test
	public void updateInfo(){
		TVstarEnroll vstarEnroll = new TVstarEnroll();
		vstarEnroll.setId(8);
		vstarEnroll.setStatus(VstarConstant.ENROLL_STATUS.STATUS_7);
		Integer count = vstarEnrollService.updateInfo(vstarEnroll);
		System.out.println("=======输出更新结果====="+count);
	}
	
	@Test
	public void getImgList(){
		String ids="214, 215, 216, 220";
		vstarEnrollService.syncAnchorImgInfo(ids);
	}

}
