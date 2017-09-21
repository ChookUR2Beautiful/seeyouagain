package com.xmniao.service.quartz;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.xmniao.dao.xmer.XmerCountDao;
import com.xmniao.service.common.CommonServiceImpl;
import com.xmniao.urs.dao.XmerDao;

/**
 * 
*    
* 项目名称：busineService   
* 类名称：XmerQuertzService   
* 类描述：   收取寻蜜客服务费用任务
* 创建人：yezhiyong   
* 创建时间：2016年6月28日 下午6:13:50   
* @version    
*
 */
public class XmerQuertzService {
	
    /*
     * 日志记录
     */
    private final Logger log = Logger.getLogger(XmerQuertzService.class);
    
    @Autowired
    private XmerCountDao xmerDao;
    
    @Autowired
    private CommonServiceImpl commentServiceImpl;
    
	/**
	 * 
	* @Title: collectXmerFee
	* @Description: 每月检测寻蜜客本月消费额度是否满500，不满则收取50元服务费
	* @return void    返回类型
	* @author
	* @throws
	 */
	public void collectXmerFee(){
		log.info("定时收取寻蜜客上个月消费额度不满500的服务费开始......");
		//创建查询参数map实例
		Map<Object,Object> paramMap = new HashMap<Object,Object>();
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		//获取上个月月初时间
//		String startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());
//		paramMap.put("startDate", startDate);
		//获取本月月初时间
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH)-1);
//		String endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());
//		paramMap.put("endDate", endDate);
		
		//统计月消费额未满500元的uid
		paramMap.put("startDate", "2000-06-29 11:51:00");
		paramMap.put("endDate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date().getTime()));
		List<Integer> uids = xmerDao.queryXmerUid(paramMap);
		System.out.println(uids);
		//收取服务费用
		if (uids != null && uids.size() > 0) {
			for (Integer uid : uids) {
				if (uid != null) {
					Map<String, String> reqMap = new HashMap<>();
					reqMap.put("uId", uid + "");
					reqMap.put("userType", "1");
					reqMap.put("amount", "0");
					reqMap.put("balance", "50");
					reqMap.put("commision", "0");
					reqMap.put("zbalance", "0");
					reqMap.put("integral", "0");
					reqMap.put("sellerAmount", "0");
					reqMap.put("orderId", "");
					reqMap.put("remark", "寻蜜客服务费");
					reqMap.put("rType", "32");
					int result = commentServiceImpl.modifyWalletBalance(reqMap);
					System.out.println(result);
				}
			}
		}
		log.info("定时收取寻蜜客上个月消费额度不满500的服务费结束......");
		
	}
	
}
