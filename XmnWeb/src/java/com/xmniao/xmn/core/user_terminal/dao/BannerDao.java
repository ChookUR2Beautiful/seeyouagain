package com.xmniao.xmn.core.user_terminal.dao;

import java.util.List;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.user_terminal.entity.TBanner;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;
/**
 *@ClassName:BannerDao
 *@Description:导航图管理dao层
 *@author hls
 *@date:2016年5月11日上午10:35:32
 */
public interface BannerDao extends BaseDao<TBanner> {
    /**
     * @Title:getBannerList
     * @Description:查询导航图列表
     * @param banner
     * @return List<TBanner>
     * @throw
     */
	@DataSource("slave")
	public List<TBanner> getBannerList(TBanner banner);
	/**
	 * @Title:getBannerCount
	 * @Description:查询导航图列表总条数
	 * @param banner
	 * @return Long
	 * @throw
	 */
	@DataSource("slave")
	public Long getBannerCount(TBanner banner);
	/**
	 * @Title:updateBanner
	 * @Description:修改导航图
	 * @param banner void
	 * @throw
	 */
	@DataSource("write")
	public void updateBanner(TBanner banner);
	/**
	 * @Title:getBanner
	 * @Description:根据导航图编号查询导航图详情
	 * @param id
	 * @return TBanner
	 * @throw
	 */
	@DataSource("slave")
	public TBanner getBanner(Integer id);
	
	/**
	 * @Title:deleteBannerById
	 * @Description:根据导航图id批量删除导航图信息
	 * @param objects
	 * @return int
	 * @throw
	 */
	public int deleteBannerById (Object[] objects);
}
