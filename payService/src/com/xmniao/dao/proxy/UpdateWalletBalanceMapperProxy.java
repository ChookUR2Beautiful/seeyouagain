package com.xmniao.dao.proxy;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.xmniao.dao.UpdateWalletBalanceMapper;
import com.xmniao.dao.WalletMapper;
import com.xmniao.entity.Wallet;
import com.xmniao.service.WalletService;
@Service
@Primary
public class UpdateWalletBalanceMapperProxy implements UpdateWalletBalanceMapper{

	
	@Autowired
	private UpdateWalletBalanceMapper updateWalletBalanceMapper;
	
	@Autowired
	private WalletService walletService;
	
	@Override
	public Map<String, String> selectByuid(Integer uid) {
		return updateWalletBalanceMapper.selectByuid(uid);
	}

	@Override
	public Map<String, String> selectBysellerid(Integer sellerid) {
		return updateWalletBalanceMapper.selectBysellerid(sellerid);
	}

	@Override
	public Map<String, String> selectByjointid(Integer jointid) {
		return updateWalletBalanceMapper.selectByjointid(jointid);
	}

	@Override
	public Map<String, String> getWalletByUserId(Map<String, String> paramMap) {
		return updateWalletBalanceMapper.getWalletByUserId(paramMap);
	}

	@Override
	public int updateBalanceType(Map<String, Object> paramMap) {
		Wallet w = walletService.getWalletLockByUidType(paramMap.get("uId"),paramMap.get("userType"));
		if(!"0.00".equals(paramMap.get("commision"))){
			if(w.getCommision().compareTo(new BigDecimal(paramMap.get("commision_d").toString()))>0){
				return WalletMapperProxy.COMMISION_UPDATE_STATE;
			}
		}
		if(!"0.00".equals(paramMap.get("zbalance"))){
			if(w.getZbalance().compareTo(new BigDecimal(paramMap.get("zbalance_d").toString()))>0){
				return WalletMapperProxy.ZBALANCE_UPDATE_STATE;
			}
		}
		return updateWalletBalanceMapper.updateBalanceType(paramMap);
	}

	@Override
	public int insertWalletRecord(Map<String, Object> paramMap) {
		return updateWalletBalanceMapper.insertWalletRecord(paramMap);
	}

	@Override
	public int insertWR(List<Map<String, Object>> list) {
		return updateWalletBalanceMapper.insertWR(list);
	}

	@Override
	public int getWRCount(Map<String, Object> paramMap) {
		return updateWalletBalanceMapper.getWRCount(paramMap);
	}

}
