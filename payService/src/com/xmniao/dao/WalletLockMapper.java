package com.xmniao.dao;

import org.apache.ibatis.annotations.Param;

import com.xmniao.entity.WalletLock;

public interface WalletLockMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WalletLock record);

    int insertSelective(WalletLock record);

    WalletLock selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WalletLock record);

    int updateByPrimaryKey(WalletLock record);
    
    /**
     * 
     * 方法描述：查询是否锁定zbalance或commision
     * 创建人：jianming  
     * 创建时间：2017年8月14日 下午1:47:27   
     * @param accountid
     * @return
     */
	int getZbalanceLock(Integer accountid);

	int deleteByAccountid(@Param("accountid")Integer accountid,@Param("type") int type);
}