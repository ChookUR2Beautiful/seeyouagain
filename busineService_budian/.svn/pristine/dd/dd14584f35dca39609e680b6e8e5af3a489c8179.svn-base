package com.xmniao.dao.store;

import java.util.Map;

/**
 * 连锁店服务DAO层
 * @author  LiBingBing
 * @version  [版本号, 2015年5月6日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface StoreServiceDao
{
    /**
     * 连锁店提现回调更新
     * @param paramMap [请求参数]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public void modifyStoreWithdrawals(Map<String,String> paramMap);

    /**
     * 根据提现编号查询资金并拢信息
     * @param flowid [提现编号]
     * @return Map<String,Object> [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public Map<String,Object> queryStoreWithdrawalInfo(String flowid);
}
