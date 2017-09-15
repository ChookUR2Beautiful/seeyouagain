/**
 * 2016年6月7日 下午8:02:36
 */
package com.xmniao.xmn.core.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @项目名称：xmnService
 * @类名称：PaymentSignUtil
 * @类描述：支付接口签名工具类
 * @创建人： zhangchangyuan
 * @创建时间 2016年6月7日 下午8:02:36
 * @version
 */
public class PaymentSignUtil {
	
	
	/**
	 * 
	* @Title: sign
	* @Description: 支付接口签名
	* @return String
	 */
	public static String sign(Map<String, String> map, String secret) {
		List<String> keys = new ArrayList<String>(map.keySet());
		Collections.sort(keys);
		StringBuffer sign = new StringBuffer();
		for(Entry<String,String> entry : map.entrySet()){
			sign.append(entry.getValue() == null ? "" : entry.getValue());
		}
		sign.append(secret);
		return MD5.Encode(sign.toString());
	}

}
