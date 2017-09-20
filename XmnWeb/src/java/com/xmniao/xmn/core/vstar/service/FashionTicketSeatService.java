/**
 * 
 */
package com.xmniao.xmn.core.vstar.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.vstar.dao.FashionTicketSeatDao;
import com.xmniao.xmn.core.vstar.entity.FashionTicketSeat;

/**
 * 
 * 项目名称：XmnWeb_vstar
 * 
 * 类名称：FashionTicketSeatService
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人： jianming
 * 
 * 创建时间：2017年6月1日 下午5:53:22 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service
public class FashionTicketSeatService extends BaseService<FashionTicketSeat>{
	
	@Autowired
	private FashionTicketSeatDao fashionTicketSeatDao;

	@Override
	protected BaseDao getBaseDao(){
		return fashionTicketSeatDao;
	}

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年6月1日下午7:43:49 <br/>
	 * @param list
	 * @return
	 */
	public List<FashionTicketSeat> getListByIds(List<String> list) {
		return fashionTicketSeatDao.getListByIds(list);
	}

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年6月5日下午3:58:54 <br/>
	 * @param fashionTicketSeat
	 * @param seatIds
	 * @return
	 */
	public boolean checkRepetition(FashionTicketSeat fashionTicketSeat, String seatIds) {
		if(StringUtils.isBlank(seatIds)){
			return false;
		}
		List<FashionTicketSeat> list = fashionTicketSeatDao.getListByIds(Arrays.asList(seatIds.split(",")));
		for (FashionTicketSeat fashionTicketSeat2 : list) {
			if(fashionTicketSeat2.getId()!=fashionTicketSeat.getId()){
				if(zoneRangeMinIsInSeat(fashionTicketSeat,fashionTicketSeat2)){
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 方法描述：检查两座的编号是否有重复
	 * 创建人： jianming <br/>
	 * 创建时间：2017年6月5日下午4:06:28 <br/>
	 * @param fashionTicketSeat
	 * @param fashionTicketSeat2
	 * @return
	 */
	private boolean zoneRangeMinIsInSeat(FashionTicketSeat fashionTicketSeat, FashionTicketSeat fashionTicketSeat2) {
		HashSet<Integer> hashSet = new HashSet<>();
		Integer zoneRangeMin = fashionTicketSeat2.getZoneRangeMin();
		Integer zoneRangeMax = fashionTicketSeat2.getZoneRangeMax();
		if(zoneRangeMax<zoneRangeMin){
			return true;
		}
		for(;zoneRangeMin<=zoneRangeMax;zoneRangeMin++){
			hashSet.add(zoneRangeMin);
		}
		Integer min = fashionTicketSeat.getZoneRangeMin();
		Integer max = fashionTicketSeat.getZoneRangeMax();
		if(max<min){
			return true;
		}
		for(;min<=max;min++){
			if(!hashSet.add(min)){
				return true;
			}
		}
		return false;
	}

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年6月6日下午2:02:08 <br/>
	 * @param id
	 * @return
	 */
	public List<FashionTicketSeat> getListByFid(Integer id) {
		return fashionTicketSeatDao.getListByFid(id);
	}

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年6月6日下午2:23:30 <br/>
	 * @param id
	 */
	public void delete(Integer id) {
		fashionTicketSeatDao.deleteByPrimaryKey(id);
	}

	
	
}
