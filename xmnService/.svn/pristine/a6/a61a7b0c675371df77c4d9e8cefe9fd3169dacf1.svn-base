package com.xmniao.xmn.core.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * 
* @projectName: xmnService 
* @ClassName: ListSortUtil    
* @Description:list结合排序工具类   
* @author: liuzhihao   
* @date: 2016年11月15日 下午6:14:57
 */
public class ListSortUtil {

//	private static int n =0;
	/**
	 * 集合排序--由大道小
	 * @param list 需要排序的集合
	 * @param filedName 集合中map对象的key
	 */
	public static void sortListMax(List<Map<Object,Object>> list,final Object filedName) {
		
		Collections.sort(list, new Comparator<Map<Object, Object>>() {
			
            public int compare(Map<Object, Object> o1, Map<Object, Object> o2) {
            	Object fileName = filedName;
				Double sort1 = Double.parseDouble(o1.get(fileName).toString());
//				n += 1;
            	Double sort2 = Double.parseDouble(o2.get(fileName).toString());
 
            	Integer sort = (int) (ArithUtil.sub(sort2, sort1)*1000);
//            	System.out.println(n);
                return sort;
            }
        });
	}
	
	/**
	 * 集合排序--由小到大
	 * @param list 需要排序的集合
	 * @param filedName 集合中map对象的key
	 */
	public static void sortListMin(List<Map<Object,Object>> list,final Object filedName) {
		
		Collections.sort(list, new Comparator<Map<Object, Object>>() {
			
            public int compare(Map<Object, Object> o1, Map<Object, Object> o2) {
            	Object fileName = filedName;
            	Double sort1 = Double.parseDouble(o1.get(filedName).toString());
            	Double sort2 = Double.parseDouble(o2.get(filedName).toString());
 
            	Integer sort = (int) (ArithUtil.sub(sort1, sort2)*1000);
            	
                return sort;
            }
        });
	}
}
