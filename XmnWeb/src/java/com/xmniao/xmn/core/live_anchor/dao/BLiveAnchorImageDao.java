package com.xmniao.xmn.core.live_anchor.dao;

import java.util.List;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.live_anchor.entity.BLiveAnchorImage;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

/**
 * 
 * 项目名称：XmnWeb_LVB
 * 
 * 类名称：BLiveAnchorImageDao
 *
 * 类描述：主播相册Dao
 * 
 * 创建人： huang'tao
 * 
 * 创建时间：2016-8-18下午4:57:15
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public interface BLiveAnchorImageDao extends BaseDao<BLiveAnchorImage> {
	
	/**
	 * 
	 * 方法描述：删除主播相册
	 * 创建人： huang'tao
	 * 创建时间：2016-8-18下午4:43:21
	 * @param id
	 * @return
	 */
	@DataSource(value = "burs")
    int deleteById(Integer id);
	
	/**
	 * 
	 * 方法描述：根据图片类型删除相册 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-10-13下午2:42:07 <br/>
	 * @param imageType
	 * @return
	 */
	@DataSource(value = "burs")
	int deleteImages(Integer imageType);
	
	/**
	 * 
	 * 方法描述：根据条件删除相册 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-4-12下午4:53:58 <br/>
	 * @param record
	 * @return
	 */
	@DataSource(value = "burs")
	int deleteImagesByBean(BLiveAnchorImage record);


	/**
	 * 
	 * 方法描述：添加主播相册
	 * 创建人： huang'tao
	 * 创建时间：2016-8-18下午4:43:38
	 * @param record
	 * @return
	 */
    @DataSource(value = "burs")
    void add(BLiveAnchorImage record);
    
    /**
	 * 
	 * 方法描述：批量添加相册
	 * 创建人： huang'tao
	 * 创建时间：2016-8-18下午4:43:38
	 * @param record
	 * @return
	 */
    @DataSource(value = "burs")
    Integer addBatch(List<BLiveAnchorImage> imageList);

    /**
     * 
     * 方法描述：根据ID获取主播相册
     * 创建人： huang'tao
     * 创建时间：2016-8-18下午4:44:16
     * @param id
     * @return
     */
    @DataSource(value = "burs")
    BLiveAnchorImage selectById(Integer id);

    /**
     * 
     * 方法描述：更新主播相册
     * 创建人： huang'tao
     * 创建时间：2016-8-18下午4:37:54
     * @param record
     * @return
     */
    @DataSource(value = "burs")
    Integer update(BLiveAnchorImage record);
    
    /**
     * 
     * 方法描述：获取主播相册
     * 创建人： huang'tao
     * 创建时间：2016-8-18下午4:37:54
     * @param record
     * @return
     */
    @DataSource(value = "burs")
    List<BLiveAnchorImage> getList(BLiveAnchorImage record);
    
    /**
     * 
     * 方法描述：获取相册数量
     * 创建人： huang'tao
     * 创建时间：2016-8-18下午4:37:54
     * @param record
     * @return
     */
    @DataSource(value = "burs")
    Long count(BLiveAnchorImage record);

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年7月20日上午11:27:22 <br/>
	 * @param asList
	 * @return
	 */
    @DataSource(value = "burs")
	List<BLiveAnchorImage> getByImageIds(List<String> list);
}