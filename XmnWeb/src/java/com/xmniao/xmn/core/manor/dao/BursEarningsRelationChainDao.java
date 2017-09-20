package com.xmniao.xmn.core.manor.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xmniao.xmn.core.manor.entity.BursEarningsRelationChain;
import com.xmniao.xmn.core.reward_dividends.entity.BursEarningsRelation;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

public interface BursEarningsRelationChainDao {
	@DataSource("burs")
	int deleteByPrimaryKey(BursEarningsRelationChain record);

	@DataSource("burs")
	int insert(BursEarningsRelationChain record);

	@DataSource("burs")
	int insertSelective(BursEarningsRelationChain record);

	/**
	 * 方法描述：查询下级关系链 <br/>
	 * 创建人： Administrator <br/>
	 * 创建时间：2017年7月14日下午5:01:50 <br/>
	 * @param record
	 * @return
	 */
	@DataSource("burs")
	List<BursEarningsRelationChain> getBursEarningsRelationChainList(
			BursEarningsRelationChain record);

	/**
	 * 方法描述：查询下级关系链总<br/>
	 * 创建人： Administrator <br/>
	 * 创建时间：2017年7月14日下午5:01:53 <br/>
	 * @param record
	 * @return
	 */
	@DataSource("burs")
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public Long countBursEarningsRelationChain(BursEarningsRelationChain record);

	/**
	 * 方法描述：根据uid查询
	 * 创建人： jianming <br/>
	 * 创建时间：2017年9月5日下午4:26:45 <br/>
	 * @param childId
	 * @param i
	 * @return
	 */
	@DataSource("burs")
	List<BursEarningsRelation> getByUid(@Param("childId")Integer childId,@Param("objectOriented") Integer objectOriented);

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年9月5日下午5:32:36 <br/>
	 * @param list
	 * @return
	 */
	@DataSource("burs")
	int addBatch(List<BursEarningsRelationChain> list);
}