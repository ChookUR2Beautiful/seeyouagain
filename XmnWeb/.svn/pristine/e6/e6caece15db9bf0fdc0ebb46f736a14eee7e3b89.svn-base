package com.xmniao.xmn.core.businessman.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.base.ResultFile;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.base.Import.PoiImport;
import com.xmniao.xmn.core.businessman.dao.SellerSubsidyDao;
import com.xmniao.xmn.core.businessman.entity.TSellerSubsidy;
import com.xmniao.xmn.core.system_settings.entity.TUser;
import com.xmniao.xmn.core.util.FastfdsConstant;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：SellerService
 * 
 * 类描述： 商家(商户)
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年11月11日15时22分21秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@Service
public class SellerSubsidyService extends BaseService<TSellerSubsidy> {
	
	@Autowired
	private SellerSubsidyDao sellerSubsidyDao;

	@Override
	protected BaseDao<TSellerSubsidy> getBaseDao() {
		return sellerSubsidyDao;
	}
	
	/**
	 * 初始化商家补贴发放信息
	 */
	public void initSellerSubsidyInfo(Integer idsubsidy, ModelAndView model) {
		if(null != idsubsidy){
			TSellerSubsidy sellerSubsidyList = getObject(idsubsidy.longValue());
			model.addObject("sellerSubsidyList", sellerSubsidyList);
		}
	}
	/**
	 * 添加或则编辑商家补贴发放信息
	 * @param seller
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public Resultable addOrUpdteSellerSubsidy(TSellerSubsidy tSellerSubsidy,HttpServletRequest request){
		Resultable resultable=new Resultable();
		this.log.info("sellerSubsidyService-->list TSellerSubsidy=" + tSellerSubsidy);
		Date d=new Date();
		if(tSellerSubsidy.getIdsubsidy() == null){//添加
			tSellerSubsidy.setDatecreated(d);
			tSellerSubsidy.setCreator(((TUser)request.getSession().getAttribute("currentUs")).getUsername());
			sellerSubsidyDao.add(tSellerSubsidy);
			resultable.setSuccess(true);
		}else{
			tSellerSubsidy.setDateupdated(d);
			tSellerSubsidy.setUpdator(((TUser)request.getSession().getAttribute("currentUs")).getUsername());
			sellerSubsidyDao.update(tSellerSubsidy);
			resultable.setSuccess(true);
		}
		return resultable;
	}
	
	/**
	 * 添加或则编辑商家补贴发放信息
	 * @param seller
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public ResultFile importDataSellerSubsidy(MultipartFile multipartFile,String userName){
		ResultFile rf=null;
		try {
			Date d=new Date();
			List<TSellerSubsidy> subsidies = PoiImport.dataImport(multipartFile, TSellerSubsidy.class);
			
			for(int i=0;i<subsidies.size();i++){
				setDefualtValue(subsidies.get(i),d,userName);
			}
			super.addBatch(subsidies);
			rf = new ResultFile(FastfdsConstant.FILE_UPLOAD_FASTDFS_SUCCESS, "导入成功");
		} catch (Exception e) {
			log.error("文件上传失败", e);
			rf = new ResultFile(FastfdsConstant.FILE_UPLOAD_FASTDFS_FAILURE, "导入失败,请重新导入");
		} finally {
			super.fireLoginEvent( new String[]{"商家补贴发放","导入操作","商家补贴发放信息导入",""}, rf.getStatus());
		}
		return rf;
		
	/*	Resultable resultable=new Resultable();
		this.log.info("sellerSubsidyService-->list TSellerSubsidy=" + tSellerSubsidy);
		Date d=new Date();
		if(tSellerSubsidy.getIdsubsidy() == null){//添加
			tSellerSubsidy.setDatecreated(d);
			tSellerSubsidy.setCreator(((TUser)request.getSession().getAttribute("currentUs")).getUsername());
			sellerSubsidyDao.add(tSellerSubsidy);
			resultable.setSuccess(true);
		}else{
			tSellerSubsidy.setDateupdated(d);
			tSellerSubsidy.setUpdator(((TUser)request.getSession().getAttribute("currentUs")).getUsername());
			sellerSubsidyDao.update(tSellerSubsidy);
			resultable.setSuccess(true);
		}
		return resultable;*/
	}
	
	
	private void setDefualtValue(TSellerSubsidy tSellerSubsidy,Date d,String userName){
		tSellerSubsidy.setDatecreated(d);
		tSellerSubsidy.setCreator(userName);
		tSellerSubsidy.setIssuestatus(0);
	}
	
}
