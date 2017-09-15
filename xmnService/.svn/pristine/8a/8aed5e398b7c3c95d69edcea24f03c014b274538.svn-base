package com.xmniao.xmn.core.util;

import org.apache.commons.lang.reflect.MethodUtils;

import com.xmniao.xmn.core.common.ConstantDictionary.AppSourceState;

public class EnumUtil {

	/**
	 * 根据枚举code获取枚举name
	 * @param enumClass
	 * @param code
	 * @return
	 */
	public static  <E extends Enum<E>>  String  getEnumValue(final Class<E> enumClass,Integer code) {
		String enumName=null;
		E[] enumConstants = enumClass.getEnumConstants();
		  try {
			for (E e : enumConstants) {
				Object codeVal = MethodUtils.invokeMethod(e, "getCode", null);
				if(codeVal.equals(code)){
					enumName= (String) MethodUtils.invokeMethod(e,  "getName",null);
					return enumName;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	return enumName;
	}
	
	/**
	 * 根据枚举name获取枚举code
	 * @param enumClass
	 * @param name
	 * @return
	 */
	public static  <E extends Enum<E>>  Integer  getEnumCode(final Class<E> enumClass,String name) {
		Integer enumCode=null;
		E[] enumConstants = enumClass.getEnumConstants();
		  try {
			for (E e : enumConstants) {
				Object codeVal = MethodUtils.invokeMethod(e, "getName", null);
				if(codeVal.equals(name)){
					enumCode= (Integer) MethodUtils.invokeMethod(e,  "getCode",null);
					return enumCode;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	return enumCode;
	}
	public static void main(String[] args) {
		System.out.println(getEnumValue(AppSourceState.class, 1));
		System.out.println(getEnumCode(AppSourceState.class, "bird"));
	}
}
