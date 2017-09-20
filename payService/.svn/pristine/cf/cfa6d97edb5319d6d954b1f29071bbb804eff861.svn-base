package com.xmniao.kuaiqian;

/**
 * 简易的构建XML字符工具类
 * @author ChenBo
 *
 */
public class BuildXmlUtil  {
	private static final String LS ="<";
	private static final String RS =">";
	private static final String LE ="</";
	private static final String RE ="/>";
	
	public static String initXml(){
		return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
	}
	
	//父结点开始 <XXXXXX>
	public static String parentNodeSXml(String title,String namespace){
		if(title == null){
			return null;
		}
		if(namespace == null){
			return LS+title+namespace+RS;
		}
		return LS+title+" "+namespace+RS;
	}
	
	//父结点结束 </XXXXXX>
	public static String parentNodeEXml(String title){
		if(title == null){
			return null;
		}
		
		return LE+title+RS;
	}
	
	//普通结点 <XXXX>YY</XXXX>
	public static String appenNodeXml(String key,String value){
		if(key == null || key.trim().equals("")){
			return "";
		}
		StringBuffer sb = new StringBuffer("");
		sb.append(LS).append(key).append(RS).append(value).append(LE).append(key).append(RS);
		return sb.toString();
	}
		
	public static void main(String[] args){
		StringBuffer reqXml= new StringBuffer("");
		reqXml.append(BuildXmlUtil.initXml());
		reqXml.append(BuildXmlUtil.parentNodeSXml("MasMessage", "xmlns=\"http://www.99bill.com/mas_cnp_merchant_interface\""));
		reqXml.append(BuildXmlUtil.appenNodeXml("version", "1.0"));
		reqXml.append(BuildXmlUtil.parentNodeSXml("TxnMsgContent", ""));
		reqXml.append(BuildXmlUtil.appenNodeXml("txnType", "RFD"));
		reqXml.append(BuildXmlUtil.appenNodeXml("interactiveStatus", "1"));
		reqXml.append(BuildXmlUtil.appenNodeXml("amount", "100.00"));
		reqXml.append(BuildXmlUtil.appenNodeXml("merchantId", "88888888"));
		reqXml.append(BuildXmlUtil.appenNodeXml("terminalId", "666666"));
		reqXml.append(BuildXmlUtil.appenNodeXml("entryTime", "20150122105530"));
		reqXml.append(BuildXmlUtil.appenNodeXml("externalRefNumber", "2145621141"));
		reqXml.append(BuildXmlUtil.appenNodeXml("ext", "xunminiao"));
		reqXml.append(BuildXmlUtil.appenNodeXml("ext1", "chenbo"));
		reqXml.append(BuildXmlUtil.appenNodeXml("origRefNumber", "214566351121114"));
		reqXml.append(BuildXmlUtil.parentNodeEXml("TxnMsgContent"));
		reqXml.append(BuildXmlUtil.parentNodeEXml("MasMessage"));
		System.out.println("构建的XML文件内容："+reqXml.toString());
	}
}