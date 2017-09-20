package com.xmniao.xmn.core.fresh.dao;

import java.util.List;
import java.util.Map;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.fresh.entity.TBillFreshSub;
import com.xmniao.xmn.core.fresh.entity.TProductBill;

/**
 * 项目名称： XmnWeb
 * 类名称： BillFreshSubDao.java
 * 类描述：子订单管理Dao
 * 创建人： lifeng
 * 创建时间： 2016年6月24日下午2:12:31
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 * @version
 */
public interface BillFreshSubDao extends BaseDao<TBillFreshSub> {

	/**
	 * @Description:查询列表
	 * @Param:tbillFreshSub
	 * @return:List<TBillFreshSub>
	 * @author:lifeng
	 * @time:2016年6月24日下午2:12:50
	 */
	List<TBillFreshSub> getTBillFreshSubList(TBillFreshSub tbillFreshSub);

	/**
	 * @Description: 查询总记录数
	 * @Param:
	 * @return:Long
	 * @author:lifeng
	 * @time:2016年6月24日下午2:13:18
	 */
	Long tbillFreshSubCount(TBillFreshSub tbillFreshSub);

	/**
	 * @Description: 条件查询一条信息
	 * @Param:tBillFreshSub
	 * @return:TBillFreshSub
	 * @author:lifeng
	 * @time:2016年6月24日下午4:23:39
	 */
	TBillFreshSub getBillFreshSub(TBillFreshSub tBillFreshSub);
	
	/**
	 * @Description: 查询子订单商品
	 * @Param:tBillFreshSub
	 * @return:TBillFreshSub
	 * @author:lifeng
	 * @time:2016年6月24日下午4:23:39
	 */
	List<TProductBill> getBillFreshSubProd(Map<Object, Object> map);
	
	/**
	 * @Title:getBillFresh
	 * @Description:根据生鲜订单编号查询订单详情（批量查询）
	 * @param bid
	 * @return TBillFresh
	 * @throw
	 */
	List<TBillFreshSub> getBillFreshSubList(Object[] bids);

	/**
	 * @Description: 导出数据
	 * @Param:
	 * @return:List<TBillFreshSub>
	 * @author:lifeng
	 * @time:2016年6月24日下午5:23:48
	 */
	List<TBillFreshSub> getBillFreshSubForExport(TBillFreshSub tbillFreshSub);

	/**
	 * @Description: 检查时间范围内是否有数据
	 * @Param:
	 * @return:Integer
	 * @author:lifeng
	 * @time:2016年6月24日下午5:24:56
	 */
	Integer checkdata(TBillFreshSub tbillFreshSub);

	/**
	 * @Description: 更新状态为发货
	 * @Param:paramMap
	 * @return:void
	 * @author:lifeng
	 * @time:2016年6月24日下午8:14:17
	 */
	void updateSubCourier(Map<String, Object> paramMap);
	
	/**
	 * @Description:订单发货后，订单状态修改 
	 * @Param:
	 * @return:void
	 * @author:lifeng
	 * @time:2016年6月25日上午9:42:29
	 */
	void updateSubReceive(Map<String,Object> paramMap);

}
