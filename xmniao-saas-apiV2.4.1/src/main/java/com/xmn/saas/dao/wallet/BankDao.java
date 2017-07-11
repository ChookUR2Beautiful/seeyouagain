package com.xmn.saas.dao.wallet;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xmn.saas.entity.wallet.BankList;


@Repository
public interface BankDao {
	
	/**
	 * 
	 * @Description: 查询银行卡列表
	 * @author xiaoxiong
	 * @date 2016年10月13日
	 */
	List<BankList> bankList();

}
