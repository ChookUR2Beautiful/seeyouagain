package com.xmniao.kuaiqian;

import java.util.Map;



@SuppressWarnings("unchecked")
public class ParseUtil {
	public static Map<String,String> parseXML(String resXml){
		Map<String,String> returnRespXml=null;
		ParseXMLUtil pxu=ParseXMLUtil.initParseXMLUtil();
		if(resXml!=null){
		returnRespXml= pxu.returnXMLData(pxu.parseXML(resXml), "TxnMsgContent", "ErrorMsgContent");
		}
		return returnRespXml;
	}
}
