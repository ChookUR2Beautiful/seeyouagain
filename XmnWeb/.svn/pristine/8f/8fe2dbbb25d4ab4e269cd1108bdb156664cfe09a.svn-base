/**
 * 
 */
package com.xmniao.xmn.core.cloud_design.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.cloud_design.dao.DMaterialAttrDao;
import com.xmniao.xmn.core.cloud_design.dao.DMaterialAttrValDao;
import com.xmniao.xmn.core.cloud_design.dao.DMaterialCarouselSourceDao;
import com.xmniao.xmn.core.cloud_design.dao.DMaterialDao;
import com.xmniao.xmn.core.cloud_design.entity.DMaterial;
import com.xmniao.xmn.core.cloud_design.entity.DMaterialAttr;
import com.xmniao.xmn.core.cloud_design.entity.DMaterialAttrGroup;
import com.xmniao.xmn.core.cloud_design.entity.DMaterialAttrVal;
import com.xmniao.xmn.core.cloud_design.entity.DMaterialCarousel;
import com.xmniao.xmn.core.cloud_design.entity.DMaterialCarouselSource;
import com.xmniao.xmn.core.util.FastfdsConstant;
import com.xmniao.xmn.core.util.PageConstant;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：DMaterialService
 * 
 * 类描述： 物料Service
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-11-22 下午8:11:58 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service
public class DMaterialService extends BaseService<DMaterial> {
	
	/**
	 * 注入物料Dao
	 */
	@Autowired
	private DMaterialDao materialDao;
	
	/**
	 * 注入物料元数据Dao
	 */
	@Autowired
	private DMaterialCarouselSourceDao sourceDao;
	
	
	/**
	 * 注入物料规格Dao
	 */
	@Autowired
	private DMaterialAttrDao materialAttrDao;
	
	/**
	 * 注入物料商品规格细项Dao
	 */
	@Autowired
	private DMaterialAttrValDao materialAttrValDao;
	
	/**
	 * 注入物料模板Service
	 */
	@Autowired
	private DMaterialCarouselService carouselService;
	
	/**
	 * 注入物料元数据服务
	 */
	@Autowired
	private DMaterialCarouselSourceService carouselSrcService;
	
	/**
	 * 注入商品规格分组信息服务
	 */
	@Autowired
	private DMaterialAttrGroupService materialAttrGroupService;
	

	@SuppressWarnings("rawtypes")
	@Override
	protected BaseDao getBaseDao() {
		return materialDao;
	}
	
	/**
	 * 
	 * 方法描述：添加物料 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-22下午8:10:07 <br/>
	 * @param record
	 * @return
	 */
    public void add(DMaterial record){
    	materialDao.add(record);
    }

	/**
	 * 
	 * 方法描述：根据ID查询物料 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-22下午8:10:18 <br/>
	 * @param id
	 * @return
	 */
    public DMaterial selectById(Long id){
    	return materialDao.selectById(id);
    }
    
    /**
	 * 
	 * 方法描述：获取物料列表 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-22下午8:10:18 <br/>
	 * @param id
	 * @return
	 */
    public List<DMaterial> getList(DMaterial material){
    	return materialDao.getList(material);
    }
    
    /**
	 * 
	 * 方法描述：统计物料总数 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-22下午8:10:18 <br/>
	 * @param id
	 * @return
	 */
    public Long count(DMaterial material){
    	return materialDao.count(material);
    }

	/**
	 * 
	 * 方法描述：跟新物料 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-22下午8:10:28 <br/>
	 * @param record
	 * @return
	 */
    public Integer update(DMaterial record){
    	return materialDao.update(record);
    }

