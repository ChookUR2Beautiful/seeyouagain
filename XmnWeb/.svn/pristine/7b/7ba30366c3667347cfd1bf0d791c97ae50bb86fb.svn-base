package com.xmniao.xmn.core.live_anchor.dao;

import java.util.List;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.live_anchor.entity.TLiveCoupon;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;


/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TLiveCouponDao
 * 
 * 类描述： 直播粉丝券实体类
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-10-26 下午3:14:24 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public interface TLiveCouponDao extends BaseDao<TLiveCoupon>{
	
	/**
	 * 
	 * 方法描述：删除直播粉丝券 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-10-26下午5:20:26 <br/>
	 * @param cid
	 * @return
	 */
	@DataSource(value="master")
    int deleteByPrimaryKey(Integer cid);

	/**
	 * 
	 * 方法描述：新增直播粉丝券 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-10-26下午5:20:30 <br/>
	 * @param record
	 * @return
	 */
	@DataSource(value="master")
    int insertSelective(TLiveCoupon record);

	/**
	 * 
	 * 方法描述：查询直播粉丝券 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-10-26下午5:20:34 <br/>
	 * @param cid
	 * @return
	 */
	@DataSource(value="slave")
    TLiveCoupon selectByPrimaryKey(Integer cid);

	/**
	 * 
	 * 方法描述：更新直播粉丝券 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-10-26下午5:20:41 <br/>
	 * @param record
	 * @return
	 */
	@DataSource(value="master")
    int updateByPrimaryKey(TLiveCoupon record);
	
	/**
	 * 
	 * 方法描述：获取直播粉丝券列表 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-10-26下午5:20:41 <br/>
	 * @param record
	 * @return
	 */
	@DataSource(value="slave")
	List<TLiveCoupon> getList(TLiveCoupon record);
	
	
	/**
	 * 
	 * 方法描述：获取直播粉丝券列表(只包括主表字段) <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-10-26下午5:20:41 <br/>
	 * @param record
	 * @return
	 */
	@DataSource(value="slave")
	List<TLiveCoupon> getLiveCoupon(TLiveCoupon record);
	
	/**
	 * 
	 * 方法描述：批量添加抵用券,并设置Bean主键 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-10-26下午5:20:41 <br/>
	 * @param record
	 * @return
	 */
	@DataSource(value="master")
	int addBatchReturnId(List<TLiveCoupon> couponList);
	
	@DataSource(value="slave")
	List<TLiveCoupon> getLiveCouponByCids(Object[] objects);
	
	@DataSource(value="slave")
	int deleteLiveCouponByCids(Object[] objects);

}