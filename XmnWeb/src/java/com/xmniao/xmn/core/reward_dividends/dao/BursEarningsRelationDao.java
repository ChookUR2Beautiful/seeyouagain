package com.xmniao.xmn.core.reward_dividends.dao;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.live_anchor.entity.BLiver;
import com.xmniao.xmn.core.reward_dividends.entity.BursEarningsRelation;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：BursEarningsRelationDao
 * 
 * 类描述： 会员收益关系链DAO
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2017-5-15 下午4:27:40 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public interface BursEarningsRelationDao extends BaseDao<BursEarningsRelation>{
	
	/**
	 * 
	 * 方法描述：根据ID查询会员收益关系链 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-5-16上午11:18:18 <br/>
	 */
	@DataSource(value="burs")
	BursEarningsRelation getObjectById(Long id);
	
	/**
	 * 
	 * 方法描述：查询会员收益关系链列表 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-5-16上午11:18:18 <br/>
	 */
	@DataSource(value="burs")
	List<BursEarningsRelation> getList(BursEarningsRelation relation);
	
	/**
	 * 
	 * 方法描述：统计会员收益关系链记录数 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-5-16上午11:18:18 <br/>
	 */
	@DataSource(value="burs")
	Long count(BursEarningsRelation relation);
	
	/**
	 * 
	 * 方法描述：添加会员收益关系链 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-5-16上午11:18:18 <br/>
	 */
	@DataSource(value="burs")
	void add(BursEarningsRelation relation);
	
	/**
	 * 
	 * 方法描述：更新会员收益关系链 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-5-16上午11:18:18 <br/>
	 */
	@DataSource(value="burs")
	Integer update(BursEarningsRelation relation);

	/**
	 * 方法描述：根据UID,会员渠道等统计下线人数 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-5-19下午2:01:56 <br/>
	 * @param bursRelationInfo
	 * @return
	 */
	@DataSource(value="burs")
	long countJuniorsNum(BursEarningsRelation bursRelationInfo);
	
	/**
	 * 方法描述：获取会员列表 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-5-19下午2:01:56 <br/>
	 * @param bursRelationInfo
	 * @return
	 */
	@DataSource(value="burs")
	List<BLiver> getLiverInfoList(BLiver liver);
	
	/**
	 * 
	 * 方法描述：获取等下级会员关系链信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-5-19下午6:16:09 <br/>
	 * @param bursRelationInfo
	 * @return
	 */
	@DataSource(value="burs")
	List<BursEarningsRelation> getJuniorList(BursEarningsRelation bursRelationInfo);
	
	/**
	 *
	 * 方法描述：获取会员收益关系链基础信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-5-22下午2:21:46 <br/>
	 * @param paramMap
	 * @return
	 */
	@DataSource(value="burs")
	List<BursEarningsRelation> getBaseList(Map<String,Object> paramMap);

	/**
	 *
	 * 方法描述：更新间接上级为null <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-5-22下午3:59:06 <br/>
	 * @param bursRelation
	 * @return
	 */
	@DataSource(value="burs")
	int updateIndirect2Null(BursEarningsRelation bursRelation);


	@DataSource(value="burs")
	BursEarningsRelation getBursEarningsRelationByUid(BursEarningsRelation bursRelationInfo);
	
	@DataSource(value="burs")
	List<BursEarningsRelation> getLowerBursEarningsRelationList(BursEarningsRelation bursRelationInfo);

	@DataSource("burs")
    List<BursEarningsRelation> selectSubNodeList(@Param("uid") Integer uid);

	@DataSource("burs")
    Integer selectParentIdByOriented(@Param("uid") Integer uid, @Param("objectOriented") int objectOriented);
	
	/**
	 * 方法描述：删除寻密客身分, 适应于V客推荐主播 <br/>
	 * 创建人： Administrator <br/>
	 * 创建时间：2017年8月11日上午10:22:13 <br/>
	 * @param relation
	 * @return
	 */
	@DataSource("burs")
	Integer deleteRelationByUid(BursEarningsRelation relation);

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年9月5日上午10:25:10 <br/>
	 * @param objectOriented
	 * @param uid
	 * @return
	 */
	@DataSource("burs")
	BursEarningsRelation getByUid(@Param("objectOriented") Integer objectOriented,@Param("uid") Integer uid);

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年9月5日下午5:22:13 <br/>
	 * @param bursEarningsRelation
	 */
	@DataSource("burs")
	int insertSelective(BursEarningsRelation bursEarningsRelation);

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年9月6日上午9:58:29 <br/>
	 * @param objectOriented
	 * @return
	 */
	@DataSource("burs")
	List<BursEarningsRelation> getListByType(BursEarningsRelation burs);
}