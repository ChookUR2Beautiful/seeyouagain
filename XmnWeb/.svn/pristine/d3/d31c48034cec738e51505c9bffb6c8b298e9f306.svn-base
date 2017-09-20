/**
 * 
 */
package com.xmniao.xmn.core.live_anchor.dao;

import java.util.List;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.live_anchor.entity.TLiveRecord;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TLiveRecordAddBatchDao
 * 
 * 类描述：批量添加通告Dao
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2017-3-7 下午2:20:32 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */

public interface TLiveRecordAddBatchDao extends BaseDao<TLiveRecord> {
	
	/**
	 * 
	 * 方法描述：批量更新直播通告信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-3-7下午3:18:57 <br/>
	 * @param liveRecord
	 * @return
	 */
	@DataSource(value="slave")
	Integer updateBatch(TLiveRecord liveRecord);
	
	/**
	 * 
	 * 方法描述：新增直播通告信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-3-7下午3:18:57 <br/>
	 * @param liveRecord
	 * @return 返回新增记录ID
	 */
	@DataSource(value="slave")
	int addReturnId(TLiveRecord liveRecord);

	/**
	 * 
	 * 方法描述：批量添加通告 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-3-7下午3:08:17 <br/>
	 * @param liveRecordList
	 * @return
	 */
	@DataSource(value="slave")
	Integer addBatch(List<TLiveRecord> liveRecordList);
	
	/**
	 * 
	 * 方法描述：批量删除通告 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-3-7下午3:08:17 <br/>
	 * @param liveRecordList
	 * @return
	 */
	@DataSource(value="slave")
	int deleteBatch(Object[] ids);
	
	
}
