package com.xmniao.kuaiqian;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.xml.sax.InputSource;

@SuppressWarnings("unchecked")
public class ParseXMLUtil {
	
	public static ParseXMLUtil initParseXMLUtil(){
		return new ParseXMLUtil();
	}
	
	private HashMap parseXMLNode(HashMap hm,Element e){
		Element child=null; 
		
		for(Iterator childs= e.getChildren().iterator();childs.hasNext();){
			child=(Element)childs.next(); 
			hm.put(child.getName(), child.getValue()); 
		}
		return hm;
	}
	private HashMap parseXMLNodeList(HashMap hm,Element e,String flag2){
		Element child=null;   
		Element child_txn=null;
		HashMap HM_txn=null;
		List list=null;
		
		for(Iterator childs= e.getChildren().iterator();childs.hasNext();){
			child=(Element)childs.next(); 
			if(flag2.equals(child.getName())){
				list=new ArrayList();   

				for (Iterator childs2= child.getChildren().iterator();childs2.hasNext();){
					HM_txn=new HashMap();      
					child_txn=(Element)childs2.next();
					HM_txn=parseXMLNode(HM_txn,child_txn); 
					list.add(HM_txn);    
				}
				hm.put("Txn", list);
			}else{
				hm.put(child.getName(), child.getValue());
			}
		}
		return hm;
	}
	
	public Element parseXML(String resXml){
		SAXBuilder sb=new SAXBuilder(); 
        StringReader read = new StringReader(resXml);
        InputSource inSource = new InputSource(read);
		Document doc=null;  
		Element root=null; 
		try {
			doc=sb.build(inSource);		
		} catch (JDOMException e1) {
			System.out.println("抛异常");
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		if(doc!=null){
			root=doc.getRootElement();  
		}else{
			System.out.println("root");
		}
		return root;
	}
	
	public HashMap returnXMLData(Element root,String flag1,String flag2){
		HashMap xmlData=null;
		Element child=null;   
		
		if(root!=null){
			xmlData=new HashMap();
			String childName="";
			
			for (Iterator childs= root.getChildren().iterator();childs.hasNext();) {
				child=(Element)childs.next(); 
				childName=(String)child.getName();  
				
				if(flag1.equals(childName)){
					xmlData=parseXMLNode(xmlData,child);  
				}else if(flag2.equals(childName)){
					xmlData=parseXMLNode(xmlData,child);  
				}else if("ErrorMsgContent".equals(childName)){
					xmlData=parseXMLNode(xmlData,child);  
				}else{
					xmlData.put(childName, child.getValue());
				}
			}
		}
		return xmlData;
	}
	
	public HashMap returnXMLDataList(Element root,String flag1,String flag2){
		HashMap xmlData=null;	//����һ��HashMap����
		Element child=null;			//����һ��ElementԪ�ض���
		
		if(root!=null){
			xmlData=new HashMap(); //��ʼ��HashMap,��4����õ������
			String childName="";
			//���濪ʼ���ѭ��
			for (Iterator childs1= root.getChildren().iterator();childs1.hasNext();) {
				child=(Element)childs1.next(); //��ȡÿһ����Ԫ��
				childName=(String)child.getName();  //�õ��ýڵ�����
				//�����ж���4�ж�ÿ�δ��ĸ�ڵ���ȡ��XMLԪ�أ��������ϸ���ͬ�?
				if(flag1.equals(childName)){
					xmlData=parseXMLNodeList(xmlData,child,flag2);   //����parseXMLNode����õ��ýڵ��µ�����Ԫ��
				}else if("ErrorMsgContent".equals(childName)){
					xmlData=parseXMLNode(xmlData,child);   //����parseXMLNode����õ��ýڵ��µ�����Ԫ��
				}else{
					xmlData.put(childName, child.getValue());  //����Ԫ�ر��浽HashMap��
				}
			}
		}
		//ѭ������
		return xmlData;
	}
	
	@SuppressWarnings("unchecked")
	public static HashMap<String,String> paseXML(InputStream is){
		SAXBuilder sb=new SAXBuilder();
		Document doc=null;
		try {
			doc=sb.build(is);
		} catch (JDOMException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		Element root=doc.getRootElement();
		Element child=null;
		
		HashMap<String,String> childHM=new HashMap<String,String>();
		
		for (Iterator<Element> childs= root.getChildren().iterator();childs.hasNext();) {
			child=childs.next();
			childHM.put(child.getName(), child.getValue());
		}
		return childHM;
	}
	
}
