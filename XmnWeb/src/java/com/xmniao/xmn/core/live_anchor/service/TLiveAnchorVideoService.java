/**
 * 
 */
package com.xmniao.xmn.core.live_anchor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.live_anchor.dao.TLiveAnchorVideoDao;
import com.xmniao.xmn.core.live_anchor.entity.TLiveAnchorVideo;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TLiveAnchorVideoService
 * 
 * 类描述： 直播精彩视频Service
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-12-8 下午8:25:41 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service
public class TLiveAnchorVideoService extends BaseService<TLiveAnchorVideo> {

	/**
	 * 注入直播精彩视频
	 */
	@Autowired
	private TLiveAnchorVideoDao liveAnchorVideoDao;

	@SuppressWarnings("rawtypes")
	@Override
	protected BaseDao getBaseDao() {
		return liveAnchorVideoDao;
	}

	/**
	 * 方法描述：批量更新视频上线状态 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-23下午2:12:37 <br/>
	 * @param ids
	 * @param status
	 * @return
	 */
	public Resultable updateBatch(TLiveAnchorVideo anchorVideo) {
		Resultable result=new Resultable();
		try {
				liveAnchorVideoDao.updateBatch(anchorVideo);
				result.setSuccess(true);
				result.setMsg("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("操作失败");
			
		}finally{
			//写入 日志记录表
			String[] data={"直播频道,视频ID",anchorVideo.getIds().toString(),"修改上线状态","修改"};
			fireLoginEvent(data,result.getSuccess()==true?1:0);
		}
		
		return result;
	}

}
