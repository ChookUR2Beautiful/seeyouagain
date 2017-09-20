package com.xmniao.quartz.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.xmniao.common.PayConstants;
import com.xmniao.quartz.AutoUpdateWithradI;
import com.xmniao.service.CommonService;

public abstract class AutoUpdateWithrad implements AutoUpdateWithradI {
	
	
	/** 初始日志类*/
	protected final Logger log = Logger.getLogger(this.getClass());
	
	/** 公共服务  */
	@Autowired
	protected CommonService commonService;
	
	/**
	 * 定时任务执行方法
	 */
	
	public int autoMention() {

		log.info("自动更新提现状态");

		// 用户类型
		int userType = 0;
		// 更新会员提现状态              **注意： 这里其实是会员 和商家的
		userType = 1;
		autoUpdateWithdrawState(userType);
		// 更新合作商提现状态
		userType = 4;
		autoUpdateWithdrawState(userType);
		return 0;
	}
	
	
	protected void autoUpdateWithdrawState(int userType) {
		// 更新列表
		List<Map<String, Object>> list = null;
		// 需要更新记录数
		Integer count = null;
		// 用户类型 提现时第三方保留的用户类型
		String usertype1 = "";
		// 第三方 订单号
		String tradeNo = "";
		// 记录表 订单号
		String orderNumber = "";
		// 支付状态
		String status = "";
		// 查询第三方结果集
		Map<String, String> resultMap = null;
		String statusStr = PayConstants.WITHDRAW_STATUS_PROCESS;
		Map<String, String> params = this.getParameter();
		String platform = params.get("platform");
		String withdrawType = params.get("withdrawType");
		

		count = commonService.getWithdrawCountByStatusPlatform(userType, statusStr, platform);
		
		log.info("withdrawType:"+withdrawType+",更新自动提现状态：  用户类型：" + userType + ",提现开始。共需更改提现状态：" + count);
		
		for (int x = 0; x < count; x = x + 500) {
			
			list = commonService.getWithdrawListByStatusPlatform(userType, statusStr, platform, 0, 500);
			
			for (Map<String, Object> m : list) {
				log.info("withdrawType:"+withdrawType+",定时查询更新：id:" + m.get("id")+",account:"+m.get("account")+",fullname:"+m.get("fullname"));
                //查询第三方的支付结果
				resultMap = this.queryOrder(m,userType);
				log.info("查询第三方的支付结果:"+resultMap);
				log.info("resultMap:"+resultMap);
				if(resultMap == null){
					log.error("查询失败");
					continue;
				}
				
				status =  resultMap.get("status");

				if (status.equals("0")) {//查询失败
					log.info("withdrawType:"+withdrawType+",订单号为： "+m.get("id")+"，查询失败！" +  resultMap.get("Message"));
					continue;
				}
				if(status.equals(PayConstants.WITHDRAW_STATUS_PROCESS)){
					log.info("withdrawType:"+withdrawType+",订单号为： "+m.get("id")+"，提现处理中！");
					continue;
				}

				orderNumber = String.valueOf(m.get("id"));
				resultMap.put("orderNumber", orderNumber);
				tradeNo =  resultMap.get("platform_code");
				usertype1 = resultMap.get("usertype");
				// 更新提现状态
				try {
					log.info("withdrawType:"+withdrawType+",更新提现状态，platform_code：" + tradeNo + ",orderNumber:"
							+ orderNumber);
					commonService.updateWithdrawState(orderNumber, usertype1,
							Integer.valueOf(status), withdrawType, resultMap);

				} catch (Exception e) {
					log.error("withdrawType:"+withdrawType+",自动更新异常,tradeNo:" + tradeNo + ",orderNumber:"
							+ orderNumber, e);
				}
			}

		}
		log.info("withdrawType:"+withdrawType+",更新自动提现状态：  用户类型：" + userType + ",提现结束。共更改提现状态：" + count);

		
	}
	
	/**

	 * @return 提现方式 withdrawType 和 platform 
	 */
	protected abstract Map<String,String> getParameter();
	
	/**
	 * @param m 提现记录  
	 * @return   status 0查询失败  1打款成功 2打款失败 3打款处理中      Message platform_code usertype
	 */
	protected abstract Map<String, String> queryOrder(Map<String, Object> m,int userType);
	

}
