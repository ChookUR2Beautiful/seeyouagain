package com.xmniao.xmn.core.businessman.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.businessman.entity.ExperienceComment;

public interface ExperienceCommentDao extends BaseDao<ExperienceComment>{
    int deleteByPrimaryKey(Integer id);

    int insert(ExperienceComment record);

    int insertSelective(ExperienceComment record);

    ExperienceComment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ExperienceComment record);

    int updateByPrimaryKeyWithBLOBs(ExperienceComment record);

    int updateByPrimaryKey(ExperienceComment record);

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年5月16日下午2:09:08 <br/>
	 * @param id
	 * @param isRecommend
	 * @return
	 */
	int updateIsRecommend(@Param("id")Integer id,@Param("isRecommend") Integer isRecommend);

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年5月16日下午2:10:51 <br/>
	 * @param id
	 * @param reviewState
	 * @param content 
	 * @return
	 */
	int updateReviewState(@Param("id")Integer id, @Param("reviewState")Integer reviewState,@Param("refuseRemark") String refuseRemark);

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年5月18日上午11:13:00 <br/>
	 * @param experienceComment
	 * @return
	 */
	List<ExperienceComment> getCommentChoose(ExperienceComment experienceComment);
}