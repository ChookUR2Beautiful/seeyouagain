package com.xmniao.xmn.core.businessman.dao;

import java.util.List;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.businessman.entity.RecommendSpecial;

public interface RecommendSpecialDao extends BaseDao<RecommendSpecial>{
    int deleteByPrimaryKey(Integer id);

    int insert(RecommendSpecial record);

    int insertSelective(RecommendSpecial record);

    RecommendSpecial selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RecommendSpecial record);

    int updateByPrimaryKeyWithBLOBs(RecommendSpecial record);

    int updateByPrimaryKey(RecommendSpecial record);

	/**
	 * 方法描述：加载专题下拉框
	 * 创建人： jianming <br/>
	 * 创建时间：2017年2月22日下午2:05:33 <br/>
	 * @param recommendSpecial
	 * @return
	 */
	List<RecommendSpecial> getSpecialChoose(RecommendSpecial recommendSpecial);

	/**
	 * 方法描述：修改专题为推荐
	 * 创建人： jianming <br/>
	 * 创建时间：2017年2月22日下午3:34:08 <br/>
	 * @param recommendSpecial
	 */
	void updateRecommend(RecommendSpecial recommendSpecial);

	/**
	 * 方法描述：删除推荐
	 * 创建人： jianming <br/>
	 * 创建时间：2017年2月22日下午5:36:53 <br/>
	 * @param id
	 */
	void deleteSpecial(Integer id);
}