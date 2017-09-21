/**    
 * 文件名：SellerActivityQuertzService.java    
 *    
 * 版本信息：    
 * 日期：2016年12月22日    
 * Copyright (c) 广东寻蜜鸟网络技术有限公司  2016     
 * 版权所有    
 *    
 */
package com.xmniao.service.quartz;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import com.xmniao.dao.sellerOrder.ActivityRoulleteDao;
import com.xmniao.service.common.MongoBaseService;

/**
 * 
 * 项目名称：busineService
 * 
 * 类名称：SellerActivityQuertzService
 * 
 * 类描述： 商家活动标志
 * 
 * 创建人： Chen Bo
 * 
 * 创建时间：2016年12月1日 下午5:44:10 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */

@Service("sellerActivityQuertz")
public class SellerActivityQuertzService {
    /*
     * 日志记录
     */
    private final Logger log = Logger.getLogger(SellerActivityQuertzService.class);
    
    @Resource(name="sellerMongo")
    private String sellerMongo;
    
    @Autowired
    private MongoBaseService mongoService;
    
    @Autowired
    private ActivityRoulleteDao activityRoulleteDao;
    
	public void sellerActivityModify(){
		//1 查询视图t_activity_view,
		try{
			Map<String,Object> actMap = new HashMap<String,Object>();
			actMap.put("nowDate", new Date());
			List<Integer> sellerList = activityRoulleteDao.getSellerActivityViewLst(actMap);
			Criteria criteria =null;
			Map<String,Object> updateMap = new HashMap<String,Object>();
			//先将所有1的置0
			criteria=Criteria.where("sellerid").nin(sellerList).and("is_activity").is(1);
			updateMap.put("is_activity", 0);
			mongoService.updateAll(sellerMongo, criteria, updateMap);
			//重新将开启的置1
			criteria = Criteria.where("sellerid").in(sellerList);
			updateMap.put("is_activity", 1);
			mongoService.updateAll(sellerMongo, criteria, updateMap);
		}catch(Exception e){
			log.error("查询更新异常",e);
		}
	}

}
