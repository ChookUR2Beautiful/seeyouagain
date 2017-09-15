package com.xmniao.xmn.core.catehome.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.catehome.entity.SpecilTopicPic;
import com.xmniao.xmn.core.util.dataSource.DataSource;

@Repository
public interface SpecilTopicPicDao {
	
	@DataSource("joint")
    int deleteByPrimaryKey(Integer id);

	@DataSource("joint")
    int insert(SpecilTopicPic record);

	@DataSource("joint")
    int insertSelective(SpecilTopicPic record);

	@DataSource("joint")
    SpecilTopicPic selectByPrimaryKey(Integer id);

	@DataSource("joint")
    int updateByPrimaryKeySelective(SpecilTopicPic record);

	@DataSource("joint")
    int updateByPrimaryKey(SpecilTopicPic record);
	
	/**
	 * 通过专题活动查询专题活动图片列表
	 * @param fid
	 * @return
	 */
	@DataSource("joint")
	List<SpecilTopicPic> findAllByFid(@Param("fid")Integer fid);
	
	/**
	 * 首页好吃推荐--专题图片
	 * 分页查询
	 * @return
	 */
	@DataSource("joint")
	List<SpecilTopicPic> findRecommendActivityPic(@Param("cityId")Integer cityId,@Param("page")Integer page,@Param("pageSize")Integer pageSize);
	
	/**
	 * 查询全国的
	 * @param cityId
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@DataSource("joint")
	List<SpecilTopicPic> findRecommendActivityPicByUncity(@Param("page")Integer page,@Param("pageSize")Integer pageSize);

	/**
	 * 好吃推荐
	 * @param param
	 * @return
	 */
	@DataSource("joint")
	List<Map<Object, Object>> findRecommendActivityPicByCondition(Map<Object, Object> param);

	/**查询首页网红推荐（专题精选）
	 * @param cityId
	 * @param i
	 * @param maxValue
	 * @return
	 */
	@DataSource("joint")
	List<SpecilTopicPic> findTopicAndNumSellerPackage(@Param("cityId")Integer cityId,@Param("page")Integer page,@Param("pageSize")Integer pageSize);

}