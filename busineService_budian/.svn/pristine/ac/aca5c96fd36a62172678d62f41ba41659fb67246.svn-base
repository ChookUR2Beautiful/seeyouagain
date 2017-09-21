package com.xmniao.common;

import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang.RandomStringUtils;

public class OrderSnGenerator {
	public synchronized static  String generate(){
		String orderSn = DateUtil.dateFormat(new Date(), "yyMMddHHmmss") 
				+ RandomStringUtils.randomNumeric(4);
	
		return orderSn;
	}
	
	public static String  generatorUUID(){
		String uuid = UUID.randomUUID().toString(); 
		String[] uidArray=uuid.split("-");
		return uidArray[uidArray.length-1].concat(uidArray[uidArray.length-2]);
	}
}
