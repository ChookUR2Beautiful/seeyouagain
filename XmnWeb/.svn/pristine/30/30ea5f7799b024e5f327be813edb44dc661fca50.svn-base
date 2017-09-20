/**
 * 
 */
package com.xmniao.xmn.core.cloud_design.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.cloud_design.dao.DMaterialCategoryAttrDao;
import com.xmniao.xmn.core.cloud_design.dao.DMaterialCategoryAttrRelationDao;
import com.xmniao.xmn.core.cloud_design.dao.DMaterialCategoryAttrValDao;
import com.xmniao.xmn.core.cloud_design.entity.DMaterialCategoryAttr;
import com.xmniao.xmn.core.cloud_design.entity.DMaterialCategoryAttrRelation;
import com.xmniao.xmn.core.cloud_design.entity.DMaterialCategoryAttrVal;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：DMaterialCategoryAttrService
 * 
 * 类描述： 物料规格Service
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-9-30 上午9:48:51 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service
public class DMaterialCategoryAttrService extends BaseService<DMaterialCategoryAttr>{
	
	/**
	 * 注入物料规格Dao
	 */
	@Autowired
	private DMaterialCategoryAttrDao categoryAttrDao;
	
	/**
	 * 注入物料规格细项Dao
	 */
	@Autowired
	private DMaterialCategoryAttrValDao attrValDao;
	
	/**
	 * 注入物料分类、规格关联关系Dao
	 */
	@Autowired
	private DMaterialCategoryAttrRelationDao categoryAttrRelationDao;
	
	/* (non-Javadoc)
	 * @see com.xmniao.xmn.core.base.BaseService#getBaseDao()
	 */
	@SuppressWarnings("rawtypes")
	@Override
	protected BaseDao getBaseDao() {
		return categoryAttrDao;
	}
	
	/**
	 * 
	 * 方法描述：根据主键Id删除物料规格 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-9-29下午6:01:12 <br/>
	 * @param id
	 * @return
	 */
    public int deleteById(Long id){
		return categoryAttrDao.deleteById(id);
	}

	/**
	 * 
	 * 方法描述：添加物料规格 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-9-29下午6:04:16 <br/>
	 * @param record
	 * @return
	 */
    public void add(DMaterialCategoryAttr record){
		categoryAttrDao.add(record);
	}

	/**
	 * 
	 * 方法描述：根据主键Id查询物料规格 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-9-29下午6:05:39 <br/>
	 * @param id
	 * @return
	 */
    public DMaterialCategoryAttr selectById(Long id){
		return categoryAttrDao.selectById(id);
	}

	/**
	 * 
	 * 方法描述：获取物料规格列表 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-10-8上午11:52:43 <br/>
	 * @param categoryAttr
	 * @return
	 */
	public List<DMaterialCategoryAttr> getList(DMaterialCategoryAttr categoryAttr){
		return categoryAttrDao.getList(categoryAttr);
	}
	/**
	 * 
	 * 方法描述：更新物料规格 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-9-30上午9:45:39 <br/>
	 * @param record
	 * @return
	 */
    public Integer update(DMaterialCategoryAttr record){
		return categoryAttrDao.update(record);
	}

	/**
	 * 方法描述：统计物料规格数量 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-10-8下午2:29:46 <br/>
	 * @param categoryAttr
	 * @return
	 */
	public Long count(DMaterialCategoryAttr categoryAttr) {
		return categoryAttrDao.count(categoryAttr);
	}

	/**
	 * 方法描述：保存规格信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-10-18下午5:14:24 <br/>
	 * @param categoryAttr
	 */
	public void save(DMaterialCategoryAttr categoryAttr) {
		add(categoryAttr);
		syncAttrVal(categoryAttr);
		syncCategoryAttrRelation(categoryAttr);
	}

	/**
	 * 方法描述：
	 * 1、更新物料规格信息 
	 * 2、更新规格细项数据
	 * 3、更新关联物料分类信息
	 * <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-22上午9:42:18 <br/>
	 * @param categoryAttr
	 * @return
	 */
	public Resultable updateAttrInfo(DMaterialCategoryAttr categoryAttr) {
		// TODO Auto-generated method stub
		Resultable result = new Resultable();
		try {
			categoryAttrDao.update(categoryAttr);
			syncAttrVal(categoryAttr);
			syncCategoryAttrRelation(categoryAttr);
			result.setSuccess(true);
			result.setMsg("操作成功!");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("操作失败!");
			this.log.error("更新物料规格信息失败:"+e.getMessage(), e);
		}finally{
			//写入 日志记录表
			String[] data={"雏鸟云设计规格编号",categoryAttr.getId().toString(),"修改","修改"};
			fireLoginEvent(data,result.getSuccess()==true?1:0);
		}
		return result;
	}

	/**
	 * 方法描述：同步物料分类、规格关联关系 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-22上午10:57:12 <br/>
	 * @param categoryAttr
	 */
	private void syncCategoryAttrRelation(DMaterialCategoryAttr categoryAttr) {
		
		List<DMaterialCategoryAttrRelation> relationList = new ArrayList<DMaterialCategoryAttrRelation>();
		Long materialAttrId = categoryAttr.getId();
		String name = categoryAttr.getName();
		categoryAttrRelationDao.deleteByMaterialAttrId(materialAttrId);
		String categoryVals = categoryAttr.getCategoryVals();
		if(StringUtils.isNotBlank(categoryVals)){
			String[] categoryArray = categoryVals.split(",");
			for(String category:categoryArray){
				DMaterialCategoryAttrRelation relationBean= new DMaterialCategoryAttrRelation();
				String[] key_value = category.split("_");
				String categoryId = key_value[0];
				String categoryVal = key_value[1];
				relationBean.setCategoryId(Long.valueOf(categoryId));
				relationBean.setCategoryVal(categoryVal);
				relationBean.setMaterialAttrId(materialAttrId);
				relationBean.setMaterialAttrVal(name);
				
				relationList.add(relationBean);
			}
			categoryAttrRelationDao.addBatch(relationList);
		}
	}

	/**
	 * 方法描述：同步规格细项数据 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-22上午10:00:38 <br/>
	 * @param categoryAttr
	 */
	private void syncAttrVal(DMaterialCategoryAttr categoryAttr) {
		// TODO Auto-generated method stub
		
		List<String> valList=new ArrayList<String>();
		List<DMaterialCategoryAttrVal> attrValList = new ArrayList<DMaterialCategoryAttrVal>();
		Long categoryAttrId = categoryAttr.getId();
//		attrValDao.deleteByAttrId(categoryAttrId);
		String attrVals = categoryAttr.getAttrVals();
		if(StringUtils.isNotBlank(attrVals)){
			String[] attrArray = attrVals.split(",");
			for(String attrVal:attrArray){
				String[] key_value = attrVal.split("_");
				String key = key_value[0];
				String value = key_value[1];
				if("undefined".equals(key)){
					valList.add(value);
				}
			}
		}
		
		if(valList!=null && valList.size()>0){
			int sortVal=0;
			for(String attrVal:valList){
				DMaterialCategoryAttrVal attrValBean= new DMaterialCategoryAttrVal();
				attrValBean.setCategoryAttrId(categoryAttrId);
				attrValBean.setVal(attrVal);
				attrValBean.setSortVal(sortVal);
				sortVal++;
				attrValList.add(attrValBean);
			}
			
			attrValDao.addBatch(attrValList);
		}
	}


}
