package com.xmniao.xmn.core.vstar.dao;

import java.util.List;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.vstar.entity.TicketsPrice;

public interface TicketsPriceDao extends BaseDao<TicketsPrice>{
    int deleteByPrimaryKey(Integer id);

    int insert(TicketsPrice record);

    int insertSelective(TicketsPrice record);

    TicketsPrice selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TicketsPrice record);

    int updateByPrimaryKey(TicketsPrice record);

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年6月2日下午3:36:43 <br/>
	 * @param asList
	 * @return
	 */
	List<TicketsPrice> getPricesBySeatsIds(List<String> asList);

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年6月2日下午4:28:03 <br/>
	 * @param ticketsPrice
	 * @return
	 */
	Long countRepetition(TicketsPrice ticketsPrice);

	/**
	 * 方法描述：
	 * 创建人： jianming <br/>
	 * 创建时间：2017年6月2日下午7:09:06 <br/>
	 * @param sids
	 */
	void updateStateByIds(List<String> sids);

	/**
	 * 方法描述：
	 * 创建人： jianming <br/>
	 * 创建时间：2017年6月6日上午10:10:43 <br/>
	 * @param ticketsPrice
	 * @return
	 */
	List<TicketsPrice> getByPriceGroup(TicketsPrice ticketsPrice);
}