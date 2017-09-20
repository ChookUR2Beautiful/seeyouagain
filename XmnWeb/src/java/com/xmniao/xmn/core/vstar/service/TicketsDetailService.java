/**
 * 
 */
package com.xmniao.xmn.core.vstar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.vstar.dao.TicketsDetailDao;
import com.xmniao.xmn.core.vstar.entity.TicketsDetail;

/**
 * 
 * 项目名称：XmnWeb_vstar
 * 
 * 类名称：TicketsDetailService
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人： jianming
 * 
 * 创建时间：2017年6月2日 下午7:26:22 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service
public class TicketsDetailService extends BaseService<TicketsDetail>{

	@Autowired
	private TicketsDetailDao ticketsDetailDao;

	@Override
	protected BaseDao getBaseDao() {
		return ticketsDetailDao;
	}

	/**
	 * 方法描述：检查门票是否卖出
	 * 创建人： jianming <br/>
	 * 创建时间：2017年6月12日下午5:18:18 <br/>
	 * @param asList
	 * @return
	 */
	public List<TicketsDetail> checkSeatsIsSell(List<String> ids) {
		return ticketsDetailDao.getSellByIds(ids);
	}
}
