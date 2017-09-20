package com.xmniao.xmn.core.user_terminal.dao;

import java.util.List;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.user_terminal.entity.TPost;
import com.xmniao.xmn.core.user_terminal.entity.TPostPic;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

/**
 * 
 * @项目名称：XmnWeb
 * 
 * @类名称：TPostPicDao
 * 
 * @类描述：帖子图片表
 * 
 * @创建人：zhou'dekun
 * 
 * @创建时间：2014年12月10日20时07分10秒
 * 
 * @Copyright:广东寻蜜鸟网络技术有限公司
 */
public interface TPostPicDao extends BaseDao<TPostPic> {
	@DataSource("slave")
	public List<TPostPic> getListpic(Long tid);

}
