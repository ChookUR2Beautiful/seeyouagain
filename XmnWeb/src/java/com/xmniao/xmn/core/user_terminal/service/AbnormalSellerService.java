/**
 * 
 */
package com.xmniao.xmn.core.user_terminal.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aliyun.common.utils.DateUtil;
import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.user_terminal.dao.AbnormalSellerDao;
import com.xmniao.xmn.core.user_terminal.entity.AbnormalSeller;

/**
 * 项目名称：XmnWeb
 * 
 * 类名称：AbnormalSellerService
 *
 * 创建人： chenjie
 * 
 * 创建时间：2016年8月5日下午2:25:13
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */

@Service
public class AbnormalSellerService extends BaseService<AbnormalSeller>{
	
	@Autowired
	private AbnormalSellerDao abnormalSellerDao;
	
	@Override
	protected BaseDao getBaseDao() {
		return abnormalSellerDao;
	}
	
	/**
	 * 
	 * 方法描述：查询待处理举报商家列表
	 * 创建人： huang'tao
	 * 创建时间：2016年8月5日下午3:12:12
	 * @param abnormalSeller
	 * @return
	 */
	public List<AbnormalSeller> getAbnormalSellerList(AbnormalSeller abnormalSeller){
		return abnormalSellerDao.getAbnormalSellerList(abnormalSeller);
	}
	
	/**
	 * 
	 * 方法描述：查询符合条件的记录总数
	 * 创建人： huang'tao
	 * 创建时间：2016年8月5日下午3:13:30
	 * @param abnormalSeller
	 * @return
	 */
	public long getCount(AbnormalSeller abnormalSeller){
		return abnormalSellerDao.getAbnormalSellerCount(abnormalSeller);
	}
	
	/**
	 * 
	 * 方法描述：根据id删除举报商家
	 * 创建人：chenjie
	 * 创建时间：2016年8月6日上午9:36:25
	 * @param id
	 */
	public void deleteInfoById(String id) throws Exception{
		abnormalSellerDao.deleteInfoById(id);
	}
	
	
	/**
	 * 
	 * 方法描述：更新举报商家状态
	 * 创建人： chenjie
	 * 创建时间：2016年8月6日上午9:43:50
	 * @param id
	 * @param status
	 */
	public Resultable dealInfo(AbnormalSeller abnormalSeller){
		Resultable resultable = new Resultable();
		
		try {
			abnormalSeller.setEdate(new Date());
			abnormalSellerDao.dealInfo(abnormalSeller);
			resultable.setSuccess(true);
			resultable.setMsg("操作成功");
			return resultable;
		} catch (Exception e) {
			log.error(e);
			resultable.setSuccess(true);
			resultable.setMsg("操作失败");
			return resultable;
		}
	}
}
