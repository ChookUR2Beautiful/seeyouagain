package com.xmniao.common;

import java.util.HashMap;
import java.util.Map;

public class RefundUtil {

	/**
	 * 返回一个Map数据
	 * @param code 状态代码
	 * @param msg 错误信息
	 * @return
	 */
	public static Map<String,String> returnMap(String code,String msg){
		return returnMap(code,msg,"");
	}
	
	/**
	 * 返回一个Map数据
	 * @param code 状态代码
	 * @param msg 错误信息
	 * @param response 正确时的附带返回信息
	 * @return
	 */
	public static Map<String,String> returnMap(String code,String msg,String response){
		Map<String,String> resultMap = new HashMap<String,String>();
		resultMap.put("code", code);
		resultMap.put("Msg",msg);
		resultMap.put("response",response);
		return resultMap;
	}
	
	
	/**
	 * 是否退款失败
	 * @param map
	 * @return
	 */
	public static boolean isRefundFail(Map<String,String> map){
		if(isRefundSuccess(map)){
			return false;
		}
		else if(isRefunding(map)){
			return false;
		}
		else{
			return true;
		}
	}
	/**
	 * 是否还在退款中
	 * @param map
	 * @return
	 */
	public static boolean isRefunding(Map<String,String> map){
		String resultCode = map.get("code");
		if(resultCode.equals(StateCodeUtil.PR_REQUEST_THIRD_SUCCESS) 		
				|| resultCode.equals(StateCodeUtil.PR_THIRD_REFUNDING)
				|| resultCode.equals(StateCodeUtil.PR_REQUEST_REPEAT)){
			return true;
		}
		else{
			return false;
		}
	}
	
	/**
	 * 是否退款成功
	 * @param map
	 * @return
	 */
	public static boolean isRefundSuccess(Map<String,String> map){
		String resultCode = map.get("code");
		if(resultCode.equals(StateCodeUtil.PR_SUCCESS) || resultCode.equals(StateCodeUtil.PR_REFUNDED)){
			return true;
		}
		else{
			return false;
		}
	}
}
