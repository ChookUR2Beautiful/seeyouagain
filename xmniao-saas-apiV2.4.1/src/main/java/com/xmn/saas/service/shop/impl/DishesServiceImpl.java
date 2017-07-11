package com.xmn.saas.service.shop.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xmn.saas.base.GlobalConfig;
import com.xmn.saas.dao.shop.DishesDao;
import com.xmn.saas.entity.shop.Dishes;
import com.xmn.saas.service.shop.DishesService;

/**
 * 
*      
* 类名称：DishesServiceImpl   
* 类描述：   推荐菜业务实现类
* 创建人：xiaoxiong   
* 创建时间：2016年9月28日 下午5:47:54   
* 修改人：xiaoxiong   
* 修改时间：2016年9月28日 下午5:47:54   
* 修改备注：   
* @version    
*
 */
@Service
public class DishesServiceImpl implements DishesService{
	
	@Autowired
	private DishesDao dishesDao;
	
	@Autowired
	private GlobalConfig config;
	
	
	/**
	 * 查询推荐菜列表
	 */
	@Override
	public List<Dishes> queryFoodList(Integer sellerid) {
		
		return dishesDao.queryFoodList(sellerid,config.getImageHost());
	}


	/**
	 * 查询推荐菜详细信息
	 */
	@Override
	public Dishes queryFoodById(Integer id) {
		
		return dishesDao.queryFoodById(id,config.getImageHost());
	}

	/**
	 * 添加推荐菜
	 */
	@Override
	@Transactional
	public Integer create(Dishes dishes) {
			
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		  dishes.setUrl(dishes.getUrl().replace(config.getImageHost(), ""));	
		  dishes.setSdate(sdf.format(new Date()));//添加时间
		  dishes.setDatastatus(1);//数据状态：1上架，2删除（标记为删除不显示），3下架
		  dishes.setAtag(1);//是否为广告位 0否 1是
		  dishes.setSource(1);//数据来源
		  dishesDao.create(dishes);
		  return dishes.getId();
		
	}

	/**
	 * 修改推荐菜
	 */
	@Override
	@Transactional
	public void modify(Dishes dishes) {
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dishes.setPdate(sdf.format(new Date()));//修改时间
		if(dishes.getUrl()!=null){
			dishes.setUrl(dishes.getUrl().replace(config.getImageHost(), ""));
		}
		
		dishesDao.modify(dishes);
		
	}

}
