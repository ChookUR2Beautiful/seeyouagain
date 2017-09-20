/**
 * 
 */
package com.xmniao.xmn.core.fresh.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.fresh.dao.FreshImageDao;
import com.xmniao.xmn.core.fresh.entity.FreshImage;
import com.xmniao.xmn.core.fresh.util.FreshConstants;

/**
 * 
 * 项目名称：XmnWeb1
 * 
 * 类名称：FreshImageService
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人： jianming
 * 
 * 创建时间：2017年1月4日 下午5:43:14
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@Service
public class FreshImageService extends BaseService<FreshImage> {

	@Autowired
	private FreshImageDao freshImageDao;

	@Override
	protected BaseDao getBaseDao() {
		return freshImageDao;
	}

	/**
	 * 方法描述：添加或更改banner 创建人： jianming <br/>
	 * 创建时间：2017年1月4日下午6:31:24 <br/>
	 * 
	 * @param list
	 * @param id
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveBanner(List<FreshImage> list) {
		freshImageDao.deleteAllByTypeId(list.get(0).getTypeId(), list.get(0).getType());
		// 添加
		Integer sort = 10;
		for (FreshImage freshImage : list) {
			freshImage.setState(1);
			freshImage.setCreateTime(new Date());
			freshImage.setUpdateTime(new Date());
			freshImage.setSort(sort--);
			String jumpUrl = freshImage.getJumpUrl();

			if (StringUtils.isBlank(jumpUrl)) {
				if (freshImage.getType() != FreshConstants.MODULE_IMAGE_TYPE) {
					freshImage.setJumpType(3);
				}
			} else if (jumpUrl.indexOf("[") == 0) {
				// 跳转商品详情
				int indexOf = jumpUrl.indexOf("[");
				int indexOf2 = jumpUrl.indexOf("]");
				Long id = new Long(jumpUrl.substring(indexOf + 1, indexOf2));
				if (freshImage.getJumpType() == 1) {
					freshImage.setJumpProductId(id);
				} else if (freshImage.getJumpType() == 6) {
					freshImage.setJumpActivityId(id.intValue());
				}
				freshImage.setRemark(freshImage.getJumpUrl());
				freshImage.setJumpUrl(null);
			} else {
				freshImage.setJumpType(2);
			}

			freshImageDao.insertSelective(freshImage);

		}
	}

}
