package com.xmniao.xmn.core.jobmanage.dao;

import java.util.List;
import java.util.Map;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.jobmanage.entity.RecruitTag;
import com.xmniao.xmn.core.jobmanage.entity.UserCV;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;
/**
 *@ClassName:UserCVDao
 *@Description:用户简历管理dao层
 *@author hls
 *@date:2016年5月31日下午2:40:33
 */
public interface UserCVDao extends BaseDao<UserCV> {
	/**
	 * @Title:selectUserCVInfoList
	 * @Description:查询用户简历列表
	 * @param userCV
	 * @return List<UserCV>
	 * @throw
	 */
	@DataSource("burs")
	public List<UserCV> selectUserCVInfoList(UserCV userCV);
	
	
	/**
	 * @Title:userCVInfoCount
	 * @Description:查询用户简历列表总条数
	 * @param userCV
	 * @return Long
	 * @throw
	 */
	@DataSource("burs")
	public Long userCVInfoCount(UserCV userCV);
	/**
	 * @Title:exportUserCVList
	 * @Description:导出用户简历列表
	 * @param userCV
	 * @return List<BXmer>
	 * @throw
	 */
	@DataSource("burs")
	public List<UserCV> exportUserCVList(UserCV userCV);
	/**
	 * @Title:deleteBannerById
	 * @Description:根据id批量删除用户简历（逻辑删除）
	 * @param objects
	 * @return int
	 * @throw
	 */
	@DataSource("burs")
	public int deleteUserCVById (Object[] objects);
	/**
	 * @Title:selectTanameByID
	 * @Description:根据用户简历id批量查询对应的标签名
	 * @param objects
	 * @return List<RecruitTag>
	 * @throw
	 */
	@DataSource("burs")
	public List<RecruitTag> selectTanameByID (Map<String,Object> map);
}
