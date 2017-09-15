package com.xmniao.xmn.core.util;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.verification.dao.BillDao;


/**
 * 
*    
* 项目名称：xmnService   
* 类名称：MonthConsMoney   
* 类描述：   月消费金额
* 创建人：xiaoxiong   
* 创建时间：2016年7月12日 下午3:14:25   
* @version    
*
 */

@Service
public class MonthConsAmount {
	
	@Autowired
	private BillDao billDao;
	
	/**
	 * 报错日志类
	 */
	private final Logger log = Logger.getLogger(MonthConsAmount.class);
	
	public double getMonthConsAmount(int uid){
		double amout=0;//普通订单消费金额
		double integralAmount=0;//积分订单消费金额
		Map<String,Object> paramMap=new HashMap<>();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
		paramMap.put("uid", uid);
		paramMap.put("datetime", sdf.format(new Date()));
		try {
			//查询商品订单消费金额
			amout=billDao.queryBillAllMoney(paramMap);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("查询订单消费总额失败。。。");
		}
		try {
			//查询积分订单消费金额
			integralAmount=billDao.queryBillBargainAllMoney(paramMap);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("查询积分订单月消费总额失败。。。");
		}
	    BigDecimal b = new BigDecimal(amout+integralAmount); 
		return b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

}
