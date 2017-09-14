package com.javmin.main;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.javmin.entity.SysBaby;
import com.javmin.entity.SysUser;
import com.javmin.mapper.SysBabyMapper;
import com.javmin.mapper.SysUserMapper;

public class SpringRun {

	private ApplicationContext applicationContext;
	
	@Before
	public void before(){
		this.applicationContext=new ClassPathXmlApplicationContext("spring.xml");
	}
	
	@Test
	public void select(){
		SysUserMapper sysUserMapper = (SysUserMapper) applicationContext.getBean("sysUserMapper");
		SysUser object = sysUserMapper.getObject(892955517384249345L);
		/*List<SysUser> selectPage = sysUserMapper.selectPage(new Page<SysUser>(1,10), new EntityWrapper<SysUser>().eq("name", "jianjian"));
		for (SysUser sysUser : selectPage) {
			System.out.println(sysUser);
		} */
		System.out.println(object);
		SysBabyMapper sysBabyMapper = (SysBabyMapper) applicationContext.getBean("sysBabyMapper");
		SysBaby selectById = sysBabyMapper.selectById(1);
		System.out.println(selectById);
	}
	
	@Test
	public void insert(){
		SysBaby sysUser = new SysBaby();
		sysUser.setName("李小华");
		sysUser.setAge(22);
		sysUser.insert();
	}
	
	
	@Test
	public void update(){
		SysBaby sysUser = new SysBaby();
		sysUser.setId(892957465558708225L);
		sysUser.setName("李大华");
		sysUser.updateById();
	}
	
	@Test
	public void delete(){
		SysBaby sysUser = new SysBaby();
		sysUser.setId(892957465558708225L);
		sysUser.deleteById();
	}
	
}
