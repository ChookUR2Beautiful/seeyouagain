package com.xmniao.xmn.core.fresh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.fresh.entity.IndianaBout;

public interface IndianaBoutDao extends BaseDao<IndianaBout>{
    int deleteByPrimaryKey(Integer id);

    int insert(IndianaBout record);

    int insertSelective(IndianaBout record);

    IndianaBout selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(IndianaBout record);

    int updateByPrimaryKey(IndianaBout record);

	/**
	 * 方法描述：根据活动id查找第一个
	 * 创建人： jianming <br/>
	 * 创建时间：2017年2月24日上午10:36:52 <br/>
	 * @param activityId
	 * @return
	 */
	IndianaBout getByActivityId(Integer activityId);

	/**
	 * 方法描述：查询份数买完的期
	 * 创建人： jianming <br/>
	 * 创建时间：2017年2月27日上午11:09:08 <br/>
	 * @return
	 */
	List<IndianaBout> getSellOut();

	/**
	 * 方法描述：还原库存
	 * 创建人： jianming <br/>
	 * 创建时间：2017年2月27日上午11:52:04 <br/>
	 * @param codeId
	 * @param string 
	 */
	void restoreStore(@Param("codeId")Long codeId, @Param("pvIds")String pvIds);

	/**
	 * 方法描述：统计机器人购买次数
	 * 创建人： jianming <br/>
	 * 创建时间：2017年2月27日下午2:42:48 <br/>
	 * @param boutId
	 * @param robotId
	 * @return
	 */
	Long countWinnerVeces(@Param("boutId")Integer boutId, @Param("robotId")Integer robotId);

	/**
	 * 方法描述：添加下一期
	 * 创建人： jianming <br/>
	 * 创建时间：2017年2月27日下午2:55:57 <br/>
	 * @param indianaBout
	 */
	void insertNextBout(IndianaBout indianaBout);

	/**
	 * 方法描述：查询还有2分钟结束的活动并且人数不够的
	 * 创建人： jianming <br/>
	 * 创建时间：2017年2月27日下午3:45:06 <br/>
	 * @return
	 */
	List<IndianaBout> getNotFinishAtTime();

	/**
	 * 方法描述：剩余次数减一
	 * 创建人： jianming <br/>
	 * 创建时间：2017年2月28日下午1:42:28 <br/>
	 * @param indianaBout
	 */
	void updateLastBout(IndianaBout indianaBout);
}