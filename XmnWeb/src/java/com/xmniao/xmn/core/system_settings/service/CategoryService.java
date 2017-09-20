package com.xmniao.xmn.core.system_settings.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseEvent;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.system_settings.dao.CategoryDao;
import com.xmniao.xmn.core.system_settings.entity.Category;

@Service
public class CategoryService extends BaseService<Category>{
	
	private List<Category> trades;

	@Autowired
	private CategoryDao categoryDao;
	
	@Override
	protected BaseDao getBaseDao() {
		return categoryDao;
	}
	
	/**
	 * 获取一级分类
	 * @return
	 */
	public List<Category> getParentList(Category category){
		return categoryDao.getParentList(category);
	}
	
	/**
	 * 获取所有一级分类
	 * @return
	 */
	public List<Category> getParent(){
		return categoryDao.getParent();
	}
	
	/**
	 *根据一级分类 获取二级分类
	 * @return
	 */
	public Map<Integer, List<Category>> getSubList(List<Category> parentCategory){
		Map<Integer, List<Category>> map =null;
		List<Category> categorys = categoryDao.getSubList(parentCategory);
		if(null!=categorys&&!categorys.isEmpty()){
			map = new HashMap<Integer, List<Category>>();
			setCategory(categorys,map);
		}
		return map;
	}
	
	/**
	 * 根据id删除分类
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteById(Long id){
		categoryDao.deleteById(id);
		fireEvent(BaseEvent.TRADE_EVENT, null);
	}
	
	/**
	 * 递归遍历分类列表
	 * @param array
	 * @param id
	 * @param map
	 */
	private  void setCategory(List<Category> array,Map<Integer, List<Category>> map){
		for(Category c:array){
			int pid  = c.getPid();
			addTAreas(pid,c,map);
		}
	}
	
	/**
	 * 处理分类列表使其与对于pid关联
	 * @param pid
	 * @param leftShowAu
	 * @param t
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	private void addTAreas(int pid,Category t,Map<Integer, List<Category>> map){
		List<Category> sublist = !map.containsKey(pid)? new ArrayList<Category>():map.get(pid);
		sublist.add(t);
		map.put(pid, sublist);
	}
	
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void add(Category t) {
		categoryDao.add(t);
		fireEvent(BaseEvent.TRADE_EVENT, null);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public Integer update(Category t) {
		Integer count = categoryDao.update(t);
		fireEvent(BaseEvent.TRADE_EVENT, null);
		return count;
		
	}
	
	
	
	public List<Category> getLdAll(){
		return trades;
	}
	
	
	public void updateTrades(){
		trades = categoryDao.getLdAll();
	}
	
	
	@PostConstruct
	public void init(){
		updateTrades();
	}

}
