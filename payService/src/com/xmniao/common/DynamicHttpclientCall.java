package com.xmniao.common;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;

public class DynamicHttpclientCall {
      private String namespace;
     private String methodName;
      private String wsdlLocation;
      private String soapResponseData;
  
      public DynamicHttpclientCall(String namespace, String methodName,
              String wsdlLocation) {
  
          this.namespace = namespace;
          this.methodName = methodName;
          this.wsdlLocation = wsdlLocation;
      }
  
      private int invoke(Map<String, String> patameterMap) throws Exception {
          GetMethod postMethod = new GetMethod(wsdlLocation);
          String soapRequestData = buildRequestData(patameterMap);
  
          byte[] bytes = soapRequestData.getBytes("utf-8");
      InputStream inputStream = new ByteArrayInputStream(bytes, 0,
              bytes.length);
//      RequestEntity requestEntity = new InputStreamRequestEntity(inputStream,
//              bytes.length, "application/soap+xml; charset=utf-8");
//          postMethod.setRequestEntity(requestEntity);
//  
          HttpClient httpClient = new HttpClient();
          int statusCode = httpClient.executeMethod(postMethod);
          soapResponseData = postMethod.getResponseBodyAsString();
  
          return statusCode;
      }
  
      private String buildRequestData(Map<String, String> patameterMap) {
         StringBuffer soapRequestData = new StringBuffer();
          soapRequestData.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
      soapRequestData
              .append("<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\""
                      + " xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\""
                      + " xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">");
      soapRequestData.append("<soap12:Body>");
      soapRequestData.append("<" + methodName + " xmlns=\"" + namespace
              + "\">");
     soapRequestData.append("<" + methodName + "Request>");
  
          Set<String> nameSet = patameterMap.keySet();
          for (String name : nameSet) {
              soapRequestData.append("<" + name + ">" + patameterMap.get(name)
                  + "</" + name + ">");
      }
      
      soapRequestData.append("</" + methodName + "Request>");
      soapRequestData.append("</" + methodName + ">");
      soapRequestData.append("</soap12:Body>");
      soapRequestData.append("</soap12:Envelope>");
  
          return soapRequestData.toString();
      }
  
      /**
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
	  
	  //test01();
	  //test02();
	  test03();	
     
 }
  
  public static void test01() throws Exception{
	           String soapRequestData = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
			                  + "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\""
			                   + " xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\""
			                   + " xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">"
			                   + " <soap12:Body>"
			                   + " <GetAPACShippingPackage xmlns=\"http://shippingapi.ebay.cn/\">"
			                   + " <GetAPACShippingPackageRequest>"
			                   + " <TrackCode>123</TrackCode>"
			                   + " <Version>123</Version>"
			                   + " <APIDevUserID>123</APIDevUserID>"
			                   + " <APIPassword>123</APIPassword>"
			                   + " <APISellerUserID>123</APISellerUserID>"
			                   + " <MessageID>123</MessageID>"
			                   + " </GetAPACShippingPackageRequest>"
			                   + " </GetAPACShippingPackage>" + "</soap12:Body>"
			                   + " </soap12:Envelope>";
			   
			           System.out.println(soapRequestData);
			   
			           PostMethod postMethod = new PostMethod(
			                   "http://epacketws.pushauction.net/v3/orderservice.asmx?wsdl");
			   
			           // 然后把Soap请求数据添加到PostMethod中
			           byte[] b = soapRequestData.getBytes("utf-8");
			           InputStream is = new ByteArrayInputStream(b, 0, b.length);
			           RequestEntity re = new InputStreamRequestEntity(is, b.length,
			                   "application/soap+xml; charset=utf-8");
			           postMethod.setRequestEntity(re);
			   
			           // 最后生成一个HttpClient对象，并发出postMethod请求
			           HttpClient httpClient = new HttpClient();
			           int statusCode = httpClient.executeMethod(postMethod);
			           if(statusCode == 200) {
			               System.out.println("调用成功！");
			               String soapResponseData = postMethod.getResponseBodyAsString();
			               System.out.println(soapResponseData);
			           }
			           else {
			               System.out.println("调用失败！错误码：" + statusCode);
			           }
			   


  }
  public static void test02() throws Exception{
	  DynamicHttpclientCall dynamicHttpclientCall = new DynamicHttpclientCall(
		                 "http://shippingapi.ebay.cn/", "GetAPACShippingPackage",
		                 "http://epacketws.pushauction.net/v3/orderservice.asmx?wsdl");

  
          Map<String, String> patameterMap = new HashMap<String, String>();
  	          
          patameterMap.put("TrackCode", "123");
      patameterMap.put("Version", "123");
      patameterMap.put("APIDevUserID", "123");
      patameterMap.put("APIPassword", "123");
      patameterMap.put("APISellerUserID", "123");
      patameterMap.put("MessageID", "123");
      patameterMap.put("TrackCode", "123");

          
          String soapRequestData = dynamicHttpclientCall.buildRequestData(patameterMap);
          System.out.println(soapRequestData);
  
          int statusCode = dynamicHttpclientCall.invoke(patameterMap);
          if(statusCode == 200) {
             System.out.println("调用成功！");
         System.out.println(dynamicHttpclientCall.soapResponseData);
     }
     else {
         System.out.println("调用失败！错误码：" + statusCode);
     }
  }
  public static void test03() throws Exception{
      DynamicHttpclientCall dynamicHttpclientCall = new DynamicHttpclientCall(
              "http://www.sdo.com/mas/api/refund/", "refundRequest",
              "http://mas.sdo.com/api-acquire-channel/services/refundService?wsdl");
  
          Map<String, String> patameterMap = new HashMap<String, String>();
  	          
          patameterMap.put("Name", "REFUND_REQ");
      patameterMap.put("Version", "V4.4.1.1.1");
      patameterMap.put("Charset", "UTF-8");
      patameterMap.put("SenderId", "424660");
      patameterMap.put("SendTime", "20150205151524");
      patameterMap.put("MerchantNo", "424660");
      patameterMap.put("RefundOrderNo", "1502051040367392");
      patameterMap.put("OriginalOrderNo", "1502021615555748");
      patameterMap.put("RefundAmount", "0.01");
      patameterMap.put("RefundRoute", "0");
      patameterMap.put("NotifyURL", "http://192.168.20.242:8080/payService/shengRefund");
      patameterMap.put("Memo", "xunminiao");
      patameterMap.put("Ext1", "xunminiao");
      patameterMap.put("SignType", "MD5");
      patameterMap.put("SignMsg", "B9EDF09D91A9AEEB7BA957CF6FF86309");
          
          String soapRequestData = dynamicHttpclientCall.buildRequestData(patameterMap);
          System.out.println(soapRequestData);
  
          int statusCode = dynamicHttpclientCall.invoke(patameterMap);
          if(statusCode == 200) {
             System.out.println("调用成功！");
         System.out.println(dynamicHttpclientCall.soapResponseData);
     }
     else {
         System.out.println("调用失败！错误码：" + statusCode);
     }			  
  }
}
