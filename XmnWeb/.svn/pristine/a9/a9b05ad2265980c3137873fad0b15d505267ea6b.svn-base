package com.xmniao.xmn.core.fresh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.fresh.entity.IndianaDduonum;

public interface IndianaDduonumDao extends BaseDao<IndianaDduonum>{
    int deleteByPrimaryKey(Long id);

    int insert(IndianaDduonum record);

    int insertSelective(IndianaDduonum record);

    IndianaDduonum selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(IndianaDduonum record);

    int updateByPrimaryKey(IndianaDduonum record);

	/**
	 * 方法描述：根据期数id查询并且是机器人参与记录
	 * 创建人： jianming <br/>
	 * 创建时间：2017年2月27日上午11:18:11 <br/>
	 * @param id
	 * @return
	 */
	List<IndianaDduonum> getByBoutIsNotReal(Integer id);

	/**
	 * 方法描述：统计机器人买的数量
	 * 创建人： jianming <br/>
	 * 创建时间：2017年2月27日上午11:27:00 <br/>
	 * @param id
	 * @return
	 */
	Long countRobot(Integer id);

	/**
	 * 方法描述：
	 * 创建人： jianming <br/>
	 * 创建时间：2017年2月27日上午11:31:31 <br/>
	 * @param id
	 * @param nextInt
	 * @return
	 */
	IndianaDduonum getWinnerRobot(@Param("boutId")Integer id, @Param("nextInt")int nextInt);

	/**
	 * 方法描述：统计购买次数
	 * 创建人： jianming <br/>
	 * 创建时间：2017年2月27日下午2:45:56 <br/>
	 * @param boutId
	 * @param robotId
	 * @param uid 
	 * @return
	 */
	Long countWinnerVeces(@Param("boutId")Integer boutId, @Param("robotId")Integer robotId, @Param("uid")Integer uid);

	/**
	 * 方法描述：查询预设中奖号码
	 * 创建人： jianming <br/>
	 * 创建时间：2017年2月27日下午3:03:29 <br/>
	 * @param id
	 * @return
	 */
	IndianaDduonum getHasWinnerNum(Integer id);

	/**
	 * 方法描述：统计用户买的数量
	 * 创建人： jianming <br/>
	 * 创建时间：2017年2月27日下午4:15:34 <br/>
	 * @param id
	 * @return
	 */
	Long countUid(Integer id);

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年2月27日下午4:20:53 <br/>
	 * @param id
	 * @param nextInt
	 * @return
	 */
	IndianaDduonum getWinnerUid(@Param("boutId")Integer id,@Param("nextInt") int nextInt);

	/**
	 * 方法描述：预设中奖
	 * 创建人： jianming <br/>
	 * 创建时间：2017年2月27日下午6:39:23 <br/>
	 * @param dduonum
	 */
	void setWinner(IndianaDduonum dduonum);

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年2月27日下午7:33:10 <br/>
	 * @param dduonum
	 */
	void cancelWinner(IndianaDduonum dduonum);

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年3月7日下午3:19:05 <br/>
	 * @return
	 */
	List<IndianaDduonum> getLastFifty();
}