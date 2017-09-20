/**
 * 
 */
package com.xmniao.xmn.core.business_statistics.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.business_statistics.dao.TMicrographModuleDao;
import com.xmniao.xmn.core.business_statistics.dao.TMicrographPageDao;
import com.xmniao.xmn.core.business_statistics.entity.TMicrographModule;
import com.xmniao.xmn.core.business_statistics.entity.TMicrographPage;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TMicrographPageService
 * 
 * 类描述： 微图助力模板页面Service
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-12-14 下午4:28:12 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service
public class TMicrographPageService extends BaseService<TMicrographPage> {
	
	/**
	 * 注入页面Dao
	 */
	@Autowired
	private TMicrographPageDao micrographPagedao;
	
	/**
	 * 注入模块Dao
	 */
	@Autowired
	private TMicrographModuleDao moduleDao;
	
	@SuppressWarnings("rawtypes")
	@Override
	protected BaseDao getBaseDao() {
		return micrographPagedao;
	}

	/**
	 * 方法描述：保存页面信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-15下午4:59:01 <br/>
	 * @param micrograpPage
	 */
	public void saveInfo(TMicrographPage micrograpPage) {
		Integer maxId = micrographPagedao.getMaxIdByTemplateId(micrograpPage);
		micrograpPage.setPage(maxId);
		micrographPagedao.add(micrograpPage);
		syncModuleInfo(micrograpPage);
	}

	/**
	 * 方法描述：同步页面模块信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-15下午5:03:14 <br/>
	 * @param micrograpPage
	 */
	private void syncModuleInfo(TMicrographPage micrograpPage) {
		Integer pageId=micrograpPage.getId();
		List<TMicrographModule> moduleList = new ArrayList<TMicrographModule>();
		List<TMicrographModule> source = micrograpPage.getSource();
		if(source!=null && source.size()>0){
			int sortVal=0;
			for(TMicrographModule module:source){
				if(module.getType()!=null){
					setModuleInfo(module,micrograpPage);
					module.setSortVal(sortVal);
					moduleList.add(module);
					sortVal++;
				}
			}
			moduleDao.deleteByPageId(pageId);
			if(moduleList != null &&  moduleList.size()>0){
				moduleDao.addBatch(moduleList);
			}
		}
	}

	/**
	 * 方法描述：设置模块边距、宽高比等信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-15下午8:38:22 <br/>
	 * @param module
	 * @param micrograpPage
	 */
	private void setModuleInfo(TMicrographModule module, TMicrographPage micrograpPage) {
		Integer pageId = micrograpPage.getId();
		BigDecimal pageWidth = new BigDecimal(micrograpPage.getWidth());
		BigDecimal pageHeight =new BigDecimal( micrograpPage.getHeight());
		BigDecimal multiplicand=new BigDecimal(100);
		String xPoint ="";
		String yPoint ="";
		module.setPageId(pageId);
		//模块类型 0:文字 1:图片
		if(module.getType().compareTo(0)==0){
			String fontCoordinate = module.getFontCoordinate();
			xPoint = fontCoordinate.split(",")[0];
			yPoint = fontCoordinate.split(",")[1];
			
		}else{
			String imgCoordinate = module.getImgCoordinate();
			xPoint=imgCoordinate.split(",")[0];
			yPoint=imgCoordinate.split(",")[1];
		}
		
		BigDecimal xPointVal = new BigDecimal(xPoint);
		BigDecimal yPointVal = new BigDecimal(yPoint);
		BigDecimal leftStyle=xPointVal.divide(pageWidth,2,BigDecimal.ROUND_HALF_EVEN).multiply(multiplicand);
		BigDecimal top=yPointVal.divide(pageHeight,2,BigDecimal.ROUND_HALF_EVEN).multiply(multiplicand);
		
		
		String imgSize = module.getImgSize();
		BigDecimal imgWidth=new BigDecimal(imgSize.split(",")[0]);
		BigDecimal imgHeight=new BigDecimal(imgSize.split(",")[1]);
		
		BigDecimal width=imgWidth.divide(pageWidth,2,BigDecimal.ROUND_HALF_EVEN).multiply(multiplicand);
		BigDecimal height=imgHeight.divide(pageHeight,2,BigDecimal.ROUND_HALF_EVEN).multiply(multiplicand);
		
		
		module.setLeftStyle(leftStyle.longValue());
		module.setTop(top.longValue());
		module.setWidth(width);
		module.setHeight(height);
		module.setImage(module.getPicUrl());
		module.setCreateTime(new Date());
		module.setUpdateTime(new Date());
	}

	/**
	 * 方法描述：设置模板页面基础信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-16上午11:51:08 <br/>
	 * @param micrograpPage
	 * @return
	 */
	public Resultable setPageBaseInfo(TMicrographPage micrograpPage) {
		Resultable result=new Resultable();
		try {
			Integer id = micrograpPage.getId();
			TMicrographPage pageBaseInfo = micrographPagedao.getObject(new Long(id));
			result.setSuccess(true);
			result.setData(pageBaseInfo);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("加载模板页面基础信息失败!");
			this.log.error("加载模板页面基础信息失败:"+e.getMessage(), e);
		}
		return result;
	}

	/**
	 * 方法描述：设置页面模块信息<br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-16下午2:56:30 <br/>
	 * @param micrograpPage
	 * @return
	 */
	public Resultable setPageSrcInfo(TMicrographPage micrograpPage) {
		// TODO Auto-generated method stub
		Resultable result=new Resultable();
		try {
			Integer pageId = micrograpPage.getId();
			TMicrographModule moduleReq=new TMicrographModule();
			moduleReq.setPageId(pageId);
			List<TMicrographModule> list = moduleDao.getList(moduleReq);
			result.setSuccess(true);
			result.setData(list);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("加载模板页面框架信息失败!");
			this.log.error("加载模板页面框架信息失败:"+e.getMessage(), e);
		}
		
		return result;
	}

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-16下午3:45:22 <br/>
	 * @param micrograpPage
	 */
	public void updateInfo(TMicrographPage micrograpPage) {
		micrographPagedao.update(micrograpPage);
		syncModuleInfo(micrograpPage);
	}

}
