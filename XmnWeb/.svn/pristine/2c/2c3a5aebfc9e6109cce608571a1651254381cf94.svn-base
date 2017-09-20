/**
 * 
 */
package com.xmniao.xmn.core.fresh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.fresh.dao.MallPackageImgDao;
import com.xmniao.xmn.core.fresh.entity.MallPackageImg;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：MallPackageImgService
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人： jianming
 * 
 * 创建时间：2017年8月7日 下午2:54:54 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service
public class MallPackageImgService extends BaseService<MallPackageImg>{

	@Autowired
	private MallPackageImgDao mallPackageImgDao;
	
	@Override
	protected BaseDao getBaseDao() {
		return mallPackageImgDao;
	}

	/**
	 * 方法描述：删除所有套餐图片
	 * 创建人： jianming <br/>
	 * 创建时间：2017年8月10日上午10:41:38 <br/>
	 * @param id
	 */
	public void deletePackageImgs(Long id) {
		mallPackageImgDao.deletePackageImgs(id);
	}

}
