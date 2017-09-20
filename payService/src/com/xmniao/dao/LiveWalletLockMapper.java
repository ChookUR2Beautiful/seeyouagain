package com.xmniao.dao;

import org.apache.ibatis.annotations.Param;

import com.xmniao.entity.LiveWalletLock;

public interface LiveWalletLockMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LiveWalletLock record);

    int insertSelective(LiveWalletLock record);

    LiveWalletLock selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LiveWalletLock record);

    int updateByPrimaryKey(LiveWalletLock record);

	int countZbalanceLock(Integer id);

	int deleteByWalletIdType(@Param("walletId")Integer walletId,@Param("type") Integer type);
}