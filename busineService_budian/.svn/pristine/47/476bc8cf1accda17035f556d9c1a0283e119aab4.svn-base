package com.xmniao.dao.order;

import java.util.List;
import java.util.Map;

import com.xmniao.domain.activity.RewardActivityBean;

/**
 * 活动服务模块DAO层
 * @author  LiBingBing
 * @version  [版本号, 2015年1月16日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface ActivityServiceDao
{
    /**
     * 根据订单号获取手机订单的所有信息
     * @param bid [订单号]
     * @return Map<String,Object> [返回参数说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public Map<String, Object> queryMobileBillAll(String bid);
    
    /**
     * 活动订单更新接口
     * @param paraMap [请求参数]
     * @return Map<String,String> [返回参数说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public int mdyMobileActivitiesInfos(Map<String, String> paraMap);
    
    /**
     * 查询打赏活动业务
     * @return List<RewardActivityBean> [返回打赏活动实体类LIST集合]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public List<RewardActivityBean> queryRewardActtyInfos();
    
    /**
     * 批量更新打赏活动记录状态
     * @param paraList [未打赏记录的LIST集合]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public void modifyRewardActtyInfos(RewardActivityBean reqBean);
}
