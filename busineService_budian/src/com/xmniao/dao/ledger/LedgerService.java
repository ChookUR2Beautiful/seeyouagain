package com.xmniao.dao.ledger;

import java.util.Map;

import com.xmniao.domain.ledger.LedgerBean;

/**
 * 分账服务接口
 * @author  HuangXiaobin
 * @version  [版本号, 2014年11月18日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface LedgerService {

	/**
	 * 获取分账结果
	 * @param bean 分账计算类
	 * @return
	 */
	public Map<String,Object> getLedgerMoney(LedgerBean bean) throws Exception;
	
	/**
	 * 分账信息业务处理
	 * @param paramMap [请求参数]
	 * @return LedgerBean [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public LedgerBean ledgerInfoProcess(Map<String, Object> paramMap);
}