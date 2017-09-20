/**
 * 
 */
package com.xmniao.xmn.core.cloud_design.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.cloud_design.dao.TransportFeeDao;
import com.xmniao.xmn.core.cloud_design.entity.PostCondition;
import com.xmniao.xmn.core.cloud_design.entity.PostTemplate;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TransportFeeService
 * 
 * 类描述： 运费模板
 * 
 * 创建人： chenJie
 * 
 * 创建时间：2016年11月25日 下午5:58:48 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */

@Service
public class TransportFeeService {
	
	@Autowired
	private TransportFeeDao transportFeeDao;
	
	/**
	 * 
	 * 方法描述：获取运费模板列表
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年11月25日下午6:07:04 <br/>
	 * @return
	 */
	public List<PostTemplate> getList(PostTemplate pTemplate){
		
		List<PostTemplate> templateList = transportFeeDao.getList(pTemplate);
		
		
		for (PostTemplate postTemplate : templateList) {
			postTemplate.setConditions(transportFeeDao.getConditions(postTemplate.getId()));
		}
		
		return templateList;
	}
	
	/**
	 * 
	 * 方法描述：获取运费模板列表2
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年11月25日下午6:07:04 <br/>
	 * @return
	 */
	public List<PostTemplate> getListTwo(PostTemplate pTemplate){
		
		pTemplate.setLimit(-1);
		
		List<PostTemplate> templateList = transportFeeDao.getList(pTemplate);
		
		return templateList;
	}
	
	/**
	 * 
	 * 方法描述：统计模板总数
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年11月26日下午3:34:41 <br/>
	 * @return
	 */
	public Long count(){
		return transportFeeDao.count();
	}
	
	/**
	 * 
	 * 方法描述：获取全国省级区域地址
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年11月28日下午4:09:17 <br/>
	 * @return
	 */
	public List<Map<String,Object>> getAreaList(){
		return transportFeeDao.getAreaList();
	}
	
	/**
	 * 
	 * 方法描述：新增运费模板
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年11月28日下午5:13:11 <br/>
	 * @param postTemplate
	 */
	public void addTemplate(PostTemplate postTemplate){
		postTemplate.setCreateTime(new Date());
		//插入运费模板
		transportFeeDao.addTemplate(postTemplate);
		
		for (PostCondition  postCondition : postTemplate.getConditions()) {
			
			postCondition.setTemplateId(postTemplate.getId());
			postCondition.setCreateTime(formatDate());
			postCondition.setUpdateTime(formatDate());
			
			transportFeeDao.addCondition(postCondition);
		}
		
	}
	
	/**
	 * 
	 * 方法描述：删除运费模板
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年11月29日下午2:56:40 <br/>
	 * @param postTemplate
	 * @return
	 */
	public Integer deleteTemplate(PostTemplate postTemplate){
		return transportFeeDao.deleteTemplate(postTemplate);
	}
	

	/**
	 * 
	 * 方法描述：编辑
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年11月29日下午9:06:48 <br/>
	 * @param postTemplate
	 * @return
	 */
	public void update(PostTemplate postTemplate){
		
	 transportFeeDao.update(postTemplate);
	 
	 transportFeeDao.deleteConditions(postTemplate.getId());
	 
	 for (PostCondition  postCondition : postTemplate.getConditions()) {
			if(postCondition.getDeliveryNo() == null || postCondition.getDeliveryCity() == null){
				continue;
			}
			postCondition.setTemplateId(postTemplate.getId());
			postCondition.setUpdateTime(formatDate());
			
			transportFeeDao.addCondition(postCondition);
		}
	}
	
	private String formatDate(){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}
 }
