package com.xmniao.xmn.core.live_anchor.dao;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.live_anchor.entity.TExperienceofficerActivity;
import com.xmniao.xmn.core.live_anchor.entity.TExperienceofficerEnrollRecord;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

public interface TExperienceofficerActivityDao extends BaseDao<TExperienceofficerActivity>{

	/*获取报名列表*/
	List<TExperienceofficerEnrollRecord> getEnrollRecordList(TExperienceofficerEnrollRecord enrollRecord);
	
	/* 更新报名记录 */
	int updateEnrollRecord(Map<String,Object> uRecord);

	/*更新场次剩余名额*/
	int updateRemainderNum(TExperienceofficerActivity activity);
	
	/**
	 * 
	 * 方法描述：查询分页
	 * 创建人： ChenBo <br/>
	 * 创建时间：2017年5月22日下午3:54:05 <br/>
	 * @param map
	 * @return
	 */
	List<TExperienceofficerActivity> getListByMap(Map<String,Object> map);
	
	/**
	 * 
	 * 方法描述：统计
	 * 创建人： ChenBo <br/>
	 * 创建时间：2017年5月22日下午3:54:13 <br/>
	 * @param map
	 * @return
	 */
	Long countByMap(Map<String,Object> map);
}