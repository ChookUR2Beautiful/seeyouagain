/**
 * 
 */
package com.xmniao.xmn.core.fresh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.fresh.dao.TFreshLabelDao;
import com.xmniao.xmn.core.fresh.entity.TFreshLabel;
import com.xmniao.xmn.core.live_anchor.entity.TSellerPackage;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TFreshLabelService
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人：
 * 
 * 创建时间：2017年2月20日 下午6:33:00 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */

@Service
public class TFreshLabelService extends BaseService<TFreshLabel> {
	
	@Autowired
	private TFreshLabelDao freshLabelDao;
	
	@Override
	protected BaseDao getBaseDao() {
		return freshLabelDao;
	}
	
	
	

	/**
	 * 获取商家列表信息
	 * 
	 * @param seller
	 * @return
	 */
	public List<TFreshLabel> getFreshLabelInfoList(TFreshLabel freshLabel) {
		// 商家列表内容
		List<TFreshLabel> freshLabelList = freshLabelDao.getLabelInfoList(freshLabel);
		return freshLabelList;
	}
	

	/**
	 * 方法描述：保存操作
	 * 创建人：  caiyl<br/>
	 * 创建时间：2017年2月21日上午10:33:49 <br/>
	 * @param TFreshLabel
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveActivity(TFreshLabel record) {
		freshLabelDao.insert(record);
	}




	/**
	 * 方法描述：修改操作
	 * 创建人： caiyl
	 * 创建时间：2017年2月21日下午10:08:59 <br/>
	 * @param TFreshLabel
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateTFreshLabel(TFreshLabel record) {
		freshLabelDao.update(record);
	}






	/**
	 * 方法描述：删除活动
	 * 创建人：  caiyl<br/>
	 * 创建时间：2017年2月22日上午9:50:03 <br/>
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteActivity(Integer id) {
		freshLabelDao.deleteByPrimaryKey(id);
	}




	/**
	 * 方法描述：加载下拉框
	 * 创建人： jianming <br/>
	 * 创建时间：2017年3月3日上午9:50:50 <br/>
	 * @param freshLabel
	 * @return
	 */
	public List<TFreshLabel> getLabelChoose(TFreshLabel freshLabel) {
		return freshLabelDao.getLabelChoose(freshLabel);
	}

}
