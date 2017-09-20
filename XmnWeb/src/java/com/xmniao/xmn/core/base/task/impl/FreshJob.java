/**
 * 
 */
package com.xmniao.xmn.core.base.task.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xmniao.xmn.core.fresh.dao.ActivityGroup1Dao;
import com.xmniao.xmn.core.fresh.dao.ActivityProductDao;
import com.xmniao.xmn.core.fresh.dao.FreshManageDao;
import com.xmniao.xmn.core.fresh.entity.ActivityGroup;
import com.xmniao.xmn.core.fresh.entity.ActivityProduct;
import com.xmniao.xmn.core.fresh.entity.IndianaDduonum;
import com.xmniao.xmn.core.fresh.service.IndianaDduonumService;

/**
 * 
 * 项目名称：XmnWeb1
 * 
 * 类名称：FreshJob
 * 
 * 类描述： 积分超市过期活动库存还原
 * 
 * 创建人： jianming
 * 
 * 创建时间：2017年1月7日 上午11:52:09 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/com/xmniao/xmn/resource/config/spring-context.xml"})
public class FreshJob {
	
	@Autowired
	private ActivityGroup1Dao activityGroupDao;
	
	@Autowired
	private ActivityProductDao activityProductDao;
	
	@Autowired
	private IndianaDduonumService indianaDduonumService;
	
	@Autowired
	private FreshManageDao freshManageDao;

	private static final Logger logger = LoggerFactory.getLogger(FreshJob.class);
	
	
	//@Test
	public void execute()  {
		logger.info("[开始执行积分超市库存还原定时任务]");
		try {
			List<ActivityGroup> activityGroup=activityGroupDao.selectEndActvityGroup();
			if(activityGroup!=null&&activityGroup.size()>0){
				for (ActivityGroup group : activityGroup) {
					Integer result=activityGroupDao.updateActivityGroup(group.getStock(), group.getCodeId(), group.getPvIds());
					activityGroupDao.clearStock(group.getId());
					if(result>0){
						activityGroupDao.clearActivityProductStore(group.getCodeId(),group.getActivityId());
						activityGroupDao.updateActivityProduct(group.getStock(),group.getCodeId());
					}
				}
			}
			List<ActivityProduct> activityProducts= activityProductDao.selectEndActivityProduct();
			if(activityProducts!=null&&activityProducts.size()>0){
				for (ActivityProduct activityProduct : activityProducts) {
					activityProductDao.clearStoreZero(activityProduct.getId());
					freshManageDao.updateActivityProduct(activityProduct.getSellStore(),activityProduct.getCodeId());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("执行积分超市库存还原定时任务出现异常",e);
			throw new RuntimeException("执行积分超市库存还原定时任务出现异常");
		}
	}

	/* (non-Javadoc)
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 */
	
	@Test
	public void winnerNum(){
		List<IndianaDduonum> list=indianaDduonumService.getLastFifty();
		SimpleDateFormat dateFormat = new SimpleDateFormat("HHmmssSSS");
		Long sum=0L;
		for (IndianaDduonum indianaDduonum : list) {
			String createTime = indianaDduonum.getCreateTime();
			String replace = createTime.replace(":", "");
			Integer integer = new Integer(replace.substring(11));
			sum+=integer;
		}
		System.out.println("--------------"+sum%88+"---------------");
	}

}
