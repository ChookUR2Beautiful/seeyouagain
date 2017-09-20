package com.xmniao.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.xmniao.entity.Wallet;

/**
 * 钱包Mapper
 * 
 * @author YangJing
 * 
 */
public interface WalletMapper {

	/**
	 * 根据用户Id获取钱包信息
	 * @param uid
	 * @return
	 */
	Map<String, Object> selectByuid(Map<String,Object> map);



	/**
	 * 根据用户Id获取钱包信息
	 * 
	 * @param paramMap
	 * @return
	 */
	Map<String, String> getWalletByUserId(Map<String, String> paramMap);

	/**
	 * 修改用户密码
	 * 
	 * @param paramMap
	 * @return
	 */
	int updatePwdBySign(Map<String, String> paramMap);

	/**
	 * 新增用户/商家钱包账号
	 * @param paramMap
	 * @return
	 */
	int addWalletByuid(Map<String,String> paramMap);
	

	/**
	 * 获取能提现佣金的用户 
	 * 
	 * @return
	 */
	List<Map<String, Object>> getMentionCommisionUsers();
	
	
	/**
	 * 获取能提现营收的用户 
	 * 
	 * @return
	 */
	List<Map<String, Object>> getMentionIncomeUsers();

	/**
	 * 
	 * 
	 * @param paramMap
	 * @return
	 */
	int updateWalletBySign(Map<String, String> paramMap);

	
	//修改钱包金额 
	int updateWalletMoney(@Param("wallet") Wallet wallet,@Param("oldSign") String oldSign);
	
	//xmer钱包金额转到会员钱包  @author chenjie 
	int updateWalletMoneyFromXmer(Map<String,Object> map);
	
	//直播钱包鸟蛋提现到balance
	int updateWalletMoneyFromLive(Map<String,Object> map);
	
	//根据钱包拿到整个wallet对象数据
	Wallet getWalletById(Integer accountid);
	
	//根据uid拿到整个wallet对象数据
	Wallet getWalletByuId(Map<String,Object> map);
	
	//根据account拿到整个wallet对象数据
	Wallet getWalletByAccount(@Param("account") String account,@Param("userType") String userType);
	/**
	 * 查询某用户的可提现金额
	 * @param paramMap  userType-->用户类型
	 * 					uId     -->用户ID
	 * @return					-->可提现金额
	 */
	double getWithdrawalsByid(Map<String,Object> paramMap);
	
	
	
	/**
	 * 获取当着指定页码的钱包信息
	 */
	List<Map<String,String>> getwelletList(Map<String,Object> map);
	
	/**
	 * 符合条件的总记录数
	 */
	int getCountPage(Map<String,Object> map);
	
	/**
	 * 总钱包数
	 * @return
	 */
	int getWalletCount(Map<String,String> map);
	
	/**
	 * 获取钱包个数（验证钱包是否存在）
	 * 
	 * @param map
	 * @return
	 */
	int getWalletNum(Map<String, String> map);
	
	/**
	 * 查询用户总金额
	 * 
	 * @param map
	 * @return
	 */
	double getAmountTotal(Map<String, Object> map);
	
	/**
	 * 查询钱包信息
	 * 
	 * @param map
	 * @return
	 */
	List<Map<String, String>> getWalletAccount(Map<String, Object> map);
	
	/**
	 * 根据类型修改余额
	 * 
	 * @param map
	 * @return
	 */
	int updateMoneyByType(Map<String, String> map);
	
	/**
	 * 
	 * @param remarks
	 * @return
	 */
	int checkReward(String remarks);

	/**
	 * 统计用户提醒金额
	 * 
	 * @param map
	 * @return
	 */
	Map<String, Object> getWithdrawalsMoney(Map<String, Object> map); 

	
	/**
	 * 有效总钱包数
	 * @return
	 */
	int getEffectiveWalletCount(Map<String,String> map);
	
	/**
	 * 查询用户的钱包列表
	 * @return
	 */
	List<Map<String,Object>> getUserWalletBalance(Map<String, Object> map);
	
	/**
	 * 查询商户的钱包列表
	 * @return
	 */
	List<Map<String,Object>> getSellerWalletBalance(Map<String, Object> map);
	
	/**
	 * 查询合作商的钱包列表
	 * @return
	 */
	List<Map<String,Object>> getJonitWalletBalance(Map<String, Object> map);
	
	/**
	 * 查询钱包
	 * @param map
	 * @return
	 */
	Map<String,String> getWalletInfoByAccount(Map<String,String> map);
	
	int updateWallet(Wallet wallet);
	
	
	/**
	 * 统计商户营收返利提现记录
	 * @param amountType
	 * @return
	 */
	int countSellerIncomeStatistics(@Param("countDate") String countDate);
	
	/**
	 * 获取商户营收返利提现记录列表
	 * @param map
	 * @return
	 */
	List<Map<String,Object>> listSellerIncomeStatistics(Map<String,Object> map);
	
	
	/**
	 * 统计商户分账返利提现记录
	 * @param amountType
	 * @return
	 */
	int countSellerCommisionStatistics(@Param("countDate") String countDate);
	
	/**
	 * 获取商户分账返利提现记录列表
	 * @param map
	 * @return
	 */
	List<Map<String,Object>> listSellerCommisionStatistics(Map<String,Object> map);


	
	/**
	 * 
	 * 方法描述：根据uid和类型获取用户钱包
	 * 创建人：jianming  
	 * 创建时间：2017年5月5日 下午2:12:14   
	 * @param uid
	 * @param typeId
	 * @return
	 */
	Integer getWalletIdByUserId(@Param("uid")Integer uid, @Param("typeId")int typeId);



	Wallet getWalletLockByUidType(@Param("uid")Integer uid,@Param("userType") Integer userType);



	Wallet getWalletLockById(Integer accountid);



	List<Wallet> getWalletByUids(@Param("uids")List<Integer> uids,@Param("typeId") Integer typeId);

	
}
