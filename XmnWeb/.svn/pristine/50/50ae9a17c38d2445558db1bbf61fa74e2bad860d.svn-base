package com.xmniao.xmn.core.xmermanagerment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.xmermanagerment.dao.TSaasOrderDao;
import com.xmniao.xmn.core.xmermanagerment.entity.TSaasOrder;

/**
 * 项目名称： XmnWeb
 * 类名称： 寻蜜客套餐订单
 * 类描述：com.xmniao.xmn.core.xmermanagerment.service
 * 创建人： lifeng
 * 创建时间： 2016年6月15日下午8:05:18
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 * @version
 */
@Service
public class TSaasOrderService extends BaseService<TSaasOrder> {
	
	@Autowired
	private TSaasOrderDao saasOrderDao;

	@Override
	protected BaseDao<TSaasOrder> getBaseDao() {
		return saasOrderDao;
	}

	/**
	 * @Description: 列表
	 * @Param:TSaasOrder
	 * @return:List<TSaasOrder>
	 * @author:lifeng
	 * @time:2016年6月15日下午8:06:24
	 */
	public List<TSaasOrder> getSaasOrderList(TSaasOrder saasOrder) {
		return saasOrderDao.getSaasOrderList(saasOrder);
	}

	/**
	 * @Description: 总记录数
	 * @Param:TSaasOrder
	 * @return:Long
	 * @author:lifeng
	 * @time:2016年6月15日下午8:06:36
	 */
	public Long getCountByParam(TSaasOrder saasOrder) {
		return saasOrderDao.getCountByParam(saasOrder);
	}

}
