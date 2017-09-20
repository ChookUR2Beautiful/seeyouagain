package com.xmniao.xmn.core.xmermanagerment.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.thrift.client.proxy.ThriftClientProxy;
import com.xmniao.xmn.core.thrift.service.payRefundService.FreshRefundService;
import com.xmniao.xmn.core.thrift.service.payRefundService.RefundRequest;
import com.xmniao.xmn.core.xmermanagerment.dao.TSaasSoldOrderDao;
import com.xmniao.xmn.core.xmermanagerment.entity.TSaasSoldOrder;

/**
 * 项目名称： XmnWeb
 * 类名称： 商家签约订单
 * 类描述：com.xmniao.xmn.core.xmermanagerment.service
 * 创建人： lifeng
 * 创建时间： 2016年6月15日下午8:05:30
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 * @version
 */
@Service
public class TSaasSoldOrderService extends BaseService<TSaasSoldOrder> {
	
	@Autowired
	private TSaasSoldOrderDao saasSoldOrderDao;
	
	@Resource(name = "freshRefundServiceClient")
	private ThriftClientProxy saasSoldOrderRefundServiceClient;

	@Override
	protected BaseDao<TSaasSoldOrder> getBaseDao() {
		return saasSoldOrderDao;
	}

	/**
	 * @Description: 列表
	 * @Param:saasSoldOrder
	 * @return:List<TSaasSoldOrder>
	 * @author:lifeng
	 * @time:2016年6月15日下午8:07:00
	 */
	public List<TSaasSoldOrder> getSaasSoldOrderList(TSaasSoldOrder saasSoldOrder) {
		return saasSoldOrderDao.getSaasSoldOrderList(saasSoldOrder);
	}

	/**
	 * @Description: 总记录数
	 * @Param:saasSoldOrder
	 * @return:Long
	 * @author:lifeng
	 * @time:2016年6月15日下午8:07:12
	 */
	public Long getCountByParam(TSaasSoldOrder saasSoldOrder) {
		return saasSoldOrderDao.getCountByParam(saasSoldOrder);
	}
	
	/**
	 * @Description: 调用接口，商家签约订单退款
	 * @Param:
	 * @return:Map<String,String>
	 * @author:lifeng
	 * @time:2016年6月25日上午9:15:20
	 */
	public Map<String, String> saasSoldOrderRefund(RefundRequest refundRequest) {
		FreshRefundService.Client client = (FreshRefundService.Client)(saasSoldOrderRefundServiceClient.getClient());//支付服务
		Map<String, String> result = new HashMap<String,String>();
		try {
			result = client.FreshRefund(refundRequest);
		} catch (Exception e) {
			log.error("退款失败", e);
		} finally {
			saasSoldOrderRefundServiceClient.returnCon();
		}
		return result;
	}

}
