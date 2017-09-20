/**
 * 
 */
package com.xmniao.xmn.core.manor.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.manor.dao.ManorLevelDao;
import com.xmniao.xmn.core.manor.entity.ManorLevel;

/**
 * 
 * 项目名称：XmnWeb_Manor
 * 
 * 类名称：ManorLevelService
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人： ChenBo
 * 
 * 创建时间：2017年6月12日 下午4:47:27 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */

@Service
public class ManorLevelService {

	@Autowired
	private ManorLevelDao manorLevelDao;
	
//	@Override
//	protected BaseDao getBaseDao() {
//		return manorLevelDao;
//	}

	public List<Map<String,String>> getActivateSetting(){
		List<Map<String,String>> activateList = null;
		
		//调用支付服务，获取激活配置
		activateList = new ArrayList<>();
		Map<String,String> map2 = new HashMap<>();
		map2.put("number", "200");
		map2.put("type", "1");
		
		Map<String,String> map = new HashMap<>();
		map.put("number", "100");
		map.put("type", "3");
		
		activateList.add(map);
		activateList.add(map2);
		return activateList;
	}
	
	public List<Map<String,String>> saveActivateSetting(){
		List<Map<String,String>> activateList = null;
		
		//调用支付服务，获取激活配置
		
		return activateList;
	}
	
	public void add(ManorLevel manorLevel){
		 manorLevelDao.add(manorLevel);
	}
	public int update(ManorLevel manorLevel){
		return  manorLevelDao.update(manorLevel);
	}
	public ManorLevel getObject(ManorLevel manorLevel){
		return  manorLevelDao.getObject((long)manorLevel.getId());
	}
	public long count(ManorLevel manorLevel){
		return  manorLevelDao.count(manorLevel);
	}
	public List<ManorLevel> getList(ManorLevel manorLevel){
		return  manorLevelDao.getList(manorLevel);
	}
	/**
	 * 
	 * 方法描述：获取等级最高的等级 <br/>
	 * 创建人： ChenBo <br/>
	 * 创建时间：2017年6月14日上午11:09:30 <br/>
	 * @param 
	 * @return
	 */
	public ManorLevel getMaxObject(){
		return  manorLevelDao.getMaxObject();
	}
}
