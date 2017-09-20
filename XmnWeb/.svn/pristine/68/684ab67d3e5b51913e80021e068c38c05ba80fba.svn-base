/**
 * 
 */
package com.xmniao.xmn.core.business_statistics.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.business_statistics.dao.TCelebrityAriticleCarouselDao;
import com.xmniao.xmn.core.business_statistics.dao.TCelebrityAriticleDao;
import com.xmniao.xmn.core.business_statistics.entity.TCelebrityAriticle;
import com.xmniao.xmn.core.business_statistics.entity.TCelebrityAriticleCarousel;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TCelebrityAriticleService
 * 
 * 类描述： saas商户端文章Service
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-12-12 下午6:21:09 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service
public class TCelebrityAriticleService extends BaseService<TCelebrityAriticle> {
	
	/**
	 * 注入文章服务
	 */
	@Autowired
	private TCelebrityAriticleDao ariticleDao;
	
	/**
	 * 注入文章相册服务
	 */
	@Autowired
	private TCelebrityAriticleCarouselDao photoDao;

	@SuppressWarnings("rawtypes")
	@Override
	protected BaseDao getBaseDao() {
		return ariticleDao;
	}


	/**
	 * 方法描述：保存文章信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-13下午2:36:15 <br/>
	 * @param article
	 */
	public void saveInfo(TCelebrityAriticle article) {
		article.setCreateTime(new Date());
		ariticleDao.add(article);
		syncPhotos(article);
	}


	/**
	 * 方法描述：更新文章信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-13下午3:48:08 <br/>
	 * @param article
	 */
	public void updateInfo(TCelebrityAriticle article) {
		ariticleDao.update(article);
		syncPhotos(article);
	}


	/**
	 * 方法描述：同步文章相册信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-13下午3:50:24 <br/>
	 * @param article
	 */
	private void syncPhotos(TCelebrityAriticle article) {
		List<TCelebrityAriticleCarousel> imageList=new ArrayList<TCelebrityAriticleCarousel>();
		Long celebrityAriticleId = article.getId();
		Integer status = article.getStatus();//1:单页图文  2:多图相册
		if(status!=null && status.intValue()==2){
			photoDao.deleteByAriticleId(celebrityAriticleId);
			String relativePath = article.getRelativePath();
			if(StringUtils.isNotBlank(relativePath)){
				int order=0;
				String[] relativePathList = relativePath.split(";");
				for(String image:relativePathList){
					TCelebrityAriticleCarousel carousel=new TCelebrityAriticleCarousel();
					carousel.setCelebrityAriticleId(celebrityAriticleId);
					carousel.setImage(image);
					carousel.setOrder(order);
					imageList.add(carousel);
					order++;
				}
			}
			
			if(imageList!=null && imageList.size()>0){
				photoDao.addPhotoBatch(imageList);
			}
		}
	}
	
	

}
