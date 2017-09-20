package com.xmniao.xmn.core.util.holder;

public class DataAuthorityHolder {
	private static final ThreadLocal<DataAuthorityInfo> dataAuthorityHolder = new ThreadLocal<>();
	
	public static void remove(){
		dataAuthorityHolder.remove();
	}
	public static void putDataAuthorityInfo(DataAuthorityInfo authorityInfo){
		dataAuthorityHolder.set(authorityInfo);
	}
	
	public static DataAuthorityInfo getDataAuthorityInfo(){
		return dataAuthorityHolder.get();
	}
	
}
