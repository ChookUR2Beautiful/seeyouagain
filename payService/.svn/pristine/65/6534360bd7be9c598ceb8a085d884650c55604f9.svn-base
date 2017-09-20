package com.xmniao.common;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ChinaPnRReturnData {
 

    public Map<String, Object> resolveXML(InputStream inputstram)
	    throws Exception {
	Map<String, Object> map = new HashMap<String, Object>();
	SAXReader reader = new SAXReader(); // SAXReader读取
	Document document = reader
		.read(new InputStreamReader(inputstram, "GBK"));
	Element root = document.getRootElement(); // 获取所有根元素
	list(root,map);
	return map;
    }

    public Map<String, Object> list(Element e,Map<String, Object> map) {
    	
	List<Element> list = e.elements();
	for (Element child : list) {
	    list(child,map);
	    map.put(child.getName(), child.getText());
	    System.out.println(child.getName() + "------------"
		    + map.get(child.getName()));
	}
	return map;

    }

}
