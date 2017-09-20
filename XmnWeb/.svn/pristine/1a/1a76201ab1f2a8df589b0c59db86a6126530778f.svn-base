package com.xmniao.xmn.core.marketingmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.marketingmanagement.dao.MaterialDao;
import com.xmniao.xmn.core.marketingmanagement.entity.TMaterial;

/**
 * 物料订单业务层
 * @author yhl
 * 2016年7月18日10:20:55
 * */
@Service
public class MaterialService extends BaseService<TMaterial> {

	@Autowired 
	private MaterialDao materialDao;
	
	@SuppressWarnings("rawtypes")
	@Override
	protected BaseDao getBaseDao() {
		return materialDao;
	}
	
	/**
	 * 获取物料列表信息
	 * 2016年7月15日15:50:35
	 * @author yhl
	 * @param TMaterial
	 * @return List<TMaterial>
	 * */
	public List<TMaterial>  getMaterialList(TMaterial tMaterial){
		List<TMaterial> mrList = materialDao.getMaterialList(tMaterial);
		return mrList;
	}
	
	
	/**
	 * 获取物料详细信息
	 * 2016年7月15日15:50:35
	 * @author yhl
	 * @param TMaterial
	 * @return List<TMaterial>
	 * */
	public Long  getMaterialListCount(TMaterial tMaterial){
		Long count = materialDao.getMaterialListCount(tMaterial);
		return count;
	}
	
	
	/**
	 * 获取物料列表信息
	 * 2016年7月15日15:50:35
	 * @author yhl
	 * @param TMaterial
	 * @return List<TMaterial>
	 * */
	public TMaterial  getMaterialInfo(Long id){
		return materialDao.getMaterialInfo(id);
	}
	
}
