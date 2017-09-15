package com.xmniao.xmn.core.util;
/**
 *@ClassName:SaasBidTye
 *@Description:SaaS软件套餐订单编号格式
 *@author hls
 *@date:2016年5月19日下午6:14:43
 */
public class SaasBidType {
	//生成订单格式 : 160501 100655 1254。格式6位年月日+小时 分 秒+4位随机数
	public static String getBid(){
		String dateStr = DateUtil.getCurrentTimeStr();
    	String[] da = dateStr.replace(" ","-").replace(":","-").substring(2, dateStr.length()).split("-");
    	StringBuilder db = new StringBuilder();
    	for (int i = 0; i < da.length; i++) {
    		String dr = da[i];
    		db.append(dr);
		}
		return db.append(((int)(Math.random()*9000+1000))+"").toString();
	}
}
