package com.xmniao.xmn.core.xmer.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.util.dataSource.DataSource;

/**
 * 
 * @ClassName:UrsEarningsRelationDao
 * @Description:会员关系链信息表Dao
 * @Author:xw
 * @Date:2017年5月2日下午6:30:59
 */
@Repository
public interface UrsEarningsRelationDao {

	/**
	 * 
	 * @Title:queryRelationList
	 * @Description:根据uid查询 寻蜜客 下级和下下级 uid列表
	 * @param uid 当前寻蜜客uid
	 * @return List<Integer> 下级和下下级 寻蜜客uid List集合
	 * 2017年5月2日下午6:34:36
	 */
	@DataSource("burs")
	List<Integer> queryRelationList(Integer uid);

	/**
	 * 查询直接寻蜜客id集合
	 * @Title:listDirectRelation
	 * @Description:查询下级寻蜜客id
	 * @param uid
	 * @return List<Integer>
	 * 2017年5月25日上午11:05:20
	 */
	@DataSource("burs")
	List<Integer> listDirectRelation(Integer uid);
	
	
	/**
	 * 
	 * @Title:queryRelationChain
	 * @Description: 根据uid查询寻蜜客会员关系链
	 * @param uid 寻蜜客uid
	 * @return String
	 * 2017年5月3日下午2:41:53
	 */
	@DataSource("burs")
	String queryRelationChain(String uid);

	
}
