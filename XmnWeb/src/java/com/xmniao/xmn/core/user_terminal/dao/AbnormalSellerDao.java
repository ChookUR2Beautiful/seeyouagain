/**
 * 
 */
package com.xmniao.xmn.core.user_terminal.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.user_terminal.entity.AbnormalSeller;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

/**
 * 项目名称：XmnWeb
 * 
 * 类名称：AbnormalSellerDao
 *
 * 创建人： chenjie
 * 
 * 创建时间：2016年8月5日下午2:27:45
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public interface AbnormalSellerDao extends BaseDao<AbnormalSeller>{
	
	/**
	 * 
	 * 方法描述：查询待处理举报商家列表
	 * 创建人： 陈杰
	 * 创建时间：2016年8月5日下午2:39:52
	 * @param abnormalSeller
	 * @return
	 */
	@DataSource("slave")
	public List<AbnormalSeller> getAbnormalSellerList(AbnormalSeller abnormalSeller);
	
	
	
	/**
	 * 
	 * 方法描述：查询记录总数
	 * 创建人： chenjie
	 * 创建时间：2016年8月5日下午3:10:57
	 * @param abnormalSeller
	 * @return
	 */
	@DataSource("slave")
	public Long getAbnormalSellerCount(AbnormalSeller abnormalSeller);
	
	
	/**
	 * 
	 * 方法描述：根据id删除举报商家记录
	 * 创建人： chenjie
	 * 创建时间：2016年8月6日上午9:33:34
	 * @param id
	 * @return
	 */
	@DataSource("slave")
	public Integer deleteInfoById(String id);
	
	/**
	 * 
	 * 方法描述：更新举报商家状态
	 * 创建人： chenjie
	 * 创建时间：2016年8月6日上午9:42:22
	 * @param id
	 * @param status
	 * @return
	 */
	@DataSource("slave")
	public Integer dealInfo(AbnormalSeller abnormalSeller);
	
}
