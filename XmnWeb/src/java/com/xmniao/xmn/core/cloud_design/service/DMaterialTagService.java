/**
 * 
 */
package com.xmniao.xmn.core.cloud_design.service;

import java.util.List;

import org.jsoup.helper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.cloud_design.dao.DMaterialTagDao;
import com.xmniao.xmn.core.cloud_design.entity.DMaterialTag;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：DMaterialTagService
 * 
 * 类描述： 物料标签服务类
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-9-27 下午6:15:26 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service
public class DMaterialTagService extends BaseService<DMaterialTag>{
	
	/**
	 * 注入物料标签服务
	 */
	@Autowired
	private DMaterialTagDao materialTagDao;
	
	/* (non-Javadoc)
	 * @see com.xmniao.xmn.core.base.BaseService#getBaseDao()
	 */
	@SuppressWarnings("rawtypes")
	@Override
	protected BaseDao getBaseDao() {
		return materialTagDao;
	}
	
	/**
	 * 
	 * 方法描述：根据主键删除物料标签 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-9-27下午4:48:22 <br/>
	 * @param id
	 * @return
	 */
	public int deleteById(Long id){
		return materialTagDao.deleteById(id);
	}
	
	/**
	 * 
	 * 方法描述：添加物料标签 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-9-28上午11:27:09 <br/>
	 */
	public void add(DMaterialTag materialTag){
		if(StringUtil.isBlank(materialTag.getLayers())){
			materialTag.setLayers("-");//无意义占位符
		}
		addReturnId(materialTag);
		Long id = materialTag.getId();
		DMaterialTag materialTagInfo = selectById(Long.valueOf(id));
		Long parentId = materialTagInfo.getParentId();
		StringBuffer layers = new StringBuffer();//层级码,格式：父层级码/id。(1003/1003001)
		String parentLayers ="";
		if(parentId != null){
			DMaterialTag parentMaterialTagInfo = selectById(parentId);//父级物料标点
			parentLayers = parentMaterialTagInfo.getLayers()==null ? "":parentMaterialTagInfo.getLayers();//父级层级码
		}
		layers.append(parentLayers);
		
		if(StringUtil.isBlank(parentLayers)){
			layers.append(id);
		}else{
			layers.append("/"+id);
		}
		
		materialTagInfo.setLayers(layers.toString());
		update(materialTagInfo);
	}


	/**
	 * 
	 * 方法描述：添加物料标签，返回成功添加记录数  <br/>
	 * 创建人： huang'tao <br/>
	 * 创建时间：2016-9-27下午4:46:13 <br/>
	 * 
	 * @param record
	 * @return
	 */
	public int addReturnId(DMaterialTag record){
		return materialTagDao.addReturnId(record);
	}

	/**
	 * 
	 * 方法描述：根据主键查询物料标签 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-9-27下午4:48:34 <br/>
	 * @param id
	 * @return
	 */
	public DMaterialTag selectById(Long id){
		return materialTagDao.selectById(id);
	}
	
	/**
	 * 
	 * 方法描述：获取物料标签列表<br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-9-28上午9:50:34 <br/>
	 * @param materialTag
	 * @return
	 */
	public List<DMaterialTag> getList(DMaterialTag materialTag){
		return materialTagDao.getList(materialTag);
	}
	
	public Long count(DMaterialTag materialTag){
		return materialTagDao.count(materialTag);
	}

	/**
	 * 
	 * 方法描述：更新物料标签 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-9-27下午4:57:45 <br/>
	 * @param record
	 * @return
	 */
	public Integer update(DMaterialTag record){
		return materialTagDao.update(record);
	}

}
