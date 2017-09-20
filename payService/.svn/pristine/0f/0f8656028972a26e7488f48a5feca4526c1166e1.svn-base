package com.xmniao.dao.proxy;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.xmniao.dao.WalletMapper;
import com.xmniao.entity.Wallet;
import com.xmniao.exception.WalletZbalanceLockException;
@Service
@Primary
public class WalletMapperProxy implements WalletMapper{

	public static final int ZBALANCE_UPDATE_STATE = -1;
	public static final int COMMISION_UPDATE_STATE = -2;
	
	@Autowired
	private WalletMapper walletMapper;
	
	@Override
	public Map<String, Object> selectByuid(Map<String, Object> map) {
		return walletMapper.selectByuid(map);
	}

	@Override
	public Map<String, String> getWalletByUserId(Map<String, String> paramMap) {
		return walletMapper.getWalletByUserId(paramMap);
	}

	@Override
	public int updatePwdBySign(Map<String, String> paramMap) {
		return walletMapper.updatePwdBySign(paramMap);
	}

	@Override
	public int addWalletByuid(Map<String, String> paramMap) {
		return walletMapper.addWalletByuid(paramMap);
	}

	@Override
	public List<Map<String, Object>> getMentionCommisionUsers() {
		return walletMapper.getMentionCommisionUsers();
	}

	@Override
	public List<Map<String, Object>> getMentionIncomeUsers() {
		return walletMapper.getMentionIncomeUsers();
	}

	@Override
	public int updateWalletBySign(Map<String, String> paramMap) {
		Wallet w = walletMapper.getWalletLockByUidType( new Integer(paramMap.get("uId")),new Integer(paramMap.get("typeId")));
		if(w!=null&&w.getCommision().compareTo(new BigDecimal(paramMap.get("commision")))>0){
			throw new WalletZbalanceLockException();
		}
		return walletMapper.updateWalletBySign(paramMap);
	}

	@Override
	public int updateWalletMoney(Wallet wallet, String oldSign) {
		Wallet w = walletMapper.getWalletLockById(wallet.getAccountid());
		if(w!=null&&w.getCommision().compareTo(wallet.getCommision())>0){
			throw new WalletZbalanceLockException();
		}
		if(w!=null&&w.getZbalance().compareTo(wallet.getZbalance())>0){
			throw new WalletZbalanceLockException();
		}
		return walletMapper.updateWalletMoney(wallet, oldSign);
	}

	@Override
	public int updateWalletMoneyFromXmer(Map<String, Object> map) {
		Wallet w = walletMapper.getWalletLockById(Integer.valueOf(map.get("accountid").toString()));
		if(w!=null&&w.getCommision().compareTo(new BigDecimal(map.get("commision").toString()))>0){
			throw new WalletZbalanceLockException();
		}
		return walletMapper.updateWalletMoneyFromXmer(map);
	}

	@Override
	public int updateWalletMoneyFromLive(Map<String, Object> map) {
		Wallet w = walletMapper.getWalletLockById(Integer.valueOf(map.get("accountid").toString()));
		if(w!=null&&w.getCommision().compareTo(new BigDecimal(map.get("commision").toString()))>0){
			throw new WalletZbalanceLockException();
		}
		return walletMapper.updateWalletMoneyFromLive(map);
	}

	@Override
	public Wallet getWalletById(Integer accountid) {
		return walletMapper.getWalletById(accountid);
	}

	@Override
	public Wallet getWalletByuId(Map<String, Object> map) {
		return walletMapper.getWalletByuId(map);
	}

	@Override
	public Wallet getWalletByAccount(String account, String userType) {
		return walletMapper.getWalletByAccount(account, userType);
	}

	@Override
	public double getWithdrawalsByid(Map<String, Object> paramMap) {
		return walletMapper.getWithdrawalsByid(paramMap);
	}

	@Override
	public List<Map<String, String>> getwelletList(Map<String, Object> map) {
		return walletMapper.getwelletList(map);
	}

	@Override
	public int getCountPage(Map<String, Object> map) {
		return walletMapper.getCountPage(map);
	}

	@Override
	public int getWalletCount(Map<String, String> map) {
		return walletMapper.getWalletCount(map);
	}

	@Override
	public int getWalletNum(Map<String, String> map) {
		return walletMapper.getWalletNum(map);
	}

	@Override
	public double getAmountTotal(Map<String, Object> map) {
		return walletMapper.getAmountTotal(map);
	}

	@Override
	public List<Map<String, String>> getWalletAccount(Map<String, Object> map) {
		return walletMapper.getWalletAccount(map);
	}

	@Override
	public int updateMoneyByType(Map<String, String> map) {
		return walletMapper.updateMoneyByType(map);
	}

	@Override
	public int checkReward(String remarks) {
		return walletMapper.checkReward(remarks);
	}

	@Override
	public Map<String, Object> getWithdrawalsMoney(Map<String, Object> map) {
		return walletMapper.getWithdrawalsMoney(map);
	}

	@Override
	public int getEffectiveWalletCount(Map<String, String> map) {
		return walletMapper.getEffectiveWalletCount(map);
	}

	@Override
	public List<Map<String, Object>> getUserWalletBalance(Map<String, Object> map) {
		return walletMapper.getUserWalletBalance(map);
	}

	@Override
	public List<Map<String, Object>> getSellerWalletBalance(Map<String, Object> map) {
		return walletMapper.getSellerWalletBalance(map);
	}

	@Override
	public List<Map<String, Object>> getJonitWalletBalance(Map<String, Object> map) {
		return walletMapper.getJonitWalletBalance(map);
	}

	@Override
	public Map<String, String> getWalletInfoByAccount(Map<String, String> map) {
		return walletMapper.getWalletInfoByAccount(map);
	}

	@Override
	public int updateWallet(Wallet wallet) {
		return walletMapper.updateWallet(wallet);
	}

	@Override
	public int countSellerIncomeStatistics(String countDate) {
		return walletMapper.countSellerIncomeStatistics(countDate);
	}

	@Override
	public List<Map<String, Object>> listSellerIncomeStatistics(Map<String, Object> map) {
		return walletMapper.listSellerIncomeStatistics(map);
	}

	@Override
	public int countSellerCommisionStatistics(String countDate) {
		return walletMapper.countSellerCommisionStatistics(countDate);
	}

	@Override
	public List<Map<String, Object>> listSellerCommisionStatistics(Map<String, Object> map) {
		return walletMapper.listSellerCommisionStatistics(map);
	}

	@Override
	public Integer getWalletIdByUserId(Integer uid, int typeId) {
		return walletMapper.getWalletIdByUserId(uid, typeId);
	}

	@Override
	public Wallet getWalletLockByUidType(Integer uid, Integer userType) {
		return walletMapper.getWalletLockByUidType(uid, userType);
	}

	@Override
	public Wallet getWalletLockById(Integer accountid) {
		return walletMapper.getWalletLockById(accountid);
	}

	@Override
	public List<Wallet> getWalletByUids(List<Integer> uids,Integer typeId) {
		return walletMapper.getWalletByUids(uids,typeId);
	}

}
