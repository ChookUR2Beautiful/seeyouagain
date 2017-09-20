/**
 * 
 */
package com.xmniao.xmn.core.fresh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.fresh.dao.FreshWordDao;
import com.xmniao.xmn.core.fresh.entity.FreshWord;

/**
 * 
 * 项目名称：XmnWeb1
 * 
 * 类名称：FreshWordService
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人： jianming
 * 
 * 创建时间：2017年1月9日 上午9:28:15 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service
public class FreshWordService extends BaseService<FreshWord> {

	@Autowired
	private FreshWordDao freshWordDao;
	
	@Override
	protected BaseDao getBaseDao() {
		return freshWordDao;
	}

	/**
	 * 方法描述：查询前10真实搜索
	 * 创建人： jianming <br/>
	 * 创建时间：2017年1月9日下午4:18:54 <br/>
	 * @param freshWord
	 * @return
	 */
	public List<FreshWord> getMaxTenWord(FreshWord freshWord) {
		return freshWordDao.getMaxTenWord(freshWord);
	}

	/**
	 * 方法描述：分页加载
	 * 创建人： jianming <br/>
	 * 创建时间：2017年1月9日下午6:12:25 <br/>
	 * @param freshWord
	 * @return
	 */
	public List<FreshWord> getPageList(FreshWord freshWord) {
		return freshWordDao.getPageList(freshWord);
	}

	/**
	 * 方法描述：删除
	 * 创建人： jianming <br/>
	 * 创建时间：2017年1月9日下午6:19:47 <br/>
	 * @param id
	 */
	public void delete(Long id) {
		freshWordDao.deleteByPrimaryKey(id);
	}

	
}
