/**
 * 
 */
package com.xmniao.xmn.core.fresh.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.fresh.dao.MallPackageDao;
import com.xmniao.xmn.core.fresh.entity.MallPackage;
import com.xmniao.xmn.core.fresh.entity.MallPackageImg;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：MallPackageService
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人： jianming
 * 
 * 创建时间：2017年8月7日 下午2:53:53 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service
public class MallPackageService extends BaseService<MallPackage>{

	@Autowired
	private MallPackageDao mallPackageDao;
	
	@Autowired
	private MallPackageProductService mallPackageProductService;
	
	@Autowired
	private MallPackageImgService mallPackageImgService;
	
	@Override
	protected BaseDao getBaseDao() {
		return mallPackageDao;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Integer update(MallPackage mallPackage){
		String productIds = mallPackage.getProductIds();
		int i=mallPackageDao.update(mallPackage);
		mallPackageProductService.updatePackageIdByIds(Arrays.asList(productIds.split(",")),mallPackage.getId());
		addImgs(mallPackage);
		return i;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void add(MallPackage mallPackage){
		String productIds = mallPackage.getProductIds();
		mallPackageDao.add(mallPackage);
		mallPackageProductService.updatePackageIdByIds(Arrays.asList(productIds.split(",")),mallPackage.getId());
		addImgs(mallPackage);
	}

	private void addImgs(MallPackage mallPackage) {
		mallPackageImgService.deletePackageImgs(mallPackage.getId());
		List<String> imgUrls = Arrays.asList(mallPackage.getImgUrls().split(","));
		List<MallPackageImg> mallPackageImgs = new ArrayList<>();
		int i=imgUrls.size();
		for (String url : imgUrls) {
			MallPackageImg mallPackageImg = new MallPackageImg();
			mallPackageImg.setImgUrl(url);
			mallPackageImg.setImgType(Byte.valueOf("2"));
			mallPackageImg.setPackageId(mallPackage.getId());
			mallPackageImg.setSort(i--);
			mallPackageImgs.add(mallPackageImg);
		}
		MallPackageImg mallPackageImg = new MallPackageImg();
		mallPackageImg.setImgUrl(mallPackage.getPackageImgMine());
		mallPackageImg.setImgType(Byte.valueOf("1"));
		mallPackageImg.setPackageId(mallPackage.getId());
		mallPackageImgs.add(mallPackageImg);
		mallPackageImgService.addBatch(mallPackageImgs);
	}

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年8月10日上午11:39:24 <br/>
	 * @param ids
	 * @param status
	 * @return
	 */
	public void updateStatusBatch(List<String> ids, String status) {
		mallPackageDao.updateStatusBatch(ids,status);
	}


}
