package com.xmniao.xmn.core.xmnpay.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.util.NMD5;
import com.xmniao.xmn.core.xmnpay.dao.WalletDao;
import com.xmniao.xmn.core.xmnpay.entity.Bwallet;

/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：WalletService
 * 
 * 类描述： 直连支付库-钱包服务类
 * 
 * 创建人： Chen Bo
 * 
 * 创建时间：2016年3月24日 下午4:58:09 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@Service
public class WalletService{
	
	//private final Logger log = Logger.getLogger(getClass());
	
	@Autowired
	private WalletDao walletDao;
	
	
	public boolean checkAccount(String account){
		Bwallet wallet = new Bwallet();
		wallet.setAccount(account);
		return walletDao.hasWallet(wallet)==0?true:false;

	}
	
	public int addWallet(Bwallet wallet){
		wallet.setpPwd(null);
		wallet.setLastDate(new Date());
		wallet.setEffectDate(new Date());
		wallet.setApplyDate(new Date());
		wallet.setStatus(1);
		wallet.setSignType(1);
		wallet.setSellerid(0);
		wallet.setJointid(0);
		
		wallet.setSign(walletMD5(wallet));

		return walletDao.addWallet(wallet);
	}
	
	public String walletMD5(Bwallet wallet){
		final Double ZERO = new Double(0.0);
		
		StringBuffer sb = new StringBuffer();
		sb.append(wallet.getUid()==null?0:wallet.getUid());
		sb.append(wallet.getSellerid()==null?0:wallet.getSellerid());
		sb.append(wallet.getJointid()==null?0:wallet.getJointid());
		sb.append(wallet.getpPwd()==null?"":wallet.getpPwd());
		sb.append(String.format("%.2f",wallet.getAmount()==null?ZERO:wallet.getAmount()));
		//		Double.valueOf(String.valueOf(signMap.get("amount")))));
		sb.append(String.format("%.2f",wallet.getBalance()==null?ZERO:wallet.getBalance()));
		//		Double.valueOf(String.valueOf(signMap.get("balance")))));
		sb.append(String.format("%.2f",wallet.getCommision()==null?ZERO:wallet.getCommision()));
		//		Double.valueOf(String.valueOf(signMap.get("commision")))));
		sb.append(String.format("%.2f",wallet.getZbalance()==null?ZERO:wallet.getZbalance()));
		//		Double.valueOf(String.valueOf(signMap.get("zbalance")))));
		sb.append(wallet.getIntegral()==null?0:wallet.getIntegral());
		sb.append(String.format("%.2f",wallet.getSellerAmount()==null?ZERO:wallet.getSellerAmount()));		
		//		Double.valueOf(String.valueOf(signMap.get("sellerAmount")))));

		System.out.println("钱包加密：" + sb.toString());
		return NMD5.Encode(sb.toString());
	}

	public List<Bwallet> getWalletList(Object[] uIds){
		return walletDao.selectWallet(uIds);
	}
}