	/**
	 * 方法描述：批量更新商品状态 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-23下午2:12:37 <br/>
	 * @param ids
	 * @param status
	 * @return
	 */
	public Resultable updateBatch(String ids, String status) {
		Resultable result=new Resultable();
		try {
			Map<String,Object> map=new HashMap<String,Object>();
			if(StringUtils.isNotBlank(ids)){
				map.put("ids", ids.split(","));
				map.put("status", status);
				materialDao.updateBatch(map);
				result.setSuccess(true);
				result.setMsg("操作成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("操作失败");
			
		}finally{
			//写入 日志记录表
			String[] data={"雏鸟云设计,商品ID",ids,"修改上架状态","修改"};
			fireLoginEvent(data,result.getSuccess()==true?1:0);
		}
		
		return result;
	}

	/**
	 * 方法描述：保存物料信息 <br/>
	 * 1.保存物料基本信息 <br/>
	 * 2.保存物料规格信息<br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-24下午3:35:15 <br/>
	 * @param material 
	 * @return
	 */
	public Resultable saveInfo(DMaterial material) {
		Resultable result= new Resultable();
		try {
			material.setCreateTime(new Date());
			material.setUpdateTime(new Date());
			convertHtml(material);
			materialDao.add(material);
			syncBannerInfo(material);
			syncMaterialAttrValInfo(material);
			syncMaterialAttrGroupInfo(material);
			result.setSuccess(true);
			result.setMsg("操作成功!");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("操作失败!");
		}
		
		return result;
	}

	/**
	 * 方法描述：更新物料信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-25下午6:35:13 <br/>
	 * @param material
	 * @return
	 */
	public Resultable updateInfo(DMaterial material) {
		Resultable result=new Resultable();
		try {
			material.setUpdateTime(new Date());
			convertHtml(material);
			materialDao.update(material);
			syncBannerInfo(material);
			syncMaterialAttrValInfo(material);
			syncMaterialAttrGroupInfo(material);
			result.setSuccess(true);
			result.setMsg("操作成功!");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("操作失败!");
			this.log.error("更新物料信息失败："+e.getMessage(), e);
		}
		return result;
	}

	/**
	 * 方法描述：将富文本内容转化为html文本 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-7上午9:51:10 <br/>
	 * @param material
	 */
	private void convertHtml(DMaterial material) {
		StringBuffer html=new StringBuffer();
		String content = material.getContent();
		if(content==null){
			return;
		}
		content=content.replaceAll("&lt;", "<");
		content=content.replaceAll("&gt;", ">");
		html.append("<html>");
		html.append(content);
		html.append("</html>");
		String htmlContent = html.toString();
		material.setHtmlContent(htmlContent);
	}

	/**
	 * 方法描述：同步物料商品规格分组信息 <br/>
	 * 1、删除物料商品规格分组信息
	 * 2、新增物料商品规格分组信息
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-30上午11:52:18 <br/>
	 * @param material
	 */
	private void syncMaterialAttrGroupInfo(DMaterial material) {
		List<DMaterialAttrGroup>  attrGroupList= new ArrayList<DMaterialAttrGroup>();
		Long materialId = material.getId();
		materialAttrGroupService.deleteByMaterialId(materialId);
		
		List<DMaterialAttrGroup> materialAttrGroupList = material.getMaterialAttrGroupList();
		if(materialAttrGroupList!=null && materialAttrGroupList.size()>0){
			int sortVal=0;
			for(DMaterialAttrGroup attrGroupInfo:materialAttrGroupList){
				String materialAttrIds = attrGroupInfo.getMaterialAttrIds();
				String materialAttrVals = attrGroupInfo.getMaterialAttrVals();
				BigDecimal amount = attrGroupInfo.getAmount();
				if(materialAttrIds!=null && materialAttrVals!=null && amount!=null){
					attrGroupInfo.setMaterialId(materialId);
					attrGroupInfo.setSortVal(sortVal);
					attrGroupList.add(attrGroupInfo);
					sortVal++;
				}
				
			}
			materialAttrGroupService.addBatch(attrGroupList);
		}
		
	}

	/**
	 * 方法描述：同步物料规格及规格细项信息 <br/>
	 * 1、删除物料规格及规格细项<br/>
	 * 2、新增物料规格及规格细项			
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-28下午5:47:28 <br/>
	 * @param material
	 */
	private void syncMaterialAttrValInfo(DMaterial material) {
		List<DMaterialAttrVal> materialAttrValList=new ArrayList<DMaterialAttrVal>();
		Long materialId = material.getId();
		Long categoryId = material.getCategoryId();
		materialAttrValDao.deleteByMaterialId(materialId);
		materialAttrDao.deleteByMaterialId(materialId);
		//1_类型:1_影视,2_传媒;2_副色调:1_浅黄,2_淡蓝
		String materialAttrVals = material.getMaterialAttrVals();
		if(StringUtils.isNotBlank(materialAttrVals)){
			String[] materialAttrValArray = materialAttrVals.split(";");
			int sortVal=0;
			for(String materialAttrVal:materialAttrValArray){
				DMaterialAttr materialAttr = new DMaterialAttr();
				String[] attrAndAttrDetail = materialAttrVal.split(":");
				if(attrAndAttrDetail.length>0){
					String attrInfo = attrAndAttrDetail[0];
					String[] attrKey_Value = attrInfo.split("_");
					Long attrKey = attrKey_Value[0]==null ? 0l:Long.valueOf(attrKey_Value[0]);
					materialAttr.setMaterialId(materialId);
					materialAttr.setCategoryId(categoryId);
					materialAttr.setCategoryAttrId(attrKey);
					materialAttr.setName(attrKey_Value[1]);
					materialAttr.setSortVal(sortVal);
					materialAttrDao.add(materialAttr);
					sortVal++;
				}
				
				if(attrAndAttrDetail.length>1){
					Long materialAttrId = materialAttr.getId();
					String attrDetailInfo= attrAndAttrDetail[1];
					String[] attrDetailArray = attrDetailInfo.split(",");
					//遍历规格细项
					int orderVal=0;
					for(String attrDetail:attrDetailArray){
						DMaterialAttrVal materialAttrValBean = new DMaterialAttrVal();
						String[] attrDetailKey_value = attrDetail.split("_");
						Long categoryAttrValId = attrDetailKey_value[0] == null ? 0l:Long.valueOf(attrDetailKey_value[0]);
						String val = attrDetailKey_value[1];
						materialAttrValBean.setMaterialAttrId(materialAttrId);
						materialAttrValBean.setCategoryAttrValId(categoryAttrValId);
						materialAttrValBean.setVal(val);
						materialAttrValBean.setSortVal(orderVal);
						materialAttrValList.add(materialAttrValBean);
						orderVal++;
					}
				}
			}
			
			if(materialAttrValList!=null && materialAttrValList.size()>0){
				materialAttrValDao.addBatch(materialAttrValList);
			}
		}
	}

	/**
	 * 方法描述：同步物料banner图信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-26上午10:45:24 <br/>
	 * @param material
	 */
	private void syncBannerInfo(DMaterial material) {
		List<DMaterialCarouselSource> bannerList = material.getBannerList();
		Long materialId = material.getId();
		DMaterialCarouselSource srcRequest=new DMaterialCarouselSource();
		srcRequest.setMaterialId(materialId);
		srcRequest.setImgType("001");
		sourceDao.delete(srcRequest);
		int i=0;
		for(DMaterialCarouselSource source:bannerList){
			System.out.println("pic"+i+"="+source.getPicUrl());
			source.setMaterialId(materialId);
			source.setOrderVal(i);
			source.setType("001");//框架类型（图片：001，文字：002）
			source.setCreateTime(new Date());
			source.setUpdateTime(new Date());
			source.setImgType("001");//banner图：001，模板图：002,背景图：003
			i++;
		}
		sourceDao.addBatch(bannerList);
	}

	/**
	 * 方法描述：设置物料商品信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-29下午5:25:54 <br/>
	 * @param material
	 * @return
	 */
	public DMaterial setMaterialInfo(DMaterial material) {
		Long materialId = material.getId();
//		Long categoryId = material.getCategoryId();
		DMaterial materialInfo = materialDao.selectById(materialId);
		DMaterialCarouselSource srcRequest= new DMaterialCarouselSource();
		srcRequest.setMaterialId(materialId);
		srcRequest.setImgType("001");
		List<DMaterialCarouselSource> bannerList = carouselSrcService.getList(srcRequest);
		materialInfo.setBannerList(bannerList);
		
		
		//物料规格分组信息(表格内容)
		DMaterialAttrGroup attrGroup= new DMaterialAttrGroup();
		attrGroup.setMaterialId(materialId);
		attrGroup.setLimit(PageConstant.PAGE_LIMIT_NO);
		List<DMaterialAttrGroup> materialAttrGroupList = materialAttrGroupService.getList(attrGroup);
		for(DMaterialAttrGroup attrGroupInfo:materialAttrGroupList){
			String materialAttrVals = attrGroupInfo.getMaterialAttrVals();
			List<String> materialAttrValList=new ArrayList<String>();
			if(materialAttrVals==null){
				return null;
			}
			String[] materialAttrValArray = materialAttrVals.split(",");
			for(String materialAttrVal:materialAttrValArray){
				materialAttrValList.add(materialAttrVal);
			}
			attrGroupInfo.setMaterialAttrValList(materialAttrValList);
		}
		materialInfo.setMaterialAttrGroupList(materialAttrGroupList);
		
		
		//物料规格(表头信息)
		if(materialAttrGroupList!=null && materialAttrGroupList.size()>0){
			DMaterialAttr materialAttr=new DMaterialAttr();
			List<String> categoryAttrIdList =new ArrayList<String>();
			DMaterialAttrGroup materialAttrGroup = materialAttrGroupList.get(0);
			String materialAttrIds = materialAttrGroup.getMaterialAttrIds();
			String[] attrIdArray = materialAttrIds.split(",");
			for(String attrId:attrIdArray){
				categoryAttrIdList.add(attrId);
			}
			materialAttr.setMaterialId(materialId);
			materialAttr.setCategoryAttrIdList(categoryAttrIdList);
			
			List<DMaterialAttr> materialAttrList = materialAttrDao.getList(materialAttr);
			materialInfo.setMaterialAttrList(materialAttrList);
		}
		
		//TODO load template info
		DMaterialCarousel carousel=new DMaterialCarousel();
		List<DMaterialCarousel> carouselList = new ArrayList<DMaterialCarousel>();
		carousel.setMaterialId(materialId);
		List<DMaterialCarousel> materialCarouselList = carouselService.getList(carousel);
		for(DMaterialCarousel carouselBean:materialCarouselList){
			Long id = carouselBean.getId();
			DMaterialCarouselSource carouselSourceReq=new DMaterialCarouselSource();
			carouselSourceReq.setMaterialId(materialId);
			carouselSourceReq.setMaterialCarouselId(id);
			carouselSourceReq.setImgType("003");//图片类型（banner图：001，模板图：002,背景图：003）
			List<DMaterialCarouselSource> sourceList = sourceDao.getList(carouselSourceReq);
			if(sourceList!=null && sourceList.size()>0){
				DMaterialCarouselSource backImgSrc = sourceList.get(0);
				String picUrl = backImgSrc.getPicUrl();
				carouselBean.setPicUrl(FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP+picUrl);
			}
			carouselList.add(carouselBean);
		}
		
		materialInfo.setMaterialCarouselList(carouselList);
		
		return materialInfo;
	}

}
