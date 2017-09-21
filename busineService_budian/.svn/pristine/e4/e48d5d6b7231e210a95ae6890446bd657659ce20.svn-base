package com.xmniao.service.quartz;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.xmniao.dao.order.ActivityProductDao;
import com.xmniao.dao.order.FreshActivityDao;
import com.xmniao.dao.order.FreshManageDao;
import com.xmniao.domain.order.ActivityProduct;
import com.xmniao.domain.order.FreshActivityBean;

/**
 * 积分超市过期活动库存还原
 * @author chenJie
 *
 */
public class FreshActivityQuertzService {
	
	/**
	 * 初始化日志类
	 */
	private final Logger log = Logger.getLogger(FreshActivityQuertzService.class);
	
	@Autowired
	private FreshActivityDao  activityGroupDao;
	
	@Autowired
	private ActivityProductDao activityProductDao;
	
	@Autowired
	private FreshManageDao freshManageDao;
	
	/**
	 * 定时还原库存
	 */
	public void restoreRepertory(){
		log.info("开始执行积分超市库存还原定时任务...");
		
		try {
			List<FreshActivityBean> activityGroup=activityGroupDao.selectEndActvityGroup();
			if(activityGroup!=null&&activityGroup.size()>0){
				for (FreshActivityBean group : activityGroup) {
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
			log.info("定时执行积分超市库存还原定时任务结束,共还原"+(activityGroup==null?0:activityGroup.size())+"个活动");
		} catch (Exception e) {
			log.error("执行积分超市库存还原定时任务出现异常",e);
			throw new RuntimeException("执行积分超市库存还原定时任务出现异常");
		}
	}
	
	
	
	public void updateActivityProductAndGroup(int store, Long codeId,String pvIds){
		if(StringUtils.isNotBlank(pvIds)){
			int i=freshManageDao.updateActivityGroup(store,codeId,pvIds);
			if(i>0){
				freshManageDao.updateActivityProduct(store,codeId);
			}
		}else{
			freshManageDao.updateActivityProduct(store,codeId);
		}
	}
}
