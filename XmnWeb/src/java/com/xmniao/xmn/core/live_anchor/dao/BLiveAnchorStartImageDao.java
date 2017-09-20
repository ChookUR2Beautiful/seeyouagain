/**
 * 
 */
package com.xmniao.xmn.core.live_anchor.dao;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.live_anchor.entity.BLiveAnchorStartImage;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;


/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：BLiveAnchorStartImageDao
 * 
 * 类描述： 开始直播图
 * 
 * 创建人： jianming
 * 
 * 创建时间：2016年9月5日 下午1:52:29 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
public interface BLiveAnchorStartImageDao extends BaseDao<BLiveAnchorStartImage>{

	/**
	 * 方法描述：图片类型数量
	 * 创建人： jianming
	 * 创建时间：2016年9月5日下午8:13:19
	 * @param pageImageType 
	 * @return
	 */
	@DataSource(value = "slave")
	public int hasPageImageType(Integer pageImageType);

	/**
	 * 方法描述：根据id查询
	 * 创建人： jianming
	 * 创建时间：2016年9月5日下午8:36:32
	 * @param startImageId
	 * @return
	 */
	@DataSource(value = "slave")
	public Object selectByPrimaryKey(Integer startImageId);

	/**
	 * 方法描述：删除
	 * 创建人：jianming
	 * 创建时间：2016年9月6日上午10:44:29
	 * @param bLiveAnchorStartImage
	 */
	@DataSource(value="master")
	public void deleteByPrimaryKey(BLiveAnchorStartImage bLiveAnchorStartImage);

	/**
	 * 方法描述：根据图片类型查询
	 * 创建人： Administrator
	 * 创建时间：2016年9月6日下午6:26:26
	 * @param imageType
	 * @return
	 */
	@DataSource(value = "slave")
	public BLiveAnchorStartImage selectByImageType(Integer imageType);

	/**
	 * 方法描述：在此处添加方法描述
	 * 创建人： Administrator
	 * 创建时间：2016年9月7日上午9:10:14
	 * @param bLiveAnchorStartImage
	 */
	@DataSource(value="master")
	public void updateStatus(BLiveAnchorStartImage bLiveAnchorStartImage);
	   

}
