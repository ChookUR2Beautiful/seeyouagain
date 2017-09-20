/**
 * 
 */
package com.xmniao.xmn.core.businessman.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.businessman.dao.RecommendSpecialDao;
import com.xmniao.xmn.core.businessman.entity.RecommendSpecial;
import com.xmniao.xmn.core.businessman.util.SellerConstants;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：RecommendSpecialService
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人： jianming
 * 
 * 创建时间：2017年2月22日 下午1:41:44 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service
public class RecommendSpecialService extends BaseService<RecommendSpecial>{

	@Autowired
	private RecommendSpecialDao recommendSpecialDao;
	
	@Override
	protected BaseDao getBaseDao() {
		return recommendSpecialDao;
	}

	/**
	 * 方法描述：加载专题下拉框
	 * 创建人： jianming <br/>
	 * 创建时间：2017年2月22日下午2:02:52 <br/>
	 * @param recommendSpecial
	 * @return
	 */
	public List<RecommendSpecial> getSpecialChoose(RecommendSpecial recommendSpecial) {
		return recommendSpecialDao.getSpecialChoose(recommendSpecial);
	}

	/**
	 * 方法描述：修改专题为推荐
	 * 创建人： jianming <br/>
	 * 创建时间：2017年2月22日下午3:32:37 <br/>
	 * @param recommendSpecial
	 */
	public void addSpecial(RecommendSpecial recommendSpecial) {
		if(recommendSpecial.getCityId()==null&&recommendSpecial.getProvinceId()==null){
			recommendSpecial.setIsNationwide(SellerConstants.RECOMMEND_SPECIAL_IS_NATIONWIDE);
		}else{
			recommendSpecial.setIsNationwide(SellerConstants.RECOMMEND_SPECIAL_IS_NOT_NATIONWIDE);
		}
		if(recommendSpecial.getHomeSort()==null){
			recommendSpecial.setHomeSort(0);
		}
		recommendSpecialDao.updateRecommend(recommendSpecial);
	}

	/**
	 * 方法描述：修改排序
	 * 创建人： jianming <br/>
	 * 创建时间：2017年2月22日下午5:18:57 <br/>
	 * @param id
	 * @param homeSort
	 */
	public void updateSort(Integer id, Integer homeSort) {
		RecommendSpecial recommendSpecial = new RecommendSpecial();
		recommendSpecial.setId(id);
		recommendSpecial.setHomeSort(homeSort);
		recommendSpecialDao.updateRecommend(recommendSpecial);
	}

	/**
	 * 方法描述：删除推荐
	 * 创建人： jianming <br/>
	 * 创建时间：2017年2月22日下午5:36:27 <br/>
	 * @param id
	 */
	public void deleteSpecial(Integer id) {
		recommendSpecialDao.deleteSpecial(id);
	}


}
