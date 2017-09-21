package com.xmniao.dao.mike;

import java.util.Map;

/**
 * 向蜜客服务接口类DAO层
 * @author  LiBingBing
 * @version  [版本号, 2014年11月25日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface MikeServiceDao
{
    /**
     * 向蜜客缴费更新订单状态接口
     * @param paramMap [参数说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public int modifyMikeInviteInfo(Map<String, String> paramMap);
}
