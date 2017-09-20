/**
 * 
 */
package com.xmniao.xmn.test.fresh;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.xmniao.xmn.core.fresh.entity.TBillFreshSub;
import com.xmniao.xmn.core.fresh.service.BillFreshSubService;
import com.xmniao.xmn.core.fresh.service.FreshManageService;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：FreshTest
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人： jianming
 * 
 * 创建时间：2017年3月8日 下午5:51:50 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/com/xmniao/xmn/resource/config/spring-context.xml" })
@Transactional
public class FreshTest {
	
	@Autowired
	private FreshManageService freshManageService;
	
	@Autowired
	private BillFreshSubService billFreshSubService;
	 
	
	public void execut(){
		freshManageService.updateActivityProductAndGroup(5,1000001072L,"374,376");
	}
	
	@Test
	public void doTest(){
		List<TBillFreshSub> tBillFreshSubList = billFreshSubService.getTBillFreshSubList(new TBillFreshSub());
		System.out.println(tBillFreshSubList);
	}
	
}
