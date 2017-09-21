package com.xmniao.service.quartz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.xmniao.dao.order.OrderServiceDao;
import com.xmniao.domain.order.RefundOrdRecordBean;

/**
 * 退款订单处理
 * 
 * @author wenchunhong
 * 
 */
public class ReFundQuertzService {

	/**
	 * 日志记录
	 */
	private final Logger log = Logger.getLogger(ReFundQuertzService.class);

	@Resource
	private OrderServiceDao orderDao;

	/**
	 * 定时任务入口
	 */
	public void execute() {
		long sTime = System.currentTimeMillis();
		try {

			List<RefundOrdRecordBean> refunds = orderDao.queryRefundOrderList();

			if (refunds != null && refunds.size() > 0) {
				Map<String, Object> map = new HashMap<String, Object>();
				List<Long> ids = new ArrayList<Long>();
				for (RefundOrdRecordBean refund : refunds) {
					ids.add(refund.getBid());
				}
				map.put("idList", ids);
				map.put("remarks", "平台同意退款");
				orderDao.modifyBatchOrderMapStatus(map);
			}
		} catch (Exception e) {
			log.error("refund error.", e);
		}

		long eTime = System.currentTimeMillis();

		log.info("total time =" + (eTime - sTime ));
	}
}
