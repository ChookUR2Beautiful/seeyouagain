package com.xmniao.xmn.core.vod.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.util.dataSource.DataSource;


@Repository
public interface LiveVodDao {
	
	/**
	 * 回放绑定直播记录
	 * @Description: TODO
	 * @author xiaoxiong
	 * @date 2016年8月30日
	 * @version
	 */
	@DataSource("joint")
	void insertLiveVideo(Map<String, Object> param);
	
	/**
	 * 查询主播直播视频
	 * @Description: TODO
	 * @author xiaoxiong
	 * @date 2016年8月30日
	 * @version
	 */
	@DataSource("joint")
	List<Map<String,Object>> queryAnchorVedio(Map<String, Object> params);

}
