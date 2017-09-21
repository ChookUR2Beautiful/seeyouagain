package com.xmniao.common;

import java.util.Map;

public class PageUtil {

	public static int totalPages(int pagesize, int total){
		int pageContent = 0;
		if (total % pagesize == 0) {
			pageContent = total / pagesize;
		} else {
			pageContent = total / pagesize + 1;
		}
		return pageContent;
	}
	
	public static int startPage(int page , int pageSize){
		return (page- 1 ) * pageSize;
	}
	
	public static void startPage(int page , int pageSize, Map param){
		param.put("startPage", (page - 1 ) * pageSize);
		param.put("pageSize", pageSize);
	}
}
