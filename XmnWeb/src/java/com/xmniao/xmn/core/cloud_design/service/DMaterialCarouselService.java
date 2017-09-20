/**
 * 
 */
package com.xmniao.xmn.core.cloud_design.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.cloud_design.dao.DMaterialCarouselDao;
import com.xmniao.xmn.core.cloud_design.entity.DMaterialCarousel;
import com.xmniao.xmn.core.cloud_design.entity.DMaterialCarouselSource;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：DMaterialCarouselService
 * 
 * 类描述： 物料商品模板Service
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-12-5 下午4:27:14 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service
public class DMaterialCarouselService extends BaseService<DMaterialCarousel> {
	
	/**
	 * 注入物料模板Dao
	 */
	@Autowired
	private DMaterialCarouselDao materialCarouselDao;
	
	/**
	 * 注入物料模板元数据Dao
	 */
	@Autowired
	private DMaterialCarouselSourceService srcService;

	@SuppressWarnings("rawtypes")
	@Override
	protected BaseDao getBaseDao() {
		return materialCarouselDao;
	}
	
	/**
	 * 
	 * 方法描述：根据ID删除物料模板 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-5下午7:40:40 <br/>
	 * @param id
	 * @return
	 */
    public  int deleteById(Long id){
    	return materialCarouselDao.deleteById(id);
    }


    /**
	 * 
	 * 方法描述：添加物料模板 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-5下午7:40:40 <br/>
	 * @param record
	 * @return
	 */
    public void add(DMaterialCarousel record){
    	materialCarouselDao.add(record);
    }

    /**
     * 
     * 方法描述：根据ID查询物料模板 <br/>
     * 创建人：  huang'tao <br/>
     * 创建时间：2016-12-5下午7:41:36 <br/>
     * @param id
     * @return
     */
    public DMaterialCarousel selectById(Long id){
    	return materialCarouselDao.selectById(id);
    }
    
    /**
     * 
     * 方法描述：获取物料模板列表 <br/>
     * 创建人：  huang'tao <br/>
     * 创建时间：2016-12-5下午7:41:36 <br/>
     * @param record
     * @return
     */
    public List<DMaterialCarousel> getList(DMaterialCarousel record){
    	return materialCarouselDao.getList(record);
    }

    /**
     * 
     * 方法描述：更新物料模板 <br/>
     * 创建人：  huang'tao <br/>
     * 创建时间：2016-12-5下午7:41:36 <br/>
     * @param record
     * @return
     */
    public Integer update(DMaterialCarousel record){
    	return materialCarouselDao.update(record);
    }

	/**
	 * 方法描述：保存模板信息(背景，图片，文案) <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-5下午5:02:28 <br/>
	 * @param materialCarousel
	 */
	public void saveInfo(DMaterialCarousel materialCarousel) {
		List<DMaterialCarouselSource> sourceListToAdd=new ArrayList<DMaterialCarouselSource>();
		Long materialId = materialCarousel.getMaterialId();
		List<DMaterialCarouselSource> sourceList = materialCarousel.getSource();
		add(materialCarousel);
		Long materialCarouselId = materialCarousel.getId();
		int orderVal=0;
		for(DMaterialCarouselSource carouselSrc:sourceList){
			carouselSrc.setMaterialId(materialId);
			carouselSrc.setMaterialCarouselId(materialCarouselId);
			carouselSrc.setCreateTime(new Date());
			carouselSrc.setUpdateTime(new Date());
			if(carouselSrc.getType() != null){
				carouselSrc.setOrderVal(orderVal);
				sourceListToAdd.add(carouselSrc);
				orderVal++;
			}
			log.debug(carouselSrc.toString());
		}
		srcService.addBatch(sourceListToAdd);
	}

