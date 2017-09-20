package com.xmniao.xmn.core.xmnburs.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

import com.xmniao.xmn.core.live_anchor.entity.BLiver;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;
import com.xmniao.xmn.core.xmnburs.entity.Burs;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：BursDao
 * 
 * 类描述： 寻蜜鸟操作b_urs表DAO
 * 
 * 创建人： Chen Bo
 * 
 * 创建时间：2016年3月23日 下午5:07:25 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public interface BursDao{

	/*
	 * 新增寻蜜鸟用户
	 */
	@DataSource("burs")
	Integer addUrs(Burs burs);
	
	/*
	 * 修改寻蜜鸟用户
	 */
	@DataSource("burs")
	int updateUrs(Burs burs);
	
	/*
	 * 检测该账号是否存在
	 */
	@DataSource("burs")
	long checkAccount(Burs burs);
	
	/*
	 * 获取寻蜜鸟用户
	 */
	@DataSource("burs")
	Burs getUrs(Burs burs);
	
	/*
	 * 获取主播关联的寻蜜鸟用户详情
	 */
	@DataSource("burs")
	BLiver getUrsDetailInfo(BLiver liver);
	
	/**
	 * 
	 * 方法描述：根据uids获取寻蜜鸟用户
	 * 创建人：  huang'tao
	 * 创建时间：2016-9-2上午11:24:46
	 * @param uids
	 * @return
	 */
	@DataSource("burs")
	List<Burs> getUrsListByUids(Object[] uids);
	
	
	/**
	 * 
	 * 方法描述：根据UIDS获取会员活动收益等级
	 * 创建人：  huang'tao
	 * 创建时间：2016-9-2上午11:24:46
	 * @param uids
	 * @return
	 */
	@DataSource("burs")
	List<Map<String,Object>> getUrsEarningsRankByUids(Object[] uids);

	/**
	 * 方法描述：根据条件获取用户列表<br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年3月29日下午2:06:06 <br/>
	 * @param burs
	 * @return
	 */
	@DataSource("burs")
	List<Burs> getList(Burs burs);
	
	/**
	 * 
	 * 方法描述：获取用户数量 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-8-16下午5:39:46 <br/>
	 * @param burs
	 * @return
	 */
	@DataSource("burs")
	long count(Burs burs);

	/**
	 * 方法描述：获取包含等级信息的用户列表
	 * 创建人： jianming <br/>
	 * 创建时间：2017年4月5日下午8:04:43 <br/>
	 * @param array
	 * @return
	 */
	@DataSource("burs")
	List<Burs> getUrsListAndLevelByUids(Object[] array);

	@DataSource("burs")
    List<Map<String,String>> selectLiverInfo(List<BLiver> liverList);

	@DataSource("burs")
    Burs queryBurByUname(@Param("uname") String uname);


	@DataSource("burs")
	String selectUrsName(@Param("uid") Integer uid);

    /**
     * 方法描述：在此处添加方法描述 <br/>
     * 创建人： jianming <br/>
     * 创建时间：2017年6月14日下午6:46:39 <br/>
     * @param array
     * @return
     */
    @DataSource("burs")
    List<Burs> getAnchorIdByUids(Object[] array);


	@DataSource("burs")
	Burs queryBurByUid(@Param("uid") Integer uid);

	@DataSource("burs")
	List<Burs> selectByUids(@Param("uids") HashSet<Integer> uids);
	
	/*
	 * 获取主播关联的寻蜜鸟用户详情
	 */
	@DataSource("burs")
	List<BLiver> getUrsDetailInfoList(Object[] array);
	
	/**
	 * 方法描述：查询V客关联的信息 <br/>
	 * 创建人： Administrator <br/>
	 * 创建时间：2017年8月8日下午4:22:26 <br/>
	 * @param array
	 * @return
	 */
	@DataSource("burs")
	List<Burs> getVerUrsDetailInfoList(Object[] array);
	
}