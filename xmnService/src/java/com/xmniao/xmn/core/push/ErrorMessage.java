package com.xmniao.xmn.core.push;

/**
 * 文件名：ErrorMessage.java</br></br>
 * 功能描述：错误信息提示枚举类</br>
 * Copyright (C) 2014 呙垒西个人   版权所有。
 * @author 呙垒西
 * @version 1.0</br>
 * 2014-12-10
 */
public enum ErrorMessage {
	
	ERROR1001(1001,"报文必须包含data"),
	ERROR1002(1002,"报文必须包含source"),
	ERROR1003(1003,"data无对应值"),
	ERROR1004(1004,"source无对应值"),
	ERROR1005(1005,"非法数据源"),
	//android
	ERROR1006(1006,"参数必须包含accessid"),
	ERROR1007(1007,"参数必须包含secretkey"),
	ERROR1008(1008,"参数必须包含devicetoken"),
	ERROR1009(1009,"参数必须包含title"),
	ERROR1010(1010,"参数必须包含msg"),
	ERROR1011(1011,"accessid不能为空"),
	ERROR1012(1012,"secretkey不能为空"),
	ERROR1013(1013,"devicetoken不能为空"),
	ERROR1014(1014,"title不能为空"),
	ERROR1015(1015,"msg不能为空"),
	//IOS
	ERROR1016(1016,"参数必须包含alert"),
	ERROR1017(1017,"参数必须包含sound"),
	ERROR1018(1018,"参数必须包含category"),
	ERROR1019(1019,"alert不能为空"),
	ERROR1020(1020,"sound不能为空"),
	ERROR1021(1021,"category不能为空"),
	
	
	
	SUCCESS(0,"成功"),
	ERROR(999,"失败");
	
	private Integer error;
	private String msg;
	
	ErrorMessage(Integer error,String msg){
		this.error = error;
		this.msg = msg;
	}
	
	public Integer getError(){
		return error;
	}
	
	public String getMsg(){
		return msg;
	}
}
