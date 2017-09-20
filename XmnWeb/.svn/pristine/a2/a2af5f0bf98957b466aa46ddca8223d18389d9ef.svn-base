package com.xmniao.xmn.core.fresh.dao;

import java.util.List;
import java.util.Map;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.fresh.entity.TBillFresh;
import com.xmniao.xmn.core.fresh.entity.Texpress;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;
/**
 *@ClassName:BillFreshDao
 *@Description:生鲜订单管理dao层
 *@author hls
 *@date:2016年1月5日下午4:15:39
 */
public interface BillFreshDao extends BaseDao<TBillFresh> {
    /**
     * @Title:getFreshList
     * @Description:查询生鲜订单列表
     * @param paraMap
     * @return List<TBillFresh>
     * @throw
     */
	@DataSource("slave")
	public List<TBillFresh> getFreshList(TBillFresh tbillFresh);
	/**
	 * @Title:getFreshCount
	 * @Description:查询生鲜表总条数
	 * @return int
	 * @throw
	 */
	@DataSource("slave")
	public Long getFreshCount(TBillFresh tbillFresh);
	/**
	 * @Title:updateWstatus
	 * @Description:发货操作，更新物流状态等
	 * @param paraMap void
	 * @throw
	 */
	@DataSource("write")
	public void updateWstatus(Map<String,Object> paraMap);
	/**
	 * @Title:getBillFresh
	 * @Description:根据生鲜订单编号查询订单详情
	 * @param bid
	 * @return TBillFresh
	 * @throw
	 */
	@DataSource("slave")
	public TBillFresh getBillFresh(Long bid);
	/**
	 * @Title:getBillFresh
	 * @Description:根据生鲜订单编号查询订单详情（批量查询）
	 * @param bid
	 * @return TBillFresh
	 * @throw
	 */
	@DataSource("slave")
	List<TBillFresh> getBillFreshList(Object[] bids);
	
	
	/**
	 * @Title:getBillFreshForExport
	 * @Description:查询导出订单数据
	 * @param billFresh
	 * @return List<TBillFresh>
	 * @throw
	 */
	@DataSource("slave")
	public List<TBillFresh> getBillFreshForExport(TBillFresh billFresh);
	
	
	/**
	 * @Title:getBillFreshForExport
	 * @Description:查询导出订单数据
	 * @param billFresh
	 * @return List<TBillFresh>
	 * @throw
	 */
	@DataSource("slave")
	public List<TBillFresh> getBillFreshForExport1(TBillFresh billFresh);
	
	/**
	 * @Title:checkdata
	 * @Description:判断是否有订单数据
	 * @param billFresh
	 * @return Boolean
	 * @throw
	 */
	@DataSource("slave")
	public Integer checkdata(TBillFresh billFresh);
	/**
	 * @Title:getExpressList
	 * @Description:获取快递方式列表
	 * @return List<Texpress>
	 * @throw
	 */
	@DataSource("slave")
	List<Texpress> getExpressList();
	/**
	 * 方法描述：统计单数
	 * 创建人： jianming <br/>
	 * 创建时间：2017年2月15日上午10:52:26 <br/>
	 * @param specifiedDate 
	 * @return
	 */
	public List<Map<String, Object>> countOrderNum(String specifiedDate);
	/**
	 * 方法描述：获得某天的订单总数
	 * 创建人： jianming <br/>
	 * 创建时间：2017年2月15日下午4:35:56 <br/>
	 * @param date
	 * @return
	 */
	public Map<String, Object> getOrderByDay(String date);
}
