package com.xmn.designer.utils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据通用验证
 * @ClassName: DataValidation 
 * @author dengqiang
 * @date 2016年9月28日 下午7:23:01 
 *
 */
@SuppressWarnings("all")
public class DataValidation {
	
	private static DataValidation dataValidation=new DataValidation();
	
	public static DataValidation getInstance(){
		return dataValidation;
	}
	
	
	/**
	 * 验证用户是不是商家新用户
	 * @Title: verifyIsNewUser 
	 * @param @param userId
	 * @param @return
	 * @param @throws Exception    设定文件 
	 * @return boolean    返回类型 
	 * @throws
	 */
	public boolean verifyIsNewUser(Long userId,Integer sellerid)throws Exception{
		return true;
	}
	
	
	/**
	 *  验证总金额 [单个红包金额  * 5 < 总金额      说明： 确保每个红包活动最少能有5个或以上获得]
	 * @Title: valiAmount 
	 * @param @param totalAmount
	 * @param @param randMoney
	 * @param @return
	 * @param @throws Exception    设定文件 
	 * @return Boolean    返回类型 
	 * @throws
	 */
	public boolean valiAmount(BigDecimal totalAmount, BigDecimal randMoney) throws Exception {
		Map<String, Object> resultMap=new HashMap<String, Object>();
		if (totalAmount == null) {
			throw new Exception("totalAmount is null");
		}
		if (randMoney == null) {
			throw new Exception("randMoney is null");
		}
		BigDecimal resultAmount =randMoney.multiply(new BigDecimal(5));
		if (totalAmount.compareTo(resultAmount) < 0) {
			return false;
		} else {
			return true;
		}
	}
	
}
