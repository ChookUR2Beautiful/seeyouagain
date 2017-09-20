/**
 * 
 */
package com.xmniao.xmn.core.businessman.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.businessman.dao.CatehomeCommentDao;
import com.xmniao.xmn.core.businessman.dao.SellerDao;
import com.xmniao.xmn.core.businessman.dao.UnsignedSellerDao;
import com.xmniao.xmn.core.businessman.entity.CatehomeComment;
import com.xmniao.xmn.core.businessman.entity.ExperienceComment;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：CateCommentService
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人： jianming
 * 
 * 创建时间：2017年5月18日 上午10:00:10 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */

@Service
public class CatehomeCommentService extends BaseService<CatehomeComment>{

	@Autowired
	private  CatehomeCommentDao catehomeCommentDao;
	
	@Autowired
	private ExperienceCommentService experienceCommentService;
	
	@Autowired
	private SellerDao sellerDao;
	
	@Autowired
	private UnsignedSellerDao unsignedSellerDao;
	
	@Override
	protected BaseDao getBaseDao() {
		return catehomeCommentDao;
	}

	/**
	 * 方法描述：加载点评商户下拉框
	 * 创建人： jianming <br/>
	 * 创建时间：2017年5月18日上午11:10:13 <br/>
	 * @param experienceComment
	 * @return
	 */
	public List<ExperienceComment> getCommentChoose(ExperienceComment experienceComment) {
		return experienceCommentService.getCommentChoose(experienceComment);
	}
	
	@Override
	public void add(CatehomeComment catehomeComment){
		ExperienceComment experienceComment = experienceCommentService.getObject(catehomeComment.getCommontId());
		catehomeComment.setSellerid(experienceComment.getSellerid());
	catehomeComment.setSellerType(experienceComment.getSellerType());
		catehomeComment.setCreateTime(new Date());
		catehomeComment.setUpdateTime(new Date());
		catehomeCommentDao.add(catehomeComment);
	}
	
	@Override
	public List<CatehomeComment> getList(CatehomeComment catehomeComment){
		 List<CatehomeComment> list = catehomeCommentDao.getList(catehomeComment);
		 if(list.isEmpty()){
			 return list;
		 }
		 List<CatehomeComment> newList= new ArrayList<>();
		 if(catehomeComment.getProvinceId()!=null&&catehomeComment.getCityId()!=null){
			 for (CatehomeComment catehomeComment2 : list) {
				 if(catehomeComment2.getSellerType()==1){
					 //签约
					long i= sellerDao.checkIdAtArea(catehomeComment2.getSellerid(),catehomeComment.getProvinceId(),catehomeComment.getCityId());
					if(i>0){
						newList.add(catehomeComment2);
					}
				 }else{
					 //非签约
					 long i= unsignedSellerDao.checkIdAtArea(catehomeComment2.getSellerid(),catehomeComment.getProvinceId(),catehomeComment.getCityId());
					 if(i>0){
							newList.add(catehomeComment2);
						}
				 }
			 }
			 return newList;
		 }
		 return list;
	}

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年5月18日下午3:38:54 <br/>
	 * @param id
	 * @param sort
	 */
	public int updateCommentSort(Integer id, Integer sort) {
		return catehomeCommentDao.updateCommentSort(id,sort);
	}

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年5月18日下午3:42:22 <br/>
	 * @param id
	 */
	public int deleteComment(Integer id) {
		return catehomeCommentDao.deleteComment(id);
	}
	

}
