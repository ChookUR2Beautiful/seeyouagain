package com.xmniao.dao.profit;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.xmniao.domain.profit.JointProfitCensusd;


/**
 * 合作商日收益统计
 * @author wench
 *
 */
public interface JointProfitCensusdMapper2 
{
    /**
     * 查询当天统计的合作商日收益订单总条数
     * @return int [返回记录数]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public int queryProfitCensus();
    
    /**
     * 查询合作商和消费合作商
     * @param reqMap [到账时间和支付时间MAP]
     * @return List<Map<String,Integer>> [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public List<Map<String,Integer>> queryJointAndConsumJoint(Map<String,String> reqMap);
    
    /**
     * 查询商户和合作商信息
     * @param jointid [合作商ID]
     * @return List<JointProfitCensusd> [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public List<JointProfitCensusd> querySellerAndJointInfo(Map<String,Object> map);
    
    
    /**
     * 方法2
     */
    public List<JointProfitCensusd> querySellerAndJointInfo2(Map<String,Object> map);
    
    
    /**
     * 商户佣金总额
     * @param reqMap [请求参数MAP]
     * @return List<String> [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public List<String> querySellerCommision(Map<String,String> reqMap);
    
    
    /**
     * 记录是否存在
     */
    public int queryOneJointProfitCensusdBySelleridAndJointIdAndDate(JointProfitCensusd reqBean);
    
    /**
     * 存在就更新
     */
    public boolean updateOneJointProfitCensusdBySelleridAndJointIdAndDate(JointProfitCensusd reqBean);
    
    
    
    /**
     * 插入合作商收益表
     * @param reqBean [请求参数]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public void insertJointProfitCensusd(JointProfitCensusd reqBean);
    
    /**
     * 查询合作商收益全都为0的记录
     * @param reqBean
     * @return int [返回记录数]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public int queryJointProfitZero(JointProfitCensusd reqBean);
    
    /**
     * 删掉合作商收益全都为0的记录
     * @param reqBean [参数说明]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public void deleteJointProfitZero(JointProfitCensusd reqBean);
    
    
    /****
     * 分账金额总信息
     */
    public List<Map<String,Object>> queryCommissionAndMoney(Map<String,String> map);
    
    
    /**
     * 已到账收益总额
     * @param reqMap [请求参数MAP]
     * @return List<String> [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public List<Map<String,String>> queryJointHasLedgerProfit(Map<String,String> reqMap);
    
    /**
     * 商家名称
     */
    public String getSellernameBySellerid(String sellerid);
    /**
     * 合作商名称
     */
    public String getCorporateByjointid(String jointid);
}
