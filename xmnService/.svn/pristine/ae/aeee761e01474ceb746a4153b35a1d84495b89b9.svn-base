package com.xmniao.xmn.core.util;

import java.util.List;

/**
 * 
* @projectName: xmnService 
* @ClassName: PageUtil    
* @Description:代码分页工具类   
* @author: liuzhihao   
* @date: 2016年11月15日 下午4:11:40
 */
public class PageUtil {


	/**
	 * 程序分页函数
	 * @param pageNo
	 * @param pageSize
	 * @param list
	 * @return
	 */
	public static <T> List<T> findAllByPage(int pageNo,int pageSize,List<T> list){
		
		int rem = list.size() % pageSize;
		if(rem == 0){
			return list.subList((pageNo-1)*pageSize, pageNo*pageSize);
		}else{
			if(list.size() < pageSize){
				return list.subList(list.size()-rem, list.size());
			}else{
				return list.subList((pageNo-1)*pageSize, pageNo*pageSize-(pageSize-rem) == list.size()?list.size():pageNo*pageSize);
			}
		}
	}
}
