package com.xmniao.xmn.core.live.service;

import java.util.List;
import java.util.Map;

import com.xmniao.xmn.core.common.Page;

public interface LiveTrailerService {

	/**
	 * 预告直播列表
	 * @param liveTrailerRequest
	 * @return
	 */
	public List<Map<Object,Object>> queryLiveTrailer(Page page); 
}
