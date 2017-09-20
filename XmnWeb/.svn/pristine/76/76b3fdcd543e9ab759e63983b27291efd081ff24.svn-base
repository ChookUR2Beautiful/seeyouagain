package com.xmniao.xmn.core.fresh.dao;

import java.util.List;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.fresh.entity.FreshWord;

public interface FreshWordDao extends BaseDao<FreshWord>{
    int deleteByPrimaryKey(Long id);

    int insert(FreshWord record);

    int insertSelective(FreshWord record);

    FreshWord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FreshWord record);

    int updateByPrimaryKey(FreshWord record);

	/**
	 * 方法描述：查询前十真实搜索
	 * 创建人： jianming <br/>
	 * 创建时间：2017年1月9日下午4:19:33 <br/>
	 * @param freshWord
	 * @return
	 */
	List<FreshWord> getMaxTenWord(FreshWord freshWord);

	/**
	 * 方法描述：加载列表
	 * 创建人： jianming <br/>
	 * 创建时间：2017年1月9日下午6:13:00 <br/>
	 * @param freshWord
	 * @return
	 */
	List<FreshWord> getPageList(FreshWord freshWord);

}