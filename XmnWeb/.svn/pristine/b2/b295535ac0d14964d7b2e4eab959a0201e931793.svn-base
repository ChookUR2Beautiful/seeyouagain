/**
 * 
 */
package com.xmniao.xmn.core.vstar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.common.dao.AreaDao;
import com.xmniao.xmn.core.common.entity.TArea;
import com.xmniao.xmn.core.vstar.dao.DivisionRegionDao;
import com.xmniao.xmn.core.vstar.entity.DivisionRegion;

/**
 * 
 * 项目名称：XmnWeb_vstar
 * 
 * 类名称：DivisionRegionService
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人： jianming
 * 
 * 创建时间：2017年6月7日 下午3:44:03 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service
public class DivisionRegionService extends BaseService<DivisionRegion>{

	@Autowired
	private DivisionRegionDao divisionRegionDao;
	
	@Autowired
	private AreaDao areaDao;
	
	@Override
	protected BaseDao getBaseDao() {
		return divisionRegionDao;
	}

	/**
	 * 方法描述：获取未设置赛区的城市
	 * 创建人： jianming <br/>
	 * 创建时间：2017年6月8日上午10:14:25 <br/>
	 * @param id 
	 * @return
	 */
	public List<TArea> getNoChooseCitys(Integer id) {
		return areaDao.getDivisionRNoChooseCitys(id);
	}

}
