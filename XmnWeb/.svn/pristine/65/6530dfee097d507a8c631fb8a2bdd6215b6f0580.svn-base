package com.xmniao.xmn.core.vstar.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.vstar.entity.TicketsDetail;

public interface TicketsDetailDao extends BaseDao<TicketsDetail>{
    int deleteByPrimaryKey(Integer id);

    int insert(TicketsDetail record);

    int insertSelective(TicketsDetail record);

    TicketsDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TicketsDetail record);

    int updateByPrimaryKey(TicketsDetail record);

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年6月9日上午11:45:41 <br/>
	 * @param id
	 * @return
	 */
	List<TicketsDetail> getByOrderId(Integer id);

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年6月12日下午2:58:32 <br/>
	 * @param id
	 * @return
	 */
	List<TicketsDetail> getSellingByTicketsId(Integer id);

	/**
	 * 方法描述：根据Id获取其他已出售的门票
	 * 创建人： jianming <br/>
	 * 创建时间：2017年6月12日下午5:19:42 <br/>
	 * @param ids
	 * @return
	 */
	List<TicketsDetail> getSellByIds(@Param("list")List<String> ids);

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年6月12日下午5:40:57 <br/>
	 * @param detailIds
	 * @return
	 */
	List<TicketsDetail> getByIds(List<String> detailIds);

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年6月12日下午7:59:52 <br/>
	 * @param ticketsDetail
	 * @return
	 */
	int updateToSell(TicketsDetail ticketsDetail);
}