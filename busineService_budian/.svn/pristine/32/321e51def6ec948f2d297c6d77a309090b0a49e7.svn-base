package com.xmniao.dao.refund;

import java.util.List;
import java.util.Map;

import com.xmniao.domain.order.OrdRecordBean;

/**
 * 订单退款服务接口DAO层
 * @author  LiBingBing
 * @version  [版本号, 2015年8月5日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface RefundOrderServiceDao
{
    /**
     * 获取订单信息
     * @param bid [订单号]
     * @return Map<String,Object> [返回订单信息]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public Map<String,Object> queryOrderInfo(String bid);
    
    
    /**
     * 获取订单信息2
     */
    public Map<String,Object> queryOrderInfoForRefund(String bid);
    
    
    
    /**
     * 更新订单状态信息
     * @param reqMap [参数说明]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public void modifyOrderInfo(Map<String,String> reqMap);
    
    /**
     * 插入订单记录信息
     * @param reqBean [订单记录JAVABean]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public void insertBillRecord(OrdRecordBean reqBean);
    
    /**
     * 插入订单退款记录信息
     * @param reqMap [参数说明]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public void insertOrderRefundRecord(Map<String,String> reqMap);
    
    /**
     * 删除订单退款记录
     */
    public boolean deleteOrderRefundRecord(Map<String,String> reqMap);
    
    /**
     * 
    * @Title: queryOrderInfoByBid
    * @Description: 根据订单编号查询订单的需要的统计信息来统计商户日收益，合作商日收益 
    * @return Map<String,Object>    返回类型
    * @throws
     */
    public Map<String,Object> queryOrderInfoByBid(String bid);
    
    /**
     * 
    * @Title: querySellerCensusBySellerid
    * @Description: 依商户编号及时间查询是否存在商户统计记录
    * @return int    返回类型
    * @throws
     */
    public int querySellerCensusBySellerid(Map<String,String> reqMap);
    
    /**
     * 
    * @Title: modifySellerCensusBySellerid
    * @Description: 依商户编号及时间修改商户日统计数据
    * @return int    返回类型
    * @throws
     */
    public int modifySellerCensusBySellerid(Map<String,Object> reqMap);
    
    /**
     * 
    * @Title: addSellerDayCensus
    * @Description: 插入一条商户日统计数据
    * @return int    返回类型
    * @throws
     */
    public int addSellerDayCensus(Map<String,Object> reqMap);
    
    /**
     * 查询合作商日收益总额
     */
    public Map<String,Object> queryJointDayCensus(Map<String,Object> map);
    
    /**
     * 修改合作商日收益总额
     */
    public boolean updateJointDayCensus(Map<String,Object> map);
    
    /**
     * 新增合作商日收益记录
     */
    public boolean insertJointDayCensus(Map<String,Object> map);
    
    /**
     * 查询优惠券与订单关系
     * @param bid [订单号]
     * @return List<Map<String,Object>> [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public List<Map<String, Object>> getOrderCouponList(String bid);
    
    /**
     * 根据优惠券ID修改用户使用状态为已使用
     * @param serial [优惠券序列码]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public void mdyCouponUserStatus(String cdid);
}
