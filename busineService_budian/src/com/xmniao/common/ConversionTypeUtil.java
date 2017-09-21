package com.xmniao.common;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据类型转换工具类
 * @author  LiBingBing
 * @version  [版本号, 2015年7月28日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ConversionTypeUtil
{
    /**
     * 转换为BigDecimal类型
     * @param obj [请求参数]
     * @return BigDecimal [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static BigDecimal conversionToBigDecimal(Object obj)
    {
        return new BigDecimal(String.valueOf(obj));
    }
    
    
	/**
	 * 数组转成字符串
	 * 
	 * @param arr
	 * @param delim
	 * @return
	 */
	public static String arrayToDelimitedString(Object[] arr, String delim) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			if (i > 0) {
				sb.append(delim);
			}
			sb.append(arr[i]);
		}
		return sb.toString();
	}
	
	/**
	 * 方法描述：数组指定分隔符输出 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-7-11上午11:47:05 <br/>
	 * @param <T>
	 * @param list
	 * @param separator
	 * @return
	 */
	public static <T> String listToString(List<T> list, String separator) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			if (i == list.size() - 1) {
				sb.append(list.get(i));
			} else {
				sb.append(list.get(i));
				sb.append(separator);
			}
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		List<Integer> uidList=new ArrayList<Integer>();
		uidList.add(12);
		uidList.add(23);
		uidList.add(34);
		String listToString = ConversionTypeUtil.listToString(uidList, ",");
		System.out.println(listToString);
	}
    
    
}
