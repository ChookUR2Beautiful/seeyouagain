package com.xmniao.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.thrift.TException;

import com.xmniao.entity.Wallet;
import com.xmniao.entity.WalletExpansion;
import com.xmniao.entity.WalletExpansionRecord;
import com.xmniao.entity.WalletRecord;
import com.xmniao.exception.ParamBlankException;
import com.xmniao.thrift.ledger.FailureException;

/**
 * 钱包Service
 * @author ChenBo
 *
 */
public interface WalletService {

	/**
	 * 更新钱包金额及积分信息，金额或积分不足扣时，允许扣为负数
	 * @param walletMap
	 * @return
	 */
	public Map<String,String> updateWalletAmount(Map<String,Object> walletMap);
	
	/**
	 * 更新钱包金额及积分信息,金额或积分不足扣时，返回失败
	 * @param walletMap
	 * @return
	 */
	public Map<String,String> updateWalletAmount2(Map<String,Object> walletMap);
	
	/**
	 * 向钱包充钱或退款
	 * walletMap可传两组查询条件：
	 * 1.通过accountid查询
	 * 2.通过uid/sellerid/jointid查询
	 * 两者都传的话，以accountid为准
	 * @param walletMap 
	 * @return
	 */
	//public Map<String,String> addWalletAmount(Map<String,Object> walletMap);

	/**
	 * 从钱包中支出或扣款
	 * walletMap可传两组查询条件：
	 * 1.通过accountid查询
	 * 2.通过uid/sellerid/jointid查询
	 * 两者都传的话，以accountid为准
	 * @param walletMap
	 * @return
	 */
	//public Map<String,String> subtractWalletAmount(Map<String,Object> walletMap);
	
	/**
	 * 更新钱包积分
	 * @param walletMap
	 * @return
	 */
	//public Map<String,String> updateWalletIntegral(Map<String,Object> walletMap);
	
	/**
	 * 增加钱包积分
	 * @param walletMap
	 * @return
	 */
	//public Map<String,String> addWalletIntegral(Map<String,Object> walletMap);
	
	
	/**
	 * 减少钱包积分
	 * @param walletMap
	 * @return
	 */
	//public Map<String,String> subtractWalletIntegral(Map<String,Object> walletMap);
	
	/**
	 * 批量修改钱包余额
	 * 
	 * @param paramMapList
	 * @return
	 * @throws FailureException
	 * @throws TException
	 */
	public Map<String, String> updateBalance(
			List<Map<String, String>> paramMapList) throws FailureException,
			TException;
	
	/**
	 * 钱包余额合并
	 * 
	 * @param map
	 * @return
	 * @throws FailureException
	 */
	public Map<String, String> mergeMoney(Map<String, String> map)
			throws FailureException;
	
	/**
	 * 平台扣款
	 * @param map
	 * @param sequenceArray 扣款字段顺序数据
	 * @return
	 * @throws FailureException
	 */
	public Map<String,String> walletDeductions(Map<String,Object> walletMap,String[] sequenceArray)
			throws FailureException;
	
	/**
	 * 锁定/解锁钱包
	 * @param walletMap
	 * @return
	 */
	public Map<String,String> setLockWallet(Map<String,Object> walletMap);
	
	/**
	 * 删除钱包修改记录数据
	 * @param delMap
	 * @return
	 */
	public int delWalletRecord(Map<String,Object> delMap);
	
	/**
	 * 获取钱包修改记录金额
	 */
	public WalletRecord getWalletRecord(WalletRecord wr);

	/**
	 * 方法描述：更新钱包金额
	 * 创建人： chenJie
	 * 创建时间：2016年9月5日下午3:20:17
	 * @param paramMap
	 * @return
	 * @throws FailureException
	 * @throws TException
	 */
	Map<String, String> updateBalance2(Map<String, String> paramMap)
			throws FailureException, TException;
	
	/**
	 * 添加钱包
	 * @Title: checkandaddwallet 
	 * @Description:
	 */
	public int checkandaddwallet(String uId, String userType, String password,
			String sellerName);
	
	/**
	 * 
	 * 方法描述：获得扩展钱包
	 * 创建人：jianming  
	 * 创建时间：2017年4月7日 上午11:23:46   
	 * @param param
	 * @return
	 */
	public WalletExpansion getWalletExpansion(Map<String, String> param) throws ParamBlankException;
	
	/**
	 * 
	 * 方法描述：生成扩展钱包
	 * 创建人：jianming  
	 * 创建时间：2017年4月7日 下午1:50:10   
	 * @param param
	 * @return
	 * @throws ParamBlankException 
	 */
	public WalletExpansion addWalletExpansion(Map<String, String> param) throws ParamBlankException;
	
	/**
	 * 
	 * 方法描述：查询钱包列表
	 * 创建人：jianming  
	 * 创建时间：2017年4月8日 上午11:13:34   
	 * @param param
	 * @return
	 */
	public List<WalletExpansion> getWalletExpansionList(Map<String, String> param) throws ParamBlankException;
	
	/**
	 * 
	 * 方法描述：查询钱包操作记录
	 * 创建人：jianming  
	 * 创建时间：2017年4月8日 下午2:34:06   
	 * @param param
	 * @return
	 */
	public List<WalletExpansionRecord> getWalletExpansionRecordList(Map<String, String> param) throws ParamBlankException;
	
	/**
	 * 
	 * 方法描述：检查钱包前检查参数方法
	 * 创建人：jianming  
	 * 创建时间：2017年4月24日 下午2:41:50   
	 * @param param
	 * @return
	 * @throws ParamBlankException
	 */
	Boolean checkWalletSelectParam(Map<String, String> param) throws ParamBlankException;
	
	/**
	 * 
	 * 方法描述：查询是否锁定zbalance或commision
	 * 创建人：jianming  
	 * 创建时间：2017年8月14日 上午11:52:32   
	 * @param accountid
	 * @return
	 */
	public Boolean hasZbalanceLock(Integer accountid);

	/**
	 * 
	 * 方法描述：
	 * 创建人：jianming  
	 * 创建时间：2017年8月15日 下午3:04:31   
	 * @param updateOption 操作类型 0 加锁  1 解锁
	 * @param lockOption 锁定类型
	 * @param accountid 钱包Id
	 * @return
	 */
	public int lockWallet(Integer updateOption, Integer lockOption, Integer accountid,Integer type);

	public Wallet getWalletLockByUidType(Object object, Object object2);
	
}
