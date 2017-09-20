package com.xmniao.dao;

import java.util.Map;

public interface CheckWalletMapper {
	/*
	 * 查询用户钱包是否存在
	 */
	Map<String, Object> getUserPwd(Map<String, Object> paramMap);
	
	/*
	 * 检验用户、商家及合作商是否第一次提现
	 */
	int selectByUid(Map<String, Object> paramMap);
	
}