	/**
	 * 方法描述：设置物料模板基础信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-5下午8:38:50 <br/>
	 * @param materialCarousel
	 * @return
	 */
	public Resultable setMaterialCarouselInfo(DMaterialCarousel materialCarousel) {
		Resultable result=new Resultable();
		DMaterialCarouselSource carouselSourceReq=new DMaterialCarouselSource();
		try {
			Long id = materialCarousel.getId();
			Long materialId = materialCarousel.getMaterialId();
			DMaterialCarousel carouselInfo = selectById(id);
			carouselSourceReq.setMaterialId(materialId);
			carouselSourceReq.setMaterialCarouselId(id);
			carouselSourceReq.setImgType("003");//图片类型（banner图：001，模板图：002,背景图：003）
			List<DMaterialCarouselSource> sourceList = srcService.getList(carouselSourceReq);
			if(sourceList!=null && sourceList.size()>0){
				DMaterialCarouselSource carouselSource = sourceList.get(0);
				String picUrl = carouselSource.getPicUrl();
				String imgSize = carouselSource.getImgSize();
				String[] imgSizeInfo = imgSize.split("X");
				if(imgSizeInfo.length>0){
					String width = imgSizeInfo[0];
					String height = imgSizeInfo[1];
					carouselInfo.setWidth(width);
					carouselInfo.setHeight(height);
					carouselInfo.setImgSize(imgSize);
				}
				carouselInfo.setPicUrl(picUrl);
			}
			result.setSuccess(true);
			result.setData(carouselInfo);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("加载物料模板基础信息出错!");
			this.log.error("加载物料模板基础信息出错:"+e.getMessage(), e);
		}
		return result;
	}

	/**
	 * 方法描述：设置物料模板元数据信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-6上午9:56:29 <br/>
	 * @param materialCarousel
	 * @return
	 */
	public Resultable setMaterialCarouselSrcInfo(DMaterialCarousel materialCarousel) {
		Resultable result=new Resultable();
		DMaterialCarouselSource carouselSourceReq=new DMaterialCarouselSource();
		try {
			Long id = materialCarousel.getId();
			Long materialId = materialCarousel.getMaterialId();
			carouselSourceReq.setMaterialId(materialId);
			carouselSourceReq.setMaterialCarouselId(id);
			
			List<DMaterialCarouselSource> sourceList = srcService.getSourceList(carouselSourceReq);
			result.setSuccess(true);
			
			result.setData(sourceList);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("加载物料模板图片及文字框架信息出错!");
			this.log.error("加载物料模板图片及文字框架信息出错:"+e.getMessage(), e);
		}
		return result;
	}

	/**
	 * 方法描述：更新模板信息(背景，图片，文案)<br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-6下午4:33:49 <br/>
	 * @param materialCarousel
	 * @throws Exception 
	 */
	public Resultable updateInfo(DMaterialCarousel materialCarousel) {
		// TODO Auto-generated method stub
		Resultable result=new Resultable();
		List<DMaterialCarouselSource> sourceListToAdd=new ArrayList<DMaterialCarouselSource>();
		Long materialId = materialCarousel.getMaterialId();
		String materialIdStr = materialCarousel.getMaterialId()==null?"":materialCarousel.getMaterialId().toString();
		Long materialCarouselId = materialCarousel.getId();
		String materialCarouselIdStr = materialCarousel.getId()==null?"":materialCarousel.getId().toString();
		if(StringUtils.isBlank(materialIdStr)||StringUtils.isBlank(materialCarouselIdStr)){
			result.setSuccess(false);
			result.setMsg("获取物料模板基础信息失败，操作不成功!");
			return result;
		}
		List<DMaterialCarouselSource> sourceList = materialCarousel.getSource();
		materialCarousel.setCreateTime(new Date());
		materialCarousel.setUpdateTime(new Date());
		//更新物料模板
		update(materialCarousel);
		
		//根据materialId 和materialCarouselId 删除物料背景,图片,文字
		DMaterialCarouselSource carouselSource=new DMaterialCarouselSource();
		carouselSource.setMaterialId(materialId);
		carouselSource.setMaterialCarouselId(materialCarouselId);
		srcService.delete(carouselSource);
		int orderVal=0;
		for(DMaterialCarouselSource carouselSrc:sourceList){
			carouselSrc.setMaterialId(materialId);
			carouselSrc.setMaterialCarouselId(materialCarouselId);
			carouselSrc.setCreateTime(new Date());
			carouselSrc.setUpdateTime(new Date());
			if(carouselSrc.getType() != null){
				carouselSrc.setOrderVal(orderVal);
				sourceListToAdd.add(carouselSrc);
				orderVal++;
			}
			log.debug(carouselSrc.toString());
		}
		srcService.addBatch(sourceListToAdd);
		result.setSuccess(true);
		result.setMsg("保存成功!");
		
		return result;
	}

}
