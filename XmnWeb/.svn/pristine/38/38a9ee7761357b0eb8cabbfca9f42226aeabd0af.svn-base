/**
 * 
 */
package com.xmniao.xmn.core.vstar.service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.vstar.dao.TicketsPriceDao;
import com.xmniao.xmn.core.vstar.entity.TicketsPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 
 * 项目名称：XmnWeb_vstar
 * 
 * 类名称：TicketsPriceService
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人： jianming
 * 
 * 创建时间：2017年6月2日 下午3:19:27 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service
public class TicketsPriceService extends BaseService<TicketsPrice>{

	@Autowired
	private TicketsPriceDao ticketsPriceDao;

	@Override
	protected BaseDao getBaseDao() {
		return ticketsPriceDao;
	}

	/**
	 * 方法描述：根据座位id获取门票价格
	 * 创建人： jianming <br/>
	 * 创建时间：2017年6月2日下午3:35:47 <br/>
	 * @param asList
	 * @return
	 */
	public List<TicketsPrice> getPricesBySeatsIds(List<String> asList) {
		return ticketsPriceDao.getPricesBySeatsIds(asList);
	}

	/**
	 * 方法描述：检查此规格是否重复
	 * 创建人： jianming <br/>
	 * 创建时间：2017年6月2日下午4:26:43 <br/>
	 * @param ticketsPrice
	 * @return
	 */
	public boolean checkIsRepetition(TicketsPrice ticketsPrice) {
		List<TicketsPrice> pr=ticketsPriceDao.getByPriceGroup(ticketsPrice);
		if(pr.isEmpty()){
			return false;
		}
		if(pr.size()==1){
			TicketsPrice ticketsPrice2 = pr.get(0);
			if(ticketsPrice2.getId()==ticketsPrice.getId()){
				return false;
			}else{
				return true;
			}
		}else{
			return true;
		}
	}

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年6月2日下午4:36:58 <br/>
	 * @param id
	 */
	public int delete(Integer id) {
		return ticketsPriceDao.deleteByPrimaryKey(id);
	}
	
}
