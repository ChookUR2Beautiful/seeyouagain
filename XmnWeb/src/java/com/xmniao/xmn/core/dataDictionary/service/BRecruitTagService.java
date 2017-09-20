package com.xmniao.xmn.core.dataDictionary.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.dataDictionary.dao.BRecruitTagDao;
import com.xmniao.xmn.core.dataDictionary.entity.BRecruitTag;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：BRecruitTagService
 * 
 * 创建人： huang'tao
 * 
 * 创建时间：2016-5-30下午6:03:04
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@Service
public class BRecruitTagService extends BaseService<BRecruitTag> {

	@Autowired
	private BRecruitTagDao recruitTagDao;

	@SuppressWarnings("rawtypes")
	@Override
	protected BaseDao getBaseDao() {
		return recruitTagDao;
	}

	/**
	 * 
	 * @param recruitTag
	 * @param pageable
	 */
	public void getListPage(BRecruitTag recruitTag, Pageable<BRecruitTag> pageable) {
		pageable.setContent(this.getRecruitTagList(recruitTag));
		pageable.setTotal(this.countTag(recruitTag));
	}

	/**
	 * 统计标签数量
	 * 创建人： huang'tao
	 * 创建时间：2016-5-31上午9:57:25
	 * @param recruitTag
	 * @return
	 */
	public Long countTag(BRecruitTag recruitTag) {
		return recruitTagDao.count(recruitTag);
	}

	/**
	 * 获取标签列表
	 * 创建人： huang'tao
	 * 创建时间：2016-5-31上午9:57:22
	 * @param recruitTag
	 * @return
	 */
	public List<BRecruitTag> getRecruitTagList(BRecruitTag recruitTag) {
		return recruitTagDao.getList(recruitTag);
	}
	
	/**
	 * 获取标签
	 */
	public BRecruitTag getRecruitTag(Long id){
		return recruitTagDao.getRecruitTag(id);
	};

	/**
	 * 更新标签
	 */
	public Integer update(BRecruitTag recruitTag){
		return recruitTagDao.update(recruitTag);
	}
	
	public void add(BRecruitTag recruitTag){
		 recruitTagDao.add(recruitTag);
	}
}
