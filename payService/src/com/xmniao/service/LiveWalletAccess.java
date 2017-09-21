package com.xmniao.service;

import com.xmniao.entity.LiveWallet;
import com.xmniao.thrift.ledger.FailureException;
import com.xmniao.thrift.ledger.ResponseData;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by yang.qiang on 2017/5/4.
 *
 * 用户直播钱包数据访问类
 */
public interface LiveWalletAccess {

    /**
     * 获取用户直播钱包
     * 直播钱包为空, 钱包状态异常会返回错误
     * @param uid
     */
    LiveWallet getLiveWallet(Integer uid);

    /**
     * V客兑换SAAS套餐: 扣除鸟币, 修改鸟币限额
     * @param liveWallet
     * @param deductZbalance
     * @param remarks
     */
    void exchangeSaas(LiveWallet liveWallet, BigDecimal deductZbalance, String remarks);
    
    /**
     * 
     * 方法描述：主播得礼物分账&V客礼物分账
     * 创建人：jianming  
     * 创建时间：2017年5月22日 下午3:44:10   
     * @param paramList
     * @return
     */
	Boolean liveGiftLedger(Map<String, String> paramList);
	
	/**
	 * 
	 * 方法描述：是否锁定鸟币
	 * 创建人：jianming  
	 * 创建时间：2017年8月14日 下午2:05:13   
	 * @param id
	 * @return
	 */
	Boolean hasZbalanceLock(Integer id);

	/**
	 * 
	 * 方法描述：修改钱包锁
	 * 创建人：jianming  
	 * 创建时间：2017年8月15日 下午8:05:57   
	 * @param updateOption
	 * @param lockOption
	 * @param walletId
	 * @param type
	 * @return
	 */
	int lockWallet(Integer updateOption, Integer lockOption, Integer walletId, Integer type);

	ResponseData liveWalletOption(Map<String, String> paraMap) throws FailureException;

	boolean verifySign(Map<String, Object> liveWallet);
}